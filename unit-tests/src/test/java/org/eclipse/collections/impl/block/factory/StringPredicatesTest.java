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

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringPredicatesTest
{
    @Test
    public void startsWith()
    {
        assertFalse(StringPredicates.startsWith("Hello").accept(null));
        assertTrue(StringPredicates.startsWith("Hello").accept("HelloWorld"));
        assertFalse(StringPredicates.startsWith("World").accept("HelloWorld"));
        assertEquals("StringPredicates.startsWith(\"Hello\")", StringPredicates.startsWith("Hello").toString());
    }

    @Test
    public void endsWith()
    {
        assertFalse(StringPredicates.endsWith("Hello").accept(null));
        assertFalse(StringPredicates.endsWith("Hello").accept("HelloWorld"));
        assertTrue(StringPredicates.endsWith("World").accept("HelloWorld"));
        assertEquals("StringPredicates.endsWith(\"Hello\")", StringPredicates.endsWith("Hello").toString());
    }

    @Test
    public void equalsIgnoreCase()
    {
        assertFalse(StringPredicates.equalsIgnoreCase("HELLO").accept(null));
        assertTrue(StringPredicates.equalsIgnoreCase("HELLO").accept("hello"));
        assertTrue(StringPredicates.equalsIgnoreCase("world").accept("WORLD"));
        assertFalse(StringPredicates.equalsIgnoreCase("Hello").accept("World"));
        assertEquals("StringPredicates.equalsIgnoreCase(\"Hello\")", StringPredicates.equalsIgnoreCase("Hello").toString());
    }

    @Test
    public void containsString()
    {
        assertTrue(StringPredicates.contains("Hello").accept("WorldHelloWorld"));
        assertTrue(StringPredicates.contains("Hello").and(StringPredicates.contains("World")).accept("WorldHelloWorld"));
        assertFalse(StringPredicates.contains("Goodbye").accept("WorldHelloWorld"));
        assertEquals("StringPredicates.contains(\"Hello\")", StringPredicates.contains("Hello").toString());
    }

    @Test
    public void containsCharacter()
    {
        assertTrue(StringPredicates.contains("H".charAt(0)).accept("WorldHelloWorld"));
        assertFalse(StringPredicates.contains("B".charAt(0)).accept("WorldHelloWorld"));
        assertEquals("StringPredicates.contains(\"H\")", StringPredicates.contains("H".charAt(0)).toString());
    }

    @Test
    public void emptyAndNotEmpty()
    {
        assertFalse(StringPredicates.empty().accept("WorldHelloWorld"));
        assertEquals("StringPredicates.empty()", StringPredicates.empty().toString());
        assertTrue(StringPredicates.notEmpty().accept("WorldHelloWorld"));
        assertEquals("StringPredicates.notEmpty()", StringPredicates.notEmpty().toString());
        assertTrue(StringPredicates.empty().accept(""));
        assertFalse(StringPredicates.notEmpty().accept(""));
    }

    @Test
    public void lessThan()
    {
        assertTrue(StringPredicates.lessThan("b").accept("a"));
        assertFalse(StringPredicates.lessThan("b").accept("b"));
        assertFalse(StringPredicates.lessThan("b").accept("c"));
        assertEquals("StringPredicates.lessThan(\"b\")", StringPredicates.lessThan("b").toString());
    }

    @Test
    public void lessThanOrEqualTo()
    {
        assertTrue(StringPredicates.lessThanOrEqualTo("b").accept("a"));
        assertTrue(StringPredicates.lessThanOrEqualTo("b").accept("b"));
        assertFalse(StringPredicates.lessThanOrEqualTo("b").accept("c"));
        assertEquals("StringPredicates.lessThanOrEqualTo(\"b\")", StringPredicates.lessThanOrEqualTo("b").toString());
    }

    @Test
    public void greaterThan()
    {
        assertFalse(StringPredicates.greaterThan("b").accept("a"));
        assertFalse(StringPredicates.greaterThan("b").accept("b"));
        assertTrue(StringPredicates.greaterThan("b").accept("c"));
        assertEquals("StringPredicates.greaterThan(\"b\")", StringPredicates.greaterThan("b").toString());
    }

    @Test
    public void greaterThanOrEqualTo()
    {
        assertFalse(StringPredicates.greaterThanOrEqualTo("b").accept("a"));
        assertTrue(StringPredicates.greaterThanOrEqualTo("b").accept("b"));
        assertTrue(StringPredicates.greaterThanOrEqualTo("b").accept("c"));
        assertEquals("StringPredicates.greaterThanOrEqualTo(\"b\")", StringPredicates.greaterThanOrEqualTo("b").toString());
    }

    @Test
    public void matches()
    {
        assertTrue(StringPredicates.matches("a*b*").accept("aaaaabbbbb"));
        assertFalse(StringPredicates.matches("a*b").accept("ba"));
        assertEquals("StringPredicates.matches(\"a*b\")", StringPredicates.matches("a*b").toString());
    }

    @Test
    public void size()
    {
        assertTrue(StringPredicates.size(1).accept("a"));
        assertFalse(StringPredicates.size(0).accept("a"));
        assertTrue(StringPredicates.size(2).accept("ab"));
        assertEquals("StringPredicates.size(2)", StringPredicates.size(2).toString());
    }

    @Test
    public void hasLetters()
    {
        assertTrue(StringPredicates.hasLetters().accept("a2a"));
        assertFalse(StringPredicates.hasLetters().accept("222"));
        assertEquals("StringPredicates.hasLetters()", StringPredicates.hasLetters().toString());
    }

    @Test
    public void hasDigits()
    {
        assertFalse(StringPredicates.hasDigits().accept("aaa"));
        assertTrue(StringPredicates.hasDigits().accept("a22"));
        assertEquals("StringPredicates.hasDigits()", StringPredicates.hasDigits().toString());
    }

    @Test
    public void hasLettersAndDigits()
    {
        Predicate<String> predicate = StringPredicates.hasLettersAndDigits();
        assertTrue(predicate.accept("a2a"));
        assertFalse(predicate.accept("aaa"));
        assertFalse(predicate.accept("222"));
        assertEquals("StringPredicates.hasLettersAndDigits()", predicate.toString());
    }

    @Test
    public void hasLettersOrDigits()
    {
        Predicate<String> predicate = StringPredicates.hasLettersOrDigits();
        assertTrue(predicate.accept("a2a"));
        assertTrue(predicate.accept("aaa"));
        assertTrue(predicate.accept("222"));
        assertEquals("StringPredicates.hasLettersOrDigits()", predicate.toString());
    }

    @Test
    public void isAlpha()
    {
        Predicate<String> predicate = StringPredicates.isAlpha();
        assertTrue(predicate.accept("aaa"));
        assertFalse(predicate.accept("a2a"));
        assertEquals("StringPredicates.isAlpha()", predicate.toString());
    }

    @Test
    public void isAlphaNumeric()
    {
        Predicate<String> predicate = StringPredicates.isAlphanumeric();
        assertTrue(predicate.accept("aaa"));
        assertTrue(predicate.accept("a2a"));
        assertEquals("StringPredicates.isAlphanumeric()", predicate.toString());
    }

    @Test
    public void isBlank()
    {
        Predicate<String> predicate = StringPredicates.isBlank();
        assertTrue(predicate.accept(""));
        assertTrue(predicate.accept(" "));
        assertFalse(predicate.accept("a2a"));
        assertEquals("StringPredicates.isBlank()", predicate.toString());
    }

    @Test
    public void notBlank()
    {
        Predicate<String> predicate = StringPredicates.notBlank();
        assertFalse(predicate.accept(""));
        assertFalse(predicate.accept(" "));
        assertTrue(predicate.accept("a2a"));
        assertEquals("StringPredicates.notBlank()", predicate.toString());
    }

    @Test
    public void isNumeric()
    {
        Predicate<String> predicate = StringPredicates.isNumeric();
        assertTrue(predicate.accept("222"));
        assertFalse(predicate.accept("a2a2a2"));
        assertFalse(predicate.accept("aaa"));
        assertEquals("StringPredicates.isNumeric()", predicate.toString());
    }

    @Test
    public void hasLowerCase()
    {
        Predicate<String> predicate = StringPredicates.hasLowerCase();
        assertTrue(predicate.accept("aaa"));
        assertFalse(predicate.accept("AAA"));
        assertEquals("StringPredicates.hasLowerCase()", predicate.toString());
    }

    @Test
    public void hasUpperCase()
    {
        Predicate<String> predicate = StringPredicates.hasUpperCase();
        assertFalse(predicate.accept("aaa"));
        assertTrue(predicate.accept("AAA"));
        assertEquals("StringPredicates.hasUpperCase()", predicate.toString());
    }

    @Test
    public void hasUndefined()
    {
        Predicate<String> predicate = StringPredicates.hasUndefined();
        assertFalse(predicate.accept("aaa"));
        assertEquals("StringPredicates.hasUndefined()", predicate.toString());
    }

    @Test
    public void hasSpaces()
    {
        Predicate<String> predicate = StringPredicates.hasSpaces();
        assertTrue(predicate.accept("a a a"));
        assertTrue(predicate.accept(" "));
        assertFalse(predicate.accept("aaa"));
        assertEquals("StringPredicates.hasSpaces()", predicate.toString());
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(StringPredicates.class);
    }
}
