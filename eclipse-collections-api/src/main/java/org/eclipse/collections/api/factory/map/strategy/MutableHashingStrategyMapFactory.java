/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.map.strategy;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.map.MutableMap;

public interface MutableHashingStrategyMapFactory
{
    /**
     * Same as {@link #with(HashingStrategy)}.
     */
    <K, V> MutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy);

    <K, V> MutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy);

    /**
     * Same as {@link #with(HashingStrategy, Object, Object)}.
     */
    <K, V> MutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy, K key, V value);

    <K, V> MutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy, K key, V value);

    /**
     * Same as {@link #with(HashingStrategy, Object, Object, Object, Object)}.
     */
    <K, V> MutableMap<K, V> of(HashingStrategy<? super K> hashingStrategy, K key1, V value1, K key2, V value2);

    <K, V> MutableMap<K, V> with(HashingStrategy<? super K> hashingStrategy, K key1, V value1, K key2, V value2);

    /**
     * Same as {@link #with(HashingStrategy, Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> MutableMap<K, V> of(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3);

    <K, V> MutableMap<K, V> with(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3);

    /**
     * Same as {@link #with(HashingStrategy, Object, Object, Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> MutableMap<K, V> of(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4);

    <K, V> MutableMap<K, V> with(
            HashingStrategy<? super K> hashingStrategy,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4);
}
