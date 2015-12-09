/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

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

    public Comparator<? super T> comparator()
    {
        return this.delegate.comparator();
    }

    public ParallelSortedSetIterable<T> asUnique()
    {
        return this;
    }

    public ParallelSortedSetIterable<T> select(Predicate<? super T> predicate)
    {
        return new NonParallelSortedSetIterable<T>(this.delegate.select(predicate));
    }

    public <P> ParallelSortedSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelSortedSetIterable<T>(this.delegate.selectWith(predicate, parameter));
    }

    public ParallelSortedSetIterable<T> reject(Predicate<? super T> predicate)
    {
        return new NonParallelSortedSetIterable<T>(this.delegate.reject(predicate));
    }

    public <P> ParallelSortedSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelSortedSetIterable<T>(this.delegate.rejectWith(predicate, parameter));
    }

    public <S> ParallelSortedSetIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return new NonParallelSortedSetIterable<S>(this.delegate.selectInstancesOf(clazz));
    }

    public <V> ParallelListIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return new NonParallelListIterable<V>(this.delegate.collect(function));
    }

    public <P, V> ParallelListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return new NonParallelListIterable<V>(this.delegate.collectWith(function, parameter));
    }

    public <V> ParallelListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return new NonParallelListIterable<V>(this.delegate.collectIf(predicate, function));
    }

    public <V> ParallelListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new NonParallelListIterable<V>(this.delegate.flatCollect(function));
    }

    public <V> SortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.groupBy(function);
    }

    public <V> SortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.delegate.groupByEach(function);
    }
}
