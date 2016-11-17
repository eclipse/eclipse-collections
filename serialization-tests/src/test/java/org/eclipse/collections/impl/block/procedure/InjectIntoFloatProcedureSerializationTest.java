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

import org.eclipse.collections.impl.block.procedure.primitive.InjectIntoFloatProcedure;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class InjectIntoFloatProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5wcmlt\n"
                        + "aXRpdmUuSW5qZWN0SW50b0Zsb2F0UHJvY2VkdXJlAAAAAAAAAAECAAJGAAZyZXN1bHRMAAhmdW5j\n"
                        + "dGlvbnQAUUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0\n"
                        + "aXZlL0Zsb2F0T2JqZWN0VG9GbG9hdEZ1bmN0aW9uO3hwAAAAAHA=",
                new InjectIntoFloatProcedure<>(0.0F, null));
    }
}
