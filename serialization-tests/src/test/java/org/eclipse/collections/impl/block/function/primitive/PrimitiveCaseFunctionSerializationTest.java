/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class PrimitiveCaseFunctionSerializationTest
{
    @Test
    public void booleanCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5Cb29sZWFuQ2FzZUZ1bmN0aW9uAAAAAAAAAAECAAJMAA9kZWZhdWx0RnVuY3Rpb250AE5M\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9Cb29s\n"
                        + "ZWFuVG9PYmplY3RGdW5jdGlvbjtMABJwcmVkaWNhdGVGdW5jdGlvbnN0AC5Mb3JnL2VjbGlwc2Uv\n"
                        + "Y29sbGVjdGlvbnMvYXBpL2xpc3QvTXV0YWJsZUxpc3Q7eHBwc3IAMm9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new BooleanCaseFunction<>());
    }

    @Test
    public void byteCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5CeXRlQ2FzZUZ1bmN0aW9uAAAAAAAAAAECAAJMAA9kZWZhdWx0RnVuY3Rpb250AEtMb3Jn\n"
                        + "L2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9CeXRlVG9P\n"
                        + "YmplY3RGdW5jdGlvbjtMABJwcmVkaWNhdGVGdW5jdGlvbnN0AC5Mb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvYXBpL2xpc3QvTXV0YWJsZUxpc3Q7eHBwc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new ByteCaseFunction<>());
    }

    @Test
    public void charCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5DaGFyQ2FzZUZ1bmN0aW9uAAAAAAAAAAECAAJMAA9kZWZhdWx0RnVuY3Rpb250AEtMb3Jn\n"
                        + "L2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9DaGFyVG9P\n"
                        + "YmplY3RGdW5jdGlvbjtMABJwcmVkaWNhdGVGdW5jdGlvbnN0AC5Mb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvYXBpL2xpc3QvTXV0YWJsZUxpc3Q7eHBwc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new CharCaseFunction<>());
    }

    @Test
    public void shortCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5TaG9ydENhc2VGdW5jdGlvbgAAAAAAAAABAgACTAAPZGVmYXVsdEZ1bmN0aW9udABMTG9y\n"
                        + "Zy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUvU2hvcnRU\n"
                        + "b09iamVjdEZ1bmN0aW9uO0wAEnByZWRpY2F0ZUZ1bmN0aW9uc3QALkxvcmcvZWNsaXBzZS9jb2xs\n"
                        + "ZWN0aW9ucy9hcGkvbGlzdC9NdXRhYmxlTGlzdDt4cHBzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5saXN0Lm11dGFibGUuRmFzdExpc3QAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new ShortCaseFunction<>());
    }

    @Test
    public void intCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5JbnRDYXNlRnVuY3Rpb24AAAAAAAAAAQIAAkwAD2RlZmF1bHRGdW5jdGlvbnQASkxvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0ludFRvT2Jq\n"
                        + "ZWN0RnVuY3Rpb247TAAScHJlZGljYXRlRnVuY3Rpb25zdAAuTG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwcHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                new IntCaseFunction<>());
    }

    @Test
    public void floatCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5GbG9hdENhc2VGdW5jdGlvbgAAAAAAAAABAgACTAAPZGVmYXVsdEZ1bmN0aW9udABMTG9y\n"
                        + "Zy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUvRmxvYXRU\n"
                        + "b09iamVjdEZ1bmN0aW9uO0wAEnByZWRpY2F0ZUZ1bmN0aW9uc3QALkxvcmcvZWNsaXBzZS9jb2xs\n"
                        + "ZWN0aW9ucy9hcGkvbGlzdC9NdXRhYmxlTGlzdDt4cHBzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5saXN0Lm11dGFibGUuRmFzdExpc3QAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new FloatCaseFunction<>());
    }

    @Test
    public void longCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5Mb25nQ2FzZUZ1bmN0aW9uAAAAAAAAAAECAAJMAA9kZWZhdWx0RnVuY3Rpb250AEtMb3Jn\n"
                        + "L2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9Mb25nVG9P\n"
                        + "YmplY3RGdW5jdGlvbjtMABJwcmVkaWNhdGVGdW5jdGlvbnN0AC5Mb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvYXBpL2xpc3QvTXV0YWJsZUxpc3Q7eHBwc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new LongCaseFunction<>());
    }

    @Test
    public void doubleCaseFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5Eb3VibGVDYXNlRnVuY3Rpb24AAAAAAAAAAQIAAkwAD2RlZmF1bHRGdW5jdGlvbnQATUxv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0RvdWJs\n"
                        + "ZVRvT2JqZWN0RnVuY3Rpb247TAAScHJlZGljYXRlRnVuY3Rpb25zdAAuTG9yZy9lY2xpcHNlL2Nv\n"
                        + "bGxlY3Rpb25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwcHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                new DoubleCaseFunction<>());
    }
}
