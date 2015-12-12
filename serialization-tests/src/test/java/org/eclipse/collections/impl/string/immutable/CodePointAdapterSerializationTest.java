/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.string.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CodePointAdapterSerializationTest
{
    public static final String EMPTY_CODE_LIST = "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0cmluZy5pbW11dGFibGUuQ29k\n"
            + "ZVBvaW50QWRhcHRlcgAAAAAAAAABAgABTAAHYWRhcHRlZHQAEkxqYXZhL2xhbmcvU3RyaW5nO3hw\n"
            + "dAAA";
    public static final String HELLO_WORLD_STRING = "rO0ABXNyADljb20uZ3MuY29sbGVjdGlvbnMuaW1wbC5zdHJpbmcuaW1tdXRhYmxlLkNvZGVQb2lun"
            + "dEFkYXB0ZXIAAAAAAAAAAQIAAUwAB2FkYXB0ZWR0ABJMamF2YS9sYW5nL1N0cmluZzt4cHQADEhln"
            + "bGxvIFdvcmxkIQ==";

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                EMPTY_CODE_LIST,
                CodePointAdapter.adapt(""));
    }

    @Test
    public void serializedFormNotEmpty()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0cmluZy5pbW11dGFibGUuQ29k\n"
                        + "ZVBvaW50QWRhcHRlcgAAAAAAAAABAgABTAAHYWRhcHRlZHQAEkxqYXZhL2xhbmcvU3RyaW5nO3hw\n"
                        + "dAAMSGVsbG8gV29ybGQh",
                CodePointAdapter.adapt("Hello World!"));
    }
}
