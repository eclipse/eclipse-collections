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

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableObjectCharSingletonMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAHxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkFic3RyYWN0SW1tdXRhYmxlT2JqZWN0Q2hhck1hcCRJbW11dGFibGVPYmplY3RDaGFyTWFw\n"
                        + "U2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHcEAAAAAXQAATF3AgABeA==",
                new ImmutableObjectCharSingletonMap<>("1", (char) 1));
    }
}
