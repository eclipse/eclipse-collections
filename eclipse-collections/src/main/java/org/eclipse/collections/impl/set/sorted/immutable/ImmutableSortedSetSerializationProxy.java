/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.sorted.immutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Comparator;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

class ImmutableSortedSetSerializationProxy<T> implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private ImmutableSortedSet<T> set;

    @SuppressWarnings("UnusedDeclaration")
    public ImmutableSortedSetSerializationProxy()
    {
        // Empty constructor for Externalizable class
    }

    ImmutableSortedSetSerializationProxy(ImmutableSortedSet<T> set)
    {
        this.set = set;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.set.comparator());
        out.writeInt(this.set.size());
        try
        {
            this.set.forEach(new CheckedProcedure<T>()
            {
                public void safeValue(T each) throws IOException
                {
                    out.writeObject(each);
                }
            });
        }
        catch (RuntimeException e)
        {
            if (e.getCause() instanceof IOException)
            {
                throw (IOException) e.getCause();
            }
            throw e;
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        Comparator<T> comparator = (Comparator<T>) in.readObject();
        int size = in.readInt();
        MutableSortedSet<T> deserializedSet = new TreeSortedSet<>(comparator);

        for (int i = 0; i < size; i++)
        {
            deserializedSet.add((T) in.readObject());
        }

        this.set = deserializedSet.toImmutable();
    }

    protected Object readResolve()
    {
        return this.set.toImmutable();
    }
}
