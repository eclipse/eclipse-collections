/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class SynchronizedObjectBooleanMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRPYmplY3RCb29sZWFuTWFwAAAAAAAAAAECAAJMAARsb2NrdAASTGphdmEv\n"
                        + "bGFuZy9PYmplY3Q7TAADbWFwdABDTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJp\n"
                        + "bWl0aXZlL011dGFibGVPYmplY3RCb29sZWFuTWFwO3hwcQB+AANzcgBHb3JnLmVjbGlwc2UuY29s\n"
                        + "bGVjdGlvbnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuT2JqZWN0Qm9vbGVhbkhhc2hNYXAA\n"
                        + "AAAAAAAAAQwAAHhwdwgAAAAAPwAAAHg=",
                new SynchronizedObjectBooleanMap<>(new ObjectBooleanHashMap<>()));
    }
}
