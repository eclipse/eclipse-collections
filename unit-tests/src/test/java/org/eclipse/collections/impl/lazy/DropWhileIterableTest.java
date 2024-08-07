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
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.math.SumProcedure;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DropWhileIterableTest extends AbstractLazyIterableTestCase
{
    private DropWhileIterable<Integer> dropWhileIterable;
    private DropWhileIterable<Integer> emptyListDropWhileIterable;
    private DropWhileIterable<Integer> alwaysFalseDropWhileIterable;
    private DropWhileIterable<Integer> mostlyFalseDropWhileIterable;
    private DropWhileIterable<Integer> alwaysTrueDropWhileIterable;

    @BeforeEach
    public void setUp()
    {
        this.dropWhileIterable = new DropWhileIterable<>(Interval.oneTo(5), each -> each <= 2);
        this.emptyListDropWhileIterable = new DropWhileIterable<>(FastList.newList(), each -> each <= 2);
        this.alwaysFalseDropWhileIterable = new DropWhileIterable<>(Interval.oneTo(5), Predicates.alwaysFalse());
        this.mostlyFalseDropWhileIterable = new DropWhileIterable<>(Interval.oneTo(5), each -> each <= 4);
        this.alwaysTrueDropWhileIterable = new DropWhileIterable<>(Interval.oneTo(5), Predicates.alwaysTrue());
    }

    @Test
    public void basic()
    {
        assertEquals(3, this.dropWhileIterable.size());
        assertEquals(FastList.newListWith(3, 4, 5), this.dropWhileIterable.toList());

        assertEquals(0, this.emptyListDropWhileIterable.size());
        assertEquals(5, this.alwaysFalseDropWhileIterable.size());
        assertEquals(1, this.mostlyFalseDropWhileIterable.size());
        assertEquals(0, this.alwaysTrueDropWhileIterable.size());
    }

    @Test
    public void forEach()
    {
        Sum sum1 = new IntegerSum(0);
        this.dropWhileIterable.forEach(new SumProcedure<>(sum1));
        assertEquals(12, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        this.emptyListDropWhileIterable.forEach(new SumProcedure<>(sum2));
        assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        this.alwaysFalseDropWhileIterable.forEach(new SumProcedure<>(sum3));
        assertEquals(15, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        this.mostlyFalseDropWhileIterable.forEach(new SumProcedure<>(sum5));
        assertEquals(5, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        this.alwaysTrueDropWhileIterable.forEach(new SumProcedure<>(sum6));
        assertEquals(0, sum6.getValue().intValue());
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

        this.dropWhileIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        assertEquals(FastList.newListWith(0, 1, 2), indices);
        assertEquals(12, sum.getValue().intValue());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.emptyListDropWhileIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        assertEquals(0, indices.size());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.alwaysFalseDropWhileIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        assertEquals(FastList.newListWith(0, 1, 2, 3, 4), indices);
        assertEquals(15, sum.getValue().intValue());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.mostlyFalseDropWhileIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        assertEquals(FastList.newListWith(0), indices);
        assertEquals(5, sum.getValue().intValue());

        indices.clear();
        sum.add(sum.getValue().intValue() * -1);
        this.alwaysTrueDropWhileIterable.forEachWithIndex(indexRecordingAndSumProcedure);
        assertEquals(0, indices.size());
    }

    @Test
    public void forEachWith()
    {
        Procedure2<Integer, Sum> sumAdditionProcedure = (each, sum) -> sum.add(each);

        Sum sum1 = new IntegerSum(0);
        this.dropWhileIterable.forEachWith(sumAdditionProcedure, sum1);
        assertEquals(12, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        this.emptyListDropWhileIterable.forEachWith(sumAdditionProcedure, sum2);
        assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        this.alwaysFalseDropWhileIterable.forEachWith(sumAdditionProcedure, sum3);
        assertEquals(15, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        this.mostlyFalseDropWhileIterable.forEachWith(sumAdditionProcedure, sum5);
        assertEquals(5, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        this.alwaysTrueDropWhileIterable.forEachWith(sumAdditionProcedure, sum6);
        assertEquals(0, sum6.getValue().intValue());
    }

    @Override
    @Test
    public void iterator()
    {
        Sum sum1 = new IntegerSum(0);
        for (Integer each : this.dropWhileIterable)
        {
            sum1.add(each);
        }
        assertEquals(12, sum1.getValue().intValue());

        Sum sum2 = new IntegerSum(0);
        for (Integer each : this.emptyListDropWhileIterable)
        {
            sum2.add(each);
        }
        assertEquals(0, sum2.getValue().intValue());

        Sum sum3 = new IntegerSum(0);
        for (Integer each : this.alwaysFalseDropWhileIterable)
        {
            sum3.add(each);
        }
        assertEquals(15, sum3.getValue().intValue());

        Sum sum5 = new IntegerSum(0);
        for (Integer each : this.mostlyFalseDropWhileIterable)
        {
            sum5.add(each);
        }
        assertEquals(5, sum5.getValue().intValue());

        Sum sum6 = new IntegerSum(0);
        for (Integer each : this.alwaysTrueDropWhileIterable)
        {
            sum6.add(each);
        }
        assertEquals(0, sum6.getValue().intValue());
    }

    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return LazyIterate.dropWhile(FastList.newListWith(elements), Predicates.alwaysFalse());
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        assertEquals(
                FastList.newListWith(2, 3, 4, 5),
                new DropWhileIterable<>(FastList.newListWith(1, 1, 2, 3, 3, 3, 4, 5), each -> each % 2 != 0).distinct().toList());
    }
}
