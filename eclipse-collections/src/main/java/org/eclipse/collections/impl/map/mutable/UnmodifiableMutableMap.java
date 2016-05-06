/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableByteBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableFloatBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableShortBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleObjectToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.FloatObjectToFloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.IntObjectToIntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.LongObjectToLongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.UnmodifiableIteratorAdapter;
import org.eclipse.collections.impl.UnmodifiableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.tuple.AbstractImmutableEntry;
import org.eclipse.collections.impl.utility.LazyIterate;

/**
 * An unmodifiable view of a map.
 *
 * @see MutableMap#asUnmodifiable()
 */
public class UnmodifiableMutableMap<K, V>
        extends UnmodifiableMap<K, V>
        implements MutableMap<K, V>
{
    private static final long serialVersionUID = 1L;

    protected UnmodifiableMutableMap(MutableMap<K, V> map)
    {
        super(map);
    }

    /**
     * This method will take a MutableMap and wrap it directly in a UnmodifiableMutableMap.  It will
     * take any other non-GS-map and first adapt it will a MapAdapter, and then return a
     * UnmodifiableMutableMap that wraps the adapter.
     */
    public static <K, V, M extends Map<K, V>> UnmodifiableMutableMap<K, V> of(M map)
    {
        if (map == null)
        {
            throw new IllegalArgumentException("cannot create a UnmodifiableMutableMap for null");
        }
        return new UnmodifiableMutableMap<>(MapAdapter.adapt(map));
    }

    public MutableMap<K, V> newEmpty()
    {
        return this.getMutableMap().newEmpty();
    }

    public boolean notEmpty()
    {
        return this.getMutableMap().notEmpty();
    }

    public void forEachValue(Procedure<? super V> procedure)
    {
        this.getMutableMap().forEachValue(procedure);
    }

    public void forEachKey(Procedure<? super K> procedure)
    {
        this.getMutableMap().forEachKey(procedure);
    }

    public void forEachKeyValue(Procedure2<? super K, ? super V> procedure)
    {
        this.getMutableMap().forEachKeyValue(procedure);
    }

    public MutableMap<V, K> flipUniqueValues()
    {
        return this.getMutableMap().flipUniqueValues();
    }

    public <E> MutableMap<K, V> collectKeysAndValues(
            Iterable<E> iterable,
            Function<? super E, ? extends K> keyFunction,
            Function<? super E, ? extends V> valueFunction)
    {
        throw new UnsupportedOperationException("Cannot call collectKeysAndValues() on " + this.getClass().getSimpleName());
    }

    public V removeKey(K key)
    {
        throw new UnsupportedOperationException("Cannot call removeKey() on " + this.getClass().getSimpleName());
    }

    public V updateValue(K key, Function0<? extends V> factory, Function<? super V, ? extends V> function)
    {
        throw new UnsupportedOperationException("Cannot call updateValue() on " + this.getClass().getSimpleName());
    }

    public <P> V updateValueWith(
            K key,
            Function0<? extends V> factory,
            Function2<? super V, ? super P, ? extends V> function,
            P parameter)
    {
        throw new UnsupportedOperationException("Cannot call updateValueWith() on " + this.getClass().getSimpleName());
    }

    public V getIfAbsentPut(K key, Function0<? extends V> function)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            throw new UnsupportedOperationException("Cannot mutate " + this.getClass().getSimpleName());
        }
        return result;
    }

    public V getIfAbsentPut(K key, V value)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            throw new UnsupportedOperationException("Cannot mutate " + this.getClass().getSimpleName());
        }
        return result;
    }

    public V getIfAbsentPutWithKey(K key, Function<? super K, ? extends V> function)
    {
        return this.getIfAbsentPutWith(key, function, key);
    }

    public <P> V getIfAbsentPutWith(
            K key,
            Function<? super P, ? extends V> function,
            P parameter)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            throw new UnsupportedOperationException("Cannot mutate " + this.getClass().getSimpleName());
        }
        return result;
    }

    public V getIfAbsent(K key, Function0<? extends V> function)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            return function.value();
        }
        return result;
    }

    public V getIfAbsentValue(K key, V value)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            return value;
        }
        return result;
    }

    public <P> V getIfAbsentWith(
            K key,
            Function<? super P, ? extends V> function,
            P parameter)
    {
        V result = this.get(key);
        if (this.isAbsent(result, key))
        {
            return function.valueOf(parameter);
        }
        return result;
    }

    private boolean isAbsent(V result, K key)
    {
        return result == null && !this.containsKey(key);
    }

    public <A> A ifPresentApply(K key, Function<? super V, ? extends A> function)
    {
        return this.getMutableMap().ifPresentApply(key, function);
    }

    public V add(Pair<K, V> keyValuePair)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }

    public MutableMap<K, V> withKeyValue(K key, V value)
    {
        throw new UnsupportedOperationException("Cannot call withKeyValue() on " + this.getClass().getSimpleName());
    }

    public MutableMap<K, V> withAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues)
    {
        throw new UnsupportedOperationException("Cannot call withAllKeyValues() on " + this.getClass().getSimpleName());
    }

    public MutableMap<K, V> withAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValuePairs)
    {
        throw new UnsupportedOperationException("Cannot call withAllKeyValueArguments() on " + this.getClass().getSimpleName());
    }

    public MutableMap<K, V> withoutKey(K key)
    {
        throw new UnsupportedOperationException("Cannot call withoutKey() on " + this.getClass().getSimpleName());
    }

    public MutableMap<K, V> withoutAllKeys(Iterable<? extends K> keys)
    {
        throw new UnsupportedOperationException("Cannot call withoutAllKeys() on " + this.getClass().getSimpleName());
    }

    public MutableSetMultimap<V, K> flip()
    {
        return this.getMutableMap().flip();
    }

    @Override
    public MutableMap<K, V> clone()
    {
        return this;
    }

    public MutableMap<K, V> asUnmodifiable()
    {
        return this;
    }

    public MutableMap<K, V> asSynchronized()
    {
        return SynchronizedMutableMap.of(this);
    }

    public MutableMap<K, V> tap(Procedure<? super V> procedure)
    {
        this.forEach(procedure);
        return this;
    }

    public void forEach(Procedure<? super V> procedure)
    {
        this.each(procedure);
    }

    public void each(Procedure<? super V> procedure)
    {
        this.getMutableMap().forEach(procedure);
    }

    public void forEachWithIndex(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        this.getMutableMap().forEachWithIndex(objectIntProcedure);
    }

    public <P> void forEachWith(Procedure2<? super V, ? super P> procedure, P parameter)
    {
        this.getMutableMap().forEachWith(procedure, parameter);
    }

    public Iterator<V> iterator()
    {
        return new UnmodifiableIteratorAdapter<>(this.getMutableMap().iterator());
    }

    @Override
    public int hashCode()
    {
        return this.getMutableMap().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.getMutableMap().equals(obj);
    }

    protected MutableMap<K, V> getMutableMap()
    {
        return (MutableMap<K, V>) this.delegate;
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
        return LazyIterate.adapt(this.entrySet()).collect(AbstractImmutableEntry.getPairFunction());
    }

    public ImmutableMap<K, V> toImmutable()
    {
        return Maps.immutable.withAll(this);
    }

    public <R> MutableMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function)
    {
        return this.getMutableMap().collectValues(function);
    }

    public <K2, V2> MutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return this.getMutableMap().collect(function);
    }

    public MutableMap<K, V> select(Predicate2<? super K, ? super V> predicate)
    {
        return this.getMutableMap().select(predicate);
    }

    public MutableMap<K, V> reject(Predicate2<? super K, ? super V> predicate)
    {
        return this.getMutableMap().reject(predicate);
    }

    public Pair<K, V> detect(Predicate2<? super K, ? super V> predicate)
    {
        return this.getMutableMap().detect(predicate);
    }

    public boolean allSatisfy(Predicate<? super V> predicate)
    {
        return this.getMutableMap().allSatisfy(predicate);
    }

    public <P> boolean allSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().allSatisfyWith(predicate, parameter);
    }

    public boolean noneSatisfy(Predicate<? super V> predicate)
    {
        return this.getMutableMap().noneSatisfy(predicate);
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().noneSatisfyWith(predicate, parameter);
    }

    public boolean anySatisfy(Predicate<? super V> predicate)
    {
        return this.getMutableMap().anySatisfy(predicate);
    }

    public <P> boolean anySatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().anySatisfyWith(predicate, parameter);
    }

    public void appendString(Appendable appendable)
    {
        this.getMutableMap().appendString(appendable);
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.getMutableMap().appendString(appendable, separator);
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.getMutableMap().appendString(appendable, start, separator, end);
    }

    public MutableBag<V> toBag()
    {
        return this.getMutableMap().toBag();
    }

    public MutableSortedBag<V> toSortedBag()
    {
        return this.getMutableMap().toSortedBag();
    }

    public MutableSortedBag<V> toSortedBag(Comparator<? super V> comparator)
    {
        return this.getMutableMap().toSortedBag(comparator);
    }

    public <R extends Comparable<? super R>> MutableSortedBag<V> toSortedBagBy(Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().toSortedBagBy(function);
    }

    public LazyIterable<V> asLazy()
    {
        return this.getMutableMap().asLazy();
    }

    public <R extends Collection<V>> R into(R target)
    {
        return this.getMutableMap().into(target);
    }

    public MutableList<V> toList()
    {
        return this.getMutableMap().toList();
    }

    public <NK, NV> MutableMap<NK, NV> toMap(
            Function<? super V, ? extends NK> keyFunction,
            Function<? super V, ? extends NV> valueFunction)
    {
        return this.getMutableMap().toMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Function<? super V, ? extends NK> keyFunction,
            Function<? super V, ? extends NV> valueFunction)
    {
        return this.getMutableMap().toSortedMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Comparator<? super NK> comparator,
            Function<? super V, ? extends NK> keyFunction,
            Function<? super V, ? extends NV> valueFunction)
    {
        return this.getMutableMap().toSortedMap(comparator, keyFunction, valueFunction);
    }

    public MutableSet<V> toSet()
    {
        return this.getMutableMap().toSet();
    }

    public MutableList<V> toSortedList()
    {
        return this.getMutableMap().toSortedList();
    }

    public MutableList<V> toSortedList(Comparator<? super V> comparator)
    {
        return this.getMutableMap().toSortedList(comparator);
    }

    public <R extends Comparable<? super R>> MutableList<V> toSortedListBy(Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().toSortedListBy(function);
    }

    public MutableSortedSet<V> toSortedSet()
    {
        return this.getMutableMap().toSortedSet();
    }

    public MutableSortedSet<V> toSortedSet(Comparator<? super V> comparator)
    {
        return this.getMutableMap().toSortedSet(comparator);
    }

    public <R extends Comparable<? super R>> MutableSortedSet<V> toSortedSetBy(Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().toSortedSetBy(function);
    }

    public RichIterable<RichIterable<V>> chunk(int size)
    {
        return this.getMutableMap().chunk(size);
    }

    public <R, C extends Collection<R>> C collect(Function<? super V, ? extends R> function, C target)
    {
        return this.getMutableMap().collect(function, target);
    }

    public <R, C extends Collection<R>> C collectIf(Predicate<? super V> predicate, Function<? super V, ? extends R> function, C target)
    {
        return this.getMutableMap().collectIf(predicate, function, target);
    }

    public <P, VV> MutableBag<VV> collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter)
    {
        return this.getMutableMap().collectWith(function, parameter);
    }

    public <P, R, C extends Collection<R>> C collectWith(Function2<? super V, ? super P, ? extends R> function, P parameter, C targetCollection)
    {
        return this.getMutableMap().collectWith(function, parameter, targetCollection);
    }

    public boolean contains(Object object)
    {
        return this.containsValue(object);
    }

    public boolean containsAllArguments(Object... elements)
    {
        return this.getMutableMap().containsAllArguments(elements);
    }

    public boolean containsAllIterable(Iterable<?> source)
    {
        return this.getMutableMap().containsAllIterable(source);
    }

    public boolean containsAll(Collection<?> source)
    {
        return this.containsAllIterable(source);
    }

    public int count(Predicate<? super V> predicate)
    {
        return this.getMutableMap().count(predicate);
    }

    public <P> int countWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().countWith(predicate, parameter);
    }

    public V detect(Predicate<? super V> predicate)
    {
        return this.getMutableMap().detect(predicate);
    }

    public <P> V detectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().detectWith(predicate, parameter);
    }

    public V detectIfNone(Predicate<? super V> predicate, Function0<? extends V> function)
    {
        return this.getMutableMap().detectIfNone(predicate, function);
    }

    public <P> V detectWithIfNone(Predicate2<? super V, ? super P> predicate, P parameter, Function0<? extends V> function)
    {
        return this.getMutableMap().detectWithIfNone(predicate, parameter, function);
    }

    public <R, C extends Collection<R>> C flatCollect(Function<? super V, ? extends Iterable<R>> function, C target)
    {
        return this.getMutableMap().flatCollect(function, target);
    }

    public V getFirst()
    {
        return this.getMutableMap().getFirst();
    }

    public V getLast()
    {
        return this.getMutableMap().getLast();
    }

    public <R> MutableBagMultimap<R, V> groupBy(Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().groupBy(function);
    }

    public <R, C extends MutableMultimap<R, V>> C groupBy(Function<? super V, ? extends R> function, C target)
    {
        return this.getMutableMap().groupBy(function, target);
    }

    public <R> MutableBagMultimap<R, V> groupByEach(Function<? super V, ? extends Iterable<R>> function)
    {
        return this.getMutableMap().groupByEach(function);
    }

    public <R, C extends MutableMultimap<R, V>> C groupByEach(Function<? super V, ? extends Iterable<R>> function, C target)
    {
        return this.getMutableMap().groupByEach(function, target);
    }

    public <VV> MutableMap<VV, V> groupByUniqueKey(Function<? super V, ? extends VV> function)
    {
        return this.getMutableMap().groupByUniqueKey(function);
    }

    public <VV, R extends MutableMap<VV, V>> R groupByUniqueKey(Function<? super V, ? extends VV> function, R target)
    {
        return this.getMutableMap().groupByUniqueKey(function, target);
    }

    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super V, ? extends IV> function)
    {
        return this.getMutableMap().injectInto(injectedValue, function);
    }

    public int injectInto(int injectedValue, IntObjectToIntFunction<? super V> function)
    {
        return this.getMutableMap().injectInto(injectedValue, function);
    }

    public long injectInto(long injectedValue, LongObjectToLongFunction<? super V> function)
    {
        return this.getMutableMap().injectInto(injectedValue, function);
    }

    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super V> function)
    {
        return this.getMutableMap().injectInto(injectedValue, function);
    }

    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super V> function)
    {
        return this.getMutableMap().injectInto(injectedValue, function);
    }

    public long sumOfInt(IntFunction<? super V> function)
    {
        return this.getMutableMap().sumOfInt(function);
    }

    public double sumOfFloat(FloatFunction<? super V> function)
    {
        return this.getMutableMap().sumOfFloat(function);
    }

    public long sumOfLong(LongFunction<? super V> function)
    {
        return this.getMutableMap().sumOfLong(function);
    }

    public double sumOfDouble(DoubleFunction<? super V> function)
    {
        return this.getMutableMap().sumOfDouble(function);
    }

    public <V1> MutableObjectLongMap<V1> sumByInt(Function<? super V, ? extends V1> groupBy, IntFunction<? super V> function)
    {
        return this.getMutableMap().sumByInt(groupBy, function);
    }

    public <V1> MutableObjectDoubleMap<V1> sumByFloat(Function<? super V, ? extends V1> groupBy, FloatFunction<? super V> function)
    {
        return this.getMutableMap().sumByFloat(groupBy, function);
    }

    public <V1> MutableObjectLongMap<V1> sumByLong(Function<? super V, ? extends V1> groupBy, LongFunction<? super V> function)
    {
        return this.getMutableMap().sumByLong(groupBy, function);
    }

    public <V1> MutableObjectDoubleMap<V1> sumByDouble(Function<? super V, ? extends V1> groupBy, DoubleFunction<? super V> function)
    {
        return this.getMutableMap().sumByDouble(groupBy, function);
    }

    public String makeString()
    {
        return this.getMutableMap().makeString();
    }

    public String makeString(String separator)
    {
        return this.getMutableMap().makeString(separator);
    }

    public String makeString(String start, String separator, String end)
    {
        return this.getMutableMap().makeString(start, separator, end);
    }

    public V max()
    {
        return this.getMutableMap().max();
    }

    public V max(Comparator<? super V> comparator)
    {
        return this.getMutableMap().max(comparator);
    }

    public <R extends Comparable<? super R>> V maxBy(Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().maxBy(function);
    }

    public V min()
    {
        return this.getMutableMap().min();
    }

    public V min(Comparator<? super V> comparator)
    {
        return this.getMutableMap().min(comparator);
    }

    public <R extends Comparable<? super R>> V minBy(Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().minBy(function);
    }

    public Object[] toArray()
    {
        return this.getMutableMap().toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.getMutableMap().toArray(a);
    }

    public <S, R extends Collection<Pair<V, S>>> R zip(Iterable<S> that, R target)
    {
        return this.getMutableMap().zip(that, target);
    }

    public <R extends Collection<Pair<V, Integer>>> R zipWithIndex(R target)
    {
        return this.getMutableMap().zipWithIndex(target);
    }

    public <R> MutableBag<R> collect(Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().collect(function);
    }

    public MutableBooleanBag collectBoolean(BooleanFunction<? super V> booleanFunction)
    {
        return this.getMutableMap().collectBoolean(booleanFunction);
    }

    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super V> booleanFunction, R target)
    {
        return this.getMutableMap().collectBoolean(booleanFunction, target);
    }

    public MutableByteBag collectByte(ByteFunction<? super V> byteFunction)
    {
        return this.getMutableMap().collectByte(byteFunction);
    }

    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super V> byteFunction, R target)
    {
        return this.getMutableMap().collectByte(byteFunction, target);
    }

    public MutableCharBag collectChar(CharFunction<? super V> charFunction)
    {
        return this.getMutableMap().collectChar(charFunction);
    }

    public <R extends MutableCharCollection> R collectChar(CharFunction<? super V> charFunction, R target)
    {
        return this.getMutableMap().collectChar(charFunction, target);
    }

    public MutableDoubleBag collectDouble(DoubleFunction<? super V> doubleFunction)
    {
        return this.getMutableMap().collectDouble(doubleFunction);
    }

    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super V> doubleFunction, R target)
    {
        return this.getMutableMap().collectDouble(doubleFunction, target);
    }

    public MutableFloatBag collectFloat(FloatFunction<? super V> floatFunction)
    {
        return this.getMutableMap().collectFloat(floatFunction);
    }

    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super V> floatFunction, R target)
    {
        return this.getMutableMap().collectFloat(floatFunction, target);
    }

    public MutableIntBag collectInt(IntFunction<? super V> intFunction)
    {
        return this.getMutableMap().collectInt(intFunction);
    }

    public <R extends MutableIntCollection> R collectInt(IntFunction<? super V> intFunction, R target)
    {
        return this.getMutableMap().collectInt(intFunction, target);
    }

    public MutableLongBag collectLong(LongFunction<? super V> longFunction)
    {
        return this.getMutableMap().collectLong(longFunction);
    }

    public <R extends MutableLongCollection> R collectLong(LongFunction<? super V> longFunction, R target)
    {
        return this.getMutableMap().collectLong(longFunction, target);
    }

    public MutableShortBag collectShort(ShortFunction<? super V> shortFunction)
    {
        return this.getMutableMap().collectShort(shortFunction);
    }

    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super V> shortFunction, R target)
    {
        return this.getMutableMap().collectShort(shortFunction, target);
    }

    public <R> MutableBag<R> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends R> function)
    {
        return this.getMutableMap().collectIf(predicate, function);
    }

    public <R> MutableBag<R> flatCollect(Function<? super V, ? extends Iterable<R>> function)
    {
        return this.getMutableMap().flatCollect(function);
    }

    public MutableBag<V> select(Predicate<? super V> predicate)
    {
        return this.getMutableMap().select(predicate);
    }

    public <R extends Collection<V>> R select(Predicate<? super V> predicate, R target)
    {
        return this.getMutableMap().select(predicate, target);
    }

    public <P> MutableBag<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().selectWith(predicate, parameter);
    }

    public <P, R extends Collection<V>> R selectWith(Predicate2<? super V, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.getMutableMap().selectWith(predicate, parameter, targetCollection);
    }

    public MutableBag<V> reject(Predicate<? super V> predicate)
    {
        return this.getMutableMap().reject(predicate);
    }

    public <R extends Collection<V>> R reject(Predicate<? super V> predicate, R target)
    {
        return this.getMutableMap().reject(predicate, target);
    }

    public <P> MutableBag<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().rejectWith(predicate, parameter);
    }

    public <P, R extends Collection<V>> R rejectWith(Predicate2<? super V, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.getMutableMap().rejectWith(predicate, parameter, targetCollection);
    }

    public <S> MutableBag<S> selectInstancesOf(Class<S> clazz)
    {
        return this.getMutableMap().selectInstancesOf(clazz);
    }

    public PartitionMutableBag<V> partition(Predicate<? super V> predicate)
    {
        return this.getMutableMap().partition(predicate);
    }

    public <P> PartitionMutableBag<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getMutableMap().partitionWith(predicate, parameter);
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    public <S> MutableBag<Pair<V, S>> zip(Iterable<S> that)
    {
        return this.getMutableMap().zip(that);
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    public MutableSet<Pair<V, Integer>> zipWithIndex()
    {
        return this.getMutableMap().zipWithIndex();
    }

    public <K2, V2> MutableMap<K2, V2> aggregateInPlaceBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Procedure2<? super V2, ? super V> mutatingAggregator)
    {
        return this.getMutableMap().aggregateInPlaceBy(groupBy, zeroValueFactory, mutatingAggregator);
    }

    public <K2, V2> MutableMap<K2, V2> aggregateBy(
            Function<? super V, ? extends K2> groupBy,
            Function0<? extends V2> zeroValueFactory,
            Function2<? super V2, ? super V, ? extends V2> nonMutatingAggregator)
    {
        return this.getMutableMap().aggregateBy(groupBy, zeroValueFactory, nonMutatingAggregator);
    }
}
