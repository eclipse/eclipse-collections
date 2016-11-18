/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.list;

import java.util.Iterator;
import java.util.RandomAccess;
import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.impl.lazy.AbstractLazyIterable;
import org.eclipse.collections.impl.lazy.parallel.AbstractParallelIterable;

@Beta
public final class ListIterableParallelIterable<T> extends AbstractParallelListIterable<T, RootListBatch<T>>
{
    private final ListIterable<T> delegate;
    private final ExecutorService executorService;
    private final int batchSize;

    public ListIterableParallelIterable(ListIterable<T> delegate, ExecutorService executorService, int batchSize)
    {
        if (executorService == null)
        {
            throw new NullPointerException();
        }
        if (batchSize < 1)
        {
            throw new IllegalArgumentException();
        }
        if (!(delegate instanceof RandomAccess))
        {
            throw new IllegalArgumentException();
        }
        this.delegate = delegate;
        this.executorService = executorService;
        this.batchSize = batchSize;
    }

    @Override
    public ExecutorService getExecutorService()
    {
        return this.executorService;
    }

    @Override
    public LazyIterable<RootListBatch<T>> split()
    {
        return new ListIterableParallelBatchLazyIterable();
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        AbstractParallelIterable.forEach(this, procedure);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return AbstractParallelIterable.anySatisfy(this, predicate);
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return AbstractParallelIterable.allSatisfy(this, predicate);
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return AbstractParallelIterable.detect(this, predicate);
    }

    @Override
    public Object[] toArray()
    {
        // TODO: Implement in parallel
        return this.delegate.toArray();
    }

    @Override
    public <E> E[] toArray(E[] array)
    {
        // TODO: Implement in parallel
        return this.delegate.toArray(array);
    }

    @Override
    public <V> ListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        // TODO: Implement in parallel
        return this.delegate.groupBy(function);
    }

    @Override
    public <V> ListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        // TODO: Implement in parallel
        return this.delegate.groupByEach(function);
    }

    @Override
    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        // TODO: Implement in parallel
        return this.delegate.groupByUniqueKey(function);
    }

    @Override
    public int getBatchSize()
    {
        return this.batchSize;
    }

    private class ListIterableParallelBatchIterator implements Iterator<RootListBatch<T>>
    {
        protected int chunkIndex;

        @Override
        public boolean hasNext()
        {
            return this.chunkIndex * ListIterableParallelIterable.this.getBatchSize() < ListIterableParallelIterable.this.delegate.size();
        }

        @Override
        public RootListBatch<T> next()
        {
            int chunkStartIndex = this.chunkIndex * ListIterableParallelIterable.this.getBatchSize();
            int chunkEndIndex = (this.chunkIndex + 1) * ListIterableParallelIterable.this.getBatchSize();
            int truncatedChunkEndIndex = Math.min(chunkEndIndex, ListIterableParallelIterable.this.delegate.size());
            this.chunkIndex++;
            return new ListIterableBatch<>(ListIterableParallelIterable.this.delegate, chunkStartIndex, truncatedChunkEndIndex);
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Cannot call remove() on " + ListIterableParallelIterable.this.delegate.getClass().getSimpleName());
        }
    }

    private class ListIterableParallelBatchLazyIterable
            extends AbstractLazyIterable<RootListBatch<T>>
    {
        @Override
        public void each(Procedure<? super RootListBatch<T>> procedure)
        {
            for (RootListBatch<T> chunk : this)
            {
                procedure.value(chunk);
            }
        }

        @Override
        public Iterator<RootListBatch<T>> iterator()
        {
            return new ListIterableParallelBatchIterator();
        }
    }
}
