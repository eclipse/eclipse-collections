/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.lazy.parallel.ParallelIterableTestCase;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

public abstract class ParallelUnsortedSetIterableTestCase extends ParallelIterableTestCase
{
    @Override
    protected abstract ParallelUnsortedSetIterable<Integer> classUnderTest();

    @Override
    protected abstract ParallelUnsortedSetIterable<Integer> newWith(Integer... littleElements);

    @Override
    protected MutableSet<Integer> getExpected()
    {
        return UnifiedSet.newSetWith(1, 2, 3, 4);
    }

    @Override
    protected MutableSet<Integer> getExpectedWith(Integer... littleElements)
    {
        return UnifiedSet.newSetWith(littleElements);
    }

    @Override
    protected RichIterable<Integer> getExpectedCollect()
    {
        return HashBag.newBagWith(1, 2, 3, 4);
    }

    @Override
    protected boolean isOrdered()
    {
        return false;
    }

    @Override
    protected boolean isUnique()
    {
        return true;
    }
}
