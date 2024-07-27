/*
 * Copyright (c) 2024 Goldman Sachs and others.
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
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.ImmutableBooleanSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.tuple.primitive.BooleanBooleanPair;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.collection.immutable.primitive.AbstractImmutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @BeforeEach
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
        assertTrue(set.containsAll(true, false, true));
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
        assertFalse(this.emptySet.notEmpty());
        assertTrue(this.falseSet.notEmpty());
        assertTrue(this.trueSet.notEmpty());
        assertTrue(this.trueFalseSet.notEmpty());
    }

    @Override
    @Test
    public void contains()
    {
        super.contains();
        assertFalse(this.emptySet.contains(true));
        assertFalse(this.emptySet.contains(false));
        assertTrue(this.falseSet.contains(false));
        assertFalse(this.falseSet.contains(true));
        assertTrue(this.trueSet.contains(true));
        assertFalse(this.trueSet.contains(false));
        assertTrue(this.trueFalseSet.contains(true));
        assertTrue(this.trueFalseSet.contains(false));
    }

    @Override
    @Test
    public void containsAllArray()
    {
        super.containsAllArray();
        assertFalse(this.emptySet.containsAll(true));
        assertFalse(this.emptySet.containsAll(true, false));
        assertTrue(this.falseSet.containsAll(false, false));
        assertFalse(this.falseSet.containsAll(true, true));
        assertFalse(this.falseSet.containsAll(true, false, true));
        assertTrue(this.trueSet.containsAll(true, true));
        assertFalse(this.trueSet.containsAll(false, false));
        assertFalse(this.trueSet.containsAll(true, false, false));
        assertTrue(this.trueFalseSet.containsAll(true, true));
        assertTrue(this.trueFalseSet.containsAll(false, false));
        assertTrue(this.trueFalseSet.containsAll(false, true, true));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        super.containsAllIterable();
        assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true)));
        assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(this.falseSet.containsAll(BooleanArrayList.newListWith(false, false)));
        assertFalse(this.falseSet.containsAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(this.falseSet.containsAll(BooleanArrayList.newListWith(true, false, true)));
        assertTrue(this.trueSet.containsAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(this.trueSet.containsAll(BooleanArrayList.newListWith(false, false)));
        assertFalse(this.trueSet.containsAll(BooleanArrayList.newListWith(true, false, false)));
        assertTrue(this.trueFalseSet.containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(this.trueFalseSet.containsAll(BooleanArrayList.newListWith(false, false)));
        assertTrue(this.trueFalseSet.containsAll(BooleanArrayList.newListWith(false, true, true)));
    }

    @Override
    @Test
    public void toArray()
    {
        super.toArray();
        assertEquals(0L, this.emptySet.toArray().length);

        assertEquals(1L, this.falseSet.toArray().length);
        assertFalse(this.falseSet.toArray()[0]);

        assertEquals(1L, this.trueSet.toArray().length);
        assertTrue(this.trueSet.toArray()[0]);

        assertEquals(2L, this.trueFalseSet.toArray().length);
        assertTrue(Arrays.equals(new boolean[]{false, true}, this.trueFalseSet.toArray())
                || Arrays.equals(new boolean[]{true, false}, this.trueFalseSet.toArray()));
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        assertEquals(new BooleanArrayList(), this.emptySet.toList());
        assertEquals(BooleanArrayList.newListWith(false), this.falseSet.toList());
        assertEquals(BooleanArrayList.newListWith(true), this.trueSet.toList());
        assertTrue(BooleanArrayList.newListWith(false, true).equals(this.trueFalseSet.toList())
                || BooleanArrayList.newListWith(true, false).equals(this.trueFalseSet.toList()));
    }

    @Override
    @Test
    public void toSet()
    {
        super.toSet();
        assertEquals(new BooleanHashSet(), this.emptySet.toSet());
        assertEquals(BooleanHashSet.newSetWith(false), this.falseSet.toSet());
        assertEquals(BooleanHashSet.newSetWith(true), this.trueSet.toSet());
        assertEquals(BooleanHashSet.newSetWith(false, true), this.trueFalseSet.toSet());
    }

    @Override
    @Test
    public void toBag()
    {
        assertEquals(new BooleanHashBag(), this.emptySet.toBag());
        assertEquals(BooleanHashBag.newBagWith(false), this.falseSet.toBag());
        assertEquals(BooleanHashBag.newBagWith(true), this.trueSet.toBag());
        assertEquals(BooleanHashBag.newBagWith(false, true), this.trueFalseSet.toBag());
    }

    @Override
    @Test
    public void testEquals()
    {
        assertNotEquals(this.falseSet, this.emptySet);
        assertNotEquals(this.falseSet, this.trueSet);
        assertNotEquals(this.falseSet, this.trueFalseSet);
        assertNotEquals(this.trueSet, this.emptySet);
        assertNotEquals(this.trueSet, this.trueFalseSet);
        assertNotEquals(this.trueFalseSet, this.emptySet);
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
        assertEquals(UnifiedSet.newSet().hashCode(), this.emptySet.hashCode());
        assertEquals(UnifiedSet.newSetWith(false).hashCode(), this.falseSet.hashCode());
        assertEquals(UnifiedSet.newSetWith(true).hashCode(), this.trueSet.hashCode());
        assertEquals(UnifiedSet.newSetWith(true, false).hashCode(), this.trueFalseSet.hashCode());
        assertEquals(UnifiedSet.newSetWith(false, true).hashCode(), this.trueFalseSet.hashCode());
        assertNotEquals(UnifiedSet.newSetWith(false).hashCode(), this.trueFalseSet.hashCode());
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator booleanIterator0 = this.emptySet.booleanIterator();
        assertFalse(booleanIterator0.hasNext());
        assertThrows(NoSuchElementException.class, booleanIterator0::next);

        BooleanIterator booleanIterator1 = this.falseSet.booleanIterator();
        assertTrue(booleanIterator1.hasNext());
        assertFalse(booleanIterator1.next());
        assertFalse(booleanIterator1.hasNext());
        assertThrows(NoSuchElementException.class, booleanIterator1::next);

        BooleanIterator booleanIterator2 = this.trueSet.booleanIterator();
        assertTrue(booleanIterator2.hasNext());
        assertTrue(booleanIterator2.next());
        assertFalse(booleanIterator2.hasNext());
        assertThrows(NoSuchElementException.class, booleanIterator2::next);

        BooleanIterator booleanIterator3 = this.trueFalseSet.booleanIterator();
        assertTrue(booleanIterator3.hasNext());
        MutableBooleanSet actual = new BooleanHashSet();
        actual.add(booleanIterator3.next());
        assertTrue(booleanIterator3.hasNext());
        actual.add(booleanIterator3.next());
        assertEquals(BooleanHashSet.newSetWith(true, false), actual);
        assertFalse(booleanIterator3.hasNext());
        assertThrows(NoSuchElementException.class, booleanIterator3::next);
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

        assertEquals("", sum[0]);
        assertEquals("false", sum[1]);
        assertEquals("true", sum[2]);
        assertTrue("truefalse".equals(sum[3]) || "falsetrue".equals(sum[3]));
    }

    @Override
    @Test
    public void injectInto()
    {
        ObjectBooleanToObjectFunction<MutableInteger, MutableInteger> function = (object, value) -> object.add(value ? 1 : 0);
        assertEquals(new MutableInteger(1), BooleanHashSet.newSetWith(true, false, true).injectInto(new MutableInteger(0), function));
        assertEquals(new MutableInteger(1), BooleanHashSet.newSetWith(true).injectInto(new MutableInteger(0), function));
        assertEquals(new MutableInteger(0), BooleanHashSet.newSetWith(false).injectInto(new MutableInteger(0), function));
        assertEquals(new MutableInteger(0), new BooleanHashSet().injectInto(new MutableInteger(0), function));
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
        assertEquals(0L, this.emptySet.count(BooleanPredicates.isTrue()));
        assertEquals(0L, this.falseSet.count(BooleanPredicates.isTrue()));
        assertEquals(1L, this.falseSet.count(BooleanPredicates.isFalse()));
        assertEquals(0L, this.trueSet.count(BooleanPredicates.isFalse()));
        assertEquals(1L, this.trueFalseSet.count(BooleanPredicates.isTrue()));
        assertEquals(0L, this.trueFalseSet.count(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        assertEquals(1L, this.trueFalseSet.count(BooleanPredicates.isFalse()));
        assertEquals(1L, this.trueFalseSet.count(BooleanPredicates.isTrue()));
        assertEquals(2L, this.trueFalseSet.count(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();
        assertFalse(this.emptySet.anySatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.falseSet.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.falseSet.anySatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.trueSet.anySatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.trueSet.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.trueFalseSet.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.trueFalseSet.anySatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.trueFalseSet.anySatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();
        assertTrue(this.emptySet.allSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.falseSet.allSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.falseSet.allSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.trueSet.allSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.trueSet.allSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.trueFalseSet.allSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.trueFalseSet.allSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.trueFalseSet.allSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        assertTrue(this.trueFalseSet.allSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        assertTrue(this.emptySet.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.falseSet.noneSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.falseSet.noneSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.trueSet.noneSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.trueSet.noneSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.trueFalseSet.noneSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.trueFalseSet.noneSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.trueFalseSet.noneSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        assertFalse(this.trueFalseSet.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
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
        assertTrue(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), true));
        assertFalse(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), false));
        assertTrue(this.falseSet.detectIfNone(BooleanPredicates.isTrue(), true));
        assertFalse(this.falseSet.detectIfNone(BooleanPredicates.isTrue(), false));
        assertFalse(this.falseSet.detectIfNone(BooleanPredicates.isFalse(), true));
        assertFalse(this.falseSet.detectIfNone(BooleanPredicates.isFalse(), false));
        assertTrue(this.trueSet.detectIfNone(BooleanPredicates.isFalse(), true));
        assertFalse(this.trueSet.detectIfNone(BooleanPredicates.isFalse(), false));
        assertTrue(this.trueSet.detectIfNone(BooleanPredicates.isTrue(), true));
        assertTrue(this.trueSet.detectIfNone(BooleanPredicates.isTrue(), false));
        assertTrue(this.trueFalseSet.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), true));
        assertFalse(this.trueFalseSet.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), false));
        assertFalse(this.trueFalseSet.detectIfNone(BooleanPredicates.isFalse(), true));
        assertTrue(this.trueFalseSet.detectIfNone(BooleanPredicates.isTrue(), false));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        BooleanToObjectFunction<Boolean> function = parameter -> !parameter;
        assertEquals(UnifiedSet.newSetWith(true, false), this.trueFalseSet.collect(function));
        assertEquals(UnifiedSet.newSetWith(false), this.trueSet.collect(function));
        assertEquals(UnifiedSet.newSetWith(true), this.falseSet.collect(function));
        assertEquals(UnifiedSet.newSetWith(), this.emptySet.collect(function));
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        assertEquals("[]", this.emptySet.toString());
        assertEquals("[false]", this.falseSet.toString());
        assertEquals("[true]", this.trueSet.toString());
        assertTrue("[true, false]".equals(this.trueFalseSet.toString())
                || "[false, true]".equals(this.trueFalseSet.toString()));
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        assertEquals("", this.emptySet.makeString());
        assertEquals("false", this.falseSet.makeString());
        assertEquals("true", this.trueSet.makeString());
        assertTrue("true, false".equals(this.trueFalseSet.makeString())
                || "false, true".equals(this.trueFalseSet.makeString()));

        assertEquals("", this.emptySet.makeString("/"));
        assertEquals("false", this.falseSet.makeString("/"));
        assertEquals("true", this.trueSet.makeString("/"));
        assertTrue("true/false".equals(this.trueFalseSet.makeString("/"))
                || "false/true".equals(this.trueFalseSet.makeString("/")), trueFalseSet.makeString("/"));

        assertEquals("[]", this.emptySet.makeString("[", "/", "]"));
        assertEquals("[false]", this.falseSet.makeString("[", "/", "]"));
        assertEquals("[true]", this.trueSet.makeString("[", "/", "]"));
        assertTrue("[true/false]".equals(this.trueFalseSet.makeString("[", "/", "]"))
                || "[false/true]".equals(this.trueFalseSet.makeString("[", "/", "]")), trueFalseSet.makeString("[", "/", "]"));
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        StringBuilder appendable = new StringBuilder();
        this.emptySet.appendString(appendable);
        assertEquals("", appendable.toString());

        StringBuilder appendable1 = new StringBuilder();
        this.falseSet.appendString(appendable1);
        assertEquals("false", appendable1.toString());

        StringBuilder appendable2 = new StringBuilder();
        this.trueSet.appendString(appendable2);
        assertEquals("true", appendable2.toString());

        StringBuilder appendable3 = new StringBuilder();
        this.trueFalseSet.appendString(appendable3);
        assertTrue("true, false".equals(appendable3.toString())
                || "false, true".equals(appendable3.toString()));

        StringBuilder appendable4 = new StringBuilder();
        this.trueFalseSet.appendString(appendable4, "[", ", ", "]");
        assertTrue("[true, false]".equals(appendable4.toString())
                || "[false, true]".equals(appendable4.toString()));
    }

    @Override
    @Test
    public void asLazy()
    {
        super.asLazy();
        Verify.assertInstanceOf(LazyBooleanIterable.class, this.emptySet.asLazy());
        assertEquals(this.emptySet, this.emptySet.asLazy().toSet());
        assertEquals(this.falseSet, this.falseSet.asLazy().toSet());
        assertEquals(this.trueSet, this.trueSet.asLazy().toSet());
        assertEquals(this.trueFalseSet, this.trueFalseSet.asLazy().toSet());
    }

    private void assertSizeAndContains(ImmutableBooleanCollection collection, boolean... elements)
    {
        assertEquals(elements.length, collection.size());
        for (boolean i : elements)
        {
            assertTrue(collection.contains(i));
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

    @Test
    public void union()
    {
        ImmutableBooleanSet set11 = this.newWith(true);
        ImmutableBooleanSet set21 = this.newWith(false);
        ImmutableBooleanSet actual = set11.union(set21);
        assertEquals(this.trueFalseSet, actual);

        ImmutableBooleanSet set12 = this.newWith(false);
        ImmutableBooleanSet set22 = this.newWith(false);
        ImmutableBooleanSet actual2 = set12.union(set22);
        assertEquals(this.falseSet, actual2);

        ImmutableBooleanSet set13 = this.newWith(true);
        ImmutableBooleanSet set23 = this.newWith(true);
        ImmutableBooleanSet actual3 = set13.union(set23);
        assertEquals(this.trueSet, actual3);

        ImmutableBooleanSet set14 = this.trueFalseSet;
        ImmutableBooleanSet set24 = this.newWith();
        ImmutableBooleanSet actual4 = set14.union(set24);
        assertEquals(this.trueFalseSet, actual4);

        ImmutableBooleanSet set15 = this.newWith();
        ImmutableBooleanSet set25 = this.newWith();
        ImmutableBooleanSet actual5 = set15.union(set25);
        assertEquals(this.emptySet, actual5);
    }

    @Test
    public void intersect()
    {
        ImmutableBooleanSet set11 = this.newWith(true);
        ImmutableBooleanSet set21 = this.newWith(false);
        ImmutableBooleanSet actual = set11.intersect(set21);
        assertEquals(this.emptySet, actual);

        ImmutableBooleanSet set12 = this.newWith(false);
        ImmutableBooleanSet set22 = this.newWith(false);
        ImmutableBooleanSet actual2 = set12.intersect(set22);
        assertEquals(this.falseSet, actual2);

        ImmutableBooleanSet set13 = this.newWith(true);
        ImmutableBooleanSet set23 = this.newWith(true);
        ImmutableBooleanSet actual3 = set13.intersect(set23);
        assertEquals(this.trueSet, actual3);

        ImmutableBooleanSet set14 = this.trueFalseSet;
        ImmutableBooleanSet set24 = this.newWith();
        ImmutableBooleanSet actual4 = set14.intersect(set24);
        assertEquals(this.emptySet, actual4);

        ImmutableBooleanSet set15 = this.newWith();
        ImmutableBooleanSet set25 = this.newWith();
        ImmutableBooleanSet actual5 = set15.intersect(set25);
        assertEquals(this.emptySet, actual5);
    }

    @Test
    public void difference()
    {
        ImmutableBooleanSet set11 = this.newWith(true);
        ImmutableBooleanSet set21 = this.newWith(false);
        ImmutableBooleanSet actual = set11.difference(set21);
        assertEquals(this.trueSet, actual);

        ImmutableBooleanSet set12 = this.newWith(false);
        ImmutableBooleanSet set22 = this.newWith(false);
        ImmutableBooleanSet actual2 = set12.difference(set22);
        assertEquals(this.emptySet, actual2);

        ImmutableBooleanSet set13 = this.trueFalseSet;
        ImmutableBooleanSet set23 = this.trueFalseSet;
        ImmutableBooleanSet actual3 = set13.difference(set23);
        assertEquals(this.emptySet, actual3);

        ImmutableBooleanSet set14 = this.trueFalseSet;
        ImmutableBooleanSet set24 = this.newWith();
        ImmutableBooleanSet actual4 = set14.difference(set24);
        assertEquals(this.trueFalseSet, actual4);

        ImmutableBooleanSet set15 = this.newWith();
        ImmutableBooleanSet set25 = this.trueFalseSet;
        ImmutableBooleanSet actual5 = set15.difference(set25);
        assertEquals(this.emptySet, actual5);

        ImmutableBooleanSet set16 = this.newWith();
        ImmutableBooleanSet set26 = this.newWith();
        ImmutableBooleanSet actual6 = set16.difference(set26);
        assertEquals(this.emptySet, actual6);
    }

    @Test
    public void symmetricDifference()
    {
        ImmutableBooleanSet set11 = this.newWith(true);
        ImmutableBooleanSet set21 = this.newWith(false);
        ImmutableBooleanSet actual = set11.symmetricDifference(set21);
        assertEquals(this.trueFalseSet, actual);

        ImmutableBooleanSet set12 = this.newWith(false);
        ImmutableBooleanSet set22 = this.newWith(false);
        ImmutableBooleanSet actual2 = set12.symmetricDifference(set22);
        assertEquals(this.emptySet, actual2);

        ImmutableBooleanSet set13 = this.trueFalseSet;
        ImmutableBooleanSet set23 = this.trueFalseSet;
        ImmutableBooleanSet actual3 = set13.symmetricDifference(set23);
        assertEquals(this.emptySet, actual3);

        ImmutableBooleanSet set14 = this.trueFalseSet;
        ImmutableBooleanSet set24 = this.newWith();
        ImmutableBooleanSet actual4 = set14.symmetricDifference(set24);
        assertEquals(this.trueFalseSet, actual4);

        ImmutableBooleanSet set15 = this.newWith();
        ImmutableBooleanSet set25 = this.trueFalseSet;
        ImmutableBooleanSet actual5 = set15.symmetricDifference(set25);
        assertEquals(this.trueFalseSet, actual5);

        ImmutableBooleanSet set16 = this.newWith();
        ImmutableBooleanSet set26 = this.newWith();
        ImmutableBooleanSet actual6 = set16.symmetricDifference(set26);
        assertEquals(this.emptySet, actual6);
    }

    @Test
    public void isSubsetOf()
    {
        ImmutableBooleanSet set11 = this.newWith(true);
        ImmutableBooleanSet set21 = this.newWith(false);
        assertFalse(set11.isSubsetOf(set21));

        ImmutableBooleanSet set12 = this.newWith(false);
        ImmutableBooleanSet set22 = this.newWith(false);
        assertTrue(set12.isSubsetOf(set22));

        ImmutableBooleanSet set13 = this.trueFalseSet;
        ImmutableBooleanSet set23 = this.trueFalseSet;
        assertTrue(set13.isSubsetOf(set23));

        ImmutableBooleanSet set14 = this.trueFalseSet;
        ImmutableBooleanSet set24 = this.newWith();
        assertFalse(set14.isSubsetOf(set24));

        ImmutableBooleanSet set15 = this.newWith();
        ImmutableBooleanSet set25 = this.trueFalseSet;
        assertTrue(set15.isSubsetOf(set25));

        ImmutableBooleanSet set16 = this.newWith();
        ImmutableBooleanSet set26 = this.newWith();
        assertTrue(set16.isSubsetOf(set26));
    }

    @Test
    public void isProperSubsetOf()
    {
        ImmutableBooleanSet set11 = this.newWith(true);
        ImmutableBooleanSet set21 = this.newWith(false);
        assertFalse(set11.isProperSubsetOf(set21));

        ImmutableBooleanSet set12 = this.newWith(false);
        ImmutableBooleanSet set22 = this.newWith(false);
        assertFalse(set12.isProperSubsetOf(set22));

        ImmutableBooleanSet set13 = this.trueSet;
        ImmutableBooleanSet set23 = this.trueFalseSet;
        assertTrue(set13.isProperSubsetOf(set23));

        ImmutableBooleanSet set14 = this.falseSet;
        ImmutableBooleanSet set24 = this.trueFalseSet;
        assertTrue(set14.isProperSubsetOf(set24));

        ImmutableBooleanSet set15 = this.trueFalseSet;
        ImmutableBooleanSet set25 = this.newWith();
        assertFalse(set15.isProperSubsetOf(set25));

        ImmutableBooleanSet set16 = this.newWith();
        ImmutableBooleanSet set26 = this.trueFalseSet;
        assertTrue(set16.isProperSubsetOf(set26));

        ImmutableBooleanSet set17 = this.newWith();
        ImmutableBooleanSet set27 = this.newWith();
        assertFalse(set17.isProperSubsetOf(set27));
    }

    @Test
    public void cartesianProduct()
    {
        ImmutableBooleanSet set11 = this.trueSet;
        ImmutableBooleanSet set21 = this.falseSet;
        MutableSet<BooleanBooleanPair> expected1 = Sets.mutable.with(
                PrimitiveTuples.pair(true, false));
        assertEquals(expected1, set11.cartesianProduct(set21).toSet());

        ImmutableBooleanSet set12 = this.falseSet;
        ImmutableBooleanSet set22 = this.falseSet;
        MutableSet<BooleanBooleanPair> expected2 = Sets.mutable.with(
                PrimitiveTuples.pair(false, false));
        assertEquals(expected2, set12.cartesianProduct(set22).toSet());

        ImmutableBooleanSet set13 = this.trueSet;
        ImmutableBooleanSet set23 = this.trueFalseSet;
        MutableSet<BooleanBooleanPair> expected3 = Sets.mutable.with(
                PrimitiveTuples.pair(true, true),
                PrimitiveTuples.pair(true, false));
        assertEquals(expected3, set13.cartesianProduct(set23).toSet());

        ImmutableBooleanSet set14 = this.falseSet;
        ImmutableBooleanSet set24 = this.trueFalseSet;
        MutableSet<BooleanBooleanPair> expected4 = Sets.mutable.with(
                PrimitiveTuples.pair(false, true),
                PrimitiveTuples.pair(false, false));
        assertEquals(expected4, set14.cartesianProduct(set24).toSet());

        ImmutableBooleanSet set15 = this.trueFalseSet;
        ImmutableBooleanSet set25 = this.newWith();
        assertEquals(Sets.mutable.empty(), set15.cartesianProduct(set25).toSet());

        ImmutableBooleanSet set16 = this.newWith();
        ImmutableBooleanSet set26 = this.trueFalseSet;
        assertEquals(Sets.mutable.empty(), set16.cartesianProduct(set26).toSet());

        ImmutableBooleanSet set17 = this.newWith();
        ImmutableBooleanSet set27 = this.newWith();
        assertEquals(Sets.mutable.empty(), set17.cartesianProduct(set27).toSet());
    }
}
