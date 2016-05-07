/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set;

import org.eclipse.collections.api.ParallelIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.set.UnsortedSetMultimap;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.lazy.parallel.NonParallelIterable;
import org.eclipse.collections.impl.lazy.parallel.bag.NonParallelUnsortedBag;

public class NonParallelUnsortedSetIterable<T> extends NonParallelIterable<T, UnsortedSetIterable<T>> implements ParallelUnsortedSetIterable<T>
{
    public NonParallelUnsortedSetIterable(UnsortedSetIterable<T> delegate)
    {
        super(delegate);
    }

    @Override
    public ParallelUnsortedSetIterable<T> asUnique()
    {
        return this;
    }

    @Override
    public ParallelUnsortedSetIterable<T> select(Predicate<? super T> predicate)
    {
        return new NonParallelUnsortedSetIterable<>(this.delegate.select(predicate));
    }

    @Override
    public <P> ParallelUnsortedSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelUnsortedSetIterable<>(this.delegate.selectWith(predicate, parameter));
    }

    @Override
    public ParallelUnsortedSetIterable<T> reject(Predicate<? super T> predicate)
    {
        return new NonParallelUnsortedSetIterable<>(this.delegate.reject(predicate));
    }

    @Override
    public <P> ParallelUnsortedSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return new NonParallelUnsortedSetIterable<>(this.delegate.rejectWith(predicate, parameter));
    }

    @Override
    public <S> ParallelUnsortedSetIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return new NonParallelUnsortedSetIterable<>(this.delegate.selectInstancesOf(clazz));
    }

    @Override
    public <V> ParallelIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return new NonParallelUnsortedBag<>(this.delegate.collect(function, new HashBag<>()));
    }

    @Override
    public <P, V> ParallelIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return new NonParallelUnsortedBag<>(this.delegate.collectWith(function, parameter, new HashBag<>()));
    }

    @Override
    public <V> ParallelIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return new NonParallelUnsortedBag<>(this.delegate.collectIf(predicate, function, new HashBag<>()));
    }

    @Override
    public <V> ParallelIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new NonParallelUnsortedBag<>(this.delegate.flatCollect(function, new HashBag<>()));
    }

    @Override
    public <V> UnsortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.delegate.groupBy(function);
    }

    @Override
    public <V> UnsortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.delegate.groupByEach(function);
    }
}
