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

public class MaxComparatorProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5NYXhD\n"
                        + "b21wYXJhdG9yUHJvY2VkdXJlAAAAAAAAAAECAAB4cgBAb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5ibG9jay5wcm9jZWR1cmUuQ29tcGFyYXRvclByb2NlZHVyZQAAAAAAAAABAgADWgASdmlz\n"
                        + "aXRlZEF0TGVhc3RPbmNlTAAKY29tcGFyYXRvcnQAFkxqYXZhL3V0aWwvQ29tcGFyYXRvcjtMAAZy\n"
                        + "ZXN1bHR0ABJMamF2YS9sYW5nL09iamVjdDt4cABwcA==",
                new MaxComparatorProcedure<>(null));
    }
}
