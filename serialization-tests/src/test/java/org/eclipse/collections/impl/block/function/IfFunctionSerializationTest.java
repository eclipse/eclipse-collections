/*
 * Copyright (c) 2016 Goldman Sachs.
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

public class IfFunctionSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLklmRnVu\n"
                        + "Y3Rpb24AAAAAAAAAAQIAA0wADGVsc2VGdW5jdGlvbnQANUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9u\n"
                        + "cy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247TAAIZnVuY3Rpb25xAH4AAUwACXByZWRpY2F0\n"
                        + "ZXQAN0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL1ByZWRpY2F0\n"
                        + "ZTt4cHBwcA==",
                new IfFunction<>(null, null));
    }
}
