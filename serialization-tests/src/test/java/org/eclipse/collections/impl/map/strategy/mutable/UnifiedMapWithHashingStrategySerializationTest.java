/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.strategy.mutable;

import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class UnifiedMapWithHashingStrategySerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zdHJhdGVneS5tdXRhYmxl\n"
                        + "LlVuaWZpZWRNYXBXaXRoSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAEMAAB4cHNyAExvcmcuZWNsaXBz\n"
                        + "ZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGluZ1N0cmF0ZWdpZXMkRGVmYXVs\n"
                        + "dFN0cmF0ZWd5AAAAAAAAAAECAAB4cHcIAAAAAD9AAAB4",
                UnifiedMapWithHashingStrategy.newMap(HashingStrategies.defaultStrategy()));
    }

    @Test
    public void keySet()
    {
        // SerialVersionUID not important for objects with writeReplace()
        Verify.assertSerializedForm(
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5zdHJhdGVneS5tdXRhYmxl\n"
                        + "LlVuaWZpZWRTZXRXaXRoSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAEMAAB4cHNyAExvcmcuZWNsaXBz\n"
                        + "ZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGluZ1N0cmF0ZWdpZXMkRGVmYXVs\n"
                        + "dFN0cmF0ZWd5AAAAAAAAAAECAAB4cHcIAAAAAD9AAAB4",
                UnifiedMapWithHashingStrategy.newMap(HashingStrategies.defaultStrategy()).keySet());
    }

    @Test
    public void entrySet()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5zdHJhdGVneS5tdXRhYmxl\n"
                        + "LlVuaWZpZWRNYXBXaXRoSGFzaGluZ1N0cmF0ZWd5JEVudHJ5U2V0AAAAAAAAAAECAAFMAAZ0aGlz\n"
                        + "JDB0AFFMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvaW1wbC9tYXAvc3RyYXRlZ3kvbXV0YWJsZS9V\n"
                        + "bmlmaWVkTWFwV2l0aEhhc2hpbmdTdHJhdGVneTt4cHNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLm1hcC5zdHJhdGVneS5tdXRhYmxlLlVuaWZpZWRNYXBXaXRoSGFzaGluZ1N0cmF0ZWd5\n"
                        + "AAAAAAAAAAEMAAB4cHNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rv\n"
                        + "cnkuSGFzaGluZ1N0cmF0ZWdpZXMkRGVmYXVsdFN0cmF0ZWd5AAAAAAAAAAECAAB4cHcIAAAAAD9A\n"
                        + "AAB4",
                UnifiedMapWithHashingStrategy.newMap(HashingStrategies.defaultStrategy()).entrySet());
    }

    @Test
    public void values()
    {
        Verify.assertSerializedForm(
                "rO0ABXNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlz\n"
                        + "dAAAAAAAAAABDAAAeHB3BAAAAAB4",
                UnifiedMapWithHashingStrategy.newMap(HashingStrategies.defaultStrategy()).values());
    }
}
