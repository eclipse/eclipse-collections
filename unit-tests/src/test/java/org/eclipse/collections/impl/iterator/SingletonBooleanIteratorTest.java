/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.iterator;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class SingletonBooleanIteratorTest
{
    @Test
    public void hasNext()
    {
        SingletonBooleanIterator iterator = new SingletonBooleanIterator(false);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void next()
    {
        SingletonBooleanIterator iterator = new SingletonBooleanIterator(false);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(false, iterator.next());
        Assert.assertFalse(iterator.hasNext());
        try
        {
            iterator.next();
            Assert.fail("NoSuchElementException should have been thrown");
        }
        catch (NoSuchElementException e)
        {
            //Expected
        }
    }
}
