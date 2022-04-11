/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.strategy.mutable;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.factory.map.strategy.MutableHashingStrategyMapFactory;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;

public enum MutableHashingStrategyMapFactoryImpl implements MutableHashingStrategyMapFactory
{
    INSTANCE;

    @Override
    public <K, V> MutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy)
    {
        return this.with(hashingStrategy);
    }

    @Override
    public <K, V> MutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy)
    {
        return new UnifiedMapWithHashingStrategy<>(hashingStrategy);
    }

    @Override
    public <K, V, T> MutableMap<K, V> fromFunction(Function<? super K, ? extends T> function)
    {
        return this.with(HashingStrategies.fromFunction(function));
    }

    @Override
    public <K, V> MutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy, K key, V value)
    {
        return this.with(hashingStrategy, key, value);
    }

    @Override
    public <K, V> MutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy, K key, V value)
    {
        return UnifiedMapWithHashingStrategy.newWithKeysValues(hashingStrategy, key, value);
    }

    @Override
    public <K, V> MutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy, K key1, V value1, K key2, V value2)
    {
        return this.with(
                hashingStrategy,
                key1, value1,
                key2, value2);
    }

    @Override
    public <K, V> MutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy, K key1, V value1, K key2, V value2)
    {
        return UnifiedMapWithHashingStrategy.newWithKeysValues(
                hashingStrategy,
                key1, value1,
                key2, value2);
    }

    @Override
    public <K, V> MutableMap<K, V> of(
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
    public <K, V> MutableMap<K, V> with(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        return UnifiedMapWithHashingStrategy.newWithKeysValues(
                hashingStrategy,
                key1, value1,
                key2, value2,
                key3, value3);
    }

    @Override
    public <K, V> MutableMap<K, V> of(
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
    public <K, V> MutableMap<K, V> with(
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
                key4, value4);
    }
}
