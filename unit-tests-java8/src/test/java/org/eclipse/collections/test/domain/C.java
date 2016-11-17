/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.domain;

public class C implements A
{
    private final double d;

    public C(double d)
    {
        this.d = d;
    }

    @Override
    public double getDoubleValue()
    {
        return this.d;
    }

    @Override
    public String toString()
    {
        return "C{d=" + this.d + '}';
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

        C c = (C) o;

        if (Double.compare(c.d, this.d) != 0)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        long temp = Double.doubleToLongBits(this.d);
        return (int) (temp ^ (temp >>> 32));
    }
}
