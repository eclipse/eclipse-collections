/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.List;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class UnmodifiableMapTest
{
    private static final String ROCK_OUT = "Bands that Rock";
    private static final String NAP_TIME = "Bands than Don't";
    private static final MutableList<String> MASTERS_OF_ROCK = Lists.mutable.of("Nine Inch Nails", "Soundgarden", "White Zombie", "Radiohead");

    private MutableMap<String, List<String>> mutableMap;
    private UnmodifiableMap<String, List<String>> unmodifiableMap;

    @Before
    public void setUp()
    {
        this.mutableMap = Maps.mutable.of(
                ROCK_OUT, MASTERS_OF_ROCK,
                NAP_TIME, Lists.mutable.of("Metallica", "Bon Jovi", "Europe", "Scorpions"));
        this.unmodifiableMap = new UnmodifiableMap<>(this.mutableMap);
    }

    @Test
    public void testNullConstructorArgument()
    {
        assertThrows(NullPointerException.class, () -> new UnmodifiableMap<>(null));
    }

    @Test
    public void testSize()
    {
        Verify.assertSize(this.mutableMap.size(), this.unmodifiableMap);
    }

    @Test
    public void testIsEmpty()
    {
        assertEquals(this.mutableMap.isEmpty(), this.unmodifiableMap.isEmpty());
    }

    @Test
    public void testContainsKey()
    {
        assertTrue(this.unmodifiableMap.containsKey(ROCK_OUT));
    }

    @Test
    public void testContainsValue()
    {
        assertTrue(this.unmodifiableMap.containsValue(MASTERS_OF_ROCK));
    }

    @Test
    public void testGet()
    {
        assertEquals(MASTERS_OF_ROCK, this.unmodifiableMap.get(ROCK_OUT));
    }

    @Test
    public void testPut()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableMap.put("foo", Lists.mutable.of()));
    }

    @Test
    public void testRemove()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableMap.remove(ROCK_OUT));
    }

    @Test
    public void testPutAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableMap.putAll(Maps.mutable.of()));
    }

    @Test
    public void testClear()
    {
        assertThrows(UnsupportedOperationException.class, this.unmodifiableMap::clear);
    }

    @Test
    public void testKeySet()
    {
        assertEquals(this.mutableMap.keySet(), this.unmodifiableMap.keySet());
    }

    @Test
    public void testValues()
    {
        Verify.assertContainsAll(this.mutableMap.values(), this.unmodifiableMap.values().toArray());
    }

    @Test
    public void testEntrySet()
    {
        assertEquals(this.mutableMap.entrySet(), this.unmodifiableMap.entrySet());
    }

    @Test
    public void testToString()
    {
        assertEquals(this.mutableMap.toString(), this.unmodifiableMap.toString());
    }

    @Test
    public void testMutableTransparency()
    {
        this.mutableMap.remove(NAP_TIME);
        Verify.assertSize(this.mutableMap.size(), this.unmodifiableMap);
    }

    @Test
    public void equalsAndHashCode()
    {
        Verify.assertEqualsAndHashCode(this.mutableMap, this.unmodifiableMap);
    }

    @Test
    public void notNull()
    {
        assertThrows(NullPointerException.class, () -> new UnmodifiableMap<>(null));
    }

    @Test
    public void entrySetsEqual()
    {
        Verify.assertEqualsAndHashCode(this.mutableMap.entrySet(), this.unmodifiableMap.entrySet());
    }
}
