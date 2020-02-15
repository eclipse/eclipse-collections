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

import java.util.Iterator;
import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.api.set.UnsortedSetIterable;
import org.eclipse.collections.impl.UnmodifiableIteratorAdapter;

/**
 * This class provides a ImmutableSet wrapper around any UnsortedSetIterable interface instance.
 * <p>
 * To create a new wrapper around an existing UnsortedSetIterable instance, use the {@link #adapt(UnsortedSetIterable)} factory method.
 */
public final class ImmutableSetAdapter<T> extends AbstractImmutableSet<T>
{
    private final UnsortedSetIterable<T> delegate;

    ImmutableSetAdapter(UnsortedSetIterable<T> delegate)
    {
        this.delegate = delegate;
    }

    public static <E> ImmutableSet<E> adapt(UnsortedSetIterable<E> set)
    {
        if (set instanceof ImmutableSet)
        {
            return (ImmutableSet<E>) set;
        }
        return new ImmutableSetAdapter<>(set);
    }

    @Override
    public int size()
    {
        return this.delegate.size();
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
        this.delegate.each(procedure);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new UnmodifiableIteratorAdapter<>(this.delegate.iterator());
    }

    @Override
    public boolean contains(Object object)
    {
        return this.delegate.contains(object);
    }

    @Override
    public ParallelUnsortedSetIterable<T> asParallel(ExecutorService executorService, int batchSize)
    {
        return this.delegate.asParallel(executorService, batchSize);
    }

    @Override
    public int hashCode()
    {
        return this.delegate.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.delegate.equals(obj);
    }
}
