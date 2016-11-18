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

public class FastListSerializationTest
{
    public static final String FAST_LIST_WITH_ONE_NULL =
            "rO0ABXNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlz\n"
                    + "dAAAAAAAAAABDAAAeHB3BAAAAAFweA==";
    public static final String FAST_LIST_EMPTY =
            "rO0ABXNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlz\n"
                    + "dAAAAAAAAAABDAAAeHB3BAAAAAB4";

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                FAST_LIST_EMPTY,
                FastList.newList());
    }

    @Test
    public void listWithOneNull()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlz\n"
                        + "dAAAAAAAAAABDAAAeHB3BAAAAAFweA==",
                FastList.newListWith((Object) null));
    }

    @Test
    public void subList()
    {
        // SerialVersionUID not important for objects with writeReplace()
        Verify.assertSerializedForm(
                FAST_LIST_WITH_ONE_NULL,
                FastList.newListWith(null, null).subList(0, 1));
    }
}
