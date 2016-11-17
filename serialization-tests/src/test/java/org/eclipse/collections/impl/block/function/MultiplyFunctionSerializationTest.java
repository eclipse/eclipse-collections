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

public class MultiplyFunctionSerializationTest
{
    @Test
    public void multiplyDouble()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk11bHRp\n"
                        + "cGx5RnVuY3Rpb24kTXVsdGlwbHlEb3VibGVGdW5jdGlvbgAAAAAAAAABAgAAeHA=",
                MultiplyFunction.DOUBLE);
    }

    @Test
    public void multiplyInteger()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk11bHRp\n"
                        + "cGx5RnVuY3Rpb24kTXVsdGlwbHlJbnRlZ2VyRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                MultiplyFunction.INTEGER);
    }

    @Test
    public void multiplyLong()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLk11bHRp\n"
                        + "cGx5RnVuY3Rpb24kTXVsdGlwbHlMb25nRnVuY3Rpb24AAAAAAAAAAQIAAHhw",
                MultiplyFunction.LONG);
    }
}
