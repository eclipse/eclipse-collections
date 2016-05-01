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

import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;

/**
 * Applies a shortFunction to an object and adds the result to a target short collection.
 */
public final class CollectShortProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final ShortFunction<? super T> shortFunction;
    private final MutableShortCollection shortCollection;

    public CollectShortProcedure(ShortFunction<? super T> shortFunction, MutableShortCollection targetCollection)
    {
        this.shortFunction = shortFunction;
        this.shortCollection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        short value = this.shortFunction.shortValueOf(object);
        this.shortCollection.add(value);
    }

    public MutableShortCollection getShortCollection()
    {
        return this.shortCollection;
    }
}
