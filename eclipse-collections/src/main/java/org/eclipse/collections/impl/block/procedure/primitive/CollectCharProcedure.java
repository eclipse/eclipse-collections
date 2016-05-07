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

import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;

/**
 * Applies a charFunction to an object and adds the result to a target char collection.
 */
public final class CollectCharProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final CharFunction<? super T> charFunction;
    private final MutableCharCollection charCollection;

    public CollectCharProcedure(CharFunction<? super T> charFunction, MutableCharCollection targetCollection)
    {
        this.charFunction = charFunction;
        this.charCollection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        char value = this.charFunction.charValueOf(object);
        this.charCollection.add(value);
    }

    public MutableCharCollection getCharCollection()
    {
        return this.charCollection;
    }
}
