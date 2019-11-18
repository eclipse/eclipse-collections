/*
 * Copyright (c) 2019 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map;

import java.util.Map;
import java.util.Set;

import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.factory.Maps;

/**
 * A FixedSizeMap is a map that may be mutated, but cannot grow or shrink in size.
 */
public interface FixedSizeMap<K, V>
        extends MutableMap<K, V>
{
    static <K, V> FixedSizeMap<K, V> empty()
    {
        return Maps.fixedSize.empty();
    }

    static <K, V> FixedSizeMap<K, V> of()
    {
        return Maps.fixedSize.of();
    }

    static <K, V> FixedSizeMap<K, V> of(K key, V value)
    {
        return Maps.fixedSize.of(key, value);
    }

    static <K, V> FixedSizeMap<K, V> of(K key1, V value1, K key2, V value2)
    {
        return Maps.fixedSize.of(key1, value1, key2, value2);
    }

    static <K, V> FixedSizeMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return Maps.fixedSize.of(key1, value1, key2, value2, key3, value3);
    }

    /**
     * @throws UnsupportedOperationException the {@code clear} operation is not supported by this map.
     */
    @Override
    void clear();

    /**
     * @throws UnsupportedOperationException the {@code put} operation is not supported by this map.
     */
    @Override
    V put(K key, V value);

    /**
     * @throws UnsupportedOperationException the {@code putAll} operation is not supported by this map.
     */
    @Override
    void putAll(Map<? extends K, ? extends V> map);

    /**
     * @throws UnsupportedOperationException the {@code remove} operation is not supported by this map.
     */
    @Override
    V remove(Object key);

    /**
     * @throws UnsupportedOperationException the {@code removeKey} operation is not supported by this map.
     */
    @Override
    V removeKey(K key);

    /**
     * @throws UnsupportedOperationException the {@code removeAllKeys} operation is not supported by this map.
     */
    @Override
    boolean removeAllKeys(Set<? extends K> keys);

    /**
     * @throws UnsupportedOperationException the {@code removeIf} operation is not supported by this map.
     */
    @Override
    boolean removeIf(Predicate2<? super K, ? super V> predicate);

    @Override
    FixedSizeMap<K, V> tap(Procedure<? super V> procedure);
}
