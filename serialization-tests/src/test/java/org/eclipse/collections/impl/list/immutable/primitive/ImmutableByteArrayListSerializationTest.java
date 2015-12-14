/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableByteArrayListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLnByaW1p\n"
                        + "dGl2ZS5JbW11dGFibGVCeXRlQXJyYXlMaXN0AAAAAAAAAAECAAFbAAVpdGVtc3QAAltCeHB1cgAC\n"
                        + "W0Ks8xf4BghU4AIAAHhwAAAACwECAwQFBgcICQoL",
                ImmutableByteArrayList.newListWith((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11));
    }
}
