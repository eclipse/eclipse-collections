/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableTripletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZVRyaXBsZXRvbkxpc3QAAAAAAAAAAQIAA0wACGVsZW1lbnQxdAASTGphdmEvbGFuZy9PYmpl\n"
                        + "Y3Q7TAAIZWxlbWVudDJxAH4AAUwACGVsZW1lbnQzcQB+AAF4cHNyABFqYXZhLmxhbmcuSW50ZWdl\n"
                        + "chLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAAB\n"
                        + "c3EAfgADAAAAAnNxAH4AAwAAAAM=",
                new ImmutableTripletonList<>(1, 2, 3));
    }
}
