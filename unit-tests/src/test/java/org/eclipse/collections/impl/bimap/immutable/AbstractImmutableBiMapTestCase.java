/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.immutable;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.map.immutable.ImmutableMapIterableTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractImmutableBiMapTestCase extends ImmutableMapIterableTestCase
{
    @Override
    protected abstract ImmutableBiMap<Integer, String> classUnderTest();

    protected abstract ImmutableBiMap<Integer, String> newEmpty();

    protected abstract ImmutableBiMap<Integer, String> newWithMap();

    protected abstract ImmutableBiMap<Integer, String> newWithHashBiMap();

    protected abstract ImmutableBiMap<Integer, String> newWithImmutableMap();

    @Override
    protected int size()
    {
        return 4;
    }

    @Override
    @Test
    public void testToString()
    {
        assertEquals("{1=1, 2=2, 3=3, 4=4}", this.classUnderTest().toString());
    }

    @Test
    public void testNewEmpty()
    {
        assertTrue(this.newEmpty().isEmpty());
    }

    @Test
    public void testNewWithMap()
    {
        assertEquals(this.classUnderTest(), this.newWithMap());
    }

    @Test
    public void testNewWithHashBiMap()
    {
        assertEquals(this.classUnderTest(), this.newWithHashBiMap());
    }

    @Test
    public void testNewWithImmutableMap()
    {
        assertEquals(this.classUnderTest(), this.newWithImmutableMap());
    }

    @Test
    public void containsKey()
    {
        assertTrue(this.classUnderTest().containsKey(1));
        assertFalse(this.classUnderTest().containsKey(5));
    }

    @Test
    public void toImmutable()
    {
        assertEquals(this.classUnderTest(), this.classUnderTest().toImmutable());
    }

    @Test
    public void toMapTarget()
    {
        ImmutableBiMap<String, String> immutableBiMap = BiMaps.immutable.with("1", "1", "2", "2", "3", "3", "4", "4");

        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("1", "1");
        expectedMap.put("2", "2");
        expectedMap.put("3", "3");
        expectedMap.put("4", "4");

        Map<String, String> actualMap = (Map<String, String>) immutableBiMap.toMap(Functions.getPassThru(), Functions.getPassThru(), expectedMap);

        assertEquals(expectedMap, actualMap);
    }
}
