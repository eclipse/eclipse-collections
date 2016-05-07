/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map.primitive;

import java.util.Collection;
import java.util.Map;

import org.eclipse.collections.api.RichIterable;
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
import org.eclipse.collections.api.map.UnsortedMapIterable;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionBag;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 8.0.
 */
public interface PrimitiveObjectMap<V> extends RichIterable<V>
{
    boolean containsValue(Object value);

    void forEachValue(Procedure<? super V> procedure);

    /**
     * Follows the same general contract as {@link Map#equals(Object)}.
     */
    @Override
    boolean equals(Object o);

    /**
     * Follows the same general contract as {@link Map#hashCode()}.
     */
    @Override
    int hashCode();

    /**
     * Returns a string representation of this PrimitiveObjectMap. The string representation consists of a list of the
     * map's key-value pairs in the order they are returned by its iterator. The key and value in each key-value pair are separated
     * by a colon (<tt>":"</tt>) and each pair is enclosed in square brackets (<tt>"[]"</tt>). Adjacent key-value pairs
     * are separated by the characters <tt>", "</tt> (comma and space). Keys and values are converted to strings as by
     * String#valueOf().
     *
     * @return a string representation of this PrimitiveObjectMap
     */
    @Override
    String toString();

    Collection<V> values();

    @Override
    Bag<V> select(Predicate<? super V> predicate);

    @Override
    <P> Bag<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    Bag<V> reject(Predicate<? super V> predicate);

    @Override
    <P> Bag<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    PartitionBag<V> partition(Predicate<? super V> predicate);

    @Override
    <P> PartitionBag<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    <S> Bag<S> selectInstancesOf(Class<S> clazz);

    @Override
    <VV> Bag<VV> collect(Function<? super V, ? extends VV> function);

    @Override
    BooleanBag collectBoolean(BooleanFunction<? super V> booleanFunction);

    @Override
    ByteBag collectByte(ByteFunction<? super V> byteFunction);

    @Override
    CharBag collectChar(CharFunction<? super V> charFunction);

    @Override
    DoubleBag collectDouble(DoubleFunction<? super V> doubleFunction);

    @Override
    FloatBag collectFloat(FloatFunction<? super V> floatFunction);

    @Override
    IntBag collectInt(IntFunction<? super V> intFunction);

    @Override
    LongBag collectLong(LongFunction<? super V> longFunction);

    @Override
    ShortBag collectShort(ShortFunction<? super V> shortFunction);

    @Override
    <P, VV> Bag<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter);

    @Override
    <VV> Bag<VV> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends VV> function);

    @Override
    <VV> Bag<VV> flatCollect(Function<? super V, ? extends Iterable<VV>> function);

    @Override
    <VV> BagMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function);

    @Override
    <VV> BagMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function);

    @Override
    <VV> UnsortedMapIterable<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    <S> Bag<Pair<V, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    UnsortedSetIterable<Pair<V, Integer>> zipWithIndex();
}
