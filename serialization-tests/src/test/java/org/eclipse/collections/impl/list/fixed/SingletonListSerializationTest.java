/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastListSerializationTest;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SingletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuZml4ZWQuU2luZ2xldG9u\n"
                        + "TGlzdAAAAAAAAAABDAAAeHBweA==",
                Lists.fixedSize.of((Object) null));
    }

    @Test
    public void subList()
    {
        Verify.assertSerializedForm(
                1L,
                FastListSerializationTest.FAST_LIST_WITH_ONE_NULL,
                Lists.fixedSize.of((Object) null).subList(0, 1));
    }
}
