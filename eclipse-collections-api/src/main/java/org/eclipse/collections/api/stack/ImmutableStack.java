/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.stack;

import org.eclipse.collections.api.ImmutableIterable;
import org.eclipse.collections.api.block.function.Function;
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
import org.eclipse.collections.api.map.ImmutableMap;
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

public interface ImmutableStack<T> extends StackIterable<T>, ImmutableIterable<T>
{
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

    @Override
    <V> ImmutableStack<V> collectWithIndex(ObjectIntToObjectFunction<? super T, ? extends V> function);

    @Override
    <V> ImmutableStack<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    @Override
    <V> ImmutableListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> ImmutableListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    <V> ImmutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function);

    @Override
    <S> ImmutableStack<Pair<T, S>> zip(Iterable<S> that);

    @Override
    ImmutableStack<Pair<T, Integer>> zipWithIndex();

    /**
     * Size takes linear time on ImmutableStacks.
     */
    @Override
    int size();
}
