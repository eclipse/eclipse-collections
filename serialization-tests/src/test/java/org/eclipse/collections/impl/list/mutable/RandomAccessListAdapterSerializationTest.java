/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class RandomAccessListAdapterSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5SYW5kb21B\n"
                        + "Y2Nlc3NMaXN0QWRhcHRlcgAAAAAAAAABAgABTAAIZGVsZWdhdGV0ABBMamF2YS91dGlsL0xpc3Q7\n"
                        + "eHBzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5saXN0Lm11dGFibGUuRmFzdExpc3QA\n"
                        + "AAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new RandomAccessListAdapter<>(FastList.newList()));
    }
}
