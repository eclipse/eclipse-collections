/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block;

import org.eclipse.collections.impl.block.factory.Functions2;
import org.eclipse.collections.impl.block.factory.ObjectIntProcedures;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class BlocksSerializationTest
{
    @Test
    public void asObjectIntProcedure()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuT2JqZWN0\n"
                        + "SW50UHJvY2VkdXJlcyRQcm9jZWR1cmVBZGFwdGVyAAAAAAAAAAECAAFMAAlwcm9jZWR1cmV0ADdM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3Byb2NlZHVyZS9Qcm9jZWR1cmU7eHBw\n",
                ObjectIntProcedures.fromProcedure(null));
    }

    @Test
    public void asProcedure2()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJvY2Vk\n"
                        + "dXJlczIkUHJvY2VkdXJlQWRhcHRlcgAAAAAAAAABAgABTAAJcHJvY2VkdXJldAA3TG9yZy9lY2xp\n"
                        + "cHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9wcm9jZWR1cmUvUHJvY2VkdXJlO3hwcA==",
                Procedures2.fromProcedure(null));
    }

    @Test
    public void asFunction2()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rp\n"
                        + "b25zMiRGdW5jdGlvbkFkYXB0ZXIAAAAAAAAAAQIAAUwACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNl\n"
                        + "L2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjt4cHA=",
                Functions2.fromFunction(null));
    }
}
