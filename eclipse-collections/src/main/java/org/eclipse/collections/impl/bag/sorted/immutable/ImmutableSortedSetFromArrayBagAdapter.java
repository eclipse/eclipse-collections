/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.immutable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.UnmodifiableIteratorAdapter;
import org.eclipse.collections.impl.set.sorted.immutable.AbstractImmutableSortedSet;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.internal.SortedSetIterables;

final class ImmutableSortedSetFromArrayBagAdapter<T> extends AbstractImmutableSortedSet<T>
{
    private final T[] elements;
    private final Comparator<? super T> comparator;

    ImmutableSortedSetFromArrayBagAdapter(T[] elements, Comparator<? super T> comparator)
    {
        this.elements = elements;
        this.comparator = comparator;
    }

    @Override
    public ImmutableSortedSet<T> take(int count)
    {
        return SortedSets.immutable.ofAll(this.comparator, ArrayIterate.take(this.elements, count));
    }

    @Override
    public ImmutableSortedSet<T> drop(int count)
    {
        return SortedSets.immutable.ofAll(this.comparator, ArrayIterate.drop(this.elements, count));
    }

    @Override
    public Comparator<? super T> comparator()
    {
        return this.comparator;
    }

    @Override
    public int size()
    {
        return this.elements.length;
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
        return Spliterators.spliterator(this.elements, Spliterator.DISTINCT | Spliterator.SORTED);
    }

    @Override
    public int indexOf(Object object)
    {
        return ArrayIterate.indexOf(this.elements, object);
    }

    @Override
    public void forEach(int from, int to, Procedure<? super T> procedure)
    {
        ArrayIterate.forEach(this.elements, from, to, procedure);
    }

    @Override
    public void forEachWithIndex(int from, int to, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        ArrayIterate.forEachWithIndex(this.elements, from, to, objectIntProcedure);
    }

    @Override
    public T first()
    {
        return ArrayIterate.getFirst(this.elements);
    }

    @Override
    public T last()
    {
        return ArrayIterate.getLast(this.elements);
    }

    @Override
    public int compareTo(SortedSetIterable<T> o)
    {
        return SortedSetIterables.compare(this, o);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (!(obj instanceof Set))
        {
            return false;
        }
        Set<?> otherSet = (Set<?>) obj;
        if (otherSet.size() != this.size())
        {
            return false;
        }
        try
        {
            return this.containsAll(otherSet);
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
}
