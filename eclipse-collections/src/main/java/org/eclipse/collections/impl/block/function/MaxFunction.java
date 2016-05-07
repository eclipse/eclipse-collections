/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import org.eclipse.collections.api.block.function.Function2;

/**
 * MaxFunction contains iterator aware implementations of Max() for integers, doubles, and longs.
 */
public final class MaxFunction
{
    public static final Function2<Integer, Integer, Integer> INTEGER = new MaxIntegerFunction();
    public static final Function2<Double, Double, Double> DOUBLE = new MaxDoubleFunction();
    public static final Function2<Long, Long, Long> LONG = new MaxLongFunction();

    private MaxFunction()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    private static class MaxIntegerFunction implements Function2<Integer, Integer, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer argument1, Integer argument2)
        {
            if (argument1 == null)
            {
                return argument2;
            }
            if (argument2 == null)
            {
                return argument1;
            }
            return argument1.intValue() > argument2.intValue() ? argument1 : argument2;
        }
    }

    private static class MaxDoubleFunction implements Function2<Double, Double, Double>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Double value(Double argument1, Double argument2)
        {
            if (argument1 == null)
            {
                return argument2;
            }
            if (argument2 == null)
            {
                return argument1;
            }
            return argument1.doubleValue() > argument2.doubleValue() ? argument1 : argument2;
        }
    }

    private static class MaxLongFunction implements Function2<Long, Long, Long>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Long value(Long argument1, Long argument2)
        {
            if (argument1 == null)
            {
                return argument2;
            }
            if (argument2 == null)
            {
                return argument1;
            }
            return argument1.longValue() > argument2.longValue() ? argument1 : argument2;
        }
    }
}
