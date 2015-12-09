/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.test.bag.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.test.MutableUnorderedIterableTestCase;
import org.eclipse.collections.test.bag.UnsortedBagTestCase;
import org.eclipse.collections.test.bag.mutable.sorted.MutableBagIterableTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public interface MutableBagTestCase extends UnsortedBagTestCase, MutableUnorderedIterableTestCase, MutableBagIterableTestCase
{
    @Override
    <T> MutableBag<T> newWith(T... elements);

    @Test
    default void MutableBag_addOccurrences()
    {
        MutableBag<Integer> mutableBag = this.newWith(1, 2, 2, 3, 3, 3);
        mutableBag.addOccurrences(4, 4);
        assertEquals(Bags.immutable.with(1, 2, 2, 3, 3, 3, 4, 4, 4, 4), mutableBag);
        mutableBag.addOccurrences(1, 2);
        assertEquals(Bags.immutable.with(1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4), mutableBag);
    }

    @Test
    default void MutableBag_removeOccurrences()
    {
        MutableBag<Integer> mutableBag = this.newWith(1, 2, 2, 3, 3, 3);
        assertFalse(mutableBag.removeOccurrences(4, 4));
        assertEquals(Bags.immutable.with(1, 2, 2, 3, 3, 3), mutableBag);
        assertTrue(mutableBag.removeOccurrences(1, 2));
        assertEquals(Bags.immutable.with(2, 2, 3, 3, 3), mutableBag);
        assertTrue(mutableBag.removeOccurrences(3, 2));
        assertEquals(Bags.immutable.with(2, 2, 3), mutableBag);
        assertTrue(mutableBag.removeOccurrences(2, 1));
        assertEquals(Bags.immutable.with(2, 3), mutableBag);
    }
}
