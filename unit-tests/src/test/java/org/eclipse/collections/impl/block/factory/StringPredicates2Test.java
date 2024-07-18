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

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringPredicates2Test
{
    @Test
    public void startsWith()
    {
        assertFalse(StringPredicates2.startsWith().accept(null, "Hello"));
        assertTrue(StringPredicates2.startsWith().accept("HelloWorld", "Hello"));
        assertFalse(StringPredicates2.startsWith().accept("HelloWorld", "World"));
        assertEquals("StringPredicates2.startsWith()", StringPredicates2.startsWith().toString());
    }

    @Test
    public void notStartsWith()
    {
        assertTrue(StringPredicates2.notStartsWith().accept(null, "Hello"));
        assertFalse(StringPredicates2.notStartsWith().accept("HelloWorld", "Hello"));
        assertTrue(StringPredicates2.notStartsWith().accept("HelloWorld", "World"));
        assertEquals("StringPredicates2.notStartsWith()", StringPredicates2.notStartsWith().toString());
    }

    @Test
    public void endsWith()
    {
        assertFalse(StringPredicates2.endsWith().accept(null, "Hello"));
        assertFalse(StringPredicates2.endsWith().accept("HelloWorld", "Hello"));
        assertTrue(StringPredicates2.endsWith().accept("HelloWorld", "World"));
        assertEquals("StringPredicates2.endsWith()", StringPredicates2.endsWith().toString());
    }

    @Test
    public void notEndsWith()
    {
        assertTrue(StringPredicates2.notEndsWith().accept(null, "Hello"));
        assertTrue(StringPredicates2.notEndsWith().accept("HelloWorld", "Hello"));
        assertFalse(StringPredicates2.notEndsWith().accept("HelloWorld", "World"));
        assertEquals("StringPredicates2.notEndsWith()", StringPredicates2.notEndsWith().toString());
    }

    @Test
    public void equalsIgnoreCase()
    {
        assertFalse(StringPredicates2.equalsIgnoreCase().accept(null, "HELLO"));
        assertTrue(StringPredicates2.equalsIgnoreCase().accept("hello", "HELLO"));
        assertTrue(StringPredicates2.equalsIgnoreCase().accept("WORLD", "world"));
        assertFalse(StringPredicates2.equalsIgnoreCase().accept("World", "Hello"));
        assertEquals("StringPredicates2.equalsIgnoreCase()", StringPredicates2.equalsIgnoreCase().toString());
    }

    @Test
    public void notEqualsIgnoreCase()
    {
        assertTrue(StringPredicates2.notEqualsIgnoreCase().accept(null, "HELLO"));
        assertFalse(StringPredicates2.notEqualsIgnoreCase().accept("hello", "HELLO"));
        assertFalse(StringPredicates2.notEqualsIgnoreCase().accept("WORLD", "world"));
        assertTrue(StringPredicates2.notEqualsIgnoreCase().accept("World", "Hello"));
        assertEquals("StringPredicates2.notEqualsIgnoreCase()", StringPredicates2.notEqualsIgnoreCase().toString());
    }

    @Test
    public void containsString()
    {
        assertTrue(StringPredicates2.contains().accept("WorldHelloWorld", "Hello"));
        assertFalse(StringPredicates2.contains().accept("WorldHelloWorld", "Goodbye"));
        assertEquals("StringPredicates2.contains()", StringPredicates2.contains().toString());
    }

    @Test
    public void matches()
    {
        assertTrue(StringPredicates2.matches().accept("aaaaabbbbb", "a*b*"));
        assertFalse(StringPredicates2.matches().accept("ba", "a*b"));
        assertEquals("StringPredicates2.matches()", StringPredicates2.matches().toString());
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(StringPredicates2.class);
    }
}
