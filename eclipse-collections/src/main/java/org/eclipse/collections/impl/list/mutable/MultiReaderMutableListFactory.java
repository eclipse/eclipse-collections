/*
 * Copyright (c) 2022 Goldman Sachs and others.
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
import org.eclipse.collections.api.factory.list.MultiReaderListFactory;
import org.eclipse.collections.api.list.MultiReaderList;

public class MultiReaderMutableListFactory implements MultiReaderListFactory
{
    public static final MultiReaderListFactory INSTANCE = new MultiReaderMutableListFactory();

    @Override
    public <T> MultiReaderList<T> empty()
    {
        return MultiReaderFastList.newList();
    }

    @Override
    public <T> MultiReaderList<T> with(T... items)
    {
        return MultiReaderFastList.newListWith(items);
    }

    @Override
    public <T> MultiReaderList<T> withInitialCapacity(int capacity)
    {
        if (capacity < 0)
        {
            throw new IllegalArgumentException("initial capacity cannot be less than 0");
        }

        return MultiReaderFastList.newList(capacity);
    }

    @Override
    public <T> MultiReaderList<T> withAll(Iterable<? extends T> iterable)
    {
        return MultiReaderFastList.newList(iterable);
    }

    @Override
    public <T> MultiReaderList<T> fromStream(Stream<? extends T> stream)
    {
        return stream.collect(Collectors.toCollection(MultiReaderFastList::newList));
    }

    @Override
    public <T> MultiReaderList<T> withNValues(int size, Function0<? extends T> factory)
    {
        MultiReaderFastList<T> newFastList = MultiReaderFastList.newList(size);
        for (int i = 0; i < size; i++)
        {
            newFastList.add(factory.value());
        }
        return newFastList;
    }
}
