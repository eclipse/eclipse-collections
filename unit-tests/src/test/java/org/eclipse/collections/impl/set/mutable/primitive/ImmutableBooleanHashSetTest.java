/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.block.function.primitive.BooleanToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectBooleanToObjectFunction;
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.collection.immutable.primitive.AbstractImmutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImmutableBooleanHashSetTest extends AbstractImmutableBooleanCollectionTestCase
{
    private ImmutableBooleanSet emptySet;
    private ImmutableBooleanSet falseSet;
    private ImmutableBooleanSet trueSet;
    private ImmutableBooleanSet trueFalseSet;

    @Override
    protected ImmutableBooleanSet classUnderTest()
    {
        return BooleanHashSet.newSetWith(true, false).toImmutable();
    }

    @Override
    protected ImmutableBooleanSet newWith(boolean... elements)
    {
        return BooleanHashSet.newSetWith(elements).toImmutable();
    }

    @Override
    protected MutableBooleanSet newMutableCollectionWith(boolean... elements)
    {
        return BooleanHashSet.newSetWith(elements);
    }

    @Override
    protected MutableSet<Object> newObjectCollectionWith(Object... elements)
    {
        return UnifiedSet.newSetWith(elements);
    }

    @Before
    public void setup()
    {
        this.emptySet = this.newWith();
        this.falseSet = this.newWith(false);
        this.trueSet = this.newWith(true);
        this.trueFalseSet = this.newWith(true, false);
    }

    @Override
    @Test
    public void newCollectionWith()
    {
        ImmutableBooleanSet set = this.classUnderTest();
        Verify.assertSize(2, set);
        Assert.assertTrue(set.containsAll(true, false, true));
    }

    @Override
    @Test
    public void isEmpty()
    {
        super.isEmpty();
        Verify.assertEmpty(this.emptySet);
        Verify.assertNotEmpty(this.falseSet);
        Verify.assertNotEmpty(this.trueSet);
        Verify.assertNotEmpty(this.trueFalseSet);
    }

    @Override
    @Test
    public void notEmpty()
    {
        super.notEmpty();
        Assert.assertFalse(this.emptySet.notEmpty());
        Assert.assertTrue(this.falseSet.notEmpty());
        Assert.assertTrue(this.trueSet.notEmpty());
        Assert.assertTrue(this.trueFalseSet.notEmpty());
    }

    @Override
    @Test
    public void contains()
    {
        super.contains();
        Assert.assertFalse(this.emptySet.contains(true));
        Assert.assertFalse(this.emptySet.contains(false));
        Assert.assertTrue(this.falseSet.contains(false));
        Assert.assertFalse(this.falseSet.contains(true));
        Assert.assertTrue(this.trueSet.contains(true));
        Assert.assertFalse(this.trueSet.contains(false));
        Assert.assertTrue(this.trueFalseSet.contains(true));
        Assert.assertTrue(this.trueFalseSet.contains(false));
    }

    @Override
    @Test
    public void containsAllArray()
    {
        super.containsAllArray();
        Assert.assertFalse(this.emptySet.containsAll(true));
        Assert.assertFalse(this.emptySet.containsAll(true, false));
        Assert.assertTrue(this.falseSet.containsAll(false, false));
        Assert.assertFalse(this.falseSet.containsAll(true, true));
        Assert.assertFalse(this.falseSet.containsAll(true, false, true));
        Assert.assertTrue(this.trueSet.containsAll(true, true));
        Assert.assertFalse(this.trueSet.containsAll(false, false));
        Assert.assertFalse(this.trueSet.containsAll(true, false, false));
        Assert.assertTrue(this.trueFalseSet.containsAll(true, true));
        Assert.assertTrue(this.trueFalseSet.containsAll(false, false));
        Assert.assertTrue(this.trueFalseSet.containsAll(false, true, true));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        super.containsAllIterable();
        Assert.assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertTrue(this.falseSet.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertFalse(this.falseSet.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(this.falseSet.containsAll(BooleanArrayList.newListWith(true, false, true)));
        Assert.assertTrue(this.trueSet.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(this.trueSet.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertFalse(this.trueSet.containsAll(BooleanArrayList.newListWith(true, false, false)));
        Assert.assertTrue(this.trueFalseSet.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertTrue(this.trueFalseSet.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertTrue(this.trueFalseSet.containsAll(BooleanArrayList.newListWith(false, true, true)));
    }

    @Override
    @Test
    public void toArray()
    {
        super.toArray();
        Assert.assertEquals(0L, this.emptySet.toArray().length);

        Assert.assertEquals(1L, this.falseSet.toArray().length);
        Assert.assertFalse(this.falseSet.toArray()[0]);

        Assert.assertEquals(1L, this.trueSet.toArray().length);
        Assert.assertTrue(this.trueSet.toArray()[0]);

        Assert.assertEquals(2L, this.trueFalseSet.toArray().length);
        Assert.assertTrue(Arrays.equals(new boolean[]{false, true}, this.trueFalseSet.toArray())
                || Arrays.equals(new boolean[]{true, false}, this.trueFalseSet.toArray()));
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        Assert.assertEquals(new BooleanArrayList(), this.emptySet.toList());
        Assert.assertEquals(BooleanArrayList.newListWith(false), this.falseSet.toList());
        Assert.assertEquals(BooleanArrayList.newListWith(true), this.trueSet.toList());
        Assert.assertTrue(BooleanArrayList.newListWith(false, true).equals(this.trueFalseSet.toList())
                || BooleanArrayList.newListWith(true, false).equals(this.trueFalseSet.toList()));
    }

    @Override
    @Test
    public void toSet()
    {
        super.toSet();
        Assert.assertEquals(new BooleanHashSet(), this.emptySet.toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.falseSet.toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.trueSet.toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(false, true), this.trueFalseSet.toSet());
    }

    @Override
    @Test
    public void toBag()
    {
        Assert.assertEquals(new BooleanHashBag(), this.emptySet.toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(false), this.falseSet.toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(true), this.trueSet.toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(false, true), this.trueFalseSet.toBag());
    }

    @Override
    @Test
    public void testEquals()
    {
        Assert.assertNotEquals(this.falseSet, this.emptySet);
        Assert.assertNotEquals(this.falseSet, this.trueSet);
        Assert.assertNotEquals(this.falseSet, this.trueFalseSet);
        Assert.assertNotEquals(this.trueSet, this.emptySet);
        Assert.assertNotEquals(this.trueSet, this.trueFalseSet);
        Assert.assertNotEquals(this.trueFalseSet, this.emptySet);
        Verify.assertEqualsAndHashCode(this.newWith(false, true), this.trueFalseSet);
        Verify.assertEqualsAndHashCode(this.newWith(true, false), this.trueFalseSet);

        Verify.assertPostSerializedIdentity(this.emptySet);
        Verify.assertPostSerializedIdentity(this.falseSet);
        Verify.assertPostSerializedIdentity(this.trueSet);
        Verify.assertPostSerializedIdentity(this.trueFalseSet);
    }

    @Override
    @Test
    public void testHashCode()
    {
        super.testHashCode();
        Assert.assertEquals(UnifiedSet.newSet().hashCode(), this.emptySet.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(false).hashCode(), this.falseSet.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(true).hashCode(), this.trueSet.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(true, false).hashCode(), this.trueFalseSet.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(false, true).hashCode(), this.trueFalseSet.hashCode());
        Assert.assertNotEquals(UnifiedSet.newSetWith(false).hashCode(), this.trueFalseSet.hashCode());
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator booleanIterator0 = this.emptySet.booleanIterator();
        Assert.assertFalse(booleanIterator0.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) booleanIterator0::next);

        BooleanIterator booleanIterator1 = this.falseSet.booleanIterator();
        Assert.assertTrue(booleanIterator1.hasNext());
        Assert.assertFalse(booleanIterator1.next());
        Assert.assertFalse(booleanIterator1.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) booleanIterator1::next);

        BooleanIterator booleanIterator2 = this.trueSet.booleanIterator();
        Assert.assertTrue(booleanIterator2.hasNext());
        Assert.assertTrue(booleanIterator2.next());
        Assert.assertFalse(booleanIterator2.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) booleanIterator2::next);

        BooleanIterator booleanIterator3 = this.trueFalseSet.booleanIterator();
        Assert.assertTrue(booleanIterator3.hasNext());
        BooleanHashSet actual = new BooleanHashSet();
        actual.add(booleanIterator3.next());
        Assert.assertTrue(booleanIterator3.hasNext());
        actual.add(booleanIterator3.next());
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), actual);
        Assert.assertFalse(booleanIterator3.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) booleanIterator3::next);
    }

    @Override
    @Test
    public void forEach()
    {
        String[] sum = new String[4];
        for (int i = 0; i < sum.length; i++)
        {
            sum[i] = "";
        }
        this.emptySet.forEach(each -> sum[0] += each);
        this.falseSet.forEach(each -> sum[1] += each);
        this.trueSet.forEach(each -> sum[2] += each);
        this.trueFalseSet.forEach(each -> sum[3] += each);

        Assert.assertEquals("", sum[0]);
        Assert.assertEquals("false", sum[1]);
        Assert.assertEquals("true", sum[2]);
        Assert.assertTrue("truefalse".equals(sum[3]) || "falsetrue".equals(sum[3]));
    }

    @Override
    @Test
    public void injectInto()
    {
        ObjectBooleanToObjectFunction<MutableInteger, MutableInteger> function = (object, value) -> object.add(value ? 1 : 0);
        Assert.assertEquals(new MutableInteger(1), BooleanHashSet.newSetWith(true, false, true).injectInto(new MutableInteger(0), function));
        Assert.assertEquals(new MutableInteger(1), BooleanHashSet.newSetWith(true).injectInto(new MutableInteger(0), function));
        Assert.assertEquals(new MutableInteger(0), BooleanHashSet.newSetWith(false).injectInto(new MutableInteger(0), function));
        Assert.assertEquals(new MutableInteger(0), new BooleanHashSet().injectInto(new MutableInteger(0), function));
    }

    @Override
    @Test
    public void size()
    {
        super.size();
        Verify.assertSize(2, this.classUnderTest());
    }

    @Override
    @Test
    public void count()
    {
        Assert.assertEquals(0L, this.emptySet.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(0L, this.falseSet.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(1L, this.falseSet.count(BooleanPredicates.isFalse()));
        Assert.assertEquals(0L, this.trueSet.count(BooleanPredicates.isFalse()));
        Assert.assertEquals(1L, this.trueFalseSet.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(0L, this.trueFalseSet.count(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Assert.assertEquals(1L, this.trueFalseSet.count(BooleanPredicates.isFalse()));
        Assert.assertEquals(1L, this.trueFalseSet.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(2L, this.trueFalseSet.count(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();
        Assert.assertFalse(this.emptySet.anySatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Assert.assertFalse(this.falseSet.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.falseSet.anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.trueSet.anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.trueSet.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.trueFalseSet.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.trueFalseSet.anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.trueFalseSet.anySatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();
        Assert.assertTrue(this.emptySet.allSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Assert.assertFalse(this.falseSet.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.falseSet.allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.trueSet.allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.trueSet.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.trueFalseSet.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.trueFalseSet.allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.trueFalseSet.allSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Assert.assertTrue(this.trueFalseSet.allSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        Assert.assertFalse(this.emptySet.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Assert.assertFalse(this.falseSet.noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.falseSet.noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.trueSet.noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.trueSet.noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.trueFalseSet.noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.trueFalseSet.noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.trueFalseSet.noneSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Assert.assertFalse(this.trueFalseSet.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void select()
    {
        Verify.assertEmpty(this.emptySet.select(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Verify.assertEmpty(this.falseSet.select(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.falseSet.select(BooleanPredicates.isFalse()));
        Verify.assertEmpty(this.trueSet.select(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.trueSet.select(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.trueFalseSet.select(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.trueFalseSet.select(BooleanPredicates.isTrue()));
        Verify.assertEmpty(this.trueFalseSet.select(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Verify.assertSize(2, this.trueFalseSet.select(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void reject()
    {
        Verify.assertEmpty(this.emptySet.reject(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Verify.assertEmpty(this.trueSet.reject(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.trueSet.reject(BooleanPredicates.isFalse()));
        Verify.assertEmpty(this.falseSet.reject(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.falseSet.reject(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.trueFalseSet.reject(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.trueFalseSet.reject(BooleanPredicates.isTrue()));
        Verify.assertEmpty(this.trueFalseSet.reject(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Verify.assertSize(2, this.trueFalseSet.reject(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        super.detectIfNone();
        Assert.assertTrue(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), true));
        Assert.assertFalse(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), false));
        Assert.assertTrue(this.falseSet.detectIfNone(BooleanPredicates.isTrue(), true));
        Assert.assertFalse(this.falseSet.detectIfNone(BooleanPredicates.isTrue(), false));
        Assert.assertFalse(this.falseSet.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertFalse(this.falseSet.detectIfNone(BooleanPredicates.isFalse(), false));
        Assert.assertTrue(this.trueSet.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertFalse(this.trueSet.detectIfNone(BooleanPredicates.isFalse(), false));
        Assert.assertTrue(this.trueSet.detectIfNone(BooleanPredicates.isTrue(), true));
        Assert.assertTrue(this.trueSet.detectIfNone(BooleanPredicates.isTrue(), false));
        Assert.assertTrue(this.trueFalseSet.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), true));
        Assert.assertFalse(this.trueFalseSet.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), false));
        Assert.assertFalse(this.trueFalseSet.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertTrue(this.trueFalseSet.detectIfNone(BooleanPredicates.isTrue(), false));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        BooleanToObjectFunction<Boolean> function = parameter -> !parameter;
        Assert.assertEquals(UnifiedSet.newSetWith(true, false), this.trueFalseSet.collect(function));
        Assert.assertEquals(UnifiedSet.newSetWith(false), this.trueSet.collect(function));
        Assert.assertEquals(UnifiedSet.newSetWith(true), this.falseSet.collect(function));
        Assert.assertEquals(UnifiedSet.newSetWith(), this.emptySet.collect(function));
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        Assert.assertEquals("[]", this.emptySet.toString());
        Assert.assertEquals("[false]", this.falseSet.toString());
        Assert.assertEquals("[true]", this.trueSet.toString());
        Assert.assertTrue("[true, false]".equals(this.trueFalseSet.toString())
                || "[false, true]".equals(this.trueFalseSet.toString()));
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        Assert.assertEquals("", this.emptySet.makeString());
        Assert.assertEquals("false", this.falseSet.makeString());
        Assert.assertEquals("true", this.trueSet.makeString());
        Assert.assertTrue("true, false".equals(this.trueFalseSet.makeString())
                || "false, true".equals(this.trueFalseSet.makeString()));

        Assert.assertEquals("", this.emptySet.makeString("/"));
        Assert.assertEquals("false", this.falseSet.makeString("/"));
        Assert.assertEquals("true", this.trueSet.makeString("/"));
        Assert.assertTrue(this.trueFalseSet.makeString("/"), "true/false".equals(this.trueFalseSet.makeString("/"))
                || "false/true".equals(this.trueFalseSet.makeString("/")));

        Assert.assertEquals("[]", this.emptySet.makeString("[", "/", "]"));
        Assert.assertEquals("[false]", this.falseSet.makeString("[", "/", "]"));
        Assert.assertEquals("[true]", this.trueSet.makeString("[", "/", "]"));
        Assert.assertTrue(this.trueFalseSet.makeString("[", "/", "]"), "[true/false]".equals(this.trueFalseSet.makeString("[", "/", "]"))
                || "[false/true]".equals(this.trueFalseSet.makeString("[", "/", "]")));
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder appendable = new StringBuilder();
        this.emptySet.appendString(appendable);
        Assert.assertEquals("", appendable.toString());

        StringBuilder appendable1 = new StringBuilder();
        this.falseSet.appendString(appendable1);
        Assert.assertEquals("false", appendable1.toString());

        StringBuilder appendable2 = new StringBuilder();
        this.trueSet.appendString(appendable2);
        Assert.assertEquals("true", appendable2.toString());

        StringBuilder appendable3 = new StringBuilder();
        this.trueFalseSet.appendString(appendable3);
        Assert.assertTrue("true, false".equals(appendable3.toString())
                || "false, true".equals(appendable3.toString()));

        StringBuilder appendable4 = new StringBuilder();
        this.trueFalseSet.appendString(appendable4, "[", ", ", "]");
        Assert.assertTrue("[true, false]".equals(appendable4.toString())
                || "[false, true]".equals(appendable4.toString()));
    }

    @Override
    @Test
    public void asLazy()
    {
        super.asLazy();
        Verify.assertInstanceOf(LazyBooleanIterable.class, this.emptySet.asLazy());
        Assert.assertEquals(this.emptySet, this.emptySet.asLazy().toSet());
        Assert.assertEquals(this.falseSet, this.falseSet.asLazy().toSet());
        Assert.assertEquals(this.trueSet, this.trueSet.asLazy().toSet());
        Assert.assertEquals(this.trueFalseSet, this.trueFalseSet.asLazy().toSet());
    }

    private void assertSizeAndContains(ImmutableBooleanCollection collection, boolean... elements)
    {
        Assert.assertEquals(elements.length, collection.size());
        for (boolean i : elements)
        {
            Assert.assertTrue(collection.contains(i));
        }
    }

    @Override
    @Test
    public void testNewWith()
    {
        ImmutableBooleanCollection immutableCollection = this.newWith();
        ImmutableBooleanCollection collection = immutableCollection.newWith(true);
        ImmutableBooleanCollection collection0 = immutableCollection.newWith(true).newWith(false);
        this.assertSizeAndContains(immutableCollection);
        this.assertSizeAndContains(collection, true);
        this.assertSizeAndContains(collection0, true, false);
    }

    @Override
    @Test
    public void newWithAll()
    {
        ImmutableBooleanCollection immutableCollection = this.newWith();
        ImmutableBooleanCollection collection = immutableCollection.newWithAll(this.newMutableCollectionWith(true));
        ImmutableBooleanCollection collection0 = immutableCollection.newWithAll(this.newMutableCollectionWith(false));
        ImmutableBooleanCollection collection1 = immutableCollection.newWithAll(this.newMutableCollectionWith(true, false));
        this.assertSizeAndContains(immutableCollection);
        this.assertSizeAndContains(collection, true);
        this.assertSizeAndContains(collection0, false);
        this.assertSizeAndContains(collection1, true, false);
    }

    @Override
    @Test
    public void newWithout()
    {
        ImmutableBooleanCollection collection3 = this.newWith(true, false);
        ImmutableBooleanCollection collection2 = collection3.newWithout(true);
        ImmutableBooleanCollection collection1 = collection3.newWithout(false);

        this.assertSizeAndContains(collection1, true);
        this.assertSizeAndContains(collection2, false);
    }

    @Override
    @Test
    public void newWithoutAll()
    {
        ImmutableBooleanCollection collection3 = this.newWith(true, false);
        ImmutableBooleanCollection collection2 = collection3.newWithoutAll(this.newMutableCollectionWith(true));
        ImmutableBooleanCollection collection1 = collection3.newWithoutAll(this.newMutableCollectionWith(false));
        ImmutableBooleanCollection collection0 = collection3.newWithoutAll(this.newMutableCollectionWith(true, false));

        this.assertSizeAndContains(collection0);
        this.assertSizeAndContains(collection1, true);
        this.assertSizeAndContains(collection2, false);
    }
}
