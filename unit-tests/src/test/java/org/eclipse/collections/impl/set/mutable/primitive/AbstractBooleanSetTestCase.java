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
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.tuple.primitive.BooleanBooleanPair;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableBooleanCollectionTestCase;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractBooleanSetTestCase extends AbstractMutableBooleanCollectionTestCase
{
    private MutableBooleanSet emptySet;
    private MutableBooleanSet setWithFalse;
    private MutableBooleanSet setWithTrue;
    private MutableBooleanSet setWithTrueFalse;

    @Override
    protected abstract MutableBooleanSet classUnderTest();

    @Override
    protected abstract MutableBooleanSet newWith(boolean... elements);

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
        this.setWithFalse = this.newWith(false);
        this.setWithTrue = this.newWith(true);
        this.setWithTrueFalse = this.newWith(true, false);
    }

    @Override
    @Test
    public void newCollectionWith()
    {
        MutableBooleanSet set = this.classUnderTest();
        Verify.assertSize(2, set);
        assertTrue(set.containsAll(true, false, true));
    }

    @Override
    @Test
    public void isEmpty()
    {
        super.isEmpty();
        Verify.assertEmpty(this.emptySet);
        Verify.assertNotEmpty(this.setWithFalse);
        Verify.assertNotEmpty(this.setWithTrue);
        Verify.assertNotEmpty(this.setWithTrueFalse);
    }

    @Override
    @Test
    public void notEmpty()
    {
        super.notEmpty();
        assertFalse(this.emptySet.notEmpty());
        assertTrue(this.setWithFalse.notEmpty());
        assertTrue(this.setWithTrue.notEmpty());
        assertTrue(this.setWithTrueFalse.notEmpty());
    }

    @Override
    @Test
    public void clear()
    {
        super.clear();
        this.emptySet.clear();
        this.setWithFalse.clear();
        this.setWithTrue.clear();
        this.setWithTrueFalse.clear();
        Verify.assertEmpty(this.emptySet);
        Verify.assertEmpty(this.setWithFalse);
        Verify.assertEmpty(this.setWithTrue);
        Verify.assertEmpty(this.setWithTrueFalse);
        assertFalse(this.setWithFalse.contains(false));
        assertFalse(this.setWithTrue.contains(true));
        assertFalse(this.setWithTrueFalse.contains(true));
        assertFalse(this.setWithTrueFalse.contains(false));
    }

    @Override
    @Test
    public void contains()
    {
        super.contains();
        assertFalse(this.emptySet.contains(true));
        assertFalse(this.emptySet.contains(false));
        assertTrue(this.setWithFalse.contains(false));
        assertFalse(this.setWithFalse.contains(true));
        assertTrue(this.setWithTrue.contains(true));
        assertFalse(this.setWithTrue.contains(false));
        assertTrue(this.setWithTrueFalse.contains(true));
        assertTrue(this.setWithTrueFalse.contains(false));
    }

    @Override
    @Test
    public void containsAllArray()
    {
        super.containsAllArray();
        assertFalse(this.emptySet.containsAll(true));
        assertFalse(this.emptySet.containsAll(true, false));
        assertTrue(this.setWithFalse.containsAll(false, false));
        assertFalse(this.setWithFalse.containsAll(true, true));
        assertFalse(this.setWithFalse.containsAll(true, false, true));
        assertTrue(this.setWithTrue.containsAll(true, true));
        assertFalse(this.setWithTrue.containsAll(false, false));
        assertFalse(this.setWithTrue.containsAll(true, false, false));
        assertTrue(this.setWithTrueFalse.containsAll(true, true));
        assertTrue(this.setWithTrueFalse.containsAll(false, false));
        assertTrue(this.setWithTrueFalse.containsAll(false, true, true));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        super.containsAllIterable();
        assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true)));
        assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(this.setWithFalse.containsAll(BooleanArrayList.newListWith(false, false)));
        assertFalse(this.setWithFalse.containsAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(this.setWithFalse.containsAll(BooleanArrayList.newListWith(true, false, true)));
        assertTrue(this.setWithTrue.containsAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(this.setWithTrue.containsAll(BooleanArrayList.newListWith(false, false)));
        assertFalse(this.setWithTrue.containsAll(BooleanArrayList.newListWith(true, false, false)));
        assertTrue(this.setWithTrueFalse.containsAll(BooleanArrayList.newListWith(true, true)));
        assertTrue(this.setWithTrueFalse.containsAll(BooleanArrayList.newListWith(false, false)));
        assertTrue(this.setWithTrueFalse.containsAll(BooleanArrayList.newListWith(false, true, true)));
    }

    @Override
    @Test
    public void add()
    {
        assertTrue(this.emptySet.add(true));
        assertEquals(BooleanHashSet.newSetWith(true), this.emptySet);
        MutableBooleanSet set = this.newWith();
        assertTrue(set.add(false));
        assertEquals(BooleanHashSet.newSetWith(false), set);
        assertFalse(this.setWithFalse.add(false));
        assertTrue(this.setWithFalse.add(true));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithFalse);
        assertFalse(this.setWithTrue.add(true));
        assertTrue(this.setWithTrue.add(false));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrue);
        assertFalse(this.setWithTrueFalse.add(true));
        assertFalse(this.setWithTrueFalse.add(false));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse);
    }

    @Override
    @Test
    public void addAllArray()
    {
        assertTrue(this.emptySet.addAll(true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.emptySet);
        assertFalse(this.setWithFalse.addAll(false, false));
        assertTrue(this.setWithFalse.addAll(true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithFalse);
        assertFalse(this.setWithTrue.addAll(true, true));
        assertTrue(this.setWithTrue.addAll(true, false, true));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrue);
        assertFalse(this.setWithTrueFalse.addAll(true, false));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse);
    }

    @Override
    @Test
    public void addAllIterable()
    {
        assertTrue(this.emptySet.addAll(BooleanHashSet.newSetWith(true, false, true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.emptySet);
        assertFalse(this.setWithFalse.addAll(BooleanHashSet.newSetWith(false, false)));
        assertTrue(this.setWithFalse.addAll(BooleanHashSet.newSetWith(true, false, true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithFalse);
        assertFalse(this.setWithTrue.addAll(BooleanHashSet.newSetWith(true, true)));
        assertTrue(this.setWithTrue.addAll(BooleanHashSet.newSetWith(true, false, true)));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrue);
        assertFalse(this.setWithTrueFalse.addAll(BooleanHashSet.newSetWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse);
    }

    @Override
    @Test
    public void remove()
    {
        assertTrue(this.setWithTrueFalse.remove(true));
        assertEquals(BooleanHashSet.newSetWith(false), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        assertTrue(set.remove(false));
        assertEquals(BooleanHashSet.newSetWith(true), set);
        assertFalse(this.setWithTrue.remove(false));
        assertTrue(this.setWithTrue.remove(true));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);
        assertFalse(this.setWithFalse.remove(true));
        assertTrue(this.setWithFalse.remove(false));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);
        assertFalse(this.emptySet.remove(true));
        assertFalse(this.emptySet.remove(false));
        assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void removeAll()
    {
        super.removeAll();
        assertFalse(this.emptySet.removeAll());
        assertFalse(this.setWithFalse.removeAll());
        assertFalse(this.setWithTrue.removeAll());
        assertFalse(this.setWithTrueFalse.removeAll());

        assertTrue(this.setWithTrueFalse.removeAll(true, true));
        assertEquals(BooleanHashSet.newSetWith(false), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        assertTrue(set.removeAll(true, false));
        assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        assertTrue(sett.removeAll(false, false));
        assertEquals(BooleanHashSet.newSetWith(true), sett);

        assertFalse(this.setWithTrue.removeAll(false, false));
        MutableBooleanSet sett2 = this.newWith(true);
        assertTrue(sett2.removeAll(true, true));
        assertEquals(BooleanHashSet.newSetWith(), sett2);
        assertTrue(this.setWithTrue.removeAll(true, false));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        assertFalse(this.setWithFalse.removeAll(true, true));
        MutableBooleanSet sett3 = this.newWith(false);
        assertTrue(sett3.removeAll(false, false));
        assertEquals(BooleanHashSet.newSetWith(), sett3);
        assertTrue(this.setWithFalse.removeAll(true, false));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        assertFalse(this.emptySet.removeAll(true, true));
        assertFalse(this.emptySet.removeAll(true, false));
        assertFalse(this.emptySet.removeAll(false, false));
        assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void removeAll_iterable()
    {
        super.removeAll_iterable();
        assertFalse(this.emptySet.removeAll(new BooleanArrayList()));
        assertFalse(this.setWithFalse.removeAll(new BooleanArrayList()));
        assertFalse(this.setWithTrue.removeAll(new BooleanArrayList()));
        assertFalse(this.setWithTrueFalse.removeAll(new BooleanArrayList()));

        assertTrue(this.setWithTrueFalse.removeAll(BooleanArrayList.newListWith(true, true)));
        assertEquals(BooleanHashSet.newSetWith(false), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        assertTrue(set.removeAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        assertTrue(sett.removeAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(true), sett);

        assertFalse(this.setWithTrue.removeAll(BooleanArrayList.newListWith(false, false)));
        MutableBooleanSet sett2 = this.newWith(true);
        assertTrue(sett2.removeAll(BooleanArrayList.newListWith(true, true)));
        assertEquals(BooleanHashSet.newSetWith(), sett2);
        assertTrue(this.setWithTrue.removeAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        assertFalse(this.setWithFalse.removeAll(true, true));
        MutableBooleanSet sett3 = this.newWith(false);
        assertTrue(sett3.removeAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(), sett3);
        assertTrue(this.setWithFalse.removeAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        assertFalse(this.emptySet.removeAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(this.emptySet.removeAll(BooleanArrayList.newListWith(true, false)));
        assertFalse(this.emptySet.removeAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void retainAll()
    {
        super.retainAll();
        assertFalse(this.emptySet.retainAll());
        assertTrue(this.setWithTrueFalse.retainAll(true, true));
        assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        assertTrue(set.retainAll());
        assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        assertTrue(sett.retainAll(false, false));
        assertEquals(BooleanHashSet.newSetWith(false), sett);

        MutableBooleanSet sett2 = this.newWith(true);
        assertTrue(sett2.retainAll(false, false));
        assertEquals(BooleanHashSet.newSetWith(), sett2);
        assertTrue(this.setWithTrue.retainAll(false, false));
        assertFalse(this.setWithTrue.retainAll(true, false));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        MutableBooleanSet sett3 = this.newWith(false);
        assertFalse(sett3.retainAll(false, false));
        assertEquals(BooleanHashSet.newSetWith(false), sett3);
        assertTrue(this.setWithFalse.retainAll(true, true));
        assertFalse(this.setWithFalse.retainAll(true, false));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        assertFalse(this.emptySet.retainAll(true, true));
        assertFalse(this.emptySet.retainAll(true, false));
        assertFalse(this.emptySet.retainAll(false, false));
        assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void retainAll_iterable()
    {
        super.retainAll_iterable();
        assertFalse(this.emptySet.retainAll(new BooleanArrayList()));
        assertTrue(this.setWithTrueFalse.retainAll(BooleanArrayList.newListWith(true, true)));
        assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        assertTrue(set.retainAll(BooleanArrayList.newListWith()));
        assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        assertTrue(sett.retainAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(false), sett);

        MutableBooleanSet sett2 = this.newWith(true);
        assertTrue(sett2.retainAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(), sett2);
        assertTrue(this.setWithTrue.retainAll(BooleanArrayList.newListWith(false, false)));
        assertFalse(this.setWithTrue.retainAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        MutableBooleanSet sett3 = this.newWith(false);
        assertFalse(sett3.retainAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(false), sett3);
        assertTrue(this.setWithFalse.retainAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(this.setWithFalse.retainAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        assertFalse(this.emptySet.retainAll(BooleanArrayList.newListWith(true, true)));
        assertFalse(this.emptySet.retainAll(BooleanArrayList.newListWith(true, false)));
        assertFalse(this.emptySet.retainAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void with()
    {
        super.with();
        MutableBooleanCollection emptySet = this.newWith();
        MutableBooleanCollection set = emptySet.with(false);
        MutableBooleanSet set1 = this.newWith().with(true);
        MutableBooleanSet set2 = this.newWith().with(true).with(false);
        MutableBooleanSet set3 = this.newWith().with(false).with(true);
        assertSame(emptySet, set);
        assertEquals(this.setWithFalse, set);
        assertEquals(this.setWithTrue, set1);
        assertEquals(this.setWithTrueFalse, set2);
        assertEquals(this.setWithTrueFalse, set3);
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse.with(true));
    }

    @Override
    @Test
    public void withAll()
    {
        super.withAll();
        MutableBooleanCollection emptySet = this.newWith();
        MutableBooleanCollection set = emptySet.withAll(BooleanArrayList.newListWith(false));
        MutableBooleanSet set1 = this.newWith().withAll(BooleanArrayList.newListWith(true));
        MutableBooleanSet set2 = this.newWith().withAll(BooleanArrayList.newListWith(true, false));
        MutableBooleanSet set3 = this.newWith().withAll(BooleanArrayList.newListWith(true, false));
        assertSame(emptySet, set);
        assertEquals(this.setWithFalse, set);
        assertEquals(this.setWithTrue, set1);
        assertEquals(this.setWithTrueFalse, set2);
        assertEquals(this.setWithTrueFalse, set3);
        assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse.withAll(BooleanHashSet.newSetWith(true, false)));
    }

    @Override
    @Test
    public void without()
    {
        assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse.without(false));
        assertSame(this.setWithTrueFalse, this.setWithTrueFalse.without(false));
        assertEquals(BooleanHashSet.newSetWith(false), this.newWith(true, false).without(true));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrueFalse.without(true));
        assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrue.without(false));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue.without(true));
        assertEquals(BooleanHashSet.newSetWith(false), this.setWithFalse.without(true));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse.without(false));
        assertEquals(new BooleanHashSet(), this.emptySet.without(true));
        assertEquals(new BooleanHashSet(), this.emptySet.without(false));
    }

    @Override
    @Test
    public void withoutAll()
    {
        super.withoutAll();
        assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse.withoutAll(BooleanArrayList.newListWith(false, false)));
        assertSame(this.setWithTrueFalse, this.setWithTrueFalse.withoutAll(BooleanArrayList.newListWith(false, false)));
        assertEquals(BooleanHashSet.newSetWith(false), this.newWith(true, false).withoutAll(BooleanArrayList.newListWith(true, true)));
        assertEquals(BooleanHashSet.newSetWith(), this.newWith(true, false).withoutAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrueFalse.withoutAll(BooleanArrayList.newListWith(true)));
        assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrue.withoutAll(BooleanArrayList.newListWith(false)));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue.withoutAll(BooleanArrayList.newListWith(true)));
        assertEquals(BooleanHashSet.newSetWith(), this.newWith(true).withoutAll(BooleanArrayList.newListWith(false, true)));
        assertEquals(BooleanHashSet.newSetWith(false), this.setWithFalse.withoutAll(BooleanArrayList.newListWith(true)));
        assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse.withoutAll(BooleanArrayList.newListWith(false)));
        assertEquals(BooleanHashSet.newSetWith(), this.newWith(false).withoutAll(BooleanArrayList.newListWith(true, false)));
        assertEquals(new BooleanHashSet(), this.emptySet.withoutAll(BooleanArrayList.newListWith(true)));
        assertEquals(new BooleanHashSet(), this.emptySet.withoutAll(BooleanArrayList.newListWith(false)));
        assertEquals(new BooleanHashSet(), this.emptySet.withoutAll(BooleanArrayList.newListWith(false, true)));
    }

    @Override
    @Test
    public void toArray()
    {
        super.toArray();
        assertEquals(0L, this.emptySet.toArray().length);

        assertEquals(1L, this.setWithFalse.toArray().length);
        assertFalse(this.setWithFalse.toArray()[0]);

        assertEquals(1L, this.setWithTrue.toArray().length);
        assertTrue(this.setWithTrue.toArray()[0]);

        assertEquals(2L, this.setWithTrueFalse.toArray().length);
        assertTrue(Arrays.equals(new boolean[]{false, true}, this.setWithTrueFalse.toArray())
                || Arrays.equals(new boolean[]{true, false}, this.setWithTrueFalse.toArray()));
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        assertEquals(new BooleanArrayList(), this.emptySet.toList());
        assertEquals(BooleanArrayList.newListWith(false), this.setWithFalse.toList());
        assertEquals(BooleanArrayList.newListWith(true), this.setWithTrue.toList());
        assertTrue(BooleanArrayList.newListWith(false, true).equals(this.setWithTrueFalse.toList())
                || BooleanArrayList.newListWith(true, false).equals(this.setWithTrueFalse.toList()));
    }

    @Override
    @Test
    public void toSet()
    {
        super.toSet();
        assertEquals(new BooleanHashSet(), this.emptySet.toSet());
        assertEquals(BooleanHashSet.newSetWith(false), this.setWithFalse.toSet());
        assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrue.toSet());
        assertEquals(BooleanHashSet.newSetWith(false, true), this.setWithTrueFalse.toSet());
    }

    @Override
    @Test
    public void toBag()
    {
        assertEquals(new BooleanHashBag(), this.emptySet.toBag());
        assertEquals(BooleanHashBag.newBagWith(false), this.setWithFalse.toBag());
        assertEquals(BooleanHashBag.newBagWith(true), this.setWithTrue.toBag());
        assertEquals(BooleanHashBag.newBagWith(false, true), this.setWithTrueFalse.toBag());
    }

    @Override
    @Test
    public void testEquals()
    {
        assertNotEquals(this.setWithFalse, this.emptySet);
        assertNotEquals(this.setWithFalse, this.setWithTrue);
        assertNotEquals(this.setWithFalse, this.setWithTrueFalse);
        assertNotEquals(this.setWithTrue, this.emptySet);
        assertNotEquals(this.setWithTrue, this.setWithTrueFalse);
        assertNotEquals(this.setWithTrueFalse, this.emptySet);
        Verify.assertEqualsAndHashCode(this.newWith(false, true), this.setWithTrueFalse);
        Verify.assertEqualsAndHashCode(this.newWith(true, false), this.setWithTrueFalse);

        Verify.assertPostSerializedEqualsAndHashCode(this.emptySet);
        Verify.assertPostSerializedEqualsAndHashCode(this.setWithFalse);
        Verify.assertPostSerializedEqualsAndHashCode(this.setWithTrue);
        Verify.assertPostSerializedEqualsAndHashCode(this.setWithTrueFalse);
    }

    @Override
    @Test
    public void testHashCode()
    {
        super.testHashCode();
        assertEquals(UnifiedSet.newSet().hashCode(), this.emptySet.hashCode());
        assertEquals(UnifiedSet.newSetWith(false).hashCode(), this.setWithFalse.hashCode());
        assertEquals(UnifiedSet.newSetWith(true).hashCode(), this.setWithTrue.hashCode());
        assertEquals(UnifiedSet.newSetWith(true, false).hashCode(), this.setWithTrueFalse.hashCode());
        assertEquals(UnifiedSet.newSetWith(false, true).hashCode(), this.setWithTrueFalse.hashCode());
        assertNotEquals(UnifiedSet.newSetWith(false).hashCode(), this.setWithTrueFalse.hashCode());
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator booleanIterator0 = this.emptySet.booleanIterator();
        assertFalse(booleanIterator0.hasNext());
        assertThrows(NoSuchElementException.class, booleanIterator0::next);

        BooleanIterator booleanIterator1 = this.setWithFalse.booleanIterator();
        assertTrue(booleanIterator1.hasNext());
        assertFalse(booleanIterator1.next());
        assertFalse(booleanIterator1.hasNext());
        assertThrows(NoSuchElementException.class, booleanIterator1::next);

        BooleanIterator booleanIterator2 = this.setWithTrue.booleanIterator();
        assertTrue(booleanIterator2.hasNext());
        assertTrue(booleanIterator2.next());
        assertFalse(booleanIterator2.hasNext());
        assertThrows(NoSuchElementException.class, booleanIterator2::next);

        BooleanIterator booleanIterator3 = this.setWithTrueFalse.booleanIterator();
        MutableBooleanSet actual = new BooleanHashSet();
        assertTrue(booleanIterator3.hasNext());
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
        this.setWithFalse.forEach(each -> sum[1] += each);
        this.setWithTrue.forEach(each -> sum[2] += each);
        this.setWithTrueFalse.forEach(each -> sum[3] += each);

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
        assertEquals(0L, this.setWithFalse.count(BooleanPredicates.isTrue()));
        assertEquals(1L, this.setWithFalse.count(BooleanPredicates.isFalse()));
        assertEquals(0L, this.setWithTrue.count(BooleanPredicates.isFalse()));
        assertEquals(1L, this.setWithTrueFalse.count(BooleanPredicates.isTrue()));
        assertEquals(0L, this.setWithTrueFalse.count(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        assertEquals(1L, this.setWithTrueFalse.count(BooleanPredicates.isFalse()));
        assertEquals(1L, this.setWithTrueFalse.count(BooleanPredicates.isTrue()));
        assertEquals(2L, this.setWithTrueFalse.count(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();
        assertFalse(this.emptySet.anySatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.setWithFalse.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.setWithFalse.anySatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.setWithTrue.anySatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.setWithTrue.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.setWithTrueFalse.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.setWithTrueFalse.anySatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.setWithTrueFalse.anySatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();
        assertTrue(this.emptySet.allSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.setWithFalse.allSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.setWithFalse.allSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.setWithTrue.allSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.setWithTrue.allSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.setWithTrueFalse.allSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.setWithTrueFalse.allSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.setWithTrueFalse.allSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        assertTrue(this.setWithTrueFalse.allSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        assertTrue(this.emptySet.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        assertFalse(this.setWithFalse.noneSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.setWithFalse.noneSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.setWithTrue.noneSatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.setWithTrue.noneSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.isTrue()));
        assertFalse(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.isFalse()));
        assertTrue(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        assertFalse(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void select()
    {
        Verify.assertEmpty(this.emptySet.select(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Verify.assertEmpty(this.setWithFalse.select(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.setWithFalse.select(BooleanPredicates.isFalse()));
        Verify.assertEmpty(this.setWithTrue.select(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.setWithTrue.select(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.setWithTrueFalse.select(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.setWithTrueFalse.select(BooleanPredicates.isTrue()));
        Verify.assertEmpty(this.setWithTrueFalse.select(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Verify.assertSize(2, this.setWithTrueFalse.select(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void reject()
    {
        Verify.assertEmpty(this.emptySet.reject(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Verify.assertEmpty(this.setWithTrue.reject(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.setWithTrue.reject(BooleanPredicates.isFalse()));
        Verify.assertEmpty(this.setWithFalse.reject(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.setWithFalse.reject(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.setWithTrueFalse.reject(BooleanPredicates.isFalse()));
        Verify.assertSize(1, this.setWithTrueFalse.reject(BooleanPredicates.isTrue()));
        Verify.assertEmpty(this.setWithTrueFalse.reject(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Verify.assertSize(2, this.setWithTrueFalse.reject(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        super.detectIfNone();
        assertTrue(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), true));
        assertFalse(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), false));
        assertTrue(this.setWithFalse.detectIfNone(BooleanPredicates.isTrue(), true));
        assertFalse(this.setWithFalse.detectIfNone(BooleanPredicates.isTrue(), false));
        assertFalse(this.setWithFalse.detectIfNone(BooleanPredicates.isFalse(), true));
        assertFalse(this.setWithFalse.detectIfNone(BooleanPredicates.isFalse(), false));
        assertTrue(this.setWithTrue.detectIfNone(BooleanPredicates.isFalse(), true));
        assertFalse(this.setWithTrue.detectIfNone(BooleanPredicates.isFalse(), false));
        assertTrue(this.setWithTrue.detectIfNone(BooleanPredicates.isTrue(), true));
        assertTrue(this.setWithTrue.detectIfNone(BooleanPredicates.isTrue(), false));
        assertTrue(this.setWithTrueFalse.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), true));
        assertFalse(this.setWithTrueFalse.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), false));
        assertFalse(this.setWithTrueFalse.detectIfNone(BooleanPredicates.isFalse(), true));
        assertTrue(this.setWithTrueFalse.detectIfNone(BooleanPredicates.isTrue(), false));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        BooleanToObjectFunction<Boolean> function = parameter -> !parameter;
        assertEquals(UnifiedSet.newSetWith(true, false), this.setWithTrueFalse.collect(function));
        assertEquals(UnifiedSet.newSetWith(false), this.setWithTrue.collect(function));
        assertEquals(UnifiedSet.newSetWith(true), this.setWithFalse.collect(function));
        assertEquals(UnifiedSet.newSetWith(), this.emptySet.collect(function));
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        assertEquals("[]", this.emptySet.toString());
        assertEquals("[false]", this.setWithFalse.toString());
        assertEquals("[true]", this.setWithTrue.toString());
        assertTrue("[true, false]".equals(this.setWithTrueFalse.toString())
                || "[false, true]".equals(this.setWithTrueFalse.toString()));
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        assertEquals("", this.emptySet.makeString());
        assertEquals("false", this.setWithFalse.makeString());
        assertEquals("true", this.setWithTrue.makeString());
        assertTrue("true, false".equals(this.setWithTrueFalse.makeString())
                || "false, true".equals(this.setWithTrueFalse.makeString()));

        assertEquals("", this.emptySet.makeString("/"));
        assertEquals("false", this.setWithFalse.makeString("/"));
        assertEquals("true", this.setWithTrue.makeString("/"));
        assertTrue("true/false".equals(this.setWithTrueFalse.makeString("/"))
                || "false/true".equals(this.setWithTrueFalse.makeString("/")), this.setWithTrueFalse.makeString("/"));

        assertEquals("[]", this.emptySet.makeString("[", "/", "]"));
        assertEquals("[false]", this.setWithFalse.makeString("[", "/", "]"));
        assertEquals("[true]", this.setWithTrue.makeString("[", "/", "]"));
        assertTrue("[true/false]".equals(this.setWithTrueFalse.makeString("[", "/", "]"))
                || "[false/true]".equals(this.setWithTrueFalse.makeString("[", "/", "]")), this.setWithTrueFalse.makeString("[", "/", "]"));
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
        this.setWithFalse.appendString(appendable1);
        assertEquals("false", appendable1.toString());

        StringBuilder appendable2 = new StringBuilder();
        this.setWithTrue.appendString(appendable2);
        assertEquals("true", appendable2.toString());

        StringBuilder appendable3 = new StringBuilder();
        this.setWithTrueFalse.appendString(appendable3);
        assertTrue("true, false".equals(appendable3.toString())
                || "false, true".equals(appendable3.toString()));

        StringBuilder appendable4 = new StringBuilder();
        this.setWithTrueFalse.appendString(appendable4, "[", ", ", "]");
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
        assertEquals(this.setWithFalse, this.setWithFalse.asLazy().toSet());
        assertEquals(this.setWithTrue, this.setWithTrue.asLazy().toSet());
        assertEquals(this.setWithTrueFalse, this.setWithTrueFalse.asLazy().toSet());
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        Verify.assertInstanceOf(SynchronizedBooleanSet.class, this.emptySet.asSynchronized());
        assertEquals(new SynchronizedBooleanSet(this.emptySet), this.emptySet.asSynchronized());
        assertEquals(new SynchronizedBooleanSet(this.setWithFalse), this.setWithFalse.asSynchronized());
        assertEquals(new SynchronizedBooleanSet(this.setWithTrue), this.setWithTrue.asSynchronized());
        assertEquals(new SynchronizedBooleanSet(this.setWithTrueFalse), this.setWithTrueFalse.asSynchronized());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        Verify.assertInstanceOf(UnmodifiableBooleanSet.class, this.emptySet.asUnmodifiable());
        assertEquals(new UnmodifiableBooleanSet(this.emptySet), this.emptySet.asUnmodifiable());
        assertEquals(new UnmodifiableBooleanSet(this.setWithFalse), this.setWithFalse.asUnmodifiable());
        assertEquals(new UnmodifiableBooleanSet(this.setWithTrue), this.setWithTrue.asUnmodifiable());
        assertEquals(new UnmodifiableBooleanSet(this.setWithTrueFalse), this.setWithTrueFalse.asUnmodifiable());
    }

    @Test
    public void union()
    {
        MutableBooleanSet set11 = this.newWith(true);
        MutableBooleanSet set21 = this.newWith(false);
        MutableBooleanSet actual = set11.union(set21);
        assertEquals(this.setWithTrueFalse, actual);

        MutableBooleanSet set12 = this.newWith(false);
        MutableBooleanSet set22 = this.newWith(false);
        MutableBooleanSet actual2 = set12.union(set22);
        assertEquals(this.setWithFalse, actual2);

        MutableBooleanSet set13 = this.newWith(true);
        MutableBooleanSet set23 = this.newWith(true);
        MutableBooleanSet actual3 = set13.union(set23);
        assertEquals(this.setWithTrue, actual3);

        MutableBooleanSet set14 = this.setWithTrueFalse;
        MutableBooleanSet set24 = this.newWith();
        MutableBooleanSet actual4 = set14.union(set24);
        assertEquals(this.setWithTrueFalse, actual4);

        MutableBooleanSet set15 = this.newWith();
        MutableBooleanSet set25 = this.newWith();
        MutableBooleanSet actual5 = set15.union(set25);
        assertEquals(this.emptySet, actual5);
    }

    @Test
    public void intersect()
    {
        MutableBooleanSet set11 = this.newWith(true);
        MutableBooleanSet set21 = this.newWith(false);
        MutableBooleanSet actual = set11.intersect(set21);
        assertEquals(this.emptySet, actual);

        MutableBooleanSet set12 = this.newWith(false);
        MutableBooleanSet set22 = this.newWith(false);
        MutableBooleanSet actual2 = set12.intersect(set22);
        assertEquals(this.setWithFalse, actual2);

        MutableBooleanSet set13 = this.newWith(true);
        MutableBooleanSet set23 = this.newWith(true);
        MutableBooleanSet actual3 = set13.intersect(set23);
        assertEquals(this.setWithTrue, actual3);

        MutableBooleanSet set14 = this.setWithTrueFalse;
        MutableBooleanSet set24 = this.newWith();
        MutableBooleanSet actual4 = set14.intersect(set24);
        assertEquals(this.emptySet, actual4);

        MutableBooleanSet set15 = this.newWith();
        MutableBooleanSet set25 = this.newWith();
        MutableBooleanSet actual5 = set15.intersect(set25);
        assertEquals(this.emptySet, actual5);
    }

    @Test
    public void difference()
    {
        MutableBooleanSet set11 = this.newWith(true);
        MutableBooleanSet set21 = this.newWith(false);
        MutableBooleanSet actual = set11.difference(set21);
        assertEquals(this.setWithTrue, actual);

        MutableBooleanSet set12 = this.newWith(false);
        MutableBooleanSet set22 = this.newWith(false);
        MutableBooleanSet actual2 = set12.difference(set22);
        assertEquals(this.emptySet, actual2);

        MutableBooleanSet set13 = this.setWithTrueFalse;
        MutableBooleanSet set23 = this.setWithTrueFalse;
        MutableBooleanSet actual3 = set13.difference(set23);
        assertEquals(this.emptySet, actual3);

        MutableBooleanSet set14 = this.setWithTrueFalse;
        MutableBooleanSet set24 = this.newWith();
        MutableBooleanSet actual4 = set14.difference(set24);
        assertEquals(this.setWithTrueFalse, actual4);

        MutableBooleanSet set15 = this.newWith();
        MutableBooleanSet set25 = this.setWithTrueFalse;
        MutableBooleanSet actual5 = set15.difference(set25);
        assertEquals(this.emptySet, actual5);

        MutableBooleanSet set16 = this.newWith();
        MutableBooleanSet set26 = this.newWith();
        MutableBooleanSet actual6 = set16.difference(set26);
        assertEquals(this.emptySet, actual6);
    }

    @Test
    public void symmetricDifference()
    {
        MutableBooleanSet set11 = this.newWith(true);
        MutableBooleanSet set21 = this.newWith(false);
        MutableBooleanSet actual = set11.symmetricDifference(set21);
        assertEquals(this.setWithTrueFalse, actual);

        MutableBooleanSet set12 = this.newWith(false);
        MutableBooleanSet set22 = this.newWith(false);
        MutableBooleanSet actual2 = set12.symmetricDifference(set22);
        assertEquals(this.emptySet, actual2);

        MutableBooleanSet set13 = this.setWithTrueFalse;
        MutableBooleanSet set23 = this.setWithTrueFalse;
        MutableBooleanSet actual3 = set13.symmetricDifference(set23);
        assertEquals(this.emptySet, actual3);

        MutableBooleanSet set14 = this.setWithTrueFalse;
        MutableBooleanSet set24 = this.newWith();
        MutableBooleanSet actual4 = set14.symmetricDifference(set24);
        assertEquals(this.setWithTrueFalse, actual4);

        MutableBooleanSet set15 = this.newWith();
        MutableBooleanSet set25 = this.setWithTrueFalse;
        MutableBooleanSet actual5 = set15.symmetricDifference(set25);
        assertEquals(this.setWithTrueFalse, actual5);

        MutableBooleanSet set16 = this.newWith();
        MutableBooleanSet set26 = this.newWith();
        MutableBooleanSet actual6 = set16.symmetricDifference(set26);
        assertEquals(this.emptySet, actual6);
    }

    @Test
    public void isSubsetOf()
    {
        MutableBooleanSet set11 = this.newWith(true);
        MutableBooleanSet set21 = this.newWith(false);
        assertFalse(set11.isSubsetOf(set21));

        MutableBooleanSet set12 = this.newWith(false);
        MutableBooleanSet set22 = this.newWith(false);
        assertTrue(set12.isSubsetOf(set22));

        MutableBooleanSet set13 = this.setWithTrueFalse;
        MutableBooleanSet set23 = this.setWithTrueFalse;
        assertTrue(set13.isSubsetOf(set23));

        MutableBooleanSet set14 = this.setWithTrueFalse;
        MutableBooleanSet set24 = this.newWith();
        assertFalse(set14.isSubsetOf(set24));

        MutableBooleanSet set15 = this.newWith();
        MutableBooleanSet set25 = this.setWithTrueFalse;
        assertTrue(set15.isSubsetOf(set25));

        MutableBooleanSet set16 = this.newWith();
        MutableBooleanSet set26 = this.newWith();
        assertTrue(set16.isSubsetOf(set26));
    }

    @Test
    public void isProperSubsetOf()
    {
        MutableBooleanSet set11 = this.newWith(true);
        MutableBooleanSet set21 = this.newWith(false);
        assertFalse(set11.isProperSubsetOf(set21));

        MutableBooleanSet set12 = this.newWith(false);
        MutableBooleanSet set22 = this.newWith(false);
        assertFalse(set12.isProperSubsetOf(set22));

        MutableBooleanSet set13 = this.setWithTrue;
        MutableBooleanSet set23 = this.setWithTrueFalse;
        assertTrue(set13.isProperSubsetOf(set23));

        MutableBooleanSet set14 = this.setWithFalse;
        MutableBooleanSet set24 = this.setWithTrueFalse;
        assertTrue(set14.isProperSubsetOf(set24));

        MutableBooleanSet set15 = this.setWithTrueFalse;
        MutableBooleanSet set25 = this.newWith();
        assertFalse(set15.isProperSubsetOf(set25));

        MutableBooleanSet set16 = this.newWith();
        MutableBooleanSet set26 = this.setWithTrueFalse;
        assertTrue(set16.isProperSubsetOf(set26));

        MutableBooleanSet set17 = this.newWith();
        MutableBooleanSet set27 = this.newWith();
        assertFalse(set17.isProperSubsetOf(set27));
    }

    @Test
    public void cartesianProduct()
    {
        MutableBooleanSet set11 = this.setWithTrue;
        MutableBooleanSet set21 = this.setWithFalse;
        MutableSet<BooleanBooleanPair> expected1 = Sets.mutable.with(
                PrimitiveTuples.pair(true, false));
        assertEquals(expected1, set11.cartesianProduct(set21).toSet());

        MutableBooleanSet set12 = this.newWith(false);
        MutableBooleanSet set22 = this.newWith(false);
        MutableSet<BooleanBooleanPair> expected2 = Sets.mutable.with(
                PrimitiveTuples.pair(false, false));
        assertEquals(expected2, set12.cartesianProduct(set22).toSet());

        MutableBooleanSet set13 = this.setWithTrue;
        MutableBooleanSet set23 = this.setWithTrueFalse;
        MutableSet<BooleanBooleanPair> expected3 = Sets.mutable.with(
                PrimitiveTuples.pair(true, true),
                PrimitiveTuples.pair(true, false));
        assertEquals(expected3, set13.cartesianProduct(set23).toSet());

        MutableBooleanSet set14 = this.setWithFalse;
        MutableBooleanSet set24 = this.setWithTrueFalse;
        MutableSet<BooleanBooleanPair> expected4 = Sets.mutable.with(
                PrimitiveTuples.pair(false, true),
                PrimitiveTuples.pair(false, false));
        assertEquals(expected4, set14.cartesianProduct(set24).toSet());

        MutableBooleanSet set15 = this.setWithTrueFalse;
        MutableBooleanSet set25 = this.newWith();
        assertEquals(Sets.mutable.empty(), set15.cartesianProduct(set25).toSet());

        MutableBooleanSet set16 = this.newWith();
        MutableBooleanSet set26 = this.setWithTrueFalse;
        assertEquals(Sets.mutable.empty(), set16.cartesianProduct(set26).toSet());

        MutableBooleanSet set17 = this.newWith();
        MutableBooleanSet set27 = this.newWith();
        assertEquals(Sets.mutable.empty(), set17.cartesianProduct(set27).toSet());
    }
}
