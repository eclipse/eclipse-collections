/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 1.0
 */
public interface MutableSetMultimap<K, V>
        extends UnsortedSetMultimap<K, V>, MutableSetIterableMultimap<K, V>
{
    /**
     * Puts the key / value combination into the MutableSetMultimap and returns the MutableSetMultimap (this).
     * @since 11.1
     */
    @Override
    default MutableSetMultimap<K, V> withKeyValue(K key, V value)
    {
        this.put(key, value);
        return this;
    }

    @Override
    default MutableSetMultimap<K, V> withKeyMultiValues(K key, V... values)
    {
        return (MutableSetMultimap<K, V>) MutableSetIterableMultimap.super.withKeyMultiValues(key, values);
    }

    /**
     * @since 11.0
     */
    void forEachKeyMutableSet(Procedure2<? super K, ? super MutableSet<V>> procedure);

    @Override
    MutableSet<V> replaceValues(K key, Iterable<? extends V> values);

    @Override
    MutableSet<V> removeAll(Object key);

    @Override
    MutableSetMultimap<K, V> newEmpty();

    @Override
    MutableSet<V> get(K key);

    @Override
    MutableSet<V> getIfAbsentPutAll(K key, Iterable<? extends V> values);

    @Override
    MutableSetMultimap<V, K> flip();

    @Override
    MutableSetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableSetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableSetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super RichIterable<V>> predicate);

    @Override
    MutableSetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super RichIterable<V>> predicate);

    @Override
    <K2, V2> MutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <K2, V2> MutableBagMultimap<K2, V2> collectKeyMultiValues(Function<? super K, ? extends K2> keyFunction, Function<? super V, ? extends V2> valueFunction);

    @Override
    <V2> MutableBagMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);

    @Override
    MutableSetMultimap<K, V> asSynchronized();
}
