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

import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.api.multimap.sortedbag.ImmutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedbag.SortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.SortedSetMultimap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.bag.sorted.mutable.TreeBagMultimap;
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
        ImmutableListMultimap<Integer, Integer> empty = Multimaps.immutable.list.of();
        ImmutableListMultimap<Integer, Integer> emptyWith = Multimaps.immutable.list.with();
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        ImmutableListMultimap<Integer, Integer> one = Multimaps.immutable.list.of(1, 1);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableListMultimap<Integer, Integer> two = Multimaps.immutable.list.of(1, 1, 2, 2);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableListMultimap<Integer, Integer> three = Multimaps.immutable.list.of(1, 1, 2, 2, 3, 3);
        ListMultimap<Integer, Integer> expectedThree = FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
    }

    @Test
    public void immutableSet()
    {
        ImmutableSetMultimap<Integer, Integer> empty = Multimaps.immutable.set.of();
        ImmutableSetMultimap<Integer, Integer> emptyWith = Multimaps.immutable.set.with();
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        ImmutableSetMultimap<Integer, Integer> one = Multimaps.immutable.set.of(1, 1);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableSetMultimap<Integer, Integer> two = Multimaps.immutable.set.of(1, 1, 2, 2);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableSetMultimap<Integer, Integer> three = Multimaps.immutable.set.of(1, 1, 2, 2, 3, 3);
        SetMultimap<Integer, Integer> expectedThree = UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
    }

    @Test
    public void immutableSortedSet()
    {
        ImmutableSortedSetMultimap<Integer, Integer> empty = Multimaps.immutable.sortedSet.of(Integer::compareTo);
        ImmutableSortedSetMultimap<Integer, Integer> emptyWith = Multimaps.immutable.sortedSet.with(Integer::compareTo);
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        ImmutableSortedSetMultimap<Integer, Integer> one = Multimaps.immutable.sortedSet.of(Integer::compareTo, 1, 1);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableSortedSetMultimap<Integer, Integer> two = Multimaps.immutable.sortedSet.of(Integer::compareTo, 1, 1, 2, 2);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableSortedSetMultimap<String, String> toStringOffTwo = Multimaps.immutable.sortedSet.of(String::compareTo, "A", "B");
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair("A", "B")), toStringOffTwo);
        ImmutableSortedSetMultimap<Integer, Integer> three = Multimaps.immutable.sortedSet.of(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        SortedSetMultimap<Integer, Integer> expectedThree = TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
    }

    @Test
    public void immutableSortedBagMultimap()
    {
        ImmutableSortedBagMultimap<Integer, Integer> emptyWith = Multimaps.immutable.sortedBag.with(Integer::compareTo);
        ImmutableSortedBagMultimap<Integer, Integer> emptyOf = Multimaps.immutable.sortedBag.of(Integer::compareTo);
        Verify.assertEmpty(emptyWith);
        Assert.assertNotNull(emptyWith.comparator());
        Verify.assertEmpty(emptyOf);
        Assert.assertNotNull(emptyOf.comparator());
        ImmutableSortedBagMultimap<Integer, Integer> oneOf = Multimaps.immutable.sortedBag.of(Integer::compareTo, 1, 1);
        ImmutableSortedBagMultimap<Integer, Integer> oneWith = Multimaps.immutable.sortedBag.with(Integer::compareTo, 1, 1);
        TreeBagMultimap<Integer, Integer> expectedOne = TreeBagMultimap.newMultimap(Tuples.pair(1, 1));
        Assert.assertEquals(expectedOne, oneOf);
        Assert.assertNotNull(oneOf.comparator());
        Assert.assertEquals(expectedOne, oneWith);
        Assert.assertNotNull(oneWith.comparator());
        ImmutableSortedBagMultimap<String, String> toStringOfTwo = Multimaps.immutable.sortedBag.of(String::compareTo, "A", "B");
        ImmutableSortedBagMultimap<String, String> toStringWithTwo = Multimaps.immutable.sortedBag.with(String::compareTo, "A", "B");
        TreeBagMultimap<String, String> expectedStringOfTwo = TreeBagMultimap.newMultimap(Tuples.pair("A", "B"));
        Assert.assertEquals(expectedStringOfTwo, toStringOfTwo);
        Assert.assertNotNull(toStringOfTwo.comparator());
        Assert.assertEquals(expectedStringOfTwo, toStringWithTwo);
        Assert.assertNotNull(toStringWithTwo.comparator());
        TreeBagMultimap<Integer, Integer> expectedTwo = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2));
        ImmutableSortedBagMultimap<Integer, Integer> twoOf = Multimaps.immutable.sortedBag.of(Integer::compareTo, 1, 1, 2, 2);
        ImmutableSortedBagMultimap<Integer, Integer> twoWith = Multimaps.immutable.sortedBag.with(Integer::compareTo, 1, 1, 2, 2);
        Assert.assertEquals(expectedTwo, twoOf);
        Assert.assertNotNull(twoOf);
        Assert.assertEquals(expectedTwo, twoWith);
        Assert.assertNotNull(twoWith.comparator());
        ImmutableSortedBagMultimap<Integer, Integer> threeOf = Multimaps.immutable.sortedBag.of(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        ImmutableSortedBagMultimap<Integer, Integer> threeWith = Multimaps.immutable.sortedBag.with(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        SortedBagMultimap<Integer, Integer> expectedThree = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, threeOf);
        Assert.assertNotNull(threeOf.comparator());
        Assert.assertEquals(expectedThree, threeWith);
        Assert.assertNotNull(threeWith.comparator());
    }

    @Test
    public void immutableSortedBagMultimapWithoutComparator()
    {
        ImmutableSortedBagMultimap<Integer, Integer> emptyWith = Multimaps.immutable.sortedBag.with();
        ImmutableSortedBagMultimap<Integer, Integer> emptyOf = Multimaps.immutable.sortedBag.of();
        Verify.assertEmpty(emptyWith);
        Assert.assertNull(emptyWith.comparator());
        Verify.assertEmpty(emptyOf);
        Assert.assertNull(emptyOf.comparator());
        ImmutableSortedBagMultimap<Integer, Integer> oneOf = Multimaps.immutable.sortedBag.of(1, 1);
        ImmutableSortedBagMultimap<Integer, Integer> oneWith = Multimaps.immutable.sortedBag.with(1, 1);
        TreeBagMultimap<Integer, Integer> expectedOne = TreeBagMultimap.newMultimap(Tuples.pair(1, 1));
        Assert.assertEquals(expectedOne, oneOf);
        Assert.assertNull(oneOf.comparator());
        Assert.assertEquals(expectedOne, oneWith);
        Assert.assertNull(oneWith.comparator());
        ImmutableSortedBagMultimap<String, String> toStringOfTwo = Multimaps.immutable.sortedBag.of("A", "B");
        ImmutableSortedBagMultimap<String, String> toStringWithTwo = Multimaps.immutable.sortedBag.with("A", "B");
        TreeBagMultimap<String, String> expectedStringOfTwo = TreeBagMultimap.newMultimap(Tuples.pair("A", "B"));
        Assert.assertEquals(expectedStringOfTwo, toStringOfTwo);
        Assert.assertNull(toStringOfTwo.comparator());
        Assert.assertEquals(expectedStringOfTwo, toStringWithTwo);
        Assert.assertNull(toStringWithTwo.comparator());
        TreeBagMultimap<Integer, Integer> expectedTwo = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2));
        ImmutableSortedBagMultimap<Integer, Integer> twoOf = Multimaps.immutable.sortedBag.of(1, 1, 2, 2);
        ImmutableSortedBagMultimap<Integer, Integer> twoWith = Multimaps.immutable.sortedBag.with(1, 1, 2, 2);
        Assert.assertEquals(expectedTwo, twoOf);
        Assert.assertNotNull(twoOf);
        Assert.assertEquals(expectedTwo, twoWith);
        Assert.assertNull(twoWith.comparator());
        ImmutableSortedBagMultimap<Integer, Integer> threeOf = Multimaps.immutable.sortedBag.of(1, 1, 2, 2, 3, 3);
        ImmutableSortedBagMultimap<Integer, Integer> threeWith = Multimaps.immutable.sortedBag.with(1, 1, 2, 2, 3, 3);
        SortedBagMultimap<Integer, Integer> expectedThree = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, threeOf);
        Assert.assertNull(threeOf.comparator());
        Assert.assertEquals(expectedThree, threeWith);
        Assert.assertNull(threeWith.comparator());
    }

    @Test
    public void immutableBag()
    {
        ImmutableBagMultimap<Integer, Integer> empty = Multimaps.immutable.bag.of();
        ImmutableBagMultimap<Integer, Integer> emptyWith = Multimaps.immutable.bag.with();
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        ImmutableBagMultimap<Integer, Integer> one = Multimaps.immutable.bag.of(1, 1);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1)), one);
        ImmutableBagMultimap<Integer, Integer> two = Multimaps.immutable.bag.of(1, 1, 2, 2);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        ImmutableBagMultimap<Integer, Integer> three = Multimaps.immutable.bag.of(1, 1, 2, 2, 3, 3);
        BagMultimap<Integer, Integer> expectedThree = HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
    }

    @Test
    public void mutableList()
    {
        MutableListMultimap<Integer, Integer> empty = Multimaps.mutable.list.of();
        MutableListMultimap<Integer, Integer> emptyWith = Multimaps.mutable.list.with();
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        MutableListMultimap<Integer, Integer> one = Multimaps.mutable.list.of(1, 1);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableListMultimap<Integer, Integer> two = Multimaps.mutable.list.of(1, 1, 2, 2);
        Assert.assertEquals(FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableListMultimap<Integer, Integer> three = Multimaps.mutable.list.of(1, 1, 2, 2, 3, 3);
        ListMultimap<Integer, Integer> expectedThree = FastListMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
        Assert.assertEquals(expectedThree, Multimaps.mutable.list.withAll(three));
    }

    @Test
    public void mutableSet()
    {
        MutableSetMultimap<Integer, Integer> empty = Multimaps.mutable.set.of();
        MutableSetMultimap<Integer, Integer> emptyWith = Multimaps.mutable.set.with();
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        MutableSetMultimap<Integer, Integer> one = Multimaps.mutable.set.of(1, 1);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableSetMultimap<Integer, Integer> two = Multimaps.mutable.set.of(1, 1, 2, 2);
        Assert.assertEquals(UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableSetMultimap<Integer, Integer> three = Multimaps.mutable.set.of(1, 1, 2, 2, 3, 3);
        SetMultimap<Integer, Integer> expectedThree = UnifiedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
        Assert.assertEquals(expectedThree, Multimaps.mutable.set.withAll(three));
    }

    @Test
    public void mutableSortedSet()
    {
        MutableSortedSetMultimap<Integer, Integer> empty = Multimaps.mutable.sortedSet.of(Integer::compareTo);
        MutableSortedSetMultimap<Integer, Integer> emptyWith = Multimaps.mutable.sortedSet.with(Integer::compareTo);
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        MutableSortedSetMultimap<Integer, Integer> one = Multimaps.mutable.sortedSet.of(Integer::compareTo, 1, 1);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableSortedSetMultimap<Integer, Integer> two = Multimaps.mutable.sortedSet.of(Integer::compareTo, 1, 1, 2, 2);
        Assert.assertEquals(TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableSortedSetMultimap<Integer, Integer> three = Multimaps.mutable.sortedSet.of(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        SortedSetMultimap<Integer, Integer> expectedThree = TreeSortedSetMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
        Assert.assertEquals(expectedThree, Multimaps.mutable.sortedSet.withAll(three));
    }

    @Test
    public void mutableBag()
    {
        MutableBagMultimap<Integer, Integer> empty = Multimaps.mutable.bag.of();
        MutableBagMultimap<Integer, Integer> emptyWith = Multimaps.mutable.bag.with();
        Verify.assertEmpty(empty);
        Verify.assertEmpty(emptyWith);
        MutableBagMultimap<Integer, Integer> one = Multimaps.mutable.bag.of(1, 1);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1)), one);
        MutableBagMultimap<Integer, Integer> two = Multimaps.mutable.bag.of(1, 1, 2, 2);
        Assert.assertEquals(HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2)), two);
        MutableBagMultimap<Integer, Integer> three = Multimaps.mutable.bag.of(1, 1, 2, 2, 3, 3);
        BagMultimap<Integer, Integer> expectedThree = HashBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, three);
        Assert.assertEquals(expectedThree, Multimaps.mutable.bag.withAll(three));
    }

    @Test
    public void mutableSortedBagMultimap()
    {
        MutableSortedBagMultimap<Object, Object> empty = Multimaps.mutable.sortedBag.empty();
        MutableSortedBagMultimap<Object, Object> emptyWith = Multimaps.mutable.sortedBag.with();
        MutableSortedBagMultimap<Object, Object> emptyOf = Multimaps.mutable.sortedBag.of();
        Verify.assertEmpty(empty);
        Assert.assertNull(empty.comparator());
        Verify.assertEmpty(emptyWith);
        Assert.assertNull(emptyWith.comparator());
        Verify.assertEmpty(emptyOf);
        Assert.assertNull(emptyOf.comparator());
        TreeBagMultimap<Integer, Integer> expectedOne = TreeBagMultimap.newMultimap(Tuples.pair(1, 1));
        MutableSortedBagMultimap<Integer, Integer> withOne = Multimaps.mutable.sortedBag.with(1, 1);
        Assert.assertEquals(expectedOne, withOne);
        Assert.assertNull(withOne.comparator());
        MutableSortedBagMultimap<Integer, Integer> ofOne = Multimaps.mutable.sortedBag.of(1, 1);
        Assert.assertEquals(expectedOne, ofOne);
        Assert.assertNull(ofOne.comparator());
        TreeBagMultimap<String, String> expectedOneStrings = TreeBagMultimap.newMultimap(Tuples.pair("A", "B"));
        MutableSortedBagMultimap<String, String> withStrings = Multimaps.mutable.sortedBag.with("A", "B");
        Assert.assertEquals(expectedOneStrings, withStrings);
        Assert.assertNull(withStrings.comparator());
        MutableSortedBagMultimap<String, String> ofStrings = Multimaps.mutable.sortedBag.of("A", "B");
        Assert.assertEquals(expectedOneStrings, ofStrings);
        Assert.assertNull(ofStrings.comparator());
        TreeBagMultimap<Integer, Integer> expectedTwo = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2));
        MutableSortedBagMultimap<Integer, Integer> withTwoItems = Multimaps.mutable.sortedBag.with(1, 1, 2, 2);
        Assert.assertEquals(expectedTwo, withTwoItems);
        Assert.assertNull(withTwoItems.comparator());
        MutableSortedBagMultimap<Integer, Integer> ofTwoItems = Multimaps.mutable.sortedBag.of(1, 1, 2, 2);
        Assert.assertEquals(expectedTwo, ofTwoItems);
        Assert.assertNull(ofTwoItems.comparator());
        MutableSortedBagMultimap<Integer, Integer> threeWith = Multimaps.mutable.sortedBag.with(1, 1, 2, 2, 3, 3);
        Assert.assertNull(threeWith.comparator());
        MutableSortedBagMultimap<Integer, Integer> threeOf = Multimaps.mutable.sortedBag.of(1, 1, 2, 2, 3, 3);
        Assert.assertNull(threeOf.comparator());
        SortedBagMultimap<Integer, Integer> expectedThree = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, threeWith);
        Assert.assertEquals(expectedThree, threeOf);
        Assert.assertEquals(expectedThree, Multimaps.mutable.sortedBag.withAll(threeWith));
    }

    @Test
    public void mutableSortedBagMultimapWithComparator()
    {
        MutableSortedBagMultimap<String, String> empty = Multimaps.mutable.sortedBag.empty(String::compareTo);
        MutableSortedBagMultimap<String, String> emptyWith = Multimaps.mutable.sortedBag.with(String::compareTo);
        MutableSortedBagMultimap<String, String> emptyOf = Multimaps.mutable.sortedBag.of(String::compareTo);
        Verify.assertEmpty(empty);
        Assert.assertNotNull(empty.comparator());
        Verify.assertEmpty(emptyWith);
        Assert.assertNotNull(emptyWith.comparator());
        Verify.assertEmpty(emptyOf);
        Assert.assertNotNull(emptyOf.comparator());
        TreeBagMultimap<Integer, Integer> expectedOne = TreeBagMultimap.newMultimap(Tuples.pair(1, 1));
        MutableSortedBagMultimap<Integer, Integer> withOne = Multimaps.mutable.sortedBag.with(Integer::compareTo, 1, 1);
        Assert.assertEquals(expectedOne, withOne);
        Assert.assertNotNull(withOne.comparator());
        MutableSortedBagMultimap<Integer, Integer> ofOne = Multimaps.mutable.sortedBag.of(Integer::compareTo, 1, 1);
        Assert.assertEquals(expectedOne, ofOne);
        Assert.assertNotNull(ofOne.comparator());
        TreeBagMultimap<String, String> expectedOneStrings = TreeBagMultimap.newMultimap(Tuples.pair("A", "B"));
        MutableSortedBagMultimap<String, String> withStrings = Multimaps.mutable.sortedBag.with(String::compareTo, "A", "B");
        Assert.assertEquals(expectedOneStrings, withStrings);
        Assert.assertNotNull(withStrings.comparator());
        MutableSortedBagMultimap<String, String> ofStrings = Multimaps.mutable.sortedBag.of(String::compareTo, "A", "B");
        Assert.assertEquals(expectedOneStrings, ofStrings);
        Assert.assertNotNull(ofStrings.comparator());
        TreeBagMultimap<Integer, Integer> expectedTwo = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2));
        MutableSortedBagMultimap<Integer, Integer> withTwoItems = Multimaps.mutable.sortedBag.with(Integer::compareTo, 1, 1, 2, 2);
        Assert.assertEquals(expectedTwo, withTwoItems);
        Assert.assertNotNull(withTwoItems.comparator());
        MutableSortedBagMultimap<Integer, Integer> ofTwoItems = Multimaps.mutable.sortedBag.of(Integer::compareTo, 1, 1, 2, 2);
        Assert.assertEquals(expectedTwo, ofTwoItems);
        Assert.assertNotNull(ofTwoItems.comparator());
        MutableSortedBagMultimap<Integer, Integer> threeWith = Multimaps.mutable.sortedBag.with(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        Assert.assertNotNull(threeWith.comparator());
        MutableSortedBagMultimap<Integer, Integer> threeOf = Multimaps.mutable.sortedBag.of(Integer::compareTo, 1, 1, 2, 2, 3, 3);
        Assert.assertNotNull(threeOf.comparator());
        SortedBagMultimap<Integer, Integer> expectedThree = TreeBagMultimap.newMultimap(Tuples.pair(1, 1), Tuples.pair(2, 2), Tuples.pair(3, 3));
        Assert.assertEquals(expectedThree, threeWith);
        Assert.assertEquals(expectedThree, threeOf);
        Assert.assertEquals(expectedThree, Multimaps.mutable.sortedBag.withAll(threeWith));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Multimaps.class);
    }
}
