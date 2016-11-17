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

public class IntPredicatesSerializationTest
{
    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkQWx3YXlzRmFsc2VJbnRQcmVkaWNhdGUAAAAAAAAAAQIAAHhw",
                IntPredicates.alwaysFalse());
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkQWx3YXlzVHJ1ZUludFByZWRpY2F0ZQAAAAAAAAABAgAAeHA=",
                IntPredicates.alwaysTrue());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkRXF1YWxzSW50UHJlZGljYXRlAAAAAAAAAAECAAFJAAhleHBlY3Rl\n"
                        + "ZHhwAAAAAA==",
                IntPredicates.equal(0));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkTGVzc1RoYW5JbnRQcmVkaWNhdGUAAAAAAAAAAQIAAUkACGV4cGVj\n"
                        + "dGVkeHAAAAAA",
                IntPredicates.lessThan(0));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkR3JlYXRlclRoYW5JbnRQcmVkaWNhdGUAAAAAAAAAAQIAAUkACGV4\n"
                        + "cGVjdGVkeHAAAAAA",
                IntPredicates.greaterThan(0));
    }

    @Test
    public void isEven()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkSW50SXNFdmVuUHJlZGljYXRlAAAAAAAAAAECAAB4cA==",
                IntPredicates.isEven());
    }

    @Test
    public void isOdd()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkSW50SXNPZGRQcmVkaWNhdGUAAAAAAAAAAQIAAHhw",
                IntPredicates.isOdd());
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkQW5kSW50UHJlZGljYXRlAAAAAAAAAAECAAJMAANvbmV0AERMb3Jn\n"
                        + "L2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUvSW50UHJl\n"
                        + "ZGljYXRlO0wAA3R3b3EAfgABeHBzcgBVb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9j\n"
                        + "ay5mYWN0b3J5LnByaW1pdGl2ZS5JbnRQcmVkaWNhdGVzJEludElzRXZlblByZWRpY2F0ZQAAAAAA\n"
                        + "AAABAgAAeHBzcgBUb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LnBy\n"
                        + "aW1pdGl2ZS5JbnRQcmVkaWNhdGVzJEludElzT2RkUHJlZGljYXRlAAAAAAAAAAECAAB4cA==",
                IntPredicates.and(IntPredicates.isEven(), IntPredicates.isOdd()));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkT3JJbnRQcmVkaWNhdGUAAAAAAAAAAQIAAkwAA29uZXQARExvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9JbnRQcmVk\n"
                        + "aWNhdGU7TAADdHdvcQB+AAF4cHNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkucHJpbWl0aXZlLkludFByZWRpY2F0ZXMkSW50SXNFdmVuUHJlZGljYXRlAAAAAAAA\n"
                        + "AAECAAB4cHNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJp\n"
                        + "bWl0aXZlLkludFByZWRpY2F0ZXMkSW50SXNPZGRQcmVkaWNhdGUAAAAAAAAAAQIAAHhw",
                IntPredicates.or(IntPredicates.isEven(), IntPredicates.isOdd()));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFByZWRpY2F0ZXMkTm90SW50UHJlZGljYXRlAAAAAAAAAAECAAFMAAZuZWdhdGV0AERM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUvSW50\n"
                        + "UHJlZGljYXRlO3hwc3IAVW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9y\n"
                        + "eS5wcmltaXRpdmUuSW50UHJlZGljYXRlcyRJbnRJc0V2ZW5QcmVkaWNhdGUAAAAAAAAAAQIAAHhw\n",
                IntPredicates.not(IntPredicates.isEven()));
    }
}
