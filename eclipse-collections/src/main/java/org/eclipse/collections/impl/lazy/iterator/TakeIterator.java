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

/**
 * Iterates over the first count elements of the iterator or the full size of the iterator
 * if the count is greater than the length of the receiver.
 */
public final class TakeIterator<T> implements Iterator<T>
{
    private final Iterator<T> iterator;
    private final int count;

    private int currentIndex;

    public TakeIterator(Iterable<T> iterable, int count)
    {
        this(iterable.iterator(), count);
    }

    public TakeIterator(Iterator<T> iterator, int count)
    {
        this.iterator = iterator;
        this.count = count;
    }

    @Override
    public boolean hasNext()
    {
        return this.currentIndex < this.count && this.iterator.hasNext();
    }

    @Override
    public T next()
    {
        if (this.hasNext())
        {
            this.currentIndex++;
            return this.iterator.next();
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove from a take iterator");
    }
}
