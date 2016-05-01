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

import org.eclipse.collections.api.ParallelIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.set.SetMultimap;

/**
 * @since 5.0
 */
@Beta
public interface ParallelSetIterable<T> extends ParallelIterable<T>
{
    @Override
    ParallelSetIterable<T> asUnique();

    /**
     * Creates a parallel iterable for selecting elements from the current iterable.
     */
    @Override
    ParallelSetIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> ParallelSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    /**
     * Creates a parallel iterable for rejecting elements from the current iterable.
     */
    @Override
    ParallelSetIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ParallelSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ParallelSetIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> SetMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> SetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);
}
