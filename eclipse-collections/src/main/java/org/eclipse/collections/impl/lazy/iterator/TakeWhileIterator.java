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

import org.eclipse.collections.api.block.predicate.Predicate;

/**
 * Iterates over the elements of the iterator until predicate returns false.
 *
 * @since 8.0
 */
public final class TakeWhileIterator<T> implements Iterator<T>
{
    private static final Object NULL = new Object();
    private final Iterator<T> iterator;
    private final Predicate<? super T> predicate;

    private boolean terminate;
    private Object next = NULL;

    public TakeWhileIterator(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        this(iterable.iterator(), predicate);
    }

    public TakeWhileIterator(Iterator<T> iterator, Predicate<? super T> predicate)
    {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext()
    {
        if (this.next != NULL)
        {
            return true;
        }
        if (!this.terminate && this.iterator.hasNext())
        {
            T temp = this.iterator.next();
            if (this.predicate.accept(temp))
            {
                this.next = temp;
                return true;
            }

            this.terminate = true;
            this.next = NULL;
        }
        return false;
    }

    @Override
    public T next()
    {
        if (this.hasNext())
        {
            Object temp = this.next;
            this.next = NULL;
            return (T) temp;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove from a takeWhile iterator");
    }
}
