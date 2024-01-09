/*
 * Copyright (c) 2022 Goldman Sachs and others.
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
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.junit.Assert;
import org.junit.Test;

public class TapIteratorTest
{
    @Test(expected = NoSuchElementException.class)
    public void nextIfDoesntHaveAnything()
    {
        new TapIterator<>(Lists.immutable.of(), object -> {
        }).next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeIsUnsupported()
    {
        new TapIterator<>(Lists.immutable.of().iterator(), object -> {
        }).remove();
    }

    @Test
    public void nextAfterEmptyIterable()
    {
        Object expected = new Object();
        Iterator<Object> iterator = new TapIterator<>(
                Lists.fixedSize.of(expected), object -> { });
        Assert.assertSame(expected, iterator.next());
    }

    @Test
    public void iterator()
    {
        Iterator<AtomicInteger> iterator =
                new TapIterator<>(
                        Lists.mutable.of(
                                new AtomicInteger(1),
                                new AtomicInteger(2),
                                new AtomicInteger(3)),
                        each -> each.set(each.get() * 10));

        MutableIntList intList = IntLists.mutable.empty();
        while (iterator.hasNext())
        {
            intList.add(iterator.next().get());
        }
        Assert.assertEquals(IntLists.mutable.of(10, 20, 30), intList);
    }
}
