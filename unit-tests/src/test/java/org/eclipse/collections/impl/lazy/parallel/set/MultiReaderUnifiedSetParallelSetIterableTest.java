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

import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.eclipse.collections.impl.set.mutable.MultiReaderUnifiedSet;
import org.eclipse.collections.impl.set.mutable.SetAdapter;

public class MultiReaderUnifiedSetParallelSetIterableTest extends ParallelUnsortedSetIterableTestCase
{
    @Override
    protected ParallelUnsortedSetIterable<Integer> classUnderTest()
    {
        return this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
    }

    @Override
    protected ParallelUnsortedSetIterable<Integer> newWith(Integer... littleElements)
    {
        return SetAdapter.adapt(MultiReaderUnifiedSet.newSetWith(littleElements)).asParallel(this.executorService, this.batchSize);
    }

    @Override
    protected MutableSet<Integer> getExpectedWith(Integer... littleElements)
    {
        return SetAdapter.adapt(MultiReaderUnifiedSet.newSetWith(littleElements));
    }
}
