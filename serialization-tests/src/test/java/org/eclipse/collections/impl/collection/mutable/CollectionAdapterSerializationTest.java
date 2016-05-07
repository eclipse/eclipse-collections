/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CollectionAdapterSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5D\n"
                        + "b2xsZWN0aW9uQWRhcHRlcgAAAAAAAAABAgABTAAIZGVsZWdhdGV0ABZMamF2YS91dGlsL0NvbGxl\n"
                        + "Y3Rpb247eHBzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5saXN0Lm11dGFibGUuRmFz\n"
                        + "dExpc3QAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new CollectionAdapter<>(Lists.mutable.of()));
    }
}
