/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractImmutableUnifiedSetTestCase
{
    public abstract ImmutableSet<Integer> newSet(Integer... elements);

    public abstract ImmutableSet<Integer> newSetWith(int one, int two);

    public abstract ImmutableSet<Integer> newSetWith(int one, int two, int three);

    public abstract ImmutableSet<Integer> newSetWith(int... littleElements);

    @Test
    public void newCollection()
    {
        ImmutableSet<Integer> set = this.newSet();
        Assert.assertTrue(set.isEmpty());
        Assert.assertEquals(0, set.size());
    }

    @Test
    public void newWith()
    {
        ImmutableSet<Integer> set = this.newSet(1, 2, 3);
        ImmutableSet<Integer> with = set.newWith(4);
        Assert.assertNotEquals(set, with);
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), with);
        Assert.assertSame(set, set.newWith(3));
    }

    @Test
    public void newWithAll()
    {
        ImmutableSet<Integer> set = this.newSet(1, 2, 3);
        ImmutableSet<Integer> withAll = set.newWithAll(UnifiedSet.newSetWith(4, 5));
        Assert.assertNotEquals(set, withAll);
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), withAll);
    }

    @Test
    public void newWithOut()
    {
        ImmutableSet<Integer> set = this.newSet(1, 2, 3, 4);
        ImmutableSet<Integer> without = set.newWithout(4);
        Assert.assertNotEquals(set, without);
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), without);
        Assert.assertSame(set, set.newWithout(5));
    }

    @Test
    public void newWithoutAll()
    {
        ImmutableSet<Integer> set = this.newSet(1, 2, 3, 4, 5);
        ImmutableSet<Integer> withoutAll = set.newWithoutAll(UnifiedSet.newSetWith(4, 5));
        Assert.assertNotEquals(set, withoutAll);
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), withoutAll);
        ImmutableSet<Integer> largeList = this.newSet(Interval.oneTo(200).toArray());
        ImmutableSet<Integer> largeWithoutAll = largeList.newWithoutAll(FastList.newList(Interval.oneTo(100)));
        Assert.assertEquals(UnifiedSet.newSet(Interval.fromTo(101, 200)), largeWithoutAll);
        ImmutableSet<Integer> largeWithoutAll2 = largeWithoutAll.newWithoutAll(Interval.fromTo(101, 150));
        Assert.assertEquals(UnifiedSet.newSet(Interval.fromTo(151, 200)), largeWithoutAll2);
        ImmutableSet<Integer> largeWithoutAll3 = largeWithoutAll2.newWithoutAll(UnifiedSet.newSet(Interval.fromTo(151, 199)));
        Assert.assertEquals(UnifiedSet.newSetWith(200), largeWithoutAll3);
    }

    @Test
    public void newSetWith()
    {
        ImmutableSet<Integer> set = this.newSetWith(1);
        Assert.assertTrue(set.notEmpty());
        Assert.assertEquals(1, set.size());
        Assert.assertTrue(set.contains(1));
    }

    @Test
    public void newListWithVarArgs()
    {
        ImmutableSet<Integer> set = this.newSetWith(1, 2, 3, 4);
        Assert.assertTrue(set.notEmpty());
        Assert.assertEquals(4, set.size());
        Assert.assertTrue(set.containsAllArguments(1, 2, 3, 4));
        Assert.assertTrue(set.containsAllIterable(Interval.oneTo(4)));
    }

    @Test
    public void tap()
    {
        MutableList<Integer> tapResult = Lists.mutable.of();
        ImmutableSet<Integer> set = this.newSetWith(1, 2, 3, 4);
        Assert.assertSame(set, set.tap(tapResult::add));
        Assert.assertEquals(set.toList(), tapResult);
    }

    @Test
    public void forEach()
    {
        MutableList<Integer> result = Lists.mutable.of();
        ImmutableSet<Integer> set = this.newSetWith(1, 2, 3, 4);
        set.forEach(CollectionAddProcedure.on(result));
        Verify.assertSize(4, result);
        Verify.assertContainsAll(result, 1, 2, 3, 4);
    }

    @Test
    public void forEachWithIndex()
    {
        MutableList<Integer> result = Lists.mutable.of();
        ImmutableSet<Integer> set = this.newSetWith(1, 2, 3, 4);
        set.forEachWithIndex((object, index) -> result.add(object + index));
        Verify.assertContainsAll(result, 1, 3, 5, 7);
    }

    @Test
    public void select()
    {
        Assert.assertTrue(this.newSetWith(1, 2, 3, 4, 5).select(Predicates.lessThan(3)).containsAllArguments(1, 2));
        Assert.assertFalse(this.newSetWith(-1, 2, 3, 4, 5).select(Predicates.lessThan(3)).containsAllArguments(3, 4, 5));
    }

    @Test
    public void reject()
    {
        Assert.assertTrue(this.newSetWith(1, 2, 3, 4).reject(Predicates.lessThan(3)).containsAllArguments(3, 4));
    }

    @Test
    public void collect()
    {
        Assert.assertTrue(
                this.newSetWith(1, 2, 3, 4).collect(String::valueOf).containsAllArguments("1", "2", "3", "4"));
    }

    @Test
    public void detect()
    {
        Assert.assertEquals(Integer.valueOf(3), this.newSetWith(1, 2, 3, 4, 5).detect(Integer.valueOf(3)::equals));
        Assert.assertNull(this.newSetWith(1, 2, 3, 4, 5).detect(Integer.valueOf(6)::equals));
    }

    @Test
    public void detectIfNone()
    {
        Function0<Integer> function = new PassThruFunction0<>(6);
        Assert.assertEquals(Integer.valueOf(3), this.newSetWith(1, 2, 3, 4, 5).detectIfNone(Integer.valueOf(3)::equals, function));
        Assert.assertEquals(Integer.valueOf(6), this.newSetWith(1, 2, 3, 4, 5).detectIfNone(Integer.valueOf(6)::equals, function));
    }

    @Test
    public void allSatisfy()
    {
        Assert.assertTrue(this.newSetWith(1, 2, 3).allSatisfy(Integer.class::isInstance));
        Assert.assertFalse(this.newSetWith(1, 2, 3).allSatisfy(Integer.valueOf(1)::equals));
    }

    @Test
    public void anySatisfy()
    {
        Assert.assertFalse(this.newSetWith(1, 2, 3).anySatisfy(String.class::isInstance));
        Assert.assertTrue(this.newSetWith(1, 2, 3).anySatisfy(Integer.class::isInstance));
    }

    @Test
    public void noneSatisfy()
    {
        Assert.assertTrue(this.newSetWith(1, 2, 3).noneSatisfy(String.class::isInstance));
        Assert.assertTrue(this.newSetWith(1, 2, 3).noneSatisfy(Integer.valueOf(100)::equals));
        Assert.assertFalse(this.newSetWith(1, 2, 3).noneSatisfy(Integer.valueOf(1)::equals));
    }

    @Test
    public void count()
    {
        Assert.assertEquals(3, this.newSetWith(1, 2, 3).count(Integer.class::isInstance));
    }

    @Test
    public void collectIf()
    {
        Assert.assertTrue(this.newSetWith(1, 2, 3).collectIf(
                Integer.class::isInstance,
                String::valueOf).containsAllArguments("1", "2", "3"));
    }

    @Test
    public void getFirst()
    {
        Assert.assertEquals(Integer.valueOf(1), this.newSetWith(1, 2, 3).getFirst());
        Assert.assertNotEquals(Integer.valueOf(3), this.newSetWith(1, 2, 3).getFirst());
    }

    @Test
    public void getLast()
    {
        Assert.assertNotNull(this.newSetWith(1, 2, 3).getLast());
    }

    @Test
    public void getOnly()
    {
        Assert.assertEquals(Integer.valueOf(1), this.newSetWith(1).getOnly());
    }

    @Test(expected = IllegalStateException.class)
    public void getOnly_throws_when_empty()
    {
        this.newSet().getOnly();
    }

    @Test(expected = IllegalStateException.class)
    public void getOnly_throws_when_multiple_values()
    {
        this.newSetWith(1, 2, 3).getOnly();
    }

    @Test
    public void isEmpty()
    {
        Assert.assertTrue(this.newSet().isEmpty());
        Assert.assertTrue(this.newSetWith(1, 2).notEmpty());
    }

    @Test
    public void iterator()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        Iterator<Integer> iterator = objects.iterator();
        for (int i = objects.size(); i-- > 0; )
        {
            Integer integer = iterator.next();
            Assert.assertEquals(3, integer.intValue() + i);
        }
    }

    @Test
    public void injectInto()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        Integer result = objects.injectInto(1, AddFunction.INTEGER);
        Assert.assertEquals(Integer.valueOf(7), result);
    }

    @Test
    public void injectIntoInt()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        int result = objects.injectInto(1, AddFunction.INTEGER_TO_INT);
        Assert.assertEquals(7, result);
    }

    @Test
    public void injectIntoLong()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        long result = objects.injectInto(1, AddFunction.INTEGER_TO_LONG);
        Assert.assertEquals(7, result);
    }

    @Test
    public void injectIntoDouble()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        double result = objects.injectInto(1, AddFunction.INTEGER_TO_DOUBLE);
        Assert.assertEquals(7.0d, result, 0.001);
    }

    @Test
    public void injectIntoFloat()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        float result = objects.injectInto(1, AddFunction.INTEGER_TO_FLOAT);
        Assert.assertEquals(7.0d, result, 0.001);
    }

    @Test
    public void sumFloat()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        double actual = objects.sumOfFloat(Integer::floatValue);
        Assert.assertEquals(6.0f, actual, 0.001);
    }

    @Test
    public void sumDouble()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        double actual = objects.sumOfDouble(Integer::doubleValue);
        Assert.assertEquals(6.0d, actual, 0.001);
    }

    @Test
    public void sumInteger()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        long actual = objects.sumOfInt(integer -> integer);
        Assert.assertEquals(6, actual);
    }

    @Test
    public void sumLong()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        long actual = objects.sumOfLong(Integer::longValue);
        Assert.assertEquals(6, actual);
    }

    @Test
    public void toArray()
    {
        ImmutableSet<Integer> objects = this.newSetWith(1, 2, 3);
        Object[] array = objects.toArray();
        Verify.assertSize(3, array);
        Integer[] array2 = objects.toArray(new Integer[3]);
        Verify.assertSize(3, array2);
    }

    @Test
    public void equalsAndHashCode()
    {
        ImmutableSet<Integer> set1 = this.newSetWith(1, 2, 3, 4);
        ImmutableSet<Integer> set2 = this.newSetWith(1, 2, 3, 4);
        ImmutableSet<Integer> set3 = this.newSetWith(2, 3, 4);
        Assert.assertNotEquals(set1, null);
        Verify.assertEqualsAndHashCode(set1, set1);
        Verify.assertEqualsAndHashCode(set1, set2);
        Assert.assertNotEquals(set2, set3);
        UnifiedSet<Integer> fastSet = UnifiedSet.newSet(set1);
        Verify.assertEqualsAndHashCode(set1, fastSet);
        Assert.assertEquals(set1, new HashSet<>(fastSet));
        Verify.assertEqualsAndHashCode(set1, UnifiedSet.newSetWith(1, 2, 3, 4));
    }

    @Test
    public void forEachWith()
    {
        MutableList<Integer> result = Lists.mutable.of();
        ImmutableSet<Integer> collection = this.newSetWith(1, 2, 3, 4);
        collection.forEachWith((argument1, argument2) -> result.add(argument1 + argument2), 0);
        Verify.assertSize(4, result);
        Verify.assertContainsAll(result, 1, 2, 3, 4);
    }

    @Test
    public void toList()
    {
        ImmutableSet<Integer> integers = this.newSetWith(1, 2, 3, 4);
        MutableList<Integer> list = integers.toList();
        Assert.assertTrue(list.containsAllArguments(1, 2, 3, 4));
    }

    @Test
    public void toSortedList()
    {
        ImmutableSet<Integer> integers = this.newSetWith(2, 4, 1, 3);
        MutableList<Integer> list = integers.toSortedList(Collections.reverseOrder());
        Assert.assertEquals(FastList.newListWith(4, 3, 2, 1), list);
    }

    @Test
    public void toSortedListBy()
    {
        ImmutableSet<Integer> integers = this.newSetWith(2, 4, 1, 3);
        MutableList<Integer> list = integers.toSortedListBy(String::valueOf);
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), list);
    }

    @Test
    public void asSortedSet()
    {
        ImmutableSet<Integer> integers = this.newSetWith(2, 3, 1, 4);
        MutableSortedSet<Integer> set = integers.toSortedSet();
        Verify.assertSortedSetsEqual(TreeSortedSet.newSet(integers), set);
    }

    @Test
    public void toSortedSet_with_comparator()
    {
        ImmutableSet<Integer> integers = this.newSetWith(2, 4, 1, 3);
        MutableSortedSet<Integer> set = integers.toSortedSet(Collections.reverseOrder());
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(Collections.reverseOrder(), 1, 2, 3, 4), set);
    }

    @Test
    public void toSortedSetBy()
    {
        ImmutableSet<Integer> integers = this.newSetWith(4, 1, 3, 2);
        MutableSortedSet<Integer> set = integers.toSortedSetBy(String::valueOf);
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(1, 2, 3, 4), set);
    }

    @Test
    public void toSet()
    {
        ImmutableSet<Integer> integers = this.newSetWith(1, 2, 3, 4);
        MutableSet<Integer> set = integers.toSet();
        Verify.assertContainsAll(set, 1, 2, 3, 4);
    }

    @Test
    public void toMap()
    {
        ImmutableSet<Integer> integers = this.newSetWith(1, 2, 3, 4);
        MutableMap<String, String> map =
                integers.toMap(String::valueOf, String::valueOf);
        Assert.assertEquals(UnifiedMap.newWithKeysValues("1", "1", "2", "2", "3", "3", "4", "4"), map);
    }

    @Test
    public void serialization()
    {
        ImmutableSet<Integer> set = this.newSetWith(1, 2, 3, 4, 5);
        ImmutableSet<Integer> deserializedCollection = SerializeTestHelper.serializeDeserialize(set);
        Assert.assertEquals(5, deserializedCollection.size());
        Assert.assertTrue(deserializedCollection.containsAllArguments(1, 2, 3, 4, 5));
        Verify.assertEqualsAndHashCode(set, deserializedCollection);
    }

    @Test
    public void testToString()
    {
        Assert.assertEquals("[1]", this.newSetWith(1).toString());
    }

    @Test
    public void powerSet()
    {
        ImmutableSet<Integer> set = this.newSetWith(1, 2, 3);
        ImmutableSet<UnsortedSetIterable<Integer>> powerSet = set.powerSet();
        Verify.assertSize(8, powerSet);
        Verify.assertContains(set, powerSet);
        Verify.assertContains(Sets.immutable.<Integer>of(), powerSet);
        Verify.assertInstanceOf(ImmutableSet.class, powerSet.getFirst());
        Verify.assertInstanceOf(ImmutableSet.class, powerSet.getLast());
    }

    @Test
    public void groupByUniqueKey()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 1, 2, 2, 3, 3), this.newSetWith(1, 2, 3).groupByUniqueKey(id -> id));
    }

    @Test(expected = IllegalStateException.class)
    public void groupByUniqueKey_throws()
    {
        this.newSetWith(1, 2, 3).groupByUniqueKey(Functions.getFixedValue(1));
    }

    @Test
    public void groupByUniqueKey_target()
    {
        MutableMap<Integer, Integer> integers = this.newSetWith(1, 2, 3).groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(0, 0));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(0, 0, 1, 1, 2, 2, 3, 3), integers);
    }

    @Test(expected = IllegalStateException.class)
    public void groupByUniqueKey_target_throws()
    {
        this.newSetWith(1, 2, 3).groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(2, 2));
    }
}
