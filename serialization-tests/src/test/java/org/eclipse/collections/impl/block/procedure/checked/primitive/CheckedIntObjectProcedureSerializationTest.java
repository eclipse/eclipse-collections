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

public class CheckedIntObjectProcedureSerializationTest
{
    private static final CheckedIntObjectProcedure<?> CHECKED_INT_OBJECT_PROCEDURE = new CheckedIntObjectProcedure<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void safeValue(int value, Object object)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLnByaW1pdGl2ZS5DaGVja2VkSW50T2JqZWN0UHJvY2VkdXJlU2VyaWFsaXphdGlvblRlc3Qk\n"
                        + "MQAAAAAAAAABAgAAeHIAWG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2sucHJvY2Vk\n"
                        + "dXJlLmNoZWNrZWQucHJpbWl0aXZlLkNoZWNrZWRJbnRPYmplY3RQcm9jZWR1cmUAAAAAAAAAAQIA\n"
                        + "AHhw",
                CHECKED_INT_OBJECT_PROCEDURE);
    }
}
