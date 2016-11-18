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
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.math.SumProcedure;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DropIterableTest extends AbstractLazyIterableTestCase
{
    private DropIterable<Integer> dropIterable;
    private DropIterable<Integer> emptyListDropIterable;
    private DropIterable<Integer> zeroCountDropIterable;
    private DropIterable<Integer> nearCountDropIterable;
    private DropIterable<Integer> sameCountDropIterable;
    private DropIterable<Integer> higherCountDropIterable;

    @Before
    public void setUp()
    {
        this.dropIterable = new DropIterable<>(Interval.oneTo(5), 2);
        this.emptyListDropIterable = new DropIterable<>(FastList.newList(), 2);
        this.zeroCountDropIterable = new DropIterable<>(Interval.oneTo(5), 0);
        this.nearCountDropIterable = new DropIterable<>(Interval.oneTo(5), 4);
        this.sameCountDropIterable = new DropIterable<>(Interval.oneTo(5), 5);
        this.higherCountDropIterable = new DropIterable<>(Interval.oneTo(5), 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negative_throws()
    {
        new DropIterable<>(Interval.oneTo(5), -1);
    }

    @Test
    public void basic()
    {
        Assert.assertEquals(3, this.dropIterable.size());
        Assert.assertEquals(FastList.newListWith(3, 4, 5), this.dropIterable.toList());

        Assert.assertEquals(0, this.emptyListDropIterable.size());
        Assert.assertEquals(5, this.zeroCountDropIterable.size());
        Assert.assertEquals(1, this.nearCountDropIterable.size());
        Assert.assertEquals(0, this.sameCountDropIterable.size());
        Assert.assertEquals(0, this.higherCountDropIterable.size());
    }

    @Test
    public void forEach()
    {
        Sum sum1 = new IntegerSum(0);
        this.dropIterable.forEach(new SumProcedure<>(sum1));
        Assert.assertEquals(12, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        this.emptyListDropIterable.forEach(new SumProcedure<>(sum2));
        Assert.assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        this.zeroCountDropIterable.forEach(new SumProcedure<>(sum3));
        Assert.assertEquals(15, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        this.nearCountDropIterable.forEach(new SumProcedure<>(sum5));
        Assert.assertEquals(5, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        this.sameCountDropIterable.forEach(new SumProcedure<>(sum6));
        Assert.assertEquals(0, sum6.getValue().intValue());

        Sum sum7 = new IntegerSum(0);
        this.higherCountDropIterable.forEach(new SumProcedure<>(sum7));
        Assert.assertEquals(0, sum7.getValue().intValue());
    }

    @Test
    public void forEachWithIndex()
    {
        Sum sum = new IntegerSum(0);
        FastList<Integer> indices = FastList.newList(5);
        ObjectIntProcedure<Integer> indexRecordingAndSumProcedure = (each, index) -> {
            indices.add(index);
            sum.add(each);
        };

        this.dropIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        Assert.assertEquals(FastList.newListWith(0, 1, 2), indices);
        Assert.assertEquals(12, sum.getValue().intValue());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.emptyListDropIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        Assert.assertEquals(0, indices.size());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.zeroCountDropIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        Assert.assertEquals(FastList.newListWith(0, 1, 2, 3, 4), indices);
        Assert.assertEquals(15, sum.getValue().intValue());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.nearCountDropIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        Assert.assertEquals(FastList.newListWith(0), indices);
        Assert.assertEquals(5, sum.getValue().intValue());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.sameCountDropIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        Assert.assertEquals(0, indices.size());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.higherCountDropIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        Assert.assertEquals(0, indices.size());
    }

    @Test
    public void forEachWith()
    {
        Procedure2<Integer, Sum> sumAdditionProcedure = (each, sum) -> sum.add(each);

        Sum sum1 = new IntegerSum(0);
        this.dropIterable.forEachWith(sumAdditionProcedure, sum1);
        Assert.assertEquals(12, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        this.emptyListDropIterable.forEachWith(sumAdditionProcedure, sum2);
        Assert.assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        this.zeroCountDropIterable.forEachWith(sumAdditionProcedure, sum3);
        Assert.assertEquals(15, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        this.nearCountDropIterable.forEachWith(sumAdditionProcedure, sum5);
        Assert.assertEquals(5, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        this.sameCountDropIterable.forEachWith(sumAdditionProcedure, sum6);
        Assert.assertEquals(0, sum6.getValue().intValue());

        Sum sum7 = new IntegerSum(0);
        this.higherCountDropIterable.forEachWith(sumAdditionProcedure, sum7);
        Assert.assertEquals(0, sum7.getValue().intValue());
    }

    @Override
    @Test
    public void iterator()
    {
        Sum sum1 = new IntegerSum(0);
        for (Integer each : this.dropIterable)
        {
            sum1.add(each);
        }
        Assert.assertEquals(12, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        for (Integer each : this.emptyListDropIterable)
        {
            sum2.add(each);
        }
        Assert.assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        for (Integer each : this.zeroCountDropIterable)
        {
            sum3.add(each);
        }
        Assert.assertEquals(15, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        for (Integer each : this.nearCountDropIterable)
        {
            sum5.add(each);
        }
        Assert.assertEquals(5, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        for (Integer each : this.sameCountDropIterable)
        {
            sum6.add(each);
        }
        Assert.assertEquals(0, sum6.getValue().intValue());

        Sum sum7 = new IntegerSum(0);
        for (Integer each : this.higherCountDropIterable)
        {
            sum7.add(each);
        }
        Assert.assertEquals(0, sum7.getValue().intValue());
    }

    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.drop(FastList.newListWith(elements), 0);
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        Assert.assertEquals(
                FastList.newListWith(2, 3, 4, 5),
                new DropIterable<>(FastList.newListWith(1, 1, 2, 3, 3, 3, 4, 5), 2).distinct().toList());
    }
}
