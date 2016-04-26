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

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;

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
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.block.procedure.AppendStringProcedure;
import org.eclipse.collections.impl.block.procedure.CollectIfProcedure;
import org.eclipse.collections.impl.block.procedure.CollectProcedure;
import org.eclipse.collections.impl.block.procedure.CountProcedure;
import org.eclipse.collections.impl.block.procedure.FlatCollectProcedure;
import org.eclipse.collections.impl.block.procedure.GroupByUniqueKeyProcedure;
import org.eclipse.collections.impl.block.procedure.InjectIntoProcedure;
import org.eclipse.collections.impl.block.procedure.MapCollectProcedure;
import org.eclipse.collections.impl.block.procedure.MaxByProcedure;
import org.eclipse.collections.impl.block.procedure.MaxComparatorProcedure;
import org.eclipse.collections.impl.block.procedure.MaxProcedure;
import org.eclipse.collections.impl.block.procedure.MinByProcedure;
import org.eclipse.collections.impl.block.procedure.MinComparatorProcedure;
import org.eclipse.collections.impl.block.procedure.MinProcedure;
import org.eclipse.collections.impl.block.procedure.MultimapEachPutProcedure;
import org.eclipse.collections.impl.block.procedure.MultimapPutProcedure;
import org.eclipse.collections.impl.block.procedure.RejectProcedure;
import org.eclipse.collections.impl.block.procedure.SelectProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfDoubleProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfFloatProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfIntProcedure;
import org.eclipse.collections.impl.block.procedure.SumOfLongProcedure;
import org.eclipse.collections.impl.block.procedure.ZipWithIndexProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectBooleanProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectByteProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectCharProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectDoubleProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectFloatProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectIntProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectLongProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.CollectShortProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoDoubleProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoFloatProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoIntProcedure;
import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoLongProcedure;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.SortedMaps;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.eclipse.collections.impl.utility.internal.IterableIterate;

public abstract class AbstractRichIterable<T> implements RichIterable<T>
{
    public boolean contains(Object object)
    {
        return this.anySatisfyWith(Predicates2.equal(), object);
    }

    public boolean containsAllIterable(Iterable<?> source)
    {
        return Iterate.allSatisfyWith(source, Predicates2.in(), this);
    }

    public boolean containsAllArguments(Object... elements)
    {
        return ArrayIterate.allSatisfyWith(elements, Predicates2.in(), this);
    }

    public Object[] toArray()
    {
        final Object[] result = new Object[this.size()];
        this.forEachWithIndex(new ObjectIntProcedure<T>()
        {
            public void value(T each, int index)
            {
                result[index] = each;
            }
        });
        return result;
    }

    public <E> E[] toArray(E[] array)
    {
        int size = this.size();
        final E[] result = array.length < size
                ? (E[]) Array.newInstance(array.getClass().getComponentType(), size)
                : array;

        this.forEachWithIndex(new ObjectIntProcedure<Object>()
        {
            public void value(Object each, int index)
            {
                result[index] = (E) each;
            }
        });
        if (result.length > size)
        {
            result[size] = null;
        }
        return result;
    }

    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    public boolean notEmpty()
    {
        return !this.isEmpty();
    }

    public MutableList<T> toList()
    {
        MutableList<T> list = Lists.mutable.empty();
        this.forEachWith(Procedures2.<T>addToCollection(), list);
        return list;
    }

    public MutableList<T> toSortedList()
    {
        return this.toList().sortThis();
    }

    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.toList().sortThis(comparator);
    }

    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return this.toSortedList(Comparators.byFunction(function));
    }

    public MutableSortedSet<T> toSortedSet()
    {
        MutableSortedSet<T> treeSet = SortedSets.mutable.empty();
        this.forEachWith(Procedures2.<T>addToCollection(), treeSet);
        return treeSet;
    }

    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        MutableSortedSet<T> treeSet = SortedSets.mutable.with(comparator);
        this.forEachWith(Procedures2.<T>addToCollection(), treeSet);
        return treeSet;
    }

    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return this.toSortedSet(Comparators.byFunction(function));
    }

    public MutableSet<T> toSet()
    {
        MutableSet<T> set = Sets.mutable.empty();
        this.forEachWith(Procedures2.<T>addToCollection(), set);
        return set;
    }

    public MutableBag<T> toBag()
    {
        MutableBag<T> bag = Bags.mutable.empty();
        this.forEachWith(Procedures2.<T>addToCollection(), bag);
        return bag;
    }

    public MutableSortedBag<T> toSortedBag()
    {
        MutableSortedBag<T> sortedBag = TreeBag.newBag();
        this.forEachWith(Procedures2.<T>addToCollection(), sortedBag);
        return sortedBag;
    }

    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        MutableSortedBag<T> sortedBag = TreeBag.newBag(comparator);
        this.forEachWith(Procedures2.<T>addToCollection(), sortedBag);
        return sortedBag;
    }

    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return this.toSortedBag(Comparators.byFunction(function));
    }

    public <K, V> MutableMap<K, V> toMap(
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction)
    {
        MutableMap<K, V> map = Maps.mutable.empty();
        this.forEach(new MapCollectProcedure<T, K, V>(map, keyFunction, valueFunction));
        return map;
    }

    public <K, V> MutableSortedMap<K, V> toSortedMap(
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction)
    {
        MutableSortedMap<K, V> sortedMap = SortedMaps.mutable.empty();
        this.forEach(new MapCollectProcedure<T, K, V>(sortedMap, keyFunction, valueFunction));
        return sortedMap;
    }

    public <K, V> MutableSortedMap<K, V> toSortedMap(
            Comparator<? super K> comparator,
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction)
    {
        MutableSortedMap<K, V> sortedMap = SortedMaps.mutable.with(comparator);
        this.forEach(new MapCollectProcedure<T, K, V>(sortedMap, keyFunction, valueFunction));
        return sortedMap;
    }

    public <R extends Collection<T>> R select(Predicate<? super T> predicate, R target)
    {
        this.forEach(new SelectProcedure<T>(predicate, target));
        return target;
    }

    public <P, R extends Collection<T>> R selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R target)
    {
        return this.select(Predicates.bind(predicate, parameter), target);
    }

    public <R extends Collection<T>> R reject(Predicate<? super T> predicate, R target)
    {
        this.forEach(new RejectProcedure<T>(predicate, target));
        return target;
    }

    public <P, R extends Collection<T>> R rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            R target)
    {
        return this.reject(Predicates.bind(predicate, parameter), target);
    }

    public <V, R extends Collection<V>> R collect(Function<? super T, ? extends V> function, R target)
    {
        this.forEach(new CollectProcedure<T, V>(function, target));
        return target;
    }

    public <P, V, R extends Collection<V>> R collectWith(
            Function2<? super T, ? super P, ? extends V> function,
            P parameter,
            R target)
    {
        return this.collect(Functions.bind(function, parameter), target);
    }

    public <V, R extends Collection<V>> R collectIf(
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function,
            R target)
    {
        this.forEach(new CollectIfProcedure<T, V>(target, function, predicate));
        return target;
    }

    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        T result = this.detect(predicate);
        return result == null ? function.value() : result;
    }

    public <P> T detectWithIfNone(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Function0<? extends T> function)
    {
        return this.detectIfNone(Predicates.bind(predicate, parameter), function);
    }

    public T min(Comparator<? super T> comparator)
    {
        MinComparatorProcedure<T> procedure = new MinComparatorProcedure<T>(comparator);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public T max(Comparator<? super T> comparator)
    {
        MaxComparatorProcedure<T> procedure = new MaxComparatorProcedure<T>(comparator);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public T min()
    {
        MinProcedure<T> procedure = new MinProcedure<T>();
        this.forEach(procedure);
        return procedure.getResult();
    }

    public T max()
    {
        MaxProcedure<T> procedure = new MaxProcedure<T>();
        this.forEach(procedure);
        return procedure.getResult();
    }

    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        MinByProcedure<T, V> minByProcedure = new MinByProcedure<T, V>(function);
        this.forEach(minByProcedure);
        return minByProcedure.getResult();
    }

    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        MaxByProcedure<T, V> maxByProcedure = new MaxByProcedure<T, V>(function);
        this.forEach(maxByProcedure);
        return maxByProcedure.getResult();
    }

    public LazyIterable<T> asLazy()
    {
        return LazyIterate.adapt(this);
    }

    public <V, R extends Collection<V>> R flatCollect(
            Function<? super T, ? extends Iterable<V>> function,
            R target)
    {
        this.forEach(new FlatCollectProcedure<T, V>(function, target));
        return target;
    }

    public T detect(Predicate<? super T> predicate)
    {
        return IterableIterate.detect(this, predicate);
    }

    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.detect(Predicates.bind(predicate, parameter));
    }

    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return IterableIterate.anySatisfy(this, predicate);
    }

    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return IterableIterate.allSatisfy(this, predicate);
    }

    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return IterableIterate.noneSatisfy(this, predicate);
    }

    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.anySatisfy(Predicates.bind(predicate, parameter));
    }

    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.allSatisfy(Predicates.bind(predicate, parameter));
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.noneSatisfy(Predicates.bind(predicate, parameter));
    }

    public int count(Predicate<? super T> predicate)
    {
        CountProcedure<T> procedure = new CountProcedure<T>(predicate);
        this.forEach(procedure);
        return procedure.getCount();
    }

    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.count(Predicates.bind(predicate, parameter));
    }

    public <IV> IV injectInto(IV injectedValue, Function2<? super IV, ? super T, ? extends IV> function)
    {
        InjectIntoProcedure<IV, T> procedure = new InjectIntoProcedure<IV, T>(injectedValue, function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public int injectInto(int injectedValue, IntObjectToIntFunction<? super T> function)
    {
        InjectIntoIntProcedure<T> procedure = new InjectIntoIntProcedure<T>(injectedValue, function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public long injectInto(long injectedValue, LongObjectToLongFunction<? super T> function)
    {
        InjectIntoLongProcedure<T> procedure = new InjectIntoLongProcedure<T>(injectedValue, function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public double injectInto(double injectedValue, DoubleObjectToDoubleFunction<? super T> function)
    {
        InjectIntoDoubleProcedure<T> procedure = new InjectIntoDoubleProcedure<T>(injectedValue, function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public <R extends Collection<T>> R into(R target)
    {
        return Iterate.addAllTo(this, target);
    }

    public float injectInto(float injectedValue, FloatObjectToFloatFunction<? super T> function)
    {
        InjectIntoFloatProcedure<T> procedure = new InjectIntoFloatProcedure<T>(injectedValue, function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public long sumOfInt(IntFunction<? super T> function)
    {
        SumOfIntProcedure<T> procedure = new SumOfIntProcedure<T>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public double sumOfFloat(FloatFunction<? super T> function)
    {
        SumOfFloatProcedure<T> procedure = new SumOfFloatProcedure<T>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public long sumOfLong(LongFunction<? super T> function)
    {
        SumOfLongProcedure<T> procedure = new SumOfLongProcedure<T>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public double sumOfDouble(DoubleFunction<? super T> function)
    {
        SumOfDoubleProcedure<T> procedure = new SumOfDoubleProcedure<T>(function);
        this.forEach(procedure);
        return procedure.getResult();
    }

    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        IterableIterate.forEachWithIndex(this, objectIntProcedure);
    }

    public final void forEach(Procedure<? super T> procedure)
    {
        this.each(procedure);
    }

    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.forEach(Procedures.bind(procedure, parameter));
    }

    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return IterableIterate.zip(this, that, target);
    }

    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        this.forEach(ZipWithIndexProcedure.create(target));
        return target;
    }

    /**
     * Returns a string representation of this collection.  The string representation consists of a list of the
     * collection's elements in the order they are returned by its iterator, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters <tt>", "</tt> (comma and space).  Elements
     * are converted to strings as by <tt>String.valueOf(Object)</tt>.<p>
     * <p>
     * This implementation creates an empty string buffer, appends a left square bracket, and iterates over the
     * collection appending the string representation of each element in turn.  After appending each element except the
     * last, the string <tt>", "</tt> is appended.  Finally a right bracket is appended.  A string is obtained from the
     * string buffer, and returned.
     *
     * @return a string representation of this collection.
     */
    @Override
    public String toString()
    {
        return this.makeString("[", ", ", "]");
    }

    public String makeString()
    {
        return this.makeString(", ");
    }

    public String makeString(String separator)
    {
        return this.makeString("", separator, "");
    }

    public String makeString(String start, String separator, String end)
    {
        Appendable stringBuilder = new StringBuilder();
        this.appendString(stringBuilder, start, separator, end);
        return stringBuilder.toString();
    }

    public void appendString(Appendable appendable)
    {
        this.appendString(appendable, ", ");
    }

    public void appendString(Appendable appendable, String separator)
    {
        AppendStringProcedure<T> appendStringProcedure = new AppendStringProcedure<T>(appendable, separator);
        this.forEach(appendStringProcedure);
    }

    public void appendString(Appendable appendable, String start, String separator, String end)
    {
        AppendStringProcedure<T> appendStringProcedure = new AppendStringProcedure<T>(appendable, separator);
        try
        {
            appendable.append(start);
            this.forEach(appendStringProcedure);
            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean containsAll(Collection<?> collection)
    {
        return this.containsAllIterable(collection);
    }

    public <R extends MutableBooleanCollection> R collectBoolean(BooleanFunction<? super T> booleanFunction, R target)
    {
        this.forEach(new CollectBooleanProcedure<T>(booleanFunction, target));
        return target;
    }

    public <R extends MutableByteCollection> R collectByte(ByteFunction<? super T> byteFunction, R target)
    {
        this.forEach(new CollectByteProcedure<T>(byteFunction, target));
        return target;
    }

    public <R extends MutableCharCollection> R collectChar(CharFunction<? super T> charFunction, R target)
    {
        this.forEach(new CollectCharProcedure<T>(charFunction, target));
        return target;
    }

    public <R extends MutableDoubleCollection> R collectDouble(DoubleFunction<? super T> doubleFunction, R target)
    {
        this.forEach(new CollectDoubleProcedure<T>(doubleFunction, target));
        return target;
    }

    public <R extends MutableFloatCollection> R collectFloat(FloatFunction<? super T> floatFunction, R target)
    {
        this.forEach(new CollectFloatProcedure<T>(floatFunction, target));
        return target;
    }

    public <R extends MutableIntCollection> R collectInt(IntFunction<? super T> intFunction, R target)
    {
        this.forEach(new CollectIntProcedure<T>(intFunction, target));
        return target;
    }

    public <R extends MutableLongCollection> R collectLong(LongFunction<? super T> longFunction, R target)
    {
        this.forEach(new CollectLongProcedure<T>(longFunction, target));
        return target;
    }

    public <R extends MutableShortCollection> R collectShort(ShortFunction<? super T> shortFunction, R target)
    {
        this.forEach(new CollectShortProcedure<T>(shortFunction, target));
        return target;
    }

    public <V, R extends MutableMultimap<V, T>> R groupBy(
            Function<? super T, ? extends V> function,
            R target)
    {
        this.forEach(MultimapPutProcedure.on(target, function));
        return target;
    }

    public <V, R extends MutableMultimap<V, T>> R groupByEach(
            Function<? super T, ? extends Iterable<V>> function,
            R target)
    {
        this.forEach(MultimapEachPutProcedure.on(target, function));
        return target;
    }

    public <V, R extends MutableMap<V, T>> R groupByUniqueKey(
            Function<? super T, ? extends V> function,
            R target)
    {
        this.forEach(new GroupByUniqueKeyProcedure<T, V>(target, function));
        return target;
    }
}
