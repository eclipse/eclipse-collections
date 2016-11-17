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

public class CompositeFastListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyADtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5Db21wb3Np\n"
                        + "dGVGYXN0TGlzdAAAAAAAAAACAgACSQAEc2l6ZUwABWxpc3RzdAA0TG9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2ltcGwvbGlzdC9tdXRhYmxlL0Zhc3RMaXN0O3hwAAAAAHNyADJvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4\n",
                new CompositeFastList<>());
    }
}
