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

public class CheckedObjectIntProcedureSerializationTest
{
    private static final CheckedObjectIntProcedure<?> CHECKED_OBJECT_INT_PROCEDURE = new CheckedObjectIntProcedure<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void safeValue(Object object, int index)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLkNoZWNrZWRPYmplY3RJbnRQcm9jZWR1cmVTZXJpYWxpemF0aW9uVGVzdCQxAAAAAAAAAAEC\n"
                        + "AAB4cgBOb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5wcm9jZWR1cmUuY2hlY2tl\n"
                        + "ZC5DaGVja2VkT2JqZWN0SW50UHJvY2VkdXJlAAAAAAAAAAECAAB4cA==",
                CHECKED_OBJECT_INT_PROCEDURE);
    }
}
