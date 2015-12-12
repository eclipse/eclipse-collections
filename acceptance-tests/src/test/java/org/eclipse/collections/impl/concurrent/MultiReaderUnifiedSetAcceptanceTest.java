/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.CollidingInt;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.MultiReaderUnifiedSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MultiReaderUnifiedSetAcceptanceTest
{
    @Test
    public void testUnifiedSet()
    {
        MultiReaderUnifiedSet<Integer> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            Assert.assertTrue(set.add(i));
        }
        Verify.assertSize(size, set);
        for (int i = 0; i < size; i++)
        {
            Verify.assertContains(i, set);
        }

        for (int i = 0; i < size; i += 2)
        {
            Assert.assertTrue(set.remove(i));
        }
        Verify.assertSize(size / 2, set);
        for (int i = 1; i < size; i += 2)
        {
            Verify.assertContains(i, set);
        }
    }

    @Test
    public void testUnifiedSetClear()
    {
        assertUnifiedSetClear(0);
        assertUnifiedSetClear(1);
        assertUnifiedSetClear(2);
        assertUnifiedSetClear(3);
    }

    private static void assertUnifiedSetClear(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            Assert.assertTrue(set.add(new CollidingInt(i, shift)));
        }
        set.clear();
        Verify.assertEmpty(set);
        for (int i = 0; i < size; i++)
        {
            Verify.assertNotContains(new CollidingInt(i, shift), set);
        }
    }

    @Test
    public void testUnifiedSetForEach()
    {
        assertUnifiedSetForEach(0);
        assertUnifiedSetForEach(1);
        assertUnifiedSetForEach(2);
        assertUnifiedSetForEach(3);
    }

    private static void assertUnifiedSetForEach(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            Assert.assertTrue(set.add(new CollidingInt(i, shift)));
        }
        MutableList<CollidingInt> keys = FastList.newList(size);
        set.forEach(Procedures.cast(keys::add));
        Verify.assertSize(size, keys);
        Collections.sort(keys);

        for (int i = 0; i < size; i++)
        {
            Verify.assertItemAtIndex(new CollidingInt(i, shift), i, keys);
        }
    }

    @Test
    public void testUnifiedSetForEachWith()
    {
        assertUnifiedSetForEachWith(0);
        assertUnifiedSetForEachWith(1);
        assertUnifiedSetForEachWith(2);
        assertUnifiedSetForEachWith(3);
    }

    private static void assertUnifiedSetForEachWith(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            Assert.assertTrue(set.add(new CollidingInt(i, shift)));
        }
        MutableList<CollidingInt> keys = FastList.newList(size);
        set.forEachWith((key, s) -> {
            Assert.assertEquals("foo", s);
            keys.add(key);
        }, "foo");
        Verify.assertSize(size, keys);
        Collections.sort(keys);

        for (int i = 0; i < size; i++)
        {
            Verify.assertItemAtIndex(new CollidingInt(i, shift), i, keys);
        }
    }

    @Test
    public void testUnifiedSetForEachWithIndex()
    {
        assertUnifiedSetForEachWithIndex(0);
        assertUnifiedSetForEachWithIndex(1);
        assertUnifiedSetForEachWithIndex(2);
        assertUnifiedSetForEachWithIndex(3);
    }

    private static void assertUnifiedSetForEachWithIndex(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            Assert.assertTrue(set.add(new CollidingInt(i, shift)));
        }
        MutableList<CollidingInt> keys = FastList.newList(size);
        int[] prevIndex = new int[1];
        set.forEachWithIndex((key, index) -> {
            Assert.assertEquals(prevIndex[0], index);
            prevIndex[0]++;
            keys.add(key);
        });
        Verify.assertSize(size, keys);
        Collections.sort(keys);

        for (int i = 0; i < size; i++)
        {
            Verify.assertItemAtIndex(new CollidingInt(i, shift), i, keys);
        }
    }

    @Test
    public void testUnifiedSetAddAll()
    {
        assertUnifiedSetAddAll(0);
        assertUnifiedSetAddAll(1);
        assertUnifiedSetAddAll(2);
        assertUnifiedSetAddAll(3);
    }

    private static void assertUnifiedSetAddAll(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
        }
        MultiReaderUnifiedSet<CollidingInt> newSet = MultiReaderUnifiedSet.newSet(size);
        newSet.addAll(set);

        Verify.assertSize(size, newSet);
        for (int i = 0; i < size; i++)
        {
            Verify.assertContains(new CollidingInt(i, shift), newSet);
        }
    }

    @Test
    public void testUnifiedSetAddAllWithHashSet()
    {
        assertUnifiedSetAddAllWithHashSet(0);
        assertUnifiedSetAddAllWithHashSet(1);
        assertUnifiedSetAddAllWithHashSet(2);
        assertUnifiedSetAddAllWithHashSet(3);
    }

    private static void assertUnifiedSetAddAllWithHashSet(int shift)
    {
        Set<CollidingInt> set = new HashSet<>();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
        }
        MultiReaderUnifiedSet<CollidingInt> newSet = MultiReaderUnifiedSet.newSet(size);
        newSet.addAll(set);

        Verify.assertSize(size, newSet);
        for (int i = 0; i < size; i++)
        {
            Verify.assertContains(new CollidingInt(i, shift), newSet);
        }

        MultiReaderUnifiedSet<CollidingInt> newSet2 = MultiReaderUnifiedSet.newSet();
        newSet2.addAll(set);

        Verify.assertSize(size, newSet2);
        for (int i = 0; i < size; i++)
        {
            Verify.assertContains(new CollidingInt(i, shift), newSet2);
        }
    }

    @Test
    public void testUnifiedSetReplace()
    {
        assertUnifiedSetReplace(0);
        assertUnifiedSetReplace(1);
        assertUnifiedSetReplace(2);
        assertUnifiedSetReplace(3);
    }

    private static void assertUnifiedSetReplace(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
        }
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
        }
        Verify.assertSize(size, set);
        for (int i = 0; i < size; i++)
        {
            Verify.assertContains(new CollidingInt(i, shift), set);
        }
    }

    @Test
    public void testUnifiedSetRetainAllFromList()
    {
        runUnifiedSetRetainAllFromList(0);
        runUnifiedSetRetainAllFromList(1);
        runUnifiedSetRetainAllFromList(2);
        runUnifiedSetRetainAllFromList(3);
    }

    private static void runUnifiedSetRetainAllFromList(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        MutableList<CollidingInt> toRetain = Lists.mutable.of();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
            if (i % 2 == 0)
            {
                toRetain.add(new CollidingInt(i, shift));
            }
        }
        Verify.assertSize(size, set);
        Assert.assertTrue(set.containsAll(toRetain));

        Assert.assertTrue(set.retainAll(toRetain));
        Assert.assertTrue(set.containsAll(toRetain));

        Assert.assertFalse(set.retainAll(toRetain)); // a second call should not modify the set

        Verify.assertSize(size / 2, set);

        for (int i = 0; i < size; i += 2)
        {
            Verify.assertContains(new CollidingInt(i, shift), set);
        }
    }

    @Test
    public void testUnifiedSetRetainAllFromSet()
    {
        runUnifiedSetRetainAllFromSet(0);
        runUnifiedSetRetainAllFromSet(1);
        runUnifiedSetRetainAllFromSet(2);
        runUnifiedSetRetainAllFromSet(3);
    }

    private static void runUnifiedSetRetainAllFromSet(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        Set<CollidingInt> toRetain = new HashSet<>();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
            if (i % 2 == 0)
            {
                toRetain.add(new CollidingInt(i, shift));
            }
        }
        Verify.assertSize(size, set);
        Assert.assertTrue(set.containsAll(toRetain));

        Assert.assertTrue(set.retainAll(toRetain));
        Assert.assertTrue(set.containsAll(toRetain));

        Assert.assertFalse(set.retainAll(toRetain)); // a second call should not modify the set

        Verify.assertSize(size / 2, set);

        for (int i = 0; i < size; i += 2)
        {
            Verify.assertContains(new CollidingInt(i, shift), set);
        }
    }

    @Test
    public void testUnifiedSetToArray()
    {
        runUnifiedSetToArray(0);
        runUnifiedSetToArray(1);
        runUnifiedSetToArray(2);
        runUnifiedSetToArray(3);
    }

    private static void runUnifiedSetToArray(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
        }
        Verify.assertSize(size, set);

        Object[] keys = set.toArray();
        Assert.assertEquals(size, keys.length);
        Arrays.sort(keys);

        for (int i = 0; i < size; i++)
        {
            Verify.assertItemAtIndex(new CollidingInt(i, shift), i, keys);
        }
    }

    @Test
    public void testUnifiedSetSerialize()
    {
        runUnifiedSetSerialize(0);
        runUnifiedSetSerialize(1);
        runUnifiedSetSerialize(2);
        runUnifiedSetSerialize(3);
    }

    private static void runUnifiedSetSerialize(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
        }
        set.add(null);
        set = SerializeTestHelper.serializeDeserialize(set);

        Verify.assertSize(size + 1, set);
        for (int i = 0; i < size; i++)
        {
            Verify.assertContains(new CollidingInt(i, shift), set);
        }
        Verify.assertContains(null, set);
    }

    @Test
    public void testUnifiedSetKeySetToArrayDest()
    {
        MutableSet<Integer> set = MultiReaderUnifiedSet.newSetWith(1, 2, 3, 4);
        // deliberately to small to force the method to allocate one of the correct size
        Integer[] dest = new Integer[2];
        Integer[] result = set.toArray(dest);
        Verify.assertSize(4, result);
        Arrays.sort(result);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4}, result);
    }

    @Test
    public void testUnifiedSetEqualsAndHashCode()
    {
        assertUnifiedSetEqualsAndHashCode(0);
        assertUnifiedSetEqualsAndHashCode(1);
        assertUnifiedSetEqualsAndHashCode(2);
        assertUnifiedSetEqualsAndHashCode(3);
    }

    private static void assertUnifiedSetEqualsAndHashCode(int shift)
    {
        MutableSet<CollidingInt> set1 = MultiReaderUnifiedSet.newSet();
        Set<CollidingInt> set2 = new HashSet<>();
        MutableSet<CollidingInt> set3 = MultiReaderUnifiedSet.newSet();
        MutableSet<CollidingInt> set4 = MultiReaderUnifiedSet.newSet();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set1.add(new CollidingInt(i, shift));
            set2.add(new CollidingInt(i, shift));
            set3.add(new CollidingInt(i, shift));
            set4.add(new CollidingInt(size - i - 1, shift));
        }

        Assert.assertEquals(set1, set2);
        Assert.assertEquals(set1.hashCode(), set2.hashCode());
        Verify.assertSetsEqual(set1, set3);
        Verify.assertEqualsAndHashCode(set1, set3);
        Verify.assertSetsEqual(set2, set4);
        Assert.assertEquals(set4, set2);
        Assert.assertEquals(set2.hashCode(), set4.hashCode());
    }

    @Test
    public void testUnifiedSetRemoveAll()
    {
        runUnifiedSetRemoveAll(0);
        runUnifiedSetRemoveAll(1);
        runUnifiedSetRemoveAll(2);
        runUnifiedSetRemoveAll(3);
    }

    private static void runUnifiedSetRemoveAll(int shift)
    {
        MultiReaderUnifiedSet<CollidingInt> set = MultiReaderUnifiedSet.newSet();

        List<CollidingInt> toRemove = new ArrayList<>();

        int size = 100000;
        for (int i = 0; i < size; i++)
        {
            set.add(new CollidingInt(i, shift));
            if (i % 2 == 0)
            {
                toRemove.add(new CollidingInt(i, shift));
            }
        }
        Verify.assertSize(size, set);

        Assert.assertTrue(set.removeAll(toRemove));

        Assert.assertFalse(set.removeAll(toRemove)); // a second call should not modify the set

        Verify.assertSize(size / 2, set);

        for (int i = 1; i < size; i += 2)
        {
            Verify.assertContains(new CollidingInt(i, shift), set);
        }
    }

    @Test
    public void testUnifiedSetPutDoesNotReplace()
    {
        this.assertUnifiedSetPutDoesNotReplace(0);
        this.assertUnifiedSetPutDoesNotReplace(1);
        this.assertUnifiedSetPutDoesNotReplace(2);
        this.assertUnifiedSetPutDoesNotReplace(3);
        this.assertUnifiedSetPutDoesNotReplace(4);
    }

    private void assertUnifiedSetPutDoesNotReplace(int shift)
    {
        MultiReaderUnifiedSet<CollidingIntWithFlag> set = MultiReaderUnifiedSet.newSet();

        for (int i = 0; i < 1000; i++)
        {
            Assert.assertTrue(set.add(new CollidingIntWithFlag(i, shift, false)));
        }
        Assert.assertEquals(1000, set.size());

        for (int i = 0; i < 1000; i++)
        {
            Assert.assertFalse(set.add(new CollidingIntWithFlag(i, shift, true)));
        }
        Assert.assertEquals(1000, set.size());
        set.withReadLockAndDelegate(delegate -> {
            for (CollidingIntWithFlag ciwf : delegate)
            {
                Assert.assertFalse(ciwf.isFlag());
            }
        });
    }

    private static final class CollidingIntWithFlag extends CollidingInt
    {
        private static final long serialVersionUID = 1L;
        private final boolean flag;

        private CollidingIntWithFlag(int value, int shift, boolean flag)
        {
            super(value, shift);
            this.flag = flag;
        }

        public boolean isFlag()
        {
            return this.flag;
        }
    }
}
