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

public class MultimapFunctionsSerializationTest
{
    @Test
    public void get()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuTXVsdGlt\n"
                        + "YXBGdW5jdGlvbnMkTXVsdGltYXBHZXRGdW5jdGlvbgAAAAAAAAABAgABTAAIbXVsdGltYXB0AC9M\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL211bHRpbWFwL011bHRpbWFwO3hwcA==",
                MultimapFunctions.get(null));
    }
}
