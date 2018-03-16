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

import java.util.LongSummaryStatistics;

import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.junit.Assert;
import org.junit.Test;

public class SerializableLongSummaryStatisticsTest
{
    @Test
    public void valuesEqual()
    {
        SerializableLongSummaryStatistics with = SerializableLongSummaryStatistics.with(1, 2, 3);
        LongSummaryStatistics without = new LongSummaryStatistics();
        without.accept(1L);
        without.accept(2L);
        without.accept(3L);
        Assert.assertTrue(with.valuesEqual(without));
    }

    @Test
    public void serialization()
    {
        SerializableLongSummaryStatistics stats = SerializableLongSummaryStatistics.with(1, 2, 3);
        SerializableLongSummaryStatistics deserialized = SerializeTestHelper.serializeDeserialize(stats);
        Assert.assertTrue(stats.valuesEqual(deserialized));
    }
}
