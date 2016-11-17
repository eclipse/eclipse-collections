/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class AbstractImmutableEntrySerializationTest
{
    @Test
    public void getPairFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnR1cGxlLkFic3RyYWN0SW1tdXRh\n"
                        + "YmxlRW50cnkkUGFpckZ1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                AbstractImmutableEntry.getPairFunction());
    }
}
