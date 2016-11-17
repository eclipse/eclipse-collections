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

import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;

/**
 * Applies a doubleFunction to an object and adds the result to a target double collection.
 */
public final class CollectDoubleProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final DoubleFunction<? super T> doubleFunction;
    private final MutableDoubleCollection doubleCollection;

    public CollectDoubleProcedure(DoubleFunction<? super T> doubleFunction, MutableDoubleCollection targetCollection)
    {
        this.doubleFunction = doubleFunction;
        this.doubleCollection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        double value = this.doubleFunction.doubleValueOf(object);
        this.doubleCollection.add(value);
    }

    public MutableDoubleCollection getDoubleCollection()
    {
        return this.doubleCollection;
    }
}
