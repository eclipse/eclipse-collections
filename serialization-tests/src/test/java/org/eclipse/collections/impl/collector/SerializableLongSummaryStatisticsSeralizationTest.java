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

public class SerializableLongSummaryStatisticsSeralizationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rvci5TZXJpYWxpemFi\n"
                        + "bGVMb25nU3VtbWFyeVN0YXRpc3RpY3MAAAAAAAAAAQwAAHhwdyAAAAAAAAAABQAAAAAAAAABAAAA\n"
                        + "AAAAAAUAAAAAAAAAD3g=",
                SerializableLongSummaryStatistics.with(1L, 2L, 3L, 4L, 5L));

        SerializableLongSummaryStatistics test = SerializeTestHelper.serializeDeserialize(SerializableLongSummaryStatistics.with(1L, 2L, 3L, 4L, 5L));
        Assert.assertTrue(SerializableLongSummaryStatistics.with(1L, 2L, 3L, 4L, 5L).valuesEqual(test));
    }
}
