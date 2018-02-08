/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.checked;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CheckedFunction2SerializationTest
{
    private static final CheckedFunction2<?, ?, ?> CHECKED_FUNCTION_2 = new CheckedFunction2<Object, Object, Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Object safeValue(Object argument1, Object argument2)
        {
            return null;
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLmNoZWNr\n"
                        + "ZWQuQ2hlY2tlZEZ1bmN0aW9uMlNlcmlhbGl6YXRpb25UZXN0JDEAAAAAAAAAAQIAAHhyAERvcmcu\n"
                        + "ZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLmNoZWNrZWQuQ2hlY2tlZEZ1\n"
                        + "bmN0aW9uMgAAAAAAAAABAgAAeHA=",
                CHECKED_FUNCTION_2);
    }
}
