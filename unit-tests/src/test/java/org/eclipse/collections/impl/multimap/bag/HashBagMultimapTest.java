/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Bags;

/**
 * Test of {@link HashBagMultimap}.
 */
public class HashBagMultimapTest extends AbstractMutableBagMultimapTestCase
{
    @Override
    protected <K, V> MutableBagMultimap<K, V> newMultimap()
    {
        return HashBagMultimap.newMultimap();
    }

    @Override
    protected <K, V> MutableBagMultimap<K, V> newMultimapWithKeyValue(K key, V value)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key, value);
    }

    @Override
    protected <K, V> MutableBagMultimap<K, V> newMultimapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2);
    }

    @Override
    protected <K, V> MutableBagMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3);
    }

    @Override
    protected <K, V> MutableBagMultimap<K, V> newMultimapWithKeysValues(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        return this.<K, V>newMultimap()
                .withKeyValue(key1, value1)
                .withKeyValue(key2, value2)
                .withKeyValue(key3, value3)
                .withKeyValue(key4, value4);
    }

    @SafeVarargs
    @Override
    protected final <K, V> MutableBagMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return HashBagMultimap.newMultimap(pairs);
    }

    @Override
    protected <K, V> MutableBagMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        return HashBagMultimap.newMultimap(inputIterable);
    }

    @SafeVarargs
    @Override
    protected final <V> MutableBag<V> createCollection(V... args)
    {
        return Bags.mutable.of(args);
    }
}
