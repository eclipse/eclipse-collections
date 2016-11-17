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

public class B implements A
{
    private final int i;

    public B(int i)
    {
        this.i = i;
    }

    @Override
    public double getDoubleValue()
    {
        return Integer.valueOf(this.i).doubleValue();
    }

    @Override
    public String toString()
    {
        return "B{i=" + this.i + '}';
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

        B b = (B) o;

        if (this.i != b.i)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return this.i;
    }
}
