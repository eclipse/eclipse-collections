/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import org.eclipse.collections.api.block.function.primitive.ByteToObjectFunction;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableByteSet;
import org.eclipse.collections.impl.block.factory.primitive.BytePredicates;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ByteHashSet}.
 */
public class ByteHashSetTest extends AbstractByteSetTestCase
{
    @Override
    protected final ByteHashSet classUnderTest()
    {
        return ByteHashSet.newSetWith((byte) 1, (byte) 2, (byte) 3);
    }

    @Override
    protected ByteHashSet newWith(byte... elements)
    {
        return ByteHashSet.newSetWith(elements);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newWithInitialCapacity_negative_throws()
    {
        new ByteHashSet(-1);
    }

    @Override
    @Test
    public void newCollection()
    {
        super.newCollection();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -98, (byte) -64, (byte) -128);
        ByteHashSet hashSetFromList = ByteHashSet.newSet(ByteArrayList.newListWith((byte) 0, (byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -98, (byte) -64, (byte) -128));
        ByteHashSet hashSetFromSet = ByteHashSet.newSet(set);
        Assert.assertEquals(set, hashSetFromList);
        Assert.assertEquals(set, hashSetFromSet);
    }

    @Override
    @Test
    public void injectInto()
    {
        super.injectInto();

        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) -128, (byte) 64);
        Byte sum = set.injectInto(Byte.valueOf((byte) 0), (result, value) -> Byte.valueOf((byte) (result + value)));
        Assert.assertEquals(Byte.valueOf((byte) -99), sum);
    }

    @Override
    @Test
    public void appendString()
    {
        super.appendString();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) -128, (byte) 64);
        StringBuilder sb = new StringBuilder();
        sb.append("con");
        sb.append("tents");
        set.appendString(sb, "start", ",", "end");

        Assert.assertEquals("contentsstart-128,-64,-2,-1,0,1,31,64end", sb.toString());
    }

    @Override
    @Test
    public void reject()
    {
        super.reject();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) -128, (byte) 64);
        MutableByteSet actualSet = set.reject(BytePredicates.greaterThan((byte) 0));

        ByteHashSet expectedSet = ByteHashSet.newSetWith((byte) 0, (byte) -1, (byte) -2, (byte) -64, (byte) -128);
        Assert.assertEquals(expectedSet, actualSet);

        MutableByteSet actualSet2 = set.reject(BytePredicates.lessThan((byte) 0));
        ByteHashSet expectedSet2 = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) 64);
        Assert.assertEquals(expectedSet2, actualSet2);
    }

    @Test
    public void hashcode()
    {
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) -128, (byte) 64);
        Assert.assertEquals(-99, set.hashCode());

        ByteHashSet set1 = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31);
        Assert.assertEquals(32, set1.hashCode());

        ByteHashSet set2 = ByteHashSet.newSetWith((byte) -76, (byte) -128, (byte) -127);
        Assert.assertEquals(-331, set2.hashCode());

        ByteHashSet set3 = ByteHashSet.newSetWith((byte) -33, (byte) 127, (byte) 65);
        Assert.assertEquals(159, set3.hashCode());
    }

    @Override
    @Test
    public void collect()
    {
        super.collect();
        ByteToObjectFunction<Byte> function = parameter -> (byte) (parameter - 1);

        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) 111, (byte) 64);
        MutableSet<Byte> actualSet = set.collect(function);
        MutableSet<Byte> expectedSet = UnifiedSet.newSetWith((byte) -1, (byte) 0, (byte) 30, (byte) -2, (byte) -3, (byte) -65, (byte) 110, (byte) 63);

        Assert.assertEquals(expectedSet, actualSet);
    }

    @Override
    @Test
    public void anySatisfy()
    {
        super.anySatisfy();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) -65, (byte) -128, (byte) 111, (byte) 64);

        Assert.assertTrue(set.anySatisfy(BytePredicates.lessThan((byte) 0)));
        Assert.assertTrue(set.anySatisfy(BytePredicates.lessThan((byte) -2)));
        Assert.assertTrue(set.anySatisfy(BytePredicates.lessThan((byte) -64)));
        Assert.assertTrue(set.anySatisfy(BytePredicates.greaterThan((byte) 65)));
        Assert.assertFalse(set.anySatisfy(BytePredicates.greaterThan((byte) 121)));
        Assert.assertFalse(set.anySatisfy(BytePredicates.lessThan((byte) -128)));
    }

    @Override
    @Test
    public void allSatisfy()
    {
        super.allSatisfy();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) 111,
                (byte) 64, (byte) 65, (byte) 125, (byte) -65, (byte) -125);

        Assert.assertTrue(set.allSatisfy(BytePredicates.lessThan((byte) 127)));
        Assert.assertTrue(set.allSatisfy(BytePredicates.greaterThan((byte) -126)));
        Assert.assertFalse(set.allSatisfy(BytePredicates.lessThan((byte) 0)));
        Assert.assertFalse(set.allSatisfy(BytePredicates.greaterThan((byte) 63)));
        Assert.assertFalse(set.allSatisfy(BytePredicates.equal((byte) 68)));
        Assert.assertFalse(set.allSatisfy(BytePredicates.equal((byte) -124)));
        Assert.assertFalse(set.allSatisfy(BytePredicates.lessThan((byte) 68)));
        Assert.assertFalse(set.allSatisfy(BytePredicates.greaterThan((byte) -68)));
    }

    @Override
    @Test
    public void noneSatisfy()
    {
        super.noneSatisfy();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) 111,
                (byte) 64, (byte) 65, (byte) 125, (byte) -65, (byte) -125);

        Assert.assertFalse(set.noneSatisfy(BytePredicates.lessThan((byte) 127)));
        Assert.assertTrue(set.noneSatisfy(BytePredicates.greaterThan((byte) 127)));
        Assert.assertFalse(set.noneSatisfy(BytePredicates.lessThan((byte) 65)));
        Assert.assertFalse(set.noneSatisfy(BytePredicates.greaterThan((byte) -2)));
        Assert.assertFalse(set.noneSatisfy(BytePredicates.greaterThan((byte) -65)));
        Assert.assertFalse(set.noneSatisfy(BytePredicates.greaterThan((byte) 124)));
        Assert.assertFalse(set.noneSatisfy(BytePredicates.lessThan((byte) -1)));
        Assert.assertFalse(set.noneSatisfy(BytePredicates.lessThan((byte) -65)));
    }

    @Override
    @Test
    public void sum()
    {
        super.sum();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) -128, (byte) 64);
        Assert.assertEquals(-99, set.sum());

        ByteHashSet set1 = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31);
        Assert.assertEquals(32, set1.sum());

        ByteHashSet set2 = ByteHashSet.newSetWith((byte) -76, (byte) -128, (byte) -127);
        Assert.assertEquals(-331, set2.sum());

        ByteHashSet set3 = ByteHashSet.newSetWith((byte) -33, (byte) 127, (byte) 65);
        Assert.assertEquals(159, set3.sum());
    }

    @Override
    @Test
    public void min()
    {
        super.min();
        ByteHashSet set = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31, (byte) -1, (byte) -2, (byte) -64, (byte) -128, (byte) 64, (byte) 127);
        Assert.assertEquals(-128, set.min());

        ByteHashSet set1 = ByteHashSet.newSetWith((byte) 0, (byte) 1, (byte) 31);
        Assert.assertEquals(0, set1.min());

        ByteHashSet set2 = ByteHashSet.newSetWith((byte) -76, (byte) -128, (byte) -127);
        Assert.assertEquals(-128, set2.min());

        ByteHashSet set3 = ByteHashSet.newSetWith((byte) -33, (byte) 127, (byte) 65);
        Assert.assertEquals(-33, set3.min());

        ByteHashSet set4 = ByteHashSet.newSetWith((byte) -65, (byte) -127, (byte) -90);
        Assert.assertEquals(-127, set4.min());

        ByteHashSet set5 = ByteHashSet.newSetWith((byte) 75, (byte) 85, (byte) 127);
        Assert.assertEquals(75, set5.min());
    }

    @Override
    @Test
    public void max()
    {
        super.max();
        Assert.assertEquals(9L, this.newWith((byte) -1, (byte) -2, (byte) 9).max());
        Assert.assertEquals(127L, this.newWith((byte) -1, (byte) -2, (byte) 9, (byte) -65, (byte) -127, (byte) 65, (byte) 127).max());
        Assert.assertEquals(-1L, this.newWith((byte) -1, (byte) -2, (byte) -9, (byte) -65, (byte) -127).max());
        Assert.assertEquals(-65L, this.newWith((byte) -65, (byte) -87, (byte) -127).max());
    }

    @Test
    public void testEqualsContainingElements()
    {
        Verify.assertPostSerializedEqualsAndHashCode(this.newWith((byte) 14, (byte) 2, (byte) 30, (byte) 31, (byte) -90, (byte) 64, (byte) 0, (byte) 127, (byte) -14, (byte) -2, (byte) -30, (byte) -31, (byte) -65, (byte) -64, (byte) -128, (byte) 127));
    }

    @Test
    public void addAndCheckField()
    {
        ByteHashSet hashSet = new ByteHashSet();
        Assert.assertTrue(hashSet.add((byte) 14));
        Assert.assertFalse(hashSet.add((byte) 14));
        Assert.assertTrue(hashSet.add((byte) 2));
        Assert.assertFalse(hashSet.add((byte) 2));
        Assert.assertTrue(hashSet.add((byte) 35));
        Assert.assertFalse(hashSet.add((byte) 35));
        Assert.assertTrue(hashSet.add((byte) 31));
        Assert.assertFalse(hashSet.add((byte) 31));
        Assert.assertTrue(hashSet.add((byte) 32));
        Assert.assertFalse(hashSet.add((byte) 32));
        Assert.assertTrue(hashSet.add((byte) 0));
        Assert.assertFalse(hashSet.add((byte) 0));
        Assert.assertTrue(hashSet.add((byte) 1));
        Assert.assertFalse(hashSet.add((byte) 1));
        Assert.assertEquals(ByteHashSet.newSetWith((byte) 14, (byte) 2, (byte) 31, (byte) 32, (byte) 35, (byte) 0, (byte) 1), hashSet);
    }

    @Test
    public void addWithRehash()
    {
        ByteHashSet hashSet = new ByteHashSet();
        Assert.assertTrue(hashSet.addAll((byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39));
        Assert.assertEquals(8, hashSet.size());
        Assert.assertTrue(hashSet.addAll((byte) 0, (byte) 63, (byte) 64, (byte) 127, (byte) -1, (byte) -64, (byte) -65, (byte) -128));
        Assert.assertEquals(16, hashSet.size());
    }

    @Test
    public void addEverySlot()
    {
        this.addAndRemoveData(new ByteHashSet());
    }

    private void addAndRemoveData(ByteHashSet hashSet)
    {
        for (byte i = (byte) 100; i < (byte) 200; i++)
        {
            Assert.assertFalse(hashSet.contains(i));
            Assert.assertTrue(hashSet.add(i));
            Assert.assertTrue(hashSet.remove(i));
        }
    }

    @Test
    public void addDuplicateWithRemovedSlot()
    {
        ByteHashSet hashSet = new ByteHashSet();
        hashSet.add((byte) 0);
        hashSet.add((byte) 1);
        hashSet.add((byte) 63);
        hashSet.add((byte) 64);
        hashSet.add((byte) 65);
        hashSet.add((byte) 127);
        hashSet.add((byte) -1);
        hashSet.add((byte) -2);
        hashSet.add((byte) -63);
        hashSet.add((byte) -64);
        hashSet.add((byte) -127);

        hashSet.add((byte) -1);
        hashSet.add((byte) -2);
        hashSet.add((byte) -63);
        hashSet.add((byte) -64);
        hashSet.add((byte) -127);

        Assert.assertEquals(11, hashSet.size());
    }

    @Test
    public void addZeroToThirtyOne()
    {
        ByteHashSet hashSet = ByteHashSet.newSetWith();
        for (byte i = (byte) 0; i <= (byte) 31; i++)
        {
            Assert.assertTrue(hashSet.add(i));
            Assert.assertFalse(hashSet.add(i));
        }
        for (byte i = (byte) 0; i <= (byte) 31; i++)
        {
            Assert.assertTrue(hashSet.contains(i));
        }

        for (byte i = (byte) 0; i <= (byte) 31; i++)
        {
            Assert.assertTrue(hashSet.contains(i));
            Assert.assertTrue(hashSet.remove(i));
            Assert.assertFalse(hashSet.contains(i));
            Assert.assertFalse(hashSet.remove(i));
        }

        Assert.assertEquals(new ByteHashSet(), hashSet);
    }

    @Test
    public void addDuplicates()
    {
        ByteHashSet hashSet = ByteHashSet.newSetWith();
        Assert.assertEquals(0, hashSet.size());

        hashSet.add((byte) 0);
        hashSet.add((byte) 1);
        hashSet.add((byte) 2);
        hashSet.add((byte) 20);
        hashSet.add((byte) 55);
        hashSet.add((byte) 63);

        hashSet.add((byte) 0);
        hashSet.add((byte) 1);
        hashSet.add((byte) 63);
        Assert.assertEquals(6, hashSet.size());
    }

    @Test
    public void addRemoveAndContainsIntegrationTest()
    {
        ByteHashSet hashSet = ByteHashSet.newSetWith();

        Assert.assertFalse(hashSet.contains((byte) 0));
        Assert.assertFalse(hashSet.contains((byte) 1));
        Assert.assertFalse(hashSet.contains((byte) 2));
        Assert.assertFalse(hashSet.contains((byte) 20));
        Assert.assertFalse(hashSet.contains((byte) 55));
        Assert.assertFalse(hashSet.contains((byte) 63));
        Assert.assertFalse(hashSet.contains((byte) 63));

        hashSet.add((byte) 0);
        hashSet.add((byte) 1);
        hashSet.add((byte) 2);
        hashSet.add((byte) 20);
        hashSet.add((byte) 55);
        hashSet.add((byte) 63);

        Assert.assertTrue(hashSet.contains((byte) 0));
        Assert.assertTrue(hashSet.contains((byte) 1));
        Assert.assertTrue(hashSet.contains((byte) 2));
        Assert.assertTrue(hashSet.contains((byte) 20));
        Assert.assertTrue(hashSet.contains((byte) 55));
        Assert.assertTrue(hashSet.contains((byte) 63));
        Assert.assertTrue(hashSet.contains((byte) 63));

        Assert.assertFalse(hashSet.contains((byte) 23));
        Assert.assertFalse(hashSet.contains((byte) 44));
        Assert.assertFalse(hashSet.contains((byte) 54));

        hashSet.remove((byte) 0);
        Assert.assertFalse(hashSet.contains((byte) 0));

        hashSet.remove((byte) 20);
        Assert.assertFalse(hashSet.contains((byte) 20));

        Assert.assertFalse(hashSet.contains((byte) 64));
        Assert.assertFalse(hashSet.contains((byte) 66));
        Assert.assertFalse(hashSet.contains((byte) 78));
        Assert.assertFalse(hashSet.contains((byte) 100));
        Assert.assertFalse(hashSet.contains((byte) 127));

        hashSet.add((byte) 64);
        hashSet.add((byte) 66);
        hashSet.add((byte) 78);
        hashSet.add((byte) 100);
        hashSet.add((byte) 127);

        Assert.assertTrue(hashSet.contains((byte) 64));
        Assert.assertTrue(hashSet.contains((byte) 66));
        Assert.assertTrue(hashSet.contains((byte) 78));
        Assert.assertTrue(hashSet.contains((byte) 100));
        Assert.assertTrue(hashSet.contains((byte) 127));

        Assert.assertFalse(hashSet.contains((byte) 70));
        Assert.assertFalse(hashSet.contains((byte) 75));
        Assert.assertFalse(hashSet.contains((byte) 125));

        hashSet.remove((byte) 64);
        Assert.assertFalse(hashSet.contains((byte) 64));

        hashSet.remove((byte) 100);
        Assert.assertFalse(hashSet.contains((byte) 100));

        hashSet.add((byte) -1);
        hashSet.add((byte) -2);
        hashSet.add((byte) -33);
        hashSet.add((byte) -34);
        hashSet.add((byte) -50);
        hashSet.add((byte) -64);

        hashSet.add((byte) -65);
        hashSet.add((byte) -78);
        hashSet.add((byte) -112);
        hashSet.add((byte) -127);
        hashSet.add((byte) -128);

        Assert.assertTrue(hashSet.contains((byte) -1));
        Assert.assertTrue(hashSet.contains((byte) -2));
        Assert.assertTrue(hashSet.contains((byte) -33));
        Assert.assertTrue(hashSet.contains((byte) -34));
        Assert.assertTrue(hashSet.contains((byte) -50));
        Assert.assertTrue(hashSet.contains((byte) -64));

        Assert.assertTrue(hashSet.contains((byte) -65));
        Assert.assertTrue(hashSet.contains((byte) -78));
        Assert.assertTrue(hashSet.contains((byte) -112));
        Assert.assertTrue(hashSet.contains((byte) -127));
        Assert.assertTrue(hashSet.contains((byte) -128));

        Assert.assertFalse(hashSet.contains((byte) -31));
        Assert.assertFalse(hashSet.contains((byte) -55));
        Assert.assertFalse(hashSet.contains((byte) -66));

        Assert.assertFalse(hashSet.contains((byte) -75));
        Assert.assertFalse(hashSet.contains((byte) -80));
        Assert.assertFalse(hashSet.contains((byte) -100));

        hashSet.remove((byte) -1);
        hashSet.remove((byte) -2);
        hashSet.remove((byte) -65);
        hashSet.remove((byte) -127);
        hashSet.remove((byte) -33);

        Assert.assertFalse(hashSet.contains((byte) -1));
        Assert.assertFalse(hashSet.contains((byte) -2));
        Assert.assertFalse(hashSet.contains((byte) -65));
        Assert.assertFalse(hashSet.contains((byte) -127));
        Assert.assertFalse(hashSet.contains((byte) -33));
    }

    @Test
    public void testToArray()
    {
        ByteHashSet hashSet = ByteHashSet.newSetWith();
        hashSet.add((byte) 0);
        hashSet.add((byte) 1);
        hashSet.add((byte) 2);
        hashSet.add((byte) 20);
        hashSet.add((byte) 55);
        hashSet.add((byte) 63);

        hashSet.add((byte) 64);
        hashSet.add((byte) 65);
        hashSet.add((byte) 67);
        hashSet.add((byte) 70);
        hashSet.add((byte) 78);
        hashSet.add((byte) 80);
        hashSet.add((byte) 87);
        hashSet.add((byte) 98);
        hashSet.add((byte) 127);

        hashSet.add((byte) -1);
        hashSet.add((byte) -5);
        hashSet.add((byte) -14);
        hashSet.add((byte) -56);
        hashSet.add((byte) -63);
        hashSet.add((byte) -64);

        hashSet.add((byte) -65);
        hashSet.add((byte) -67);
        hashSet.add((byte) -100);
        hashSet.add((byte) -98);
        hashSet.add((byte) -87);
        hashSet.add((byte) -128);

        byte[] expected = {-128, -100, -98, -87, -67, -65, -64, -63, -56, -14, -5, -1, 0, 1, 2, 20, 55, 63, 64, 65, 67, 70, 78, 80, 87, 98, 127};

        Assert.assertArrayEquals(expected, hashSet.toArray());
    }

    @Test
    public void toImmutable()
    {
        ByteHashSet hashSet = ByteHashSet.newSetWith();
        Assert.assertEquals(0, hashSet.toImmutable().size());

        ByteHashSet hashSet1 = ByteHashSet.newSetWith((byte) -1);
        Assert.assertEquals(1, hashSet1.toImmutable().size());

        ByteHashSet hashSet2 = ByteHashSet.newSetWith((byte) -1, (byte) -4);
        Assert.assertEquals(2, hashSet2.toImmutable().size());
    }

    @Test
    public void freeze()
    {
        ByteHashSet hashSet = ByteHashSet.newSetWith();
        Assert.assertEquals(0, hashSet.freeze().size());

        ByteHashSet hashSet1 = ByteHashSet.newSetWith((byte) -1);
        Assert.assertEquals(1, hashSet1.freeze().size());

        ByteHashSet hashSet2 = ByteHashSet.newSetWith((byte) -1, (byte) -4);
        Assert.assertEquals(2, hashSet2.freeze().size());
    }
}
