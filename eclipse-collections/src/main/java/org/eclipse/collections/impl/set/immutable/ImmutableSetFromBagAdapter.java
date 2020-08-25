/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.set.ImmutableSet;

/**
 * This class provides a {@link ImmutableSet} wrapper around any Bag interface instance.
 */
public final class ImmutableSetFromBagAdapter<T>
        extends AbstractImmutableSet<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final Bag<T> delegate;

    public ImmutableSetFromBagAdapter(Bag<T> delegate)
    {
        this.delegate = delegate;
    }

    private Object writeReplace()
    {
        return new ImmutableSetSerializationProxy<>(this);
    }

    @Override
    public int size()
    {
        return this.delegate.sizeDistinct();
    }

    @Override
    public T getFirst()
    {
        return this.delegate.getFirst();
    }

    @Override
    public T getLast()
    {
        return this.delegate.getLast();
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
    public boolean contains(Object object)
    {
        return this.delegate.contains(object);
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
