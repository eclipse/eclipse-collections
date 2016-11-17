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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.parallel.ParallelIterate;
import org.eclipse.collections.impl.test.Verify;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for {@link ConcurrentHashMap}.
 */
public class ConcurrentHashMapAcceptanceTest
{
    private static final MutableMap<Integer, MutableBag<Integer>> BAG_MUTABLE_MAP = Interval.oneTo(1000).groupBy(each -> each % 100).toMap(HashBag::new);

    private ExecutorService executor;

    @Before
    public void setUp()
    {
        this.executor = Executors.newFixedThreadPool(20);
    }

    @After
    public void tearDown()
    {
        this.executor.shutdown();
    }

    @Test
    public void parallelGroupByIntoConcurrentHashMap()
    {
        MutableMap<Integer, MutableBag<Integer>> actual = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(
                Interval.oneTo(1000000),
                each -> actual.getIfAbsentPut(each % 100000, () -> HashBag.<Integer>newBag().asSynchronized()).add(each),
                10,
                this.executor);
        Verify.assertEqualsAndHashCode(Interval.oneTo(1000000).groupBy(each -> each % 100000).toMap(HashBag::new), actual);
    }

    @Test
    public void parallelForEachValue()
    {
        ConcurrentHashMap<Integer, Integer> source =
                ConcurrentHashMap.newMap(Interval.oneTo(1000).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        MutableMap<Integer, MutableBag<Integer>> actual = ConcurrentHashMap.newMap();
        Procedure<Integer> procedure = each -> actual.getIfAbsentPut(each % 100, () -> HashBag.<Integer>newBag().asSynchronized()).add(each);
        source.parallelForEachValue(FastList.newList(Collections.nCopies(5, procedure)), this.executor);
        Verify.assertEqualsAndHashCode(BAG_MUTABLE_MAP, actual);
    }

    @Test
    public void parallelForEachEntry()
    {
        ConcurrentHashMap<Integer, Integer> source =
                ConcurrentHashMap.newMap(Interval.oneTo(1000).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        MutableMap<Integer, MutableBag<Integer>> actual = ConcurrentHashMap.newMap();
        Procedure2<Integer, Integer> procedure2 = (key, value) -> actual.getIfAbsentPut(value % 100, () -> HashBag.<Integer>newBag().asSynchronized()).add(value);
        source.parallelForEachKeyValue(FastList.newList(Collections.nCopies(5, procedure2)), this.executor);
        Verify.assertEqualsAndHashCode(BAG_MUTABLE_MAP, actual);
    }

    @Test
    public void putAllInParallelSmallMap()
    {
        ConcurrentHashMap<Integer, Integer> source = ConcurrentHashMap.newMap(Interval.oneTo(1000).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        ConcurrentHashMap<Integer, Integer> target = ConcurrentHashMap.newMap();
        target.putAllInParallel(source, 10, this.executor);
        Verify.assertEqualsAndHashCode(source, target);
    }

    @Test
    public void putAllInParallelLargeMap()
    {
        ConcurrentHashMap<Integer, Integer> source = ConcurrentHashMap.newMap(Interval.oneTo(60000).toMap(Functions.getIntegerPassThru(), Functions.getIntegerPassThru()));
        ConcurrentHashMap<Integer, Integer> target = ConcurrentHashMap.newMap();
        target.putAllInParallel(source, 10, this.executor);
        Verify.assertEqualsAndHashCode(source, target);
    }

    @Test
    public void concurrentPutGetPutAllRemoveContainsKeyContainsValueGetIfAbsentPutTest()
    {
        ConcurrentHashMap<Integer, Integer> map1 = ConcurrentHashMap.newMap();
        ConcurrentHashMap<Integer, Integer> map2 = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(1000), each -> {
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
        }, 10, this.executor);
        Verify.assertEqualsAndHashCode(map1, map2);
    }

    @Test
    public void concurrentPutIfAbsentGetIfPresentPutTest()
    {
        ConcurrentHashMap<Integer, Integer> map1 = ConcurrentHashMap.newMap();
        ConcurrentHashMap<Integer, Integer> map2 = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(1000), each -> {
            map1.put(each, each);
            map1.put(each, each);
            Assert.assertEquals(each, map1.get(each));
            map2.putAll(Maps.mutable.of(each, each));
            map2.putAll(Maps.mutable.of(each, each));
            map1.remove(each);
            Assert.assertNull(map1.putIfAbsentGetIfPresent(each, new KeyTransformer(), new ValueFactory(), null, null));
            Assert.assertEquals(each, map1.putIfAbsentGetIfPresent(each, new KeyTransformer(), new ValueFactory(), null, null));
        }, 10, this.executor);
        Assert.assertEquals(map1, map2);
    }

    @Test
    public void concurrentClear()
    {
        ConcurrentHashMap<Integer, Integer> map = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(1000), each -> {
            for (int i = 0; i < each; i++)
            {
                map.put(each + i * 1000, each);
            }
            map.clear();
            for (int i = 0; i < 100; i++)
            {
                map.put(each + i * 1000, each);
            }
            map.clear();
        }, 10, this.executor);
        Verify.assertEmpty(map);
    }

    @Test
    public void concurrentRemoveAndPutIfAbsent()
    {
        ConcurrentHashMap<Integer, Integer> map1 = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(1000), each -> {
            Assert.assertNull(map1.put(each, each));
            map1.remove(each);
            Assert.assertNull(map1.get(each));
            Assert.assertEquals(each, map1.getIfAbsentPut(each, Functions.getIntegerPassThru()));
            map1.remove(each);
            Assert.assertNull(map1.get(each));
            Assert.assertEquals(each, map1.getIfAbsentPutWith(each, Functions.getIntegerPassThru(), each));
            map1.remove(each);
            Assert.assertNull(map1.get(each));
            for (int i = 0; i < each; i++)
            {
                Assert.assertNull(map1.putIfAbsent(each + i * 1000, each));
            }
            for (int i = 0; i < each; i++)
            {
                Assert.assertEquals(each, map1.putIfAbsent(each + i * 1000, each));
            }
            for (int i = 0; i < each; i++)
            {
                Assert.assertEquals(each, map1.remove(each + i * 1000));
            }
        }, 10, this.executor);
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

    @Test
    public void size()
    {
        ConcurrentHashMap<Integer, Integer> map = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(10_000), each -> map.put(each, each));
        Assert.assertEquals(10_000, map.size());
        Assert.assertEquals(10_000, map.keySet().size());
        Assert.assertEquals(10_000, map.values().size());
        Assert.assertEquals(10_000, map.entrySet().size());
    }

    @Test
    public void size_entrySet()
    {
        ConcurrentHashMap<Integer, Integer> map = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(10_000), each -> map.put(each, each));
        Assert.assertEquals(10_000, map.entrySet().size());
    }

    @Test
    public void size_keySet()
    {
        ConcurrentHashMap<Integer, Integer> map = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(10_000), each -> map.put(each, each));
        Assert.assertEquals(10_000, map.keySet().size());
    }

    @Test
    public void size_values()
    {
        ConcurrentHashMap<Integer, Integer> map = ConcurrentHashMap.newMap();
        ParallelIterate.forEach(Interval.oneTo(10_000), each -> map.put(each, each));
        Assert.assertEquals(10_000, map.values().size());
    }
}
