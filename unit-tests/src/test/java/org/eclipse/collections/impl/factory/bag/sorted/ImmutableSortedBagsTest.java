/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.bag.sorted;

import java.util.Comparator;
import java.util.List;

import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableSortedBagsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(TreeBag.newBag(), ImmutableSortedBag.of());
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of());
        Assert.assertEquals(TreeBag.newBagWith(1), ImmutableSortedBag.of(1));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1));
        Assert.assertEquals(TreeBag.newBagWith(1, 2), ImmutableSortedBag.of(1, 2));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3), ImmutableSortedBag.of(1, 2, 3));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4), ImmutableSortedBag.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3, 4));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5), ImmutableSortedBag.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3, 4, 5));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6), ImmutableSortedBag.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7), ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(TreeBag.newBagWith(3, 2, 1), ImmutableSortedBag.ofAll(TreeBag.newBagWith(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.ofAll(TreeBag.newBagWith(1, 2, 3)));

        Assert.assertEquals(TreeBag.newBag(), ImmutableSortedBag.of(Comparator.reverseOrder()));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder()));
        Assert.assertEquals(TreeBag.newBagWith(1), ImmutableSortedBag.of(Comparator.reverseOrder(), 1));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1));
        Assert.assertEquals(TreeBag.newBagWith(1, 2), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(TreeBag.newBagWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(TreeBag.newBagWith(3, 2, 1), ImmutableSortedBag.ofAll(Comparator.reverseOrder(), MutableSortedBag.of(1, 2, 3)));
        Verify.assertInstanceOf(ImmutableSortedBag.class, ImmutableSortedBag.ofAll(Comparator.reverseOrder(), MutableSortedBag.of(1, 2, 3)));
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 11; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(TreeBag.newBag(interval), ImmutableSortedBag.ofAll(interval));
            Verify.assertEqualsAndHashCode(TreeBag.newBag(interval), ImmutableSortedBag.ofAll(Comparator.reverseOrder(), interval));
        }
    }

    @Test
    public void emptyBag()
    {
        Assert.assertTrue(ImmutableSortedBag.of().isEmpty());
        Assert.assertSame(ImmutableSortedBag.of(), ImmutableSortedBag.of());
        Verify.assertPostSerializedIdentity(ImmutableSortedBag.of());
    }

    @Test
    public void newBagWith()
    {
        ImmutableSortedBag<String> bag = ImmutableSortedBag.of();
        Assert.assertEquals(bag, ImmutableSortedBag.of(bag.toArray()));
        Assert.assertEquals(bag = bag.newWith("1"), ImmutableSortedBag.of("1"));
        Assert.assertEquals(bag = bag.newWith("2"), ImmutableSortedBag.of("1", "2"));
        Assert.assertEquals(bag = bag.newWith("3"), ImmutableSortedBag.of("1", "2", "3"));
        Assert.assertEquals(bag = bag.newWith("4"), ImmutableSortedBag.of("1", "2", "3", "4"));
        Assert.assertEquals(bag = bag.newWith("5"), ImmutableSortedBag.of("1", "2", "3", "4", "5"));
        Assert.assertEquals(bag = bag.newWith("6"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6"));
        Assert.assertEquals(bag = bag.newWith("7"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6", "7"));
        Assert.assertEquals(bag = bag.newWith("8"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6", "7", "8"));
        Assert.assertEquals(bag = bag.newWith("9"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Assert.assertEquals(bag = bag.newWith("10"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Assert.assertEquals(bag = bag.newWith("11"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
        Assert.assertEquals(bag = bag.newWith("12"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
    }

    @Test
    public void newBagWithArray()
    {
        ImmutableSortedBag<String> bag = ImmutableSortedBag.of();
        Assert.assertEquals(bag = bag.newWith("1"), ImmutableSortedBag.of(new String[]{"1"}));
        Assert.assertEquals(bag = bag.newWith("2"), ImmutableSortedBag.of(new String[]{"1", "2"}));
        Assert.assertEquals(bag = bag.newWith("3"), ImmutableSortedBag.of(new String[]{"1", "2", "3"}));
        Assert.assertEquals(bag = bag.newWith("4"), ImmutableSortedBag.of(new String[]{"1", "2", "3", "4"}));
        Assert.assertEquals(bag = bag.newWith("5"), ImmutableSortedBag.of(new String[]{"1", "2", "3", "4", "5"}));
        Assert.assertEquals(bag = bag.newWith("6"), ImmutableSortedBag.of(new String[]{"1", "2", "3", "4", "5", "6"}));
        Assert.assertEquals(bag = bag.newWith("7"), ImmutableSortedBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7"}));
        Assert.assertEquals(bag = bag.newWith("8"), ImmutableSortedBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}));
        Assert.assertEquals(bag = bag.newWith("9"), ImmutableSortedBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        Assert.assertEquals(bag = bag.newWith("10"), ImmutableSortedBag.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        Assert.assertEquals(bag = bag.newWith("11"), ImmutableSortedBag.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
    }

    @Test
    public void newBagWithWithBag()
    {
        Assert.assertEquals(TreeBag.newBag(), ImmutableSortedBag.ofAll(TreeBag.newBag()));
        Assert.assertEquals(TreeBag.newBag(), ImmutableSortedBag.ofAll(Comparator.<String>reverseOrder(), TreeBag.newBag()));
        for (int i = 0; i < 12; i++)
        {
            List<Integer> interval = Interval.fromTo(0, i);
            Assert.assertEquals(TreeBag.newBag(interval), ImmutableSortedBag.ofAll(interval));
            Assert.assertEquals(TreeBag.newBag(interval), ImmutableSortedBag.ofAll(Comparator.reverseOrder(), interval));
        }
    }
}
