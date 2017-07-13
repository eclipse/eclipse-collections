/*
 * Copyright (c) 2017 Bhavana Hindupur and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.collection.mutable.SynchronizedMutableCollection;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.eclipse.collections.impl.map.AbstractSynchronizedMapIterable;
import org.eclipse.collections.impl.map.mutable.SynchronizedBiMapSerializationProxy;
import org.eclipse.collections.impl.set.mutable.SynchronizedMutableSet;
import org.eclipse.collections.impl.utility.LazyIterate;

public class SynchronizedBiMap<K, V> extends AbstractSynchronizedMapIterable<K, V> implements MutableBiMap<K, V>, Serializable
{
    private static final long serialVersionUID = 1L;

    protected SynchronizedBiMap(MutableBiMap<K, V> delegate)
    {
        super(delegate);
    }

    protected SynchronizedBiMap(MutableBiMap<K, V> delegate, Object lock)
    {
        super(delegate, lock);
    }

    /**
     * This method will take a MutableBiMap and wrap it directly in a SynchronizedBiMap.
     */
    public static <K, V> SynchronizedBiMap<K, V> of(MutableBiMap<K, V> map)
    {
        if (map == null)
        {
            throw new IllegalArgumentException("cannot create a SynchronizedBiMap for null");
        }
        return new SynchronizedBiMap<>(map);
    }

    @Override
    protected MutableBiMap<K, V> getDelegate()
    {
        return (MutableBiMap<K, V>) super.getDelegate();
    }

    @Override
    public V forcePut(K key, V value)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().forcePut(key, value);
        }
    }

    @Override
    public MutableBiMap<K, V> asSynchronized()
    {
        return this;
    }

    @Override
    public MutableBiMap<K, V> asUnmodifiable()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".asUnmodifiable() not implemented yet");
    }

    @Override
    public MutableBiMap<K, V> clone()
    {
        synchronized (this.lock)
        {
            return SynchronizedBiMap.of(this.getDelegate().clone());
        }
    }

    @Override
    public MutableBiMap<K, V> tap(Procedure<? super V> procedure)
    {
        return (MutableBiMap<K, V>) super.tap(procedure);
    }

    @Override
    public <K2, V2> MutableBiMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().collect(function);
        }
    }

    @Override
    public <R> MutableBiMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().collectValues(function);
        }
    }

    @Override
    public MutableSet<V> select(Predicate<? super V> predicate)
    {
        return (MutableSet<V>) super.select(predicate);
    }

    @Override
    public MutableBiMap<K, V> select(Predicate2<? super K, ? super V> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().select(predicate);
        }
    }

    @Override
    public <P> MutableSet<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return (MutableSet<V>) super.selectWith(predicate, parameter);
    }

    @Override
    public <S> MutableSet<S> selectInstancesOf(Class<S> clazz)
    {
        return (MutableSet<S>) super.selectInstancesOf(clazz);
    }

    @Override
    public MutableSet<V> reject(Predicate<? super V> predicate)
    {
        return (MutableSet<V>) super.reject(predicate);
    }

    @Override
    public MutableBiMap<K, V> reject(Predicate2<? super K, ? super V> predicate)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().reject(predicate);
        }
    }

    @Override
    public <P> MutableSet<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return (MutableSet<V>) super.rejectWith(predicate, parameter);
    }

    @Override
    public PartitionMutableSet<V> partition(Predicate<? super V> predicate)
    {
        return (PartitionMutableSet<V>) super.partition(predicate);
    }

    @Override
    public <P> PartitionMutableSet<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return (PartitionMutableSet<V>) super.partitionWith(predicate, parameter);
    }

    @Override
    public <V1> MutableSetMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().groupBy(function);
        }
    }

    @Override
    public <V1> MutableSetMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function)
    {
        synchronized (this.lock)
        {
            return this.getDelegate().groupByEach(function);
        }
    }

    @Override
    public MutableSetMultimap<V, K> flip()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().flip();
        }
    }

    @Override
    public MutableBiMap<K, V> newEmpty()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().newEmpty();
        }
    }

    @Override
    public MutableBiMap<V, K> inverse()
    {
        synchronized (this.lock)
        {
            return new SynchronizedBiMap<>(this.getDelegate().inverse(), this.lock);
        }
    }

    @Override
    public MutableBiMap<V, K> flipUniqueValues()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().flipUniqueValues();
        }
    }

    @Override
    public RichIterable<K> keysView()
    {
        return LazyIterate.adapt(this.keySet());
    }

    @Override
    public RichIterable<V> valuesView()
    {
        return LazyIterate.adapt(this.values());
    }

    @Override
    public ImmutableBiMap<K, V> toImmutable()
    {
        synchronized (this.lock)
        {
            return this.getDelegate().toImmutable();
        }
    }

    /**
     * @deprecated in 8.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    public MutableSet<Pair<V, Integer>> zipWithIndex()
    {
        return (MutableSet<Pair<V, Integer>>) super.zipWithIndex();
    }

    @Override
    public <VV> MutableBiMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function)
    {
        return (MutableBiMap<VV, V>) super.groupByUniqueKey(function);
    }

    /**
     * @deprecated in 8.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    public <S> MutableSet<Pair<V, S>> zip(Iterable<S> that)
    {
        return (MutableSet<Pair<V, S>>) super.zip(that);
    }

    @Override
    public MutableBiMap<K, V> withKeyValue(K key, V value)
    {
        synchronized (this.lock)
        {
            this.getDelegate().put(key, value);
            return this;
        }
    }

    @Override
    public MutableBiMap<K, V> withAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues)
    {
        synchronized (this.lock)
        {
            for (Pair<? extends K, ? extends V> keyValue : keyValues)
            {
                this.getDelegate().put(keyValue.getOne(), keyValue.getTwo());
            }
            return this;
        }
    }

    @Override
    public MutableBiMap<K, V> withAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs)
    {
        return this.withAllKeyValues(ArrayAdapter.adapt(keyValuePairs));
    }

    @Override
    public MutableBiMap<K, V> withoutKey(K key)
    {
        synchronized (this.lock)
        {
            this.getDelegate().remove(key);
            return this;
        }
    }

    @Override
    public MutableBiMap<K, V> withoutAllKeys(Iterable<? extends K> keys)
    {
        synchronized (this.lock)
        {
            for (K key : keys)
            {
                this.getDelegate().removeKey(key);
            }
            return this;
        }
    }

    @Override
    public Set<K> keySet()
    {
        synchronized (this.lock)
        {
            return SynchronizedMutableSet.of(this.getDelegate().keySet(), this.lock);
        }
    }

    @Override
    public Collection<V> values()
    {
        synchronized (this.lock)
        {
            return SynchronizedMutableCollection.of(this.getDelegate().values(), this.lock);
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        synchronized (this.lock)
        {
            return SynchronizedMutableSet.of(this.getDelegate().entrySet(), this.lock);
        }
    }

    protected Object writeReplace()
    {
        return new SynchronizedBiMapSerializationProxy<>(this.getDelegate());
    }
}
