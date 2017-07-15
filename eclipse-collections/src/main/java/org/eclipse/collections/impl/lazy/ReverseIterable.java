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
import java.util.ListIterator;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.impl.block.factory.Procedures;

/**
 * A ReverseIterable is an iterable that wraps another iterable and iterates in reverse order.
 */
public class ReverseIterable<T>
        extends AbstractLazyIterable<T>
{
    private final ListIterable<T> adapted;

    public ReverseIterable(ListIterable<T> newAdapted)
    {
        this.adapted = newAdapted;
    }

    public static <T> ReverseIterable<T> adapt(ListIterable<T> listIterable)
    {
        return new ReverseIterable<>(listIterable);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        this.adapted.reverseForEach(procedure);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.adapted.reverseForEach(Procedures.fromObjectIntProcedure(objectIntProcedure));
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.adapted.reverseForEach(Procedures.bind(procedure, parameter));
    }

    @Override
    public int size()
    {
        return this.adapted.size();
    }

    @Override
    public T getFirst()
    {
        return this.adapted.getLast();
    }

    @Override
    public T getLast()
    {
        return this.adapted.getFirst();
    }

    @Override
    public T getOnly()
    {
        return this.adapted.getOnly();
    }

    @Override
    public boolean isEmpty()
    {
        return this.adapted.isEmpty();
    }

    @Override
    public Iterator<T> iterator()
    {
        ListIterator<T> listIterator = this.adapted.listIterator(this.adapted.size());
        return new ReverseIterator<>(listIterator);
    }

    private static final class ReverseIterator<T> implements Iterator<T>
    {
        private final ListIterator<T> listIterator;

        private ReverseIterator(ListIterator<T> listIterator)
        {
            this.listIterator = listIterator;
        }

        @Override
        public boolean hasNext()
        {
            return this.listIterator.hasPrevious();
        }

        @Override
        public T next()
        {
            return this.listIterator.previous();
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
        }
    }
}
