/*
 * Copyright (c) 2018 Goldman Sachs and others.
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
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
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

/**
 * An ImmutableSortedMap is different than a JCF SortedMap in that it has no mutating methods, but it shares
 * the read-only protocol of a SortedMap.
 */
public interface ImmutableSortedMap<K, V>
        extends SortedMapIterable<K, V>, ImmutableMapIterable<K, V>
{
    @Override
    SortedMap<K, V> castToMap();

    SortedMap<K, V> castToSortedMap();

    // TODO: Keys could be ordered
    @Override
    ImmutableSortedSetMultimap<V, K> flip();

    @Override
    ImmutableSortedMap<K, V> newWithKeyValue(K key, V value);

    @Override
    ImmutableSortedMap<K, V> newWithAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues);

    @Override
    ImmutableSortedMap<K, V> newWithAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs);

    @Override
    ImmutableSortedMap<K, V> newWithoutKey(K key);

    @Override
    ImmutableSortedMap<K, V> newWithoutAllKeys(Iterable<? extends K> keys);

    MutableSortedMap<K, V> toSortedMap();

    // TODO: When we have implementations of linked hash maps
    // ImmutableOrderedMap<V, K> flipUniqueValues();

    @Override
    ImmutableSortedMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableSortedMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableSortedMap<K, V> tap(Procedure<? super V> procedure);

    @Override
    ImmutableList<V> select(Predicate<? super V> predicate);

    @Override
    <P> ImmutableList<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    ImmutableList<V> reject(Predicate<? super V> predicate);

    @Override
    <P> ImmutableList<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    <S> ImmutableList<S> selectInstancesOf(Class<S> clazz);

    @Override
    PartitionImmutableList<V> partition(Predicate<? super V> predicate);

    @Override
    <P> PartitionImmutableList<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    <R> ImmutableList<R> collect(Function<? super V, ? extends R> function);

    /**
     * @since 9.1.
     */
    @Override
    default <R> ImmutableList<R> collectWithIndex(ObjectIntToObjectFunction<? super V, ? extends R> function)
    {
        int[] index = {0};
        return this.collect(each -> function.valueOf(each, index[0]++));
    }

    @Override
    <P, VV> ImmutableList<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter);

    @Override
    <K2, V2> ImmutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <R> ImmutableSortedMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    @Override
    <R> ImmutableList<R> collectIf(
            Predicate<? super V> predicate,
            Function<? super V, ? extends R> function);

    @Override
    <R> ImmutableList<R> flatCollect(Function<? super V, ? extends Iterable<R>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, R> ImmutableList<R> flatCollectWith(Function2<? super V, ? super P, ? extends Iterable<R>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    @Override
    ImmutableBooleanList collectBoolean(BooleanFunction<? super V> booleanFunction);

    @Override
    ImmutableByteList collectByte(ByteFunction<? super V> byteFunction);

    @Override
    ImmutableCharList collectChar(CharFunction<? super V> charFunction);

    @Override
    ImmutableDoubleList collectDouble(DoubleFunction<? super V> doubleFunction);

    @Override
    ImmutableFloatList collectFloat(FloatFunction<? super V> floatFunction);

    @Override
    ImmutableIntList collectInt(IntFunction<? super V> intFunction);

    @Override
    ImmutableLongList collectLong(LongFunction<? super V> longFunction);

    @Override
    ImmutableShortList collectShort(ShortFunction<? super V> shortFunction);

    @Override
    <S> ImmutableList<Pair<V, S>> zip(Iterable<S> that);

    @Override
    ImmutableList<Pair<V, Integer>> zipWithIndex();

    @Override
    <VV> ImmutableListMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function);

    @Override
    <VV> ImmutableListMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function);

    @Override
    <VV> ImmutableMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function);

    @Override
    <K2, V2> ImmutableMap<K2, V2> aggregateInPlaceBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Procedure2<? super V2, ? super V> mutatingAggregator);

    @Override
    <K2, V2> ImmutableMap<K2, V2> aggregateBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Function2<? super V2, ? super V, ? extends V2> nonMutatingAggregator);
}
