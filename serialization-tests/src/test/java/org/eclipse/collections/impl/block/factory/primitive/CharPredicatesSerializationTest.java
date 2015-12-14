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

public class CharPredicatesSerializationTest
{
    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJEFsd2F5c0ZhbHNlQ2hhclByZWRpY2F0ZQAAAAAAAAABAgAAeHA=\n",
                CharPredicates.alwaysFalse());
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJEFsd2F5c1RydWVDaGFyUHJlZGljYXRlAAAAAAAAAAECAAB4cA==\n",
                CharPredicates.alwaysTrue());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJEVxdWFsc0NoYXJQcmVkaWNhdGUAAAAAAAAAAQIAAUMACGV4cGVj\n"
                        + "dGVkeHAAAA==",
                CharPredicates.equal((char) 0));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJExlc3NUaGFuQ2hhclByZWRpY2F0ZQAAAAAAAAABAgABQwAIZXhw\n"
                        + "ZWN0ZWR4cAAA",
                CharPredicates.lessThan((char) 0));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJEdyZWF0ZXJUaGFuQ2hhclByZWRpY2F0ZQAAAAAAAAABAgABQwAI\n"
                        + "ZXhwZWN0ZWR4cAAA",
                CharPredicates.greaterThan((char) 0));
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJEFuZENoYXJQcmVkaWNhdGUAAAAAAAAAAQIAAkwAA29uZXQARUxv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9DaGFy\n"
                        + "UHJlZGljYXRlO0wAA3R3b3EAfgABeHBzcgBZb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LnByaW1pdGl2ZS5DaGFyUHJlZGljYXRlcyRMZXNzVGhhbkNoYXJQcmVkaWNh\n"
                        + "dGUAAAAAAAAAAQIAAUMACGV4cGVjdGVkeHAAAHNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5p\n"
                        + "bXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0aXZlLkNoYXJQcmVkaWNhdGVzJEdyZWF0ZXJUaGFuQ2hh\n"
                        + "clByZWRpY2F0ZQAAAAAAAAABAgABQwAIZXhwZWN0ZWR4cAAA",
                CharPredicates.and(CharPredicates.lessThan((char) 0), CharPredicates.greaterThan((char) 0)));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJE9yQ2hhclByZWRpY2F0ZQAAAAAAAAABAgACTAADb25ldABFTG9y\n"
                        + "Zy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZlL0NoYXJQ\n"
                        + "cmVkaWNhdGU7TAADdHdvcQB+AAF4cHNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJs\n"
                        + "b2NrLmZhY3RvcnkucHJpbWl0aXZlLkNoYXJQcmVkaWNhdGVzJExlc3NUaGFuQ2hhclByZWRpY2F0\n"
                        + "ZQAAAAAAAAABAgABQwAIZXhwZWN0ZWR4cAAAc3IAXG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmlt\n"
                        + "cGwuYmxvY2suZmFjdG9yeS5wcmltaXRpdmUuQ2hhclByZWRpY2F0ZXMkR3JlYXRlclRoYW5DaGFy\n"
                        + "UHJlZGljYXRlAAAAAAAAAAECAAFDAAhleHBlY3RlZHhwAAA=",
                CharPredicates.or(CharPredicates.lessThan((char) 0), CharPredicates.greaterThan((char) 0)));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkNoYXJQcmVkaWNhdGVzJE5vdENoYXJQcmVkaWNhdGUAAAAAAAAAAQIAAUwABm5lZ2F0ZXQA\n"
                        + "RUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9D\n"
                        + "aGFyUHJlZGljYXRlO3hwc3IAWW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFj\n"
                        + "dG9yeS5wcmltaXRpdmUuQ2hhclByZWRpY2F0ZXMkTGVzc1RoYW5DaGFyUHJlZGljYXRlAAAAAAAA\n"
                        + "AAECAAFDAAhleHBlY3RlZHhwAAA=",
                CharPredicates.not(CharPredicates.lessThan((char) 0)));
    }
}
