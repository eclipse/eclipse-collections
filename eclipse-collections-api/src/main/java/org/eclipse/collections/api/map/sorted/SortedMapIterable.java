/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.map.sorted;

import java.util.Comparator;

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
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.sortedset.SortedSetMultimap;
import org.eclipse.collections.api.ordered.ReversibleIterable;
import org.eclipse.collections.api.partition.list.PartitionList;
import org.eclipse.collections.api.tuple.Pair;

/**
 * An iterable Map whose elements are sorted.
 */
public interface SortedMapIterable<K, V>
        extends MapIterable<K, V>, ReversibleIterable<V>
{
    Comparator<? super K> comparator();

    // TODO: Keys could be ordered
    SortedSetMultimap<V, K> flip();

    // TODO: When we have implementations of linked hash maps
    // OrderedMapIterable<V, K> flipUniqueValues();

    SortedMapIterable<K, V> select(Predicate2<? super K, ? super V> predicate);

    SortedMapIterable<K, V> reject(Predicate2<? super K, ? super V> predicate);

    <R> SortedMapIterable<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    SortedMapIterable<K, V> tap(Procedure<? super V> procedure);

    ListIterable<V> select(Predicate<? super V> predicate);

    <P> ListIterable<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    ListIterable<V> reject(Predicate<? super V> predicate);

    <P> ListIterable<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    PartitionList<V> partition(Predicate<? super V> predicate);

    <P> PartitionList<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    <S> ListIterable<S> selectInstancesOf(Class<S> clazz);

    <V1> ListIterable<V1> collect(Function<? super V, ? extends V1> function);

    BooleanList collectBoolean(BooleanFunction<? super V> booleanFunction);

    ByteList collectByte(ByteFunction<? super V> byteFunction);

    CharList collectChar(CharFunction<? super V> charFunction);

    DoubleList collectDouble(DoubleFunction<? super V> doubleFunction);

    FloatList collectFloat(FloatFunction<? super V> floatFunction);

    IntList collectInt(IntFunction<? super V> intFunction);

    LongList collectLong(LongFunction<? super V> longFunction);

    ShortList collectShort(ShortFunction<? super V> shortFunction);

    <P, V1> ListIterable<V1> collectWith(Function2<? super V, ? super P, ? extends V1> function, P parameter);

    <V1> ListIterable<V1> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends V1> function);

    <V1> ListIterable<V1> flatCollect(Function<? super V, ? extends Iterable<V1>> function);

    <S> ListIterable<Pair<V, S>> zip(Iterable<S> that);

    ListIterable<Pair<V, Integer>> zipWithIndex();

    <VV> ListMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function);

    <VV> ListMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function);

    SortedMapIterable<K, V> toReversed();

    SortedMapIterable<K, V> take(int count);

    SortedMapIterable<K, V> takeWhile(Predicate<? super V> predicate);

    SortedMapIterable<K, V> drop(int count);

    SortedMapIterable<K, V> dropWhile(Predicate<? super V> predicate);

    // TODO: PartitionSortedMapIterable?
    PartitionList<V> partitionWhile(Predicate<? super V> predicate);

    ListIterable<V> distinct();

    /**
     * Converts the SortedMapIterable to an immutable implementation. Returns this for immutable maps.
     *
     * @since 5.0
     */
    ImmutableSortedMap<K, V> toImmutable();
}
