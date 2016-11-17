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

public class IntToIntFunctionsSerializationTest
{
    @Test
    public void increment()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFRvSW50RnVuY3Rpb25zJEluY3JlbWVudEludFRvSW50RnVuY3Rpb24AAAAAAAAAAQIA\n"
                        + "AHhw",
                IntToIntFunctions.increment());
    }

    @Test
    public void decrement()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFRvSW50RnVuY3Rpb25zJERlY3JlbWVudEludFRvSW50RnVuY3Rpb24AAAAAAAAAAQIA\n"
                        + "AHhw",
                IntToIntFunctions.decrement());
    }

    @Test
    public void add()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFRvSW50RnVuY3Rpb25zJEFkZEludFRvSW50RnVuY3Rpb24AAAAAAAAAAQIAAUkACGlu\n"
                        + "dFRvQWRkeHAAAAAA",
                IntToIntFunctions.add(0));
    }

    @Test
    public void subtract()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAF9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkucHJpbWl0\n"
                        + "aXZlLkludFRvSW50RnVuY3Rpb25zJFN1YnRyYWN0SW50VG9JbnRGdW5jdGlvbgAAAAAAAAABAgAB\n"
                        + "SQANaW50VG9TdWJ0cmFjdHhwAAAAAA==",
                IntToIntFunctions.subtract(0));
    }
}
