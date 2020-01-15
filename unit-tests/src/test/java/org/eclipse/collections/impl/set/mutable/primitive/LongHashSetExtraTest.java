/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import java.lang.reflect.Field;
import java.util.Random;

import org.eclipse.collections.api.block.predicate.primitive.LongPredicate;
import org.junit.Assert;
import org.junit.Test;

// extra tests not covered in the generated portion
public class LongHashSetExtraTest
{
    @Test
    public void testManyRemoves()
    {
        Random random = new Random(-6819587229044058720L);
        int expectedCardinality = random.nextInt(100000) + 100000;
        long maxUpperBound = Long.MAX_VALUE;
        double base = Math.pow((double) maxUpperBound, 1.0 / 128);
        long[] threshold = new long[1];
        threshold[0] = (long) (maxUpperBound / base);
        long upperBound = maxUpperBound;
        LongHashSet interestingHashes = new LongHashSet();
        for (int i = 1; i < 7308; i++)
        {
            long hash = random.nextLong() & 0x7FFFFFFFFFFFFFFFL;
            if (hash <= upperBound)
            {
                interestingHashes.add(hash);
                if (hash >= threshold[0])
                {
                    while (interestingHashes.removeIf((LongPredicate) it -> it >= threshold[0]))
                    {
                        base = Math.pow((double) threshold[0], 1.0 / 128);
                        upperBound = threshold[0];
                        threshold[0] /= base;
                    }
                }
            }
        }

        // this is a test that the operation doesn't go into an infinite loop
        interestingHashes.contains(1840593613821121L);

        // this is a test that the operation doesn't go into an infinite loop
        interestingHashes.add(1840593613821121L);
    }

    @Test
    public void testArrayGrowth() throws Exception
    {
        LongHashSet set = new LongHashSet();
        long putLong = 0;
        for (; putLong < 1000L; putLong++)
        {
            set.add(putLong);
        }
        for (; putLong < 10_000_000L; putLong++)
        {
            set.add(putLong);
            set.remove(putLong - 1000L);
        }
        Field table = LongHashSet.class.getDeclaredField("table");
        table.setAccessible(true);
        Assert.assertTrue(((long[]) table.get(set)).length < 10_000);
    }
}
