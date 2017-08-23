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
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link SortedMapAdapter}.
 */
public class SortedMapAdapterTest extends MutableSortedMapTestCase
{
    @Override
    public <K, V> MutableSortedMap<K, V> newMap(Comparator<? super K> comparator)
    {
        return SortedMapAdapter.adapt(new TreeMap<>(comparator));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeyValue(Comparator<? super K> comparator, K key, V value)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>(comparator)).with(Tuples.pair(key, value));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>(comparator)).with(Tuples.pair(key1, value1), Tuples.pair(key2, value2));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>(comparator)).with(Tuples.pair(key1, value1), Tuples.pair(key2, value2), Tuples.pair(key3, value3));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>(comparator)).with(Tuples.pair(key1, value1), Tuples.pair(key2, value2), Tuples.pair(key3, value3), Tuples.pair(key4, value4));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMap()
    {
        return SortedMapAdapter.adapt(new TreeMap<>());
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeyValue(K key, V value)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>()).with(Tuples.pair(key, value));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>()).with(Tuples.pair(key1, value1), Tuples.pair(key2, value2));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>()).with(Tuples.pair(key1, value1), Tuples.pair(key2, value2), Tuples.pair(key3, value3));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return SortedMapAdapter.adapt(new TreeMap<K, V>()).with(Tuples.pair(key1, value1), Tuples.pair(key2, value2), Tuples.pair(key3, value3), Tuples.pair(key4, value4));
    }

    @Test(expected = NullPointerException.class)
    public void testNewNull()
    {
        SortedMapAdapter.adapt(null);
    }

    @Test
    public void testAdapt()
    {
        TreeSortedMap<Integer, String> sortedMap = TreeSortedMap.newMapWith(1, "1", 2, "2");
        MutableSortedMap<Integer, String> adapt = SortedMapAdapter.adapt(sortedMap);
        Assert.assertSame(sortedMap, adapt);

        SortedMap<Integer, String> treeMap = new TreeMap<>(sortedMap);
        MutableSortedMap<Integer, String> treeAdapt = SortedMaps.adapt(treeMap);
        Assert.assertNotSame(treeMap, treeAdapt);
        Assert.assertEquals(treeMap, treeAdapt);
    }
}
