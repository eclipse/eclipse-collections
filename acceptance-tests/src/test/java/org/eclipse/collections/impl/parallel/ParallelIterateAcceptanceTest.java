/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.ArrayListAdapter;
import org.eclipse.collections.impl.list.mutable.CompositeFastList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.ListAdapter;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.bag.MultiReaderHashBagMultimap;
import org.eclipse.collections.impl.multimap.bag.SynchronizedPutHashBagMultimap;
import org.eclipse.collections.impl.multimap.list.MultiReaderFastListMultimap;
import org.eclipse.collections.impl.multimap.set.MultiReaderUnifiedSetMultimap;
import org.eclipse.collections.impl.multimap.set.SynchronizedPutUnifiedSetMultimap;
import org.eclipse.collections.impl.set.mutable.MultiReaderUnifiedSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParallelIterateAcceptanceTest
{
    private static final Procedure<Integer> EXCEPTION_PROCEDURE = value -> {
        throw new RuntimeException("Thread death on its way!");
    };
    private static final ObjectIntProcedure<Integer> EXCEPTION_OBJECT_INT_PROCEDURE = (object, index) -> {
        throw new RuntimeException("Thread death on its way!");
    };

    private static final Function<Integer, Collection<String>> INT_TO_TWO_STRINGS = integer -> Lists.fixedSize.of(integer.toString(), integer.toString());

    private static final Function0<AtomicInteger> ATOMIC_INTEGER_NEW = AtomicInteger::new;

    private static final Function<Integer, String> EVEN_OR_ODD = value -> value % 2 == 0 ? "Even" : "Odd";
    private int count;
    private final MutableSet<String> threadNames = MultiReaderUnifiedSet.newSet();

    private ImmutableList<RichIterable<Integer>> iterables;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Before
    public void setUp()
    {
        Interval interval = Interval.oneTo(20000);
        this.iterables = Lists.immutable.of(
                interval.toList(),
                interval.toList().asUnmodifiable(),
                interval.toList().asSynchronized(),
                interval.toList().toImmutable(),
                interval.toSet(),
                interval.toSet().asUnmodifiable(),
                interval.toSet().asSynchronized(),
                interval.toSet().toImmutable(),
                interval.toBag(),
                interval.toBag().asUnmodifiable(),
                interval.toBag().asSynchronized(),
                interval.toBag().toImmutable(),
                interval.toSortedSet(),
                interval.toSortedSet().asUnmodifiable(),
                interval.toSortedSet().asSynchronized(),
                interval.toSortedSet().toImmutable(),
                interval.toMap(Functions.getPassThru(), Functions.getPassThru()),
                interval.toMap(Functions.getPassThru(), Functions.getPassThru()).asUnmodifiable(),
                interval.toMap(Functions.getPassThru(), Functions.getPassThru()).asSynchronized(),
                interval.toMap(Functions.getPassThru(), Functions.getPassThru()).toImmutable(),
                ArrayListAdapter.<Integer>newList().withAll(interval),
                ArrayListAdapter.<Integer>newList().withAll(interval).asUnmodifiable(),
                ArrayListAdapter.<Integer>newList().withAll(interval).asSynchronized(),
                new CompositeFastList<Integer>().withAll(interval.toList()),
                new CompositeFastList<Integer>().withAll(interval.toList()).asUnmodifiable(),
                new CompositeFastList<Integer>().withAll(interval.toList()).asSynchronized(),
                new CompositeFastList<Integer>().withAll(interval.toList()).toImmutable(),
                ListAdapter.adapt(new LinkedList<Integer>()).withAll(interval),
                ListAdapter.adapt(new LinkedList<Integer>()).withAll(interval).asUnmodifiable(),
                ListAdapter.adapt(new LinkedList<Integer>()).withAll(interval).asSynchronized(),
                UnifiedSetWithHashingStrategy.<Integer>newSet(HashingStrategies.defaultStrategy()).withAll(interval),
                UnifiedSetWithHashingStrategy.<Integer>newSet(HashingStrategies.defaultStrategy()).withAll(interval).asUnmodifiable(),
                UnifiedSetWithHashingStrategy.<Integer>newSet(HashingStrategies.defaultStrategy()).withAll(interval).asSynchronized(),
                UnifiedSetWithHashingStrategy.<Integer>newSet(HashingStrategies.defaultStrategy()).withAll(interval).toImmutable());
    }

    @After
    public void tearDown()
    {
        this.executor.shutdown();
    }

    @Test
    public void testOneLevelCall()
    {
        new RecursiveProcedure().value(1);

        synchronized (this)
        {
            Assert.assertEquals("all iterations completed", 20000, this.count);
        }
    }

    @Test
    public void testNestedCall()
    {
        new RecursiveProcedure().value(2);

        synchronized (this)
        {
            Assert.assertEquals("all iterations completed", 419980, this.count);
        }
        Assert.assertTrue("uses multiple threads", this.threadNames.size() > 1);
    }

    @Test
    public void testForEachUsingSet()
    {
        //Tests the default batch size calculations
        IntegerSum sum = new IntegerSum(0);
        MutableSet<Integer> set = Interval.toSet(1, 10000);
        ParallelIterate.forEach(set, new SumProcedure(sum), new SumCombiner(sum));
        Assert.assertEquals(50005000, sum.getSum());

        //Testing batch size 1
        IntegerSum sum2 = new IntegerSum(0);
        UnifiedSet<Integer> set2 = UnifiedSet.newSet(Interval.oneTo(100));
        ParallelIterate.forEach(set2, new SumProcedure(sum2), new SumCombiner(sum2), 1, set2.getBatchCount(set2.size()));
        Assert.assertEquals(5050, sum2.getSum());

        //Testing an uneven batch size
        IntegerSum sum3 = new IntegerSum(0);
        UnifiedSet<Integer> set3 = UnifiedSet.newSet(Interval.oneTo(100));
        ParallelIterate.forEach(set3, new SumProcedure(sum3), new SumCombiner(sum3), 1, set3.getBatchCount(13));
        Assert.assertEquals(5050, sum3.getSum());

        //Testing divideByZero exception by passing 1 as batchSize
        IntegerSum sum4 = new IntegerSum(0);
        UnifiedSet<Integer> set4 = UnifiedSet.newSet(Interval.oneTo(100));
        ParallelIterate.forEach(set4, new SumProcedure(sum4), new SumCombiner(sum4), 1);
        Assert.assertEquals(5050, sum4.getSum());
    }

    @Test
    public void testForEachUsingMap()
    {
        //Test the default batch size calculations
        IntegerSum sum1 = new IntegerSum(0);
        MutableMap<String, Integer> map1 = Interval.fromTo(1, 10000).toMap(String::valueOf, Functions.getIntegerPassThru());
        ParallelIterate.forEach(map1, new SumProcedure(sum1), new SumCombiner(sum1));
        Assert.assertEquals(50005000, sum1.getSum());

        //Testing batch size 1
        IntegerSum sum2 = new IntegerSum(0);
        UnifiedMap<String, Integer> map2 = (UnifiedMap<String, Integer>) Interval.fromTo(1, 100).toMap(String::valueOf, Functions.getIntegerPassThru());
        ParallelIterate.forEach(map2, new SumProcedure(sum2), new SumCombiner(sum2), 1, map2.getBatchCount(map2.size()));
        Assert.assertEquals(5050, sum2.getSum());

        //Testing an uneven batch size
        IntegerSum sum3 = new IntegerSum(0);
        UnifiedMap<String, Integer> set3 = (UnifiedMap<String, Integer>) Interval.fromTo(1, 100).toMap(String::valueOf, Functions.getIntegerPassThru());
        ParallelIterate.forEach(set3, new SumProcedure(sum3), new SumCombiner(sum3), 1, set3.getBatchCount(13));
        Assert.assertEquals(5050, sum3.getSum());
    }

    @Test
    public void testForEach()
    {
        IntegerSum sum1 = new IntegerSum(0);
        List<Integer> list1 = ParallelIterateAcceptanceTest.createIntegerList(16);
        ParallelIterate.forEach(list1, new SumProcedure(sum1), new SumCombiner(sum1), 1, list1.size() / 2);
        Assert.assertEquals(16, sum1.getSum());

        IntegerSum sum2 = new IntegerSum(0);
        List<Integer> list2 = ParallelIterateAcceptanceTest.createIntegerList(7);
        ParallelIterate.forEach(list2, new SumProcedure(sum2), new SumCombiner(sum2));
        Assert.assertEquals(7, sum2.getSum());

        IntegerSum sum3 = new IntegerSum(0);
        List<Integer> list3 = ParallelIterateAcceptanceTest.createIntegerList(15);
        ParallelIterate.forEach(list3, new SumProcedure(sum3), new SumCombiner(sum3), 1, list3.size() / 2);
        Assert.assertEquals(15, sum3.getSum());

        IntegerSum sum4 = new IntegerSum(0);
        List<Integer> list4 = ParallelIterateAcceptanceTest.createIntegerList(35);
        ParallelIterate.forEach(list4, new SumProcedure(sum4), new SumCombiner(sum4));
        Assert.assertEquals(35, sum4.getSum());

        IntegerSum sum5 = new IntegerSum(0);
        MutableList<Integer> list5 = FastList.newList(list4);
        ParallelIterate.forEach(list5, new SumProcedure(sum5), new SumCombiner(sum5));
        Assert.assertEquals(35, sum5.getSum());

        IntegerSum sum6 = new IntegerSum(0);
        List<Integer> list6 = ParallelIterateAcceptanceTest.createIntegerList(40);
        ParallelIterate.forEach(list6, new SumProcedure(sum6), new SumCombiner(sum6), 1, list6.size() / 2);
        Assert.assertEquals(40, sum6.getSum());

        IntegerSum sum7 = new IntegerSum(0);
        MutableList<Integer> list7 = FastList.newList(list6);
        ParallelIterate.forEach(list7, new SumProcedure(sum7), new SumCombiner(sum7), 1, list6.size() / 2);
        Assert.assertEquals(40, sum7.getSum());
    }

    @Test
    public void testForEachImmutableList()
    {
        IntegerSum sum1 = new IntegerSum(0);
        ImmutableList<Integer> list1 = Lists.immutable.ofAll(ParallelIterateAcceptanceTest.createIntegerList(16));
        ParallelIterate.forEach(list1, new SumProcedure(sum1), new SumCombiner(sum1), 1, list1.size() / 2);
        Assert.assertEquals(16, sum1.getSum());

        IntegerSum sum2 = new IntegerSum(0);
        ImmutableList<Integer> list2 = Lists.immutable.ofAll(ParallelIterateAcceptanceTest.createIntegerList(7));
        ParallelIterate.forEach(list2, new SumProcedure(sum2), new SumCombiner(sum2));
        Assert.assertEquals(7, sum2.getSum());

        IntegerSum sum3 = new IntegerSum(0);
        ImmutableList<Integer> list3 = Lists.immutable.ofAll(ParallelIterateAcceptanceTest.createIntegerList(15));
        ParallelIterate.forEach(list3, new SumProcedure(sum3), new SumCombiner(sum3), 1, list3.size() / 2);
        Assert.assertEquals(15, sum3.getSum());

        IntegerSum sum4 = new IntegerSum(0);
        ImmutableList<Integer> list4 = Lists.immutable.ofAll(ParallelIterateAcceptanceTest.createIntegerList(35));
        ParallelIterate.forEach(list4, new SumProcedure(sum4), new SumCombiner(sum4));
        Assert.assertEquals(35, sum4.getSum());

        IntegerSum sum5 = new IntegerSum(0);
        ImmutableList<Integer> list5 = FastList.newList(list4).toImmutable();
        ParallelIterate.forEach(list5, new SumProcedure(sum5), new SumCombiner(sum5));
        Assert.assertEquals(35, sum5.getSum());

        IntegerSum sum6 = new IntegerSum(0);
        ImmutableList<Integer> list6 = Lists.immutable.ofAll(ParallelIterateAcceptanceTest.createIntegerList(40));
        ParallelIterate.forEach(list6, new SumProcedure(sum6), new SumCombiner(sum6), 1, list6.size() / 2);
        Assert.assertEquals(40, sum6.getSum());

        IntegerSum sum7 = new IntegerSum(0);
        ImmutableList<Integer> list7 = FastList.newList(list6).toImmutable();
        ParallelIterate.forEach(list7, new SumProcedure(sum7), new SumCombiner(sum7), 1, list6.size() / 2);
        Assert.assertEquals(40, sum7.getSum());
    }

    @Test
    public void testForEachWithException()
    {
        Verify.assertThrows(RuntimeException.class, () -> ParallelIterate.forEach(
                ParallelIterateAcceptanceTest.createIntegerList(5),
                new PassThruProcedureFactory<>(EXCEPTION_PROCEDURE),
                new PassThruCombiner<>(),
                1,
                5));
    }

    @Test
    public void testForEachWithIndexToArrayUsingFastListSerialPath()
    {
        Integer[] array = new Integer[200];
        FastList<Integer> list = (FastList<Integer>) Interval.oneTo(200).toList();
        Assert.assertTrue(ArrayIterate.allSatisfy(array, Predicates.isNull()));
        ParallelIterate.forEachWithIndex(list, (each, index) -> array[index] = each);
        Assert.assertArrayEquals(array, list.toArray(new Integer[]{}));
    }

    @Test
    public void testForEachWithIndexToArrayUsingFastList()
    {
        Integer[] array = new Integer[200];
        FastList<Integer> list = (FastList<Integer>) Interval.oneTo(200).toList();
        Assert.assertTrue(ArrayIterate.allSatisfy(array, Predicates.isNull()));
        ParallelIterate.forEachWithIndex(list, (each, index) -> array[index] = each, 10, 10);
        Assert.assertArrayEquals(array, list.toArray(new Integer[]{}));
    }

    @Test
    public void testForEachWithIndexToArrayUsingImmutableList()
    {
        Integer[] array = new Integer[200];
        ImmutableList<Integer> list = Interval.oneTo(200).toList().toImmutable();
        Assert.assertTrue(ArrayIterate.allSatisfy(array, Predicates.isNull()));
        ParallelIterate.forEachWithIndex(list, (each, index) -> array[index] = each, 10, 10);
        Assert.assertArrayEquals(array, list.toArray(new Integer[]{}));
    }

    @Test
    public void testForEachWithIndexToArrayUsingArrayList()
    {
        Integer[] array = new Integer[200];
        List<Integer> list = new ArrayList<>(Interval.oneTo(200));
        Assert.assertTrue(ArrayIterate.allSatisfy(array, Predicates.isNull()));
        ParallelIterate.forEachWithIndex(list, (each, index) -> array[index] = each, 10, 10);
        Assert.assertArrayEquals(array, list.toArray(new Integer[]{}));
    }

    @Test
    public void testForEachWithIndexToArrayUsingFixedArrayList()
    {
        Integer[] array = new Integer[10];
        Assert.assertTrue(ArrayIterate.allSatisfy(array, Predicates.isNull()));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ParallelIterate.forEachWithIndex(list, (each, index) -> array[index] = each, 1, 2);
        Assert.assertArrayEquals(array, list.toArray(new Integer[list.size()]));
    }

    @Test
    public void testForEachWithIndexException()
    {
        Verify.assertThrows(RuntimeException.class, () -> ParallelIterate.forEachWithIndex(
                ParallelIterateAcceptanceTest.createIntegerList(5),
                new PassThruObjectIntProcedureFactory<>(EXCEPTION_OBJECT_INT_PROCEDURE),
                new PassThruCombiner<>(),
                1,
                5));
    }

    @Test
    public void select()
    {
        this.iterables.forEach(Procedures.cast(this::basicSelect));
    }

    private void basicSelect(RichIterable<Integer> iterable)
    {
        Collection<Integer> actual1 = ParallelIterate.select(iterable, Predicates.greaterThan(10000));
        Collection<Integer> actual2 = ParallelIterate.select(iterable, Predicates.greaterThan(10000), HashBag.newBag(), 3, this.executor, true);
        Collection<Integer> actual3 = ParallelIterate.select(iterable, Predicates.greaterThan(10000), true);
        RichIterable<Integer> expected = iterable.select(Predicates.greaterThan(10000));
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual1.getClass().getSimpleName(), expected, actual1);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual2.getClass().getSimpleName(), expected.toBag(), actual2);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual3.getClass().getSimpleName(), expected.toBag(), HashBag.newBag(actual3));
    }

    @Test
    public void selectSortedSet()
    {
        RichIterable<Integer> iterable = Interval.oneTo(20000).toSortedSet();
        Collection<Integer> actual1 = ParallelIterate.select(iterable, Predicates.greaterThan(10000));
        Collection<Integer> actual2 = ParallelIterate.select(iterable, Predicates.greaterThan(10000), true);
        RichIterable<Integer> expected = iterable.select(Predicates.greaterThan(10000));
        Assert.assertSame(expected.getClass(), actual1.getClass());
        Assert.assertSame(expected.getClass(), actual2.getClass());
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual1.getClass().getSimpleName(), expected, actual1);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual2.getClass().getSimpleName(), expected, actual2);
    }

    @Test
    public void count()
    {
        this.iterables.forEach(Procedures.cast(this::basicCount));
    }

    private void basicCount(RichIterable<Integer> listIterable)
    {
        int actual1 = ParallelIterate.count(listIterable, Predicates.greaterThan(10000));
        int actual2 = ParallelIterate.count(listIterable, Predicates.greaterThan(10000), 11, this.executor);
        Assert.assertEquals(10000, actual1);
        Assert.assertEquals(10000, actual2);
    }

    @Test
    public void reject()
    {
        this.iterables.forEach(Procedures.cast(this::basicReject));
    }

    private void basicReject(RichIterable<Integer> iterable)
    {
        Collection<Integer> actual1 = ParallelIterate.reject(iterable, Predicates.greaterThan(10000));
        Collection<Integer> actual2 = ParallelIterate.reject(iterable, Predicates.greaterThan(10000), HashBag.newBag(), 3, this.executor, true);
        Collection<Integer> actual3 = ParallelIterate.reject(iterable, Predicates.greaterThan(10000), true);
        RichIterable<Integer> expected = iterable.reject(Predicates.greaterThan(10000));
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual1.getClass().getSimpleName(), expected, actual1);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual2.getClass().getSimpleName(), expected.toBag(), actual2);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual3.getClass().getSimpleName(), expected.toBag(), HashBag.newBag(actual3));
    }

    @Test
    public void collect()
    {
        this.iterables.forEach(Procedures.cast(this::basicCollect));
    }

    private void basicCollect(RichIterable<Integer> iterable)
    {
        Collection<String> actual1 = ParallelIterate.collect(iterable, String::valueOf);
        Collection<String> actual2 = ParallelIterate.collect(iterable, String::valueOf, HashBag.newBag(), 3, this.executor, false);
        Collection<String> actual3 = ParallelIterate.collect(iterable, String::valueOf, true);
        RichIterable<String> expected = iterable.collect(String::valueOf);
        Verify.assertSize(20000, actual1);
        Verify.assertContains(String.valueOf(20000), actual1);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual1.getClass().getSimpleName(), expected, actual1);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual2.getClass().getSimpleName(), expected.toBag(), actual2);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual3.getClass().getSimpleName(), expected.toBag(), HashBag.newBag(actual3));
    }

    @Test
    public void collectIf()
    {
        this.iterables.forEach(Procedures.cast(this::basicCollectIf));
    }

    private void basicCollectIf(RichIterable<Integer> collection)
    {
        Predicate<Integer> greaterThan = Predicates.greaterThan(10000);
        Collection<String> actual1 = ParallelIterate.collectIf(collection, greaterThan, String::valueOf);
        Collection<String> actual2 = ParallelIterate.collectIf(collection, greaterThan, String::valueOf, HashBag.newBag(), 3, this.executor, true);
        Collection<String> actual3 = ParallelIterate.collectIf(collection, greaterThan, String::valueOf, HashBag.newBag(), 3, this.executor, true);
        Bag<String> expected = collection.collectIf(greaterThan, String::valueOf).toBag();
        Verify.assertSize(10000, actual1);
        Verify.assertNotContains(String.valueOf(9000), actual1);
        Verify.assertNotContains(String.valueOf(21000), actual1);
        Verify.assertContains(String.valueOf(15976), actual1);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual1.getClass().getSimpleName(), expected, HashBag.newBag(actual1));
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual2.getClass().getSimpleName(), expected, actual2);
        Assert.assertEquals(expected.getClass().getSimpleName() + '/' + actual3.getClass().getSimpleName(), expected, actual3);
    }

    @Test
    public void flatCollect()
    {
        this.iterables.forEach(Procedures.cast(this::basicFlatCollect));
    }

    private void basicFlatCollect(RichIterable<Integer> iterable)
    {
        Collection<String> actual1 = ParallelIterate.flatCollect(iterable, INT_TO_TWO_STRINGS);
        Collection<String> actual2 = ParallelIterate.flatCollect(iterable, INT_TO_TWO_STRINGS, HashBag.newBag(), 3, this.executor, false);
        Collection<String> actual3 = ParallelIterate.flatCollect(iterable, INT_TO_TWO_STRINGS, true);
        RichIterable<String> expected1 = iterable.flatCollect(INT_TO_TWO_STRINGS);
        RichIterable<String> expected2 = iterable.flatCollect(INT_TO_TWO_STRINGS, HashBag.newBag());
        Verify.assertContains(String.valueOf(20000), actual1);
        Assert.assertEquals(expected1.getClass().getSimpleName() + '/' + actual1.getClass().getSimpleName(), expected1, actual1);
        Assert.assertEquals(expected2.getClass().getSimpleName() + '/' + actual2.getClass().getSimpleName(), expected2, actual2);
        Assert.assertEquals(expected1.getClass().getSimpleName() + '/' + actual3.getClass().getSimpleName(), expected1.toBag(), HashBag.newBag(actual3));
    }

    @Test
    public void groupBy()
    {
        FastList<Integer> iterable = FastList.newWithNValues(10000000, new Function0<Integer>()
        {
            private int current;

            public Integer value()
            {
                if (this.current < 4)
                {
                    return Integer.valueOf(this.current++);
                }
                this.current = 0;
                return Integer.valueOf(4);
            }
        });
        iterable.shuffleThis();
        Multimap<String, Integer> expected = iterable.toBag().groupBy(String::valueOf);
        Multimap<String, Integer> expectedAsSet = iterable.toSet().groupBy(String::valueOf);
        Multimap<String, Integer> result1 = ParallelIterate.groupBy(iterable.toList(), String::valueOf, 100);
        Assert.assertEquals(expected, HashBagMultimap.newMultimap(result1));
        Multimap<String, Integer> result2 = ParallelIterate.groupBy(iterable.toList(), String::valueOf);
        Assert.assertEquals(expected, HashBagMultimap.newMultimap(result2));
        Multimap<String, Integer> result3 = ParallelIterate.groupBy(iterable.toSet(), String::valueOf, SynchronizedPutUnifiedSetMultimap.newMultimap(), 100);
        Assert.assertEquals(expectedAsSet, result3);
        Multimap<String, Integer> result4 = ParallelIterate.groupBy(iterable.toSet(), String::valueOf, SynchronizedPutUnifiedSetMultimap.newMultimap());
        Assert.assertEquals(expectedAsSet, result4);
        Multimap<String, Integer> result5 = ParallelIterate.groupBy(iterable.toSortedSet(), String::valueOf, SynchronizedPutUnifiedSetMultimap.newMultimap(), 100);
        Assert.assertEquals(expectedAsSet, result5);
        Multimap<String, Integer> result6 = ParallelIterate.groupBy(iterable.toSortedSet(), String::valueOf, SynchronizedPutUnifiedSetMultimap.newMultimap());
        Assert.assertEquals(expectedAsSet, result6);
        Multimap<String, Integer> result7 = ParallelIterate.groupBy(iterable.toBag(), String::valueOf, SynchronizedPutHashBagMultimap.newMultimap(), 100);
        Assert.assertEquals(expected, result7);
        Multimap<String, Integer> result8 = ParallelIterate.groupBy(iterable.toBag(), String::valueOf, SynchronizedPutHashBagMultimap.newMultimap());
        Assert.assertEquals(expected, result8);
        Multimap<String, Integer> result9 = ParallelIterate.groupBy(iterable.toList().toImmutable(), String::valueOf);
        Assert.assertEquals(expected, HashBagMultimap.newMultimap(result9));
        Multimap<String, Integer> result10 = ParallelIterate.groupBy(iterable.toSortedList(), String::valueOf, 100);
        Assert.assertEquals(expected, HashBagMultimap.newMultimap(result10));
        Multimap<String, Integer> result11 = ParallelIterate.groupBy(iterable.toSortedList(), String::valueOf);
        Assert.assertEquals(expected, HashBagMultimap.newMultimap(result11));

        Multimap<String, Integer> result12 = ParallelIterate.groupBy(iterable, String::valueOf, MultiReaderFastListMultimap.newMultimap(), 100);
        Assert.assertEquals(expected, HashBagMultimap.newMultimap(result12));
        Multimap<String, Integer> result13 = ParallelIterate.groupBy(iterable, String::valueOf, MultiReaderFastListMultimap.newMultimap());
        Assert.assertEquals(expected, HashBagMultimap.newMultimap(result13));

        Multimap<String, Integer> result14 = ParallelIterate.groupBy(iterable, String::valueOf, MultiReaderHashBagMultimap.newMultimap(), 100);
        Assert.assertEquals(expected, result14);
        Multimap<String, Integer> result15 = ParallelIterate.groupBy(iterable, String::valueOf, MultiReaderHashBagMultimap.newMultimap());
        Assert.assertEquals(expected, result15);

        Multimap<String, Integer> result16 = ParallelIterate.groupBy(iterable, String::valueOf, MultiReaderUnifiedSetMultimap.newMultimap(), 100);
        Assert.assertEquals(expectedAsSet, result16);
        Multimap<String, Integer> result17 = ParallelIterate.groupBy(iterable, String::valueOf, MultiReaderUnifiedSetMultimap.newMultimap());
        Assert.assertEquals(expectedAsSet, result17);
    }

    @Test
    public void aggregateInPlaceBy()
    {
        Procedure2<AtomicInteger, Integer> countAggregator = (aggregate, value) -> aggregate.incrementAndGet();
        List<Integer> list = Interval.oneTo(20000);
        MutableMap<String, AtomicInteger> aggregation =
                ParallelIterate.aggregateInPlaceBy(list, EVEN_OR_ODD, ATOMIC_INTEGER_NEW, countAggregator);
        Assert.assertEquals(10000, aggregation.get("Even").intValue());
        Assert.assertEquals(10000, aggregation.get("Odd").intValue());
        ParallelIterate.aggregateInPlaceBy(list, EVEN_OR_ODD, ATOMIC_INTEGER_NEW, countAggregator, aggregation);
        Assert.assertEquals(20000, aggregation.get("Even").intValue());
        Assert.assertEquals(20000, aggregation.get("Odd").intValue());
    }

    @Test
    public void aggregateInPlaceByWithBatchSize()
    {
        MutableList<Integer> list = LazyIterate.adapt(Collections.nCopies(1000, 1))
                .concatenate(Collections.nCopies(2000, 2))
                .concatenate(Collections.nCopies(3000, 3))
                .toList()
                .shuffleThis();
        MapIterable<String, AtomicInteger> aggregation =
                ParallelIterate.aggregateInPlaceBy(list, String::valueOf, ATOMIC_INTEGER_NEW, AtomicInteger::addAndGet, 100);
        Assert.assertEquals(1000, aggregation.get("1").intValue());
        Assert.assertEquals(4000, aggregation.get("2").intValue());
        Assert.assertEquals(9000, aggregation.get("3").intValue());
    }

    private static List<Integer> createIntegerList(int size)
    {
        return Collections.nCopies(size, Integer.valueOf(1));
    }

    private class RecursiveProcedure implements Procedure<Integer>
    {
        private static final long serialVersionUID = 1L;
        private final ExecutorService executorService = ParallelIterate.newPooledExecutor("ParallelIterateTest", false);

        @Override
        public void value(Integer level)
        {
            if (level > 0)
            {
                ParallelIterateAcceptanceTest.this.threadNames.add(Thread.currentThread().getName());
                this.executeParallelIterate(level - 1, this.executorService);
            }
            else
            {
                this.simulateWork();
            }
        }

        private void simulateWork()
        {
            synchronized (ParallelIterateAcceptanceTest.this)
            {
                ParallelIterateAcceptanceTest.this.count++;
            }
        }

        private void executeParallelIterate(int level, ExecutorService executorService)
        {
            MutableList<Integer> items = Lists.mutable.of();
            for (int i = 0; i < 20000; i++)
            {
                items.add(i % 1000 == 0 ? level : 0);
            }
            ParallelIterate.forEach(items, new RecursiveProcedure(), executorService);
        }
    }

    public static final class IntegerSum
    {
        private int sum;

        public IntegerSum(int newSum)
        {
            this.sum = newSum;
        }

        public IntegerSum add(int value)
        {
            this.sum += value;
            return this;
        }

        public int getSum()
        {
            return this.sum;
        }
    }

    public static final class SumProcedure
            implements Procedure<Integer>, Function2<IntegerSum, Integer, IntegerSum>, ProcedureFactory<SumProcedure>
    {
        private static final long serialVersionUID = 1L;

        private final IntegerSum sum;

        public SumProcedure(IntegerSum newSum)
        {
            this.sum = newSum;
        }

        @Override
        public SumProcedure create()
        {
            return new SumProcedure(new IntegerSum(0));
        }

        @Override
        public IntegerSum value(IntegerSum s1, Integer s2)
        {
            return s1.add(s2);
        }

        @Override
        public void value(Integer object)
        {
            this.sum.add(object);
        }

        public int getSum()
        {
            return this.sum.getSum();
        }
    }

    public static final class SumCombiner extends AbstractProcedureCombiner<SumProcedure>
    {
        private static final long serialVersionUID = 1L;
        private final IntegerSum sum;

        public SumCombiner(IntegerSum initialSum)
        {
            super(true);
            this.sum = initialSum;
        }

        @Override
        public void combineOne(SumProcedure sumProcedure)
        {
            this.sum.add(sumProcedure.getSum());
        }
    }
}
