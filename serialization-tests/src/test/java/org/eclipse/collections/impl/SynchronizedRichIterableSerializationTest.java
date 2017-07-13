/*
 * Copyright (c) 2017 BNY Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SynchronizedRichIterableSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAGBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLlN5bmNocm9uaXplZFJpY2hJdGVy\n"
                        + "YWJsZSRTeW5jaHJvbml6ZWRSaWNoSXRlcmFibGVTZXJpYWxpemF0aW9uUHJveHkAAAAAAAAAAQwA\n"
                        + "AHhwc3IAMm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwubGlzdC5tdXRhYmxlLkZhc3RMaXN0\n"
                        + "AAAAAAAAAAEMAAB4cHcEAAAAAXNyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFs\n"
                        + "dWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAABeHg=",
                SynchronizedRichIterable.of(Lists.mutable.with(1)));
    }
}
