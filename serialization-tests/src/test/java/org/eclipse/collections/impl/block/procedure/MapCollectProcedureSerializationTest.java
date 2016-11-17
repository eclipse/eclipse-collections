/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class MapCollectProcedureSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5NYXBD\n"
                        + "b2xsZWN0UHJvY2VkdXJlAAAAAAAAAAECAANMAAtrZXlGdW5jdGlvbnQANUxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAADbWFwdAAPTGphdmEvdXRp\n"
                        + "bC9NYXA7TAANdmFsdWVGdW5jdGlvbnEAfgABeHBwcHA=",
                new MapCollectProcedure<>(null, null, null));
    }
}
