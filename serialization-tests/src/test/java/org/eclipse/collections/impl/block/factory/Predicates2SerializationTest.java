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

public class Predicates2SerializationTest
{
    @Test
    public void throwing()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkVGhyb3dpbmdQcmVkaWNhdGUyQWRhcHRlcgAAAAAAAAABAgABTAASdGhyb3dpbmdQcmVk\n"
                        + "aWNhdGUydABJTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvYmxvY2svcHJlZGljYXRlL2No\n"
                        + "ZWNrZWQvVGhyb3dpbmdQcmVkaWNhdGUyO3hyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLnByZWRpY2F0ZS5jaGVja2VkLkNoZWNrZWRQcmVkaWNhdGUyAAAAAAAAAAECAAB4cHA=\n",
                Predicates2.throwing(null));
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkQWx3YXlzVHJ1ZQAAAAAAAAABAgAAeHIANm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmlt\n"
                        + "cGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHA=",
                Predicates2.alwaysTrue());
    }

    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkQWx3YXlzRmFsc2UAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.alwaysFalse());
    }

    @Test
    public void attributeEqual()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkQXR0cmlidXRlUHJlZGljYXRlczIAAAAAAAAAAQIAAkwACGZ1bmN0aW9udAA1TG9yZy9l\n"
                        + "Y2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjtMAAlwcmVkaWNh\n"
                        + "dGV0ADhMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNh\n"
                        + "dGUyO3hyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIAAAAAAAAAAQIAAHhwcHNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuUHJlZGljYXRlczIkRXF1YWwAAAAAAAAAAQIAAHhxAH4AAw==",
                Predicates2.attributeEqual(null));
    }

    @Test
    public void instanceOf()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkSXNJbnN0YW5jZU9mAAAAAAAAAAECAAB4cgA2b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyAAAAAAAAAAECAAB4cA==",
                Predicates2.instanceOf());
    }

    @Test
    public void notInstanceOf()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTm90SW5zdGFuY2VPZgAAAAAAAAABAgAAeHIANm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHA=",
                Predicates2.notInstanceOf());
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTGVzc1RoYW4AAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.<Integer>lessThan());
    }

    @Test
    public void lessThanOrEqualTo()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTGVzc1RoYW5PckVxdWFsAAAAAAAAAAECAAB4cgA2b3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyAAAAAAAAAAECAAB4cA==",
                Predicates2.<Integer>lessThanOrEqualTo());
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkR3JlYXRlclRoYW4AAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.<Integer>greaterThan());
    }

    @Test
    public void greaterThanOrEqualTo()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkR3JlYXRlclRoYW5PckVxdWFsAAAAAAAAAAECAAB4cgA2b3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyAAAAAAAAAAECAAB4cA==",
                Predicates2.<Integer>greaterThanOrEqualTo());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkRXF1YWwAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJs\n"
                        + "b2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.equal());
    }

    @Test
    public void notEqual()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTm90RXF1YWwAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.notEqual());
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkQW5kAAAAAAAAAAECAAJMAARsZWZ0dAA4TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2Fw\n"
                        + "aS9ibG9jay9wcmVkaWNhdGUvUHJlZGljYXRlMjtMAAVyaWdodHEAfgABeHIANm9yZy5lY2xpcHNl\n"
                        + "LmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHBw\n"
                        + "cA==",
                Predicates2.and(null, null));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkT3IAAAAAAAAAAQIAAkwABGxlZnR0ADhMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBp\n"
                        + "L2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGUyO0wABXJpZ2h0cQB+AAF4cgA2b3JnLmVjbGlwc2Uu\n"
                        + "Y29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyAAAAAAAAAAECAAB4cHBw\n",
                Predicates2.or(null, null));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTm90AAAAAAAAAAECAAFMAAlwcmVkaWNhdGV0ADhMb3JnL2VjbGlwc2UvY29sbGVjdGlv\n"
                        + "bnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGUyO3hyADZvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhwcA==",
                Predicates2.not(null));
    }

    @Test
    public void in()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkSW4AAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.in());
    }

    @Test
    public void notIn()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTm90SW4AAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJs\n"
                        + "b2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.notIn());
    }

    @Test
    public void isNull()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkSXNOdWxsAAAAAAAAAAECAAB4cgA2b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyAAAAAAAAAAECAAB4cA==",
                Predicates2.isNull());
    }

    @Test
    public void notNull()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTm90TnVsbAAAAAAAAAABAgAAeHIANm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "YmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzMgAAAAAAAAABAgAAeHA=",
                Predicates2.notNull());
    }

    @Test
    public void sameAs()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkSXNJZGVudGljYWwAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.sameAs());
    }

    @Test
    public void notSameAs()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlczIkTm90SWRlbnRpdGljYWwAAAAAAAAAAQIAAHhyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlczIAAAAAAAAAAQIAAHhw",
                Predicates2.notSameAs());
    }
}
