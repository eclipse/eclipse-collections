/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.ImmutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.ImmutableByteBag;
import org.eclipse.collections.api.bag.primitive.ImmutableCharBag;
import org.eclipse.collections.api.bag.primitive.ImmutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.ImmutableFloatBag;
import org.eclipse.collections.api.bag.primitive.ImmutableIntBag;
import org.eclipse.collections.api.bag.primitive.ImmutableLongBag;
import org.eclipse.collections.api.bag.primitive.ImmutableShortBag;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableByteBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableFloatBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableShortBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionImmutableBag;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ByteHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.CharHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.DoubleHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.FloatHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.IntHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.LongHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.ShortHashBag;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.PartitionPredicate2Procedure;
import org.eclipse.collections.impl.block.procedure.PartitionProcedure;
import org.eclipse.collections.impl.block.procedure.SelectInstancesOfProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectBooleanProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectByteProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectCharProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectDoubleProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectFloatProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectIntProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectLongProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectShortProcedure;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.map.AbstractMapIterable;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.partition.bag.PartitionHashBag;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.impl.utility.MapIterate;

public abstract class AbstractImmutableMap<K, V>
        extends AbstractMapIterable<K, V>
        implements ImmutableMap<K, V>, Map<K, V>
{
    @Override
    public Map<K, V> castToMap()
    {
        return this;
    }

    @Override
    public MutableMap<K, V> toMap()
    {
        return UnifiedMap.newMap(this);
    }

    @Override
    public ImmutableMap<K, V> toImmutable()
    {
        return this;
    }

    @Override
    public Iterator<V> iterator()
    {
        return this.valuesView().iterator();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map)
    {
        throw new UnsupportedOperationException("Cannot call putAll() on " + this.getClass().getSimpleName());
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    @Override
    public ImmutableSetMultimap<V, K> flip()
    {
        return MapIterate.flip(this).toImmutable();
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        MutableSet<Entry<K, V>> set = UnifiedSet.newSet(this.size());
        this.forEachKeyValue((key, value) -> set.add(ImmutableEntry.of(key, value)));
        return set.toImmutable().castToSet();
    }

    @Override
    public ImmutableMap<K, V> newWithKeyValue(K key, V value)
    {
        UnifiedMap<K, V> map = UnifiedMap.newMap(this);
        map.put(key, value);
        return map.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues)
    {
        UnifiedMap<K, V> map = UnifiedMap.newMap(this);
        for (Pair<? extends K, ? extends V> keyValuePair : keyValues)
        {
            map.put(keyValuePair.getOne(), keyValuePair.getTwo());
        }
        return map.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs)
    {
        UnifiedMap<K, V> map = UnifiedMap.newMap(this);
        for (Pair<? extends K, ? extends V> keyValuePair : keyValuePairs)
        {
            map.put(keyValuePair.getOne(), keyValuePair.getTwo());
        }
        return map.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithoutKey(K key)
    {
        UnifiedMap<K, V> map = UnifiedMap.newMap(this);
        map.removeKey(key);
        return map.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> newWithoutAllKeys(Iterable<? extends K> keys)
    {
        UnifiedMap<K, V> map = UnifiedMap.newMap(this);
        for (K key : keys)
        {
            map.removeKey(key);
        }
        return map.toImmutable();
    }

    @Override
    public V put(K key, V value)
    {
        throw new UnsupportedOperationException("Cannot call put() on " + this.getClass().getSimpleName());
    }

    @Override
    public V remove(Object key)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    @Override
    public ImmutableMap<V, K> flipUniqueValues()
    {
        return MapIterate.flipUniqueValues(this).toImmutable();
    }

    @Override
    public <K2, V2> ImmutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        UnifiedMap<K2, V2> result = MapIterate.collect(this, function, UnifiedMap.newMap());
        return result.toImmutable();
    }

    @Override
    public <R> ImmutableMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function)
    {
        UnifiedMap<K, R> result = MapIterate.collectValues(this, function, UnifiedMap.newMap(this.size()));
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> select(Predicate2<? super K, ? super V> predicate)
    {
        UnifiedMap<K, V> result = MapIterate.selectMapOnEntry(this, predicate, UnifiedMap.newMap());
        return result.toImmutable();
    }

    @Override
    public ImmutableMap<K, V> reject(Predicate2<? super K, ? super V> predicate)
    {
        UnifiedMap<K, V> result = MapIterate.rejectMapOnEntry(this, predicate, UnifiedMap.newMap());
        return result.toImmutable();
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
    public <R> ImmutableBag<R> collect(Function<? super V, ? extends R> function)
    {
        return this.collect(function, new HashBag<R>()).toImmutable();
    }

    @Override
    public <P, VV> ImmutableBag<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter)
    {
        return this.collect(Functions.bind(function, parameter));
    }

    @Override
    public ImmutableBooleanBag collectBoolean(BooleanFunction<? super V> booleanFunction)
    {
        MutableBooleanBag result = new BooleanHashBag();
        this.forEach(new CollectBooleanProcedure<>(booleanFunction, result));
        return result.toImmutable();
    }

    @Override
    public ImmutableByteBag collectByte(ByteFunction<? super V> byteFunction)
    {
        MutableByteBag result = new ByteHashBag();
        this.forEach(new CollectByteProcedure<>(byteFunction, result));
        return result.toImmutable();
    }

    @Override
    public ImmutableCharBag collectChar(CharFunction<? super V> charFunction)
    {
        MutableCharBag result = new CharHashBag();
        this.forEach(new CollectCharProcedure<>(charFunction, result));
        return result.toImmutable();
    }

    @Override
    public ImmutableDoubleBag collectDouble(DoubleFunction<? super V> doubleFunction)
    {
        MutableDoubleBag result = new DoubleHashBag();
        this.forEach(new CollectDoubleProcedure<>(doubleFunction, result));
        return result.toImmutable();
    }

    @Override
    public ImmutableFloatBag collectFloat(FloatFunction<? super V> floatFunction)
    {
        MutableFloatBag result = new FloatHashBag();
        this.forEach(new CollectFloatProcedure<>(floatFunction, result));
        return result.toImmutable();
    }

    @Override
    public ImmutableIntBag collectInt(IntFunction<? super V> intFunction)
    {
        MutableIntBag result = new IntHashBag();
        this.forEach(new CollectIntProcedure<>(intFunction, result));
        return result.toImmutable();
    }

    @Override
    public ImmutableLongBag collectLong(LongFunction<? super V> longFunction)
    {
        MutableLongBag result = new LongHashBag();
        this.forEach(new CollectLongProcedure<>(longFunction, result));
        return result.toImmutable();
    }

    @Override
    public ImmutableShortBag collectShort(ShortFunction<? super V> shortFunction)
    {
        MutableShortBag result = new ShortHashBag();
        this.forEach(new CollectShortProcedure<>(shortFunction, result));
        return result.toImmutable();
    }

    @Override
    public <R> ImmutableBag<R> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends R> function)
    {
        return this.collectIf(predicate, function, new HashBag<R>()).toImmutable();
    }

    @Override
    public <R> ImmutableBag<R> flatCollect(Function<? super V, ? extends Iterable<R>> function)
    {
        return this.flatCollect(function, new HashBag<>()).toImmutable();
    }

    @Override
    public ImmutableBag<V> select(Predicate<? super V> predicate)
    {
        return this.select(predicate, new HashBag<>()).toImmutable();
    }

    @Override
    public <P> ImmutableBag<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.select(Predicates.bind(predicate, parameter));
    }

    @Override
    public ImmutableMap<K, V> tap(Procedure<? super V> procedure)
    {
        this.forEach(procedure);
        return this;
    }

    @Override
    public ImmutableBag<V> reject(Predicate<? super V> predicate)
    {
        return this.reject(predicate, new HashBag<>()).toImmutable();
    }

    @Override
    public <P> ImmutableBag<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.reject(Predicates.bind(predicate, parameter));
    }

    @Override
    public PartitionImmutableBag<V> partition(Predicate<? super V> predicate)
    {
        PartitionMutableBag<V> partitionMutableBag = new PartitionHashBag<>();
        this.forEach(new PartitionProcedure<>(predicate, partitionMutableBag));
        return partitionMutableBag.toImmutable();
    }

    @Override
    public <P> PartitionImmutableBag<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        PartitionMutableBag<V> partitionMutableBag = new PartitionHashBag<>();
        this.forEach(new PartitionPredicate2Procedure<>(predicate, parameter, partitionMutableBag));
        return partitionMutableBag.toImmutable();
    }

    @Override
    public <S> ImmutableBag<S> selectInstancesOf(Class<S> clazz)
    {
        MutableBag<S> result = HashBag.newBag();
        this.forEach(new SelectInstancesOfProcedure<>(clazz, result));
        return result.toImmutable();
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    public <S> ImmutableBag<Pair<V, S>> zip(Iterable<S> that)
    {
        return this.zip(that, HashBag.newBag(this.size())).toImmutable();
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    public ImmutableSet<Pair<V, Integer>> zipWithIndex()
    {
        return this.zipWithIndex(UnifiedSet.newSet(this.size())).toImmutable();
    }

    @Override
    public <VV> ImmutableBagMultimap<VV, V> groupBy(Function<? super V, ? extends VV> function)
    {
        return this.groupBy(function, HashBagMultimap.<VV, V>newMultimap()).toImmutable();
    }

    @Override
    public <VV> ImmutableBagMultimap<VV, V> groupByEach(Function<? super V, ? extends Iterable<VV>> function)
    {
        return this.groupByEach(function, HashBagMultimap.newMultimap()).toImmutable();
    }

    @Override
    public <V1> ImmutableMap<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.<V1, V>newMap(this.size())).toImmutable();
    }

    @Override
    public <K2, V2> ImmutableMap<K2, V2> aggregateInPlaceBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Procedure2<? super V2, ? super V> mutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map.toImmutable();
    }

    @Override
    public <K2, V2> ImmutableMap<K2, V2> aggregateBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Function2<? super V2, ? super V, ? extends V2> nonMutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map.toImmutable();
    }

    @Override
    public <V1> ImmutableObjectLongMap<V1> sumByInt(Function<? super V, ? extends V1> groupBy, IntFunction<? super V> function)
    {
        MutableObjectLongMap<V1> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function)).toImmutable();
    }

    @Override
    public <V1> ImmutableObjectDoubleMap<V1> sumByFloat(Function<? super V, ? extends V1> groupBy, FloatFunction<? super V> function)
    {
        MutableObjectDoubleMap<V1> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function)).toImmutable();
    }

    @Override
    public <V1> ImmutableObjectLongMap<V1> sumByLong(Function<? super V, ? extends V1> groupBy, LongFunction<? super V> function)
    {
        MutableObjectLongMap<V1> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function)).toImmutable();
    }

    @Override
    public <V1> ImmutableObjectDoubleMap<V1> sumByDouble(Function<? super V, ? extends V1> groupBy, DoubleFunction<? super V> function)
    {
        MutableObjectDoubleMap<V1> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function)).toImmutable();
    }

    /**
     * @since 9.0
     */
    @Override
    public <V1> ImmutableBag<V1> countBy(Function<? super V, ? extends V1> function)
    {
        return this.collect(function, Bags.mutable.<V1>empty()).toImmutable();
    }

    /**
     * @since 9.0
     */
    @Override
    public <V1, P> ImmutableBag<V1> countByWith(Function2<? super V, ? super P, ? extends V1> function, P parameter)
    {
        return this.collectWith(function, parameter, Bags.mutable.<V1>empty()).toImmutable();
    }

    /**
     * @since 10.0.0
     */
    @Override
    public <V1> ImmutableBag<V1> countByEach(Function<? super V, ? extends Iterable<V1>> function)
    {
        return this.countByEach(function, Bags.mutable.empty()).toImmutable();
    }
}
