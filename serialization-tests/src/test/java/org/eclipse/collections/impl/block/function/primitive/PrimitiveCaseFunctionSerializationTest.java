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

    @Test
    public void fizzBuzz()
    {
        IntCaseFunction<String> function = new IntCaseFunction<>(e -> "")
                .addCase(e -> e % 15 == 0, e -> "FizzBuzz")
                .addCase(e -> e % 3 == 0, e -> "Fizz")
                .addCase(e -> e % 5 == 0, e -> "Buzz");

        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5JbnRDYXNlRnVuY3Rpb24AAAAAAAAAAQIAAkwAD2RlZmF1bHRGdW5jdGlvbnQASkxvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0ludFRvT2Jq\n"
                        + "ZWN0RnVuY3Rpb247TAAScHJlZGljYXRlRnVuY3Rpb25zdAAuTG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwc3IAIWphdmEubGFuZy5pbnZva2UuU2VyaWFsaXpl\n"
                        + "ZExhbWJkYW9h0JQsKTaFAgAKSQAOaW1wbE1ldGhvZEtpbmRbAAxjYXB0dXJlZEFyZ3N0ABNbTGph\n"
                        + "dmEvbGFuZy9PYmplY3Q7TAAOY2FwdHVyaW5nQ2xhc3N0ABFMamF2YS9sYW5nL0NsYXNzO0wAGGZ1\n"
                        + "bmN0aW9uYWxJbnRlcmZhY2VDbGFzc3QAEkxqYXZhL2xhbmcvU3RyaW5nO0wAHWZ1bmN0aW9uYWxJ\n"
                        + "bnRlcmZhY2VNZXRob2ROYW1lcQB+AAdMACJmdW5jdGlvbmFsSW50ZXJmYWNlTWV0aG9kU2lnbmF0\n"
                        + "dXJlcQB+AAdMAAlpbXBsQ2xhc3NxAH4AB0wADmltcGxNZXRob2ROYW1lcQB+AAdMABNpbXBsTWV0\n"
                        + "aG9kU2lnbmF0dXJlcQB+AAdMABZpbnN0YW50aWF0ZWRNZXRob2RUeXBlcQB+AAd4cAAAAAZ1cgAT\n"
                        + "W0xqYXZhLmxhbmcuT2JqZWN0O5DOWJ8QcylsAgAAeHAAAAAAdnIAXG9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZnVuY3Rpb24ucHJpbWl0aXZlLlByaW1pdGl2ZUNhc2VGdW5jdGlv\n"
                        + "blNlcmlhbGl6YXRpb25UZXN0AAAAAAAAAAAAAAB4cHQASG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25z\n"
                        + "L2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUvSW50VG9PYmplY3RGdW5jdGlvbnQAB3ZhbHVl\n"
                        + "T2Z0ABUoSSlMamF2YS9sYW5nL09iamVjdDt0AFxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9pbXBs\n"
                        + "L2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9QcmltaXRpdmVDYXNlRnVuY3Rpb25TZXJpYWxpemF0\n"
                        + "aW9uVGVzdHQAGmxhbWJkYSRmaXp6QnV6eiRlODM4OTAyZCQxdAAVKEkpTGphdmEvbGFuZy9TdHJp\n"
                        + "bmc7cQB+ABJzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5saXN0Lm11dGFibGUuRmFz\n"
                        + "dExpc3QAAAAAAAAAAQwAAHhwdwQAAAADc3IAK29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "dHVwbGUuUGFpckltcGwAAAAAAAAAAQIAAkwAA29uZXQAEkxqYXZhL2xhbmcvT2JqZWN0O0wAA3R3\n"
                        + "b3EAfgAWeHBzcQB+AAQAAAAGdXEAfgAJAAAAAHEAfgAMdABCb3JnL2VjbGlwc2UvY29sbGVjdGlv\n"
                        + "bnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUvSW50UHJlZGljYXRldAAGYWNjZXB0dAAE\n"
                        + "KEkpWnEAfgAQdAAabGFtYmRhJGZpenpCdXp6JGNlNTYyOGFmJDFxAH4AHHEAfgAcc3EAfgAEAAAA\n"
                        + "BnVxAH4ACQAAAABxAH4ADHEAfgANcQB+AA5xAH4AD3EAfgAQdAAabGFtYmRhJGZpenpCdXp6JGU4\n"
                        + "Mzg5MDJkJDJxAH4AEnEAfgASc3EAfgAVc3EAfgAEAAAABnVxAH4ACQAAAABxAH4ADHEAfgAacQB+\n"
                        + "ABtxAH4AHHEAfgAQdAAabGFtYmRhJGZpenpCdXp6JGNlNTYyOGFmJDJxAH4AHHEAfgAcc3EAfgAE\n"
                        + "AAAABnVxAH4ACQAAAABxAH4ADHEAfgANcQB+AA5xAH4AD3EAfgAQdAAabGFtYmRhJGZpenpCdXp6\n"
                        + "JGU4Mzg5MDJkJDNxAH4AEnEAfgASc3EAfgAVc3EAfgAEAAAABnVxAH4ACQAAAABxAH4ADHEAfgAa\n"
                        + "cQB+ABtxAH4AHHEAfgAQdAAabGFtYmRhJGZpenpCdXp6JGNlNTYyOGFmJDNxAH4AHHEAfgAcc3EA\n"
                        + "fgAEAAAABnVxAH4ACQAAAABxAH4ADHEAfgANcQB+AA5xAH4AD3EAfgAQdAAabGFtYmRhJGZpenpC\n"
                        + "dXp6JGU4Mzg5MDJkJDRxAH4AEnEAfgASeA==",
                function);

        // Executing the function should not change the serialized state of the function because it is stateless
        function.valueOf(15);

        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLnByaW1p\n"
                        + "dGl2ZS5JbnRDYXNlRnVuY3Rpb24AAAAAAAAAAQIAAkwAD2RlZmF1bHRGdW5jdGlvbnQASkxvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0ludFRvT2Jq\n"
                        + "ZWN0RnVuY3Rpb247TAAScHJlZGljYXRlRnVuY3Rpb25zdAAuTG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwc3IAIWphdmEubGFuZy5pbnZva2UuU2VyaWFsaXpl\n"
                        + "ZExhbWJkYW9h0JQsKTaFAgAKSQAOaW1wbE1ldGhvZEtpbmRbAAxjYXB0dXJlZEFyZ3N0ABNbTGph\n"
                        + "dmEvbGFuZy9PYmplY3Q7TAAOY2FwdHVyaW5nQ2xhc3N0ABFMamF2YS9sYW5nL0NsYXNzO0wAGGZ1\n"
                        + "bmN0aW9uYWxJbnRlcmZhY2VDbGFzc3QAEkxqYXZhL2xhbmcvU3RyaW5nO0wAHWZ1bmN0aW9uYWxJ\n"
                        + "bnRlcmZhY2VNZXRob2ROYW1lcQB+AAdMACJmdW5jdGlvbmFsSW50ZXJmYWNlTWV0aG9kU2lnbmF0\n"
                        + "dXJlcQB+AAdMAAlpbXBsQ2xhc3NxAH4AB0wADmltcGxNZXRob2ROYW1lcQB+AAdMABNpbXBsTWV0\n"
                        + "aG9kU2lnbmF0dXJlcQB+AAdMABZpbnN0YW50aWF0ZWRNZXRob2RUeXBlcQB+AAd4cAAAAAZ1cgAT\n"
                        + "W0xqYXZhLmxhbmcuT2JqZWN0O5DOWJ8QcylsAgAAeHAAAAAAdnIAXG9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZnVuY3Rpb24ucHJpbWl0aXZlLlByaW1pdGl2ZUNhc2VGdW5jdGlv\n"
                        + "blNlcmlhbGl6YXRpb25UZXN0AAAAAAAAAAAAAAB4cHQASG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25z\n"
                        + "L2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUvSW50VG9PYmplY3RGdW5jdGlvbnQAB3ZhbHVl\n"
                        + "T2Z0ABUoSSlMamF2YS9sYW5nL09iamVjdDt0AFxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9pbXBs\n"
                        + "L2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9QcmltaXRpdmVDYXNlRnVuY3Rpb25TZXJpYWxpemF0\n"
                        + "aW9uVGVzdHQAGmxhbWJkYSRmaXp6QnV6eiRlODM4OTAyZCQxdAAVKEkpTGphdmEvbGFuZy9TdHJp\n"
                        + "bmc7cQB+ABJzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5saXN0Lm11dGFibGUuRmFz\n"
                        + "dExpc3QAAAAAAAAAAQwAAHhwdwQAAAADc3IAK29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "dHVwbGUuUGFpckltcGwAAAAAAAAAAQIAAkwAA29uZXQAEkxqYXZhL2xhbmcvT2JqZWN0O0wAA3R3\n"
                        + "b3EAfgAWeHBzcQB+AAQAAAAGdXEAfgAJAAAAAHEAfgAMdABCb3JnL2VjbGlwc2UvY29sbGVjdGlv\n"
                        + "bnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUvSW50UHJlZGljYXRldAAGYWNjZXB0dAAE\n"
                        + "KEkpWnEAfgAQdAAabGFtYmRhJGZpenpCdXp6JGNlNTYyOGFmJDFxAH4AHHEAfgAcc3EAfgAEAAAA\n"
                        + "BnVxAH4ACQAAAABxAH4ADHEAfgANcQB+AA5xAH4AD3EAfgAQdAAabGFtYmRhJGZpenpCdXp6JGU4\n"
                        + "Mzg5MDJkJDJxAH4AEnEAfgASc3EAfgAVc3EAfgAEAAAABnVxAH4ACQAAAABxAH4ADHEAfgAacQB+\n"
                        + "ABtxAH4AHHEAfgAQdAAabGFtYmRhJGZpenpCdXp6JGNlNTYyOGFmJDJxAH4AHHEAfgAcc3EAfgAE\n"
                        + "AAAABnVxAH4ACQAAAABxAH4ADHEAfgANcQB+AA5xAH4AD3EAfgAQdAAabGFtYmRhJGZpenpCdXp6\n"
                        + "JGU4Mzg5MDJkJDNxAH4AEnEAfgASc3EAfgAVc3EAfgAEAAAABnVxAH4ACQAAAABxAH4ADHEAfgAa\n"
                        + "cQB+ABtxAH4AHHEAfgAQdAAabGFtYmRhJGZpenpCdXp6JGNlNTYyOGFmJDNxAH4AHHEAfgAcc3EA\n"
                        + "fgAEAAAABnVxAH4ACQAAAABxAH4ADHEAfgANcQB+AA5xAH4AD3EAfgAQdAAabGFtYmRhJGZpenpC\n"
                        + "dXp6JGU4Mzg5MDJkJDRxAH4AEnEAfgASeA==",
                function);
    }
}
