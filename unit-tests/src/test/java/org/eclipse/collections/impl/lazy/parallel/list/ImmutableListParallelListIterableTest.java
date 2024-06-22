/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.list;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class ImmutableListParallelListIterableTest extends ParallelListIterableTestCase
{
    @Override
    protected ParallelListIterable<Integer> classUnderTest()
    {
        return this.newWith(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
    }

    @Override
    protected ParallelListIterable<Integer> newWith(Integer... littleElements)
    {
        return Lists.immutable.with(littleElements).asParallel(this.executorService, this.batchSize);
    }

    @Test
    public void asParallel_small_batch()
    {
        assertThrows(IllegalArgumentException.class, () -> Lists.immutable.with(1, 2, 2, 3, 3, 3, 4, 4, 4, 4).asParallel(this.executorService, 0));
    }

    @Test
    public void asParallel_null_executorService()
    {
        assertThrows(NullPointerException.class, () -> Lists.immutable.with(1, 2, 2, 3, 3, 3, 4, 4, 4, 4).asParallel(null, 2));
    }
}
