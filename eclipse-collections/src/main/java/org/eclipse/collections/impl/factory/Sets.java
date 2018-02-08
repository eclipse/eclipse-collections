/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.factory.set.FixedSizeSetFactory;
import org.eclipse.collections.api.factory.set.ImmutableSetFactory;
import org.eclipse.collections.api.factory.set.MutableSetFactory;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.set.fixed.FixedSizeSetFactoryImpl;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.mutable.MultiReaderMutableSetFactory;
import org.eclipse.collections.impl.set.mutable.MutableSetFactoryImpl;
import org.eclipse.collections.impl.set.mutable.SetAdapter;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.LazyIterate;

/**
 * Set algebra operations are available in this class as static utility.
 * <p>
 * Most operations are non-destructive, i.e. no input sets are modified during execution.
 * The exception is operations ending in "Into." These accept the target collection of
 * the final calculation as the first parameter.
 * <p>
 * Some effort is made to return a {@code SortedSet} if any input set is sorted, but
 * this is not guaranteed (e.g., this will not be the case for collections proxied by
 * Hibernate). When in doubt, specify the target collection explicitly with the "Into"
 * version.
 *
 * This class should be used to create instances of MutableSet, ImmutableSet and FixedSizeSet
 * <p>
 * Mutable Examples:
 *
 * <pre>
 * MutableSet&lt;String&gt; emptySet = Sets.mutable.empty();
 * MutableSet&lt;String&gt; setWith = Sets.mutable.with("a", "b", "c");
 * MutableSet&lt;String&gt; setOf = Sets.mutable.of("a", "b", "c");
 * </pre>
 *
 * Immutable Examples:
 *
 * <pre>
 * ImmutableSet&lt;String&gt; emptySet = Sets.immutable.empty();
 * ImmutableSet&lt;String&gt; setWith = Sets.immutable.with("a", "b", "c");
 * ImmutableSet&lt;String&gt; setOf = Sets.immutable.of("a", "b", "c");
 * </pre>
 *
 * FixedSize Examples:
 *
 * <pre>
 * FixedSizeList&lt;String&gt; emptySet = Sets.fixedSize.empty();
 * FixedSizeList&lt;String&gt; setWith = Sets.fixedSize.with("a", "b", "c");
 * FixedSizeList&lt;String&gt; setOf = Sets.fixedSize.of("a", "b", "c");
 * </pre>
 */
@SuppressWarnings("ConstantNamingConvention")
public final class Sets
{
    public static final ImmutableSetFactory immutable = ImmutableSetFactoryImpl.INSTANCE;
    public static final FixedSizeSetFactory fixedSize = FixedSizeSetFactoryImpl.INSTANCE;
    public static final MutableSetFactory mutable = MutableSetFactoryImpl.INSTANCE;
    public static final MutableSetFactory multiReader = MultiReaderMutableSetFactory.INSTANCE;

    private static final Predicate<Set<?>> INSTANCE_OF_SORTED_SET_PREDICATE = SortedSet.class::isInstance;

    private static final Predicate<Set<?>> HAS_NON_NULL_COMPARATOR = set -> ((SortedSet<?>) set).comparator() != null;

    private Sets()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * @since 9.0.
     */
    public static <T> MutableSet<T> adapt(Set<T> list)
    {
        return SetAdapter.adapt(list);
    }

    public static <E> MutableSet<E> union(
            Set<? extends E> setA,
            Set<? extends E> setB)
    {
        return unionInto(newSet(setA, setB), setA, setB);
    }

    public static <E, R extends Set<E>> R unionInto(
            R targetSet,
            Set<? extends E> setA,
            Set<? extends E> setB)
    {
        return setA.size() > setB.size() ? fillSet(targetSet, Sets.addAllProcedure(), setA, setB)
                : fillSet(targetSet, Sets.addAllProcedure(), setB, setA);
    }

    public static <E> MutableSet<E> unionAll(Set<? extends E>... sets)
    {
        return unionAllInto(newSet(sets), sets);
    }

    public static <E, R extends Set<E>> R unionAllInto(
            R targetSet,
            Set<? extends E>... sets)
    {
        Arrays.sort(sets, 0, sets.length, Comparators.descendingCollectionSizeComparator());
        return fillSet(targetSet, Sets.addAllProcedure(), sets);
    }

    public static <E> MutableSet<E> intersect(
            Set<? extends E> setA,
            Set<? extends E> setB)
    {
        return intersectInto(newSet(setA, setB), setA, setB);
    }

    public static <E, R extends Set<E>> R intersectInto(
            R targetSet,
            Set<? extends E> setA,
            Set<? extends E> setB)
    {
        return setA.size() < setB.size() ? fillSet(targetSet, Sets.retainAllProcedure(), setA, setB)
                : fillSet(targetSet, Sets.retainAllProcedure(), setB, setA);
    }

    public static <E> MutableSet<E> intersectAll(Set<? extends E>... sets)
    {
        return intersectAllInto(newSet(sets), sets);
    }

    public static <E, R extends Set<E>> R intersectAllInto(
            R targetSet,
            Set<? extends E>... sets)
    {
        Arrays.sort(sets, 0, sets.length, Comparators.ascendingCollectionSizeComparator());
        return fillSet(targetSet, Sets.retainAllProcedure(), sets);
    }

    public static <E> MutableSet<E> difference(
            Set<? extends E> minuendSet,
            Set<? extends E> subtrahendSet)
    {
        return differenceInto(newSet(minuendSet, subtrahendSet), minuendSet, subtrahendSet);
    }

    public static <E, R extends Set<E>> R differenceInto(
            R targetSet,
            Set<? extends E> minuendSet,
            Set<? extends E> subtrahendSet)
    {
        return fillSet(targetSet, Sets.removeAllProcedure(), minuendSet, subtrahendSet);
    }

    public static <E> MutableSet<E> differenceAll(Set<? extends E>... sets)
    {
        return differenceAllInto(newSet(sets), sets);
    }

    public static <E, R extends Set<E>> R differenceAllInto(
            R targetSet,
            Set<? extends E>... sets)
    {
        return fillSet(targetSet, Sets.removeAllProcedure(), sets);
    }

    public static <E> MutableSet<E> symmetricDifference(
            Set<? extends E> setA,
            Set<? extends E> setB)
    {
        return symmetricDifferenceInto(newSet(setA, setB), setA, setB);
    }

    public static <E, R extends Set<E>> R symmetricDifferenceInto(
            R targetSet,
            Set<? extends E> setA,
            Set<? extends E> setB)
    {
        return unionInto(
                targetSet,
                differenceInto(newSet(setA, setB), setA, setB),
                differenceInto(newSet(setA, setB), setB, setA));
    }

    public static <E> boolean isSubsetOf(
            Set<? extends E> candidateSubset,
            Set<? extends E> candidateSuperset)
    {
        return candidateSubset.size() <= candidateSuperset.size()
                && candidateSuperset.containsAll(candidateSubset);
    }

    public static <E> boolean isProperSubsetOf(
            Set<? extends E> candidateSubset,
            Set<? extends E> candidateSuperset)
    {
        return candidateSubset.size() < candidateSuperset.size()
                && candidateSuperset.containsAll(candidateSubset);
    }

    private static <E> MutableSet<E> newSet(Set<? extends E>... sets)
    {
        Comparator<? super E> comparator = extractComparator(sets);
        if (comparator != null)
        {
            // TODO: this should return a SortedSetAdapter once implemented
            return SetAdapter.adapt(new TreeSet<>(comparator));
        }
        return UnifiedSet.newSet();
    }

    private static <E> Comparator<? super E> extractComparator(Set<? extends E>... sets)
    {
        Collection<Set<? extends E>> sortedSetCollection = ArrayIterate.select(sets, INSTANCE_OF_SORTED_SET_PREDICATE);
        if (sortedSetCollection.isEmpty())
        {
            return null;
        }
        SortedSet<E> sortedSetWithComparator = (SortedSet<E>) Iterate.detect(sortedSetCollection, HAS_NON_NULL_COMPARATOR);
        if (sortedSetWithComparator != null)
        {
            return sortedSetWithComparator.comparator();
        }
        return Comparators.safeNullsLow(Comparators.naturalOrder());
    }

    private static <E, R extends Set<E>> R fillSet(
            R targetSet,
            Procedure2<Set<? extends E>, R> procedure,
            Set<? extends E>... sets)
    {
        targetSet.addAll(sets[0]);
        for (int i = 1; i < sets.length; i++)
        {
            procedure.value(sets[i], targetSet);
        }
        return targetSet;
    }

    private static <E, R extends Set<E>> Procedure2<Set<? extends E>, R> addAllProcedure()
    {
        return (argumentSet, targetSet) -> targetSet.addAll(argumentSet);
    }

    private static <E, R extends Set<E>> Procedure2<Set<? extends E>, R> retainAllProcedure()
    {
        return (argumentSet, targetSet) -> targetSet.retainAll(argumentSet);
    }

    private static <E, R extends Set<E>> Procedure2<Set<? extends E>, R> removeAllProcedure()
    {
        return (argumentSet, targetSet) -> targetSet.removeAll(argumentSet);
    }

    public static <T> MutableSet<MutableSet<T>> powerSet(Set<T> set)
    {
        MutableSet<MutableSet<T>> seed = UnifiedSet.newSetWith(UnifiedSet.newSet());
        return Iterate.injectInto(seed, set, (accumulator, element) -> Sets.union(accumulator, accumulator.collect(innerSet -> innerSet.toSet().with(element))));
    }

    public static <A, B> LazyIterable<Pair<A, B>> cartesianProduct(Set<A> set1, Set<B> set2)
    {
        return Sets.cartesianProduct(set1, set2, Tuples::pair);
    }

    public static <A, B, C> LazyIterable<C> cartesianProduct(Set<A> set1, Set<B> set2, Function2<A, B, C> function)
    {
        return LazyIterate.flatCollect(set1, first -> LazyIterate.collect(set2, second -> function.value(first, second)));
    }
}
