/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import org.eclipse.collections.api.bimap.MutableBiMap;

public class HashBiMapEntrySetTest extends AbstractMutableBiMapEntrySetTest
{
    @Override
    protected <K, V> MutableBiMap<K, V> newMap()
    {
        return HashBiMap.newMap();
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return HashBiMap.newWithKeysValues(key1, value1, key2, value2);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return HashBiMap.newWithKeysValues(key1, value1, key2, value2, key3, value3);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return HashBiMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeyValue(K key, V value)
    {
        return HashBiMap.newWithKeysValues(key, value);
    }
}
