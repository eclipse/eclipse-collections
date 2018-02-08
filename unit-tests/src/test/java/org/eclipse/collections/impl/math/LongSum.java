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

/**
 * A mutable sum which uses a long as the storage mechanism
 *
 * @deprecated use MutableLong instead
 */
@Deprecated
public final class LongSum
        implements Sum
{
    private static final long serialVersionUID = 1L;

    private long sum = 0;

    public LongSum(long newSum)
    {
        this.sum = newSum;
    }

    @Override
    public Sum speciesNew()
    {
        return new LongSum(0);
    }

    @Override
    public Sum add(Object number)
    {
        this.add((Number) number);
        return this;
    }

    @Override
    public Sum add(int value)
    {
        this.add((long) value);
        return this;
    }

    @Override
    public Sum add(Sum otherSum)
    {
        return this.add(otherSum.getValue());
    }

    @Override
    public Sum add(Number number)
    {
        this.add(number.longValue());
        return this;
    }

    public Sum add(long value)
    {
        this.sum += value;
        return this;
    }

    @Override
    public Number getValue()
    {
        return this.sum;
    }

    @Override
    public boolean equals(Object o)
    {
        LongSum longSum = (LongSum) o;
        return this.sum == longSum.sum;
    }

    @Override
    public int hashCode()
    {
        return (int) (this.sum ^ this.sum >>> 32);
    }
}
