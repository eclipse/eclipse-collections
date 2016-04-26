/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.collection;

import net.jcip.annotations.Immutable;
import org.eclipse.collections.api.RichIterable;
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
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableByteCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableCharCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableIntCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableLongCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableShortCollection;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.partition.PartitionImmutableCollection;
import org.eclipse.collections.api.tuple.Pair;

/**
 * ImmutableCollection is the common interface between ImmutableList and ImmutableSet.
 */
@Immutable
public interface ImmutableCollection<T>
        extends RichIterable<T>
{
    ImmutableCollection<T> newWith(T element);

    ImmutableCollection<T> newWithout(T element);

    ImmutableCollection<T> newWithAll(Iterable<? extends T> elements);

    ImmutableCollection<T> newWithoutAll(Iterable<? extends T> elements);

    ImmutableCollection<T> tap(Procedure<? super T> procedure);

    ImmutableCollection<T> select(Predicate<? super T> predicate);

    <P> ImmutableCollection<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    ImmutableCollection<T> reject(Predicate<? super T> predicate);

    <P> ImmutableCollection<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionImmutableCollection<T> partition(Predicate<? super T> predicate);

    <P> PartitionImmutableCollection<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> ImmutableCollection<S> selectInstancesOf(Class<S> clazz);

    <V> ImmutableCollection<V> collect(Function<? super T, ? extends V> function);

    ImmutableBooleanCollection collectBoolean(BooleanFunction<? super T> booleanFunction);

    ImmutableByteCollection collectByte(ByteFunction<? super T> byteFunction);

    ImmutableCharCollection collectChar(CharFunction<? super T> charFunction);

    ImmutableDoubleCollection collectDouble(DoubleFunction<? super T> doubleFunction);

    ImmutableFloatCollection collectFloat(FloatFunction<? super T> floatFunction);

    ImmutableIntCollection collectInt(IntFunction<? super T> intFunction);

    ImmutableLongCollection collectLong(LongFunction<? super T> longFunction);

    ImmutableShortCollection collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ImmutableCollection<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ImmutableCollection<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ImmutableCollection<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    <V> ImmutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function);

    <V> ImmutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function);

    <V> ImmutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function);

    <V> ImmutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function);

    <V> ImmutableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ImmutableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <V> ImmutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function);

    <S> ImmutableCollection<Pair<T, S>> zip(Iterable<S> that);

    ImmutableCollection<Pair<T, Integer>> zipWithIndex();

    <K, V> ImmutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator);

    <K, V> ImmutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);
}
