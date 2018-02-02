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

public class CheckedPredicate2SerializationTest
{
    private static final CheckedPredicate2<?, ?> CHECKED_PREDICATE_2 = new CheckedPredicate2<Object, Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean safeAccept(Object object, Object param)
        {
            return false;
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByZWRpY2F0ZS5jaGVj\n"
                        + "a2VkLkNoZWNrZWRQcmVkaWNhdGUyU2VyaWFsaXphdGlvblRlc3QkMQAAAAAAAAABAgAAeHIARm9y\n"
                        + "Zy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2sucHJlZGljYXRlLmNoZWNrZWQuQ2hlY2tl\n"
                        + "ZFByZWRpY2F0ZTIAAAAAAAAAAQIAAHhw",
                CHECKED_PREDICATE_2);
    }
}
