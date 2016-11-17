/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import java.util.Collections;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.FixedSizeList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.stack.MutableStack;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.stack.mutable.ArrayStack;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractMemoryEfficientMutableListTestCase
{
    protected MutableList<String> list;

    @Before
    public void setUp()
    {
        this.list = this.classUnderTest();
    }

    protected MutableList<String> classUnderTest()
    {
        return Lists.fixedSize.ofAll(this.getNStrings());
    }

    private MutableList<String> getNStrings()
    {
        return Interval.oneTo(this.getSize()).collect(String::valueOf).toList();
    }

    protected abstract int getSize();

    protected abstract Class<?> getListType();

    @Test
    public void testGetClass()
    {
        Verify.assertInstanceOf(this.getListType(), this.list);
    }

    @Test
    public void sortThis()
    {
        this.list.shuffleThis();
        MutableList<String> sortedList = this.list.sortThis();
        Assert.assertSame(this.list, sortedList);
        Assert.assertEquals(
                this.getNStrings(),
                sortedList);
    }

    @Test
    public void sortThisBy()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisBy(Functions.getStringToInteger()));
    }

    @Test
    public void sortThisByInt()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisByInt(Integer::parseInt));
    }

    @Test
    public void sortThisByBoolean()
    {
        PartitionMutableList<String> partition = this.getNStrings().partition(s -> Integer.parseInt(s) % 2 == 0);
        MutableList<String> expected = FastList.newList(partition.getRejected()).withAll(partition.getSelected());
        Assert.assertEquals(expected, this.list.sortThisByBoolean(s -> Integer.parseInt(s) % 2 == 0));
    }

    @Test
    public void sortThisByChar()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisByChar(string -> string.charAt(0)));
    }

    @Test
    public void sortThisByByte()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisByByte(Byte::parseByte));
    }

    @Test
    public void sortThisByShort()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisByShort(Short::parseShort));
    }

    @Test
    public void sortThisByFloat()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisByFloat(Float::parseFloat));
    }

    @Test
    public void sortThisByLong()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisByLong(Long::parseLong));
    }

    @Test
    public void sortThisByDouble()
    {
        this.list.shuffleThis();
        Assert.assertEquals(
                this.getNStrings(),
                this.list.sortThisByDouble(Double::parseDouble));
    }

    @Test
    public void reverseThis()
    {
        MutableList<String> expected = FastList.newList(this.list);
        MutableList<String> actual = this.list.reverseThis();
        Collections.reverse(expected);
        Assert.assertEquals(actual, expected);
        Assert.assertSame(this.list, actual);
    }

    @Test
    public void toReversed()
    {
        MutableList<String> actual = this.list.toReversed();
        MutableList<String> expected = FastList.newList(this.list).reverseThis();
        Assert.assertEquals(actual, expected);
        Assert.assertNotSame(this.list, actual);
    }

    @Test
    public void with()
    {
        MutableList<String> list = this.classUnderTest();
        Assert.assertFalse(list.contains("11"));
        MutableList<String> listWith = list.with("11");
        Assert.assertTrue(listWith.containsAll(list));
        Assert.assertTrue(listWith.contains("11"));
        Verify.assertInstanceOf(FixedSizeList.class, listWith);
    }

    @Test
    public void withAll()
    {
        MutableList<String> list = this.classUnderTest();
        Verify.assertContainsNone(list, "11", "12");
        MutableList<String> listWith = list.withAll(FastList.newListWith("11", "12"));
        Assert.assertTrue(listWith.containsAll(list));
        Verify.assertContainsAll(listWith, "11", "12");
        Verify.assertInstanceOf(FixedSizeList.class, listWith);
        Assert.assertSame(listWith, listWith.withAll(FastList.newList()));
    }

    @Test
    public void withoutAll()
    {
        MutableList<String> list = this.classUnderTest().with("11").with("12");
        MutableList<String> listWithout = list.withoutAll(FastList.newListWith("11", "12"));
        Assert.assertTrue(listWithout.containsAll(this.classUnderTest()));
        Verify.assertContainsNone(listWithout, "11", "12");
        Verify.assertInstanceOf(FixedSizeList.class, listWithout);
        Assert.assertSame(listWithout, listWithout.withoutAll(FastList.newList()));
    }

    @Test
    public void toStack()
    {
        MutableStack<String> stack = this.classUnderTest().toStack();
        Assert.assertEquals(ArrayStack.newStack(this.classUnderTest()), stack);
    }

    @Test
    public void aggregateByMutating()
    {
        Function<String, String> groupBy = Functions.getStringPassThru();
        Procedure2<Counter, String> sumAggregator = (aggregate, value) -> aggregate.add(Integer.parseInt(value));
        MapIterable<String, Counter> actual = this.classUnderTest().aggregateInPlaceBy(groupBy, Counter::new, sumAggregator);
        MapIterable<String, Counter> expected = FastList.newList(this.classUnderTest()).aggregateInPlaceBy(groupBy, Counter::new, sumAggregator);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void aggregateByNonMutating()
    {
        Function<String, String> groupBy = Functions.getStringPassThru();
        Function2<Integer, String, Integer> sumAggregator = (aggregate, value) -> aggregate + Integer.parseInt(value);
        MapIterable<String, Integer> actual = this.classUnderTest().aggregateBy(groupBy, () -> 0, sumAggregator);
        MapIterable<String, Integer> expected = FastList.newList(this.classUnderTest()).aggregateBy(groupBy, () -> 0, sumAggregator);
        Assert.assertEquals(expected, actual);
    }
}
