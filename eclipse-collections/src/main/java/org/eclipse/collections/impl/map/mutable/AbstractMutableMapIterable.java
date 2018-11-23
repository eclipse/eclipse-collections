/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import java.util.Iterator;
import java.util.Optional;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.map.AbstractMapIterable;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.tuple.AbstractImmutableEntry;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.eclipse.collections.impl.utility.MapIterate;

public abstract class AbstractMutableMapIterable<K, V> extends AbstractMapIterable<K, V> implements MutableMapIterable<K, V>
{
    @Override
    public Iterator<V> iterator()
    {
        return this.values().iterator();
    }

    @Override
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

    @Override
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

    @Override
    public V getIfAbsentPutWithKey(K key, Function<? super K, ? extends V> function)
    {
        return this.getIfAbsentPutWith(key, function, key);
    }

    @Override
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

    @Override
    public V updateValue(K key, Function0<? extends V> factory, Function<? super V, ? extends V> function)
    {
        V oldValue = this.getIfAbsent(key, factory);
        V newValue = function.valueOf(oldValue);
        this.put(key, newValue);
        return newValue;
    }

    @Override
    public <P> V updateValueWith(K key, Function0<? extends V> factory, Function2<? super V, ? super P, ? extends V> function, P parameter)
    {
        V oldValue = this.getIfAbsent(key, factory);
        V newValue = function.value(oldValue, parameter);
        this.put(key, newValue);
        return newValue;
    }

    @Override
    public <VV> MutableMapIterable<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.newMap(this.size()));
    }

    @Override
    public <K2, V2> MutableMap<K2, V2> aggregateInPlaceBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Procedure2<? super V2, ? super V> mutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    @Override
    public <K2, V2> MutableMap<K2, V2> aggregateBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Function2<? super V2, ? super V, ? extends V2> nonMutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
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
    public RichIterable<Pair<K, V>> keyValuesView()
    {
        return LazyIterate.adapt(this.entrySet()).collect(AbstractImmutableEntry.getPairFunction());
    }

    // TODO: push down in next major release. Return type of MutableMap prevents this from being a superclass of MutableOrderedMaps.
    @Override
    public <K2, V2> MutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return MapIterate.collect(this, function, UnifiedMap.newMap(this.size()));
    }

    // TODO: push down in next major release. Return type of MutableMap prevents this from being a superclass of MutableOrderedMaps.
    @Override
    public MutableMap<V, K> flipUniqueValues()
    {
        return MapIterate.flipUniqueValues(this);
    }

    @Override
    public Pair<K, V> detect(Predicate2<? super K, ? super V> predicate)
    {
        return MapIterate.detect(this, predicate);
    }

    @Override
    public Optional<Pair<K, V>> detectOptional(Predicate2<? super K, ? super V> predicate)
    {
        return MapIterate.detectOptional(this, predicate);
    }

    @Override
    public <V1> MutableObjectLongMap<V1> sumByInt(Function<? super V, ? extends V1> groupBy, IntFunction<? super V> function)
    {
        MutableObjectLongMap<V1> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function));
    }

    @Override
    public <V1> MutableObjectDoubleMap<V1> sumByFloat(Function<? super V, ? extends V1> groupBy, FloatFunction<? super V> function)
    {
        MutableObjectDoubleMap<V1> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function));
    }

    @Override
    public <V1> MutableObjectLongMap<V1> sumByLong(Function<? super V, ? extends V1> groupBy, LongFunction<? super V> function)
    {
        MutableObjectLongMap<V1> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function));
    }

    @Override
    public <V1> MutableObjectDoubleMap<V1> sumByDouble(Function<? super V, ? extends V1> groupBy, DoubleFunction<? super V> function)
    {
        MutableObjectDoubleMap<V1> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function));
    }

    /**
     * @since 9.0
     */
    @Override
    public <V1> MutableBag<V1> countBy(Function<? super V, ? extends V1> function)
    {
        return this.collect(function, Bags.mutable.empty());
    }

    /**
     * @since 9.0
     */
    @Override
    public <V1, P> MutableBag<V1> countByWith(Function2<? super V, ? super P, ? extends V1> function, P parameter)
    {
        return this.collectWith(function, parameter, Bags.mutable.empty());
    }

    /**
     * @since 10.0.0
     */
    @Override
    public <V1> MutableBag<V1> countByEach(Function<? super V, ? extends Iterable<V1>> function)
    {
        return this.flatCollect(function, Bags.mutable.empty());
    }
}
