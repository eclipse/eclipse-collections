/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.list;

import org.eclipse.collections.api.ParallelIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;

/**
 * A ParallelIterable is RichIterable which will defer evaluation for certain methods like select, reject, collect, etc.
 * Any methods that do not return a ParallelIterable when called will cause evaluation to be forced. Evaluation occurs
 * in parallel. All code blocks passed in must be stateless or thread-safe.
 *
 * @since 5.0
 */
@Beta
public interface ParallelListIterable<T>
        extends ParallelIterable<T>
{
    @Override
    ParallelUnsortedSetIterable<T> asUnique();

    /**
     * Creates a parallel iterable for selecting elements from the current iterable.
     */
    @Override
    ParallelListIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> ParallelListIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    /**
     * Creates a parallel iterable for rejecting elements from the current iterable.
     */
    @Override
    ParallelListIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ParallelListIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ParallelListIterable<S> selectInstancesOf(Class<S> clazz);

    /**
     * Creates a parallel iterable for collecting elements from the current iterable.
     */
    @Override
    <V> ParallelListIterable<V> collect(Function<? super T, ? extends V> function);

    @Override
    <P, V> ParallelListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    /**
     * Creates a parallel iterable for selecting and collecting elements from the current iterable.
     */
    @Override
    <V> ParallelListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    /**
     * Creates a parallel flattening iterable for the current iterable.
     */
    @Override
    <V> ParallelListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    @Override
    <V> ListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> ListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);
}
