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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.eclipse.collections.impl.list.Interval;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayListAddAllTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayListAddAllTest.class);

    @Test
    @Category(PerformanceTests.class)
    public void runArrayListAddAll()
    {
        this.runIntegerArrayListAddAll("integer");
        this.runLongArrayListAddAll("long");
        this.runIntegerArrayListAddAll("integer");
        this.runStringArrayListAddAll("string");
        this.runIntegerArrayListAddAll("integer");
        this.runLongArrayListAddAll("long");
        this.runIntegerArrayListAddAll("integer");
        this.runStringArrayListAddAll("string");
    }

    private void runIntegerArrayListAddAll(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Integer[] ints = new Integer[100000];
        for (int i = 0; i < ints.length; i++)
        {
            ints[i] = r.nextInt();
        }
        this.runArrayListAddAll(type, ints);
    }

    private void runLongArrayListAddAll(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Long[] longs = new Long[100000];
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = r.nextLong();
        }
        this.runArrayListAddAll(type, longs);
    }

    private void runStringArrayListAddAll(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        String[] strings = new String[100000];
        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = String.valueOf(r.nextLong());
        }
        this.runArrayListAddAll(type, strings);
    }

    private void runArrayListAddAll(String type, Object[] objects)
    {
        ArrayList<Object> listToAddAll = new ArrayList<>(Arrays.asList(objects));
        for (int i = 0; i < 100; i++)
        {
            this.runArrayListAddAll(listToAddAll, 100);
        }
        for (int i = 0; i < 100; i++)
        {
            this.runArrayListAddAll(listToAddAll, 100);
        }
        long now1 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            this.runArrayListAddAll(listToAddAll, 1000);
        }
        long time1 = System.currentTimeMillis() - now1;
        LOGGER.info("ArrayList, list size 100,000, {} addAll/msec: {}", type, 100000 / time1);
        long now2 = System.currentTimeMillis();
        this.runArrayListAddAll(new ArrayList<>(Interval.oneTo(100)), 100000000);
        long time2 = System.currentTimeMillis() - now2;
        LOGGER.info("ArrayList, list size 100, addAll/msec: {}", 100000000 / time2);
        long now3 = System.currentTimeMillis();
        this.runArrayListAddAll(new ArrayList<>(Interval.oneTo(1)), 1000000000);
        long time3 = System.currentTimeMillis() - now3;
        LOGGER.info("ArrayList, list size 1, addAll/msec: {}", 1000000000 / time3);
        long now4 = System.currentTimeMillis();
        this.runArrayListAddAll(new ArrayList<>(), 1000000000);
        long time4 = System.currentTimeMillis() - now4;
        LOGGER.info("ArrayList, list size (empty), addAll/msec: {}", 1000000000 / time4);
    }

    public void runArrayListAddAll(List<Object> objects, long runs)
    {
        for (long l = 0; l < runs; l++)
        {
            ArrayList<Object> list = new ArrayList<>();
            list.addAll(objects);
        }
    }
}
