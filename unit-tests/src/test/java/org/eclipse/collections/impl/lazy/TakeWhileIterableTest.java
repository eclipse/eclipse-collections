/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.CountProcedure;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TakeWhileIterableTest extends AbstractLazyIterableTestCase
{
    private TakeWhileIterable<Integer> takeWhileIterable;
    private TakeWhileIterable<Integer> emptyListTakeWhileIterable;
    private TakeWhileIterable<Integer> alwaysFalseTakeWhileIterable;
    private TakeWhileIterable<Integer> alwaysTrueTakeWhileIterable;

    @BeforeEach
    public void setUp()
    {
        this.takeWhileIterable = new TakeWhileIterable<>(Interval.oneTo(5), each -> each <= 2);
        this.emptyListTakeWhileIterable = new TakeWhileIterable<>(FastList.newList(), each -> each <= 2);
        this.alwaysFalseTakeWhileIterable = new TakeWhileIterable<>(Interval.oneTo(5), Predicates.alwaysFalse());
        this.alwaysTrueTakeWhileIterable = new TakeWhileIterable<>(Interval.oneTo(5), Predicates.alwaysTrue());
    }

    @Test
    public void basic()
    {
        assertEquals(2, this.takeWhileIterable.size());
        assertEquals(FastList.newListWith(1, 2), this.takeWhileIterable.toList());

        assertEquals(0, this.emptyListTakeWhileIterable.size());
        assertEquals(0, this.alwaysFalseTakeWhileIterable.size());
        assertEquals(5, this.alwaysTrueTakeWhileIterable.size());
    }

    @Test
    public void forEach()
    {
        CountProcedure<Integer> cb1 = new CountProcedure<>();
        this.takeWhileIterable.forEach(cb1);
        assertEquals(2, cb1.getCount());

        CountProcedure<Integer> cb2 = new CountProcedure<>();
        this.emptyListTakeWhileIterable.forEach(cb2);
        assertEquals(0, cb2.getCount());

        CountProcedure<Integer> cb3 = new CountProcedure<>();
        this.alwaysFalseTakeWhileIterable.forEach(cb3);
        assertEquals(0, cb3.getCount());

        CountProcedure<Integer> cb5 = new CountProcedure<>();
        this.alwaysTrueTakeWhileIterable.forEach(cb5);
        assertEquals(5, cb5.getCount());
    }

    @Test
    public void forEachWithIndex()
    {
        FastList<Integer> indices = FastList.newList(5);
        ObjectIntProcedure<Integer> indexRecordingProcedure = (each, index) -> indices.add(index);

        this.takeWhileIterable.forEachWithIndex(indexRecordingProcedure);
        assertEquals(FastList.newListWith(0, 1), indices);

        indices.clear();
        this.emptyListTakeWhileIterable.forEachWithIndex(indexRecordingProcedure);
        Verify.assertSize(0, indices);

        indices.clear();
        this.alwaysFalseTakeWhileIterable.forEachWithIndex(indexRecordingProcedure);
        Verify.assertSize(0, indices);

        indices.clear();
        this.alwaysTrueTakeWhileIterable.forEachWithIndex(indexRecordingProcedure);
        assertEquals(FastList.newListWith(0, 1, 2, 3, 4), indices);
    }

    @Test
    public void forEachWith()
    {
        Procedure2<Integer, Sum> sumAdditionProcedure = (each, sum) -> sum.add(each);

        Sum sum1 = new IntegerSum(0);
        this.takeWhileIterable.forEachWith(sumAdditionProcedure, sum1);
        assertEquals(3, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        this.emptyListTakeWhileIterable.forEachWith(sumAdditionProcedure, sum2);
        assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        this.alwaysFalseTakeWhileIterable.forEachWith(sumAdditionProcedure, sum3);
        assertEquals(0, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        this.alwaysTrueTakeWhileIterable.forEachWith(sumAdditionProcedure, sum5);
        assertEquals(15, sum5.getValue().intValue());
    }

    @Override
    @Test
    public void iterator()
    {
        Sum sum1 = new IntegerSum(0);
        for (Integer each : this.takeWhileIterable)
        {
            sum1.add(each);
        }
        assertEquals(3, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        for (Integer each : this.emptyListTakeWhileIterable)
        {
            sum2.add(each);
        }
        assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        for (Integer each : this.alwaysFalseTakeWhileIterable)
        {
            sum3.add(each);
        }
        assertEquals(0, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        for (Integer each : this.alwaysTrueTakeWhileIterable)
        {
            sum5.add(each);
        }
        assertEquals(15, sum5.getValue().intValue());
    }

    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.takeWhile(FastList.newListWith(elements), Predicates.alwaysTrue());
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        assertEquals(
                FastList.newListWith(3, 2, 4, 1),
                new TakeWhileIterable<>(FastList.newListWith(3, 2, 2, 4, 1, 3, 1, 5), each -> each < 5).distinct().toList());
    }
}
