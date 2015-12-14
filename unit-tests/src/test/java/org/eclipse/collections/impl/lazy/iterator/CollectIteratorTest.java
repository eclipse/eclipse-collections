/*
 * Copyright (c) 2015 Goldman Sachs.
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
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;

public class CollectIteratorTest
{
    @Test
    public void iterator()
    {
        Iterator<String> iterator = new CollectIterator<>(iList(Boolean.TRUE), String::valueOf);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("true", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorWithFunctionName()
    {
        Iterator<String> iterator = new CollectIterator<>(iList(Boolean.TRUE), String::valueOf);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("true", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorWithFunctionNameAndIterator()
    {
        Iterator<String> iterator = new CollectIterator<>(iList(Boolean.TRUE).iterator(), String::valueOf);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("true", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void noSuchElementException()
    {
        new CollectIterator<>(Lists.mutable.<Boolean>of(), String::valueOf).next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove()
    {
        new CollectIterator<>(Lists.mutable.<Boolean>of(), String::valueOf).remove();
    }
}
