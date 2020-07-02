/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.bag;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.multimap.bag.ImmutableBagIterableMultimap;
import org.eclipse.collections.api.partition.bag.PartitionImmutableBagIterable;
import org.eclipse.collections.api.set.ImmutableSetIterable;
import org.eclipse.collections.api.tuple.Pair;

public interface ImmutableBagIterable<T> extends Bag<T>, ImmutableCollection<T>
{
    @Override
    ImmutableBagIterable<T> tap(Procedure<? super T> procedure);

    @Override
    ImmutableBagIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> ImmutableBagIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    ImmutableBagIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ImmutableBagIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionImmutableBagIterable<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionImmutableBagIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ImmutableBagIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> ImmutableBagIterableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> ImmutableBagIterableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    ImmutableSetIterable<Pair<T, Integer>> zipWithIndex();

    @Override
    ImmutableBagIterable<T> selectByOccurrences(IntPredicate predicate);

    /**
     * @since 9.2
     */
    @Override
    default ImmutableBagIterable<T> selectDuplicates()
    {
        return this.selectByOccurrences(occurrences -> occurrences > 1);
    }

    /**
     * @since 9.2
     */
    @Override
    ImmutableSetIterable<T> selectUnique();

    @Override
    MutableMapIterable<T, Integer> toMapOfItemToCount();

    @Override
    <V> ImmutableCollection<V> collectWithOccurrences(ObjectIntToObjectFunction<? super T, ? extends V> function);

    /**
     * @since 10.3
     */
    @Override
    default <K, V> ImmutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> result = Maps.mutable.empty();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            K key = groupBy.valueOf(each);
            for (int i = 0; i < occurrences; i++)
            {
                result.updateValueWith(key, zeroValueFactory, nonMutatingAggregator, each);
            }
        });
        return result.toImmutable();
    }
}
