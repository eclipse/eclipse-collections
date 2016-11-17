/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.math;

public class MutableShort extends Number implements Comparable<MutableShort>
{
    private static final long serialVersionUID = 1L;
    private short value = 0;

    public MutableShort(short value)
    {
        this.value = value;
    }

    public MutableShort()
    {
        this((short) 0);
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null || this.getClass() != other.getClass())
        {
            return false;
        }
        return this.value == ((MutableShort) other).value;
    }

    @Override
    public int hashCode()
    {
        return this.intValue();
    }

    @Override
    @SuppressWarnings("CompareToUsesNonFinalVariable")
    public int compareTo(MutableShort other)
    {
        return Short.compare(this.value, other.value);
    }

    public void setValue(short value)
    {
        this.value = value;
    }

    public MutableShort add(short number)
    {
        this.value += number;
        return this;
    }

    public MutableShort subtract(short number)
    {
        this.value -= number;
        return this;
    }

    public MutableShort multiply(short number)
    {
        this.value *= number;
        return this;
    }

    public MutableShort divide(short number)
    {
        this.value /= number;
        return this;
    }

    public MutableShort min(short number)
    {
        this.value = (short) Math.min(this.intValue(), (int) number);
        return this;
    }

    public MutableShort max(short number)
    {
        this.value = (short) Math.max(this.intValue(), (int) number);
        return this;
    }

    public MutableShort abs()
    {
        this.value = (short) Math.abs(this.intValue());
        return this;
    }

    public Short toShort()
    {
        return Short.valueOf(this.value);
    }

    @Override
    public int intValue()
    {
        return (int) this.value;
    }

    @Override
    public long longValue()
    {
        return (long) this.value;
    }

    @Override
    public float floatValue()
    {
        return (float) this.value;
    }

    @Override
    public double doubleValue()
    {
        return (double) this.value;
    }

    @Override
    public short shortValue()
    {
        return this.value;
    }

    @Override
    public String toString()
    {
        return "MutableShort{value=" + this.value + '}';
    }
}
