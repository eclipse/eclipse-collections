/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.stack;

import org.eclipse.collections.api.stack.ImmutableStack;

public interface ImmutableStackFactory
{
    /**
     * @since 6.0
     */
    <T> ImmutableStack<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableStack<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableStack<T> with();

    /**
     * Same as {@link #with(Object)}.
     */
    <T> ImmutableStack<T> of(T element);

    <T> ImmutableStack<T> with(T element);

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> ImmutableStack<T> of(T... elements);

    <T> ImmutableStack<T> with(T... elements);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> ImmutableStack<T> ofAll(Iterable<? extends T> items);

    <T> ImmutableStack<T> withAll(Iterable<? extends T> items);

    /**
     * Same as {@link #withReversed(Object[])}.
     */
    <T> ImmutableStack<T> ofReversed(T... elements);

    <T> ImmutableStack<T> withReversed(T... elements);

    /**
     * Same as {@link #withAllReversed(Iterable)}.
     */
    <T> ImmutableStack<T> ofAllReversed(Iterable<? extends T> items);

    <T> ImmutableStack<T> withAllReversed(Iterable<? extends T> items);
}
