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

import org.eclipse.collections.api.map.MutableMap;

/**
 * JUnit test for {@link SynchronizedMutableMap}.
 */
public class SynchronizedMutableMapCustomLockTest extends MutableMapTestCase
{
    private static final Object LOCK = "lock";

    @Override
    public <K, V> MutableMap<K, V> newMap()
    {
        return new SynchronizedMutableMap<>(UnifiedMap.newMap(), LOCK);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeyValue(K key, V value)
    {
        return new SynchronizedMutableMap<>(UnifiedMap.newWithKeysValues(key, value), LOCK);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return new SynchronizedMutableMap<>(UnifiedMap.newWithKeysValues(key1, value1, key2, value2), LOCK);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new SynchronizedMutableMap<>(UnifiedMap.newWithKeysValues(key1, value1, key2, value2, key3, value3), LOCK);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return new SynchronizedMutableMap<>(UnifiedMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4), LOCK);
    }
}
