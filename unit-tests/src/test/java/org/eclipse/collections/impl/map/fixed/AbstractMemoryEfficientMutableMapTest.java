/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.fixed;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.FixedSizeMap;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionMutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.UnmodifiableMap;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.SynchronizedMutableMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.math.SumProcedure;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.eclipse.collections.impl.factory.Iterables.iSet;

/**
 * JUnit test for {@link AbstractMemoryEfficientMutableMap}.
 */
public abstract class AbstractMemoryEfficientMutableMapTest
{
    protected abstract MutableMap<String, String> classUnderTest();

    protected abstract MutableMap<String, Integer> mixedTypeClassUnderTest();

    public abstract void containsValue();

    @Test(expected = UnsupportedOperationException.class)
    public void put_throws()
    {
        this.classUnderTest().put(null, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove_throws()
    {
        this.classUnderTest().remove(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void putAll_throws()
    {
        this.classUnderTest().putAll(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void clear_throws()
    {
        this.classUnderTest().clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeKey_throws()
    {
        this.classUnderTest().removeKey(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void collectKeysAndValues_throws()
    {
        this.classUnderTest().collectKeysAndValues(null, null, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateValue()
    {
        this.classUnderTest().updateValue("1", () -> "", object -> object + object);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateValueWith()
    {
        this.classUnderTest().updateValueWith("1", () -> "", String::concat, "!");
    }

    @Test
    public void asUnmodifiable()
    {
        MutableMap<String, String> unmodifiable = this.classUnderTest().asUnmodifiable();
        Verify.assertMapsEqual(this.classUnderTest(), unmodifiable);
        Verify.assertInstanceOf(UnmodifiableMap.class, unmodifiable);
    }

    @Test
    public void asSynchronized()
    {
        MutableMap<String, String> synchMap = this.classUnderTest().asSynchronized();
        Verify.assertMapsEqual(this.classUnderTest(), synchMap);
        Verify.assertInstanceOf(SynchronizedMutableMap.class, synchMap);
    }

    @Test
    public void newEmpty()
    {
        MutableMap<String, String> result = this.classUnderTest().newEmpty();
        Verify.assertEmpty(result);
    }

    @Test
    public void toImmutable()
    {
        MutableMap<String, String> map = this.classUnderTest();
        Verify.assertEqualsAndHashCode(map, map.toImmutable());
        Verify.assertInstanceOf(ImmutableMap.class, map.toImmutable());
    }

    @Test
    public abstract void testToString();

    @Test
    public abstract void testEqualsAndHashCode();

    @Test
    public void testPostSerializedEqualsAndHashCode()
    {
        Verify.assertPostSerializedEqualsAndHashCode(this.classUnderTest());
    }

    @Test
    public void testClone()
    {
        try
        {
            Verify.assertShallowClone(this.classUnderTest());
        }
        catch (Exception e)
        {
            // Suppress if a Java 9 specific exception related to reflection is thrown.
            if (!e.getClass().getCanonicalName().equals("java.lang.reflect.InaccessibleObjectException"))
            {
                throw e;
            }
        }
    }

    @Test
    public abstract void select();

    @Test
    public void collectValues()
    {
        MutableMap<String, String> map = this.classUnderTest();
        MutableMap<String, String> result = map.collectValues((argument1, argument2) -> new StringBuilder(argument2).reverse().toString());

        switch (map.size())
        {
            case 0:
                Verify.assertEmpty(result);
                break;
            case 1:
                Verify.assertContainsKeyValue("1", "enO", result);
                break;
            case 2:
                Verify.assertContainsAllKeyValues(result, "1", "enO", "2", "owT");
                break;
            case 3:
                Verify.assertContainsAllKeyValues(result, "1", "enO", "2", "owT", "3", "eerhT");
                break;
            default:
                Assert.fail();
        }
    }

    @Test
    public void collect()
    {
        MutableMap<String, String> map = this.classUnderTest();
        Function2<String, String, Pair<Integer, String>> function = (argument1, argument2) -> Tuples.pair(Integer.valueOf(argument1), argument1 + ':' + new StringBuilder(argument2).reverse());
        MutableMap<Integer, String> result = map.collect(function);

        switch (map.size())
        {
            case 0:
                Verify.assertEmpty(result);
                break;
            case 1:
                Verify.assertContainsKeyValue(1, "1:enO", result);
                break;
            case 2:
                Verify.assertContainsAllKeyValues(result, 1, "1:enO", 2, "2:owT");
                break;
            case 3:
                Verify.assertContainsAllKeyValues(result, 1, "1:enO", 2, "2:owT", 3, "3:eerhT");
                break;
            default:
                Assert.fail();
        }
    }

    @Test
    public abstract void reject();

    @Test
    public abstract void detect();

    protected abstract <K, V> FixedSizeMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2);

    protected abstract <K, V> FixedSizeMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3);

    @Test
    public void allSatisfy()
    {
        MutableMap<String, String> map = this.classUnderTest();

        Assert.assertTrue(map.allSatisfy(String.class::isInstance));
        Assert.assertFalse(map.allSatisfy("Monkey"::equals));
    }

    @Test
    public void noneSatisfy()
    {
        MutableMap<String, String> map = this.classUnderTest();

        Assert.assertTrue(map.noneSatisfy(Boolean.class::isInstance));
        Assert.assertFalse(map.noneSatisfy(String.class::isInstance));
        Assert.assertTrue(map.noneSatisfy("Monkey"::equals));
    }

    @Test
    public void anySatisfy()
    {
        MutableMap<String, String> map = this.classUnderTest();

        Assert.assertTrue(map.anySatisfy(String.class::isInstance));
        Assert.assertFalse(map.anySatisfy("Monkey"::equals));
    }

    @Test
    public void appendString()
    {
        MutableMap<String, String> map = this.classUnderTest();

        StringBuilder builder1 = new StringBuilder();
        map.appendString(builder1);
        String defaultString = builder1.toString();

        StringBuilder builder2 = new StringBuilder();
        map.appendString(builder2, "|");
        String delimitedString = builder2.toString();

        StringBuilder builder3 = new StringBuilder();
        map.appendString(builder3, "{", "|", "}");
        String wrappedString = builder3.toString();

        switch (map.size())
        {
            case 1:
                Assert.assertEquals("One", defaultString);
                Assert.assertEquals("One", delimitedString);
                Assert.assertEquals("{One}", wrappedString);
                break;
            case 2:
                Assert.assertEquals(8, defaultString.length());
                Assert.assertEquals(7, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                Assert.assertEquals(9, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                Assert.assertTrue(wrappedString.startsWith("{"));
                Assert.assertTrue(wrappedString.endsWith("}"));
                break;
            case 3:
                Assert.assertEquals(15, defaultString.length());
                Assert.assertEquals(13, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                Assert.assertEquals(15, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                Assert.assertTrue(wrappedString.startsWith("{"));
                Assert.assertTrue(wrappedString.endsWith("}"));
                break;
            default:
                Assert.assertEquals("", defaultString);
                Assert.assertEquals("", delimitedString);
                Assert.assertEquals("{}", wrappedString);
                break;
        }
    }

    @Test
    public void toBag()
    {
        MutableMap<String, String> map = this.classUnderTest();

        MutableBag<String> bag = map.toBag();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", bag);
                break;
            case 2:
                Verify.assertContainsAll(bag, "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(bag, "One", "Two", "Three");
                break;
            default:
                Verify.assertEmpty(bag);
                break;
        }
    }

    @Test
    public void flip()
    {
        MutableMap<String, String> map = this.classUnderTest();
        MutableMultimap<String, String> multi = map.flip();

        Set<String> keySet = map.keySet();
        multi.forEachValue(each -> Verify.assertContains(each, keySet));

        Collection<String> values = map.values();
        multi.forEachKey(each -> Verify.assertContains(each, values));

        Assert.assertEquals(multi.size(), map.size());
        Assert.assertTrue(multi.sizeDistinct() <= map.size()); // should be the same or less since values are degenerate

        MutableMap<String, Integer> siMap = this.mixedTypeClassUnderTest();
        MutableMultimap<Integer, String> siMulti = siMap.flip();
        Assert.assertEquals(siMulti.size(), siMap.size());
        Assert.assertTrue(siMulti.sizeDistinct() <= siMap.size());
    }

    @Test
    public void asLazy()
    {
        MutableMap<String, String> map = this.classUnderTest();

        LazyIterable<String> lazy = map.asLazy();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", lazy.toList());
                break;
            case 2:
                Verify.assertContainsAll(lazy.toList(), "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(lazy.toList(), "One", "Two", "Three");
                break;
            default:
                Verify.assertEmpty(lazy.toList());
                break;
        }
    }

    @Test
    public void toList()
    {
        MutableMap<String, String> map = this.classUnderTest();

        MutableList<String> list = map.toList();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", list);
                break;
            case 2:
                Verify.assertContainsAll(list, "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(list, "One", "Two", "Three");
                break;
            default:
                Verify.assertEmpty(list);
                break;
        }
    }

    @Test
    public void toMap()
    {
        MutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "3", "Three", "4", "Four");

        MutableMap<Integer, String> actual = map.toMap(String::length, String::valueOf);

        switch (map.size())
        {
            case 1:
                Assert.assertEquals(UnifiedMap.newWithKeysValues(3, "One"), actual);
                break;
            case 2:
                Assert.assertEquals(UnifiedMap.newWithKeysValues(3, "One", 5, "Three"), actual);
                break;
            case 3:
                Assert.assertEquals(UnifiedMap.newWithKeysValues(3, "One", 5, "Three", 4, "Four"), actual);
                break;
            default:
                Verify.assertEmpty(actual);
                break;
        }
    }

    @Test
    public void toSet()
    {
        MutableMap<String, String> map = this.classUnderTest();

        MutableSet<String> set = map.toSet();
        switch (map.size())
        {
            case 1:
                Verify.assertContains("One", set);
                break;
            case 2:
                Verify.assertContainsAll(set, "One", "Two");
                break;
            case 3:
                Verify.assertContainsAll(set, "One", "Two", "Three");
                break;
            default:
                Verify.assertEmpty(set);
                break;
        }
    }

    @Test
    public void toSortedList()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        MutableList<Integer> sorted = map.toSortedList();
        switch (map.size())
        {
            case 1:
                Assert.assertEquals(iList(1), sorted);
                break;
            case 2:
                Assert.assertEquals(iList(1, 2), sorted);
                break;
            case 3:
                Assert.assertEquals(iList(1, 2, 3), sorted);
                break;
            default:
                Verify.assertEmpty(sorted);
                break;
        }

        MutableList<Integer> reverse = map.toSortedList(Collections.reverseOrder());
        switch (map.size())
        {
            case 1:
                Assert.assertEquals(iList(1), reverse);
                break;
            case 2:
                Assert.assertEquals(iList(2, 1), reverse);
                break;
            case 3:
                Assert.assertEquals(iList(3, 2, 1), reverse);
                break;
            default:
                Verify.assertEmpty(reverse);
                break;
        }
    }

    @Test
    public void toSortedListBy()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        MutableList<Integer> list = map.toSortedListBy(String::valueOf);
        switch (map.size())
        {
            case 1:
                Assert.assertEquals(iList(1), list);
                break;
            case 2:
                Assert.assertEquals(iList(1, 2), list);
                break;
            case 3:
                Assert.assertEquals(iList(1, 2, 3), list);
                break;
            default:
                Verify.assertEmpty(list);
                break;
        }
    }

    @Test
    public void chunk()
    {
        MutableMap<String, String> map = this.classUnderTest();

        RichIterable<RichIterable<String>> chunks = map.chunk(2).toList();

        RichIterable<Integer> sizes = chunks.collect(RichIterable::size);

        switch (map.size())
        {
            case 1:
                Assert.assertEquals(iList(1), sizes);
                break;
            case 2:
                Assert.assertEquals(iList(2), sizes);
                break;
            case 3:
                Assert.assertEquals(iList(2, 1), sizes);
                break;
            default:
                Assert.assertEquals(0, chunks.size());
                break;
        }
    }

    @Test
    public void collect_value()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);
        MutableSet<String> collect = map.collect(Functions.getToString()).toSet();
        UnifiedSet<String> collectToTarget = map.collect(String::valueOf, UnifiedSet.newSet());

        switch (map.size())
        {
            case 1:
                Verify.assertContainsAll(collect, "1");
                Verify.assertContainsAll(collectToTarget, "1");
                break;
            case 2:
                Verify.assertContainsAll(collect, "1", "2");
                Verify.assertContainsAll(collectToTarget, "1", "2");
                break;
            case 3:
                Verify.assertContainsAll(collect, "1", "2", "3");
                Verify.assertContainsAll(collectToTarget, "1", "2", "3");
                break;
            default:
                Verify.assertEmpty(collect);
                break;
        }
    }

    @Test
    public void collectIf()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        MutableSet<String> collect = map.collectIf(Integer.class::isInstance, String::valueOf).toSet();
        UnifiedSet<String> collectToTarget = map.collectIf(Integer.class::isInstance, String::valueOf, UnifiedSet.newSet());

        switch (map.size())
        {
            case 1:
                Verify.assertContainsAll(collect, "1");
                Verify.assertContainsAll(collectToTarget, "1");
                break;
            case 2:
                Verify.assertContainsAll(collect, "1", "2");
                Verify.assertContainsAll(collectToTarget, "1", "2");
                break;
            case 3:
                Verify.assertContainsAll(collect, "1", "2", "3");
                Verify.assertContainsAll(collectToTarget, "1", "2", "3");
                break;
            default:
                Verify.assertEmpty(collect);
                break;
        }
    }

    @Test
    public void collectWith()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        MutableBag<Integer> collectWith = map.collectWith(AddFunction.INTEGER, 1, HashBag.newBag());

        switch (map.size())
        {
            case 1:
                Assert.assertEquals(Bags.mutable.of(2), collectWith);
                break;
            case 2:
                Assert.assertEquals(Bags.mutable.of(2, 3), collectWith);
                break;
            case 3:
                Assert.assertEquals(Bags.mutable.of(2, 3, 4), collectWith);
                break;
            default:
                Verify.assertEmpty(collectWith);
                break;
        }
    }

    @Test
    public void contains()
    {
        MutableMap<String, String> map = this.classUnderTest();

        switch (map.size())
        {
            case 1:
                Assert.assertTrue(map.contains("One"));
                Assert.assertFalse(map.contains("Two"));
                break;
            case 2:
                Assert.assertTrue(map.contains("Two"));
                break;
            case 3:
                Assert.assertTrue(map.contains("Two"));
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void getFirst()
    {
        MutableMap<String, String> map = this.classUnderTest();

        if (map.isEmpty())
        {
            String value = map.getFirst();
            Assert.assertNull(value);
        }
        else
        {
            String value = map.getFirst();
            Assert.assertNotNull(value);
            Verify.assertContains(value, map.values());
        }
    }

    @Test
    public void getLast()
    {
        MutableMap<String, String> map = this.classUnderTest();

        if (map.isEmpty())
        {
            String value = map.getLast();
            Assert.assertNull(value);
        }
        else
        {
            String value = map.getLast();
            Assert.assertNotNull(value);
            Verify.assertContains(value, map.values());
        }
    }

    @Test
    public void containsAllIterable()
    {
        MutableMap<String, String> map = this.classUnderTest();

        switch (map.size())
        {
            case 1:
                Assert.assertTrue(map.containsAllIterable(iList("One")));
                break;
            case 2:
                Assert.assertTrue(map.containsAllIterable(iList("One", "Two")));
                break;
            case 3:
                Assert.assertTrue(map.containsAllIterable(iList("One", "Two", "Three")));
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void containsAllArguments()
    {
        MutableMap<String, String> map = this.classUnderTest();

        switch (map.size())
        {
            case 1:
                Assert.assertTrue(map.containsAllArguments("One"));
                break;
            case 2:
                Assert.assertTrue(map.containsAllArguments("One", "Two"));
                break;
            case 3:
                Assert.assertTrue(map.containsAllArguments("One", "Two", "Three"));
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void count()
    {
        MutableMap<String, String> map = this.classUnderTest();

        int actual = map.count(Predicates.or("One"::equals, "Three"::equals));

        switch (map.size())
        {
            case 1:
                Assert.assertEquals(1, actual);
                break;
            case 2:
                Assert.assertEquals(1, actual);
                break;
            case 3:
                Assert.assertEquals(2, actual);
                break;
            default:
                Assert.assertEquals(0, actual);
                break;
        }
    }

    @Test
    public void detect_value()
    {
        MutableMap<String, String> map = this.classUnderTest();

        if (map.isEmpty())
        {
            String resultNotFound = map.detect(ignored -> true);
            Assert.assertNull(resultNotFound);
        }
        else
        {
            String resultFound = map.detect("One"::equals);
            Assert.assertEquals("One", resultFound);

            String resultNotFound = map.detect("Four"::equals);
            Assert.assertNull(resultNotFound);
        }
    }

    @Test
    public void detectIfNone_value()
    {
        MutableMap<String, String> map = this.classUnderTest();

        if (map.isEmpty())
        {
            String resultNotFound = map.detectIfNone(ignored -> true, () -> "Zero");
            Assert.assertEquals("Zero", resultNotFound);
        }
        else
        {
            String resultNotFound = map.detectIfNone("Four"::equals, () -> "Zero");
            Assert.assertEquals("Zero", resultNotFound);

            String resultFound = map.detectIfNone("One"::equals, () -> "Zero");
            Assert.assertEquals("One", resultFound);
        }
    }

    @Test
    public void flatten_value()
    {
        MutableMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two");

        Function<String, Iterable<Character>> function = object ->
        {
            MutableList<Character> result = Lists.mutable.of();
            if (object != null)
            {
                char[] chars = object.toCharArray();
                for (char aChar : chars)
                {
                    result.add(Character.valueOf(aChar));
                }
            }
            return result;
        };

        RichIterable<Character> blob = map.flatCollect(function);
        RichIterable<Character> blobFromTarget = map.flatCollect(function, FastList.newList());

        switch (map.size())
        {
            case 1:
                Assert.assertTrue(blob.containsAllArguments(
                        Character.valueOf('O'),
                        Character.valueOf('n'),
                        Character.valueOf('e')));
                Assert.assertTrue(blobFromTarget.containsAllArguments(
                        Character.valueOf('O'),
                        Character.valueOf('n'),
                        Character.valueOf('e')));
                break;
            case 2:
            case 3:
                Assert.assertTrue(blob.containsAllArguments(
                        Character.valueOf('O'),
                        Character.valueOf('n'),
                        Character.valueOf('e'),
                        Character.valueOf('T'),
                        Character.valueOf('w'),
                        Character.valueOf('o')));
                Assert.assertTrue(blobFromTarget.containsAllArguments(
                        Character.valueOf('O'),
                        Character.valueOf('n'),
                        Character.valueOf('e'),
                        Character.valueOf('T'),
                        Character.valueOf('w'),
                        Character.valueOf('o')));
                break;
            default:
                Assert.assertEquals(0, blob.size());
                Assert.assertEquals(0, blobFromTarget.size());
                break;
        }
    }

    @Test
    public void groupBy()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        Function<Integer, Boolean> isOddFunction = object -> IntegerPredicates.isOdd().accept(object);

        Multimap<Boolean, Integer> expected;

        switch (map.size())
        {
            case 1:
                expected = FastListMultimap.newMultimap(Tuples.pair(Boolean.TRUE, 1));
                break;
            case 2:
                expected = FastListMultimap.newMultimap(Tuples.pair(Boolean.TRUE, 1), Tuples.pair(Boolean.FALSE, 2));
                break;
            case 3:
                expected = FastListMultimap.newMultimap(Tuples.pair(Boolean.TRUE, 1), Tuples.pair(Boolean.TRUE, 3), Tuples.pair(Boolean.FALSE, 2));
                break;
            default:
                expected = FastListMultimap.newMultimap();
                break;
        }

        Multimap<Boolean, Integer> actual = map.groupBy(isOddFunction);
        Assert.assertEquals(HashBagMultimap.newMultimap(expected), HashBagMultimap.newMultimap(actual));

        Multimap<Boolean, Integer> actualFromTarget = map.groupBy(isOddFunction, FastListMultimap.newMultimap());
        Assert.assertEquals(HashBagMultimap.newMultimap(expected), HashBagMultimap.newMultimap(actualFromTarget));
    }

    @Test
    public void groupByEach()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        MutableMultimap<Integer, Integer> expected = FastListMultimap.newMultimap();
        for (int i = 1; i < map.size(); i++)
        {
            expected.putAll(-i, Interval.fromTo(i, map.size()));
        }

        NegativeIntervalFunction function = new NegativeIntervalFunction();
        Multimap<Integer, Integer> actual = map.groupByEach(function);
        expected.forEachKey(each ->
        {
            Assert.assertTrue(actual.containsKey(each));
            MutableList<Integer> values = actual.get(each).toList();
            Verify.assertNotEmpty(values);
            Assert.assertTrue(expected.get(each).containsAllIterable(values));
        });

        Multimap<Integer, Integer> actualFromTarget = map.groupByEach(function, FastListMultimap.newMultimap());
        expected.forEachKey(each ->
        {
            Assert.assertTrue(actualFromTarget.containsKey(each));
            MutableList<Integer> values = actualFromTarget.get(each).toList();
            Verify.assertNotEmpty(values);
            Assert.assertTrue(expected.get(each).containsAllIterable(values));
        });
    }

    @Test
    public void injectInto()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        Integer expectedInteger;
        IntegerSum expectedSum;

        switch (map.size())
        {
            case 1:
                expectedSum = new IntegerSum(1);
                expectedInteger = Integer.valueOf(1);
                break;
            case 2:
                expectedSum = new IntegerSum(3);
                expectedInteger = Integer.valueOf(3);
                break;
            case 3:
                expectedSum = new IntegerSum(6);
                expectedInteger = Integer.valueOf(6);
                break;
            default:
                expectedSum = new IntegerSum(0);
                expectedInteger = Integer.valueOf(0);
                break;
        }

        Integer actual = map.injectInto(0, AddFunction.INTEGER);
        Assert.assertEquals(expectedInteger, actual);

        Sum sum = map.injectInto(new IntegerSum(0), SumProcedure.number());
        Assert.assertEquals(expectedSum, sum);
    }

    @Test
    public void makeString()
    {
        MutableMap<String, String> map = this.classUnderTest();

        String defaultString = map.makeString();
        String delimitedString = map.makeString("|");
        String wrappedString = map.makeString("{", "|", "}");

        switch (map.size())
        {
            case 1:
                Assert.assertEquals("One", defaultString);
                Assert.assertEquals("One", delimitedString);
                Assert.assertEquals("{One}", wrappedString);
                break;
            case 2:
                Assert.assertEquals(8, defaultString.length());
                Assert.assertEquals(7, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                Assert.assertEquals(9, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                Assert.assertTrue(wrappedString.startsWith("{"));
                Assert.assertTrue(wrappedString.endsWith("}"));
                break;
            case 3:
                Assert.assertEquals(15, defaultString.length());
                Assert.assertEquals(13, delimitedString.length());
                Verify.assertContains("|", delimitedString);
                Assert.assertEquals(15, wrappedString.length());
                Verify.assertContains("|", wrappedString);
                Assert.assertTrue(wrappedString.startsWith("{"));
                Assert.assertTrue(wrappedString.endsWith("}"));
                break;
            default:
                Assert.assertEquals("", defaultString);
                Assert.assertEquals("", delimitedString);
                Assert.assertEquals("{}", wrappedString);
                break;
        }
    }

    @Test
    public void min()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        Assert.assertEquals(Integer.valueOf(1), map.min());
        Assert.assertEquals(Integer.valueOf(1), map.min(Integer::compareTo));
    }

    @Test
    public void max()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        Integer max;

        switch (map.size())
        {
            case 1:
                max = Integer.valueOf(1);
                break;
            case 2:
                max = Integer.valueOf(2);
                break;
            case 3:
                max = Integer.valueOf(3);
                break;
            default:
                throw new IllegalStateException("We should not get here, this test method should be overriden by the empty map.");
        }

        Assert.assertEquals(max, map.max());
        Assert.assertEquals(max, map.max(Integer::compareTo));
    }

    @Test
    public void minBy()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        Assert.assertEquals(Integer.valueOf(1), map.minBy(String::valueOf));
    }

    @Test
    public void maxBy()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);
        Assert.assertEquals(Integer.valueOf(map.size()), map.maxBy(String::valueOf));
        Verify.assertContains(Integer.valueOf(map.size()), iList(1, 2, 3));
    }

    @Test
    public void reject_value()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        MutableSet<Integer> rejected = map.reject(IntegerPredicates.isEven()).toSet();
        UnifiedSet<Integer> rejectedIntoTarget = map.reject(IntegerPredicates.isEven(), UnifiedSet.newSet());

        ImmutableSet<Integer> expected = this.expectReject(map.size());
        Assert.assertEquals(expected, rejected);
        Assert.assertEquals(expected, rejectedIntoTarget);
    }

    private ImmutableSet<Integer> expectReject(int size)
    {
        switch (size)
        {
            case 0:
                return iSet();
            case 1:
            case 2:
                return iSet(1);
            case 3:
                return iSet(1, 3);
            default:
                throw new AssertionError();
        }
    }

    @Test
    public void rejectWith_value()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        switch (map.size())
        {
            case 1:
                Verify.assertEmpty(map.rejectWith(Predicates2.lessThan(), 2, UnifiedSet.newSet()));
                break;
            case 2:
                Verify.assertContainsAll(map.rejectWith(Predicates2.lessThan(), 2, UnifiedSet.newSet()), 2);
                break;
            case 3:
                Verify.assertContainsAll(map.rejectWith(Predicates2.lessThan(), 2, UnifiedSet.newSet()), 2, 3);
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void select_value()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);
        ImmutableSet<Integer> expected = this.expectSelect(map.size());

        Assert.assertEquals(expected, map.select(IntegerPredicates.isEven()).toSet());
        Assert.assertEquals(expected, map.select(IntegerPredicates.isEven(), UnifiedSet.newSet()));
    }

    private ImmutableSet<Integer> expectSelect(int size)
    {
        switch (size)
        {
            case 0:
            case 1:
                return iSet();
            case 2:
            case 3:
                return iSet(2);
            default:
                throw new AssertionError();
        }
    }

    @Test
    public void selectWith_value()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        switch (map.size())
        {
            case 1:
                Verify.assertContainsAll(map.selectWith(Predicates2.lessThan(), 3, UnifiedSet.newSet()), 1);
                break;
            case 2:
                Verify.assertContainsAll(map.selectWith(Predicates2.lessThan(), 3, UnifiedSet.newSet()), 1, 2);
                break;
            case 3:
                Verify.assertContainsAll(map.selectWith(Predicates2.lessThan(), 3, UnifiedSet.newSet()), 1, 2);
                break;
            default:
                Verify.assertEmpty(map);
                break;
        }
    }

    @Test
    public void partition_value()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);
        PartitionMutableCollection<Integer> partition = map.partition(IntegerPredicates.isEven());

        Assert.assertEquals(this.expectSelect(map.size()), partition.getSelected().toSet());
        Assert.assertEquals(this.expectReject(map.size()), partition.getRejected().toSet());
    }

    @Test
    public void partitionWith_value()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);
        PartitionMutableCollection<Integer> partition = map.partitionWith(Predicates2.in(), map.select(IntegerPredicates.isEven()));

        Assert.assertEquals(this.expectSelect(map.size()), partition.getSelected().toSet());
        Assert.assertEquals(this.expectReject(map.size()), partition.getRejected().toSet());
    }

    @Test
    public void toArray()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);

        Object[] array = map.toArray();
        Verify.assertSize(map.size(), array);

        Integer[] array2 = map.toArray(new Integer[0]);
        Verify.assertSize(map.size(), array2);

        Integer[] array3 = map.toArray(new Integer[map.size()]);
        Verify.assertSize(map.size(), array3);

        if (map.size() > 1)
        {
            Integer[] array4 = map.toArray(new Integer[map.size() - 1]);
            Verify.assertSize(map.size(), array4);
        }

        Integer[] array5 = map.toArray(new Integer[6]);
        Verify.assertSize(6, array5);
    }

    @Test
    public void zip()
    {
        MutableMap<String, String> map = this.classUnderTest();

        List<Object> nulls = Collections.nCopies(map.size(), null);
        List<Object> nullsPlusOne = Collections.nCopies(map.size() + 1, null);

        RichIterable<Pair<String, Object>> pairs = map.zip(nulls);
        Assert.assertEquals(
                map.toSet(),
                pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet());
        Assert.assertEquals(
                nulls,
                pairs.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        RichIterable<Pair<String, Object>> pairsPlusOne = map.zip(nullsPlusOne);
        Assert.assertEquals(
                map.toSet(),
                pairsPlusOne.collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet());
        Assert.assertEquals(nulls, pairsPlusOne.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        if (map.notEmpty())
        {
            List<Object> nullsMinusOne = Collections.nCopies(map.size() - 1, null);
            RichIterable<Pair<String, Object>> pairsMinusOne = map.zip(nullsMinusOne);
            Assert.assertEquals(map.size() - 1, pairsMinusOne.size());
            Assert.assertTrue(map.values().containsAll(pairsMinusOne.collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet()));
        }

        Assert.assertEquals(
                map.zip(nulls).toSet(),
                map.zip(nulls, UnifiedSet.newSet()));
    }

    @Test
    public void zipWithIndex()
    {
        MutableMap<String, String> map = this.classUnderTest();

        RichIterable<Pair<String, Integer>> pairs = map.zipWithIndex();

        Assert.assertEquals(
                map.toSet(),
                pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne).toSet());
        if (map.notEmpty())
        {
            Assert.assertEquals(
                    Interval.zeroTo(map.size() - 1).toSet(),
                    pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo, UnifiedSet.newSet()));
        }

        Assert.assertEquals(
                map.zipWithIndex().toSet(),
                map.zipWithIndex(UnifiedSet.newSet()));
    }

    @Test
    public abstract void withKeyValue();

    @Test
    public abstract void withAllKeyValueArguments();

    @Test
    public abstract void withoutKey();

    @Test
    public abstract void withoutAllKeys();

    @Test
    public abstract void forEachKeyValue();

    @Test
    public abstract void nonUniqueWithKeyValue();

    @Test
    public abstract void forEachValue();

    @Test
    public abstract void forEachKey();

    @Test
    public abstract void getIfAbsentPut();

    @Test
    public abstract void getIfAbsent_function();

    @Test
    public abstract void getIfAbsent();

    @Test
    public abstract void ifPresentApply();

    @Test
    public abstract void notEmpty();

    @Test
    public abstract void forEachWith();

    @Test
    public abstract void forEachWithIndex();

    @Test
    public abstract void entrySet();

    @Test
    public abstract void values();

    @Test
    public abstract void keySet();

    @Test
    public void tap()
    {
        MutableList<String> tapResult = Lists.mutable.of();
        MutableMap<String, String> map = this.classUnderTest();
        Assert.assertSame(map, map.tap(tapResult::add));
        Assert.assertEquals(map.toList(), tapResult);
    }

    @Test
    public abstract void forEach();

    @Test
    public abstract void iterator();

    @Test
    public abstract void getIfAbsentPutWith();

    @Test
    public abstract void getIfAbsentWith();

    @Test
    public abstract void asLazyKeys();

    @Test
    public abstract void asLazyValues();
}
