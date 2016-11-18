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

public class AppendStringProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5BcHBl\n"
                        + "bmRTdHJpbmdQcm9jZWR1cmUAAAAAAAAAAQIAA1oABWZpcnN0TAAKYXBwZW5kYWJsZXQAFkxqYXZh\n"
                        + "L2xhbmcvQXBwZW5kYWJsZTtMAAlzZXBhcmF0b3J0ABJMamF2YS9sYW5nL1N0cmluZzt4cAFwcA==\n",
                new AppendStringProcedure<>(null, null));
    }
}
