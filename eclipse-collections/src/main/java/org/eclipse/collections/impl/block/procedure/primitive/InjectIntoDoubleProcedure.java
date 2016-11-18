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

import org.eclipse.collections.api.block.function.primitive.DoubleObjectToDoubleFunction;
import org.eclipse.collections.api.block.procedure.Procedure;

public class InjectIntoDoubleProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private double result;
    private final DoubleObjectToDoubleFunction<? super T> function;

    public InjectIntoDoubleProcedure(double injectedValue, DoubleObjectToDoubleFunction<? super T> function)
    {
        this.result = injectedValue;
        this.function = function;
    }

    @Override
    public void value(T each)
    {
        this.result = this.function.doubleValueOf(this.result, each);
    }

    public double getResult()
    {
        return this.result;
    }
}
