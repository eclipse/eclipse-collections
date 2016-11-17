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

import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;

/**
 * Applies an intFunction to an object and adds the result to a target int collection.
 */
public final class CollectIntProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final IntFunction<? super T> intFunction;
    private final MutableIntCollection intCollection;

    public CollectIntProcedure(IntFunction<? super T> intFunction, MutableIntCollection targetCollection)
    {
        this.intFunction = intFunction;
        this.intCollection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        int value = this.intFunction.intValueOf(object);
        this.intCollection.add(value);
    }

    public MutableIntCollection getIntCollection()
    {
        return this.intCollection;
    }
}
