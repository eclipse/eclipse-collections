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

public final class CollectIterator<T, V>
        implements Iterator<V>
{
    private final Iterator<T> iterator;
    private final Function<? super T, ? extends V> function;

    public CollectIterator(Iterable<T> iterable, Function<? super T, ? extends V> function)
    {
        this(iterable.iterator(), function);
    }

    public CollectIterator(Iterator<T> newIterator, Function<? super T, ? extends V> function)
    {
        this.iterator = newIterator;
        this.function = function;
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove from a collect iterator");
    }

    @Override
    public boolean hasNext()
    {
        return this.iterator.hasNext();
    }

    @Override
    public V next()
    {
        if (this.hasNext())
        {
            return this.function.valueOf(this.iterator.next());
        }
        throw new NoSuchElementException();
    }
}
