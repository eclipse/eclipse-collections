/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map.sorted;

import java.util.SortedMap;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.ImmutableByteList;
import org.eclipse.collections.api.list.primitive.ImmutableCharList;
import org.eclipse.collections.api.list.primitive.ImmutableDoubleList;
import org.eclipse.collections.api.list.primitive.ImmutableFloatList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.ImmutableShortList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.ImmutableMapIterable;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.tuple.Pair;
import net.jcip.annotations.Immutable;

/**
 * An ImmutableSortedMap is different than a JCF SortedMap in that it has no mutating methods, but it shares
 * the read-only protocol of a SortedMap.
 */
@Immutable
public interface ImmutableSortedMap<K, V>
        extends SortedMapIterable<K, V>, ImmutableMapIterable<K, V>
{
    SortedMap<K, V> castToMap();

    SortedMap<K, V> castToSortedMap();

    // TODO: Keys could be ordered
    ImmutableSortedSetMultimap<V, K> flip();

    ImmutableSortedMap<K, V> newWithKeyValue(K key, V value);

    ImmutableSortedMap<K, V> newWithAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues);

    ImmutableSortedMap<K, V> newWithAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs);

    ImmutableSortedMap<K, V> newWithoutKey(K key);

    ImmutableSortedMap<K, V> newWithoutAllKeys(Iterable<? extends K> keys);

    MutableSortedMap<K, V> toSortedMap();

    // TODO: When we have implementations of linked hash maps
    // ImmutableOrderedMap<V, K> flipUniqueValues();

    ImmutableSortedMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    ImmutableSortedMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    ImmutableSortedMap<K, V> tap(Procedure<? super V> procedure);

    ImmutableList<V> select(Predicate<? super V> predicate);

    <P> ImmutableList<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    ImmutableList<V> reject(Predicate<? super V> predicate);

    <P> ImmutableList<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    <S> ImmutableList<S> selectInstancesOf(Class<S> clazz);

    PartitionImmutableList<V> partition(Predicate<? super V> predicate);

    <P> PartitionImmutableList<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    <R> ImmutableList<R> collect(Function<? super V, ? extends R> function);

    <P, VV> ImmutableList<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter);

    <K2, V2> ImmutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    <R> ImmutableSortedMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    <R> ImmutableList<R> collectIf(
            Predicate<? super V> predicate,
            Function<? super V, ? extends R> function);

    <R> ImmutableList<R> flatCollect(Function<? super V, ? extends Iterable<R>> function);

    ImmutableBooleanList collectBoolean(BooleanFunction<? super V> booleanFunction);

    ImmutableByteList collectByte(ByteFunction<? super V> byteFunction);

    ImmutableCharList collectChar(CharFunction<? super V> charFunction);

    ImmutableDoubleList collectDouble(DoubleFunction<? super V> doubleFunction);

    ImmutableFloatList collectFloat(FloatFunction<? super V> floatFunction);

    ImmutableIntList collectInt(IntFunction<? super V> intFunction);

    ImmutableLongList collectLong(LongFunction<? super V> longFunction);

    ImmutableShortList collectShort(ShortFunction<? super V> shortFunction);

    <S> ImmutableList<Pair<V, S>> zip(Iterable<S> that);

    ImmutableList<Pair<V, Integer>> zipWithIndex();

    <VV> ImmutableListMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function);

    <VV> ImmutableListMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function);

    <VV> ImmutableMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function);

    <K2, V2> ImmutableMap<K2, V2> aggregateInPlaceBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Procedure2<? super V2, ? super V> mutatingAggregator);

    <K2, V2> ImmutableMap<K2, V2> aggregateBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Function2<? super V2, ? super V, ? extends V2> nonMutatingAggregator);
}
