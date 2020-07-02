/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.collections.api.set.MutableSet;

/**
 * MultiReadUnifiedSet provides a thread-safe wrapper around a UnifiedSet, using a ReentrantReadWriteLock. In order to
 * provide true thread-safety, MultiReaderUnifiedSet does <em>not</em> implement {@code iterator()} as this method requires an external
 * lock to be taken to provide thread-safe iteration. You can use an {@code iterator()} if you use the
 * {@code withReadLockAndDelegate()} or {@code withWriteLockAndDelegate()} methods. Both of these methods take a parameter of type
 * {@code Procedure<MutableSet>}, and a wrapped version of the underlying Unified is returned. This wrapper guarantees that no
 * external pointer can ever reference the underlying UnifiedSet outside of a locked procedure. In the case of the read
 * lock method, an Unmodifiable version of the collection is offered, which will throw UnsupportedOperationExceptions on
 * any write methods like add or remove.
 */
public final class MultiReaderUnifiedSet<T> extends AbstractMultiReaderSet<T> implements Externalizable
{
    private static final long serialVersionUID = 1L;

    /**
     * @deprecated Empty default constructor used for serialization.
     */
    @SuppressWarnings("UnusedDeclaration")
    @Deprecated
    public MultiReaderUnifiedSet()
    {
        // For Externalizable use only
    }

    private MultiReaderUnifiedSet(MutableSet<T> newDelegate)
    {
        this(newDelegate, new ReentrantReadWriteLock());
    }

    private MultiReaderUnifiedSet(MutableSet<T> newDelegate, ReadWriteLock newLock)
    {
        this.lock = newLock;
        this.lockWrapper = new ReadWriteLockWrapper(newLock);
        this.delegate = newDelegate;
    }

    public static <T> MultiReaderUnifiedSet<T> newSet()
    {
        return new MultiReaderUnifiedSet<>(UnifiedSet.newSet());
    }

    public static <T> MultiReaderUnifiedSet<T> newSet(int capacity)
    {
        return new MultiReaderUnifiedSet<>(UnifiedSet.newSet(capacity));
    }

    public static <T> MultiReaderUnifiedSet<T> newSet(Iterable<T> iterable)
    {
        return new MultiReaderUnifiedSet<>(UnifiedSet.newSet(iterable));
    }

    @SafeVarargs
    public static <T> MultiReaderUnifiedSet<T> newSetWith(T... elements)
    {
        return new MultiReaderUnifiedSet<>(UnifiedSet.newSetWith(elements));
    }

    @Override
    protected <K> AbstractMultiReaderSet<K> newSetFrom(MutableSet<K> newDelegate)
    {
        return MultiReaderUnifiedSet.newSet(newDelegate);
    }

    @Override
    public MutableSet<T> newEmpty()
    {
        return MultiReaderUnifiedSet.newSet();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.delegate);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.delegate = (MutableSet<T>) in.readObject();
        this.lock = new ReentrantReadWriteLock();
        this.lockWrapper = new ReadWriteLockWrapper(this.lock);
    }
}
