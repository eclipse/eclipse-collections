/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class MapAdapterSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLk1hcEFkYXB0\n"
                        + "ZXIAAAAAAAAAAQIAAUwACGRlbGVnYXRldAAPTGphdmEvdXRpbC9NYXA7eHBzcgAzb3JnLmVjbGlw\n"
                        + "c2UuY29sbGVjdGlvbnMuaW1wbC5tYXAubXV0YWJsZS5VbmlmaWVkTWFwAAAAAAAAAAEMAAB4cHcI\n"
                        + "AAAAAD9AAAB4",
                new MapAdapter<>(UnifiedMap.newMap()));
    }

    @Test
    public void keySet()
    {
        Verify.assertSerializedForm(
                1L,
                UnifiedMapSerializationTest.UNIFIED_MAP_KEY_SET,
                new MapAdapter<>(UnifiedMap.newMap()).keySet());
    }

    @Test
    public void entrySet()
    {
        Verify.assertSerializedForm(
                1L,
                UnifiedMapSerializationTest.UNIFIED_MAP_ENTRY_SET,
                new MapAdapter<>(UnifiedMap.newMap()).entrySet());
    }

    @Test
    public void values()
    {
        Verify.assertSerializedForm(
                1L,
                UnifiedMapSerializationTest.UNIFIED_MAP_VALUES,
                new MapAdapter<>(UnifiedMap.newMap()).values());
    }
}
