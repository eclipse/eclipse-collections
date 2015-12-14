/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set;

import org.eclipse.collections.api.set.sorted.ParallelSortedSetIterable;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.lazy.parallel.set.sorted.ParallelSortedSetIterableTestCase;

public class ImmutableTreeSetParallelSetIterableTest extends ParallelSortedSetIterableTestCase
{
    @Override
    protected ParallelSortedSetIterable<Integer> classUnderTest()
    {
        return this.newWith(4, 3, 2, 1);
    }

    @Override
    protected ParallelSortedSetIterable<Integer> newWith(Integer... littleElements)
    {
        return SortedSets.immutable.with(Comparators.reverseNaturalOrder(), littleElements).asParallel(this.executorService, this.batchSize);
    }

    @Override
    protected SortedSetIterable<Integer> getExpectedWith(Integer... littleElements)
    {
        return SortedSets.immutable.with(Comparators.reverseNaturalOrder(), littleElements);
    }
}
