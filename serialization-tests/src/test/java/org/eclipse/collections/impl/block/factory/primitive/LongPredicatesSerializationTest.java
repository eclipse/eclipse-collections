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

public class LongPredicatesSerializationTest
{
    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJEFsd2F5c0ZhbHNlTG9uZ1ByZWRpY2F0ZQAAAAAAAAABAgAAeHA=\n",
                LongPredicates.alwaysFalse());
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJEFsd2F5c1RydWVMb25nUHJlZGljYXRlAAAAAAAAAAECAAB4cA==\n",
                LongPredicates.alwaysTrue());
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJEVxdWFsc0xvbmdQcmVkaWNhdGUAAAAAAAAAAQIAAUoACGV4cGVj\n"
                        + "dGVkeHAAAAAAAAAAAA==",
                LongPredicates.equal(0L));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJExlc3NUaGFuTG9uZ1ByZWRpY2F0ZQAAAAAAAAABAgABSgAIZXhw\n"
                        + "ZWN0ZWR4cAAAAAAAAAAA",
                LongPredicates.lessThan(0L));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJEdyZWF0ZXJUaGFuTG9uZ1ByZWRpY2F0ZQAAAAAAAAABAgABSgAI\n"
                        + "ZXhwZWN0ZWR4cAAAAAAAAAAA",
                LongPredicates.greaterThan(0L));
    }

    @Test
    public void isEven()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJExvbmdJc0V2ZW5QcmVkaWNhdGUAAAAAAAAAAQIAAHhw",
                LongPredicates.isEven());
    }

    @Test
    public void isOdd()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJExvbmdJc09kZFByZWRpY2F0ZQAAAAAAAAABAgAAeHA=",
                LongPredicates.isOdd());
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJEFuZExvbmdQcmVkaWNhdGUAAAAAAAAAAQIAAkwAA29uZXQARUxv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9Mb25n\n"
                        + "UHJlZGljYXRlO0wAA3R3b3EAfgABeHBzcgBXb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LnByaW1pdGl2ZS5Mb25nUHJlZGljYXRlcyRMb25nSXNFdmVuUHJlZGljYXRl\n"
                        + "AAAAAAAAAAECAAB4cHNyAFZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rv\n"
                        + "cnkucHJpbWl0aXZlLkxvbmdQcmVkaWNhdGVzJExvbmdJc09kZFByZWRpY2F0ZQAAAAAAAAABAgAA\n"
                        + "eHA=",
                LongPredicates.and(LongPredicates.isEven(), LongPredicates.isOdd()));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJE9yTG9uZ1ByZWRpY2F0ZQAAAAAAAAABAgACTAADb25ldABFTG9y\n"
                        + "Zy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvcHJpbWl0aXZlL0xvbmdQ\n"
                        + "cmVkaWNhdGU7TAADdHdvcQB+AAF4cHNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJs\n"
                        + "b2NrLmZhY3RvcnkucHJpbWl0aXZlLkxvbmdQcmVkaWNhdGVzJExvbmdJc0V2ZW5QcmVkaWNhdGUA\n"
                        + "AAAAAAAAAQIAAHhwc3IAVm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9y\n"
                        + "eS5wcmltaXRpdmUuTG9uZ1ByZWRpY2F0ZXMkTG9uZ0lzT2RkUHJlZGljYXRlAAAAAAAAAAECAAB4\n"
                        + "cA==",
                LongPredicates.or(LongPredicates.isEven(), LongPredicates.isOdd()));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkxvbmdQcmVkaWNhdGVzJE5vdExvbmdQcmVkaWNhdGUAAAAAAAAAAQIAAUwABm5lZ2F0ZXQA\n"
                        + "RUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL3ByaW1pdGl2ZS9M\n"
                        + "b25nUHJlZGljYXRlO3hwc3IAV29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFj\n"
                        + "dG9yeS5wcmltaXRpdmUuTG9uZ1ByZWRpY2F0ZXMkTG9uZ0lzRXZlblByZWRpY2F0ZQAAAAAAAAAB\n"
                        + "AgAAeHA=",
                LongPredicates.not(LongPredicates.isEven()));
    }
}
