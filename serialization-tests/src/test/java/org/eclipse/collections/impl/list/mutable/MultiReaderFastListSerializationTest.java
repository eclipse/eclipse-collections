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

public class MultiReaderFastListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5NdWx0aVJl\n"
                        + "YWRlckZhc3RMaXN0AAAAAAAAAAEMAAB4cHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "Lmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4eA==",
                MultiReaderFastList.newList());
    }
}
