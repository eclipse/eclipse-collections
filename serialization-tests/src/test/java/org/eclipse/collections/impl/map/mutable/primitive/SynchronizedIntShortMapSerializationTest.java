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

public class SynchronizedIntShortMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRJbnRTaG9ydE1hcAAAAAAAAAABAgACTAAEbG9ja3QAEkxqYXZhL2xhbmcv\n"
                        + "T2JqZWN0O0wAA21hcHQAPkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1pdGl2\n"
                        + "ZS9NdXRhYmxlSW50U2hvcnRNYXA7eHBxAH4AA3NyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5JbnRTaG9ydEhhc2hNYXAAAAAAAAAAAQwAAHhwdwQA\n"
                        + "AAAAeA==",
                new SynchronizedIntShortMap(new IntShortHashMap()));
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
                        + "AAAAAAABDAAAeHB3BAAAAAB4c3IASm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubWFwLm11\n"
                        + "dGFibGUucHJpbWl0aXZlLlN5bmNocm9uaXplZEludFNob3J0TWFwAAAAAAAAAAECAAJMAARsb2Nr\n"
                        + "cQB+AANMAANtYXB0AD5Mb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmltaXRpdmUv\n"
                        + "TXV0YWJsZUludFNob3J0TWFwO3hwcQB+AAlzcgBCb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5tYXAubXV0YWJsZS5wcmltaXRpdmUuSW50U2hvcnRIYXNoTWFwAAAAAAAAAAEMAAB4cHcEAAAA\n"
                        + "AHg=",
                new SynchronizedIntShortMap(new IntShortHashMap()).keySet());
    }
}
