/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map;

import java.util.Map;

import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.test.map.mutable.MapTestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

public interface UnmodifiableMapTestCase
        extends MapTestCase
{
    @Test
    default void Map_put()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        assertThrows(UnsupportedOperationException.class, () -> map.put(4, "Four"));
        assertThrows(UnsupportedOperationException.class, () -> map.put(1, "One"));
        assertThrows(UnsupportedOperationException.class, () -> map.put(5, null));
        assertThrows(UnsupportedOperationException.class, () -> map.put(null, "Six"));
        assertEquals(this.newWithKeysValues(3, "Three", 2, "Two", 1, "One"), map);
    }

    @Test
    @Override
    default void Map_putAll()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(3, "Three", 2, "2");
        Map<Integer, String> toAdd = (Map<Integer, String>) this.newWithKeysValues(2, "Two", 1, "One");

        assertThrows(UnsupportedOperationException.class, () -> map.putAll(toAdd));

        Map<Integer, String> expected = (Map<Integer, String>) this.newWithKeysValues(3, "Three", 2, "2");
        assertEquals(expected, map);

        assertThrows(UnsupportedOperationException.class, () -> map.putAll(null));
        assertThrows(UnsupportedOperationException.class, () -> map.putAll(Map.of()));
    }

    @Test
    @Override
    default void Map_merge()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        // new key
        assertThrows(UnsupportedOperationException.class, () -> map.merge(4, "4", (v1, v2) -> {
            fail("Expected lambda not to be called on unmodifiable map");
            return null;
        }));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        // existing key
        assertThrows(UnsupportedOperationException.class, () -> map.merge(2, "Two", (v1, v2) -> {
            fail("Expected lambda not to be called on unmodifiable map");
            return null;
        }));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        // null value
        assertThrows(UnsupportedOperationException.class, () -> map.merge(1, null, (v1, v2) -> {
            fail("Expected no merge to be performed since the value is null");
            return null;
        }));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        // null remapping function
        assertThrows(UnsupportedOperationException.class, () -> map.merge(1, "4", null));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_remove()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        assertThrows(UnsupportedOperationException.class, () -> map.remove(1));
        assertThrows(UnsupportedOperationException.class, () -> map.remove(2));
        assertThrows(UnsupportedOperationException.class, () -> map.remove(3));
        assertThrows(UnsupportedOperationException.class, () -> map.remove(4));
        assertThrows(UnsupportedOperationException.class, () -> map.remove(null));
        assertEquals(this.newWithKeysValues(3, "Three", 2, "Two", 1, "One"), map);
    }

    @Test
    @Override
    default void Map_entrySet_remove()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        Map<Integer, String> unchangedCopy = (Map<Integer, String>) this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");

        assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(ImmutableEntry.of(2, "Two")));
        assertEquals(unchangedCopy, map);

        assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(ImmutableEntry.of(4, "Four")));
        assertEquals(unchangedCopy, map);

        assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(null));
        assertEquals(unchangedCopy, map);

        assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(ImmutableEntry.of(null, "Two")));
        assertEquals(unchangedCopy, map);

        assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(ImmutableEntry.of(2, null)));
        assertEquals(unchangedCopy, map);
    }

    @Test
    default void Map_clear()
    {
        Map<Object, String> map = (Map<Object, String>) this.newWith("Three", "Two", "One");
        assertThrows(UnsupportedOperationException.class, map::clear);
        assertEquals(this.newWith("Three", "Two", "One"), map);

        Map<Object, Object> map2 = (Map<Object, Object>) this.newWith();
        assertThrows(UnsupportedOperationException.class, map2::clear);
        Assert.assertEquals(this.newWith(), map2);
    }

    @Test
    default void Map_replaceAll()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.replaceAll((k, v) -> {
            fail("Expected lambda not to be called on unmodifiable map");
            return null;
        }));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_putIfAbsent()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.putIfAbsent(1, "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.putIfAbsent(1, "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.putIfAbsent(1, null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.putIfAbsent(4, "4"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.putIfAbsent(4, null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.putIfAbsent(null, "4"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_removeValue()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.remove(1, "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.remove(1, "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.remove(1, null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.remove(4, "Four"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.remove(4, null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.remove(null, "4"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_replaceValue()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, "1", "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, "1", "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, "One", "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, "One", null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(4, "Four", "4"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, null, "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(null, "1", "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_replace()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(4, "Four"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(null, "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.replace(1, null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_computeIfAbsent()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfAbsent(1, k -> "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfAbsent(1, k -> "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfAbsent(1, k -> null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfAbsent(4, k -> "Four"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfAbsent(4, k -> null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfAbsent(null, k -> "4"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_computeIfPresent()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfPresent(1, (k, v) -> "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfPresent(1, (k, v) -> "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfPresent(1, (k, v) -> null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfPresent(4, (k, v) -> "Four"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfPresent(4, (k, v) -> null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.computeIfPresent(null, (k, v) -> "4"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }

    @Test
    default void Map_compute()
    {
        Map<Integer, String> map = (Map<Integer, String>) this.newWithKeysValues(1, "1", 2, "2", 3, "3");

        assertThrows(UnsupportedOperationException.class, () -> map.compute(1, (k, v) -> "1"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.compute(1, (k, v) -> "One"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.compute(1, (k, v) -> null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.compute(4, (k, v) -> "Four"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.compute(4, (k, v) -> null));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);

        assertThrows(UnsupportedOperationException.class, () -> map.compute(null, (k, v) -> "4"));
        assertEquals(this.newWithKeysValues(1, "1", 2, "2", 3, "3"), map);
    }
}
