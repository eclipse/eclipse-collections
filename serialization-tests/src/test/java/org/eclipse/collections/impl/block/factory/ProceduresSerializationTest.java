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

public class ProceduresSerializationTest
{
    @Test
    public void throwing()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlcyRUaHJvd2luZ1Byb2NlZHVyZUFkYXB0ZXIAAAAAAAAAAQIAAUwAEXRocm93aW5nUHJvY2Vk\n"
                        + "dXJldABITG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvYmxvY2svcHJvY2VkdXJlL2NoZWNr\n"
                        + "ZWQvVGhyb3dpbmdQcm9jZWR1cmU7eHIARW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2sucHJvY2VkdXJlLmNoZWNrZWQuQ2hlY2tlZFByb2NlZHVyZQAAAAAAAAABAgAAeHBw",
                Procedures.throwing(null));
    }

    @Test
    public void println()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlcyRQcmludGxuUHJvY2VkdXJlAAAAAAAAAAECAAFMAAZzdHJlYW10ABVMamF2YS9pby9Qcmlu\n"
                        + "dFN0cmVhbTt4cHA=",
                Procedures.println(null));
    }

    @Test
    public void append()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlcyRBcHBlbmRQcm9jZWR1cmUAAAAAAAAAAQIAAUwACmFwcGVuZGFibGV0ABZMamF2YS9sYW5n\n"
                        + "L0FwcGVuZGFibGU7eHBw",
                Procedures.append(null));
    }

    @Test
    public void synchronizedEach()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlcyRTeW5jaHJvbml6ZWRQcm9jZWR1cmUAAAAAAAAAAQIAAUwACXByb2NlZHVyZXQAN0xvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2VkdXJlL1Byb2NlZHVyZTt4cHA=",
                Procedures.synchronizedEach(null));
    }

    @Test
    public void asProcedure()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlcyRPYmplY3RJbnRQcm9jZWR1cmVBZGFwdGVyAAAAAAAAAAICAAJJAAVjb3VudEwAEm9iamVj\n"
                        + "dEludFByb2NlZHVyZXQASkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJvY2Vk\n"
                        + "dXJlL3ByaW1pdGl2ZS9PYmplY3RJbnRQcm9jZWR1cmU7eHAAAAAAcA==",
                Procedures.fromObjectIntProcedure(null));
    }

    @Test
    public void bind()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlcyRCaW5kUHJvY2VkdXJlAAAAAAAAAAECAAJMAAlwYXJhbWV0ZXJ0ABJMamF2YS9sYW5nL09i\n"
                        + "amVjdDtMAAlwcm9jZWR1cmV0ADhMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3By\n"
                        + "b2NlZHVyZS9Qcm9jZWR1cmUyO3hwcHA=",
                Procedures.bind(null, null));
    }
}
