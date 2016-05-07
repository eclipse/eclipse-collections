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

import org.junit.Test;

public abstract class NonParallelSortedSetIterableTestCase extends ParallelSortedSetIterableTestCase
{
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
