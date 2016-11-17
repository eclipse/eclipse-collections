/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.bag;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.ParallelBag;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.lazy.parallel.ParallelIterableTestCase;
import org.junit.Assert;
import org.junit.Test;

public abstract class ParallelBagTestCase extends ParallelIterableTestCase
{
    @Override
    protected abstract ParallelBag<Integer> classUnderTest();

    @Override
    protected abstract ParallelBag<Integer> newWith(Integer... littleElements);

    @Override
    protected MutableBag<Integer> getExpected()
    {
        return HashBag.newBagWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
    }

    @Override
    protected MutableBag<Integer> getExpectedWith(Integer... littleElements)
    {
        return HashBag.newBagWith(littleElements);
    }

    @Override
    protected boolean isOrdered()
    {
        return false;
    }

    @Override
    protected boolean isUnique()
    {
        return false;
    }

    @Test
    public void forEachWithOccurrences()
    {
        MutableBag<Integer> actual = HashBag.<Integer>newBag().asSynchronized();
        this.classUnderTest().forEachWithOccurrences(actual::addOccurrences);
        Assert.assertEquals(this.getExpected().toBag(), actual);
    }

    @Override
    @Test
    public void forEach_executionException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void collect_executionException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void anySatisfy_executionException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void allSatisfy_executionException()
    {
        // Not applicable
    }

    @Override
    @Test
    public void detect_executionException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void forEach_interruptedException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void anySatisfy_interruptedException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void allSatisfy_interruptedException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void detect_interruptedException()
    {
        // Not applicable in serial
    }

    @Override
    @Test
    public void toString_interruptedException()
    {
        // Not applicable in serial
    }
}
