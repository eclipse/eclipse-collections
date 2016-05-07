/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Test;

public class ImmutableUnifiedMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUuSW1tdXRh\n"
                        + "YmxlTWFwU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHcEAAAABHNyABFqYXZhLmxhbmcu\n"
                        + "SW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAA\n"
                        + "eHAAAAABcQB+AARzcQB+AAIAAAACcQB+AAVzcQB+AAIAAAADcQB+AAZzcQB+AAIAAAAEcQB+AAd4\n",
                new ImmutableUnifiedMap<>(
                        Tuples.pair(1, 1),
                        Tuples.pair(2, 2),
                        Tuples.pair(3, 3),
                        Tuples.pair(4, 4)));
    }
}
