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

public class ShortPredicatesSerializationTest
{
    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRBbHdheXNGYWxzZVNob3J0UHJlZGljYXRlAAAAAAAAAAECAAB4\n"
                        + "cA==",
                ShortPredicates.alwaysFalse());
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRBbHdheXNUcnVlU2hvcnRQcmVkaWNhdGUAAAAAAAAAAQIAAHhw\n",
                ShortPredicates.alwaysTrue());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRFcXVhbHNTaG9ydFByZWRpY2F0ZQAAAAAAAAABAgABUwAIZXhw\n"
                        + "ZWN0ZWR4cAAA",
                ShortPredicates.equal((short) 0));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRMZXNzVGhhblNob3J0UHJlZGljYXRlAAAAAAAAAAECAAFTAAhl\n"
                        + "eHBlY3RlZHhwAAA=",
                ShortPredicates.lessThan((short) 0));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRHcmVhdGVyVGhhblNob3J0UHJlZGljYXRlAAAAAAAAAAECAAFT\n"
                        + "AAhleHBlY3RlZHhwAAA=",
                ShortPredicates.greaterThan((short) 0));
    }

    @Test
    public void isEven()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRTaG9ydElzRXZlblByZWRpY2F0ZQAAAAAAAAABAgAAeHA=",
                ShortPredicates.isEven());
    }

    @Test
    public void isOdd()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRTaG9ydElzT2RkUHJlZGljYXRlAAAAAAAAAAECAAB4cA==",
                ShortPredicates.isOdd());
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRBbmRTaG9ydFByZWRpY2F0ZQAAAAAAAAABAgACTAADb25ldABG\n"
                        + "TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZlL1No\n"
                        + "b3J0UHJlZGljYXRlO0wAA3R3b3EAfgABeHBzcgBZb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5ibG9jay5mYWN0b3J5LnByaW1pdGl2ZS5TaG9ydFByZWRpY2F0ZXMkU2hvcnRJc0V2ZW5QcmVk\n"
                        + "aWNhdGUAAAAAAAAAAQIAAHhwc3IAWG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "ZmFjdG9yeS5wcmltaXRpdmUuU2hvcnRQcmVkaWNhdGVzJFNob3J0SXNPZGRQcmVkaWNhdGUAAAAA\n"
                        + "AAAAAQIAAHhw",
                ShortPredicates.and(ShortPredicates.isEven(), ShortPredicates.isOdd()));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyRPclNob3J0UHJlZGljYXRlAAAAAAAAAAECAAJMAANvbmV0AEZM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUvU2hv\n"
                        + "cnRQcmVkaWNhdGU7TAADdHdvcQB+AAF4cHNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLmZhY3RvcnkucHJpbWl0aXZlLlNob3J0UHJlZGljYXRlcyRTaG9ydElzRXZlblByZWRp\n"
                        + "Y2F0ZQAAAAAAAAABAgAAeHBzcgBYb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5m\n"
                        + "YWN0b3J5LnByaW1pdGl2ZS5TaG9ydFByZWRpY2F0ZXMkU2hvcnRJc09kZFByZWRpY2F0ZQAAAAAA\n"
                        + "AAABAgAAeHA=",
                ShortPredicates.or(ShortPredicates.isEven(), ShortPredicates.isOdd()));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLlNob3J0UHJlZGljYXRlcyROb3RTaG9ydFByZWRpY2F0ZQAAAAAAAAABAgABTAAGbmVnYXRl\n"
                        + "dABGTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZl\n"
                        + "L1Nob3J0UHJlZGljYXRlO3hwc3IAWW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "ZmFjdG9yeS5wcmltaXRpdmUuU2hvcnRQcmVkaWNhdGVzJFNob3J0SXNFdmVuUHJlZGljYXRlAAAA\n"
                        + "AAAAAAECAAB4cA==",
                ShortPredicates.not(ShortPredicates.isEven()));
    }
}
