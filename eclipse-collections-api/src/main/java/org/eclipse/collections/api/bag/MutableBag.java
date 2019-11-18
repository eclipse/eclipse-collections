/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.bag;

import java.util.stream.Stream;

import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.bag.primitive.MutableByteBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.api.bag.primitive.MutableFloatBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.bag.primitive.MutableLongBag;
import org.eclipse.collections.api.bag.primitive.MutableShortBag;
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
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.bag.PartitionMutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A MutableBag is a Collection whose elements are unordered and may contain duplicate entries. It varies from
 * MutableCollection in that it adds a protocol for determining, adding, and removing the number of occurrences for an
 * item.
 *
 * @since 1.0
 */
public interface MutableBag<T>
        extends UnsortedBag<T>, MutableBagIterable<T>
{
    static <T> MutableBag<T> empty()
    {
        return Bags.mutable.empty();
    }

    static <T> MutableBag<T> of()
    {
        return Bags.mutable.of();
    }

    static <T> MutableBag<T> of(T... elements)
    {
        return Bags.mutable.of(elements);
    }

    static <T> MutableBag<T> ofAll(Iterable<? extends T> items)
    {
        return Bags.mutable.ofAll(items);
    }

    static <T> MutableBag<T> fromStream(Stream<? extends T> stream)
    {
        return Bags.mutable.fromStream(stream);
    }

    @Override
    MutableMap<T, Integer> toMapOfItemToCount();

    @Override
    MutableBag<T> selectByOccurrences(IntPredicate predicate);

    /**
     * @since 9.2
     */
    @Override
    default MutableBag<T> selectDuplicates()
    {
        return this.selectByOccurrences(occurrences -> occurrences > 1);
    }

    /**
     * @since 9.2
     */
    @Override
    default MutableSet<T> selectUnique()
    {
        MutableSet<T> result = Sets.mutable.empty();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            if (occurrences == 1)
            {
                result.add(each);
            }
        });
        return result;
    }

    @Override
    MutableBag<T> with(T element);

    @Override
    MutableBag<T> without(T element);

    @Override
    MutableBag<T> withAll(Iterable<? extends T> elements);

    @Override
    MutableBag<T> withoutAll(Iterable<? extends T> elements);

    @Override
    MutableBag<T> newEmpty();

    @Override
    MutableBag<T> asUnmodifiable();

    @Override
    MutableBag<T> asSynchronized();

    @Override
    PartitionMutableBag<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionMutableBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <V> MutableBagMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> MutableBagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @since 9.0
     */
    @Override
    default <V> MutableBag<V> countBy(Function<? super T, ? extends V> function)
    {
        return this.collect(function);
    }

    /**
     * @since 9.0
     */
    @Override
    default <V, P> MutableBag<V> countByWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.collectWith(function, parameter);
    }

    /**
     * @since 10.0.0
     */
    @Override
    default <V> MutableBag<V> countByEach(Function<? super T, ? extends Iterable<V>> function)
    {
        return this.flatCollect(function);
    }

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zip(Iterable)} instead.
     */
    @Override
    @Deprecated
    <S> MutableBag<Pair<T, S>> zip(Iterable<S> that);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    MutableSet<Pair<T, Integer>> zipWithIndex();

    @Override
    MutableBag<T> tap(Procedure<? super T> procedure);

    @Override
    MutableBag<T> select(Predicate<? super T> predicate);

    @Override
    <P> MutableBag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    MutableBag<T> reject(Predicate<? super T> predicate);

    @Override
    <P> MutableBag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> MutableBag<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> MutableBag<V> collect(Function<? super T, ? extends V> function);

    @Override
    MutableByteBag collectByte(ByteFunction<? super T> byteFunction);

    @Override
    MutableCharBag collectChar(CharFunction<? super T> charFunction);

    @Override
    MutableIntBag collectInt(IntFunction<? super T> intFunction);

    @Override
    MutableBooleanBag collectBoolean(BooleanFunction<? super T> booleanFunction);

    @Override
    MutableDoubleBag collectDouble(DoubleFunction<? super T> doubleFunction);

    @Override
    MutableFloatBag collectFloat(FloatFunction<? super T> floatFunction);

    @Override
    MutableLongBag collectLong(LongFunction<? super T> longFunction);

    @Override
    MutableShortBag collectShort(ShortFunction<? super T> shortFunction);

    @Override
    <P, V> MutableBag<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    @Override
    <V> MutableBag<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    @Override
    <V> MutableBag<V> collectWithOccurrences(ObjectIntToObjectFunction<? super T, ? extends V> function);

    @Override
    <V> MutableBag<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, V> MutableBag<V> flatCollectWith(Function2<? super T, ? super P, ? extends Iterable<V>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    /**
     * @since 8.0
     */
    @Override
    ImmutableBag<T> toImmutable();
}
