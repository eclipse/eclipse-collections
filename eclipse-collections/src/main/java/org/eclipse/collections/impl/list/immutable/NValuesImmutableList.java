/*
 * Copyright (c) 2019 Two Sigma.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.parallel.BatchIterable;

final class NValuesImmutableList<T>
        extends AbstractImmutableList<T>
        implements Serializable, RandomAccess, BatchIterable<T>
{
    private static final long serialVersionUID = 1L;

    private final int size;
    private final T value;

    NValuesImmutableList(int size, T value)
    {
        this.size = size;
        this.value = value;
    }

    @Override
    public ImmutableList<T> newWith(T element)
    {
        if (Objects.equals(this.value, element))
        {
            return new NValuesImmutableList<>(this.size + 1, this.value);
        }

        return ImmutableArrayList.newList(this.asLazy().concatenate(Lists.immutable.with(element)));
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;
        for (int i = 0; i < this.size; i++)
        {
            hashCode = 31 * hashCode + (this.value == null ? 0 : this.value.hashCode());
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof List))
        {
            return false;
        }
        if (o instanceof NValuesImmutableList)
        {
            NValuesImmutableList<?> that = (NValuesImmutableList<?>) o;
            return this.size == that.size && Objects.equals(this.value, that.value);
        }
        return o.equals(this);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        for (int i = 0; i < this.size; i++)
        {
            procedure.value(this.value);
        }
    }

    @Override
    public int size()
    {
        return this.size;
    }

    @Override
    public T get(int index)
    {
        if (index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }

        return this.value;
    }

    @Override
    public void batchForEach(Procedure<? super T> procedure, int sectionIndex, int sectionCount)
    {
        int sectionSize = this.size / sectionCount;
        int start = sectionSize * sectionIndex;
        int end = sectionIndex == sectionCount - 1 ? this.size : start + sectionSize;
        for (int i = start; i < end; i++)
        {
            procedure.value(this.value);
        }
    }
}
