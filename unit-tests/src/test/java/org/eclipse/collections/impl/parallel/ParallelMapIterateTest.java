/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ParallelMapIterateTest
{
    @Test
    public void forEachKeyValueWithNoParameters()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        MutableMap<String, String> map = UnifiedMap.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        ParallelMapIterate.forEachKeyValue(map, concurrentMap::put);
        Verify.assertMapsEqual(concurrentMap, map);
    }

    @Test
    public void forEachKeyValueWithExecutor()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        MutableMap<String, String> map = UnifiedMap.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        ParallelMapIterate.forEachKeyValue(map, concurrentMap::put, Executors.newSingleThreadExecutor());
        Verify.assertMapsEqual(concurrentMap, map);
    }

    @Test
    public void forEachKeyValueWithMinForkSizeTaskCountAndExecutorParallel()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        MutableMap<String, String> map = UnifiedMap.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        ParallelMapIterate.forEachKeyValue(map, concurrentMap::put, 1, 3, Executors.newSingleThreadExecutor());
        Verify.assertMapsEqual(concurrentMap, map);
    }

    @Test
    public void forEachKeyValueWithMinForkSizeTaskCountAndExecutorSerial()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        MutableMap<String, String> map = UnifiedMap.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        ParallelMapIterate.forEachKeyValue(map, concurrentMap::put, 5, 3, Executors.newSingleThreadExecutor());
        Verify.assertMapsEqual(concurrentMap, map);
    }

    @Test
    public void forEachKeyValueWithMinForkSizeAndTaskCountParallel()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        MutableMap<String, String> map = UnifiedMap.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        ParallelMapIterate.forEachKeyValue(map, concurrentMap::put, 1, 3);
        Verify.assertMapsEqual(concurrentMap, map);
    }

    @Test
    public void forEachKeyValueWithMinForkSizeAndTaskCountSerial()
    {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        MutableMap<String, String> map = UnifiedMap.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        ParallelMapIterate.forEachKeyValue(map, concurrentMap::put, 5, 3);
        Verify.assertMapsEqual(concurrentMap, map);
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(ParallelMapIterate.class);
    }
}
