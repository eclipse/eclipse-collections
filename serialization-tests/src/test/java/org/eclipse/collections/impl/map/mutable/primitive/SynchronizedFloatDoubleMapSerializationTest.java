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

public class SynchronizedFloatDoubleMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRGbG9hdERvdWJsZU1hcAAAAAAAAAABAgACTAAEbG9ja3QAEkxqYXZhL2xh\n"
                        + "bmcvT2JqZWN0O0wAA21hcHQAQUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1p\n"
                        + "dGl2ZS9NdXRhYmxlRmxvYXREb3VibGVNYXA7eHBxAH4AA3NyAEVvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5GbG9hdERvdWJsZUhhc2hNYXAAAAAAAAAA\n"
                        + "AQwAAHhwdwQAAAAAeA==",
                new SynchronizedFloatDoubleMap(new FloatDoubleHashMap()));
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
                        + "U2VyUmVwAAAAAAAAAAEMAAB4cHcEAAAAAHhzcgBNb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5tYXAubXV0YWJsZS5wcmltaXRpdmUuU3luY2hyb25pemVkRmxvYXREb3VibGVNYXAAAAAAAAAA\n"
                        + "AQIAAkwABGxvY2txAH4AA0wAA21hcHQAQUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbWFw\n"
                        + "L3ByaW1pdGl2ZS9NdXRhYmxlRmxvYXREb3VibGVNYXA7eHBxAH4ACXNyAEVvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5GbG9hdERvdWJsZUhhc2hNYXAA\n"
                        + "AAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new SynchronizedFloatDoubleMap(new FloatDoubleHashMap()).keySet());
    }
}
