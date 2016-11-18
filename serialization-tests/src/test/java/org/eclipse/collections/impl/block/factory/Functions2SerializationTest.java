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

public class Functions2SerializationTest
{
    @Test
    public void throwing()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRUaHJvd2luZ0Z1bmN0aW9uMkFkYXB0ZXIAAAAAAAAAAQIAAUwAEXRocm93aW5nRnVuY3Rp\n"
                        + "b24ydABHTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvYmxvY2svZnVuY3Rpb24vY2hlY2tl\n"
                        + "ZC9UaHJvd2luZ0Z1bmN0aW9uMjt4cgBEb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9j\n"
                        + "ay5mdW5jdGlvbi5jaGVja2VkLkNoZWNrZWRGdW5jdGlvbjIAAAAAAAAAAQIAAHhwcA==",
                Functions2.throwing(null));
    }

    @Test
    public void integerAddition()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRJbnRlZ2VyQWRkaXRpb24AAAAAAAAAAQIAAHhw",
                Functions2.integerAddition());
    }

    @Test
    public void value()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRGdW5jdGlvbkFkYXB0ZXIAAAAAAAAAAQIAAUwACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNl\n"
                        + "L2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjt4cHA=",
                Functions2.fromFunction(null));
    }

    @Test
    public void min()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRNaW5GdW5jdGlvbjIAAAAAAAAAAQIAAUwACmNvbXBhcmF0b3J0ABZMamF2YS91dGlsL0Nv\n"
                        + "bXBhcmF0b3I7eHBw",
                Functions2.min(null));
    }

    @Test
    public void max()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRNYXhGdW5jdGlvbjIAAAAAAAAAAQIAAUwACmNvbXBhcmF0b3J0ABZMamF2YS91dGlsL0Nv\n"
                        + "bXBhcmF0b3I7eHBw",
                Functions2.max(null));
    }

    @Test
    public void minBy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRNaW5CeUZ1bmN0aW9uMgAAAAAAAAABAgABTAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2Uv\n"
                        + "Y29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO3hwcA==",
                Functions2.minBy(null));
    }

    @Test
    public void maxBy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRNYXhCeUZ1bmN0aW9uMgAAAAAAAAABAgABTAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2Uv\n"
                        + "Y29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO3hwcA==",
                Functions2.maxBy(null));
    }
}
