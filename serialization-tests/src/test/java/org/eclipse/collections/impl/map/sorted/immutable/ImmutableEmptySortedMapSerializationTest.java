/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableEmptySortedMapSerializationTest
{
    static final String EXPECTED_BASE_64_FORM =
            "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zb3J0ZWQuaW1tdXRhYmxl\n"
                    + "LkltbXV0YWJsZVNvcnRlZE1hcFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHBwdwQAAAAA\n"
                    + "eA==";

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zb3J0ZWQuaW1tdXRhYmxl\n"
                        + "LkltbXV0YWJsZVNvcnRlZE1hcFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHBwdwQAAAAA\n"
                        + "eA==",
                ImmutableEmptySortedMap.INSTANCE);
    }
}
