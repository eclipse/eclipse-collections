/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked;

import java.io.IOException;
import java.io.ObjectOutput;

import org.eclipse.collections.api.RichIterable;

public class MultimapKeyValuesSerializingProcedure<K, V>
        extends CheckedProcedure2<K, RichIterable<V>>
{
    private static final long serialVersionUID = 1L;
    private final ObjectOutput out;

    public MultimapKeyValuesSerializingProcedure(ObjectOutput out)
    {
        this.out = out;
    }

    @Override
    public void safeValue(K key, RichIterable<V> iterable) throws IOException
    {
        this.out.writeObject(key);
        this.out.writeInt(iterable.size());
        iterable.forEach(new CheckedProcedure<V>()
        {
            public void safeValue(V object) throws IOException
            {
                MultimapKeyValuesSerializingProcedure.this.out.writeObject(object);
            }
        });
    }
}
