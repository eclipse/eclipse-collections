/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.sortedbag;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.bag.MutableBagIterableMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 4.2
 */
public interface MutableSortedBagMultimap<K, V>
        extends MutableBagIterableMultimap<K, V>, SortedBagMultimap<K, V>
{
    /**
     * Puts the key / value combination into the MutableSortedBagMultimap and returns the MutableSortedBagMultimap (this).
     * @since 11.1
     */
    @Override
    default MutableSortedBagMultimap<K, V> withKeyValue(K key, V value)
    {
        this.put(key, value);
        return this;
    }

    @Override
    default MutableSortedBagMultimap<K, V> withKeyMultiValues(K key, V... values)
    {
        return (MutableSortedBagMultimap<K, V>) MutableBagIterableMultimap.super.withKeyMultiValues(key, values);
    }

    @Override
    MutableSortedBag<V> replaceValues(K key, Iterable<? extends V> values);

    @Override
    MutableSortedBag<V> removeAll(Object key);

    @Override
    MutableSortedBagMultimap<K, V> newEmpty();

    @Override
    MutableSortedBag<V> get(K key);

    @Override
    MutableSortedBag<V> getIfAbsentPutAll(K key, Iterable<? extends V> values);

    @Override
    MutableBagMultimap<V, K> flip();

    @Override
    MutableSortedBagMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableSortedBagMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableSortedBagMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super RichIterable<V>> predicate);

    @Override
    MutableSortedBagMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super RichIterable<V>> predicate);

    @Override
    <K2, V2> MutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <K2, V2> MutableBagMultimap<K2, V2> collectKeyMultiValues(Function<? super K, ? extends K2> keyFunction, Function<? super V, ? extends V2> valueFunction);

    @Override
    <V2> MutableListMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);

    @Override
    MutableSortedBagMultimap<K, V> asSynchronized();
}
