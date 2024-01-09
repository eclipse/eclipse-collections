/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public interface MapTestCase
{
    <T> Map<Object, T> newWith(T... elements);

    <K, V> Map<K, V> newWithKeysValues(Object... elements);

    default boolean supportsNullKeys()
    {
        return true;
    }

    default boolean supportsNullValues()
    {
        return true;
    }

    default void Iterable_toString()
    {
        Map<String, Integer> map = this.newWithKeysValues("Two", 2, "One", 1);
        Assert.assertEquals("[Two, One]", map.keySet().toString());
        Assert.assertEquals("[2, 1]", map.values().toString());
        Assert.assertEquals("[Two=2, One=1]", map.entrySet().toString());
    }

    @Test
    default void Map_clear()
    {
        Map<Object, String> map = this.newWith("Three", "Two", "One");
        map.clear();
        assertEquals(this.newWith(), map);

        Map<Object, Object> map2 = this.newWith();
        map2.clear();
        assertEquals(this.newWith(), map2);
    }

    @Test
    default void Map_remove()
    {
        Map<Integer, String> map = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        assertEquals("Two", map.remove(2));
        assertEquals(
                this.newWithKeysValues(3, "Three", 1, "One"),
                map);

        if (this.supportsNullKeys())
        {
            assertNull(map.remove(null));
            assertEquals(
                    this.newWithKeysValues(3, "Three", 1, "One"),
                    map);

            Map<Integer, String> map2 = this.newWithKeysValues(3, "Three", null, "Two", 1, "One");
            assertEquals("Two", map2.remove(null));
            assertEquals(
                    this.newWithKeysValues(3, "Three", 1, "One"),
                    map2);
        }
    }

    @Test
    default void Map_entrySet_remove()
    {
        Map<Integer, String> map = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        assertTrue(map.entrySet().remove(ImmutableEntry.of(2, "Two")));
        assertEquals(
                this.newWithKeysValues(3, "Three", 1, "One"),
                map);

        assertFalse(map.entrySet().remove(ImmutableEntry.of(4, "Four")));
        assertEquals(
                this.newWithKeysValues(3, "Three", 1, "One"),
                map);

        if (this.supportsNullKeys())
        {
            assertFalse(map.entrySet().remove(null));
            assertEquals(
                    this.newWithKeysValues(3, "Three", 1, "One"),
                    map);

            Map<Integer, String> map2 = this.newWithKeysValues(3, "Three", null, "Two", 1, "One");
            assertTrue(map2.entrySet().remove(ImmutableEntry.of(null, "Two")));
            assertEquals(
                    this.newWithKeysValues(3, "Three", 1, "One"),
                    map2);
        }
    }

    @Test
    default void Map_putAll()
    {
        Map<Integer, String> map = this.newWithKeysValues(
                3, "Three",
                2, "2");
        Map<Integer, String> toAdd = this.newWithKeysValues(
                2, "Two",
                1, "One");

        map.putAll(toAdd);

        Map<Integer, String> expected = this.newWithKeysValues(
                3, "Three",
                2, "Two",
                1, "One");

        assertEquals(expected, map);

        //Testing JDK map
        Map<Integer, String> map2 = this.newWithKeysValues(
                3, "Three",
                2, "2");
        Map<Integer, String> hashMaptoAdd = new LinkedHashMap<>();
        hashMaptoAdd.put(2, "Two");
        hashMaptoAdd.put(1, "One");
        map2.putAll(hashMaptoAdd);

        assertEquals(expected, map2);
    }

    @Test
    default void Map_merge()
    {
        Map<Integer, String> map = this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        // null value
        Assert.assertThrows(NullPointerException.class, () -> map.merge(1, null, (v1, v2) -> {
            Assert.fail("Expected no merge to be performed since the value is null");
            return null;
        }));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        // null remapping function
        Assert.assertThrows(NullPointerException.class, () -> map.merge(1, "4", null));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        // new key, remapping function isn't called
        String value1 = map.merge(4, "4", (v1, v2) -> {
            Assert.fail("Expected no merge to be performed since the key is not present in the map");
            return null;
        });
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4"), map);
        assertEquals("4", value1);

        // exiting key
        String value2 = map.merge(2, "Two", (v1, v2) -> {
            assertEquals("2", v1);
            assertEquals("Two", v2);
            return v1 + v2;
        });
        Assert.assertEquals("2Two", value2);
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2Two", 3, "3", 4, "4"), map);

        // existing key, null returned from remapping function, removes key
        String value3 = map.merge(3, "Three", (v1, v2) -> null);
        assertNull(value3);
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2Two", 4, "4"), map);

        // new key, null returned from remapping function
        String value4 = map.merge(5, "5", (v1, v2) -> null);
        Assert.assertEquals("5", value4);
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2Two", 4, "4", 5, "5"), map);

        // existing key, remapping function throws exception
        Assert.assertThrows(IllegalArgumentException.class, () -> map.merge(4, "Four", (v1, v2) -> {
            throw new IllegalArgumentException();
        }));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2Two", 4, "4", 5, "5"), map);
    }
}
