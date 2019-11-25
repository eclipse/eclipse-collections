/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.list;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableListsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(FastList.newList(), ImmutableList.of());
        Assert.assertEquals(FastList.newList(), ImmutableList.empty());
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of());
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.empty());
        Assert.assertEquals(FastList.newListWith(1), ImmutableList.of(1));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1));
        Assert.assertEquals(FastList.newListWith(1, 2), ImmutableList.of(1, 2));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), ImmutableList.of(1, 2, 3));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), ImmutableList.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3, 4));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), ImmutableList.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3, 4, 5));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6), ImmutableList.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), ImmutableList.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8), ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), ImmutableList.ofAll(FastList.newListWith(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.ofAll(FastList.newListWith(1, 2, 3)));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), ImmutableList.fromStream(Stream.of(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.fromStream(Stream.of(1, 2, 3)));
    }

    @Test
    public void castToList()
    {
        List<Object> list = ImmutableList.of().castToList();
        Assert.assertNotNull(list);
        Assert.assertSame(ImmutableList.of(), list);
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 11; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(interval, ImmutableList.ofAll(interval));
            Stream<Integer> stream = IntStream.rangeClosed(1, i).boxed();
            Verify.assertEqualsAndHashCode(interval, ImmutableList.fromStream(stream));
        }
    }

    @Test
    public void copyList()
    {
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.ofAll(Lists.fixedSize.of()));
        MutableList<Integer> list = Lists.fixedSize.of(1);
        ImmutableList<Integer> immutableList = list.toImmutable();
        Verify.assertInstanceOf(ImmutableList.class, ImmutableList.ofAll(list));
        Assert.assertSame(ImmutableList.ofAll(immutableList.castToList()), immutableList);
    }

    @Test
    public void emptyList()
    {
        Assert.assertTrue(ImmutableList.of().isEmpty());
        Assert.assertSame(ImmutableList.of(), ImmutableList.of());
        Verify.assertPostSerializedIdentity(ImmutableList.of());
    }

    @Test
    public void newListWith()
    {
        ImmutableList<String> list = ImmutableList.of();
        Assert.assertEquals(list, ImmutableList.of(list.toArray()));
        Assert.assertEquals(list = list.newWith("1"), ImmutableList.of("1"));
        Assert.assertEquals(list = list.newWith("2"), ImmutableList.of("1", "2"));
        Assert.assertEquals(list = list.newWith("3"), ImmutableList.of("1", "2", "3"));
        Assert.assertEquals(list = list.newWith("4"), ImmutableList.of("1", "2", "3", "4"));
        Assert.assertEquals(list = list.newWith("5"), ImmutableList.of("1", "2", "3", "4", "5"));
        Assert.assertEquals(list = list.newWith("6"), ImmutableList.of("1", "2", "3", "4", "5", "6"));
        Assert.assertEquals(list = list.newWith("7"), ImmutableList.of("1", "2", "3", "4", "5", "6", "7"));
        Assert.assertEquals(list = list.newWith("8"), ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8"));
        Assert.assertEquals(list = list.newWith("9"), ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Assert.assertEquals(list = list.newWith("10"), ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Assert.assertEquals(list = list.newWith("11"), ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
        Assert.assertEquals(list = list.newWith("12"), ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
    }

    @Test
    public void newListWithArray()
    {
        ImmutableList<String> list = ImmutableList.of();
        Assert.assertEquals(list = list.newWith("1"), ImmutableList.of(new String[]{"1"}));
        Assert.assertEquals(list = list.newWith("2"), ImmutableList.of(new String[]{"1", "2"}));
        Assert.assertEquals(list = list.newWith("3"), ImmutableList.of(new String[]{"1", "2", "3"}));
        Assert.assertEquals(list = list.newWith("4"), ImmutableList.of(new String[]{"1", "2", "3", "4"}));
        Assert.assertEquals(list = list.newWith("5"), ImmutableList.of(new String[]{"1", "2", "3", "4", "5"}));
        Assert.assertEquals(list = list.newWith("6"), ImmutableList.of(new String[]{"1", "2", "3", "4", "5", "6"}));
        Assert.assertEquals(list = list.newWith("7"), ImmutableList.of(new String[]{"1", "2", "3", "4", "5", "6", "7"}));
        Assert.assertEquals(list = list.newWith("8"), ImmutableList.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
        Assert.assertEquals(list = list.newWith("9"), ImmutableList.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        Assert.assertEquals(list = list.newWith("10"), ImmutableList.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        Assert.assertEquals(list = list.newWith("11"), ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
    }

    @Test
    public void newListWithWithList()
    {
        Assert.assertEquals(FastList.newList(), ImmutableList.ofAll(FastList.newList()));
        for (int i = 0; i < 12; i++)
        {
            List<Integer> list = Interval.fromTo(0, i);
            Assert.assertEquals(list, ImmutableList.ofAll(list));
        }
    }
}
