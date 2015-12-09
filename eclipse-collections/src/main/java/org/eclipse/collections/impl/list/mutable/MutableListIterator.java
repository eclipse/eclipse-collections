/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.list.mutable;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public final class MutableListIterator<T> extends MutableIterator<T> implements ListIterator<T>
{
    public MutableListIterator(List<T> list, int index)
    {
        super(list);
        this.currentIndex = index;
    }

    public boolean hasPrevious()
    {
        return this.currentIndex != 0;
    }

    public T previous()
    {
        try
        {
            int i = this.currentIndex - 1;
            T previous = this.list.get(i);
            this.currentIndex = i;
            this.lastIndex = i;
            return previous;
        }
        catch (IndexOutOfBoundsException ignored)
        {
            throw new NoSuchElementException();
        }
    }

    public int nextIndex()
    {
        return this.currentIndex;
    }

    public int previousIndex()
    {
        return this.currentIndex - 1;
    }

    public void set(T o)
    {
        if (this.lastIndex == -1)
        {
            throw new IllegalStateException();
        }

        try
        {
            this.list.set(this.lastIndex, o);
        }
        catch (IndexOutOfBoundsException ignored)
        {
            throw new ConcurrentModificationException();
        }
    }

    public void add(T o)
    {
        this.list.add(this.currentIndex++, o);
        this.lastIndex = -1;
    }
}
