/*
 * Copyright (c) 2016 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.bag;

import org.eclipse.collections.api.bag.MutableBagIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 6.0
 */
public interface MutableBagIterableMultimap<K, V>
        extends MutableMultimap<K, V>, BagMultimap<K, V>
{
    @Override
    MutableBagIterable<V> replaceValues(K key, Iterable<? extends V> values);

    @Override
    MutableBagIterable<V> removeAll(Object key);

    @Override
    MutableBagIterableMultimap<K, V> newEmpty();

    @Override
    MutableBagIterable<V> get(K key);

    @Override
    MutableBagIterableMultimap<V, K> flip();

    @Override
    MutableBagIterableMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableBagIterableMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableBagIterableMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    MutableBagIterableMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    <K2, V2> MutableBagIterableMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <V2> MutableMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);

    @Override
    MutableBagIterableMultimap<K, V> asSynchronized();
}
