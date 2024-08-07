/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class MultimapKeyValuePutAllProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5NdWx0\n"
                        + "aW1hcEtleVZhbHVlUHV0QWxsUHJvY2VkdXJlAAAAAAAAAAECAANMAAtrZXlGdW5jdGlvbnQANUxv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAAPbXV0\n"
                        + "YWJsZU11bHRpbWFwdAA2TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tdWx0aW1hcC9NdXRh\n"
                        + "YmxlTXVsdGltYXA7TAANdmFsdWVGdW5jdGlvbnEAfgABeHBwcHA=",
                new MultimapKeyValuePutAllProcedure<>(null, null, null));
    }
}
