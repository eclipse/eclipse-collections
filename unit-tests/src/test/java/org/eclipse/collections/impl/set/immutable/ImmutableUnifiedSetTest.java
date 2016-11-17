/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.math.SumProcedure;
import org.eclipse.collections.impl.parallel.BatchIterable;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ImmutableUnifiedSet}.
 */
public class ImmutableUnifiedSetTest extends AbstractImmutableUnifiedSetTestCase
{
    @Override
    public ImmutableSet<Integer> newSet(Integer... elements)
    {
        return ImmutableUnifiedSet.newSetWith(elements);
    }

    @Override
    public ImmutableSet<Integer> newSetWith(int one, int two)
    {
        return ImmutableUnifiedSet.newSetWith(one, two);
    }

    @Override
    public ImmutableSet<Integer> newSetWith(int one, int two, int three)
    {
        return ImmutableUnifiedSet.newSetWith(one, two, three);
    }

    @Override
    public ImmutableSet<Integer> newSetWith(int... littleElements)
    {
        Integer[] bigElements = new Integer[littleElements.length];
        for (int i = 0; i < littleElements.length; i++)
        {
            bigElements[i] = littleElements[i];
        }
        return ImmutableUnifiedSet.newSetWith(bigElements);
    }

    @Override
    @Test
    public void newCollection()
    {
        super.newCollection();
        ImmutableSet<Integer> set = ImmutableUnifiedSet.newSet(UnifiedSet.newSet());
        Assert.assertTrue(set.isEmpty());
        Verify.assertSize(0, set);
    }

    @Test
    public void getBatchCount()
    {
        BatchIterable<Integer> integerBatchIterable = (BatchIterable<Integer>) this.newSet(1, 2, 3, 4, 5, 6);
        Assert.assertEquals(2, integerBatchIterable.getBatchCount(3));
    }

    @Test
    public void batchForEach()
    {
        Sum sum = new IntegerSum(0);
        BatchIterable<Integer> integerBatchIterable = (BatchIterable<Integer>) this.newSet(1, 2, 3, 4, 5);
        integerBatchIterable.batchForEach(new SumProcedure<>(sum), 0, 1);
        Assert.assertEquals(15, sum.getValue());
    }
}
