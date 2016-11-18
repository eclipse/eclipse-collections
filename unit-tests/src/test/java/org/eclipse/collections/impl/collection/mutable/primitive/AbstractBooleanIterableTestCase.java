/*
 * Copyright (c) 2015 Goldman Sachs.
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
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(iterable.containsAll(true, false, true));

        BooleanIterable iterable1 = this.newWith();
        Verify.assertEmpty(iterable1);
        Assert.assertFalse(iterable1.containsAll(true, false, true));

        BooleanIterable iterable2 = this.newWith(true);
        Verify.assertSize(1, iterable2);
        Assert.assertFalse(iterable2.containsAll(true, false, true));
        Assert.assertTrue(iterable2.containsAll(true, true));
    }

    @Test
    public void newCollection()
    {
        Assert.assertEquals(this.newMutableCollectionWith(), this.newWith());
        Assert.assertEquals(this.newMutableCollectionWith(true, false, true), this.newWith(true, false, true));
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
        Assert.assertFalse(this.newWith().notEmpty());
        Assert.assertTrue(this.classUnderTest().notEmpty());
        Assert.assertTrue(this.newWith(false).notEmpty());
        Assert.assertTrue(this.newWith(true).notEmpty());
    }

    @Test
    public void contains()
    {
        BooleanIterable emptyCollection = this.newWith();
        Assert.assertFalse(emptyCollection.contains(true));
        Assert.assertFalse(emptyCollection.contains(false));
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        Assert.assertEquals(size >= 1, booleanIterable.contains(true));
        Assert.assertEquals(size >= 2, booleanIterable.contains(false));
        Assert.assertFalse(this.newWith(true, true, true).contains(false));
        Assert.assertFalse(this.newWith(false, false, false).contains(true));
    }

    @Test
    public void containsAllArray()
    {
        BooleanIterable iterable = this.classUnderTest();
        int size = iterable.size();
        Assert.assertEquals(size >= 1, iterable.containsAll(true));
        Assert.assertEquals(size >= 2, iterable.containsAll(true, false, true));
        Assert.assertEquals(size >= 2, iterable.containsAll(true, false));
        Assert.assertEquals(size >= 1, iterable.containsAll(true, true));
        Assert.assertEquals(size >= 2, iterable.containsAll(false, false));

        BooleanIterable emptyCollection = this.newWith();
        Assert.assertFalse(emptyCollection.containsAll(true));
        Assert.assertFalse(emptyCollection.containsAll(false));
        Assert.assertFalse(emptyCollection.containsAll(false, true, false));
        Assert.assertFalse(this.newWith(true, true).containsAll(false, true, false));

        BooleanIterable trueCollection = this.newWith(true, true, true, true);
        Assert.assertFalse(trueCollection.containsAll(true, false));
        BooleanIterable falseCollection = this.newWith(false, false, false, false);
        Assert.assertFalse(falseCollection.containsAll(true, false));
    }

    @Test
    public void containsAllIterable()
    {
        BooleanIterable emptyCollection = this.newWith();
        Assert.assertTrue(emptyCollection.containsAll(new BooleanArrayList()));
        Assert.assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertFalse(emptyCollection.containsAll(BooleanArrayList.newListWith(false)));
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        Assert.assertTrue(booleanIterable.containsAll(new BooleanArrayList()));
        Assert.assertEquals(size >= 1, booleanIterable.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertEquals(size >= 2, booleanIterable.containsAll(BooleanArrayList.newListWith(false)));
        Assert.assertEquals(size >= 2, booleanIterable.containsAll(BooleanArrayList.newListWith(true, false)));
        BooleanIterable iterable = this.newWith(true, true, false, false, false);
        Assert.assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertTrue(iterable.containsAll(BooleanArrayList.newListWith(false)));
        Assert.assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertTrue(iterable.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertTrue(iterable.containsAll(BooleanArrayList.newListWith(true, false, true)));
        Assert.assertFalse(this.newWith(true, true).containsAll(BooleanArrayList.newListWith(false, true, false)));

        BooleanIterable trueCollection = this.newWith(true, true, true, true);
        Assert.assertFalse(trueCollection.containsAll(BooleanArrayList.newListWith(true, false)));
        BooleanIterable falseCollection = this.newWith(false, false, false, false);
        Assert.assertFalse(falseCollection.containsAll(BooleanArrayList.newListWith(true, false)));
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_throws()
    {
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        while (iterator.hasNext())
        {
            iterator.next();
        }

        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_throws_non_empty_collection()
    {
        BooleanIterable iterable = this.newWith(true, true, true);
        BooleanIterator iterator = iterable.booleanIterator();
        while (iterator.hasNext())
        {
            Assert.assertTrue(iterator.next());
        }
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_throws_emptyList()
    {
        this.newWith().booleanIterator().next();
    }

    @Test
    public void booleanIterator()
    {
        BooleanArrayList list = BooleanArrayList.newListWith(true, false, true);
        BooleanIterator iterator = this.classUnderTest().booleanIterator();
        for (int i = 0; i < 3; i++)
        {
            Assert.assertTrue(iterator.hasNext());
            Assert.assertTrue(list.remove(iterator.next()));
        }
        Verify.assertEmpty(list);
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void forEach()
    {
        long[] sum = new long[1];
        this.classUnderTest().forEach(each -> sum[0] += each ? 1 : 0);

        int size = this.classUnderTest().size();
        int halfSize = size / 2;
        Assert.assertEquals((size & 1) == 0 ? halfSize : halfSize + 1, sum[0]);

        long[] sum1 = new long[1];
        this.newWith(true, false, false, true, true, true).forEach(each -> sum1[0] += each ? 1 : 2);

        Assert.assertEquals(8L, sum1[0]);
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
        Assert.assertEquals(2L, this.newWith(true, false, true).count(BooleanPredicates.isTrue()));
        Assert.assertEquals(0L, this.newWith().count(BooleanPredicates.isFalse()));

        BooleanIterable iterable = this.newWith(true, false, false, true, true, true);
        Assert.assertEquals(4L, iterable.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(2L, iterable.count(BooleanPredicates.isFalse()));
        Assert.assertEquals(6L, iterable.count(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));

        BooleanIterable iterable1 = this.classUnderTest();
        int size = iterable1.size();
        int halfSize = size / 2;
        Assert.assertEquals((size & 1) == 1 ? halfSize + 1 : halfSize, iterable1.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(halfSize, iterable1.count(BooleanPredicates.isFalse()));
    }

    @Test
    public void anySatisfy()
    {
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        Assert.assertEquals(size >= 1, booleanIterable.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertEquals(size >= 2, booleanIterable.anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.newWith(true, true).anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.newWith().anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.newWith().anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith(true).anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.newWith(false).anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith(false, false, false).anySatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void allSatisfy()
    {
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        Assert.assertEquals(size <= 1, booleanIterable.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertEquals(size == 0, booleanIterable.allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.newWith().allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith().allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.newWith(false, false).allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.newWith(true, false).allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith(true, true, true).allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith(false, false, false).allSatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void noneSatisfy()
    {
        BooleanIterable booleanIterable = this.classUnderTest();
        int size = booleanIterable.size();
        Assert.assertEquals(size == 0, booleanIterable.noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertEquals(size <= 1, booleanIterable.noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.newWith().noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith().noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.newWith(false, false).noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith(true, true).noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.newWith(true, true).noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.newWith(false, false, false).noneSatisfy(BooleanPredicates.isTrue()));
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
        Assert.assertEquals(this.newMutableCollectionWith(true, true, true, true), iterable1.select(BooleanPredicates.isTrue()));
        Assert.assertEquals(this.newMutableCollectionWith(false, false, false), iterable1.select(BooleanPredicates.isFalse()));
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
        Assert.assertEquals(this.newMutableCollectionWith(false, false, false), iterable1.reject(BooleanPredicates.isTrue()));
        Assert.assertEquals(this.newMutableCollectionWith(true, true, true, true), iterable1.reject(BooleanPredicates.isFalse()));
    }

    @Test
    public void detectIfNone()
    {
        BooleanIterable iterable = this.classUnderTest();
        int size = iterable.size();
        Assert.assertEquals(size < 2, iterable.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertTrue(iterable.detectIfNone(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), true));

        BooleanIterable iterable1 = this.newWith(true, true, true);
        Assert.assertFalse(iterable1.detectIfNone(BooleanPredicates.isFalse(), false));
        Assert.assertTrue(iterable1.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertTrue(iterable1.detectIfNone(BooleanPredicates.isTrue(), false));
        Assert.assertTrue(iterable1.detectIfNone(BooleanPredicates.isTrue(), true));

        BooleanIterable iterable2 = this.newWith(false, false, false);
        Assert.assertTrue(iterable2.detectIfNone(BooleanPredicates.isTrue(), true));
        Assert.assertFalse(iterable2.detectIfNone(BooleanPredicates.isTrue(), false));
        Assert.assertFalse(iterable2.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertFalse(iterable2.detectIfNone(BooleanPredicates.isFalse(), false));
    }

    @Test
    public void collect()
    {
        FastList<Object> objects = FastList.newListWith();
        for (int i = 0; i < this.classUnderTest().size(); i++)
        {
            objects.add((i & 1) == 0 ? 1 : 0);
        }
        RichIterable<Object> expected = this.newObjectCollectionWith(objects.toArray());
        Assert.assertEquals(expected, this.classUnderTest().collect(value -> Integer.valueOf(value ? 1 : 0)));

        Assert.assertEquals(this.newObjectCollectionWith(false, true, false), this.newWith(true, false, true).collect(parameter -> !parameter));
        Assert.assertEquals(this.newObjectCollectionWith(), this.newWith().collect(parameter -> !parameter));
        Assert.assertEquals(this.newObjectCollectionWith(true), this.newWith(false).collect(parameter -> !parameter));
    }

    @Test
    public void injectInto()
    {
        BooleanIterable iterable = this.newWith(true, false, true);
        MutableInteger result = iterable.injectInto(new MutableInteger(0), (object, value) -> object.add(value ? 1 : 0));
        Assert.assertEquals(new MutableInteger(2), result);
    }

    @Test
    public void toArray()
    {
        Assert.assertEquals(0L, this.newWith().toArray().length);
        Assert.assertTrue(Arrays.equals(new boolean[]{true}, this.newWith(true).toArray()));
        Assert.assertTrue(Arrays.equals(new boolean[]{false, true}, this.newWith(true, false).toArray())
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
        Assert.assertNotEquals(iterable1, iterable3);
        Assert.assertNotEquals(iterable1, iterable4);
        Assert.assertNotEquals(this.newWith(), this.newWith(true));
        Assert.assertNotEquals(iterable6, this.newWith(true, false));
    }

    @Test
    public void testHashCode()
    {
        Assert.assertEquals(this.newObjectCollectionWith().hashCode(), this.newWith().hashCode());
        Assert.assertEquals(this.newObjectCollectionWith(true, false, true).hashCode(),
                this.newWith(true, false, true).hashCode());
        Assert.assertEquals(this.newObjectCollectionWith(true).hashCode(),
                this.newWith(true).hashCode());
        Assert.assertEquals(this.newObjectCollectionWith(false).hashCode(),
                this.newWith(false).hashCode());
    }

    @Test
    public void testToString()
    {
        Assert.assertEquals("[]", this.newWith().toString());
        Assert.assertEquals("[true]", this.newWith(true).toString());
        BooleanIterable iterable = this.newWith(true, false);
        Assert.assertTrue("[true, false]".equals(iterable.toString())
                || "[false, true]".equals(iterable.toString()));
    }

    @Test
    public void makeString()
    {
        Assert.assertEquals("true", this.newWith(true).makeString("/"));
        Assert.assertEquals("", this.newWith().makeString());
        Assert.assertEquals("", this.newWith().makeString("/"));
        Assert.assertEquals("[]", this.newWith().makeString("[", "/", "]"));
        BooleanIterable iterable = this.newWith(true, false);
        Assert.assertTrue("true, false".equals(iterable.makeString())
                || "false, true".equals(iterable.makeString()));
        Assert.assertTrue(iterable.makeString("/"), "true/false".equals(iterable.makeString("/"))
                || "false/true".equals(iterable.makeString("/")));
        Assert.assertTrue(iterable.makeString("[", "/", "]"), "[true/false]".equals(iterable.makeString("[", "/", "]"))
                || "[false/true]".equals(iterable.makeString("[", "/", "]")));
    }

    @Test
    public void appendString()
    {
        StringBuilder appendable = new StringBuilder();
        this.newWith().appendString(appendable);
        Assert.assertEquals("", appendable.toString());
        this.newWith().appendString(appendable, "/");
        Assert.assertEquals("", appendable.toString());
        this.newWith().appendString(appendable, "[", "/", "]");
        Assert.assertEquals("[]", appendable.toString());
        StringBuilder appendable1 = new StringBuilder();
        this.newWith(true).appendString(appendable1);
        Assert.assertEquals("true", appendable1.toString());
        StringBuilder appendable2 = new StringBuilder();
        BooleanIterable iterable = this.newWith(true, false);
        iterable.appendString(appendable2);
        Assert.assertTrue("true, false".equals(appendable2.toString())
                || "false, true".equals(appendable2.toString()));
        StringBuilder appendable3 = new StringBuilder();
        iterable.appendString(appendable3, "/");
        Assert.assertTrue("true/false".equals(appendable3.toString())
                || "false/true".equals(appendable3.toString()));
        StringBuilder appendable4 = new StringBuilder();
        iterable.appendString(appendable4, "[", ", ", "]");
        Assert.assertEquals(iterable.toString(), appendable4.toString());
    }

    @Test
    public void toList()
    {
        BooleanIterable iterable = this.newWith(true, false);
        Assert.assertTrue(BooleanArrayList.newListWith(false, true).equals(iterable.toList())
                || BooleanArrayList.newListWith(true, false).equals(iterable.toList()));
        BooleanIterable iterable1 = this.newWith(true);
        Assert.assertEquals(BooleanArrayList.newListWith(true), iterable1.toList());
        BooleanIterable iterable0 = this.newWith();
        Assert.assertEquals(BooleanArrayList.newListWith(), iterable0.toList());
    }

    @Test
    public void toSet()
    {
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.newWith().toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.newWith(true).toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.newWith(true, false, false, true, true, true).toSet());
    }

    @Test
    public void toBag()
    {
        Assert.assertEquals(BooleanHashBag.newBagWith(), this.newWith().toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(true), this.newWith(true).toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(true, false, true), this.newWith(true, false, true).toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(false, false, true, true, true, true), this.newWith(true, false, false, true, true, true).toBag());
    }

    @Test
    public void asLazy()
    {
        BooleanIterable iterable = this.classUnderTest();
        Assert.assertEquals(iterable.toBag(), iterable.asLazy().toBag());
        Verify.assertInstanceOf(LazyBooleanIterable.class, iterable.asLazy());
    }
}
