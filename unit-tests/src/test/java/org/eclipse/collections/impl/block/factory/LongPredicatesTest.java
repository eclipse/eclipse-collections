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

public class LongPredicatesTest
{
    private static final Function<Long, Long> LONG_VALUE = Long::longValue;

    @Test
    public void isOdd()
    {
        assertTrue(LongPredicates.isOdd().accept(1L));
        assertFalse(LongPredicates.isOdd().accept(-2L));
    }

    @Test
    public void isEven()
    {
        assertTrue(LongPredicates.isEven().accept(-42L));
        assertTrue(LongPredicates.isEven().accept(0L));
        assertFalse(LongPredicates.isEven().accept(1L));
    }

    @Test
    public void attributeIsOdd()
    {
        assertTrue(LongPredicates.attributeIsOdd(LONG_VALUE).accept(1L));
        assertFalse(LongPredicates.attributeIsOdd(LONG_VALUE).accept(-2L));
    }

    @Test
    public void attributeIsEven()
    {
        assertTrue(LongPredicates.attributeIsEven(LONG_VALUE).accept(-42L));
        assertTrue(LongPredicates.attributeIsEven(LONG_VALUE).accept(0L));
        assertFalse(LongPredicates.attributeIsEven(LONG_VALUE).accept(1L));
    }

    @Test
    public void isZero()
    {
        assertTrue(LongPredicates.isZero().accept(0L));
        assertFalse(LongPredicates.isZero().accept(1L));
        assertFalse(LongPredicates.isZero().accept(-1L));
    }

    @Test
    public void isPositive()
    {
        assertFalse(LongPredicates.isPositive().accept(0L));
        assertTrue(LongPredicates.isPositive().accept(1L));
        assertFalse(LongPredicates.isPositive().accept(-1L));
    }

    @Test
    public void isNegative()
    {
        assertFalse(LongPredicates.isNegative().accept(0L));
        assertFalse(LongPredicates.isNegative().accept(1L));
        assertTrue(LongPredicates.isNegative().accept(-1L));
    }

    @Test
    public void attributeIsZero()
    {
        assertTrue(LongPredicates.attributeIsZero(Integer::longValue).accept(0));
        assertFalse(LongPredicates.attributeIsZero(Integer::longValue).accept(1));
    }

    @Test
    public void attributeIsPositive()
    {
        assertTrue(LongPredicates.attributeIsPositive(Integer::longValue).accept(1));
        assertFalse(LongPredicates.attributeIsPositive(Integer::longValue).accept(0));
        assertFalse(LongPredicates.attributeIsPositive(Integer::longValue).accept(-1));
    }

    @Test
    public void attributeIsNegative()
    {
        assertTrue(LongPredicates.attributeIsNegative(Integer::longValue).accept(-1));
        assertFalse(LongPredicates.attributeIsNegative(Integer::longValue).accept(0));
        assertFalse(LongPredicates.attributeIsNegative(Integer::longValue).accept(1));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(LongPredicates.class);
    }
}
