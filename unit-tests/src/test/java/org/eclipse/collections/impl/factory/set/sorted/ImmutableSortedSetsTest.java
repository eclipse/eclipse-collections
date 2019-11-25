/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.set.sorted;

import java.util.Comparator;
import java.util.SortedSet;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableSortedSetsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(UnifiedSet.newSet(), ImmutableSortedSet.of());
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.of());
        Assert.assertEquals(TreeSortedSet.newSet(Comparators.reverseNaturalOrder()), ImmutableSortedSet.empty(Comparators.reverseNaturalOrder()));
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.empty(Comparators.reverseNaturalOrder()));
        Assert.assertSame(ImmutableSortedSet.empty(), ImmutableSortedSet.of(new Integer[0]));
        Assert.assertSame(ImmutableSortedSet.empty(), ImmutableSortedSet.of((Object[]) null));
        Assert.assertEquals(UnifiedSet.newSetWith(), ImmutableSortedSet.of(Comparators.reverseNaturalOrder()));
        Assert.assertEquals(TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder()).comparator(), ImmutableSortedSet.of(Comparators.reverseNaturalOrder()).comparator());
        Assert.assertEquals(TreeSortedSet.newSetWith().comparator(), ImmutableSortedSet.of((Comparator<Integer>) null).comparator());
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), ImmutableSortedSet.of(1, 2, 2));
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), ImmutableSortedSet.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6), ImmutableSortedSet.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(
                SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8),
                ImmutableSortedSet.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, ImmutableSortedSet.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofAll(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8).comparator(), ImmutableSortedSet.ofAll(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)).comparator());
        Assert.assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofAll(Comparators.reverseNaturalOrder(), SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8).comparator(), ImmutableSortedSet.ofAll(Comparators.reverseNaturalOrder(), SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)).comparator());
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofAll(ImmutableSortedSet.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofAll(ImmutableSortedSet.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofSortedSet(ImmutableSortedSet.of(1, 2, 3, 4, 5, 6, 7, 8).castToSortedSet()));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofSortedSet(ImmutableSortedSet.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8).castToSortedSet()));
        Assert.assertEquals(SortedSets.mutable.empty(), ImmutableSortedSet.ofSortedSet(SortedSets.mutable.with()));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofSortedSet(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), ImmutableSortedSet.ofSortedSet(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8)));
        Assert.assertEquals(ImmutableSortedSet.empty(), ImmutableSortedSet.ofAll(null, Lists.mutable.empty()));
    }

    @Test
    public void castToSet()
    {
        SortedSet<Object> set = ImmutableSortedSet.of().castToSortedSet();
        Assert.assertNotNull(set);
        Assert.assertSame(ImmutableSortedSet.of(), set);
    }

    @Test
    public void emptySet()
    {
        Assert.assertTrue(ImmutableSortedSet.of().isEmpty());
        Assert.assertSame(ImmutableSortedSet.of(), ImmutableSortedSet.of());
        Verify.assertPostSerializedIdentity(ImmutableSortedSet.of());
    }
}
