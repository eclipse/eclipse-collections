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

public class IfProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5JZlBy\n"
                        + "b2NlZHVyZQAAAAAAAAABAgADTAANZWxzZVByb2NlZHVyZXQAN0xvcmcvZWNsaXBzZS9jb2xsZWN0\n"
                        + "aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL1Byb2NlZHVyZTtMAAlwcmVkaWNhdGV0ADdMb3JnL2Vj\n"
                        + "bGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGU7TAAJcHJvY2Vk\n"
                        + "dXJlcQB+AAF4cHBwcA==",
                new IfProcedure<>(null, null, null));
    }
}
