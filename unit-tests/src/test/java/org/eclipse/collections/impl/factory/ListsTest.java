/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import java.util.List;

import org.eclipse.collections.api.factory.list.FixedSizeListFactory;
import org.eclipse.collections.api.factory.list.ImmutableListFactory;
import org.eclipse.collections.api.list.FixedSizeList;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ListsTest
{
    @Test
    public void immutables()
    {
        ImmutableListFactory listFactory = Lists.immutable;
        Assert.assertEquals(FastList.newList(), listFactory.of());
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of());
        Assert.assertEquals(FastList.newListWith(1), listFactory.of(1));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1));
        Assert.assertEquals(FastList.newListWith(1, 2), listFactory.of(1, 2));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), listFactory.of(1, 2, 3));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), listFactory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3, 4));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), listFactory.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3, 4, 5));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6), listFactory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), listFactory.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8), listFactory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9), listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), listFactory.ofAll(FastList.newListWith(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableList.class, listFactory.ofAll(FastList.newListWith(1, 2, 3)));
    }

    @Test
    public void immutableWithListTest()
    {
        Assert.assertEquals(Lists.mutable.of(), Lists.mutable.of().toImmutable());
        Assert.assertEquals(Lists.mutable.of(1).without(1), Lists.mutable.of(1).without(1).toImmutable());
        for (int i = 0; i < 12; i++)
        {
            MutableList<Integer> integers = Interval.fromTo(0, i).toList();
            Assert.assertEquals(integers, integers.toImmutable());
            Assert.assertEquals(integers.toImmutable(), integers.toImmutable());
        }
    }

    @Test
    public void fixedSize()
    {
        FixedSizeListFactory listFactory = Lists.fixedSize;
        Assert.assertEquals(FastList.newList(), listFactory.of());
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of());
        Assert.assertEquals(FastList.newListWith(1), listFactory.of(1));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1));
        Assert.assertEquals(FastList.newListWith(1, 2), listFactory.of(1, 2));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), listFactory.of(1, 2, 3));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), listFactory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3, 4));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), listFactory.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3, 4, 5));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6), listFactory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), listFactory.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8), listFactory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9), listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), listFactory.ofAll(FastList.newListWith(1, 2, 3)));
        Verify.assertInstanceOf(FixedSizeList.class, listFactory.ofAll(FastList.newListWith(1, 2, 3)));
    }

    @Test
    public void castToList()
    {
        List<Object> list = Lists.immutable.of().castToList();
        Assert.assertNotNull(list);
        Assert.assertSame(Lists.immutable.of(), list);
    }

    @Test
    public void newList()
    {
        for (int i = 1; i <= 11; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(FastList.newList(interval), Lists.immutable.ofAll(interval));
        }
    }

    @Test
    public void copyList()
    {
        Verify.assertInstanceOf(ImmutableList.class, Lists.immutable.ofAll(Lists.fixedSize.of()));
        MutableList<Integer> list = Lists.fixedSize.of(1);
        ImmutableList<Integer> immutableList = list.toImmutable();
        Verify.assertInstanceOf(ImmutableList.class, Lists.immutable.ofAll(list));
        Assert.assertSame(Lists.immutable.ofAll(immutableList.castToList()), immutableList);
    }

    @Test
    public void emptyList()
    {
        Assert.assertTrue(Lists.immutable.of().isEmpty());
        Assert.assertSame(Lists.immutable.of(), Lists.immutable.of());
        Verify.assertPostSerializedIdentity(Lists.immutable.of());
    }

    @Test
    public void newListWith()
    {
        ImmutableList<String> list = Lists.immutable.of();
        Assert.assertEquals(list, Lists.immutable.of(list.toArray()));
        Assert.assertEquals(list = list.newWith("1"), Lists.immutable.of("1"));
        Assert.assertEquals(list = list.newWith("2"), Lists.immutable.of("1", "2"));
        Assert.assertEquals(list = list.newWith("3"), Lists.immutable.of("1", "2", "3"));
        Assert.assertEquals(list = list.newWith("4"), Lists.immutable.of("1", "2", "3", "4"));
        Assert.assertEquals(list = list.newWith("5"), Lists.immutable.of("1", "2", "3", "4", "5"));
        Assert.assertEquals(list = list.newWith("6"), Lists.immutable.of("1", "2", "3", "4", "5", "6"));
        Assert.assertEquals(list = list.newWith("7"), Lists.immutable.of("1", "2", "3", "4", "5", "6", "7"));
        Assert.assertEquals(list = list.newWith("8"), Lists.immutable.of("1", "2", "3", "4", "5", "6", "7", "8"));
        Assert.assertEquals(list = list.newWith("9"), Lists.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Assert.assertEquals(list = list.newWith("10"), Lists.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Assert.assertEquals(list = list.newWith("11"), Lists.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
        Assert.assertEquals(list = list.newWith("12"), Lists.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
    }

    @Test
    public void newListWithArray()
    {
        ImmutableList<String> list = Lists.immutable.of();
        Assert.assertEquals(list = list.newWith("1"), Lists.immutable.of(new String[]{"1"}));
        Assert.assertEquals(list = list.newWith("2"), Lists.immutable.of(new String[]{"1", "2"}));
        Assert.assertEquals(list = list.newWith("3"), Lists.immutable.of(new String[]{"1", "2", "3"}));
        Assert.assertEquals(list = list.newWith("4"), Lists.immutable.of(new String[]{"1", "2", "3", "4"}));
        Assert.assertEquals(list = list.newWith("5"), Lists.immutable.of(new String[]{"1", "2", "3", "4", "5"}));
        Assert.assertEquals(list = list.newWith("6"), Lists.immutable.of(new String[]{"1", "2", "3", "4", "5", "6"}));
        Assert.assertEquals(list = list.newWith("7"), Lists.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7"}));
        Assert.assertEquals(list = list.newWith("8"), Lists.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
        Assert.assertEquals(list = list.newWith("9"), Lists.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        Assert.assertEquals(list = list.newWith("10"), Lists.immutable.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        Assert.assertEquals(list = list.newWith("11"), Lists.immutable.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
    }

    @Test
    public void newListWithList()
    {
        ImmutableList<String> list = Lists.immutable.of();
        FastList<String> fastList = FastList.newListWith("1");
        Assert.assertEquals(list = list.newWith("1"), fastList.toImmutable());
        Assert.assertEquals(list = list.newWith("2"), fastList.with("2").toImmutable());
        Assert.assertEquals(list = list.newWith("3"), fastList.with("3").toImmutable());
        Assert.assertEquals(list = list.newWith("4"), fastList.with("4").toImmutable());
        Assert.assertEquals(list = list.newWith("5"), fastList.with("5").toImmutable());
        Assert.assertEquals(list = list.newWith("6"), fastList.with("6").toImmutable());
        Assert.assertEquals(list = list.newWith("7"), fastList.with("7").toImmutable());
        Assert.assertEquals(list = list.newWith("8"), fastList.with("8").toImmutable());
        Assert.assertEquals(list = list.newWith("9"), fastList.with("9").toImmutable());
        Assert.assertEquals(list = list.newWith("10"), fastList.with("10").toImmutable());
        Assert.assertEquals(list = list.newWith("11"), fastList.with("11").toImmutable());
    }

    @Test
    public void newListWithWithList()
    {
        Assert.assertEquals(FastList.newList(), Lists.immutable.ofAll(FastList.newList()));
        for (int i = 0; i < 12; i++)
        {
            List<Integer> list = Interval.fromTo(0, i);
            Assert.assertEquals(list, Lists.immutable.ofAll(list));
        }
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Lists.class);
    }
}
