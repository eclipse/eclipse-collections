/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.ThrowingAppendable;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.MutableInteger;
import org.eclipse.collections.impl.math.MutableLong;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

public class IntervalTest
{
    @Test
    public void into()
    {
        int sum = Interval.oneTo(5)
                .select(Predicates.lessThan(5))
                .into(FastList.newList())
                .injectInto(0, AddFunction.INTEGER_TO_INT);
        Assert.assertEquals(10, sum);
    }

    @Test
    public void fromAndToAndBy()
    {
        Interval interval = Interval.from(1);
        Interval interval2 = interval.to(10);
        Interval interval3 = interval2.by(2);
        Verify.assertEqualsAndHashCode(interval, Interval.fromTo(1, 1));
        Verify.assertEqualsAndHashCode(interval2, Interval.fromTo(1, 10));
        Verify.assertEqualsAndHashCode(interval3, Interval.fromToBy(1, 10, 2));
        Verify.assertSize(Integer.MAX_VALUE, Interval.fromTo(Integer.MIN_VALUE + 1, -1));
        Verify.assertSize(Integer.MAX_VALUE, Interval.fromTo(1, Integer.MAX_VALUE));

        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromTo(Integer.MIN_VALUE, Integer.MAX_VALUE));
        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromTo(-1, Integer.MAX_VALUE));
        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromToBy(Integer.MIN_VALUE, Integer.MAX_VALUE, 2));
        Verify.assertSize(Interval.fromTo(Integer.MIN_VALUE + 1, -1).size(), Interval.oneTo(Integer.MAX_VALUE));

        Assert.assertEquals(Lists.mutable.with(0), Interval.fromToBy(0, 2, 3));
        Assert.assertEquals(Lists.mutable.with(0), Interval.fromToBy(0, -2, -3));
        Assert.assertEquals(Lists.mutable.with(1_000_000_000), Interval.fromToBy(1_000_000_000, 2_000_000_000, 1_500_000_000));
        Assert.assertEquals(Lists.mutable.with(-1_000_000_000), Interval.fromToBy(-1_000_000_000, -2_000_000_000, -1_500_000_000));
        Assert.assertEquals(Lists.mutable.with(Integer.MIN_VALUE), Interval.fromToBy(Integer.MIN_VALUE, Integer.MIN_VALUE + 10, 20));
        Assert.assertEquals(Lists.mutable.with(Integer.MAX_VALUE), Interval.fromToBy(Integer.MAX_VALUE, Integer.MAX_VALUE - 10, -20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromToBy_throws_step_size_zero()
    {
        Interval.fromToBy(0, 0, 0);
    }

    @Test
    public void fromToBy_with_same_start_and_end_with_negative_step()
    {
        MutableList<Integer> integers = Interval.fromToBy(2, 2, -2).toList();

        Verify.assertSize(1, integers);
        Verify.assertContains(2, integers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneToBy_throws_step_size_zero()
    {
        Interval.oneToBy(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroToBy_throws_step_size_zero()
    {
        Interval.zeroToBy(0, 0);
    }

    @Test
    public void equalsAndHashCode()
    {
        Interval interval1 = Interval.oneTo(5);
        Interval interval2 = Interval.oneTo(5);
        Interval interval3 = Interval.zeroTo(5);
        Verify.assertPostSerializedEqualsAndHashCode(interval1);
        Verify.assertEqualsAndHashCode(interval1, interval2);
        Assert.assertNotEquals(interval1, interval3);
        Assert.assertNotEquals(interval3, interval1);

        Verify.assertEqualsAndHashCode(Interval.fromToBy(1, 5, 2), Interval.fromToBy(1, 6, 2));
        Verify.assertEqualsAndHashCode(FastList.newListWith(1, 2, 3), Interval.fromTo(1, 3));
        Verify.assertEqualsAndHashCode(FastList.newListWith(3, 2, 1), Interval.fromTo(3, 1));

        Assert.assertNotEquals(FastList.newListWith(1, 2, 3, 4), Interval.fromTo(1, 3));
        Assert.assertNotEquals(FastList.newListWith(1, 2, 4), Interval.fromTo(1, 3));
        Assert.assertNotEquals(FastList.newListWith(3, 2, 0), Interval.fromTo(3, 1));

        Verify.assertEqualsAndHashCode(FastList.newListWith(-1, -2, -3), Interval.fromTo(-1, -3));
    }

    @Test
    public void forEachOnFromToInterval()
    {
        MutableList<Integer> result = Lists.mutable.of();
        Interval interval = Interval.oneTo(5);
        interval.forEach(CollectionAddProcedure.on(result));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), result);
    }

    @Test
    public void forEachWithExecutor()
    {
        MutableList<Integer> result = Lists.mutable.of();
        Interval interval = Interval.oneTo(5);
        interval.forEach(CollectionAddProcedure.on(result), Executors.newSingleThreadExecutor());
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), result);
    }

    @Test
    public void forEachWithExecutorInReverse()
    {
        MutableList<Integer> result = Lists.mutable.of();
        Interval interval = Interval.fromToBy(5, 1, -1);
        interval.forEach(CollectionAddProcedure.on(result), Executors.newSingleThreadExecutor());
        Assert.assertEquals(FastList.newListWith(5, 4, 3, 2, 1), result);
    }

    @Test
    public void runWithExecutor() throws InterruptedException
    {
        MutableList<String> result = Lists.mutable.of();
        ExecutorService service = Executors.newSingleThreadExecutor();
        Interval.oneTo(3).run(() -> result.add(null), service);
        service.shutdown();
        service.awaitTermination(20, TimeUnit.SECONDS);
        Assert.assertEquals(FastList.<String>newListWith(null, null, null), result);
    }

    @Test
    public void runWithExecutorInReverse() throws InterruptedException
    {
        MutableList<String> result = Lists.mutable.of();
        ExecutorService service = Executors.newSingleThreadExecutor();
        Interval.fromTo(3, 1).run(() -> result.add(null), service);
        service.shutdown();
        service.awaitTermination(20, TimeUnit.SECONDS);
        Assert.assertEquals(FastList.<String>newListWith(null, null, null), result);
    }

    @Test
    public void reverseForEachOnFromToInterval()
    {
        List<Integer> result = new ArrayList<>();
        Interval interval = Interval.oneTo(5);
        interval.reverseForEach(result::add);
        Verify.assertSize(5, result);
        Verify.assertContains(1, result);
        Verify.assertContains(5, result);
        Assert.assertEquals(Integer.valueOf(5), Iterate.getFirst(result));
        Assert.assertEquals(Integer.valueOf(1), Iterate.getLast(result));

        result.clear();
        interval.reverseThis().reverseForEach(result::add);
        Verify.assertSize(5, result);
        Verify.assertContains(1, result);
        Verify.assertContains(5, result);
        Assert.assertEquals(Integer.valueOf(1), Iterate.getFirst(result));
        Assert.assertEquals(Integer.valueOf(5), Iterate.getLast(result));
    }

    @Test
    public void forEachOnFromToByInterval()
    {
        List<Integer> result = new ArrayList<>();
        Interval interval = Interval.fromToBy(1, 5, 2);
        interval.forEach(CollectionAddProcedure.on(result));
        Verify.assertSize(3, result);
        Verify.assertContains(1, result);
        Verify.assertNotContains(2, result);
        Verify.assertContains(5, result);
    }

    @Test
    public void forEachOnFromToByInterval2()
    {
        List<Integer> result = new ArrayList<>();
        Interval interval = Interval.fromToBy(5, 1, -2);
        interval.forEach(CollectionAddProcedure.on(result));
        Verify.assertSize(3, result);
        Verify.assertContains(1, result);
        Verify.assertNotContains(2, result);
        Verify.assertContains(5, result);
    }

    @Test
    public void injectIntoOnFromToByInterval()
    {
        Interval interval = Interval.oneTo(5);
        Assert.assertEquals(Integer.valueOf(20), interval.injectInto(5, AddFunction.INTEGER));
        Assert.assertEquals(Integer.valueOf(20), interval.reverseThis().injectInto(5, AddFunction.INTEGER));
    }

    @Test
    public void injectIntoOnFromToBySameStartEndNegativeStepInterval()
    {
        Interval interval = Interval.fromToBy(2, 2, -2);

        Assert.assertEquals(Integer.valueOf(0), interval.injectInto(-2, AddFunction.INTEGER));
        Assert.assertEquals(Integer.valueOf(0), interval.reverseThis().injectInto(-2, AddFunction.INTEGER));
    }

    @Test
    public void sumInterval()
    {
        int sum = Interval.oneTo(5).injectInto(0, AddFunction.INTEGER_TO_INT);
        Assert.assertEquals(15, sum);
    }

    @Test
    public void maxInterval()
    {
        Integer value = Interval.oneTo(5).injectInto(0, Integer::max);
        Assert.assertEquals(5, value.intValue());
    }

    @Test
    public void reverseInjectIntoOnFromToByInterval()
    {
        Interval interval = Interval.oneTo(5);
        Assert.assertEquals(Integer.valueOf(20), interval.reverseInjectInto(5, AddFunction.INTEGER));
        Assert.assertEquals(Integer.valueOf(20), interval.reverseThis().reverseInjectInto(5, AddFunction.INTEGER));
    }

    @Test
    public void collectOnFromToByInterval()
    {
        Interval interval = Interval.oneToBy(5, 2);
        LazyIterable<String> result = interval.collect(String::valueOf);
        Verify.assertIterableSize(3, result);
        Verify.assertContainsAll(result, "1", "5");
        Verify.assertNotContains("2", result);
    }

    @Test
    public void collectOnFromToInterval()
    {
        Interval interval = Interval.oneTo(5);
        LazyIterable<String> result = interval.collect(String::valueOf);
        Verify.assertIterableSize(5, result);
        Verify.assertContainsAll(result, "1", "5");
    }

    @Test
    public void selectOnFromToInterval()
    {
        Interval interval = Interval.oneTo(5);
        Assert.assertEquals(FastList.newListWith(2, 4), interval.select(IntegerPredicates.isEven()).toList());
        Assert.assertEquals(FastList.newListWith(4, 2), interval.reverseThis().select(IntegerPredicates.isEven()).toList());
    }

    @Test
    public void rejectOnFromToInterval()
    {
        Interval interval = Interval.oneTo(5);
        Assert.assertEquals(FastList.newListWith(1, 3, 5), interval.reject(IntegerPredicates.isEven()).toList());
        Assert.assertEquals(FastList.newListWith(5, 3, 1), interval.reverseThis().reject(IntegerPredicates.isEven()).toList());
    }

    @Test
    public void reverseThis()
    {
        Interval interval = Interval.fromToBy(5, 1, -1);
        Interval interval2 = interval.reverseThis();
        List<Integer> result = new ArrayList<>();
        interval2.forEach(CollectionAddProcedure.on(result));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), result);
    }

    @Test
    public void intervalAsArray()
    {
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, Interval.toArray(1, 5));
    }

    @Test
    public void intervalAsIntArray()
    {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Interval.fromTo(1, 5).toIntArray());
    }

    @Test
    public void intervalAsReverseArray()
    {
        Integer[] array = Interval.toReverseArray(1, 5);
        Verify.assertSize(5, array);
        Assert.assertTrue(ArrayIterate.contains(array, 1));
        Assert.assertTrue(ArrayIterate.contains(array, 5));
        Assert.assertEquals(ArrayIterate.getFirst(array), Integer.valueOf(5));
        Assert.assertEquals(ArrayIterate.getLast(array), Integer.valueOf(1));
    }

    @Test
    public void intervalToList()
    {
        MutableList<Integer> list = Interval.fromTo(1, 5).toList();
        Verify.assertSize(5, list);
        Verify.assertContainsAll(list, 1, 2, 3, 4, 5);
    }

    @Test
    public void intervalAsReverseList()
    {
        MutableList<Integer> list = Interval.toReverseList(1, 5);
        Verify.assertSize(5, list);
        Verify.assertStartsWith(list, 5, 4, 3, 2, 1);
    }

    @Test
    public void intervalToSet()
    {
        MutableSet<Integer> set = Interval.toSet(1, 5);
        Verify.assertSize(5, set);
        Verify.assertContainsAll(set, 1, 2, 3, 4, 5);
    }

    @Test
    public void invalidIntervals()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromToBy(5, 1, 2));
        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromToBy(5, 1, 0));
        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromToBy(-5, 1, -1));
    }

    @Test
    public void toFastList()
    {
        Interval interval = Interval.evensFromTo(0, 10);
        FastList<Integer> toList = (FastList<Integer>) interval.toList();
        Verify.assertStartsWith(toList, 0, 2, 4, 6, 8, 10);
        Verify.assertSize(6, toList);
    }

    @Test
    public void toSet()
    {
        Interval interval = Interval.evensFromTo(0, 10);
        MutableSet<Integer> set = interval.toSet();
        Verify.assertContainsAll(set, 0, 2, 4, 6, 8, 10);
        Verify.assertSize(6, set);
    }

    @Test
    public void testToString()
    {
        Interval interval = Interval.evensFromTo(0, 10);
        Assert.assertEquals("Interval from: 0 to: 10 step: 2 size: 6", interval.toString());
    }

    @Test
    public void evens()
    {
        Interval interval = Interval.evensFromTo(0, 10);
        int[] evens = {0, 2, 4, 6, 8, 10};
        int[] odds = {1, 3, 5, 7, 9};
        this.assertIntervalContainsAll(interval, evens);
        this.denyIntervalContainsAny(interval, odds);
        Verify.assertSize(6, interval);

        Interval reverseInterval = Interval.evensFromTo(10, 0);
        this.assertIntervalContainsAll(reverseInterval, evens);
        this.denyIntervalContainsAny(reverseInterval, odds);
        Verify.assertSize(6, reverseInterval);

        Interval negativeInterval = Interval.evensFromTo(-5, 5);
        int[] negativeEvens = {-4, -2, 0, 2, 4};
        int[] negativeOdds = {-3, -1, 1, 3};
        this.assertIntervalContainsAll(negativeInterval, negativeEvens);
        this.denyIntervalContainsAny(negativeInterval, negativeOdds);
        Verify.assertSize(5, negativeInterval);

        Interval reverseNegativeInterval = Interval.evensFromTo(5, -5);
        this.assertIntervalContainsAll(reverseNegativeInterval, negativeEvens);
        this.denyIntervalContainsAny(reverseNegativeInterval, negativeOdds);
        Verify.assertSize(5, reverseNegativeInterval);
    }

    private void assertIntervalContainsAll(Interval interval, int[] expectedValues)
    {
        for (int value : expectedValues)
        {
            Verify.assertContains(value, interval);
        }
    }

    private void denyIntervalContainsAny(Interval interval, int[] expectedValues)
    {
        for (int value : expectedValues)
        {
            Verify.assertNotContains(value, interval);
        }
    }

    @Test
    public void odds()
    {
        Interval interval1 = Interval.oddsFromTo(0, 10);
        Assert.assertTrue(interval1.containsAll(1, 3, 5, 7, 9));
        Assert.assertTrue(interval1.containsNone(2, 4, 6, 8));
        Verify.assertSize(5, interval1);

        Interval reverseInterval1 = Interval.oddsFromTo(10, 0);
        Assert.assertTrue(reverseInterval1.containsAll(1, 3, 5, 7, 9));
        Assert.assertTrue(reverseInterval1.containsNone(0, 2, 4, 6, 8, 10));
        Verify.assertSize(5, reverseInterval1);

        Interval interval2 = Interval.oddsFromTo(-5, 5);
        Assert.assertTrue(interval2.containsAll(-5, -3, -1, 1, 3, 5));
        Assert.assertTrue(interval2.containsNone(-4, -2, 0, 2, 4));
        Verify.assertSize(6, interval2);

        Interval reverseInterval2 = Interval.oddsFromTo(5, -5);
        Assert.assertTrue(reverseInterval2.containsAll(-5, -3, -1, 1, 3, 5));
        Assert.assertTrue(reverseInterval2.containsNone(-4, -2, 0, 2, 4));
        Verify.assertSize(6, reverseInterval2);
    }

    @Test
    public void size()
    {
        Verify.assertSize(100, Interval.fromTo(1, 100));
        Verify.assertSize(50, Interval.fromToBy(1, 100, 2));
        Verify.assertSize(34, Interval.fromToBy(1, 100, 3));
        Verify.assertSize(25, Interval.fromToBy(1, 100, 4));
        Verify.assertSize(20, Interval.fromToBy(1, 100, 5));
        Verify.assertSize(17, Interval.fromToBy(1, 100, 6));
        Verify.assertSize(15, Interval.fromToBy(1, 100, 7));
        Verify.assertSize(13, Interval.fromToBy(1, 100, 8));
        Verify.assertSize(12, Interval.fromToBy(1, 100, 9));
        Verify.assertSize(10, Interval.fromToBy(1, 100, 10));
        Verify.assertSize(11, Interval.fromTo(0, 10));
        Verify.assertSize(1, Interval.zero());
        Verify.assertSize(11, Interval.fromTo(0, -10));
        Verify.assertSize(3, Interval.evensFromTo(2, -2));
        Verify.assertSize(2, Interval.oddsFromTo(2, -2));

        // Positive Ranges
        Verify.assertSize(10, Interval.zeroTo(9));
        Verify.assertSize(2_000_000_000, Interval.oneTo(2_000_000_000));
        Verify.assertSize(200_000_000, Interval.oneTo(2_000_000_000).by(10));
        Verify.assertSize(2_000_000_000, Interval.fromTo(2_000_000_000, 1).by(-1));
        Verify.assertSize(500_000_000, Interval.oneTo(2_000_000_000).by(4));
        Verify.assertSize(222_222_223, Interval.oneTo(2_000_000_000).by(9));
        // Negative Ranges
        Verify.assertSize(10, Interval.fromTo(0, -9));
        Verify.assertSize(2_000_000_000, Interval.fromTo(-1, -2_000_000_000));
        Verify.assertSize(200_000_000, Interval.fromTo(-1, -2_000_000_000).by(-10));
        Verify.assertSize(2_000_000_000, Interval.fromTo(-2_000_000_000, -1).by(1));
        Verify.assertSize(500_000_000, Interval.fromTo(-1, -2_000_000_000).by(-4));
        Verify.assertSize(222_222_223, Interval.fromTo(-1, -2_000_000_000).by(-9));
        // Overlapping Ranges
        Verify.assertSize(21, Interval.fromTo(10, -10));
        Verify.assertSize(5, Interval.fromTo(10, -10).by(-5));
        Verify.assertSize(5, Interval.fromTo(-10, 10).by(5));
        Verify.assertSize(2_000_000_001, Interval.fromTo(1_000_000_000, -1_000_000_000));
        Verify.assertSize(200_000_001, Interval.fromTo(1_000_000_000, -1_000_000_000).by(-10));
        Verify.assertSize(1, Interval.fromToBy(1_000_000_000, 2_000_000_000, 1_500_000_000));
        Verify.assertSize(1, Interval.fromToBy(-1_000_000_000, -2_000_000_000, -1_500_000_000));
    }

    @Test
    public void contains()
    {
        Verify.assertContains(0, Interval.zero());
        Assert.assertTrue(Interval.oneTo(5).containsAll(1, 5));
        Assert.assertTrue(Interval.oneTo(5).containsNone(6, 7));
        Assert.assertFalse(Interval.oneTo(5).containsAll(1, 6));
        Assert.assertFalse(Interval.oneTo(5).containsNone(1, 6));
        Verify.assertNotContains(0, Interval.oneTo(5));
        Assert.assertTrue(Interval.fromTo(-1, -5).containsAll(-1, -5));
        Verify.assertNotContains(1, Interval.fromTo(-1, -5));

        Verify.assertContains(Integer.valueOf(0), Interval.zero());
        Verify.assertNotContains(Integer.valueOf(0), Interval.oneTo(5));
        Verify.assertNotContains(Integer.valueOf(1), Interval.fromTo(-1, -5));

        Verify.assertNotContains(new Object(), Interval.zeroTo(5));

        Interval bigInterval = Interval.fromToBy(Integer.MIN_VALUE, Integer.MAX_VALUE, 1_000_000);
        Assert.assertTrue(bigInterval.contains(Integer.MIN_VALUE + 1_000_000));
        Assert.assertFalse(bigInterval.contains(Integer.MIN_VALUE + 1_000_001));
        Assert.assertTrue(bigInterval.contains(Integer.MIN_VALUE + (1_000_000 * 10)));
        Assert.assertFalse(bigInterval.contains(Integer.MIN_VALUE + (1_000_001 * 10)));
        Assert.assertTrue(bigInterval.contains(Integer.MIN_VALUE + (1_000_000 * 100)));
        Assert.assertFalse(bigInterval.contains(Integer.MIN_VALUE + (1_000_001 * 100)));
        Assert.assertTrue(
                Interval.fromToBy(1_000_000_000, 2_000_000_000, 1_500_000_000)
                        .contains(1_000_000_000));
        Assert.assertTrue(
                Interval.fromToBy(-1_000_000_000, -2_000_000_000, -1_500_000_000)
                        .contains(-1_000_000_000));

        int minValue = -1_000_000_000;
        int maxValue = 1_000_000_000;
        Interval largeInterval = Interval.fromToBy(minValue, maxValue, 10);

        Assert.assertTrue(largeInterval.containsAll(
                maxValue - 10,
                maxValue - 100,
                maxValue - 1000,
                maxValue - 10000));
        Assert.assertTrue(largeInterval.contains(minValue + 10));
    }

    @Test
    public void largeReverseUnderflowTest()
    {
        Interval reverse = Interval.fromToBy(Integer.MAX_VALUE, Integer.MIN_VALUE + 10, -10);
        Assert.assertFalse(reverse.contains(Integer.MIN_VALUE + 10));
        Assert.assertEquals(Integer.valueOf(Integer.MAX_VALUE), reverse.getFirst());
        Integer expectedLast = Integer.valueOf(-2_147_483_633);
        Assert.assertEquals(expectedLast, reverse.getLast());
        Assert.assertTrue(reverse.contains(Integer.MAX_VALUE));
        Assert.assertTrue(reverse.contains(7));
        Assert.assertTrue(reverse.contains(-3));
        Assert.assertTrue(reverse.contains(expectedLast));
        Assert.assertTrue(reverse.contains(expectedLast + 1000));
        Assert.assertEquals(214_748_364, reverse.indexOf(Integer.valueOf(7)));
        Assert.assertEquals(214_748_365, reverse.indexOf(Integer.valueOf(-3)));
        Assert.assertEquals(429_496_728, reverse.indexOf(expectedLast));
        Assert.assertEquals(429_496_728, reverse.lastIndexOf(expectedLast));
        Integer expectedAtIndex300Million = Integer.valueOf(-852_516_353);
        Assert.assertTrue(reverse.contains(expectedAtIndex300Million));
        Assert.assertEquals(300_000_000, reverse.indexOf(expectedAtIndex300Million));
        Assert.assertEquals(300_000_000, reverse.lastIndexOf(expectedAtIndex300Million));
        Integer expectedAtIndex400Million = Integer.valueOf(-1_852_516_353);
        Assert.assertTrue(reverse.contains(expectedAtIndex400Million));
        Assert.assertEquals(400_000_000, reverse.indexOf(expectedAtIndex400Million));
        Assert.assertEquals(400_000_000, reverse.lastIndexOf(expectedAtIndex400Million));
    }

    @Test
    public void forwardOverflowTest()
    {
        Integer from = Integer.valueOf(Integer.MAX_VALUE - 10);
        Integer second = Integer.valueOf(Integer.MAX_VALUE - 2);
        long expected = from.longValue() + second.longValue();
        Interval interval = Interval.fromToBy(from, Integer.MAX_VALUE, 8);
        Verify.assertSize(2, interval);
        Assert.assertEquals(Lists.mutable.with(from, second), interval);
        Assert.assertEquals(1, interval.countWith(Objects::equals, second));
        Assert.assertEquals(expected, interval.sumOfLong(Integer::longValue));
        MutableLong result = new MutableLong();
        interval.each(each -> result.add(each.longValue()));
        Assert.assertEquals(expected, result.longValue());
        result.clear();
        Assert.assertEquals(
                expected,
                interval.injectInto(new MutableLong(), MutableLong::add).longValue());
        Assert.assertEquals(
                2,
                interval.injectInto(0, (int value, Integer each) -> value + 1));
        Assert.assertEquals(
                2L,
                interval.injectInto(0L, (long value, Integer each) -> value + 1));
        Assert.assertEquals(
                2.0,
                interval.injectInto(0.0, (double value, Integer each) -> value + 1.0),
                0.0);
        Assert.assertEquals(
                2.0f,
                interval.injectInto(0.0f, (float value, Integer each) -> value + 1.0f),
                0.0f);
    }

    @Test
    public void reverseOverflowTest()
    {
        Integer from = Integer.valueOf(Integer.MIN_VALUE + 10);
        Integer second = Integer.valueOf(Integer.MIN_VALUE + 2);
        long expected = (long) from + (long) second;
        Interval interval = Interval.fromToBy(from, Integer.MIN_VALUE, -8);
        Assert.assertEquals(2, interval.size());
        Assert.assertEquals(Lists.mutable.with(from, second), interval);
        Assert.assertEquals(1, interval.countWith(Objects::equals, second));
        MutableLong result = new MutableLong();
        interval.each(each -> result.add(each.longValue()));
        Assert.assertEquals(expected, result.longValue());
        Assert.assertEquals(expected, interval.injectInto(new MutableLong(), MutableLong::add).longValue());
        Assert.assertEquals(
                2,
                interval.injectInto(0, (int value, Integer each) -> value + 1));
        Assert.assertEquals(
                2L,
                interval.injectInto(0L, (long value, Integer each) -> value + 1));
        Assert.assertEquals(
                2.0,
                interval.injectInto(0.0, (double value, Integer each) -> value + 1.0),
                0.0);
        Assert.assertEquals(
                2.0f,
                interval.injectInto(0.0f, (float value, Integer each) -> value + 1.0f),
                0.0f);
    }

    @Test
    public void factorial()
    {
        Verify.assertThrows(IllegalStateException.class, () -> Interval.fromTo(-1, -5).factorial());
        Assert.assertEquals(1, Interval.zero().factorial().intValue());
        Assert.assertEquals(1, Interval.oneTo(1).factorial().intValue());
        Assert.assertEquals(6, Interval.oneTo(3).factorial().intValue());
        Assert.assertEquals(2432902008176640000L, Interval.oneTo(20).factorial().longValue());
        Assert.assertEquals(new BigInteger("51090942171709440000"), Interval.oneTo(21).factorial());
        Assert.assertEquals(new BigInteger("1405006117752879898543142606244511569936384000000000"), Interval.oneTo(42).factorial());
    }

    @Test
    public void product()
    {
        Assert.assertEquals(0, Interval.zero().product().intValue());
        Assert.assertEquals(0, Interval.fromTo(-1, 1).product().intValue());
        Assert.assertEquals(2, Interval.fromTo(-2, -1).product().intValue());
        Assert.assertEquals(-6, Interval.fromTo(-3, -1).product().intValue());
        Assert.assertEquals(200, Interval.fromToBy(10, 20, 10).product().intValue());
        Assert.assertEquals(200, Interval.fromToBy(-10, -20, -10).product().intValue());
        Assert.assertEquals(-6000, Interval.fromToBy(-10, -30, -10).product().intValue());
        Assert.assertEquals(6000, Interval.fromToBy(30, 10, -10).product().intValue());
        Assert.assertEquals(6000, Interval.fromToBy(30, 10, -10).reverseThis().product().intValue());
    }

    @Test
    public void iterator()
    {
        Interval zero = Interval.zero();
        Iterator<Integer> zeroIterator = zero.iterator();
        Assert.assertTrue(zeroIterator.hasNext());
        Assert.assertEquals(Integer.valueOf(0), zeroIterator.next());
        Assert.assertFalse(zeroIterator.hasNext());
        Interval oneToFive = Interval.oneTo(5);
        Iterator<Integer> oneToFiveIterator = oneToFive.iterator();
        for (int i = 1; i < 6; i++)
        {
            Assert.assertTrue(oneToFiveIterator.hasNext());
            Assert.assertEquals(Integer.valueOf(i), oneToFiveIterator.next());
        }
        Verify.assertThrows(NoSuchElementException.class, (Runnable) oneToFiveIterator::next);
        Interval threeToNegativeThree = Interval.fromTo(3, -3);
        Iterator<Integer> threeToNegativeThreeIterator = threeToNegativeThree.iterator();
        for (int i = 3; i > -4; i--)
        {
            Assert.assertTrue(threeToNegativeThreeIterator.hasNext());
            Assert.assertEquals(Integer.valueOf(i), threeToNegativeThreeIterator.next());
        }
        Verify.assertThrows(NoSuchElementException.class, (Runnable) threeToNegativeThreeIterator::next);
        Verify.assertThrows(UnsupportedOperationException.class, () -> Interval.zeroTo(10).iterator().remove());
    }

    @Test
    public void forEachWithIndex()
    {
        IntegerSum sum = new IntegerSum(0);
        Interval.oneTo(5).forEachWithIndex((ObjectIntProcedure<Integer>) (each, index) -> sum.add(each + index));
        Assert.assertEquals(25, sum.getIntSum());
        IntegerSum zeroSum = new IntegerSum(0);
        Interval.fromTo(0, -4).forEachWithIndex((ObjectIntProcedure<Integer>) (each, index) -> zeroSum.add(each + index));
        Assert.assertEquals(0, zeroSum.getIntSum());

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> Interval.zeroTo(10).forEachWithIndex(null, -1, 10));
    }

    @Test
    public void run()
    {
        IntegerSum sum = new IntegerSum(0);
        Interval.oneTo(5).run(() -> sum.add(1));
        Assert.assertEquals(5, sum.getIntSum());
        IntegerSum sum2 = new IntegerSum(0);
        Interval.fromTo(5, 1).run(() -> sum2.add(1));
        Assert.assertEquals(5, sum2.getIntSum());
    }

    @Test
    public void forEachWith()
    {
        IntegerSum sum = new IntegerSum(0);
        Interval.oneTo(5).forEachWith((Integer each, Integer parameter) -> sum.add(each + parameter), 0);
        Assert.assertEquals(15, sum.getIntSum());
        IntegerSum sum2 = new IntegerSum(0);
        Interval.fromTo(5, 1).forEachWith((Integer each, Integer parameter) -> sum2.add(each + parameter), 0);
        Assert.assertEquals(15, sum2.getIntSum());
    }

    @Test
    public void select()
    {
        Interval interval = Interval.fromTo(10, -10).by(-5);

        MutableList<Integer> expected = FastList.newListWith(10, 0, -10);
        Assert.assertEquals(expected, interval.select(IntegerPredicates.isEven()).toList());
        Assert.assertEquals(expected, interval.select(IntegerPredicates.isEven(), FastList.newList()));
    }

    @Test
    public void reject()
    {
        Interval interval = Interval.fromTo(10, -10).by(-5);

        MutableList<Integer> expected = FastList.newListWith(5, -5);
        Assert.assertEquals(expected, interval.reject(IntegerPredicates.isEven(), FastList.newList()));
    }

    @Test
    public void collect()
    {
        Interval interval = Interval.fromTo(10, -10).by(-5);

        MutableList<String> expected = FastList.newListWith("10", "5", "0", "-5", "-10");
        Assert.assertEquals(expected, interval.collect(String::valueOf).toList());
        Assert.assertEquals(expected, interval.collect(String::valueOf, FastList.newList()));
    }

    @Test
    public void getFirst()
    {
        Assert.assertEquals(Integer.valueOf(10), Interval.fromTo(10, -10).by(-5).getFirst());
        Assert.assertEquals(Integer.valueOf(-10), Interval.fromTo(-10, 10).by(5).getFirst());
        Assert.assertEquals(Integer.valueOf(0), Interval.zero().getFirst());
    }

    @Test
    public void getLast()
    {
        Assert.assertEquals(Integer.valueOf(-10), Interval.fromTo(10, -10).by(-5).getLast());
        Assert.assertEquals(Integer.valueOf(-10), Interval.fromTo(10, -12).by(-5).getLast());
        Assert.assertEquals(Integer.valueOf(10), Interval.fromTo(-10, 10).by(5).getLast());
        Assert.assertEquals(Integer.valueOf(10), Interval.fromTo(-10, 12).by(5).getLast());
        Assert.assertEquals(Integer.valueOf(0), Interval.zero().getLast());
    }

    @Test
    public void forEach_with_start_end()
    {
        Interval interval = Interval.fromTo(-10, 12).by(5);

        MutableList<Integer> forwardResult = Lists.mutable.of();
        interval.forEach(CollectionAddProcedure.on(forwardResult), 1, 3);
        Assert.assertEquals(FastList.newListWith(-5, 0, 5), forwardResult);

        MutableList<Integer> backwardsResult = Lists.mutable.of();
        interval.forEach(CollectionAddProcedure.on(backwardsResult), 3, 1);
        Assert.assertEquals(FastList.newListWith(5, 0, -5), backwardsResult);

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> interval.forEach(null, -1, 3));
    }

    @Test
    public void forEachWith_with_start_end()
    {
        Interval interval = Interval.fromTo(-10, 12).by(5);

        MutableList<Integer> forwardResult = Lists.mutable.of();
        interval.forEachWithIndex(new AddParametersProcedure(forwardResult), 1, 3);
        Assert.assertEquals(FastList.newListWith(-4, 2, 8), forwardResult);

        MutableList<Integer> backwardsResult = Lists.mutable.of();
        interval.forEachWithIndex(new AddParametersProcedure(backwardsResult), 3, 1);
        Assert.assertEquals(FastList.newListWith(8, 2, -4), backwardsResult);
    }

    @Test
    public void forEach_with_same_start_and_end_with_negative_step()
    {
        Interval interval = Interval.fromToBy(2, 2, -2);

        MutableInteger counter = new MutableInteger(0);
        interval.forEach((Procedure<Integer>) each -> counter.add(1));

        Assert.assertEquals(1, counter.toInteger().intValue());
    }

    @Test
    public void indexOf()
    {
        Interval interval = Interval.fromTo(-10, 12).by(5);
        Assert.assertEquals(0, interval.indexOf(-10));
        Assert.assertEquals(1, interval.indexOf(-5));
        Assert.assertEquals(2, interval.indexOf(0));
        Assert.assertEquals(3, interval.indexOf(5));
        Assert.assertEquals(4, interval.indexOf(10));

        Assert.assertEquals(-1, interval.indexOf(-15));
        Assert.assertEquals(-1, interval.indexOf(-11));
        Assert.assertEquals(-1, interval.indexOf(-9));
        Assert.assertEquals(-1, interval.indexOf(11));
        Assert.assertEquals(-1, interval.indexOf(15));

        Interval backwardsInterval = Interval.fromTo(10, -12).by(-5);
        Assert.assertEquals(0, backwardsInterval.indexOf(10));
        Assert.assertEquals(1, backwardsInterval.indexOf(5));
        Assert.assertEquals(2, backwardsInterval.indexOf(0));
        Assert.assertEquals(3, backwardsInterval.indexOf(-5));
        Assert.assertEquals(4, backwardsInterval.indexOf(-10));

        Assert.assertEquals(-1, backwardsInterval.indexOf(15));
        Assert.assertEquals(-1, backwardsInterval.indexOf(11));
        Assert.assertEquals(-1, backwardsInterval.indexOf(9));
        Assert.assertEquals(-1, backwardsInterval.indexOf(-11));
        Assert.assertEquals(-1, backwardsInterval.indexOf(-15));
    }

    @Test
    public void lastIndexOf()
    {
        Interval interval = Interval.fromTo(-10, 12).by(5);
        Assert.assertEquals(0, interval.lastIndexOf(-10));
        Assert.assertEquals(1, interval.lastIndexOf(-5));
        Assert.assertEquals(2, interval.lastIndexOf(0));
        Assert.assertEquals(3, interval.lastIndexOf(5));
        Assert.assertEquals(4, interval.lastIndexOf(10));

        Assert.assertEquals(-1, interval.lastIndexOf(-15));
        Assert.assertEquals(-1, interval.lastIndexOf(-11));
        Assert.assertEquals(-1, interval.lastIndexOf(-9));
        Assert.assertEquals(-1, interval.lastIndexOf(11));
        Assert.assertEquals(-1, interval.lastIndexOf(15));
        Assert.assertEquals(-1, interval.lastIndexOf(new Object()));

        Interval backwardsInterval = Interval.fromTo(10, -12).by(-5);
        Assert.assertEquals(0, backwardsInterval.lastIndexOf(10));
        Assert.assertEquals(1, backwardsInterval.lastIndexOf(5));
        Assert.assertEquals(2, backwardsInterval.lastIndexOf(0));
        Assert.assertEquals(3, backwardsInterval.lastIndexOf(-5));
        Assert.assertEquals(4, backwardsInterval.lastIndexOf(-10));

        Assert.assertEquals(-1, backwardsInterval.lastIndexOf(15));
        Assert.assertEquals(-1, backwardsInterval.lastIndexOf(11));
        Assert.assertEquals(-1, backwardsInterval.lastIndexOf(9));
        Assert.assertEquals(-1, backwardsInterval.lastIndexOf(-11));
        Assert.assertEquals(-1, backwardsInterval.lastIndexOf(-15));
    }

    @Test
    public void get()
    {
        Interval interval = Interval.fromTo(-10, 12).by(5);
        Verify.assertItemAtIndex(Integer.valueOf(-10), 0, interval);
        Verify.assertItemAtIndex(Integer.valueOf(-5), 1, interval);
        Verify.assertItemAtIndex(Integer.valueOf(0), 2, interval);
        Verify.assertItemAtIndex(Integer.valueOf(5), 3, interval);
        Verify.assertItemAtIndex(Integer.valueOf(10), 4, interval);

        Verify.assertThrows(IndexOutOfBoundsException.class, () -> interval.get(-1));
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> interval.get(5));
    }

    @Test
    public void subList()
    {
        Interval interval = Interval.fromTo(1, 5);
        Assert.assertEquals(FastList.newListWith(2, 3), interval.subList(1, 3));
    }

    @Test
    public void containsAll()
    {
        Assert.assertTrue(Interval.fromTo(1, 3).containsAll(FastList.newListWith(1, 2, 3)));
        Assert.assertFalse(Interval.fromTo(1, 3).containsAll(FastList.newListWith(1, 2, 4)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void add()
    {
        Interval.fromTo(1, 3).add(4);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void add_at_index()
    {
        Interval.fromTo(1, 3).add(0, 4);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove()
    {
        Interval.fromTo(1, 3).remove(Integer.valueOf(4));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove_at_index()
    {
        Interval.fromTo(1, 3).remove(0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAll()
    {
        Interval.fromTo(1, 3).addAll(FastList.newListWith(4, 5, 6));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAll_at_index()
    {
        Interval.fromTo(1, 3).addAll(0, FastList.newListWith(4, 5, 6));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeAll()
    {
        Interval.fromTo(1, 3).removeAll(FastList.newListWith(4, 5, 6));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void retainAll()
    {
        Interval.fromTo(1, 3).retainAll(FastList.newListWith(4, 5, 6));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void clear()
    {
        Interval.fromTo(1, 3).clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void set()
    {
        Interval.fromTo(1, 3).set(0, 0);
    }

    @Test
    public void take()
    {
        Verify.assertIterableEmpty(Interval.fromTo(1, 3).take(0));
        Assert.assertEquals(FastList.newListWith(1, 2), Interval.fromTo(1, 3).take(2));
        Assert.assertEquals(FastList.newListWith(1, 2), Interval.fromTo(1, 2).take(3));

        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromTo(1, 3).take(-1));
    }

    @Test
    public void drop()
    {
        Assert.assertEquals(FastList.newListWith(3, 4), Interval.fromTo(1, 4).drop(2));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), Interval.fromTo(1, 4).drop(0));
        Verify.assertIterableEmpty(Interval.fromTo(1, 2).drop(3));

        Verify.assertThrows(IllegalArgumentException.class, () -> Interval.fromTo(1, 3).drop(-1));
    }

    @Test
    public void takeWhile()
    {
        Verify.assertIterableEmpty(Interval.fromTo(1, 3).takeWhile(Predicates.alwaysFalse()).toList());
        Assert.assertEquals(FastList.newListWith(1, 2), Interval.fromTo(1, 3).takeWhile(each -> each <= 2).toList());
        Assert.assertEquals(FastList.newListWith(1, 2), Interval.fromTo(1, 2).takeWhile(Predicates.alwaysTrue()).toList());

        Verify.assertThrows(IllegalStateException.class, () -> Interval.fromTo(1, 3).takeWhile(null));
    }

    @Test
    public void dropWhile()
    {
        Assert.assertEquals(FastList.newListWith(3, 4), Interval.fromTo(1, 4).dropWhile(each -> each <= 2).toList());
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), Interval.fromTo(1, 4).dropWhile(Predicates.alwaysFalse()).toList());
        Verify.assertIterableEmpty(Interval.fromTo(1, 2).dropWhile(Predicates.alwaysTrue()).toList());

        Verify.assertThrows(IllegalStateException.class, () -> Interval.fromTo(1, 3).dropWhile(null));
    }

    @Test
    public void distinct()
    {
        LazyIterable<Integer> integers = Interval.oneTo(1000000000);

        Assert.assertEquals(
                FastList.newListWith(1, 2, 3, 4, 5),
                integers.distinct().take(5).toList());

        LazyIterable<Integer> lazyInterval = Interval.oneTo(1000000).flatCollect(Interval::oneTo);
        LazyIterable<Integer> distinct = lazyInterval.distinct();
        LazyIterable<Integer> take = distinct.take(5);
        Assert.assertEquals(Lists.immutable.of(1, 2, 3, 4, 5), take.toList());
    }

    private static final class AddParametersProcedure implements ObjectIntProcedure<Integer>
    {
        private final MutableList<Integer> forwardResult;

        private AddParametersProcedure(MutableList<Integer> forwardResult)
        {
            this.forwardResult = forwardResult;
        }

        @Override
        public void value(Integer each, int index)
        {
            this.forwardResult.add(each + index);
        }
    }

    @Test
    public void tap()
    {
        MutableList<Integer> tapResult = Lists.mutable.of();
        Interval interval = Interval.fromTo(10, -10).by(-5);
        LazyIterable<Integer> lazyTapIterable = interval.tap(tapResult::add);
        lazyTapIterable.each(x -> {
        }); //force evaluation
        Assert.assertEquals(interval, tapResult);
    }

    @Test
    public void appendStringThrows()
    {
        Verify.assertThrows(
                RuntimeException.class,
                () -> Interval.oneTo(5)
                        .appendString(new ThrowingAppendable()));
        Verify.assertThrows(
                RuntimeException.class,
                () -> Interval.oneTo(5)
                        .appendString(new ThrowingAppendable(), ", "));
        Verify.assertThrows(
                RuntimeException.class,
                () -> Interval.oneTo(5)
                        .appendString(new ThrowingAppendable(), "[", ", ", "]"));
    }
}
