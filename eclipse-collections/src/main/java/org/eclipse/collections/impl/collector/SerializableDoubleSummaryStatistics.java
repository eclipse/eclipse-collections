/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.util.DoubleSummaryStatistics;

import org.eclipse.collections.api.block.procedure.primitive.DoubleProcedure;

/**
 * Provides a serializable version of DoubleSummaryStatistics.
 *
 * @since 8.1
 * @deprecated since 8.2 since it will not work with Java 9 natively. Will be removed in 9.0.0.
 */
@Deprecated
public class SerializableDoubleSummaryStatistics
        extends DoubleSummaryStatistics
        implements DoubleProcedure, Externalizable
{
    private static final long serialVersionUID = 1L;

    private static final Field COUNT;
    private static final Field SUM;
    private static final Field SUM_COMPENSATION;
    private static final Field SIMPLE_SUM;
    private static final Field MIN;
    private static final Field MAX;

    static
    {
        Field count = null;
        Field sum = null;
        Field sumCompensation = null;
        Field simpleSum = null;
        Field min = null;
        Field max = null;
        try
        {
            count = DoubleSummaryStatistics.class.getDeclaredField("count");
            count.setAccessible(true);
            sum = DoubleSummaryStatistics.class.getDeclaredField("sum");
            sum.setAccessible(true);
            sumCompensation = DoubleSummaryStatistics.class.getDeclaredField("sumCompensation");
            sumCompensation.setAccessible(true);
            simpleSum = DoubleSummaryStatistics.class.getDeclaredField("simpleSum");
            simpleSum.setAccessible(true);
            min = DoubleSummaryStatistics.class.getDeclaredField("min");
            min.setAccessible(true);
            max = DoubleSummaryStatistics.class.getDeclaredField("max");
            max.setAccessible(true);
        }
        catch (Exception ignored)
        {
            count = null;
            sum = null;
            sumCompensation = null;
            simpleSum = null;
            min = null;
            max = null;
        }
        COUNT = count;
        SUM = sum;
        SUM_COMPENSATION = sumCompensation;
        SIMPLE_SUM = simpleSum;
        MIN = min;
        MAX = max;
    }

    public static SerializableDoubleSummaryStatistics with(double... values)
    {
        SerializableDoubleSummaryStatistics result = new SerializableDoubleSummaryStatistics();
        for (double value : values)
        {
            result.value(value);
        }
        return result;
    }

    @Override
    public void value(double each)
    {
        this.accept(each);
    }

    public boolean valuesEqual(DoubleSummaryStatistics other)
    {
        return this.getCount() == other.getCount()
                && Double.doubleToLongBits(this.getSum()) == Double.doubleToLongBits(other.getSum())
                && Double.doubleToLongBits(this.getMin()) == Double.doubleToLongBits(other.getMin())
                && Double.doubleToLongBits(this.getMax()) == Double.doubleToLongBits(other.getMax());
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        if (COUNT == null)
        {
            throw new NotSerializableException("Unable to access private fields in DoubleSummaryStatistics.");
        }
        out.writeLong(this.getCount());
        out.writeDouble(this.getMin());
        out.writeDouble(this.getMax());
        try
        {
            out.writeDouble(SUM.getDouble(this));
            out.writeDouble(SUM_COMPENSATION.getDouble(this));
            out.writeDouble(SIMPLE_SUM.getDouble(this));
        }
        catch (IllegalAccessException ex)
        {
            throw new RuntimeException("IllegalAccessException when writing SerializableDoubleSummaryStatistics", ex);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException
    {
        if (COUNT == null)
        {
            throw new NotSerializableException("Unable to access private fields in DoubleSummaryStatistics.");
        }
        try
        {
            COUNT.setLong(this, in.readLong());
            MIN.setDouble(this, in.readDouble());
            MAX.setDouble(this, in.readDouble());
            SUM.setDouble(this, in.readDouble());
            SUM_COMPENSATION.setDouble(this, in.readDouble());
            SIMPLE_SUM.setDouble(this, in.readDouble());
        }
        catch (IllegalAccessException ex)
        {
            throw new RuntimeException("IllegalAccessException when reading SerializableDoubleSummaryStatistics", ex);
        }
    }
}
