/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.immutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.eclipse.collections.api.stack.StackIterable;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure;
import org.eclipse.collections.impl.factory.Stacks;
import org.eclipse.collections.impl.list.mutable.FastList;

class ImmutableStackSerializationProxy<T> implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private StackIterable<T> stack;

    @SuppressWarnings("UnusedDeclaration")
    public ImmutableStackSerializationProxy()
    {
        // Empty constructor for Externalizable class
    }

    protected ImmutableStackSerializationProxy(StackIterable<T> stack)
    {
        this.stack = stack;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(this.stack.size());
        try
        {
            this.stack.forEach(new CheckedProcedure<T>()
            {
                public void safeValue(T object) throws IOException
                {
                    out.writeObject(object);
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
        int size = in.readInt();
        FastList<T> deserializedDelegate = new FastList<>(size);

        for (int i = 0; i < size; i++)
        {
            deserializedDelegate.add((T) in.readObject());
        }

        this.stack = Stacks.immutable.withAllReversed(deserializedDelegate);
    }

    protected Object readResolve()
    {
        return this.stack;
    }
}
