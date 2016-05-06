/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map;

import java.util.Map;
import java.util.Set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
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

    @Override
    public <VV> MutableMapIterable<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().groupByUniqueKey(function);
        }
    }

    public <KK, VV> MutableMap<KK, VV> aggregateInPlaceBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Procedure2<? super VV, ? super V> mutatingAggregator)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().aggregateInPlaceBy(groupBy, zeroValueFactory, mutatingAggregator);
        }
    }

    public <KK, VV> MutableMap<KK, VV> aggregateBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Function2<? super VV, ? super V, ? extends VV> nonMutatingAggregator)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().aggregateBy(groupBy, zeroValueFactory, nonMutatingAggregator);
        }
    }

    public RichIterable<Pair<K, V>> keyValuesView()
    {
        synchronized (this.lock)
        {
            Set<Entry<K, V>> entries = this.getDelegate().entrySet();
            Iterable<Pair<K, V>> pairs = Iterate.collect(entries, AbstractImmutableEntry.getPairFunction());
            return LazyIterate.adapt(pairs);
        }
    }

    public <V1> MutableObjectLongMap<V1> sumByInt(Function<? super V, ? extends V1> groupBy, IntFunction<? super V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByInt(groupBy, function);
        }
    }

    public <V1> MutableObjectDoubleMap<V1> sumByFloat(Function<? super V, ? extends V1> groupBy, FloatFunction<? super V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByFloat(groupBy, function);
        }
    }

    public <V1> MutableObjectLongMap<V1> sumByLong(Function<? super V, ? extends V1> groupBy, LongFunction<? super V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByLong(groupBy, function);
        }
    }

    public <V1> MutableObjectDoubleMap<V1> sumByDouble(Function<? super V, ? extends V1> groupBy, DoubleFunction<? super V> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().sumByDouble(groupBy, function);
        }
    }
}

