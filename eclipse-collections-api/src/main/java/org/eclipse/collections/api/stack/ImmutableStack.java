/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.stack;

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

public interface ImmutableStack<T> extends StackIterable<T>
{
    ImmutableStack<T> push(T item);

    ImmutableStack<T> pop();

    ImmutableStack<T> pop(int count);

    ImmutableStack<T> takeWhile(Predicate<? super T> predicate);

    ImmutableStack<T> dropWhile(Predicate<? super T> predicate);

    PartitionImmutableStack<T> partitionWhile(Predicate<? super T> predicate);

    ImmutableStack<T> distinct();

    ImmutableStack<T> tap(Procedure<? super T> procedure);

    ImmutableStack<T> select(Predicate<? super T> predicate);

    <P> ImmutableStack<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    ImmutableStack<T> reject(Predicate<? super T> predicate);

    <P> ImmutableStack<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> ImmutableStack<S> selectInstancesOf(Class<S> clazz);

    PartitionImmutableStack<T> partition(Predicate<? super T> predicate);

    <P> PartitionImmutableStack<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <V> ImmutableStack<V> collect(Function<? super T, ? extends V> function);

    ImmutableBooleanStack collectBoolean(BooleanFunction<? super T> booleanFunction);

    ImmutableByteStack collectByte(ByteFunction<? super T> byteFunction);

    ImmutableCharStack collectChar(CharFunction<? super T> charFunction);

    ImmutableDoubleStack collectDouble(DoubleFunction<? super T> doubleFunction);

    ImmutableFloatStack collectFloat(FloatFunction<? super T> floatFunction);

    ImmutableIntStack collectInt(IntFunction<? super T> intFunction);

    ImmutableLongStack collectLong(LongFunction<? super T> longFunction);

    ImmutableShortStack collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ImmutableStack<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ImmutableStack<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ImmutableStack<V> collectWithIndex(ObjectIntToObjectFunction<? super T, ? extends V> function);

    <V> ImmutableStack<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    <V> ImmutableListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ImmutableListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <V> ImmutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function);

    <S> ImmutableStack<Pair<T, S>> zip(Iterable<S> that);

    ImmutableStack<Pair<T, Integer>> zipWithIndex();

    <K, V> ImmutableMap<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator);

    <K, V> ImmutableMap<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);

    /**
     * Size takes linear time on ImmutableStacks.
     */
    int size();
}
