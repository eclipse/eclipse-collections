/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.Iterator;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.lazy.iterator.DistinctIterator;
import org.eclipse.collections.impl.utility.internal.IterableIterate;
import net.jcip.annotations.Immutable;

/**
 * A DistinctIterable is an iterable that eliminates duplicates from a source iterable as it iterates.
 *
 * @since 5.0
 */
@Immutable
public class DistinctIterable<T>
        extends AbstractLazyIterable<T>
{
    private final Iterable<T> adapted;

    public DistinctIterable(Iterable<T> newAdapted)
    {
        this.adapted = newAdapted;
    }

    @Override
    public LazyIterable<T> distinct()
    {
        return this;
    }

    public void each(Procedure<? super T> procedure)
    {
        IterableIterate.forEach(this, procedure);
    }

    public Iterator<T> iterator()
    {
        return new DistinctIterator<T>(this.adapted);
    }
}
