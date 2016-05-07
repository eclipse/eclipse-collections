/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import java.io.Externalizable;
import java.util.Map;

import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

/**
 * A {@link MutableBiMap} which uses two hash tables as its underlying data store.
 *
 * @since 4.2
 */
public class HashBiMap<K, V> extends AbstractMutableBiMap<K, V> implements Externalizable
{
    private static final long serialVersionUID = 1L;

    public HashBiMap()
    {
        super(UnifiedMap.newMap(), UnifiedMap.newMap());
    }

    public HashBiMap(int initialSize)
    {
        super(UnifiedMap.newMap(initialSize), UnifiedMap.newMap(initialSize));
    }

    public HashBiMap(Map<K, V> map)
    {
        super(map);
    }

    HashBiMap(Map<K, V> keysToValues, Map<V, K> valuesToKeys)
    {
        super(keysToValues, valuesToKeys);
    }

    public static <K, V> HashBiMap<K, V> newMap()
    {
        return new HashBiMap<>();
    }

    public static <K, V> HashBiMap<K, V> newWithKeysValues(K key, V value)
    {
        return new HashBiMap<K, V>(1).withKeysValues(key, value);
    }

    public static <K, V> HashBiMap<K, V> newWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return new HashBiMap<K, V>(2).withKeysValues(key1, value1, key2, value2);
    }

    public static <K, V> HashBiMap<K, V> newWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new HashBiMap<K, V>(3).withKeysValues(key1, value1, key2, value2, key3, value3);
    }

    public static <K, V> HashBiMap<K, V> newWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return new HashBiMap<K, V>(4).withKeysValues(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    public HashBiMap<K, V> withKeysValues(K key, V value)
    {
        this.put(key, value);
        return this;
    }

    public HashBiMap<K, V> withKeysValues(K key1, V value1, K key2, V value2)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        return this;
    }

    public HashBiMap<K, V> withKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        this.put(key3, value3);
        return this;
    }

    public HashBiMap<K, V> withKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        this.put(key3, value3);
        this.put(key4, value4);
        return this;
    }
}
