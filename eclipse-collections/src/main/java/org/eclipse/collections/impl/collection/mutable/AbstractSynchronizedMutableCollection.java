/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import java.util.Collection;

import net.jcip.annotations.ThreadSafe;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.collection.AbstractSynchronizedRichIterable;

@ThreadSafe
public abstract class AbstractSynchronizedMutableCollection<T> extends AbstractSynchronizedRichIterable<T> implements MutableCollection<T>
{
    protected AbstractSynchronizedMutableCollection(MutableCollection<T> delegate)
    {
        this(delegate, null);
    }

    protected AbstractSynchronizedMutableCollection(MutableCollection<T> delegate, Object lock)
    {
        super(delegate, lock);
    }

    @Override
    protected MutableCollection<T> getDelegate()
    {
        return (MutableCollection<T>) super.getDelegate();
    }

    @Override
    public boolean add(T o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().add(o);
        }
    }

    @Override
    public boolean remove(Object o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().remove(o);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> coll)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().addAll(coll);
        }
    }

    @Override
    public boolean removeAll(Collection<?> coll)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().removeAll(coll);
        }
    }

    @Override
    public boolean retainAll(Collection<?> coll)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().retainAll(coll);
        }
    }

    @Override
    public void clear()
    {
        synchronized (this.getLock())
        {
            this.getDelegate().clear();
        }
    }

    @Override
    public boolean removeIf(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().removeIf(predicate);
        }
    }

    @Override
    public <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().removeIfWith(predicate, parameter);
        }
    }

    @Override
    public boolean addAllIterable(Iterable<? extends T> iterable)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().addAllIterable(iterable);
        }
    }

    @Override
    public boolean removeAllIterable(Iterable<?> iterable)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().removeAllIterable(iterable);
        }
    }

    @Override
    public boolean retainAllIterable(Iterable<?> iterable)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().retainAllIterable(iterable);
        }
    }

    @Override
    public <P> Twin<MutableList<T>> selectAndRejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectAndRejectWith(predicate, parameter);
        }
    }

    @Override
    public <IV, P> IV injectIntoWith(
            IV injectValue,
            Function3<? super IV, ? super T, ? super P, ? extends IV> function,
            P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().injectIntoWith(injectValue, function, parameter);
        }
    }

    @Override
    public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().groupByUniqueKey(function);
        }
    }

    @Override
    public <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().aggregateInPlaceBy(groupBy, zeroValueFactory, mutatingAggregator);
        }
    }

    @Override
    public <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().aggregateBy(groupBy, zeroValueFactory, nonMutatingAggregator);
        }
    }

    @Override
    public <V> MutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByInt(groupBy, function);
        }
    }

    @Override
    public <V> MutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByFloat(groupBy, function);
        }
    }

    @Override
    public <V> MutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByLong(groupBy, function);
        }
    }

    @Override
    public <V> MutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByDouble(groupBy, function);
        }
    }
}
