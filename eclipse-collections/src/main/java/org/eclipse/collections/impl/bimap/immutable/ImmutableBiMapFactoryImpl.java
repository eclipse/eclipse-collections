/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.immutable;

import java.util.Map;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.factory.bimap.ImmutableBiMapFactory;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.utility.MapIterate;

public enum ImmutableBiMapFactoryImpl implements ImmutableBiMapFactory
{
    INSTANCE;

    private static final ImmutableHashBiMap<?, ?> EMPTY_INSTANCE = new ImmutableHashBiMap<>(Maps.immutable.empty(), Maps.immutable.empty());

    @Override
    public <K, V> ImmutableBiMap<K, V> empty()
    {
        return (ImmutableBiMap<K, V>) EMPTY_INSTANCE;
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> of()
    {
        return this.empty();
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> with()
    {
        return this.empty();
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> of(K key, V value)
    {
        return this.with(key, value);
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> with(K key, V value)
    {
        return new ImmutableHashBiMap<>(
                Maps.immutable.with(key, value),
                Maps.immutable.with(value, key));
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> of(K key1, V value1, K key2, V value2)
    {
        return this.with(key1, value1, key2, value2);
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> with(K key1, V value1, K key2, V value2)
    {
        return new ImmutableHashBiMap<>(
                Maps.immutable.with(key1, value1, key2, value2),
                Maps.immutable.with(value1, key1, value2, key2));
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return this.with(key1, value1, key2, value2, key3, value3);
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new ImmutableHashBiMap<>(
                Maps.immutable.with(key1, value1, key2, value2, key3, value3),
                Maps.immutable.with(value1, key1, value2, key2, value3, key3));
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return this.with(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return new ImmutableHashBiMap<>(
                Maps.immutable.with(key1, value1, key2, value2, key3, value3, key4, value4),
                Maps.immutable.with(value1, key1, value2, key2, value3, key3, value4, key4));
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> ofAll(Map<K, V> map)
    {
        return this.withAll(map);
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> withAll(Map<K, V> map)
    {
        if (map instanceof ImmutableBiMap<?, ?>)
        {
            return (ImmutableBiMap<K, V>) map;
        }
        if (map instanceof MutableBiMap<?, ?>)
        {
            return this.withAll((MutableBiMap<K, V>) map);
        }
        ImmutableMap<K, V> immutableMap = Maps.immutable.withAll(map);
        return new ImmutableHashBiMap<>(immutableMap, Maps.immutable.withAll(MapIterate.flipUniqueValues(immutableMap)));
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> ofAll(MutableBiMap<K, V> biMap)
    {
        return this.withAll(biMap);
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> withAll(MutableBiMap<K, V> biMap)
    {
        return new ImmutableHashBiMap<>(Maps.immutable.withAll(biMap), Maps.immutable.withAll(biMap.inverse()));
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> ofAll(ImmutableMap<K, V> immutableMap)
    {
        return this.withAll(immutableMap);
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> withAll(ImmutableMap<K, V> immutableMap)
    {
        if (immutableMap instanceof ImmutableBiMap)
        {
            return (ImmutableBiMap<K, V>) immutableMap;
        }
        return new ImmutableHashBiMap<>(immutableMap, immutableMap.flipUniqueValues());
    }
}
