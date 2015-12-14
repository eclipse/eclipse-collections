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

import org.eclipse.collections.api.set.ImmutableSet;

public interface ImmutableSetFactory
{
    /**
     * @since 6.0
     */
    <T> ImmutableSet<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableSet<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableSet<T> with();

    /**
     * Same as {@link #with(Object)}.
     */
    <T> ImmutableSet<T> of(T one);

    <T> ImmutableSet<T> with(T one);

    /**
     * Same as {@link #with(Object, Object)}.
     */
    <T> ImmutableSet<T> of(T one, T two);

    <T> ImmutableSet<T> with(T one, T two);

    /**
     * Same as {@link #with(Object, Object, Object)}.
     */
    <T> ImmutableSet<T> of(T one, T two, T three);

    <T> ImmutableSet<T> with(T one, T two, T three);

    /**
     * Same as {@link #with(Object, Object, Object, Object)}.
     */
    <T> ImmutableSet<T> of(T one, T two, T three, T four);

    <T> ImmutableSet<T> with(T one, T two, T three, T four);

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> ImmutableSet<T> of(T... items);

    <T> ImmutableSet<T> with(T... items);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> ImmutableSet<T> ofAll(Iterable<? extends T> items);

    <T> ImmutableSet<T> withAll(Iterable<? extends T> items);
}
