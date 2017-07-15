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

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.lazy.iterator.ZipIterator;
import org.eclipse.collections.impl.utility.internal.IterableIterate;

/**
 * A ZipIterable is an iterable that transforms a source iterable on a condition as it iterates.
 */
public class ZipIterable<X, Y>
        extends AbstractLazyIterable<Pair<X, Y>>
{
    private final Iterable<X> xs;
    private final Iterable<Y> ys;

    public ZipIterable(Iterable<X> xs, Iterable<Y> ys)
    {
        this.xs = xs;
        this.ys = ys;
    }

    @Override
    public Iterator<Pair<X, Y>> iterator()
    {
        return new ZipIterator<>(this.xs, this.ys);
    }

    @Override
    public void each(Procedure<? super Pair<X, Y>> procedure)
    {
        IterableIterate.forEach(this, procedure);
    }
}
