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

public class RejectProcedureCombinerSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLlJlamVjdFByb2Nl\n"
                        + "ZHVyZUNvbWJpbmVyAAAAAAAAAAECAAB4cgBEb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5w\n"
                        + "YXJhbGxlbC5BYnN0cmFjdFByZWRpY2F0ZUJhc2VkQ29tYmluZXIAAAAAAAAAAQIAAUwABnJlc3Vs\n"
                        + "dHQAFkxqYXZhL3V0aWwvQ29sbGVjdGlvbjt4cgA/b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5wYXJhbGxlbC5BYnN0cmFjdFByb2NlZHVyZUNvbWJpbmVyAAAAAAAAAAECAAFaAA11c2VDb21i\n"
                        + "aW5lT25leHABc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubGlzdC5tdXRhYmxlLkZh\n"
                        + "c3RMaXN0AAAAAAAAAAEMAAB4cHcEAAAAAHg=",
                new RejectProcedureCombiner<>(null, null, 1, true));
    }
}
