/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.bag;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ObjectIntToObjectFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.predicate.primitive.IntPredicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.partition.bag.PartitionBag;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;

/**
 * A Bag is a Collection whose elements are unordered and may contain duplicate entries.  It varies from
 * MutableCollection in that it adds a protocol for determining, adding, and removing the number of occurrences for an
 * item.
 *
 * @since 1.0
 */
public interface Bag<T>
        extends RichIterable<T>
{
    /**
     * Two bags<tt>b1</tt> and <tt>b2</tt> are equal if <tt>m1.toMapOfItemToCount().equals(m2.toMapOfItemToCount())</tt>.
     *
     * @see Map#equals(Object)
     */
    @Override
    boolean equals(Object object);

    /**
     * Returns the hash code for this Bag, defined as <tt>this.{@link #toMapOfItemToCount()}.hashCode()</tt>.
     *
     * @see Map#hashCode()
     */
    @Override
    int hashCode();

    @Override
    Bag<T> tap(Procedure<? super T> procedure);

    @Override
    Bag<T> select(Predicate<? super T> predicate);

    @Override
    <P> Bag<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    Bag<T> reject(Predicate<? super T> predicate);

    @Override
    <P> Bag<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionBag<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionBag<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> Bag<S> selectInstancesOf(Class<S> clazz);

    @Override
    <V> BagMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    @Override
    <V> BagMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    @Override
    SetIterable<Pair<T, Integer>> zipWithIndex();

    /**
     * For each distinct item, with the number of occurrences, execute the specified procedure.
     */
    void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure);

    /**
     * The occurrences of a distinct item in the bag.
     */
    int occurrencesOf(Object item);

    /**
     * Returns all elements of the bag that have a number of occurrences that satisfy the predicate.
     *
     * @since 3.0
     */
    Bag<T> selectByOccurrences(IntPredicate predicate);

    /**
     * Returns all elements of the bag that have more than one occurrence.
     *
     * @since 9.2
     */
    default Bag<T> selectDuplicates()
    {
        return this.selectByOccurrences(occurrences -> occurrences > 1);
    }

    /**
     * Returns a set containing all elements of the bag that have exactly one occurrence.
     *
     * @since 9.2
     */
    default SetIterable<T> selectUnique()
    {
        throw new UnsupportedOperationException("Adding default implementation so as to not break compatibility");
    }

    /**
     * Returns the {@code count} most frequently occurring items.
     *
     * In the event of a tie, all of the items with the number of occurrences that match the occurrences of the last
     * item will be returned.
     *
     * @since 6.0
     */
    ListIterable<ObjectIntPair<T>> topOccurrences(int count);

    /**
     * Returns the {@code count} least frequently occurring items.
     *
     * In the event of a tie, all of the items with the number of occurrences that match the occurrences of the last
     * item will be returned.
     *
     * @since 6.0
     */
    ListIterable<ObjectIntPair<T>> bottomOccurrences(int count);

    /**
     * The size of the Bag when counting only distinct elements.
     */
    int sizeDistinct();

    /**
     * Converts the Bag to a Map of the Item type to its count as an Integer.
     */
    MapIterable<T, Integer> toMapOfItemToCount();

    /**
     * Returns a string representation of this bag. The string representation consists of a list of element-count mappings.
     * <p>
     * <pre>
     * Assert.assertEquals("{1=1, 2=2, 3=3}", Bags.mutable.with(1, 2, 2, 3, 3, 3).toStringOfItemToCount());
     * </pre>
     * This string representation is similar to {@link java.util.AbstractMap#toString()}, not {@link RichIterable#toString()},
     * whereas the <code>toString()</code> implementation for a Bag is consistent with {@link RichIterable#toString()}.
     *
     * @return a string representation of this bag
     * @since 3.0
     */
    String toStringOfItemToCount();

    ImmutableBagIterable<T> toImmutable();

    /**
     * @since 8.0
     */
    @Override
    default IntSummaryStatistics summarizeInt(IntFunction<? super T> function)
    {
        IntSummaryStatistics stats = new IntSummaryStatistics();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            int result = function.intValueOf(each);
            for (int i = 0; i < occurrences; i++)
            {
                stats.accept(result);
            }
        });
        return stats;
    }

    /**
     * @since 8.0
     */
    @Override
    default DoubleSummaryStatistics summarizeFloat(FloatFunction<? super T> function)
    {
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            float result = function.floatValueOf(each);
            for (int i = 0; i < occurrences; i++)
            {
                stats.accept(result);
            }
        });
        return stats;
    }

    /**
     * @since 8.0
     */
    @Override
    default LongSummaryStatistics summarizeLong(LongFunction<? super T> function)
    {
        LongSummaryStatistics stats = new LongSummaryStatistics();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            long result = function.longValueOf(each);
            for (int i = 0; i < occurrences; i++)
            {
                stats.accept(result);
            }
        });
        return stats;
    }

    /**
     * @since 8.0
     */
    @Override
    default DoubleSummaryStatistics summarizeDouble(DoubleFunction<? super T> function)
    {
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            double result = function.doubleValueOf(each);
            for (int i = 0; i < occurrences; i++)
            {
                stats.accept(result);
            }
        });
        return stats;
    }

    /**
     * This method produces the equivalent result as {@link Stream#collect(Collector)}.
     *
     * @since 8.0
     */
    @Override
    default <R, A> R reduceInPlace(Collector<? super T, A, R> collector)
    {
        A mutableResult = collector.supplier().get();
        BiConsumer<A, ? super T> accumulator = collector.accumulator();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            for (int i = 0; i < occurrences; i++)
            {
                accumulator.accept(mutableResult, each);
            }
        });
        return collector.finisher().apply(mutableResult);
    }

    /**
     * This method produces the equivalent result as {@link Stream#collect(Supplier, BiConsumer, BiConsumer)}.
     *
     * @since 8.0
     */
    @Override
    default <R> R reduceInPlace(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator)
    {
        R mutableResult = supplier.get();
        this.forEachWithOccurrences((each, occurrences) ->
        {
            for (int i = 0; i < occurrences; i++)
            {
                accumulator.accept(mutableResult, each);
            }
        });
        return mutableResult;
    }

    /**
     * Iterates over the unique elements and their occurrences and collects the results of applying the
     * specified function into the target collection.
     *
     * @since 9.1.
     */
    default <V, R extends Collection<V>> R collectWithOccurrences(
            ObjectIntToObjectFunction<? super T, ? extends V> function,
            R target)
    {
        this.forEachWithOccurrences((each, occurrences) -> target.add(function.valueOf(each, occurrences)));
        return target;
    }
}
