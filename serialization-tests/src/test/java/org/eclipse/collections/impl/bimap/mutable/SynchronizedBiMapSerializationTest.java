/*
 * Copyright (c) 2016 Bhavana Hindupur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedBiMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLlN5bmNocm9u\n"
                        + "aXplZEJpTWFwU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADRvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLmJpbWFwLm11dGFibGUuSGFzaEJpTWFwAAAAAAAAAAEMAAB4cHcIAAAA\n"
                        + "AD9AAAB4eA==",
                SynchronizedBiMap.of(BiMaps.mutable.of()));
    }

    @Test
    public void keySet()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5T\n"
                        + "eW5jaHJvbml6ZWRDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADNv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLlNldEFkYXB0ZXIAAAAAAAAA\n"
                        + "AQIAAUwACGRlbGVnYXRldAAPTGphdmEvdXRpbC9TZXQ7eHBzcgAzb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5zZXQubXV0YWJsZS5VbmlmaWVkU2V0AAAAAAAAAAEMAAB4cHcIAAAAAD9AAAB4\n"
                        + "eA==",
                SynchronizedBiMap.of(BiMaps.mutable.of()).keySet());
    }
}
