/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.io.Serializable;

public class CollidingInt implements Serializable, Comparable<CollidingInt>
{
    private static final long serialVersionUID = 1L;
    private final int value;
    private final int shift;

    public CollidingInt(int value, int shift)
    {
        this.shift = shift;
        this.value = value;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }

        CollidingInt that = (CollidingInt) o;

        return this.value == that.value && this.shift == that.shift;
    }

    @Override
    public int hashCode()
    {
        return this.value >> this.shift;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public int compareTo(CollidingInt o)
    {
        int result = Integer.valueOf(this.value).compareTo(o.value);
        if (result != 0)
        {
            return result;
        }
        return Integer.valueOf(this.shift).compareTo(o.shift);
    }
}
