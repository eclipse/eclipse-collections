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

import java.util.Collection;

import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class FunctionsSerializationTest
{
    @Test
    public void throwing()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFRocm93aW5nRnVuY3Rpb25BZGFwdGVyAAAAAAAAAAECAAFMABB0aHJvd2luZ0Z1bmN0aW9u\n"
                        + "dABGTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvYmxvY2svZnVuY3Rpb24vY2hlY2tlZC9U\n"
                        + "aHJvd2luZ0Z1bmN0aW9uO3hyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1\n"
                        + "bmN0aW9uLmNoZWNrZWQuQ2hlY2tlZEZ1bmN0aW9uAAAAAAAAAAECAAB4cHA=",
                Functions.throwing(null));
    }

    @Test
    public void getPassThru()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFBhc3NUaHJ1RnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.getPassThru());
    }

    @Test
    public void getTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFRydWVGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                Functions.getTrue());
    }

    @Test
    public void getFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZhbHNlRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.getFalse());
    }

    @Test
    public void getIntegerPassThru()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEludGVnZXJQYXNzVGhydUZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions.getIntegerPassThru());
    }

    @Test
    public void getLongPassThru()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJExvbmdQYXNzVGhydUZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions.getLongPassThru());
    }

    @Test
    public void getDoublePassThru()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJERvdWJsZVBhc3NUaHJ1RnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.getDoublePassThru());
    }

    @Test
    public void getStringTrim()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFN0cmluZ1RyaW1GdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                Functions.getStringTrim());
    }

    @Test
    public void getFixedValue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZpeGVkVmFsdWVGdW5jdGlvbgAAAAAAAAABAgABTAAFdmFsdWV0ABJMamF2YS9sYW5nL09i\n"
                        + "amVjdDt4cHA=",
                Functions.getFixedValue(null));
    }

    @Test
    public void getToClass()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJENsYXNzRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.getToClass());
    }

    @Test
    public void getMathSinFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJE1hdGhTaW5GdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                Functions.getMathSinFunction());
    }

    @Test
    public void squaredInteger()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFNxdWFyZWRJbnRlZ2VyRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.squaredInteger());
    }

    @Test
    public void getToString()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFRvU3RyaW5nRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.getToString());
    }

    @Test
    public void getStringToInteger()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFN0cmluZ1RvSW50ZWdlckZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions.getStringToInteger());
    }

    @Test
    public void withDefault()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJERlZmF1bHRGdW5jdGlvbgAAAAAAAAABAgACTAAMZGVmYXVsdFZhbHVldAASTGphdmEvbGFu\n"
                        + "Zy9PYmplY3Q7TAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2Nr\n"
                        + "L2Z1bmN0aW9uL0Z1bmN0aW9uO3hwcHNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJs\n"
                        + "b2NrLmZhY3RvcnkuRnVuY3Rpb25zJFBhc3NUaHJ1RnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.withDefault(Functions.getPassThru(), null));
    }

    @Test
    public void nullSafe()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJE51bGxTYWZlRnVuY3Rpb24AAAAAAAAAAQIAAkwACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNl\n"
                        + "L2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjtMAAludWxsVmFsdWV0ABJM\n"
                        + "amF2YS9sYW5nL09iamVjdDt4cHNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuRnVuY3Rpb25zJFBhc3NUaHJ1RnVuY3Rpb24AAAAAAAAAAQIAAHhwcA==",
                Functions.nullSafe(Functions.getPassThru()));
    }

    @Test
    public void firstNotNullValue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZpcnN0Tm90TnVsbEZ1bmN0aW9uAAAAAAAAAAECAAFbAAlmdW5jdGlvbnN0ADZbTG9yZy9l\n"
                        + "Y2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjt4cHVyADZbTG9y\n"
                        + "Zy5lY2xpcHNlLmNvbGxlY3Rpb25zLmFwaS5ibG9jay5mdW5jdGlvbi5GdW5jdGlvbjvtku5jQqEU\n"
                        + "iQIAAHhwAAAAAA==",
                Functions.firstNotNullValue());
    }

    @Test
    public void firstNotEmptyStringValue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZpcnN0Tm90RW1wdHlTdHJpbmdGdW5jdGlvbgAAAAAAAAABAgABWwAJZnVuY3Rpb25zdAA2\n"
                        + "W0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247eHB1\n"
                        + "cgA2W0xvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suZnVuY3Rpb24uRnVuY3Rpb247\n"
                        + "7ZLuY0KhFIkCAAB4cAAAAAA=",
                Functions.firstNotEmptyStringValue());
    }

    @Test
    public void firstNotEmptyCollectionValue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZpcnN0Tm90RW1wdHlDb2xsZWN0aW9uRnVuY3Rpb24AAAAAAAAAAQIAAVsACWZ1bmN0aW9u\n"
                        + "c3QANltMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9u\n"
                        + "O3hwdXIANltMb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuYXBpLmJsb2NrLmZ1bmN0aW9uLkZ1bmN0\n"
                        + "aW9uO+2S7mNCoRSJAgAAeHAAAAAA",
                Functions.<Integer, String, Collection<String>>firstNotEmptyCollectionValue());
    }

    @Test
    public void synchronizedEach()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFN5bmNocm9uaXplZEZ1bmN0aW9uAAAAAAAAAAECAAFMAAhmdW5jdGlvbnQANUxvcmcvZWNs\n"
                        + "aXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247eHBw",
                Functions.synchronizedEach(null));
    }

    @Test
    public void bind_procedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEJpbmRQcm9jZWR1cmUAAAAAAAAAAQIAAkwACGRlbGVnYXRldAA3TG9yZy9lY2xpcHNlL2Nv\n"
                        + "bGxlY3Rpb25zL2FwaS9ibG9jay9wcm9jZWR1cmUvUHJvY2VkdXJlO0wACGZ1bmN0aW9udAA1TG9y\n"
                        + "Zy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjt4cHBw",
                Functions.bind((Procedure<Object>) null, null));
    }

    @Test
    public void bind_procedure2()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEJpbmRQcm9jZWR1cmUyAAAAAAAAAAECAAJMAAhkZWxlZ2F0ZXQAOExvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL1Byb2NlZHVyZTI7TAAIZnVuY3Rpb250ADVM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO3hwcHA=\n",
                Functions.bind((Procedure2<Object, Object>) null, null));
    }

    @Test
    public void bind_object_int_procedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEJpbmRPYmplY3RJbnRQcm9jZWR1cmUAAAAAAAAAAQIAAkwACGRlbGVnYXRldABKTG9yZy9l\n"
                        + "Y2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcm9jZWR1cmUvcHJpbWl0aXZlL09iamVjdElu\n"
                        + "dFByb2NlZHVyZTtMAAhmdW5jdGlvbnQANUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxv\n"
                        + "Y2svZnVuY3Rpb24vRnVuY3Rpb247eHBwcA==",
                Functions.bind((ObjectIntProcedure<Object>) null, null));
    }

    @Test
    public void bind_function2_parameter()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEJpbmRGdW5jdGlvbjIAAAAAAAAAAQIAAkwACGRlbGVnYXRldAA2TG9yZy9lY2xpcHNlL2Nv\n"
                        + "bGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjI7TAAJcGFyYW1ldGVydAASTGph\n"
                        + "dmEvbGFuZy9PYmplY3Q7eHBwcA==",
                Functions.bind((Function2<Object, Object, Object>) null, null));
    }

    @Test
    public void getKeyFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJE1hcEtleUZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions.getKeyFunction());
    }

    @Test
    public void getValueFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJE1hcFZhbHVlRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.getValueFunction());
    }

    @Test
    public void getSizeOf()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFNpemVGdW5jdGlvbgAAAAAAAAABAgAAeHIASW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmlt\n"
                        + "cGwuYmxvY2suZnVuY3Rpb24ucHJpbWl0aXZlLkludGVnZXJGdW5jdGlvbkltcGwAAAAAAAAAAQIA\n"
                        + "AHhw",
                Functions.getSizeOf());
    }

    @Test
    public void chain()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZ1bmN0aW9uQ2hhaW4AAAAAAAAAAQIAAkwACWZ1bmN0aW9uMXQANUxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAAJZnVuY3Rpb24ycQB+AAF4\n"
                        + "cHBw",
                Functions.chain(null, null));
    }

    @Test
    public void chainChain()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZ1bmN0aW9uQ2hhaW4AAAAAAAAAAQIAAkwACWZ1bmN0aW9uMXQANUxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAAJZnVuY3Rpb24ycQB+AAF4\n"
                        + "cHNxAH4AAHBwcA==",
                Functions.chain(null, null).chain(null));
    }

    @Test
    public void chainBoolean()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEJvb2xlYW5GdW5jdGlvbkNoYWluAAAAAAAAAAECAAJMAAlmdW5jdGlvbjF0ADVMb3JnL2Vj\n"
                        + "bGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO0wACWZ1bmN0aW9u\n"
                        + "MnQARkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZl\n"
                        + "L0Jvb2xlYW5GdW5jdGlvbjt4cHBw",
                Functions.chainBoolean(null, null));
    }

    @Test
    public void chainByte()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEJ5dGVGdW5jdGlvbkNoYWluAAAAAAAAAAECAAJMAAlmdW5jdGlvbjF0ADVMb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO0wACWZ1bmN0aW9uMnQA\n"
                        + "Q0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0J5\n"
                        + "dGVGdW5jdGlvbjt4cHBw",
                Functions.chainByte(null, null));
    }

    @Test
    public void chainChar()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJENoYXJGdW5jdGlvbkNoYWluAAAAAAAAAAECAAJMAAlmdW5jdGlvbjF0ADVMb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO0wACWZ1bmN0aW9uMnQA\n"
                        + "Q0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0No\n"
                        + "YXJGdW5jdGlvbjt4cHBw",
                Functions.chainChar(null, null));
    }

    @Test
    public void chainDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJERvdWJsZUZ1bmN0aW9uQ2hhaW4AAAAAAAAAAQIAAkwACWZ1bmN0aW9uMXQANUxvcmcvZWNs\n"
                        + "aXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAAJZnVuY3Rpb24y\n"
                        + "dABFTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUv\n"
                        + "RG91YmxlRnVuY3Rpb247eHBwcA==",
                Functions.chainDouble(null, null));
    }

    @Test
    public void chainFloat()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZsb2F0RnVuY3Rpb25DaGFpbgAAAAAAAAABAgACTAAJZnVuY3Rpb24xdAA1TG9yZy9lY2xp\n"
                        + "cHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjtMAAlmdW5jdGlvbjJ0\n"
                        + "AERMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9G\n"
                        + "bG9hdEZ1bmN0aW9uO3hwcHA=",
                Functions.chainFloat(null, null));
    }

    @Test
    public void chainInt()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEludEZ1bmN0aW9uQ2hhaW4AAAAAAAAAAQIAAkwACWZ1bmN0aW9uMXQANUxvcmcvZWNsaXBz\n"
                        + "ZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAAJZnVuY3Rpb24ydABC\n"
                        + "TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUvSW50\n"
                        + "RnVuY3Rpb247eHBwcA==",
                Functions.chainInt(null, null));
    }

    @Test
    public void chainLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJExvbmdGdW5jdGlvbkNoYWluAAAAAAAAAAECAAJMAAlmdW5jdGlvbjF0ADVMb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO0wACWZ1bmN0aW9uMnQA\n"
                        + "Q0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0xv\n"
                        + "bmdGdW5jdGlvbjt4cHBw",
                Functions.chainLong(null, null));
    }

    @Test
    public void chainShort()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFNob3J0RnVuY3Rpb25DaGFpbgAAAAAAAAABAgACTAAJZnVuY3Rpb24xdAA1TG9yZy9lY2xp\n"
                        + "cHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjtMAAlmdW5jdGlvbjJ0\n"
                        + "AERMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9T\n"
                        + "aG9ydEZ1bmN0aW9uO3hwcHA=",
                Functions.chainShort(null, null));
    }

    @Test
    public void getOneFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJEZpcnN0T2ZQYWlyRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.firstOfPair());
    }

    @Test
    public void getTwoFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFNlY29uZE9mUGFpckZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions.secondOfPair());
    }

    @Test
    public void classForName()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJENsYXNzRm9yTmFtZUZ1bmN0aW9uAAAAAAAAAAECAAB4cgBDb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5ibG9jay5mdW5jdGlvbi5jaGVja2VkLkNoZWNrZWRGdW5jdGlvbgAAAAAAAAAB\n"
                        + "AgAAeHA=",
                Functions.classForName());
    }

    @Test
    public void getSwappedPairFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zJFN3YXBwZWRQYWlyRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions.swappedPair());
    }
}
