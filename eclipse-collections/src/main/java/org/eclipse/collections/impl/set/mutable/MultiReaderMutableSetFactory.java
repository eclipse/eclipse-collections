/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.collections.api.factory.set.MutableSetFactory;
import org.eclipse.collections.api.set.MutableSet;

public enum MultiReaderMutableSetFactory implements MutableSetFactory
{
    INSTANCE;

    @Override
    public <T> MutableSet<T> empty()
    {
        return MultiReaderUnifiedSet.newSet();
    }

    @Override
    public <T> MutableSet<T> with(T... items)
    {
        return MultiReaderUnifiedSet.newSetWith(items);
    }

    @Override
    public <T> MutableSet<T> withInitialCapacity(int capacity)
    {
        if (capacity < 0)
        {
            throw new IllegalArgumentException("initial capacity cannot be less than 0");
        }

        return MultiReaderUnifiedSet.newSet(capacity);
    }

    @Override
    public <T> MutableSet<T> withAll(Iterable<? extends T> iterable)
    {
        return MultiReaderUnifiedSet.newSet((Iterable<T>) iterable);
    }

    @Override
    public <T> MutableSet<T> fromStream(Stream<? extends T> stream)
    {
        return stream.collect(Collectors.toCollection(MultiReaderUnifiedSet::newSet));
    }
}
