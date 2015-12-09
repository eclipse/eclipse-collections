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

import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableByteBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableFloatBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableShortBag;
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
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A MutableBag is a Collection whose elements are unordered and may contain duplicate entries.  It varies from
 * MutableCollection in that it adds a protocol for determining, adding, and removing the number of occurrences for an
 * item.
 *
 * @since 1.0
 */
public interface MutableBag<T>
        extends UnsortedBag<T>, MutableBagIterable<T>
{
    MutableMap<T, Integer> toMapOfItemToCount();

    MutableBag<T> selectByOccurrences(IntPredicate predicate);

    MutableBag<T> with(T element);

    MutableBag<T> without(T element);

    MutableBag<T> withAll(Iterable<? extends T> elements);

    MutableBag<T> withoutAll(Iterable<? extends T> elements);

    MutableBag<T> newEmpty();

    MutableBag<T> asUnmodifiable();

    MutableBag<T> asSynchronized();

    PartitionMutableBag<T> partition(Predicate<? super T> predicate);

    <P> PartitionMutableBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <V> MutableBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> MutableBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    <S> MutableBag<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    MutableSet<Pair<T, Integer>> zipWithIndex();

    MutableBag<T> tap(Procedure<? super T> procedure);

    MutableBag<T> select(Predicate<? super T> predicate);

    <P> MutableBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    MutableBag<T> reject(Predicate<? super T> predicate);

    <P> MutableBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> MutableBag<S> selectInstancesOf(Class<S> clazz);

    <V> MutableBag<V> collect(Function<? super T, ? extends V> function);

    MutableByteBag collectByte(ByteFunction<? super T> byteFunction);

    MutableCharBag collectChar(CharFunction<? super T> charFunction);

    MutableIntBag collectInt(IntFunction<? super T> intFunction);

    MutableBooleanBag collectBoolean(BooleanFunction<? super T> booleanFunction);

    MutableDoubleBag collectDouble(DoubleFunction<? super T> doubleFunction);

    MutableFloatBag collectFloat(FloatFunction<? super T> floatFunction);

    MutableLongBag collectLong(LongFunction<? super T> longFunction);

    MutableShortBag collectShort(ShortFunction<? super T> shortFunction);

    <P, V> MutableBag<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> MutableBag<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> MutableBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);
}
