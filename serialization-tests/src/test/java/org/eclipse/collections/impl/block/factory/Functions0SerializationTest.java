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

public class Functions0SerializationTest
{
    @Test
    public void throwing()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCRUaHJvd2luZ0Z1bmN0aW9uMEFkYXB0ZXIAAAAAAAAAAQIAAUwAEXRocm93aW5nRnVuY3Rp\n"
                        + "b24wdABHTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvYmxvY2svZnVuY3Rpb24vY2hlY2tl\n"
                        + "ZC9UaHJvd2luZ0Z1bmN0aW9uMDt4cgBEb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9j\n"
                        + "ay5mdW5jdGlvbi5jaGVja2VkLkNoZWNrZWRGdW5jdGlvbjAAAAAAAAAAAQIAAHhwcA==",
                Functions0.throwing(null));
    }

    @Test
    public void getTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCRUcnVlRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions0.getTrue());
    }

    @Test
    public void getFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCRGYWxzZUZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions0.getFalse());
    }

    @Test
    public void newFastList()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCROZXdGYXN0TGlzdEZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions0.newFastList());
    }

    @Test
    public void newUnified()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCROZXdVbmlmaWVkU2V0RnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions0.newUnifiedSet());
    }

    @Test
    public void newHashBag()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCROZXdIYXNoQmFnRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions0.newHashBag());
    }

    @Test
    public void newUnifiedMap()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCROZXdVbmlmaWVkTWFwRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                Functions0.newUnifiedMap());
    }

    @Test
    public void zeroAtomicInteger()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCRBdG9taWNJbnRlZ2VyWmVyb0Z1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions0.zeroAtomicInteger());
    }

    @Test
    public void zeroAtomicLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMCRBdG9taWNMb25nWmVyb0Z1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Functions0.zeroAtomicLong());
    }

    @Test
    public void value()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLlBhc3NU\n"
                        + "aHJ1RnVuY3Rpb24wAAAAAAAAAAECAAFMAAZyZXN1bHR0ABJMamF2YS9sYW5nL09iamVjdDt4cHA=\n",
                Functions0.value(null));
    }
}
