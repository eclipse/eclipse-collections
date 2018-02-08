/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CheckedObjectDoubleProcedureSerializationTest
{
    private static final CheckedObjectDoubleProcedure<?> CHECKED_OBJECT_DOUBLE_PROCEDURE = new CheckedObjectDoubleProcedure<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void safeValue(Object item1, double item2)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAG5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLnByaW1pdGl2ZS5DaGVja2VkT2JqZWN0RG91YmxlUHJvY2VkdXJlU2VyaWFsaXphdGlvblRl\n"
                        + "c3QkMQAAAAAAAAABAgAAeHIAW29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2sucHJv\n"
                        + "Y2VkdXJlLmNoZWNrZWQucHJpbWl0aXZlLkNoZWNrZWRPYmplY3REb3VibGVQcm9jZWR1cmUAAAAA\n"
                        + "AAAAAQIAAHhw",
                CHECKED_OBJECT_DOUBLE_PROCEDURE);
    }
}
