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

public class MultimapKeyValuePutProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5NdWx0\n"
                        + "aW1hcEtleVZhbHVlUHV0UHJvY2VkdXJlAAAAAAAAAAECAANMAAtrZXlGdW5jdGlvbnQANUxvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAAPbXV0YWJs\n"
                        + "ZU11bHRpbWFwdAA2TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tdWx0aW1hcC9NdXRhYmxl\n"
                        + "TXVsdGltYXA7TAANdmFsdWVGdW5jdGlvbnEAfgABeHBwcHA=",
                new MultimapKeyValuePutProcedure<>(null, null, null));
    }
}
