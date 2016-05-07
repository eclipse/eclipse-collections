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

import java.util.LinkedList;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableMutableListSerializationTest
{
    @Test
    public void serializedForm_random_access()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5V\n"
                        + "bm1vZGlmaWFibGVDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADJv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAAB\n"
                        + "DAAAeHB3BAAAAAB4eA==",
                UnmodifiableMutableList.of(FastList.newList()));
    }

    @Test
    public void serializedForm_not_random_access()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5V\n"
                        + "bm1vZGlmaWFibGVDb2xsZWN0aW9uU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHNyADVv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5MaXN0QWRhcHRlcgAAAAAA\n"
                        + "AAABAgABTAAIZGVsZWdhdGV0ABBMamF2YS91dGlsL0xpc3Q7eHBzcgAUamF2YS51dGlsLkxpbmtl\n"
                        + "ZExpc3QMKVNdSmCIIgMAAHhwdwQAAAAAeHg=",
                UnmodifiableMutableList.of(new LinkedList<>()));
    }
}
