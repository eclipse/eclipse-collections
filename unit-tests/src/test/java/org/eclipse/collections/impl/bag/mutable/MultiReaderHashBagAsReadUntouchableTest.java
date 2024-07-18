/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.collection.mutable.UnmodifiableMutableCollectionTestCase;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultiReaderHashBagAsReadUntouchableTest extends UnmodifiableMutableCollectionTestCase<Integer>
{
    @Override
    protected MutableBag<Integer> getCollection()
    {
        return MultiReaderHashBag.newBagWith(1, 1).asReadUntouchable();
    }

    @Test
    public void addOccurrences()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.getCollection().addOccurrences(1, 1));
    }

    @Test
    public void removeOccurrences()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.getCollection().removeOccurrences(1, 1));
    }

    @Test
    public void setOccurrences()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.getCollection().setOccurrences(1, 1));
    }

    @Test
    public void occurrencesOf()
    {
        assertEquals(2, this.getCollection().occurrencesOf(1));
        assertEquals(0, this.getCollection().occurrencesOf(0));
    }

    @Test
    public void sizeDistinct()
    {
        assertEquals(1, this.getCollection().sizeDistinct());
    }

    @Test
    public void toMapOfItemToCount()
    {
        assertEquals(UnifiedMap.newWithKeysValues(1, 2), this.getCollection().toMapOfItemToCount());
    }

    @Test
    public void toStringOfItemToCount()
    {
        assertEquals("{1=2}", this.getCollection().toStringOfItemToCount());
    }

    @Test
    public void forEachWithOccurrences()
    {
        int[] sum = new int[1];
        this.getCollection().forEachWithOccurrences((each, occurrences) -> {
            if (occurrences > 1)
            {
                sum[0] += each * occurrences;
            }
        });

        assertEquals(2, sum[0]);
    }

    @Test
    public void selectUnique()
    {
        MutableBag<String> bag = MultiReaderHashBag.newBagWith("0", "1", "1", "1", "1", "2", "2", "2", "3", "3", "4", "5").asReadUntouchable();
        MutableSet<String> expected = Sets.mutable.with("0", "4", "5");
        MutableSet<String> actual = bag.selectUnique();
        assertEquals(expected, actual);
    }

    @Test
    public void distinctView()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.getCollection().distinctView());
    }
}
