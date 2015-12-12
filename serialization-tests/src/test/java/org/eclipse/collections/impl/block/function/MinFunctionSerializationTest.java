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

public class MinFunctionSerializationTest
{
    @Test
    public void minDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk1pbkZ1\n"
                        + "bmN0aW9uJE1pbkRvdWJsZUZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                MinFunction.DOUBLE);
    }

    @Test
    public void minInteger()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk1pbkZ1\n"
                        + "bmN0aW9uJE1pbkludGVnZXJGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                MinFunction.INTEGER);
    }

    @Test
    public void minLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk1pbkZ1\n"
                        + "bmN0aW9uJE1pbkxvbmdGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                MinFunction.LONG);
    }
}
