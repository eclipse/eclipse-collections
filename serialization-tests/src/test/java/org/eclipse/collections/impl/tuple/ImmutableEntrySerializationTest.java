/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableEntrySerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLkltbXV0YWJsZUVudHJ5\n"
                        + "AAAAAAAAAAECAAB4cgA5b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC50dXBsZS5BYnN0cmFj\n"
                        + "dEltbXV0YWJsZUVudHJ5AAAAAAAAAAECAAJMAANrZXl0ABJMamF2YS9sYW5nL09iamVjdDtMAAV2\n"
                        + "YWx1ZXEAfgACeHBwcA==",
                new ImmutableEntry<>(null, null));
    }
}
