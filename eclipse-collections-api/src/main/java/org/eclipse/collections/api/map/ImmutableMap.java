/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.primitive.ImmutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.ImmutableByteBag;
import org.eclipse.collections.api.bag.primitive.ImmutableCharBag;
import org.eclipse.collections.api.bag.primitive.ImmutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.ImmutableFloatBag;
import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.ImmutableLongBag;
import org.eclipse.collections.api.bag.primitive.ImmutableShortBag;
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
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionImmutableBag;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * An ImmutableMap is different than a JCF Map but in that it has no mutating methods.  It shares the read-only
 * protocol of a JDK Map.
 */
public interface ImmutableMap<K, V>
        extends UnsortedMapIterable<K, V>, ImmutableMapIterable<K, V>
{
    @Override
    ImmutableMap<K, V> newWithKeyValue(K key, V value);

    @Override
    ImmutableMap<K, V> newWithAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues);

    @Override
    ImmutableMap<K, V> newWithAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs);

    @Override
    ImmutableMap<K, V> newWithoutKey(K key);

    @Override
    ImmutableMap<K, V> newWithoutAllKeys(Iterable<? extends K> keys);

    MutableMap<K, V> toMap();

    @Override
    ImmutableSetMultimap<V, K> flip();

    @Override
    ImmutableMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    @Override
    ImmutableMap<K, V> tap(Procedure<? super V> procedure);

    @Override
    ImmutableBag<V> select(Predicate<? super V> predicate);

    @Override
    <P> ImmutableBag<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    ImmutableBag<V> reject(Predicate<? super V> predicate);

    @Override
    <P> ImmutableBag<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    PartitionImmutableBag<V> partition(Predicate<? super V> predicate);

    @Override
    <P> PartitionImmutableBag<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    <S> ImmutableBag<S> selectInstancesOf(Class<S> clazz);

    @Override
    <K2, V2> ImmutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <R> ImmutableMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    @Override
    <VV> ImmutableBag<VV> collect(Function<? super V, ? extends VV> function);

    @Override
    <P, VV> ImmutableBag<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter);

    @Override
    ImmutableBooleanBag collectBoolean(BooleanFunction<? super V> booleanFunction);

    @Override
    ImmutableByteBag collectByte(ByteFunction<? super V> byteFunction);

    @Override
    ImmutableCharBag collectChar(CharFunction<? super V> charFunction);

    @Override
    ImmutableDoubleBag collectDouble(DoubleFunction<? super V> doubleFunction);

    @Override
    ImmutableFloatBag collectFloat(FloatFunction<? super V> floatFunction);

    @Override
    ImmutableIntBag collectInt(IntFunction<? super V> intFunction);

    @Override
    ImmutableLongBag collectLong(LongFunction<? super V> longFunction);

    @Override
    ImmutableShortBag collectShort(ShortFunction<? super V> shortFunction);

    @Override
    <R> ImmutableBag<R> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends R> function);

    @Override
    <R> ImmutableBag<R> flatCollect(Function<? super V, ? extends Iterable<R>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, R> ImmutableBag<R> flatCollectWith(Function2<? super V, ? super P, ? extends Iterable<R>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    <S> ImmutableBag<Pair<V, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    ImmutableSet<Pair<V, Integer>> zipWithIndex();

    @Override
    <VV> ImmutableBagMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function);

    @Override
    <VV> ImmutableBagMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function);

    @Override
    <V1> ImmutableMap<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function);

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

    @Override
    ImmutableMap<V, K> flipUniqueValues();
}
