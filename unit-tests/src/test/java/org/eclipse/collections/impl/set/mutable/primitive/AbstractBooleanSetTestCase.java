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
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.collection.mutable.primitive.AbstractMutableBooleanCollectionTestCase;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Before
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
        Assert.assertTrue(set.containsAll(true, false, true));
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
        Assert.assertFalse(this.emptySet.notEmpty());
        Assert.assertTrue(this.setWithFalse.notEmpty());
        Assert.assertTrue(this.setWithTrue.notEmpty());
        Assert.assertTrue(this.setWithTrueFalse.notEmpty());
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
        Assert.assertFalse(this.setWithFalse.contains(false));
        Assert.assertFalse(this.setWithTrue.contains(true));
        Assert.assertFalse(this.setWithTrueFalse.contains(true));
        Assert.assertFalse(this.setWithTrueFalse.contains(false));
    }

    @Override
    @Test
    public void contains()
    {
        super.contains();
        Assert.assertFalse(this.emptySet.contains(true));
        Assert.assertFalse(this.emptySet.contains(false));
        Assert.assertTrue(this.setWithFalse.contains(false));
        Assert.assertFalse(this.setWithFalse.contains(true));
        Assert.assertTrue(this.setWithTrue.contains(true));
        Assert.assertFalse(this.setWithTrue.contains(false));
        Assert.assertTrue(this.setWithTrueFalse.contains(true));
        Assert.assertTrue(this.setWithTrueFalse.contains(false));
    }

    @Override
    @Test
    public void containsAllArray()
    {
        super.containsAllArray();
        Assert.assertFalse(this.emptySet.containsAll(true));
        Assert.assertFalse(this.emptySet.containsAll(true, false));
        Assert.assertTrue(this.setWithFalse.containsAll(false, false));
        Assert.assertFalse(this.setWithFalse.containsAll(true, true));
        Assert.assertFalse(this.setWithFalse.containsAll(true, false, true));
        Assert.assertTrue(this.setWithTrue.containsAll(true, true));
        Assert.assertFalse(this.setWithTrue.containsAll(false, false));
        Assert.assertFalse(this.setWithTrue.containsAll(true, false, false));
        Assert.assertTrue(this.setWithTrueFalse.containsAll(true, true));
        Assert.assertTrue(this.setWithTrueFalse.containsAll(false, false));
        Assert.assertTrue(this.setWithTrueFalse.containsAll(false, true, true));
    }

    @Override
    @Test
    public void containsAllIterable()
    {
        super.containsAllIterable();
        Assert.assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true)));
        Assert.assertFalse(this.emptySet.containsAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertTrue(this.setWithFalse.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertFalse(this.setWithFalse.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(this.setWithFalse.containsAll(BooleanArrayList.newListWith(true, false, true)));
        Assert.assertTrue(this.setWithTrue.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(this.setWithTrue.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertFalse(this.setWithTrue.containsAll(BooleanArrayList.newListWith(true, false, false)));
        Assert.assertTrue(this.setWithTrueFalse.containsAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertTrue(this.setWithTrueFalse.containsAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertTrue(this.setWithTrueFalse.containsAll(BooleanArrayList.newListWith(false, true, true)));
    }

    @Override
    @Test
    public void add()
    {
        Assert.assertTrue(this.emptySet.add(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.emptySet);
        MutableBooleanSet set = this.newWith();
        Assert.assertTrue(set.add(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), set);
        Assert.assertFalse(this.setWithFalse.add(false));
        Assert.assertTrue(this.setWithFalse.add(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithFalse);
        Assert.assertFalse(this.setWithTrue.add(true));
        Assert.assertTrue(this.setWithTrue.add(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrue);
        Assert.assertFalse(this.setWithTrueFalse.add(true));
        Assert.assertFalse(this.setWithTrueFalse.add(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse);
    }

    @Override
    @Test
    public void addAllArray()
    {
        Assert.assertTrue(this.emptySet.addAll(true, false, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.emptySet);
        Assert.assertFalse(this.setWithFalse.addAll(false, false));
        Assert.assertTrue(this.setWithFalse.addAll(true, false, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithFalse);
        Assert.assertFalse(this.setWithTrue.addAll(true, true));
        Assert.assertTrue(this.setWithTrue.addAll(true, false, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrue);
        Assert.assertFalse(this.setWithTrueFalse.addAll(true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse);
    }

    @Override
    @Test
    public void addAllIterable()
    {
        Assert.assertTrue(this.emptySet.addAll(BooleanHashSet.newSetWith(true, false, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.emptySet);
        Assert.assertFalse(this.setWithFalse.addAll(BooleanHashSet.newSetWith(false, false)));
        Assert.assertTrue(this.setWithFalse.addAll(BooleanHashSet.newSetWith(true, false, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithFalse);
        Assert.assertFalse(this.setWithTrue.addAll(BooleanHashSet.newSetWith(true, true)));
        Assert.assertTrue(this.setWithTrue.addAll(BooleanHashSet.newSetWith(true, false, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrue);
        Assert.assertFalse(this.setWithTrueFalse.addAll(BooleanHashSet.newSetWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse);
    }

    @Override
    @Test
    public void remove()
    {
        Assert.assertTrue(this.setWithTrueFalse.remove(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        Assert.assertTrue(set.remove(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), set);
        Assert.assertFalse(this.setWithTrue.remove(false));
        Assert.assertTrue(this.setWithTrue.remove(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);
        Assert.assertFalse(this.setWithFalse.remove(true));
        Assert.assertTrue(this.setWithFalse.remove(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);
        Assert.assertFalse(this.emptySet.remove(true));
        Assert.assertFalse(this.emptySet.remove(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void removeAll()
    {
        super.removeAll();
        Assert.assertFalse(this.emptySet.removeAll());
        Assert.assertFalse(this.setWithFalse.removeAll());
        Assert.assertFalse(this.setWithTrue.removeAll());
        Assert.assertFalse(this.setWithTrueFalse.removeAll());

        Assert.assertTrue(this.setWithTrueFalse.removeAll(true, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        Assert.assertTrue(set.removeAll(true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        Assert.assertTrue(sett.removeAll(false, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), sett);

        Assert.assertFalse(this.setWithTrue.removeAll(false, false));
        MutableBooleanSet sett2 = this.newWith(true);
        Assert.assertTrue(sett2.removeAll(true, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(), sett2);
        Assert.assertTrue(this.setWithTrue.removeAll(true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        Assert.assertFalse(this.setWithFalse.removeAll(true, true));
        MutableBooleanSet sett3 = this.newWith(false);
        Assert.assertTrue(sett3.removeAll(false, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), sett3);
        Assert.assertTrue(this.setWithFalse.removeAll(true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        Assert.assertFalse(this.emptySet.removeAll(true, true));
        Assert.assertFalse(this.emptySet.removeAll(true, false));
        Assert.assertFalse(this.emptySet.removeAll(false, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void removeAll_iterable()
    {
        super.removeAll_iterable();
        Assert.assertFalse(this.emptySet.removeAll(new BooleanArrayList()));
        Assert.assertFalse(this.setWithFalse.removeAll(new BooleanArrayList()));
        Assert.assertFalse(this.setWithTrue.removeAll(new BooleanArrayList()));
        Assert.assertFalse(this.setWithTrueFalse.removeAll(new BooleanArrayList()));

        Assert.assertTrue(this.setWithTrueFalse.removeAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        Assert.assertTrue(set.removeAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        Assert.assertTrue(sett.removeAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), sett);

        Assert.assertFalse(this.setWithTrue.removeAll(BooleanArrayList.newListWith(false, false)));
        MutableBooleanSet sett2 = this.newWith(true);
        Assert.assertTrue(sett2.removeAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), sett2);
        Assert.assertTrue(this.setWithTrue.removeAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        Assert.assertFalse(this.setWithFalse.removeAll(true, true));
        MutableBooleanSet sett3 = this.newWith(false);
        Assert.assertTrue(sett3.removeAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), sett3);
        Assert.assertTrue(this.setWithFalse.removeAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        Assert.assertFalse(this.emptySet.removeAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(this.emptySet.removeAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertFalse(this.emptySet.removeAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void retainAll()
    {
        super.retainAll();
        Assert.assertFalse(this.emptySet.retainAll());
        Assert.assertTrue(this.setWithTrueFalse.retainAll(true, true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        Assert.assertTrue(set.retainAll());
        Assert.assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        Assert.assertTrue(sett.retainAll(false, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), sett);

        MutableBooleanSet sett2 = this.newWith(true);
        Assert.assertTrue(sett2.retainAll(false, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), sett2);
        Assert.assertTrue(this.setWithTrue.retainAll(false, false));
        Assert.assertFalse(this.setWithTrue.retainAll(true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        MutableBooleanSet sett3 = this.newWith(false);
        Assert.assertFalse(sett3.retainAll(false, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), sett3);
        Assert.assertTrue(this.setWithFalse.retainAll(true, true));
        Assert.assertFalse(this.setWithFalse.retainAll(true, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        Assert.assertFalse(this.emptySet.retainAll(true, true));
        Assert.assertFalse(this.emptySet.retainAll(true, false));
        Assert.assertFalse(this.emptySet.retainAll(false, false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
    }

    @Override
    @Test
    public void retainAll_iterable()
    {
        super.retainAll_iterable();
        Assert.assertFalse(this.emptySet.retainAll(new BooleanArrayList()));
        Assert.assertTrue(this.setWithTrueFalse.retainAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse);
        MutableBooleanSet set = this.newWith(true, false);
        Assert.assertTrue(set.retainAll(BooleanArrayList.newListWith()));
        Assert.assertEquals(BooleanHashSet.newSetWith(), set);
        MutableBooleanSet sett = this.newWith(true, false);
        Assert.assertTrue(sett.retainAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), sett);

        MutableBooleanSet sett2 = this.newWith(true);
        Assert.assertTrue(sett2.retainAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), sett2);
        Assert.assertTrue(this.setWithTrue.retainAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertFalse(this.setWithTrue.retainAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue);

        MutableBooleanSet sett3 = this.newWith(false);
        Assert.assertFalse(sett3.retainAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), sett3);
        Assert.assertTrue(this.setWithFalse.retainAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(this.setWithFalse.retainAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse);

        Assert.assertFalse(this.emptySet.retainAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertFalse(this.emptySet.retainAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertFalse(this.emptySet.retainAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.emptySet);
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
        Assert.assertSame(emptySet, set);
        Assert.assertEquals(this.setWithFalse, set);
        Assert.assertEquals(this.setWithTrue, set1);
        Assert.assertEquals(this.setWithTrueFalse, set2);
        Assert.assertEquals(this.setWithTrueFalse, set3);
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse.with(true));
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
        Assert.assertSame(emptySet, set);
        Assert.assertEquals(this.setWithFalse, set);
        Assert.assertEquals(this.setWithTrue, set1);
        Assert.assertEquals(this.setWithTrueFalse, set2);
        Assert.assertEquals(this.setWithTrueFalse, set3);
        Assert.assertEquals(BooleanHashSet.newSetWith(true, false), this.setWithTrueFalse.withAll(BooleanHashSet.newSetWith(true, false)));
    }

    @Override
    @Test
    public void without()
    {
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse.without(false));
        Assert.assertSame(this.setWithTrueFalse, this.setWithTrueFalse.without(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.newWith(true, false).without(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrueFalse.without(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrue.without(false));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue.without(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.setWithFalse.without(true));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse.without(false));
        Assert.assertEquals(new BooleanHashSet(), this.emptySet.without(true));
        Assert.assertEquals(new BooleanHashSet(), this.emptySet.without(false));
    }

    @Override
    @Test
    public void withoutAll()
    {
        super.withoutAll();
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrueFalse.withoutAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertSame(this.setWithTrueFalse, this.setWithTrueFalse.withoutAll(BooleanArrayList.newListWith(false, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.newWith(true, false).withoutAll(BooleanArrayList.newListWith(true, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.newWith(true, false).withoutAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrueFalse.withoutAll(BooleanArrayList.newListWith(true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrue.withoutAll(BooleanArrayList.newListWith(false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithTrue.withoutAll(BooleanArrayList.newListWith(true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.newWith(true).withoutAll(BooleanArrayList.newListWith(false, true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.setWithFalse.withoutAll(BooleanArrayList.newListWith(true)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.setWithFalse.withoutAll(BooleanArrayList.newListWith(false)));
        Assert.assertEquals(BooleanHashSet.newSetWith(), this.newWith(false).withoutAll(BooleanArrayList.newListWith(true, false)));
        Assert.assertEquals(new BooleanHashSet(), this.emptySet.withoutAll(BooleanArrayList.newListWith(true)));
        Assert.assertEquals(new BooleanHashSet(), this.emptySet.withoutAll(BooleanArrayList.newListWith(false)));
        Assert.assertEquals(new BooleanHashSet(), this.emptySet.withoutAll(BooleanArrayList.newListWith(false, true)));
    }

    @Override
    @Test
    public void toArray()
    {
        super.toArray();
        Assert.assertEquals(0L, this.emptySet.toArray().length);

        Assert.assertEquals(1L, this.setWithFalse.toArray().length);
        Assert.assertFalse(this.setWithFalse.toArray()[0]);

        Assert.assertEquals(1L, this.setWithTrue.toArray().length);
        Assert.assertTrue(this.setWithTrue.toArray()[0]);

        Assert.assertEquals(2L, this.setWithTrueFalse.toArray().length);
        Assert.assertTrue(Arrays.equals(new boolean[]{false, true}, this.setWithTrueFalse.toArray())
                || Arrays.equals(new boolean[]{true, false}, this.setWithTrueFalse.toArray()));
    }

    @Override
    @Test
    public void toList()
    {
        super.toList();
        Assert.assertEquals(new BooleanArrayList(), this.emptySet.toList());
        Assert.assertEquals(BooleanArrayList.newListWith(false), this.setWithFalse.toList());
        Assert.assertEquals(BooleanArrayList.newListWith(true), this.setWithTrue.toList());
        Assert.assertTrue(BooleanArrayList.newListWith(false, true).equals(this.setWithTrueFalse.toList())
                || BooleanArrayList.newListWith(true, false).equals(this.setWithTrueFalse.toList()));
    }

    @Override
    @Test
    public void toSet()
    {
        super.toSet();
        Assert.assertEquals(new BooleanHashSet(), this.emptySet.toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(false), this.setWithFalse.toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(true), this.setWithTrue.toSet());
        Assert.assertEquals(BooleanHashSet.newSetWith(false, true), this.setWithTrueFalse.toSet());
    }

    @Override
    @Test
    public void toBag()
    {
        Assert.assertEquals(new BooleanHashBag(), this.emptySet.toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(false), this.setWithFalse.toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(true), this.setWithTrue.toBag());
        Assert.assertEquals(BooleanHashBag.newBagWith(false, true), this.setWithTrueFalse.toBag());
    }

    @Override
    @Test
    public void testEquals()
    {
        Assert.assertNotEquals(this.setWithFalse, this.emptySet);
        Assert.assertNotEquals(this.setWithFalse, this.setWithTrue);
        Assert.assertNotEquals(this.setWithFalse, this.setWithTrueFalse);
        Assert.assertNotEquals(this.setWithTrue, this.emptySet);
        Assert.assertNotEquals(this.setWithTrue, this.setWithTrueFalse);
        Assert.assertNotEquals(this.setWithTrueFalse, this.emptySet);
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
        Assert.assertEquals(UnifiedSet.newSet().hashCode(), this.emptySet.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(false).hashCode(), this.setWithFalse.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(true).hashCode(), this.setWithTrue.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(true, false).hashCode(), this.setWithTrueFalse.hashCode());
        Assert.assertEquals(UnifiedSet.newSetWith(false, true).hashCode(), this.setWithTrueFalse.hashCode());
        Assert.assertNotEquals(UnifiedSet.newSetWith(false).hashCode(), this.setWithTrueFalse.hashCode());
    }

    @Override
    @Test
    public void booleanIterator()
    {
        BooleanIterator booleanIterator0 = this.emptySet.booleanIterator();
        Assert.assertFalse(booleanIterator0.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) booleanIterator0::next);

        BooleanIterator booleanIterator1 = this.setWithFalse.booleanIterator();
        Assert.assertTrue(booleanIterator1.hasNext());
        Assert.assertFalse(booleanIterator1.next());
        Assert.assertFalse(booleanIterator1.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) booleanIterator1::next);

        BooleanIterator booleanIterator2 = this.setWithTrue.booleanIterator();
        Assert.assertTrue(booleanIterator2.hasNext());
        Assert.assertTrue(booleanIterator2.next());
        Assert.assertFalse(booleanIterator2.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) booleanIterator2::next);

        BooleanIterator booleanIterator3 = this.setWithTrueFalse.booleanIterator();
        BooleanHashSet actual = new BooleanHashSet();
        Assert.assertTrue(booleanIterator3.hasNext());
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
        this.setWithFalse.forEach(each -> sum[1] += each);
        this.setWithTrue.forEach(each -> sum[2] += each);
        this.setWithTrueFalse.forEach(each -> sum[3] += each);

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
        Assert.assertEquals(0L, this.setWithFalse.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(1L, this.setWithFalse.count(BooleanPredicates.isFalse()));
        Assert.assertEquals(0L, this.setWithTrue.count(BooleanPredicates.isFalse()));
        Assert.assertEquals(1L, this.setWithTrueFalse.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(0L, this.setWithTrueFalse.count(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Assert.assertEquals(1L, this.setWithTrueFalse.count(BooleanPredicates.isFalse()));
        Assert.assertEquals(1L, this.setWithTrueFalse.count(BooleanPredicates.isTrue()));
        Assert.assertEquals(2L, this.setWithTrueFalse.count(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();
        Assert.assertFalse(this.emptySet.anySatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Assert.assertFalse(this.setWithFalse.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.setWithFalse.anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.setWithTrue.anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.setWithTrue.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.setWithTrueFalse.anySatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.setWithTrueFalse.anySatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.setWithTrueFalse.anySatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();
        Assert.assertTrue(this.emptySet.allSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Assert.assertFalse(this.setWithFalse.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.setWithFalse.allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.setWithTrue.allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.setWithTrue.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.setWithTrueFalse.allSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.setWithTrueFalse.allSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.setWithTrueFalse.allSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Assert.assertTrue(this.setWithTrueFalse.allSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        Assert.assertTrue(this.emptySet.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse())));
        Assert.assertFalse(this.setWithFalse.noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.setWithFalse.noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.setWithTrue.noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertTrue(this.setWithTrue.noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertFalse(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.isTrue()));
        Assert.assertFalse(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.isFalse()));
        Assert.assertTrue(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
        Assert.assertFalse(this.setWithTrueFalse.noneSatisfy(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.isTrue())));
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
        Assert.assertTrue(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), true));
        Assert.assertFalse(this.emptySet.detectIfNone(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.isFalse()), false));
        Assert.assertTrue(this.setWithFalse.detectIfNone(BooleanPredicates.isTrue(), true));
        Assert.assertFalse(this.setWithFalse.detectIfNone(BooleanPredicates.isTrue(), false));
        Assert.assertFalse(this.setWithFalse.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertFalse(this.setWithFalse.detectIfNone(BooleanPredicates.isFalse(), false));
        Assert.assertTrue(this.setWithTrue.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertFalse(this.setWithTrue.detectIfNone(BooleanPredicates.isFalse(), false));
        Assert.assertTrue(this.setWithTrue.detectIfNone(BooleanPredicates.isTrue(), true));
        Assert.assertTrue(this.setWithTrue.detectIfNone(BooleanPredicates.isTrue(), false));
        Assert.assertTrue(this.setWithTrueFalse.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), true));
        Assert.assertFalse(this.setWithTrueFalse.detectIfNone(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.isTrue()), false));
        Assert.assertFalse(this.setWithTrueFalse.detectIfNone(BooleanPredicates.isFalse(), true));
        Assert.assertTrue(this.setWithTrueFalse.detectIfNone(BooleanPredicates.isTrue(), false));
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        BooleanToObjectFunction<Boolean> function = parameter -> !parameter;
        Assert.assertEquals(UnifiedSet.newSetWith(true, false), this.setWithTrueFalse.collect(function));
        Assert.assertEquals(UnifiedSet.newSetWith(false), this.setWithTrue.collect(function));
        Assert.assertEquals(UnifiedSet.newSetWith(true), this.setWithFalse.collect(function));
        Assert.assertEquals(UnifiedSet.newSetWith(), this.emptySet.collect(function));
    }

    @Override
    @Test
    public void testToString()
    {
        super.testToString();
        Assert.assertEquals("[]", this.emptySet.toString());
        Assert.assertEquals("[false]", this.setWithFalse.toString());
        Assert.assertEquals("[true]", this.setWithTrue.toString());
        Assert.assertTrue("[true, false]".equals(this.setWithTrueFalse.toString())
                || "[false, true]".equals(this.setWithTrueFalse.toString()));
    }

    @Override
    @Test
    public void makeString()
    {
        super.makeString();
        Assert.assertEquals("", this.emptySet.makeString());
        Assert.assertEquals("false", this.setWithFalse.makeString());
        Assert.assertEquals("true", this.setWithTrue.makeString());
        Assert.assertTrue("true, false".equals(this.setWithTrueFalse.makeString())
                || "false, true".equals(this.setWithTrueFalse.makeString()));

        Assert.assertEquals("", this.emptySet.makeString("/"));
        Assert.assertEquals("false", this.setWithFalse.makeString("/"));
        Assert.assertEquals("true", this.setWithTrue.makeString("/"));
        Assert.assertTrue(this.setWithTrueFalse.makeString("/"), "true/false".equals(this.setWithTrueFalse.makeString("/"))
                || "false/true".equals(this.setWithTrueFalse.makeString("/")));

        Assert.assertEquals("[]", this.emptySet.makeString("[", "/", "]"));
        Assert.assertEquals("[false]", this.setWithFalse.makeString("[", "/", "]"));
        Assert.assertEquals("[true]", this.setWithTrue.makeString("[", "/", "]"));
        Assert.assertTrue(this.setWithTrueFalse.makeString("[", "/", "]"), "[true/false]".equals(this.setWithTrueFalse.makeString("[", "/", "]"))
                || "[false/true]".equals(this.setWithTrueFalse.makeString("[", "/", "]")));
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
        this.setWithFalse.appendString(appendable1);
        Assert.assertEquals("false", appendable1.toString());

        StringBuilder appendable2 = new StringBuilder();
        this.setWithTrue.appendString(appendable2);
        Assert.assertEquals("true", appendable2.toString());

        StringBuilder appendable3 = new StringBuilder();
        this.setWithTrueFalse.appendString(appendable3);
        Assert.assertTrue("true, false".equals(appendable3.toString())
                || "false, true".equals(appendable3.toString()));

        StringBuilder appendable4 = new StringBuilder();
        this.setWithTrueFalse.appendString(appendable4, "[", ", ", "]");
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
        Assert.assertEquals(this.setWithFalse, this.setWithFalse.asLazy().toSet());
        Assert.assertEquals(this.setWithTrue, this.setWithTrue.asLazy().toSet());
        Assert.assertEquals(this.setWithTrueFalse, this.setWithTrueFalse.asLazy().toSet());
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        Verify.assertInstanceOf(SynchronizedBooleanSet.class, this.emptySet.asSynchronized());
        Assert.assertEquals(new SynchronizedBooleanSet(this.emptySet), this.emptySet.asSynchronized());
        Assert.assertEquals(new SynchronizedBooleanSet(this.setWithFalse), this.setWithFalse.asSynchronized());
        Assert.assertEquals(new SynchronizedBooleanSet(this.setWithTrue), this.setWithTrue.asSynchronized());
        Assert.assertEquals(new SynchronizedBooleanSet(this.setWithTrueFalse), this.setWithTrueFalse.asSynchronized());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();
        Verify.assertInstanceOf(UnmodifiableBooleanSet.class, this.emptySet.asUnmodifiable());
        Assert.assertEquals(new UnmodifiableBooleanSet(this.emptySet), this.emptySet.asUnmodifiable());
        Assert.assertEquals(new UnmodifiableBooleanSet(this.setWithFalse), this.setWithFalse.asUnmodifiable());
        Assert.assertEquals(new UnmodifiableBooleanSet(this.setWithTrue), this.setWithTrue.asUnmodifiable());
        Assert.assertEquals(new UnmodifiableBooleanSet(this.setWithTrueFalse), this.setWithTrueFalse.asUnmodifiable());
    }
}
