/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import org.eclipse.collections.api.InternalIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.math.SumProcedure;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectIterableTest extends AbstractLazyIterableTestCase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectIterableTest.class);

    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.select(FastList.newListWith(elements), ignored -> true);
    }

    @Test
    public void forEach()
    {
        InternalIterable<Integer> select = new SelectIterable<>(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        select.forEach(new SumProcedure<>(sum));
        Assert.assertEquals(10, sum.getValue().intValue());
    }

    @Test
    public void forEachWithIndex()
    {
        InternalIterable<Integer> select = new SelectIterable<>(Interval.oneTo(5), Predicates.lessThan(2).or(Predicates.greaterThan(3)));
        Sum sum = new IntegerSum(0);
        select.forEachWithIndex((object, index) -> {
            sum.add(object);
            sum.add(index);

            LOGGER.info("value={} index={}", object, index);
        });
        Assert.assertEquals(13, sum.getValue().intValue());
    }

    @Override
    @Test
    public void iterator()
    {
        InternalIterable<Integer> select = new SelectIterable<>(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        for (Integer each : select)
        {
            sum.add(each);
        }
        Assert.assertEquals(10, sum.getValue().intValue());
    }

    @Test
    public void forEachWith()
    {
        InternalIterable<Integer> select = new SelectIterable<>(Interval.oneTo(5), Predicates.lessThan(5));
        Sum sum = new IntegerSum(0);
        select.forEachWith((each, aSum) -> aSum.add(each), sum);
        Assert.assertEquals(10, sum.getValue().intValue());
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        SelectIterable<Integer> iterable = new SelectIterable<>(FastList.newListWith(5, 3, 2, 2, 4, 1, 3, 1, 5), Predicates.lessThan(5));
        Assert.assertEquals(
                FastList.newListWith(3, 2, 4, 1),
                iterable.distinct().toList());
    }
}
