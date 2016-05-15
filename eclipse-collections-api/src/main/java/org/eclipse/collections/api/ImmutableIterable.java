/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.ImmutableMapIterable;
import org.eclipse.collections.api.map.primitive.ImmutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.partition.PartitionImmutableIterable;
import org.eclipse.collections.api.tuple.Pair;

public interface ImmutableIterable<T> extends RichIterable<T>
{
    @Override
    ImmutableIterable<T> tap(Procedure<? super T> procedure);

    @Override
    ImmutableIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> ImmutableIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    ImmutableIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ImmutableIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionImmutableIterable<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionImmutableIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ImmutableIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> ImmutableIterable<V> collect(Function<? super T, ? extends V> function);

    /*
    @Override
    ImmutableBooleanIterable collectBoolean(BooleanFunction<? super T> booleanFunction);

    @Override
    ImmutableByteIterable collectByte(ByteFunction<? super T> byteFunction);

    @Override
    ImmutableCharIterable collectChar(CharFunction<? super T> charFunction);

    @Override
    ImmutableDoubleIterable collectDouble(DoubleFunction<? super T> doubleFunction);

    @Override
    ImmutableFloatIterable collectFloat(FloatFunction<? super T> floatFunction);

    @Override
    ImmutableIntIterable collectInt(IntFunction<? super T> intFunction);

    @Override
    ImmutableLongIterable collectLong(LongFunction<? super T> longFunction);

    @Override
    ImmutableShortIterable collectShort(ShortFunction<? super T> shortFunction);
    */

    @Override
    <P, V> ImmutableIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    @Override
    <V> ImmutableIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    @Override
    <V> ImmutableIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    @Override
    <V> ImmutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function);

    @Override
    <V> ImmutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function);

    @Override
    <V> ImmutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function);

    @Override
    <V> ImmutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function);

    @Override
    <V> ImmutableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> ImmutableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    <V> ImmutableMapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function);

    @Override
    <S> ImmutableIterable<Pair<T, S>> zip(Iterable<S> that);

    @Override
    ImmutableIterable<Pair<T, Integer>> zipWithIndex();

    @Override
    <K, V> ImmutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator);

    @Override
    <K, V> ImmutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);
}
