/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 1.0
 */
public interface MutableMultimap<K, V>
        extends Multimap<K, V>
{
    @Override
    MutableMultimap<K, V> newEmpty();

    @Override
    MutableCollection<V> get(K key);

    // Modification Operations

    boolean put(K key, V value);

    /**
     * Modification operation similar to put, however, takes the key-value pair as the input.
     *
     * @param keyValuePair
     *         key value pair to add in the multimap
     * @see #put(Object, Object)
     * @since 6.0
     */
    boolean add(Pair<K, V> keyValuePair);

    boolean remove(Object key, Object value);

    // Bulk Operations
    boolean putAllPairs(Pair<K, V>... pairs);

    boolean putAllPairs(Iterable<Pair<K, V>> pairs);

    boolean putAll(K key, Iterable<? extends V> values);

    <KK extends K, VV extends V> boolean putAll(Multimap<KK, VV> multimap);

    RichIterable<V> replaceValues(K key, Iterable<? extends V> values);

    RichIterable<V> removeAll(Object key);

    void clear();

    @Override
    MutableMultimap<V, K> flip();

    @Override
    MutableMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    MutableMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    <K2, V2> MutableMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <V2> MutableMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);
}
