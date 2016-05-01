/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set.sorted;

import java.util.Comparator;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.multimap.sortedset.SortedSetMultimap;
import org.eclipse.collections.api.set.sorted.ParallelSortedSetIterable;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.lazy.parallel.NonParallelIterable;
import org.eclipse.collections.impl.lazy.parallel.list.NonParallelListIterable;

public class NonParallelSortedSetIterable<T> extends NonParallelIterable<T, SortedSetIterable<T>> implements ParallelSortedSetIterable<T>
{
    public NonParallelSortedSetIterable(SortedSetIterable<T> delegate)
    {
        super(delegate);
    }

    @Override
    public Comparator<? super T> comparator()
    {
        return this.delegate.comparator();
    }

    @Override
    public ParallelSortedSetIterable<T> asUnique()
    {
        return this;
    }

    @Override
    public ParallelSortedSetIterable<T> select(Predicate<? super T> predicate)
    {
        return new NonParallelSortedSetIterable<>(this.delegate.select(predicate));
    }

    @Override
    public <P> ParallelSortedSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelSortedSetIterable<>(this.delegate.selectWith(predicate, parameter));
    }

    @Override
    public ParallelSortedSetIterable<T> reject(Predicate<? super T> predicate)
    {
        return new NonParallelSortedSetIterable<>(this.delegate.reject(predicate));
    }

    @Override
    public <P> ParallelSortedSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelSortedSetIterable<>(this.delegate.rejectWith(predicate, parameter));
    }

    @Override
    public <S> ParallelSortedSetIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return new NonParallelSortedSetIterable<>(this.delegate.selectInstancesOf(clazz));
    }

    @Override
    public <V> ParallelListIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return new NonParallelListIterable<>(this.delegate.collect(function));
    }

    @Override
    public <P, V> ParallelListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return new NonParallelListIterable<>(this.delegate.collectWith(function, parameter));
    }

    @Override
    public <V> ParallelListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return new NonParallelListIterable<>(this.delegate.collectIf(predicate, function));
    }

    @Override
    public <V> ParallelListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new NonParallelListIterable<>(this.delegate.flatCollect(function));
    }

    @Override
    public <V> SortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.groupBy(function);
    }

    @Override
    public <V> SortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.delegate.groupByEach(function);
    }
}
