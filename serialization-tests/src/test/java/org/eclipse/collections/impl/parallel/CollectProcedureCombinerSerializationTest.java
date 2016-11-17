/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CollectProcedureCombinerSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLkNvbGxlY3RQcm9j\n"
                        + "ZWR1cmVDb21iaW5lcgAAAAAAAAABAgAAeHIARm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "cGFyYWxsZWwuQWJzdHJhY3RUcmFuc2Zvcm1lckJhc2VkQ29tYmluZXIAAAAAAAAAAQIAAUwABnJl\n"
                        + "c3VsdHQAFkxqYXZhL3V0aWwvQ29sbGVjdGlvbjt4cgA/b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5wYXJhbGxlbC5BYnN0cmFjdFByb2NlZHVyZUNvbWJpbmVyAAAAAAAAAAECAAFaAA11c2VD\n"
                        + "b21iaW5lT25leHAAc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubGlzdC5tdXRhYmxl\n"
                        + "LkZhc3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new CollectProcedureCombiner<>(null, null, 1, false));
    }
}
