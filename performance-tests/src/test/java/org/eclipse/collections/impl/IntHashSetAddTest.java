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

import java.util.Random;

import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntHashSetAddTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IntHashSetAddTest.class);

    @Test
    @Category(PerformanceTests.class)
    public void testHashPut()
    {
        this.runIntHashPut();
        this.runIntHashPut();
        this.runIntHashPut();
        this.runIntHashPut();
        this.runIntHashPut();
    }

    private void runIntHashPut()
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        int[] ints = new int[1000000];
        for (int i = 0; i < ints.length; i++)
        {
            ints[i] = r.nextInt();
        }
        this.runHashPut(ints);
    }

    private void runHashPut(int[] values)
    {
        for (int i = 0; i < 100; i++)
        {
            this.runHashContains(this.runHashPut(values, 1000, 1000), values, 1000, 1000);
        }
        for (int i = 0; i < 100; i++)
        {
            this.runHashContains(this.runHashPut(values, 1000000, 1), values, 1000000, 1);
        }
        IntHashSet set = null;
        long now1 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            set = this.runHashPut(values, 1000000, 1);
        }
        long time1 = System.currentTimeMillis() - now1;
        LOGGER.info("IntHashSet, set size 1,000,000, puts/msec: {}", 100000000 / time1);
        long now2 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            this.runHashContains(this.runHashPut(values, 1000000, 1), values, 1000000, 1);
        }
        long time2 = System.currentTimeMillis() - now2;
        LOGGER.info("IntHashSet, set size 1,000,000, contains/msec: {}", 100000000 / time2);
    }

    public IntHashSet runHashPut(int[] values, int length, int runs)
    {
        IntHashSet set = null;
        for (int i = 0; i < runs; i++)
        {
            set = new IntHashSet(8);
            for (int j = 0; j < length; j++)
            {
                set.add(values[j]);
            }
        }
        return set;
    }

    public boolean runHashContains(IntHashSet set, int[] values, int length, int runs)
    {
        boolean result = false;
        for (int i = 0; i < runs; i++)
        {
            for (int j = 0; j < length; j++)
            {
                result = set.contains(values[j]);
            }
        }
        return result;
    }
}
