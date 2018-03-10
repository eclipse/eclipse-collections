/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class IterablesTest
{
    @Test
    public void immutableLists()
    {
        this.assertEqualsAndInstanceOf(FastList.newList().toImmutable(), Iterables.iList(), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1), Iterables.iList(1), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2), Iterables.iList(1, 2), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3), Iterables.iList(1, 2, 3), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4), Iterables.iList(1, 2, 3, 4), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5), Iterables.iList(1, 2, 3, 4, 5), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6), Iterables.iList(1, 2, 3, 4, 5, 6), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7), Iterables.iList(1, 2, 3, 4, 5, 6, 7), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8), Iterables.iList(1, 2, 3, 4, 5, 6, 7, 8), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9), Iterables.iList(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10), Iterables.iList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11), Iterables.iList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), ImmutableList.class);
    }

    @Test
    public void mutableLists()
    {
        this.assertEqualsAndInstanceOf(FastList.newList(), Iterables.mList(), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1), Iterables.mList(1), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2), Iterables.mList(1, 2), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3), Iterables.mList(1, 2, 3), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4), Iterables.mList(1, 2, 3, 4), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5), Iterables.mList(1, 2, 3, 4, 5), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6), Iterables.mList(1, 2, 3, 4, 5, 6), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7), Iterables.mList(1, 2, 3, 4, 5, 6, 7), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8), Iterables.mList(1, 2, 3, 4, 5, 6, 7, 8), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9), Iterables.mList(1, 2, 3, 4, 5, 6, 7, 8, 9), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10), Iterables.mList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), MutableList.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11), Iterables.mList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), MutableList.class);
    }

    @Test
    public void immutableSets()
    {
        this.assertEqualsAndInstanceOf(UnifiedSet.newSet().toImmutable(), Iterables.iSet(), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSet(), Iterables.iSet(1), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSet(), Iterables.iSet(1, 2), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSet(), Iterables.iSet(1, 2, 3), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSet(), Iterables.iSet(1, 2, 3, 4), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toSet(), Iterables.iSet(1, 2, 3, 4, 5), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toSet(), Iterables.iSet(1, 2, 3, 4, 5, 6), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toSet(), Iterables.iSet(1, 2, 3, 4, 5, 6, 7), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toSet(), Iterables.iSet(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toSet(), Iterables.iSet(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toSet(), Iterables.iSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toSet(), Iterables.iSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), ImmutableSet.class);
    }

    @Test
    public void mutableSets()
    {
        this.assertEqualsAndInstanceOf(UnifiedSet.newSet(), Iterables.mSet(), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSet(), Iterables.mSet(1), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSet(), Iterables.mSet(1, 2), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSet(), Iterables.mSet(1, 2, 3), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSet(), Iterables.mSet(1, 2, 3, 4), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toSet(), Iterables.mSet(1, 2, 3, 4, 5), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toSet(), Iterables.mSet(1, 2, 3, 4, 5, 6), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toSet(), Iterables.mSet(1, 2, 3, 4, 5, 6, 7), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toSet(), Iterables.mSet(1, 2, 3, 4, 5, 6, 7, 8), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toSet(), Iterables.mSet(1, 2, 3, 4, 5, 6, 7, 8, 9), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toSet(), Iterables.mSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), MutableSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toSet(), Iterables.mSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), MutableSet.class);
    }

    @Test
    public void mutableBags()
    {
        this.assertEqualsAndInstanceOf(HashBag.newBag(), Iterables.mBag(), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toBag(), Iterables.mBag(1), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toBag(), Iterables.mBag(1, 2), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toBag(), Iterables.mBag(1, 2, 3), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toBag(), Iterables.mBag(1, 2, 3, 4), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toBag(), Iterables.mBag(1, 2, 3, 4, 5), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toBag(), Iterables.mBag(1, 2, 3, 4, 5, 6), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toBag(), Iterables.mBag(1, 2, 3, 4, 5, 6, 7), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toBag(), Iterables.mBag(1, 2, 3, 4, 5, 6, 7, 8), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toBag(), Iterables.mBag(1, 2, 3, 4, 5, 6, 7, 8, 9), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toBag(), Iterables.mBag(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), MutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toBag(), Iterables.mBag(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), MutableBag.class);
    }

    @Test
    public void immutableBags()
    {
        this.assertEqualsAndInstanceOf(HashBag.newBag(), Iterables.iBag(), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toBag(), Iterables.iBag(1), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toBag(), Iterables.iBag(1, 2), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toBag(), Iterables.iBag(1, 2, 3), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toBag(), Iterables.iBag(1, 2, 3, 4), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toBag(), Iterables.iBag(1, 2, 3, 4, 5), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toBag(), Iterables.iBag(1, 2, 3, 4, 5, 6), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toBag(), Iterables.iBag(1, 2, 3, 4, 5, 6, 7), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toBag(), Iterables.iBag(1, 2, 3, 4, 5, 6, 7, 8), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toBag(), Iterables.iBag(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toBag(), Iterables.iBag(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableBag.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toBag(), Iterables.iBag(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), ImmutableBag.class);
    }

    @Test
    public void immutableSortedSets()
    {
        this.assertEqualsAndInstanceOf(TreeSortedSet.newSet(), Iterables.iSortedSet(), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedSet(), Iterables.iSortedSet(1), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedSet(), Iterables.iSortedSet(1, 2), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedSet(), Iterables.iSortedSet(1, 2, 3), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4, 5), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4, 5, 6), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4, 5, 6, 7), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toSortedSet(), Iterables.iSortedSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), ImmutableSortedSet.class);
    }

    @Test
    public void immutableSortedSetsWithComparator()
    {
        this.assertEqualsAndInstanceOf(TreeSortedSet.newSet(Comparators.reverseNaturalOrder()), Iterables.iSortedSet(Comparators.reverseNaturalOrder()), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.iSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), ImmutableSortedSet.class);
    }

    @Test
    public void mutableSortedSetsWithComparator()
    {
        this.assertEqualsAndInstanceOf(TreeSortedSet.newSet(Comparators.reverseNaturalOrder()), Iterables.iSortedSet(Comparators.reverseNaturalOrder()), ImmutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toSortedSet(Comparators.reverseNaturalOrder()),
                Iterables.mSortedSet(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), MutableSortedSet.class);
    }

    @Test
    public void mutableSortedSets()
    {
        this.assertEqualsAndInstanceOf(TreeSortedSet.newSet(), Iterables.mSortedSet(), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedSet(), Iterables.mSortedSet(1), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedSet(), Iterables.mSortedSet(1, 2), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedSet(), Iterables.mSortedSet(1, 2, 3), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(5).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4, 5), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(6).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4, 5, 6), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(7).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4, 5, 6, 7), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(8).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4, 5, 6, 7, 8), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(9).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4, 5, 6, 7, 8, 9), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(10).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), MutableSortedSet.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(11).toSortedSet(), Iterables.mSortedSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), MutableSortedSet.class);
    }

    @Test
    public void mutableSortedMaps()
    {
        this.assertEqualsAndInstanceOf(TreeSortedMap.newMap(), Iterables.mSortedMap(), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(1, 1), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(1, 1, 2, 2), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(1, 1, 2, 2, 3, 3), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(1, 1, 2, 2, 3, 3, 4, 4), MutableSortedMap.class);
    }

    @Test
    public void mutableSortedMapsWithComparator()
    {
        this.assertEqualsAndInstanceOf(TreeSortedMap.newMap(Comparators.reverseNaturalOrder()), Iterables.mSortedMap(Comparators.reverseNaturalOrder()), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(TreeSortedMap.newMap(Comparators.reverseNaturalOrder()), Iterables.mSortedMap(null), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3, 4, 4), MutableSortedMap.class);
    }

    @Test
    public void mutableSortedMapsWithFunction()
    {
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedMapBy(key -> -key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedMapBy(key -> -key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedMapBy(key -> key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3), MutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedMapBy(key -> -key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3, 4, 4), MutableSortedMap.class);
    }

    @Test
    public void immutableSortedMaps()
    {
        this.assertEqualsAndInstanceOf(TreeSortedMap.newMap(), Iterables.iSortedMap(), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(1, 1), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(1, 1, 2, 2), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(1, 1, 2, 2, 3, 3), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(1, 1, 2, 2, 3, 3, 4, 4), ImmutableSortedMap.class);
    }

    @Test
    public void immutableSortedMapsWithComparator()
    {
        this.assertEqualsAndInstanceOf(TreeSortedMap.newMap(Comparators.reverseNaturalOrder()), Iterables.iSortedMap(Comparators.reverseNaturalOrder()), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedMap(Comparators.reverseNaturalOrder(), Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3, 4, 4), ImmutableSortedMap.class);
    }

    @Test
    public void immutableSortedMapsWithFunction()
    {
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toSortedMapBy(key -> -key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toSortedMapBy(key -> -key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toSortedMapBy(key -> -key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3), ImmutableSortedMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toSortedMapBy(key -> -key, Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iSortedMap(Comparators.reverseNaturalOrder(), 1, 1, 2, 2, 3, 3, 4, 4), ImmutableSortedMap.class);
    }

    @Test
    public void mutableMaps()
    {
        this.assertEqualsAndInstanceOf(UnifiedMap.newMap(), Iterables.mMap(), MutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mMap(1, 1), MutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mMap(1, 1, 2, 2), MutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mMap(1, 1, 2, 2, 3, 3), MutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.mMap(1, 1, 2, 2, 3, 3, 4, 4), MutableMap.class);
    }

    @Test
    public void immutableMaps()
    {
        this.assertEqualsAndInstanceOf(UnifiedMap.newMap(), Iterables.iMap(), ImmutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(1).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iMap(1, 1), ImmutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(2).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iMap(1, 1, 2, 2), ImmutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(3).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iMap(1, 1, 2, 2, 3, 3), ImmutableMap.class);
        this.assertEqualsAndInstanceOf(Interval.oneTo(4).toMap(Functions.getPassThru(), Functions.getPassThru()),
                Iterables.iMap(1, 1, 2, 2, 3, 3, 4, 4), ImmutableMap.class);
    }

    public void assertEqualsAndInstanceOf(Object expected, Object actual, Class<?> clazz)
    {
        Verify.assertEqualsAndHashCode(expected, actual);
        Verify.assertInstanceOf(clazz, actual);
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Iterables.class);
    }
}
