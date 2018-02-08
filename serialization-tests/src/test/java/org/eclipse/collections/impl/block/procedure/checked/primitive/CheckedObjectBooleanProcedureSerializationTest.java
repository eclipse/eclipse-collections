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

public class CheckedObjectBooleanProcedureSerializationTest
{
    private static final CheckedObjectBooleanProcedure<?> CHECKED_OBJECT_BOOLEAN_PROCEDURE = new CheckedObjectBooleanProcedure<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void safeValue(Object item1, boolean item2)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAG9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLnByaW1pdGl2ZS5DaGVja2VkT2JqZWN0Qm9vbGVhblByb2NlZHVyZVNlcmlhbGl6YXRpb25U\n"
                        + "ZXN0JDEAAAAAAAAAAQIAAHhyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnBy\n"
                        + "b2NlZHVyZS5jaGVja2VkLnByaW1pdGl2ZS5DaGVja2VkT2JqZWN0Qm9vbGVhblByb2NlZHVyZQAA\n"
                        + "AAAAAAABAgAAeHA=",
                CHECKED_OBJECT_BOOLEAN_PROCEDURE);
    }
}
