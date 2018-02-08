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

public class CheckedFunctionSerializationTest
{
    private static final CheckedFunction<?, ?> CHECKED_FUNCTION = new CheckedFunction<Object, Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Object safeValueOf(Object object)
        {
            return null;
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLmNoZWNr\n"
                        + "ZWQuQ2hlY2tlZEZ1bmN0aW9uU2VyaWFsaXphdGlvblRlc3QkMQAAAAAAAAABAgAAeHIAQ29yZy5l\n"
                        + "Y2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZnVuY3Rpb24uY2hlY2tlZC5DaGVja2VkRnVu\n"
                        + "Y3Rpb24AAAAAAAAAAQIAAHhw",
                CHECKED_FUNCTION);
    }
}
