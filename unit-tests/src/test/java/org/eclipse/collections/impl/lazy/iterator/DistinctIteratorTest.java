/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.factory.Lists;
import org.junit.Assert;
import org.junit.Test;

public class DistinctIteratorTest
{
    @Test(expected = UnsupportedOperationException.class)
    public void remove()
    {
        this.newDistinctIterator().remove();
    }

    private DistinctIterator<Integer> newDistinctIterator()
    {
        return new DistinctIterator<>(Lists.mutable.of(4, 1, 4, 10, 1, 100));
    }

    @Test
    public void iterator()
    {
        List<Integer> result = Lists.mutable.empty();
        DistinctIterator<Integer> iterator = this.newDistinctIterator();
        while (iterator.hasNext())
        {
            result.add(iterator.next());
        }
        Assert.assertEquals(Lists.mutable.of(4, 1, 10, 100), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void nextException()
    {
        Iterator<Integer> iterator = new DistinctIterator<>(Lists.mutable.empty());
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }
}
