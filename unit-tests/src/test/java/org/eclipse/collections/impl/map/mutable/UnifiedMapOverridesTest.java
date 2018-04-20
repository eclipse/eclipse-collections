/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import java.util.Map;

import org.eclipse.collections.api.map.MutableMap;

public class UnifiedMapOverridesTest extends UnifiedMapTest
{
    public static class UnifiedMapOverrides<K, V> extends UnifiedMap<K, V>
    {
        public UnifiedMapOverrides()
        {
        }

        public UnifiedMapOverrides(int initialCapacity, float loadFactor)
        {
            super(initialCapacity, loadFactor);
        }

        public UnifiedMapOverrides(Map<? extends K, ? extends V> map)
        {
            super(map);
        }

        @Override
        protected int index(Object key)
        {
            int h = key == null ? 0 : key.hashCode();
            return (h & (this.table.length >> 1) - 1) << 1;
        }

        @Override
        public UnifiedMapOverrides<K, V> clone()
        {
            return new UnifiedMapOverrides<>(this);
        }

        @Override
        public UnifiedMapOverrides<K, V> newEmpty()
        {
            return new UnifiedMapOverrides<>();
        }

        @Override
        public UnifiedMapOverrides<K, V> newEmpty(int capacity)
        {
            return new UnifiedMapOverrides<>(capacity, this.loadFactor);
        }
    }

    @Override
    public <K, V> UnifiedMapOverrides<K, V> newMap()
    {
        return new UnifiedMapOverrides<>();
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeyValue(K key, V value)
    {
        UnifiedMap<K, V> map = this.newMap();
        return map.withKeysValues(key, value);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        UnifiedMap<K, V> map = this.newMap();
        return map.withKeysValues(key1, value1, key2, value2);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        UnifiedMap<K, V> map = this.newMap();
        return map.withKeysValues(key1, value1, key2, value2, key3, value3);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        UnifiedMap<K, V> map = this.newMap();
        return map.withKeysValues(key1, value1, key2, value2, key3, value3, key4, value4);
    }
}
