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
    <T> MutableStack<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> MutableStack<T> with();

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> MutableStack<T> of(T... elements);

    <T> MutableStack<T> with(T... elements);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> MutableStack<T> ofAll(Iterable<? extends T> elements);

    <T> MutableStack<T> withAll(Iterable<? extends T> elements);

    /**
     * Same as {@link #withReversed(Object[])}.
     */
    <T> MutableStack<T> ofReversed(T... elements);

    <T> MutableStack<T> withReversed(T... elements);

    /**
     * Same as {@link #withAllReversed(Iterable)}.
     */
    <T> MutableStack<T> ofAllReversed(Iterable<? extends T> items);

    <T> MutableStack<T> withAllReversed(Iterable<? extends T> items);
}
