/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility.internal;

import java.util.Set;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.set.mutable.SetAdapter;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.LazyIterate;

/**
 * Set algebra operations.
 * <p>
 * Most operations are non-destructive, i.e. no input sets are modified during execution.
 * The exception is operations ending in "Into." These accept the target collection of
 * the final calculation as the first parameter.
 */
public final class SetIterables
{
    private SetIterables()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <E> MutableSet<E> union(
            SetIterable<? extends E> setA,
            SetIterable<? extends E> setB)
    {
        return SetIterables.unionInto(setA, setB, UnifiedSet.newSet());
    }

    public static <E, R extends Set<E>> R unionInto(
            SetIterable<? extends E> setA,
            SetIterable<? extends E> setB,
            R targetSet)
    {
        Iterate.addAllIterable(setA, targetSet);
        Iterate.addAllIterable(setB, targetSet);
        return targetSet;
    }

    public static <E> MutableSet<E> intersect(
            SetIterable<? extends E> setA,
            SetIterable<? extends E> setB)
    {
        return SetIterables.intersectInto(setA, setB, UnifiedSet.newSet());
    }

    public static <E, R extends Set<E>> R intersectInto(
            SetIterable<? extends E> setA,
            SetIterable<? extends E> setB,
            R targetSet)
    {
        MutableSet<E> adapted = SetAdapter.adapt(targetSet);
        adapted.addAllIterable(setA);
        adapted.retainAllIterable(setB);
        return targetSet;
    }

    public static <E> MutableSet<E> difference(
            SetIterable<? extends E> minuendSet,
            SetIterable<? extends E> subtrahendSet)
    {
        return SetIterables.differenceInto(minuendSet, subtrahendSet, UnifiedSet.newSet());
    }

    public static <E, R extends Set<E>> R differenceInto(
            SetIterable<? extends E> minuendSet,
            SetIterable<? extends E> subtrahendSet,
            R targetSet)
    {
        MutableSet<E> adapted = SetAdapter.adapt(targetSet);
        adapted.addAllIterable(minuendSet);
        adapted.removeAllIterable(subtrahendSet);
        return targetSet;
    }

    public static <E> MutableSet<E> symmetricDifference(
            SetIterable<? extends E> setA,
            SetIterable<? extends E> setB)
    {
        return SetIterables.symmetricDifferenceInto(setA, setB, UnifiedSet.newSet());
    }

    public static <E, R extends Set<E>> R symmetricDifferenceInto(
            SetIterable<? extends E> setA,
            SetIterable<? extends E> setB,
            R targetSet)
    {
        return SetIterables.unionInto(
                SetIterables.difference(setA, setB),
                SetIterables.difference(setB, setA),
                targetSet);
    }

    public static <E> boolean isSubsetOf(
            SetIterable<? extends E> candidateSubset,
            SetIterable<? extends E> candidateSuperset)
    {
        return candidateSubset.size() <= candidateSuperset.size()
                && candidateSuperset.containsAllIterable(candidateSubset);
    }

    public static <E> boolean isProperSubsetOf(
            SetIterable<? extends E> candidateSubset,
            SetIterable<? extends E> candidateSuperset)
    {
        return candidateSubset.size() < candidateSuperset.size()
                && candidateSuperset.containsAllIterable(candidateSubset);
    }

    public static <T> MutableSet<MutableSet<T>> powerSet(Set<T> set)
    {
        MutableSet<MutableSet<T>> seed = UnifiedSet.newSetWith(UnifiedSet.newSet());
        return powerSetWithSeed(set, seed);
    }

    public static <T> MutableSet<MutableSet<T>> powerSet(UnifiedSetWithHashingStrategy<T> set)
    {
        MutableSet<MutableSet<T>> seed = UnifiedSet.newSetWith(set.newEmpty());
        return powerSetWithSeed(set, seed);
    }

    private static <T> MutableSet<MutableSet<T>> powerSetWithSeed(Set<T> set, MutableSet<MutableSet<T>> seed)
    {
        return Iterate.injectInto(seed, set, (accumulator, element) -> SetIterables.union(accumulator, accumulator.collect(innerSet -> innerSet.clone().with(element))));
    }

    /**
     * Returns an Immutable version of powerset where the inner sets are also immutable.
     */
    public static <T> ImmutableSet<ImmutableSet<T>> immutablePowerSet(Set<T> set)
    {
        return powerSet(set).collect(MutableSet::toImmutable).toImmutable();
    }

    public static <A, B> LazyIterable<Pair<A, B>> cartesianProduct(SetIterable<A> set1, SetIterable<B> set2)
    {
        return SetIterables.cartesianProduct(set1, set2, Tuples::pair);
    }

    public static <A, B, C> LazyIterable<C> cartesianProduct(SetIterable<A> set1, SetIterable<B> set2, Function2<A, B, C> function)
    {
        return LazyIterate.flatCollect(set1, first -> LazyIterate.collect(set2, second -> function.value(first, second)));
    }
}
