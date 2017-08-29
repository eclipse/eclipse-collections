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

import org.eclipse.collections.api.factory.map.MutableMapFactory;
import org.eclipse.collections.api.map.MutableMap;

public enum MutableMapFactoryImpl implements MutableMapFactory
{
    INSTANCE;

    @Override
    public <K, V> MutableMap<K, V> empty()
    {
        return UnifiedMap.newMap();
    }

    @Override
    public <K, V> MutableMap<K, V> of()
    {
        return this.empty();
    }

    @Override
    public <K, V> MutableMap<K, V> with()
    {
        return this.empty();
    }

    @Override
    public <K, V> MutableMap<K, V> ofInitialCapacity(int capacity)
    {
        return this.withInitialCapacity(capacity);
    }

    @Override
    public <K, V> MutableMap<K, V> withInitialCapacity(int capacity)
    {
        return UnifiedMap.newMap(capacity);
    }

    @Override
    public <K, V> MutableMap<K, V> of(K key, V value)
    {
        return this.with(key, value);
    }

    @Override
    public <K, V> MutableMap<K, V> with(K key, V value)
    {
        return UnifiedMap.newWithKeysValues(key, value);
    }

    @Override
    public <K, V> MutableMap<K, V> of(K key1, V value1, K key2, V value2)
    {
        return this.with(key1, value1, key2, value2);
    }

    @Override
    public <K, V> MutableMap<K, V> with(K key1, V value1, K key2, V value2)
    {
        return UnifiedMap.newWithKeysValues(key1, value1, key2, value2);
    }

    @Override
    public <K, V> MutableMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return this.with(key1, value1, key2, value2, key3, value3);
    }

    @Override
    public <K, V> MutableMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return UnifiedMap.newWithKeysValues(key1, value1, key2, value2, key3, value3);
    }

    @Override
    public <K, V> MutableMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return this.with(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    @Override
    public <K, V> MutableMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return UnifiedMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4);
    }
}
