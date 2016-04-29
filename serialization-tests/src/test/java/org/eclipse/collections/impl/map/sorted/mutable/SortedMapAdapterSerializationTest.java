/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.mutable;

import java.util.TreeMap;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SortedMapAdapterSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zb3J0ZWQubXV0YWJsZS5T\n"
                        + "b3J0ZWRNYXBBZGFwdGVyAAAAAAAAAAECAAFMAAhkZWxlZ2F0ZXQAFUxqYXZhL3V0aWwvU29ydGVk\n"
                        + "TWFwO3hwc3IAEWphdmEudXRpbC5UcmVlTWFwDMH2Pi0lauYDAAFMAApjb21wYXJhdG9ydAAWTGph\n"
                        + "dmEvdXRpbC9Db21wYXJhdG9yO3hwcHcEAAAAAHg=",
                new SortedMapAdapter<>(new TreeMap<>()));
    }
}
