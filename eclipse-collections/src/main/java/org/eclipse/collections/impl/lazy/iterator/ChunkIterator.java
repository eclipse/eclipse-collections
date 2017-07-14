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

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.impl.factory.Lists;

public final class ChunkIterator<T>
        implements Iterator<RichIterable<T>>
{
    private final Iterator<T> iterator;
    private final int size;
    private final Function0<MutableCollection<T>> speciesNewStrategy;

    public ChunkIterator(Iterable<T> iterable, int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }

        this.size = size;
        this.iterator = iterable.iterator();

        this.speciesNewStrategy = iterable instanceof MutableCollection
                ? ((MutableCollection<T>) iterable)::newEmpty
                : Lists.mutable::empty;
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
    public RichIterable<T> next()
    {
        if (!this.iterator.hasNext())
        {
            throw new NoSuchElementException();
        }

        int i = this.size;
        MutableCollection<T> result = this.speciesNewStrategy.value();
        while (i > 0 && this.iterator.hasNext())
        {
            result.add(this.iterator.next());
            i -= 1;
        }

        return result;
    }
}
