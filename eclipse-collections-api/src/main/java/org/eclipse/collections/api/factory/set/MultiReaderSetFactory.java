/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.set;

import java.util.stream.Stream;

import org.eclipse.collections.api.set.MultiReaderSet;

public interface MultiReaderSetFactory
{
    /**
     * @since 6.0
     */
    <T> MultiReaderSet<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    default <T> MultiReaderSet<T> of()
    {
        return this.empty();
    }

    /**
     * Same as {@link #empty()}.
     */
    default <T> MultiReaderSet<T> with()
    {
        return this.empty();
    }

    /**
     * Same as {@link #with(Object[])}.
     */
    default <T> MultiReaderSet<T> of(T... items)
    {
        return this.with(items);
    }

    <T> MultiReaderSet<T> with(T... items);

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    default <T> MultiReaderSet<T> ofInitialCapacity(int capacity)
    {
        return this.withInitialCapacity(capacity);
    }

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    <T> MultiReaderSet<T> withInitialCapacity(int capacity);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    default <T> MultiReaderSet<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    <T> MultiReaderSet<T> withAll(Iterable<? extends T> items);

    /**
     * @since 10.0.
     */
    <T> MultiReaderSet<T> fromStream(Stream<? extends T> stream);
}
