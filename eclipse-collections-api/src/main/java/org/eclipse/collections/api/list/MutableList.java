/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.list;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.HashingStrategy;
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
import org.eclipse.collections.api.collection.MutableCollection;
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

/**
 * A MutableList is an implementation of a JCF List which provides methods matching the Smalltalk Collection protocol.
 */
public interface MutableList<T>
        extends MutableCollection<T>, List<T>, Cloneable, ListIterable<T>
{
    MutableList<T> with(T element);

    MutableList<T> without(T element);

    MutableList<T> withAll(Iterable<? extends T> elements);

    MutableList<T> withoutAll(Iterable<? extends T> elements);

    MutableList<T> newEmpty();

    MutableList<T> clone();

    MutableList<T> tap(Procedure<? super T> procedure);

    MutableList<T> select(Predicate<? super T> predicate);

    <P> MutableList<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    MutableList<T> reject(Predicate<? super T> predicate);

    <P> MutableList<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionMutableList<T> partition(Predicate<? super T> predicate);

    <P> PartitionMutableList<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> MutableList<S> selectInstancesOf(Class<S> clazz);

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

    /**
     * Returns a new {@code ListIterable} containing the distinct elements in this list.
     *
     * @since 7.0
     */
    MutableList<T> distinct();

    /**
     * Returns a new {@code ListIterable} containing the distinct elements in this list. Takes HashingStrategy.
     *
     * @since 7.0
     */
    MutableList<T> distinct(HashingStrategy<? super T> hashingStrategy);

    /**
     * Sorts the internal data structure of this list and returns the list itself as a convenience.
     */
    MutableList<T> sortThis(Comparator<? super T> comparator);

    /**
     * Sorts the internal data structure of this list and returns the list itself as a convenience.
     */
    MutableList<T> sortThis();

    /**
     * Sorts the internal data structure of this list based on the natural order of the attribute returned by {@code
     * function}.
     */
    <V extends Comparable<? super V>> MutableList<T> sortThisBy(Function<? super T, ? extends V> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByInt(IntFunction<? super T> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByBoolean(BooleanFunction<? super T> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByChar(CharFunction<? super T> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByByte(ByteFunction<? super T> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByShort(ShortFunction<? super T> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByFloat(FloatFunction<? super T> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByLong(LongFunction<? super T> function);

    /**
     * @since 6.0
     */
    MutableList<T> sortThisByDouble(DoubleFunction<? super T> function);

    MutableList<T> subList(int fromIndex, int toIndex);

    /**
     * Returns an unmodifable view of the list.
     * The returned list will be <tt>Serializable</tt> if this list is <tt>Serializable</tt>.
     *
     * @return an unmodifiable view of this list
     */
    MutableList<T> asUnmodifiable();

    MutableList<T> asSynchronized();

    /**
     * Returns an immutable copy of this list. If the list is immutable, it returns itself.
     * The returned list will be <tt>Serializable</tt> if this list is <tt>Serializable</tt>.
     */
    ImmutableList<T> toImmutable();

    <V> MutableListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> MutableListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <S> MutableList<Pair<T, S>> zip(Iterable<S> that);

    MutableList<Pair<T, Integer>> zipWithIndex();

    MutableList<T> take(int count);

    MutableList<T> takeWhile(Predicate<? super T> predicate);

    MutableList<T> drop(int count);

    MutableList<T> dropWhile(Predicate<? super T> predicate);

    PartitionMutableList<T> partitionWhile(Predicate<? super T> predicate);

    /**
     * Returns a new MutableList in reverse order
     */
    MutableList<T> toReversed();

    /**
     * Mutates the current list by reversing its order and returns the current list as a result
     */
    MutableList<T> reverseThis();

    MutableList<T> shuffleThis();

    MutableList<T> shuffleThis(Random rnd);
}
