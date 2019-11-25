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

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.list.FixedSizeList;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class FixedSizeListsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(FastList.newList(), FixedSizeList.of());
        Assert.assertEquals(FastList.newList(), FixedSizeList.empty());
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of());
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.empty());
        Assert.assertEquals(FastList.newListWith(1), FixedSizeList.of(1));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1));
        Assert.assertEquals(FastList.newListWith(1, 2), FixedSizeList.of(1, 2));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), FixedSizeList.of(1, 2, 3));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), FixedSizeList.of(1, 2, 3, 4));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3, 4));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), FixedSizeList.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3, 4, 5));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6), FixedSizeList.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), FixedSizeList.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8), FixedSizeList.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9), FixedSizeList.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), FixedSizeList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), FixedSizeList.ofAll(FastList.newListWith(1, 2, 3)));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.ofAll(FastList.newListWith(1, 2, 3)));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), FixedSizeList.fromStream(Stream.of(1, 2, 3)));
        Verify.assertInstanceOf(FixedSizeList.class, FixedSizeList.fromStream(Stream.of(1, 2, 3)));
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 11; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(interval, FixedSizeList.ofAll(interval));
            Stream<Integer> stream = IntStream.rangeClosed(1, i).boxed();
            Verify.assertEqualsAndHashCode(interval, FixedSizeList.fromStream(stream));
        }
    }

    @Test
    public void emptyList()
    {
        Assert.assertTrue(FixedSizeList.of().isEmpty());
        Assert.assertSame(FixedSizeList.of(), FixedSizeList.of());
        Verify.assertPostSerializedIdentity(FixedSizeList.of());
    }
}
