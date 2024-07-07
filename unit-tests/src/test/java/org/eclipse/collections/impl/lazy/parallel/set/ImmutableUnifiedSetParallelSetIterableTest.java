/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.ParallelUnsortedSetIterable;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class ImmutableUnifiedSetParallelSetIterableTest extends ParallelUnsortedSetIterableTestCase
{
    @Override
    protected ParallelUnsortedSetIterable<Integer> classUnderTest()
    {
        return this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
    }

    @Override
    protected ParallelUnsortedSetIterable<Integer> newWith(Integer... littleElements)
    {
        return Sets.immutable.with(littleElements).asParallel(this.executorService, this.batchSize);
    }

    @Override
    protected MutableSet<Integer> getExpectedWith(Integer... littleElements)
    {
        return Sets.immutable.with(littleElements).toSet();
    }

    @Test
    public void asParallel_small_batch()
    {
        assertThrows(IllegalArgumentException.class, () -> Sets.immutable.with(1, 2, 3, 4).asParallel(this.executorService, 0));
    }

    @Test
    public void asParallel_null_executorService()
    {
        assertThrows(NullPointerException.class, () -> Sets.immutable.with(1, 2, 3, 4).asParallel(null, 2));
    }
}
