/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.bimap.BiMap;
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
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;

public abstract class AbstractBiMap<K, V> implements BiMap<K, V>
{
    protected abstract MapIterable<K, V> getDelegate();

    protected abstract MapIterable<V, K> getInverse();

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Map))
        {
            return false;
        }

        Map<?, ?> map = (Map<?, ?>) obj;

        return this.getDelegate().equals(map);
    }

    @Override
    public int hashCode()
    {
        return this.getDelegate().hashCode();
    }

    public int size()
    {
        return this.getDelegate().size();
    }

    public V get(Object key)
    {
        return this.getDelegate().get(key);
    }

    public V getFirst()
    {
        return this.getDelegate().getFirst();
    }

    public V getLast()
    {
        return this.getDelegate().getLast();
    }

    public V getIfAbsent(K key, Function0<? extends V> function)
    {
        return this.getDelegate().getIfAbsent(key, function);
    }

    public V getIfAbsentValue(K key, V value)
    {
        return this.getDelegate().getIfAbsentValue(key, value);
    }

    public <P> V getIfAbsentWith(K key, Function<? super P, ? extends V> function, P parameter)
    {
        return this.getDelegate().getIfAbsentWith(key, function, parameter);
    }

    public <A> A ifPresentApply(K key, Function<? super V, ? extends A> function)
    {
        return this.getDelegate().ifPresentApply(key, function);
    }

    public boolean isEmpty()
    {
        return this.getDelegate().isEmpty();
    }

    public boolean notEmpty()
    {
        return this.getDelegate().notEmpty();
    }

    public boolean contains(Object object)
    {
        return this.getInverse().containsKey(object);
    }

    public boolean containsKey(Object key)
    {
        return this.getDelegate().containsKey(key);
    }

    public boolean containsValue(Object value)
    {
        return this.getInverse().containsKey(value);
    }

    public boolean containsAllIterable(Iterable<?> source)
    {
        return this.getInverse().keysView().containsAllIterable(source);
    }

    public boolean containsAll(Collection<?> source)
    {
        return this.getInverse().keysView().containsAll(source);
    }

    public boolean containsAllArguments(Object... elements)
    {
        return this.getInverse().keysView().containsAllArguments(elements);
    }

    public RichIterable<K> keysView()
    {
        return this.getDelegate().keysView();
    }

    public RichIterable<V> valuesView()
    {
        return this.getDelegate().valuesView();
    }

    public RichIterable<Pair<K, V>> keyValuesView()
    {
        return this.getDelegate().keyValuesView();
    }

    public <R extends Collection<V>> R into(R target)
    {
        return this.getDelegate().into(target);
    }

    public MutableList<V> toList()
    {
        return this.getDelegate().toList();
    }

    public MutableList<V> toSortedList()
    {
        return this.getDelegate().toSortedList();
    }

    public MutableList<V> toSortedList(Comparator<? super V> comparator)
    {
        return this.getDelegate().toSortedList(comparator);
    }

    public <VV extends Comparable<? super VV>> MutableList<V> toSortedListBy(Function<? super V, ? extends VV> function)
    {
        return this.getDelegate().toSortedListBy(function);
    }

    public MutableSet<V> toSet()
    {
        return this.getDelegate().toSet();
    }

    public MutableSortedSet<V> toSortedSet()
    {
        return this.getDelegate().toSortedSet();
    }

    public MutableSortedSet<V> toSortedSet(Comparator<? super V> comparator)
    {
        return this.getDelegate().toSortedSet(comparator);
    }

    public <VV extends Comparable<? super VV>> MutableSortedSet<V> toSortedSetBy(Function<? super V, ? extends VV> function)
    {
        return this.getDelegate().toSortedSetBy(function);
    }

    public MutableBag<V> toBag()
    {
        return this.getDelegate().toBag();
    }

    public MutableSortedBag<V> toSortedBag()
    {
        return this.getDelegate().toSortedBag();
    }

    public MutableSortedBag<V> toSortedBag(Comparator<? super V> comparator)
    {
        return this.getDelegate().toSortedBag(comparator);
    }

    public <VV extends Comparable<? super VV>> MutableSortedBag<V> toSortedBagBy(Function<? super V, ? extends VV> function)
    {
        return this.getDelegate().toSortedBagBy(function);
    }

    public <NK, NV> MutableMap<NK, NV> toMap(Function<? super V, ? extends NK> keyFunction, Function<? super V, ? extends NV> valueFunction)
    {
        return this.getDelegate().toMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Function<? super V, ? extends NK> keyFunction, Function<? super V, ? extends NV> valueFunction)
    {
        return this.getDelegate().toSortedMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator, Function<? super V, ? extends NK> keyFunction, Function<? super V, ? extends NV> valueFunction)
    {
        return this.getDelegate().toSortedMap(comparator, keyFunction, valueFunction);
    }

    public Object[] toArray()
    {
        return this.getDelegate().toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.getDelegate().toArray(a);
    }

    @Override
    public String toString()
    {
        return this.getDelegate().toString();
    }

    public String makeString()
    {
        return this.getDelegate().makeString();
    }

    public String makeString(String separator)
    {
        return this.getDelegate().makeString(separator);
    }

    public String makeString(String start, String separator, String end)
    {
        return this.getDelegate().makeString(start, separator, end);
    }

    public void appendString(Appendable appendable)
    {
        this.getDelegate().appendString(appendable);
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.getDelegate().appendString(appendable, separator);
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.getDelegate().appendString(appendable, start, separator, end);
    }

    public void forEachValue(Procedure<? super V> procedure)
    {
        this.getInverse().forEachKey(procedure);
    }

    public void forEachKey(Procedure<? super K> procedure)
    {
        this.getDelegate().forEachKey(procedure);
    }

    public void forEachKeyValue(Procedure2<? super K, ? super V> procedure)
    {
        this.getDelegate().forEachKeyValue(procedure);
    }

    public void each(Procedure<? super V> procedure)
    {
        this.getInverse().forEachKey(procedure);
    }

    public void forEach(Procedure<? super V> procedure)
    {
        this.each(procedure);
    }

    public void forEachWithIndex(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        this.getDelegate().forEachWithIndex(objectIntProcedure);
    }

    public <P> void forEachWith(Procedure2<? super V, ? super P> procedure, P parameter)
    {
        this.getDelegate().forEachWith(procedure, parameter);
    }

    public LazyIterable<V> asLazy()
    {
        return this.getDelegate().asLazy();
    }

    public int count(Predicate<? super V> predicate)
    {
        return this.getDelegate().count(predicate);
    }

    public <P> int countWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getDelegate().countWith(predicate, parameter);
    }

    public V min(Comparator<? super V> comparator)
    {
        return this.getDelegate().min(comparator);
    }

    public V min()
    {
        return this.getDelegate().min();
    }

    public <VV extends Comparable<? super VV>> V minBy(Function<? super V, ? extends VV> function)
    {
        return this.getDelegate().minBy(function);
    }

    public V max(Comparator<? super V> comparator)
    {
        return this.getDelegate().max(comparator);
    }

    public V max()
    {
        return this.getDelegate().max();
    }

    public <VV extends Comparable<? super VV>> V maxBy(Function<? super V, ? extends VV> function)
    {
        return this.getDelegate().maxBy(function);
    }

    public Pair<K, V> detect(Predicate2<? super K, ? super V> predicate)
    {
        return this.getDelegate().detect(predicate);
    }

    public V detect(Predicate<? super V> predicate)
    {
        return this.getDelegate().detect(predicate);
    }

    public <P> V detectWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getDelegate().detectWith(predicate, parameter);
    }

    public V detectIfNone(Predicate<? super V> predicate, Function0<? extends V> function)
    {
        return this.getDelegate().detectIfNone(predicate, function);
    }

    public <P> V detectWithIfNone(Predicate2<? super V, ? super P> predicate, P parameter, Function0<? extends V> function)
    {
        return this.getDelegate().detectWithIfNone(predicate, parameter, function);
    }

    public boolean anySatisfy(Predicate<? super V> predicate)
    {
        return this.getDelegate().anySatisfy(predicate);
    }

    public <P> boolean anySatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getDelegate().anySatisfyWith(predicate, parameter);
    }

    public boolean allSatisfy(Predicate<? super V> predicate)
    {
        return this.getDelegate().allSatisfy(predicate);
    }

    public <P> boolean allSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getDelegate().allSatisfyWith(predicate, parameter);
    }

    public boolean noneSatisfy(Predicate<? super V> predicate)
    {
        return this.getDelegate().noneSatisfy(predicate);
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super V, ? super P> predicate, P parameter)
    {
        return this.getDelegate().noneSatisfyWith(predicate, parameter);
    }

    public <VV, R extends Collection<VV>> R collect(Function<? super V, ? extends VV> function, R target)
    {
        return this.getDelegate().collect(function, target);
    }

    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super V> booleanFunction, R target)
    {
        return this.getDelegate().collectBoolean(booleanFunction, target);
    }

    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super V> byteFunction, R target)
    {
        return this.getDelegate().collectByte(byteFunction, target);
    }

    public <R extends MutableCharCollection> R collectChar(CharFunction<? super V> charFunction, R target)
    {
        return this.getDelegate().collectChar(charFunction, target);
    }

    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super V> doubleFunction, R target)
    {
        return this.getDelegate().collectDouble(doubleFunction, target);
    }

    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super V> floatFunction, R target)
    {
        return this.getDelegate().collectFloat(floatFunction, target);
    }

    public <R extends MutableIntCollection> R collectInt(IntFunction<? super V> intFunction, R target)
    {
        return this.getDelegate().collectInt(intFunction, target);
    }

    public <R extends MutableLongCollection> R collectLong(LongFunction<? super V> longFunction, R target)
    {
        return this.getDelegate().collectLong(longFunction, target);
    }

    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super V> shortFunction, R target)
    {
        return this.getDelegate().collectShort(shortFunction, target);
    }

    public <P, VV, R extends Collection<VV>> R collectWith(Function2<? super V, ? super P, ? extends VV> function, P parameter, R targetCollection)
    {
        return this.getDelegate().collectWith(function, parameter, targetCollection);
    }

    public <VV, R extends Collection<VV>> R collectIf(Predicate<? super V> predicate, Function<? super V, ? extends VV> function, R target)
    {
        return this.getDelegate().collectIf(predicate, function, target);
    }

    public <VV, R extends Collection<VV>> R flatCollect(Function<? super V, ? extends Iterable<VV>> function, R target)
    {
        return this.getDelegate().flatCollect(function, target);
    }

    public <R extends Collection<V>> R select(Predicate<? super V> predicate, R target)
    {
        return this.getDelegate().select(predicate, target);
    }

    public <P, R extends Collection<V>> R selectWith(Predicate2<? super V, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.getDelegate().selectWith(predicate, parameter, targetCollection);
    }

    public <R extends Collection<V>> R reject(Predicate<? super V> predicate, R target)
    {
        return this.getDelegate().reject(predicate, target);
    }

    public <P, R extends Collection<V>> R rejectWith(Predicate2<? super V, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.getDelegate().rejectWith(predicate, parameter, targetCollection);
    }

    /**
     * @deprecated in 8.0. Use {@link OrderedIterable#zip(Iterable, R)} instead.
     */
    @Deprecated
    public <S, R extends Collection<Pair<V, S>>> R zip(Iterable<S> that, R target)
    {
        return this.getDelegate().zip(that, target);
    }

    /**
     * @deprecated in 8.0. Use {@link OrderedIterable#zipWithIndex(R)} instead.
     */
    @Deprecated
    public <R extends Collection<Pair<V, Integer>>> R zipWithIndex(R target)
    {
        return this.getDelegate().zipWithIndex(target);
    }

    public RichIterable<RichIterable<V>> chunk(int size)
    {
        return this.getDelegate().chunk(size);
    }

    public <VV, R extends MutableMultimap<VV, V>> R groupBy(Function<? super V, ? extends VV> function, R target)
    {
        return this.getDelegate().groupBy(function, target);
    }

    public <VV, R extends MutableMultimap<VV, V>> R groupByEach(Function<? super V, ? extends Iterable<VV>> function, R target)
    {
        return this.getDelegate().groupByEach(function, target);
    }

    public <VV, R extends MutableMap<VV, V>> R groupByUniqueKey(Function<? super V, ? extends VV> function, R target)
    {
        return this.getDelegate().groupByUniqueKey(function, target);
    }

    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super V, ? extends IV> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    public int injectInto(int injectedValue, IntObjectToIntFunction<? super V> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    public long injectInto(long injectedValue, LongObjectToLongFunction<? super V> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super V> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super V> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    public long sumOfInt(IntFunction<? super V> function)
    {
        return this.getDelegate().sumOfInt(function);
    }

    public double sumOfFloat(FloatFunction<? super V> function)
    {
        return this.getDelegate().sumOfFloat(function);
    }

    public long sumOfLong(LongFunction<? super V> function)
    {
        return this.getDelegate().sumOfLong(function);
    }

    public double sumOfDouble(DoubleFunction<? super V> function)
    {
        return this.getDelegate().sumOfDouble(function);
    }

    public <V1> ObjectLongMap<V1> sumByInt(Function<V, V1> groupBy, IntFunction<? super V> function)
    {
        return this.getDelegate().sumByInt(groupBy, function);
    }

    public <V1> ObjectDoubleMap<V1> sumByFloat(Function<V, V1> groupBy, FloatFunction<? super V> function)
    {
        return this.getDelegate().sumByFloat(groupBy, function);
    }

    public <V1> ObjectLongMap<V1> sumByLong(Function<V, V1> groupBy, LongFunction<? super V> function)
    {
        return this.getDelegate().sumByLong(groupBy, function);
    }

    public <V1> ObjectDoubleMap<V1> sumByDouble(Function<V, V1> groupBy, DoubleFunction<? super V> function)
    {
        return this.getDelegate().sumByDouble(groupBy, function);
    }
}
