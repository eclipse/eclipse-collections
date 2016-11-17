/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CollectIfProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5Db2xs\n"
                        + "ZWN0SWZQcm9jZWR1cmUAAAAAAAAAAQIAA0wACmNvbGxlY3Rpb250ABZMamF2YS91dGlsL0NvbGxl\n"
                        + "Y3Rpb247TAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1\n"
                        + "bmN0aW9uL0Z1bmN0aW9uO0wACXByZWRpY2F0ZXQAN0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9h\n"
                        + "cGkvYmxvY2svcHJlZGljYXRlL1ByZWRpY2F0ZTt4cHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4cHA=",
                new CollectIfProcedure<>(10, null, null));
    }
}
