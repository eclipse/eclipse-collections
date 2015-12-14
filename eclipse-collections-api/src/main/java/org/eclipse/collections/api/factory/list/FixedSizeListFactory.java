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

import org.eclipse.collections.api.list.FixedSizeList;

public interface FixedSizeListFactory
{
    /**
     * @since 6.0
     */
    <T> FixedSizeList<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    <T> FixedSizeList<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> FixedSizeList<T> with();

    /**
     * Same as {@link #with(Object)}.
     */
    <T> FixedSizeList<T> of(T one);

    <T> FixedSizeList<T> with(T one);

    /**
     * Same as {@link #with(Object, Object)}.
     */
    <T> FixedSizeList<T> of(T one, T two);

    <T> FixedSizeList<T> with(T one, T two);

    /**
     * Same as {@link #with(Object, Object, Object)}.
     */
    <T> FixedSizeList<T> of(T one, T two, T three);

    <T> FixedSizeList<T> with(T one, T two, T three);

    /**
     * Same as {@link #with(Object, Object, Object, Object)}.
     */
    <T> FixedSizeList<T> of(T one, T two, T three, T four);

    <T> FixedSizeList<T> with(T one, T two, T three, T four);

    /**
     * Same as {@link #with(Object, Object, Object, Object, Object)}.
     */
    <T> FixedSizeList<T> of(T one, T two, T three, T four, T five);

    <T> FixedSizeList<T> with(T one, T two, T three, T four, T five);

    /**
     * Same as {@link #with(Object, Object, Object, Object, Object, Object)}.
     */
    <T> FixedSizeList<T> of(T one, T two, T three, T four, T five, T six);

    <T> FixedSizeList<T> with(T one, T two, T three, T four, T five, T six);

    /**
     * Same as {@link #with(Object[])}
     */
    <T> FixedSizeList<T> of(T... items);

    <T> FixedSizeList<T> with(T... items);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> FixedSizeList<T> ofAll(Iterable<? extends T> items);

    <T> FixedSizeList<T> withAll(Iterable<? extends T> items);
}
