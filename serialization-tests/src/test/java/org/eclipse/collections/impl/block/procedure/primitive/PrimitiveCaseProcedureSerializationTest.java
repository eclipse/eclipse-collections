/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class PrimitiveCaseProcedureSerializationTest
{
    @Test
    public void booleanCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuQm9vbGVhbkNhc2VQcm9jZWR1cmUAAAAAAAAAAQIAAkwAEGRlZmF1bHRQcm9jZWR1cmV0\n"
                        + "AEhMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9wcmltaXRpdmUv\n"
                        + "Qm9vbGVhblByb2NlZHVyZTtMABNwcmVkaWNhdGVQcm9jZWR1cmVzdAAuTG9yZy9lY2xpcHNlL2Nv\n"
                        + "bGxlY3Rpb25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwcHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                new BooleanCaseProcedure());
    }

    @Test
    public void byteCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuQnl0ZUNhc2VQcm9jZWR1cmUAAAAAAAAAAQIAAkwAEGRlZmF1bHRQcm9jZWR1cmV0AEVM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9wcmltaXRpdmUvQnl0\n"
                        + "ZVByb2NlZHVyZTtMABNwcmVkaWNhdGVQcm9jZWR1cmVzdAAuTG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwcHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                new ByteCaseProcedure());
    }

    @Test
    public void charCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuQ2hhckNhc2VQcm9jZWR1cmUAAAAAAAAAAQIAAkwAEGRlZmF1bHRQcm9jZWR1cmV0AEVM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9wcmltaXRpdmUvQ2hh\n"
                        + "clByb2NlZHVyZTtMABNwcmVkaWNhdGVQcm9jZWR1cmVzdAAuTG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwcHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                new CharCaseProcedure());
    }

    @Test
    public void shortCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuU2hvcnRDYXNlUHJvY2VkdXJlAAAAAAAAAAECAAJMABBkZWZhdWx0UHJvY2VkdXJldABG\n"
                        + "TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcm9jZWR1cmUvcHJpbWl0aXZlL1No\n"
                        + "b3J0UHJvY2VkdXJlO0wAE3ByZWRpY2F0ZVByb2NlZHVyZXN0AC5Mb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvYXBpL2xpc3QvTXV0YWJsZUxpc3Q7eHBwc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new ShortCaseProcedure());
    }

    @Test
    public void intCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuSW50Q2FzZVByb2NlZHVyZQAAAAAAAAABAgACTAAQZGVmYXVsdFByb2NlZHVyZXQARExv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL3ByaW1pdGl2ZS9JbnRQ\n"
                        + "cm9jZWR1cmU7TAATcHJlZGljYXRlUHJvY2VkdXJlc3QALkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9u\n"
                        + "cy9hcGkvbGlzdC9NdXRhYmxlTGlzdDt4cHBzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5saXN0Lm11dGFibGUuRmFzdExpc3QAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new IntCaseProcedure());
    }

    @Test
    public void floatCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuRmxvYXRDYXNlUHJvY2VkdXJlAAAAAAAAAAECAAJMABBkZWZhdWx0UHJvY2VkdXJldABG\n"
                        + "TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcm9jZWR1cmUvcHJpbWl0aXZlL0Zs\n"
                        + "b2F0UHJvY2VkdXJlO0wAE3ByZWRpY2F0ZVByb2NlZHVyZXN0AC5Mb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvYXBpL2xpc3QvTXV0YWJsZUxpc3Q7eHBwc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new FloatCaseProcedure());
    }

    @Test
    public void longCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuTG9uZ0Nhc2VQcm9jZWR1cmUAAAAAAAAAAQIAAkwAEGRlZmF1bHRQcm9jZWR1cmV0AEVM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9wcmltaXRpdmUvTG9u\n"
                        + "Z1Byb2NlZHVyZTtMABNwcmVkaWNhdGVQcm9jZWR1cmVzdAAuTG9yZy9lY2xpcHNlL2NvbGxlY3Rp\n"
                        + "b25zL2FwaS9saXN0L011dGFibGVMaXN0O3hwcHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                new LongCaseProcedure());
    }

    @Test
    public void doubleCaseProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuRG91YmxlQ2FzZVByb2NlZHVyZQAAAAAAAAABAgACTAAQZGVmYXVsdFByb2NlZHVyZXQA\n"
                        + "R0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL3ByaW1pdGl2ZS9E\n"
                        + "b3VibGVQcm9jZWR1cmU7TAATcHJlZGljYXRlUHJvY2VkdXJlc3QALkxvcmcvZWNsaXBzZS9jb2xs\n"
                        + "ZWN0aW9ucy9hcGkvbGlzdC9NdXRhYmxlTGlzdDt4cHBzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5saXN0Lm11dGFibGUuRmFzdExpc3QAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new DoubleCaseProcedure());
    }
}
