/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag.sorted.immutable;

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.sortedbag.ImmutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.AbstractImmutableMultimapTestCase;
import org.eclipse.collections.impl.multimap.bag.sorted.mutable.TreeBagMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableSortedBagMultimapImplTest extends AbstractImmutableMultimapTestCase
{
    @Override
    protected <K, V> ImmutableMultimap<K, V> classUnderTest()
    {
        return new ImmutableSortedBagMultimapImpl<>(UnifiedMap.newMap());
    }

    @Override
    protected MutableCollection<String> mutableCollection()
    {
        return SortedBags.mutable.empty();
    }

    @Override
    @Test
    public void flip()
    {
        MutableSortedBagMultimap<String, Integer> mutableMap = TreeBagMultimap.newMultimap();
        mutableMap.put("Less than 2", 1);
        mutableMap.put("Less than 3", 1);
        mutableMap.put("Less than 3", 2);
        mutableMap.put("Less than 3", 2);
        ImmutableSortedBagMultimap<String, Integer> multimap = mutableMap.toImmutable();
        ImmutableBagMultimap<Integer, String> flipped = multimap.flip();
        Assert.assertEquals(Bags.immutable.with("Less than 3", "Less than 3"), flipped.get(2));
        Assert.assertEquals(Bags.immutable.with("Less than 2", "Less than 3"), flipped.get(1));
    }

    @Override
    @Test
    public void noDuplicates()
    {
        // Bags allow duplicates
    }

    @Override
    @Test
    public void collectValues()
    {
        MutableSortedBagMultimap<String, Integer> mutableMultimap = TreeBagMultimap.newMultimap(Comparators.reverseNaturalOrder());
        mutableMultimap.putAll("1", FastList.newListWith(4, 3, 2, 1, 1));
        mutableMultimap.putAll("2", FastList.newListWith(5, 4, 3, 2, 2));
        ImmutableSortedBagMultimap<String, Integer> immutableMap = mutableMultimap.toImmutable();
        ImmutableListMultimap<String, String> collectedMultimap = immutableMap.collectValues(value -> value + "Value");
        MutableListMultimap<String, String> expectedMultimap = FastListMultimap.newMultimap();
        expectedMultimap.putAll("1", FastList.newListWith("4Value", "3Value", "2Value", "1Value", "1Value"));
        expectedMultimap.putAll("2", FastList.newListWith("5Value", "4Value", "3Value", "2Value", "2Value"));
        ImmutableListMultimap<String, String> expectedImmutableMultimap = expectedMultimap.toImmutable();
        Verify.assertListMultimapsEqual(expectedImmutableMultimap, collectedMultimap);
    }

    @Override
    @Test
    public void rejectKeysMultiValues()
    {
        MutableSortedBagMultimap<Integer, Integer> multimap = TreeBagMultimap.newMultimap(Comparators.reverseNaturalOrder());
        multimap.putAll(1, FastList.newListWith(4, 3, 2, 1, 1));
        multimap.putAll(2, FastList.newListWith(5, 4, 3, 2, 2));
        multimap.putAll(3, FastList.newListWith(4, 3, 1, 1));
        multimap.putAll(4, FastList.newListWith(4, 3, 1));
        ImmutableSortedBagMultimap<Integer, Integer> immutableMultimap = multimap.toImmutable();
        ImmutableSortedBagMultimap<Integer, Integer> selectedMultimap = immutableMultimap.rejectKeysMultiValues((key, values) -> key % 2 == 0 || Iterate.sizeOf(values) > 4);
        MutableSortedBagMultimap<Integer, Integer> expectedMultimap = TreeBagMultimap.newMultimap(Comparators.reverseNaturalOrder());
        expectedMultimap.putAll(3, FastList.newListWith(4, 3, 1, 1));
        Verify.assertSortedBagMultimapsEqual(expectedMultimap, selectedMultimap);
        Assert.assertEquals(expectedMultimap.toImmutable().comparator(), selectedMultimap.comparator());
    }

    @Test
    public void constructors()
    {
        ImmutableSortedBagMultimap<Integer, Integer> map = new ImmutableSortedBagMultimapImpl<>(Maps.immutable.empty());
        ImmutableSortedBagMultimap<Integer, Integer> map2 = new ImmutableSortedBagMultimapImpl<>(Maps.immutable.empty(), Comparators.reverseNaturalOrder());
        Assert.assertEquals(this.classUnderTest(), map);
        Assert.assertEquals(TreeBagMultimap.newMultimap(Comparators.reverseNaturalOrder()), map2);
    }

    @Test
    public void empty()
    {
        ImmutableSortedBagMultimap<Object, Object> multimap = new ImmutableSortedBagMultimapImpl<>(Maps.mutable.empty()).newEmpty();
        Verify.assertEmpty(multimap);
        Verify.assertInstanceOf(ImmutableSortedBagMultimap.class, multimap);
    }
}
