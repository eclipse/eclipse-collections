/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.primitive;

import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;

/**
 * Applies a byteFunction to an object and adds the result to a target byte collection.
 */
public final class CollectByteProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final ByteFunction<? super T> byteFunction;
    private final MutableByteCollection byteCollection;

    public CollectByteProcedure(ByteFunction<? super T> byteFunction, MutableByteCollection targetCollection)
    {
        this.byteFunction = byteFunction;
        this.byteCollection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        byte value = this.byteFunction.byteValueOf(object);
        this.byteCollection.add(value);
    }

    public MutableByteCollection getByteCollection()
    {
        return this.byteCollection;
    }
}
