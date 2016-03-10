/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.list.mutable;

import java.util.Collections;
import java.util.Random;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.test.MutableOrderedIterableTestCase;
import org.eclipse.collections.test.collection.mutable.MutableCollectionTestCase;
import org.eclipse.collections.test.list.ListIterableTestCase;
import org.eclipse.collections.test.list.ListTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertSame;

public interface MutableListTestCase extends MutableCollectionTestCase, ListTestCase, ListIterableTestCase, MutableOrderedIterableTestCase
{
    @Override
    <T> MutableList<T> newWith(T... elements);

    @Test
    default void MutableList_sortThis()
    {
        MutableList<Integer> mutableList = this.newWith(5, 1, 4, 2, 3);
        MutableList<Integer> sortedList = mutableList.sortThis();
        assertSame(mutableList, sortedList);
        assertEquals(Lists.immutable.with(1, 2, 3, 4, 5), sortedList);
    }

    @Test
    default void MutableList_shuffleThis()
    {
        Integer[] integers = Interval.oneTo(50).toArray();
        MutableList<Integer> mutableList1 = this.newWith(integers);
        MutableList<Integer> mutableList2 = this.newWith(integers);
        Collections.shuffle(mutableList1, new Random(10));
        assertEquals(mutableList1, mutableList2.shuffleThis(new Random(10)));

        MutableList<Integer> list = this.newWith(1, 2, 3);
        UnifiedSet<ImmutableList<Integer>> objects = UnifiedSet.newSet();
        while (objects.size() < 6)
        {
            objects.add(list.shuffleThis().toImmutable());
        }

        Interval interval = Interval.oneTo(1000);
        MutableList<Integer> bigList = this.newWith(interval.toArray());
        MutableList<Integer> shuffledBigList = bigList.shuffleThis(new Random(8));
        MutableList<Integer> integers1 = this.newWith(interval.toArray());
        assertEquals(integers1.shuffleThis(new Random(8)), bigList);
        assertSame(bigList, shuffledBigList);
        assertSame(bigList, bigList.shuffleThis());
        assertSame(bigList, bigList.shuffleThis(new Random(8)));
        assertEquals(interval.toBag(), bigList.toBag());
    }

    @Test
    default void MutableList_sortThis_comparator()
    {
        MutableList<Integer> mutableList = this.newWith(5, 1, 4, 2, 3);
        MutableList<Integer> sortedList = mutableList.sortThis(Comparators.reverseNaturalOrder());
        assertSame(mutableList, sortedList);
        assertEquals(Lists.immutable.with(5, 4, 3, 2, 1), sortedList);
    }

    @Test
    default void MutableList_subList_subList_remove()
    {
        MutableList<String> list = this.newWith("A", "B", "C", "D");
        MutableList<String> sublist = list.subList(0, 3);
        MutableList<String> sublist2 = sublist.subList(0, 2);

        assertEquals(Lists.immutable.with("A", "B", "C"), sublist);
        assertEquals(Lists.immutable.with("A", "B"), sublist2);

        sublist2.add("X");

        assertEquals(Lists.immutable.with("A", "B", "X", "C"), sublist);
        assertEquals(Lists.immutable.with("A", "B", "X"), sublist2);

        assertEquals("B", sublist2.remove(1));
        assertEquals(Lists.immutable.with("A", "X", "C"), sublist);
        assertEquals(Lists.immutable.with("A", "X"), sublist2);
    }
}
