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

import java.util.Comparator;

import org.eclipse.collections.api.factory.set.sorted.ImmutableSortedSetFactory;
import org.eclipse.collections.api.factory.set.sorted.MutableSortedSetFactory;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class SortedSetsTest
{
    @Test
    public void immutables()
    {
        ImmutableSortedSetFactory factory = SortedSets.immutable;
        Assert.assertEquals(UnifiedSet.newSet(), factory.of());
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of());
        Assert.assertEquals(UnifiedSet.newSet(), factory.with());
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.with());
        Assert.assertEquals(TreeSortedSet.newSet(Comparators.reverseNaturalOrder()), factory.empty(Comparators.reverseNaturalOrder()));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.empty(Comparators.reverseNaturalOrder()));
        Assert.assertSame(SortedSets.immutable.empty(), SortedSets.immutable.of(new Integer[0]));
        Assert.assertSame(SortedSets.immutable.empty(), SortedSets.immutable.of((Object[]) null));
        Assert.assertEquals(UnifiedSet.newSetWith(), factory.of(Comparators.reverseNaturalOrder()));
        Assert.assertEquals(TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder()).comparator(), factory.of(Comparators.reverseNaturalOrder()).comparator());
        Assert.assertEquals(TreeSortedSet.newSetWith().comparator(), factory.of((Comparator<Integer>) null).comparator());
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), factory.of(1, 2, 2));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), factory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6), factory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), factory.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(
                SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8),
                factory.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8).comparator(), factory.ofAll(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)).comparator());
        Assert.assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(Comparators.reverseNaturalOrder(), SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8).comparator(), factory.ofAll(Comparators.reverseNaturalOrder(), SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)).comparator());
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(SortedSets.immutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(SortedSets.immutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.immutable.of(1, 2, 3, 4, 5, 6, 7, 8).castToSortedSet()));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.immutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8).castToSortedSet()));
        Assert.assertEquals(SortedSets.mutable.empty(), factory.ofSortedSet(SortedSets.mutable.with()));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.immutable.empty(), SortedSets.immutable.ofAll(null, Lists.mutable.empty()));
    }

    @Test
    public void mutables()
    {
        MutableSortedSetFactory factory = SortedSets.mutable;
        Assert.assertEquals(TreeSortedSet.newSet(), factory.of());
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of());
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2), factory.of(1, 2, 2));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4), factory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2, 3, 4));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6), factory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.ofAll(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(TreeSortedSet.newSet(Comparators.naturalOrder()), factory.of(Comparators.naturalOrder()));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(Comparators.naturalOrder()));
        Assert.assertEquals(TreeSortedSet.newSetWith(8, 7, 6, 5, 4, 3, 2, 1), factory.ofAll(Comparators.reverseNaturalOrder(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.ofAll(Comparators.reverseNaturalOrder(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(SortedSets.class);
    }
}
