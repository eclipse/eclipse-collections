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

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ImmutableIterator<T> implements Iterator<T>
{
    /**
     * Index of element to be returned by subsequent call to next.
     */
    protected int currentIndex;

    protected final List<T> list;

    public ImmutableIterator(List<T> list)
    {
        this.list = list;
    }

    @Override
    public boolean hasNext()
    {
        return this.currentIndex != this.list.size();
    }

    @Override
    public T next()
    {
        try
        {
            T result = this.list.get(this.currentIndex);
            this.currentIndex++;
            return result;
        }
        catch (IndexOutOfBoundsException ignored)
        {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }
}
