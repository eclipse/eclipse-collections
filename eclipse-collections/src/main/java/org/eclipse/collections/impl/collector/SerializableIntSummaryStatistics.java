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
import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.block.procedure.primitive.IntProcedure;

/**
 * Provides a serializable version of IntSummaryStatistics.
 *
 * @since 8.1
 * @deprecated since 8.2 since it will not work with Java 9 natively. Will be removed in 9.0.0.
 */
@Deprecated
public class SerializableIntSummaryStatistics
        extends IntSummaryStatistics
        implements IntProcedure, Externalizable
{
    private static final long serialVersionUID = 1L;

    private static final Field COUNT;
    private static final Field SUM;
    private static final Field MIN;
    private static final Field MAX;

    static
    {
        Field count = null;
        Field sum = null;
        Field min = null;
        Field max = null;
        try
        {
            count = IntSummaryStatistics.class.getDeclaredField("count");
            count.setAccessible(true);
            sum = IntSummaryStatistics.class.getDeclaredField("sum");
            sum.setAccessible(true);
            min = IntSummaryStatistics.class.getDeclaredField("min");
            min.setAccessible(true);
            max = IntSummaryStatistics.class.getDeclaredField("max");
            max.setAccessible(true);
        }
        catch (Exception ignored)
        {
            count = null;
            sum = null;
            min = null;
            max = null;
        }
        COUNT = count;
        SUM = sum;
        MIN = min;
        MAX = max;
    }

    public static SerializableIntSummaryStatistics with(int... values)
    {
        SerializableIntSummaryStatistics result = new SerializableIntSummaryStatistics();
        for (int value : values)
        {
            result.value(value);
        }
        return result;
    }

    @Override
    public void value(int each)
    {
        this.accept(each);
    }

    public boolean valuesEqual(IntSummaryStatistics other)
    {
        return this.getCount() == other.getCount()
                && this.getMin() == other.getMin()
                && this.getMax() == other.getMax()
                && this.getSum() == other.getSum();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        if (COUNT == null)
        {
            throw new NotSerializableException("Unable to access private fields in IntSummaryStatistics.");
        }
        out.writeLong(this.getCount());
        out.writeInt(this.getMin());
        out.writeInt(this.getMax());
        out.writeLong(this.getSum());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException
    {
        if (COUNT == null)
        {
            throw new NotSerializableException("Unable to access private fields in IntSummaryStatistics.");
        }
        try
        {
            COUNT.setLong(this, in.readLong());
            MIN.setInt(this, in.readInt());
            MAX.setInt(this, in.readInt());
            SUM.setLong(this, in.readLong());
        }
        catch (IllegalAccessException ex)
        {
            throw new RuntimeException("IllegalAccessException when reading SerializableIntSummaryStatistics", ex);
        }
    }
}
