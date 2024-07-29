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

public class SynchronizedIntByteMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRJbnRCeXRlTWFwAAAAAAAAAAECAAJMAARsb2NrdAASTGphdmEvbGFuZy9P\n"
                        + "YmplY3Q7TAADbWFwdAA9TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJpbWl0aXZl\n"
                        + "L011dGFibGVJbnRCeXRlTWFwO3hwcQB+AANzcgBBb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5tYXAubXV0YWJsZS5wcmltaXRpdmUuSW50Qnl0ZUhhc2hNYXAAAAAAAAAAAQwAAHhwdwQAAAAA\n"
                        + "eA==",
                new SynchronizedIntByteMap(new IntByteHashMap()));
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
                        + "AAAAAAABDAAAeHB3BAAAAAB4c3IASW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubWFwLm11\n"
                        + "dGFibGUucHJpbWl0aXZlLlN5bmNocm9uaXplZEludEJ5dGVNYXAAAAAAAAAAAQIAAkwABGxvY2tx\n"
                        + "AH4AA0wAA21hcHQAPUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1pdGl2ZS9N\n"
                        + "dXRhYmxlSW50Qnl0ZU1hcDt4cHEAfgAJc3IAQW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "bWFwLm11dGFibGUucHJpbWl0aXZlLkludEJ5dGVIYXNoTWFwAAAAAAAAAAEMAAB4cHcEAAAAAHg=\n",
                new SynchronizedIntByteMap(new IntByteHashMap()).keySet());
    }
}
