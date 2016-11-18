/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class LongPredicatesTest
{
    private static final Function<Long, Long> LONG_VALUE = Long::longValue;

    @Test
    public void isOdd()
    {
        Assert.assertTrue(LongPredicates.isOdd().accept(1L));
        Assert.assertFalse(LongPredicates.isOdd().accept(-2L));
    }

    @Test
    public void isEven()
    {
        Assert.assertTrue(LongPredicates.isEven().accept(-42L));
        Assert.assertTrue(LongPredicates.isEven().accept(0L));
        Assert.assertFalse(LongPredicates.isEven().accept(1L));
    }

    @Test
    public void attributeIsOdd()
    {
        Assert.assertTrue(LongPredicates.attributeIsOdd(LONG_VALUE).accept(1L));
        Assert.assertFalse(LongPredicates.attributeIsOdd(LONG_VALUE).accept(-2L));
    }

    @Test
    public void attributeIsEven()
    {
        Assert.assertTrue(LongPredicates.attributeIsEven(LONG_VALUE).accept(-42L));
        Assert.assertTrue(LongPredicates.attributeIsEven(LONG_VALUE).accept(0L));
        Assert.assertFalse(LongPredicates.attributeIsEven(LONG_VALUE).accept(1L));
    }

    @Test
    public void isZero()
    {
        Assert.assertTrue(LongPredicates.isZero().accept(0L));
        Assert.assertFalse(LongPredicates.isZero().accept(1L));
        Assert.assertFalse(LongPredicates.isZero().accept(-1L));
    }

    @Test
    public void isPositive()
    {
        Assert.assertFalse(LongPredicates.isPositive().accept(0L));
        Assert.assertTrue(LongPredicates.isPositive().accept(1L));
        Assert.assertFalse(LongPredicates.isPositive().accept(-1L));
    }

    @Test
    public void isNegative()
    {
        Assert.assertFalse(LongPredicates.isNegative().accept(0L));
        Assert.assertFalse(LongPredicates.isNegative().accept(1L));
        Assert.assertTrue(LongPredicates.isNegative().accept(-1L));
    }

    @Test
    public void attributeIsZero()
    {
        Assert.assertTrue(LongPredicates.attributeIsZero(Integer::longValue).accept(0));
        Assert.assertFalse(LongPredicates.attributeIsZero(Integer::longValue).accept(1));
    }

    @Test
    public void attributeIsPositive()
    {
        Assert.assertTrue(LongPredicates.attributeIsPositive(Integer::longValue).accept(1));
        Assert.assertFalse(LongPredicates.attributeIsPositive(Integer::longValue).accept(0));
        Assert.assertFalse(LongPredicates.attributeIsPositive(Integer::longValue).accept(-1));
    }

    @Test
    public void attributeIsNegative()
    {
        Assert.assertTrue(LongPredicates.attributeIsNegative(Integer::longValue).accept(-1));
        Assert.assertFalse(LongPredicates.attributeIsNegative(Integer::longValue).accept(0));
        Assert.assertFalse(LongPredicates.attributeIsNegative(Integer::longValue).accept(1));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(LongPredicates.class);
    }
}
