/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.list.Interval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BigDecimalSummaryStatisticsTest
{
    @Test
    public void jdkForEach()
    {
        BigDecimalSummaryStatistics statistics = new BigDecimalSummaryStatistics();
        List<BigDecimal> integers = Interval.oneTo(5).collect(i -> BigDecimal.valueOf((long) i)).toList();
        integers.forEach(statistics);
        assertEquals(BigDecimal.valueOf(15L), statistics.getSum());
        assertEquals(5L, statistics.getCount());
        assertEquals(BigDecimal.valueOf(1L), statistics.getMin());
        assertEquals(BigDecimal.valueOf(1L), statistics.getMinOptional().get());
        assertEquals(BigDecimal.valueOf(5L), statistics.getMax());
        assertEquals(BigDecimal.valueOf(5L), statistics.getMaxOptional().get());
        assertEquals(BigDecimal.valueOf(3L), statistics.getAverage());
    }

    @Test
    public void each()
    {
        BigDecimalSummaryStatistics statistics = new BigDecimalSummaryStatistics();
        MutableList<BigDecimal> integers = Interval.oneTo(5).collect(i -> BigDecimal.valueOf((long) i)).toList();
        integers.each(statistics);
        assertEquals(BigDecimal.valueOf(15L), statistics.getSum());
        assertEquals(5L, statistics.getCount());
        assertEquals(BigDecimal.valueOf(1L), statistics.getMin());
        assertEquals(BigDecimal.valueOf(1L), statistics.getMinOptional().get());
        assertEquals(BigDecimal.valueOf(5L), statistics.getMax());
        assertEquals(BigDecimal.valueOf(5L), statistics.getMaxOptional().get());
        assertEquals(BigDecimal.valueOf(3L), statistics.getAverage());
    }

    @Test
    public void merge()
    {
        BigDecimalSummaryStatistics statistics1 = new BigDecimalSummaryStatistics();
        MutableList<BigDecimal> integers1 = Interval.oneTo(2).collect(i -> BigDecimal.valueOf((long) i)).toList();
        integers1.each(statistics1);
        BigDecimalSummaryStatistics statistics2 = new BigDecimalSummaryStatistics();
        MutableList<BigDecimal> integers2 = Interval.fromTo(3, 5).collect(i -> BigDecimal.valueOf((long) i)).toList();
        integers2.each(statistics2);
        assertSame(statistics1, statistics1.merge(statistics2));
        assertEquals(BigDecimal.valueOf(15L), statistics1.getSum());
        assertEquals(5L, statistics1.getCount());
        assertEquals(BigDecimal.valueOf(1L), statistics1.getMin());
        assertEquals(BigDecimal.valueOf(1L), statistics1.getMinOptional().get());
        assertEquals(BigDecimal.valueOf(5L), statistics1.getMax());
        assertEquals(BigDecimal.valueOf(5L), statistics1.getMaxOptional().get());
        assertEquals(BigDecimal.valueOf(3L), statistics1.getAverage());
    }

    @Test
    public void empty()
    {
        BigDecimalSummaryStatistics statistics = new BigDecimalSummaryStatistics();
        assertEquals(0L, statistics.getCount());
        assertEquals(BigDecimal.ZERO, statistics.getSum());
        assertEquals(BigDecimal.ZERO, statistics.getAverage());
        assertNull(statistics.getMin());
        assertNull(statistics.getMax());
    }

    @Test
    public void summarizingBigDecimal()
    {
        BigDecimalSummaryStatistics statistics =
                Interval.oneTo(5).stream().collect(Collectors2.summarizingBigDecimal(BigDecimal::new));
        assertEquals(BigDecimal.valueOf(15L), statistics.getSum());
        assertEquals(5L, statistics.getCount());
        assertEquals(BigDecimal.valueOf(1L), statistics.getMin());
        assertEquals(BigDecimal.valueOf(1L), statistics.getMinOptional().get());
        assertEquals(BigDecimal.valueOf(5L), statistics.getMax());
        assertEquals(BigDecimal.valueOf(5L), statistics.getMaxOptional().get());
        assertEquals(BigDecimal.valueOf(3L), statistics.getAverage());
    }

    @Test
    public void average()
    {
        assertEquals(
                new BigDecimal("3.333333333333333333333333333333333"),
                IntLists.mutable.with(2, 2, 6)
                        .collect(Long::valueOf)
                        .stream()
                        .collect(Collectors2.summarizingBigDecimal(BigDecimal::valueOf))
                        .getAverage());

        assertEquals(
                new BigDecimal("3.666666666666666666666666666666667"),
                IntLists.mutable.with(2, 3, 6)
                        .collect(Long::valueOf)
                        .stream()
                        .collect(Collectors2.summarizingBigDecimal(BigDecimal::valueOf))
                        .getAverage());

        assertEquals(
                new BigDecimal("1"),
                IntLists.mutable.with(1, 1, 1)
                        .collect(Long::valueOf)
                        .stream()
                        .collect(Collectors2.summarizingBigDecimal(BigDecimal::valueOf))
                        .getAverage());

        assertEquals(
                new BigDecimal("4"),
                IntLists.mutable.with(2, 3, 4, 5, 6)
                        .collect(Long::valueOf)
                        .stream()
                        .collect(Collectors2.summarizingBigDecimal(BigDecimal::valueOf))
                        .getAverage());

        assertEquals(
                new BigDecimal("3.8"),
                IntLists.mutable.with(2, 3, 4, 5, 5)
                        .collect(Long::valueOf)
                        .stream()
                        .collect(Collectors2.summarizingBigDecimal(BigDecimal::valueOf))
                        .getAverage());
    }
}
