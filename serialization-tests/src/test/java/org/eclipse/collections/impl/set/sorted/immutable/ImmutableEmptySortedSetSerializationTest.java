/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.sorted.immutable;

import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableEmptySortedSetSerializationTest
{
    @Test
    public void serializedForm_no_comparator()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5zb3J0ZWQuaW1tdXRhYmxl\n"
                        + "LkltbXV0YWJsZVNvcnRlZFNldFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHBwdwQAAAAA\n"
                        + "eA==",
                ImmutableEmptySortedSet.INSTANCE);
    }

    @Test
    public void serializedForm_with_comparator()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5zb3J0ZWQuaW1tdXRhYmxl\n"
                        + "LkltbXV0YWJsZVNvcnRlZFNldFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHBzcgBNb3Jn\n"
                        + "LmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LkNvbXBhcmF0b3JzJE5hdHVy\n"
                        + "YWxPcmRlckNvbXBhcmF0b3IAAAAAAAAAAQIAAHhwdwQAAAAAeA==",
                new ImmutableEmptySortedSet<>(Comparators.naturalOrder()));
    }
}
