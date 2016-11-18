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

public class ImmutableSeptupletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZVNlcHR1cGxldG9uTGlzdAAAAAAAAAABAgAHTAAIZWxlbWVudDF0ABJMamF2YS9sYW5nL09i\n"
                        + "amVjdDtMAAhlbGVtZW50MnEAfgABTAAIZWxlbWVudDNxAH4AAUwACGVsZW1lbnQ0cQB+AAFMAAhl\n"
                        + "bGVtZW50NXEAfgABTAAIZWxlbWVudDZxAH4AAUwACGVsZW1lbnQ3cQB+AAF4cHNyABFqYXZhLmxh\n"
                        + "bmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCL\n"
                        + "AgAAeHAAAAABc3EAfgADAAAAAnNxAH4AAwAAAANzcQB+AAMAAAAEc3EAfgADAAAABXNxAH4AAwAA\n"
                        + "AAZzcQB+AAMAAAAH",
                new ImmutableSeptupletonList<>(1, 2, 3, 4, 5, 6, 7));
    }
}
