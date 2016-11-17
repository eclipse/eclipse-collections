/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.set.sorted;

import java.util.Comparator;
import java.util.SortedSet;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

public interface ImmutableSortedSetFactory
{
    /**
     * @since 6.0
     */
    <T> ImmutableSortedSet<T> empty();

    /**
     * @since 7.0
     */
    <T> ImmutableSortedSet<T> empty(Comparator<? super T> comparator);

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableSortedSet<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableSortedSet<T> with();

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> ImmutableSortedSet<T> of(T... items);

    <T> ImmutableSortedSet<T> with(T... items);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> ImmutableSortedSet<T> ofAll(Iterable<? extends T> items);

    <T> ImmutableSortedSet<T> withAll(Iterable<? extends T> items);

    /**
     * Same as {@link #with(Comparator)}.
     */
    <T> ImmutableSortedSet<T> of(Comparator<? super T> comparator);

    <T> ImmutableSortedSet<T> with(Comparator<? super T> comparator);

    /**
     * Same as {@link #with(Comparator, Object[])}.
     */
    <T> ImmutableSortedSet<T> of(Comparator<? super T> comparator, T... items);

    <T> ImmutableSortedSet<T> with(Comparator<? super T> comparator, T... items);

    /**
     * Same as {@link #withAll(Comparator, Iterable)}.
     */
    <T> ImmutableSortedSet<T> ofAll(Comparator<? super T> comparator, Iterable<? extends T> items);

    <T> ImmutableSortedSet<T> withAll(Comparator<? super T> comparator, Iterable<? extends T> items);

    /**
     * Same as {@link #withSortedSet(SortedSet)}.
     */
    <T> ImmutableSortedSet<T> ofSortedSet(SortedSet<T> set);

    <T> ImmutableSortedSet<T> withSortedSet(SortedSet<T> set);
}
