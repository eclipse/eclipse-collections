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

import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FastListAddTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FastListAddTest.class);

    @Test
    @Category(PerformanceTests.class)
    public void runFastListAdd()
    {
        this.runIntegerFastListAdd("integer");
        this.runLongFastListAdd("long");
        this.runIntegerFastListAdd("integer");
        this.runStringFastListAdd("string");
        this.runIntegerFastListAdd("integer");
        this.runLongFastListAdd("long");
        this.runIntegerFastListAdd("integer");
        this.runStringFastListAdd("string");
    }

    private void runIntegerFastListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Integer[] ints = new Integer[1000000];
        for (int i = 0; i < ints.length; i++)
        {
            ints[i] = r.nextInt();
        }
        this.runFastListAdds(type, ints);
    }

    private void runLongFastListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Long[] longs = new Long[1000000];
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = r.nextLong();
        }
        this.runFastListAdds(type, longs);
    }

    private void runStringFastListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        String[] strings = new String[1000000];
        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = String.valueOf(r.nextLong());
        }
        this.runFastListAdds(type, strings);
    }

    private void runFastListAdds(String type, Object[] objects)
    {
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAdd(objects, 1000, 1000);
        }
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAdd(objects, 1000000, 10);
        }
        long now = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAdd(objects, 1000000, 10);
        }
        long time = System.currentTimeMillis() - now;
        LOGGER.info("FastList, list size 1,000,000, {} adds/msec: {}", type, 1000000000 / time);
    }

    public void runFastListAdd(Object[] objects, int length, int runs)
    {
        for (int i = 0; i < runs; i++)
        {
            FastList<Object> list = FastList.newList();
            for (int j = 0; j < length; j++)
            {
                list.add(objects[j]);
            }
        }
    }
}
