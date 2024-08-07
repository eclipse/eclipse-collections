/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MultiReaderList;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CollectIterableTest extends AbstractLazyIterableTestCase
{
    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.collect(FastList.newListWith(elements), Functions.identity());
    }

    @Test
    public void forEach()
    {
        LazyIterable<String> select = new CollectIterable<>(Interval.oneTo(5), String::valueOf);
        Appendable builder = new StringBuilder();
        Procedure<String> appendProcedure = Procedures.append(builder);
        select.forEach(appendProcedure);
        assertEquals("12345", builder.toString());
    }

    @Test
    public void forEachWithIndex()
    {
        LazyIterable<String> select = new CollectIterable<>(Interval.oneTo(5), String::valueOf);
        StringBuilder builder = new StringBuilder();
        select.forEachWithIndex((object, index) -> {
            builder.append(object);
            builder.append(index);
        });
        assertEquals("1021324354", builder.toString());
    }

    @Override
    @Test
    public void iterator()
    {
        LazyIterable<String> select = new CollectIterable<>(Interval.oneTo(5), String::valueOf);
        StringBuilder builder = new StringBuilder();
        for (String each : select)
        {
            builder.append(each);
        }
        assertEquals("12345", builder.toString());
    }

    @Test
    public void forEachWith()
    {
        LazyIterable<String> select = new CollectIterable<>(Interval.oneTo(5), String::valueOf);
        StringBuilder builder = new StringBuilder();
        select.forEachWith((each, aBuilder) -> aBuilder.append(each), builder);
        assertEquals("12345", builder.toString());
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        LazyIterable<String> collect = new CollectIterable<>(FastList.newListWith(3, 2, 2, 4, 1, 3, 1, 5), String::valueOf);
        assertEquals(
                FastList.newListWith("3", "2", "4", "1", "5"),
                collect.distinct().toList());
    }

    @Test
    public void injectIntoInt()
    {
        CollectIterable<Integer, String> collect = new CollectIterable<>(FastList.newListWith(1, 2, 3, 4, 5), String::valueOf);
        int sum = collect.injectInto(0, (int value, String each) -> value + Integer.parseInt(each));
        assertEquals(15, sum);
    }

    @Test
    public void injectIntoLong()
    {
        CollectIterable<Long, String> collect = new CollectIterable<>(FastList.newListWith(1L, 2L, 3L, 4L, 5L), String::valueOf);
        long sum = collect.injectInto(0L, (long value, String each) -> value + Long.parseLong(each));
        assertEquals(15L, sum);
    }

    @Test
    public void injectIntoDouble()
    {
        CollectIterable<Double, String> collect = new CollectIterable<>(FastList.newListWith(1.1d, 1.2d, 1.3d, 1.4d), String::valueOf);
        double sum = collect.injectInto(2.2d, (value, each) -> value + Double.parseDouble(each));
        assertEquals(7.2, sum, 0.1);
    }

    @Test
    public void injectIntoFloat()
    {
        CollectIterable<Float, String> collect = new CollectIterable<>(FastList.newListWith(1.1f, 1.2f, 1.3f, 1.4f), String::valueOf);
        float sum = collect.injectInto(2.2f, (float value, String each) -> value + Float.parseFloat(each));
        assertEquals(7.2, sum, 0.1);
    }

    @Test
    public void getFirstOnEmpty()
    {
        CollectIterable<Integer, String> collect = new CollectIterable<>(FastList.newList(), String::valueOf);
        assertNull(collect.getFirst());
    }

    @Test
    public void getLastOnEmpty()
    {
        CollectIterable<Integer, String> collect = new CollectIterable<>(FastList.newList(), String::valueOf);
        assertNull(collect.getLast());
    }

    @Override
    @Test
    public void toArray()
    {
        LazyIterable<String> stringNums = Interval.fromTo(0, 3).collect(Functions.getToString());
        stringNums.toArray();
        assertEquals(Lists.immutable.of("0", "1", "2", "3"), Lists.immutable.ofAll(stringNums));
    }

    @Override
    @Test
    public void detect()
    {
        super.detect();
        AtomicInteger functionCount = new AtomicInteger(0);
        MultiReaderList<Integer> integers = Lists.multiReader.withAll(Interval.oneTo(5));
        CollectIterable<Integer, Integer> collect = new CollectIterable<>(integers, functionCount::addAndGet);
        assertEquals(3L, collect.detect(each -> each.equals(3)).longValue());
        assertNull(collect.detect(each -> each.equals(100)));
    }

    @Override
    @Test
    public void detectIfNone()
    {
        super.detectIfNone();
        AtomicInteger functionCount = new AtomicInteger(0);
        MultiReaderList<Integer> integers = Lists.multiReader.withAll(Interval.oneTo(5));
        CollectIterable<Integer, Integer> collect = new CollectIterable<>(integers, functionCount::addAndGet);
        assertEquals(3L, collect.detectIfNone(each -> each.equals(3), () -> Integer.valueOf(0)).longValue());
        assertNull(collect.detectIfNone(each -> each.equals(100), () -> null));
    }

    @Override
    @Test
    public void detectWith()
    {
        super.detectWith();
        AtomicInteger functionCount = new AtomicInteger(0);
        MultiReaderList<Integer> integers = Lists.multiReader.withAll(Interval.oneTo(5));
        LazyIterable<Integer> collect = new CollectIterable<>(integers, functionCount::addAndGet);
        assertEquals(3L, collect.detectWith((each, ignore) -> each.equals(3), null).longValue());
        assertNull(collect.detectWith((each, ignore) -> each.equals(100), null));
    }

    @Override
    @Test
    public void detectWithIfNone()
    {
        super.detectWithIfNone();
        AtomicInteger functionCount = new AtomicInteger(0);
        MultiReaderList<Integer> integers = Lists.multiReader.withAll(Interval.oneTo(5));
        LazyIterable<Integer> collect = new CollectIterable<>(integers, functionCount::addAndGet);
        assertEquals(3L, collect.detectWithIfNone((each, ignore) -> each.equals(3), null, () -> Integer.valueOf(0)).longValue());
        assertNull(collect.detectWithIfNone((each, ignore) -> each.equals(100), null, () -> null));
    }

    @Override
    @Test
    public void detectOptional()
    {
        super.detectOptional();
        AtomicInteger functionCount = new AtomicInteger(0);
        MultiReaderList<Integer> integers = Lists.multiReader.withAll(Interval.oneTo(5));
        CollectIterable<Integer, Integer> collect = new CollectIterable<>(integers, functionCount::addAndGet);
        assertEquals(3L, collect.detectOptional(each -> each.equals(3)).get().longValue());
        assertNull(collect.detectOptional(each -> each.equals(100)).orElse(null));
    }

    @Override
    @Test
    public void detectWithOptional()
    {
        super.detectWithOptional();
        AtomicInteger functionCount = new AtomicInteger(0);
        MultiReaderList<Integer> integers = Lists.multiReader.withAll(Interval.oneTo(5));
        LazyIterable<Integer> collect = new CollectIterable<>(integers, functionCount::addAndGet);
        assertEquals(3L, collect.detectWithOptional((each, ignore) -> each.equals(3), null).get().longValue());
        assertNull(collect.detectWithOptional((each, ignore) -> each.equals(100), null).orElse(null));
    }
}
