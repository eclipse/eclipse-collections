/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.bag;

import org.eclipse.collections.api.bag.ImmutableBag;

/**
 * A factory which creates instances of type {@link ImmutableBag}.
 */
public interface ImmutableBagFactory
{
    /**
     * @since 6.0
     */
    <T> ImmutableBag<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableBag<T> of();

    /**
     * Same as {@link #empty()}.
     */
    <T> ImmutableBag<T> with();

    /**
     * Same as {@link #with(Object)}.
     */
    <T> ImmutableBag<T> of(T element);

    <T> ImmutableBag<T> with(T element);

    /**
     * Same as {@link #with(Object[])}.
     */
    <T> ImmutableBag<T> of(T... elements);

    <T> ImmutableBag<T> with(T... elements);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    <T> ImmutableBag<T> ofAll(Iterable<? extends T> items);

    <T> ImmutableBag<T> withAll(Iterable<? extends T> items);
}
