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

public class CollectProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5Db2xs\n"
                        + "ZWN0UHJvY2VkdXJlAAAAAAAAAAECAAJMAApjb2xsZWN0aW9udAAWTGphdmEvdXRpbC9Db2xsZWN0\n"
                        + "aW9uO0wACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5j\n"
                        + "dGlvbi9GdW5jdGlvbjt4cHBw",
                new CollectProcedure<>(null, null));
    }
}
