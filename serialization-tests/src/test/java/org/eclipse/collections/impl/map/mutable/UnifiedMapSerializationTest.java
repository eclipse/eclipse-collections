/*
 * Copyright (c) 2015 Goldman Sachs.
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

public class UnifiedMapSerializationTest
{
    public static final String UNIFIED_MAP_KEY_SET =
            "rO0ABXNyADNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLlVuaWZpZWRT\n"
                    + "ZXQAAAAAAAAAAQwAAHhwdwgAAAAAP0AAAHg=";
    public static final String UNIFIED_MAP_ENTRY_SET =
            "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLlVuaWZpZWRN\n"
                    + "YXAkRW50cnlTZXQAAAAAAAAAAQIAAUwABnRoaXMkMHQANUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9u\n"
                    + "cy9pbXBsL21hcC9tdXRhYmxlL1VuaWZpZWRNYXA7eHBzcgAzb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                    + "bnMuaW1wbC5tYXAubXV0YWJsZS5VbmlmaWVkTWFwAAAAAAAAAAEMAAB4cHcIAAAAAD9AAAB4";
    public static final String UNIFIED_MAP_VALUES =
            "rO0ABXNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlz\n"
                    + "dAAAAAAAAAABDAAAeHB3BAAAAAB4";

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLlVuaWZpZWRN\n"
                        + "YXAAAAAAAAAAAQwAAHhwdwgAAAAAP0AAAHg=",
                UnifiedMap.newMap());
    }

    @Test
    public void keySet()
    {
        Verify.assertSerializedForm(
                1L,
                UNIFIED_MAP_KEY_SET,
                UnifiedMap.newMap().keySet());
    }

    @Test
    public void entrySet()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLlVuaWZpZWRN\n"
                        + "YXAkRW50cnlTZXQAAAAAAAAAAQIAAUwABnRoaXMkMHQANUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9u\n"
                        + "cy9pbXBsL21hcC9tdXRhYmxlL1VuaWZpZWRNYXA7eHBzcgAzb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5tYXAubXV0YWJsZS5VbmlmaWVkTWFwAAAAAAAAAAEMAAB4cHcIAAAAAD9AAAB4",
                UnifiedMap.newMap().entrySet());
    }

    @Test
    public void values()
    {
        Verify.assertSerializedForm(
                UNIFIED_MAP_VALUES,
                UnifiedMap.newMap().values());
    }
}
