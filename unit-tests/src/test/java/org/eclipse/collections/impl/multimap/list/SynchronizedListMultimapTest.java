/*
 * Copyright (c) 2022 Shotaro Sano and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.list;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.list.mutable.FastList;

public class SynchronizedListMultimapTest extends AbstractMutableListMultimapTestCase
{
    @Override
    public <K, V> MutableListMultimap<K, V> newMultimap()
    {
        return new SynchronizedListMultimap<>(FastListMultimap.newMultimap());
    }

    @Override
    public <K, V> MutableListMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key, value);
    }

    @Override
    public <K, V> MutableListMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2);
    }

    @Override
    public <K, V> MutableListMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3);
    }

    @Override
    public <K, V> MutableListMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3)
                .withKeyValue(key4, value4);
    }

    @Override
    public <K, V> MutableListMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return new SynchronizedListMultimap<>(FastListMultimap.newMultimap(pairs));
    }

    @Override
    public <K, V> MutableListMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        return new SynchronizedListMultimap<>(FastListMultimap.newMultimap(inputIterable));
    }

    @SafeVarargs
    @Override
    protected final <V> MutableList<V> createCollection(V... args)
    {
        return FastList.newListWith(args);
    }
}
