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

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class StringFunctionsSerializationTest
{
    @Test
    public void length()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJExlbmd0aEZ1bmN0aW9uAAAAAAAAAAECAAB4cgBJb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5ibG9jay5mdW5jdGlvbi5wcmltaXRpdmUuSW50ZWdlckZ1bmN0aW9uSW1wbAAA\n"
                        + "AAAAAAABAgAAeHA=",
                StringFunctions.length());
    }

    @Test
    public void toLowerCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvTG93ZXJDYXNlRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                StringFunctions.toLowerCase());
    }

    @Test
    public void toUpperCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvVXBwZXJDYXNlRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                StringFunctions.toUpperCase());
    }

    @Test
    public void toInteger()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvSW50ZWdlckZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                StringFunctions.toInteger());
    }

    @Test
    public void trim()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRyaW1GdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                StringFunctions.trim());
    }

    @Test
    public void subString()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFN1YlN0cmluZ0Z1bmN0aW9uAAAAAAAAAAECAAJJAApiZWdpbkluZGV4SQAIZW5k\n"
                        + "SW5kZXh4cAAAAAAAAAAB",
                StringFunctions.subString(0, 1));
    }

    @Test
    public void toPrimitiveBoolean()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlQm9vbGVhbkZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                StringFunctions.toPrimitiveBoolean());
    }

    @Test
    public void toPrimitiveByte()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlQnl0ZUZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                StringFunctions.toPrimitiveByte());
    }

    @Test
    public void toPrimitiveChar()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlQ2hhckZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                StringFunctions.toPrimitiveChar());
    }

    @Test
    public void toFirstChar()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvRmlyc3RDaGFyRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                StringFunctions.toFirstChar());
    }

    @Test
    public void toFirstLetter()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJEZpcnN0TGV0dGVyRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                StringFunctions.firstLetter());
    }

    @Test
    public void toPrimitiveDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlRG91YmxlRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                StringFunctions.toPrimitiveDouble());
    }

    @Test
    public void toPrimitiveFloat()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlRmxvYXRGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                StringFunctions.toPrimitiveFloat());
    }

    @Test
    public void toPrimitiveInt()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlSW50RnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                StringFunctions.toPrimitiveInt());
    }

    @Test
    public void toPrimitiveLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlTG9uZ0Z1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                StringFunctions.toPrimitiveLong());
    }

    @Test
    public void toPrimitiveShort()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFRvUHJpbWl0aXZlU2hvcnRGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                StringFunctions.toPrimitiveShort());
    }

    @Test
    public void append()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJEFwcGVuZEZ1bmN0aW9uAAAAAAAAAAECAAFMAA12YWx1ZVRvQXBwZW5kdAASTGph\n"
                        + "dmEvbGFuZy9TdHJpbmc7eHBw",
                StringFunctions.append(null));
    }

    @Test
    public void prepend()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "RnVuY3Rpb25zJFByZXBlbmRGdW5jdGlvbgAAAAAAAAABAgABTAAOdmFsdWVUb1ByZXBlbmR0ABJM\n"
                        + "amF2YS9sYW5nL1N0cmluZzt4cHA=",
                StringFunctions.prepend(null));
    }
}
