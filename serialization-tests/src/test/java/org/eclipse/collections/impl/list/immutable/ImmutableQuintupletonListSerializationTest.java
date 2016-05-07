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

public class ImmutableQuintupletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLkltbXV0\n"
                        + "YWJsZVF1aW50dXBsZXRvbkxpc3QAAAAAAAAAAQIABUwACGVsZW1lbnQxdAASTGphdmEvbGFuZy9P\n"
                        + "YmplY3Q7TAAIZWxlbWVudDJxAH4AAUwACGVsZW1lbnQzcQB+AAFMAAhlbGVtZW50NHEAfgABTAAI\n"
                        + "ZWxlbWVudDVxAH4AAXhwc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhy\n"
                        + "ABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAAFzcQB+AAMAAAACc3EAfgADAAAAA3Nx\n"
                        + "AH4AAwAAAARzcQB+AAMAAAAF",
                new ImmutableQuintupletonList<>(1, 2, 3, 4, 5));
    }
}
