/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableArrayBagSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5pbW11dGFibGUuSW1tdXRh\n"
                        + "YmxlQmFnU2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHcEAAAAAnQAA09uZXcEAAAAAXQA\n"
                        + "A1R3b3cEAAAAAXg=",
                ImmutableArrayBag.newBagWith("One", "Two"));
    }
}
