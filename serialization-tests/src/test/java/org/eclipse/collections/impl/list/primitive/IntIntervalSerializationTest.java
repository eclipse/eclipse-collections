/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class IntIntervalSerializationTest
{
    public static final String EXPECTED_BASE_64_FORM =
            "rO0ABXNyADdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QucHJpbWl0aXZlLkludElu\n"
                    + "dGVydmFsAAAAAAAAAAECAANJAARmcm9tSQAEc3RlcEkAAnRveHAAAAAAAAAAAQAAAAo=";

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(1L, EXPECTED_BASE_64_FORM, IntInterval.fromToBy(0, 10, 1));
    }

    // Testing deserialization because readObject() is overridden in IntInterval
    @Test
    public void deserialization()
    {
        Verify.assertDeserializedForm(EXPECTED_BASE_64_FORM, IntInterval.fromToBy(0, 10, 1));
    }
}
