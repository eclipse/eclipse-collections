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

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayListAddTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayListAddTest.class);

    @Test
    @Category(PerformanceTests.class)
    public void runArrayListAdd()
    {
        this.runIntegerArrayListAdd("integer");
        this.runLongArrayListAdd("long");
        this.runIntegerArrayListAdd("integer");
        this.runStringArrayListAdd("string");
        this.runIntegerArrayListAdd("integer");
        this.runLongArrayListAdd("long");
        this.runIntegerArrayListAdd("integer");
        this.runStringArrayListAdd("string");
    }

    private void runIntegerArrayListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Integer[] ints = new Integer[1000000];
        for (int i = 0; i < ints.length; i++)
        {
            ints[i] = r.nextInt();
        }
        this.runArrayListAdds(type, ints);
    }

    public void runArrayListAdd(Object[] objects, int length, int runs)
    {
        for (int i = 0; i < runs; i++)
        {
            ArrayList<Object> list = new ArrayList<>();
            for (int j = 0; j < length; j++)
            {
                list.add(objects[j]);
            }
        }
    }

    private void runLongArrayListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Long[] longs = new Long[1000000];
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = r.nextLong();
        }
        this.runArrayListAdds(type, longs);
    }

    private void runArrayListAdds(String type, Object[] objects)
    {
        for (int i = 0; i < 100; i++)
        {
            this.runArrayListAdd(objects, 1000, 1000);
        }
        for (int i = 0; i < 100; i++)
        {
            this.runArrayListAdd(objects, 1000000, 10);
        }
        long now = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            this.runArrayListAdd(objects, 1000000, 10);
        }
        long time = System.currentTimeMillis() - now;
        LOGGER.info("ArrayList, list size 1,000,000, {} adds/msec: {}", type, 1000000000 / time);
    }

    private void runStringArrayListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        String[] strings = new String[1000000];
        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = String.valueOf(r.nextLong());
        }
        this.runArrayListAdds(type, strings);
    }
}
