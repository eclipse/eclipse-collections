/*
 * Copyright (c) 2016 Bhavana Hindupur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import org.eclipse.collections.api.bimap.MutableBiMap;

/**
 * JUnit test for {@link SynchronizedBiMap}.
 */
public class SynchronizedBiMapCustomLockTest extends AbstractMutableBiMapTestCase
{
    private static final Object LOCK = "lock";

    @Override
    public MutableBiMap<Integer, Character> classUnderTest()
    {
        HashBiMap<Integer, Character> map = HashBiMap.newMap();
        map.put(1, null);
        map.put(null, 'b');
        map.put(3, 'c');
        return new SynchronizedBiMap<>(map, LOCK);
    }

    @Override
    public MutableBiMap<Integer, Character> getEmptyMap()
    {
        return new SynchronizedBiMap<>(HashBiMap.newMap(), LOCK);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMap()
    {
        return new SynchronizedBiMap<>(HashBiMap.newMap(), LOCK);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeyValue(K key, V value)
    {
        return new SynchronizedBiMap<>(HashBiMap.newWithKeysValues(key, value), LOCK);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        HashBiMap<K, V> map = HashBiMap.newWithKeysValues(key1, value1, key2, value2);
        return new SynchronizedBiMap<>(map, LOCK);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new SynchronizedBiMap<>(HashBiMap.newWithKeysValues(key1, value1, key2, value2, key3, value3), LOCK);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return new SynchronizedBiMap<>(HashBiMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4), LOCK);
    }
}
