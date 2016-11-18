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
import org.eclipse.collections.api.block.function.primitive.LongFunction;

/**
 * LongFunctionImpl is an abstract implementation of the LongFunction interface which can be subclassed
 * explicitly or as an anonymous inner class, without needing to override the valueOf method defined in
 * Function.
 */
public abstract class LongFunctionImpl<T>
        implements Function<T, Long>, LongFunction<T>
{
    private static final long serialVersionUID = 1L;

    @Override
    public Long valueOf(T anObject)
    {
        return this.longValueOf(anObject);
    }
}
