/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.list;

import java.util.stream.Stream;

import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.list.MutableList;

public interface MutableListFactory
{
    /**
     * @since 6.0
     */
    <T> MutableList<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    default <T> MutableList<T> of()
    {
        return this.empty();
    }

    /**
     * Same as {@link #empty()}.
     */
    default <T> MutableList<T> with()
    {
        return this.empty();
    }

    /**
     * Same as {@link #with(Object[])}.
     */
    default <T> MutableList<T> of(T... items)
    {
        return this.with(items);
    }

    <T> MutableList<T> with(T... items);

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    default <T> MutableList<T> ofInitialCapacity(int capacity)
    {
        return this.withInitialCapacity(capacity);
    }

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    <T> MutableList<T> withInitialCapacity(int capacity);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    default <T> MutableList<T> ofAll(Iterable<? extends T> iterable)
    {
        return this.withAll(iterable);
    }

    <T> MutableList<T> withAll(Iterable<? extends T> iterable);

    /**
     * @since 10.0.
     */
    <T> MutableList<T> fromStream(Stream<? extends T> stream);

    <T> MutableList<T> withNValues(int size, Function0<? extends T> factory);
}
