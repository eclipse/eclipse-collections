/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class SynchronizedIntBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRJbnRCYWcAAAAAAAAAAQIAAHhyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RTeW5jaHJvbml6ZWRJ\n"
                        + "bnRDb2xsZWN0aW9uAAAAAAAAAAECAAJMAApjb2xsZWN0aW9udABHTG9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2FwaS9jb2xsZWN0aW9uL3ByaW1pdGl2ZS9NdXRhYmxlSW50Q29sbGVjdGlvbjtMAARs\n"
                        + "b2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgA9b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5iYWcubXV0YWJsZS5wcmltaXRpdmUuSW50SGFzaEJhZwAAAAAAAAABDAAAeHB3BAAAAAB4cQB+\n"
                        + "AAQ=",
                new SynchronizedIntBag(new IntHashBag()));
    }
}
