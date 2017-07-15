/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.strategy.immutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.collection.mutable.UnmodifiableMutableCollection;
import org.eclipse.collections.impl.map.immutable.AbstractImmutableMap;
import org.eclipse.collections.impl.map.strategy.mutable.UnifiedMapWithHashingStrategy;
import org.eclipse.collections.impl.parallel.BatchIterable;
import org.eclipse.collections.impl.set.mutable.UnmodifiableMutableSet;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.eclipse.collections.impl.utility.MapIterate;

/**
 * @see ImmutableMap
 */
public class ImmutableUnifiedMapWithHashingStrategy<K, V>
        extends AbstractImmutableMap<K, V> implements BatchIterable<V>, Serializable
{
    private static final long serialVersionUID = 1L;
    private final UnifiedMapWithHashingStrategy<K, V> delegate;

    public ImmutableUnifiedMapWithHashingStrategy(UnifiedMapWithHashingStrategy<K, V> delegate)
    {
        this.delegate = UnifiedMapWithHashingStrategy.newMap(delegate);
    }

    public ImmutableUnifiedMapWithHashingStrategy(
            HashingStrategy<? super K> hashingStrategy,
            Pair<K, V>... pairs)
    {
        this.delegate = UnifiedMapWithHashingStrategy.newMapWith(hashingStrategy, pairs);
    }

    @Override
    public boolean equals(Object o)
    {
        return this.delegate.equals(o);
    }

    @Override
    public int hashCode()
    {
        return this.delegate.hashCode();
    }

    @Override
    public String toString()
    {
        return this.delegate.toString();
    }

    @Override
    public int size()
    {
        return this.delegate.size();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return this.delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return this.delegate.containsValue(value);
    }

    @Override
    public V get(Object key)
    {
        return this.delegate.get(key);
    }

    @Override
    public int getBatchCount(int batchSize)
    {
        return this.delegate.getBatchCount(batchSize);
    }

    @Override
    public void batchForEach(Procedure<? super V> procedure, int sectionIndex, int sectionCount)
    {
        this.delegate.batchForEach(procedure, sectionIndex, sectionCount);
    }

    @Override
    public void forEachValue(Procedure<? super V> procedure)
    {
        this.delegate.forEachValue(procedure);
    }

    @Override
    public void forEachKey(Procedure<? super K> procedure)
    {
        this.delegate.forEachKey(procedure);
    }

    @Override
    public void forEachKeyValue(Procedure2<? super K, ? super V> procedure)
    {
        this.delegate.forEachKeyValue(procedure);
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        UnifiedSetWithHashingStrategy<Entry<K, V>> result = UnifiedSetWithHashingStrategy.newSet(
                HashingStrategies.defaultStrategy(), this.delegate.size());
        HashingStrategy<? super K> hashingStrategy = this.delegate.hashingStrategy();
        this.forEachKeyValue((argument1, argument2) -> result.put(ImmutableEntryWithHashingStrategy.of(argument1, argument2, hashingStrategy)));
        return result.toImmutable().castToSet();
    }

    @Override
    public Set<K> keySet()
    {
        return UnmodifiableMutableSet.of(this.delegate.keySet());
    }

    @Override
    public Collection<V> values()
    {
        return UnmodifiableMutableCollection.of(this.delegate.values());
    }

    @Override
    public RichIterable<K> keysView()
    {
        return this.delegate.keysView();
    }

    @Override
    public RichIterable<V> valuesView()
    {
        return this.delegate.valuesView();
    }

    @Override
    public RichIterable<Pair<K, V>> keyValuesView()
    {
        return this.delegate.keyValuesView();
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        this.delegate.forEachWithIndex(objectIntProcedure);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super V, ? super P> procedure, P parameter)
    {
        this.delegate.forEachWith(procedure, parameter);
    }

    @Override
    public ImmutableMap<K, V> newWithKeyValue(K key, V value)
    {
        UnifiedMapWithHashingStrategy<K, V> result = UnifiedMapWithHashingStrategy.newMap(this.delegate);
        result.put(key, value);
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues)
    {
        UnifiedMapWithHashingStrategy<K, V> result = UnifiedMapWithHashingStrategy.newMap(this.delegate);
        for (Pair<? extends K, ? extends V> pair : keyValues)
        {
            result.put(pair.getOne(), pair.getTwo());
        }
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs)
    {
        UnifiedMapWithHashingStrategy<K, V> result = UnifiedMapWithHashingStrategy.newMap(this.delegate);
        for (Pair<? extends K, ? extends V> keyValuePair : keyValuePairs)
        {
            result.put(keyValuePair.getOne(), keyValuePair.getTwo());
        }
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithoutKey(K key)
    {
        UnifiedMapWithHashingStrategy<K, V> result = UnifiedMapWithHashingStrategy.newMap(this.delegate);
        result.remove(key);
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithoutAllKeys(Iterable<? extends K> keys)
    {
        UnifiedMapWithHashingStrategy<K, V> result = UnifiedMapWithHashingStrategy.newMap(this.delegate);
        for (K key : keys)
        {
            result.remove(key);
        }
        return result.toImmutable();
    }

    @Override
    public <R> ImmutableMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function)
    {
        MutableMap<K, R> result = MapIterate.collectValues(this, function,
                UnifiedMapWithHashingStrategy.newMap(this.delegate.hashingStrategy(), this.delegate.size()));
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> select(Predicate2<? super K, ? super V> predicate)
    {
        MutableMap<K, V> result = MapIterate.selectMapOnEntry(this, predicate, this.delegate.newEmpty());
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> reject(Predicate2<? super K, ? super V> predicate)
    {
        MutableMap<K, V> result = MapIterate.rejectMapOnEntry(this, predicate, this.delegate.newEmpty());
        return result.toImmutable();
    }

    protected Object writeReplace()
    {
        return new ImmutableMapWithHashingStrategySerializationProxy<>(this, this.delegate.hashingStrategy());
    }
}
