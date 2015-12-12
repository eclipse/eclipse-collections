/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class LazyIterateTest
{
    @Test
    public void selectForEach()
    {
        LazyIterable<Integer> select = LazyIterate.select(Interval.oneTo(5), Predicates.lessThan(5));
        int sum = select.injectInto(0, AddFunction.INTEGER_TO_INT);
        Assert.assertEquals(10, sum);
    }

    @Test
    public void selectForEachWithIndex()
    {
        LazyIterable<Integer> select = LazyIterate.select(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        select.forEachWithIndex((object, index) -> {
            sum.add(object);
            sum.add(index);
        });
        Assert.assertEquals(16, sum.getValue().intValue());
    }

    @Test
    public void selectIterator()
    {
        LazyIterable<Integer> select = LazyIterate.select(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        for (Integer each : select)
        {
            sum.add(each);
        }
        Assert.assertEquals(10, sum.getValue().intValue());
    }

    @Test
    public void selectForEachWith()
    {
        LazyIterable<Integer> select = LazyIterate.select(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        select.forEachWith((each, aSum) -> aSum.add(each), sum);
        Assert.assertEquals(10, sum.getValue().intValue());
    }

    @Test
    public void rejectForEach()
    {
        LazyIterable<Integer> select = LazyIterate.reject(Interval.oneTo(5), Predicates.lessThan(5));
        int sum = select.injectInto(0, AddFunction.INTEGER_TO_INT);
        Assert.assertEquals(5, sum);
    }

    @Test
    public void rejectForEachWithIndex()
    {
        LazyIterable<Integer> select = LazyIterate.reject(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        select.forEachWithIndex((object, index) -> {
            sum.add(object);
            sum.add(index);
        });
        Assert.assertEquals(5, sum.getValue().intValue());
    }

    @Test
    public void rejectIterator()
    {
        LazyIterable<Integer> select = LazyIterate.reject(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        for (Integer each : select)
        {
            sum.add(each);
        }
        Assert.assertEquals(5, sum.getValue().intValue());
    }

    @Test
    public void rejectForEachWith()
    {
        LazyIterable<Integer> select = LazyIterate.reject(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        select.forEachWith((each, aSum) -> aSum.add(each), sum);
        Assert.assertEquals(5, sum.getValue().intValue());
    }

    @Test
    public void collectForEach()
    {
        LazyIterable<String> select = LazyIterate.collect(Interval.oneTo(5), String::valueOf);
        Appendable builder = new StringBuilder();
        Procedure<String> appendProcedure = Procedures.append(builder);
        select.forEach(appendProcedure);
        Assert.assertEquals("12345", builder.toString());
    }

    @Test
    public void collectForEachWithIndex()
    {
        LazyIterable<String> select = LazyIterate.collect(Interval.oneTo(5), String::valueOf);
        StringBuilder builder = new StringBuilder("");
        select.forEachWithIndex((object, index) -> {
            builder.append(object);
            builder.append(index);
        });
        Assert.assertEquals("1021324354", builder.toString());
    }

    @Test
    public void collectIterator()
    {
        LazyIterable<String> select = LazyIterate.collect(Interval.oneTo(5), String::valueOf);
        StringBuilder builder = new StringBuilder("");
        for (String each : select)
        {
            builder.append(each);
        }
        Assert.assertEquals("12345", builder.toString());
    }

    @Test
    public void collectForEachWith()
    {
        LazyIterable<String> select = LazyIterate.collect(Interval.oneTo(5), String::valueOf);
        StringBuilder builder = new StringBuilder("");
        select.forEachWith((each, aBuilder) -> aBuilder.append(each), builder);
        Assert.assertEquals("12345", builder.toString());
    }

    @Test
    public void asDeferred()
    {
        MutableList<Integer> expected = FastList.newList(Interval.oneTo(5));
        MutableList<Integer> actual0 = LazyIterate.adapt(Interval.oneTo(5)).toList();
        MutableList<Integer> actual1 = Interval.oneTo(5).asLazy().toList();
        MutableList<Integer> actual2 = FastList.newList(Interval.oneTo(5)).asLazy().toList();
        MutableList<Integer> actual3 = actual2.asUnmodifiable().asLazy().toList();
        MutableList<Integer> actual4 = actual2.asSynchronized().asLazy().toList();
        MutableList<Integer> actual5 = actual2.asLazy().select(ignored -> true).toList();
        MutableList<Integer> actual6 = actual2.toImmutable().asLazy().toList();
        ImmutableList<Integer> actual7 = actual2.asLazy().toList().toImmutable();
        Assert.assertEquals(expected, actual0);
        Assert.assertEquals(expected, actual1);
        Assert.assertEquals(expected, actual2);
        Assert.assertEquals(expected, actual3);
        Assert.assertEquals(expected, actual4);
        Assert.assertEquals(expected, actual5);
        Assert.assertEquals(expected, actual6);
        Assert.assertEquals(expected, actual7);
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(LazyIterate.class);
    }
}
