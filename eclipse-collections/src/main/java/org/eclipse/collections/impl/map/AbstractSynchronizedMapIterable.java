/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.map;

import java.util.Map;
import java.util.Set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.collection.AbstractSynchronizedRichIterable;
import org.eclipse.collections.impl.tuple.AbstractImmutableEntry;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.LazyIterate;

/**
 * A synchronized view of a map.
 */
public abstract class AbstractSynchronizedMapIterable<K, V>
        extends AbstractSynchronizedRichIterable<V>
        implements MutableMapIterable<K, V>
{
    protected AbstractSynchronizedMapIterable(MutableMapIterable<K, V> delegate)
    {
        super(delegate, null);
    }

    protected AbstractSynchronizedMapIterable(MutableMapIterable<K, V> delegate, Object lock)
    {
        super(delegate, lock);
    }

    @Override
    protected MutableMapIterable<K, V> getDelegate()
    {
        return (MutableMapIterable<K, V>) super.getDelegate();
    }

    public V get(Object key)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().get(key);
        }
    }

    public V getIfAbsent(K key, Function0<? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().getIfAbsent(key, function);
        }
    }

    public V getIfAbsentValue(K key, V value)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().getIfAbsentValue(key, value);
        }
    }

    public <P> V getIfAbsentWith(K key, Function<? super P, ? extends V> function, P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().getIfAbsentWith(key, function, parameter);
        }
    }

    public <A> A ifPresentApply(K key, Function<? super V, ? extends A> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().ifPresentApply(key, function);
        }
    }

    public boolean containsKey(Object key)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().containsKey(key);
        }
    }

    public boolean containsValue(Object value)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().containsValue(value);
        }
    }

    public void forEachValue(Procedure<? super V> procedure)
    {
        synchronized (this.lock)
        {
            this.getDelegate().forEachValue(procedure);
        }
    }

    public void forEachKey(Procedure<? super K> procedure)
    {
        synchronized (this.lock)
        {
            this.getDelegate().forEachKey(procedure);
        }
    }

    public void forEachKeyValue(Procedure2<? super K, ? super V> procedure2)
    {
        synchronized (this.lock)
        {
            this.getDelegate().forEachKeyValue(procedure2);
        }
    }

    public Pair<K, V> detect(Predicate2<? super K, ? super V> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().detect(predicate);
        }
    }

    public V getIfAbsentPut(K key, Function0<? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().getIfAbsentPut(key, function);
        }
    }

    public V getIfAbsentPut(K key, V value)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().getIfAbsentPut(key, value);
        }
    }

    public V getIfAbsentPutWithKey(K key, Function<? super K, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().getIfAbsentPutWithKey(key, function);
        }
    }

    public <P> V getIfAbsentPutWith(K key, Function<? super P, ? extends V> function, P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().getIfAbsentPutWith(key, function, parameter);
        }
    }

    public V put(K key, V value)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().put(key, value);
        }
    }

    public V remove(Object key)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().remove(key);
        }
    }

    public V removeKey(K key)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().removeKey(key);
        }
    }

    public void putAll(Map<? extends K, ? extends V> map)
    {
        synchronized (this.lock)
        {
            this.getDelegate().putAll(map);
        }
    }

    public void clear()
    {
        synchronized (this.lock)
        {
            this.getDelegate().clear();
        }
    }

    public V add(Pair<K, V> keyValuePair)
    {
        synchronized (this.lock)
        {
            return this.put(keyValuePair.getOne(), keyValuePair.getTwo());
        }
    }

    public V updateValue(K key, Function0<? extends V> factory, Function<? super V, ? extends V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().updateValue(key, factory, function);
        }
    }

    public <P> V updateValueWith(
            K key,
            Function0<? extends V> factory,
            Function2<? super V, ? super P, ? extends V> function,
            P parameter)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().updateValueWith(key, factory, function, parameter);
        }
    }

    public RichIterable<Pair<K, V>> keyValuesView()
    {
        synchronized (this.lock)
        {
            Set<Entry<K, V>> entries = this.getDelegate().entrySet();
            Iterable<Pair<K, V>> pairs = Iterate.collect(entries, AbstractImmutableEntry.<K, V>getPairFunction());
            return LazyIterate.adapt(pairs);
        }
    }
}

