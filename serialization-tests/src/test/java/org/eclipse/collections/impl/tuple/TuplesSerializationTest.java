/*
 * Copyright (c) 2015 Goldman Sachs.
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

public class TuplesSerializationTest
{
    @Test
    public void pair()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyACtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLlBhaXJJbXBsAAAAAAAA\n"
                        + "AAECAAJMAANvbmV0ABJMamF2YS9sYW5nL09iamVjdDtMAAN0d29xAH4AAXhwcHA=",
                Tuples.pair(null, null));
    }

    @Test
    public void twin()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyACtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLlR3aW5JbXBsAAAAAAAA\n"
                        + "AAECAAB4cgArb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC50dXBsZS5QYWlySW1wbAAAAAAA\n"
                        + "AAABAgACTAADb25ldAASTGphdmEvbGFuZy9PYmplY3Q7TAADdHdvcQB+AAJ4cHBw",
                Tuples.twin(null, null));
    }
}
