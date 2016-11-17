/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.set;

import org.eclipse.collections.api.set.MutableSet;

public interface MutableSetFactory
{
    /**
     * @since 6.0
     */
    <T> MutableSet<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    <T> MutableSet<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> MutableSet<T> with();

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> MutableSet<T> of(T... items);

    <T> MutableSet<T> with(T... items);

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    <T> MutableSet<T> ofInitialCapacity(int capacity);

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    <T> MutableSet<T> withInitialCapacity(int capacity);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> MutableSet<T> ofAll(Iterable<? extends T> items);

    <T> MutableSet<T> withAll(Iterable<? extends T> items);
}
