/*
 * Copyright (c) 2018 Two Sigma.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable.ordered;

import java.util.LinkedHashMap;

import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.map.MutableOrderedMap;
import org.eclipse.collections.impl.map.ordered.mutable.OrderedMapAdapter;
import org.eclipse.collections.impl.map.ordered.mutable.UnmodifiableMutableOrderedMap;
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.test.UnmodifiableIterableTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(Java8Runner.class)
public class UnmodifiableMutableOrderedMapTest implements MutableOrderedMapTestCase, UnmodifiableIterableTestCase
{
    @Override
    public <T> MutableOrderedMap<Object, T> newWith(T... elements)
    {
        int i = elements.length;
        MutableOrderedMap<Object, T> result = OrderedMapAdapter.adapt(new LinkedHashMap<>());
        for (T each : elements)
        {
            assertNull(result.put(i, each));
            i--;
        }

        return UnmodifiableMutableOrderedMap.of(result);
    }

    @Override
    public <K, V> MutableOrderedMap<K, V> newWithKeysValues(Object... elements)
    {
        if (elements.length % 2 != 0)
        {
            fail(String.valueOf(elements.length));
        }

        MutableOrderedMap<K, V> result = OrderedMapAdapter.adapt(new LinkedHashMap<>());
        for (int i = 0; i < elements.length; i += 2)
        {
            assertNull(result.put((K) elements[i], (V) elements[i + 1]));
        }
        return UnmodifiableMutableOrderedMap.of(result);
    }

    @Override
    public void MutableMapIterable_removeKey()
    {
        MutableMapIterable<Object, Object> map = this.newWith();
        assertThrows(UnsupportedOperationException.class, () -> map.removeKey(2));
    }

    @Override
    public void MutableMapIterable_removeIf()
    {
        MutableMapIterable<Object, Object> map = this.newWith();
        assertThrows(UnsupportedOperationException.class, () -> map.removeIf(null));
    }

    @Override
    public void Map_remove()
    {
        MutableMapIterable<Object, Object> map = this.newWith();
        assertThrows(UnsupportedOperationException.class, () -> map.remove(2));
    }

    @Override
    public void Map_entrySet_remove()
    {
        MutableMapIterable<Object, Object> map = this.newWithKeysValues();
        assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(ImmutableEntry.of(null, null)));
    }

    @Override
    public void Map_clear()
    {
        MutableMapIterable<Object, String> map = this.newWith("Three", "Two", "One");
        assertThrows(UnsupportedOperationException.class, map::clear);
    }

    @Override
    public void Iterable_remove()
    {
        UnmodifiableIterableTestCase.super.Iterable_remove();
    }

    @Override
    public void Map_putAll()
    {
        MutableMapIterable<Integer, String> map = this.newWithKeysValues(
                3, "Three",
                2, "2");
        MutableMapIterable<Integer, String> toAdd = this.newWithKeysValues(
                2, "Two",
                1, "One");

        assertThrows(UnsupportedOperationException.class, () -> map.putAll(toAdd));
    }

    @Override
    @Test
    public void MutableMapIterable_getIfAbsentPut()
    {
        MutableMapIterable<String, Integer> map = this.newWithKeysValues("3", 3, "2", 2, "1", 1);
        assertEquals(3, map.getIfAbsentPut("3", () -> {
            throw new AssertionError();
        }));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut("4", () -> {
            throw new AssertionError();
        }));

        assertEquals(3, map.getIfAbsentPut("3", 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut("4", 4));

        assertEquals(3, map.getIfAbsentPutWithKey("3", key -> {
            throw new AssertionError();
        }));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPutWithKey("4", key -> {
            throw new AssertionError();
        }));

        assertEquals(3, map.getIfAbsentPutWith("3", x -> x + 10, 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPutWith("4", x -> x + 10, 4));
    }

    @Override
    public void MutableMapIterable_updateValue()
    {
        MutableMapIterable<String, Integer> map = this.newWithKeysValues("One", 1);
        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValue("One", () -> 0, ignored -> {
                    throw new AssertionError();
                }));
        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValue("Two", () -> 0, ignored -> {
                    throw new AssertionError();
                }));

        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValueWith("One", () -> 0, (ignored, parameter) -> {
                    throw new AssertionError();
                }, "test"));
        assertThrows(
                UnsupportedOperationException.class,
                () -> map.updateValueWith("Two", () -> 0, (ignored, parameter) -> {
                    throw new AssertionError();
                }, "test"));
    }
}
