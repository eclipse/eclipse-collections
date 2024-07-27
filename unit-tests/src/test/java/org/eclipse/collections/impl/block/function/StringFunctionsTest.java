/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.impl.block.factory.StringFunctions;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StringFunctionsTest
{
    @Test
    public void toUpperCase()
    {
        Function<String, String> function = StringFunctions.toUpperCase();
        assertEquals("UPPER", function.valueOf("upper"));
        assertEquals("UPPER", function.valueOf("Upper"));
        assertEquals("UPPER", function.valueOf("UPPER"));
        assertSame("UPPER", function.valueOf("UPPER"));
    }

    @Test
    public void toLowerCase()
    {
        Function<String, String> function = StringFunctions.toLowerCase();
        assertEquals("lower", function.valueOf("LOWER"));
        assertEquals("lower", function.valueOf("Lower"));
        assertEquals("lower", function.valueOf("lower"));
        assertSame("lower", function.valueOf("lower"));
    }

    @Test
    public void toInteger()
    {
        assertEquals(-42L, StringFunctions.toInteger().valueOf("-42").longValue());
        Verify.assertInstanceOf(Integer.class, StringFunctions.toInteger().valueOf("10"));
    }

    @Test
    public void length()
    {
        Function<String, Integer> function = StringFunctions.length();
        assertEquals(Integer.valueOf(6), function.valueOf("string"));
        assertEquals(Integer.valueOf(0), function.valueOf(""));
        assertEquals("string.length()", function.toString());
    }

    @Test
    public void trim()
    {
        Function<String, String> function = StringFunctions.trim();
        assertEquals("trim", function.valueOf("trim "));
        assertEquals("trim", function.valueOf(" trim"));
        assertEquals("trim", function.valueOf("  trim  "));
        assertEquals("trim", function.valueOf("trim"));
        assertSame("trim", function.valueOf("trim"));
        assertEquals("string.trim()", function.toString());
    }

    @Test
    public void firstLetter()
    {
        Function<String, Character> function = StringFunctions.firstLetter();
        assertNull(function.valueOf(null));
        assertNull(function.valueOf(""));
        assertEquals('A', function.valueOf("Autocthonic").charValue());
    }

    @Test
    public void subString()
    {
        Function<String, String> function1 = StringFunctions.subString(2, 5);
        String testString = "habits";
        assertEquals("bit", function1.valueOf(testString));
        Verify.assertContains("string.subString", function1.toString());

        Function<String, String> function2 = StringFunctions.subString(0, testString.length());
        assertEquals(testString, function2.valueOf(testString));

        Function<String, String> function3 = StringFunctions.subString(0, testString.length() + 1);
        assertThrows(StringIndexOutOfBoundsException.class, () -> function3.valueOf(testString));

        Function<String, String> function4 = StringFunctions.subString(-1, 1);
        assertThrows(StringIndexOutOfBoundsException.class, () -> function4.valueOf(testString));
    }

    @Test
    public void subString_throws_on_short_string()
    {
        assertThrows(StringIndexOutOfBoundsException.class, () -> StringFunctions.subString(2, 4).valueOf("hi"));
    }

    @Test
    public void subString_throws_on_null()
    {
        assertThrows(NullPointerException.class, () -> StringFunctions.subString(2, 4).valueOf(null));
    }

    @Test
    public void toPrimitiveBoolean()
    {
        assertTrue(StringFunctions.toPrimitiveBoolean().booleanValueOf("true"));
        assertFalse(StringFunctions.toPrimitiveBoolean().booleanValueOf("nah"));
    }

    @Test
    public void toPrimitiveByte()
    {
        assertEquals((byte) 16, StringFunctions.toPrimitiveByte().byteValueOf("16"));
    }

    @Test
    public void toFirstChar()
    {
        assertEquals('X', StringFunctions.toFirstChar().charValueOf("X-ray"));
    }

    @Test
    public void toPrimitiveChar()
    {
        assertEquals('A', StringFunctions.toPrimitiveChar().charValueOf("65"));
    }

    @Test
    public void toPrimitiveCharWithEmptyString()
    {
        assertThrows(StringIndexOutOfBoundsException.class, () -> StringFunctions.toFirstChar().charValueOf(""));
    }

    @Test
    public void toPrimitiveDouble()
    {
        assertEquals(3.14159265359d, StringFunctions.toPrimitiveDouble().doubleValueOf("3.14159265359"), 0.0);
    }

    @Test
    public void toPrimitiveFloat()
    {
        assertEquals(3.1415d, StringFunctions.toPrimitiveFloat().floatValueOf("3.1415"), 0.00001);
    }

    @Test
    public void toPrimitiveInt()
    {
        assertEquals(256, StringFunctions.toPrimitiveInt().intValueOf("256"));
    }

    @Test
    public void toPrimitiveLong()
    {
        assertEquals(0x7fffffffffffffffL, StringFunctions.toPrimitiveLong().longValueOf("9223372036854775807"));
    }

    @Test
    public void toPrimitiveShort()
    {
        assertEquals(-32768, StringFunctions.toPrimitiveShort().shortValueOf("-32768"));
    }

    @Test
    public void append()
    {
        Verify.assertContainsAll(FastList.newListWith("1", "2", "3", "4", "5").collect(StringFunctions.append("!")), "1!", "2!", "3!", "4!", "5!");
    }

    @Test
    public void prepend()
    {
        Verify.assertContainsAll(FastList.newListWith("1", "2", "3", "4", "5").collect(StringFunctions.prepend("@")), "@1", "@2", "@3", "@4", "@5");
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(StringFunctions.class);
    }
}
