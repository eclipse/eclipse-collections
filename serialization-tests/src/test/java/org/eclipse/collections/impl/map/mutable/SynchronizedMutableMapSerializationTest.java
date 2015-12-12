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

public class SynchronizedMutableMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLlN5bmNocm9u\n"
                        + "aXplZE1hcFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHBzcgAzb3JnLmVjbGlwc2UuY29s\n"
                        + "bGVjdGlvbnMuaW1wbC5tYXAubXV0YWJsZS5VbmlmaWVkTWFwAAAAAAAAAAEMAAB4cHcIAAAAAD9A\n"
                        + "AAB4eA==",
                SynchronizedMutableMap.of(Maps.mutable.of()));
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
                SynchronizedMutableMap.of(Maps.mutable.of()).keySet());
    }

    @Test
    public void entrySet()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5T\n"
                        + "eW5jaHJvbml6ZWRDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADNv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLlNldEFkYXB0ZXIAAAAAAAAA\n"
                        + "AQIAAUwACGRlbGVnYXRldAAPTGphdmEvdXRpbC9TZXQ7eHBzcgA8b3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5tYXAubXV0YWJsZS5VbmlmaWVkTWFwJEVudHJ5U2V0AAAAAAAAAAECAAFMAAZ0\n"
                        + "aGlzJDB0ADVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvaW1wbC9tYXAvbXV0YWJsZS9VbmlmaWVk\n"
                        + "TWFwO3hwc3IAM29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubWFwLm11dGFibGUuVW5pZmll\n"
                        + "ZE1hcAAAAAAAAAABDAAAeHB3CAAAAAA/QAAAeHg=",
                SynchronizedMutableMap.of(Maps.mutable.of()).entrySet());
    }

    @Test
    public void values()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5T\n"
                        + "eW5jaHJvbml6ZWRDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyAEFv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5Db2xsZWN0aW9u\n"
                        + "QWRhcHRlcgAAAAAAAAABAgABTAAIZGVsZWdhdGV0ABZMamF2YS91dGlsL0NvbGxlY3Rpb247eHBz\n"
                        + "cgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5saXN0Lm11dGFibGUuRmFzdExpc3QAAAAA\n"
                        + "AAAAAQwAAHhwdwQAAAAAeHg=",
                SynchronizedMutableMap.of(Maps.mutable.of()).values());
    }
}
