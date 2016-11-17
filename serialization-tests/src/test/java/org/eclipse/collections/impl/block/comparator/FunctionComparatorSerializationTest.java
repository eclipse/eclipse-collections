/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.comparator;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class FunctionComparatorSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmNvbXBhcmF0b3IuRnVu\n"
                        + "Y3Rpb25Db21wYXJhdG9yAAAAAAAAAAECAAJMAApjb21wYXJhdG9ydAAWTGphdmEvdXRpbC9Db21w\n"
                        + "YXJhdG9yO0wACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9m\n"
                        + "dW5jdGlvbi9GdW5jdGlvbjt4cHBw",
                new FunctionComparator<>(null, null));
    }
}
