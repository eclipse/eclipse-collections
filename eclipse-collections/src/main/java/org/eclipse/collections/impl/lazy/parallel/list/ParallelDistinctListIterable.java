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

import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.multimap.set.UnsortedSetMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.lazy.parallel.set.AbstractParallelUnsortedSetIterable;
import org.eclipse.collections.impl.lazy.parallel.set.UnsortedSetBatch;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;

@Beta
class ParallelDistinctListIterable<T> extends AbstractParallelUnsortedSetIterable<T, UnsortedSetBatch<T>>
{
    private final AbstractParallelListIterable<T, ? extends ListBatch<T>> delegate;

    ParallelDistinctListIterable(AbstractParallelListIterable<T, ? extends ListBatch<T>> delegate)
    {
        this.delegate = delegate;
    }

    @Override
    public ExecutorService getExecutorService()
    {
        return this.delegate.getExecutorService();
    }

    @Override
    public int getBatchSize()
    {
        return this.delegate.getBatchSize();
    }

    @Override
    public LazyIterable<UnsortedSetBatch<T>> split()
    {
        // TODO: Replace the map with a concurrent set once it's implemented
        ConcurrentHashMap<T, Boolean> distinct = new ConcurrentHashMap<>();
        return this.delegate.split().collect(listBatch -> listBatch.distinct(distinct));
    }

    @Override
    public ParallelUnsortedSetIterable<T> asUnique()
    {
        return this;
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        // TODO: Replace the map with a concurrent set once it's implemented
        ConcurrentHashMap<T, Boolean> distinct = new ConcurrentHashMap<>();
        this.delegate.forEach(each -> {
            if (distinct.put(each, true) == null)
            {
                procedure.value(each);
            }
        });
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.delegate.anySatisfy(new DistinctAndPredicate<>(predicate));
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.delegate.allSatisfy(new DistinctOrPredicate<>(predicate));
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return this.delegate.detect(new DistinctAndPredicate<>(predicate));
    }

    @Override
    public Object[] toArray()
    {
        // TODO: Implement in parallel
        return this.delegate.toList().distinct().toArray();
    }

    @Override
    public <E> E[] toArray(E[] array)
    {
        // TODO: Implement in parallel
        return this.delegate.toList().distinct().toArray(array);
    }

    @Override
    public <V> UnsortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        // TODO: Implement in parallel
        return this.delegate.toSet().groupBy(function, new UnifiedSetMultimap<>());
    }

    @Override
    public <V> UnsortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        // TODO: Implement in parallel
        return this.delegate.toSet().groupByEach(function, new UnifiedSetMultimap<>());
    }

    @Override
    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        // TODO: Implement in parallel
        return this.delegate.toSet().groupByUniqueKey(function);
    }

    private static final class DistinctAndPredicate<T> implements Predicate<T>
    {
        // TODO: Replace the map with a concurrent set once it's implemented
        private final ConcurrentHashMap<T, Boolean> distinct = new ConcurrentHashMap<>();
        private final Predicate<? super T> predicate;

        private DistinctAndPredicate(Predicate<? super T> predicate)
        {
            this.predicate = predicate;
        }

        @Override
        public boolean accept(T each)
        {
            return this.distinct.put(each, true) == null && this.predicate.accept(each);
        }
    }

    private static final class DistinctOrPredicate<T> implements Predicate<T>
    {
        // TODO: Replace the map with a concurrent set once it's implemented
        private final ConcurrentHashMap<T, Boolean> distinct = new ConcurrentHashMap<>();
        private final Predicate<? super T> predicate;

        private DistinctOrPredicate(Predicate<? super T> predicate)
        {
            this.predicate = predicate;
        }

        @Override
        public boolean accept(T each)
        {
            boolean distinct = this.distinct.put(each, true) == null;
            return distinct && this.predicate.accept(each) || !distinct;
        }
    }
}
