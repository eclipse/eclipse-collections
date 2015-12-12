/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class HashBiMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJpbWFwLm11dGFibGUuSGFzaEJp\n"
                        + "TWFwAAAAAAAAAAEMAAB4cHcIAAAAAT9AAABzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIA\n"
                        + "AUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAXNyABNqYXZhLmxh\n"
                        + "bmcuQ2hhcmFjdGVyNItH2WsaJngCAAFDAAV2YWx1ZXhwAGF4",
                HashBiMap.newWithKeysValues(1, 'a'));
    }

    @Test
    public void inverse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJpbWFwLm11dGFibGUuQWJzdHJh\n"
                        + "Y3RNdXRhYmxlQmlNYXAkSW52ZXJzZQAAAAAAAAABDAAAeHB3CAAAAAE/QAAAc3IAE2phdmEubGFu\n"
                        + "Zy5DaGFyYWN0ZXI0i0fZaxomeAIAAUMABXZhbHVleHAAYXNyABFqYXZhLmxhbmcuSW50ZWdlchLi\n"
                        + "oKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAABeA==\n",
                HashBiMap.newWithKeysValues(1, 'a').inverse());
    }
}
