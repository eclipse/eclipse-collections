/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api;

import java.util.Comparator;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;

/**
 * A ParallelIterable is RichIterable which will defer evaluation for certain methods like select, reject, collect, etc.
 * Any methods that do not return a ParallelIterable when called will cause evaluation to be forced. Evaluation occurs
 * in parallel. All code blocks passed in must be stateless or thread-safe.
 *
 * @since 5.0
 */
@Beta
public interface ParallelIterable<T>
{
    ParallelIterable<T> asUnique();

    /**
     * Creates a parallel iterable for selecting elements from the current iterable.
     */
    ParallelIterable<T> select(Predicate<? super T> predicate);

    <P> ParallelIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    <S> ParallelIterable<S> selectInstancesOf(Class<S> clazz);

    /**
     * Creates a parallel iterable for rejecting elements from the current iterable.
     */
    ParallelIterable<T> reject(Predicate<? super T> predicate);

    <P> ParallelIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    /**
     * Creates a parallel iterable for collecting elements from the current iterable.
     */
    <V> ParallelIterable<V> collect(Function<? super T, ? extends V> function);

    <P, V> ParallelIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    /**
     * Creates a parallel iterable for selecting and collecting elements from the current iterable.
     */
    <V> ParallelIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    /**
     * Creates a parallel flattening iterable for the current iterable.
     */
    <V> ParallelIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    void forEach(Procedure<? super T> procedure);

    <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter);

    T detect(Predicate<? super T> predicate);

    <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function);

    <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function);

    int count(Predicate<? super T> predicate);

    <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter);

    boolean anySatisfy(Predicate<? super T> predicate);

    <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter);

    boolean allSatisfy(Predicate<? super T> predicate);

    <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter);

    boolean noneSatisfy(Predicate<? super T> predicate);

    <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter);

    MutableList<T> toList();

    default MutableList<T> toSortedList()
    {
        return this.toList().toSortedList();
    }

    MutableList<T> toSortedList(Comparator<? super T> comparator);

    <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function);

    MutableSet<T> toSet();

    MutableSortedSet<T> toSortedSet();

    MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator);

    <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function);

    MutableBag<T> toBag();

    MutableSortedBag<T> toSortedBag();

    MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator);

    <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function);

    <NK, NV> MutableMap<NK, NV> toMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction);

    <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction);

    <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator, Function<? super T, ? extends NK> keyFunction, Function<? super T, ? extends NV> valueFunction);

    default Object[] toArray()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".toArray() not implemented yet");
    }

    <T1> T1[] toArray(T1[] target);

    T min(Comparator<? super T> comparator);

    T max(Comparator<? super T> comparator);

    T min();

    T max();

    <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function);

    <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function);

    /**
     * Returns the final long result of evaluating function for each element of the iterable in parallel
     * and adding the results together.
     *
     * @since 6.0
     */
    long sumOfInt(IntFunction<? super T> function);

    /**
     * Returns the final double result of evaluating function for each element of the iterable in parallel
     * and adding the results together. It uses Kahan summation algorithm to reduce numerical error.
     *
     * @since 6.0
     */
    double sumOfFloat(FloatFunction<? super T> function);

    /**
     * Returns the final long result of evaluating function for each element of the iterable in parallel
     * and adding the results together.
     *
     * @since 6.0
     */
    long sumOfLong(LongFunction<? super T> function);

    /**
     * Returns the final double result of evaluating function for each element of the iterable in parallel
     * and adding the results together. It uses Kahan summation algorithm to reduce numerical error.
     *
     * @since 6.0
     */
    double sumOfDouble(DoubleFunction<? super T> function);

    default String makeString()
    {
        return this.makeString(", ");
    }

    default String makeString(String separator)
    {
        return this.makeString("", separator, "");
    }

    default String makeString(String start, String separator, String end)
    {
        Appendable stringBuilder = new StringBuilder();
        this.appendString(stringBuilder, start, separator, end);
        return stringBuilder.toString();
    }

    default String makeString(Function<? super T, Object> function, String start, String separator, String end)
    {
        return this.collect(function).makeString(start, separator, end);
    }

    default void appendString(Appendable appendable)
    {
        this.appendString(appendable, ", ");
    }

    default void appendString(Appendable appendable, String separator)
    {
        this.appendString(appendable, "", separator, "");
    }

    void appendString(Appendable appendable, String start, String separator, String end);

    <V> Multimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> Multimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    <V> MapIterable<V, T> groupByUniqueKey(Function<? super T, ? extends V> function);

    <K, V> MapIterable<K, V> aggregateInPlaceBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Procedure2<? super V, ? super T> mutatingAggregator);

    <K, V> MapIterable<K, V> aggregateBy(Function<? super T, ? extends K> groupBy, Function0<? extends V> zeroValueFactory, Function2<? super V, ? super T, ? extends V> nonMutatingAggregator);
}
