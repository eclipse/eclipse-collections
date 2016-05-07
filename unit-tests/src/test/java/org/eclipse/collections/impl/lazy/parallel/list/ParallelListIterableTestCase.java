/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.list;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.impl.lazy.parallel.ParallelIterableTestCase;
import org.eclipse.collections.impl.list.mutable.FastList;

public abstract class ParallelListIterableTestCase extends ParallelIterableTestCase
{
    @Override
    protected abstract ParallelListIterable<Integer> classUnderTest();

    @Override
    protected abstract ParallelListIterable<Integer> newWith(Integer... littleElements);

    @Override
    protected MutableList<Integer> getExpected()
    {
        return FastList.newListWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
    }

    @Override
    protected ListIterable<Integer> getExpectedWith(Integer... littleElements)
    {
        return FastList.newListWith(littleElements);
    }

    @Override
    protected boolean isOrdered()
    {
        return true;
    }

    @Override
    protected boolean isUnique()
    {
        return false;
    }
}
