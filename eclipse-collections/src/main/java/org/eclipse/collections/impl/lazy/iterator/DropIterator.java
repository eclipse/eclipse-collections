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

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.impl.block.predicate.DropIterablePredicate;

/**
 * Iterates over the elements of the iterator skipping the first count elements or the full iterator if the count is
 * non-positive.
 *
 * @deprecated in 8.0. Use {@link SelectIterator#SelectIterator(Iterable, Predicate)} with {@link DropIterablePredicate#DropIterablePredicate(int)} as a predicate instead.
 */
@Deprecated
public final class DropIterator<T> implements Iterator<T>
{
    private final Iterator<T> delegateIterator;

    public DropIterator(Iterable<T> iterable, int count)
    {
        this(iterable.iterator(), count);
    }

    public DropIterator(Iterator<T> iterator, int count)
    {
        this.delegateIterator = new SelectIterator<>(iterator, new DropIterablePredicate<>(count));
    }

    @Override
    public boolean hasNext()
    {
        return this.delegateIterator.hasNext();
    }

    @Override
    public T next()
    {
        return this.delegateIterator.next();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove from a drop iterator");
    }
}
