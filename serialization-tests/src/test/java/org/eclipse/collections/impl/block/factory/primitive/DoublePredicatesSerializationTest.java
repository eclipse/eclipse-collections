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

public class DoublePredicatesSerializationTest
{
    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkQWx3YXlzRmFsc2VEb3VibGVQcmVkaWNhdGUAAAAAAAAAAQIA\n"
                        + "AHhw",
                DoublePredicates.alwaysFalse());
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkQWx3YXlzVHJ1ZURvdWJsZVByZWRpY2F0ZQAAAAAAAAABAgAA\n"
                        + "eHA=",
                DoublePredicates.alwaysTrue());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkRXF1YWxzRG91YmxlUHJlZGljYXRlAAAAAAAAAAECAAFEAAhl\n"
                        + "eHBlY3RlZHhwAAAAAAAAAAA=",
                DoublePredicates.equal(0.0));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkTGVzc1RoYW5Eb3VibGVQcmVkaWNhdGUAAAAAAAAAAQIAAUQA\n"
                        + "CGV4cGVjdGVkeHAAAAAAAAAAAA==",
                DoublePredicates.lessThan(0.0));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkR3JlYXRlclRoYW5Eb3VibGVQcmVkaWNhdGUAAAAAAAAAAQIA\n"
                        + "AUQACGV4cGVjdGVkeHAAAAAAAAAAAA==",
                DoublePredicates.greaterThan(0.0));
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkQW5kRG91YmxlUHJlZGljYXRlAAAAAAAAAAECAAJMAANvbmV0\n"
                        + "AEdMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUv\n"
                        + "RG91YmxlUHJlZGljYXRlO0wAA3R3b3EAfgABeHBzcgBdb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5ibG9jay5mYWN0b3J5LnByaW1pdGl2ZS5Eb3VibGVQcmVkaWNhdGVzJExlc3NUaGFuRG91\n"
                        + "YmxlUHJlZGljYXRlAAAAAAAAAAECAAFEAAhleHBlY3RlZHhwAAAAAAAAAABzcQB+AAMAAAAAAAAA\n"
                        + "AA==",
                DoublePredicates.and(DoublePredicates.lessThan(0.0), DoublePredicates.lessThan(0.0)));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkT3JEb3VibGVQcmVkaWNhdGUAAAAAAAAAAQIAAkwAA29uZXQA\n"
                        + "R0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9E\n"
                        + "b3VibGVQcmVkaWNhdGU7TAADdHdvcQB+AAF4cHNyAF1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0aXZlLkRvdWJsZVByZWRpY2F0ZXMkTGVzc1RoYW5Eb3Vi\n"
                        + "bGVQcmVkaWNhdGUAAAAAAAAAAQIAAUQACGV4cGVjdGVkeHAAAAAAAAAAAHNxAH4AAwAAAAAAAAAA\n",
                DoublePredicates.or(DoublePredicates.lessThan(0.0), DoublePredicates.lessThan(0.0)));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkRvdWJsZVByZWRpY2F0ZXMkTm90RG91YmxlUHJlZGljYXRlAAAAAAAAAAECAAFMAAZuZWdh\n"
                        + "dGV0AEdMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRp\n"
                        + "dmUvRG91YmxlUHJlZGljYXRlO3hwc3IAXW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5wcmltaXRpdmUuRG91YmxlUHJlZGljYXRlcyRMZXNzVGhhbkRvdWJsZVByZWRp\n"
                        + "Y2F0ZQAAAAAAAAABAgABRAAIZXhwZWN0ZWR4cAAAAAAAAAAA",
                DoublePredicates.not(DoublePredicates.lessThan(0.0)));
    }
}
