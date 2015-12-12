/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.map.AbstractMapIterable;
import org.eclipse.collections.impl.tuple.AbstractImmutableEntry;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.eclipse.collections.impl.utility.MapIterate;

public abstract class AbstractMutableMapIterable<K, V> extends AbstractMapIterable<K, V> implements MutableMapIterable<K, V>
{
    public Iterator<V> iterator()
    {
        return this.values().iterator();
    }

    public V getIfAbsentPut(K key, Function0<? extends V> function)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            result = function.value();
            this.put(key, result);
        }
        return result;
    }

    public V getIfAbsentPut(K key, V value)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            result = value;
            this.put(key, result);
        }
        return result;
    }

    public V getIfAbsentPutWithKey(K key, Function<? super K, ? extends V> function)
    {
        return this.getIfAbsentPutWith(key, function, key);
    }

    public <P> V getIfAbsentPutWith(K key, Function<? super P, ? extends V> function, P parameter)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            result = function.valueOf(parameter);
            this.put(key, result);
        }
        return result;
    }

    public V add(Pair<K, V> keyValuePair)
    {
        return this.put(keyValuePair.getOne(), keyValuePair.getTwo());
    }

    public V updateValue(K key, Function0<? extends V> factory, Function<? super V, ? extends V> function)
    {
        V oldValue = this.getIfAbsent(key, factory);
        V newValue = function.valueOf(oldValue);
        this.put(key, newValue);
        return newValue;
    }

    public <P> V updateValueWith(K key, Function0<? extends V> factory, Function2<? super V, ? super P, ? extends V> function, P parameter)
    {
        V oldValue = this.getIfAbsent(key, factory);
        V newValue = function.value(oldValue, parameter);
        this.put(key, newValue);
        return newValue;
    }

    public <V1> MutableMap<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.<V1, V>newMap());
    }

    public <K2, V2> MutableMap<K2, V2> aggregateInPlaceBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Procedure2<? super V2, ? super V> mutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<V, K2, V2>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    public <K2, V2> MutableMap<K2, V2> aggregateBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Function2<? super V2, ? super V, ? extends V2> nonMutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<V, K2, V2>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }

    public RichIterable<K> keysView()
    {
        return LazyIterate.adapt(this.keySet());
    }

    public RichIterable<V> valuesView()
    {
        return LazyIterate.adapt(this.values());
    }

    public RichIterable<Pair<K, V>> keyValuesView()
    {
        return LazyIterate.adapt(this.entrySet()).collect(AbstractImmutableEntry.<K, V>getPairFunction());
    }

    public <K2, V2> MutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return MapIterate.collect(this, function, UnifiedMap.<K2, V2>newMap(this.size()));
    }

    public MutableMap<V, K> flipUniqueValues()
    {
        return MapIterate.flipUniqueValues(this);
    }

    public Pair<K, V> detect(Predicate2<? super K, ? super V> predicate)
    {
        return MapIterate.detect(this, predicate);
    }
}
