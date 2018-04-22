/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.strategy.mutable;

import java.util.Map;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;

public class UnifiedMapWithHashingStrategyOverridesTest extends UnifiedMapWithHashingStrategyTest
{
    public static final class UnifiedMapWithHashingStrategyOverrides<K, V> extends UnifiedMapWithHashingStrategy<K, V>
    {
        public UnifiedMapWithHashingStrategyOverrides()
        {
        }

        public UnifiedMapWithHashingStrategyOverrides(HashingStrategy<? super K> strategy)
        {
            super(strategy);
        }

        public UnifiedMapWithHashingStrategyOverrides(HashingStrategy<? super K> hashingStrategy, Map<? extends K, ? extends V> map)
        {
            super(hashingStrategy, map);
        }

        public UnifiedMapWithHashingStrategyOverrides(HashingStrategy<? super K> hashingStrategy, int capacity, float loadFactor)
        {
            super(hashingStrategy, capacity, loadFactor);
        }

        @Override
        protected int index(K key)
        {
            int h = this.hashingStrategy.computeHashCode(key);
            return (h & (this.table.length >> 1) - 1) << 1;
        }

        @Override
        public UnifiedMapWithHashingStrategyOverrides<K, V> clone()
        {
            return new UnifiedMapWithHashingStrategyOverrides<>(this.hashingStrategy, this);
        }

        @Override
        public UnifiedMapWithHashingStrategyOverrides<K, V> newEmpty()
        {
            return new UnifiedMapWithHashingStrategyOverrides<>(this.hashingStrategy);
        }

        @Override
        public UnifiedMapWithHashingStrategyOverrides<K, V> newEmpty(int capacity)
        {
            return new UnifiedMapWithHashingStrategyOverrides<K, V>(this.hashingStrategy, capacity, this.loadFactor);
        }
    }

    @Override
    public <K, V> UnifiedMapWithHashingStrategyOverrides<K, V> newMap()
    {
        HashingStrategy<K> nshs = HashingStrategies.nullSafeHashingStrategy(HashingStrategies.defaultStrategy());
        UnifiedMapWithHashingStrategyOverrides<K, V> map = new UnifiedMapWithHashingStrategyOverrides<>(nshs);
        return map;
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeyValue(K key, V value)
    {
        UnifiedMapWithHashingStrategy<K, V> map = this.newMap();
        return map.withKeysValues(key, value);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        UnifiedMapWithHashingStrategy<K, V> map = this.newMap();
        return map.withKeysValues(key1, value1, key2, value2);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(
            K key1, V value1, K key2, V value2, K key3,
            V value3)
    {
        UnifiedMapWithHashingStrategy<K, V> map = this.newMap();
        return map.withKeysValues(key1, value1, key2, value2, key3, value3);
    }

    @Override
    public <K, V> MutableMap<K, V> newMapWithKeysValues(
            K key1, V value1, K key2, V value2, K key3,
            V value3, K key4, V value4)
    {
        UnifiedMapWithHashingStrategy<K, V> map = this.newMap();
        return map.withKeysValues(key1, value1, key2, value2, key3, value3, key4, value4);
    }
}
