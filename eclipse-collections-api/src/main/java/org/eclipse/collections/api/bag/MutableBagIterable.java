/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

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
    /**
     * Add number of {@code occurrences} for an {@code item}. If the {@code item} does not exist, then the {@code item} is added to the bag.
     *
     * <p>
     * For Example:
     * <pre>
     * MutableBagIterable&lt;String&gt; names = Bags.mutable.of("A", "B", "B");
     * Assert.assertEquals(4, names.<b>addOccurrences</b>("A", 3));
     * </pre>
     *
     * @return updated number of occurrences.
     * @throws IllegalArgumentException if {@code occurrences} are less than 0.
     */
    int addOccurrences(T item, int occurrences);

    boolean removeOccurrences(Object item, int occurrences);

    boolean setOccurrences(T item, int occurrences);

    @Override
    MutableBagIterable<T> tap(Procedure<? super T> procedure);

    @Override
    MutableBagIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> MutableBagIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    MutableBagIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> MutableBagIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionMutableBagIterable<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionMutableBagIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> MutableBagIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> MutableBagIterableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> MutableBagIterableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    MutableSetIterable<Pair<T, Integer>> zipWithIndex();

    @Override
    MutableBagIterable<T> selectByOccurrences(IntPredicate predicate);

    /**
     * @since 9.2
     */
    @Override
    default MutableBagIterable<T> selectDuplicates()
    {
        return this.selectByOccurrences(occurrences -> occurrences > 1);
    }

    /**
     * @since 9.2
     */
    @Override
    default MutableSetIterable<T> selectUnique()
    {
        throw new UnsupportedOperationException("Adding default implementation so as to not break compatibility");
    }

    @Override
    MutableMapIterable<T, Integer> toMapOfItemToCount();

    /**
     * @since 6.0
     */
    @Override
    MutableList<ObjectIntPair<T>> topOccurrences(int count);

    /**
     * @since 6.0
     */
    @Override
    MutableList<ObjectIntPair<T>> bottomOccurrences(int count);

    @Override
    MutableBagIterable<T> with(T element);

    @Override
    MutableBagIterable<T> without(T element);

    @Override
    MutableBagIterable<T> withAll(Iterable<? extends T> elements);

    @Override
    MutableBagIterable<T> withoutAll(Iterable<? extends T> elements);
}
