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

import java.util.List;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.junit.Assert;
import org.junit.Test;

public class SummaryStatisticsTest
{
    @Test
    public void jdkForEach()
    {
        List<ValueHolder> valueHolders =
                Lists.mutable.withNValues(100, () -> new ValueHolder(1, 1L, 1.0d));
        SummaryStatistics<ValueHolder> summaryStatistics = new SummaryStatistics<>();
        summaryStatistics.addIntFunction("0", ValueHolder::getIntValue);
        summaryStatistics.addIntFunction("1", ValueHolder::getIntValue);
        summaryStatistics.addLongFunction("0", ValueHolder::getLongValue);
        summaryStatistics.addLongFunction("1", ValueHolder::getLongValue);
        summaryStatistics.addDoubleFunction("0", ValueHolder::getDoubleValue);
        summaryStatistics.addDoubleFunction("1", ValueHolder::getDoubleValue);
        valueHolders.forEach(summaryStatistics);
        Assert.assertEquals(100, summaryStatistics.getIntStats("0").getSum());
        Assert.assertEquals(100, summaryStatistics.getIntStats("1").getSum());
        Assert.assertEquals(100L, summaryStatistics.getLongStats("0").getSum());
        Assert.assertEquals(100L, summaryStatistics.getLongStats("1").getSum());
        Assert.assertEquals(100.0d, summaryStatistics.getDoubleStats("0").getSum(), 0.0);
        Assert.assertEquals(100.0d, summaryStatistics.getDoubleStats("1").getSum(), 0.0);
    }

    @Test
    public void each()
    {
        MutableList<ValueHolder> valueHolders =
                Lists.mutable.withNValues(100, () -> new ValueHolder(1, 1L, 1.0d));
        SummaryStatistics<ValueHolder> summaryStatistics = new SummaryStatistics<>();
        summaryStatistics.addIntFunction("0", ValueHolder::getIntValue);
        summaryStatistics.addIntFunction("1", ValueHolder::getIntValue);
        summaryStatistics.addLongFunction("0", ValueHolder::getLongValue);
        summaryStatistics.addLongFunction("1", ValueHolder::getLongValue);
        summaryStatistics.addDoubleFunction("0", ValueHolder::getDoubleValue);
        summaryStatistics.addDoubleFunction("1", ValueHolder::getDoubleValue);
        valueHolders.each(summaryStatistics);
        Assert.assertEquals(100, summaryStatistics.getIntStats("0").getSum());
        Assert.assertEquals(100, summaryStatistics.getIntStats("1").getSum());
        Assert.assertEquals(100L, summaryStatistics.getLongStats("0").getSum());
        Assert.assertEquals(100L, summaryStatistics.getLongStats("1").getSum());
        Assert.assertEquals(100.0d, summaryStatistics.getDoubleStats("0").getSum(), 0.0);
        Assert.assertEquals(100.0d, summaryStatistics.getDoubleStats("1").getSum(), 0.0);
    }

    @Test
    public void merge()
    {
        MutableList<ValueHolder> valueHolders =
                Lists.mutable.withNValues(100, () -> new ValueHolder(1, 1L, 1.0d));
        SummaryStatistics<ValueHolder> summaryStatistics1 = new SummaryStatistics<>();
        SummaryStatistics<ValueHolder> summaryStatistics2 = new SummaryStatistics<>();
        summaryStatistics1.addIntFunction("0", ValueHolder::getIntValue);
        summaryStatistics2.addIntFunction("0", ValueHolder::getIntValue);
        summaryStatistics1.addLongFunction("0", ValueHolder::getLongValue);
        summaryStatistics2.addLongFunction("0", ValueHolder::getLongValue);
        summaryStatistics1.addDoubleFunction("0", ValueHolder::getDoubleValue);
        summaryStatistics2.addDoubleFunction("0", ValueHolder::getDoubleValue);
        valueHolders.each(summaryStatistics1);
        valueHolders.each(summaryStatistics2);
        summaryStatistics1.merge(summaryStatistics2);
        Assert.assertEquals(200, summaryStatistics1.getIntStats("0").getSum());
        Assert.assertEquals(200L, summaryStatistics1.getLongStats("0").getSum());
        Assert.assertEquals(200.0d, summaryStatistics1.getDoubleStats("0").getSum(), 0.0);
        Assert.assertEquals(100, summaryStatistics2.getIntStats("0").getSum());
        Assert.assertEquals(100L, summaryStatistics2.getLongStats("0").getSum());
        Assert.assertEquals(100.0d, summaryStatistics2.getDoubleStats("0").getSum(), 0.0);
    }

    @Test
    public void serialization()
    {
        SummaryStatistics<Object> stats = new SummaryStatistics<>().addIntFunction("1", each -> 1);
        SummaryStatistics<Object> deserialized = SerializeTestHelper.serializeDeserialize(stats);
        Assert.assertNotSame(stats, deserialized);
        Assert.assertNotNull(deserialized.getIntStats("1"));
    }
}
