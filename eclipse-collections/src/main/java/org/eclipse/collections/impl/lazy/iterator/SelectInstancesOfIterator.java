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

public final class SelectInstancesOfIterator<T>
        implements Iterator<T>
{
    private static final Object NULL = new Object();
    private final Iterator<?> iterator;
    private final Class<T> clazz;
    private Object next = NULL;

    public SelectInstancesOfIterator(Iterable<?> iterable, Class<T> clazz)
    {
        this(iterable.iterator(), clazz);
    }

    public SelectInstancesOfIterator(Iterator<?> iterator, Class<T> clazz)
    {
        this.iterator = iterator;
        this.clazz = clazz;
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove from a selectInstances iterator");
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
            Object temp = this.iterator.next();
            if (this.clazz.isInstance(temp))
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
