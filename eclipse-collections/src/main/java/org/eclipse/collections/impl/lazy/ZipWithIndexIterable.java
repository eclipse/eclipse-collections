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
import org.eclipse.collections.impl.lazy.iterator.ZipWithIndexIterator;
import org.eclipse.collections.impl.utility.internal.IterableIterate;

/**
 * A CollectIterable is an iterable that transforms a source iterable on a condition as it iterates.
 */
public class ZipWithIndexIterable<T>
        extends AbstractLazyIterable<Pair<T, Integer>>
{
    private final Iterable<T> iterable;

    public ZipWithIndexIterable(Iterable<T> iterable)
    {
        this.iterable = iterable;
    }

    @Override
    public Iterator<Pair<T, Integer>> iterator()
    {
        return new ZipWithIndexIterator<>(this.iterable);
    }

    @Override
    public void each(Procedure<? super Pair<T, Integer>> procedure)
    {
        IterableIterate.forEach(this, procedure);
    }
}
