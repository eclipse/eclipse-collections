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
import java.util.function.Supplier;

/**
 * Function0 is a zero argument lambda.  It can be stored in a variable or passed as a parameter and executed
 * by calling the value method.
 */
@FunctionalInterface
public interface Function0<R>
        extends Supplier<R>, Serializable
{
    R value();

    @Override
    default R get()
    {
        return this.value();
    }
}
