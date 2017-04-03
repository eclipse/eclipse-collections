/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

public class ValueHolder
{
    private final String groupBy;
    private final int intValue;
    private final long longValue;
    private final double doubleValue;

    public ValueHolder(int intValue, long longValue, double doubleValue)
    {
        this.groupBy = "A";
        this.intValue = intValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
    }

    public ValueHolder(String groupBy, int intValue, long longValue, double doubleValue)
    {
        this.groupBy = groupBy;
        this.intValue = intValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
    }

    public String getGroupBy()
    {
        return this.groupBy;
    }

    public int getIntValue()
    {
        return this.intValue;
    }

    public long getLongValue()
    {
        return this.longValue;
    }

    public double getDoubleValue()
    {
        return this.doubleValue;
    }
}
