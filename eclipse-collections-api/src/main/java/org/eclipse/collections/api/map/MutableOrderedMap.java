/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map;

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
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.tuple.Pair;

public interface MutableOrderedMap<K, V> extends OrderedMap<K, V>, MutableMapIterable<K, V>
{
    @Override
    MutableOrderedMap<K, V> tap(Procedure<? super V> procedure);

    @Override
    MutableOrderedMap<V, K> flipUniqueValues();

    @Override
    MutableListMultimap<V, K> flip();

    @Override
    MutableOrderedMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    @Override
    MutableOrderedMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    @Override
    <K2, V2> MutableOrderedMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    <R> MutableOrderedMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    @Override
    MutableOrderedMap<K, V> toReversed();

    @Override
    MutableOrderedMap<K, V> take(int count);

    @Override
    MutableOrderedMap<K, V> takeWhile(Predicate<? super V> predicate);

    @Override
    MutableOrderedMap<K, V> drop(int count);

    @Override
    MutableOrderedMap<K, V> dropWhile(Predicate<? super V> predicate);

    @Override
    PartitionMutableList<V> partitionWhile(Predicate<? super V> predicate);

    @Override
    MutableList<V> distinct();

    @Override
    MutableList<V> select(Predicate<? super V> predicate);

    @Override
    <P> MutableList<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    MutableList<V> reject(Predicate<? super V> predicate);

    @Override
    <P> MutableList<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    PartitionMutableList<V> partition(Predicate<? super V> predicate);

    @Override
    <P> PartitionMutableList<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    @Override
    MutableBooleanList collectBoolean(BooleanFunction<? super V> booleanFunction);

    @Override
    MutableByteList collectByte(ByteFunction<? super V> byteFunction);

    @Override
    MutableCharList collectChar(CharFunction<? super V> charFunction);

    @Override
    MutableDoubleList collectDouble(DoubleFunction<? super V> doubleFunction);

    @Override
    MutableFloatList collectFloat(FloatFunction<? super V> floatFunction);

    @Override
    MutableIntList collectInt(IntFunction<? super V> intFunction);

    @Override
    MutableLongList collectLong(LongFunction<? super V> longFunction);

    @Override
    MutableShortList collectShort(ShortFunction<? super V> shortFunction);

    @Override
    <S> MutableList<Pair<V, S>> zip(Iterable<S> that);

    @Override
    MutableList<Pair<V, Integer>> zipWithIndex();

    @Override
    <VV> MutableList<VV> collect(Function<? super V, ? extends VV> function);

    @Override
    <P, V1> MutableList<V1> collectWith(Function2<? super V, ? super P, ? extends V1> function, P parameter);

    @Override
    <V1> MutableList<V1> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends V1> function);

    @Override
    <S> MutableList<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V1> MutableList<V1> flatCollect(Function<? super V, ? extends Iterable<V1>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, V1> MutableList<V1> flatCollectWith(Function2<? super V, ? super P, ? extends Iterable<V1>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    @Override
    <V1> MutableListMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function);

    @Override
    <V1> MutableListMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function);

    @Override
    <V1> MutableOrderedMap<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function);
}
