/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.bimap;

import java.util.Map;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A {@link BiMap} whose contents can be altered after initialization.
 *
 * @since 4.2
 */
public interface MutableBiMap<K, V> extends BiMap<K, V>, MutableMapIterable<K, V>, Cloneable
{
    @Override
    MutableBiMap<K, V> newEmpty();

    @Override
    MutableBiMap<V, K> inverse();

    @Override
    MutableBiMap<V, K> flipUniqueValues();

    @Override
    MutableSetMultimap<V, K> flip();

    /**
     * Similar to {@link Map#put(Object, Object)}, except that it throws on the addition of a duplicate value.
     *
     * @throws IllegalArgumentException if the value already exists in the bimap.
     */
    @Override
    V put(K key, V value);

    /**
     * Similar to {@link #put(Object, Object)}, except that it quietly removes any existing entry with the same
     * value before putting the key-value pair.
     */
    V forcePut(K key, V value);

    @Override
    MutableBiMap<K, V> asSynchronized();

    @Override
    MutableBiMap<K, V> asUnmodifiable();

    MutableBiMap<K, V> clone();

    @Override
    MutableBiMap<K, V> tap(Procedure<? super V> procedure);

    @Override
    MutableBiMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableBiMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    @Override
    <K2, V2> MutableBiMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <R> MutableBiMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    @Override
    MutableSet<V> select(Predicate<? super V> predicate);

    @Override
    <P> MutableSet<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    MutableSet<V> reject(Predicate<? super V> predicate);

    @Override
    <P> MutableSet<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    PartitionMutableSet<V> partition(Predicate<? super V> predicate);

    @Override
    <P> PartitionMutableSet<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    <S> MutableSet<S> selectInstancesOf(Class<S> clazz);

    /**
     * @deprecated in 8.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    <S> MutableSet<Pair<V, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 8.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    MutableSet<Pair<V, Integer>> zipWithIndex();

    @Override
    <V1> MutableSetMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function);

    @Override
    <V1> MutableSetMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function);

    @Override
    <VV> MutableBiMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function);

    @Override
    MutableBiMap<K, V> withKeyValue(K key, V value);

    @Override
    MutableBiMap<K, V> withAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues);

    @Override
    MutableBiMap<K, V> withAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs);

    @Override
    MutableBiMap<K, V> withoutKey(K key);

    @Override
    MutableBiMap<K, V> withoutAllKeys(Iterable<? extends K> keys);
}
