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

import org.eclipse.collections.impl.map.mutable.primitive.ObjectIntHashMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableObjectIntHashMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAHpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkFic3RyYWN0SW1tdXRhYmxlT2JqZWN0SW50TWFwJEltbXV0YWJsZU9iamVjdEludE1hcFNl\n"
                        + "cmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAJ0AAEydwQAAAACdAABMXcEAAAAAXg=\n",
                new ImmutableObjectIntHashMap<>(ObjectIntHashMap.newWithKeysValues("1", 1, "2", 2)));
    }
}
