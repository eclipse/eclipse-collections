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
    private static final IntToIntFunction INCREMENT = new IncrementIntToIntFunction();

    private static final IntToIntFunction DECREMENT = new DecrementIntToIntFunction();

    private IntToIntFunctions()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static IntToIntFunction increment()
    {
        return INCREMENT;
    }

    public static IntToIntFunction decrement()
    {
        return DECREMENT;
    }

    public static IntToIntFunction add(int intToAdd)
    {
        return new AddIntToIntFunction(intToAdd);
    }

    public static IntToIntFunction subtract(int intToSubtract)
    {
        return new SubtractIntToIntFunction(intToSubtract);
    }

    private static class IncrementIntToIntFunction implements IntToIntFunction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public int valueOf(int intParameter)
        {
            return intParameter + 1;
        }
    }

    private static class DecrementIntToIntFunction implements IntToIntFunction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public int valueOf(int intParameter)
        {
            return intParameter - 1;
        }
    }

    private static final class AddIntToIntFunction implements IntToIntFunction
    {
        private static final long serialVersionUID = 1L;
        private final int intToAdd;

        private AddIntToIntFunction(int intToAdd)
        {
            this.intToAdd = intToAdd;
        }

        @Override
        public int valueOf(int intParameter)
        {
            return intParameter + this.intToAdd;
        }
    }

    private static final class SubtractIntToIntFunction implements IntToIntFunction
    {
        private static final long serialVersionUID = 1L;
        private final int intToSubtract;

        private SubtractIntToIntFunction(int intToSubtract)
        {
            this.intToSubtract = intToSubtract;
        }

        @Override
        public int valueOf(int intParameter)
        {
            return intParameter - this.intToSubtract;
        }
    }
}
