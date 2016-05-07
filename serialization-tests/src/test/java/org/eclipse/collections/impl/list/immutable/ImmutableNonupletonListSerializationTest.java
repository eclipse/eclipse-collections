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

public class ImmutableNonupletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZU5vbnVwbGV0b25MaXN0AAAAAAAAAAECAAlMAAhlbGVtZW50MXQAEkxqYXZhL2xhbmcvT2Jq\n"
                        + "ZWN0O0wACGVsZW1lbnQycQB+AAFMAAhlbGVtZW50M3EAfgABTAAIZWxlbWVudDRxAH4AAUwACGVs\n"
                        + "ZW1lbnQ1cQB+AAFMAAhlbGVtZW50NnEAfgABTAAIZWxlbWVudDdxAH4AAUwACGVsZW1lbnQ4cQB+\n"
                        + "AAFMAAhlbGVtZW50OXEAfgABeHBzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZh\n"
                        + "bHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAXNxAH4AAwAAAAJzcQB+AAMA\n"
                        + "AAADc3EAfgADAAAABHNxAH4AAwAAAAVzcQB+AAMAAAAGc3EAfgADAAAAB3NxAH4AAwAAAAhzcQB+\n"
                        + "AAMAAAAJ",
                new ImmutableNonupletonList<>(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }
}
