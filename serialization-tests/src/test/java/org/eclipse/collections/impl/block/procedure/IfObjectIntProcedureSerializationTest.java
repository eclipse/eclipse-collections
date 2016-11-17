/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class IfObjectIntProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5JZk9i\n"
                        + "amVjdEludFByb2NlZHVyZQAAAAAAAAACAgADTAAFaW5kZXh0ACZMb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvaW1wbC9Db3VudGVyO0wAEm9iamVjdEludFByb2NlZHVyZXQASkxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL3ByaW1pdGl2ZS9PYmplY3RJbnRQcm9jZWR1\n"
                        + "cmU7TAAJcHJlZGljYXRldAA3TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVk\n"
                        + "aWNhdGUvUHJlZGljYXRlO3hwc3IAJG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuQ291bnRl\n"
                        + "cgAAAAAAAAABDAAAeHB3BAAAAAB4cHA=",
                new IfObjectIntProcedure<>(null, null));
    }
}
