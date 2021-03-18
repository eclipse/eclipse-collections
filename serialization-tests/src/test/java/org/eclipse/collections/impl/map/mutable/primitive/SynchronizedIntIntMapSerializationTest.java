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

public class SynchronizedIntIntMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRJbnRJbnRNYXAAAAAAAAAAAQIAAkwABGxvY2t0ABJMamF2YS9sYW5nL09i\n"
                        + "amVjdDtMAANtYXB0ADxMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmltaXRpdmUv\n"
                        + "TXV0YWJsZUludEludE1hcDt4cHEAfgADc3IAQG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "bWFwLm11dGFibGUucHJpbWl0aXZlLkludEludEhhc2hNYXAAAAAAAAAAAQwAAHhwdwQAAAAAeA==\n",
                new SynchronizedIntIntMap(new IntIntHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRJbnRTZXQAAAAAAAAAAQIAAHhyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RTeW5jaHJvbml6ZWRJ\n"
                        + "bnRDb2xsZWN0aW9uAAAAAAAAAAECAAJMAApjb2xsZWN0aW9udABHTG9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2FwaS9jb2xsZWN0aW9uL3ByaW1pdGl2ZS9NdXRhYmxlSW50Q29sbGVjdGlvbjtMAARs\n"
                        + "b2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBSb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RNdXRhYmxlSW50S2V5U2V0JFNlclJlcAAA\n"
                        + "AAAAAAABDAAAeHB3BAAAAAB4c3IASG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubWFwLm11\n"
                        + "dGFibGUucHJpbWl0aXZlLlN5bmNocm9uaXplZEludEludE1hcAAAAAAAAAABAgACTAAEbG9ja3EA\n"
                        + "fgADTAADbWFwdAA8TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJpbWl0aXZlL011\n"
                        + "dGFibGVJbnRJbnRNYXA7eHBxAH4ACXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1h\n"
                        + "cC5tdXRhYmxlLnByaW1pdGl2ZS5JbnRJbnRIYXNoTWFwAAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new SynchronizedIntIntMap(new IntIntHashMap()).keySet());
    }
}
