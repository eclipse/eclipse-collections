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

public class ImmutableDecapletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZURlY2FwbGV0b25MaXN0AAAAAAAAAAECAApMAAhlbGVtZW50MXQAEkxqYXZhL2xhbmcvT2Jq\n"
                        + "ZWN0O0wACWVsZW1lbnQxMHEAfgABTAAIZWxlbWVudDJxAH4AAUwACGVsZW1lbnQzcQB+AAFMAAhl\n"
                        + "bGVtZW50NHEAfgABTAAIZWxlbWVudDVxAH4AAUwACGVsZW1lbnQ2cQB+AAFMAAhlbGVtZW50N3EA\n"
                        + "fgABTAAIZWxlbWVudDhxAH4AAUwACGVsZW1lbnQ5cQB+AAF4cHNyABFqYXZhLmxhbmcuSW50ZWdl\n"
                        + "chLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAAB\n"
                        + "c3EAfgADAAAACnNxAH4AAwAAAAJzcQB+AAMAAAADc3EAfgADAAAABHNxAH4AAwAAAAVzcQB+AAMA\n"
                        + "AAAGc3EAfgADAAAAB3NxAH4AAwAAAAhzcQB+AAMAAAAJ",
                new ImmutableDecapletonList<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
