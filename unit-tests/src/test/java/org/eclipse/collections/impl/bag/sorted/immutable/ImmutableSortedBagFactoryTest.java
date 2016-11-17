/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.immutable;

import java.util.Comparator;

import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableSortedBagFactoryTest
{
    @Test
    public void empty()
    {
        Assert.assertEquals(TreeBag.newBag(), SortedBags.immutable.empty());
        Verify.assertInstanceOf(ImmutableSortedBag.class, SortedBags.immutable.empty());

        Assert.assertEquals(TreeBag.newBag(Comparators.reverseNaturalOrder()), SortedBags.immutable.empty(Comparators.reverseNaturalOrder()));
        Verify.assertInstanceOf(ImmutableSortedBag.class, SortedBags.immutable.empty(Comparators.reverseNaturalOrder()));
    }

    @Test
    public void ofElements()
    {
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.mutable.of(1, 1, 2)), SortedBags.immutable.of(1, 1, 2));
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.mutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2)), SortedBags.immutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2));

        Assert.assertEquals(TreeBag.newBag(), SortedBags.immutable.of());
        Verify.assertInstanceOf(ImmutableSortedBag.class, SortedBags.immutable.of());
        Comparator<Integer> nullComparator = null;
        Assert.assertEquals(TreeBag.newBag(), SortedBags.immutable.of(nullComparator));
        Verify.assertInstanceOf(ImmutableSortedBag.class, SortedBags.immutable.of(nullComparator));
        Assert.assertEquals(TreeBag.newBag(Comparators.reverseNaturalOrder()), SortedBags.immutable.of(Comparator.reverseOrder()));
        Verify.assertInstanceOf(ImmutableSortedBag.class, SortedBags.immutable.of(Comparator.reverseOrder()));
        Assert.assertEquals(TreeBag.newBag(Comparators.reverseNaturalOrder()), SortedBags.immutable.of(Comparator.reverseOrder(), new Integer[]{}));
        Verify.assertInstanceOf(ImmutableSortedBag.class, SortedBags.immutable.of(Comparator.reverseOrder(), new Integer[]{}));
        Assert.assertEquals(TreeBag.newBag(), SortedBags.immutable.of(new Integer[]{}));
        Verify.assertInstanceOf(ImmutableSortedBag.class, SortedBags.immutable.of(new Integer[]{}));
    }

    @Test
    public void withElements()
    {
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.mutable.with(1, 1, 2)), SortedBags.immutable.with(1, 1, 2));
        Verify.assertThrows(IllegalArgumentException.class, () -> new ImmutableSortedBagImpl<>(SortedBags.mutable.with(Comparators.reverseNaturalOrder(), FastList.newList().toArray())));
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.mutable.with(Comparators.reverseNaturalOrder(), 1, 1, 2)), SortedBags.immutable.with(Comparators.reverseNaturalOrder(), 1, 1, 2));
    }

    @Test
    public void ofAll()
    {
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.mutable.of(1, 1, 2)), SortedBags.immutable.ofAll(new ImmutableSortedBagImpl<>(TreeBag.newBagWith(1, 1, 2))));
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.mutable.of(1, 1, 2)), SortedBags.immutable.ofAll(FastList.newListWith(1, 1, 2)));
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.mutable.of(Comparators.reverseNaturalOrder(), 1, 1, 2)), SortedBags.immutable.ofAll(Comparators.reverseNaturalOrder(), FastList.newListWith(1, 1, 2)));
    }

    @Test
    public void ofSortedBag()
    {
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.immutable.of(1)), SortedBags.immutable.ofSortedBag(new ImmutableSortedBagImpl<>(TreeBag.newBagWith(1))));
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.immutable.of(1)), SortedBags.immutable.ofSortedBag(TreeBag.newBagWith(1)));
        Assert.assertEquals(SortedBags.immutable.of(Comparators.reverseNaturalOrder()), SortedBags.immutable.ofSortedBag(TreeBag.newBag(Comparators.reverseNaturalOrder())));
    }

    @Test
    public void withSortedBag()
    {
        Assert.assertEquals(new ImmutableSortedBagImpl<>(SortedBags.immutable.of(1)), SortedBags.immutable.ofSortedBag(new ImmutableSortedBagImpl<>(TreeBag.newBagWith(1))));
    }
}
