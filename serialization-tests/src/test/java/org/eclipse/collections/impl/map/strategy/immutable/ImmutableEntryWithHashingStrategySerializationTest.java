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

public class ImmutableEntryWithHashingStrategySerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zdHJhdGVneS5pbW11dGFi\n"
                        + "bGUuSW1tdXRhYmxlRW50cnlXaXRoSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAECAAFMAA9oYXNoaW5n\n"
                        + "U3RyYXRlZ3l0ADNMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL0hhc2hpbmdTdHJh\n"
                        + "dGVneTt4cgA5b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC50dXBsZS5BYnN0cmFjdEltbXV0\n"
                        + "YWJsZUVudHJ5AAAAAAAAAAECAAJMAANrZXl0ABJMamF2YS9sYW5nL09iamVjdDtMAAV2YWx1ZXEA\n"
                        + "fgADeHBwcHNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFz\n"
                        + "aGluZ1N0cmF0ZWdpZXMkRGVmYXVsdFN0cmF0ZWd5AAAAAAAAAAECAAB4cA==",
                new ImmutableEntryWithHashingStrategy<>(null, null, HashingStrategies.defaultStrategy()));
    }
}
