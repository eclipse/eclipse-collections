/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel;

import java.util.concurrent.locks.ReadWriteLock;

import org.eclipse.collections.api.ParallelIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.Multimap;

@Beta
public final class MultiReaderParallelIterable<T> extends AbstractMultiReaderParallelIterable<T, ParallelIterable<T>>
{
    public MultiReaderParallelIterable(ParallelIterable<T> delegate, ReadWriteLock lock)
    {
        super(delegate, lock);
    }

    @Override
    public ParallelIterable<T> asUnique()
    {
        return this.wrap(this.delegate.asUnique());
    }

    @Override
    public ParallelIterable<T> select(Predicate<? super T> predicate)
    {
        return this.wrap(this.delegate.select(predicate));
    }

    @Override
    public <P> ParallelIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.wrap(this.delegate.selectWith(predicate, parameter));
    }

    @Override
    public ParallelIterable<T> reject(Predicate<? super T> predicate)
    {
        return this.wrap(this.delegate.reject(predicate));
    }

    @Override
    public <P> ParallelIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.wrap(this.delegate.rejectWith(predicate, parameter));
    }

    @Override
    public <S> ParallelIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return this.wrap(this.delegate.selectInstancesOf(clazz));
    }

    @Override
    public <V> ParallelIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return this.wrap(this.delegate.collect(function));
    }

    @Override
    public <P, V> ParallelIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.wrap(this.delegate.collectWith(function, parameter));
    }

    @Override
    public <V> ParallelIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.wrap(this.delegate.collectIf(predicate, function));
    }

    @Override
    public <V> ParallelIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.wrap(this.delegate.flatCollect(function));
    }

    @Override
    public <V> Multimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.groupBy(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public <V> Multimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        this.lock.readLock().lock();
        try
        {
            return this.delegate.groupByEach(function);
        }
        finally
        {
            this.lock.readLock().unlock();
        }
    }
}
