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

public class LongPredicatesSerializationTest
{
    @Test
    public void isZero()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuTG9uZ1By\n"
                        + "ZWRpY2F0ZXMkTG9uZ0lzWmVybwAAAAAAAAABAgAAeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cA==",
                LongPredicates.isZero());
    }

    @Test
    public void isPositive()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuTG9uZ1By\n"
                        + "ZWRpY2F0ZXMkTG9uZ0lzUG9zaXRpdmUAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                LongPredicates.isPositive());
    }

    @Test
    public void isNegative()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuTG9uZ1By\n"
                        + "ZWRpY2F0ZXMkTG9uZ0lzTmVnYXRpdmUAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                LongPredicates.isNegative());
    }

    @Test
    public void isOdd()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuTG9uZ1By\n"
                        + "ZWRpY2F0ZXMkTG9uZ0lzT2RkAAAAAAAAAAECAAB4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhw",
                LongPredicates.isOdd());
    }

    @Test
    public void isEven()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuTG9uZ1By\n"
                        + "ZWRpY2F0ZXMkTG9uZ0lzRXZlbgAAAAAAAAABAgAAeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cA==",
                LongPredicates.isEven());
    }
}
