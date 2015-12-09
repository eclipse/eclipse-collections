/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.multimap.set.sorted.TreeSortedSetMultimap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

public class MultimapsTest
{
    @Test
    public void immutableList()
    {
        ImmutableListMultimap<Integer, Integer> empty = Multimaps.immutable.list.with();
        Assert.assertEquals(0, empty.size());
        ImmutableListMultimap<Integer, Integer> one = Multimaps.immutable.list.with(1, 1);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableListMultimap<Integer, Integer> two = Multimaps.immutable.list.with(1, 1, 2, 2);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableListMultimap<Integer, Integer> three = Multimaps.immutable.list.with(1, 1, 2, 2, 3, 3);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void immutableSet()
    {
        ImmutableSetMultimap<Integer, Integer> empty = Multimaps.immutable.set.with();
        Assert.assertEquals(0, empty.size());
        ImmutableSetMultimap<Integer, Integer> one = Multimaps.immutable.set.with(1, 1);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableSetMultimap<Integer, Integer> two = Multimaps.immutable.set.with(1, 1, 2, 2);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableSetMultimap<Integer, Integer> three = Multimaps.immutable.set.with(1, 1, 2, 2, 3, 3);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void immutableSortedSet()
    {
        ImmutableSortedSetMultimap<Integer, Integer> empty = Multimaps.immutable.sortedSet.with(Integer::compareTo);
        Assert.assertEquals(0, empty.size());
        ImmutableSortedSetMultimap<Integer, Integer> one = Multimaps.immutable.sortedSet.with(Integer::compareTo, 1, 1);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableSortedSetMultimap<Integer, Integer> two = Multimaps.immutable.sortedSet.with(Integer::compareTo, 1, 1, 2, 2);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableSortedSetMultimap<Integer, Integer> three = Multimaps.immutable.sortedSet.with(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void immutableBag()
    {
        ImmutableBagMultimap<Integer, Integer> empty = Multimaps.immutable.bag.with();
        Assert.assertEquals(0, empty.size());
        ImmutableBagMultimap<Integer, Integer> one = Multimaps.immutable.bag.with(1, 1);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableBagMultimap<Integer, Integer> two = Multimaps.immutable.bag.with(1, 1, 2, 2);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableBagMultimap<Integer, Integer> three = Multimaps.immutable.bag.with(1, 1, 2, 2, 3, 3);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void mutableList()
    {
        MutableListMultimap<Integer, Integer> empty = Multimaps.mutable.list.with();
        Assert.assertEquals(0, empty.size());
        MutableListMultimap<Integer, Integer> one = Multimaps.mutable.list.with(1, 1);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableListMultimap<Integer, Integer> two = Multimaps.mutable.list.with(1, 1, 2, 2);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableListMultimap<Integer, Integer> three = Multimaps.mutable.list.with(1, 1, 2, 2, 3, 3);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void mutableSet()
    {
        MutableSetMultimap<Integer, Integer> empty = Multimaps.mutable.set.with();
        Assert.assertEquals(0, empty.size());
        MutableSetMultimap<Integer, Integer> one = Multimaps.mutable.set.with(1, 1);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableSetMultimap<Integer, Integer> two = Multimaps.mutable.set.with(1, 1, 2, 2);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableSetMultimap<Integer, Integer> three = Multimaps.mutable.set.with(1, 1, 2, 2, 3, 3);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void mutableSortedSet()
    {
        MutableSortedSetMultimap<Integer, Integer> empty = Multimaps.mutable.sortedSet.with(Integer::compareTo);
        Assert.assertEquals(0, empty.size());
        MutableSortedSetMultimap<Integer, Integer> one = Multimaps.mutable.sortedSet.with(Integer::compareTo, 1, 1);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableSortedSetMultimap<Integer, Integer> two = Multimaps.mutable.sortedSet.with(Integer::compareTo, 1, 1, 2, 2);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableSortedSetMultimap<Integer, Integer> three = Multimaps.mutable.sortedSet.with(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void mutableBag()
    {
        MutableBagMultimap<Integer, Integer> empty = Multimaps.mutable.bag.with();
        Assert.assertEquals(0, empty.size());
        MutableBagMultimap<Integer, Integer> one = Multimaps.mutable.bag.with(1, 1);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableBagMultimap<Integer, Integer> two = Multimaps.mutable.bag.with(1, 1, 2, 2);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableBagMultimap<Integer, Integer> three = Multimaps.mutable.bag.with(1, 1, 2, 2, 3, 3);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3)), three);
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Multimaps.class);
    }
}
