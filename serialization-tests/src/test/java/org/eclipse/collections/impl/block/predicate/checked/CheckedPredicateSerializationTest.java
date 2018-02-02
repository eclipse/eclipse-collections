/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.predicate.checked;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CheckedPredicateSerializationTest
{
    private static final CheckedPredicate<?> CHECKED_PREDICATE = new CheckedPredicate<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean safeAccept(Object object)
        {
            return false;
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5jaGVj\n"
                        + "a2VkLkNoZWNrZWRQcmVkaWNhdGVTZXJpYWxpemF0aW9uVGVzdCQxAAAAAAAAAAECAAB4cgBFb3Jn\n"
                        + "LmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5wcmVkaWNhdGUuY2hlY2tlZC5DaGVja2Vk\n"
                        + "UHJlZGljYXRlAAAAAAAAAAECAAB4cA==",
                CHECKED_PREDICATE);
    }
}
