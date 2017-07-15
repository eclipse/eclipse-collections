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

public final class ZipIterator<X, Y>
        implements Iterator<Pair<X, Y>>
{
    private final Iterator<X> xIterator;
    private final Iterator<Y> yIterator;

    public ZipIterator(Iterable<X> xs, Iterable<Y> ys)
    {
        this.xIterator = xs.iterator();
        this.yIterator = ys.iterator();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    @Override
    public boolean hasNext()
    {
        return this.xIterator.hasNext() && this.yIterator.hasNext();
    }

    @Override
    public Pair<X, Y> next()
    {
        return Tuples.pair(this.xIterator.next(), this.yIterator.next());
    }
}
