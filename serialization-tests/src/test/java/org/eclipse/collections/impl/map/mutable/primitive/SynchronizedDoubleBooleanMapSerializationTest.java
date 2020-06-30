/*
 * Copyright (c) 2015 Goldman Sachs.
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

public class SynchronizedDoubleBooleanMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWREb3VibGVCb29sZWFuTWFwAAAAAAAAAAECAAJMAARsb2NrdAASTGphdmEv\n"
                        + "bGFuZy9PYmplY3Q7TAADbWFwdABDTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJp\n"
                        + "bWl0aXZlL011dGFibGVEb3VibGVCb29sZWFuTWFwO3hwcQB+AANzcgBHb3JnLmVjbGlwc2UuY29s\n"
                        + "bGVjdGlvbnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuRG91YmxlQm9vbGVhbkhhc2hNYXAA\n"
                        + "AAAAAAAAAQwAAHhwdwgAAAAAPwAAAHg=",
                new SynchronizedDoubleBooleanMap(new DoubleBooleanHashMap()));
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
                        + "U2V0JFNlclJlcAAAAAAAAAABDAAAeHB3BAAAAAB4c3IAT29yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLlN5bmNocm9uaXplZERvdWJsZUJvb2xlYW5NYXAA\n"
                        + "AAAAAAAAAQIAAkwABGxvY2txAH4AA0wAA21hcHQAQ0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9h\n"
                        + "cGkvbWFwL3ByaW1pdGl2ZS9NdXRhYmxlRG91YmxlQm9vbGVhbk1hcDt4cHEAfgAJc3IAR29yZy5l\n"
                        + "Y2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLkRvdWJsZUJvb2xl\n"
                        + "YW5IYXNoTWFwAAAAAAAAAAEMAAB4cHcIAAAAAD8AAAB4",
                new SynchronizedDoubleBooleanMap(new DoubleBooleanHashMap()).keySet());
    }
}
