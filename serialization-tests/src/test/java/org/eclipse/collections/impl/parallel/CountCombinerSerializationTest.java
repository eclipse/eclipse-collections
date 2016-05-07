/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CountCombinerSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLkNvdW50Q29tYmlu\n"
                        + "ZXIAAAAAAAAAAQIAAUkABWNvdW50eHIAP29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwucGFy\n"
                        + "YWxsZWwuQWJzdHJhY3RQcm9jZWR1cmVDb21iaW5lcgAAAAAAAAABAgABWgANdXNlQ29tYmluZU9u\n"
                        + "ZXhwAQAAAAA=",
                new CountCombiner<>());
    }
}
