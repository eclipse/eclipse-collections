/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableTripletonMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUuSW1tdXRh\n"
                        + "YmxlTWFwU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHcEAAAAA3QAAWFwdAABYnB0AAFj\n"
                        + "cHg=",
                new ImmutableTripletonMap<>("a", null, "b", null, "c", null));
    }
}
