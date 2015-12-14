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

public class ImmutableIntArrayListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLnByaW1p\n"
                        + "dGl2ZS5JbW11dGFibGVJbnRBcnJheUxpc3QAAAAAAAAAAQIAAVsABWl0ZW1zdAACW0l4cHVyAAJb\n"
                        + "SU26YCZ26rKlAgAAeHAAAAALAAAAAQAAAAIAAAADAAAABAAAAAUAAAAGAAAABwAAAAgAAAAJAAAA\n"
                        + "CgAAAAs=",
                ImmutableIntArrayList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
    }
}
