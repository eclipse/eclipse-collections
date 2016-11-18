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

public final class MutableDouble extends Number implements Comparable<MutableDouble>
{
    private static final long serialVersionUID = 1L;
    private double value = 0.0;

    public MutableDouble(double value)
    {
        this.value = value;
    }

    public MutableDouble()
    {
        this(0.0);
    }

    @Override
    public int compareTo(MutableDouble other)
    {
        return Double.compare(this.value, other.value);
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
        return Double.compare(((MutableDouble) other).value, this.value) == 0;
    }

    @Override
    public int hashCode()
    {
        long temp = Double.doubleToLongBits(this.value);
        return (int) (temp ^ (temp >>> 32));
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public MutableDouble add(double number)
    {
        this.value += number;
        return this;
    }

    public MutableDouble subtract(double number)
    {
        this.value -= number;
        return this;
    }

    public MutableDouble multiply(double number)
    {
        this.value *= number;
        return this;
    }

    public MutableDouble divide(double number)
    {
        this.value /= number;
        return this;
    }

    public MutableDouble min(double number)
    {
        this.value = Math.min(this.value, number);
        return this;
    }

    public MutableDouble max(double number)
    {
        this.value = Math.max(this.value, number);
        return this;
    }

    public MutableDouble abs()
    {
        this.value = Math.abs(this.value);
        return this;
    }

    public Double toDouble()
    {
        return Double.valueOf(this.value);
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
        return this.value;
    }

    @Override
    public String toString()
    {
        return "MutableDouble{value=" + this.value + '}';
    }
}
