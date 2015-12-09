/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.bag;

import org.eclipse.collections.api.bag.primitive.ImmutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.ImmutableByteBag;
import org.eclipse.collections.api.bag.primitive.ImmutableCharBag;
import org.eclipse.collections.api.bag.primitive.ImmutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.ImmutableFloatBag;
import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.ImmutableLongBag;
import org.eclipse.collections.api.bag.primitive.ImmutableShortBag;
import org.eclipse.collections.api.block.function.Function;
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
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionImmutableBag;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;

/**
 * @since 1.0
 */
public interface ImmutableBag<T> extends UnsortedBag<T>, ImmutableBagIterable<T>
{
    ImmutableBag<T> newWith(T element);

    ImmutableBag<T> newWithout(T element);

    ImmutableBag<T> newWithAll(Iterable<? extends T> elements);

    ImmutableBag<T> newWithoutAll(Iterable<? extends T> elements);

    ImmutableBag<T> selectByOccurrences(IntPredicate predicate);

    ImmutableBag<T> tap(Procedure<? super T> procedure);

    ImmutableBag<T> select(Predicate<? super T> predicate);

    <P> ImmutableBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    ImmutableBag<T> reject(Predicate<? super T> predicate);

    <P> ImmutableBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionImmutableBag<T> partition(Predicate<? super T> predicate);

    <P> PartitionImmutableBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> ImmutableBag<S> selectInstancesOf(Class<S> clazz);

    <V> ImmutableBag<V> collect(Function<? super T, ? extends V> function);

    ImmutableBooleanBag collectBoolean(BooleanFunction<? super T> booleanFunction);

    ImmutableByteBag collectByte(ByteFunction<? super T> byteFunction);

    ImmutableCharBag collectChar(CharFunction<? super T> charFunction);

    ImmutableDoubleBag collectDouble(DoubleFunction<? super T> doubleFunction);

    ImmutableFloatBag collectFloat(FloatFunction<? super T> floatFunction);

    ImmutableIntBag collectInt(IntFunction<? super T> intFunction);

    ImmutableLongBag collectLong(LongFunction<? super T> longFunction);

    ImmutableShortBag collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ImmutableBag<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ImmutableBag<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ImmutableBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    <V> ImmutableBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ImmutableBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    <S> ImmutableBag<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    ImmutableSet<Pair<T, Integer>> zipWithIndex();

    /**
     * @since 6.0
     */
    ImmutableList<ObjectIntPair<T>> topOccurrences(int count);

    /**
     * @since 6.0
     */
    ImmutableList<ObjectIntPair<T>> bottomOccurrences(int count);
}
