/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.primitive;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;

/**
 * DoubleFunctionImpl is an abstract implementation of the DoubleFunction interface which can be subclassed
 * explicitly or as an anonymous inner class, without needing to override the valueOf method defined in
 * Function.
 */
public abstract class DoubleFunctionImpl<T>
        implements Function<T, Double>, DoubleFunction<T>
{
    private static final long serialVersionUID = 1L;

    private static final Double DOUBLE_ZERO = 0.0;

    @Override
    public Double valueOf(T anObject)
    {
        double value = this.doubleValueOf(anObject);
        return value == 0.0 ? DOUBLE_ZERO : Double.valueOf(value);
    }
}
