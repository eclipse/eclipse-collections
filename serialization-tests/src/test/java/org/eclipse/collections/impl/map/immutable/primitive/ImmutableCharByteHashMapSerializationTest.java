/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import org.eclipse.collections.impl.map.mutable.primitive.CharByteHashMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableCharByteHashMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAHRvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUNoYXJCeXRlSGFzaE1hcCRJbW11dGFibGVDaGFyQnl0ZU1hcFNlcmlhbGl6\n"
                        + "YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3CgAAAAIAAQEAAgJ4",
                new ImmutableCharByteHashMap(CharByteHashMap.newWithKeysValues((char) 1, (byte) 1, (char) 2, (byte) 2)));
    }
}
