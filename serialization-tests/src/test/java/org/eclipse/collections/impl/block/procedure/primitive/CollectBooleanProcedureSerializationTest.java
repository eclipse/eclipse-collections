/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class CollectBooleanProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuQ29sbGVjdEJvb2xlYW5Qcm9jZWR1cmUAAAAAAAAAAQIAAkwAEWJvb2xlYW5Db2xsZWN0\n"
                        + "aW9udABLTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9jb2xsZWN0aW9uL3ByaW1pdGl2ZS9N\n"
                        + "dXRhYmxlQm9vbGVhbkNvbGxlY3Rpb247TAAPYm9vbGVhbkZ1bmN0aW9udABGTG9yZy9lY2xpcHNl\n"
                        + "L2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUvQm9vbGVhbkZ1bmN0aW9u\n"
                        + "O3hwcHA=",
                new CollectBooleanProcedure(null, null));
    }
}
