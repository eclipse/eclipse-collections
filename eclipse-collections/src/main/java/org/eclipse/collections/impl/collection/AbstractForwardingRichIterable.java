/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.ByteIterable;
import org.eclipse.collections.api.CharIterable;
import org.eclipse.collections.api.DoubleIterable;
import org.eclipse.collections.api.FloatIterable;
import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.LongIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.ShortIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.MutableBagIterable;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.bimap.MutableBiMap;
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
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.map.primitive.ObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

/**
 * An abstract base class for RichIterable decorators
 *
 * @since 10.0.
 */
public abstract class AbstractForwardingRichIterable<T> implements RichIterable<T>
{
    protected abstract RichIterable<T> getDelegate();

    @Override
    public boolean equals(Object obj)
    {
        return this.getDelegate().equals(obj);
    }

    @Override
    public int hashCode()
    {
        return this.getDelegate().hashCode();
    }

    @Override
    public String toString()
    {
        return this.getDelegate().toString();
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.getDelegate().iterator();
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.getDelegate().forEach(procedure);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.getDelegate().forEachWithIndex(objectIntProcedure);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.getDelegate().forEachWith(procedure, parameter);
    }

    @Override
    public int size()
    {
        return this.getDelegate().size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.getDelegate().isEmpty();
    }

    @Override
    public boolean notEmpty()
    {
        return this.getDelegate().notEmpty();
    }

    @Override
    public T getFirst()
    {
        return this.getDelegate().getFirst();
    }

    @Override
    public T getLast()
    {
        return this.getDelegate().getLast();
    }

    @Override
    public boolean contains(Object o)
    {
        return this.getDelegate().contains(o);
    }

    @Override
    public boolean containsAllIterable(Iterable<?> source)
    {
        return this.getDelegate().containsAllIterable(source);
    }

    @Override
    public boolean containsAll(Collection<?> coll)
    {
        return this.getDelegate().containsAll(coll);
    }

    @Override
    public boolean containsAllArguments(Object... elements)
    {
        return this.getDelegate().containsAllArguments(elements);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        this.getDelegate().forEach(procedure);
    }

    @Override
    public RichIterable<T> select(Predicate<? super T> predicate)
    {
        return this.getDelegate().select(predicate);
    }

    @Override
    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        return this.getDelegate().select(predicate, target);
    }

    @Override
    public <P> RichIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().selectWith(predicate, parameter);
    }

    @Override
    public <P, R extends Collection<T>> R selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R targetCollection)
    {
        return this.getDelegate().selectWith(predicate, parameter, targetCollection);
    }

    @Override
    public RichIterable<T> reject(Predicate<? super T> predicate)
    {
        return this.getDelegate().reject(predicate);
    }

    @Override
    public <P> RichIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().rejectWith(predicate, parameter);
    }

    @Override
    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        return this.getDelegate().reject(predicate, target);
    }

    @Override
    public <P, R extends Collection<T>> R rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R targetCollection)
    {
        return this.getDelegate().rejectWith(predicate, parameter, targetCollection);
    }

    @Override
    public PartitionIterable<T> partition(Predicate<? super T> predicate)
    {
        return this.getDelegate().partition(predicate);
    }

    @Override
    public <P> PartitionIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().partitionWith(predicate, parameter);
    }

    @Override
    public <S> RichIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return this.getDelegate().selectInstancesOf(clazz);
    }

    @Override
    public <V> RichIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().collect(function);
    }

    @Override
    public BooleanIterable collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return this.getDelegate().collectBoolean(booleanFunction);
    }

    @Override
    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
    {
        return this.getDelegate().collectBoolean(booleanFunction, target);
    }

    @Override
    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
    {
        return this.getDelegate().collectByte(byteFunction, target);
    }

    @Override
    public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
    {
        return this.getDelegate().collectChar(charFunction, target);
    }

    @Override
    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
    {
        return this.getDelegate().collectDouble(doubleFunction, target);
    }

    @Override
    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
    {
        return this.getDelegate().collectFloat(floatFunction, target);
    }

    @Override
    public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
    {
        return this.getDelegate().collectInt(intFunction, target);
    }

    @Override
    public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
    {
        return this.getDelegate().collectLong(longFunction, target);
    }

    @Override
    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
    {
        return this.getDelegate().collectShort(shortFunction, target);
    }

    @Override
    public <V, R extends Collection<V>> R collect(Function<? super T, ? extends V> function, R target)
    {
        return this.getDelegate().collect(function, target);
    }

    @Override
    public <P, A, R extends Collection<A>> R collectWith(
            Function2<? super T, ? super P, ? extends A> function,
            P parameter,
            R targetCollection)
    {
        return this.getDelegate().collectWith(function, parameter, targetCollection);
    }

    @Override
    public <V, R extends Collection<V>> R collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.getDelegate().collectIf(predicate, function, target);
    }

    @Override
    public <V, R extends Collection<V>> R flatCollect(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.getDelegate().flatCollect(function, target);
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return this.getDelegate().detect(predicate);
    }

    @Override
    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().detectWith(predicate, parameter);
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return this.getDelegate().detectOptional(predicate);
    }

    @Override
    public <P> Optional<T> detectWithOptional(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().detectWithOptional(predicate, parameter);
    }

    @Override
    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        return this.getDelegate().detectIfNone(predicate, function);
    }

    @Override
    public <P> T detectWithIfNone(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Function0<? extends T> function)
    {
        return this.getDelegate().detectWithIfNone(predicate, parameter, function);
    }

    @Override
    public int count(Predicate<? super T> predicate)
    {
        return this.getDelegate().count(predicate);
    }

    @Override
    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().countWith(predicate, parameter);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.getDelegate().anySatisfy(predicate);
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().anySatisfyWith(predicate, parameter);
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.getDelegate().allSatisfy(predicate);
    }

    @Override
    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().allSatisfyWith(predicate, parameter);
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.getDelegate().noneSatisfy(predicate);
    }

    @Override
    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().noneSatisfyWith(predicate, parameter);
    }

    @Override
    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    @Override
    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    @Override
    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    @Override
    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    @Override
    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> function)
    {
        return this.getDelegate().injectInto(injectedValue, function);
    }

    @Override
    public <R extends Collection<T>> R into(R target)
    {
        return this.getDelegate().into(target);
    }

    @Override
    public MutableList<T> toList()
    {
        return this.getDelegate().toList();
    }

    @Override
    public MutableList<T> toSortedList()
    {
        return this.getDelegate().toSortedList();
    }

    @Override
    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.getDelegate().toSortedList(comparator);
    }

    @Override
    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().toSortedList(Comparators.byFunction(function));
    }

    @Override
    public MutableSet<T> toSet()
    {
        return this.getDelegate().toSet();
    }

    @Override
    public MutableSortedSet<T> toSortedSet()
    {
        return this.getDelegate().toSortedSet();
    }

    @Override
    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        return this.getDelegate().toSortedSet(comparator);
    }

    @Override
    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().toSortedSetBy(function);
    }

    @Override
    public MutableBag<T> toBag()
    {
        return this.getDelegate().toBag();
    }

    @Override
    public MutableSortedBag<T> toSortedBag()
    {
        return this.getDelegate().toSortedBag();
    }

    @Override
    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        return this.getDelegate().toSortedBag(comparator);
    }

    @Override
    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().toSortedBag(Comparators.byFunction(function));
    }

    @Override
    public <NK, NV> MutableMap<NK, NV> toMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getDelegate().toMap(keyFunction, valueFunction);
    }

    @Override
    public <K, V, R extends Map<K, V>> R toMap(
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction,
            R target)
    {
        return this.getDelegate().toMap(keyFunction, valueFunction, target);
    }

    @Override
    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getDelegate().toSortedMap(keyFunction, valueFunction);
    }

    @Override
    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Comparator<? super NK> comparator,
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getDelegate().toSortedMap(comparator, keyFunction, valueFunction);
    }

    @Override
    public <KK extends Comparable<? super KK>, NK, NV> MutableSortedMap<NK, NV> toSortedMapBy(
            Function<? super NK, KK> sortBy,
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getDelegate().toSortedMapBy(sortBy, keyFunction, valueFunction);
    }

    @Override
    public <NK, NV> MutableBiMap<NK, NV> toBiMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getDelegate().toBiMap(keyFunction, valueFunction);
    }

    @Override
    public LazyIterable<T> asLazy()
    {
        return this.getDelegate().asLazy();
    }

    @Override
    public Object[] toArray()
    {
        return this.getDelegate().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return this.getDelegate().toArray(a);
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        return this.getDelegate().min(comparator);
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        return this.getDelegate().max(comparator);
    }

    @Override
    public Optional<T> minOptional(Comparator<? super T> comparator)
    {
        return this.getDelegate().minOptional(comparator);
    }

    @Override
    public Optional<T> maxOptional(Comparator<? super T> comparator)
    {
        return this.getDelegate().maxOptional(comparator);
    }

    @Override
    public T min()
    {
        return this.getDelegate().min();
    }

    @Override
    public T max()
    {
        return this.getDelegate().max();
    }

    @Override
    public Optional<T> minOptional()
    {
        return this.getDelegate().minOptional();
    }

    @Override
    public Optional<T> maxOptional()
    {
        return this.getDelegate().maxOptional();
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().minBy(function);
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().maxBy(function);
    }

    @Override
    public <V extends Comparable<? super V>> Optional<T> minByOptional(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().minByOptional(function);
    }

    @Override
    public <V extends Comparable<? super V>> Optional<T> maxByOptional(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().maxByOptional(function);
    }

    @Override
    public long sumOfInt(IntFunction<? super T> function)
    {
        return this.getDelegate().sumOfInt(function);
    }

    @Override
    public double sumOfFloat(FloatFunction<? super T> function)
    {
        return this.getDelegate().sumOfFloat(function);
    }

    @Override
    public long sumOfLong(LongFunction<? super T> function)
    {
        return this.getDelegate().sumOfLong(function);
    }

    @Override
    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        return this.getDelegate().sumOfDouble(function);
    }

    @Override
    public String makeString()
    {
        return this.getDelegate().makeString();
    }

    @Override
    public String makeString(String separator)
    {
        return this.getDelegate().makeString(separator);
    }

    @Override
    public String makeString(String start, String separator, String end)
    {
        return this.getDelegate().makeString(start, separator, end);
    }

    @Override
    public void appendString(Appendable appendable)
    {
        this.getDelegate().appendString(appendable);
    }

    @Override
    public void appendString(Appendable appendable, String separator)
    {
        this.getDelegate().appendString(appendable, separator);
    }

    @Override
    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.getDelegate().appendString(appendable, start, separator, end);
    }

    /**
     * @since 9.0
     */
    @Override
    public <V> Bag<V> countBy(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().countBy(function);
    }

    /**
     * @since 9.0
     */
    @Override
    public <V, R extends MutableBagIterable<V>> R countBy(Function<? super T, ? extends V> function, R target)
    {
        return this.getDelegate().countBy(function, target);
    }

    /**
     * @since 9.0
     */
    @Override
    public <V, P> Bag<V> countByWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.getDelegate().countByWith(function, parameter);
    }

    /**
     * @since 9.0
     */
    @Override
    public <V, P, R extends MutableBagIterable<V>> R countByWith(Function2<? super T, ? super P, ? extends V> function, P parameter, R target)
    {
        return this.getDelegate().countByWith(function, parameter, target);
    }

    /**
     * @since 10.0.0
     */
    @Override
    public <V> Bag<V> countByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.getDelegate().countByEach(function);
    }

    /**
     * @since 10.0.0
     */
    @Override
    public <V, R extends MutableBagIterable<V>> R countByEach(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.getDelegate().countByEach(function, target);
    }

    @Override
    public <V, R extends MutableMultimap<V, T>> R groupBy(
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.getDelegate().groupBy(function, target);
    }

    @Override
    public <V, R extends MutableMultimap<V, T>> R groupByEach(
            Function<? super T, ? extends Iterable<V>> function,
            R target)
    {
        return this.getDelegate().groupByEach(function, target);
    }

    @Override
    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().groupByUniqueKey(function, UnifiedMap.newMap(this.size()));
    }

    @Override
    public <V, R extends MutableMapIterable<V, T>> R groupByUniqueKey(
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.getDelegate().groupByUniqueKey(function, target);
    }

    @Override
    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return this.getDelegate().zip(that, target);
    }

    @Override
    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return this.getDelegate().zipWithIndex(target);
    }

    @Override
    public RichIterable<RichIterable<T>> chunk(int size)
    {
        return this.getDelegate().chunk(size);
    }

    @Override
    public T getOnly()
    {
        return this.getDelegate().getOnly();
    }

    @Override
    public ByteIterable collectByte(ByteFunction<? super T> byteFunction)
    {
        return this.getDelegate().collectByte(byteFunction);
    }

    @Override
    public CharIterable collectChar(CharFunction<? super T> charFunction)
    {
        return this.getDelegate().collectChar(charFunction);
    }

    @Override
    public DoubleIterable collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.getDelegate().collectDouble(doubleFunction);
    }

    @Override
    public FloatIterable collectFloat(FloatFunction<? super T> floatFunction)
    {
        return this.getDelegate().collectFloat(floatFunction);
    }

    @Override
    public IntIterable collectInt(IntFunction<? super T> intFunction)
    {
        return this.getDelegate().collectInt(intFunction);
    }

    @Override
    public LongIterable collectLong(LongFunction<? super T> longFunction)
    {
        return this.getDelegate().collectLong(longFunction);
    }

    @Override
    public ShortIterable collectShort(ShortFunction<? super T> shortFunction)
    {
        return this.getDelegate().collectShort(shortFunction);
    }

    @Override
    public <P, V> RichIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.getDelegate().collectWith(function, parameter);
    }

    @Override
    public <V> RichIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.getDelegate().collectIf(predicate, function);
    }

    @Override
    public <V> RichIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.getDelegate().flatCollect(function);
    }

    @Override
    public <V> ObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
    {
        return this.getDelegate().sumByInt(groupBy, function);
    }

    @Override
    public <V> ObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        return this.getDelegate().sumByFloat(groupBy, function);
    }

    @Override
    public <V> ObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        return this.getDelegate().sumByLong(groupBy, function);
    }

    @Override
    public <V> ObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
    {
        return this.getDelegate().sumByDouble(groupBy, function);
    }

    @Override
    public <V> Multimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.getDelegate().groupBy(function);
    }

    @Override
    public <V> Multimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.getDelegate().groupByEach(function);
    }

    @Override
    public <S> RichIterable<Pair<T, S>> zip(Iterable<S> that)
    {
        return this.getDelegate().zip(that);
    }

    @Override
    public RichIterable<Pair<T, Integer>> zipWithIndex()
    {
        return this.getDelegate().zipWithIndex();
    }

    @Override
    public <K, V> MapIterable<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator)
    {
        return this.getDelegate().aggregateInPlaceBy(groupBy, zeroValueFactory, mutatingAggregator);
    }

    @Override
    public <K, V> MapIterable<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        return this.getDelegate().aggregateBy(groupBy, zeroValueFactory, nonMutatingAggregator);
    }

    @Override
    public RichIterable<T> tap(Procedure<? super T> procedure)
    {
        return this.getDelegate().tap(procedure);
    }
}
