/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BooleanPredicatesTest
{
    @Test
    public void testEqual()
    {
        assertTrue(BooleanPredicates.equal(true).accept(true));
        assertTrue(BooleanPredicates.equal(false).accept(false));
        assertFalse(BooleanPredicates.equal(true).accept(false));
        assertFalse(BooleanPredicates.equal(false).accept(true));
    }

    @Test
    public void testIsTrue()
    {
        assertTrue(BooleanPredicates.isTrue().accept(true));
        assertFalse(BooleanPredicates.isTrue().accept(false));
    }

    @Test
    public void testIsFalse()
    {
        assertTrue(BooleanPredicates.isFalse().accept(false));
        assertFalse(BooleanPredicates.isFalse().accept(true));
    }

    @Test
    public void testAnd()
    {
        assertFalse(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.equal(true)).accept(false));
        assertFalse(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.equal(false)).accept(false));
        assertFalse(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.equal(true)).accept(false));
        assertTrue(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.equal(false)).accept(false));

        assertTrue(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.equal(true)).accept(true));
        assertFalse(BooleanPredicates.and(BooleanPredicates.isTrue(), BooleanPredicates.equal(false)).accept(false));
        assertFalse(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.equal(true)).accept(true));
        assertFalse(BooleanPredicates.and(BooleanPredicates.isFalse(), BooleanPredicates.equal(false)).accept(true));

        assertFalse(BooleanPredicates.and(BooleanPredicates.isFalse(), value -> !value).accept(true));
        assertTrue(BooleanPredicates.and(BooleanPredicates.isFalse(), value -> !value).accept(false));
    }

    @Test
    public void testOr()
    {
        assertFalse(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.equal(true)).accept(false));
        assertTrue(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.equal(false)).accept(false));
        assertTrue(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.equal(true)).accept(false));
        assertTrue(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.equal(false)).accept(false));

        assertTrue(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.equal(true)).accept(true));
        assertTrue(BooleanPredicates.or(BooleanPredicates.isTrue(), BooleanPredicates.equal(false)).accept(true));
        assertTrue(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.equal(true)).accept(true));
        assertFalse(BooleanPredicates.or(BooleanPredicates.isFalse(), BooleanPredicates.equal(false)).accept(true));

        assertTrue(BooleanPredicates.or(BooleanPredicates.isFalse(), value -> !value).accept(false));
        assertFalse(BooleanPredicates.or(BooleanPredicates.isFalse(), value -> !value).accept(true));
    }

    @Test
    public void testNot()
    {
        assertTrue(BooleanPredicates.not(BooleanPredicates.isTrue()).accept(false));
        assertFalse(BooleanPredicates.not(BooleanPredicates.isTrue()).accept(true));
        assertTrue(BooleanPredicates.not(BooleanPredicates.isFalse()).accept(true));
        assertFalse(BooleanPredicates.not(BooleanPredicates.isFalse()).accept(false));
        assertTrue(BooleanPredicates.not(true).accept(false));
        assertFalse(BooleanPredicates.not(true).accept(true));
        assertTrue(BooleanPredicates.not(false).accept(true));
        assertFalse(BooleanPredicates.not(false).accept(false));
    }

    @Test
    public void testAlwaysTrue()
    {
        assertTrue(BooleanPredicates.alwaysTrue().accept(false));
        assertTrue(BooleanPredicates.alwaysTrue().accept(true));
    }

    @Test
    public void testAlwaysFalse()
    {
        assertFalse(BooleanPredicates.alwaysFalse().accept(false));
        assertFalse(BooleanPredicates.alwaysFalse().accept(true));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(BooleanPredicates.class);
    }
}
