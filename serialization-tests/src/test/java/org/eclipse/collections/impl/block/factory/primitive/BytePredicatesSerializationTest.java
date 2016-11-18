/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class BytePredicatesSerializationTest
{
    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJEFsd2F5c0ZhbHNlQnl0ZVByZWRpY2F0ZQAAAAAAAAABAgAAeHA=\n",
                BytePredicates.alwaysFalse());
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJEFsd2F5c1RydWVCeXRlUHJlZGljYXRlAAAAAAAAAAECAAB4cA==\n",
                BytePredicates.alwaysTrue());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJEVxdWFsc0J5dGVQcmVkaWNhdGUAAAAAAAAAAQIAAUIACGV4cGVj\n"
                        + "dGVkeHAA",
                BytePredicates.equal((byte) 0));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJExlc3NUaGFuQnl0ZVByZWRpY2F0ZQAAAAAAAAABAgABQgAIZXhw\n"
                        + "ZWN0ZWR4cAA=",
                BytePredicates.lessThan((byte) 0));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJEdyZWF0ZXJUaGFuQnl0ZVByZWRpY2F0ZQAAAAAAAAABAgABQgAI\n"
                        + "ZXhwZWN0ZWR4cAA=",
                BytePredicates.greaterThan((byte) 0));
    }

    @Test
    public void isEven()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJEJ5dGVJc0V2ZW5QcmVkaWNhdGUAAAAAAAAAAQIAAHhw",
                BytePredicates.isEven());
    }

    @Test
    public void isOdd()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJEJ5dGVJc09kZFByZWRpY2F0ZQAAAAAAAAABAgAAeHA=",
                BytePredicates.isOdd());
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJEFuZEJ5dGVQcmVkaWNhdGUAAAAAAAAAAQIAAkwAA29uZXQARUxv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9CeXRl\n"
                        + "UHJlZGljYXRlO0wAA3R3b3EAfgABeHBzcgBXb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LnByaW1pdGl2ZS5CeXRlUHJlZGljYXRlcyRCeXRlSXNFdmVuUHJlZGljYXRl\n"
                        + "AAAAAAAAAAECAAB4cHNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rv\n"
                        + "cnkucHJpbWl0aXZlLkJ5dGVQcmVkaWNhdGVzJEJ5dGVJc09kZFByZWRpY2F0ZQAAAAAAAAABAgAA\n"
                        + "eHA=",
                BytePredicates.and(BytePredicates.isEven(), BytePredicates.isOdd()));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJE9yQnl0ZVByZWRpY2F0ZQAAAAAAAAABAgACTAADb25ldABFTG9y\n"
                        + "Zy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZlL0J5dGVQ\n"
                        + "cmVkaWNhdGU7TAADdHdvcQB+AAF4cHNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJs\n"
                        + "b2NrLmZhY3RvcnkucHJpbWl0aXZlLkJ5dGVQcmVkaWNhdGVzJEJ5dGVJc0V2ZW5QcmVkaWNhdGUA\n"
                        + "AAAAAAAAAQIAAHhwc3IAVm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9y\n"
                        + "eS5wcmltaXRpdmUuQnl0ZVByZWRpY2F0ZXMkQnl0ZUlzT2RkUHJlZGljYXRlAAAAAAAAAAECAAB4\n"
                        + "cA==",
                BytePredicates.or(BytePredicates.isEven(), BytePredicates.isOdd()));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkJ5dGVQcmVkaWNhdGVzJE5vdEJ5dGVQcmVkaWNhdGUAAAAAAAAAAQIAAUwABm5lZ2F0ZXQA\n"
                        + "RUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9C\n"
                        + "eXRlUHJlZGljYXRlO3hwc3IAV29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFj\n"
                        + "dG9yeS5wcmltaXRpdmUuQnl0ZVByZWRpY2F0ZXMkQnl0ZUlzRXZlblByZWRpY2F0ZQAAAAAAAAAB\n"
                        + "AgAAeHA=",
                BytePredicates.not(BytePredicates.isEven()));
    }
}
