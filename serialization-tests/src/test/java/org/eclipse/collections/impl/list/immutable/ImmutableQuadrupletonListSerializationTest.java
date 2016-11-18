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

public class ImmutableQuadrupletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZVF1YWRydXBsZXRvbkxpc3QAAAAAAAAAAQIABEwACGVsZW1lbnQxdAASTGphdmEvbGFuZy9P\n"
                        + "YmplY3Q7TAAIZWxlbWVudDJxAH4AAUwACGVsZW1lbnQzcQB+AAFMAAhlbGVtZW50NHEAfgABeHBz\n"
                        + "cgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1i\n"
                        + "ZXKGrJUdC5TgiwIAAHhwAAAAAXNxAH4AAwAAAAJzcQB+AAMAAAADc3EAfgADAAAABA==",
                new ImmutableQuadrupletonList<>(1, 2, 3, 4));
    }
}
