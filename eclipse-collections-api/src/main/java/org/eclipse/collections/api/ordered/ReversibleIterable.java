/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.ordered;

import org.eclipse.collections.api.LazyIterable;
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
import org.eclipse.collections.api.multimap.ordered.ReversibleIterableMultimap;
import org.eclipse.collections.api.ordered.primitive.ReversibleBooleanIterable;
import org.eclipse.collections.api.ordered.primitive.ReversibleByteIterable;
import org.eclipse.collections.api.ordered.primitive.ReversibleCharIterable;
import org.eclipse.collections.api.ordered.primitive.ReversibleDoubleIterable;
import org.eclipse.collections.api.ordered.primitive.ReversibleFloatIterable;
import org.eclipse.collections.api.ordered.primitive.ReversibleIntIterable;
import org.eclipse.collections.api.ordered.primitive.ReversibleLongIterable;
import org.eclipse.collections.api.ordered.primitive.ReversibleShortIterable;
import org.eclipse.collections.api.partition.ordered.PartitionReversibleIterable;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A ReversibleIterable is an ordered iterable that you can iterate over forwards or backwards. Besides being ordered,
 * it has methods that support efficient iteration from the end, including {@link #asReversed()} and
 * {@link #reverseForEach(Procedure)}.
 *
 * @since 5.0
 */
public interface ReversibleIterable<T> extends OrderedIterable<T>
{
    /**
     * Evaluates the procedure for each element of the list iterating in reverse order.
     * <p>
     * <pre>e.g.
     * people.reverseForEach(new Procedure<Person>()
     * {
     *     public void value(Person person)
     *     {
     *         LOGGER.info(person.getName());
     *     }
     * });
     * </pre>
     */
    void reverseForEach(Procedure<? super T> procedure);

    /**
     * Returns a reversed view of this ReversibleIterable.
     */
    LazyIterable<T> asReversed();

    /**
     * Returns a new ReversibleIterable in reverse order.
     *
     * @since 6.0.0
     */
    ReversibleIterable<T> toReversed();

    /**
     * Returns the index of the last element of the {@code ReversibleIterable} for which the {@code predicate} evaluates to true.
     * Returns -1 if no element evaluates true for the {@code predicate}.
     *
     * @since 6.0
     */
    int detectLastIndex(Predicate<? super T> predicate);

    /**
     * Returns the first {@code count} elements of the iterable
     * or all the elements in the iterable if {@code count} is greater than the length of
     * the iterable.
     *
     * @param count the number of items to take.
     * @throws IllegalArgumentException if {@code count} is less than zero
     * @since 6.0
     */
    ReversibleIterable<T> take(int count);

    /**
     * Returns the initial elements that satisfy the Predicate. Short circuits at the first element which does not
     * satisfy the Predicate.
     */
    ReversibleIterable<T> takeWhile(Predicate<? super T> predicate);

    /**
     * Returns an iterable after skipping the first {@code count} elements
     * or an empty iterable if the {@code count} is greater than the length of the iterable.
     *
     * @param count the number of items to drop.
     * @throws IllegalArgumentException if {@code count} is less than zero
     * @since 6.0
     */
    ReversibleIterable<T> drop(int count);

    /**
     * Returns the final elements that do not satisfy the Predicate. Short circuits at the first element which does
     * satisfy the Predicate.
     */
    ReversibleIterable<T> dropWhile(Predicate<? super T> predicate);

    PartitionReversibleIterable<T> partitionWhile(Predicate<? super T> predicate);

    ReversibleIterable<T> distinct();

    ReversibleIterable<T> select(Predicate<? super T> predicate);

    <P> ReversibleIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    ReversibleIterable<T> reject(Predicate<? super T> predicate);

    <P> ReversibleIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionReversibleIterable<T> partition(Predicate<? super T> predicate);

    <P> PartitionReversibleIterable<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> ReversibleIterable<S> selectInstancesOf(Class<S> clazz);

    <V> ReversibleIterable<V> collect(Function<? super T, ? extends V> function);

    <P, V> ReversibleIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ReversibleIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ReversibleIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    ReversibleBooleanIterable collectBoolean(BooleanFunction<? super T> booleanFunction);

    ReversibleByteIterable collectByte(ByteFunction<? super T> byteFunction);

    ReversibleCharIterable collectChar(CharFunction<? super T> charFunction);

    ReversibleDoubleIterable collectDouble(DoubleFunction<? super T> doubleFunction);

    ReversibleFloatIterable collectFloat(FloatFunction<? super T> floatFunction);

    ReversibleIntIterable collectInt(IntFunction<? super T> intFunction);

    ReversibleLongIterable collectLong(LongFunction<? super T> longFunction);

    ReversibleShortIterable collectShort(ShortFunction<? super T> shortFunction);

    <V> ReversibleIterableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ReversibleIterableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <S> ReversibleIterable<Pair<T, S>> zip(Iterable<S> that);

    ReversibleIterable<Pair<T, Integer>> zipWithIndex();
}
