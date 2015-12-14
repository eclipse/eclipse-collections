/*
 * Copyright (c) 2015 Goldman Sachs.
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

public class ArrayListAdapterSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5BcnJheUxp\n"
                        + "c3RBZGFwdGVyAAAAAAAAAAECAAFMAAhkZWxlZ2F0ZXQAFUxqYXZhL3V0aWwvQXJyYXlMaXN0O3hw\n"
                        + "c3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAB3BAAAAAB4",
                ArrayListAdapter.newList(0));
    }
}
