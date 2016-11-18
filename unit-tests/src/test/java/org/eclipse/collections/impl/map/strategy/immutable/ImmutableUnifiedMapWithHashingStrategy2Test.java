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

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.map.MapIterableTestCase;
import org.eclipse.collections.impl.map.strategy.mutable.UnifiedMapWithHashingStrategy;
import org.eclipse.collections.impl.tuple.Tuples;

public class ImmutableUnifiedMapWithHashingStrategy2Test extends MapIterableTestCase
{
    @Override
    protected <K, V> ImmutableMap<K, V> newMap()
    {
        return new ImmutableUnifiedMapWithHashingStrategy<>(
                UnifiedMapWithHashingStrategy.newMap(HashingStrategies.nullSafeHashingStrategy(HashingStrategies.defaultStrategy())));
    }

    @Override
    protected <K, V> ImmutableMap<K, V> newMapWithKeyValue(K key1, V value1)
    {
        return new ImmutableUnifiedMapWithHashingStrategy<>(
                UnifiedMapWithHashingStrategy.newWithKeysValues(HashingStrategies.nullSafeHashingStrategy(
                        HashingStrategies.defaultStrategy()), key1, value1));
    }

    @Override
    protected <K, V> ImmutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return new ImmutableUnifiedMapWithHashingStrategy<>(
                UnifiedMapWithHashingStrategy.newWithKeysValues(HashingStrategies.nullSafeHashingStrategy(
                        HashingStrategies.defaultStrategy()), key1, value1, key2, value2));
    }

    @Override
    protected <K, V> ImmutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new ImmutableUnifiedMapWithHashingStrategy<>(
                UnifiedMapWithHashingStrategy.newWithKeysValues(HashingStrategies.nullSafeHashingStrategy(
                        HashingStrategies.defaultStrategy()), key1, value1, key2, value2, key3, value3));
    }

    @Override
    protected <K, V> ImmutableMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return new ImmutableUnifiedMapWithHashingStrategy<>(
                UnifiedMapWithHashingStrategy.newMapWith(HashingStrategies.nullSafeHashingStrategy(
                        HashingStrategies.defaultStrategy()), Tuples.pair(key1, value1), Tuples.pair(key2, value2), Tuples.pair(key3, value3), Tuples.pair(key4, value4)));
    }
}
