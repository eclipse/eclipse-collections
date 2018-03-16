/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class SerializableIntSummaryStatisticsSeralizationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rvci5TZXJpYWxpemFi\n"
                        + "bGVJbnRTdW1tYXJ5U3RhdGlzdGljcwAAAAAAAAABDAAAeHB3GAAAAAAAAAAFAAAAAQAAAAUAAAAA\n"
                        + "AAAAD3g=",
                SerializableIntSummaryStatistics.with(1, 2, 3, 4, 5));

        SerializableIntSummaryStatistics test = SerializeTestHelper.serializeDeserialize(SerializableIntSummaryStatistics.with(1, 2, 3, 4, 5));
        Assert.assertTrue(SerializableIntSummaryStatistics.with(1, 2, 3, 4, 5).valuesEqual(test));
    }
}
