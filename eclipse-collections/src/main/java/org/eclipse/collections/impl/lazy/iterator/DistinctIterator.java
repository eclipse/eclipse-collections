/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

public final class DistinctIterator<T>
        implements Iterator<T>
{
    private static final Object NULL = new Object();
    private final Iterator<T> iterator;
    private final MutableSet<T> seenSoFar = UnifiedSet.newSet();
    private Object next = NULL;

    public DistinctIterator(Iterable<T> iterable)
    {
        this(iterable.iterator());
    }

    public DistinctIterator(Iterator<T> iterator)
    {
        this.iterator = iterator;
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove from a distinct iterator");
    }

    @Override
    public boolean hasNext()
    {
        if (this.next != NULL)
        {
            return true;
        }
        while (this.iterator.hasNext())
        {
            T temp = this.iterator.next();
            if (this.seenSoFar.add(temp))
            {
                this.next = temp;
                return true;
            }
        }
        return false;
    }

    @Override
    public T next()
    {
        if (this.next != NULL || this.hasNext())
        {
            Object temp = this.next;
            this.next = NULL;
            return (T) temp;
        }
        throw new NoSuchElementException();
    }
}
