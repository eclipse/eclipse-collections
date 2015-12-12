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

public class StringPredicates2SerializationTest
{
    @Test
    public void contains()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkQ29udGFpbnNTdHJpbmcAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xs\n"
                        + "ZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                StringPredicates2.contains());
    }

    @Test
    public void startsWith()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkU3RhcnRzV2l0aAAAAAAAAAABAgAAeHIANm9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHA=",
                StringPredicates2.startsWith());
    }

    @Test
    public void notStartsWith()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkTm90U3RhcnRzV2l0aAAAAAAAAAABAgAAeHIANm9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHA=",
                StringPredicates2.notStartsWith());
    }

    @Test
    public void endsWith()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkRW5kc1dpdGgAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                StringPredicates2.endsWith());
    }

    @Test
    public void notEndsWith()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkTm90RW5kc1dpdGgAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                StringPredicates2.notEndsWith());
    }

    @Test
    public void equalsIgnoreCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkRXF1YWxzSWdub3JlQ2FzZQAAAAAAAAABAgAAeHIANm9yZy5lY2xpcHNlLmNv\n"
                        + "bGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHA=",
                StringPredicates2.equalsIgnoreCase());
    }

    @Test
    public void notEqualsIgnoreCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkTm90RXF1YWxzSWdub3JlQ2FzZQAAAAAAAAABAgAAeHIANm9yZy5lY2xpcHNl\n"
                        + "LmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHA=\n",
                StringPredicates2.notEqualsIgnoreCase());
    }

    @Test
    public void matches()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlczIkTWF0Y2hlc1JlZ2V4AAAAAAAAAAECAAB4cgA2b3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyAAAAAAAAAAECAAB4cA==",
                StringPredicates2.matches());
    }
}
