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

public class FloatPredicatesSerializationTest
{
    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyRBbHdheXNGYWxzZUZsb2F0UHJlZGljYXRlAAAAAAAAAAECAAB4\n"
                        + "cA==",
                FloatPredicates.alwaysFalse());
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyRBbHdheXNUcnVlRmxvYXRQcmVkaWNhdGUAAAAAAAAAAQIAAHhw\n",
                FloatPredicates.alwaysTrue());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyRFcXVhbHNGbG9hdFByZWRpY2F0ZQAAAAAAAAABAgABRgAIZXhw\n"
                        + "ZWN0ZWR4cAAAAAA=",
                FloatPredicates.equal(0.0f));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyRMZXNzVGhhbkZsb2F0UHJlZGljYXRlAAAAAAAAAAECAAFGAAhl\n"
                        + "eHBlY3RlZHhwAAAAAA==",
                FloatPredicates.lessThan(0.0f));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyRHcmVhdGVyVGhhbkZsb2F0UHJlZGljYXRlAAAAAAAAAAECAAFG\n"
                        + "AAhleHBlY3RlZHhwAAAAAA==",
                FloatPredicates.greaterThan(0.0f));
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyRBbmRGbG9hdFByZWRpY2F0ZQAAAAAAAAABAgACTAADb25ldABG\n"
                        + "TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZlL0Zs\n"
                        + "b2F0UHJlZGljYXRlO0wAA3R3b3EAfgABeHBzcgBbb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5ibG9jay5mYWN0b3J5LnByaW1pdGl2ZS5GbG9hdFByZWRpY2F0ZXMkTGVzc1RoYW5GbG9hdFBy\n"
                        + "ZWRpY2F0ZQAAAAAAAAABAgABRgAIZXhwZWN0ZWR4cAAAAABzcgBeb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LnByaW1pdGl2ZS5GbG9hdFByZWRpY2F0ZXMkR3JlYXRl\n"
                        + "clRoYW5GbG9hdFByZWRpY2F0ZQAAAAAAAAABAgABRgAIZXhwZWN0ZWR4cAAAAAA=",
                FloatPredicates.and(FloatPredicates.lessThan(0.0f), FloatPredicates.greaterThan(0.0f)));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyRPckZsb2F0UHJlZGljYXRlAAAAAAAAAAECAAJMAANvbmV0AEZM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9wcmltaXRpdmUvRmxv\n"
                        + "YXRQcmVkaWNhdGU7TAADdHdvcQB+AAF4cHNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLmZhY3RvcnkucHJpbWl0aXZlLkZsb2F0UHJlZGljYXRlcyRMZXNzVGhhbkZsb2F0UHJl\n"
                        + "ZGljYXRlAAAAAAAAAAECAAFGAAhleHBlY3RlZHhwAAAAAHNyAF5vcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0aXZlLkZsb2F0UHJlZGljYXRlcyRHcmVhdGVy\n"
                        + "VGhhbkZsb2F0UHJlZGljYXRlAAAAAAAAAAECAAFGAAhleHBlY3RlZHhwAAAAAA==",
                FloatPredicates.or(FloatPredicates.lessThan(0.0f), FloatPredicates.greaterThan(0.0f)));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkZsb2F0UHJlZGljYXRlcyROb3RGbG9hdFByZWRpY2F0ZQAAAAAAAAABAgABTAAGbmVnYXRl\n"
                        + "dABGTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZl\n"
                        + "L0Zsb2F0UHJlZGljYXRlO3hwc3IAW29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "ZmFjdG9yeS5wcmltaXRpdmUuRmxvYXRQcmVkaWNhdGVzJExlc3NUaGFuRmxvYXRQcmVkaWNhdGUA\n"
                        + "AAAAAAAAAQIAAUYACGV4cGVjdGVkeHAAAAAA",
                FloatPredicates.not(FloatPredicates.lessThan(0.0f)));
    }
}
