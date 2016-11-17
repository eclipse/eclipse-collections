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

public class MutableByte extends Number implements Comparable<MutableByte>
{
    private static final long serialVersionUID = 1L;
    private byte value = 0;

    public MutableByte(byte value)
    {
        this.value = value;
    }

    public MutableByte()
    {
        this((byte) 0);
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
        return this.value == ((MutableByte) other).value;
    }

    @Override
    public int hashCode()
    {
        return this.intValue();
    }

    @Override
    public int compareTo(MutableByte other)
    {
        return Byte.compare(this.value, other.value);
    }

    public void setValue(byte value)
    {
        this.value = value;
    }

    public MutableByte add(byte number)
    {
        this.value += number;
        return this;
    }

    public MutableByte subtract(byte number)
    {
        this.value -= number;
        return this;
    }

    public MutableByte multiply(byte number)
    {
        this.value *= number;
        return this;
    }

    public MutableByte divide(byte number)
    {
        this.value /= number;
        return this;
    }

    public MutableByte min(byte number)
    {
        this.value = (byte) Math.min(this.intValue(), (int) number);
        return this;
    }

    public MutableByte max(byte number)
    {
        this.value = (byte) Math.max(this.intValue(), (int) number);
        return this;
    }

    public MutableByte abs()
    {
        this.value = (byte) Math.abs(this.intValue());
        return this;
    }

    public Byte toByte()
    {
        return Byte.valueOf(this.value);
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
    public byte byteValue()
    {
        return this.value;
    }

    @Override
    public String toString()
    {
        return "MutableByte{value=" + this.value + '}';
    }
}
