/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import java.io.Serializable;
import java.util.RandomAccess;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.Lists;

/**
 * This is a single element immutable List which is created by calling
 * Immutable.newListWith(one) method.
 */
final class ImmutableSingletonList<T>
        extends AbstractImmutableList<T>
        implements Serializable, RandomAccess
{
    private static final long serialVersionUID = 1L;

    private final T element1;

    ImmutableSingletonList(T obj1)
    {
        this.element1 = obj1;
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public boolean contains(Object obj)
    {
        return Comparators.nullSafeEquals(obj, this.element1);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        procedure.value(this.element1);
    }

    @Override
    public T get(int index)
    {
        if (index == 0)
        {
            return this.element1;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
    }

    @Override
    public T getFirst()
    {
        return this.element1;
    }

    @Override
    public T getLast()
    {
        return this.element1;
    }

    @Override
    public T getOnly()
    {
        return this.element1;
    }

    @Override
    public ImmutableList<T> toReversed()
    {
        return this;
    }

    @Override
    public ImmutableList<T> newWith(T newItem)
    {
        return Lists.immutable.with(this.element1, newItem);
    }
}
