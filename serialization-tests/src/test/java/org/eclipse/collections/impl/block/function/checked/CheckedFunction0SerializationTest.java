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

public class CheckedFunction0SerializationTest
{
    private static final CheckedFunction0<?> CHECKED_FUNCTION_0 = new CheckedFunction0<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Object safeValue()
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
                        + "ZWQuQ2hlY2tlZEZ1bmN0aW9uMFNlcmlhbGl6YXRpb25UZXN0JDEAAAAAAAAAAQIAAHhyAERvcmcu\n"
                        + "ZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLmNoZWNrZWQuQ2hlY2tlZEZ1\n"
                        + "bmN0aW9uMAAAAAAAAAABAgAAeHA=",
                CHECKED_FUNCTION_0);
    }
}
