/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class Procedures2SerializationTest
{
    @Test
    public void throwing()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlczIkVGhyb3dpbmdQcm9jZWR1cmUyQWRhcHRlcgAAAAAAAAABAgABTAASdGhyb3dpbmdQcm9j\n"
                        + "ZWR1cmUydABJTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvYmxvY2svcHJvY2VkdXJlL2No\n"
                        + "ZWNrZWQvVGhyb3dpbmdQcm9jZWR1cmUyO3hyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLnByb2NlZHVyZS5jaGVja2VkLkNoZWNrZWRQcm9jZWR1cmUyAAAAAAAAAAECAAB4cHA=\n",
                Procedures2.throwing(null));
    }

    @Test
    public void addToCollection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlczIkQWRkVG9Db2xsZWN0aW9uAAAAAAAAAAECAAB4cA==",
                Procedures2.addToCollection());
    }

    @Test
    public void removeFromCollection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlczIkUmVtb3ZlRnJvbUNvbGxlY3Rpb24AAAAAAAAAAQIAAHhw",
                Procedures2.removeFromCollection());
    }
}
