/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class UnifiedSetAsPoolTest
{
    private final UnifiedSet<Integer> staticPool = UnifiedSet.newSet();

    @Test
    public void getReturnsNullIfObjectIsNotPooled()
    {
        Assert.assertNull(this.staticPool.get(1));
    }

    @Test
    public void getReturnsOriginalObjectForIdenticalObject()
    {
        Integer firstPooledObject = 1;
        this.staticPool.put(firstPooledObject);
        Assert.assertSame(firstPooledObject, this.staticPool.get(firstPooledObject));
    }

    @Test
    public void getReturnsPooledObjectForEqualObject()
    {
        UnifiedSet<AlwaysEqual> pool = UnifiedSet.newSet();
        AlwaysEqual firstObject = new AlwaysEqual();
        pool.put(firstObject);
        AlwaysEqual equalObject = new AlwaysEqual();  // deliberate new instance
        Assert.assertSame(firstObject, pool.get(equalObject));
    }

    private static final class AlwaysEqual
    {
        @Override
        public boolean equals(Object obj)
        {
            return obj != null;
        }

        @Override
        public int hashCode()
        {
            return 0;
        }
    }

    @Test
    public void putReturnsPassedInObject()
    {
        Integer firstObject = 1;
        Object returnedObject = this.staticPool.put(firstObject);
        Assert.assertSame(returnedObject, firstObject);
    }

    @Test
    public void putAndGetReturnOriginalPooledObjectForEqualObject()
    {
        AlwaysEqual firstObject = new AlwaysEqual();
        UnifiedSet<AlwaysEqual> pool = UnifiedSet.newSet();
        pool.put(firstObject);
        AlwaysEqual secondObject = new AlwaysEqual();  // deliberate new instance
        Object returnedObject = pool.put(secondObject);

        Assert.assertSame(returnedObject, firstObject);
        Assert.assertSame(firstObject, pool.get(secondObject));
    }

    @Test
    public void removeFromPool()
    {
        Integer firstObject = 1;

        this.staticPool.put(firstObject);
        Integer returnedObject = this.staticPool.removeFromPool(firstObject);

        Assert.assertSame(returnedObject, firstObject);
        Verify.assertEmpty(this.staticPool);
    }
}
