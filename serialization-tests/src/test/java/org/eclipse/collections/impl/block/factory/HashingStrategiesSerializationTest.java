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

public class HashingStrategiesSerializationTest
{
    @Test
    public void defaultHashingStrategy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkRGVmYXVsdFN0cmF0ZWd5AAAAAAAAAAECAAB4cA==",
                HashingStrategies.defaultStrategy());
    }

    @Test
    public void nullSafeHashingStrategy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkTnVsbFNhZmVIYXNoaW5nU3RyYXRlZ3kAAAAAAAAAAQIAAUwAE25vbk51bGxT\n"
                        + "YWZlU3RyYXRlZ3l0ADNMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL0hhc2hpbmdT\n"
                        + "dHJhdGVneTt4cHNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rvcnku\n"
                        + "SGFzaGluZ1N0cmF0ZWdpZXMkRGVmYXVsdFN0cmF0ZWd5AAAAAAAAAAECAAB4cA==",
                HashingStrategies.nullSafeHashingStrategy(HashingStrategies.defaultStrategy()));
    }

    @Test
    public void nullSafeFromFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkTnVsbFNhZmVGdW5jdGlvbkhhc2hpbmdTdHJhdGVneQAAAAAAAAABAgABTAAI\n"
                        + "ZnVuY3Rpb250ADVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1\n"
                        + "bmN0aW9uO3hwc3IARW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5G\n"
                        + "dW5jdGlvbnMkVG9TdHJpbmdGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                HashingStrategies.nullSafeFromFunction(Functions.getToString()));
    }

    @Test
    public void fromFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkRnVuY3Rpb25IYXNoaW5nU3RyYXRlZ3kAAAAAAAAAAQIAAUwACGZ1bmN0aW9u\n"
                        + "dAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjt4\n"
                        + "cHNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rpb25z\n"
                        + "JFRvU3RyaW5nRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                HashingStrategies.fromFunction(Functions.getToString()));
    }

    @Test
    public void identityHashingStrategy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkSWRlbnRpdHlIYXNoaW5nU3RyYXRlZ3kAAAAAAAAAAQIAAHhw",
                HashingStrategies.identityStrategy());
    }

    @Test
    public void chainedHashingStrategy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkQ2hhaW5lZEhhc2hpbmdTdHJhdGVneQAAAAAAAAABAgABWwARaGFzaGluZ1N0\n"
                        + "cmF0ZWdpZXN0ADRbTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9IYXNoaW5nU3Ry\n"
                        + "YXRlZ3k7eHB1cgA0W0xvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suSGFzaGluZ1N0\n"
                        + "cmF0ZWd5O95/YgQKnz/HAgAAeHAAAAABc3IAVG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "YmxvY2suZmFjdG9yeS5IYXNoaW5nU3RyYXRlZ2llcyRJZGVudGl0eUhhc2hpbmdTdHJhdGVneQAA\n"
                        + "AAAAAAABAgAAeHA=",
                HashingStrategies.chain(HashingStrategies.identityStrategy()));
    }

    @Test
    public void fromBooleanFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkQm9vbGVhbkZ1bmN0aW9uSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAECAAFMAAhm\n"
                        + "dW5jdGlvbnQARkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJp\n"
                        + "bWl0aXZlL0Jvb2xlYW5GdW5jdGlvbjt4cHNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLmZhY3RvcnkuU3RyaW5nRnVuY3Rpb25zJFRvUHJpbWl0aXZlQm9vbGVhbkZ1bmN0aW9u\n"
                        + "AAAAAAAAAAECAAB4cA==",
                HashingStrategies.fromBooleanFunction(StringFunctions.toPrimitiveBoolean()));
    }

    @Test
    public void fromByteFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkQnl0ZUZ1bmN0aW9uSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAECAAFMAAhmdW5j\n"
                        + "dGlvbnQAQ0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0\n"
                        + "aXZlL0J5dGVGdW5jdGlvbjt4cHNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuU3RyaW5nRnVuY3Rpb25zJFRvUHJpbWl0aXZlQnl0ZUZ1bmN0aW9uAAAAAAAAAAEC\n"
                        + "AAB4cA==",
                HashingStrategies.fromByteFunction(StringFunctions.toPrimitiveByte()));
    }

    @Test
    public void fromCharFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkQ2hhckZ1bmN0aW9uSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAECAAFMAAhmdW5j\n"
                        + "dGlvbnQAQ0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0\n"
                        + "aXZlL0NoYXJGdW5jdGlvbjt4cHNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuU3RyaW5nRnVuY3Rpb25zJFRvUHJpbWl0aXZlQ2hhckZ1bmN0aW9uAAAAAAAAAAEC\n"
                        + "AAB4cA==",
                HashingStrategies.fromCharFunction(StringFunctions.toPrimitiveChar()));
    }

    @Test
    public void fromDoubleFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkRG91YmxlRnVuY3Rpb25IYXNoaW5nU3RyYXRlZ3kAAAAAAAAAAQIAAUwACGZ1\n"
                        + "bmN0aW9udABFTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmlt\n"
                        + "aXRpdmUvRG91YmxlRnVuY3Rpb247eHBzcgBUb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LlN0cmluZ0Z1bmN0aW9ucyRUb1ByaW1pdGl2ZURvdWJsZUZ1bmN0aW9uAAAA\n"
                        + "AAAAAAECAAB4cA==",
                HashingStrategies.fromDoubleFunction(StringFunctions.toPrimitiveDouble()));
    }

    @Test
    public void fromFloatFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkRmxvYXRGdW5jdGlvbkhhc2hpbmdTdHJhdGVneQAAAAAAAAABAgABTAAIZnVu\n"
                        + "Y3Rpb250AERMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1p\n"
                        + "dGl2ZS9GbG9hdEZ1bmN0aW9uO3hwc3IAU29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5TdHJpbmdGdW5jdGlvbnMkVG9QcmltaXRpdmVGbG9hdEZ1bmN0aW9uAAAAAAAA\n"
                        + "AAECAAB4cA==",
                HashingStrategies.fromFloatFunction(StringFunctions.toPrimitiveFloat()));
    }

    @Test
    public void fromIntFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkSW50RnVuY3Rpb25IYXNoaW5nU3RyYXRlZ3kAAAAAAAAAAQIAAUwACGZ1bmN0\n"
                        + "aW9udABCTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRp\n"
                        + "dmUvSW50RnVuY3Rpb247eHBzcgBRb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5m\n"
                        + "YWN0b3J5LlN0cmluZ0Z1bmN0aW9ucyRUb1ByaW1pdGl2ZUludEZ1bmN0aW9uAAAAAAAAAAECAAB4\n"
                        + "cA==",
                HashingStrategies.fromIntFunction(StringFunctions.toPrimitiveInt()));
    }

    @Test
    public void fromLongFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkTG9uZ0Z1bmN0aW9uSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAECAAFMAAhmdW5j\n"
                        + "dGlvbnQAQ0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0\n"
                        + "aXZlL0xvbmdGdW5jdGlvbjt4cHNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuU3RyaW5nRnVuY3Rpb25zJFRvUHJpbWl0aXZlTG9uZ0Z1bmN0aW9uAAAAAAAAAAEC\n"
                        + "AAB4cA==",
                HashingStrategies.fromLongFunction(StringFunctions.toPrimitiveLong()));
    }

    @Test
    public void fromShortFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGlu\n"
                        + "Z1N0cmF0ZWdpZXMkU2hvcnRGdW5jdGlvbkhhc2hpbmdTdHJhdGVneQAAAAAAAAABAgABTAAIZnVu\n"
                        + "Y3Rpb250AERMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1p\n"
                        + "dGl2ZS9TaG9ydEZ1bmN0aW9uO3hwc3IAU29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5TdHJpbmdGdW5jdGlvbnMkVG9QcmltaXRpdmVTaG9ydEZ1bmN0aW9uAAAAAAAA\n"
                        + "AAECAAB4cA==",
                HashingStrategies.fromShortFunction(StringFunctions.toPrimitiveShort()));
    }
}
