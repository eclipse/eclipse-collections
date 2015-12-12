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
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.api.list.primitive.ByteList;
import org.eclipse.collections.api.list.primitive.CharList;
import org.eclipse.collections.api.list.primitive.DoubleList;
import org.eclipse.collections.api.list.primitive.FloatList;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.list.primitive.LongList;
import org.eclipse.collections.api.list.primitive.ShortList;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.ordered.ReversibleIterable;
import org.eclipse.collections.api.partition.list.PartitionList;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A map whose keys are ordered but not necessarily sorted, for example a linked hash map.
 */
public interface OrderedMap<K, V>
        extends MapIterable<K, V>, ReversibleIterable<V>
{
    OrderedMap<K, V> tap(Procedure<? super V> procedure);

    OrderedMap<V, K> flipUniqueValues();

    ListMultimap<V, K> flip();

    OrderedMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    OrderedMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    <K2, V2> OrderedMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    <R> OrderedMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    ImmutableOrderedMap<K, V> toImmutable();

    OrderedMap<K, V> toReversed();

    OrderedMap<K, V> take(int count);

    OrderedMap<K, V> takeWhile(Predicate<? super V> predicate);

    OrderedMap<K, V> drop(int count);

    OrderedMap<K, V> dropWhile(Predicate<? super V> predicate);

    PartitionList<V> partitionWhile(Predicate<? super V> predicate);

    ListIterable<V> distinct();

    ListIterable<V> select(Predicate<? super V> predicate);

    <P> ListIterable<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    ListIterable<V> reject(Predicate<? super V> predicate);

    <P> ListIterable<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    PartitionList<V> partition(Predicate<? super V> predicate);

    <P> PartitionList<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    BooleanList collectBoolean(BooleanFunction<? super V> booleanFunction);

    ByteList collectByte(ByteFunction<? super V> byteFunction);

    CharList collectChar(CharFunction<? super V> charFunction);

    DoubleList collectDouble(DoubleFunction<? super V> doubleFunction);

    FloatList collectFloat(FloatFunction<? super V> floatFunction);

    IntList collectInt(IntFunction<? super V> intFunction);

    LongList collectLong(LongFunction<? super V> longFunction);

    ShortList collectShort(ShortFunction<? super V> shortFunction);

    <S> ListIterable<Pair<V, S>> zip(Iterable<S> that);

    ListIterable<Pair<V, Integer>> zipWithIndex();

    <P, V1> ListIterable<V1> collectWith(Function2<? super V, ? super P, ? extends V1> function, P parameter);

    <V1> ListIterable<V1> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends V1> function);

    <S> ListIterable<S> selectInstancesOf(Class<S> clazz);

    <V1> ListIterable<V1> flatCollect(Function<? super V, ? extends Iterable<V1>> function);

    <V1> ListMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function);

    <V1> ListMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function);

    <V1> OrderedMap<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function);

    <KK, VV> OrderedMap<KK, VV> aggregateInPlaceBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Procedure2<? super VV, ? super V> mutatingAggregator);

    <KK, VV> OrderedMap<KK, VV> aggregateBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Function2<? super VV, ? super V, ? extends VV> nonMutatingAggregator);
}
