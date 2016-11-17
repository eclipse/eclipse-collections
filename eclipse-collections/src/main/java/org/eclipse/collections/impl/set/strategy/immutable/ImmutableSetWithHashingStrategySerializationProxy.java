/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.strategy.immutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;

class ImmutableSetWithHashingStrategySerializationProxy<T> implements Externalizable
{
    private static final long serialVersionUID = 1L;
    private ImmutableSet<T> set;
    private HashingStrategy<? super T> hashingStrategy;

    @SuppressWarnings("UnusedDeclaration")
    public ImmutableSetWithHashingStrategySerializationProxy()
    {
        // Empty constructor for Externalizable class
    }

    ImmutableSetWithHashingStrategySerializationProxy(ImmutableSet<T> set, HashingStrategy<? super T> hashingStrategy)
    {
        this.set = set;
        this.hashingStrategy = hashingStrategy;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.hashingStrategy);
        out.writeInt(this.set.size());
        try
        {
            this.set.forEach(new CheckedProcedure<T>()
            {
                public void safeValue(T value) throws IOException
                {
                    out.writeObject(value);
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
        HashingStrategy<? super T> strategy = (HashingStrategy<? super T>) in.readObject();
        int size = in.readInt();
        UnifiedSetWithHashingStrategy<T> deserializedSet = UnifiedSetWithHashingStrategy.newSet(strategy);

        for (int i = 0; i < size; i++)
        {
            deserializedSet.put((T) in.readObject());
        }

        this.set = deserializedSet.toImmutable();
    }

    protected Object readResolve()
    {
        return this.set;
    }
}
