/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.eclipse.collections.api.map.MutableMapIterable;

public class SynchronizedMapSerializationProxy<K, V> implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private MutableMapIterable<K, V> map;

    @SuppressWarnings("UnusedDeclaration")
    public SynchronizedMapSerializationProxy()
    {
        // Empty constructor for Externalizable class
    }

    public SynchronizedMapSerializationProxy(MutableMapIterable<K, V> map)
    {
        this.map = map;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.map);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.map = (MutableMapIterable<K, V>) in.readObject();
    }

    protected Object readResolve()
    {
        return this.map.asSynchronized();
    }
}
