/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map.primitive;

import org.eclipse.collections.api.bag.MutableBag;
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
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;

public interface MutablePrimitiveObjectMap<V>
{
    void clear();

    <K, VV> MutableMap<K, VV> aggregateInPlaceBy(Function<? super V, ? extends K> groupBy, Function0<? extends VV> zeroValueFactory, Procedure2<? super VV, ? super V> mutatingAggregator);

    <K, VV> MutableMap<K, VV> aggregateBy(Function<? super V, ? extends K> groupBy, Function0<? extends VV> zeroValueFactory, Function2<? super VV, ? super V, ? extends VV> nonMutatingAggregator);

    <VV> MutableBagMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function);

    <VV> MutableBagMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function);

    <VV> MutableMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function);

    <VV> MutableBag<VV> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends VV> function);

    <VV> MutableCollection<VV> collect(Function<? super V, ? extends VV> function);

    MutableBooleanCollection collectBoolean(BooleanFunction<? super V> booleanFunction);

    MutableByteCollection collectByte(ByteFunction<? super V> byteFunction);

    MutableCharCollection collectChar(CharFunction<? super V> charFunction);

    MutableDoubleCollection collectDouble(DoubleFunction<? super V> doubleFunction);

    MutableFloatCollection collectFloat(FloatFunction<? super V> floatFunction);

    MutableIntCollection collectInt(IntFunction<? super V> intFunction);

    MutableLongCollection collectLong(LongFunction<? super V> longFunction);

    MutableShortCollection collectShort(ShortFunction<? super V> shortFunction);

    <P, VV> MutableCollection<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter);

    <S> MutableBag<S> selectInstancesOf(Class<S> clazz);

    MutableCollection<V> select(Predicate<? super V> predicate);

    <P> MutableCollection<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    MutableCollection<V> reject(Predicate<? super V> predicate);

    <P> MutableCollection<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    PartitionMutableBag<V> partition(Predicate<? super V> predicate);

    <P> PartitionMutableBag<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    /**
     * @deprecated in 7.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    <S> MutableBag<Pair<V, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 7.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    MutableSet<Pair<V, Integer>> zipWithIndex();
}
