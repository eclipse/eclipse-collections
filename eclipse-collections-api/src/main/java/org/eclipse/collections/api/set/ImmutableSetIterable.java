/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.set;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.multimap.set.ImmutableSetIterableMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.PartitionImmutableSetIterable;
import org.eclipse.collections.api.tuple.Pair;

/**
 * @since 6.0
 */
public interface ImmutableSetIterable<T> extends SetIterable<T>, ImmutableCollection<T>
{
    @Override
    ImmutableSetIterable<T> tap(Procedure<? super T> procedure);

    @Override
    ImmutableSetIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> ImmutableSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    ImmutableSetIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ImmutableSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionImmutableSetIterable<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionImmutableSetIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ImmutableSetIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> ImmutableSetIterableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> ImmutableSetIterableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    ImmutableSetIterable<Pair<T, Integer>> zipWithIndex();
}
