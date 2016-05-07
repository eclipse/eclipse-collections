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

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

public final class ZipWithIndexIterator<T>
        implements Iterator<Pair<T, Integer>>
{
    private final Iterator<T> iterator;
    private int index = 0;

    public ZipWithIndexIterator(Iterable<T> iterable)
    {
        this.iterator = iterable.iterator();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    @Override
    public boolean hasNext()
    {
        return this.iterator.hasNext();
    }

    @Override
    public Pair<T, Integer> next()
    {
        try
        {
            return Tuples.pair(this.iterator.next(), this.index);
        }
        finally
        {
            this.index += 1;
        }
    }
}
