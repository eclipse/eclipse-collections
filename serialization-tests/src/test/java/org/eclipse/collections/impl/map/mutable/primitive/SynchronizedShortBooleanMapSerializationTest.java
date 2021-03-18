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

public class SynchronizedShortBooleanMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRTaG9ydEJvb2xlYW5NYXAAAAAAAAAAAQIAAkwABGxvY2t0ABJMamF2YS9s\n"
                        + "YW5nL09iamVjdDtMAANtYXB0AEJMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmlt\n"
                        + "aXRpdmUvTXV0YWJsZVNob3J0Qm9vbGVhbk1hcDt4cHEAfgADc3IARm9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLlNob3J0Qm9vbGVhbkhhc2hNYXAAAAAA\n"
                        + "AAAAAQwAAHhwdwgAAAAAPwAAAHg=",
                new SynchronizedShortBooleanMap(new ShortBooleanHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRTaG9ydFNldAAAAAAAAAABAgAAeHIAXW9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuY29sbGVjdGlvbi5tdXRhYmxlLnByaW1pdGl2ZS5BYnN0cmFjdFN5bmNocm9uaXpl\n"
                        + "ZFNob3J0Q29sbGVjdGlvbgAAAAAAAAABAgACTAAKY29sbGVjdGlvbnQASUxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZVNob3J0Q29sbGVjdGlv\n"
                        + "bjtMAARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBUb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RNdXRhYmxlU2hvcnRLZXlTZXQk\n"
                        + "U2VyUmVwAAAAAAAAAAEMAAB4cHcEAAAAAHhzcgBOb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5tYXAubXV0YWJsZS5wcmltaXRpdmUuU3luY2hyb25pemVkU2hvcnRCb29sZWFuTWFwAAAAAAAA\n"
                        + "AAECAAJMAARsb2NrcQB+AANMAANtYXB0AEJMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21h\n"
                        + "cC9wcmltaXRpdmUvTXV0YWJsZVNob3J0Qm9vbGVhbk1hcDt4cHEAfgAJc3IARm9yZy5lY2xpcHNl\n"
                        + "LmNvbGxlY3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLlNob3J0Qm9vbGVhbkhhc2hN\n"
                        + "YXAAAAAAAAAAAQwAAHhwdwgAAAAAPwAAAHg=",
                new SynchronizedShortBooleanMap(new ShortBooleanHashMap()).keySet());
    }
}
