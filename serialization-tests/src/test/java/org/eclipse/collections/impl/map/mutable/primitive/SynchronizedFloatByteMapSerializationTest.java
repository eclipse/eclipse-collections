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

public class SynchronizedFloatByteMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRGbG9hdEJ5dGVNYXAAAAAAAAAAAQIAAkwABGxvY2t0ABJMamF2YS9sYW5n\n"
                        + "L09iamVjdDtMAANtYXB0AD9Mb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmltaXRp\n"
                        + "dmUvTXV0YWJsZUZsb2F0Qnl0ZU1hcDt4cHEAfgADc3IAQ29yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLkZsb2F0Qnl0ZUhhc2hNYXAAAAAAAAAAAQwAAHhw\n"
                        + "dwQAAAAAeA==",
                new SynchronizedFloatByteMap(new FloatByteHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRGbG9hdFNldAAAAAAAAAABAgAAeHIAXW9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuY29sbGVjdGlvbi5tdXRhYmxlLnByaW1pdGl2ZS5BYnN0cmFjdFN5bmNocm9uaXpl\n"
                        + "ZEZsb2F0Q29sbGVjdGlvbgAAAAAAAAABAgACTAAKY29sbGVjdGlvbnQASUxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZUZsb2F0Q29sbGVjdGlv\n"
                        + "bjtMAARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBUb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RNdXRhYmxlRmxvYXRLZXlTZXQk\n"
                        + "U2VyUmVwAAAAAAAAAAEMAAB4cHcEAAAAAHhzcgBLb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5tYXAubXV0YWJsZS5wcmltaXRpdmUuU3luY2hyb25pemVkRmxvYXRCeXRlTWFwAAAAAAAAAAEC\n"
                        + "AAJMAARsb2NrcQB+AANMAANtYXB0AD9Mb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9w\n"
                        + "cmltaXRpdmUvTXV0YWJsZUZsb2F0Qnl0ZU1hcDt4cHEAfgAJc3IAQ29yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLkZsb2F0Qnl0ZUhhc2hNYXAAAAAAAAAA\n"
                        + "AQwAAHhwdwQAAAAAeA==",
                new SynchronizedFloatByteMap(new FloatByteHashMap()).keySet());
    }
}
