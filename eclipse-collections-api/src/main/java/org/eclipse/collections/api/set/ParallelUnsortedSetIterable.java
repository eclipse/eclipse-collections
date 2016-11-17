/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.set;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.multimap.set.UnsortedSetMultimap;

/**
 * A ParallelIterable is RichIterable which will defer evaluation for certain methods like select, reject, collect, etc.
 * Any methods that do not return a ParallelIterable when called will cause evaluation to be forced. Evaluation occurs
 * in parallel. All code blocks passed in must be stateless or thread-safe.
 *
 * @since 5.0
 */
@Beta
public interface ParallelUnsortedSetIterable<T> extends ParallelSetIterable<T>
{
    @Override
    ParallelUnsortedSetIterable<T> asUnique();

    /**
     * Creates a parallel iterable for selecting elements from the current iterable.
     */
    @Override
    ParallelUnsortedSetIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> ParallelUnsortedSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    /**
     * Creates a parallel iterable for rejecting elements from the current iterable.
     */
    @Override
    ParallelUnsortedSetIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ParallelUnsortedSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ParallelUnsortedSetIterable<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> UnsortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> UnsortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

//    /**
//     * Returns a parallel BooleanIterable which will transform the underlying iterable data to boolean values based on the booleanFunction.
//     */
//    ParallelBooleanIterable collectBoolean(BooleanFunction<? super T> booleanFunction);
//
//    /**
//     * Returns a parallel ByteIterable which will transform the underlying iterable data to byte values based on the byteFunction.
//     */
//    ParallelByteIterable collectByte(ByteFunction<? super T> byteFunction);
//
//    /**
//     * Returns a parallel CharIterable which will transform the underlying iterable data to char values based on the charFunction.
//     */
//    ParallelCharIterable collectChar(CharFunction<? super T> charFunction);
//
//    /**
//     * Returns a parallel DoubleIterable which will transform the underlying iterable data to double values based on the doubleFunction.
//     */
//    ParallelDoubleIterable collectDouble(DoubleFunction<? super T> doubleFunction);
//
//    /**
//     * Returns a parallel FloatIterable which will transform the underlying iterable data to float values based on the floatFunction.
//     */
//    ParallelFloatIterable collectFloat(FloatFunction<? super T> floatFunction);
//
//    /**
//     * Returns a parallel IntIterable which will transform the underlying iterable data to int values based on the intFunction.
//     */
//    ParallelIntIterable collectInt(IntFunction<? super T> intFunction);
//
//    /**
//     * Returns a parallel LongIterable which will transform the underlying iterable data to long values based on the longFunction.
//     */
//    ParallelLongIterable collectLong(LongFunction<? super T> longFunction);
//
//    /**
//     * Returns a parallel ShortIterable which will transform the underlying iterable data to short values based on the shortFunction.
//     */
//    ParallelShortIterable collectShort(ShortFunction<? super T> shortFunction);
}
