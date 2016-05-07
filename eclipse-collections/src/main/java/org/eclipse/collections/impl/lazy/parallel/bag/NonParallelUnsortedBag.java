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

import org.eclipse.collections.api.bag.ParallelUnsortedBag;
import org.eclipse.collections.api.bag.UnsortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.multimap.bag.UnsortedBagMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.lazy.parallel.NonParallelIterable;
import org.eclipse.collections.impl.lazy.parallel.set.NonParallelUnsortedSetIterable;

public class NonParallelUnsortedBag<T> extends NonParallelIterable<T, UnsortedBag<T>> implements ParallelUnsortedBag<T>
{
    public NonParallelUnsortedBag(UnsortedBag<T> delegate)
    {
        super(delegate);
    }

    @Override
    public void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure)
    {
        this.delegate.forEachWithOccurrences(procedure);
    }

    @Override
    public ParallelUnsortedSetIterable<T> asUnique()
    {
        return new NonParallelUnsortedSetIterable<>(this.toBag().toSet());
    }

    @Override
    public ParallelUnsortedBag<T> select(Predicate<? super T> predicate)
    {
        return new NonParallelUnsortedBag<>(this.delegate.select(predicate));
    }

    @Override
    public <P> ParallelUnsortedBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelUnsortedBag<>(this.delegate.selectWith(predicate, parameter));
    }

    @Override
    public ParallelUnsortedBag<T> reject(Predicate<? super T> predicate)
    {
        return new NonParallelUnsortedBag<>(this.delegate.reject(predicate));
    }

    @Override
    public <P> ParallelUnsortedBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelUnsortedBag<>(this.delegate.rejectWith(predicate, parameter));
    }

    @Override
    public <S> ParallelUnsortedBag<S> selectInstancesOf(Class<S> clazz)
    {
        return new NonParallelUnsortedBag<>(this.delegate.selectInstancesOf(clazz));
    }

    @Override
    public <V> ParallelUnsortedBag<V> collect(Function<? super T, ? extends V> function)
    {
        return new NonParallelUnsortedBag<>(this.delegate.collect(function));
    }

    @Override
    public <P, V> ParallelUnsortedBag<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return new NonParallelUnsortedBag<>(this.delegate.collectWith(function, parameter));
    }

    @Override
    public <V> ParallelUnsortedBag<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return new NonParallelUnsortedBag<>(this.delegate.collectIf(predicate, function));
    }

    @Override
    public <V> ParallelUnsortedBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new NonParallelUnsortedBag<>(this.delegate.flatCollect(function));
    }

    @Override
    public <V> UnsortedBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.groupBy(function);
    }

    @Override
    public <V> UnsortedBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.delegate.groupByEach(function);
    }
}
