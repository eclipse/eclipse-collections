/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import net.jcip.annotations.Immutable;

/**
 * A Singleton iterator which is empty and can be used by all empty collections.
 */
@Immutable
public final class EmptyIterator<T>
        implements ListIterator<T>
{
    private static final EmptyIterator<?> INSTANCE = new EmptyIterator<Object>();

    private EmptyIterator()
    {
    }

    public static <T> EmptyIterator<T> getInstance()
    {
        return (EmptyIterator<T>) INSTANCE;
    }

    public boolean hasNext()
    {
        return false;
    }

    public T next()
    {
        throw new NoSuchElementException();
    }

    public void remove()
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    public boolean hasPrevious()
    {
        return false;
    }

    public T previous()
    {
        throw new NoSuchElementException();
    }

    public int nextIndex()
    {
        return 0;
    }

    public int previousIndex()
    {
        return -1;
    }

    public void set(T t)
    {
        throw new UnsupportedOperationException("Cannot call set() on " + this.getClass().getSimpleName());
    }

    public void add(T t)
    {
        throw new UnsupportedOperationException("Cannot call add() on " + this.getClass().getSimpleName());
    }
}
