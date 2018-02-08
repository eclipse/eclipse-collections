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

public class CheckedByteObjectProcedureSerializationTest
{
    private static final CheckedByteObjectProcedure<?> CHECKED_BYTE_OBJECT_PROCEDURE = new CheckedByteObjectProcedure<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void safeValue(byte value, Object object)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLnByaW1pdGl2ZS5DaGVja2VkQnl0ZU9iamVjdFByb2NlZHVyZVNlcmlhbGl6YXRpb25UZXN0\n"
                        + "JDEAAAAAAAAAAQIAAHhyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2Nl\n"
                        + "ZHVyZS5jaGVja2VkLnByaW1pdGl2ZS5DaGVja2VkQnl0ZU9iamVjdFByb2NlZHVyZQAAAAAAAAAB\n"
                        + "AgAAeHA=",
                CHECKED_BYTE_OBJECT_PROCEDURE);
    }
}
