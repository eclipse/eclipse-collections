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

import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.SortedBag;

/**
 * A factory which creates instances of type {@link ImmutableSortedBag}.
 */
public interface ImmutableSortedBagFactory
{
    <T> ImmutableSortedBag<T> empty();

    <T> ImmutableSortedBag<T> empty(Comparator<? super T> comparator);

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableSortedBag<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableSortedBag<T> with();

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> ImmutableSortedBag<T> of(T... items);

    <T> ImmutableSortedBag<T> with(T... items);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> ImmutableSortedBag<T> ofAll(Iterable<? extends T> items);

    <T> ImmutableSortedBag<T> withAll(Iterable<? extends T> items);

    /**
     * Same as {@link #with(Comparator, Object[])}.
     */
    <T> ImmutableSortedBag<T> of(Comparator<? super T> comparator, T... items);

    <T> ImmutableSortedBag<T> with(Comparator<? super T> comparator, T... items);

    /**
     * Same as {@link #with(Comparator)}.
     */
    <T> ImmutableSortedBag<T> of(Comparator<? super T> comparator);

    <T> ImmutableSortedBag<T> with(Comparator<? super T> comparator);

    /**
     * Same as {@link #withAll(Comparator, Iterable)}.
     */
    <T> ImmutableSortedBag<T> ofAll(Comparator<? super T> comparator, Iterable<? extends T> items);

    <T> ImmutableSortedBag<T> withAll(Comparator<? super T> comparator, Iterable<? extends T> items);

    /**
     * Same as {@link #withSortedBag(SortedBag)}.
     */
    <T> ImmutableSortedBag<T> ofSortedBag(SortedBag<T> bag);

    <T> ImmutableSortedBag<T> withSortedBag(SortedBag<T> bag);
}
