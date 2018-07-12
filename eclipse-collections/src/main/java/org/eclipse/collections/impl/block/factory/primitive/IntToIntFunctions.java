/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory.primitive;

import org.eclipse.collections.api.block.function.primitive.IntToIntFunction;

public final class IntToIntFunctions
{
    private IntToIntFunctions()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static IntToIntFunction increment()
    {
        return Math::incrementExact;
    }

    public static IntToIntFunction decrement()
    {
        return Math::decrementExact;
    }

    public static IntToIntFunction add(int intToAdd)
    {
        return value -> Math.addExact(value, intToAdd);
    }

    public static IntToIntFunction subtract(int intToSubtract)
    {
        return value -> Math.subtractExact(value, intToSubtract);
    }
}
