/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map;

import org.eclipse.collections.api.bag.Bag;
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
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.set.UnsortedSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionBag;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.api.tuple.Pair;

/**
 * An iterable Map whose elements are unsorted.
 */
public interface UnsortedMapIterable<K, V>
        extends MapIterable<K, V>
{
    UnsortedSetMultimap<V, K> flip();

    UnsortedMapIterable<V, K> flipUniqueValues();

    UnsortedMapIterable<K, V> tap(Procedure<? super V> procedure);

    UnsortedMapIterable<K, V> select(Predicate2<? super K, ? super V> predicate);

    UnsortedMapIterable<K, V> reject(Predicate2<? super K, ? super V> predicate);

    <R> UnsortedMapIterable<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    <K2, V2> UnsortedMapIterable<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    Bag<V> select(Predicate<? super V> predicate);

    <P> Bag<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    Bag<V> reject(Predicate<? super V> predicate);

    <P> Bag<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    PartitionBag<V> partition(Predicate<? super V> predicate);

    <S> Bag<S> selectInstancesOf(Class<S> clazz);

    <V1> Bag<V1> collect(Function<? super V, ? extends V1> function);

    BooleanBag collectBoolean(BooleanFunction<? super V> booleanFunction);

    ByteBag collectByte(ByteFunction<? super V> byteFunction);

    CharBag collectChar(CharFunction<? super V> charFunction);

    DoubleBag collectDouble(DoubleFunction<? super V> doubleFunction);

    FloatBag collectFloat(FloatFunction<? super V> floatFunction);

    IntBag collectInt(IntFunction<? super V> intFunction);

    LongBag collectLong(LongFunction<? super V> longFunction);

    ShortBag collectShort(ShortFunction<? super V> shortFunction);

    <P, V1> Bag<V1> collectWith(Function2<? super V, ? super P, ? extends V1> function, P parameter);

    <V1> Bag<V1> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends V1> function);

    <V1> Bag<V1> flatCollect(Function<? super V, ? extends Iterable<V1>> function);

    <V1> BagMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function);

    <V1> BagMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function);

    <V1> UnsortedMapIterable<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    <S> Bag<Pair<V, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    UnsortedSetIterable<Pair<V, Integer>> zipWithIndex();

    /**
     * Converts the UnsortedMapIterable to an immutable implementation. Returns this for immutable maps.
     *
     * @since 5.0
     */
    ImmutableMap<K, V> toImmutable();
}
