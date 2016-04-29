/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.immutable;

import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableHashBiMapSerializationTest
{
    private static final String SERIALIZED_FORM = "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJpbWFwLmltbXV0YWJsZS5JbW11\n"
            + "dGFibGVCaU1hcFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAFzcgARamF2YS5s\n"
            + "YW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5Tg\n"
            + "iwIAAHhwAAAAAXNyABNqYXZhLmxhbmcuQ2hhcmFjdGVyNItH2WsaJngCAAFDAAV2YWx1ZXhwAGF4\n";

    @Test
    public void serializedForm()
    {
        ImmutableHashBiMap<Integer, Character> biMap = new ImmutableHashBiMap<>(
                Maps.immutable.with(1, 'a'),
                Maps.immutable.with('a', 1));

        Verify.assertSerializedForm(1L, SERIALIZED_FORM, biMap);
    }

    @Test
    public void inverse()
    {
        ImmutableHashBiMap<Character, Integer> biMap = new ImmutableHashBiMap<>(
                Maps.immutable.with('a', 1),
                Maps.immutable.with(1, 'a'));

        Verify.assertSerializedForm(1L, "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJpbWFwLmltbXV0YWJsZS5JbW11\n"
                + "dGFibGVCaU1hcFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAFzcgARamF2YS5s\n"
                + "YW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5Tg\n"
                + "iwIAAHhwAAAAAXNyABNqYXZhLmxhbmcuQ2hhcmFjdGVyNItH2WsaJngCAAFDAAV2YWx1ZXhwAGF4\n", biMap.inverse());
    }
}
