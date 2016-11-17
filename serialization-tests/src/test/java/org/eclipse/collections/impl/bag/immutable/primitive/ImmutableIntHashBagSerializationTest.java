/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableIntHashBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUludEhhc2hCYWckSW1tdXRhYmxlSW50QmFnU2VyaWFsaXphdGlvblByb3h5\n"
                        + "AAAAAAAAAAEMAAB4cHcUAAAAAgAAAAEAAAABAAAAAgAAAAF4",
                ImmutableIntHashBag.newBagWith(1, 2));
    }
}
