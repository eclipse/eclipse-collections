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

import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;

/**
 * Applies a floatFunction to an object and adds the result to a target float collection.
 */
public final class CollectFloatProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final FloatFunction<? super T> floatFunction;
    private final MutableFloatCollection floatCollection;

    public CollectFloatProcedure(FloatFunction<? super T> floatFunction, MutableFloatCollection targetCollection)
    {
        this.floatFunction = floatFunction;
        this.floatCollection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        float value = this.floatFunction.floatValueOf(object);
        this.floatCollection.add(value);
    }

    public MutableFloatCollection getFloatCollection()
    {
        return this.floatCollection;
    }
}
