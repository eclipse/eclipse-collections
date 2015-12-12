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

import org.eclipse.collections.impl.map.mutable.primitive.FloatFloatHashMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableFloatFloatHashMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAHhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUZsb2F0RmxvYXRIYXNoTWFwJEltbXV0YWJsZUZsb2F0RmxvYXRNYXBTZXJp\n"
                        + "YWxpemF0aW9uUHJveHkAAAAAAAAAAQwAAHhwdxQAAAACP4AAAD+AAABAAAAAQAAAAHg=",
                new ImmutableFloatFloatHashMap(FloatFloatHashMap.newWithKeysValues(1.0f, 1.0f, 2.0f, 2.0f)));
    }
}
