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

import org.eclipse.collections.api.set.sorted.MutableSortedSet;

public interface MutableSortedSetFactory
{
    /**
     * @since 6.0
     */
    <T> MutableSortedSet<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    <T> MutableSortedSet<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> MutableSortedSet<T> with();

    <T> MutableSortedSet<T> of(T... items);

    <T> MutableSortedSet<T> with(T... items);

    <T> MutableSortedSet<T> ofAll(Iterable<? extends T> items);

    <T> MutableSortedSet<T> withAll(Iterable<? extends T> items);

    <T> MutableSortedSet<T> of(Comparator<? super T> comparator);

    <T> MutableSortedSet<T> with(Comparator<? super T> comparator);

    <T> MutableSortedSet<T> of(Comparator<? super T> comparator, T... items);

    <T> MutableSortedSet<T> with(Comparator<? super T> comparator, T... items);

    <T> MutableSortedSet<T> ofAll(Comparator<? super T> comparator, Iterable<? extends T> items);

    <T> MutableSortedSet<T> withAll(Comparator<? super T> comparator, Iterable<? extends T> items);
}
