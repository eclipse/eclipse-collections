/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.sorted.mutable;

import java.util.TreeSet;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SortedSetAdapterSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5zb3J0ZWQubXV0YWJsZS5T\n"
                        + "b3J0ZWRTZXRBZGFwdGVyAAAAAAAAAAECAAFMAAhkZWxlZ2F0ZXQAFUxqYXZhL3V0aWwvU29ydGVk\n"
                        + "U2V0O3hwc3IAEWphdmEudXRpbC5UcmVlU2V03ZhQk5Xth1sDAAB4cHB3BAAAAAB4",
                SortedSetAdapter.adapt(new TreeSet<Integer>()));
    }
}
