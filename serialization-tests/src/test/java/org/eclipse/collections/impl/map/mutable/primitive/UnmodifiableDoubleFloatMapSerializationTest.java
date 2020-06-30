/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnmodifiableDoubleFloatMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5Vbm1vZGlmaWFibGVEb3VibGVGbG9hdE1hcAAAAAAAAAABAgABTAADbWFwdABBTG9yZy9lY2xp\n"
                        + "cHNlL2NvbGxlY3Rpb25zL2FwaS9tYXAvcHJpbWl0aXZlL011dGFibGVEb3VibGVGbG9hdE1hcDt4\n"
                        + "cHNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2ZS5E\n"
                        + "b3VibGVGbG9hdEhhc2hNYXAAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new UnmodifiableDoubleFloatMap(new DoubleFloatHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5Vbm1vZGlmaWFibGVEb3VibGVTZXQAAAAAAAAAAQIAAHhyAF5vcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmNvbGxlY3Rpb24ubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RVbm1vZGlmaWFi\n"
                        + "bGVEb3VibGVDb2xsZWN0aW9uAAAAAAAAAAECAAFMAApjb2xsZWN0aW9udABKTG9yZy9lY2xpcHNl\n"
                        + "L2NvbGxlY3Rpb25zL2FwaS9jb2xsZWN0aW9uL3ByaW1pdGl2ZS9NdXRhYmxlRG91YmxlQ29sbGVj\n"
                        + "dGlvbjt4cHNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1p\n"
                        + "dGl2ZS5BYnN0cmFjdE11dGFibGVEb3VibGVLZXlTZXQkU2VyUmVwAAAAAAAAAAEMAAB4cHcEAAAA\n"
                        + "AHg=",
                new UnmodifiableDoubleFloatMap(new DoubleFloatHashMap()).keySet());
    }
}
