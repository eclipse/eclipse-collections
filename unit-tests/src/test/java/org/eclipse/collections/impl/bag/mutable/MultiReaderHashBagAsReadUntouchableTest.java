/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.collection.mutable.UnmodifiableMutableCollectionTestCase;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.junit.Assert;
import org.junit.Test;

public class MultiReaderHashBagAsReadUntouchableTest extends UnmodifiableMutableCollectionTestCase<Integer>
{
    @Override
    protected MutableBag<Integer> getCollection()
    {
        return MultiReaderHashBag.newBagWith(1, 1).asReadUntouchable();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addOccurrences()
    {
        this.getCollection().addOccurrences(1, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeOccurrences()
    {
        this.getCollection().removeOccurrences(1, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setOccurrences()
    {
        this.getCollection().setOccurrences(1, 1);
    }

    @Test
    public void occurrencesOf()
    {
        Assert.assertEquals(2, this.getCollection().occurrencesOf(1));
        Assert.assertEquals(0, this.getCollection().occurrencesOf(0));
    }

    @Test
    public void sizeDistinct()
    {
        Assert.assertEquals(1, this.getCollection().sizeDistinct());
    }

    @Test
    public void toMapOfItemToCount()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), this.getCollection().toMapOfItemToCount());
    }

    @Test
    public void toStringOfItemToCount()
    {
        Assert.assertEquals("{1=2}", this.getCollection().toStringOfItemToCount());
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

        Assert.assertEquals(2, sum[0]);
    }

    @Test
    public void selectUnique()
    {
        MutableBag<String> bag = MultiReaderHashBag.newBagWith("0", "1", "1", "1", "1", "2", "2", "2", "3", "3", "4", "5").asReadUntouchable();
        MutableSet<String> expected = Sets.mutable.with("0", "4", "5");
        MutableSet<String> actual = bag.selectUnique();
        Assert.assertEquals(expected, actual);
    }
}
