/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.bag;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.ParallelUnsortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.UnsortedBagMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.BagAddOccurrencesProcedure;
import org.eclipse.collections.impl.lazy.parallel.AbstractParallelIterable;
import org.eclipse.collections.impl.multimap.bag.SynchronizedPutHashBagMultimap;

@Beta
public abstract class AbstractParallelUnsortedBag<T, B extends UnsortedBagBatch<T>> extends AbstractParallelIterable<T, B> implements ParallelUnsortedBag<T>
{
    @Override
    protected boolean isOrdered()
    {
        return false;
    }

    @Override
    public ParallelUnsortedSetIterable<T> asUnique()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".asUnique() not implemented yet");
    }

    @Override
    public ParallelUnsortedBag<T> select(Predicate<? super T> predicate)
    {
        return new ParallelSelectUnsortedBag<>(this, predicate);
    }

    @Override
    public <P> ParallelUnsortedBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.select(Predicates.bind(predicate, parameter));
    }

    @Override
    public <S> ParallelUnsortedBag<S> selectInstancesOf(Class<S> clazz)
    {
        return (ParallelUnsortedBag<S>) this.select(Predicates.instanceOf(clazz));
    }

    @Override
    public ParallelUnsortedBag<T> reject(Predicate<? super T> predicate)
    {
        return this.select(Predicates.not(predicate));
    }

    @Override
    public <P> ParallelUnsortedBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.reject(Predicates.bind(predicate, parameter));
    }

    @Override
    public <V> ParallelUnsortedBag<V> collect(Function<? super T, ? extends V> function)
    {
        return new ParallelCollectUnsortedBag<>(this, function);
    }

    @Override
    public <P, V> ParallelUnsortedBag<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.collect(Functions.bind(function, parameter));
    }

    @Override
    public <V> ParallelUnsortedBag<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.select(predicate).collect(function);
    }

    @Override
    public <V> ParallelUnsortedBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".flatCollect() not implemented yet");
    }

    @Override
    public MutableBag<T> toBag()
    {
        MutableBag<T> result = HashBag.<T>newBag().asSynchronized();
        this.forEachWithOccurrences(BagAddOccurrencesProcedure.on(result));
        return result;
    }

    @Override
    public <V> UnsortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        MutableBagMultimap<V, T> result = SynchronizedPutHashBagMultimap.newMultimap();
        this.forEachWithOccurrences((each, occurrences) -> {
            V key = function.valueOf(each);
            for (int i = 0; i < occurrences; i++)
            {
                result.put(key, each);
            }
        });
        return result;
    }

    @Override
    public <V> UnsortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        MutableBagMultimap<V, T> result = SynchronizedPutHashBagMultimap.newMultimap();
        this.forEachWithOccurrences((each, occurrences) -> {
            Iterable<V> keys = function.valueOf(each);
            for (V key : keys)
            {
                for (int i = 0; i < occurrences; i++)
                {
                    result.put(key, each);
                }
            }
        });
        return result;
    }
}
