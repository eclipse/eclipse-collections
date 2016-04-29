/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.immutable;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

import net.jcip.annotations.Immutable;
import org.eclipse.collections.api.LazyIterable;
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
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.ImmutableByteList;
import org.eclipse.collections.api.list.primitive.ImmutableCharList;
import org.eclipse.collections.api.list.primitive.ImmutableDoubleList;
import org.eclipse.collections.api.list.primitive.ImmutableFloatList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.ImmutableShortList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
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
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.impl.map.AbstractMapIterable;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.partition.list.PartitionFastList;
import org.eclipse.collections.impl.utility.MapIterate;

@Immutable
public abstract class AbstractImmutableSortedMap<K, V>
        extends AbstractMapIterable<K, V>
        implements ImmutableSortedMap<K, V>, SortedMap<K, V>
{
    public SortedMap<K, V> castToMap()
    {
        return this;
    }

    public SortedMap<K, V> castToSortedMap()
    {
        return this;
    }

    public MutableSortedMap<K, V> toSortedMap()
    {
        return TreeSortedMap.newMap(this);
    }

    public ImmutableSortedMap<K, V> toImmutable()
    {
        return this;
    }

    public Iterator<V> iterator()
    {
        return this.valuesView().iterator();
    }

    public void putAll(Map<? extends K, ? extends V> map)
    {
        throw new UnsupportedOperationException("Cannot call putAll() on " + this.getClass().getSimpleName());
    }

    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    public ImmutableSortedMap<K, V> newWithKeyValue(K key, V value)
    {
        TreeSortedMap<K, V> sortedMap = TreeSortedMap.newMap(this);
        sortedMap.put(key, value);
        return sortedMap.toImmutable();
    }

    public ImmutableSortedMap<K, V> newWithAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues)
    {
        TreeSortedMap<K, V> sortedMap = TreeSortedMap.newMap(this);
        for (Pair<? extends K, ? extends V> keyValuePair : keyValues)
        {
            sortedMap.put(keyValuePair.getOne(), keyValuePair.getTwo());
        }
        return sortedMap.toImmutable();
    }

    public ImmutableSortedMap<K, V> newWithAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs)
    {
        TreeSortedMap<K, V> sortedMap = TreeSortedMap.newMap(this);
        for (Pair<? extends K, ? extends V> keyValuePair : keyValuePairs)
        {
            sortedMap.put(keyValuePair.getOne(), keyValuePair.getTwo());
        }
        return sortedMap.toImmutable();
    }

    public ImmutableSortedMap<K, V> newWithoutKey(K key)
    {
        TreeSortedMap<K, V> sortedMap = TreeSortedMap.newMap(this);
        sortedMap.removeKey(key);
        return sortedMap.toImmutable();
    }

    public ImmutableSortedMap<K, V> newWithoutAllKeys(Iterable<? extends K> keys)
    {
        TreeSortedMap<K, V> sortedMap = TreeSortedMap.newMap(this);
        for (K key : keys)
        {
            sortedMap.removeKey(key);
        }
        return sortedMap.toImmutable();
    }

    public V put(K key, V value)
    {
        throw new UnsupportedOperationException("Cannot call put() on " + this.getClass().getSimpleName());
    }

    public V remove(Object key)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    public ImmutableSortedSetMultimap<V, K> flip()
    {
        return MapIterate.flip(this).toImmutable();
    }

    public ImmutableSortedMap<K, V> tap(Procedure<? super V> procedure)
    {
        this.forEach(procedure);
        return this;
    }

    public ImmutableList<V> select(Predicate<? super V> predicate)
    {
        return this.select(predicate, FastList.<V>newList(this.size())).toImmutable();
    }

    public <P> ImmutableList<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.select(Predicates.bind(predicate, parameter));
    }

    public ImmutableSortedMap<K, V> select(final Predicate2<? super K, ? super V> predicate)
    {
        final MutableSortedMap<K, V> selectedMap = SortedMaps.mutable.with(this.comparator());
        this.forEachKeyValue((key, value) -> {
            if (predicate.accept(key, value))
            {
                selectedMap.put(key, value);
            }
        });
        return selectedMap.toImmutable();
    }

    public ImmutableList<V> reject(Predicate<? super V> predicate)
    {
        return this.reject(predicate, FastList.<V>newList(this.size())).toImmutable();
    }

    public <P> ImmutableList<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.reject(Predicates.bind(predicate, parameter));
    }

    public ImmutableSortedMap<K, V> reject(final Predicate2<? super K, ? super V> predicate)
    {
        final MutableSortedMap<K, V> rejectedMap = SortedMaps.mutable.with(this.comparator());
        this.forEachKeyValue((key, value) -> {
            if (!predicate.accept(key, value))
            {
                rejectedMap.put(key, value);
            }
        });
        return rejectedMap.toImmutable();
    }

    public PartitionImmutableList<V> partition(Predicate<? super V> predicate)
    {
        PartitionMutableList<V> partitionFastList = new PartitionFastList<>();
        this.forEach(new PartitionProcedure<>(predicate, partitionFastList));
        return partitionFastList.toImmutable();
    }

    public <P> PartitionImmutableList<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        PartitionMutableList<V> partitionFastList = new PartitionFastList<>();
        this.forEach(new PartitionPredicate2Procedure<>(predicate, parameter, partitionFastList));
        return partitionFastList.toImmutable();
    }

    public <S> ImmutableList<S> selectInstancesOf(Class<S> clazz)
    {
        FastList<S> result = FastList.newList(this.size());
        this.forEach(new SelectInstancesOfProcedure<>(clazz, result));
        return result.toImmutable();
    }

    public <R> ImmutableList<R> collect(Function<? super V, ? extends R> function)
    {
        return this.collect(function, FastList.<R>newList(this.size())).toImmutable();
    }

    public ImmutableBooleanList collectBoolean(BooleanFunction<? super V> booleanFunction)
    {
        BooleanArrayList result = new BooleanArrayList(this.size());
        this.forEach(new CollectBooleanProcedure<>(booleanFunction, result));
        return result.toImmutable();
    }

    public ImmutableByteList collectByte(ByteFunction<? super V> byteFunction)
    {
        ByteArrayList result = new ByteArrayList(this.size());
        this.forEach(new CollectByteProcedure<>(byteFunction, result));
        return result.toImmutable();
    }

    public ImmutableCharList collectChar(CharFunction<? super V> charFunction)
    {
        CharArrayList result = new CharArrayList(this.size());
        this.forEach(new CollectCharProcedure<>(charFunction, result));
        return result.toImmutable();
    }

    public ImmutableDoubleList collectDouble(DoubleFunction<? super V> doubleFunction)
    {
        DoubleArrayList result = new DoubleArrayList(this.size());
        this.forEach(new CollectDoubleProcedure<>(doubleFunction, result));
        return result.toImmutable();
    }

    public ImmutableFloatList collectFloat(FloatFunction<? super V> floatFunction)
    {
        FloatArrayList result = new FloatArrayList(this.size());
        this.forEach(new CollectFloatProcedure<>(floatFunction, result));
        return result.toImmutable();
    }

    public ImmutableIntList collectInt(IntFunction<? super V> intFunction)
    {
        IntArrayList result = new IntArrayList(this.size());
        this.forEach(new CollectIntProcedure<>(intFunction, result));
        return result.toImmutable();
    }

    public ImmutableLongList collectLong(LongFunction<? super V> longFunction)
    {
        LongArrayList result = new LongArrayList(this.size());
        this.forEach(new CollectLongProcedure<>(longFunction, result));
        return result.toImmutable();
    }

    public ImmutableShortList collectShort(ShortFunction<? super V> shortFunction)
    {
        ShortArrayList result = new ShortArrayList(this.size());
        this.forEach(new CollectShortProcedure<>(shortFunction, result));
        return result.toImmutable();
    }

    public <K2, V2> ImmutableMap<K2, V2> collect(final Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        final MutableMap<K2, V2> collectedMap = UnifiedMap.newMap(this.size());
        this.forEachKeyValue((key, value) -> collectedMap.add(function.value(key, value)));
        return collectedMap.toImmutable();
    }

    public <P, VV> ImmutableList<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter)
    {
        return this.collect(Functions.bind(function, parameter));
    }

    public <R> ImmutableList<R> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends R> function)
    {
        return this.collectIf(predicate, function, FastList.<R>newList(this.size())).toImmutable();
    }

    public <R> ImmutableSortedMap<K, R> collectValues(final Function2<? super K, ? super V, ? extends R> function)
    {
        final MutableSortedMap<K, R> collectedMap = SortedMaps.mutable.with(this.comparator());
        this.forEachKeyValue((key, value) -> collectedMap.put(key, function.value(key, value)));
        return collectedMap.toImmutable();
    }

    public Pair<K, V> detect(final Predicate2<? super K, ? super V> predicate)
    {
        return this.keyValuesView().detect(each -> predicate.accept(each.getOne(), each.getTwo()));
    }

    public <R> ImmutableList<R> flatCollect(Function<? super V, ? extends Iterable<R>> function)
    {
        return this.flatCollect(function, FastList.<R>newList(this.size())).toImmutable();
    }

    public <S> ImmutableList<Pair<V, S>> zip(Iterable<S> that)
    {
        return this.zip(that, FastList.<Pair<V, S>>newList(this.size())).toImmutable();
    }

    public ImmutableList<Pair<V, Integer>> zipWithIndex()
    {
        return this.zipWithIndex(FastList.<Pair<V, Integer>>newList(this.size())).toImmutable();
    }

    public SortedMap<K, V> subMap(K fromKey, K toKey)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".subMap() not implemented yet");
    }

    public SortedMap<K, V> headMap(K toKey)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".headMap() not implemented yet");
    }

    public SortedMap<K, V> tailMap(K fromKey)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".tailMap() not implemented yet");
    }

    public <R> ImmutableListMultimap<R, V> groupBy(Function<? super V, ? extends R> function)
    {
        return this.groupBy(function, FastListMultimap.<R, V>newMultimap()).toImmutable();
    }

    public <R> ImmutableListMultimap<R, V> groupByEach(Function<? super V, ? extends Iterable<R>> function)
    {
        return this.groupByEach(function, FastListMultimap.<R, V>newMultimap()).toImmutable();
    }

    public <V1> ImmutableMap<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.<V1, V>newMap()).toImmutable();
    }

    public <K2, V2> ImmutableMap<K2, V2> aggregateInPlaceBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Procedure2<? super V2, ? super V> mutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map.toImmutable();
    }

    public <K2, V2> ImmutableMap<K2, V2> aggregateBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Function2<? super V2, ? super V, ? extends V2> nonMutatingAggregator)
    {
        MutableMap<K2, V2> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map.toImmutable();
    }

    public <V1> ImmutableObjectLongMap<V1> sumByInt(Function<? super V, ? extends V1> groupBy, IntFunction<? super V> function)
    {
        MutableObjectLongMap<V1> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function)).toImmutable();
    }

    public <V1> ImmutableObjectDoubleMap<V1> sumByFloat(Function<? super V, ? extends V1> groupBy, FloatFunction<? super V> function)
    {
        MutableObjectDoubleMap<V1> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function)).toImmutable();
    }

    public <V1> ImmutableObjectLongMap<V1> sumByLong(Function<? super V, ? extends V1> groupBy, LongFunction<? super V> function)
    {
        MutableObjectLongMap<V1> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function)).toImmutable();
    }

    public <V1> ImmutableObjectDoubleMap<V1> sumByDouble(Function<? super V, ? extends V1> groupBy, DoubleFunction<? super V> function)
    {
        MutableObjectDoubleMap<V1> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function)).toImmutable();
    }

    public void reverseForEach(Procedure<? super V> procedure)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".reverseForEach() not implemented yet");
    }

    public LazyIterable<V> asReversed()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".asReversed() not implemented yet");
    }

    public ImmutableSortedMap<K, V> toReversed()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".toReversed() not implemented yet");
    }

    public int detectLastIndex(Predicate<? super V> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".detectLastIndex() not implemented yet");
    }

    public ImmutableSortedMap<K, V> takeWhile(Predicate<? super V> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".takeWhile() not implemented yet");
    }

    public ImmutableSortedMap<K, V> dropWhile(Predicate<? super V> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".dropWhile() not implemented yet");
    }

    public PartitionImmutableList<V> partitionWhile(Predicate<? super V> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".partitionWhile() not implemented yet");
    }

    public ImmutableList<V> distinct()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".distinct() not implemented yet");
    }

    public int indexOf(Object object)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".indexOf() not implemented yet");
    }

    public <S> boolean corresponds(OrderedIterable<S> other, Predicate2<? super V, ? super S> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".corresponds() not implemented yet");
    }

    public void forEach(int startIndex, int endIndex, Procedure<? super V> procedure)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".forEach() not implemented yet");
    }

    public void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super V> objectIntProcedure)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".forEachWithIndex() not implemented yet");
    }

    public MutableStack<V> toStack()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".toStack() not implemented yet");
    }

    public int detectIndex(Predicate<? super V> predicate)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".detectIndex() not implemented yet");
    }
}
