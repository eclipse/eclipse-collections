/*
 * Copyright (c) 2022 Shotaro Sano and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag.sorted.mutable;

import java.util.Comparator;

import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;

public class SynchronizedSortedBagMultimapTest extends AbstractMutableSortedBagMultimapTestCase
{
    @Override
    protected <K, V> MutableSortedBagMultimap<K, V> newMultimap(Comparator<V> comparator)
    {
        return new SynchronizedSortedBagMultimap<>(TreeBagMultimap.newMultimap(comparator));
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimap()
    {
        return new SynchronizedSortedBagMultimap<>(TreeBagMultimap.newMultimap());
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key, value);
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2);
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3);
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3)
                .withKeyValue(key4, value4);
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return new SynchronizedSortedBagMultimap<>(TreeBagMultimap.newMultimap(pairs));
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        return new SynchronizedSortedBagMultimap<>(TreeBagMultimap.newMultimap(inputIterable));
    }

    @SafeVarargs
    @Override
    protected final <V> TreeBag<V> createCollection(V... args)
    {
        return TreeBag.newBagWith(args);
    }
}
