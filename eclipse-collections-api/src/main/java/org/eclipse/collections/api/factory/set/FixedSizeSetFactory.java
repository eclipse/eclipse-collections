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

import org.eclipse.collections.api.set.FixedSizeSet;
import org.eclipse.collections.api.set.MutableSet;

public interface FixedSizeSetFactory
{
    /**
     * @since 6.0
     */
    <T> FixedSizeSet<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    <T> FixedSizeSet<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> FixedSizeSet<T> with();

    /**
     * Same as {@link #with(Object)}.
     */
    <T> FixedSizeSet<T> of(T one);

    <T> FixedSizeSet<T> with(T one);

    /**
     * Same as {@link #with(Object, Object)}.
     */
    <T> FixedSizeSet<T> of(T one, T two);

    <T> FixedSizeSet<T> with(T one, T two);

    /**
     * Same as {@link #with(Object, Object, Object)}.
     */
    <T> FixedSizeSet<T> of(T one, T two, T three);

    <T> FixedSizeSet<T> with(T one, T two, T three);

    /**
     * Same as {@link #with(Object, Object, Object, Object)}.
     */
    <T> FixedSizeSet<T> of(T one, T two, T three, T four);

    <T> FixedSizeSet<T> with(T one, T two, T three, T four);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> MutableSet<T> ofAll(Iterable<? extends T> items);

    <T> MutableSet<T> withAll(Iterable<? extends T> items);
}
