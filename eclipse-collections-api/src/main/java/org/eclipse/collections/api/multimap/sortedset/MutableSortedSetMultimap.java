/*
 * Copyright (c) 2016 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.sortedset;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetIterableMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 1.0
 */
public interface MutableSortedSetMultimap<K, V>
        extends MutableSetIterableMultimap<K, V>, SortedSetMultimap<K, V>
{
    @Override
    MutableSortedSet<V> replaceValues(K key, Iterable<? extends V> values);

    @Override
    MutableSortedSet<V> removeAll(Object key);

    @Override
    MutableSortedSetMultimap<K, V> newEmpty();

    @Override
    MutableSortedSet<V> get(K key);

    @Override
    MutableSetMultimap<V, K> flip();

    @Override
    MutableSortedSetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableSortedSetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableSortedSetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    MutableSortedSetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    <K2, V2> MutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <V2> MutableListMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);

    @Override
    MutableSortedSetMultimap<K, V> asSynchronized();
}
