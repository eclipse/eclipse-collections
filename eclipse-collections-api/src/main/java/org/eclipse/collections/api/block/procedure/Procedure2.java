/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.procedure;

import java.io.Serializable;
import java.util.function.BiConsumer;

/**
 * A Procedure2 is used by forEachWith() methods and for MapIterate.forEachKeyValue().  In the forEachKeyValue()
 * case the procedure takes the key as the first argument, and the value as the second.   In the forEachWith() case
 * the procedure takes the the element of the collection as the first argument, and the specified parameter as the
 * second argument.
 */
@FunctionalInterface
public interface Procedure2<T1, T2>
        extends BiConsumer<T1, T2>, Serializable
{
    void value(T1 argument1, T2 argument2);

    @Override
    default void accept(T1 argument1, T2 argument2)
    {
        this.value(argument1, argument2);
    }
}
