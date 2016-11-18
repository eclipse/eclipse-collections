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

import java.util.Collection;

import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleObjectToDoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatObjectToFloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntObjectToIntFunction;
import org.eclipse.collections.api.block.function.primitive.LongObjectToLongFunction;

/**
 * Provides static Function2s which can be used by Iterate.injectInto for adding primitives or to a collection
 */
public final class AddFunction
{
    public static final IntObjectToIntFunction<Integer> INTEGER_TO_INT = new AddIntegerToIntFunction();
    public static final LongObjectToLongFunction<Integer> INTEGER_TO_LONG = new AddIntegerToLongFunction();
    public static final DoubleObjectToDoubleFunction<Integer> INTEGER_TO_DOUBLE = new AddIntegerToDoubleFunction();
    public static final FloatObjectToFloatFunction<Integer> INTEGER_TO_FLOAT = new AddIntegerToFloatFunction();
    public static final DoubleObjectToDoubleFunction<Double> DOUBLE_TO_DOUBLE = new AddDoubleToDoubleFunction();
    public static final FloatObjectToFloatFunction<Float> FLOAT_TO_FLOAT = new AddFloatToFloatFunction();
    public static final Function2<Integer, Integer, Integer> INTEGER = new AddIntegerFunction();
    public static final Function2<Double, Double, Double> DOUBLE = new AddDoubleFunction();
    public static final Function2<Float, Float, Float> FLOAT = new AddFloatFunction();
    public static final Function2<Long, Long, Long> LONG = new AddLongFunction();
    public static final Function2<String, String, String> STRING = new AddStringFunction();
    public static final Function2<Collection<?>, ?, Collection<?>> COLLECTION = new AddCollectionFunction();

    private AddFunction()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    private static class AddIntegerToIntFunction implements IntObjectToIntFunction<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public int intValueOf(int intParameter, Integer objectParameter)
        {
            return intParameter + objectParameter;
        }
    }

    private static class AddIntegerToLongFunction implements LongObjectToLongFunction<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public long longValueOf(long longParameter, Integer objectParameter)
        {
            return longParameter + objectParameter.longValue();
        }
    }

    private static class AddIntegerToDoubleFunction implements DoubleObjectToDoubleFunction<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public double doubleValueOf(double doubleParameter, Integer objectParameter)
        {
            return doubleParameter + objectParameter;
        }
    }

    private static class AddIntegerToFloatFunction implements FloatObjectToFloatFunction<Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public float floatValueOf(float floatParameter, Integer objectParameter)
        {
            return floatParameter + objectParameter;
        }
    }

    private static class AddDoubleToDoubleFunction implements DoubleObjectToDoubleFunction<Double>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public double doubleValueOf(double doubleParameter, Double objectParameter)
        {
            return doubleParameter + objectParameter;
        }
    }

    private static class AddFloatToFloatFunction implements FloatObjectToFloatFunction<Float>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public float floatValueOf(float floatParameter, Float objectParameter)
        {
            return floatParameter + objectParameter;
        }
    }

    private static class AddIntegerFunction implements Function2<Integer, Integer, Integer>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer value(Integer argument1, Integer argument2)
        {
            return argument1 + argument2;
        }
    }

    private static class AddDoubleFunction implements Function2<Double, Double, Double>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Double value(Double argument1, Double argument2)
        {
            return argument1 + argument2;
        }
    }

    private static class AddFloatFunction implements Function2<Float, Float, Float>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Float value(Float argument1, Float argument2)
        {
            return argument1 + argument2;
        }
    }

    private static class AddLongFunction implements Function2<Long, Long, Long>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Long value(Long argument1, Long argument2)
        {
            return argument1 + argument2;
        }
    }

    private static class AddStringFunction implements Function2<String, String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public String value(String argument1, String argument2)
        {
            if (argument1 != null && argument2 != null)
            {
                return argument1 + argument2;
            }

            return argument1 == null ? argument2 : argument1;
        }
    }

    private static class AddCollectionFunction<T> implements Function2<Collection<T>, T, Collection<T>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Collection<T> value(Collection<T> collection, T addElement)
        {
            collection.add(addElement);
            return collection;
        }
    }
}
