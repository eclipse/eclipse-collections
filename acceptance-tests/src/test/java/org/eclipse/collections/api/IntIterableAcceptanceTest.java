/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api;

import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link IntIterable}.
 */
public class IntIterableAcceptanceTest
{
    @Test
    public void testContainsAllWithMillionElementIterables()
    {
        MutableIntList list1 = IntLists.mutable.withInitialCapacity(1_000_000);
        MutableIntList list2 = IntLists.mutable.withInitialCapacity(1_000_000);

        for (int i = 0; i < 1_000_000 - 1; i++)
        {
            list1.add(i);
            list2.add(i);
        }

        list1.add(1_000_000);
        Assert.assertTrue(list1.containsAll(list2));
    }

    @Test
    public void testContainsAnyWithMillionElementIterable()
    {
        MutableIntList list = IntLists.mutable.withInitialCapacity(1_000_000);
        int[] source = new int[32];

        for (int i = 0; i < 1_000_000; i++)
        {
            if (i < source.length)
            {
                source[i] = 1_000_030 - i;
            }
            list.add(i);
        }

        Assert.assertTrue(list.containsAny(source));
    }

    @Test
    public void testContainsNoneWithMillionElementIterable()
    {
        MutableIntList list = IntLists.mutable.withInitialCapacity(1_000_000);
        int[] source = new int[32];

        for (int i = 0; i < 1_000_000; i++)
        {
            if (i < source.length)
            {
                source[i] = 1_000_030 - i;
            }
            list.add(i);
        }

        Assert.assertFalse(list.containsNone(source));
    }
}
