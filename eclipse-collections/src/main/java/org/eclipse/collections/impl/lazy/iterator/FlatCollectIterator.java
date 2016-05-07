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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.impl.EmptyIterator;

public final class FlatCollectIterator<T, V> implements Iterator<V>
{
    private final Iterator<T> iterator;
    private final Function<? super T, ? extends Iterable<V>> function;
    private Iterator<V> innerIterator = EmptyIterator.getInstance();

    public FlatCollectIterator(
            Iterable<T> iterable,
            Function<? super T, ? extends Iterable<V>> newFunction)
    {
        this(iterable.iterator(), newFunction);
    }

    public FlatCollectIterator(
            Iterator<T> newIterator,
            Function<? super T, ? extends Iterable<V>> newFunction)
    {
        this.iterator = newIterator;
        this.function = newFunction;
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove from a flatCollect iterator");
    }

    @Override
    public boolean hasNext()
    {
        while (true)
        {
            if (this.innerIterator.hasNext())
            {
                return true;
            }
            if (!this.iterator.hasNext())
            {
                return false;
            }
            this.innerIterator = this.function.valueOf(this.iterator.next()).iterator();
        }
    }

    @Override
    public V next()
    {
        if (!this.hasNext())
        {
            throw new NoSuchElementException();
        }
        return this.innerIterator.next();
    }
}
