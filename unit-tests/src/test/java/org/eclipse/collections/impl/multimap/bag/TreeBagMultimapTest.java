/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag;

import java.util.Collections;
import java.util.Comparator;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test of {@link TreeBagMultimap}.
 *
 * @deprecated in 6.0
 */
@Deprecated
public class TreeBagMultimapTest extends org.eclipse.collections.impl.multimap.bag.sorted.TreeBagMultimapTest
{
    @Override
    protected <K, V> MutableSortedBagMultimap<K, V> newMultimap(Comparator<V> comparator)
    {
        return TreeBagMultimap.newMultimap(comparator);
    }

    @Override
    public <K, V> MutableSortedBagMultimap<K, V> newMultimap()
    {
        return TreeBagMultimap.newMultimap();
    }

    @SafeVarargs
    @Override
    public final <K, V> TreeBagMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return TreeBagMultimap.newMultimap(pairs);
    }

    @Override
    protected <K, V> MutableSortedBagMultimap<K, V> newMultimapFromPairs(Iterable<Pair<K, V>> inputIterable)
    {
        MutableSortedBagMultimap<K, V> mutableMultimap = this.newMultimap();
        Iterate.forEach(inputIterable, mutableMultimap::add);
        return mutableMultimap;
    }

    @SafeVarargs
    @Override
    protected final <V> TreeBag<V> createCollection(V... args)
    {
        return TreeBag.newBagWith(args);
    }

    @Override
    @Test
    public void testEmptyConstructor()
    {
        MutableSortedBagMultimap<Integer, Integer> map = TreeBagMultimap.newMultimap();
        for (int i = 1; i < 6; ++i)
        {
            for (int j = 1; j < i + 1; ++j)
            {
                map.put(i, j);
            }
        }
        Verify.assertSize(5, map.keysView().toList());
        for (int i = 1; i < 6; ++i)
        {
            Verify.assertSortedBagsEqual(TreeBag.newBag(Interval.oneTo(i)), map.get(i));
        }
    }

    @Override
    @Test
    public void testComparatorConstructors()
    {
        MutableSortedBagMultimap<Boolean, Integer> revMap = TreeBagMultimap.newMultimap(Collections.reverseOrder());
        for (int i = 1; i < 10; ++i)
        {
            revMap.put(IntegerPredicates.isOdd().accept(i), i);
        }
        Verify.assertSize(2, revMap.keysView().toList());
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Collections.reverseOrder(), 9, 7, 5, 3, 1), revMap.get(Boolean.TRUE));
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Collections.reverseOrder(), 8, 6, 4, 2), revMap.get(Boolean.FALSE));
        MutableSortedBagMultimap<Boolean, Integer> revMap2 = TreeBagMultimap.newMultimap(revMap);
        Verify.assertMapsEqual(revMap2.toMap(), revMap.toMap());
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Collections.reverseOrder(), 9, 7, 5, 3, 1), revMap2.get(Boolean.TRUE));
    }

    @Override
    @Test
    public void testCollection()
    {
        TreeBagMultimap<Integer, Integer> bagMultimap = TreeBagMultimap.newMultimap(Collections.reverseOrder());
        MutableSortedBag<Integer> collection = bagMultimap.createCollection();
        collection.addAll(FastList.newListWith(1, 4, 2, 3, 5, 5));
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Collections.reverseOrder(), 5, 5, 4, 3, 2, 1), collection);
        bagMultimap.putAll(1, collection);
        Verify.assertSortedBagsEqual(TreeBag.newBagWith(Collections.reverseOrder(), 5, 5, 4, 3, 2, 1), collection);
        bagMultimap.put(1, 0);
        assertEquals(Integer.valueOf(0), bagMultimap.get(1).getLast());
        bagMultimap.putAll(2, FastList.newListWith(0, 1, 2, 3, 4, 5, 5));
        Verify.assertSortedBagsEqual(bagMultimap.get(1), bagMultimap.get(2));
    }

    @Override
    @Test
    public void testNewEmpty()
    {
        TreeBagMultimap<Object, Integer> expected = TreeBagMultimap.newMultimap(Collections.reverseOrder());
        TreeBagMultimap<Object, Integer> actual = expected.newEmpty();
        expected.putAll(1, FastList.newListWith(4, 3, 1, 2));
        expected.putAll(2, FastList.newListWith(5, 7, 6, 8));
        actual.putAll(1, FastList.newListWith(4, 3, 1, 2));
        actual.putAll(2, FastList.newListWith(5, 7, 6, 8));
        Verify.assertMapsEqual(expected.toMap(), actual.toMap());
        Verify.assertSortedBagsEqual(expected.get(1), actual.get(1));
        Verify.assertSortedBagsEqual(expected.get(2), actual.get(2));
    }
}
