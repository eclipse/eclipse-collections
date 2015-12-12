/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class AddFunctionSerializationTest
{
    @Test
    public void addDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLkFkZEZ1\n"
                        + "bmN0aW9uJEFkZERvdWJsZUZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                AddFunction.DOUBLE);
    }

    @Test
    public void addFloat()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLkFkZEZ1\n"
                        + "bmN0aW9uJEFkZEZsb2F0RnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                AddFunction.FLOAT);
    }

    @Test
    public void addInteger()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLkFkZEZ1\n"
                        + "bmN0aW9uJEFkZEludGVnZXJGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                AddFunction.INTEGER);
    }

    @Test
    public void addLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLkFkZEZ1\n"
                        + "bmN0aW9uJEFkZExvbmdGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                AddFunction.LONG);
    }

    @Test
    public void addString()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLkFkZEZ1\n"
                        + "bmN0aW9uJEFkZFN0cmluZ0Z1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                AddFunction.STRING);
    }

    @Test
    public void addCollection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLkFkZEZ1\n"
                        + "bmN0aW9uJEFkZENvbGxlY3Rpb25GdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                AddFunction.COLLECTION);
    }
}
