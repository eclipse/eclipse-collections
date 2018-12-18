/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.factory.list.MutableListFactory;
import org.eclipse.collections.api.list.MutableList;

public class MutableListFactoryImpl implements MutableListFactory
{
    public static final MutableListFactory INSTANCE = new MutableListFactoryImpl();

    @Override
    public <T> MutableList<T> empty()
    {
        return FastList.newList();
    }

    @SafeVarargs
    @Override
    public <T> MutableList<T> with(T... items)
    {
        return FastList.newListWith(items);
    }

    @Override
    public <T> MutableList<T> withInitialCapacity(int capacity)
    {
        if (capacity < 0)
        {
            throw new IllegalArgumentException("initial capacity cannot be less than 0");
        }

        return FastList.newList(capacity);
    }

    @Override
    public <T> MutableList<T> withAll(Iterable<? extends T> iterable)
    {
        return FastList.newList(iterable);
    }

    @Override
    public <T> MutableList<T> withNValues(int size, Function0<? extends T> factory)
    {
        return FastList.newWithNValues(size, factory);
    }

    @Override
    public <T> MutableList<T> fromStream(Stream<? extends T> stream)
    {
        return stream.collect(Collectors.toCollection(FastList::newList));
    }
}
