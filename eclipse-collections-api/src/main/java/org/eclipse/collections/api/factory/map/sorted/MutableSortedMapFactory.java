/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.map.sorted;

import java.util.Comparator;
import java.util.Map;

import org.eclipse.collections.api.map.sorted.MutableSortedMap;

public interface MutableSortedMapFactory
{
    /**
     * @since 6.0
     */
    <K, V> MutableSortedMap<K, V> empty();

    /**
     * @since 12.0
     */
    <K, V> MutableSortedMap<K, V> empty(Comparator<? super K> comparator);

    /**
     * Same as {@link #empty()}.
     */
    <K, V> MutableSortedMap<K, V> of();

    /**
     * Same as {@link #empty()}.
     */
    <K, V> MutableSortedMap<K, V> with();

    /**
     * Same as {@link #with(Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(K key, V value);

    <K, V> MutableSortedMap<K, V> with(K key, V value);

    /**
     * Same as {@link #with(Object, Object, Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(K key1, V value1, K key2, V value2);

    <K, V> MutableSortedMap<K, V> with(K key1, V value1, K key2, V value2);

    /**
     * Same as {@link #with(Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3);

    <K, V> MutableSortedMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3);

    /**
     * Same as {@link #with(Object, Object, Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    <K, V> MutableSortedMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    /**
     * Same as {@link #with(Comparator)}.
     */
    <K, V> MutableSortedMap<K, V> of(Comparator<? super K> comparator);

    <K, V> MutableSortedMap<K, V> with(Comparator<? super K> comparator);

    /**
     * Same as {@link #with(Comparator, Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(Comparator<? super K> comparator, K key, V value);

    <K, V> MutableSortedMap<K, V> with(Comparator<? super K> comparator, K key, V value);

    /**
     * Same as {@link #with(Comparator, Object, Object, Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(Comparator<? super K> comparator, K key1, V value1, K key2, V value2);

    <K, V> MutableSortedMap<K, V> with(Comparator<? super K> comparator, K key1, V value1, K key2, V value2);

    /**
     * Same as {@link #with(Comparator, Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3);

    <K, V> MutableSortedMap<K, V> with(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3);

    /**
     * Same as {@link #with(Comparator, Object, Object, Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> MutableSortedMap<K, V> of(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    <K, V> MutableSortedMap<K, V> with(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    /**
     * Same as {@link #withSortedMap(Map)}.
     */
    <K, V> MutableSortedMap<K, V> ofSortedMap(Map<? extends K, ? extends V> map);

    <K, V> MutableSortedMap<K, V> withSortedMap(Map<? extends K, ? extends V> map);
}
