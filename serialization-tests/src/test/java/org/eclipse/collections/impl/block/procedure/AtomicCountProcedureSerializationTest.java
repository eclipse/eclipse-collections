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

public class AtomicCountProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5BdG9t\n"
                        + "aWNDb3VudFByb2NlZHVyZQAAAAAAAAABAgACTAAFY291bnR0ACtMamF2YS91dGlsL2NvbmN1cnJl\n"
                        + "bnQvYXRvbWljL0F0b21pY0ludGVnZXI7TAAJcHJlZGljYXRldAA3TG9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvUHJlZGljYXRlO3hwc3IAKWphdmEudXRpbC5jb25j\n"
                        + "dXJyZW50LmF0b21pYy5BdG9taWNJbnRlZ2VyVj9ezIxsFooCAAFJAAV2YWx1ZXhyABBqYXZhLmxh\n"
                        + "bmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAABw",
                new AtomicCountProcedure<>(null));
    }
}
