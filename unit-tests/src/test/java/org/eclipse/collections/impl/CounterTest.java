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

import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class CounterTest
{
    @Test
    public void basicLifecycle()
    {
        Counter counter = new Counter();

        Assert.assertEquals(0, counter.getCount());
        counter.increment();
        Assert.assertEquals(1, counter.getCount());
        counter.increment();
        Assert.assertEquals(2, counter.getCount());
        counter.add(16);
        Assert.assertEquals(18, counter.getCount());
        Interval.oneTo(1000).forEach(Procedures.cast(each -> counter.increment()));
        Assert.assertEquals(1018, counter.getCount());
        Assert.assertEquals("1018", counter.toString());

        counter.reset();
        Assert.assertEquals(0, counter.getCount());
        counter.add(4);
        Assert.assertEquals(4, counter.getCount());
        counter.increment();
        Assert.assertEquals(5, counter.getCount());

        Assert.assertEquals("5", counter.toString());
    }

    @Test
    public void equalsAndHashCode()
    {
        Verify.assertEqualsAndHashCode(new Counter(1), new Counter(1));
        Assert.assertNotEquals(new Counter(1), new Counter(2));
    }

    @Test
    public void serialization()
    {
        Verify.assertPostSerializedEqualsAndHashCode(new Counter());
    }
}
