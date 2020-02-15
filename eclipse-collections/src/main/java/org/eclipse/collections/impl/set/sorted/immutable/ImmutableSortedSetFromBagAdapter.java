/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.sorted.immutable;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.utility.Iterate;
import org.eclipse.collections.impl.utility.ListIterate;
import org.eclipse.collections.impl.utility.internal.IterableIterate;
import org.eclipse.collections.impl.utility.internal.SortedSetIterables;

/**
 * This class provides a {@link ImmutableSet} wrapper around any Bag interface instance.
 */
public final class ImmutableSortedSetFromBagAdapter<T>
        extends AbstractImmutableSortedSet<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final SortedBag<T> delegate;

    public ImmutableSortedSetFromBagAdapter(SortedBag<T> delegate)
    {
        this.delegate = delegate;
    }

    private Object writeReplace()
    {
        return new ImmutableSortedSetSerializationProxy<>(this);
    }

    @Override
    public boolean contains(Object object)
    {
        return this.delegate.contains(object);
    }

    @Override
    public ImmutableSortedSet<T> take(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        if (count >= this.size())
        {
            return this;
        }
        if (count == 0)
        {
            return SortedSets.immutable.empty(this.comparator());
        }
        return SortedSets.immutable.ofAll(this.comparator(), this.delegate.distinctView().asLazy().take(count));
    }

    @Override
    public ImmutableSortedSet<T> drop(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        if (count == 0)
        {
            return this;
        }
        if (count >= this.size())
        {
            return SortedSets.immutable.empty(this.comparator());
        }
        return SortedSets.immutable.ofAll(this.comparator(), this.delegate.distinctView().asLazy().drop(count));
    }

    @Override
    public Comparator<? super T> comparator()
    {
        return this.delegate.comparator();
    }

    @Override
    public int size()
    {
        return this.delegate.sizeDistinct();
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        this.delegate.distinctView().each(procedure);
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.delegate.distinctView().iterator();
    }

    @Override
    public int indexOf(Object object)
    {
        return Iterate.indexOf(this.delegate.distinctView(), object);
    }

    @Override
    public void forEach(int fromIndex, int toIndex, Procedure<? super T> procedure)
    {
        ListIterate.rangeCheck(fromIndex, toIndex, this.size());

        if (fromIndex > toIndex)
        {
            throw new IllegalArgumentException("fromIndex must not be greater than toIndex");
        }
        IterableIterate.forEach(this.delegate.distinctView(), fromIndex, toIndex, procedure);
    }

    @Override
    public void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super T> objectIntProcedure)
    {
        ListIterate.rangeCheck(fromIndex, toIndex, this.size());

        if (fromIndex > toIndex)
        {
            throw new IllegalArgumentException("fromIndex must not be greater than toIndex");
        }
        IterableIterate.forEachWithIndex(this.delegate.distinctView(), fromIndex, toIndex, objectIntProcedure);
    }

    @Override
    public T first()
    {
        return this.delegate.getFirst();
    }

    @Override
    public T last()
    {
        return this.delegate.getLast();
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

        return this.containsAll(otherSet);
    }

    @Override
    public int hashCode()
    {
        int result = 0;
        for (T each : this.delegate.distinctView())
        {
            result += each.hashCode();
        }

        return result;
    }
}
