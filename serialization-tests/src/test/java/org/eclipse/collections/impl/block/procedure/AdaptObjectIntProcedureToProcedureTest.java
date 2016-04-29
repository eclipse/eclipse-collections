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

public class AdaptObjectIntProcedureToProcedureTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5BZGFw\n"
                        + "dE9iamVjdEludFByb2NlZHVyZVRvUHJvY2VkdXJlAAAAAAAAAAECAAJMAAVpbmRleHQAJkxvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9pbXBsL0NvdW50ZXI7TAASb2JqZWN0SW50UHJvY2VkdXJldABK\n"
                        + "TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcm9jZWR1cmUvcHJpbWl0aXZlL09i\n"
                        + "amVjdEludFByb2NlZHVyZTt4cHNyACRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLkNvdW50\n"
                        + "ZXIAAAAAAAAAAQwAAHhwdwQAAAAAeHA=",
                new AdaptObjectIntProcedureToProcedure<>(null));
    }
}
