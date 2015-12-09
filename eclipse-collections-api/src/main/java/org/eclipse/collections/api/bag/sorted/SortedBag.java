/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.bag.sorted;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.bag.Bag;
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
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.api.list.primitive.ByteList;
import org.eclipse.collections.api.list.primitive.CharList;
import org.eclipse.collections.api.list.primitive.DoubleList;
import org.eclipse.collections.api.list.primitive.FloatList;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.list.primitive.LongList;
import org.eclipse.collections.api.list.primitive.ShortList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.sorted.SortedMapIterable;
import org.eclipse.collections.api.multimap.sortedbag.SortedBagMultimap;
import org.eclipse.collections.api.ordered.ReversibleIterable;
import org.eclipse.collections.api.ordered.SortedIterable;
import org.eclipse.collections.api.partition.bag.sorted.PartitionSortedBag;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.api.tuple.Pair;

/**
 * An Iterable whose elements are sorted by some comparator or their natural ordering and may contain duplicate entries.
 *
 * @since 4.2
 */
public interface SortedBag<T>
        extends Bag<T>, Comparable<SortedBag<T>>, SortedIterable<T>, ReversibleIterable<T>
{
    SortedBag<T> selectByOccurrences(IntPredicate predicate);

    SortedMapIterable<T, Integer> toMapOfItemToCount();

    /**
     * Convert the SortedBag to an ImmutableSortedBag.  If the bag is immutable, it returns itself.
     * Not yet supported.
     */
    ImmutableSortedBag<T> toImmutable();

    /**
     * Returns the minimum element out of this container based on the natural order, not the order of this bag.
     * If you want the minimum element based on the order of this bag, use {@link #getFirst()}.
     *
     * @throws ClassCastException     if the elements are not {@link Comparable}
     * @throws NoSuchElementException if the SortedBag is empty
     * @since 1.0
     */
    T min();

    /**
     * Returns the maximum element out of this container based on the natural order, not the order of this bag.
     * If you want the maximum element based on the order of this bag, use {@link #getLast()}.
     *
     * @throws ClassCastException     if the elements are not {@link Comparable}
     * @throws NoSuchElementException if the SortedBag is empty
     * @since 1.0
     */
    T max();

    SortedBag<T> tap(Procedure<? super T> procedure);

    SortedBag<T> select(Predicate<? super T> predicate);

    <P> SortedBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    SortedBag<T> reject(Predicate<? super T> predicate);

    <P> SortedBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionSortedBag<T> partition(Predicate<? super T> predicate);

    <P> PartitionSortedBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionSortedBag<T> partitionWhile(Predicate<? super T> predicate);

    <S> SortedBag<S> selectInstancesOf(Class<S> clazz);

    <V> ListIterable<V> collect(Function<? super T, ? extends V> function);

    BooleanList collectBoolean(BooleanFunction<? super T> booleanFunction);

    ByteList collectByte(ByteFunction<? super T> byteFunction);

    CharList collectChar(CharFunction<? super T> charFunction);

    DoubleList collectDouble(DoubleFunction<? super T> doubleFunction);

    FloatList collectFloat(FloatFunction<? super T> floatFunction);

    IntList collectInt(IntFunction<? super T> intFunction);

    LongList collectLong(LongFunction<? super T> longFunction);

    ShortList collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    SortedSetIterable<T> distinct();

    SortedBag<T> takeWhile(Predicate<? super T> predicate);

    SortedBag<T> dropWhile(Predicate<? super T> predicate);

    <V> SortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> SortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * Can return an MapIterable that's backed by a LinkedHashMap.
     */
    <K, V> MapIterable<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);

    /**
     * Can return an MapIterable that's backed by a LinkedHashMap.
     */
    <K, V> MapIterable<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator);

    /**
     * Returns the comparator used to order the elements in this bag, or null if this bag uses the natural ordering of
     * its elements.
     */
    Comparator<? super T> comparator();

    SortedSetIterable<Pair<T, Integer>> zipWithIndex();

    SortedBag<T> toReversed();

    SortedBag<T> take(int count);

    SortedBag<T> drop(int count);
}
