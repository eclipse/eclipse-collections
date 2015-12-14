/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.strategy.mutable;

import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnifiedSetWithHashingStrategySerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5zdHJhdGVneS5tdXRhYmxl\n"
                        + "LlVuaWZpZWRTZXRXaXRoSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAEMAAB4cHNyAExvcmcuZWNsaXBz\n"
                        + "ZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGluZ1N0cmF0ZWdpZXMkRGVmYXVs\n"
                        + "dFN0cmF0ZWd5AAAAAAAAAAECAAB4cHcIAAAAAD9AAAB4",
                UnifiedSetWithHashingStrategy.newSet(HashingStrategies.defaultStrategy()));
    }
}
