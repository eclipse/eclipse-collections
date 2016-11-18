/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory.primitive;

import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class PrimitiveFunctionsSerializationTest
{
    @Test
    public void integerIsPositive()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJEludGVnZXJJc1Bvc2l0aXZlAAAAAAAAAAECAAB4cA==",
                PrimitiveFunctions.integerIsPositive());
    }

    @Test
    public void unboxNumberToInt()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94TnVtYmVyVG9JbnQAAAAAAAAAAQIAAHhw",
                PrimitiveFunctions.unboxNumberToInt());
    }

    @Test
    public void unboxIntegerToByte()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94SW50ZWdlclRvQnl0ZQAAAAAAAAABAgAAeHA=",
                PrimitiveFunctions.unboxIntegerToByte());
    }

    @Test
    public void unboxIntegerToChar()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94SW50ZWdlclRvQ2hhcgAAAAAAAAABAgAAeHA=",
                PrimitiveFunctions.unboxIntegerToChar());
    }

    @Test
    public void unboxIntegerToInt()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94SW50ZWdlclRvSW50AAAAAAAAAAECAAB4cA==",
                PrimitiveFunctions.unboxIntegerToInt());
    }

    @Test
    public void unboxNumberToFloat()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94TnVtYmVyVG9GbG9hdAAAAAAAAAABAgAAeHA=",
                PrimitiveFunctions.unboxNumberToFloat());
    }

    @Test
    public void unboxNumberToLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94TnVtYmVyVG9Mb25nAAAAAAAAAAECAAB4cA==",
                PrimitiveFunctions.unboxNumberToLong());
    }

    @Test
    public void unboxNumberToDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94TnVtYmVyVG9Eb3VibGUAAAAAAAAAAQIAAHhw",
                PrimitiveFunctions.unboxNumberToDouble());
    }

    @Test
    public void unboxIntegerToFloat()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94SW50ZWdlclRvRmxvYXQAAAAAAAAAAQIAAHhw",
                PrimitiveFunctions.unboxIntegerToFloat());
    }

    @Test
    public void unboxIntegerToLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94SW50ZWdlclRvTG9uZwAAAAAAAAABAgAAeHA=",
                PrimitiveFunctions.unboxIntegerToLong());
    }

    @Test
    public void unboxIntegerToShort()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94SW50ZWdlclRvU2hvcnQAAAAAAAAAAQIAAHhw",
                PrimitiveFunctions.unboxIntegerToShort());
    }

    @Test
    public void unboxIntegerToDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94SW50ZWdlclRvRG91YmxlAAAAAAAAAAECAAB4cA==",
                PrimitiveFunctions.unboxIntegerToDouble());
    }

    @Test
    public void unboxDoubleToDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94RG91YmxlVG9Eb3VibGUAAAAAAAAAAQIAAHhw",
                PrimitiveFunctions.unboxDoubleToDouble());
    }

    @Test
    public void unboxFloatToFloat()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJFVuYm94RmxvYXRUb0Zsb2F0AAAAAAAAAAECAAB4cA==",
                PrimitiveFunctions.unboxFloatToFloat());
    }

    @Test
    public void sumByInt()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJDEAAAAAAAAAAQIAAkwADHZhbCRmdW5jdGlvbnQAQkxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0ludEZ1bmN0aW9uO0wAC3Zh\n"
                        + "bCRncm91cEJ5dAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9G\n"
                        + "dW5jdGlvbjt4cHBw",
                PrimitiveFunctions.sumByIntFunction(null, null));
    }

    @Test
    public void sumByLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJDMAAAAAAAAAAQIAAkwADHZhbCRmdW5jdGlvbnQAQ0xvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0xvbmdGdW5jdGlvbjtMAAt2\n"
                        + "YWwkZ3JvdXBCeXQANUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24v\n"
                        + "RnVuY3Rpb247eHBwcA==",
                PrimitiveFunctions.sumByLongFunction(null, null));
    }

    @Test
    public void sumByFloat()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJDIAAAAAAAAAAQIAA0wADGNvbXBlbnNhdGlvbnQAQkxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1pdGl2ZS9NdXRhYmxlT2JqZWN0RG91YmxlTWFwO0wADHZh\n"
                        + "bCRmdW5jdGlvbnQARExvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24v\n"
                        + "cHJpbWl0aXZlL0Zsb2F0RnVuY3Rpb247TAALdmFsJGdyb3VwQnl0ADVMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO3hwc3IARm9yZy5lY2xpcHNlLmNv\n"
                        + "bGxlY3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLk9iamVjdERvdWJsZUhhc2hNYXAA\n"
                        + "AAAAAAAAAQwAAHhwdwQAAAAAeHBw",
                PrimitiveFunctions.sumByFloatFunction(null, null));
    }

    @Test
    public void sumByDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJpbWl0\n"
                        + "aXZlRnVuY3Rpb25zJDQAAAAAAAAAAQIAA0wADGNvbXBlbnNhdGlvbnQAQkxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1pdGl2ZS9NdXRhYmxlT2JqZWN0RG91YmxlTWFwO0wADHZh\n"
                        + "bCRmdW5jdGlvbnQARUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24v\n"
                        + "cHJpbWl0aXZlL0RvdWJsZUZ1bmN0aW9uO0wAC3ZhbCRncm91cEJ5dAA1TG9yZy9lY2xpcHNlL2Nv\n"
                        + "bGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjt4cHNyAEZvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5PYmplY3REb3VibGVIYXNoTWFw\n"
                        + "AAAAAAAAAAEMAAB4cHcEAAAAAHhwcA==",
                PrimitiveFunctions.sumByDoubleFunction(null, null));
    }
}
