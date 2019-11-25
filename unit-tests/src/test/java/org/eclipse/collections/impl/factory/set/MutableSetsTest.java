/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.set;

import java.lang.reflect.Field;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MutableSetsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(UnifiedSet.newSet(), MutableSet.empty());
        Assert.assertEquals(UnifiedSet.newSet(), MutableSet.of());
        Verify.assertInstanceOf(MutableSet.class, MutableSet.of());
        Assert.assertEquals(UnifiedSet.newSetWith(1), MutableSet.of(1));
        Verify.assertInstanceOf(MutableSet.class, MutableSet.of(1));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2), MutableSet.of(1, 2));
        Verify.assertInstanceOf(MutableSet.class, MutableSet.of(1, 2));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3), MutableSet.of(1, 2, 3));
        Verify.assertInstanceOf(MutableSet.class, MutableSet.of(1, 2, 3));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), MutableSet.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableSet.class, MutableSet.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), MutableSet.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(MutableSet.class, MutableSet.of(1, 2, 3, 4, 5));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), MutableSet.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(MutableSet.class, MutableSet.ofAll(UnifiedSet.newSetWith(1, 2, 3, 4, 5)));
        Assert.assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4, 5), MutableSet.fromStream(Stream.of(1, 2, 3, 4, 5)));
        Verify.assertInstanceOf(MutableSet.class, MutableSet.fromStream(Stream.of(1, 2, 3, 4, 5)));
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 5; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(UnifiedSet.newSet(interval), MutableSet.ofAll(interval));
            Stream<Integer> stream = IntStream.rangeClosed(1, i).boxed();
            Verify.assertEqualsAndHashCode(UnifiedSet.newSet(interval), MutableSet.fromStream(stream));
        }
    }

    @Test
    public void ofInitialCapacity()
    {
        MutableSet<String> set1 = MutableSet.ofInitialCapacity(0);
        this.assertPresizedSetSizeEquals(0, (UnifiedSet<String>) set1);

        MutableSet<String> set2 = MutableSet.ofInitialCapacity(5);
        this.assertPresizedSetSizeEquals(5, (UnifiedSet<String>) set2);

        MutableSet<String> set3 = MutableSet.ofInitialCapacity(20);
        this.assertPresizedSetSizeEquals(20, (UnifiedSet<String>) set3);

        MutableSet<String> set4 = MutableSet.ofInitialCapacity(60);
        this.assertPresizedSetSizeEquals(60, (UnifiedSet<String>) set4);

        MutableSet<String> set5 = MutableSet.ofInitialCapacity(64);
        this.assertPresizedSetSizeEquals(60, (UnifiedSet<String>) set5);

        MutableSet<String> set6 = MutableSet.ofInitialCapacity(65);
        this.assertPresizedSetSizeEquals(65, (UnifiedSet<String>) set6);

        Verify.assertThrows(IllegalArgumentException.class, () -> MutableSet.ofInitialCapacity(-12));
    }

    private void assertPresizedSetSizeEquals(int initialCapacity, UnifiedSet<String> set)
    {
        try
        {
            Field tableField = UnifiedSet.class.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] table = (Object[]) tableField.get(set);

            int size = (int) Math.ceil(initialCapacity / 0.75f);
            int capacity = 1;
            while (capacity < size)
            {
                capacity <<= 1;
            }
            Assert.assertEquals(capacity, table.length);
        }
        catch (SecurityException ignored)
        {
            Assert.fail("Unable to modify the visibility of the field 'table' on UnifiedSet");
        }
        catch (NoSuchFieldException ignored)
        {
            Assert.fail("No field named 'table' in UnifiedSet");
        }
        catch (IllegalAccessException ignored)
        {
            Assert.fail("No access to the field 'table' in UnifiedSet");
        }
    }
}
