/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.stack;

import java.util.stream.Stream;

import org.eclipse.collections.api.bag.ImmutableBag;
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
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.partition.stack.PartitionImmutableStack;
import org.eclipse.collections.api.stack.primitive.ImmutableBooleanStack;
import org.eclipse.collections.api.stack.primitive.ImmutableByteStack;
import org.eclipse.collections.api.stack.primitive.ImmutableCharStack;
import org.eclipse.collections.api.stack.primitive.ImmutableDoubleStack;
import org.eclipse.collections.api.stack.primitive.ImmutableFloatStack;
import org.eclipse.collections.api.stack.primitive.ImmutableIntStack;
import org.eclipse.collections.api.stack.primitive.ImmutableLongStack;
import org.eclipse.collections.api.stack.primitive.ImmutableShortStack;
import org.eclipse.collections.api.tuple.Pair;

public interface ImmutableStack<T> extends StackIterable<T>
{
    static <T> ImmutableStack<T> empty()
    {
        return Stacks.immutable.empty();
    }

    static <T> ImmutableStack<T> of()
    {
        return Stacks.immutable.of();
    }

    static <T> ImmutableStack<T> of(T element)
    {
        return Stacks.immutable.of(element);
    }

    static <T> ImmutableStack<T> of(T... elements)
    {
        return Stacks.immutable.of(elements);
    }

    static <T> ImmutableStack<T> ofAll(Iterable<? extends T> items)
    {
        return Stacks.immutable.ofAll(items);
    }

    static <T> ImmutableStack<T> fromStream(Stream<? extends T> stream)
    {
        return Stacks.immutable.fromStream(stream);
    }

    static <T> ImmutableStack<T> ofReversed(T... elements)
    {
        return Stacks.immutable.ofReversed(elements);
    }

    static <T> ImmutableStack<T> ofAllReversed(Iterable<? extends T> items)
    {
        return Stacks.immutable.ofAllReversed(items);
    }

    ImmutableStack<T> push(T item);

    ImmutableStack<T> pop();

    ImmutableStack<T> pop(int count);

    @Override
    ImmutableStack<T> takeWhile(Predicate<? super T> predicate);

    @Override
    ImmutableStack<T> dropWhile(Predicate<? super T> predicate);

    @Override
    PartitionImmutableStack<T> partitionWhile(Predicate<? super T> predicate);

    @Override
    ImmutableStack<T> distinct();

    @Override
    ImmutableStack<T> tap(Procedure<? super T> procedure);

    @Override
    ImmutableStack<T> select(Predicate<? super T> predicate);

    @Override
    <P> ImmutableStack<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    ImmutableStack<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ImmutableStack<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ImmutableStack<S> selectInstancesOf(Class<S> clazz);

    @Override
    PartitionImmutableStack<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionImmutableStack<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <V> ImmutableStack<V> collect(Function<? super T, ? extends V> function);

    @Override
    ImmutableBooleanStack collectBoolean(BooleanFunction<? super T> booleanFunction);

    @Override
    ImmutableByteStack collectByte(ByteFunction<? super T> byteFunction);

    @Override
    ImmutableCharStack collectChar(CharFunction<? super T> charFunction);

    @Override
    ImmutableDoubleStack collectDouble(DoubleFunction<? super T> doubleFunction);

    @Override
    ImmutableFloatStack collectFloat(FloatFunction<? super T> floatFunction);

    @Override
    ImmutableIntStack collectInt(IntFunction<? super T> intFunction);

    @Override
    ImmutableLongStack collectLong(LongFunction<? super T> longFunction);

    @Override
    ImmutableShortStack collectShort(ShortFunction<? super T> shortFunction);

    @Override
    <P, V> ImmutableStack<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    @Override
    <V> ImmutableStack<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    /**
     * @since 9.1.
     */
    @Override
    default <V> ImmutableStack<V> collectWithIndex(ObjectIntToObjectFunction<? super T, ? extends V> function)
    {
        int[] index = {0};
        return this.collect(each -> function.valueOf(each, index[0]++));
    }

    @Override
    <V> ImmutableStack<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, V> ImmutableStack<V> flatCollectWith(Function2<? super T, ? super P, ? extends Iterable<V>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    @Override
    <V> ImmutableListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    /**
     * @since 9.0
     */
    @Override
    default <V> ImmutableBag<V> countBy(Function<? super T, ? extends V> function)
    {
        return this.asLazy().<V>collect(function).toBag().toImmutable();
    }

    /**
     * @since 9.0
     */
    @Override
    default <V, P> ImmutableBag<V> countByWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.asLazy().<P, V>collectWith(function, parameter).toBag().toImmutable();
    }

    /**
     * @since 10.0.0
     */
    @Override
    default <V> ImmutableBag<V> countByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.asLazy().flatCollect(function).toBag().toImmutable();
    }

    @Override
    <V> ImmutableListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    <V> ImmutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function);

    @Override
    <S> ImmutableStack<Pair<T, S>> zip(Iterable<S> that);

    @Override
    ImmutableStack<Pair<T, Integer>> zipWithIndex();

    @Override
    <K, V> ImmutableMap<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator);

    @Override
    <K, V> ImmutableMap<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);

    @Override
    <V> ImmutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function);

    @Override
    <V> ImmutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function);

    @Override
    <V> ImmutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function);

    @Override
    <V> ImmutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function);

    /**
     * Size takes linear time on ImmutableStacks.
     */
    @Override
    int size();
}
