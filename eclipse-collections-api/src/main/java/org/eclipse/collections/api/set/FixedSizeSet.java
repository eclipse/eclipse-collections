/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.set;

import java.util.stream.Stream;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.FixedSizeCollection;
import org.eclipse.collections.api.factory.Sets;

/**
 * A FixedSizeSet is a set that may be mutated, but cannot grow or shrink in size.
 */
public interface FixedSizeSet<T>
        extends MutableSet<T>, FixedSizeCollection<T>
{
    static <T> FixedSizeSet<T> empty()
    {
        return Sets.fixedSize.empty();
    }

    static <T> FixedSizeSet<T> of()
    {
        return Sets.fixedSize.of();
    }

    static <T> FixedSizeSet<T> of(T one)
    {
        return Sets.fixedSize.of(one);
    }

    static <T> FixedSizeSet<T> of(T one, T two)
    {
        return Sets.fixedSize.of(one, two);
    }

    static <T> FixedSizeSet<T> of(T one, T two, T three)
    {
        return Sets.fixedSize.of(one, two, three);
    }

    static <T> FixedSizeSet<T> of(T one, T two, T three, T four)
    {
        return Sets.fixedSize.of(one, two, three, four);
    }

    static <T> MutableSet<T> ofAll(Iterable<? extends T> items)
    {
        return Sets.fixedSize.ofAll(items);
    }

    static <T> MutableSet<T> fromStream(Stream<? extends T> stream)
    {
        return Sets.fixedSize.fromStream(stream);
    }

    @Override
    FixedSizeSet<T> tap(Procedure<? super T> procedure);
}
