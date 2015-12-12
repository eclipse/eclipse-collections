/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.bag;

import org.eclipse.collections.api.bag.primitive.BooleanBag;
import org.eclipse.collections.api.bag.primitive.ByteBag;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.bag.primitive.DoubleBag;
import org.eclipse.collections.api.bag.primitive.FloatBag;
import org.eclipse.collections.api.bag.primitive.IntBag;
import org.eclipse.collections.api.bag.primitive.LongBag;
import org.eclipse.collections.api.bag.primitive.ShortBag;
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
import org.eclipse.collections.api.multimap.bag.UnsortedBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionUnsortedBag;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.api.tuple.Pair;

public interface UnsortedBag<T> extends Bag<T>
{
    UnsortedBag<T> selectByOccurrences(IntPredicate predicate);

    UnsortedBag<T> select(Predicate<? super T> predicate);

    <P> UnsortedBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    UnsortedBag<T> reject(Predicate<? super T> predicate);

    <P> UnsortedBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> UnsortedBag<S> selectInstancesOf(Class<S> clazz);

    PartitionUnsortedBag<T> partition(Predicate<? super T> predicate);

    <V> UnsortedBag<V> collect(Function<? super T, ? extends V> function);

    BooleanBag collectBoolean(BooleanFunction<? super T> booleanFunction);

    ByteBag collectByte(ByteFunction<? super T> byteFunction);

    CharBag collectChar(CharFunction<? super T> charFunction);

    DoubleBag collectDouble(DoubleFunction<? super T> doubleFunction);

    FloatBag collectFloat(FloatFunction<? super T> floatFunction);

    IntBag collectInt(IntFunction<? super T> intFunction);

    LongBag collectLong(LongFunction<? super T> longFunction);

    ShortBag collectShort(ShortFunction<? super T> shortFunction);

    <P, V> UnsortedBag<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> UnsortedBag<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> UnsortedBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    <S> UnsortedBag<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    UnsortedSetIterable<Pair<T, Integer>> zipWithIndex();

    <V> UnsortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> UnsortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * Converts the UnsortedBag to an ImmutableBag. If the bag is immutable, it returns itself.
     */
    ImmutableBag<T> toImmutable();
}
