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

import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;
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

    @Test
    public void oneCaseWithDefaultIntProcecure()
    {
        MutableIntList ifOneList = IntLists.mutable.empty();
        MutableIntList defaultList = IntLists.mutable.empty();
        MutableIntList list = IntLists.mutable.with(1, 2);
        IntCaseProcedure procedure =
                new IntCaseProcedure(defaultList::add)
                        .addCase(value -> value == 1, ifOneList::add);

        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuSW50Q2FzZVByb2NlZHVyZQAAAAAAAAABAgACTAAQZGVmYXVsdFByb2NlZHVyZXQARExv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL3ByaW1pdGl2ZS9JbnRQ\n"
                        + "cm9jZWR1cmU7TAATcHJlZGljYXRlUHJvY2VkdXJlc3QALkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9u\n"
                        + "cy9hcGkvbGlzdC9NdXRhYmxlTGlzdDt4cHNyACFqYXZhLmxhbmcuaW52b2tlLlNlcmlhbGl6ZWRM\n"
                        + "YW1iZGFvYdCULCk2hQIACkkADmltcGxNZXRob2RLaW5kWwAMY2FwdHVyZWRBcmdzdAATW0xqYXZh\n"
                        + "L2xhbmcvT2JqZWN0O0wADmNhcHR1cmluZ0NsYXNzdAARTGphdmEvbGFuZy9DbGFzcztMABhmdW5j\n"
                        + "dGlvbmFsSW50ZXJmYWNlQ2xhc3N0ABJMamF2YS9sYW5nL1N0cmluZztMAB1mdW5jdGlvbmFsSW50\n"
                        + "ZXJmYWNlTWV0aG9kTmFtZXEAfgAHTAAiZnVuY3Rpb25hbEludGVyZmFjZU1ldGhvZFNpZ25hdHVy\n"
                        + "ZXEAfgAHTAAJaW1wbENsYXNzcQB+AAdMAA5pbXBsTWV0aG9kTmFtZXEAfgAHTAATaW1wbE1ldGhv\n"
                        + "ZFNpZ25hdHVyZXEAfgAHTAAWaW5zdGFudGlhdGVkTWV0aG9kVHlwZXEAfgAHeHAAAAAJdXIAE1tM\n"
                        + "amF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAAAXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRpdmUuSW50QXJyYXlMaXN0AAAAAAAAAAEMAAB4\n"
                        + "cHcEAAAAAHh2cgBeb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5wcm9jZWR1cmUu\n"
                        + "cHJpbWl0aXZlLlByaW1pdGl2ZUNhc2VQcm9jZWR1cmVTZXJpYWxpemF0aW9uVGVzdAAAAAAAAAAA\n"
                        + "AAAAeHB0AEJvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL3ByaW1p\n"
                        + "dGl2ZS9JbnRQcm9jZWR1cmV0AAV2YWx1ZXQABChJKVZ0AEVvcmcvZWNsaXBzZS9jb2xsZWN0aW9u\n"
                        + "cy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZUludENvbGxlY3Rpb250AANhZGR0AAQo\n"
                        + "SSlacQB+ABFzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5saXN0Lm11dGFibGUuRmFz\n"
                        + "dExpc3QAAAAAAAAAAQwAAHhwdwQAAAABc3IAK29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "dHVwbGUuUGFpckltcGwAAAAAAAAAAQIAAkwAA29uZXQAEkxqYXZhL2xhbmcvT2JqZWN0O0wAA3R3\n"
                        + "b3EAfgAYeHBzcQB+AAQAAAAGdXEAfgAJAAAAAHEAfgAOdABCb3JnL2VjbGlwc2UvY29sbGVjdGlv\n"
                        + "bnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUvSW50UHJlZGljYXRldAAGYWNjZXB0cQB+\n"
                        + "ABR0AF5vcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9pbXBsL2Jsb2NrL3Byb2NlZHVyZS9wcmltaXRp\n"
                        + "dmUvUHJpbWl0aXZlQ2FzZVByb2NlZHVyZVNlcmlhbGl6YXRpb25UZXN0dAAwbGFtYmRhJG9uZUNh\n"
                        + "c2VXaXRoRGVmYXVsdEludFByb2NlY3VyZSQxODg0ZGZjMCQxcQB+ABRxAH4AFHNxAH4ABAAAAAl1\n"
                        + "cQB+AAkAAAABc3EAfgALdwQAAAAAeHEAfgAOcQB+AA9xAH4AEHEAfgARcQB+ABJxAH4AE3EAfgAU\n"
                        + "cQB+ABF4",
                procedure);

        // After the execution the internal state of the procedure should change
        // because ifOneList and defaultList are captured in the lambdas
        list.each(procedure);

        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuSW50Q2FzZVByb2NlZHVyZQAAAAAAAAABAgACTAAQZGVmYXVsdFByb2NlZHVyZXQARExv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL3ByaW1pdGl2ZS9JbnRQ\n"
                        + "cm9jZWR1cmU7TAATcHJlZGljYXRlUHJvY2VkdXJlc3QALkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9u\n"
                        + "cy9hcGkvbGlzdC9NdXRhYmxlTGlzdDt4cHNyACFqYXZhLmxhbmcuaW52b2tlLlNlcmlhbGl6ZWRM\n"
                        + "YW1iZGFvYdCULCk2hQIACkkADmltcGxNZXRob2RLaW5kWwAMY2FwdHVyZWRBcmdzdAATW0xqYXZh\n"
                        + "L2xhbmcvT2JqZWN0O0wADmNhcHR1cmluZ0NsYXNzdAARTGphdmEvbGFuZy9DbGFzcztMABhmdW5j\n"
                        + "dGlvbmFsSW50ZXJmYWNlQ2xhc3N0ABJMamF2YS9sYW5nL1N0cmluZztMAB1mdW5jdGlvbmFsSW50\n"
                        + "ZXJmYWNlTWV0aG9kTmFtZXEAfgAHTAAiZnVuY3Rpb25hbEludGVyZmFjZU1ldGhvZFNpZ25hdHVy\n"
                        + "ZXEAfgAHTAAJaW1wbENsYXNzcQB+AAdMAA5pbXBsTWV0aG9kTmFtZXEAfgAHTAATaW1wbE1ldGhv\n"
                        + "ZFNpZ25hdHVyZXEAfgAHTAAWaW5zdGFudGlhdGVkTWV0aG9kVHlwZXEAfgAHeHAAAAAJdXIAE1tM\n"
                        + "amF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAAAXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRpdmUuSW50QXJyYXlMaXN0AAAAAAAAAAEMAAB4\n"
                        + "cHcIAAAAAQAAAAJ4dnIAXm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2sucHJvY2Vk\n"
                        + "dXJlLnByaW1pdGl2ZS5QcmltaXRpdmVDYXNlUHJvY2VkdXJlU2VyaWFsaXphdGlvblRlc3QAAAAA\n"
                        + "AAAAAAAAAHhwdABCb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9w\n"
                        + "cmltaXRpdmUvSW50UHJvY2VkdXJldAAFdmFsdWV0AAQoSSlWdABFb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVJbnRDb2xsZWN0aW9udAADYWRk\n"
                        + "dAAEKEkpWnEAfgARc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubGlzdC5tdXRhYmxl\n"
                        + "LkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAXNyACtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLnR1cGxlLlBhaXJJbXBsAAAAAAAAAAECAAJMAANvbmV0ABJMamF2YS9sYW5nL09iamVjdDtM\n"
                        + "AAN0d29xAH4AGHhwc3EAfgAEAAAABnVxAH4ACQAAAABxAH4ADnQAQm9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZlL0ludFByZWRpY2F0ZXQABmFjY2Vw\n"
                        + "dHEAfgAUdABeb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvaW1wbC9ibG9jay9wcm9jZWR1cmUvcHJp\n"
                        + "bWl0aXZlL1ByaW1pdGl2ZUNhc2VQcm9jZWR1cmVTZXJpYWxpemF0aW9uVGVzdHQAMGxhbWJkYSRv\n"
                        + "bmVDYXNlV2l0aERlZmF1bHRJbnRQcm9jZWN1cmUkMTg4NGRmYzAkMXEAfgAUcQB+ABRzcQB+AAQA\n"
                        + "AAAJdXEAfgAJAAAAAXNxAH4AC3cIAAAAAQAAAAF4cQB+AA5xAH4AD3EAfgAQcQB+ABFxAH4AEnEA\n"
                        + "fgATcQB+ABRxAH4AEXg=",
                procedure);
    }
}
