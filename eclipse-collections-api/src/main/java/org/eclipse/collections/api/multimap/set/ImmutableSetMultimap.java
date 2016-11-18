/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.set;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 1.0
 */
public interface ImmutableSetMultimap<K, V>
        extends UnsortedSetMultimap<K, V>, ImmutableSetIterableMultimap<K, V>
{
    @Override
    ImmutableSet<V> get(K key);

    @Override
    ImmutableSetMultimap<K, V> newEmpty();

    @Override
    ImmutableSetMultimap<K, V> newWith(K key, V value);

    @Override
    ImmutableSetMultimap<K, V> newWithout(Object key, Object value);

    @Override
    ImmutableSetMultimap<K, V> newWithAll(K key, Iterable<? extends V> values);

    @Override
    ImmutableSetMultimap<K, V> newWithoutAll(Object key);

    @Override
    ImmutableSetMultimap<V, K> flip();

    @Override
    ImmutableSetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableSetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableSetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    ImmutableSetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    <K2, V2> ImmutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <V2> ImmutableBagMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);
}
