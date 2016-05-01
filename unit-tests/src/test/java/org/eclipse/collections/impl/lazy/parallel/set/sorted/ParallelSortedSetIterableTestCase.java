/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set.sorted;

import org.eclipse.collections.api.set.sorted.ParallelSortedSetIterable;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.lazy.parallel.ParallelIterableTestCase;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

public abstract class ParallelSortedSetIterableTestCase extends ParallelIterableTestCase
{
    @Override
    protected abstract ParallelSortedSetIterable<Integer> classUnderTest();

    @Override
    protected SortedSetIterable<Integer> getExpected()
    {
        return TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder(), 4, 3, 2, 1);
    }

    @Override
    protected SortedSetIterable<Integer> getExpectedWith(Integer... littleElements)
    {
        return TreeSortedSet.newSetWith(Comparators.reverseNaturalOrder(), littleElements);
    }

    @Override
    protected boolean isOrdered()
    {
        return true;
    }

    @Override
    protected boolean isUnique()
    {
        return true;
    }
}
