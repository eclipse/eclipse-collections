/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Procedures2Test
{
    @Test
    public void throwing()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Procedures2.throwing((a, b) -> { throw new IOException(); }).value(null, null));
    }

    @Test
    public void throwingWithUserSpecifiedException()
    {
        Verify.assertThrowsWithCause(
                RuntimeException.class,
                IOException.class,
                () -> Procedures2.throwing(
                        (one, two) -> { throw new IOException(); },
                        (one, two, ce) -> new RuntimeException(ce)).value(null, null));
        Verify.assertThrowsWithCause(
                MyRuntimeException.class,
                IOException.class,
                () -> Procedures2.throwing(
                        (one, two) -> { throw new IOException(); },
                        this::throwMyException).value(null, null));
        Verify.assertThrows(
                NullPointerException.class,
                () -> Procedures2.throwing(
                        (one, two) -> { throw new NullPointerException(); },
                        this::throwMyException).value(null, null));
    }

    private MyRuntimeException throwMyException(Object one, Object two, Throwable exception)
    {
        return new MyRuntimeException(String.valueOf(one) + String.valueOf(two), exception);
    }

    @Test
    public void asProcedure2()
    {
        CollectionAddProcedure<Integer> procedure = CollectionAddProcedure.on(FastList.newList());
        Procedure2<Integer, Object> procedure2 = Procedures2.fromProcedure(procedure);
        procedure2.value(1, null);
        Assert.assertEquals(FastList.newListWith(1), procedure.getResult());
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Procedures2.class);
    }

    private static class MyRuntimeException extends RuntimeException
    {
        MyRuntimeException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    /**
     * @since 9.2.
     */
    @Test
    public void summarizeDouble()
    {
        MutableList<Double> list = Lists.mutable.with(2.0, 2.0, 2.0, 3.0, 3.0, 3.0);
        MutableMap<String, DoubleSummaryStatistics> map = list.aggregateInPlaceBy(
                Object::toString,
                DoubleSummaryStatistics::new,
                Procedures2.summarizeDouble(Double::doubleValue));
        Verify.assertSize(2, map);
        Assert.assertEquals(6.0, map.get("2.0").getSum(), 0.0);
        Assert.assertEquals(9.0, map.get("3.0").getSum(), 0.0);
    }

    /**
     * @since 9.2.
     */
    @Test
    public void summarizeFloat()
    {
        MutableList<Float> list = Lists.mutable.with(2.0f, 2.0f, 2.0f, 3.0f, 3.0f, 3.0f);
        MutableMap<String, DoubleSummaryStatistics> map = list.aggregateInPlaceBy(
                Object::toString,
                DoubleSummaryStatistics::new,
                Procedures2.summarizeFloat(Float::floatValue));
        Verify.assertSize(2, map);
        Assert.assertEquals(6.0, map.get("2.0").getSum(), 0.0);
        Assert.assertEquals(9.0, map.get("3.0").getSum(), 0.0);
    }

    /**
     * @since 9.2.
     */
    @Test
    public void summarizeLong()
    {
        MutableList<Long> list = Lists.mutable.with(2L, 2L, 2L, 3L, 3L, 3L);
        MutableMap<String, LongSummaryStatistics> map = list.aggregateInPlaceBy(
                Object::toString,
                LongSummaryStatistics::new,
                Procedures2.summarizeLong(Long::longValue));
        Verify.assertSize(2, map);
        Assert.assertEquals(6, map.get("2").getSum());
        Assert.assertEquals(9, map.get("3").getSum());
    }

    /**
     * @since 9.2.
     */
    @Test
    public void summarizeInt()
    {
        MutableList<Integer> list = Lists.mutable.with(2, 2, 2, 3, 3, 3);
        MutableMap<String, IntSummaryStatistics> map = list.aggregateInPlaceBy(
                Object::toString,
                IntSummaryStatistics::new,
                Procedures2.summarizeInt(Integer::intValue));
        Verify.assertSize(2, map);
        Assert.assertEquals(6, map.get("2").getSum());
        Assert.assertEquals(9, map.get("3").getSum());
    }
}
