/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.map;

import java.util.Map;

import org.eclipse.collections.api.map.ImmutableMap;

public interface ImmutableMapFactory
{
    /**
     * @since 6.0
     */
    <K, V> ImmutableMap<K, V> empty();

    /**
     * Same as {@link #empty()}.
     */
    <K, V> ImmutableMap<K, V> of();

    /**
     * Same as {@link #empty()}.
     */
    <K, V> ImmutableMap<K, V> with();

    /**
     * Same as {@link #with(Object, Object)}.
     */
    <K, V> ImmutableMap<K, V> of(K key, V value);

    <K, V> ImmutableMap<K, V> with(K key, V value);

    /**
     * Same as {@link #with(Object, Object, Object, Object)}.
     */
    <K, V> ImmutableMap<K, V> of(K key1, V value1, K key2, V value2);

    <K, V> ImmutableMap<K, V> with(K key1, V value1, K key2, V value2);

    /**
     * Same as {@link #with(Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> ImmutableMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3);

    <K, V> ImmutableMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3);

    /**
     * Same as {@link #with(Object, Object, Object, Object, Object, Object, Object, Object)}.
     */
    <K, V> ImmutableMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    <K, V> ImmutableMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    <K, V> ImmutableMap<K, V> ofMap(Map<K, V> map);

    /**
     * Same as {@link #withAll(Map)}.
     */
    <K, V> ImmutableMap<K, V> ofAll(Map<K, V> map);

    <K, V> ImmutableMap<K, V> withAll(Map<K, V> map);
}
