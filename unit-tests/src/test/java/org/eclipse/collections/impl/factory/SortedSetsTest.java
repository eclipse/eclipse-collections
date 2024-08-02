/*
 * Copyright (c) 2021 Goldman Sachs.
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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SortedSetsTest
{
    @Test
    public void immutables()
    {
        ImmutableSortedSetFactory factory = SortedSets.immutable;
        assertEquals(UnifiedSet.newSet(), factory.of());
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of());
        assertEquals(UnifiedSet.newSet(), factory.with());
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.with());
        assertEquals(TreeSortedSet.newSet(Comparators.reverseNaturalOrder()), factory.empty(Comparators.reverseNaturalOrder()));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.empty(Comparators.reverseNaturalOrder()));
        assertSame(SortedSets.immutable.empty(), SortedSets.immutable.of(new Integer[0]));
        assertSame(SortedSets.immutable.empty(), SortedSets.immutable.of((Object[]) null));
        assertEquals(UnifiedSet.newSetWith(), factory.of(Comparators.reverseNaturalOrder()));
        assertEquals(TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder()).comparator(), factory.of(Comparators.reverseNaturalOrder()).comparator());
        assertEquals(TreeSortedSet.newSetWith().comparator(), factory.of((Comparator<Integer>) null).comparator());
        assertEquals(UnifiedSet.newSetWith(1, 2), factory.of(1, 2, 2));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), factory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2, 3, 4));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6), factory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6));
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), factory.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(
                SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8),
                factory.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableSortedSet.class, factory.of(Comparators.reverseNaturalOrder(), 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8));
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8).comparator(), factory.ofAll(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)).comparator());
        assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(Comparators.reverseNaturalOrder(), SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8).comparator(), factory.ofAll(Comparators.reverseNaturalOrder(), SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)).comparator());
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(SortedSets.immutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(SortedSets.immutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.immutable.of(1, 2, 3, 4, 5, 6, 7, 8).castToSortedSet()));
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.immutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8).castToSortedSet()));
        assertEquals(SortedSets.mutable.empty(), factory.ofSortedSet(SortedSets.mutable.with()));
        assertEquals(SortedSets.mutable.empty(Integer::compare), factory.ofSortedSet(SortedSets.mutable.with(Integer::compare)));
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(SortedSets.mutable.of(1, 2, 3, 4, 5, 6, 7, 8), factory.ofSortedSet(SortedSets.mutable.of(Comparators.reverseNaturalOrder(), 1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(SortedSets.immutable.empty(), SortedSets.immutable.ofAll(null, Lists.mutable.empty()));
    }

    @Test
    public void mutables()
    {
        MutableSortedSetFactory factory = SortedSets.mutable;
        assertEquals(TreeSortedSet.newSet(), factory.of());
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of());
        assertEquals(TreeSortedSet.newSetWith(1, 2), factory.of(1, 2, 2));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2));
        assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4), factory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2, 3, 4));
        assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6), factory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6));
        assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(TreeSortedSet.newSetWith(1, 2, 3, 4, 5, 6, 7, 8), factory.ofAll(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.ofAll(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        assertEquals(TreeSortedSet.newSet(Comparators.naturalOrder()), factory.of(Comparators.naturalOrder()));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.of(Comparators.naturalOrder()));
        assertEquals(TreeSortedSet.newSetWith(8, 7, 6, 5, 4, 3, 2, 1), factory.ofAll(Comparators.reverseNaturalOrder(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
        Verify.assertInstanceOf(MutableSortedSet.class, factory.ofAll(Comparators.reverseNaturalOrder(), FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(SortedSets.class);
    }
}
