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

import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableMutableMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLlVubW9kaWZp\n"
                        + "YWJsZU11dGFibGVNYXAAAAAAAAAAAQIAAHhyACxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LlVubW9kaWZpYWJsZU1hcAAAAAAAAAABAgABTAAIZGVsZWdhdGV0AA9MamF2YS91dGlsL01hcDt4\n"
                        + "cHNyADNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLlVuaWZpZWRNYXAA\n"
                        + "AAAAAAAAAQwAAHhwdwgAAAAAP0AAAHg=",
                UnmodifiableMutableMap.of(Maps.mutable.of()));
    }

    @Test
    public void keySet()
    {
        Verify.assertSerializedForm(
                -9215047833775013803L,
                "rO0ABXNyACVqYXZhLnV0aWwuQ29sbGVjdGlvbnMkVW5tb2RpZmlhYmxlU2V0gB2S0Y+bgFUCAAB4\n"
                        + "cgAsamF2YS51dGlsLkNvbGxlY3Rpb25zJFVubW9kaWZpYWJsZUNvbGxlY3Rpb24ZQgCAy173HgIA\n"
                        + "AUwAAWN0ABZMamF2YS91dGlsL0NvbGxlY3Rpb247eHBzcgAzb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5zZXQubXV0YWJsZS5VbmlmaWVkU2V0AAAAAAAAAAEMAAB4cHcIAAAAAD9AAAB4",
                UnmodifiableMutableMap.of(Maps.mutable.of()).keySet());
    }

    @Test
    public void entrySet()
    {
        Verify.assertSerializedForm(
                7854390611657943733L,
                "rO0ABXNyADpqYXZhLnV0aWwuQ29sbGVjdGlvbnMkVW5tb2RpZmlhYmxlTWFwJFVubW9kaWZpYWJs\n"
                        + "ZUVudHJ5U2V0bQBmpZ8I6rUCAAB4cgAlamF2YS51dGlsLkNvbGxlY3Rpb25zJFVubW9kaWZpYWJs\n"
                        + "ZVNldIAdktGPm4BVAgAAeHIALGphdmEudXRpbC5Db2xsZWN0aW9ucyRVbm1vZGlmaWFibGVDb2xs\n"
                        + "ZWN0aW9uGUIAgMte9x4CAAFMAAFjdAAWTGphdmEvdXRpbC9Db2xsZWN0aW9uO3hwc3IAPG9yZy5l\n"
                        + "Y2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubWFwLm11dGFibGUuVW5pZmllZE1hcCRFbnRyeVNldAAA\n"
                        + "AAAAAAABAgABTAAGdGhpcyQwdAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvbWFwL211\n"
                        + "dGFibGUvVW5pZmllZE1hcDt4cHNyADNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5t\n"
                        + "dXRhYmxlLlVuaWZpZWRNYXAAAAAAAAAAAQwAAHhwdwgAAAAAP0AAAHg=",
                UnmodifiableMutableMap.of(Maps.mutable.of()).entrySet());
    }

    @Test
    public void values()
    {
        Verify.assertSerializedForm(
                1820017752578914078L,
                "rO0ABXNyACxqYXZhLnV0aWwuQ29sbGVjdGlvbnMkVW5tb2RpZmlhYmxlQ29sbGVjdGlvbhlCAIDL\n"
                        + "XvceAgABTAABY3QAFkxqYXZhL3V0aWwvQ29sbGVjdGlvbjt4cHNyADJvcmcuZWNsaXBzZS5jb2xs\n"
                        + "ZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                UnmodifiableMutableMap.of(Maps.mutable.of()).values());
    }
}
