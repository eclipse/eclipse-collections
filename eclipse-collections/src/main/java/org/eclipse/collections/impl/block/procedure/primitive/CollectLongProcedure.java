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

import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;

/**
 * Applies a longFunction to an object and adds the result to a target long collection.
 */
public final class CollectLongProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final LongFunction<? super T> longFunction;
    private final MutableLongCollection longCollection;

    public CollectLongProcedure(LongFunction<? super T> longFunction, MutableLongCollection targetCollection)
    {
        this.longFunction = longFunction;
        this.longCollection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        long value = this.longFunction.longValueOf(object);
        this.longCollection.add(value);
    }

    public MutableLongCollection getLongCollection()
    {
        return this.longCollection;
    }
}
