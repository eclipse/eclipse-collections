/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.bag;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableBagsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(HashBag.newBag(), ImmutableBag.of());
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of());
        Assert.assertEquals(HashBag.newBagWith(1), ImmutableBag.of(1));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1));
        Assert.assertEquals(HashBag.newBagWith(1, 2), ImmutableBag.of(1, 2));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3), ImmutableBag.of(1, 2, 3));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3, 4), ImmutableBag.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3, 4));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5), ImmutableBag.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3, 4, 5));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6), ImmutableBag.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6, 7), ImmutableBag.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8), ImmutableBag.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(HashBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(HashBag.newBagWith(3, 2, 1), ImmutableBag.ofAll(HashBag.newBagWith(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.ofAll(HashBag.newBagWith(1, 2, 3)));
        Assert.assertEquals(HashBag.newBagWith(3, 2, 1), ImmutableBag.fromStream(Stream.of(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableBag.class, ImmutableBag.fromStream(Stream.of(1, 2, 3)));
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 11; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(HashBag.newBag(interval), ImmutableBag.ofAll(interval));
            Stream<Integer> stream = IntStream.rangeClosed(1, i).boxed();
            Verify.assertEqualsAndHashCode(HashBag.newBag(interval), ImmutableBag.fromStream(stream));
        }
    }

    @Test
    public void emptyBag()
    {
        Assert.assertTrue(ImmutableBag.of().isEmpty());
        Assert.assertSame(ImmutableBag.of(), ImmutableBag.of());
        Verify.assertPostSerializedIdentity(ImmutableBag.of());
    }

    @Test
    public void newBagWith()
    {
        ImmutableBag<String> bag = ImmutableBag.of();
        Assert.assertEquals(bag, ImmutableBag.of(bag.toArray()));
        Assert.assertEquals(bag = bag.newWith("1"), ImmutableBag.of("1"));
        Assert.assertEquals(bag = bag.newWith("2"), ImmutableBag.of("1", "2"));
        Assert.assertEquals(bag = bag.newWith("3"), ImmutableBag.of("1", "2", "3"));
        Assert.assertEquals(bag = bag.newWith("4"), ImmutableBag.of("1", "2", "3", "4"));
        Assert.assertEquals(bag = bag.newWith("5"), ImmutableBag.of("1", "2", "3", "4", "5"));
        Assert.assertEquals(bag = bag.newWith("6"), ImmutableBag.of("1", "2", "3", "4", "5", "6"));
        Assert.assertEquals(bag = bag.newWith("7"), ImmutableBag.of("1", "2", "3", "4", "5", "6", "7"));
        Assert.assertEquals(bag = bag.newWith("8"), ImmutableBag.of("1", "2", "3", "4", "5", "6", "7", "8"));
        Assert.assertEquals(bag = bag.newWith("9"), ImmutableBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Assert.assertEquals(bag = bag.newWith("10"), ImmutableBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Assert.assertEquals(bag = bag.newWith("11"), ImmutableBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
        Assert.assertEquals(bag = bag.newWith("12"), ImmutableBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
    }

    @Test
    public void newBagWithArray()
    {
        ImmutableBag<String> bag = ImmutableBag.of();
        Assert.assertEquals(bag = bag.newWith("1"), ImmutableBag.of(new String[]{"1"}));
        Assert.assertEquals(bag = bag.newWith("2"), ImmutableBag.of(new String[]{"1", "2"}));
        Assert.assertEquals(bag = bag.newWith("3"), ImmutableBag.of(new String[]{"1", "2", "3"}));
        Assert.assertEquals(bag = bag.newWith("4"), ImmutableBag.of(new String[]{"1", "2", "3", "4"}));
        Assert.assertEquals(bag = bag.newWith("5"), ImmutableBag.of(new String[]{"1", "2", "3", "4", "5"}));
        Assert.assertEquals(bag = bag.newWith("6"), ImmutableBag.of(new String[]{"1", "2", "3", "4", "5", "6"}));
        Assert.assertEquals(bag = bag.newWith("7"), ImmutableBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7"}));
        Assert.assertEquals(bag = bag.newWith("8"), ImmutableBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
        Assert.assertEquals(bag = bag.newWith("9"), ImmutableBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        Assert.assertEquals(bag = bag.newWith("10"), ImmutableBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        Assert.assertEquals(bag = bag.newWith("11"), ImmutableBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
    }

    @Test
    public void newBagWithWithBag()
    {
        Assert.assertEquals(HashBag.newBag(), ImmutableBag.ofAll(HashBag.newBag()));
        for (int i = 0; i < 12; i++)
        {
            List<Integer> interval = Interval.fromTo(0, i);
            Assert.assertEquals(HashBag.newBag(interval), ImmutableBag.ofAll(interval));
        }
    }
}
