/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.ordered;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.multimap.ordered.SortedIterableMultimap;
import org.eclipse.collections.api.partition.ordered.PartitionSortedIterable;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A SortedIterable is an ordered iterable where the elements are stored in sorted order defined by a non-strict partial
 * order relation. The sort order is determined by the Comparator returned by {@link #comparator()} or is the natural
 * ordering if {@code comparator()} returns {@code null}. Operations that would sort the collection can be faster than
 * O(n log n). For example {@link #toSortedList()} takes O(n) time.
 *
 * @since 5.0
 */
public interface SortedIterable<T> extends OrderedIterable<T>
{
    /**
     * Returns the comparator used to order the elements in this container, or null if this container uses the natural
     * ordering of its elements.
     */
    Comparator<? super T> comparator();

    /**
     * Returns the initial elements that satisfy the Predicate. Short circuits at the first element which does not
     * satisfy the Predicate.
     */
    SortedIterable<T> takeWhile(Predicate<? super T> predicate);

    /**
     * Returns the final elements that do not satisfy the Predicate. Short circuits at the first element which does
     * satisfy the Predicate.
     */
    SortedIterable<T> dropWhile(Predicate<? super T> predicate);

    /**
     * Returns a Partition of the initial elements that satisfy the Predicate and the remaining elements. Short circuits at the first element which does
     * satisfy the Predicate.
     */
    PartitionSortedIterable<T> partitionWhile(Predicate<? super T> predicate);

    /**
     * Returns a new {@code SortedIterable} containing the distinct elements in this iterable.
     * <p>
     * Conceptually similar to {@link #toSet()}.{@link #toList()} but retains the original order. If an element appears
     * multiple times in this iterable, the first one will be copied into the result.
     *
     * @return {@code SortedIterable} of distinct elements
     */
    SortedIterable<T> distinct();

    /**
     * Converts the SortedIterable to a mutable MutableStack implementation.
     */
    MutableStack<T> toStack();

    /**
     * Returns the minimum element out of this container based on the natural order, not the order of this container.
     * If you want the minimum element based on the order of this container, use {@link #getFirst()}.
     *
     * @throws ClassCastException     if the elements are not {@link Comparable}
     * @throws NoSuchElementException if the SortedIterable is empty
     */
    T min();

    /**
     * Returns the maximum element out of this container based on the natural order, not the order of this container.
     * If you want the maximum element based on the order of this container, use {@link #getLast()}.
     *
     * @throws ClassCastException     if the elements are not {@link Comparable}
     * @throws NoSuchElementException if the SortedIterable is empty
     */
    T max();

    SortedIterable<T> select(Predicate<? super T> predicate);

    <P> SortedIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    SortedIterable<T> reject(Predicate<? super T> predicate);

    <P> SortedIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionSortedIterable<T> partition(Predicate<? super T> predicate);

    <S> SortedIterable<S> selectInstancesOf(Class<S> clazz);

    <V> SortedIterableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> SortedIterableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <S> ListIterable<Pair<T, S>> zip(Iterable<S> that);

    SortedIterable<Pair<T, Integer>> zipWithIndex();
}
