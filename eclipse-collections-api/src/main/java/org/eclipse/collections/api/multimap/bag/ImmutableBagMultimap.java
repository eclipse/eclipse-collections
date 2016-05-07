/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.bag;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 1.0
 */
public interface ImmutableBagMultimap<K, V>
        extends UnsortedBagMultimap<K, V>, ImmutableBagIterableMultimap<K, V>
{
    @Override
    ImmutableBagMultimap<K, V> newEmpty();

    @Override
    ImmutableBag<V> get(K key);

    @Override
    ImmutableBagMultimap<K, V> newWith(K key, V value);

    @Override
    ImmutableBagMultimap<K, V> newWithout(Object key, Object value);

    @Override
    ImmutableBagMultimap<K, V> newWithAll(K key, Iterable<? extends V> values);

    @Override
    ImmutableBagMultimap<K, V> newWithoutAll(Object key);

    @Override
    ImmutableBagMultimap<V, K> flip();

    @Override
    ImmutableBagMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableBagMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableBagMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    ImmutableBagMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    <K2, V2> ImmutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <V2> ImmutableBagMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);
}
