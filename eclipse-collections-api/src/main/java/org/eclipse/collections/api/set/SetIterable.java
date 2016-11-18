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

import java.util.Set;
import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.ordered.OrderedIterable;
import org.eclipse.collections.api.partition.set.PartitionSet;
import org.eclipse.collections.api.tuple.Pair;

/**
 * A Read-only Set api, with the minor exception inherited from java.lang.Iterable (iterable.iterator().remove()).
 */
public interface SetIterable<T> extends RichIterable<T>
{
    /**
     * Returns the set of all objects that are a member of {@code this} or {@code set} or both. The union of [1, 2, 3]
     * and [2, 3, 4] is the set [1, 2, 3, 4]. If equal elements appear in both sets, then the output will contain the
     * copy from {@code this}.
     */
    SetIterable<T> union(SetIterable<? extends T> set);

    /**
     * Same as {@link #union(SetIterable)} but adds all the objects to {@code targetSet} and returns it.
     */
    <R extends Set<T>> R unionInto(SetIterable<? extends T> set, R targetSet);

    /**
     * Returns the set of all objects that are members of both {@code this} and {@code set}. The intersection of
     * [1, 2, 3] and [2, 3, 4] is the set [2, 3]. The output will contain instances from {@code this}, not {@code set}.
     */
    SetIterable<T> intersect(SetIterable<? extends T> set);

    /**
     * Same as {@link #intersect(SetIterable)} but adds all the objects to {@code targetSet} and returns it.
     */
    <R extends Set<T>> R intersectInto(SetIterable<? extends T> set, R targetSet);

    /**
     * Returns the set of all members of {@code this} that are not members of {@code subtrahendSet}. The difference of
     * [1, 2, 3] and [2, 3, 4] is [1].
     */
    SetIterable<T> difference(SetIterable<? extends T> subtrahendSet);

    /**
     * Same as {@link #difference(SetIterable)} but adds all the objects to {@code targetSet} and returns it.
     */
    <R extends Set<T>> R differenceInto(SetIterable<? extends T> subtrahendSet, R targetSet);

    /**
     * Returns the set of all objects that are a member of exactly one of {@code this} and {@code setB} (elements which
     * are in one of the sets, but not in both). For instance, for the sets [1, 2, 3] and [2, 3, 4], the symmetric
     * difference set is [1, 4] . It is the set difference of the union and the intersection.
     */
    SetIterable<T> symmetricDifference(SetIterable<? extends T> setB);

    /**
     * Same as {@link #symmetricDifference(SetIterable)} but adds all the objects to {@code targetSet} and returns it.
     */
    <R extends Set<T>> R symmetricDifferenceInto(SetIterable<? extends T> set, R targetSet);

    /**
     * Returns {@literal true} if all the members of {@code this} are also members of {@code candidateSuperset}.
     * For example, [1, 2] is a subset of [1, 2, 3], but [1, 4] is not.
     */
    boolean isSubsetOf(SetIterable<? extends T> candidateSuperset);

    /**
     * Returns {@literal true} if all the members of {@code this} are also members of {@code candidateSuperset} and the
     * two sets are not equal. For example, [1, 2] is a proper subset of [1, 2, 3], but [1, 2, 3] is not.
     */
    boolean isProperSubsetOf(SetIterable<? extends T> candidateSuperset);

    /**
     * Returns the set whose members are all possible ordered pairs (a, b) where a is a member of {@code this} and b is a
     * member of {@code set}.
     */
    <B> LazyIterable<Pair<T, B>> cartesianProduct(SetIterable<B> set);

    @Override
    SetIterable<T> tap(Procedure<? super T> procedure);

    @Override
    SetIterable<T> select(Predicate<? super T> predicate);

    @Override
    <P> SetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    SetIterable<T> reject(Predicate<? super T> predicate);

    @Override
    <P> SetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    PartitionSet<T> partition(Predicate<? super T> predicate);

    @Override
    <P> PartitionSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    @Override
    <S> SetIterable<S> selectInstancesOf(Class<S> clazz);

    /**
     * @deprecated in 6.0. Use {@link OrderedIterable#zipWithIndex()} instead.
     */
    @Override
    @Deprecated
    SetIterable<Pair<T, Integer>> zipWithIndex();

    /**
     * Returns a parallel iterable of this SetIterable.
     *
     * @since 6.0
     */
    @Beta
    ParallelSetIterable<T> asParallel(ExecutorService executorService, int batchSize);

    /**
     * Follows the same general contract as {@link Set#equals(Object)}.
     */
    @Override
    boolean equals(Object o);

    /**
     * Follows the same general contract as {@link Set#hashCode()}.
     */
    @Override
    int hashCode();

    ImmutableSetIterable<T> toImmutable();
}
