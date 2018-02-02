/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CheckedProcedure2SerializationTest
{
    private static final CheckedProcedure2<?, ?> CHECKED_PROCEDURE_2 = new CheckedProcedure2<Object, Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void safeValue(Object object, Object parameter)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLkNoZWNrZWRQcm9jZWR1cmUyU2VyaWFsaXphdGlvblRlc3QkMQAAAAAAAAABAgAAeHIARm9y\n"
                        + "Zy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2sucHJvY2VkdXJlLmNoZWNrZWQuQ2hlY2tl\n"
                        + "ZFByb2NlZHVyZTIAAAAAAAAAAQIAAHhw",
                CHECKED_PROCEDURE_2);
    }
}
