/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class SynchronizedByteCharMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRCeXRlQ2hhck1hcAAAAAAAAAABAgACTAAEbG9ja3QAEkxqYXZhL2xhbmcv\n"
                        + "T2JqZWN0O0wAA21hcHQAPkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1pdGl2\n"
                        + "ZS9NdXRhYmxlQnl0ZUNoYXJNYXA7eHBxAH4AA3NyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5CeXRlQ2hhckhhc2hNYXAAAAAAAAAAAQwAAHhwdwQA\n"
                        + "AAAAeA==",
                new SynchronizedByteCharMap(new ByteCharHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRCeXRlU2V0AAAAAAAAAAECAAB4cgBcb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5jb2xsZWN0aW9uLm11dGFibGUucHJpbWl0aXZlLkFic3RyYWN0U3luY2hyb25pemVk\n"
                        + "Qnl0ZUNvbGxlY3Rpb24AAAAAAAAAAQIAAkwACmNvbGxlY3Rpb250AEhMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVCeXRlQ29sbGVjdGlvbjtM\n"
                        + "AARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBTb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RNdXRhYmxlQnl0ZUtleVNldCRTZXJS\n"
                        + "ZXAAAAAAAAAAAQwAAHhwdwQAAAAAeHNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1h\n"
                        + "cC5tdXRhYmxlLnByaW1pdGl2ZS5TeW5jaHJvbml6ZWRCeXRlQ2hhck1hcAAAAAAAAAABAgACTAAE\n"
                        + "bG9ja3EAfgADTAADbWFwdAA+TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJpbWl0\n"
                        + "aXZlL011dGFibGVCeXRlQ2hhck1hcDt4cHEAfgAJc3IAQm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLkJ5dGVDaGFySGFzaE1hcAAAAAAAAAABDAAAeHB3\n"
                        + "BAAAAAB4",
                new SynchronizedByteCharMap(new ByteCharHashMap()).keySet());
    }
}
