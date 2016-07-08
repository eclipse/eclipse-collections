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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 1.0
 */
public interface ImmutableMultimap<K, V>
        extends Multimap<K, V>
{
    @Override
    ImmutableMultimap<K, V> newEmpty();

    @Override
    ImmutableCollection<V> get(K key);

    default ImmutableMultimap<K, V> newWith(K key, V value)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.put(key, value);
        return mutableMultimap.toImmutable();
    }

    default ImmutableMultimap<K, V> newWithout(Object key, Object value)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.remove(key, value);
        return mutableMultimap.toImmutable();
    }

    default ImmutableMultimap<K, V> newWithAll(K key, Iterable<? extends V> values)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.putAll(key, values);
        return mutableMultimap.toImmutable();
    }

    default ImmutableMultimap<K, V> newWithoutAll(Object key)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.removeAll(key);
        return mutableMultimap.toImmutable();
    }

    @Override
    ImmutableMultimap<V, K> flip();

    @Override
    ImmutableMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    ImmutableMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate);

    @Override
    <K2, V2> ImmutableMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <V2> ImmutableMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function);
}
