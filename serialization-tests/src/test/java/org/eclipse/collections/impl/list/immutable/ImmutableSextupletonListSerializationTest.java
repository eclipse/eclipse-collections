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

public class ImmutableSextupletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZVNleHR1cGxldG9uTGlzdAAAAAAAAAABAgAGTAAIZWxlbWVudDF0ABJMamF2YS9sYW5nL09i\n"
                        + "amVjdDtMAAhlbGVtZW50MnEAfgABTAAIZWxlbWVudDNxAH4AAUwACGVsZW1lbnQ0cQB+AAFMAAhl\n"
                        + "bGVtZW50NXEAfgABTAAIZWxlbWVudDZxAH4AAXhwc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeB\n"
                        + "hzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAAFzcQB+AAMA\n"
                        + "AAACc3EAfgADAAAAA3NxAH4AAwAAAARzcQB+AAMAAAAFc3EAfgADAAAABg==",
                new ImmutableSextupletonList<>(1, 2, 3, 4, 5, 6));
    }
}
