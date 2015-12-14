/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedShortListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRp\n"
                        + "dmUuU3luY2hyb25pemVkU2hvcnRMaXN0AAAAAAAAAAECAAB4cgBdb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5jb2xsZWN0aW9uLm11dGFibGUucHJpbWl0aXZlLkFic3RyYWN0U3luY2hyb25p\n"
                        + "emVkU2hvcnRDb2xsZWN0aW9uAAAAAAAAAAECAAJMAApjb2xsZWN0aW9udABJTG9yZy9lY2xpcHNl\n"
                        + "L2NvbGxlY3Rpb25zL2FwaS9jb2xsZWN0aW9uL3ByaW1pdGl2ZS9NdXRhYmxlU2hvcnRDb2xsZWN0\n"
                        + "aW9uO0wABGxvY2t0ABJMamF2YS9sYW5nL09iamVjdDt4cHNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5wcmltaXRpdmUuU2hvcnRBcnJheUxpc3QAAAAAAAAAAQwA\n"
                        + "AHhwdwQAAAAAeHEAfgAE",
                new SynchronizedShortList(new ShortArrayList()));
    }
}
