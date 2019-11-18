/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.list;

import java.util.stream.Stream;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.FixedSizeCollection;
import org.eclipse.collections.api.factory.Lists;

/**
 * A FixedSizeList is a list that may be mutated, but cannot grow or shrink in size. The typical
 * mutation allowed for a FixedSizeList implementation is a working implementation for set(int, T).
 * This will allow the FixedSizeList to be sorted.
 */
public interface FixedSizeList<T>
        extends MutableList<T>, FixedSizeCollection<T>
{
    static <T> FixedSizeList<T> empty()
    {
        return Lists.fixedSize.empty();
    }

    static <T> FixedSizeList<T> of()
    {
        return Lists.fixedSize.of();
    }

    static <T> FixedSizeList<T> of(T one)
    {
        return Lists.fixedSize.of(one);
    }

    static <T> FixedSizeList<T> of(T one, T two)
    {
        return Lists.fixedSize.of(one, two);
    }

    static <T> FixedSizeList<T> of(T one, T two, T three)
    {
        return Lists.fixedSize.of(one, two, three);
    }

    static <T> FixedSizeList<T> of(T one, T two, T three, T four)
    {
        return Lists.fixedSize.of(one, two, three, four);
    }

    static <T> FixedSizeList<T> of(T one, T two, T three, T four, T five)
    {
        return Lists.fixedSize.of(one, two, three, four, five);
    }

    static <T> FixedSizeList<T> of(T one, T two, T three, T four, T five, T six)
    {
        return Lists.fixedSize.of(one, two, three, four, five, six);
    }

    static <T> FixedSizeList<T> of(T... items)
    {
        return Lists.fixedSize.of(items);
    }

    static <T> FixedSizeList<T> ofAll(Iterable<? extends T> items)
    {
        return Lists.fixedSize.ofAll(items);
    }

    static <T> FixedSizeList<T> fromStream(Stream<? extends T> stream)
    {
        return Lists.fixedSize.fromStream(stream);
    }

    @Override
    FixedSizeList<T> toReversed();

    @Override
    FixedSizeList<T> tap(Procedure<? super T> procedure);
}
