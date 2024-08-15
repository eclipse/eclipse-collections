/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable.sorted;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.test.MutableOrderedIterableTestCase;
import org.junit.jupiter.api.Test;

import static org.eclipse.collections.test.IterableTestCase.assertIterablesEqual;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface MutableSortedBagTestCase extends SortedBagTestCase, MutableOrderedIterableTestCase, MutableBagIterableTestCase
{
    @Override
    <T> MutableSortedBag<T> newWith(T... elements);

    @Override
    @Test
    default void MutableBagIterable_addOccurrences()
    {
        MutableSortedBag<Integer> mutableSortedBag = this.newWith(3, 3, 3, 2, 2, 1);
        assertIterablesEqual(4, mutableSortedBag.addOccurrences(4, 4));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1), mutableSortedBag);
        assertIterablesEqual(3, mutableSortedBag.addOccurrences(1, 2));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1, 1, 1), mutableSortedBag);
        assertIterablesEqual(3, mutableSortedBag.addOccurrences(1, 0));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1, 1, 1), mutableSortedBag);

        assertThrows(IllegalArgumentException.class, () -> mutableSortedBag.addOccurrences(4, -1));
    }

    @Override
    @Test
    default void MutableBagIterable_removeOccurrences()
    {
        MutableSortedBag<Integer> mutableBag = this.newWith(3, 3, 3, 2, 2, 1);
        assertFalse(mutableBag.removeOccurrences(4, 4));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 3, 3, 2, 2, 1), mutableBag);
        assertFalse(mutableBag.removeOccurrences(3, 0));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 3, 3, 2, 2, 1), mutableBag);
        assertTrue(mutableBag.removeOccurrences(1, 2));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 3, 3, 2, 2), mutableBag);
        assertTrue(mutableBag.removeOccurrences(3, 2));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 2, 2), mutableBag);
        assertTrue(mutableBag.removeOccurrences(2, 1));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 2), mutableBag);
        assertTrue(mutableBag.removeOccurrences(2, 2));
        assertIterablesEqual(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3), mutableBag);

        assertThrows(IllegalArgumentException.class, () -> mutableBag.removeOccurrences(4, -1));
    }
}
