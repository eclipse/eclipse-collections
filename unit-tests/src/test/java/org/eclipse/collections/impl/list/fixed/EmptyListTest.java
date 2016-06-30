/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import java.util.NoSuchElementException;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class EmptyListTest
{
    @Test
    public void size()
    {
        Verify.assertSize(0, new EmptyList<>());
    }

    @Test
    public void empty()
    {
        Assert.assertTrue(new EmptyList<>().isEmpty());
        Assert.assertFalse(new EmptyList<>().notEmpty());
        Assert.assertTrue(Lists.fixedSize.of().isEmpty());
        Assert.assertFalse(Lists.fixedSize.of().notEmpty());
    }

    @Test
    public void getFirstLast()
    {
        Assert.assertNull(new EmptyList<>().getFirst());
        Assert.assertNull(new EmptyList<>().getLast());
    }

    @Test
    public void getOnly()
    {
        Verify.assertThrows(IllegalStateException.class, () -> new EmptyList<>().getOnly());
    }

    @Test
    public void readResolve()
    {
        Verify.assertInstanceOf(EmptyList.class, Lists.fixedSize.of());
        Verify.assertPostSerializedIdentity(Lists.fixedSize.of());
    }

    @Test
    public void testClone()
    {
        Assert.assertSame(Lists.fixedSize.of().clone(), Lists.fixedSize.of());
    }

    @Test(expected = NoSuchElementException.class)
    public void min()
    {
        Lists.fixedSize.of().min(Comparators.naturalOrder());
    }

    @Test(expected = NoSuchElementException.class)
    public void max()
    {
        Lists.fixedSize.of().max(Comparators.naturalOrder());
    }

    @Test(expected = NoSuchElementException.class)
    public void min_without_comparator()
    {
        Lists.fixedSize.of().min();
    }

    @Test(expected = NoSuchElementException.class)
    public void max_without_comparator()
    {
        Lists.fixedSize.of().max();
    }

    @Test(expected = NoSuchElementException.class)
    public void minBy()
    {
        Lists.fixedSize.of().minBy(String::valueOf);
    }

    @Test(expected = NoSuchElementException.class)
    public void maxBy()
    {
        Lists.fixedSize.of().maxBy(String::valueOf);
    }

    @Test
    public void zip()
    {
        Assert.assertEquals(
                Lists.fixedSize.of(),
                Lists.fixedSize.of().zip(FastList.newListWith(1, 2, 3)));
    }

    @Test
    public void zipWithIndex()
    {
        Assert.assertEquals(
                Lists.fixedSize.of(),
                Lists.fixedSize.of().zipWithIndex());
    }

    @Test
    public void chunk_large_size()
    {
        Assert.assertEquals(Lists.fixedSize.of(), Lists.fixedSize.of().chunk(10));
    }

    @Test
    public void sortThis()
    {
        MutableList<Object> expected = Lists.fixedSize.of();
        MutableList<Object> list = Lists.fixedSize.of();
        MutableList<Object> sortedList = list.sortThis();
        Assert.assertEquals(expected, sortedList);
        Assert.assertSame(sortedList, list);
    }

    @Test
    public void sortThisBy()
    {
        MutableList<Object> expected = Lists.fixedSize.of();
        MutableList<Object> list = Lists.fixedSize.of();
        MutableList<Object> sortedList = list.sortThisBy(String::valueOf);
        Assert.assertEquals(expected, sortedList);
        Assert.assertSame(sortedList, list);
    }

    @Test
    public void with()
    {
        MutableList<Integer> list = new EmptyList<Integer>().with(1);
        Verify.assertListsEqual(FastList.newListWith(1), list);
        Verify.assertInstanceOf(SingletonList.class, list);
    }

    @Test
    public void withAll()
    {
        MutableList<Integer> list = new EmptyList<Integer>().withAll(FastList.newListWith(1, 2));
        Verify.assertListsEqual(FastList.newListWith(1, 2), list);
        Verify.assertInstanceOf(DoubletonList.class, list);
    }

    @Test
    public void without()
    {
        MutableList<Integer> list = new EmptyList<>();
        Assert.assertSame(list, list.without(2));
    }

    @Test
    public void withoutAll()
    {
        MutableList<Integer> list = new EmptyList<>();
        Assert.assertEquals(list, list.withoutAll(FastList.newListWith(1, 2)));
    }

    @Test
    public void collectPrimitives()
    {
        MutableList<Integer> list = new EmptyList<>();
        Verify.assertEmpty(list.collectBoolean(PrimitiveFunctions.integerIsPositive()));
        Verify.assertEmpty(list.collectByte(PrimitiveFunctions.unboxIntegerToByte()));
        Verify.assertEmpty(list.collectChar(PrimitiveFunctions.unboxIntegerToChar()));
        Verify.assertEmpty(list.collectDouble(PrimitiveFunctions.unboxIntegerToDouble()));
        Verify.assertEmpty(list.collectFloat(PrimitiveFunctions.unboxIntegerToFloat()));
        Verify.assertEmpty(list.collectInt(PrimitiveFunctions.unboxIntegerToInt()));
        Verify.assertEmpty(list.collectLong(PrimitiveFunctions.unboxIntegerToLong()));
        Verify.assertEmpty(list.collectShort(PrimitiveFunctions.unboxIntegerToShort()));
    }
}
