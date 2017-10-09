/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.list;

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
    <T> MutableList<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> MutableList<T> with();

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> MutableList<T> of(T... items);

    <T> MutableList<T> with(T... items);

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    default <T> MutableList<T> ofInitialCapacity(int capacity)
    {
        throw new UnsupportedOperationException("Adding default implementation so as to not break compatibility");
    }

    /**
     * Same as {@link #empty()}. but takes in initial capacity.
     */
    default <T> MutableList<T> withInitialCapacity(int capacity)
    {
        throw new UnsupportedOperationException("Adding default implementation so as to not break compatibility");
    }

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> MutableList<T> ofAll(Iterable<? extends T> iterable);

    <T> MutableList<T> withAll(Iterable<? extends T> iterable);

    <T> MutableList<T> withNValues(int size, Function0<T> factory);
}
