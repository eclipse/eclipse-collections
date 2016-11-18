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

import org.eclipse.collections.api.ParallelIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.UnsortedBagMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.lazy.parallel.set.ParallelCollectIterable;
import org.eclipse.collections.impl.lazy.parallel.set.ParallelFlatCollectIterable;
import org.eclipse.collections.impl.multimap.bag.SynchronizedPutHashBagMultimap;

@Beta
public abstract class AbstractParallelIterableImpl<T, B extends Batch<T>> extends AbstractParallelIterable<T, B>
{
    @Override
    protected boolean isOrdered()
    {
        return false;
    }

    @Override
    public ParallelUnsortedSetIterable<T> asUnique()
    {
        return new ParallelDistinctIterable<>(this);
    }

    @Override
    public ParallelIterable<T> select(Predicate<? super T> predicate)
    {
        return new ParallelSelectIterable<>(this, predicate);
    }

    @Override
    public <P> ParallelIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.select(Predicates.bind(predicate, parameter));
    }

    @Override
    public <S> ParallelIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return (ParallelIterable<S>) this.select(Predicates.instanceOf(clazz));
    }

    @Override
    public ParallelIterable<T> reject(Predicate<? super T> predicate)
    {
        return this.select(Predicates.not(predicate));
    }

    @Override
    public <P> ParallelIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.reject(Predicates.bind(predicate, parameter));
    }

    @Override
    public <V> ParallelIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return new ParallelCollectIterable<>(this, function);
    }

    @Override
    public <P, V> ParallelIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.collect(Functions.bind(function, parameter));
    }

    @Override
    public <V> ParallelIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.select(predicate).collect(function);
    }

    @Override
    public <V> ParallelIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new ParallelFlatCollectIterable<>(this, function);
    }

    @Override
    public <V> UnsortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        MutableBagMultimap<V, T> result = SynchronizedPutHashBagMultimap.newMultimap();
        this.forEach(each -> {
            V key = function.valueOf(each);
            result.put(key, each);
        });
        return result;
    }

    @Override
    public <V> UnsortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        MutableBagMultimap<V, T> result = SynchronizedPutHashBagMultimap.newMultimap();
        this.forEach(each -> {
            Iterable<V> keys = function.valueOf(each);
            for (V key : keys)
            {
                result.put(key, each);
            }
        });
        return result;
    }
}
