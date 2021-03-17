/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.collection;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.collections.api.RichIterable;
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
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.primitive.ImmutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableByteCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableCharCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableIntCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableLongCollection;
import org.eclipse.collections.api.collection.primitive.ImmutableShortCollection;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.partition.PartitionImmutableCollection;
import org.eclipse.collections.api.tuple.Pair;

/**
 * ImmutableCollection is the common interface between ImmutableList, ImmutableSet and ImmutableBag.
 * The ImmutableCollection interface is "contractually immutable" in that it does not have any mutating
 * methods available on the public interface.
 */
public interface ImmutableCollection<T>
        extends RichIterable<T>
{
    /**
     * This method is similar to the {@code with} method in {@code MutableCollection}
     * with the difference that a new copy of this collection with the element appended will be returned.
     */
    ImmutableCollection<T> newWith(T element);

    /**
     * This method is similar to the {@code without} method in {@code MutableCollection}
     * with the difference that a new copy of this collection with the element removed will be returned.
     */
    ImmutableCollection<T> newWithout(T element);

    /**
     * This method is similar to the {@code withAll} method in {@code MutableCollection}
     * with the difference that a new copy of this collection with the elements appended will be returned.
     */
    ImmutableCollection<T> newWithAll(Iterable<? extends T> elements);

    /**
     * This method is similar to the {@code withoutAll} method in {@code MutableCollection}
     * with the difference that a new copy of this collection with the elements removed will be returned.
     */
    ImmutableCollection<T> newWithoutAll(Iterable<? extends T> elements);

    @Override
    ImmutableCollection<T> tap(Procedure<? super T> procedure);

    @Override
    ImmutableCollection<T> select(Predicate<? super T> predicate);

    @Override
    <P> ImmutableCollection<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    ImmutableCollection<T> reject(Predicate<? super T> predicate);

    @Override
    <P> ImmutableCollection<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionImmutableCollection<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionImmutableCollection<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> ImmutableCollection<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> ImmutableCollection<V> collect(Function<? super T, ? extends V> function);

    @Override
    ImmutableBooleanCollection collectBoolean(BooleanFunction<? super T> booleanFunction);

    @Override
    ImmutableByteCollection collectByte(ByteFunction<? super T> byteFunction);

    @Override
    ImmutableCharCollection collectChar(CharFunction<? super T> charFunction);

    @Override
    ImmutableDoubleCollection collectDouble(DoubleFunction<? super T> doubleFunction);

    @Override
    ImmutableFloatCollection collectFloat(FloatFunction<? super T> floatFunction);

    @Override
    ImmutableIntCollection collectInt(IntFunction<? super T> intFunction);

    @Override
    ImmutableLongCollection collectLong(LongFunction<? super T> longFunction);

    @Override
    ImmutableShortCollection collectShort(ShortFunction<? super T> shortFunction);

    @Override
    <P, V> ImmutableCollection<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    @Override
    <V> ImmutableCollection<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    @Override
    <V> ImmutableCollection<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    /**
     * @since 9.2
     */
    @Override
    default <P, V> ImmutableCollection<V> flatCollectWith(Function2<? super T, ? super P, ? extends Iterable<V>> function, P parameter)
    {
        return this.flatCollect(each -> function.apply(each, parameter));
    }

    @Override
    <V> ImmutableObjectLongMap<V> sumByInt(Function<? super T, ? extends V> groupBy, IntFunction<? super T> function);

    @Override
    <V> ImmutableObjectDoubleMap<V> sumByFloat(Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function);

    @Override
    <V> ImmutableObjectLongMap<V> sumByLong(Function<? super T, ? extends V> groupBy, LongFunction<? super T> function);

    @Override
    <V> ImmutableObjectDoubleMap<V> sumByDouble(Function<? super T, ? extends V> groupBy, DoubleFunction<? super T> function);

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
    <V> ImmutableMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> ImmutableMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    default <V> ImmutableMap<V, T> groupByUniqueKey(Function<? super T, ? extends V> function)
    {
        MutableMap<V, T> target = Maps.mutable.withInitialCapacity(this.size());
        return this.groupByUniqueKey(function, target).toImmutable();
    }

    @Override
    <S> ImmutableCollection<Pair<T, S>> zip(Iterable<S> that);

    @Override
    ImmutableCollection<Pair<T, Integer>> zipWithIndex();

    @Override
    default <K, V> ImmutableMap<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = Maps.mutable.empty();
        this.forEach(each ->
        {
            K key = groupBy.valueOf(each);
            V value = map.getIfAbsentPut(key, zeroValueFactory);
            mutatingAggregator.value(value, each);
        });
        return map.toImmutable();
    }

    @Override
    default <K, V> ImmutableMap<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = this.aggregateBy(
                groupBy,
                zeroValueFactory,
                nonMutatingAggregator,
                Maps.mutable.empty());
        return map.toImmutable();
    }

    /**
     * @since 9.0
     */
    default Stream<T> stream()
    {
        return StreamSupport.stream(this.spliterator(), false);
    }

    /**
     * @since 9.0
     */
    default Stream<T> parallelStream()
    {
        return StreamSupport.stream(this.spliterator(), true);
    }

    /**
     * @since 9.0
     */
    @Override
    default Spliterator<T> spliterator()
    {
        return Spliterators.spliterator(this.iterator(), (long) this.size(), 0);
    }

    /**
     * This can be overridden in most implementations to just return this.
     *
     * @since 9.0
     */
    Collection<T> castToCollection();
}
