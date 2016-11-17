/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.immutable.strategy;

import java.util.Random;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.map.strategy.mutable.UnifiedMapWithHashingStrategy;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.test.map.immutable.ImmutableMapTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Java8Runner.class)
public class ImmutableUnifiedMapWithHashingStrategyTest implements ImmutableMapTestCase
{
    private static final long CURRENT_TIME_MILLIS = System.currentTimeMillis();

    @Override
    public final <T> ImmutableMap<Object, T> newWith(T... elements)
    {
        Random random = new Random(CURRENT_TIME_MILLIS);

        MutableMap<Object, T> result = new UnifiedMapWithHashingStrategy<>(HashingStrategies.defaultStrategy());
        for (T each : elements)
        {
            assertNull(result.put(random.nextDouble(), each));
        }
        return result.toImmutable();
    }

    @Test
    public void sanity()
    {
        assertEquals("ImmutableUnifiedMapWithHashingStrategy", this.newWith(1, 2, 3, 4, 5).getClass().getSimpleName());
    }
}
