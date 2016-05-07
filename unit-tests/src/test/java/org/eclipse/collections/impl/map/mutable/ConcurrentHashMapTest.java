/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import java.util.Collections;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.parallel.ParallelIterate;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iSet;

/**
 * JUnit test for {@link ConcurrentHashMap}.
 */
public class ConcurrentHashMapTest extends ConcurrentHashMapTestCase
{
    public static final MutableMap<Integer, MutableBag<Integer>> SMALL_BAG_MUTABLE_MAP = Interval.oneTo(100).groupBy(each -> each % 10).toMap(HashBag::new);

    @Override
    public <K, V> ConcurrentMutableMap<K, V> newMap()
    {
        return ConcurrentHashMap.newMap();
    }

    @Override
    public <K, V> ConcurrentMutableMap<K, V> newMapWithKeyValue(K key, V value)
    {
        return ConcurrentHashMap.<K, V>newMap().withKeyValue(key, value);
    }

    @Override
    public <K, V> ConcurrentMutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return ConcurrentHashMap.<K, V>newMap().withKeyValue(key1, value1).withKeyValue(key2, value2);
    }

    @Override
    public <K, V> ConcurrentMutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return ConcurrentHashMap.<K, V>newMap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3);
    }

    @Override
    public <K, V> ConcurrentMutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return ConcurrentHashMap.<K, V>newMap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3)
                .withKeyValue(key4, value4);
    }

    @Test
    public void doubleReverseTest()
    {
        FastList<String> source = FastList.newListWith("1", "2", "3");
        MutableList<String> expectedDoubleReverse = source.toReversed().collect(new Function<String, String>()
        {
            private String visited = "";

            public String valueOf(String object)
            {
                return this.visited += object;
            }
        }).toReversed();
        Assert.assertEquals(FastList.newListWith("321", "32", "3"), expectedDoubleReverse);
        MutableList<String> expectedNormal = source.collect(new Function<String, String>()
        {
            private String visited = "";

            public String valueOf(String object)
            {
                return this.visited += object;
            }
        });
        Assert.assertEquals(FastList.newListWith("1", "12", "123"), expectedNormal);
    }

    @Test
    public void putIfAbsent()
    {
        ConcurrentMutableMap<Integer, Integer> map = this.newMapWithKeysValues(1, 1, 2, 2);
        Assert.assertEquals(Integer.valueOf(1), map.putIfAbsent(1, 1));
        Assert.assertNull(map.putIfAbsent(3, 3));
    }

    @Test
    public void replace()
    {
        ConcurrentMutableMap<Integer, Integer> map = this.newMapWithKeysValues(1, 1, 2, 2);
        Assert.assertEquals(Integer.valueOf(1), map.replace(1, 7));
        Assert.assertEquals(Integer.valueOf(7), map.get(1));
        Assert.assertNull(map.replace(3, 3));
    }

    @Test
    public void entrySetContains()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("One", Integer.valueOf(1), "Two", Integer.valueOf(2), "Three", Integer.valueOf(3));
        Assert.assertFalse(map.entrySet().contains(null));
        Assert.assertFalse(map.entrySet().contains(ImmutableEntry.of("Zero", Integer.valueOf(0))));
        Assert.assertTrue(map.entrySet().contains(ImmutableEntry.of("One", Integer.valueOf(1))));
    }

    @Test
    public void entrySetRemove()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("One", Integer.valueOf(1), "Two", Integer.valueOf(2), "Three", Integer.valueOf(3));
        Assert.assertFalse(map.entrySet().remove(null));
        Assert.assertFalse(map.entrySet().remove(ImmutableEntry.of("Zero", Integer.valueOf(0))));
        Assert.assertTrue(map.entrySet().remove(ImmutableEntry.of("One", Integer.valueOf(1))));
    }

    @Test
    public void replaceWithOldValue()
    {
        ConcurrentMutableMap<Integer, Integer> map = this.newMapWithKeysValues(1, 1, 2, 2);
        Assert.assertTrue(map.replace(1, 1, 7));
        Assert.assertEquals(Integer.valueOf(7), map.get(1));
        Assert.assertFalse(map.replace(2, 3, 3));
    }

    @Test
    public void removeWithKeyValue()
    {
        ConcurrentMutableMap<Integer, Integer> map = this.newMapWithKeysValues(1, 1, 2, 2);
        Assert.assertTrue(map.remove(1, 1));
        Assert.assertFalse(map.remove(2, 3));
    }

    @Override
    @Test
    public void removeFromEntrySet()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Assert.assertTrue(map.entrySet().remove(ImmutableEntry.of("Two", 2)));
        Assert.assertEquals(UnifiedMap.newWithKeysValues("One", 1, "Three", 3), map);

        Assert.assertFalse(map.entrySet().remove(ImmutableEntry.of("Four", 4)));
        Verify.assertEqualsAndHashCode(UnifiedMap.newWithKeysValues("One", 1, "Three", 3), map);
    }

    @Override
    @Test
    public void removeAllFromEntrySet()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Assert.assertTrue(map.entrySet().removeAll(FastList.newListWith(
                ImmutableEntry.of("One", 1),
                ImmutableEntry.of("Three", 3))));
        Assert.assertEquals(UnifiedMap.newWithKeysValues("Two", 2), map);

        Assert.assertFalse(map.entrySet().removeAll(FastList.newListWith(ImmutableEntry.of("Four", 4))));
        Verify.assertEqualsAndHashCode(UnifiedMap.newWithKeysValues("Two", 2), map);
    }

    @Override
    @Test
    public void keySetEqualsAndHashCode()
    {
        MutableMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Verify.assertEqualsAndHashCode(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());
    }

    @Test
    public void equalsEdgeCases()
    {
        Assert.assertNotEquals(ConcurrentHashMap.newMap().withKeyValue(1, 1), ConcurrentHashMap.newMap());
        Assert.assertNotEquals(ConcurrentHashMap.newMap().withKeyValue(1, 1), ConcurrentHashMap.newMap().withKeyValue(1, 1).withKeyValue(2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeInitialSize()
    {
        ConcurrentHashMap.newMap(-1);
    }

    @Override
    @Test
    public void partition_value()
    {
        MapIterable<String, Integer> map = this.newMapWithKeysValues(
                "A", 1,
                "B", 2,
                "C", 3,
                "D", 4);
        PartitionIterable<Integer> partition = map.partition(IntegerPredicates.isEven());
        Assert.assertEquals(iSet(2, 4), partition.getSelected().toSet());
        Assert.assertEquals(iSet(1, 3), partition.getRejected().toSet());
    }

    @Override
    @Test
    public void partitionWith_value()
    {
        MapIterable<String, Integer> map = this.newMapWithKeysValues(
                "A", 1,
                "B", 2,
                "C", 3,
                "D", 4);
        PartitionIterable<Integer> partition = map.partitionWith(Predicates2.in(), map.select(IntegerPredicates.isEven()));
        Assert.assertEquals(iSet(2, 4), partition.getSelected().toSet());
        Assert.assertEquals(iSet(1, 3), partition.getRejected().toSet());
    }

    @Test
    public void parallelGroupByIntoConcurrentHashMap()
    {
        MutableMap<Integer, MutableBag<Integer>> actual = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(100), each -> actual.getIfAbsentPut(each % 10, () -> HashBag.<Integer>newBag().asSynchronized()).add(each), 10, this.executor);
        Verify.assertEqualsAndHashCode(SMALL_BAG_MUTABLE_MAP, actual);
    }

    @Test
    public void parallelForEachValue()
    {
        ConcurrentHashMap<Integer, Integer> source =
                ConcurrentHashMap.newMap(Interval.oneTo(100).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        MutableMap<Integer, MutableBag<Integer>> actual = ConcurrentHashMap.newMap();
        Procedure<Integer> procedure = each -> actual.getIfAbsentPut(each % 10, () -> HashBag.<Integer>newBag().asSynchronized()).add(each);
        source.parallelForEachValue(FastList.newList(Collections.nCopies(5, procedure)), this.executor);
        Verify.assertEqualsAndHashCode(SMALL_BAG_MUTABLE_MAP, actual);
    }

    @Test
    public void parallelForEachEntry()
    {
        ConcurrentHashMap<Integer, Integer> source =
                ConcurrentHashMap.newMap(Interval.oneTo(100).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        MutableMap<Integer, MutableBag<Integer>> actual = ConcurrentHashMap.newMap();
        Procedure2<Integer, Integer> procedure2 = (key, value) -> actual.getIfAbsentPut(value % 10, () -> HashBag.<Integer>newBag().asSynchronized()).add(value);
        source.parallelForEachKeyValue(FastList.newList(Collections.nCopies(5, procedure2)), this.executor);
        Verify.assertEqualsAndHashCode(SMALL_BAG_MUTABLE_MAP, actual);
    }

    @Test
    public void putAllInParallelSmallMap()
    {
        ConcurrentHashMap<Integer, Integer> source = ConcurrentHashMap.newMap(Interval.oneTo(100).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        ConcurrentHashMap<Integer, Integer> target = ConcurrentHashMap.newMap();
        target.putAllInParallel(source, 100, this.executor);
        Verify.assertEqualsAndHashCode(source, target);
    }

    @Test
    public void putAllInParallelLargeMap()
    {
        ConcurrentHashMap<Integer, Integer> source = ConcurrentHashMap.newMap(Interval.oneTo(600).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        ConcurrentHashMap<Integer, Integer> target = ConcurrentHashMap.newMap();
        target.putAllInParallel(source, 100, this.executor);
        Verify.assertEqualsAndHashCode(source, target);
    }

    @Test
    public void concurrentPutGetPutAllRemoveContainsKeyContainsValueGetIfAbsentPutTest()
    {
        ConcurrentHashMap<Integer, Integer> map1 = ConcurrentHashMap.newMap();
        ConcurrentHashMap<Integer, Integer> map2 = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(100), each -> {
            map1.put(each, each);
            Assert.assertEquals(each, map1.get(each));
            map2.putAll(Maps.mutable.of(each, each));
            map1.remove(each);
            map1.putAll(Maps.mutable.of(each, each));
            Assert.assertEquals(each, map2.get(each));
            map2.remove(each);
            Assert.assertNull(map2.get(each));
            Assert.assertFalse(map2.containsValue(each));
            Assert.assertFalse(map2.containsKey(each));
            Assert.assertEquals(each, map2.getIfAbsentPut(each, Functions.getIntegerPassThru()));
            Assert.assertTrue(map2.containsValue(each));
            Assert.assertTrue(map2.containsKey(each));
            Assert.assertEquals(each, map2.getIfAbsentPut(each, Functions.getIntegerPassThru()));
            map2.remove(each);
            Assert.assertEquals(each, map2.getIfAbsentPutWith(each, Functions.getIntegerPassThru(), each));
            Assert.assertEquals(each, map2.getIfAbsentPutWith(each, Functions.getIntegerPassThru(), each));
            Assert.assertEquals(each, map2.getIfAbsentPut(each, Functions.getIntegerPassThru()));
        }, 1, this.executor);
        Verify.assertEqualsAndHashCode(map1, map2);
    }

    @Test
    public void concurrentPutIfAbsentGetIfPresentPutTest()
    {
        ConcurrentHashMap<Integer, Integer> map1 = ConcurrentHashMap.newMap();
        ConcurrentHashMap<Integer, Integer> map2 = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(100), each -> {
            map1.put(each, each);
            map1.put(each, each);
            Assert.assertEquals(each, map1.get(each));
            map2.putAll(Maps.mutable.of(each, each));
            map2.putAll(Maps.mutable.of(each, each));
            map1.remove(each);
            Assert.assertNull(map1.putIfAbsentGetIfPresent(each, new KeyTransformer(), new ValueFactory(), null, null));
            Assert.assertEquals(each, map1.putIfAbsentGetIfPresent(each, new KeyTransformer(), new ValueFactory(), null, null));
        }, 1, this.executor);
        Assert.assertEquals(map1, map2);
    }

    @Test
    public void concurrentClear()
    {
        ConcurrentHashMap<Integer, Integer> map = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(100), each -> {
            for (int i = 0; i < 10; i++)
            {
                map.put(each + i * 1000, each);
            }
            map.clear();
        }, 1, this.executor);
        Verify.assertEmpty(map);
    }

    @Test
    public void concurrentRemoveAndPutIfAbsent()
    {
        ConcurrentHashMap<Integer, Integer> map1 = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(100), each -> {
            Assert.assertNull(map1.put(each, each));
            map1.remove(each);
            Assert.assertNull(map1.get(each));
            Assert.assertEquals(each, map1.getIfAbsentPut(each, Functions.getIntegerPassThru()));
            map1.remove(each);
            Assert.assertNull(map1.get(each));
            Assert.assertEquals(each, map1.getIfAbsentPutWith(each, Functions.getIntegerPassThru(), each));
            map1.remove(each);
            Assert.assertNull(map1.get(each));
            for (int i = 0; i < 10; i++)
            {
                Assert.assertNull(map1.putIfAbsent(each + i * 1000, each));
            }
            for (int i = 0; i < 10; i++)
            {
                Assert.assertEquals(each, map1.putIfAbsent(each + i * 1000, each));
            }
            for (int i = 0; i < 10; i++)
            {
                Assert.assertEquals(each, map1.remove(each + i * 1000));
            }
        }, 1, this.executor);
    }

    @Test
    public void emptyToString()
    {
        ConcurrentHashMap<?, ?> empty = ConcurrentHashMap.newMap(0);
        Assert.assertEquals("{}", empty.toString());
    }

    private static class KeyTransformer implements Function2<Integer, Integer, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer key, Integer value)
        {
            return key;
        }
    }

    private static class ValueFactory implements Function3<Object, Object, Integer, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Object argument1, Object argument2, Integer key)
        {
            return key;
        }
    }
}
