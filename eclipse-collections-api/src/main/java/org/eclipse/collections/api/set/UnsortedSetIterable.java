/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.set;

import java.util.concurrent.ExecutorService;

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
import org.eclipse.collections.api.multimap.set.UnsortedSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.set.primitive.BooleanSet;
import org.eclipse.collections.api.set.primitive.ByteSet;
import org.eclipse.collections.api.set.primitive.CharSet;
import org.eclipse.collections.api.set.primitive.DoubleSet;
import org.eclipse.collections.api.set.primitive.FloatSet;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.set.primitive.LongSet;
import org.eclipse.collections.api.set.primitive.ShortSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * An iterable whose items are unique.
 */
public interface UnsortedSetIterable<T>
        extends SetIterable<T>
{
    /**
     * Returns the set whose members are all possible subsets of {@code this}. For example, the powerset of [1, 2] is
     * [[], [1], [2], [1, 2]].
     */
    UnsortedSetIterable<UnsortedSetIterable<T>> powerSet();

    <V> UnsortedSetIterable<V> collect(Function<? super T, ? extends V> function);

    BooleanSet collectBoolean(BooleanFunction<? super T> booleanFunction);

    ByteSet collectByte(ByteFunction<? super T> byteFunction);

    CharSet collectChar(CharFunction<? super T> charFunction);

    DoubleSet collectDouble(DoubleFunction<? super T> doubleFunction);

    FloatSet collectFloat(FloatFunction<? super T> floatFunction);

    IntSet collectInt(IntFunction<? super T> intFunction);

    LongSet collectLong(LongFunction<? super T> longFunction);

    ShortSet collectShort(ShortFunction<? super T> shortFunction);

    <P, V> UnsortedSetIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> UnsortedSetIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> UnsortedSetIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    <V> UnsortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> UnsortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    UnsortedSetIterable<T> union(SetIterable<? extends T> set);

    UnsortedSetIterable<T> intersect(SetIterable<? extends T> set);

    UnsortedSetIterable<T> difference(SetIterable<? extends T> subtrahendSet);

    UnsortedSetIterable<T> symmetricDifference(SetIterable<? extends T> setB);

    UnsortedSetIterable<T> select(Predicate<? super T> predicate);

    <P> UnsortedSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    UnsortedSetIterable<T> reject(Predicate<? super T> predicate);

    <P> UnsortedSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> UnsortedSetIterable<S> selectInstancesOf(Class<S> clazz);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Deprecated
    <S> UnsortedSetIterable<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Deprecated
    UnsortedSetIterable<Pair<T, Integer>> zipWithIndex();

    /**
     * Converts the UnsortedSetIterable to an immutable implementation. Returns this for immutable sets.
     *
     * @since 5.0
     */
    ImmutableSet<T> toImmutable();

    ParallelUnsortedSetIterable<T> asParallel(ExecutorService executorService, int batchSize);
}
