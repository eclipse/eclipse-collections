/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmptyIteratorTest
{
    private EmptyIterator<Object> emptyIterator;

    @Before
    public void setUp()
    {
        this.emptyIterator = EmptyIterator.getInstance();
    }

    @Test
    public void hasPrevious()
    {
        Assert.assertFalse(this.emptyIterator.hasPrevious());
    }

    @Test
    public void previous()
    {
        Assert.assertThrows(NoSuchElementException.class, this.emptyIterator::previous);
    }

    @Test
    public void previousIndex()
    {
        Assert.assertEquals(-1, this.emptyIterator.previousIndex());
    }

    @Test
    public void set()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.emptyIterator.set(1));
    }

    @Test
    public void add()
    {
        Assert.assertThrows(UnsupportedOperationException.class, () -> this.emptyIterator.add(1));
    }

    @Test
    public void nextIndex()
    {
        Assert.assertEquals(0, (long) this.emptyIterator.nextIndex());
    }
}
