/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.Callable;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.collections.api.InternalIterable;
import org.eclipse.collections.api.PrimitiveIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMapIterable;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.map.sorted.SortedMapIterable;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.api.multimap.sortedbag.SortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedset.SortedSetMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;

/**
 * An extension of the {@link Assert} class, which adds useful additional "assert" methods.
 * You can import this class instead of Assert, and use it thus, e.g.:
 * <pre>
 *     Verify.assertEquals("fred", name);  // from original Assert class
 *     Verify.assertContains("fred", nameList);  // from new extensions
 *     Verify.assertBefore("fred", "jim", orderedNamesList);  // from new extensions
 * </pre>
 */
public final class Verify extends Assert
{
    private static final int MAX_DIFFERENCES = 5;
    private static final byte[] LINE_SEPARATOR = {'\n'};

    private Verify()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * Mangles the stack trace of {@link AssertionError} so that it looks like its been thrown from the line that
     * called to a custom assertion.
     * <p>
     * This method behaves identically to {@link #throwMangledException(AssertionError, int)} and is provided
     * for convenience for assert methods that only want to pop two stack frames. The only time that you would want to
     * call the other {@link #throwMangledException(AssertionError, int)} method is if you have a custom assert
     * that calls another custom assert i.e. the source line calling the custom asserts is more than two stack frames
     * away
     *
     * @param e The exception to mangle.
     * @see #throwMangledException(AssertionError, int)
     */
    public static void throwMangledException(AssertionError e)
    {
        /*
         * Note that we actually remove 3 frames from the stack trace because
         * we wrap the real method doing the work: e.fillInStackTrace() will
         * include us in the exceptions stack frame.
         */
        Verify.throwMangledException(e, 3);
    }

    /**
     * Mangles the stack trace of {@link AssertionError} so that it looks like
     * its been thrown from the line that called to a custom assertion.
     * <p>
     * This is useful for when you are in a debugging session and you want to go to the source
     * of the problem in the test case quickly. The regular use case for this would be something
     * along the lines of:
     * <pre>
     * public class TestFoo extends junit.framework.TestCase
     * {
     *   public void testFoo() throws Exception
     *   {
     *     Foo foo = new Foo();
     *     ...
     *     assertFoo(foo);
     *   }
     *
     *   // Custom assert
     *   private static void assertFoo(Foo foo)
     *   {
     *     try
     *     {
     *       assertEquals(...);
     *       ...
     *       assertSame(...);
     *     }
     *     catch (AssertionFailedException e)
     *     {
     *       AssertUtils.throwMangledException(e, 2);
     *     }
     *   }
     * }
     * </pre>
     * <p>
     * Without the {@code try ... catch} block around lines 11-13 the stack trace following a test failure
     * would look a little like:
     * <p>
     * <pre>
     * java.lang.AssertionError: ...
     *  at TestFoo.assertFoo(TestFoo.java:11)
     *  at TestFoo.testFoo(TestFoo.java:5)
     *  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     *  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
     *  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
     *  at java.lang.reflect.Method.invoke(Method.java:324)
     *  ...
     * </pre>
     * <p>
     * Note that the source of the error isn't readily apparent as the first line in the stack trace
     * is the code within the custom assert. If we were debugging the failure we would be more interested
     * in the second line of the stack trace which shows us where in our tests the assert failed.
     * <p>
     * With the {@code try ... catch} block around lines 11-13 the stack trace would look like the
     * following:
     * <p>
     * <pre>
     * java.lang.AssertionError: ...
     *  at TestFoo.testFoo(TestFoo.java:5)
     *  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     *  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
     *  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
     *  at java.lang.reflect.Method.invoke(Method.java:324)
     *  ...
     * </pre>
     * <p>
     * Here the source of the error is more visible as we can instantly see that the testFoo test is
     * failing at line 5.
     *
     * @param e           The exception to mangle.
     * @param framesToPop The number of frames to remove from the stack trace.
     * @throws AssertionError that was given as an argument with its stack trace mangled.
     */
    public static void throwMangledException(AssertionError e, int framesToPop)
    {
        e.fillInStackTrace();
        StackTraceElement[] stackTrace = e.getStackTrace();
        StackTraceElement[] newStackTrace = new StackTraceElement[stackTrace.length - framesToPop];
        System.arraycopy(stackTrace, framesToPop, newStackTrace, 0, newStackTrace.length);
        e.setStackTrace(newStackTrace);
        throw e;
    }

    public static void fail(String message, Throwable cause)
    {
        AssertionError failedException = new AssertionError(message, cause);
        Verify.throwMangledException(failedException);
    }

    /**
     * Assert that two items are not the same. If one item is null, the the other must be non-null.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(String, Object, Object)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(String itemsName, Object item1, Object item2)
    {
        try
        {
            if (Comparators.nullSafeEquals(item1, item2) || Comparators.nullSafeEquals(item2, item1))
            {
                Assert.fail(itemsName + " should not be equal, item1:<" + item1 + ">, item2:<" + item2 + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that two items are not the same. If one item is null, the the other must be non-null.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(Object, Object)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(Object item1, Object item2)
    {
        try
        {
            Verify.assertNotEquals("items", item1, item2);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two Strings are not equal.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(String, Object, Object)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(String itemName, String notExpected, String actual)
    {
        try
        {
            if (Comparators.nullSafeEquals(notExpected, actual))
            {
                Assert.fail(itemName + " should not equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two Strings are not equal.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(Object, Object)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(String notExpected, String actual)
    {
        try
        {
            Verify.assertNotEquals("string", notExpected, actual);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two doubles are not equal concerning a delta. If the expected value is infinity then the delta value
     * is ignored.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(String, double, double, double)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(String itemName, double notExpected, double actual, double delta)
    {
        // handle infinity specially since subtracting to infinite values gives NaN and the
        // the following test fails
        try
        {
            //noinspection FloatingPointEquality
            if (Double.isInfinite(notExpected) && notExpected == actual || Math.abs(notExpected - actual) <= delta)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two doubles are not equal concerning a delta. If the expected value is infinity then the delta value
     * is ignored.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(double, double, double)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(double notExpected, double actual, double delta)
    {
        try
        {
            Verify.assertNotEquals("double", notExpected, actual, delta);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two floats are not equal concerning a delta. If the expected value is infinity then the delta value
     * is ignored.
     */
    public static void assertNotEquals(String itemName, float notExpected, float actual, float delta)
    {
        try
        {
            // handle infinity specially since subtracting to infinite values gives NaN and the
            // the following test fails
            //noinspection FloatingPointEquality
            if (Float.isInfinite(notExpected) && notExpected == actual || Math.abs(notExpected - actual) <= delta)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two floats are not equal concerning a delta. If the expected value is infinity then the delta value
     * is ignored.
     */
    public static void assertNotEquals(float expected, float actual, float delta)
    {
        try
        {
            Verify.assertNotEquals("float", expected, actual, delta);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two longs are not equal.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(String, long, long)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(String itemName, long notExpected, long actual)
    {
        try
        {
            if (notExpected == actual)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two longs are not equal.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(long, long)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(long notExpected, long actual)
    {
        try
        {
            Verify.assertNotEquals("long", notExpected, actual);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two booleans are not equal.
     */
    public static void assertNotEquals(String itemName, boolean notExpected, boolean actual)
    {
        try
        {
            if (notExpected == actual)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two booleans are not equal.
     */
    public static void assertNotEquals(boolean notExpected, boolean actual)
    {
        try
        {
            Verify.assertNotEquals("boolean", notExpected, actual);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two bytes are not equal.
     */
    public static void assertNotEquals(String itemName, byte notExpected, byte actual)
    {
        try
        {
            if (notExpected == actual)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two bytes are not equal.
     */
    public static void assertNotEquals(byte notExpected, byte actual)
    {
        try
        {
            Verify.assertNotEquals("byte", notExpected, actual);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two chars are not equal.
     */
    public static void assertNotEquals(String itemName, char notExpected, char actual)
    {
        try
        {
            if (notExpected == actual)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two chars are not equal.
     */
    public static void assertNotEquals(char notExpected, char actual)
    {
        try
        {
            Verify.assertNotEquals("char", notExpected, actual);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two shorts are not equal.
     */
    public static void assertNotEquals(String itemName, short notExpected, short actual)
    {
        try
        {
            if (notExpected == actual)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two shorts are not equal.
     */
    public static void assertNotEquals(short notExpected, short actual)
    {
        try
        {
            Verify.assertNotEquals("short", notExpected, actual);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two ints are not equal.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(String, long, long)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(String itemName, int notExpected, int actual)
    {
        try
        {
            if (notExpected == actual)
            {
                Assert.fail(itemName + " should not be equal:<" + notExpected + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that two ints are not equal.
     *
     * @deprecated in 3.0. Use {@link Assert#assertNotEquals(long, long)} in JUnit 4.11 instead.
     */
    @Deprecated
    public static void assertNotEquals(int notExpected, int actual)
    {
        try
        {
            Verify.assertNotEquals("int", notExpected, actual);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} is empty.
     */
    public static void assertEmpty(Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertEmpty("iterable", actualIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} is empty.
     */
    public static void assertEmpty(String iterableName, Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, actualIterable);

            if (Iterate.notEmpty(actualIterable))
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + Iterate.sizeOf(actualIterable) + '>');
            }
            if (!Iterate.isEmpty(actualIterable))
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + Iterate.sizeOf(actualIterable) + '>');
            }
            if (Iterate.sizeOf(actualIterable) != 0)
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + Iterate.sizeOf(actualIterable) + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} is empty.
     */
    public static void assertEmpty(MutableMapIterable<?, ?> actualMutableMapIterable)
    {
        try
        {
            Verify.assertEmpty("mutableMapIterable", actualMutableMapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} is empty.
     */
    public static void assertEmpty(String mutableMapIterableName, MutableMapIterable<?, ?> actualMutableMapIterable)
    {
        try
        {
            Verify.assertObjectNotNull(mutableMapIterableName, actualMutableMapIterable);

            if (Iterate.notEmpty(actualMutableMapIterable))
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + Iterate.sizeOf(actualMutableMapIterable) + '>');
            }
            if (!Iterate.isEmpty(actualMutableMapIterable))
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + Iterate.sizeOf(actualMutableMapIterable) + '>');
            }
            if (!actualMutableMapIterable.isEmpty())
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + Iterate.sizeOf(actualMutableMapIterable) + '>');
            }
            if (actualMutableMapIterable.notEmpty())
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + Iterate.sizeOf(actualMutableMapIterable) + '>');
            }
            if (actualMutableMapIterable.size() != 0)
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + actualMutableMapIterable.size() + '>');
            }
            if (actualMutableMapIterable.keySet().size() != 0)
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + actualMutableMapIterable.keySet().size() + '>');
            }
            if (actualMutableMapIterable.values().size() != 0)
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + actualMutableMapIterable.values().size() + '>');
            }
            if (actualMutableMapIterable.entrySet().size() != 0)
            {
                Assert.fail(mutableMapIterableName + " should be empty; actual size:<" + actualMutableMapIterable.entrySet().size() + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link PrimitiveIterable} is empty.
     */
    public static void assertEmpty(PrimitiveIterable primitiveIterable)
    {
        try
        {
            Verify.assertEmpty("primitiveIterable", primitiveIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link PrimitiveIterable} is empty.
     */
    public static void assertEmpty(String iterableName, PrimitiveIterable primitiveIterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, primitiveIterable);

            if (primitiveIterable.notEmpty())
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + primitiveIterable.size() + '>');
            }
            if (!primitiveIterable.isEmpty())
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + primitiveIterable.size() + '>');
            }
            if (primitiveIterable.size() != 0)
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + primitiveIterable.size() + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} is empty.
     */
    public static void assertIterableEmpty(Iterable<?> iterable)
    {
        try
        {
            Verify.assertIterableEmpty("iterable", iterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} is empty.
     */
    public static void assertIterableEmpty(String iterableName, Iterable<?> iterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, iterable);

            if (Iterate.notEmpty(iterable))
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + Iterate.sizeOf(iterable) + '>');
            }
            if (!Iterate.isEmpty(iterable))
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + Iterate.sizeOf(iterable) + '>');
            }
            if (Iterate.sizeOf(iterable) != 0)
            {
                Assert.fail(iterableName + " should be empty; actual size:<" + Iterate.sizeOf(iterable) + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given object is an instanceof expectedClassType.
     */
    public static void assertInstanceOf(Class<?> expectedClassType, Object actualObject)
    {
        try
        {
            Verify.assertInstanceOf(actualObject.getClass().getName(), expectedClassType, actualObject);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given object is an instanceof expectedClassType.
     */
    public static void assertInstanceOf(String objectName, Class<?> expectedClassType, Object actualObject)
    {
        try
        {
            if (!expectedClassType.isInstance(actualObject))
            {
                Assert.fail(objectName + " is not an instance of " + expectedClassType.getName());
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given object is not an instanceof expectedClassType.
     */
    public static void assertNotInstanceOf(Class<?> expectedClassType, Object actualObject)
    {
        try
        {
            Verify.assertNotInstanceOf(actualObject.getClass().getName(), expectedClassType, actualObject);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given object is not an instanceof expectedClassType.
     */
    public static void assertNotInstanceOf(String objectName, Class<?> expectedClassType, Object actualObject)
    {
        try
        {
            if (expectedClassType.isInstance(actualObject))
            {
                Assert.fail(objectName + " is an instance of " + expectedClassType.getName());
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} is empty.
     */
    public static void assertEmpty(Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertEmpty("map", actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Multimap} is empty.
     */
    public static void assertEmpty(Multimap<?, ?> actualMultimap)
    {
        try
        {
            Verify.assertEmpty("multimap", actualMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Multimap} is empty.
     */
    public static void assertEmpty(String multimapName, Multimap<?, ?> actualMultimap)
    {
        try
        {
            Verify.assertObjectNotNull(multimapName, actualMultimap);

            if (actualMultimap.notEmpty())
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.size() + '>');
            }
            if (!actualMultimap.isEmpty())
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.size() + '>');
            }
            if (actualMultimap.size() != 0)
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.size() + '>');
            }
            if (actualMultimap.sizeDistinct() != 0)
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.size() + '>');
            }
            if (actualMultimap.keyBag().size() != 0)
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.keyBag().size() + '>');
            }
            if (actualMultimap.keysView().size() != 0)
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.keysView().size() + '>');
            }
            if (actualMultimap.valuesView().size() != 0)
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.valuesView().size() + '>');
            }
            if (actualMultimap.keyValuePairsView().size() != 0)
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.keyValuePairsView().size() + '>');
            }
            if (actualMultimap.keyMultiValuePairsView().size() != 0)
            {
                Assert.fail(multimapName + " should be empty; actual size:<" + actualMultimap.keyMultiValuePairsView().size() + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} is empty.
     */
    public static void assertEmpty(String mapName, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertObjectNotNull(mapName, actualMap);

            if (!actualMap.isEmpty())
            {
                Assert.fail(mapName + " should be empty; actual size:<" + actualMap.size() + '>');
            }
            if (actualMap.size() != 0)
            {
                Assert.fail(mapName + " should be empty; actual size:<" + actualMap.size() + '>');
            }
            if (actualMap.keySet().size() != 0)
            {
                Assert.fail(mapName + " should be empty; actual size:<" + actualMap.keySet().size() + '>');
            }
            if (actualMap.values().size() != 0)
            {
                Assert.fail(mapName + " should be empty; actual size:<" + actualMap.values().size() + '>');
            }
            if (actualMap.entrySet().size() != 0)
            {
                Assert.fail(mapName + " should be empty; actual size:<" + actualMap.entrySet().size() + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} is <em>not</em> empty.
     */
    public static void assertNotEmpty(Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertNotEmpty("iterable", actualIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} is <em>not</em> empty.
     */
    public static void assertNotEmpty(String iterableName, Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, actualIterable);
            Assert.assertFalse(iterableName + " should be non-empty, but was empty", Iterate.isEmpty(actualIterable));
            Assert.assertTrue(iterableName + " should be non-empty, but was empty", Iterate.notEmpty(actualIterable));
            Assert.assertNotEquals(iterableName + " should be non-empty, but was empty", 0, Iterate.sizeOf(actualIterable));
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} is <em>not</em> empty.
     */
    public static void assertNotEmpty(MutableMapIterable<?, ?> actualMutableMapIterable)
    {
        try
        {
            Verify.assertNotEmpty("mutableMapIterable", actualMutableMapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} is <em>not</em> empty.
     */
    public static void assertNotEmpty(String mutableMapIterableName, MutableMapIterable<?, ?> actualMutableMapIterable)
    {
        try
        {
            Verify.assertObjectNotNull(mutableMapIterableName, actualMutableMapIterable);
            Assert.assertFalse(mutableMapIterableName + " should be non-empty, but was empty", Iterate.isEmpty(actualMutableMapIterable));
            Assert.assertTrue(mutableMapIterableName + " should be non-empty, but was empty", Iterate.notEmpty(actualMutableMapIterable));
            Assert.assertTrue(mutableMapIterableName + " should be non-empty, but was empty", actualMutableMapIterable.notEmpty());
            Assert.assertNotEquals(mutableMapIterableName + " should be non-empty, but was empty", 0, actualMutableMapIterable.size());
            Assert.assertNotEquals(mutableMapIterableName + " should be non-empty, but was empty", 0, actualMutableMapIterable.keySet().size());
            Assert.assertNotEquals(mutableMapIterableName + " should be non-empty, but was empty", 0, actualMutableMapIterable.values().size());
            Assert.assertNotEquals(mutableMapIterableName + " should be non-empty, but was empty", 0, actualMutableMapIterable.entrySet().size());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link PrimitiveIterable} is <em>not</em> empty.
     */
    public static void assertNotEmpty(PrimitiveIterable primitiveIterable)
    {
        try
        {
            Verify.assertNotEmpty("primitiveIterable", primitiveIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link PrimitiveIterable} is <em>not</em> empty.
     */
    public static void assertNotEmpty(String iterableName, PrimitiveIterable primitiveIterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, primitiveIterable);
            Assert.assertFalse(iterableName + " should be non-empty, but was empty", primitiveIterable.isEmpty());
            Assert.assertTrue(iterableName + " should be non-empty, but was empty", primitiveIterable.notEmpty());
            Assert.assertNotEquals(iterableName + " should be non-empty, but was empty", 0, primitiveIterable.size());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} is <em>not</em> empty.
     */
    public static void assertIterableNotEmpty(Iterable<?> iterable)
    {
        try
        {
            Verify.assertIterableNotEmpty("iterable", iterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} is <em>not</em> empty.
     */
    public static void assertIterableNotEmpty(String iterableName, Iterable<?> iterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, iterable);
            Assert.assertFalse(iterableName + " should be non-empty, but was empty", Iterate.isEmpty(iterable));
            Assert.assertTrue(iterableName + " should be non-empty, but was empty", Iterate.notEmpty(iterable));
            Assert.assertNotEquals(iterableName + " should be non-empty, but was empty", 0, Iterate.sizeOf(iterable));
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} is <em>not</em> empty.
     */
    public static void assertNotEmpty(Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertNotEmpty("map", actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} is <em>not</em> empty.
     */
    public static void assertNotEmpty(String mapName, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertObjectNotNull(mapName, actualMap);
            Assert.assertFalse(mapName + " should be non-empty, but was empty", actualMap.isEmpty());
            Assert.assertNotEquals(mapName + " should be non-empty, but was empty", 0, actualMap.size());
            Assert.assertNotEquals(mapName + " should be non-empty, but was empty", 0, actualMap.keySet().size());
            Assert.assertNotEquals(mapName + " should be non-empty, but was empty", 0, actualMap.values().size());
            Assert.assertNotEquals(mapName + " should be non-empty, but was empty", 0, actualMap.entrySet().size());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Multimap} is <em>not</em> empty.
     */
    public static void assertNotEmpty(Multimap<?, ?> actualMultimap)
    {
        try
        {
            Verify.assertNotEmpty("multimap", actualMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Multimap} is <em>not</em> empty.
     */
    public static void assertNotEmpty(String multimapName, Multimap<?, ?> actualMultimap)
    {
        try
        {
            Verify.assertObjectNotNull(multimapName, actualMultimap);
            Assert.assertTrue(multimapName + " should be non-empty, but was empty", actualMultimap.notEmpty());
            Assert.assertFalse(multimapName + " should be non-empty, but was empty", actualMultimap.isEmpty());
            Assert.assertNotEquals(multimapName + " should be non-empty, but was empty", 0, actualMultimap.size());
            Assert.assertNotEquals(multimapName + " should be non-empty, but was empty", 0, actualMultimap.sizeDistinct());
            Assert.assertNotEquals(multimapName + " should be non-empty, but was empty", 0, actualMultimap.keyBag().size());
            Assert.assertNotEquals(multimapName + " should be non-empty, but was empty", 0, actualMultimap.keysView().size());
            Assert.assertNotEquals(multimapName + " should be non-empty, but was empty", 0, actualMultimap.valuesView().size());
            Assert.assertNotEquals(multimapName + " should be non-empty, but was empty", 0, actualMultimap.keyValuePairsView().size());
            Assert.assertNotEquals(multimapName + " should be non-empty, but was empty", 0, actualMultimap.keyMultiValuePairsView().size());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertNotEmpty(String itemsName, T[] items)
    {
        try
        {
            Verify.assertObjectNotNull(itemsName, items);
            Verify.assertNotEquals(itemsName, 0, items.length);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertNotEmpty(T[] items)
    {
        try
        {
            Verify.assertNotEmpty("items", items);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given array.
     */
    public static void assertSize(int expectedSize, Object[] actualArray)
    {
        try
        {
            Verify.assertSize("array", expectedSize, actualArray);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given array.
     */
    public static void assertSize(String arrayName, int expectedSize, Object[] actualArray)
    {
        try
        {
            Assert.assertNotNull(arrayName + " should not be null", actualArray);

            int actualSize = actualArray.length;
            if (actualSize != expectedSize)
            {
                Assert.fail("Incorrect size for "
                        + arrayName
                        + "; expected:<"
                        + expectedSize
                        + "> but was:<"
                        + actualSize
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Iterable}.
     */
    public static void assertSize(int expectedSize, Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertSize("iterable", expectedSize, actualIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Iterable}.
     */
    public static void assertSize(
            String iterableName,
            int expectedSize,
            Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, actualIterable);

            int actualSize = Iterate.sizeOf(actualIterable);
            if (actualSize != expectedSize)
            {
                Assert.fail("Incorrect size for "
                        + iterableName
                        + "; expected:<"
                        + expectedSize
                        + "> but was:<"
                        + actualSize
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link PrimitiveIterable}.
     */
    public static void assertSize(int expectedSize, PrimitiveIterable primitiveIterable)
    {
        try
        {
            Verify.assertSize("primitiveIterable", expectedSize, primitiveIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link PrimitiveIterable}.
     */
    public static void assertSize(
            String primitiveIterableName,
            int expectedSize,
            PrimitiveIterable actualPrimitiveIterable)
    {
        try
        {
            Verify.assertObjectNotNull(primitiveIterableName, actualPrimitiveIterable);

            int actualSize = actualPrimitiveIterable.size();
            if (actualSize != expectedSize)
            {
                Assert.fail("Incorrect size for "
                        + primitiveIterableName
                        + "; expected:<"
                        + expectedSize
                        + "> but was:<"
                        + actualSize
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Iterable}.
     */
    public static void assertIterableSize(int expectedSize, Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertIterableSize("iterable", expectedSize, actualIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Iterable}.
     */
    public static void assertIterableSize(
            String iterableName,
            int expectedSize,
            Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertObjectNotNull(iterableName, actualIterable);

            int actualSize = Iterate.sizeOf(actualIterable);
            if (actualSize != expectedSize)
            {
                Assert.fail("Incorrect size for "
                        + iterableName
                        + "; expected:<"
                        + expectedSize
                        + "> but was:<"
                        + actualSize
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Map}.
     */
    public static void assertSize(String mapName, int expectedSize, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertSize(mapName, expectedSize, actualMap.keySet());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Map}.
     */
    public static void assertSize(int expectedSize, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertSize("map", expectedSize, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Multimap}.
     */
    public static void assertSize(int expectedSize, Multimap<?, ?> actualMultimap)
    {
        try
        {
            Verify.assertSize("multimap", expectedSize, actualMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link Multimap}.
     */
    public static void assertSize(String multimapName, int expectedSize, Multimap<?, ?> actualMultimap)
    {
        try
        {
            int actualSize = actualMultimap.size();
            if (actualSize != expectedSize)
            {
                Assert.fail("Incorrect size for "
                        + multimapName
                        + "; expected:<"
                        + expectedSize
                        + "> but was:<"
                        + actualSize
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link MutableMapIterable}.
     */
    public static void assertSize(int expectedSize, MutableMapIterable<?, ?> mutableMapIterable)
    {
        try
        {
            Verify.assertSize("map", expectedSize, mutableMapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link MutableMapIterable}.
     */
    public static void assertSize(String mapName, int expectedSize, MutableMapIterable<?, ?> mutableMapIterable)
    {
        try
        {
            int actualSize = mutableMapIterable.size();
            if (actualSize != expectedSize)
            {
                Assert.fail("Incorrect size for " + mapName + "; expected:<" + expectedSize + "> but was:<" + actualSize + '>');
            }
            int keySetSize = mutableMapIterable.keySet().size();
            if (keySetSize != expectedSize)
            {
                Assert.fail("Incorrect size for " + mapName + ".keySet(); expected:<" + expectedSize + "> but was:<" + actualSize + '>');
            }
            int valuesSize = mutableMapIterable.values().size();
            if (valuesSize != expectedSize)
            {
                Assert.fail("Incorrect size for " + mapName + ".values(); expected:<" + expectedSize + "> but was:<" + actualSize + '>');
            }
            int entrySetSize = mutableMapIterable.entrySet().size();
            if (entrySetSize != expectedSize)
            {
                Assert.fail("Incorrect size for " + mapName + ".entrySet(); expected:<" + expectedSize + "> but was:<" + actualSize + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link ImmutableSet}.
     */
    public static void assertSize(int expectedSize, ImmutableSet<?> actualImmutableSet)
    {
        try
        {
            Verify.assertSize("immutable set", expectedSize, actualImmutableSet);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the size of the given {@link ImmutableSet}.
     */
    public static void assertSize(String immutableSetName, int expectedSize, ImmutableSet<?> actualImmutableSet)
    {
        try
        {
            int actualSize = actualImmutableSet.size();
            if (actualSize != expectedSize)
            {
                Assert.fail("Incorrect size for "
                        + immutableSetName
                        + "; expected:<"
                        + expectedSize
                        + "> but was:<"
                        + actualSize
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code stringToFind} is contained within the {@code stringToSearch}.
     */
    public static void assertContains(String stringToFind, String stringToSearch)
    {
        try
        {
            Verify.assertContains("string", stringToFind, stringToSearch);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code unexpectedString} is <em>not</em> contained within the {@code stringToSearch}.
     */
    public static void assertNotContains(String unexpectedString, String stringToSearch)
    {
        try
        {
            Verify.assertNotContains("string", unexpectedString, stringToSearch);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code stringToFind} is contained within the {@code stringToSearch}.
     */
    public static void assertContains(String stringName, String stringToFind, String stringToSearch)
    {
        try
        {
            Assert.assertNotNull("stringToFind should not be null", stringToFind);
            Assert.assertNotNull("stringToSearch should not be null", stringToSearch);

            if (!stringToSearch.contains(stringToFind))
            {
                Assert.fail(stringName
                        + " did not contain stringToFind:<"
                        + stringToFind
                        + "> in stringToSearch:<"
                        + stringToSearch
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code unexpectedString} is <em>not</em> contained within the {@code stringToSearch}.
     */
    public static void assertNotContains(String stringName, String unexpectedString, String stringToSearch)
    {
        try
        {
            Assert.assertNotNull("unexpectedString should not be null", unexpectedString);
            Assert.assertNotNull("stringToSearch should not be null", stringToSearch);

            if (stringToSearch.contains(unexpectedString))
            {
                Assert.fail(stringName
                        + " contains unexpectedString:<"
                        + unexpectedString
                        + "> in stringToSearch:<"
                        + stringToSearch
                        + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertCount(
            int expectedCount,
            Iterable<T> iterable,
            Predicate<? super T> predicate)
    {
        Assert.assertEquals(expectedCount, Iterate.count(iterable, predicate));
    }

    public static <T> void assertAllSatisfy(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        try
        {
            Verify.assertAllSatisfy("The following items failed to satisfy the condition", iterable, predicate);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertAllSatisfy(Map<K, V> map, Predicate<? super V> predicate)
    {
        try
        {
            Verify.assertAllSatisfy(map.values(), predicate);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertAllSatisfy(String message, Iterable<T> iterable, Predicate<? super T> predicate)
    {
        try
        {
            MutableList<T> unacceptable = Iterate.reject(iterable, predicate, Lists.mutable.of());
            if (unacceptable.notEmpty())
            {
                Assert.fail(message + " <" + unacceptable + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertAnySatisfy(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        try
        {
            Verify.assertAnySatisfy("No items satisfied the condition", iterable, predicate);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertAnySatisfy(Map<K, V> map, Predicate<? super V> predicate)
    {
        try
        {
            Verify.assertAnySatisfy(map.values(), predicate);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertAnySatisfy(String message, Iterable<T> iterable, Predicate<? super T> predicate)
    {
        try
        {
            Assert.assertTrue(message, Predicates.<T>anySatisfy(predicate).accept(iterable));
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertNoneSatisfy(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        try
        {
            Verify.assertNoneSatisfy("The following items satisfied the condition", iterable, predicate);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertNoneSatisfy(Map<K, V> map, Predicate<? super V> predicate)
    {
        try
        {
            Verify.assertNoneSatisfy(map.values(), predicate);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertNoneSatisfy(String message, Iterable<T> iterable, Predicate<? super T> predicate)
    {
        try
        {
            MutableList<T> unacceptable = Iterate.select(iterable, predicate, Lists.mutable.empty());
            if (unacceptable.notEmpty())
            {
                Assert.fail(message + " <" + unacceptable + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(Map<?, ?> actualMap, Object... keyValues)
    {
        try
        {
            Verify.assertContainsAllKeyValues("map", actualMap, keyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(
            String mapName,
            Map<?, ?> actualMap,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            if (expectedKeyValues.length % 2 != 0)
            {
                Assert.fail("Odd number of keys and values (every key must have a value)");
            }

            Verify.assertObjectNotNull(mapName, actualMap);
            Verify.assertMapContainsKeys(mapName, actualMap, expectedKeyValues);
            Verify.assertMapContainsValues(mapName, actualMap, expectedKeyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MapIterable} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(MapIterable<?, ?> mapIterable, Object... keyValues)
    {
        try
        {
            Verify.assertContainsAllKeyValues("map", mapIterable, keyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MapIterable} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(
            String mapIterableName,
            MapIterable<?, ?> mapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            if (expectedKeyValues.length % 2 != 0)
            {
                Assert.fail("Odd number of keys and values (every key must have a value)");
            }

            Verify.assertObjectNotNull(mapIterableName, mapIterable);
            Verify.assertMapContainsKeys(mapIterableName, mapIterable, expectedKeyValues);
            Verify.assertMapContainsValues(mapIterableName, mapIterable, expectedKeyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(MutableMapIterable<?, ?> mutableMapIterable, Object... keyValues)
    {
        try
        {
            Verify.assertContainsAllKeyValues("map", mutableMapIterable, keyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(
            String mutableMapIterableName,
            MutableMapIterable<?, ?> mutableMapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            if (expectedKeyValues.length % 2 != 0)
            {
                Assert.fail("Odd number of keys and values (every key must have a value)");
            }

            Verify.assertObjectNotNull(mutableMapIterableName, mutableMapIterable);
            Verify.assertMapContainsKeys(mutableMapIterableName, mutableMapIterable, expectedKeyValues);
            Verify.assertMapContainsValues(mutableMapIterableName, mutableMapIterable, expectedKeyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableMapIterable} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(ImmutableMapIterable<?, ?> immutableMapIterable, Object... keyValues)
    {
        try
        {
            Verify.assertContainsAllKeyValues("map", immutableMapIterable, keyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableMapIterable} contains all of the given keys and values.
     */
    public static void assertContainsAllKeyValues(
            String immutableMapIterableName,
            ImmutableMapIterable<?, ?> immutableMapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            if (expectedKeyValues.length % 2 != 0)
            {
                Assert.fail("Odd number of keys and values (every key must have a value)");
            }

            Verify.assertObjectNotNull(immutableMapIterableName, immutableMapIterable);
            Verify.assertMapContainsKeys(immutableMapIterableName, immutableMapIterable, expectedKeyValues);
            Verify.assertMapContainsValues(immutableMapIterableName, immutableMapIterable, expectedKeyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void denyContainsAny(Collection<?> actualCollection, Object... items)
    {
        try
        {
            Verify.denyContainsAny("collection", actualCollection, items);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertContainsNone(Collection<?> actualCollection, Object... items)
    {
        try
        {
            Verify.denyContainsAny("collection", actualCollection, items);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} contains the given item.
     */
    public static void assertContains(Object expectedItem, Collection<?> actualCollection)
    {
        try
        {
            Verify.assertContains("collection", expectedItem, actualCollection);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} contains the given item.
     */
    public static void assertContains(
            String collectionName,
            Object expectedItem,
            Collection<?> actualCollection)
    {
        try
        {
            Verify.assertObjectNotNull(collectionName, actualCollection);

            if (!actualCollection.contains(expectedItem))
            {
                Assert.fail(collectionName + " did not contain expectedItem:<" + expectedItem + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableCollection} contains the given item.
     */
    public static void assertContains(Object expectedItem, ImmutableCollection<?> actualImmutableCollection)
    {
        try
        {
            Verify.assertContains("ImmutableCollection", expectedItem, actualImmutableCollection);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableCollection} contains the given item.
     */
    public static void assertContains(
            String immutableCollectionName,
            Object expectedItem,
            ImmutableCollection<?> actualImmutableCollection)
    {
        try
        {
            Verify.assertObjectNotNull(immutableCollectionName, actualImmutableCollection);

            if (!actualImmutableCollection.contains(expectedItem))
            {
                Assert.fail(immutableCollectionName + " did not contain expectedItem:<" + expectedItem + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertContainsAll(
            Iterable<?> iterable,
            Object... items)
    {
        try
        {
            Verify.assertContainsAll("iterable", iterable, items);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertContainsAll(
            String collectionName,
            Iterable<?> iterable,
            Object... items)
    {
        try
        {
            Verify.assertObjectNotNull(collectionName, iterable);

            Verify.assertNotEmpty("Expected items in assertion", items);

            Predicate<Object> containsPredicate = each -> Iterate.contains(iterable, each);

            if (!ArrayIterate.allSatisfy(items, containsPredicate))
            {
                ImmutableList<Object> result = Lists.immutable.of(items).newWithoutAll(iterable);
                Assert.fail(collectionName + " did not contain these items" + ":<" + result + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertListsEqual(List<?> expectedList, List<?> actualList)
    {
        try
        {
            Verify.assertListsEqual("list", expectedList, actualList);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertListsEqual(String listName, List<?> expectedList, List<?> actualList)
    {
        try
        {
            if (expectedList == null && actualList == null)
            {
                return;
            }
            Assert.assertNotNull(expectedList);
            Assert.assertNotNull(actualList);
            Assert.assertEquals(listName + " size", expectedList.size(), actualList.size());
            for (int index = 0; index < actualList.size(); index++)
            {
                Object eachExpected = expectedList.get(index);
                Object eachActual = actualList.get(index);
                if (!Comparators.nullSafeEquals(eachExpected, eachActual))
                {
                    junit.framework.Assert.failNotEquals(listName + " first differed at element [" + index + "];", eachExpected, eachActual);
                }
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSetsEqual(Set<?> expectedSet, Set<?> actualSet)
    {
        try
        {
            Verify.assertSetsEqual("set", expectedSet, actualSet);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSetsEqual(String setName, Set<?> expectedSet, Set<?> actualSet)
    {
        try
        {
            if (expectedSet == null)
            {
                Assert.assertNull(setName + " should be null", actualSet);
                return;
            }

            Verify.assertObjectNotNull(setName, actualSet);
            Verify.assertSize(setName, expectedSet.size(), actualSet);

            if (!actualSet.equals(expectedSet))
            {
                MutableSet<?> inExpectedOnlySet = UnifiedSet.newSet(expectedSet);
                inExpectedOnlySet.removeAll(actualSet);

                int numberDifferences = inExpectedOnlySet.size();
                String message = setName + ": " + numberDifferences + " elements different.";

                if (numberDifferences > MAX_DIFFERENCES)
                {
                    Assert.fail(message);
                }

                MutableSet<?> inActualOnlySet = UnifiedSet.newSet(actualSet);
                inActualOnlySet.removeAll(expectedSet);

                //noinspection UseOfObsoleteAssert
                junit.framework.Assert.failNotEquals(message, inExpectedOnlySet, inActualOnlySet);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSortedSetsEqual(SortedSet<?> expectedSet, SortedSet<?> actualSet)
    {
        try
        {
            Verify.assertSortedSetsEqual("sortedSets", expectedSet, actualSet);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSortedSetsEqual(String setName, SortedSet<?> expectedSet, SortedSet<?> actualSet)
    {
        try
        {
            Assert.assertEquals(setName, expectedSet, actualSet);
            Verify.assertIterablesEqual(setName, expectedSet, actualSet);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSortedBagsEqual(SortedBag<?> expectedBag, SortedBag<?> actualBag)
    {
        try
        {
            Verify.assertSortedBagsEqual("sortedBags", expectedBag, actualBag);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSortedBagsEqual(String bagName, SortedBag<?> expectedBag, SortedBag<?> actualBag)
    {
        try
        {
            Assert.assertEquals(bagName, expectedBag, actualBag);
            Verify.assertIterablesEqual(bagName, expectedBag, actualBag);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSortedMapsEqual(SortedMapIterable<?, ?> expectedMap, SortedMapIterable<?, ?> actualMap)
    {
        try
        {
            Verify.assertSortedMapsEqual("sortedMaps", expectedMap, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSortedMapsEqual(String mapName, SortedMapIterable<?, ?> expectedMap, SortedMapIterable<?, ?> actualMap)
    {
        try
        {
            Assert.assertEquals(mapName, expectedMap, actualMap);
            Verify.assertIterablesEqual(mapName, expectedMap, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertIterablesEqual(Iterable<?> expectedIterable, Iterable<?> actualIterable)
    {
        try
        {
            Verify.assertIterablesEqual("iterables", expectedIterable, actualIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertIterablesEqual(String iterableName, Iterable<?> expectedIterable, Iterable<?> actualIterable)
    {
        try
        {
            if (expectedIterable == null)
            {
                Assert.assertNull(iterableName + " should be null", actualIterable);
                return;
            }

            Verify.assertObjectNotNull(iterableName, actualIterable);

            if (expectedIterable instanceof InternalIterable<?> && actualIterable instanceof InternalIterable<?>)
            {
                MutableList<Object> expectedList = FastList.newList();
                MutableList<Object> actualList = FastList.newList();
                ((InternalIterable<?>) expectedIterable).forEach(CollectionAddProcedure.on(expectedList));
                ((InternalIterable<?>) actualIterable).forEach(CollectionAddProcedure.on(actualList));
                Verify.assertListsEqual(iterableName, expectedList, actualList);
            }
            else
            {
                Iterator<?> expectedIterator = expectedIterable.iterator();
                Iterator<?> actualIterator = actualIterable.iterator();
                int index = 0;

                while (expectedIterator.hasNext() && actualIterator.hasNext())
                {
                    Object eachExpected = expectedIterator.next();
                    Object eachActual = actualIterator.next();

                    if (!Comparators.nullSafeEquals(eachExpected, eachActual))
                    {
                        //noinspection UseOfObsoleteAssert
                        junit.framework.Assert.failNotEquals(iterableName + " first differed at element [" + index + "];", eachExpected, eachActual);
                    }
                    index++;
                }

                Assert.assertFalse("Actual " + iterableName + " had " + index + " elements but expected " + iterableName + " had more.", expectedIterator.hasNext());
                Assert.assertFalse("Expected " + iterableName + " had " + index + " elements but actual " + iterableName + " had more.", actualIterator.hasNext());
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertMapsEqual(Map<?, ?> expectedMap, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertMapsEqual("map", expectedMap, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertMapsEqual(String mapName, Map<?, ?> expectedMap, Map<?, ?> actualMap)
    {
        try
        {
            if (expectedMap == null)
            {
                Assert.assertNull(mapName + " should be null", actualMap);
                return;
            }

            Assert.assertNotNull(mapName + " should not be null", actualMap);

            Set<? extends Map.Entry<?, ?>> expectedEntries = expectedMap.entrySet();
            for (Map.Entry<?, ?> expectedEntry : expectedEntries)
            {
                Object expectedKey = expectedEntry.getKey();
                Object expectedValue = expectedEntry.getValue();
                Object actualValue = actualMap.get(expectedKey);
                if (!Comparators.nullSafeEquals(actualValue, expectedValue))
                {
                    Assert.fail("Values differ at key " + expectedKey + " expected " + expectedValue + " but was " + actualValue);
                }
            }
            Verify.assertSetsEqual(mapName + " keys", expectedMap.keySet(), actualMap.keySet());
            Verify.assertSetsEqual(mapName + " entries", expectedMap.entrySet(), actualMap.entrySet());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertBagsEqual(Bag<?> expectedBag, Bag<?> actualBag)
    {
        try
        {
            Verify.assertBagsEqual("bag", expectedBag, actualBag);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertBagsEqual(String bagName, Bag<?> expectedBag, Bag<?> actualBag)
    {
        try
        {
            if (expectedBag == null)
            {
                Assert.assertNull(bagName + " should be null", actualBag);
                return;
            }

            Assert.assertNotNull(bagName + " should not be null", actualBag);

            Assert.assertEquals(bagName + " size", expectedBag.size(), actualBag.size());
            Assert.assertEquals(bagName + " sizeDistinct", expectedBag.sizeDistinct(), actualBag.sizeDistinct());

            expectedBag.forEachWithOccurrences((expectedKey, expectedValue) ->
            {
                int actualValue = actualBag.occurrencesOf(expectedKey);
                Assert.assertEquals("Occurrences of " + expectedKey, expectedValue, actualValue);
            });
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertListMultimapsEqual(ListMultimap<K, V> expectedListMultimap, ListMultimap<K, V> actualListMultimap)
    {
        try
        {
            Verify.assertListMultimapsEqual("ListMultimap", expectedListMultimap, actualListMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertListMultimapsEqual(String multimapName, ListMultimap<K, V> expectedListMultimap, ListMultimap<K, V> actualListMultimap)
    {
        try
        {
            if (expectedListMultimap == null)
            {
                Assert.assertNull(multimapName + " should be null", actualListMultimap);
                return;
            }

            Assert.assertNotNull(multimapName + " should not be null", actualListMultimap);

            Assert.assertEquals(multimapName + " size", expectedListMultimap.size(), actualListMultimap.size());
            Verify.assertBagsEqual(multimapName + " keyBag", expectedListMultimap.keyBag(), actualListMultimap.keyBag());

            for (K key : expectedListMultimap.keysView())
            {
                Verify.assertListsEqual(multimapName + " value list for key:" + key, (List<V>) expectedListMultimap.get(key), (List<V>) actualListMultimap.get(key));
            }
            Assert.assertEquals(multimapName, expectedListMultimap, actualListMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertSetMultimapsEqual(SetMultimap<K, V> expectedSetMultimap, SetMultimap<K, V> actualSetMultimap)
    {
        try
        {
            Verify.assertSetMultimapsEqual("SetMultimap", expectedSetMultimap, actualSetMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertSetMultimapsEqual(String multimapName, SetMultimap<K, V> expectedSetMultimap, SetMultimap<K, V> actualSetMultimap)
    {
        try
        {
            if (expectedSetMultimap == null)
            {
                Assert.assertNull(multimapName + " should be null", actualSetMultimap);
                return;
            }

            Assert.assertNotNull(multimapName + " should not be null", actualSetMultimap);

            Assert.assertEquals(multimapName + " size", expectedSetMultimap.size(), actualSetMultimap.size());
            Verify.assertBagsEqual(multimapName + " keyBag", expectedSetMultimap.keyBag(), actualSetMultimap.keyBag());

            for (K key : expectedSetMultimap.keysView())
            {
                Verify.assertSetsEqual(multimapName + " value set for key:" + key, (Set<V>) expectedSetMultimap.get(key), (Set<V>) actualSetMultimap.get(key));
            }
            Assert.assertEquals(multimapName, expectedSetMultimap, actualSetMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertBagMultimapsEqual(BagMultimap<K, V> expectedBagMultimap, BagMultimap<K, V> actualBagMultimap)
    {
        try
        {
            Verify.assertBagMultimapsEqual("BagMultimap", expectedBagMultimap, actualBagMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertBagMultimapsEqual(String multimapName, BagMultimap<K, V> expectedBagMultimap, BagMultimap<K, V> actualBagMultimap)
    {
        try
        {
            if (expectedBagMultimap == null)
            {
                Assert.assertNull(multimapName + " should be null", actualBagMultimap);
                return;
            }

            Assert.assertNotNull(multimapName + " should not be null", actualBagMultimap);

            Assert.assertEquals(multimapName + " size", expectedBagMultimap.size(), actualBagMultimap.size());
            Verify.assertBagsEqual(multimapName + " keyBag", expectedBagMultimap.keyBag(), actualBagMultimap.keyBag());

            for (K key : expectedBagMultimap.keysView())
            {
                Verify.assertBagsEqual(multimapName + " value bag for key:" + key, expectedBagMultimap.get(key), actualBagMultimap.get(key));
            }
            Assert.assertEquals(multimapName, expectedBagMultimap, actualBagMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertSortedSetMultimapsEqual(SortedSetMultimap<K, V> expectedSortedSetMultimap, SortedSetMultimap<K, V> actualSortedSetMultimap)
    {
        try
        {
            Verify.assertSortedSetMultimapsEqual("SortedSetMultimap", expectedSortedSetMultimap, actualSortedSetMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertSortedSetMultimapsEqual(String multimapName, SortedSetMultimap<K, V> expectedSortedSetMultimap, SortedSetMultimap<K, V> actualSortedSetMultimap)
    {
        try
        {
            if (expectedSortedSetMultimap == null)
            {
                Assert.assertNull(multimapName + " should be null", actualSortedSetMultimap);
                return;
            }

            Assert.assertNotNull(multimapName + " should not be null", actualSortedSetMultimap);

            Assert.assertEquals(multimapName + " size", expectedSortedSetMultimap.size(), actualSortedSetMultimap.size());
            Verify.assertBagsEqual(multimapName + " keyBag", expectedSortedSetMultimap.keyBag(), actualSortedSetMultimap.keyBag());

            for (K key : expectedSortedSetMultimap.keysView())
            {
                Verify.assertSortedSetsEqual(multimapName + " value set for key:" + key, (SortedSet<V>) expectedSortedSetMultimap.get(key), (SortedSet<V>) actualSortedSetMultimap.get(key));
            }
            Assert.assertEquals(multimapName, expectedSortedSetMultimap, actualSortedSetMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertSortedBagMultimapsEqual(SortedBagMultimap<K, V> expectedSortedBagMultimap, SortedBagMultimap<K, V> actualSortedBagMultimap)
    {
        try
        {
            Verify.assertSortedBagMultimapsEqual("SortedBagMultimap", expectedSortedBagMultimap, actualSortedBagMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <K, V> void assertSortedBagMultimapsEqual(String multimapName, SortedBagMultimap<K, V> expectedSortedBagMultimap, SortedBagMultimap<K, V> actualSortedBagMultimap)
    {
        try
        {
            if (expectedSortedBagMultimap == null)
            {
                Assert.assertNull(multimapName + " should be null", actualSortedBagMultimap);
                return;
            }

            Assert.assertNotNull(multimapName + " should not be null", actualSortedBagMultimap);

            Assert.assertEquals(multimapName + " size", expectedSortedBagMultimap.size(), actualSortedBagMultimap.size());
            Verify.assertBagsEqual(multimapName + " keyBag", expectedSortedBagMultimap.keyBag(), actualSortedBagMultimap.keyBag());

            for (K key : expectedSortedBagMultimap.keysView())
            {
                Verify.assertSortedBagsEqual(multimapName + " value set for key:" + key, expectedSortedBagMultimap.get(key), actualSortedBagMultimap.get(key));
            }
            Assert.assertEquals(multimapName, expectedSortedBagMultimap, actualSortedBagMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsKeys(
            String mapName,
            Map<?, ?> actualMap,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableList<Object> expectedKeys = Lists.mutable.of();
            for (int i = 0; i < expectedKeyValues.length; i += 2)
            {
                expectedKeys.add(expectedKeyValues[i]);
            }

            Verify.assertContainsAll(mapName + ".keySet()", actualMap.keySet(), expectedKeys.toArray());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsValues(
            String mapName,
            Map<?, ?> actualMap,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableMap<Object, String> missingEntries = UnifiedMap.newMap();
            int i = 0;
            while (i < expectedKeyValues.length)
            {
                Object expectedKey = expectedKeyValues[i++];
                Object expectedValue = expectedKeyValues[i++];
                Object actualValue = actualMap.get(expectedKey);
                if (!Comparators.nullSafeEquals(expectedValue, actualValue))
                {
                    missingEntries.put(
                            expectedKey,
                            "expectedValue:<" + expectedValue + ">, actualValue:<" + actualValue + '>');
                }
            }
            if (!missingEntries.isEmpty())
            {
                StringBuilder buf = new StringBuilder(mapName + " has incorrect values for keys:[");
                for (Map.Entry<Object, String> expectedEntry : missingEntries.entrySet())
                {
                    buf.append("key:<")
                            .append(expectedEntry.getKey())
                            .append(',')
                            .append(expectedEntry.getValue())
                            .append("> ");
                }
                buf.append(']');
                Assert.fail(buf.toString());
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsKeys(
            String mapIterableName,
            MapIterable<?, ?> mapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableList<Object> expectedKeys = Lists.mutable.of();
            for (int i = 0; i < expectedKeyValues.length; i += 2)
            {
                expectedKeys.add(expectedKeyValues[i]);
            }

            Verify.assertContainsAll(mapIterableName + ".keysView()", mapIterable.keysView(), expectedKeys.toArray());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsValues(
            String mapIterableName,
            MapIterable<?, ?> mapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableList<Object> expectedValues = Lists.mutable.of();
            for (int i = 1; i < expectedKeyValues.length; i += 2)
            {
                expectedValues.add(expectedKeyValues[i]);
            }

            Verify.assertContainsAll(mapIterableName + ".valuesView()", mapIterable.valuesView(), expectedValues.toArray());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsKeys(
            String mutableMapIterableName,
            MutableMapIterable<?, ?> mutableMapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableList<Object> expectedKeys = Lists.mutable.of();
            for (int i = 0; i < expectedKeyValues.length; i += 2)
            {
                expectedKeys.add(expectedKeyValues[i]);
            }

            Verify.assertContainsAll(mutableMapIterableName + ".keysView()", mutableMapIterable.keysView(), expectedKeys.toArray());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsValues(
            String mutableMapIterableName,
            MutableMapIterable<?, ?> mutableMapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableList<Object> expectedValues = Lists.mutable.of();
            for (int i = 1; i < expectedKeyValues.length; i += 2)
            {
                expectedValues.add(expectedKeyValues[i]);
            }

            Verify.assertContainsAll(mutableMapIterableName + ".valuesView()", mutableMapIterable.valuesView(), expectedValues.toArray());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsKeys(
            String immutableMapIterableName,
            ImmutableMapIterable<?, ?> immutableMapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableList<Object> expectedKeys = Lists.mutable.of();
            for (int i = 0; i < expectedKeyValues.length; i += 2)
            {
                expectedKeys.add(expectedKeyValues[i]);
            }

            Verify.assertContainsAll(immutableMapIterableName + ".keysView()", immutableMapIterable.keysView(), expectedKeys.toArray());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static void assertMapContainsValues(
            String immutableMapIterableName,
            ImmutableMapIterable<?, ?> immutableMapIterable,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            MutableList<Object> expectedValues = Lists.mutable.of();
            for (int i = 1; i < expectedKeyValues.length; i += 2)
            {
                expectedValues.add(expectedKeyValues[i]);
            }

            Verify.assertContainsAll(immutableMapIterableName + ".valuesView()", immutableMapIterable.valuesView(), expectedValues.toArray());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Multimap} contains an entry with the given key and value.
     */
    public static <K, V> void assertContainsEntry(
            K expectedKey,
            V expectedValue,
            Multimap<K, V> actualMultimap)
    {
        try
        {
            Verify.assertContainsEntry("multimap", expectedKey, expectedValue, actualMultimap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Multimap} contains an entry with the given key and value.
     */
    public static <K, V> void assertContainsEntry(
            String multimapName,
            K expectedKey,
            V expectedValue,
            Multimap<K, V> actualMultimap)
    {
        try
        {
            Assert.assertNotNull(multimapName, actualMultimap);

            if (!actualMultimap.containsKeyAndValue(expectedKey, expectedValue))
            {
                Assert.fail(multimapName + " did not contain entry: <" + expectedKey + ", " + expectedValue + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the given {@link Multimap} contains all of the given keys and values.
     */
    public static void assertContainsAllEntries(Multimap<?, ?> actualMultimap, Object... keyValues)
    {
        try
        {
            Verify.assertContainsAllEntries("multimap", actualMultimap, keyValues);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert the given {@link Multimap} contains all of the given keys and values.
     */
    public static void assertContainsAllEntries(
            String multimapName,
            Multimap<?, ?> actualMultimap,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            if (expectedKeyValues.length % 2 != 0)
            {
                Assert.fail("Odd number of keys and values (every key must have a value)");
            }

            Verify.assertObjectNotNull(multimapName, actualMultimap);

            MutableList<Map.Entry<?, ?>> missingEntries = Lists.mutable.of();
            for (int i = 0; i < expectedKeyValues.length; i += 2)
            {
                Object expectedKey = expectedKeyValues[i];
                Object expectedValue = expectedKeyValues[i + 1];

                if (!actualMultimap.containsKeyAndValue(expectedKey, expectedValue))
                {
                    missingEntries.add(new ImmutableEntry<>(expectedKey, expectedValue));
                }
            }

            if (!missingEntries.isEmpty())
            {
                Assert.fail(multimapName + " is missing entries: " + missingEntries);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void denyContainsAny(
            String collectionName,
            Collection<?> actualCollection,
            Object... items)
    {
        try
        {
            Verify.assertNotEmpty("Expected items in assertion", items);

            Verify.assertObjectNotNull(collectionName, actualCollection);

            MutableSet<Object> intersection = Sets.intersect(UnifiedSet.newSet(actualCollection), UnifiedSet.newSetWith(items));
            if (intersection.notEmpty())
            {
                Assert.fail(collectionName
                        + " has an intersection with these items and should not :<" + intersection + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} contains an entry with the given key.
     */
    public static void assertContainsKey(Object expectedKey, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertContainsKey("map", expectedKey, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} contains an entry with the given key.
     */
    public static void assertContainsKey(String mapName, Object expectedKey, Map<?, ?> actualMap)
    {
        try
        {
            Assert.assertNotNull(mapName, actualMap);

            if (!actualMap.containsKey(expectedKey))
            {
                Assert.fail(mapName + " did not contain expectedKey:<" + expectedKey + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MapIterable} contains an entry with the given key.
     */
    public static void assertContainsKey(Object expectedKey, MapIterable<?, ?> mapIterable)
    {
        try
        {
            Verify.assertContainsKey("map", expectedKey, mapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MapIterable} contains an entry with the given key.
     */
    public static void assertContainsKey(
            String mapIterableName,
            Object expectedKey,
            MapIterable<?, ?> mapIterable)
    {
        try
        {
            Assert.assertNotNull(mapIterableName, mapIterable);

            if (!mapIterable.containsKey(expectedKey))
            {
                Assert.fail(mapIterableName + " did not contain expectedKey:<" + expectedKey + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} contains an entry with the given key.
     */
    public static void assertContainsKey(Object expectedKey, MutableMapIterable<?, ?> mutableMapIterable)
    {
        try
        {
            Verify.assertContainsKey("map", expectedKey, mutableMapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} contains an entry with the given key.
     */
    public static void assertContainsKey(
            String mutableMapIterableName,
            Object expectedKey,
            MutableMapIterable<?, ?> mutableMapIterable)
    {
        try
        {
            Assert.assertNotNull(mutableMapIterableName, mutableMapIterable);

            if (!mutableMapIterable.containsKey(expectedKey))
            {
                Assert.fail(mutableMapIterableName + " did not contain expectedKey:<" + expectedKey + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableMapIterable} contains an entry with the given key.
     */
    public static void assertContainsKey(Object expectedKey, ImmutableMapIterable<?, ?> immutableMapIterable)
    {
        try
        {
            Verify.assertContainsKey("map", expectedKey, immutableMapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableMapIterable} contains an entry with the given key.
     */
    public static void assertContainsKey(
            String immutableMapIterableName,
            Object expectedKey,
            ImmutableMapIterable<?, ?> immutableMapIterable)
    {
        try
        {
            Assert.assertNotNull(immutableMapIterableName, immutableMapIterable);

            if (!immutableMapIterable.containsKey(expectedKey))
            {
                Assert.fail(immutableMapIterableName + " did not contain expectedKey:<" + expectedKey + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Deny that the given {@link Map} contains an entry with the given key.
     */
    public static void denyContainsKey(Object unexpectedKey, Map<?, ?> actualMap)
    {
        try
        {
            Verify.denyContainsKey("map", unexpectedKey, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Deny that the given {@link Map} contains an entry with the given key.
     */
    public static void denyContainsKey(String mapName, Object unexpectedKey, Map<?, ?> actualMap)
    {
        try
        {
            Assert.assertNotNull(mapName, actualMap);

            if (actualMap.containsKey(unexpectedKey))
            {
                Assert.fail(mapName + " contained unexpectedKey:<" + unexpectedKey + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            Object expectedKey,
            Object expectedValue,
            Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertContainsKeyValue("map", expectedKey, expectedValue, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Map} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            String mapName,
            Object expectedKey,
            Object expectedValue,
            Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertContainsKey(mapName, expectedKey, actualMap);

            Object actualValue = actualMap.get(expectedKey);
            if (!Comparators.nullSafeEquals(actualValue, expectedValue))
            {
                Assert.fail(
                        mapName
                                + " entry with expectedKey:<"
                                + expectedKey
                                + "> "
                                + "did not contain expectedValue:<"
                                + expectedValue
                                + ">, "
                                + "but had actualValue:<"
                                + actualValue
                                + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MapIterable} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            Object expectedKey,
            Object expectedValue,
            MapIterable<?, ?> mapIterable)
    {
        try
        {
            Verify.assertContainsKeyValue("map", expectedKey, expectedValue, mapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MapIterable} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            String mapIterableName,
            Object expectedKey,
            Object expectedValue,
            MapIterable<?, ?> mapIterable)
    {
        try
        {
            Verify.assertContainsKey(mapIterableName, expectedKey, mapIterable);

            Object actualValue = mapIterable.get(expectedKey);
            if (!Comparators.nullSafeEquals(actualValue, expectedValue))
            {
                Assert.fail(
                        mapIterableName
                                + " entry with expectedKey:<"
                                + expectedKey
                                + "> "
                                + "did not contain expectedValue:<"
                                + expectedValue
                                + ">, "
                                + "but had actualValue:<"
                                + actualValue
                                + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            Object expectedKey,
            Object expectedValue,
            MutableMapIterable<?, ?> mapIterable)
    {
        try
        {
            Verify.assertContainsKeyValue("map", expectedKey, expectedValue, mapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link MutableMapIterable} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            String mapIterableName,
            Object expectedKey,
            Object expectedValue,
            MutableMapIterable<?, ?> mutableMapIterable)
    {
        try
        {
            Verify.assertContainsKey(mapIterableName, expectedKey, mutableMapIterable);

            Object actualValue = mutableMapIterable.get(expectedKey);
            if (!Comparators.nullSafeEquals(actualValue, expectedValue))
            {
                Assert.fail(
                        mapIterableName
                                + " entry with expectedKey:<"
                                + expectedKey
                                + "> "
                                + "did not contain expectedValue:<"
                                + expectedValue
                                + ">, "
                                + "but had actualValue:<"
                                + actualValue
                                + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableMapIterable} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            Object expectedKey,
            Object expectedValue,
            ImmutableMapIterable<?, ?> mapIterable)
    {
        try
        {
            Verify.assertContainsKeyValue("map", expectedKey, expectedValue, mapIterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link ImmutableMapIterable} contains an entry with the given key and value.
     */
    public static void assertContainsKeyValue(
            String mapIterableName,
            Object expectedKey,
            Object expectedValue,
            ImmutableMapIterable<?, ?> immutableMapIterable)
    {
        try
        {
            Verify.assertContainsKey(mapIterableName, expectedKey, immutableMapIterable);

            Object actualValue = immutableMapIterable.get(expectedKey);
            if (!Comparators.nullSafeEquals(actualValue, expectedValue))
            {
                Assert.fail(
                        mapIterableName
                                + " entry with expectedKey:<"
                                + expectedKey
                                + "> "
                                + "did not contain expectedValue:<"
                                + expectedValue
                                + ">, "
                                + "but had actualValue:<"
                                + actualValue
                                + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} does <em>not</em> contain the given item.
     */
    public static void assertNotContains(Object unexpectedItem, Collection<?> actualCollection)
    {
        try
        {
            Verify.assertNotContains("collection", unexpectedItem, actualCollection);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} does <em>not</em> contain the given item.
     */
    public static void assertNotContains(
            String collectionName,
            Object unexpectedItem,
            Collection<?> actualCollection)
    {
        try
        {
            Verify.assertObjectNotNull(collectionName, actualCollection);

            if (actualCollection.contains(unexpectedItem))
            {
                Assert.fail(collectionName + " should not contain unexpectedItem:<" + unexpectedItem + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} does <em>not</em> contain the given item.
     */
    public static void assertNotContains(Object unexpectedItem, Iterable<?> iterable)
    {
        try
        {
            Verify.assertNotContains("iterable", unexpectedItem, iterable);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Iterable} does <em>not</em> contain the given item.
     */
    public static void assertNotContains(
            String collectionName,
            Object unexpectedItem,
            Iterable<?> iterable)
    {
        try
        {
            Verify.assertObjectNotNull(collectionName, iterable);

            if (Iterate.contains(iterable, unexpectedItem))
            {
                Assert.fail(collectionName + " should not contain unexpectedItem:<" + unexpectedItem + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} does <em>not</em> contain the given item.
     */
    public static void assertNotContainsKey(Object unexpectedKey, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertNotContainsKey("map", unexpectedKey, actualMap);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@link Collection} does <em>not</em> contain the given item.
     */
    public static void assertNotContainsKey(String mapName, Object unexpectedKey, Map<?, ?> actualMap)
    {
        try
        {
            Verify.assertObjectNotNull(mapName, actualMap);

            if (actualMap.containsKey(unexpectedKey))
            {
                Assert.fail(mapName + " should not contain unexpectedItem:<" + unexpectedKey + '>');
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the formerItem appears before the latterItem in the given {@link Collection}.
     * Both the formerItem and the latterItem must appear in the collection, or this assert will fail.
     */
    public static void assertBefore(Object formerItem, Object latterItem, List<?> actualList)
    {
        try
        {
            Verify.assertBefore("list", formerItem, latterItem, actualList);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the formerItem appears before the latterItem in the given {@link Collection}.
     * {@link #assertContains(String, Object, Collection)} will be called for both the formerItem and the
     * latterItem, prior to the "before" assertion.
     */
    public static void assertBefore(
            String listName,
            Object formerItem,
            Object latterItem,
            List<?> actualList)
    {
        try
        {
            Verify.assertObjectNotNull(listName, actualList);
            Verify.assertNotEquals(
                    "Bad test, formerItem and latterItem are equal, listName:<" + listName + '>',
                    formerItem,
                    latterItem);
            Verify.assertContainsAll(listName, actualList, formerItem, latterItem);
            int formerPosition = actualList.indexOf(formerItem);
            int latterPosition = actualList.indexOf(latterItem);
            if (latterPosition < formerPosition)
            {
                Assert.fail("Items in "
                        + listName
                        + " are in incorrect order; "
                        + "expected formerItem:<"
                        + formerItem
                        + "> "
                        + "to appear before latterItem:<"
                        + latterItem
                        + ">, but didn't");
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertObjectNotNull(String objectName, Object actualObject)
    {
        try
        {
            Assert.assertNotNull(objectName + " should not be null", actualObject);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code item} is at the {@code index} in the given {@link List}.
     */
    public static void assertItemAtIndex(Object expectedItem, int index, List<?> list)
    {
        try
        {
            Verify.assertItemAtIndex("list", expectedItem, index, list);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code item} is at the {@code index} in the given {@code array}.
     */
    public static void assertItemAtIndex(Object expectedItem, int index, Object[] array)
    {
        try
        {
            Verify.assertItemAtIndex("array", expectedItem, index, array);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertStartsWith(T[] array, T... items)
    {
        try
        {
            Verify.assertNotEmpty("Expected items in assertion", items);

            for (int i = 0; i < items.length; i++)
            {
                T item = items[i];
                Verify.assertItemAtIndex("array", item, i, array);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertStartsWith(List<T> list, T... items)
    {
        try
        {
            Verify.assertStartsWith("list", list, items);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertStartsWith(String listName, List<T> list, T... items)
    {
        try
        {
            Verify.assertNotEmpty("Expected items in assertion", items);

            for (int i = 0; i < items.length; i++)
            {
                T item = items[i];
                Verify.assertItemAtIndex(listName, item, i, list);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertEndsWith(List<T> list, T... items)
    {
        try
        {
            Verify.assertNotEmpty("Expected items in assertion", items);

            for (int i = 0; i < items.length; i++)
            {
                T item = items[i];
                Verify.assertItemAtIndex("list", item, list.size() - items.length + i, list);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertEndsWith(T[] array, T... items)
    {
        try
        {
            Verify.assertNotEmpty("Expected items in assertion", items);

            for (int i = 0; i < items.length; i++)
            {
                T item = items[i];
                Verify.assertItemAtIndex("array", item, array.length - items.length + i, array);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code item} is at the {@code index} in the given {@link List}.
     */
    public static void assertItemAtIndex(
            String listName,
            Object expectedItem,
            int index,
            List<?> list)
    {
        try
        {
            Verify.assertObjectNotNull(listName, list);

            Object actualItem = list.get(index);
            if (!Comparators.nullSafeEquals(expectedItem, actualItem))
            {
                Assert.assertEquals(
                        listName + " has incorrect element at index:<" + index + '>',
                        expectedItem,
                        actualItem);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that the given {@code item} is at the {@code index} in the given {@link List}.
     */
    public static void assertItemAtIndex(
            String arrayName,
            Object expectedItem,
            int index,
            Object[] array)
    {
        try
        {
            Assert.assertNotNull(array);
            Object actualItem = array[index];
            if (!Comparators.nullSafeEquals(expectedItem, actualItem))
            {
                Assert.assertEquals(
                        arrayName + " has incorrect element at index:<" + index + '>',
                        expectedItem,
                        actualItem);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertPostSerializedEqualsAndHashCode(Object object)
    {
        try
        {
            Object deserialized = SerializeTestHelper.serializeDeserialize(object);
            Verify.assertEqualsAndHashCode("objects", object, deserialized);
            Assert.assertNotSame("not same object", object, deserialized);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertPostSerializedEqualsHashCodeAndToString(Object object)
    {
        try
        {
            Object deserialized = SerializeTestHelper.serializeDeserialize(object);
            Verify.assertEqualsAndHashCode("objects", object, deserialized);
            Assert.assertNotSame("not same object", object, deserialized);
            Assert.assertEquals("not same toString", object.toString(), deserialized.toString());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertPostSerializedIdentity(Object object)
    {
        try
        {
            Object deserialized = SerializeTestHelper.serializeDeserialize(object);
            Verify.assertEqualsAndHashCode("objects", object, deserialized);
            Assert.assertSame("same object", object, deserialized);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSerializedForm(String expectedBase64Form, Object actualObject)
    {
        try
        {
            Verify.assertInstanceOf(Serializable.class, actualObject);
            Assert.assertEquals(
                    "Serialization was broken.",
                    expectedBase64Form,
                    Verify.encodeObject(actualObject));
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertSerializedForm(
            long expectedSerialVersionUID,
            String expectedBase64Form,
            Object actualObject)
    {
        try
        {
            Verify.assertInstanceOf(Serializable.class, actualObject);

            Assert.assertEquals(
                    "Serialization was broken.",
                    expectedBase64Form,
                    Verify.encodeObject(actualObject));

            Object decodeToObject = Verify.decodeObject(expectedBase64Form);

            Assert.assertEquals(
                    "serialVersionUID's differ",
                    expectedSerialVersionUID,
                    ObjectStreamClass.lookup(decodeToObject.getClass()).getSerialVersionUID());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static void assertDeserializedForm(String expectedBase64Form, Object actualObject)
    {
        try
        {
            Verify.assertInstanceOf(Serializable.class, actualObject);

            Object decodeToObject = Verify.decodeObject(expectedBase64Form);
            Assert.assertEquals("Serialization was broken.", decodeToObject, actualObject);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static Object decodeObject(String expectedBase64Form)
    {
        try
        {
            byte[] bytes = Base64.decodeBase64(expectedBase64Form);
            return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new AssertionError(e);
        }
    }

    private static String encodeObject(Object actualObject)
    {
        try
        {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(actualObject);
            objectOutputStream.flush();
            objectOutputStream.close();

            String string = new Base64(76, LINE_SEPARATOR, false).encodeAsString(byteArrayOutputStream.toByteArray());
            String trimmedString = Verify.removeFinalNewline(string);
            return Verify.addFinalNewline(trimmedString);
        }
        catch (IOException e)
        {
            throw new AssertionError(e);
        }
    }

    private static String removeFinalNewline(String string)
    {
        return string.substring(0, string.length() - 1);
    }

    private static String addFinalNewline(String string)
    {
        if (string.length() % 77 == 76)
        {
            return string + '\n';
        }
        return string;
    }

    public static void assertNotSerializable(Object actualObject)
    {
        try
        {
            Verify.assertThrows(NotSerializableException.class, () ->
            {
                new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(actualObject);
                return null;
            });
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that {@code objectA} and {@code objectB} are equal (via the {@link Object#equals(Object)} method,
     * and that they both return the same {@link Object#hashCode()}.
     */
    public static void assertEqualsAndHashCode(Object objectA, Object objectB)
    {
        try
        {
            Verify.assertEqualsAndHashCode("objects", objectA, objectB);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that a value is negative.
     */
    public static void assertNegative(int value)
    {
        try
        {
            Assert.assertTrue(value + " is not negative", value < 0);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that a value is positive.
     */
    public static void assertPositive(int value)
    {
        try
        {
            Assert.assertTrue(value + " is not positive", value > 0);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Asserts that a value is positive.
     */
    public static void assertZero(int value)
    {
        try
        {
            Assert.assertEquals(0, value);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Assert that {@code objectA} and {@code objectB} are equal (via the {@link Object#equals(Object)} method,
     * and that they both return the same {@link Object#hashCode()}.
     */
    public static void assertEqualsAndHashCode(String itemNames, Object objectA, Object objectB)
    {
        try
        {
            if (objectA == null || objectB == null)
            {
                Assert.fail("Neither item should be null: <" + objectA + "> <" + objectB + '>');
            }

            Assert.assertFalse("Neither item should equal null", objectA.equals(null));
            Assert.assertFalse("Neither item should equal null", objectB.equals(null));
            Verify.assertNotEquals("Neither item should equal new Object()", objectA.equals(new Object()));
            Verify.assertNotEquals("Neither item should equal new Object()", objectB.equals(new Object()));
            Assert.assertEquals("Expected " + itemNames + " to be equal.", objectA, objectA);
            Assert.assertEquals("Expected " + itemNames + " to be equal.", objectB, objectB);
            Assert.assertEquals("Expected " + itemNames + " to be equal.", objectA, objectB);
            Assert.assertEquals("Expected " + itemNames + " to be equal.", objectB, objectA);
            Assert.assertEquals(
                    "Expected " + itemNames + " to have the same hashCode().",
                    objectA.hashCode(),
                    objectB.hashCode());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * @deprecated since 8.2.0 as will not work with Java 9
     */
    @Deprecated
    public static void assertShallowClone(Cloneable object)
    {
        try
        {
            Verify.assertShallowClone("object", object);
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * @deprecated since 8.2.0 as will not work with Java 9
     */
    @Deprecated
    public static void assertShallowClone(String itemName, Cloneable object)
    {
        try
        {
            Method method = Object.class.getDeclaredMethod("clone", (Class<?>[]) null);
            method.setAccessible(true);
            Object clone = method.invoke(object);
            String prefix = itemName + " and its clone";
            Assert.assertNotSame(prefix, object, clone);
            Verify.assertEqualsAndHashCode(prefix, object, clone);
        }
        catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e)
        {
            throw new AssertionError(e.getLocalizedMessage());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    public static <T> void assertClassNonInstantiable(Class<T> aClass)
    {
        try
        {
            try
            {
                aClass.newInstance();
                Assert.fail("Expected class '" + aClass + "' to be non-instantiable");
            }
            catch (InstantiationException e)
            {
                // pass
            }
            catch (IllegalAccessException e)
            {
                if (Verify.canInstantiateThroughReflection(aClass))
                {
                    Assert.fail("Expected constructor of non-instantiable class '" + aClass + "' to throw an exception, but didn't");
                }
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    private static <T> boolean canInstantiateThroughReflection(Class<T> aClass)
    {
        try
        {
            Constructor<T> declaredConstructor = aClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            declaredConstructor.newInstance();
            return true;
        }
        catch (NoSuchMethodException | AssertionError | IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            return false;
        }
    }

    public static void assertError(Class<? extends Error> expectedErrorClass, Runnable code)
    {
        try
        {
            code.run();
        }
        catch (Error ex)
        {
            try
            {
                Assert.assertSame(
                        "Caught error of type <"
                                + ex.getClass().getName()
                                + ">, expected one of type <"
                                + expectedErrorClass.getName()
                                + '>',
                        expectedErrorClass,
                        ex.getClass());
                return;
            }
            catch (AssertionError e)
            {
                Verify.throwMangledException(e);
            }
        }

        try
        {
            Assert.fail("Block did not throw an error of type " + expectedErrorClass.getName());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Runs the {@link Callable} {@code code} and asserts that it throws an {@code Exception} of the type
     * {@code expectedExceptionClass}.
     * <p>
     * {@code Callable} is most appropriate when a checked exception will be thrown.
     * If a subclass of {@link RuntimeException} will be thrown, the form
     * {@link #assertThrows(Class, Runnable)} may be more convenient.
     * <p>
     * e.g.
     * <pre>
     * Verify.<b>assertThrows</b>(StringIndexOutOfBoundsException.class, new Callable&lt;String&gt;()
     * {
     *    public String call() throws Exception
     *    {
     *        return "Craig".substring(42, 3);
     *    }
     * });
     * </pre>
     *
     * @see #assertThrows(Class, Runnable)
     */
    public static void assertThrows(
            Class<? extends Exception> expectedExceptionClass,
            Callable<?> code)
    {
        try
        {
            code.call();
        }
        catch (Exception ex)
        {
            try
            {
                Assert.assertSame(
                        "Caught exception of type <"
                                + ex.getClass().getName()
                                + ">, expected one of type <"
                                + expectedExceptionClass.getName()
                                + '>'
                                + '\n'
                                + "Exception Message: " + ex.getMessage()
                                + '\n',
                        expectedExceptionClass,
                        ex.getClass());
                return;
            }
            catch (AssertionError e)
            {
                Verify.throwMangledException(e);
            }
        }

        try
        {
            Assert.fail("Block did not throw an exception of type " + expectedExceptionClass.getName());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Runs the {@link Runnable} {@code code} and asserts that it throws an {@code Exception} of the type
     * {@code expectedExceptionClass}.
     * <p>
     * {@code Runnable} is most appropriate when a subclass of {@link RuntimeException} will be thrown.
     * If a checked exception will be thrown, the form {@link #assertThrows(Class, Callable)} may be more
     * convenient.
     * <p>
     * e.g.
     * <pre>
     * Verify.<b>assertThrows</b>(NullPointerException.class, new Runnable()
     * {
     *    public void run()
     *    {
     *        final Integer integer = null;
     *        LOGGER.info(integer.toString());
     *    }
     * });
     * </pre>
     *
     * @see #assertThrows(Class, Callable)
     */
    public static void assertThrows(
            Class<? extends Exception> expectedExceptionClass,
            Runnable code)
    {
        try
        {
            code.run();
        }
        catch (RuntimeException ex)
        {
            try
            {
                Assert.assertSame(
                        "Caught exception of type <"
                                + ex.getClass().getName()
                                + ">, expected one of type <"
                                + expectedExceptionClass.getName()
                                + '>'
                                + '\n'
                                + "Exception Message: " + ex.getMessage()
                                + '\n',
                        expectedExceptionClass,
                        ex.getClass());
                return;
            }
            catch (AssertionError e)
            {
                Verify.throwMangledException(e);
            }
        }

        try
        {
            Assert.fail("Block did not throw an exception of type " + expectedExceptionClass.getName());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Runs the {@link Callable} {@code code} and asserts that it throws an {@code Exception} of the type
     * {@code expectedExceptionClass}, which contains a cause of type expectedCauseClass.
     * <p>
     * {@code Callable} is most appropriate when a checked exception will be thrown.
     * If a subclass of {@link RuntimeException} will be thrown, the form
     * {@link #assertThrowsWithCause(Class, Class, Runnable)} may be more convenient.
     * <p>
     * e.g.
     * <pre>
     * Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, new Callable<Void>()
     * {
     *    public Void call() throws Exception
     *    {
     *        try
     *        {
     *            new File("").createNewFile();
     *        }
     *        catch (final IOException e)
     *        {
     *            throw new RuntimeException("Uh oh!", e);
     *        }
     *        return null;
     *    }
     * });
     * </pre>
     *
     * @see #assertThrowsWithCause(Class, Class, Runnable)
     */
    public static void assertThrowsWithCause(
            Class<? extends Exception> expectedExceptionClass,
            Class<? extends Throwable> expectedCauseClass,
            Callable<?> code)
    {
        try
        {
            code.call();
        }
        catch (Exception ex)
        {
            try
            {
                Assert.assertSame(
                        "Caught exception of type <"
                                + ex.getClass().getName()
                                + ">, expected one of type <"
                                + expectedExceptionClass.getName()
                                + '>',
                        expectedExceptionClass,
                        ex.getClass());
                Throwable actualCauseClass = ex.getCause();
                Assert.assertNotNull(
                        "Caught exception with null cause, expected cause of type <"
                                + expectedCauseClass.getName()
                                + '>',
                        actualCauseClass);
                Assert.assertSame(
                        "Caught exception with cause of type<"
                                + actualCauseClass.getClass().getName()
                                + ">, expected cause of type <"
                                + expectedCauseClass.getName()
                                + '>',
                        expectedCauseClass,
                        actualCauseClass.getClass());
                return;
            }
            catch (AssertionError e)
            {
                Verify.throwMangledException(e);
            }
        }

        try
        {
            Assert.fail("Block did not throw an exception of type " + expectedExceptionClass.getName());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }

    /**
     * Runs the {@link Runnable} {@code code} and asserts that it throws an {@code Exception} of the type
     * {@code expectedExceptionClass}, which contains a cause of type expectedCauseClass.
     * <p>
     * {@code Runnable} is most appropriate when a subclass of {@link RuntimeException} will be thrown.
     * If a checked exception will be thrown, the form {@link #assertThrowsWithCause(Class, Class, Callable)}
     * may be more convenient.
     * <p>
     * e.g.
     * <pre>
     * Verify.assertThrowsWithCause(RuntimeException.class, StringIndexOutOfBoundsException.class, new Runnable()
     * {
     *    public void run()
     *    {
     *        try
     *        {
     *            LOGGER.info("Craig".substring(42, 3));
     *        }
     *        catch (final StringIndexOutOfBoundsException e)
     *        {
     *            throw new RuntimeException("Uh oh!", e);
     *        }
     *    }
     * });
     * </pre>
     *
     * @see #assertThrowsWithCause(Class, Class, Callable)
     */
    public static void assertThrowsWithCause(
            Class<? extends Exception> expectedExceptionClass,
            Class<? extends Throwable> expectedCauseClass,
            Runnable code)
    {
        try
        {
            code.run();
        }
        catch (RuntimeException ex)
        {
            try
            {
                Assert.assertSame(
                        "Caught exception of type <"
                                + ex.getClass().getName()
                                + ">, expected one of type <"
                                + expectedExceptionClass.getName()
                                + '>',
                        expectedExceptionClass,
                        ex.getClass());
                Throwable actualCauseClass = ex.getCause();
                Assert.assertNotNull(
                        "Caught exception with null cause, expected cause of type <"
                                + expectedCauseClass.getName()
                                + '>',
                        actualCauseClass);
                Assert.assertSame(
                        "Caught exception with cause of type<"
                                + actualCauseClass.getClass().getName()
                                + ">, expected cause of type <"
                                + expectedCauseClass.getName()
                                + '>',
                        expectedCauseClass,
                        actualCauseClass.getClass());
                return;
            }
            catch (AssertionError e)
            {
                Verify.throwMangledException(e);
            }
        }

        try
        {
            Assert.fail("Block did not throw an exception of type " + expectedExceptionClass.getName());
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }
}
