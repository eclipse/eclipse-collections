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

import java.io.Serializable;

public class MutableCharacter implements Comparable<MutableCharacter>, Serializable
{
    private static final long serialVersionUID = 1L;
    private char value = 0;

    public MutableCharacter(char value)
    {
        this.value = value;
    }

    public MutableCharacter()
    {
        this((char) 0);
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
        return this.value == ((MutableCharacter) other).value;
    }

    @Override
    public int hashCode()
    {
        return this.intValue();
    }

    @Override
    public int compareTo(MutableCharacter other)
    {
        return Character.compare(this.value, other.value);
    }

    public void setValue(char value)
    {
        this.value = value;
    }

    @Override
    protected MutableCharacter clone()
    {
        try
        {
            MutableCharacter cloned = (MutableCharacter) super.clone();
            cloned.value = this.value;
            return cloned;
        }
        catch (CloneNotSupportedException e)
        {
            throw new AssertionError(e);
        }
    }

    public MutableCharacter add(char number)
    {
        this.value += number;
        return this;
    }

    public MutableCharacter subtract(char number)
    {
        this.value -= number;
        return this;
    }

    public MutableCharacter multiply(char number)
    {
        this.value *= number;
        return this;
    }

    public MutableCharacter divide(char number)
    {
        this.value /= number;
        return this;
    }

    public MutableCharacter min(char number)
    {
        this.value = (char) Math.min(this.intValue(), (int) number);
        return this;
    }

    public MutableCharacter max(char number)
    {
        this.value = (char) Math.max(this.intValue(), (int) number);
        return this;
    }

    public MutableCharacter abs()
    {
        this.value = (char) Math.abs(this.intValue());
        return this;
    }

    public Character toCharacter()
    {
        return Character.valueOf(this.value);
    }

    public int intValue()
    {
        return (int) this.value;
    }

    public short shortValue()
    {
        return (short) this.value;
    }

    public byte byteValue()
    {
        return (byte) this.value;
    }

    public long longValue()
    {
        return (long) this.value;
    }

    public float floatValue()
    {
        return (float) this.value;
    }

    public double doubleValue()
    {
        return (double) this.value;
    }

    @Override
    public String toString()
    {
        return "MutableCharacter{value=" + this.value + '}';
    }
}
