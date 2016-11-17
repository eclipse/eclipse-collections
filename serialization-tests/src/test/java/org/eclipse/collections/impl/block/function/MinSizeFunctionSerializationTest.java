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

public class MinSizeFunctionSerializationTest
{
    @Test
    public void minSizeCollection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk1pblNp\n"
                        + "emVGdW5jdGlvbiRNaW5TaXplQ29sbGVjdGlvbkZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                MinSizeFunction.COLLECTION);
    }

    @Test
    public void minSizeMap()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk1pblNp\n"
                        + "emVGdW5jdGlvbiRNaW5TaXplTWFwRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                MinSizeFunction.MAP);
    }

    @Test
    public void minSizeString()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk1pblNp\n"
                        + "emVGdW5jdGlvbiRNaW5TaXplU3RyaW5nRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                MinSizeFunction.STRING);
    }
}
