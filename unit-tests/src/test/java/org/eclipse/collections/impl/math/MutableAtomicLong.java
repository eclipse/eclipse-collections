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

import java.util.concurrent.atomic.AtomicLong;

public final class MutableAtomicLong extends AtomicLong implements Comparable<MutableAtomicLong>
{
    private static final long serialVersionUID = 1L;

    public MutableAtomicLong(long value)
    {
        super(value);
    }

    public MutableAtomicLong()
    {
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

        MutableAtomicLong that = (MutableAtomicLong) other;

        return this.get() == that.get();
    }

    @Override
    public int hashCode()
    {
        long value = this.get();
        return (int) (value ^ (value >>> 32));
    }

    @Override
    public int compareTo(MutableAtomicLong other)
    {
        return Long.compare(this.get(), other.get());
    }

    public MutableAtomicLong add(long number)
    {
        this.getAndAdd(number);
        return this;
    }

    public MutableAtomicLong subtract(long number)
    {
        while (true)
        {
            long current = this.get();
            long next = current - number;
            if (this.compareAndSet(current, next))
            {
                break;
            }
        }
        return this;
    }

    public MutableAtomicLong multiply(long number)
    {
        while (true)
        {
            long current = this.get();
            long next = current * number;
            if (this.compareAndSet(current, next))
            {
                break;
            }
        }
        return this;
    }

    public MutableAtomicLong divide(long number)
    {
        while (true)
        {
            long current = this.get();
            long next = current / number;
            if (this.compareAndSet(current, next))
            {
                break;
            }
        }
        return this;
    }

    public MutableAtomicLong min(long number)
    {
        while (true)
        {
            long current = this.get();
            long next = Math.min(current, number);
            if (this.compareAndSet(current, next))
            {
                break;
            }
        }
        return this;
    }

    public MutableAtomicLong max(long number)
    {
        while (true)
        {
            long current = this.get();
            long next = Math.max(current, number);
            if (this.compareAndSet(current, next))
            {
                break;
            }
        }
        return this;
    }

    public MutableAtomicLong abs()
    {
        while (true)
        {
            long current = this.get();
            long next = Math.abs(current);
            if (this.compareAndSet(current, next))
            {
                break;
            }
        }
        return this;
    }

    public Long toLong()
    {
        return Long.valueOf(this.get());
    }
}
