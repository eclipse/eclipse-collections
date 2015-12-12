/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.set.sorted;

import java.util.Set;
import java.util.SortedSet;

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
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.ImmutableByteList;
import org.eclipse.collections.api.list.primitive.ImmutableCharList;
import org.eclipse.collections.api.list.primitive.ImmutableDoubleList;
import org.eclipse.collections.api.list.primitive.ImmutableFloatList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.ImmutableShortList;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.partition.set.sorted.PartitionImmutableSortedSet;
import org.eclipse.collections.api.set.ImmutableSetIterable;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.tuple.Pair;
import net.jcip.annotations.Immutable;

/**
 * ImmutableSortedSet is the non-modifiable equivalent interface to {@link MutableSortedSet}. {@link
 * MutableSortedSet#toImmutable()} will give you an appropriately trimmed implementation of ImmutableSortedSet. All
 * ImmutableSortedSet implementations must implement the {@link SortedSet} interface so they can satisfy the {@link
 * Set#equals(Object)} contract and be compared against other Sets.
 */
@Immutable
public interface ImmutableSortedSet<T>
        extends SortedSetIterable<T>, ImmutableSetIterable<T>
{
    ImmutableSortedSet<T> newWith(T element);

    ImmutableSortedSet<T> newWithout(T element);

    ImmutableSortedSet<T> newWithAll(Iterable<? extends T> elements);

    ImmutableSortedSet<T> newWithoutAll(Iterable<? extends T> elements);

    ImmutableSortedSet<T> tap(Procedure<? super T> procedure);

    ImmutableSortedSet<T> select(Predicate<? super T> predicate);

    <P> ImmutableSortedSet<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    ImmutableSortedSet<T> reject(Predicate<? super T> predicate);

    <P> ImmutableSortedSet<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionImmutableSortedSet<T> partition(Predicate<? super T> predicate);

    <P> PartitionImmutableSortedSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionImmutableSortedSet<T> partitionWhile(Predicate<? super T> predicate);

    <S> ImmutableSortedSet<S> selectInstancesOf(Class<S> clazz);

    <V> ImmutableList<V> collect(Function<? super T, ? extends V> function);

    ImmutableBooleanList collectBoolean(BooleanFunction<? super T> booleanFunction);

    ImmutableByteList collectByte(ByteFunction<? super T> byteFunction);

    ImmutableCharList collectChar(CharFunction<? super T> charFunction);

    ImmutableDoubleList collectDouble(DoubleFunction<? super T> doubleFunction);

    ImmutableFloatList collectFloat(FloatFunction<? super T> floatFunction);

    ImmutableIntList collectInt(IntFunction<? super T> intFunction);

    ImmutableLongList collectLong(LongFunction<? super T> longFunction);

    ImmutableShortList collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ImmutableList<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ImmutableList<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ImmutableList<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    ImmutableSortedSet<T> distinct();

    ImmutableSortedSet<T> takeWhile(Predicate<? super T> predicate);

    ImmutableSortedSet<T> dropWhile(Predicate<? super T> predicate);

    <V> ImmutableSortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ImmutableSortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <S> ImmutableList<Pair<T, S>> zip(Iterable<S> that);

    ImmutableSortedSet<Pair<T, Integer>> zipWithIndex();

    ImmutableSortedSet<T> toReversed();

    ImmutableSortedSet<T> take(int count);

    ImmutableSortedSet<T> drop(int count);

    SortedSet<T> castToSortedSet();

    ImmutableSortedSet<T> union(SetIterable<? extends T> set);

    ImmutableSortedSet<T> intersect(SetIterable<? extends T> set);

    ImmutableSortedSet<T> difference(SetIterable<? extends T> subtrahendSet);

    ImmutableSortedSet<T> symmetricDifference(SetIterable<? extends T> setB);

    ImmutableSortedSet<SortedSetIterable<T>> powerSet();
}
