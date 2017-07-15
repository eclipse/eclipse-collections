/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.lazy.iterator.ChunkIterator;
import org.eclipse.collections.impl.utility.internal.IterableIterate;

/**
 * A ChunkIterable is an iterable that partitions a source iterable into fixed size chunks as it iterates.
 */
public class ChunkIterable<T>
        extends AbstractLazyIterable<RichIterable<T>>
{
    private final Iterable<T> iterable;
    private final int size;

    public ChunkIterable(Iterable<T> iterable, int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }

        this.iterable = iterable;
        this.size = size;
    }

    @Override
    public Iterator<RichIterable<T>> iterator()
    {
        return new ChunkIterator<>(this.iterable, this.size);
    }

    @Override
    public void each(Procedure<? super RichIterable<T>> procedure)
    {
        IterableIterate.forEach(this, procedure);
    }
}
