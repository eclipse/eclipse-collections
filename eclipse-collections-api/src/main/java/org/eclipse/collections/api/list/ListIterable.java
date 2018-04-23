/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.list;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.annotation.Beta;
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
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
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
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;

/**
 * An iterable whose items are ordered and may be accessed directly by index.  A reverseForEach
 * internal iterator is available iterating over the indexed iterable in reverse, starting from
 * the end and going to the beginning.  Additionally, internal iterators are available for batching
 * style iteration which is useful for parallel processing.
 */
public interface ListIterable<T>
        extends ReversibleIterable<T>
{
    /**
     * Returns the item at the specified position in this list iterable.
     */
    T get(int index);

    /**
     * Returns the index of the last occurrence of the specified item
     * in this list, or -1 if this list does not contain the item.
     */
    int lastIndexOf(Object o);

    /**
     * Returns the item at index 0 of the container.  If the container is empty, null is returned.  If null
     * is a valid item of the container, then a developer will need to check to see if the container is
     * empty first.
     */
    @Override
    T getFirst();

    /**
     * Returns the item at index (size() - 1) of the container.  If the container is empty, null is returned.  If null
     * is a valid item of the container, then a developer will need to check to see if the container is
     * empty first.
     */
    @Override
    T getLast();

    /**
     * @see List#listIterator()
     * @since 1.0.
     */
    ListIterator<T> listIterator();

    /**
     * @see List#listIterator(int)
     * @since 1.0.
     */
    ListIterator<T> listIterator(int index);

    /**
     * Converts the list to a mutable MutableStack implementation.
     *
     * @since 2.0
     */
    @Override
    MutableStack<T> toStack();

    /**
     * Converts the ListIterable to an immutable implementation. Returns this for immutable lists.
     *
     * @since 5.0
     */
    ImmutableList<T> toImmutable();

    @Override
    ListIterable<T> tap(Procedure<? super T> procedure);

    @Override
    ListIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> ListIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    ListIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ListIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionList<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionList<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ListIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> ListIterable<V> collect(Function<? super T, ? extends V> function);

    /**
     * @since 9.1.
     */
    @Override
    default <V> ListIterable<V> collectWithIndex(ObjectIntToObjectFunction<? super T, ? extends V> function)
    {
        int[] index = {0};
        return this.collect(each -> function.valueOf(each, index[0]++));
    }

    @Override
    BooleanList collectBoolean(BooleanFunction<? super T> booleanFunction);

    @Override
    ByteList collectByte(ByteFunction<? super T> byteFunction);

    @Override
    CharList collectChar(CharFunction<? super T> charFunction);

    @Override
    DoubleList collectDouble(DoubleFunction<? super T> doubleFunction);

    @Override
    FloatList collectFloat(FloatFunction<? super T> floatFunction);

    @Override
    IntList collectInt(IntFunction<? super T> intFunction);

    @Override
    LongList collectLong(LongFunction<? super T> longFunction);

    @Override
    ShortList collectShort(ShortFunction<? super T> shortFunction);

    @Override
    <P, V> ListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    @Override
    <V> ListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    @Override
    <V> ListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, V> ListIterable<V> flatCollectWith(Function2<? super T, ? super P, ? extends Iterable<V>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    @Override
    <V> ListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> ListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * Returns a new {@code ListIterable} containing the distinct elements in this list.
     * <p>
     * Conceptually similar to {@link #toSet()}.{@link #toList()} but retains the original order. If an element appears
     * multiple times in this list, the first one will be copied into the result.
     *
     * @return {@code ListIterable} of distinct elements
     * @since 3.0
     */
    @Override
    ListIterable<T> distinct();

    /**
     * Returns a new {@code ListIterable} containing the distinct elements in this list. Takes a HashingStrategy.
     *
     * @return {@code ListIterable} of distinct elements
     * @since 7.0
     */
    ListIterable<T> distinct(HashingStrategy<? super T> hashingStrategy);

    /**
     * Returns a new {@code ListIterable} containing the distinct elements in this list.
     * The specified function will be used to create a HashingStrategy to unique the elements.
     *
     * @see ListIterable#distinct(HashingStrategy)
     * @since 9.0
     */
    <V> ListIterable<T> distinctBy(Function<? super T, ? extends V> function);

    @Override
    <S> ListIterable<Pair<T, S>> zip(Iterable<S> that);

    @Override
    ListIterable<Pair<T, Integer>> zipWithIndex();

    @Override
    ListIterable<T> take(int count);

    /**
     * Returns the initial elements that satisfy the Predicate. Short circuits at the first element which does not
     * satisfy the Predicate.
     *
     * @since 3.0
     */
    @Override
    ListIterable<T> takeWhile(Predicate<? super T> predicate);

    @Override
    ListIterable<T> drop(int count);

    /**
     * Returns the final elements that do not satisfy the Predicate. Short circuits at the first element which does
     * satisfy the Predicate.
     *
     * @since 3.0
     */
    @Override
    ListIterable<T> dropWhile(Predicate<? super T> predicate);

    /**
     * Returns a Partition of the initial elements that satisfy the Predicate and the remaining elements. Short circuits at the first element which does
     * satisfy the Predicate.
     *
     * @since 3.0
     */
    @Override
    PartitionList<T> partitionWhile(Predicate<? super T> predicate);

    @Override
    ListIterable<T> toReversed();

    /**
     * Returns a parallel iterable of this ListIterable.
     *
     * @since 6.0
     */
    @Beta
    ParallelListIterable<T> asParallel(ExecutorService executorService, int batchSize);

    /**
     * Searches for the specified object using the binary search algorithm. The list must be sorted into ascending
     * order according to the specified comparator.
     *
     * @see Collections#binarySearch(List, Object, Comparator)
     */
    int binarySearch(T key, Comparator<? super T> comparator);

    /**
     * Searches for the specified object using the binary search algorithm. The elements in this list must implement
     * Comparable and the list must be sorted into ascending order.
     *
     * @see Collections#binarySearch(List, Object)
     */
    default int binarySearch(T key)
    {
        return Collections.binarySearch((List<? extends Comparable<? super T>>) this, key);
    }

    /**
     * Follows the same general contract as {@link List#equals(Object)}.
     */
    @Override
    boolean equals(Object o);

    /**
     * Follows the same general contract as {@link List#hashCode()}.
     */
    @Override
    int hashCode();

    /**
     * @see List#subList(int, int)
     * @since 6.0
     */
    ListIterable<T> subList(int fromIndex, int toIndex);
}
