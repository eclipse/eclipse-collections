/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.immutable;

import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.SortedBags;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableEmptySortedBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5zb3J0ZWQuaW1tdXRhYmxl\n"
                        + "LkltbXV0YWJsZVNvcnRlZEJhZ1NlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAABw\n"
                        + "eA==",
                SortedBags.immutable.empty());
    }

    @Test
    public void serializedForm_comparator()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5zb3J0ZWQuaW1tdXRhYmxl\n"
                        + "LkltbXV0YWJsZVNvcnRlZEJhZ1NlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAABz\n"
                        + "cgBIb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LkNvbXBhcmF0b3Jz\n"
                        + "JFJldmVyc2VDb21wYXJhdG9yAAAAAAAAAAECAAFMAApjb21wYXJhdG9ydAAWTGphdmEvdXRpbC9D\n"
                        + "b21wYXJhdG9yO3hwc3IATW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9y\n"
                        + "eS5Db21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9yAAAAAAAAAAECAAB4cHg=",
                SortedBags.immutable.empty(Comparators.reverseNaturalOrder()));
    }
}
