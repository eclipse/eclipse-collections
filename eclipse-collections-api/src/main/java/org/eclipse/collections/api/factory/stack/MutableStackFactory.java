/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.stack;

import java.util.stream.Stream;

import org.eclipse.collections.api.stack.MutableStack;

public interface MutableStackFactory
{
    /**
     * @since 6.0
     */
    <T> MutableStack<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    default <T> MutableStack<T> of()
    {
        return this.empty();
    }

    /**
     * Same as {@link #empty()}.
     */
    default <T> MutableStack<T> with()
    {
        return this.empty();
    }

    /**
     * Same as {@link #with(Object[])}.
     */
    default <T> MutableStack<T> of(T... elements)
    {
        return this.with(elements);
    }

    <T> MutableStack<T> with(T... elements);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    default <T> MutableStack<T> ofAll(Iterable<? extends T> elements)
    {
        return this.withAll(elements);
    }

    <T> MutableStack<T> withAll(Iterable<? extends T> elements);

    /**
     * @since 10.0.
     */
    <T> MutableStack<T> fromStream(Stream<? extends T> stream);

    /**
     * Same as {@link #withReversed(Object[])}.
     */
    default <T> MutableStack<T> ofReversed(T... elements)
    {
        return this.withReversed(elements);
    }

    <T> MutableStack<T> withReversed(T... elements);

    /**
     * Same as {@link #withAllReversed(Iterable)}.
     */
    default <T> MutableStack<T> ofAllReversed(Iterable<? extends T> items)
    {
        return this.withAllReversed(items);
    }

    <T> MutableStack<T> withAllReversed(Iterable<? extends T> items);
}
