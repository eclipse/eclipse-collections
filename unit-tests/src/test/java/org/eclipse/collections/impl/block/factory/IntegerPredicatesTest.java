/*
 * Copyright (c) 2021 Goldman Sachs.
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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerPredicatesTest
{
    private static final Function<Integer, Integer> INT_VALUE = integer -> integer;

    @Test
    public void isOdd()
    {
        assertTrue(IntegerPredicates.isOdd().accept(1));
        assertFalse(IntegerPredicates.isOdd().accept(-2));
    }

    @Test
    public void isEven()
    {
        assertTrue(IntegerPredicates.isEven().accept(-42));
        assertTrue(IntegerPredicates.isEven().accept(0));
        assertFalse(IntegerPredicates.isEven().accept(1));
    }

    @Test
    public void attributeIsOdd()
    {
        assertTrue(IntegerPredicates.attributeIsOdd(INT_VALUE).accept(1));
        assertFalse(IntegerPredicates.attributeIsOdd(INT_VALUE).accept(-2));
    }

    @Test
    public void attributeIsEven()
    {
        assertTrue(IntegerPredicates.attributeIsEven(INT_VALUE).accept(-42));
        assertTrue(IntegerPredicates.attributeIsEven(INT_VALUE).accept(0));
        assertFalse(IntegerPredicates.attributeIsEven(INT_VALUE).accept(1));
    }

    @Test
    public void attributeIsZero()
    {
        assertFalse(IntegerPredicates.attributeIsZero(INT_VALUE).accept(-42));
        assertTrue(IntegerPredicates.attributeIsZero(INT_VALUE).accept(0));
        assertFalse(IntegerPredicates.attributeIsZero(INT_VALUE).accept(1));
    }

    @Test
    public void attributeIsPositive()
    {
        assertFalse(IntegerPredicates.attributeIsPositive(INT_VALUE).accept(-42));
        assertFalse(IntegerPredicates.attributeIsPositive(INT_VALUE).accept(0));
        assertTrue(IntegerPredicates.attributeIsPositive(INT_VALUE).accept(1));
    }

    @Test
    public void attributeIsNegative()
    {
        assertTrue(IntegerPredicates.attributeIsNegative(INT_VALUE).accept(-42));
        assertFalse(IntegerPredicates.attributeIsNegative(INT_VALUE).accept(0));
        assertFalse(IntegerPredicates.attributeIsNegative(INT_VALUE).accept(1));
    }

    @Test
    public void isZero()
    {
        assertTrue(IntegerPredicates.isZero().accept(0));
        assertFalse(IntegerPredicates.isZero().accept(1));
        assertFalse(IntegerPredicates.isZero().accept(-1));
    }

    @Test
    public void isPositive()
    {
        assertFalse(IntegerPredicates.isPositive().accept(0));
        assertTrue(IntegerPredicates.isPositive().accept(1));
        assertFalse(IntegerPredicates.isPositive().accept(-1));
    }

    @Test
    public void isNegative()
    {
        assertFalse(IntegerPredicates.isNegative().accept(0));
        assertFalse(IntegerPredicates.isNegative().accept(1));
        assertTrue(IntegerPredicates.isNegative().accept(-1));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(IntegerPredicates.class);
    }
}
