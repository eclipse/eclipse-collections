/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.strategy.immutable;

import java.util.Map;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.factory.map.strategy.ImmutableHashingStrategyMapFactory;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.map.strategy.mutable.UnifiedMapWithHashingStrategy;

public enum ImmutableHashingStrategyMapFactoryImpl implements ImmutableHashingStrategyMapFactory
{
    INSTANCE;

    @Override
    public <K, V> ImmutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy)
    {
        return this.with(hashingStrategy);
    }

    @Override
    public <K, V> ImmutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy)
    {
        return new ImmutableEmptyMapWithHashingStrategy<>(hashingStrategy);
    }

    @Override
    public <K, V> ImmutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy, K key, V value)
    {
        return this.with(hashingStrategy, key, value);
    }

    @Override
    public <K, V> ImmutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy, K key, V value)
    {
        return UnifiedMapWithHashingStrategy.newWithKeysValues(hashingStrategy, key, value).toImmutable();
    }

    @Override
    public <K, V> ImmutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy, K key1, V value1, K key2, V value2)
    {
        return this.with(
                hashingStrategy,
                key1, value1,
                key2, value2);
    }

    @Override
    public <K, V> ImmutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy, K key1, V value1, K key2, V value2)
    {
        return UnifiedMapWithHashingStrategy.newWithKeysValues(
                hashingStrategy,
                key1, value1,
                key2, value2).toImmutable();
    }

    @Override
    public <K, V> ImmutableMap<K, V> of(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        return this.with(
                hashingStrategy,
                key1, value1,
                key2, value2,
                key3, value3);
    }

    @Override
    public <K, V> ImmutableMap<K, V> with(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        return UnifiedMapWithHashingStrategy.newWithKeysValues(
                hashingStrategy,
                key1, value1,
                key2, value2,
                key3, value3).toImmutable();
    }

    @Override
    public <K, V> ImmutableMap<K, V> of(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        return this.with(
                hashingStrategy,
                key1, value1,
                key2, value2,
                key3, value3,
                key4, value4);
    }

    @Override
    public <K, V> ImmutableMap<K, V> with(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        return UnifiedMapWithHashingStrategy.newWithKeysValues(
                hashingStrategy,
                key1, value1,
                key2, value2,
                key3, value3,
                key4, value4).toImmutable();
    }

    /**
     * @deprecated use {@link #ofAll(Map)} instead (inlineable)
     */
    @Override
    @Deprecated
    public <K, V> ImmutableMap<K, V> ofMap(Map<K, V> map)
    {
        return this.ofAll(map);
    }

    @Override
    public <K, V> ImmutableMap<K, V> ofAll(Map<K, V> map)
    {
        return this.withAll(map);
    }

    @Override
    public <K, V> ImmutableMap<K, V> withAll(Map<K, V> map)
    {
        if (!(map instanceof UnifiedMapWithHashingStrategy<?, ?>))
        {
            throw new IllegalArgumentException();
        }

        UnifiedMapWithHashingStrategy<K, V> mapWithHashingStrategy = (UnifiedMapWithHashingStrategy<K, V>) map;

        if (mapWithHashingStrategy.isEmpty())
        {
            return this.of(mapWithHashingStrategy.hashingStrategy());
        }

        return new ImmutableUnifiedMapWithHashingStrategy<>(mapWithHashingStrategy);
    }
}
