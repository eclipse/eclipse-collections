/*
 * Copyright (c) 2015 Goldman Sachs.
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
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
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

    public boolean add(T o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().add(o);
        }
    }

    public boolean remove(Object o)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().remove(o);
        }
    }

    public boolean addAll(Collection<? extends T> coll)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().addAll(coll);
        }
    }

    public boolean removeAll(Collection<?> coll)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().removeAll(coll);
        }
    }

    public boolean retainAll(Collection<?> coll)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().retainAll(coll);
        }
    }

    public void clear()
    {
        synchronized (this.getLock())
        {
            this.getDelegate().clear();
        }
    }

    public boolean removeIf(Predicate<? super T> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().removeIf(predicate);
        }
    }

    public <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().removeIfWith(predicate, parameter);
        }
    }

    public boolean addAllIterable(Iterable<? extends T> iterable)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().addAllIterable(iterable);
        }
    }

    public boolean removeAllIterable(Iterable<?> iterable)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().removeAllIterable(iterable);
        }
    }

    public boolean retainAllIterable(Iterable<?> iterable)
    {
        synchronized (this.getLock())
        {
            return this.getDelegate().retainAllIterable(iterable);
        }
    }

    public <P> Twin<MutableList<T>> selectAndRejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().selectAndRejectWith(predicate, parameter);
        }
    }

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
}
