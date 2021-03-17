/*
 * Copyright (c) 2021 Goldman Sachs.
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

public class UnmodifiableShortByteMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5Vbm1vZGlmaWFibGVTaG9ydEJ5dGVNYXAAAAAAAAAAAQIAAUwAA21hcHQAP0xvcmcvZWNsaXBz\n"
                        + "ZS9jb2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1pdGl2ZS9NdXRhYmxlU2hvcnRCeXRlTWFwO3hwc3IA\n"
                        + "Q29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLlNob3J0\n"
                        + "Qnl0ZUhhc2hNYXAAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                new UnmodifiableShortByteMap(new ShortByteHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5Vbm1vZGlmaWFibGVTaG9ydFNldAAAAAAAAAABAgAAeHIAXW9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuY29sbGVjdGlvbi5tdXRhYmxlLnByaW1pdGl2ZS5BYnN0cmFjdFVubW9kaWZpYWJs\n"
                        + "ZVNob3J0Q29sbGVjdGlvbgAAAAAAAAABAgABTAAKY29sbGVjdGlvbnQASUxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvY29sbGVjdGlvbi9wcmltaXRpdmUvTXV0YWJsZVNob3J0Q29sbGVjdGlv\n"
                        + "bjt4cHNyAFRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5BYnN0cmFjdE11dGFibGVTaG9ydEtleVNldCRTZXJSZXAAAAAAAAAAAQwAAHhwdwQAAAAAeA==\n",
                new UnmodifiableShortByteMap(new ShortByteHashMap()).keySet());
    }
}
