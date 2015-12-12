/*
 * Copyright (c) 2015 Goldman Sachs.
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
    MutableBiMap<K, V> newEmpty();

    MutableBiMap<V, K> inverse();

    MutableBiMap<V, K> flipUniqueValues();

    MutableSetMultimap<V, K> flip();

    /**
     * Similar to {@link Map#put(Object, Object)}, except that it throws on the addition of a duplicate value.
     *
     * @throws IllegalArgumentException if the value already exists in the bimap.
     */
    V put(K key, V value);

    /**
     * Similar to {@link #put(Object, Object)}, except that it quietly removes any existing entry with the same
     * value before putting the key-value pair.
     */
    V forcePut(K key, V value);

    MutableBiMap<K, V> asSynchronized();

    MutableBiMap<K, V> asUnmodifiable();

    MutableBiMap<K, V> clone();

    MutableBiMap<K, V> tap(Procedure<? super V> procedure);

    MutableBiMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    MutableBiMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    <K2, V2> MutableBiMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    <R> MutableBiMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    MutableSet<V> select(Predicate<? super V> predicate);

    <P> MutableSet<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    MutableSet<V> reject(Predicate<? super V> predicate);

    <P> MutableSet<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    PartitionMutableSet<V> partition(Predicate<? super V> predicate);

    <P> PartitionMutableSet<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    <S> MutableSet<S> selectInstancesOf(Class<S> clazz);

    <S> MutableSet<Pair<V, S>> zip(Iterable<S> that);

    MutableSet<Pair<V, Integer>> zipWithIndex();

    <V1> MutableSetMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function);

    <V1> MutableSetMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function);

    <VV> MutableBiMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function);

    MutableBiMap<K, V> withKeyValue(K key, V value);

    MutableBiMap<K, V> withAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues);

    MutableBiMap<K, V> withAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs);

    MutableBiMap<K, V> withoutKey(K key);

    MutableBiMap<K, V> withoutAllKeys(Iterable<? extends K> keys);
}
