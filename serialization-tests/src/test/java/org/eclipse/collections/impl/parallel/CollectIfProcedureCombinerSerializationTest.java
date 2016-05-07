/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CollectIfProcedureCombinerSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLkNvbGxlY3RJZlBy\n"
                        + "b2NlZHVyZUNvbWJpbmVyAAAAAAAAAAECAAB4cgBGb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5wYXJhbGxlbC5BYnN0cmFjdFRyYW5zZm9ybWVyQmFzZWRDb21iaW5lcgAAAAAAAAABAgABTAAG\n"
                        + "cmVzdWx0dAAWTGphdmEvdXRpbC9Db2xsZWN0aW9uO3hyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLnBhcmFsbGVsLkFic3RyYWN0UHJvY2VkdXJlQ29tYmluZXIAAAAAAAAAAQIAAVoADXVz\n"
                        + "ZUNvbWJpbmVPbmV4cABzcgAyb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5saXN0Lm11dGFi\n"
                        + "bGUuRmFzdExpc3QAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new CollectIfProcedureCombiner<>(null, null, 1, false));
    }
}
