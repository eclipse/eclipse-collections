/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.strategy.immutable;

import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class ImmutableEmptyMapWithHashingStrategySerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAGVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zdHJhdGVneS5pbW11dGFi\n"
                        + "bGUuSW1tdXRhYmxlTWFwV2l0aEhhc2hpbmdTdHJhdGVneVNlcmlhbGl6YXRpb25Qcm94eQAAAAAA\n"
                        + "AAABDAAAeHBzcgBMb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5Lkhh\n"
                        + "c2hpbmdTdHJhdGVnaWVzJERlZmF1bHRTdHJhdGVneQAAAAAAAAABAgAAeHB3BAAAAAB4",
                new ImmutableEmptyMapWithHashingStrategy<>(HashingStrategies.defaultStrategy()));
    }
}
