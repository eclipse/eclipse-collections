/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.Collection;

import net.jcip.annotations.Immutable;
import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.LazyByteIterable;
import org.eclipse.collections.api.LazyCharIterable;
import org.eclipse.collections.api.LazyDoubleIterable;
import org.eclipse.collections.api.LazyFloatIterable;
import org.eclipse.collections.api.LazyIntIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.LazyLongIterable;
import org.eclipse.collections.api.LazyShortIterable;
import org.eclipse.collections.api.RichIterable;
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
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.map.primitive.ObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ObjectLongMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.AbstractRichIterable;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.PartitionProcedure;
import org.eclipse.collections.impl.lazy.primitive.CollectBooleanIterable;
import org.eclipse.collections.impl.lazy.primitive.CollectByteIterable;
import org.eclipse.collections.impl.lazy.primitive.CollectCharIterable;
import org.eclipse.collections.impl.lazy.primitive.CollectDoubleIterable;
import org.eclipse.collections.impl.lazy.primitive.CollectFloatIterable;
import org.eclipse.collections.impl.lazy.primitive.CollectIntIterable;
import org.eclipse.collections.impl.lazy.primitive.CollectLongIterable;
import org.eclipse.collections.impl.lazy.primitive.CollectShortIterable;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.partition.list.PartitionFastList;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.utility.LazyIterate;

/**
 * AbstractLazyIterable provides a base from which deferred iterables such as SelectIterable,
 * RejectIterable and CollectIterable can be derived.
 */
@Immutable
public abstract class AbstractLazyIterable<T>
        extends AbstractRichIterable<T>
        implements LazyIterable<T>
{
    @Override
    public LazyIterable<T> asLazy()
    {
        return this;
    }

    public <R extends Collection<T>> R into(R target)
    {
        this.forEachWith(Procedures2.<T>addToCollection(), target);
        return target;
    }

    @Override
    public <E> E[] toArray(E[] array)
    {
        return this.toList().toArray(array);
    }

    public int size()
    {
        return this.count(Predicates.alwaysTrue());
    }

    @Override
    public boolean isEmpty()
    {
        return !this.anySatisfy(Predicates.alwaysTrue());
    }

    public T getFirst()
    {
        return this.detect(Predicates.alwaysTrue());
    }

    public T getLast()
    {
        final T[] result = (T[]) new Object[1];
        this.each(each -> result[0] = each);
        return result[0];
    }

    public LazyIterable<T> select(Predicate<? super T> predicate)
    {
        return LazyIterate.select(this, predicate);
    }

    public <P> LazyIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return LazyIterate.select(this, Predicates.bind(predicate, parameter));
    }

    public LazyIterable<T> reject(Predicate<? super T> predicate)
    {
        return LazyIterate.reject(this, predicate);
    }

    public <P> LazyIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return LazyIterate.reject(this, Predicates.bind(predicate, parameter));
    }

    public PartitionMutableList<T> partition(Predicate<? super T> predicate)
    {
        PartitionMutableList<T> partitionMutableList = new PartitionFastList<T>();
        this.forEach(new PartitionProcedure<T>(predicate, partitionMutableList));
        return partitionMutableList;
    }

    public <P> PartitionMutableList<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.partition(Predicates.bind(predicate, parameter));
    }

    public <S> LazyIterable<S> selectInstancesOf(Class<S> clazz)
    {
        return LazyIterate.selectInstancesOf(this, clazz);
    }

    public <V> LazyIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return LazyIterate.collect(this, function);
    }

    public LazyBooleanIterable collectBoolean(BooleanFunction<? super T> booleanFunction)
    {
        return new CollectBooleanIterable<T>(this, booleanFunction);
    }

    public LazyByteIterable collectByte(ByteFunction<? super T> byteFunction)
    {
        return new CollectByteIterable<T>(this, byteFunction);
    }

    public LazyCharIterable collectChar(CharFunction<? super T> charFunction)
    {
        return new CollectCharIterable<T>(this, charFunction);
    }

    public LazyDoubleIterable collectDouble(DoubleFunction<? super T> doubleFunction)
    {
        return new CollectDoubleIterable<T>(this, doubleFunction);
    }

    public LazyFloatIterable collectFloat(FloatFunction<? super T> floatFunction)
    {
        return new CollectFloatIterable<T>(this, floatFunction);
    }

    public LazyIntIterable collectInt(IntFunction<? super T> intFunction)
    {
        return new CollectIntIterable<T>(this, intFunction);
    }

    public LazyLongIterable collectLong(LongFunction<? super T> longFunction)
    {
        return new CollectLongIterable<T>(this, longFunction);
    }

    public LazyShortIterable collectShort(ShortFunction<? super T> shortFunction)
    {
        return new CollectShortIterable<T>(this, shortFunction);
    }

    public <P, V> LazyIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return LazyIterate.collect(this, Functions.bind(function, parameter));
    }

    public <V> LazyIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return LazyIterate.flatCollect(this, function);
    }

    public LazyIterable<T> concatenate(Iterable<T> iterable)
    {
        return LazyIterate.concatenate(this, iterable);
    }

    public <V> LazyIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return LazyIterate.collectIf(this, predicate, function);
    }

    public LazyIterable<T> take(int count)
    {
        return LazyIterate.take(this, count);
    }

    public LazyIterable<T> drop(int count)
    {
        return LazyIterate.drop(this, count);
    }

    public LazyIterable<T> takeWhile(Predicate<? super T> predicate)
    {
        return LazyIterate.takeWhile(this, predicate);
    }

    public LazyIterable<T> dropWhile(Predicate<? super T> predicate)
    {
        return LazyIterate.dropWhile(this, predicate);
    }

    public LazyIterable<T> distinct()
    {
        return LazyIterate.distinct(this);
    }

    public MutableStack<T> toStack()
    {
        return ArrayStack.newStack(this);
    }

    public <V> Multimap<V, T> groupBy(Function<? super T, ? extends V> function)
    {
        return this.groupBy(function, FastListMultimap.<V, T>newMultimap());
    }

    public <V> Multimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.groupByEach(function, FastListMultimap.<V, T>newMultimap());
    }

    public <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        return this.groupByUniqueKey(function, UnifiedMap.<V, T>newMap());
    }

    public <S> LazyIterable<Pair<T, S>> zip(Iterable<S> that)
    {
        return LazyIterate.zip(this, that);
    }

    public LazyIterable<Pair<T, Integer>> zipWithIndex()
    {
        return LazyIterate.zipWithIndex(this);
    }

    public LazyIterable<RichIterable<T>> chunk(int size)
    {
        return LazyIterate.chunk(this, size);
    }

    public LazyIterable<T> tap(Procedure<? super T> procedure)
    {
        return LazyIterate.tap(this, procedure);
    }

    public <K, V> MapIterable<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new MutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    public <K, V> MapIterable<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = UnifiedMap.newMap();
        this.forEach(new NonMutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }

    public <V> ObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByIntFunction(groupBy, function));
    }

    public <V> ObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByFloatFunction(groupBy, function));
    }

    public <V> ObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        MutableObjectLongMap<V> result = ObjectLongHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByLongFunction(groupBy, function));
    }

    public <V> ObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function)
    {
        MutableObjectDoubleMap<V> result = ObjectDoubleHashMap.newMap();
        return this.injectInto(result, PrimitiveFunctions.sumByDoubleFunction(groupBy, function));
    }
}
