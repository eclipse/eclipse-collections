/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import java.util.Random;

import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.map.primitive.MutableIntLongMap;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.SpreadFunctions;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.junit.Assert;
import org.junit.Test;

public class IntLongMapProbeTest
{
    private static final int SMALL_COLLIDING_KEY_COUNT = 500;
    private static final int LARGE_COLLIDING_KEY_COUNT = 40000;

    private int smallMask(int spread)
    {
        return spread & ((1 << 11) - 1);
    }

    private int largeMask(int spread)
    {
        return spread & ((1 << 20) - 1);
    }

    @Test
    public void randomNumbers_get()
    {
        MutableIntLongMap intlongSmallNonPresized = new IntLongHashMap();
        this.testRandomGet(intlongSmallNonPresized, SMALL_COLLIDING_KEY_COUNT);

        MutableIntLongMap intlongSmallPresized = new IntLongHashMap(1_000);
        this.testRandomGet(intlongSmallPresized, SMALL_COLLIDING_KEY_COUNT);

        MutableIntLongMap intlongLargeNonPresized = new IntLongHashMap();
        this.testRandomGet(intlongLargeNonPresized, LARGE_COLLIDING_KEY_COUNT);

        MutableIntLongMap intlongLargePresized = new IntLongHashMap(1_000_000);
        this.testRandomGet(intlongLargePresized, LARGE_COLLIDING_KEY_COUNT);
    }

    @Test
    public void nonRandomNumbers_get()
    {
        MutableIntLongMap intlongMapSmall = new IntLongHashMap(1_000);
        int[] intKeysForSmallMap = this.getSmallCollidingNumbers().toArray();
        this.testNonRandomGet(intlongMapSmall, SMALL_COLLIDING_KEY_COUNT, intKeysForSmallMap);

        MutableIntLongMap intlongMapLarge = new IntLongHashMap(1_000_000);
        int[] intKeysForLargeMap = this.getLargeCollidingNumbers().toArray();
        this.testNonRandomGet(intlongMapLarge, LARGE_COLLIDING_KEY_COUNT, intKeysForLargeMap);
    }

    @Test
    public void nonRandomNumbers_remove()
    {
        MutableIntLongMap intlongMapSmallNonPresized = new IntLongHashMap();
        int[] intKeysForMap = this.getSmallCollidingNumbers().toArray();
        this.testNonRandomRemove(intlongMapSmallNonPresized, SMALL_COLLIDING_KEY_COUNT, intKeysForMap);

        MutableIntLongMap intlongMapSmallPresized = new IntLongHashMap(1_000);
        int[] intKeysForMap2 = this.getSmallCollidingNumbers().toArray();
        this.testNonRandomRemove(intlongMapSmallPresized, SMALL_COLLIDING_KEY_COUNT, intKeysForMap2);

        MutableIntLongMap intlongMapLargeNonPresized = new IntLongHashMap();
        int[] intKeysForMap3 = this.getLargeCollidingNumbers().toArray();
        this.testNonRandomRemove(intlongMapLargeNonPresized, LARGE_COLLIDING_KEY_COUNT, intKeysForMap3);

        MutableIntLongMap intlongMapLargePresized = new IntLongHashMap(1_000_000);
        int[] intKeysForMap4 = this.getLargeCollidingNumbers().toArray();
        this.testNonRandomRemove(intlongMapLargePresized, LARGE_COLLIDING_KEY_COUNT, intKeysForMap4);
    }

    @Test
    public void randomNumbers_remove()
    {
        MutableIntLongMap intlongMapSmallNonPresized = new IntLongHashMap();
        this.testRandomRemove(intlongMapSmallNonPresized, SMALL_COLLIDING_KEY_COUNT);

        MutableIntLongMap intlongMapSmallPresized = new IntLongHashMap(1_000);
        this.testRandomRemove(intlongMapSmallPresized, SMALL_COLLIDING_KEY_COUNT);

        MutableIntLongMap intlongMapLargeNonPresized = new IntLongHashMap();
        this.testRandomRemove(intlongMapLargeNonPresized, LARGE_COLLIDING_KEY_COUNT);

        MutableIntLongMap intlongMapLargePresized = new IntLongHashMap(1_000_000);
        this.testRandomRemove(intlongMapLargePresized, LARGE_COLLIDING_KEY_COUNT);
    }

    private void testNonRandomGet(MutableIntLongMap intlongMap, int keyCount, int[] intKeysForMap)
    {
        Random random = new Random(0x123456789ABCDL);
        this.shuffle(intKeysForMap, random);

        for (int i = 0; i < keyCount; i++)
        {
            intlongMap.put(intKeysForMap[i], (long) (intKeysForMap[i] * 10));
        }

        for (int i = 0; i < intlongMap.size(); i++)
        {
            Assert.assertEquals((long) (intKeysForMap[i] * 10), intlongMap.get(intKeysForMap[i]));
        }
    }

    private void testRandomGet(MutableIntLongMap intlongMap, int keyCount)
    {
        Random random = new Random(0x123456789ABCDL);
        MutableIntSet set = new IntHashSet(keyCount);
        while (set.size() < keyCount)
        {
            set.add(random.nextInt());
        }
        int[] randomNumbersForMap = set.toArray();
        this.shuffle(randomNumbersForMap, random);

        for (int i = 0; i < keyCount; i++)
        {
            intlongMap.put(randomNumbersForMap[i], (long) (randomNumbersForMap[i] * 10));
        }

        for (int i = 0; i < intlongMap.size(); i++)
        {
            Assert.assertEquals((long) (randomNumbersForMap[i] * 10), intlongMap.get(randomNumbersForMap[i]));
        }
    }

    private void testNonRandomRemove(MutableIntLongMap intlongMap, int keyCount, int[] intKeysForMap)
    {
        Random random = new Random(0x123456789ABCDL);
        this.shuffle(intKeysForMap, random);

        for (int i = 0; i < keyCount; i++)
        {
            intlongMap.put(intKeysForMap[i], (long) (intKeysForMap[i] * 10));
        }

        this.shuffle(intKeysForMap, random);
        for (int i = 0; i < intKeysForMap.length; i++)
        {
            intlongMap.remove(intKeysForMap[i]);
            for (int j = i + 1; j < intKeysForMap.length; j++)
            {
                Assert.assertEquals((long) (intKeysForMap[j] * 10), intlongMap.get(intKeysForMap[j]));
            }
        }
    }

    private void testRandomRemove(MutableIntLongMap intlongMap, int keyCount)
    {
        Random random = new Random(0x123456789ABCDL);
        MutableIntSet set = new IntHashSet(keyCount);
        while (set.size() < keyCount)
        {
            set.add(random.nextInt());
        }
        int[] intKeysForMap = set.toArray();
        this.shuffle(intKeysForMap, random);

        for (int i = 0; i < keyCount; i++)
        {
            intlongMap.put(intKeysForMap[i], (long) (intKeysForMap[i] * 10));
        }

        this.shuffle(intKeysForMap, random);
        for (int i = 0; i < intKeysForMap.length; i++)
        {
            intlongMap.remove(intKeysForMap[i]);
            for (int j = i + 1; j < intKeysForMap.length; j++)
            {
                Assert.assertEquals((long) (intKeysForMap[j] * 10), intlongMap.get(intKeysForMap[j]));
            }
        }
    }

    private MutableIntList getSmallCollidingNumbers()
    {
        int lower = Integer.MIN_VALUE;
        int upper = Integer.MAX_VALUE;

        MutableIntList collidingNumbers = new IntArrayList();
        int numberOne = this.smallMask(SpreadFunctions.intSpreadOne(0xABCDEF1));
        int numberTwo = this.smallMask(SpreadFunctions.intSpreadTwo(0xABCDEF1));
        for (int i = lower; i < upper && collidingNumbers.size() < SMALL_COLLIDING_KEY_COUNT; i++)
        {
            if (this.smallMask(SpreadFunctions.intSpreadOne(i)) == numberOne && this.smallMask(SpreadFunctions.intSpreadTwo(i)) == numberTwo)
            {
                collidingNumbers.add(i);
            }
        }
        return collidingNumbers;
    }

    private MutableIntList getLargeCollidingNumbers()
    {
        int lower = Integer.MIN_VALUE;
        int upper = Integer.MAX_VALUE;
        int number = 23;
        MutableIntList collidingNumbers = new IntArrayList();
        for (int i = lower; i < upper && collidingNumbers.size() < LARGE_COLLIDING_KEY_COUNT; i++)
        {
            int index = this.largeMask(SpreadFunctions.intSpreadOne(i));
            if (index >= number && index <= number + 100)
            {
                collidingNumbers.add(i);
            }
        }
        return collidingNumbers;
    }

    public void shuffle(int[] intArray, Random rnd)
    {
        for (int i = intArray.length; i > 1; i--)
        {
            IntLongMapProbeTest.swap(intArray, i - 1, rnd.nextInt(i));
        }
    }

    private static void swap(int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
