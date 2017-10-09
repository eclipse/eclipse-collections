/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.factory.list.MutableListFactory;
import org.eclipse.collections.api.list.MutableList;

public enum MutableListFactoryImpl implements MutableListFactory
{
    INSTANCE;

    @Override
    public <T> MutableList<T> empty()
    {
        return FastList.newList();
    }

    @Override
    public <T> MutableList<T> of()
    {
        return this.empty();
    }

    @Override
    public <T> MutableList<T> with()
    {
        return this.empty();
    }

    @Override
    public <T> MutableList<T> of(T... items)
    {
        return this.with(items);
    }

    @Override
    public <T> MutableList<T> with(T... items)
    {
        return FastList.newListWith(items);
    }

    @Override
    public <T> MutableList<T> ofInitialCapacity(int capacity)
    {
        return this.withInitialCapacity(capacity);
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
    public <T> MutableList<T> ofAll(Iterable<? extends T> iterable)
    {
        return this.withAll(iterable);
    }

    @Override
    public <T> MutableList<T> withAll(Iterable<? extends T> iterable)
    {
        return FastList.newList(iterable);
    }

    @Override
    public <T> MutableList<T> withNValues(int size, Function0<T> factory)
    {
        return FastList.newWithNValues(size, factory);
    }
}
