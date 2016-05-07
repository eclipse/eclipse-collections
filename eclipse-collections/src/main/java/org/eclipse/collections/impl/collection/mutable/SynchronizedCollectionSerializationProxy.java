/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.eclipse.collections.api.collection.MutableCollection;

public class SynchronizedCollectionSerializationProxy<T> implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private MutableCollection<T> mutableCollection;

    @SuppressWarnings("UnusedDeclaration")
    public SynchronizedCollectionSerializationProxy()
    {
        // Empty constructor for Externalizable class
    }

    public SynchronizedCollectionSerializationProxy(MutableCollection<T> collection)
    {
        this.mutableCollection = collection;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.mutableCollection);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.mutableCollection = (MutableCollection<T>) in.readObject();
    }

    protected Object readResolve()
    {
        return this.mutableCollection.asSynchronized();
    }
}
