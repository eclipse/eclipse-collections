/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import java.util.ListIterator;

public class FixedSizeListIteratorAdapter<T>
        implements ListIterator<T>
{
    private final ListIterator<T> iterator;

    public FixedSizeListIteratorAdapter(ListIterator<T> iterator)
    {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext()
    {
        return this.iterator.hasNext();
    }

    @Override
    public T next()
    {
        return this.iterator.next();
    }

    @Override
    public boolean hasPrevious()
    {
        return this.iterator.hasPrevious();
    }

    @Override
    public T previous()
    {
        return this.iterator.previous();
    }

    @Override
    public int nextIndex()
    {
        return this.iterator.nextIndex();
    }

    @Override
    public int previousIndex()
    {
        return this.iterator.previousIndex();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    @Override
    public void set(T o)
    {
        this.iterator.set(o);
    }

    @Override
    public void add(T o)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }
}
