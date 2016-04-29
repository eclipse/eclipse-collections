/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
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
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionMutableCollection;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.UnmodifiableIteratorAdapter;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.factory.primitive.ObjectDoubleMaps;
import org.eclipse.collections.impl.factory.primitive.ObjectLongMaps;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.utility.LazyIterate;

public class AbstractUnmodifiableMutableCollection<T> implements MutableCollection<T>
{
    private final MutableCollection<? extends T> collection;

    protected AbstractUnmodifiableMutableCollection(MutableCollection<? extends T> mutableCollection)
    {
        if (mutableCollection == null)
        {
            throw new NullPointerException("cannot create a UnmodifiableMutableCollection for null");
        }
        this.collection = mutableCollection;
    }

    public int size()
    {
        return this.collection.size();
    }

    public boolean isEmpty()
    {
        return this.collection.isEmpty();
    }

    public boolean contains(Object o)
    {
        return this.collection.contains(o);
    }

    public Iterator<T> iterator()
    {
        return new UnmodifiableIteratorAdapter<>(this.collection.iterator());
    }

    public Object[] toArray()
    {
        return this.collection.toArray();
    }

    public <S> S[] toArray(S[] a)
    {
        return this.collection.toArray(a);
    }

    public boolean add(T o)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }

    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    public boolean containsAll(Collection<?> c)
    {
        return this.collection.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c)
    {
        throw new UnsupportedOperationException("Cannot call addAll() on " + this.getClass().getSimpleName());
    }

    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("Cannot call retainAll() on " + this.getClass().getSimpleName());
    }

    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("Cannot call removeAll() on " + this.getClass().getSimpleName());
    }

    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    protected MutableCollection<T> getMutableCollection()
    {
        return (MutableCollection<T>) this.collection;
    }

    public boolean addAllIterable(Iterable<? extends T> iterable)
    {
        throw new UnsupportedOperationException("Cannot call addAllIterable() on " + this.getClass().getSimpleName());
    }

    public boolean removeAllIterable(Iterable<?> iterable)
    {
        throw new UnsupportedOperationException("Cannot call removeAllIterable() on " + this.getClass().getSimpleName());
    }

    public boolean retainAllIterable(Iterable<?> iterable)
    {
        throw new UnsupportedOperationException("Cannot call retainAllIterable() on " + this.getClass().getSimpleName());
    }

    public MutableCollection<T> asUnmodifiable()
    {
        return this;
    }

    public MutableCollection<T> asSynchronized()
    {
        return SynchronizedMutableCollection.of(this);
    }

    public ImmutableCollection<T> toImmutable()
    {
        return this.getMutableCollection().toImmutable();
    }

    public LazyIterable<T> asLazy()
    {
        return LazyIterate.adapt(this);
    }

    public MutableCollection<T> tap(Procedure<? super T> procedure)
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
        this.getMutableCollection().forEach(procedure);
    }

    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.getMutableCollection().forEachWithIndex(objectIntProcedure);
    }

    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.getMutableCollection().forEachWith(procedure, parameter);
    }

    public boolean containsAllIterable(Iterable<?> source)
    {
        return this.getMutableCollection().containsAllIterable(source);
    }

    public boolean containsAllArguments(Object... elements)
    {
        return this.getMutableCollection().containsAllArguments(elements);
    }

    public boolean notEmpty()
    {
        return this.getMutableCollection().notEmpty();
    }

    public MutableCollection<T> newEmpty()
    {
        return this.getMutableCollection().newEmpty();
    }

    public T getFirst()
    {
        return this.getMutableCollection().getFirst();
    }

    public T getLast()
    {
        return this.getMutableCollection().getLast();
    }

    public MutableCollection<T> select(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().select(predicate);
    }

    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        return this.getMutableCollection().select(predicate, target);
    }

    public <P> MutableCollection<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().selectWith(predicate, parameter);
    }

    public <P, R extends Collection<T>> R selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R targetCollection)
    {
        return this.getMutableCollection().selectWith(predicate, parameter, targetCollection);
    }

    public MutableCollection<T> reject(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().reject(predicate);
    }

    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        return this.getMutableCollection().reject(predicate, target);
    }

    public <P> MutableCollection<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().rejectWith(predicate, parameter);
    }

    public <P, R extends Collection<T>> R rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R targetCollection)
    {
        return this.getMutableCollection().rejectWith(predicate, parameter, targetCollection);
    }

    public <P> Twin<MutableList<T>> selectAndRejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        return this.getMutableCollection().selectAndRejectWith(predicate, parameter);
    }

    public PartitionMutableCollection<T> partition(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().partition(predicate);
    }

    public <P> PartitionMutableCollection<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().partitionWith(predicate, parameter);
    }

    public <S> MutableCollection<S> selectInstancesOf(Class<S> clazz)
    {
        return this.getMutableCollection().selectInstancesOf(clazz);
    }

    public boolean removeIf(Predicate<? super T> predicate)
    {
        throw new UnsupportedOperationException("Cannot call removeIf() on " + this.getClass().getSimpleName());
    }

    public <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        throw new UnsupportedOperationException("Cannot call removeIfWith() on " + this.getClass().getSimpleName());
    }

    public <V> MutableCollection<V> collect(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().collect(function);
    }

    public MutableBooleanCollection collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return this.getMutableCollection().collectBoolean(booleanFunction);
    }

    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
    {
        return this.getMutableCollection().collectBoolean(booleanFunction, target);
    }

    public MutableByteCollection collectByte(ByteFunction<? super T> byteFunction)
    {
        return this.getMutableCollection().collectByte(byteFunction);
    }

    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
    {
        return this.getMutableCollection().collectByte(byteFunction, target);
    }

    public MutableCharCollection collectChar(CharFunction<? super T> charFunction)
    {
        return this.getMutableCollection().collectChar(charFunction);
    }

    public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
    {
        return this.getMutableCollection().collectChar(charFunction, target);
    }

    public MutableDoubleCollection collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.getMutableCollection().collectDouble(doubleFunction);
    }

    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
    {
        return this.getMutableCollection().collectDouble(doubleFunction, target);
    }

    public MutableFloatCollection collectFloat(FloatFunction<? super T> floatFunction)
    {
        return this.getMutableCollection().collectFloat(floatFunction);
    }

    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
    {
        return this.getMutableCollection().collectFloat(floatFunction, target);
    }

    public MutableIntCollection collectInt(IntFunction<? super T> intFunction)
    {
        return this.getMutableCollection().collectInt(intFunction);
    }

    public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
    {
        return this.getMutableCollection().collectInt(intFunction, target);
    }

    public MutableLongCollection collectLong(LongFunction<? super T> longFunction)
    {
        return this.getMutableCollection().collectLong(longFunction);
    }

    public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
    {
        return this.getMutableCollection().collectLong(longFunction, target);
    }

    public MutableShortCollection collectShort(ShortFunction<? super T> shortFunction)
    {
        return this.getMutableCollection().collectShort(shortFunction);
    }

    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
    {
        return this.getMutableCollection().collectShort(shortFunction, target);
    }

    public <V, R extends Collection<V>> R collect(Function<? super T, ? extends V> function, R target)
    {
        return this.getMutableCollection().collect(function, target);
    }

    public <V> MutableCollection<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.getMutableCollection().flatCollect(function);
    }

    public <V, R extends Collection<V>> R flatCollect(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.getMutableCollection().flatCollect(function, target);
    }

    public <P, A> MutableCollection<A> collectWith(Function2<? super T, ? super P, ? extends A> function, P parameter)
    {
        return this.getMutableCollection().collectWith(function, parameter);
    }

    public <P, A, R extends Collection<A>> R collectWith(
            Function2<? super T, ? super P, ? extends A> function,
            P parameter,
            R targetCollection)
    {
        return this.getMutableCollection().collectWith(function, parameter, targetCollection);
    }

    public <V> MutableCollection<V> collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().collectIf(predicate, function);
    }

    public <V, R extends Collection<V>> R collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.getMutableCollection().collectIf(predicate, function, target);
    }

    public T detect(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().detect(predicate);
    }

    public T min(Comparator<? super T> comparator)
    {
        return this.getMutableCollection().min(comparator);
    }

    public T max(Comparator<? super T> comparator)
    {
        return this.getMutableCollection().max(comparator);
    }

    public T min()
    {
        return this.getMutableCollection().min();
    }

    public T max()
    {
        return this.getMutableCollection().max();
    }

    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().minBy(function);
    }

    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().maxBy(function);
    }

    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        return this.getMutableCollection().detectIfNone(predicate, function);
    }

    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().detectWith(predicate, parameter);
    }

    public <P> T detectWithIfNone(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Function0<? extends T> function)
    {
        return this.getMutableCollection().detectWithIfNone(predicate, parameter, function);
    }

    public int count(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().count(predicate);
    }

    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().countWith(predicate, parameter);
    }

    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().anySatisfy(predicate);
    }

    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().anySatisfyWith(predicate, parameter);
    }

    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().allSatisfy(predicate);
    }

    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().allSatisfyWith(predicate, parameter);
    }

    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.getMutableCollection().noneSatisfy(predicate);
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getMutableCollection().noneSatisfyWith(predicate, parameter);
    }

    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return this.getMutableCollection().injectInto(injectedValue, function);
    }

    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> function)
    {
        return this.getMutableCollection().injectInto(injectedValue, function);
    }

    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> function)
    {
        return this.getMutableCollection().injectInto(injectedValue, function);
    }

    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> function)
    {
        return this.getMutableCollection().injectInto(injectedValue, function);
    }

    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> function)
    {
        return this.getMutableCollection().injectInto(injectedValue, function);
    }

    public long sumOfInt(IntFunction<? super T> function)
    {
        return this.getMutableCollection().sumOfInt(function);
    }

    public double sumOfFloat(FloatFunction<? super T> function)
    {
        return this.getMutableCollection().sumOfFloat(function);
    }

    public long sumOfLong(LongFunction<? super T> function)
    {
        return this.getMutableCollection().sumOfLong(function);
    }

    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        return this.getMutableCollection().sumOfDouble(function);
    }

    public <V> MutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function));
    }

    public <V> MutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function));
    }

    public <V> MutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function));
    }

    public <V> MutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleMaps.mutable.empty();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function));
    }

    public <IV, P> IV injectIntoWith(
            IV injectValue,
            Function3<? super IV, ? super T, ? super P, ? extends IV> function,
            P parameter)
    {
        return this.getMutableCollection().injectIntoWith(injectValue, function, parameter);
    }

    public <R extends Collection<T>> R into(R target)
    {
        return this.getMutableCollection().into(target);
    }

    public MutableList<T> toList()
    {
        return this.getMutableCollection().toList();
    }

    public MutableList<T> toSortedList()
    {
        return this.getMutableCollection().toSortedList();
    }

    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.getMutableCollection().toSortedList(comparator);
    }

    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().toSortedList(Comparators.byFunction(function));
    }

    public MutableSortedSet<T> toSortedSet()
    {
        return this.getMutableCollection().toSortedSet();
    }

    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        return this.getMutableCollection().toSortedSet(comparator);
    }

    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().toSortedSetBy(function);
    }

    public MutableSet<T> toSet()
    {
        return this.getMutableCollection().toSet();
    }

    public MutableBag<T> toBag()
    {
        return this.getMutableCollection().toBag();
    }

    public MutableSortedBag<T> toSortedBag()
    {
        return this.getMutableCollection().toSortedBag();
    }

    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        return this.getMutableCollection().toSortedBag(comparator);
    }

    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().toSortedBag(Comparators.byFunction(function));
    }

    public <NK, NV> MutableMap<NK, NV> toMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getMutableCollection().toMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getMutableCollection().toSortedMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator,
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        return this.getMutableCollection().toSortedMap(comparator, keyFunction, valueFunction);
    }

    @Override
    public String toString()
    {
        return this.getMutableCollection().toString();
    }

    public String makeString()
    {
        return this.getMutableCollection().makeString();
    }

    public String makeString(String separator)
    {
        return this.getMutableCollection().makeString(separator);
    }

    public String makeString(String start, String separator, String end)
    {
        return this.getMutableCollection().makeString(start, separator, end);
    }

    public void appendString(Appendable appendable)
    {
        this.getMutableCollection().appendString(appendable);
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.getMutableCollection().appendString(appendable, separator);
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.getMutableCollection().appendString(appendable, start, separator, end);
    }

    public <V> MutableMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().groupBy(function);
    }

    public <V, R extends MutableMultimap<V, T>> R groupBy(
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.getMutableCollection().groupBy(function, target);
    }

    public <V> MutableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.getMutableCollection().groupByEach(function);
    }

    public <V, R extends MutableMultimap<V, T>> R groupByEach(
            Function<? super T, ? extends Iterable<V>> function,
            R target)
    {
        return this.getMutableCollection().groupByEach(function, target);
    }

    public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return this.getMutableCollection().groupByUniqueKey(function);
    }

    public <V, R extends MutableMap<V, T>> R groupByUniqueKey(
            Function<? super T, ? extends V> function,
            R target)
    {
        return this.getMutableCollection().groupByUniqueKey(function, target);
    }

    public <S> MutableCollection<Pair<T, S>> zip(Iterable<S> that)
    {
        return this.getMutableCollection().zip(that);
    }

    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return this.getMutableCollection().zip(that, target);
    }

    public MutableCollection<Pair<T, Integer>> zipWithIndex()
    {
        return this.getMutableCollection().zipWithIndex();
    }

    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return this.getMutableCollection().zipWithIndex(target);
    }

    public RichIterable<RichIterable<T>> chunk(int size)
    {
        return this.getMutableCollection().chunk(size);
    }

    public MutableCollection<T> with(T element)
    {
        throw new UnsupportedOperationException("Cannot call with() on " + this.getClass().getSimpleName());
    }

    public MutableCollection<T> without(T element)
    {
        throw new UnsupportedOperationException("Cannot call without() on " + this.getClass().getSimpleName());
    }

    public MutableCollection<T> withAll(Iterable<? extends T> elements)
    {
        throw new UnsupportedOperationException("Cannot call withAll() on " + this.getClass().getSimpleName());
    }

    public MutableCollection<T> withoutAll(Iterable<? extends T> elements)
    {
        throw new UnsupportedOperationException("Cannot call withoutAll() on " + this.getClass().getSimpleName());
    }

    public <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    public <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }
}
