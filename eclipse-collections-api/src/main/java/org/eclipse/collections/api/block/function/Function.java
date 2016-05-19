/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.function;

import java.io.Serializable;

/**
 * Function is a one-argument lambda which performs a transformation on the object of type {@code T}
 * passed to the valueOf() method.  This transformation can return the value of calling a getter, or perform
 * some more elaborate logic to calculate a value, of type {@code V}.
 */
@FunctionalInterface
public interface Function<T, V>
        extends java.util.function.Function<T, V>, Serializable
{
    V valueOf(T each);

    @Override
    default V apply(T each)
    {
        return this.valueOf(each);
    }
}
