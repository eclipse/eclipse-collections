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

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MutableSortedBagsTest
{
    @Test
    public void of()
    {
        Verify.assertBagsEqual(TreeBag.newBag(), MutableSortedBag.of());
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of());
        Verify.assertBagsEqual(TreeBag.newBagWith(1), MutableSortedBag.of(1));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(1));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2), MutableSortedBag.of(1, 2));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(1, 2));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3), MutableSortedBag.of(1, 2, 3));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(1, 2, 3));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3, 4), MutableSortedBag.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(1, 2, 3, 4));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3, 4, 5), MutableSortedBag.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(1, 2, 3, 4, 5));

        Verify.assertBagsEqual(TreeBag.newBag(), MutableSortedBag.of(Comparator.reverseOrder()));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(Comparator.reverseOrder()));
        Verify.assertBagsEqual(TreeBag.newBagWith(1), MutableSortedBag.of(Comparator.reverseOrder(), 1));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(Comparator.reverseOrder(), 1));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2), MutableSortedBag.of(Comparator.reverseOrder(), 1, 2));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(Comparator.reverseOrder(), 1, 2));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3), MutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3, 4), MutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3, 4, 5), MutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.of(Comparator.reverseOrder(), 1, 2, 3, 4, 5));

        Bag<Integer> bag1 = TreeBag.newBagWith(1, 2, 2, 3, 3, 3);
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 2, 3, 3, 3), MutableSortedBag.ofAll(bag1));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.ofAll(bag1));
        Assert.assertNotSame(MutableSortedBag.ofAll(bag1), bag1);
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 2, 3, 3, 3), MutableSortedBag.ofAll(MutableList.of(1, 2, 2, 3, 3, 3)));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.ofAll(MutableList.of(1, 2, 2, 3, 3, 3)));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3, 4, 5), MutableSortedBag.ofAll(MutableSet.of(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.ofAll(MutableSet.of(1, 2, 3, 4, 5)));

        Bag<Integer> bag2 = TreeBag.newBagWith(Comparator.reverseOrder(), 1, 2, 2, 3, 3, 3);
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 2, 3, 3, 3), MutableSortedBag.ofAll(bag2));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.ofAll(bag2));
        Assert.assertNotSame(MutableSortedBag.ofAll(bag2), bag2);
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 2, 3, 3, 3), MutableSortedBag.ofAll(Comparator.reverseOrder(), MutableList.of(1, 2, 2, 3, 3, 3)));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.ofAll(Comparator.reverseOrder(), MutableList.of(1, 2, 2, 3, 3, 3)));
        Verify.assertBagsEqual(TreeBag.newBagWith(1, 2, 3, 4, 5), MutableSortedBag.ofAll(Comparator.reverseOrder(), MutableSet.of(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(MutableSortedBag.class, MutableSortedBag.ofAll(Comparator.reverseOrder(), MutableSet.of(1, 2, 3, 4, 5)));
    }
}
