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

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableByteBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableFloatBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableShortBag;
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
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;

public interface MutablePrimitiveObjectMap<V> extends PrimitiveObjectMap<V>
{
    void clear();

    <K, VV> MutableMap<K, VV> aggregateInPlaceBy(Function<? super V, ? extends K> groupBy, Function0<? extends VV> zeroValueFactory, Procedure2<? super VV, ? super V> mutatingAggregator);

    <K, VV> MutableMap<K, VV> aggregateBy(Function<? super V, ? extends K> groupBy, Function0<? extends VV> zeroValueFactory, Function2<? super VV, ? super V, ? extends VV> nonMutatingAggregator);

    <VV> MutableBagMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function);

    <VV> MutableBagMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function);

    <VV> MutableMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function);

    <VV> MutableBag<VV> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends VV> function);

    <VV> MutableBag<VV> collect(Function<? super V, ? extends VV> function);

    MutableBooleanBag collectBoolean(BooleanFunction<? super V> booleanFunction);

    MutableByteBag collectByte(ByteFunction<? super V> byteFunction);

    MutableCharBag collectChar(CharFunction<? super V> charFunction);

    MutableDoubleBag collectDouble(DoubleFunction<? super V> doubleFunction);

    MutableFloatBag collectFloat(FloatFunction<? super V> floatFunction);

    MutableIntBag collectInt(IntFunction<? super V> intFunction);

    MutableLongBag collectLong(LongFunction<? super V> longFunction);

    MutableShortBag collectShort(ShortFunction<? super V> shortFunction);

    <P, VV> MutableBag<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter);

    <VV> MutableBag<VV> flatCollect(Function<? super V, ? extends Iterable<VV>> function);

    <S> MutableBag<S> selectInstancesOf(Class<S> clazz);

    MutableBag<V> select(Predicate<? super V> predicate);

    <P> MutableBag<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    MutableBag<V> reject(Predicate<? super V> predicate);

    <P> MutableBag<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

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
