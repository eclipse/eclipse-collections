/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory.bag;

import java.util.stream.Stream;

import org.eclipse.collections.api.bag.MultiReaderBag;

/**
 * A factory which creates instances of type {@link MultiReaderBag}.
 */
public interface MultiReaderBagFactory
{
    /**
     * @since 6.0
     */
    <T> MultiReaderBag<T> empty();

    /**
     * Same as {@link #empty()}.
     */
    default <T> MultiReaderBag<T> of()
    {
        return this.empty();
    }

    /**
     * Same as {@link #empty()}.
     */
    default <T> MultiReaderBag<T> with()
    {
        return this.empty();
    }

    /**
     * Same as {@link #with(Object[])}.
     */
    default <T> MultiReaderBag<T> of(T... elements)
    {
        return this.with(elements);
    }

    <T> MultiReaderBag<T> with(T... elements);

    /**
     * Same as {@link #withAll(Iterable)}.
     */
    default <T> MultiReaderBag<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    <T> MultiReaderBag<T> withAll(Iterable<? extends T> items);

    /**
     * @since 10.0.
     */
    <T> MultiReaderBag<T> fromStream(Stream<? extends T> stream);
}
