/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link TakeIterator}.
 */
public class TakeIteratorTest
{
    @Test
    public void iterator()
    {
        Interval list = Interval.oneTo(5);

        Iterator<Integer> iterator1 = new TakeIterator<>(list.iterator(), 2);
        assertElements(iterator1, 2);

        Iterator<Integer> iterator2 = new TakeIterator<>(list, 5);
        assertElements(iterator2, 5);

        Iterator<Integer> iterator3 = new TakeIterator<>(list, 10);
        assertElements(iterator3, 5);

        Iterator<Integer> iterator4 = new TakeIterator<>(list, 0);
        assertElements(iterator4, 0);

        Iterator<Integer> iterator5 = new TakeIterator<>(Lists.fixedSize.of(), 0);
        assertElements(iterator5, 0);
    }

    private static void assertElements(Iterator<Integer> iterator, int count)
    {
        for (int i = 0; i < count; i++)
        {
            Assert.assertTrue(iterator.hasNext());
            Assert.assertEquals(Integer.valueOf(i + 1), iterator.next());
        }
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void remove()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> new TakeIterator<>(Lists.fixedSize.<Integer>of(), 0).remove());
    }

    @Test
    public void noSuchElementException()
    {
        Verify.assertThrows(NoSuchElementException.class, () -> new TakeIterator<>(Lists.fixedSize.<Integer>of(), 0).next());

        Verify.assertThrows(NoSuchElementException.class, () -> new TakeIterator<>(Lists.fixedSize.of(1, 2, 3), 0).next());
    }
}
