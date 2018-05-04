/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.set;

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
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.PartitionMutableSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.set.primitive.MutableByteSet;
import org.eclipse.collections.api.set.primitive.MutableCharSet;
import org.eclipse.collections.api.set.primitive.MutableDoubleSet;
import org.eclipse.collections.api.set.primitive.MutableFloatSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.eclipse.collections.api.set.primitive.MutableShortSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A MutableSet is an extension java.util.Set which provides methods matching the Smalltalk Collection protocol.
 */
public interface MutableSet<T>
        extends UnsortedSetIterable<T>, MutableSetIterable<T>, Cloneable
{
    @Override
    MutableSet<T> with(T element);

    @Override
    MutableSet<T> without(T element);

    @Override
    MutableSet<T> withAll(Iterable<? extends T> elements);

    @Override
    MutableSet<T> withoutAll(Iterable<? extends T> elements);

    @Override
    MutableSet<T> newEmpty();

    MutableSet<T> clone();

    @Override
    MutableSet<T> tap(Procedure<? super T> procedure);

    @Override
    MutableSet<T> select(Predicate<? super T> predicate);

    @Override
    <P> MutableSet<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    MutableSet<T> reject(Predicate<? super T> predicate);

    @Override
    <P> MutableSet<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionMutableSet<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionMutableSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> MutableSet<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> MutableSet<V> collect(Function<? super T, ? extends V> function);

    @Override
    MutableBooleanSet collectBoolean(BooleanFunction<? super T> booleanFunction);

    @Override
    MutableByteSet collectByte(ByteFunction<? super T> byteFunction);

    @Override
    MutableCharSet collectChar(CharFunction<? super T> charFunction);

    @Override
    MutableDoubleSet collectDouble(DoubleFunction<? super T> doubleFunction);

    @Override
    MutableFloatSet collectFloat(FloatFunction<? super T> floatFunction);

    @Override
    MutableIntSet collectInt(IntFunction<? super T> intFunction);

    @Override
    MutableLongSet collectLong(LongFunction<? super T> longFunction);

    @Override
    MutableShortSet collectShort(ShortFunction<? super T> shortFunction);

    @Override
    <P, V> MutableSet<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    @Override
    <V> MutableSet<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    @Override
    <V> MutableSet<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, V> MutableSet<V> flatCollectWith(Function2<? super T, ? super P, ? extends Iterable<V>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    /**
     * Returns an unmodifiable view of the set.
     *
     * @return an unmodifiable view of this set
     */
    @Override
    MutableSet<T> asUnmodifiable();

    @Override
    MutableSet<T> asSynchronized();

    /**
     * Returns an immutable copy of this set. If the set is immutable, it returns itself.
     */
    @Override
    ImmutableSet<T> toImmutable();

    @Override
    <V> MutableSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> MutableSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    <S> MutableSet<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    MutableSet<Pair<T, Integer>> zipWithIndex();

    @Override
    MutableSet<T> union(SetIterable<? extends T> set);

    @Override
    MutableSet<T> intersect(SetIterable<? extends T> set);

    @Override
    MutableSet<T> difference(SetIterable<? extends T> subtrahendSet);

    @Override
    MutableSet<T> symmetricDifference(SetIterable<? extends T> setB);

    @Override
    MutableSet<UnsortedSetIterable<T>> powerSet();
}
