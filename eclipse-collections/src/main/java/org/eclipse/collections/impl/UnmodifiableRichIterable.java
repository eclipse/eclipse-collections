/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

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
import org.eclipse.collections.api.bag.MutableBag;
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
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * An unmodifiable view of a RichIterable.
 */
public class UnmodifiableRichIterable<T>
        implements RichIterable<T>, Serializable
{
    private static final long serialVersionUID = 1L;

    protected final RichIterable<T> iterable;

    protected UnmodifiableRichIterable(RichIterable<T> richIterable)
    {
        this.iterable = richIterable;
    }

    /**
     * This method will take a RichIterable and wrap it directly in a UnmodifiableRichIterable.
     */
    public static <E, RI extends RichIterable<E>> UnmodifiableRichIterable<E> of(RI iterable)
    {
        if (iterable == null)
        {
            throw new IllegalArgumentException("cannot create a UnmodifiableRichIterable for null");
        }
        return new UnmodifiableRichIterable<E>(iterable);
    }

    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        return this.iterable.select(predicate, target);
    }

    public <P> RichIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.selectWith(predicate, parameter);
    }

    public <P, R extends Collection<T>> R selectWith(Predicate2<? super T, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.iterable.selectWith(predicate, parameter, targetCollection);
    }

    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        return this.iterable.reject(predicate, target);
    }

    public <P> RichIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.rejectWith(predicate, parameter);
    }

    public <P, R extends Collection<T>> R rejectWith(Predicate2<? super T, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.iterable.rejectWith(predicate, parameter, targetCollection);
    }

    public PartitionIterable<T> partition(Predicate<? super T> predicate)
    {
        return this.iterable.partition(predicate);
    }

    public <P> PartitionIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.partitionWith(predicate, parameter);
    }

    public <V, R extends Collection<V>> R collect(Function<? super T, ? extends V> function, R target)
    {
        return this.iterable.collect(function, target);
    }

    public <P, V> RichIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.iterable.collectWith(function, parameter);
    }

    public <P, V, R extends Collection<V>> R collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter, R targetCollection)
    {
        return this.iterable.collectWith(function, parameter, targetCollection);
    }

    public <V, R extends Collection<V>> R collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function, R target)
    {
        return this.iterable.collectIf(predicate, function, target);
    }

    public <V, R extends Collection<V>> R flatCollect(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.iterable.flatCollect(function, target);
    }

    public boolean contains(Object object)
    {
        return this.iterable.contains(object);
    }

    public boolean containsAllIterable(Iterable<?> source)
    {
        return this.iterable.containsAllIterable(source);
    }

    public boolean containsAll(Collection<?> source)
    {
        return this.iterable.containsAll(source);
    }

    public boolean containsAllArguments(Object... elements)
    {
        return this.iterable.containsAllArguments(elements);
    }

    public Object[] toArray()
    {
        return this.iterable.toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.iterable.toArray(a);
    }

    public RichIterable<T> tap(Procedure<? super T> procedure)
    {
        this.forEach(procedure);
        return this;
    }

    public void forEach(Procedure<? super T> procedure)
    {
        this.each(procedure);
    }

    public void each(Procedure<? super T> procedure)
    {
        this.iterable.forEach(procedure);
    }

    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.iterable.forEachWithIndex(objectIntProcedure);
    }

    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.iterable.forEachWith(procedure, parameter);
    }

    public boolean notEmpty()
    {
        return this.iterable.notEmpty();
    }

    public boolean isEmpty()
    {
        return this.iterable.isEmpty();
    }

    public int size()
    {
        return this.iterable.size();
    }

    public T getFirst()
    {
        return this.iterable.getFirst();
    }

    public T getLast()
    {
        return this.iterable.getLast();
    }

    public RichIterable<T> select(Predicate<? super T> predicate)
    {
        return this.iterable.select(predicate);
    }

    public RichIterable<T> reject(Predicate<? super T> predicate)
    {
        return this.iterable.reject(predicate);
    }

    public <S> RichIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return this.iterable.selectInstancesOf(clazz);
    }

    public <V> RichIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return this.iterable.collect(function);
    }

    public BooleanIterable collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return this.iterable.collectBoolean(booleanFunction);
    }

    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
    {
        return this.iterable.collectBoolean(booleanFunction, target);
    }

    public ByteIterable collectByte(ByteFunction<? super T> byteFunction)
    {
        return this.iterable.collectByte(byteFunction);
    }

    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
    {
        return this.iterable.collectByte(byteFunction, target);
    }

    public CharIterable collectChar(CharFunction<? super T> charFunction)
    {
        return this.iterable.collectChar(charFunction);
    }

    public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
    {
        return this.iterable.collectChar(charFunction, target);
    }

    public DoubleIterable collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.iterable.collectDouble(doubleFunction);
    }

    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
    {
        return this.iterable.collectDouble(doubleFunction, target);
    }

    public FloatIterable collectFloat(FloatFunction<? super T> floatFunction)
    {
        return this.iterable.collectFloat(floatFunction);
    }

    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
    {
        return this.iterable.collectFloat(floatFunction, target);
    }

    public IntIterable collectInt(IntFunction<? super T> intFunction)
    {
        return this.iterable.collectInt(intFunction);
    }

    public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
    {
        return this.iterable.collectInt(intFunction, target);
    }

    public LongIterable collectLong(LongFunction<? super T> longFunction)
    {
        return this.iterable.collectLong(longFunction);
    }

    public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
    {
        return this.iterable.collectLong(longFunction, target);
    }

    public ShortIterable collectShort(ShortFunction<? super T> shortFunction)
    {
        return this.iterable.collectShort(shortFunction);
    }

    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
    {
        return this.iterable.collectShort(shortFunction, target);
    }

    public <V> RichIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.iterable.flatCollect(function);
    }

    public <V> RichIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.iterable.collectIf(predicate, function);
    }

    public T detect(Predicate<? super T> predicate)
    {
        return this.iterable.detect(predicate);
    }

    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.detectWith(predicate, parameter);
    }

    public T min(Comparator<? super T> comparator)
    {
        return this.iterable.min(comparator);
    }

    public T max(Comparator<? super T> comparator)
    {
        return this.iterable.max(comparator);
    }

    public T min()
    {
        return this.iterable.min();
    }

    public T max()
    {
        return this.iterable.max();
    }

    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return this.iterable.minBy(function);
    }

    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return this.iterable.maxBy(function);
    }

    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        return this.iterable.detectIfNone(predicate, function);
    }

    public <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function)
    {
        return this.iterable.detectWithIfNone(predicate, parameter, function);
    }

    public int count(Predicate<? super T> predicate)
    {
        return this.iterable.count(predicate);
    }

    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.countWith(predicate, parameter);
    }

    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.iterable.anySatisfy(predicate);
    }

    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.anySatisfyWith(predicate, parameter);
    }

    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.iterable.allSatisfy(predicate);
    }

    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.allSatisfyWith(predicate, parameter);
    }

    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.iterable.noneSatisfy(predicate);
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.iterable.noneSatisfyWith(predicate, parameter);
    }

    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return this.iterable.injectInto(injectedValue, function);
    }

    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> function)
    {
        return this.iterable.injectInto(injectedValue, function);
    }

    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> function)
    {
        return this.iterable.injectInto(injectedValue, function);
    }

    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> function)
    {
        return this.iterable.injectInto(injectedValue, function);
    }

    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> function)
    {
        return this.iterable.injectInto(injectedValue, function);
    }

    public long sumOfInt(IntFunction<? super T> function)
    {
        return this.iterable.sumOfInt(function);
    }

    public double sumOfFloat(FloatFunction<? super T> function)
    {
        return this.iterable.sumOfFloat(function);
    }

    public long sumOfLong(LongFunction<? super T> function)
    {
        return this.iterable.sumOfLong(function);
    }

    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        return this.iterable.sumOfDouble(function);
    }

    public <V> ObjectLongMap<V> sumByInt(Function<T, V> groupBy, IntFunction<? super T> function)
    {
        return this.iterable.sumByInt(groupBy, function);
    }

    public <V> ObjectDoubleMap<V> sumByFloat(Function<T, V> groupBy, FloatFunction<? super T> function)
    {
        return this.iterable.sumByFloat(groupBy, function);
    }

    public <V> ObjectLongMap<V> sumByLong(Function<T, V> groupBy, LongFunction<? super T> function)
    {
        return this.iterable.sumByLong(groupBy, function);
    }

    public <V> ObjectDoubleMap<V> sumByDouble(Function<T, V> groupBy, DoubleFunction<? super T> function)
    {
        return this.iterable.sumByDouble(groupBy, function);
    }

    public <R extends Collection<T>> R into(R target)
    {
        return this.iterable.into(target);
    }

    public MutableList<T> toList()
    {
        return this.iterable.toList();
    }

    public MutableList<T> toSortedList()
    {
        return this.iterable.toSortedList();
    }

    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.iterable.toSortedList(comparator);
    }

    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return this.iterable.toSortedListBy(function);
    }

    public MutableSortedSet<T> toSortedSet()
    {
        return this.iterable.toSortedSet();
    }

    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        return this.iterable.toSortedSet(comparator);
    }

    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return this.iterable.toSortedSetBy(function);
    }

    public MutableSet<T> toSet()
    {
        return this.iterable.toSet();
    }

    public MutableBag<T> toBag()
    {
        return this.iterable.toBag();
    }

    public MutableSortedBag<T> toSortedBag()
    {
        return this.iterable.toSortedBag();
    }

    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        return this.iterable.toSortedBag(comparator);
    }

    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return this.iterable.toSortedBagBy(function);
    }

    public <NK, NV> MutableMap<NK, NV> toMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.iterable.toMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.iterable.toSortedMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator,
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.iterable.toSortedMap(comparator, keyFunction, valueFunction);
    }

    public LazyIterable<T> asLazy()
    {
        return this.iterable.asLazy();
    }

    public Iterator<T> iterator()
    {
        return new UnmodifiableIteratorAdapter<T>(this.iterable.iterator());
    }

    @Override
    public String toString()
    {
        return this.iterable.toString();
    }

    public String makeString()
    {
        return this.iterable.makeString();
    }

    public String makeString(String separator)
    {
        return this.iterable.makeString(separator);
    }

    public String makeString(String start, String separator, String end)
    {
        return this.iterable.makeString(start, separator, end);
    }

    public void appendString(Appendable appendable)
    {
        this.iterable.appendString(appendable);
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.iterable.appendString(appendable, separator);
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.iterable.appendString(appendable, start, separator, end);
    }

    public <V> Multimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.iterable.groupBy(function);
    }

    public <V, R extends MutableMultimap<V, T>> R groupBy(
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.iterable.groupBy(function, target);
    }

    public <V> Multimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.iterable.groupByEach(function);
    }

    public <V, R extends MutableMultimap<V, T>> R groupByEach(
            Function<? super T, ? extends Iterable<V>> function,
            R target)
    {
        return this.iterable.groupByEach(function, target);
    }

    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return this.iterable.groupByUniqueKey(function);
    }

    public <V, R extends MutableMap<V, T>> R groupByUniqueKey(
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.iterable.groupByUniqueKey(function, target);
    }

    public <S> RichIterable<Pair<T, S>> zip(Iterable<S> that)
    {
        return this.iterable.zip(that);
    }

    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return this.iterable.zip(that, target);
    }

    public RichIterable<Pair<T, Integer>> zipWithIndex()
    {
        return this.iterable.zipWithIndex();
    }

    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return this.iterable.zipWithIndex(target);
    }

    public RichIterable<RichIterable<T>> chunk(int size)
    {
        return this.iterable.chunk(size);
    }

    public <K, V> MapIterable<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator)
    {
        return this.iterable.aggregateInPlaceBy(groupBy, zeroValueFactory, mutatingAggregator);
    }

    public <K, V> MapIterable<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        return this.iterable.aggregateBy(groupBy, zeroValueFactory, nonMutatingAggregator);
    }
}
