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

public class CharAdapterSerializationTest
{
    public static final String EMPTY_CHAR_ADAPTER = "rO0ABXNyADlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0cmluZy5pbW11dGFibGUuQ2hh\n"
            + "ckFkYXB0ZXIAAAAAAAAAAQIAAUwAB2FkYXB0ZWR0ABJMamF2YS9sYW5nL1N0cmluZzt4cHQAAA==\n";
    public static final String HELLO_WORLD_STRING = "rO0ABXNyADRjb20uZ3MuY29sbGVjdGlvbnMuaW1wbC5zdHJpbmcuaW1tdXRhYmxlLkNoYXJBZGFwn"
            + "dGVyAAAAAAAAAAECAAFMAAdhZGFwdGVkdAASTGphdmEvbGFuZy9TdHJpbmc7eHB0AAxIZWxsbyBXn"
            + "b3JsZCE=";

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                EMPTY_CHAR_ADAPTER,
                CharAdapter.adapt(""));
    }

    @Test
    public void serializedFormNotEmpty()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0cmluZy5pbW11dGFibGUuQ2hh\n"
                        + "ckFkYXB0ZXIAAAAAAAAAAQIAAUwAB2FkYXB0ZWR0ABJMamF2YS9sYW5nL1N0cmluZzt4cHQADEhl\n"
                        + "bGxvIFdvcmxkIQ==",
                CharAdapter.adapt("Hello World!"));
    }
}
