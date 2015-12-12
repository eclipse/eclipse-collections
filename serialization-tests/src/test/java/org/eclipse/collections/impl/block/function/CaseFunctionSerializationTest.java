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

public class CaseFunctionSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZ1bmN0aW9uLkNhc2VG\n"
                        + "dW5jdGlvbgAAAAAAAAABAgACTAAPZGVmYXVsdEZ1bmN0aW9udAA1TG9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjtMABJwcmVkaWNhdGVGdW5jdGlvbnN0\n"
                        + "AC5Mb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2xpc3QvTXV0YWJsZUxpc3Q7eHBwc3IAMm9y\n"
                        + "Zy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0AAAAAAAAAAEM\n"
                        + "AAB4cHcEAAAAAHg=",
                new CaseFunction<String, Object>());
    }
}
