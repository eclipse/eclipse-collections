/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.multimap.sortedbag;

import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.ordered.ReversibleIterableMultimap;
import org.eclipse.collections.api.multimap.ordered.SortedIterableMultimap;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 4.2
 */
public interface SortedBagMultimap<K, V>
        extends BagMultimap<K, V>, SortedIterableMultimap<K, V>, ReversibleIterableMultimap<K, V>
{
    @Override
    SortedBagMultimap<K, V> newEmpty();

    @Override
    SortedBag<V> get(K key);

    @Override
    MutableSortedBagMultimap<K, V> toMutable();

    @Override
    ImmutableSortedBagMultimap<K, V> toImmutable();

    @Override
    SortedBagMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    SortedBagMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    SortedBagMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    SortedBagMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    <K2, V2> BagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <V2> ListMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);
}
