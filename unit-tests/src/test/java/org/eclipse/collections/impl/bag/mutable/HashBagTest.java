/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable;

import java.util.Collections;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.tuple.primitive.ObjectIntPair;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashBagTest extends MutableBagTestCase
{
    @Override
    protected <T> MutableBag<T> newWith(T... littleElements)
    {
        return HashBag.newBagWith(littleElements);
    }

    @Override
    protected <T> MutableBag<T> newWithOccurrences(ObjectIntPair<T>... elementsWithOccurrences)
    {
        MutableBag<T> bag = this.newWith();
        for (int i = 0; i < elementsWithOccurrences.length; i++)
        {
            ObjectIntPair<T> itemToAdd = elementsWithOccurrences[i];
            bag.addOccurrences(itemToAdd.getOne(), itemToAdd.getTwo());
        }
        return bag;
    }

    @Test
    public void newBagWith()
    {
        HashBag<String> bag = new HashBag<String>().with("apple", "apple");
        assertBagsEqual(HashBag.newBagWith("apple", "apple"), bag);

        bag.with("hope", "hope", "hope");
        assertBagsEqual(HashBag.newBagWith("apple", "apple", "hope", "hope", "hope"), bag);

        bag.withAll(Collections.nCopies(5, "ubermench"));
        assertEquals(
                UnifiedMap.newWithKeysValues(
                        "apple", 2,
                        "hope", 3,
                        "ubermench", 5),
                bag.toMapOfItemToCount());
    }

    @Override
    @Test
    public void addAll()
    {
        super.addAll();
        MutableBag<Integer> bag1 = this.newWith();
        assertTrue(bag1.addAll(this.newWith(1, 1, 2, 3)));
        Verify.assertContainsAll(bag1, 1, 2, 3);

        assertTrue(bag1.addAll(this.newWith(1, 2, 3)));
        Verify.assertSize(7, bag1);
        assertFalse(bag1.addAll(this.newWith()));
        Verify.assertContainsAll(bag1, 1, 2, 3);

        MutableBag<Integer> bag2 = this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        bag2.addAll(this.newWith(5, 5, 5, 5, 5));

        Verify.assertBagsEqual(this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5), bag2);

        MutableBag<Integer> bag3 = this.newWith(1, 2, 2, 3, 3, 3);
        bag3.addAll(this.newWith(1));

        Verify.assertBagsEqual(this.newWith(1, 1, 2, 2, 3, 3, 3), bag3);
    }

    @Test
    public void newBagFromIterable()
    {
        assertBagsEqual(
                HashBag.newBagWith(1, 2, 2, 3, 3, 3),
                HashBag.newBag(FastList.newListWith(1, 2, 2, 3, 3, 3)));
    }

    @Test
    public void newBagFromBag()
    {
        assertEquals(
                HashBag.newBagWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4),
                HashBag.newBag(HashBag.newBagWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)));
    }
}
