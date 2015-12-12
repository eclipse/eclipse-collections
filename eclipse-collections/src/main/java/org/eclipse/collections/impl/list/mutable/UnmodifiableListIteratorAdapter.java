/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import java.util.ListIterator;

public class UnmodifiableListIteratorAdapter<T>
        implements ListIterator<T>
{
    private final ListIterator<? extends T> iterator;

    public UnmodifiableListIteratorAdapter(ListIterator<T> iterator)
    {
        this.iterator = iterator;
    }

    public boolean hasNext()
    {
        return this.iterator.hasNext();
    }

    public T next()
    {
        return this.iterator.next();
    }

    public boolean hasPrevious()
    {
        return this.iterator.hasPrevious();
    }

    public T previous()
    {
        return this.iterator.previous();
    }

    public int nextIndex()
    {
        return this.iterator.nextIndex();
    }

    public int previousIndex()
    {
        return this.iterator.previousIndex();
    }

    public void remove()
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    public void set(T o)
    {
        throw new UnsupportedOperationException("Cannot call set() on " + this.getClass().getSimpleName());
    }

    public void add(T o)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }
}
