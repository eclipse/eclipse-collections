/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.lazy.parallel.list;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.lazy.parallel.NonParallelIterable;
import org.eclipse.collections.impl.lazy.parallel.set.NonParallelUnsortedSetIterable;

public class NonParallelListIterable<T> extends NonParallelIterable<T, ListIterable<T>> implements ParallelListIterable<T>
{
    public NonParallelListIterable(ListIterable<T> delegate)
    {
        super(delegate);
    }

    public ParallelUnsortedSetIterable<T> asUnique()
    {
        return new NonParallelUnsortedSetIterable<T>(this.delegate.toSet());
    }

    public ParallelListIterable<T> select(Predicate<? super T> predicate)
    {
        return new NonParallelListIterable<T>(this.delegate.select(predicate));
    }

    public <P> ParallelListIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelListIterable<T>(this.delegate.selectWith(predicate, parameter));
    }

    public ParallelListIterable<T> reject(Predicate<? super T> predicate)
    {
        return new NonParallelListIterable<T>(this.delegate.reject(predicate));
    }

    public <P> ParallelListIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelListIterable<T>(this.delegate.rejectWith(predicate, parameter));
    }

    public <S> ParallelListIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return new NonParallelListIterable<S>(this.delegate.selectInstancesOf(clazz));
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

    public <V> ListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.groupBy(function);
    }

    public <V> ListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.delegate.groupByEach(function);
    }
}
