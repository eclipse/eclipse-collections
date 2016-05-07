/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.impl.block.procedure.checked.MultimapKeyValuesSerializingProcedure;

public abstract class ImmutableMultimapSerializationProxy<K, V, R extends RichIterable<V>>
{
    private Multimap<K, V> multimapToReadInto;
    private ImmutableMap<K, R> mapToWrite;

    protected ImmutableMultimapSerializationProxy()
    {
    }

    protected ImmutableMultimapSerializationProxy(ImmutableMap<K, R> immutableMap)
    {
        this.mapToWrite = immutableMap;
    }

    protected abstract AbstractMutableMultimap<K, V, ?> createEmptyMutableMultimap();

    protected Object readResolve()
    {
        return this.multimapToReadInto.toImmutable();
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        int keysCount = this.mapToWrite.size();
        out.writeInt(keysCount);
        this.mapToWrite.forEachKeyValue(new MultimapKeyValuesSerializingProcedure<>(out));
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        AbstractMutableMultimap<K, V, ?> toReadInto = this.createEmptyMutableMultimap();
        toReadInto.readValuesFrom(in);
        this.multimapToReadInto = toReadInto;
    }
}
