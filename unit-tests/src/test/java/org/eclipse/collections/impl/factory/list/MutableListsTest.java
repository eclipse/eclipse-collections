/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.list;

import java.lang.reflect.Field;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class MutableListsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(FastList.newList(), MutableList.of());
        Assert.assertEquals(FastList.newList(), MutableList.empty());
        Verify.assertInstanceOf(MutableList.class, MutableList.of());
        Verify.assertInstanceOf(MutableList.class, MutableList.empty());
        Assert.assertEquals(FastList.newListWith(1), MutableList.of(1));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1));
        Assert.assertEquals(FastList.newListWith(1, 2), MutableList.of(1, 2));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), MutableList.of(1, 2, 3));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4), MutableList.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3, 4));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5), MutableList.of(1, 2, 3, 4, 5));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3, 4, 5));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6), MutableList.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), MutableList.of(1, 2, 3, 4, 5, 6, 7));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8), MutableList.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3, 4, 5, 6, 7, 8));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9), MutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Assert.assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), MutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Verify.assertInstanceOf(MutableList.class, MutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), MutableList.ofAll(FastList.newListWith(1, 2, 3)));
        Verify.assertInstanceOf(MutableList.class, MutableList.ofAll(FastList.newListWith(1, 2, 3)));
        Assert.assertEquals(FastList.newListWith(1, 2, 3), MutableList.fromStream(Stream.of(1, 2, 3)));
        Verify.assertInstanceOf(MutableList.class, MutableList.fromStream(Stream.of(1, 2, 3)));
    }

    @Test
    public void ofAll()
    {
        for (int i = 1; i <= 11; i++)
        {
            Interval interval = Interval.oneTo(i);
            Verify.assertEqualsAndHashCode(interval, MutableList.ofAll(interval));
            Stream<Integer> stream = IntStream.rangeClosed(1, i).boxed();
            Verify.assertEqualsAndHashCode(interval, MutableList.fromStream(stream));
        }
    }

    @Test
    public void ofInitialCapacity()
    {
        MutableList<String> list1 = MutableList.ofInitialCapacity(0);
        this.assertPresizedListEquals(0, (FastList<String>) list1);

        MutableList<String> list2 = MutableList.ofInitialCapacity(5);
        this.assertPresizedListEquals(5, (FastList<String>) list2);

        MutableList<String> list3 = MutableList.ofInitialCapacity(20);
        this.assertPresizedListEquals(20, (FastList<String>) list3);

        MutableList<String> list4 = MutableList.ofInitialCapacity(60);
        this.assertPresizedListEquals(60, (FastList<String>) list4);

        MutableList<String> list5 = MutableList.ofInitialCapacity(64);
        this.assertPresizedListEquals(64, (FastList<String>) list5);

        MutableList<String> list6 = MutableList.ofInitialCapacity(65);
        this.assertPresizedListEquals(65, (FastList<String>) list6);

        Verify.assertThrows(IllegalArgumentException.class, () -> MutableList.ofInitialCapacity(-12));
    }

    private void assertPresizedListEquals(int initialCapacity, FastList<String> list)
    {
        try
        {
            Field itemsField = FastList.class.getDeclaredField("items");
            itemsField.setAccessible(true);
            Object[] items = (Object[]) itemsField.get(list);
            Assert.assertEquals(initialCapacity, items.length);
        }
        catch (SecurityException ignored)
        {
            Assert.fail("Unable to modify the visibility of the field 'items' on FastList");
        }
        catch (NoSuchFieldException ignored)
        {
            Assert.fail("No field named 'items' in FastList");
        }
        catch (IllegalAccessException ignored)
        {
            Assert.fail("No access to the field 'items' in FastList");
        }
    }

    @Test
    public void withNValues()
    {
        ImmutableList<Integer> expected =
                IntInterval.oneTo(10).collect(each -> new Integer(1));
        MutableList<Integer> mutable =
                MutableList.withNValues(10, () -> new Integer(1));
        Assert.assertEquals(expected, mutable);
    }
}
