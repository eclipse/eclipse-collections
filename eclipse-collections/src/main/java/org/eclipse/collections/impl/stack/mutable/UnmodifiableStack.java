/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable;

import java.io.Serializable;
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
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ObjectLongMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.partition.stack.PartitionMutableStack;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.api.stack.primitive.MutableByteStack;
import org.eclipse.collections.api.stack.primitive.MutableCharStack;
import org.eclipse.collections.api.stack.primitive.MutableDoubleStack;
import org.eclipse.collections.api.stack.primitive.MutableFloatStack;
import org.eclipse.collections.api.stack.primitive.MutableIntStack;
import org.eclipse.collections.api.stack.primitive.MutableLongStack;
import org.eclipse.collections.api.stack.primitive.MutableShortStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.UnmodifiableIteratorAdapter;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;

public final class UnmodifiableStack<T> implements MutableStack<T>, Serializable
{
    private static final long serialVersionUID = 1L;

    private final MutableStack<T> mutableStack;

    UnmodifiableStack(MutableStack<T> mutableStack)
    {
        if (mutableStack == null)
        {
            throw new IllegalArgumentException("Cannot create a UnmodifiableStack on a null stack");
        }
        this.mutableStack = mutableStack;
    }

    public static <T, S extends MutableStack<T>> UnmodifiableStack<T> of(S stack)
    {
        return new UnmodifiableStack<T>(stack);
    }

    public T pop()
    {
        throw new UnsupportedOperationException("Cannot call pop() on " + this.getClass().getSimpleName());
    }

    public ListIterable<T> pop(int count)
    {
        throw new UnsupportedOperationException("Cannot call pop() on " + this.getClass().getSimpleName());
    }

    public <R extends Collection<T>> R pop(int count, R targetCollection)
    {
        throw new UnsupportedOperationException("Cannot call pop() on " + this.getClass().getSimpleName());
    }

    public <R extends MutableStack<T>> R pop(int count, R targetStack)
    {
        throw new UnsupportedOperationException("Cannot call pop() on " + this.getClass().getSimpleName());
    }

    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    public void push(T item)
    {
        throw new UnsupportedOperationException("Cannot call push() on " + this.getClass().getSimpleName());
    }

    public Iterator<T> iterator()
    {
        return new UnmodifiableIteratorAdapter<T>(this.mutableStack.iterator());
    }

    public MutableStack<T> select(Predicate<? super T> predicate)
    {
        return this.mutableStack.select(predicate);
    }

    public <P> MutableStack<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.selectWith(predicate, parameter);
    }

    public MutableStack<T> reject(Predicate<? super T> predicate)
    {
        return this.mutableStack.reject(predicate);
    }

    public <P> MutableStack<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.rejectWith(predicate, parameter);
    }

    public PartitionMutableStack<T> partition(Predicate<? super T> predicate)
    {
        return this.mutableStack.partition(predicate);
    }

    public <P> PartitionMutableStack<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.partitionWith(predicate, parameter);
    }

    public <S> RichIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return this.mutableStack.selectInstancesOf(clazz);
    }

    public <V> MutableStack<V> collect(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.collect(function);
    }

    public MutableBooleanStack collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return this.mutableStack.collectBoolean(booleanFunction);
    }

    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
    {
        return this.mutableStack.collectBoolean(booleanFunction, target);
    }

    public MutableByteStack collectByte(ByteFunction<? super T> byteFunction)
    {
        return this.mutableStack.collectByte(byteFunction);
    }

    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
    {
        return this.mutableStack.collectByte(byteFunction, target);
    }

    public MutableCharStack collectChar(CharFunction<? super T> charFunction)
    {
        return this.mutableStack.collectChar(charFunction);
    }

    public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
    {
        return this.mutableStack.collectChar(charFunction, target);
    }

    public MutableDoubleStack collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.mutableStack.collectDouble(doubleFunction);
    }

    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
    {
        return this.mutableStack.collectDouble(doubleFunction, target);
    }

    public MutableFloatStack collectFloat(FloatFunction<? super T> floatFunction)
    {
        return this.mutableStack.collectFloat(floatFunction);
    }

    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
    {
        return this.mutableStack.collectFloat(floatFunction, target);
    }

    public MutableIntStack collectInt(IntFunction<? super T> intFunction)
    {
        return this.mutableStack.collectInt(intFunction);
    }

    public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
    {
        return this.mutableStack.collectInt(intFunction, target);
    }

    public MutableLongStack collectLong(LongFunction<? super T> longFunction)
    {
        return this.mutableStack.collectLong(longFunction);
    }

    public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
    {
        return this.mutableStack.collectLong(longFunction, target);
    }

    public MutableShortStack collectShort(ShortFunction<? super T> shortFunction)
    {
        return this.mutableStack.collectShort(shortFunction);
    }

    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
    {
        return this.mutableStack.collectShort(shortFunction, target);
    }

    public <P, V> MutableStack<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.mutableStack.collectWith(function, parameter);
    }

    public <V> MutableStack<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.mutableStack.collectIf(predicate, function);
    }

    public <V> MutableStack<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.mutableStack.flatCollect(function);
    }

    public <S> MutableStack<Pair<T, S>> zip(Iterable<S> that)
    {
        return this.mutableStack.zip(that);
    }

    public MutableStack<Pair<T, Integer>> zipWithIndex()
    {
        return this.mutableStack.zipWithIndex();
    }

    public int size()
    {
        return this.mutableStack.size();
    }

    public boolean isEmpty()
    {
        return this.mutableStack.isEmpty();
    }

    public boolean notEmpty()
    {
        return this.mutableStack.notEmpty();
    }

    public T getFirst()
    {
        return this.mutableStack.getFirst();
    }

    public T getLast()
    {
        return this.mutableStack.getLast();
    }

    public boolean contains(Object object)
    {
        return this.mutableStack.contains(object);
    }

    public boolean containsAllIterable(Iterable<?> source)
    {
        return this.mutableStack.containsAllIterable(source);
    }

    public boolean containsAll(Collection<?> source)
    {
        return this.mutableStack.containsAll(source);
    }

    public boolean containsAllArguments(Object... elements)
    {
        return this.mutableStack.containsAllArguments(elements);
    }

    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        return this.mutableStack.select(predicate, target);
    }

    public <P, R extends Collection<T>> R selectWith(Predicate2<? super T, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.mutableStack.selectWith(predicate, parameter, targetCollection);
    }

    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        return this.mutableStack.reject(predicate, target);
    }

    public <P, R extends Collection<T>> R rejectWith(Predicate2<? super T, ? super P> predicate, P parameter, R targetCollection)
    {
        return this.mutableStack.rejectWith(predicate, parameter, targetCollection);
    }

    public <V, R extends Collection<V>> R collect(Function<? super T, ? extends V> function, R target)
    {
        return this.mutableStack.collect(function, target);
    }

    public <P, V, R extends Collection<V>> R collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter, R targetCollection)
    {
        return this.mutableStack.collectWith(function, parameter, targetCollection);
    }

    public <V, R extends Collection<V>> R collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function, R target)
    {
        return this.mutableStack.collectIf(predicate, function, target);
    }

    public <V, R extends Collection<V>> R flatCollect(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.mutableStack.flatCollect(function, target);
    }

    public T detect(Predicate<? super T> predicate)
    {
        return this.mutableStack.detect(predicate);
    }

    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.detectWith(predicate, parameter);
    }

    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        return this.mutableStack.detectIfNone(predicate, function);
    }

    public <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function)
    {
        return this.mutableStack.detectWithIfNone(predicate, parameter, function);
    }

    public int count(Predicate<? super T> predicate)
    {
        return this.mutableStack.count(predicate);
    }

    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.countWith(predicate, parameter);
    }

    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.mutableStack.anySatisfy(predicate);
    }

    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.anySatisfyWith(predicate, parameter);
    }

    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.mutableStack.allSatisfy(predicate);
    }

    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.allSatisfyWith(predicate, parameter);
    }

    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.mutableStack.noneSatisfy(predicate);
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.mutableStack.noneSatisfyWith(predicate, parameter);
    }

    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        return this.mutableStack.injectInto(injectedValue, function);
    }

    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> intObjectToIntFunction)
    {
        return this.mutableStack.injectInto(injectedValue, intObjectToIntFunction);
    }

    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> longObjectToLongFunction)
    {
        return this.mutableStack.injectInto(injectedValue, longObjectToLongFunction);
    }

    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> floatObjectToFloatFunction)
    {
        return this.mutableStack.injectInto(injectedValue, floatObjectToFloatFunction);
    }

    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> doubleObjectToDoubleFunction)
    {
        return this.mutableStack.injectInto(injectedValue, doubleObjectToDoubleFunction);
    }

    public MutableList<T> toList()
    {
        return this.mutableStack.toList();
    }

    public MutableList<T> toSortedList()
    {
        return this.mutableStack.toSortedList();
    }

    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.mutableStack.toSortedList(comparator);
    }

    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.toSortedListBy(function);
    }

    public MutableSet<T> toSet()
    {
        return this.mutableStack.toSet();
    }

    public MutableSortedSet<T> toSortedSet()
    {
        return this.mutableStack.toSortedSet();
    }

    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        return this.mutableStack.toSortedSet(comparator);
    }

    public MutableStack<T> toStack()
    {
        return this.mutableStack.toStack();
    }

    public ImmutableStack<T> toImmutable()
    {
        return this.mutableStack.toImmutable();
    }

    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.toSortedSetBy(function);
    }

    public MutableBag<T> toBag()
    {
        return this.mutableStack.toBag();
    }

    public MutableSortedBag<T> toSortedBag()
    {
        return this.mutableStack.toSortedBag();
    }

    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        return this.mutableStack.toSortedBag(comparator);
    }

    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.toSortedBagBy(function);
    }

    public <NK, NV> MutableMap<NK, NV> toMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        return this.mutableStack.toMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        return this.mutableStack.toSortedMap(keyFunction, valueFunction);
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator, Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction)
    {
        return this.mutableStack.toSortedMap(comparator, keyFunction, valueFunction);
    }

    public LazyIterable<T> asLazy()
    {
        return this.mutableStack.asLazy();
    }

    public Object[] toArray()
    {
        return this.mutableStack.toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return this.mutableStack.toArray(a);
    }

    public T min(Comparator<? super T> comparator)
    {
        return this.mutableStack.min(comparator);
    }

    public T max(Comparator<? super T> comparator)
    {
        return this.mutableStack.max(comparator);
    }

    public T min()
    {
        return this.mutableStack.min();
    }

    public T max()
    {
        return this.mutableStack.max();
    }

    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.minBy(function);
    }

    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.maxBy(function);
    }

    public long sumOfInt(IntFunction<? super T> intFunction)
    {
        return this.mutableStack.sumOfInt(intFunction);
    }

    public double sumOfFloat(FloatFunction<? super T> floatFunction)
    {
        return this.mutableStack.sumOfFloat(floatFunction);
    }

    public long sumOfLong(LongFunction<? super T> longFunction)
    {
        return this.mutableStack.sumOfLong(longFunction);
    }

    public double sumOfDouble(DoubleFunction<? super T> doubleFunction)
    {
        return this.mutableStack.sumOfDouble(doubleFunction);
    }

    public <V> ObjectLongMap<V> sumByInt(Function<T, V> groupBy, IntFunction<? super T> function)
    {
        ObjectLongHashMap<V> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function));
    }

    public <V> ObjectDoubleMap<V> sumByFloat(Function<T, V> groupBy, FloatFunction<? super T> function)
    {
        ObjectDoubleHashMap<V> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function));
    }

    public <V> ObjectLongMap<V> sumByLong(Function<T, V> groupBy, LongFunction<? super T> function)
    {
        ObjectLongHashMap<V> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function));
    }

    public <V> ObjectDoubleMap<V> sumByDouble(Function<T, V> groupBy, DoubleFunction<? super T> function)
    {
        ObjectDoubleHashMap<V> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function));
    }

    public String makeString()
    {
        return this.mutableStack.makeString();
    }

    public String makeString(String separator)
    {
        return this.mutableStack.makeString(separator);
    }

    public String makeString(String start, String separator, String end)
    {
        return this.mutableStack.makeString(start, separator, end);
    }

    public void appendString(Appendable appendable)
    {
        this.mutableStack.appendString(appendable);
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.mutableStack.appendString(appendable, separator);
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        this.mutableStack.appendString(appendable, start, separator, end);
    }

    public <V> MutableListMultimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.groupBy(function);
    }

    public <V, R extends MutableMultimap<V, T>> R groupBy(Function<? super T, ? extends V> function, R target)
    {
        return this.mutableStack.groupBy(function, target);
    }

    public <V> MutableListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.mutableStack.groupByEach(function);
    }

    public <V, R extends MutableMultimap<V, T>> R groupByEach(Function<? super T, ? extends Iterable<V>> function, R target)
    {
        return this.mutableStack.groupByEach(function, target);
    }

    public <V> MutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return this.mutableStack.groupByUniqueKey(function);
    }

    public <V, R extends MutableMap<V, T>> R groupByUniqueKey(Function<? super T, ? extends V> function, R target)
    {
        return this.mutableStack.groupByUniqueKey(function, target);
    }

    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return this.mutableStack.zip(that, target);
    }

    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return this.mutableStack.zipWithIndex(target);
    }

    public RichIterable<RichIterable<T>> chunk(int size)
    {
        return this.mutableStack.chunk(size);
    }

    public MutableStack<T> tap(Procedure<? super T> procedure)
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
        this.mutableStack.forEach(procedure);
    }

    public T peek()
    {
        return this.mutableStack.peek();
    }

    public ListIterable<T> peek(int count)
    {
        return this.mutableStack.peek(count);
    }

    public T peekAt(int index)
    {
        return this.mutableStack.peekAt(index);
    }

    @Override
    public String toString()
    {
        return this.mutableStack.toString();
    }

    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.mutableStack.forEachWithIndex(objectIntProcedure);
    }

    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.mutableStack.forEachWith(procedure, parameter);
    }

    public MutableStack<T> asUnmodifiable()
    {
        return this;
    }

    public MutableStack<T> asSynchronized()
    {
        return this.mutableStack.asSynchronized();
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.mutableStack.equals(obj);
    }

    @Override
    public int hashCode()
    {
        return this.mutableStack.hashCode();
    }

    public <K, V> MutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    public <K, V> MutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }
}
