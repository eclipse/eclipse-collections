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

public class IntegerPredicatesTest
{
    private static final Function<Integer, Integer> INT_VALUE = integer -> integer;

    @Test
    public void isOdd()
    {
        Assert.assertTrue(IntegerPredicates.isOdd().accept(1));
        Assert.assertFalse(IntegerPredicates.isOdd().accept(-2));
    }

    @Test
    public void isEven()
    {
        Assert.assertTrue(IntegerPredicates.isEven().accept(-42));
        Assert.assertTrue(IntegerPredicates.isEven().accept(0));
        Assert.assertFalse(IntegerPredicates.isEven().accept(1));
    }

    @Test
    public void attributeIsOdd()
    {
        Assert.assertTrue(IntegerPredicates.attributeIsOdd(INT_VALUE).accept(1));
        Assert.assertFalse(IntegerPredicates.attributeIsOdd(INT_VALUE).accept(-2));
    }

    @Test
    public void attributeIsEven()
    {
        Assert.assertTrue(IntegerPredicates.attributeIsEven(INT_VALUE).accept(-42));
        Assert.assertTrue(IntegerPredicates.attributeIsEven(INT_VALUE).accept(0));
        Assert.assertFalse(IntegerPredicates.attributeIsEven(INT_VALUE).accept(1));
    }

    @Test
    public void attributeIsZero()
    {
        Assert.assertFalse(IntegerPredicates.attributeIsZero(INT_VALUE).accept(-42));
        Assert.assertTrue(IntegerPredicates.attributeIsZero(INT_VALUE).accept(0));
        Assert.assertFalse(IntegerPredicates.attributeIsZero(INT_VALUE).accept(1));
    }

    @Test
    public void attributeIsPositive()
    {
        Assert.assertFalse(IntegerPredicates.attributeIsPositive(INT_VALUE).accept(-42));
        Assert.assertFalse(IntegerPredicates.attributeIsPositive(INT_VALUE).accept(0));
        Assert.assertTrue(IntegerPredicates.attributeIsPositive(INT_VALUE).accept(1));
    }

    @Test
    public void attributeIsNegative()
    {
        Assert.assertTrue(IntegerPredicates.attributeIsNegative(INT_VALUE).accept(-42));
        Assert.assertFalse(IntegerPredicates.attributeIsNegative(INT_VALUE).accept(0));
        Assert.assertFalse(IntegerPredicates.attributeIsNegative(INT_VALUE).accept(1));
    }

    @Test
    public void isZero()
    {
        Assert.assertTrue(IntegerPredicates.isZero().accept(0));
        Assert.assertFalse(IntegerPredicates.isZero().accept(1));
        Assert.assertFalse(IntegerPredicates.isZero().accept(-1));
    }

    @Test
    public void isPositive()
    {
        Assert.assertFalse(IntegerPredicates.isPositive().accept(0));
        Assert.assertTrue(IntegerPredicates.isPositive().accept(1));
        Assert.assertFalse(IntegerPredicates.isPositive().accept(-1));
    }

    @Test
    public void isNegative()
    {
        Assert.assertFalse(IntegerPredicates.isNegative().accept(0));
        Assert.assertFalse(IntegerPredicates.isNegative().accept(1));
        Assert.assertTrue(IntegerPredicates.isNegative().accept(-1));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(IntegerPredicates.class);
    }
}
