/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.impl.collection.AbstractSynchronizedRichIterable;

/**
 * A synchronized view of a RichIterable.
 *
 * @since 5.0
 */
public class SynchronizedRichIterable<T>
        extends AbstractSynchronizedRichIterable<T>
        implements Serializable
{
    private static final long serialVersionUID = 2L;

    protected SynchronizedRichIterable(RichIterable<T> iterable)
    {
        this(iterable, null);
    }

    protected SynchronizedRichIterable(RichIterable<T> iterable, Object newLock)
    {
        super(iterable, newLock);
    }

    /**
     * This method will take a RichIterable and wrap it directly in a SynchronizedRichIterable.
     */
    public static <E> SynchronizedRichIterable<E> of(RichIterable<E> iterable)
    {
        return new SynchronizedRichIterable<>(iterable);
    }

    protected Object writeReplace()
    {
        return new SynchronizedRichIterableSerializationProxy<>(this.getDelegate());
    }

    /**
     * This method will take a RichIterable and wrap it directly in a SynchronizedRichIterable. Additionally,
     * a developer specifies which lock to use with the collection.
     */
    public static <E> SynchronizedRichIterable<E> of(RichIterable<E> iterable, Object lock)
    {
        return new SynchronizedRichIterable<>(iterable, lock);
    }

    public static class SynchronizedRichIterableSerializationProxy<T> implements Externalizable
    {
        private static final long serialVersionUID = 1L;

        private RichIterable<T> richIterable;

        @SuppressWarnings("UnusedDeclaration")
        public SynchronizedRichIterableSerializationProxy()
        {
            // Empty constructor for Externalizable class
        }

        public SynchronizedRichIterableSerializationProxy(RichIterable<T> iterable)
        {
            this.richIterable = iterable;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException
        {
            out.writeObject(this.richIterable);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        {
            this.richIterable = (RichIterable<T>) in.readObject();
        }

        protected Object readResolve()
        {
            return new SynchronizedRichIterable<>(this.richIterable);
        }
    }
}
