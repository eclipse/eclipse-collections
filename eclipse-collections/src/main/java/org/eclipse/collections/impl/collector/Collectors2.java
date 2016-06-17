/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
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
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.partition.PartitionMutableCollection;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

/**
 * A set of Collectors for Eclipse Collections types and algorithms.
 * Includes converters to{Immutable}{Sorted}{List/Set/Bag/Map/BiMap/Multimap}.
 *
 * @since 8.0
 */
public final class Collectors2
{
    private static final Collector.Characteristics[] EMPTY_CHARACTERISTICS = {};
    private static final Collector<?, ?, String> DEFAULT_MAKE_STRING = Collectors2.makeString(", ");

    private Collectors2()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T> Collector<T, ?, String> makeString()
    {
        return (Collector<T, ?, String>) DEFAULT_MAKE_STRING;
    }

    public static <T> Collector<T, ?, String> makeString(CharSequence separator)
    {
        return Collectors2.makeString("", separator, "");
    }

    public static <T> Collector<T, ?, String> makeString(CharSequence start, CharSequence separator, CharSequence end)
    {
        return Collector.of(
                () -> new StringJoiner(separator, start, end),
                (joiner, each) -> joiner.add(String.valueOf(each)),
                StringJoiner::merge,
                StringJoiner::toString,
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, MutableList<T>> toList()
    {
        return Collector.of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, ImmutableList<T>> toImmutableList()
    {
        return Collector.<T, MutableList<T>, ImmutableList<T>>of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                MutableList::toImmutable,
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, MutableSet<T>> toSet()
    {
        return Collector.of(
                Sets.mutable::empty,
                MutableSet::add,
                MutableSet::withAll,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, ImmutableSet<T>> toImmutableSet()
    {
        return Collector.<T, MutableSet<T>, ImmutableSet<T>>of(
                Sets.mutable::empty,
                MutableSet::add,
                MutableSet::withAll,
                MutableSet::toImmutable,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, MutableSortedSet<T>> toSortedSet()
    {
        return Collector.of(
                SortedSets.mutable::empty,
                MutableSortedSet::add,
                MutableSortedSet::withAll,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, ImmutableSortedSet<T>> toImmutableSortedSet()
    {
        return Collector.<T, MutableSortedSet<T>, ImmutableSortedSet<T>>of(
                SortedSets.mutable::empty,
                MutableSortedSet::add,
                MutableSortedSet::withAll,
                MutableSortedSet::toImmutable,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, MutableSortedSet<T>> toSortedSet(Comparator<? super T> comparator)
    {
        return Collector.of(
                () -> SortedSets.mutable.with(comparator),
                MutableSortedSet::add,
                MutableSortedSet::withAll,
                Collector.Characteristics.UNORDERED);
    }

    public static <T, V extends Comparable<? super V>> Collector<T, ?, MutableSortedSet<T>> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return Collectors2.toSortedSet(Comparators.byFunction(function));
    }

    public static <T> Collector<T, ?, ImmutableSortedSet<T>> toImmutableSortedSet(Comparator<? super T> comparator)
    {
        return Collector.<T, MutableSortedSet<T>, ImmutableSortedSet<T>>of(
                () -> SortedSets.mutable.with(comparator),
                MutableSortedSet::add,
                MutableSortedSet::withAll,
                MutableSortedSet::toImmutable,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, MutableBag<T>> toBag()
    {
        return Collector.of(
                Bags.mutable::empty,
                MutableBag::add,
                MutableBag::withAll,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, ImmutableBag<T>> toImmutableBag()
    {
        return Collector.<T, MutableBag<T>, ImmutableBag<T>>of(
                Bags.mutable::empty,
                MutableBag::add,
                MutableBag::withAll,
                MutableBag::toImmutable,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, MutableList<T>> toSortedList()
    {
        return Collector.<T, MutableList<T>, MutableList<T>>of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                MutableList::sortThis,
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, ImmutableList<T>> toImmutableSortedList()
    {
        return Collector.<T, MutableList<T>, ImmutableList<T>>of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                list -> list.sortThis().toImmutable(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, MutableList<T>> toSortedList(Comparator<? super T> comparator)
    {
        return Collector.<T, MutableList<T>, MutableList<T>>of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                list -> list.sortThis(comparator),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, V extends Comparable<? super V>> Collector<T, ?, MutableList<T>> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return Collectors2.toSortedList(Comparators.byFunction(function));
    }

    public static <T> Collector<T, ?, ImmutableList<T>> toImmutableSortedList(Comparator<? super T> comparator)
    {
        return Collector.<T, MutableList<T>, ImmutableList<T>>of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                list -> list.sortThis(comparator).toImmutable(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, MutableSortedBag<T>> toSortedBag()
    {
        return Collector.of(
                SortedBags.mutable::empty,
                MutableSortedBag::add,
                MutableSortedBag::withAll,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, ImmutableSortedBag<T>> toImmutableSortedBag()
    {
        return Collector.<T, MutableSortedBag<T>, ImmutableSortedBag<T>>of(
                SortedBags.mutable::empty,
                MutableSortedBag::add,
                MutableSortedBag::withAll,
                MutableSortedBag::toImmutable,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, MutableSortedBag<T>> toSortedBag(Comparator<? super T> comparator)
    {
        return Collector.of(
                () -> SortedBags.mutable.with(comparator),
                MutableSortedBag::add,
                MutableSortedBag::withAll,
                Collector.Characteristics.UNORDERED);
    }

    public static <T, V extends Comparable<? super V>> Collector<T, ?, MutableSortedBag<T>> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return Collectors2.toSortedBag(Comparators.byFunction(function));
    }

    public static <T> Collector<T, ?, ImmutableSortedBag<T>> toImmutableSortedBag(Comparator<? super T> comparator)
    {
        return Collector.<T, MutableSortedBag<T>, ImmutableSortedBag<T>>of(
                () -> SortedBags.mutable.with(comparator),
                MutableSortedBag::add,
                MutableSortedBag::withAll,
                MutableSortedBag::toImmutable,
                Collector.Characteristics.UNORDERED);
    }

    public static <T> Collector<T, ?, MutableStack<T>> toStack()
    {
        return Collector.<T, MutableList<T>, MutableStack<T>>of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                Stacks.mutable::ofAll,
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, ImmutableStack<T>> toImmutableStack()
    {
        return Collector.<T, MutableList<T>, ImmutableStack<T>>of(
                Lists.mutable::empty,
                MutableList::add,
                MutableList::withAll,
                Stacks.immutable::ofAll,
                EMPTY_CHARACTERISTICS);
    }

    public static <T, K, V> Collector<T, ?, MutableBiMap<K, V>> toBiMap(
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collector.of(
                BiMaps.mutable::empty,
                (mbm, each) -> mbm.put(keyFunction.valueOf(each), valueFunction.valueOf(each)),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, K, V> Collector<T, ?, ImmutableBiMap<K, V>> toImmutableBiMap(
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collector.<T, MutableBiMap<K, V>, ImmutableBiMap<K, V>>of(
                BiMaps.mutable::empty,
                (mbm, each) -> mbm.put(keyFunction.valueOf(each), valueFunction.valueOf(each)),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                MutableBiMap::toImmutable,
                EMPTY_CHARACTERISTICS);
    }

    public static <T, K, V> Collector<T, ?, MutableMap<K, V>> toMap(
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collector.of(
                Maps.mutable::empty,
                (map, each) -> map.put(keyFunction.valueOf(each), valueFunction.valueOf(each)),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
            Function<? super T, ? extends K> keyFunction,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collector.<T, MutableMap<K, V>, ImmutableMap<K, V>>of(
                Maps.mutable::empty,
                (map, each) -> map.put(keyFunction.valueOf(each), valueFunction.valueOf(each)),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                MutableMap::toImmutable,
                EMPTY_CHARACTERISTICS);
    }

    private static <T, K, R extends MutableMultimap<K, T>> Collector<T, ?, R> groupBy(
            Function<? super T, ? extends K> groupBy,
            Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (map, each) -> map.put(groupBy.valueOf(each), each),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                EMPTY_CHARACTERISTICS);
    }

    private static <T, K, A extends MutableMultimap<K, T>, R extends ImmutableMultimap<K, T>> Collector<T, ?, R> groupByImmutable(
            Function<? super T, ? extends K> groupBy,
            Supplier<A> supplier,
            java.util.function.Function<A, R> finisher)
    {
        return Collector.of(
                supplier,
                (map, each) -> map.put(groupBy.valueOf(each), each),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                finisher,
                EMPTY_CHARACTERISTICS);
    }

    private static <T, K, V, R extends MutableMultimap<K, V>> Collector<T, ?, R> groupBy(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction,
            Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (map, each) -> map.put(groupBy.valueOf(each), valueFunction.valueOf(each)),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                EMPTY_CHARACTERISTICS);
    }

    private static <T, K, V, A extends MutableMultimap<K, V>, R extends ImmutableMultimap<K, V>> Collector<T, ?, R> groupByImmutable(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction,
            Supplier<A> supplier,
            java.util.function.Function<A, R> finisher)
    {
        return Collector.of(
                supplier,
                (map, each) -> map.put(groupBy.valueOf(each), valueFunction.valueOf(each)),
                (r1, r2) ->
                {
                    r1.putAll(r2);
                    return r1;
                },
                finisher,
                EMPTY_CHARACTERISTICS);
    }

    public static <T, K> Collector<T, ?, MutableListMultimap<K, T>> toListMultimap(
            Function<? super T, ? extends K> groupBy)
    {
        return Collectors2.groupBy(groupBy, Multimaps.mutable.list::empty);
    }

    public static <T, K, V> Collector<T, ?, MutableListMultimap<K, V>> toListMultimap(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collectors2.groupBy(groupBy, valueFunction, Multimaps.mutable.list::empty);
    }

    public static <T, K> Collector<T, ?, MutableSetMultimap<K, T>> toSetMultimap(
            Function<? super T, ? extends K> groupBy)
    {
        return Collectors2.groupBy(groupBy, Multimaps.mutable.set::empty);
    }

    public static <T, K, V> Collector<T, ?, MutableSetMultimap<K, V>> toSetMultimap(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collectors2.groupBy(groupBy, valueFunction, Multimaps.mutable.set::empty);
    }

    public static <T, K> Collector<T, ?, MutableBagMultimap<K, T>> toBagMultimap(
            Function<? super T, ? extends K> groupBy)
    {
        return Collectors2.groupBy(groupBy, Multimaps.mutable.bag::empty);
    }

    public static <T, K, V> Collector<T, ?, MutableBagMultimap<K, V>> toBagMultimap(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collectors2.groupBy(groupBy, valueFunction, Multimaps.mutable.bag::empty);
    }

    public static <T, K> Collector<T, ?, ImmutableListMultimap<K, T>> toImmutableListMultimap(
            Function<? super T, ? extends K> groupBy)
    {
        return Collectors2.groupByImmutable(groupBy, Multimaps.mutable.list::empty, MutableListMultimap::toImmutable);
    }

    public static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> toImmutableListMultimap(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collectors2.groupByImmutable(groupBy, valueFunction, Multimaps.mutable.list::empty, MutableListMultimap::toImmutable);
    }

    public static <T, K> Collector<T, ?, ImmutableSetMultimap<K, T>> toImmutableSetMultimap(
            Function<? super T, ? extends K> groupBy)
    {
        return Collectors2.groupByImmutable(groupBy, Multimaps.mutable.set::empty, MutableSetMultimap::toImmutable);
    }

    public static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> toImmutableSetMultimap(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collectors2.groupByImmutable(groupBy, valueFunction, Multimaps.mutable.set::empty, MutableSetMultimap::toImmutable);
    }

    public static <T, K> Collector<T, ?, ImmutableBagMultimap<K, T>> toImmutableBagMultimap(
            Function<? super T, ? extends K> groupBy)
    {
        return Collectors2.groupByImmutable(groupBy, Multimaps.mutable.bag::empty, MutableBagMultimap::toImmutable);
    }

    public static <T, K, V> Collector<T, ?, ImmutableBagMultimap<K, V>> toImmutableBagMultimap(
            Function<? super T, ? extends K> groupBy,
            Function<? super T, ? extends V> valueFunction)
    {
        return Collectors2.groupByImmutable(groupBy, valueFunction, Multimaps.mutable.bag::empty, MutableBagMultimap::toImmutable);
    }

    public static <T> Collector<T, ?, MutableList<MutableList<T>>> chunk(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }
        return Collector.of(
                Lists.mutable::empty,
                (MutableList<MutableList<T>> batches, T each) ->
                {
                    MutableList<T> batch = batches.getLast();
                    if (batch == null || batch.size() == size)
                    {
                        batch = Lists.mutable.empty();
                        batches.add(batch);
                    }
                    batch.add(each);
                },
                MutableList::withAll,
                EMPTY_CHARACTERISTICS);
    }

    public static <T, S> Collector<T, ?, MutableList<Pair<T, S>>> zip(Iterable<S> other)
    {
        Iterator<S> iterator = other.iterator();
        return Collector.of(
                Lists.mutable::empty,
                (list, each) ->
                {
                    if (iterator.hasNext())
                    {
                        list.add(Tuples.pair(each, iterator.next()));
                    }
                },
                (l, r) ->
                {
                    throw new UnsupportedOperationException("Zip not supported in parallel.");
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T> Collector<T, ?, MutableList<ObjectIntPair<T>>> zipWithIndex()
    {
        AtomicInteger index = new AtomicInteger(0);
        return Collector.of(
                Lists.mutable::empty,
                (list, each) -> list.add(PrimitiveTuples.pair(each, index.getAndAdd(1))),
                (l, r) ->
                {
                    throw new UnsupportedOperationException("ZipWithIndex not supported in parallel.");
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, V> Collector<T, ?, MutableObjectLongMap<V>> sumByInt(
            Function<? super T, ? extends V> groupBy,
            IntFunction<? super T> function)
    {
        Function2<MutableObjectLongMap<V>, T, MutableObjectLongMap<V>> accumulator =
                PrimitiveFunctions.sumByIntFunction(groupBy, function);
        return Collector.of(
                ObjectLongHashMap::newMap,
                accumulator::value,
                (map1, map2) ->
                {
                    map2.forEachKeyValue(map1::addToValue);
                    return map1;
                },
                Collector.Characteristics.UNORDERED);
    }

    public static <T, V> Collector<T, ?, MutableObjectLongMap<V>> sumByLong(
            Function<? super T, ? extends V> groupBy,
            LongFunction<? super T> function)
    {
        Function2<MutableObjectLongMap<V>, T, MutableObjectLongMap<V>> accumulator =
                PrimitiveFunctions.sumByLongFunction(groupBy, function);
        return Collector.of(
                ObjectLongHashMap::newMap,
                accumulator::value,
                (map1, map2) ->
                {
                    map2.forEachKeyValue(map1::addToValue);
                    return map1;
                },
                Collector.Characteristics.UNORDERED);
    }

    public static <T, V> Collector<T, ?, MutableObjectDoubleMap<V>> sumByFloat(
            Function<? super T, ? extends V> groupBy,
            FloatFunction<? super T> function)
    {
        Function2<MutableObjectDoubleMap<V>, T, MutableObjectDoubleMap<V>> accumulator =
                PrimitiveFunctions.sumByFloatFunction(groupBy, function);
        return Collector.of(
                ObjectDoubleHashMap::newMap,
                accumulator::value,
                (map1, map2) ->
                {
                    map2.forEachKeyValue(map1::addToValue);
                    return map1;
                },
                Collector.Characteristics.UNORDERED);
    }

    public static <T, V> Collector<T, ?, MutableObjectDoubleMap<V>> sumByDouble(
            Function<? super T, ? extends V> groupBy,
            DoubleFunction<? super T> function)
    {
        Function2<MutableObjectDoubleMap<V>, T, MutableObjectDoubleMap<V>> accumulator =
                PrimitiveFunctions.sumByDoubleFunction(groupBy, function);
        return Collector.of(
                ObjectDoubleHashMap::newMap,
                accumulator::value,
                (map1, map2) ->
                {
                    map2.forEachKeyValue(map1::addToValue);
                    return map1;
                },
                Collector.Characteristics.UNORDERED);
    }

    public static <T, R extends Collection<T>> Collector<T, ?, R> select(Predicate<? super T> predicate, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) ->
                {
                    if (predicate.accept(each))
                    {
                        collection.add(each);
                    }
                },
                Collectors2.mergeCollections(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, P, R extends Collection<T>> Collector<T, ?, R> selectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) ->
                {
                    if (predicate.accept(each, parameter))
                    {
                        collection.add(each);
                    }
                },
                Collectors2.mergeCollections(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends Collection<T>> Collector<T, ?, R> reject(Predicate<? super T> predicate, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) ->
                {
                    if (!predicate.accept(each))
                    {
                        collection.add(each);
                    }
                },
                Collectors2.mergeCollections(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, P, R extends Collection<T>> Collector<T, ?, R> rejectWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) ->
                {
                    if (!predicate.accept(each, parameter))
                    {
                        collection.add(each);
                    }
                },
                Collectors2.mergeCollections(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends PartitionMutableCollection<T>> Collector<T, ?, R> partition(
            Predicate<? super T> predicate,
            Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (partition, each) ->
                {
                    MutableCollection<T> bucket = predicate.accept(each) ? partition.getSelected() : partition.getRejected();
                    bucket.add(each);
                },
                Collectors2.mergePartitions(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, P, R extends PartitionMutableCollection<T>> Collector<T, ?, R> partitionWith(
            Predicate2<? super T, ? super P> predicate,
            P parameter,
            Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (partition, each) ->
                {
                    MutableCollection<T> bucket =
                            predicate.accept(each, parameter) ? partition.getSelected() : partition.getRejected();
                    bucket.add(each);
                },
                Collectors2.mergePartitions(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, V, R extends Collection<V>> Collector<T, ?, R> collect(
            Function<? super T, ? extends V> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.valueOf(each)),
                Collectors2.mergeCollections(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, P, V, R extends Collection<V>> Collector<T, ?, R> collectWith(
            Function2<? super T, ? super P, ? extends V> function,
            P parameter,
            Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.value(each, parameter)),
                Collectors2.mergeCollections(),
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableBooleanCollection> Collector<T, ?, R> collectBoolean(
            BooleanFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.booleanValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableByteCollection> Collector<T, ?, R> collectByte(
            ByteFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.byteValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableCharCollection> Collector<T, ?, R> collectChar(
            CharFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.charValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableShortCollection> Collector<T, ?, R> collectShort(
            ShortFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.shortValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableIntCollection> Collector<T, ?, R> collectInt(
            IntFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.intValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableFloatCollection> Collector<T, ?, R> collectFloat(
            FloatFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.floatValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableLongCollection> Collector<T, ?, R> collectLong(
            LongFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.longValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    public static <T, R extends MutableDoubleCollection> Collector<T, ?, R> collectDouble(
            DoubleFunction<? super T> function, Supplier<R> supplier)
    {
        return Collector.of(
                supplier,
                (collection, each) -> collection.add(function.doubleValueOf(each)),
                (collection1, collection2) ->
                {
                    collection1.addAll(collection2);
                    return collection1;
                },
                EMPTY_CHARACTERISTICS);
    }

    private static <T, R extends Collection<T>> BinaryOperator<R> mergeCollections()
    {
        return (collection1, collection2) ->
        {
            collection1.addAll(collection2);
            return collection1;
        };
    }

    private static <T, R extends PartitionMutableCollection<T>> BinaryOperator<R> mergePartitions()
    {
        return (partition1, partition2) ->
        {
            partition1.getSelected().addAll(partition2.getSelected());
            partition1.getRejected().addAll(partition2.getRejected());
            return partition1;
        };
    }
}

