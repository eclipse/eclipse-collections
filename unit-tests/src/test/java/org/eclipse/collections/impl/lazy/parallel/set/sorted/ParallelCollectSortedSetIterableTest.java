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

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.lazy.parallel.list.ParallelListIterableTestCase;
import org.eclipse.collections.impl.list.mutable.FastList;

public class ParallelCollectSortedSetIterableTest extends ParallelListIterableTestCase
{
    @Override
    protected ParallelListIterable<Integer> classUnderTest()
    {
        return this.newWith(44, 43, 42, 41, 33, 32, 31, 22, 21, 11);
    }

    @Override
    protected ParallelListIterable<Integer> newWith(Integer... littleElements)
    {
        return SortedSets.immutable.with(Comparators.reverseNaturalOrder(), littleElements)
                .asParallel(this.executorService, this.batchSize)
                .collect(i -> i / 10);
    }

    @Override
    protected MutableList<Integer> getExpected()
    {
        return FastList.newListWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
    }

    @Override
    protected ListIterable<Integer> getExpectedWith(Integer... littleElements)
    {
        return SortedSets.immutable.with(Comparators.reverseNaturalOrder(), littleElements)
                .collect(i -> i / 10);
    }
}
