/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable.primitive;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.BooleanSets;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract JUnit test for {@link BooleanIterable}s.
 */
public abstract class AbstractBooleanIterableTestCase
{
    protected abstract BooleanIterable classUnderTest();

    protected abstract BooleanIterable newWith(boolean... elements);

    protected abstract BooleanIterable newMutableCollectionWith(boolean... elements);

    protected abstract RichIterable<Object> newObjectCollectionWith(Object... elements);

    @Test
    public void newCollectionWith()
    {
        BooleanIterable iterable = this.newWith(true, false, true);
        Verify.assertSize(3, iterable);
        assertTrue(iterable.containsAll(true, false, true));

        BooleanIterable iterable1 = this.newWith();
        Verify.assertEmpty(iterable1);
        assertFalse(iterable1.containsAll(true, false, true));

        BooleanIterable iterable2 = this.newWith(true);
        Verify.assertSize(1, iterable2);
        assertFalse(iterable2.containsAll(true, false, true));
        assertTrue(iterable2.containsAll(true, true));
    }

    @Test
    public void newCollection()
    {
        assertEquals(this.newMutableCollectionWith(), this.newWith());
        assertEquals(this.newMutableCollectionWith(true, false, true), this.newWith(true, false, true));
    }

    @Test
    public void isEmpty()
    {
        Verify.assertEmpty(this.newWith());
        Verify.assertNotEmpty(this.classUnderTest());
        Verify.assertNotEmpty(this.newWith(false));
        Verify.assertNotEmpty(this.newWith(true));
    }

    @Test
    public void notEmpty()
    {
        assertFalse(this.newWith().notEmpty());
        assertTrue(this.classUnderTest().notEmpty());
        assertTrue(this.newWith(false).notEmpty());
        assertTrue(this.newWith(true).notEmpty());
    }

    @Test
    public void contains()
    {
        BooleanIterable emptyCollection = this.newWith();
        assertFalse(emptyCollection.contains(true));
        assertFalse(emptyCollection.contains(false));
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        assertEquals(size >= 1, booleanIterable.contains(true));
        assertEquals(size >= 2, booleanIterable.contains(false));
        assertFalse(this.newWith(true, true, true).contains(false));
        assertFalse(this.newWith(false, false, false).contains(true));
    }

    @Test
    public void containsAllArray()
    {
        BooleanIterable iterable = this.classUnderTest();
        int size = iterable.size();
        assertEquals(size >= 1, iterable.containsAll(true));
        assertEquals(size >= 2, iterable.containsAll(true, false, true));
        assertEquals(size >= 2, iterable.containsAll(true, false));
        assertEquals(size >= 1, iterable.containsAll(true, true));
        assertEquals(size >= 2, iterable.containsAll(false, false));

        BooleanIterable emptyCollection = this.newWith();
        assertFalse(emptyCollection.containsAll(true));
        assertFalse(emptyCollection.containsAll(false));
        assertFalse(emptyCollection.containsAll(false, true, false));
        assertFalse(this.newWith(true, true).containsAll(false, true, false));

        BooleanIterable trueCollection = this.newWith(true, true, true, true);
        assertFalse(trueCollection.containsAll(true, false));
        BooleanIterable falseCollection = this.newWith(false, false, false, false);
        assertFalse(falseCollection.containsAll(true, false));
    }

    @Test
    public void containsAllIterable()
    {
        BooleanIterable emptyCollection = this.newWith();
        assertTrue(emptyCollection.containsAll(new BooleanArrayList()));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        assertTrue(booleanIterable.containsAll(new BooleanArrayList()));
        assertEquals(size >= 1, booleanIterable.containsAll(BooleanArrayList.newListWith(true)));
        assertEquals(size >= 2, booleanIterable.containsAll(BooleanArrayList.newListWith(false)));
        assertEquals(size >= 2, booleanIterable.containsAll(BooleanArrayList.newListWith(true, false)));
        BooleanIterable iterable = this.newWith(true, true, false, false, false);
        assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true)));
        assertTrue(iterable.containsAll(BooleanArrayList.newListWith(false)));
        assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(iterable.containsAll(BooleanArrayList.newListWith(false, false)));
        assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true, false, true)));
        assertFalse(this.newWith(true, true).containsAll(BooleanArrayList.newListWith(false, true, false)));

        BooleanIterable trueCollection = this.newWith(true, true, true, true);
        assertFalse(trueCollection.containsAll(BooleanArrayList.newListWith(true, false)));
        BooleanIterable falseCollection = this.newWith(false, false, false, false);
        assertFalse(falseCollection.containsAll(BooleanArrayList.newListWith(true, false)));
    }

    @Test
    public void containsAnyArray()
    {
        BooleanIterable iterable = this.newWith(true);
        assertTrue(iterable.containsAny(true, false));
        assertFalse(iterable.containsAny());
        assertTrue(iterable.containsAny(true));
        assertFalse(iterable.containsAny(false, false, false));

        BooleanIterable iterable2 = this.newWith(true, false);
        assertTrue(iterable2.containsAny(true));
        assertFalse(iterable2.containsAny());
        assertTrue(iterable2.containsAny(false, false));
        assertTrue(iterable2.containsAny(true, false, true, false));

        BooleanIterable emptyIterable = this.newWith();
        assertFalse(emptyIterable.containsAny(true, true));
        assertFalse(emptyIterable.containsAny());
        assertFalse(emptyIterable.containsAny(false, true, true));
        assertFalse(emptyIterable.containsAny(false));
    }

    @Test
    public void containsAnyIterable()
    {
        BooleanIterable iterable = this.newWith(true);
        assertTrue(iterable.containsAny(BooleanLists.immutable.with(true, false)));
        assertFalse(iterable.containsAny(BooleanLists.mutable.empty()));
        assertTrue(iterable.containsAny(BooleanLists.immutable.with(true)));
        assertFalse(iterable.containsAny(BooleanLists.immutable.with(false, false, false)));

        BooleanIterable iterable2 = this.newWith(true, false);
        assertTrue(iterable2.containsAny(BooleanSets.immutable.with(true)));
        assertFalse(iterable2.containsAny(BooleanSets.mutable.empty()));
        assertTrue(iterable2.containsAny(BooleanSets.immutable.with(false, false)));
        assertTrue(iterable2.containsAny(BooleanSets.mutable.with(true, false, true, false)));

        BooleanIterable emptyIterable = this.newWith();
        assertFalse(emptyIterable.containsAny(BooleanLists.immutable.with(true, true)));
        assertFalse(emptyIterable.containsAny(BooleanLists.mutable.empty()));
        assertFalse(emptyIterable.containsAny(BooleanLists.immutable.with(false, true, true)));
        assertFalse(emptyIterable.containsAny(BooleanLists.mutable.with(false)));
    }

    @Test
    public void containsNoneArray()
    {
        BooleanIterable iterable = this.newWith(false);
        assertTrue(iterable.containsNone(true, true));
        assertTrue(iterable.containsNone());
        assertFalse(iterable.containsNone(true, false));
        assertFalse(iterable.containsNone(false));

        BooleanIterable iterable2 = this.newWith(true, false, false);
        assertFalse(iterable2.containsNone(true, false));
        assertTrue(iterable2.containsNone());
        assertFalse(iterable2.containsNone(false, false, false));
        assertFalse(iterable2.containsNone(false));

        BooleanIterable emptyIterable = this.newWith();
        assertTrue(emptyIterable.containsNone(true, true));
        assertTrue(emptyIterable.containsNone());
        assertTrue(emptyIterable.containsNone(true, false));
        assertTrue(emptyIterable.containsNone(false));
    }

    @Test
    public void containsNoneIterable()
    {
        BooleanIterable iterable = this.newWith(false);
        assertTrue(iterable.containsNone(BooleanLists.immutable.with(true, true)));
        assertTrue(iterable.containsNone(BooleanLists.mutable.empty()));
        assertFalse(iterable.containsNone(BooleanLists.immutable.with(true, false)));
        assertFalse(iterable.containsNone(BooleanLists.mutable.with(false)));

        BooleanIterable iterable2 = this.newWith(true, false, false);
        assertFalse(iterable2.containsNone(BooleanSets.immutable.with(true, false)));
        assertTrue(iterable2.containsNone(BooleanSets.mutable.empty()));
        assertFalse(iterable2.containsNone(BooleanSets.immutable.with(false, false, false)));
        assertFalse(iterable2.containsNone(BooleanSets.mutable.with(false)));

        BooleanIterable emptyIterable = this.newWith();
        assertTrue(emptyIterable.containsNone(BooleanLists.immutable.with(true, true)));
        assertTrue(emptyIterable.containsNone(BooleanLists.mutable.empty()));
        assertTrue(emptyIterable.containsNone(BooleanLists.immutable.with(true, false)));
        assertTrue(emptyIterable.containsNone(BooleanLists.mutable.with(false)));
    }

    @Test
    public void iterator_throws()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void iterator_throws_non_empty_collection()
    {
        BooleanIterable iterable = this.newWith(true, true, true);
        BooleanIterator iterator = iterable.booleanIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void iterator_throws_emptyList()
    {
        assertThrows(NoSuchElementException.class, () -> this.newWith().booleanIterator().next());
    }

    @Test
    public void booleanIterator()
    {
        BooleanArrayList list = BooleanArrayList.newListWith(true, false, true);
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        for (int i = 0; i < 3; i++)
        {
            assertTrue(iterator.hasNext());
            assertTrue(list.remove(iterator.next()));
        }
        Verify.assertEmpty(list);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void forEach()
    {
        long[] sum = new long[1];
        this.classUnderTest().forEach(each -> sum[0] += each ? 1 : 0);

        int size = this.classUnderTest().size();
        int halfSize = size / 2;
        assertEquals((size & 1) == 0 ? halfSize : halfSize + 1, sum[0]);

        long[] sum1 = new long[1];
        this.newWith(true, false, false, true, true, true).forEach(each -> sum1[0] += each ? 1 : 2);

        assertEquals(8L, sum1[0]);
    }

    @Test
    public void size()
    {
        Verify.assertSize(0, this.newWith());
        Verify.assertSize(1, this.newWith(true));
        Verify.assertSize(1, this.newWith(false));
        Verify.assertSize(2, this.newWith(true, false));
    }

    @Test
    public void count()
    {
        assertEquals(2L, this.newWith(true, false, true).count(BooleanPredicates.isTrue()));
        assertEquals(0L, this.newWith().count(BooleanPredicates.isFalse()));

        BooleanIterable iterable = this.newWith(true, false, false, true, true, true);
        assertEquals(4L, iterable.count(BooleanPredicates.isTrue()));
        assertEquals(2L, iterable.count(BooleanPredicates.isFalse()));
        assertEquals(6L, iterable.count(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));

        BooleanIterable iterable1 = this.classUnderTest();
        int size = iterable1.size();
        int halfSize = size / 2;
        assertEquals((size & 1) == 1 ? halfSize + 1 : halfSize, iterable1.count(BooleanPredicates.isTrue()));
        assertEquals(halfSize, iterable1.count(BooleanPredicates.isFalse()));
    }

    @Test
    public void anySatisfy()
    {
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        assertEquals(size >= 1, booleanIterable.anySatisfy(BooleanPredicates.isTrue()));
        assertEquals(size >= 2, booleanIterable.anySatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.newWith(true, true).anySatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.newWith().anySatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.newWith().anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith(true).anySatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.newWith(false).anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith(false, false, false).anySatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void allSatisfy()
    {
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        assertEquals(size <= 1, booleanIterable.allSatisfy(BooleanPredicates.isTrue()));
        assertEquals(size == 0, booleanIterable.allSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.newWith().allSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith().allSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.newWith(false, false).allSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.newWith(true, false).allSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith(true, true, true).allSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith(false, false, false).allSatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void noneSatisfy()
    {
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        assertEquals(size == 0, booleanIterable.noneSatisfy(BooleanPredicates.isTrue()));
        assertEquals(size <= 1, booleanIterable.noneSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.newWith().noneSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith().noneSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.newWith(false, false).noneSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith(true, true).noneSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.newWith(true, true).noneSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.newWith(false, false, false).noneSatisfy(BooleanPredicates.isTrue()));
    }

    @Test
    public void select()
    {
        BooleanIterable iterable = this.classUnderTest();
        int size = iterable.size();
        int halfSize = size / 2;
        Verify.assertSize((size & 1) == 1 ? halfSize + 1 : halfSize, iterable.select(BooleanPredicates.isTrue()));
        Verify.assertSize(halfSize, iterable.select(BooleanPredicates.isFalse()));

        BooleanIterable iterable1 = this.newWith(false, true, false, false, true, true, true);
        assertEquals(this.newMutableCollectionWith(true, true, true, true), iterable1.select(BooleanPredicates.isTrue()));
        assertEquals(this.newMutableCollectionWith(false, false, false), iterable1.select(BooleanPredicates.isFalse()));
    }

    @Test
    public void reject()
    {
        BooleanIterable iterable = this.classUnderTest();
        int size = iterable.size();
        int halfSize = size / 2;
        Verify.assertSize(halfSize, iterable.reject(BooleanPredicates.isTrue()));
        Verify.assertSize((size & 1) == 1 ? halfSize + 1 : halfSize, iterable.reject(BooleanPredicates.isFalse()));

        BooleanIterable iterable1 = this.newWith(false, true, false, false, true, true, true);
        assertEquals(this.newMutableCollectionWith(false, false, false), iterable1.reject(BooleanPredicates.isTrue()));
        assertEquals(this.newMutableCollectionWith(true, true, true, true), iterable1.reject(BooleanPredicates.isFalse()));
    }

    @Test
    public void detectIfNone()
    {
        BooleanIterable iterable = this.classUnderTest();
        int size = iterable.size();
        assertEquals(size < 2, iterable.detectIfNone(BooleanPredicates.isFalse(), true));
        assertTrue(iterable.detectIfNone(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), true));

        BooleanIterable iterable1 = this.newWith(true, true, true);
        assertFalse(iterable1.detectIfNone(BooleanPredicates.isFalse(), false));
        assertTrue(iterable1.detectIfNone(BooleanPredicates.isFalse(), true));
        assertTrue(iterable1.detectIfNone(BooleanPredicates.isTrue(), false));
        assertTrue(iterable1.detectIfNone(BooleanPredicates.isTrue(), true));

        BooleanIterable iterable2 = this.newWith(false, false, false);
        assertTrue(iterable2.detectIfNone(BooleanPredicates.isTrue(), true));
        assertFalse(iterable2.detectIfNone(BooleanPredicates.isTrue(), false));
        assertFalse(iterable2.detectIfNone(BooleanPredicates.isFalse(), true));
        assertFalse(iterable2.detectIfNone(BooleanPredicates.isFalse(), false));
    }

    @Test
    public void collect()
    {
        MutableList<Object> objects = FastList.newListWith();
        for (int i = 0; i < this.classUnderTest().size(); i++)
        {
            objects.add((i & 1) == 0 ? 1 : 0);
        }
        RichIterable<Object> expected = this.newObjectCollectionWith(objects.toArray());
        assertEquals(expected, this.classUnderTest().collect(value -> Integer.valueOf(value ? 1 : 0)));

        assertEquals(this.newObjectCollectionWith(false, true, false), this.newWith(true, false, true).collect(parameter -> !parameter));
        assertEquals(this.newObjectCollectionWith(), this.newWith().collect(parameter -> !parameter));
        assertEquals(this.newObjectCollectionWith(true), this.newWith(false).collect(parameter -> !parameter));
    }

    @Test
    public void injectInto()
    {
        BooleanIterable iterable = this.newWith(true, false, true);
        MutableInteger result = iterable.injectInto(new MutableInteger(0), (object, value) -> object.add(value ? 1 : 0));
        assertEquals(new MutableInteger(2), result);
    }

    @Test
    public void reduceOnEmptyThrows()
    {
        assertThrows(NoSuchElementException.class, () -> this.newWith().reduce((boolean result, boolean value) -> result && value));
    }

    @Test
    public void reduce()
    {
        BooleanIterable iterable1 = this.newWith(true, false, true);
        boolean and = iterable1.reduce((boolean result, boolean value) -> result && value);
        assertFalse(and);

        BooleanIterable iterable2 = this.newWith(true, true, true);
        boolean and2 = iterable2.reduce((boolean result, boolean value) -> result && value);
        assertTrue(and2);

        BooleanIterable iterable3 = this.newWith(true, false, true);
        boolean or = iterable3.reduce((boolean result, boolean value) -> result || value);
        assertTrue(or);

        BooleanIterable iterable4 = this.newWith(false, false, false);
        boolean or2 = iterable4.reduce((boolean result, boolean value) -> result || value);
        assertFalse(or2);
    }

    @Test
    public void reduceIfEmpty()
    {
        assertTrue(this.newWith().reduceIfEmpty((boolean result, boolean value) -> result && value, true));
        assertFalse(this.newWith().reduceIfEmpty((boolean result, boolean value) -> result && value, false));

        BooleanIterable iterable1 = this.newWith(true, false, true);
        boolean and = iterable1.reduceIfEmpty((boolean result, boolean value) -> result && value, true);
        assertFalse(and);

        BooleanIterable iterable2 = this.newWith(true, true, true);
        boolean and2 = iterable2.reduceIfEmpty((boolean result, boolean value) -> result && value, false);
        assertTrue(and2);

        BooleanIterable iterable3 = this.newWith(true, false, true);
        boolean or = iterable3.reduceIfEmpty((boolean result, boolean value) -> result || value, false);
        assertTrue(or);

        BooleanIterable iterable4 = this.newWith(false, false, false);
        boolean or2 = iterable4.reduceIfEmpty((boolean result, boolean value) -> result || value, true);
        assertFalse(or2);
    }

    @Test
    public void toArray()
    {
        assertEquals(0L, this.newWith().toArray().length);
        assertTrue(Arrays.equals(new boolean[]{true}, this.newWith(true).toArray()));
        assertTrue(Arrays.equals(new boolean[]{false, true}, this.newWith(true, false).toArray())
                || Arrays.equals(new boolean[]{true, false}, this.newWith(true, false).toArray()));
    }

    @Test
    public void testEquals()
    {
        BooleanIterable iterable1 = this.newWith(true, false, true, false);
        BooleanIterable iterable2 = this.newWith(true, false, true, false);
        BooleanIterable iterable3 = this.newWith(false, false, false, true);
        BooleanIterable iterable4 = this.newWith(true, true, true);
        BooleanIterable iterable5 = this.newWith(true, true, false, false, false);
        BooleanIterable iterable6 = this.newWith(true);

        Verify.assertEqualsAndHashCode(iterable1, iterable2);
        Verify.assertEqualsAndHashCode(this.newWith(), this.newWith());
        Verify.assertPostSerializedEqualsAndHashCode(iterable6);
        Verify.assertPostSerializedEqualsAndHashCode(iterable1);
        Verify.assertPostSerializedEqualsAndHashCode(iterable5);
        assertNotEquals(iterable1, iterable3);
        assertNotEquals(iterable1, iterable4);
        assertNotEquals(this.newWith(), this.newWith(true));
        assertNotEquals(iterable6, this.newWith(true, false));
    }

    @Test
    public void testHashCode()
    {
        assertEquals(this.newObjectCollectionWith().hashCode(), this.newWith().hashCode());
        assertEquals(
                this.newObjectCollectionWith(true, false, true).hashCode(),
                this.newWith(true, false, true).hashCode());
        assertEquals(
                this.newObjectCollectionWith(true).hashCode(),
                this.newWith(true).hashCode());
        assertEquals(
                this.newObjectCollectionWith(false).hashCode(),
                this.newWith(false).hashCode());
    }

    @Test
    public void testToString()
    {
        assertEquals("[]", this.newWith().toString());
        assertEquals("[true]", this.newWith(true).toString());
        BooleanIterable iterable = this.newWith(true, false);
        assertTrue("[true, false]".equals(iterable.toString())
                || "[false, true]".equals(iterable.toString()));
    }

    @Test
    public void makeString()
    {
        assertEquals("true", this.newWith(true).makeString("/"));
        assertEquals("", this.newWith().makeString());
        assertEquals("", this.newWith().makeString("/"));
        assertEquals("[]", this.newWith().makeString("[", "/", "]"));
        BooleanIterable iterable = this.newWith(true, false);
        assertTrue("true, false".equals(iterable.makeString())
                || "false, true".equals(iterable.makeString()));
        assertTrue("true/false".equals(iterable.makeString("/"))
                || "false/true".equals(iterable.makeString("/")), iterable.makeString("/"));
        assertTrue("[true/false]".equals(iterable.makeString("[", "/", "]"))
                || "[false/true]".equals(iterable.makeString("[", "/", "]")), iterable.makeString("[", "/", "]"));
    }

    @Test
    public void appendString()
    {
        StringBuilder appendable = new StringBuilder();
        this.newWith().appendString(appendable);
        assertEquals("", appendable.toString());
        this.newWith().appendString(appendable, "/");
        assertEquals("", appendable.toString());
        this.newWith().appendString(appendable, "[", "/", "]");
        assertEquals("[]", appendable.toString());
        StringBuilder appendable1 = new StringBuilder();
        this.newWith(true).appendString(appendable1);
        assertEquals("true", appendable1.toString());
        StringBuilder appendable2 = new StringBuilder();
        BooleanIterable iterable = this.newWith(true, false);
        iterable.appendString(appendable2);
        assertTrue("true, false".equals(appendable2.toString())
                || "false, true".equals(appendable2.toString()));
        StringBuilder appendable3 = new StringBuilder();
        iterable.appendString(appendable3, "/");
        assertTrue("true/false".equals(appendable3.toString())
                || "false/true".equals(appendable3.toString()));
        StringBuilder appendable4 = new StringBuilder();
        iterable.appendString(appendable4, "[", ", ", "]");
        assertEquals(iterable.toString(), appendable4.toString());
    }

    @Test
    public void toList()
    {
        BooleanIterable iterable = this.newWith(true, false);
        assertTrue(BooleanArrayList.newListWith(false, true).equals(iterable.toList())
                || BooleanArrayList.newListWith(true, false).equals(iterable.toList()));
        BooleanIterable iterable1 = this.newWith(true);
        assertEquals(BooleanArrayList.newListWith(true), iterable1.toList());
        BooleanIterable iterable0 = this.newWith();
        assertEquals(BooleanArrayList.newListWith(), iterable0.toList());
    }

    @Test
    public void toSet()
    {
        assertEquals(BooleanHashSet.newSetWith(), this.newWith().toSet());
        assertEquals(BooleanHashSet.newSetWith(true), this.newWith(true).toSet());
        assertEquals(BooleanHashSet.newSetWith(true, false), this.newWith(true, false, false, true, true, true).toSet());
    }

    @Test
    public void toBag()
    {
        assertEquals(BooleanHashBag.newBagWith(), this.newWith().toBag());
        assertEquals(BooleanHashBag.newBagWith(true), this.newWith(true).toBag());
        assertEquals(BooleanHashBag.newBagWith(true, false, true), this.newWith(true, false, true).toBag());
        assertEquals(BooleanHashBag.newBagWith(false, false, true, true, true, true), this.newWith(true, false, false, true, true, true).toBag());
    }

    @Test
    public void asLazy()
    {
        BooleanIterable iterable = this.classUnderTest();
        assertEquals(iterable.toBag(), iterable.asLazy().toBag());
        Verify.assertInstanceOf(LazyBooleanIterable.class, iterable.asLazy());
    }
}
