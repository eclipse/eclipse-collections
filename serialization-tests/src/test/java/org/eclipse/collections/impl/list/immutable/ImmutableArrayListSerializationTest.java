/*
 * Copyright (c) 2015 Goldman Sachs.
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

public class ImmutableArrayListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZUFycmF5TGlzdAAAAAAAAAABAgABWwAFaXRlbXN0ABNbTGphdmEvbGFuZy9PYmplY3Q7eHB1\n"
                        + "cgAUW0xqYXZhLmxhbmcuSW50ZWdlcjv+l62gAYPiGwIAAHhwAAAAC3NyABFqYXZhLmxhbmcuSW50\n"
                        + "ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAA\n"
                        + "AAABc3EAfgAFAAAAAnNxAH4ABQAAAANzcQB+AAUAAAAEc3EAfgAFAAAABXNxAH4ABQAAAAZzcQB+\n"
                        + "AAUAAAAHc3EAfgAFAAAACHNxAH4ABQAAAAlzcQB+AAUAAAAKc3EAfgAFAAAACw==",
                ImmutableArrayList.newListWith(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
    }
}
