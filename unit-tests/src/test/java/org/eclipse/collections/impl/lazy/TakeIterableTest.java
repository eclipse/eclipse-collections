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

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.procedure.CountProcedure;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TakeIterableTest extends AbstractLazyIterableTestCase
{
    private TakeIterable<Integer> takeIterable;
    private TakeIterable<Integer> emptyListTakeIterable;
    private TakeIterable<Integer> zeroCountTakeIterable;
    private TakeIterable<Integer> sameCountTakeIterable;
    private TakeIterable<Integer> higherCountTakeIterable;

    @Before
    public void setUp()
    {
        this.takeIterable = new TakeIterable<>(Interval.oneTo(5), 2);
        this.emptyListTakeIterable = new TakeIterable<>(FastList.newList(), 2);
        this.zeroCountTakeIterable = new TakeIterable<>(Interval.oneTo(5), 0);
        this.sameCountTakeIterable = new TakeIterable<>(Interval.oneTo(5), 5);
        this.higherCountTakeIterable = new TakeIterable<>(Interval.oneTo(5), 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negative_throws()
    {
        new TakeIterable<>(Interval.oneTo(5), -1);
    }

    @Test
    public void basic()
    {
        Assert.assertEquals(2, this.takeIterable.size());
        Assert.assertEquals(FastList.newListWith(1, 2), this.takeIterable.toList());

        Assert.assertEquals(0, this.emptyListTakeIterable.size());
        Assert.assertEquals(0, this.zeroCountTakeIterable.size());
        Assert.assertEquals(5, this.higherCountTakeIterable.size());
        Assert.assertEquals(5, this.sameCountTakeIterable.size());
    }

    @Test
    public void forEach()
    {
        CountProcedure<Integer> cb1 = new CountProcedure<>();
        this.takeIterable.forEach(cb1);
        Assert.assertEquals(2, cb1.getCount());

        CountProcedure<Integer> cb2 = new CountProcedure<>();
        this.emptyListTakeIterable.forEach(cb2);
        Assert.assertEquals(0, cb2.getCount());

        CountProcedure<Integer> cb3 = new CountProcedure<>();
        this.zeroCountTakeIterable.forEach(cb3);
        Assert.assertEquals(0, cb3.getCount());

        CountProcedure<Integer> cb5 = new CountProcedure<>();
        this.sameCountTakeIterable.forEach(cb5);
        Assert.assertEquals(5, cb5.getCount());

        CountProcedure<Integer> cb6 = new CountProcedure<>();
        this.higherCountTakeIterable.forEach(cb6);
        Assert.assertEquals(5, cb6.getCount());
    }

    @Test
    public void forEachWithIndex()
    {
        FastList<Integer> indices = FastList.newList(5);
        ObjectIntProcedure<Integer> indexRecordingProcedure = (each, index) -> indices.add(index);

        this.takeIterable.forEachWithIndex(indexRecordingProcedure);
        Assert.assertEquals(FastList.newListWith(0, 1), indices);

        indices.clear();
        this.emptyListTakeIterable.forEachWithIndex(indexRecordingProcedure);
        Verify.assertSize(0, indices);

        indices.clear();
        this.zeroCountTakeIterable.forEachWithIndex(indexRecordingProcedure);
        Verify.assertSize(0, indices);

        indices.clear();
        this.sameCountTakeIterable.forEachWithIndex(indexRecordingProcedure);
        Assert.assertEquals(FastList.newListWith(0, 1, 2, 3, 4), indices);

        indices.clear();
        this.higherCountTakeIterable.forEachWithIndex(indexRecordingProcedure);
        Assert.assertEquals(FastList.newListWith(0, 1, 2, 3, 4), indices);
    }

    @Test
    public void forEachWith()
    {
        Procedure2<Integer, Sum> sumAdditionProcedure = (each, sum) -> sum.add(each);

        Sum sum1 = new IntegerSum(0);
        this.takeIterable.forEachWith(sumAdditionProcedure, sum1);
        Assert.assertEquals(3, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        this.emptyListTakeIterable.forEachWith(sumAdditionProcedure, sum2);
        Assert.assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        this.zeroCountTakeIterable.forEachWith(sumAdditionProcedure, sum3);
        Assert.assertEquals(0, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        this.sameCountTakeIterable.forEachWith(sumAdditionProcedure, sum5);
        Assert.assertEquals(15, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        this.higherCountTakeIterable.forEachWith(sumAdditionProcedure, sum6);
        Assert.assertEquals(15, sum6.getValue().intValue());
    }

    @Override
    @Test
    public void iterator()
    {
        Sum sum1 = new IntegerSum(0);
        for (Integer each : this.takeIterable)
        {
            sum1.add(each);
        }
        Assert.assertEquals(3, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        for (Integer each : this.emptyListTakeIterable)
        {
            sum2.add(each);
        }
        Assert.assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        for (Integer each : this.zeroCountTakeIterable)
        {
            sum3.add(each);
        }
        Assert.assertEquals(0, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        for (Integer each : this.sameCountTakeIterable)
        {
            sum5.add(each);
        }
        Assert.assertEquals(15, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        for (Integer each : this.higherCountTakeIterable)
        {
            sum6.add(each);
        }
        Assert.assertEquals(15, sum6.getValue().intValue());
    }

    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.take(FastList.newListWith(elements), elements.length);
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        Assert.assertEquals(
                FastList.newListWith(3, 2, 4, 1),
                new TakeIterable<>(FastList.newListWith(3, 2, 2, 4, 1, 3, 1, 5), 7).distinct().toList());
    }
}
