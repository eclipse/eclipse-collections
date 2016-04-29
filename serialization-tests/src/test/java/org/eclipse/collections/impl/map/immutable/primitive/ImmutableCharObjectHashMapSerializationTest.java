/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import org.eclipse.collections.impl.map.mutable.primitive.CharObjectHashMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableCharObjectHashMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAHxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkFic3RyYWN0SW1tdXRhYmxlQ2hhck9iamVjdE1hcCRJbW11dGFibGVDaGFyT2JqZWN0TWFw\n"
                        + "U2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHcGAAAAAgABdAABMXcCAAJ0AAEyeA==",
                new ImmutableCharObjectHashMap<>(CharObjectHashMap.newWithKeysValues((char) 1, "1", (char) 2, "2")));
    }
}
