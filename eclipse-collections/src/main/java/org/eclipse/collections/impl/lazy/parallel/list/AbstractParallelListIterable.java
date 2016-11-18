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

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.lazy.parallel.AbstractParallelIterable;

@Beta
public abstract class AbstractParallelListIterable<T, B extends ListBatch<T>> extends AbstractParallelIterable<T, B> implements ParallelListIterable<T>
{
    @Override
    protected boolean isOrdered()
    {
        return true;
    }

    @Override
    public ParallelUnsortedSetIterable<T> asUnique()
    {
        return new ParallelDistinctListIterable<>(this);
    }

    @Override
    public ParallelListIterable<T> select(Predicate<? super T> predicate)
    {
        return new ParallelSelectListIterable<>(this, predicate);
    }

    @Override
    public <P> ParallelListIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.select(Predicates.bind(predicate, parameter));
    }

    @Override
    public <S> ParallelListIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return (ParallelListIterable<S>) this.select(Predicates.instanceOf(clazz));
    }

    @Override
    public ParallelListIterable<T> reject(Predicate<? super T> predicate)
    {
        return this.select(Predicates.not(predicate));
    }

    @Override
    public <P> ParallelListIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.reject(Predicates.bind(predicate, parameter));
    }

    @Override
    public <V> ParallelListIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return new ParallelCollectListIterable<>(this, function);
    }

    @Override
    public <P, V> ParallelListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.collect(Functions.bind(function, parameter));
    }

    @Override
    public <V> ParallelListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.select(predicate).collect(function);
    }

    @Override
    public <V> ParallelListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new ParallelFlatCollectListIterable<>(this, function);
    }

    @Override
    public <V> ListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        // TODO: Implement in parallel
        return this.toList().groupBy(function);
    }

    @Override
    public <V> ListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        // TODO: Implement in parallel
        return this.toList().groupByEach(function);
    }

    @Override
    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        // TODO: Implement in parallel
        return this.toList().groupByUniqueKey(function);
    }

    @Override
    public Object[] toArray()
    {
        // TODO: Implement in parallel
        return this.toList().toArray();
    }

    @Override
    public <E> E[] toArray(E[] array)
    {
        // TODO: Implement in parallel
        return this.toList().toArray(array);
    }
}
