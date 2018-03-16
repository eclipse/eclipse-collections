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

import java.util.DoubleSummaryStatistics;

import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.junit.Assert;
import org.junit.Test;

public class SerializableDoubleSummaryStatisticsTest
{
    @Test
    public void valuesEqual()
    {
        SerializableDoubleSummaryStatistics with = SerializableDoubleSummaryStatistics.with(1.0, 2.0, 3.0);
        DoubleSummaryStatistics without = new DoubleSummaryStatistics();
        without.accept(1.0d);
        without.accept(2.0d);
        without.accept(3.0d);
        Assert.assertTrue(with.valuesEqual(without));
    }

    @Test
    public void serialization()
    {
        SerializableDoubleSummaryStatistics stats = SerializableDoubleSummaryStatistics.with(1.0, 2.0, 3.0);
        SerializableDoubleSummaryStatistics deserialized = SerializeTestHelper.serializeDeserialize(stats);
        Assert.assertTrue(stats.valuesEqual(deserialized));
    }
}
