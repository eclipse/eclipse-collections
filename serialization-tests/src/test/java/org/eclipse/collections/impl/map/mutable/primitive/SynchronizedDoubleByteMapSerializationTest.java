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
import org.junit.Test;

public class SynchronizedDoubleByteMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWREb3VibGVCeXRlTWFwAAAAAAAAAAECAAJMAARsb2NrdAASTGphdmEvbGFu\n"
                        + "Zy9PYmplY3Q7TAADbWFwdABATG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJpbWl0\n"
                        + "aXZlL011dGFibGVEb3VibGVCeXRlTWFwO3hwcQB+AANzcgBEb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuRG91YmxlQnl0ZUhhc2hNYXAAAAAAAAAAAQwA\n"
                        + "AHhwdwQAAAAAeA==",
                new SynchronizedDoubleByteMap(new DoubleByteHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWREb3VibGVTZXQAAAAAAAAAAQIAAHhyAF5vcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RTeW5jaHJvbml6\n"
                        + "ZWREb3VibGVDb2xsZWN0aW9uAAAAAAAAAAECAAJMAApjb2xsZWN0aW9udABKTG9yZy9lY2xpcHNl\n"
                        + "L2NvbGxlY3Rpb25zL2FwaS9jb2xsZWN0aW9uL3ByaW1pdGl2ZS9NdXRhYmxlRG91YmxlQ29sbGVj\n"
                        + "dGlvbjtMAARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBVb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RNdXRhYmxlRG91YmxlS2V5\n"
                        + "U2V0JFNlclJlcAAAAAAAAAABDAAAeHB3BAAAAAB4c3IATG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLlN5bmNocm9uaXplZERvdWJsZUJ5dGVNYXAAAAAA\n"
                        + "AAAAAQIAAkwABGxvY2txAH4AA0wAA21hcHQAQExvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkv\n"
                        + "bWFwL3ByaW1pdGl2ZS9NdXRhYmxlRG91YmxlQnl0ZU1hcDt4cHEAfgAJc3IARG9yZy5lY2xpcHNl\n"
                        + "LmNvbGxlY3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLkRvdWJsZUJ5dGVIYXNoTWFw\n"
                        + "AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new SynchronizedDoubleByteMap(new DoubleByteHashMap()).keySet());
    }
}
