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

public class SynchronizedDoubleDoubleMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWREb3VibGVEb3VibGVNYXAAAAAAAAAAAQIAAkwABGxvY2t0ABJMamF2YS9s\n"
                        + "YW5nL09iamVjdDtMAANtYXB0AEJMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmlt\n"
                        + "aXRpdmUvTXV0YWJsZURvdWJsZURvdWJsZU1hcDt4cHEAfgADc3IARm9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLkRvdWJsZURvdWJsZUhhc2hNYXAAAAAA\n"
                        + "AAAAAQwAAHhwdwQAAAAAeA==",
                new SynchronizedDoubleDoubleMap(new DoubleDoubleHashMap()));
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
                        + "U2V0JFNlclJlcAAAAAAAAAABDAAAeHB3BAAAAAB4c3IATm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLlN5bmNocm9uaXplZERvdWJsZURvdWJsZU1hcAAA\n"
                        + "AAAAAAABAgACTAAEbG9ja3EAfgADTAADbWFwdABCTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2Fw\n"
                        + "aS9tYXAvcHJpbWl0aXZlL011dGFibGVEb3VibGVEb3VibGVNYXA7eHBxAH4ACXNyAEZvcmcuZWNs\n"
                        + "aXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5Eb3VibGVEb3VibGVI\n"
                        + "YXNoTWFwAAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new SynchronizedDoubleDoubleMap(new DoubleDoubleHashMap()).keySet());
    }
}
