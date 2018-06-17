/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CollectByteProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuQ29sbGVjdEJ5dGVQcm9jZWR1cmUAAAAAAAAAAQIAAkwADmJ5dGVDb2xsZWN0aW9udABI\n"
                        + "TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9jb2xsZWN0aW9uL3ByaW1pdGl2ZS9NdXRhYmxl\n"
                        + "Qnl0ZUNvbGxlY3Rpb247TAAMYnl0ZUZ1bmN0aW9udABDTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25z\n"
                        + "L2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRpdmUvQnl0ZUZ1bmN0aW9uO3hwcHA=",
                new CollectByteProcedure(null, null));
    }
}
