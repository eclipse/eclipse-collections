/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.eclipse.collections.api.block.function.primitive.IntFunction;

/**
 * A Counter can be used to increment and return an integer count.  A Counter can be used in Anonymous
 * inner classes if it is declared final, unlike an int, which once declared final cannot be modified.
 */
public final class Counter implements Externalizable
{
    public static final IntFunction<Counter> TO_COUNT = Counter::getCount;

    private static final long serialVersionUID = 1L;

    private int count;

    public Counter(int startCount)
    {
        this.count = startCount;
    }

    public Counter()
    {
        this(0);
    }

    public void increment()
    {
        this.count++;
    }

    public void decrement()
    {
        this.count--;
    }

    public void add(int value)
    {
        this.count += value;
    }

    public int getCount()
    {
        return this.count;
    }

    public void reset()
    {
        this.count = 0;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.count);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Counter))
        {
            return false;
        }

        Counter counter = (Counter) o;

        return this.count == counter.count;
    }

    @Override
    public int hashCode()
    {
        return this.count;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeInt(this.count);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException
    {
        this.count = in.readInt();
    }
}
