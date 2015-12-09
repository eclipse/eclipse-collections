/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.stack;

import java.util.AbstractCollection;
import java.util.List;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
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
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.partition.stack.PartitionStack;
import org.eclipse.collections.api.stack.primitive.BooleanStack;
import org.eclipse.collections.api.stack.primitive.ByteStack;
import org.eclipse.collections.api.stack.primitive.CharStack;
import org.eclipse.collections.api.stack.primitive.DoubleStack;
import org.eclipse.collections.api.stack.primitive.FloatStack;
import org.eclipse.collections.api.stack.primitive.IntStack;
import org.eclipse.collections.api.stack.primitive.LongStack;
import org.eclipse.collections.api.stack.primitive.ShortStack;
import org.eclipse.collections.api.tuple.Pair;

/**
 * StackIterable is a last-in-first-out data structure. All iteration methods iterate from the "top" of the stack to the
 * "bottom". In other words, it processes the most recently added elements first.
 * <p>
 * For example:
 * <p>
 * {@link #forEach(Procedure)} iterates over every element, starting with the most recently added
 * <p>
 * {@link #getFirst()} returns the most recently added element, not the element that was added first
 * <p>
 * {@link #toString()} follows the same rules as {@link AbstractCollection#toString()} except it processes the elements
 * in the same order as {@code forEach()}.
 */
public interface StackIterable<T> extends RichIterable<T>
{
    /**
     * @return the top of the stack.
     */
    T peek();

    /**
     * @return a ListIterable of the number of elements specified by the count, beginning with the top of the stack.
     */
    ListIterable<T> peek(int count);

    /**
     * @param index the location to peek into
     * @return the element at the specified index
     */
    T peekAt(int index);

    /**
     * Should return the same value as peek().
     */
    T getFirst();

    /**
     * Should not work as it violates the contract of a Stack.
     */
    T getLast();

    /**
     * Follows the same rules as {@link AbstractCollection#toString()} except it processes the elements
     * in the same order as {@code forEach()}.
     */
    String toString();

    /**
     * Follows the same general contract as {@link List#equals(Object)}, but for Stacks.
     */
    boolean equals(Object o);

    /**
     * Follows the same general contract as {@link List#hashCode()}, but for Stacks.
     */
    int hashCode();

    /**
     * Converts the stack to a MutableStack implementation.
     *
     * @since 2.0
     */
    MutableStack<T> toStack();

    StackIterable<T> tap(Procedure<? super T> procedure);

    StackIterable<T> select(Predicate<? super T> predicate);

    <P> StackIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    StackIterable<T> reject(Predicate<? super T> predicate);

    <P> StackIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionStack<T> partition(Predicate<? super T> predicate);

    <P> PartitionStack<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <V> StackIterable<V> collect(Function<? super T, ? extends V> function);

    BooleanStack collectBoolean(BooleanFunction<? super T> booleanFunction);

    ByteStack collectByte(ByteFunction<? super T> byteFunction);

    CharStack collectChar(CharFunction<? super T> charFunction);

    DoubleStack collectDouble(DoubleFunction<? super T> doubleFunction);

    FloatStack collectFloat(FloatFunction<? super T> floatFunction);

    IntStack collectInt(IntFunction<? super T> intFunction);

    LongStack collectLong(LongFunction<? super T> longFunction);

    ShortStack collectShort(ShortFunction<? super T> shortFunction);

    <P, V> StackIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> StackIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> StackIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    <V> ListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <S> StackIterable<Pair<T, S>> zip(Iterable<S> that);

    StackIterable<Pair<T, Integer>> zipWithIndex();

    /**
     * Converts the StackIterable to an immutable implementation. Returns this for immutable stacks.
     *
     * @since 5.0
     */
    ImmutableStack<T> toImmutable();
}
