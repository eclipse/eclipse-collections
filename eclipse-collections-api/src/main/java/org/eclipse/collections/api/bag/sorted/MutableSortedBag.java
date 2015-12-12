/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.bag.sorted;

import org.eclipse.collections.api.bag.MutableBagIterable;
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
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableFloatList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.list.primitive.MutableShortList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.partition.bag.sorted.PartitionMutableSortedBag;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 4.2
 */
public interface MutableSortedBag<T>
        extends SortedBag<T>, MutableBagIterable<T>, Cloneable
{
    MutableSortedBag<T> selectByOccurrences(IntPredicate predicate);

    MutableSortedMap<T, Integer> toMapOfItemToCount();

    MutableSortedBag<T> with(T element);

    MutableSortedBag<T> without(T element);

    MutableSortedBag<T> withAll(Iterable<? extends T> elements);

    MutableSortedBag<T> withoutAll(Iterable<? extends T> elements);

    MutableSortedBag<T> newEmpty();

    MutableSortedBag<T> clone();

    /**
     * Returns an unmodifiable view of the set. The returned set will be <tt>Serializable</tt> if this set is <tt>Serializable</tt>.
     *
     * @return an unmodifiable view of this set
     */
    MutableSortedBag<T> asUnmodifiable();

    MutableSortedBag<T> asSynchronized();

    MutableSortedBag<T> tap(Procedure<? super T> procedure);

    MutableSortedBag<T> select(Predicate<? super T> predicate);

    <P> MutableSortedBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    MutableSortedBag<T> reject(Predicate<? super T> predicate);

    <P> MutableSortedBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionMutableSortedBag<T> partition(Predicate<? super T> predicate);

    <P> PartitionMutableSortedBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionMutableSortedBag<T> partitionWhile(Predicate<? super T> predicate);

    <S> MutableSortedBag<S> selectInstancesOf(Class<S> clazz);

    <V> MutableList<V> collect(Function<? super T, ? extends V> function);

    MutableBooleanList collectBoolean(BooleanFunction<? super T> booleanFunction);

    MutableByteList collectByte(ByteFunction<? super T> byteFunction);

    MutableCharList collectChar(CharFunction<? super T> charFunction);

    MutableDoubleList collectDouble(DoubleFunction<? super T> doubleFunction);

    MutableFloatList collectFloat(FloatFunction<? super T> floatFunction);

    MutableIntList collectInt(IntFunction<? super T> intFunction);

    MutableLongList collectLong(LongFunction<? super T> longFunction);

    MutableShortList collectShort(ShortFunction<? super T> shortFunction);

    <P, V> MutableList<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> MutableList<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> MutableList<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    MutableSortedSet<T> distinct();

    MutableSortedBag<T> takeWhile(Predicate<? super T> predicate);

    MutableSortedBag<T> dropWhile(Predicate<? super T> predicate);

    <V> MutableSortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> MutableSortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * Can return an MutableMap that's backed by a LinkedHashMap.
     */
    <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);

    /**
     * Can return an MutableMap that's backed by a LinkedHashMap.
     */
    <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator);

    <S> MutableList<Pair<T, S>> zip(Iterable<S> that);

    MutableSortedSet<Pair<T, Integer>> zipWithIndex();

    MutableSortedBag<T> toReversed();

    MutableSortedBag<T> take(int count);

    MutableSortedBag<T> drop(int count);
}
