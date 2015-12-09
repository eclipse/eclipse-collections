/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.bag;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.multimap.bag.MutableBagIterableMultimap;
import org.eclipse.collections.api.partition.bag.PartitionMutableBagIterable;
import org.eclipse.collections.api.set.MutableSetIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;

public interface MutableBagIterable<T> extends Bag<T>, MutableCollection<T>
{
    void addOccurrences(T item, int occurrences);

    boolean removeOccurrences(Object item, int occurrences);

    boolean setOccurrences(T item, int occurrences);

    MutableBagIterable<T> tap(Procedure<? super T> procedure);

    MutableBagIterable<T> select(Predicate<? super T> predicate);

    <P> MutableBagIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    MutableBagIterable<T> reject(Predicate<? super T> predicate);

    <P> MutableBagIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionMutableBagIterable<T> partition(Predicate<? super T> predicate);

    <P> PartitionMutableBagIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> MutableBagIterable<S> selectInstancesOf(Class<S> clazz);

    <V> MutableBagIterableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> MutableBagIterableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    MutableSetIterable<Pair<T, Integer>> zipWithIndex();

    MutableBagIterable<T> selectByOccurrences(IntPredicate predicate);

    MutableMapIterable<T, Integer> toMapOfItemToCount();

    /**
     * @since 6.0
     */
    MutableList<ObjectIntPair<T>> topOccurrences(int count);

    /**
     * @since 6.0
     */
    MutableList<ObjectIntPair<T>> bottomOccurrences(int count);

    MutableBagIterable<T> with(T element);

    MutableBagIterable<T> without(T element);

    MutableBagIterable<T> withAll(Iterable<? extends T> elements);

    MutableBagIterable<T> withoutAll(Iterable<? extends T> elements);
}
