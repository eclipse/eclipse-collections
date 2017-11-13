/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.mutable;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.MutableMapIterableTestCase;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.immutable.ImmutableTreeMap;
import org.eclipse.collections.impl.multimap.set.sorted.TreeSortedSetMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;

/**
 * Abstract JUnit TestCase for {@link MutableSortedMap}s.
 */
public abstract class MutableSortedMapTestCase extends MutableMapIterableTestCase
{
    static final Comparator<Integer> REV_INT_ORDER = Comparators.reverseNaturalOrder();

    public abstract <K, V> MutableSortedMap<K, V> newMap(Comparator<? super K> comparator);

    public abstract <K, V> MutableSortedMap<K, V> newMapWithKeyValue(Comparator<? super K> comparator, K key, V value);

    public abstract <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2);

    public abstract <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3);

    public abstract <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    @Override
    protected abstract <K, V> MutableSortedMap<K, V> newMap();

    @Override
    protected abstract <K, V> MutableSortedMap<K, V> newMapWithKeyValue(K key, V value);

    @Override
    protected abstract <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2);

    @Override
    protected abstract <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3);

    @Override
    protected abstract <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    protected <T> MutableSortedMap<?, T> newWith(T... littleElements)
    {
        int i = 0;
        MutableSortedMap<Integer, T> result = this.newMap();
        for (T each : littleElements)
        {
            result.put(i, each);
            i++;
        }
        return result;
    }

    @Test
    public void testNewEmpty()
    {
        MutableSortedMap<Integer, Integer> map = this.newMapWithKeysValues(1, 1, 2, 2);
        MutableSortedMap<Integer, Integer> revMap = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, 1, 2, 2);
        Verify.assertEmpty(map.newEmpty());
        Verify.assertEmpty(revMap.newEmpty());
        Assert.assertEquals(Comparators.<Integer>reverseNaturalOrder(), revMap.newEmpty().comparator());
    }

    @Override
    @Test
    public void testNewMap()
    {
        super.testNewMap();

        MutableSortedMap<Integer, Integer> map = this.newMap();
        Verify.assertEmpty(map);

        MutableSortedMap<Integer, Integer> revMap = this.newMap(REV_INT_ORDER);
        Verify.assertEmpty(revMap);
    }

    @Override
    @Test
    public void toImmutable()
    {
        super.toImmutable();

        MutableSortedMap<Integer, String> sortedMap = this.newMapWithKeyValue(1, "One");
        ImmutableSortedMap<Integer, String> result = sortedMap.toImmutable();
        Verify.assertSize(1, result.castToSortedMap());
        Assert.assertEquals("One", result.get(1));
        Verify.assertInstanceOf(ImmutableTreeMap.class, result);
    }

    @Override
    @Test
    public void testNewMapWithKeyValue()
    {
        super.testNewMapWithKeyValue();

        MutableSortedMap<Integer, String> map = this.newMapWithKeyValue(1, "One");
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "One"), map);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeyValue(REV_INT_ORDER, 4, "Four");
        Verify.assertNotEmpty(revMap);
        Verify.assertListsEqual(FastList.newListWith("Four"), revMap.valuesView().toList());
    }

    @Test
    public void newMapWith_2()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two");
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "One", 2, "Two"), map);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 3, "Three", 4, "Four");
        Verify.assertNotEmpty(revMap);
        Verify.assertListsEqual(FastList.newListWith("Four", "Three"), revMap.valuesView().toList());
    }

    @Test
    public void newMapWith_3()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three");
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "One", 2, "Two", 3, "Three"), map);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 3, "Three", 2, "Two", 4, "Four");
        Verify.assertNotEmpty(revMap);
        Verify.assertListsEqual(FastList.newListWith("Four", "Three", "Two"), revMap.valuesView().toList());
    }

    @Test
    public void newMapWith_4()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four"), map);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 3, "Three", 2, "Two", 4, "Four");
        Verify.assertNotEmpty(revMap);
        Verify.assertListsEqual(FastList.newListWith("Four", "Three", "Two", "One"), revMap.valuesView().toList());
    }

    @Test
    public void with()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2");
        Verify.assertSortedMapsEqual(TreeSortedMap.newMapWith(1, "1", 2, "2", 3, "3"), map.with(Tuples.pair(3, "3")));
        Verify.assertSortedMapsEqual(TreeSortedMap.newMapWith(1, "1", 2, "2", 3, "3", 4, "4"), map.with(Tuples.pair(3, "3"), Tuples.pair(4, "4")));

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "1", 2, "2");
        Verify.assertSortedMapsEqual(TreeSortedMap.newMap(REV_INT_ORDER).with(1, "1", 2, "2", 3, "3"), revMap.with(Tuples.pair(3, "3")));
        Verify.assertSortedMapsEqual(TreeSortedMap.newMap(REV_INT_ORDER).with(1, "1", 2, "2", 3, "3", 4, "4"), revMap.with(Tuples.pair(3, "3"), Tuples.pair(4, "4")));
    }

    @Override
    @Test
    public void tap()
    {
        MutableList<String> tapResult = Lists.mutable.empty();
        MapIterable<Integer, String> map = this.newMapWithKeysValues(1, "One", 3, "Three", 2, "Two", 4, "Four");
        Assert.assertSame(map, map.tap(tapResult::add));
        Assert.assertEquals(map.toList(), tapResult);

        MutableList<String> revTapResult = Lists.mutable.empty();
        MapIterable<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 3, "Three", 2, "Two", 4, "Four");
        Assert.assertSame(revMap, revMap.tap(revTapResult::add));
        Assert.assertEquals(revMap.toList(), revTapResult);
    }

    @Override
    @Test
    public void forEach()
    {
        super.forEach();

        MutableList<String> list = Lists.mutable.empty();
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 3, "Three", 2, "Two", 4, "Four");
        map.forEach(CollectionAddProcedure.on(list));
        Verify.assertListsEqual(FastList.newListWith("One", "Two", "Three", "Four"), list);

        MutableList<String> list2 = Lists.mutable.empty();
        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 3, "Three", 2, "Two", 4, "Four");
        revMap.forEach(CollectionAddProcedure.on(list2));
        Verify.assertListsEqual(FastList.newListWith("Four", "Three", "Two", "One"), list2);
    }

    @Override
    @Test
    public void forEachWith()
    {
        super.forEachWith();
        MutableList<Integer> list = Lists.mutable.empty();
        MutableSortedMap<Integer, Integer> map = this.newMapWithKeysValues(-1, 1, -2, 2, -3, 3, -4, 4);
        map.forEachWith((argument1, argument2) -> list.add(argument1 + argument2), 10);
        Verify.assertListsEqual(FastList.newListWith(14, 13, 12, 11), list);
    }

    @Test
    public void forEachWith_reverse()
    {
        MutableList<Integer> list2 = Lists.mutable.empty();
        MutableSortedMap<Integer, Integer> revMap = this.newMapWithKeysValues(REV_INT_ORDER, -1, 1, -2, 2, -3, 3, -4, 4);
        revMap.forEachWith((argument1, argument2) -> list2.add(argument1 + argument2), 10);
        Verify.assertListsEqual(FastList.newListWith(11, 12, 13, 14), list2);
    }

    @Override
    @Test
    public void forEachWithIndex()
    {
        super.forEachWithIndex();

        MutableList<String> list = Lists.mutable.empty();
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
        map.forEachWithIndex((value, index) -> {
            list.add(value);
            list.add(String.valueOf(index));
        });
        Verify.assertListsEqual(FastList.newListWith("One", "0", "Two", "1", "Three", "2", "Four", "3"), list);
    }

    @Test
    public void forEachWithIndex_reverse()
    {
        MutableList<String> list2 = Lists.mutable.empty();
        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 2, "Two", 3, "Three", 4, "Four");
        revMap.forEachWithIndex((value, index) -> {
            list2.add(value);
            list2.add(String.valueOf(index));
        });
        Verify.assertListsEqual(FastList.newListWith("Four", "0", "Three", "1", "Two", "2", "One", "3"), list2);
    }

    @Override
    @Test
    public void forEachKeyValue()
    {
        super.forEachKeyValue();

        MutableList<String> result = Lists.mutable.empty();
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 2, "Two", 3, "Three");
        map.forEachKeyValue((key, value) -> result.add(key + value));
        Verify.assertListsEqual(FastList.newListWith("3Three", "2Two", "1One"), result);
    }

    @Override
    @Test
    public void forEachKey()
    {
        super.forEachKey();
        MutableList<Integer> result = Lists.mutable.empty();
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(REV_INT_ORDER, 1, "1", 2, "2", 3, "3");
        map.forEachKey(CollectionAddProcedure.on(result));
        Verify.assertListsEqual(FastList.newListWith(3, 2, 1), result);
    }

    @Override
    @Test
    public void collectValues()
    {
        super.collectValues();
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 2, "Two", 3, "Three");
        MutableSortedMap<Integer, String> actual = map.collectValues((argument1, argument2) -> new StringBuilder(argument2).reverse().toString());
        Assert.assertEquals(TreeSortedMap.<Integer, String>
                newMap(REV_INT_ORDER).with(1, "enO", 2, "owT", 3, "eerhT"), actual);
    }

    @Override
    @Test
    public void zipWithIndex()
    {
        super.zipWithIndex();
        MutableSortedMap<String, String> map = this.newMapWithKeysValues("1", "One", "2", "Two", "3", "Three");

        MutableList<Pair<String, Integer>> pairs = map.zipWithIndex();

        Verify.assertListsEqual(
                map.toList(),
                pairs.collect((Function<Pair<String, ?>, String>) Pair::getOne));

        Verify.assertListsEqual(
                Interval.zeroTo(map.size() - 1),
                pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo));

        Assert.assertEquals(
                map.zipWithIndex().toSet(),
                map.zipWithIndex(UnifiedSet.newSet()));
    }

    @Override
    @Test
    public void zip()
    {
        super.zip();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("A", 1, "B", 2, "C", 3);
        MutableList<Pair<Integer, String>> zip = map.zip(FastList.newListWith("One", "Two", "Three"));
        Verify.assertListsEqual(FastList.newListWith(Tuples.pair(1, "One"), Tuples.pair(2, "Two"), Tuples.pair(3, "Three")), zip);
    }

    @Override
    @Test
    public void select_value()
    {
        super.select_value();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "A", 1, "B", 2, "C", 3);
        Verify.assertListsEqual(FastList.newListWith(2, 1), map.select(Predicates.lessThan(3)));
    }

    @Override
    @Test
    public void reject_value()
    {
        super.reject_value();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "A", 1, "B", 2, "C", 3);
        Verify.assertListsEqual(FastList.newListWith(2, 1), map.reject(Predicates.greaterThan(2)));
    }

    @Override
    @Test
    public void partition_value()
    {
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(
                Comparators.reverseNaturalOrder(),
                "A", 1,
                "B", 2,
                "C", 3,
                "D", 4);
        PartitionMutableList<Integer> partition = map.partition(IntegerPredicates.isEven());
        Assert.assertEquals(iList(4, 2), partition.getSelected());
        Assert.assertEquals(iList(3, 1), partition.getRejected());
    }

    @Override
    @Test
    public void partitionWith_value()
    {
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(
                Comparators.reverseNaturalOrder(),
                "A", 1,
                "B", 2,
                "C", 3,
                "D", 4);
        PartitionMutableList<Integer> partition = map.partitionWith(Predicates2.in(), map.select(IntegerPredicates.isEven()));
        Assert.assertEquals(iList(4, 2), partition.getSelected());
        Assert.assertEquals(iList(3, 1), partition.getRejected());
    }

    @Override
    @Test
    public void collect_value()
    {
        super.collect_value();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "A", 1, "B", 2, "C", 3);
        MutableList<String> collect = map.collect(Functions.getToString());
        Verify.assertListsEqual(FastList.newListWith("3", "2", "1"), collect);
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndex()
    {
        MutableSortedMap<Integer, String> integers = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");
        Assert.assertEquals(
                Lists.mutable.with(
                        PrimitiveTuples.pair("1", 0),
                        PrimitiveTuples.pair("2", 1),
                        PrimitiveTuples.pair("3", 2),
                        PrimitiveTuples.pair("4", 3)),
                integers.collectWithIndex(PrimitiveTuples::pair));
    }

    /**
     * @since 9.1.
     */
    @Test
    public void collectWithIndexWithTarget()
    {
        MutableSortedMap<Integer, String> integers = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");
        Assert.assertEquals(
                Lists.mutable.with(
                        PrimitiveTuples.pair("1", 0),
                        PrimitiveTuples.pair("2", 1),
                        PrimitiveTuples.pair("3", 2),
                        PrimitiveTuples.pair("4", 3)),
                integers.collectWithIndex(PrimitiveTuples::pair, Lists.mutable.empty()));
    }

    @Override
    @Test
    public void flatten_value()
    {
        super.flatten_value();
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(REV_INT_ORDER, 1, "cd", 2, "ab");

        Function<String, Iterable<Character>> function = object -> {
            MutableList<Character> result = Lists.mutable.empty();
            char[] chars = object.toCharArray();
            for (char aChar : chars)
            {
                result.add(Character.valueOf(aChar));
            }
            return result;
        };
        Verify.assertListsEqual(FastList.newListWith('a', 'b', 'c', 'd'), map.flatCollect(function));
    }

    @Override
    @Test
    public void collectMap()
    {
        super.collectMap();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "1", 1, "2", 2, "3", 3);
        Function2<String, Integer, Pair<Integer, String>> function = (String argument1, Integer argument2) -> Tuples.pair(argument2.intValue(), String.valueOf(argument2));
        MutableMap<Integer, String> collect = map.collect(function);
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "1", 2, "2", 3, "3"), collect);
    }

    @Override
    @Test
    public void selectMap()
    {
        super.selectMap();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "1", 1, "2", 3, "3", 2, "4", 1);
        MutableSortedMap<String, Integer> select = map.select((argument1, argument2) -> argument2 != 1);
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues("2", 3, "3", 2), select);
        Verify.assertListsEqual(FastList.newListWith("3", "2"), select.keySet().toList());
    }

    @Override
    @Test
    public void rejectMap()
    {
        super.rejectMap();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "1", 1, "2", 3, "3", 2, "4", 1);
        MutableSortedMap<String, Integer> select = map.reject((argument1, argument2) -> argument2 == 1);
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues("2", 3, "3", 2), select);
        Verify.assertListsEqual(FastList.newListWith("3", "2"), select.keySet().toList());
    }

    @Override
    @Test
    public void flip()
    {
        super.flip();

        MutableSortedSetMultimap<String, String> expected = TreeSortedSetMultimap.newMultimap(Comparators.reverseNaturalOrder());
        expected.put("odd", "One");
        expected.put("even", "Two");
        expected.put("odd", "Three");
        expected.put("even", "Four");

        MutableSortedMap<String, String> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), "One", "odd", "Two", "even", "Three", "odd", "Four", "even");
        MutableSortedSetMultimap<String, String> flip = map.flip();
        Assert.assertEquals(expected, flip);
        Verify.assertSortedSetsEqual(
                TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder(), "Two", "Four"),
                flip.get("even"));
    }

    @Override
    @Test
    public void collectIf()
    {
        super.collectIf();
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "1", 4, "2", 3, "3", 2, "4", 1);
        MutableList<String> collect = map.collectIf(Predicates.greaterThan(1), String::valueOf);
        Verify.assertListsEqual(FastList.newListWith("2", "3", "4"), collect);
    }

    @Override
    @Test
    public void iterator()
    {
        super.iterator();

        MutableSortedMap<Integer, Integer> map = this.newMapWithKeysValues(REV_INT_ORDER, -1, 1, -2, 2, -3, 3);
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        for (int i = 1; i < 4; ++i)
        {
            Assert.assertEquals(i, iterator.next().intValue());
        }
        Assert.assertFalse(iterator.hasNext());
    }

    @Override
    @Test
    public void removeFromEntrySet()
    {
        // Test without using null

        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Assert.assertTrue(map.entrySet().remove(ImmutableEntry.of("Two", 2)));
        Assert.assertEquals(UnifiedMap.newWithKeysValues("One", 1, "Three", 3), map);

        Assert.assertFalse(map.entrySet().remove(ImmutableEntry.of("Four", 4)));
        Assert.assertEquals(UnifiedMap.newWithKeysValues("One", 1, "Three", 3), map);

        Assert.assertFalse(map.entrySet().remove(null));
    }

    @Override
    @Test
    public void removeAllFromEntrySet()
    {
        // Test without using null

        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Assert.assertTrue(map.entrySet().removeAll(FastList.newListWith(
                ImmutableEntry.of("One", 1),
                ImmutableEntry.of("Three", 3))));
        Assert.assertEquals(UnifiedMap.newWithKeysValues("Two", 2), map);

        Assert.assertFalse(map.entrySet().removeAll(FastList.newListWith(ImmutableEntry.of("Four", 4))));
        Assert.assertEquals(UnifiedMap.newWithKeysValues("Two", 2), map);

        Assert.assertFalse(map.entrySet().remove(null));
    }

    @Override
    @Test
    public void retainAllFromEntrySet()
    {
        super.retainAllFromEntrySet();

        // TODO: delete?

        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Assert.assertFalse(map.entrySet().retainAll(FastList.newListWith(
                ImmutableEntry.of("One", 1),
                ImmutableEntry.of("Two", 2),
                ImmutableEntry.of("Three", 3),
                ImmutableEntry.of("Four", 4))));

        Assert.assertTrue(map.entrySet().retainAll(FastList.newListWith(
                ImmutableEntry.of("One", 1),
                ImmutableEntry.of("Three", 3),
                ImmutableEntry.of("Four", 4))));
        Assert.assertEquals(UnifiedMap.newWithKeysValues("One", 1, "Three", 3), map);
    }

    @Test
    public void entrySet_sorted()
    {
        LazyIterable<Pair<String, Integer>> pairs = Interval.oneTo(100).collect(Functions.pair(Functions.getToString(), Functions.getPassThru()));
        MutableSortedMap<String, Integer> mutableSortedMap = new TreeSortedMap<>(pairs.toArray(new Pair[]{}));
        MutableList<Map.Entry<String, Integer>> entries = FastList.newList(mutableSortedMap.entrySet());
        MutableList<Map.Entry<String, Integer>> sortedEntries = entries.toSortedListBy(Functions.getKeyFunction());
        Assert.assertEquals(sortedEntries, entries);
    }

    @Test
    public void keySet()
    {
        MutableSortedMap<Integer, Integer> map = this.newMapWithKeysValues(1, -1, 2, -2, 3, -3);
        Verify.assertListsEqual(FastList.newListWith(1, 2, 3), map.keySet().toList());
        Verify.assertInstanceOf(MutableSet.class, map.keySet());

        MutableSortedMap<Integer, Integer> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, -1, 2, -2, 3, -3);
        Verify.assertListsEqual(FastList.newListWith(3, 2, 1), revMap.keySet().toList());
    }

    @Override
    @Test
    public void keySetEqualsAndHashCode()
    {
        // Test without using null

        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Verify.assertEqualsAndHashCode(TreeSortedSet.newSetWith("One", "Two", "Three"), map.keySet());
    }

    @Override
    @Test
    public void put()
    {
        // Only use Comparable objects

        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two");
        Assert.assertNull(map.put(3, "Three"));
        Assert.assertEquals(TreeSortedMap.newMapWith(1, "One", 2, "Two", 3, "Three"), map);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 2, "Two");
        Assert.assertNull(revMap.put(0, "Zero"));
        Assert.assertEquals(TreeSortedMap.<Integer, String>newMap(REV_INT_ORDER).with(0, "Zero", 1, "One", 2, "Two"), revMap);
    }

    @Override
    @Test
    public void putAll()
    {
        super.putAll();

        // TODO: delete?

        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "2");
        MutableSortedMap<Integer, String> toAdd = this.newMapWithKeysValues(2, "Two", 3, "Three");
        map.putAll(toAdd);
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "One", 2, "Two", 3, "Three"), map);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 2, "2");
        revMap.putAll(toAdd);
        Assert.assertEquals(TreeSortedMap.<Integer, String>newMap(REV_INT_ORDER).with(1, "One", 2, "Two", 3, "Three"), revMap);
    }

    @Test
    public void putAllFromCollection()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        MutableList<Integer> toAdd = FastList.newListWith(2, 3);
        map.collectKeysAndValues(toAdd, Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertMapsEqual(UnifiedMap.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "1", 2, "Two");
        revMap.collectKeysAndValues(toAdd, Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertSortedMapsEqual(TreeSortedMap.<Integer, String>newMap(REV_INT_ORDER).with(1, "1", 2, "2", 3, "3"), revMap);
    }

    @Test
    public void testEquals()
    {
        MutableSortedMap<Integer, String> map1 = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        MutableSortedMap<Integer, String> map2 = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        MutableSortedMap<Integer, String> map3 = this.newMapWithKeysValues(2, "2", 3, "3", 4, "4");
        MutableSortedMap<Integer, String> revMap1 = this.newMapWithKeysValues(REV_INT_ORDER, 1, "1", 2, "2", 3, "3");
        MutableSortedMap<Integer, String> revMap3 = this.newMapWithKeysValues(REV_INT_ORDER, 2, "2", 3, "3", 4, "4");

        Verify.assertSortedMapsEqual(map1, map2);
        Verify.assertMapsEqual(revMap1, map2);
        Verify.assertMapsEqual(revMap3, map3);

        Assert.assertNotEquals(map2, map3);
        Assert.assertNotEquals(revMap1, revMap3);
        Assert.assertNotEquals(map1, revMap3);
    }

    @Test
    public void testHashCode()
    {
        MutableSortedMap<Integer, String> map1 = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        MutableSortedMap<Integer, String> map2 = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        Verify.assertEqualsAndHashCode(map1, map2);
    }

    @Override
    @Test
    public void equalsAndHashCode()
    {
        super.equalsAndHashCode();

        MapIterable<Integer, String> map = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 3, "3");
        Verify.assertPostSerializedEqualsAndHashCode(map);
        Verify.assertEqualsAndHashCode(Maps.mutable.of(1, "1", 2, "2", 3, "3"), map);
        Verify.assertEqualsAndHashCode(Maps.immutable.of(1, "1", 2, "2", 3, "3"), map);

        Assert.assertNotEquals(map, this.newMapWithKeysValues(1, "1", 2, "2"));
        Assert.assertNotEquals(map, this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4"));
        Assert.assertNotEquals(map, this.newMapWithKeysValues(1, "1", 2, "2", 4, "4"));

        Assert.assertNotEquals(map, this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, "1", 2, "2"));
        Assert.assertNotEquals(map, this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 3, "3", 4, "4"));
        Assert.assertNotEquals(map, this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 4, "4"));
    }

    @Override
    @Test
    public void serialization()
    {
        super.serialization();

        MutableSortedMap<Integer, String> original = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        MutableSortedMap<Integer, String> copy = SerializeTestHelper.serializeDeserialize(original);
        Verify.assertSortedMapsEqual(original, copy);

        MutableSortedMap<Integer, String> revMap = this.newMapWithKeysValues(REV_INT_ORDER, 1, "One", 2, "Two");
        MutableSortedMap<Integer, String> deserialized = SerializeTestHelper.serializeDeserialize(revMap);
        Verify.assertSortedMapsEqual(revMap, deserialized);
        Verify.assertListsEqual(FastList.newListWith(2, 1), deserialized.keySet().toList());
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        super.asUnmodifiable();

        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues(1, 1, 2, 2).asUnmodifiable().put(3, 3));

        Verify.assertInstanceOf(UnmodifiableTreeMap.class, this.newMapWithKeysValues(1, "1", 2, "2").asUnmodifiable());
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();

        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two").asSynchronized();

        Verify.assertInstanceOf(SynchronizedSortedMap.class, map);
    }

    @Test
    public void firstKey()
    {
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Four", 4);
        Assert.assertEquals("Four", map.firstKey());

        MutableSortedMap<String, Integer> revMap = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "One", 1, "Two", 2, "Four", 4);
        Assert.assertEquals("Two", revMap.firstKey());

        MutableSortedMap<Object, Object> emptyMap = this.newMap();
        Verify.assertThrows(NoSuchElementException.class, (Runnable) emptyMap::firstKey);
    }

    @Test
    public void lastKey()
    {
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Four", 4);
        Assert.assertEquals("Two", map.lastKey());

        MutableSortedMap<String, Integer> revMap = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(),
                "One", 1, "Two", 2, "Four", 4);
        Assert.assertEquals("Four", revMap.lastKey());

        MutableSortedMap<Object, Object> emptyMap = this.newMap();
        Verify.assertThrows(NoSuchElementException.class, (Runnable) emptyMap::lastKey);
    }

    @Test
    public void headMap()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
        MutableSortedMap<Integer, String> subMap = map.headMap(3);

        Verify.assertSortedMapsEqual(TreeSortedMap.newMapWith(1, "One", 2, "Two"), subMap);
        Verify.assertListsEqual(FastList.newListWith(1, 2), subMap.keySet().toList());

        subMap.put(0, "Zero");
        Verify.assertContainsKeyValue(0, "Zero", map);

        subMap.removeKey(2);
        Verify.assertNotContainsKey(2, map);

        map.clear();
        Verify.assertEmpty(subMap);

        Verify.assertThrows(IllegalArgumentException.class, () -> subMap.put(4, "Illegal"));
    }

    @Test
    public void tailMap()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
        MutableSortedMap<Integer, String> subMap = map.tailMap(2);

        Verify.assertSortedMapsEqual(TreeSortedMap.newMapWith(2, "Two", 3, "Three", 4, "Four"), subMap);
        Verify.assertListsEqual(FastList.newListWith(2, 3, 4), subMap.keySet().toList());

        subMap.put(5, "Five");
        Verify.assertContainsKeyValue(5, "Five", map);

        subMap.removeKey(2);
        Verify.assertNotContainsKey(2, map);

        map.clear();
        Verify.assertEmpty(subMap);

        Verify.assertThrows(IllegalArgumentException.class, () -> subMap.put(1, "Illegal"));
    }

    @Test
    public void subMap()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three", 4, "Four");
        MutableSortedMap<Integer, String> subMap = map.subMap(2, 4);

        Verify.assertSortedMapsEqual(TreeSortedMap.newMapWith(3, "Three", 2, "Two"), subMap);
        Verify.assertListsEqual(FastList.newListWith(2, 3), subMap.keySet().toList());

        map.clear();
        Verify.assertEmpty(subMap);
        Verify.assertEmpty(map);

        subMap.put(2, "Two");
        map.put(3, "Three");
        Verify.assertContainsKeyValue(2, "Two", map);
        Verify.assertContainsKeyValue(3, "Three", subMap);

        subMap.removeKey(2);
        Verify.assertNotContainsKey(2, map);

        Verify.assertThrows(IllegalArgumentException.class, () -> subMap.put(4, "Illegal"));

        Verify.assertThrows(IllegalArgumentException.class, () -> subMap.put(1, "Illegal"));
    }

    @Test
    public void testToString()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two");
        Assert.assertEquals("{1=One, 2=Two}", map.toString());
    }

    @Test
    public void testClone()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "Two");
        MutableSortedMap<Integer, String> clone = map.clone();
        Assert.assertNotSame(map, clone);
        Verify.assertEqualsAndHashCode(map, clone);
    }

    @Test
    public void take()
    {
        MutableSortedMap<Integer, String> strings1 = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");
        Assert.assertEquals(SortedMaps.mutable.of(strings1.comparator()), strings1.take(0));
        Assert.assertSame(strings1.comparator(), strings1.take(0).comparator());
        Assert.assertEquals(SortedMaps.mutable.of(strings1.comparator(), 1, "1", 2, "2", 3, "3"), strings1.take(3));
        Assert.assertSame(strings1.comparator(), strings1.take(3).comparator());
        Assert.assertEquals(SortedMaps.mutable.of(strings1.comparator(), 1, "1", 2, "2", 3, "3"), strings1.take(strings1.size() - 1));

        MutableSortedMap<Integer, String> expectedMap = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 3, "3", 4, "4");
        MutableSortedMap<Integer, String> strings2 = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 3, "3", 4, "4");
        Assert.assertEquals(expectedMap, strings2.take(strings2.size()));
        Assert.assertEquals(expectedMap, strings2.take(10));
        Assert.assertEquals(expectedMap, strings2.take(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void take_throws()
    {
        this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4").take(-1);
    }

    @Test
    public void drop()
    {
        MutableSortedMap<Integer, String> strings1 = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");
        Assert.assertEquals(strings1, strings1.drop(0));
        Assert.assertNotSame(strings1, strings1.drop(0));
        Assert.assertSame(strings1.comparator(), strings1.drop(0).comparator());
        Assert.assertEquals(SortedMaps.mutable.of(strings1.comparator(), 4, "4"), strings1.drop(3));
        Assert.assertSame(strings1.comparator(), strings1.drop(3).comparator());
        Assert.assertEquals(SortedMaps.mutable.of(strings1.comparator(), 4, "4"), strings1.drop(strings1.size() - 1));

        MutableSortedMap<Integer, String> expectedMap = SortedMaps.mutable.of(Comparators.reverseNaturalOrder());
        MutableSortedMap<Integer, String> strings2 = this.newMapWithKeysValues(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 3, "3", 4, "4");
        Assert.assertEquals(expectedMap, strings2.drop(strings2.size()));
        Assert.assertEquals(expectedMap, strings2.drop(10));
        Assert.assertEquals(expectedMap, strings2.drop(Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void drop_throws()
    {
        this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4").drop(-1);
    }
}
