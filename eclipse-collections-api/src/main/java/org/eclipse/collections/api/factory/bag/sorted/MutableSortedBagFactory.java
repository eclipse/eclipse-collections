/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.bag.sorted;

import java.util.Comparator;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;

/**
 * A factory which creates instances of type {@link MutableSortedBag}.
 *
 * @since 6.0
 */
public interface MutableSortedBagFactory
{
    <T> MutableSortedBag<T> empty();

    <T> MutableSortedBag<T> empty(Comparator<? super T> comparator);

    /**
     * Same as {@link #with()}.
     */
    <T> MutableSortedBag<T> of();

    <T> MutableSortedBag<T> with();

    /**
     * Same as {@link #with(Comparator)}.
     */
    <T> MutableSortedBag<T> of(Comparator<? super T> comparator);

    <T> MutableSortedBag<T> with(Comparator<? super T> comparator);

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> MutableSortedBag<T> of(T... elements);

    <T> MutableSortedBag<T> with(T... elements);

    /**
     * Same as {@link #with(Comparator, Object[])}.
     */
    <T> MutableSortedBag<T> of(Comparator<? super T> comparator, T... elements);

    <T> MutableSortedBag<T> with(Comparator<? super T> comparator, T... elements);

    /**
     * Same as {@link #withAll(Comparator, Iterable)}.
     */
    <T> MutableSortedBag<T> ofAll(Iterable<? extends T> items);

    <T> MutableSortedBag<T> withAll(Iterable<? extends T> items);

    /**
     * Same as {@link #withAll(Comparator, Iterable)}
     */
    <T> MutableSortedBag<T> ofAll(Comparator<? super T> comparator, Iterable<? extends T> items);

    <T> MutableSortedBag<T> withAll(Comparator<? super T> comparator, Iterable<? extends T> items);
}
