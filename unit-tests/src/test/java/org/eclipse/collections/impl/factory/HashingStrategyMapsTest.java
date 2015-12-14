/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.factory.map.strategy.ImmutableHashingStrategyMapFactory;
import org.eclipse.collections.api.factory.map.strategy.MutableHashingStrategyMapFactory;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class HashingStrategyMapsTest
{
    @Test
    public void immutable()
    {
        ImmutableHashingStrategyMapFactory factory = HashingStrategyMaps.immutable;
        Assert.assertEquals(UnifiedMap.newMap(), factory.of(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy()));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6, 7, 8), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void mutable()
    {
        MutableHashingStrategyMapFactory factory = HashingStrategyMaps.mutable;
        Assert.assertEquals(UnifiedMap.newMap(), factory.of(HashingStrategies.defaultStrategy()));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy()));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6, 7, 8), factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableMap.class, factory.of(HashingStrategies.defaultStrategy(), 1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(HashingStrategyMaps.class);
    }
}
