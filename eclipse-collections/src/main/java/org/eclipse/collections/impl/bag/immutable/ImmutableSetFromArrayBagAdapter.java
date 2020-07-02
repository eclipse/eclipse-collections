/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.UnmodifiableIteratorAdapter;
import org.eclipse.collections.impl.set.immutable.AbstractImmutableSet;
import org.eclipse.collections.impl.utility.ArrayIterate;

final class ImmutableSetFromArrayBagAdapter<T> extends AbstractImmutableSet<T>
{
    private final T[] elements;

    ImmutableSetFromArrayBagAdapter(T[] elements)
    {
        this.elements = elements;
    }

    @Override
    public int size()
    {
        return this.elements.length;
    }

    @Override
    public T getFirst()
    {
        return ArrayIterate.getFirst(this.elements);
    }

    @Override
    public T getLast()
    {
        return ArrayIterate.getLast(this.elements);
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        ArrayIterate.forEach(this.elements, procedure);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new UnmodifiableIteratorAdapter<>(Arrays.asList(this.elements).iterator());
    }

    @Override
    public Spliterator<T> spliterator()
    {
        return Spliterators.spliterator(this.elements, Spliterator.DISTINCT);
    }

    @Override
    public int hashCode()
    {
        int result = 0;
        for (T each : this.elements)
        {
            result += each.hashCode();
        }

        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }

        if (!(o instanceof Set))
        {
            return false;
        }

        Set<?> s = (Set<?>) o;
        if (s.size() != this.size())
        {
            return false;
        }

        try
        {
            return this.containsAll(s);
        }
        catch (ClassCastException ignored)
        {
            return false;
        }
        catch (NullPointerException ignored)
        {
            return false;
        }
    }
}
