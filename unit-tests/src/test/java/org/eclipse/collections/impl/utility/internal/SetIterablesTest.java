/*
 * Copyright (c) 2020 The Bank of New York Mellon and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility.internal;

import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.tuple.primitive.LongObjectPair;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.Assert;
import org.junit.Test;

public class SetIterablesTest
{
    @Test
    public void union()
    {
        this.assertUnion(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.with(1, 2, 3, 4, 5));

        this.assertUnion(
                Sets.mutable.with(1, 2L, 3),
                Sets.immutable.with(3, 4L, 5),
                Sets.mutable.with(1, 2L, 3, 4L, 5));

        this.assertUnion(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3L, 4L, 5L),
                Sets.mutable.<Number>with(1, 2, 3, 3L, 4L, 5L));

        this.assertUnion(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.with(1, 2, 3, 4, 5));

        this.assertUnion(
                Sets.mutable.with(1, 2, 3, 6),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.with(1, 2, 3, 4, 5, 6));

        this.assertUnion(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3, 4, 5, 6),
                Sets.mutable.with(1, 2, 3, 4, 5, 6));

        this.assertUnion(
                Sets.mutable.empty(),
                Sets.immutable.empty(),
                Sets.mutable.empty());

        this.assertUnion(
                Sets.mutable.empty(),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.with(3, 4, 5));

        this.assertUnion(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.empty(),
                Sets.mutable.with(1, 2, 3));
    }

    private void assertUnion(
            SetIterable<? extends Number> set1,
            SetIterable<? extends Number> set2,
            SetIterable<? extends Number> expected)
    {
        SetIterable<? extends Number> actual1 = SetIterables.union(set1, set2);
        SetIterable<? extends Number> actual2 = SetIterables.union(set2, set1);
        Assert.assertEquals(expected, actual1);
        Assert.assertEquals(expected, actual2);
    }

    @Test
    public void intersect()
    {
        this.assertIntersect(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.with(3));

        this.assertIntersect(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3L, 4L, 5L),
                Sets.mutable.empty());

        this.assertIntersect(
                Sets.mutable.with(1, 2, 3, 6),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.with(3));

        this.assertIntersect(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3, 4, 5, 6),
                Sets.mutable.with(3));

        this.assertIntersect(
                Sets.mutable.empty(),
                Sets.immutable.empty(),
                Sets.mutable.empty());

        this.assertIntersect(
                Sets.mutable.empty(),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.empty());

        this.assertIntersect(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.empty(),
                Sets.mutable.empty());
    }

    private void assertIntersect(
            SetIterable<? extends Number> set1,
            SetIterable<? extends Number> set2,
            SetIterable<? extends Number> expected)
    {
        SetIterable<? extends Number> actual1 = SetIterables.intersect(set1, set2);
        SetIterable<? extends Number> actual2 = SetIterables.intersect(set2, set1);
        Assert.assertEquals(expected, actual1);
        Assert.assertEquals(expected, actual2);
    }

    @Test
    public void difference()
    {
        this.assertDifference(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(3, 4, 5),
                Sets.mutable.with(1, 2));

        this.assertDifference(
                Sets.mutable.with(1, 2, 3),
                Sets.immutable.with(1, 2, 3),
                Sets.mutable.empty());

        this.assertDifference(
                Sets.mutable.empty(),
                Sets.immutable.with(),
                Sets.mutable.empty());

        this.assertDifference(
                Sets.immutable.empty(),
                Sets.mutable.with(3, 4, 5),
                Sets.mutable.empty());

        this.assertDifference(
                Sets.immutable.with(1, 2, 3),
                Sets.mutable.empty(),
                Sets.mutable.with(1, 2, 3));
    }

    private void assertDifference(
            SetIterable<? extends Number> set1,
            SetIterable<? extends Number> set2,
            SetIterable<? extends Number> expected)
    {
        SetIterable<? extends Number> actual = SetIterables.difference(set1, set2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void unionPerformance()
    {
        MutableSet<Integer> setA = Interval.oneTo(1000).toSet();
        MutableSet<Integer> setB = Interval.fromTo(900, 1100).toSet();
        LongObjectPair<MutableSet<Integer>> union1 =
                this.timeCalls(() -> SetIterables.union(setA, setB), 1000);
        LongObjectPair<MutableSet<Integer>> union2 =
                this.timeCalls(() -> SetIterables.union(setB, setA), 1000);
        LongObjectPair<MutableSet<Integer>> unionInto1 =
                this.timeCalls(() -> SetIterables.unionInto(setA, setB, Sets.mutable.empty()), 1000);
        LongObjectPair<MutableSet<Integer>> unionInto2 =
                this.timeCalls(() -> SetIterables.unionInto(setB, setA, Sets.mutable.empty()), 1000);
        MutableSet<Integer> expected = Interval.oneTo(1100).toSet();
        Assert.assertEquals(expected, union1.getTwo());
        Assert.assertEquals(expected, union2.getTwo());
        Assert.assertEquals(expected, unionInto1.getTwo());
        Assert.assertEquals(expected, unionInto2.getTwo());
        Assert.assertTrue(union1.getOne() + union2.getOne() < unionInto1.getOne() + unionInto2.getOne());
    }

    public LongObjectPair<MutableSet<Integer>> timeCalls(Function0<MutableSet<Integer>> call, int times)
    {
        MutableSet<Integer> result = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++)
        {
            result = call.value();
        }
        long end = System.currentTimeMillis();
        return PrimitiveTuples.pair(end - start, result);
    }
}
