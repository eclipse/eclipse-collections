/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.collections.api.set.MultiReaderSet;
import org.eclipse.collections.api.set.MutableSet;

public final class MultiReaderSetAdapter<T> extends AbstractMultiReaderSet<T>
{
    MultiReaderSetAdapter(MutableSet<T> newDelegate, ReadWriteLock newLock)
    {
        this.lock = newLock;
        this.lockWrapper = new ReadWriteLockWrapper(newLock);
        this.delegate = newDelegate;
    }

    public static <E> MultiReaderSet<E> adapt(Set<E> set)
    {
        return MultiReaderSetAdapter.adapt(set, new ReentrantReadWriteLock());
    }

    public static <E> MultiReaderSet<E> adapt(Set<E> set, ReadWriteLock newLock)
    {
        if (set instanceof MultiReaderSet)
        {
            return (MultiReaderSet<E>) set;
        }
        return new MultiReaderSetAdapter<>(SetAdapter.adapt(set), newLock);
    }

    @Override
    public MutableSet<T> newEmpty()
    {
        return MultiReaderUnifiedSet.newSet();
    }

    @Override
    protected <K> AbstractMultiReaderSet<K> newSetFrom(MutableSet<K> newDelegate)
    {
        return MultiReaderUnifiedSet.newSet(newDelegate);
    }
}
