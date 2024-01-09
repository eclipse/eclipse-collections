/*
 * Copyright (c) 2021 Two Sigma.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable;

import java.util.Collections;

import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.test.CollisionsTestCase;
import org.eclipse.collections.test.map.MapIterableTestCase;
import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertIterablesEqual;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public interface MutableMapIterableTestCase extends MapIterableTestCase, MapTestCase
{
    default boolean supportsNullKeys()
    {
        return true;
    }

    default boolean supportsNullValues()
    {
        return true;
    }

    @Override
    <T> MutableMapIterable<Object, T> newWith(T... elements);

    @Override
    <K, V> MutableMapIterable<K, V> newWithKeysValues(Object... elements);

    @Override
    default void Iterable_toString()
    {
        MapTestCase.super.Iterable_toString();
        MapIterableTestCase.super.Iterable_toString();
    }

    @Test
    default void MutableMapIterable_removeKey()
    {
        MutableMapIterable<Integer, String> map = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        assertEquals("Two", map.removeKey(2));
        assertEquals(
                this.newWithKeysValues(3, "Three", 1, "One"),
                map);

        if (this.supportsNullKeys())
        {
            assertNull(map.removeKey(null));
            assertEquals(
                    this.newWithKeysValues(3, "Three", 1, "One"),
                    map);

            MutableMapIterable<Integer, String> map2 = this.newWithKeysValues(3, "Three", null, "Two", 1, "One");
            assertEquals("Two", map2.removeKey(null));
            assertEquals(
                    this.newWithKeysValues(3, "Three", 1, "One"),
                    map2);
        }
    }

    @Test
    default void MutableMapIterable_removeIf()
    {
        MutableMapIterable<Integer, String> map1 = this.newWithKeysValues(1, "1", 2, "Two", 3, "Three");

        assertFalse(map1.removeIf(Predicates2.alwaysFalse()));
        assertEquals(this.newWithKeysValues(1, "1", 2, "Two", 3, "Three"), map1);
        assertTrue(map1.removeIf(Predicates2.alwaysTrue()));
        assertEquals(this.newWithKeysValues(), map1);

        MutableMapIterable<Integer, String> map2 = this.newWithKeysValues(1, "One", 2, "TWO", 3, "THREE", 4, "four", 5, "Five", 6, "Six", 7, "Seven", 8, "Eight");
        assertTrue(map2.removeIf((each, value) -> each % 2 == 0 && value.length() < 4));
        assertEquals(this.newWithKeysValues(1, "One", 3, "THREE", 4, "four", 5, "Five", 7, "Seven", 8, "Eight"), map2);

        assertTrue(map2.removeIf((each, value) -> each % 2 != 0 && value.equals("THREE")));
        assertEquals(this.newWithKeysValues(1, "One", 4, "four", 5, "Five", 7, "Seven", 8, "Eight"), map2);

        assertTrue(map2.removeIf((each, value) -> each % 2 != 0));
        assertFalse(map2.removeIf((each, value) -> each % 2 != 0));
        assertEquals(this.newWithKeysValues(4, "four", 8, "Eight"), map2);

        MutableMapIterable<Integer, String> map3 = this.newWithKeysValues(CollisionsTestCase.COLLISION_1, "0", CollisionsTestCase.COLLISION_2, "17", CollisionsTestCase.COLLISION_3, "34", 100, "100");
        assertTrue(map3.removeIf((key, value) -> CollisionsTestCase.COLLISION_1.equals(key) || CollisionsTestCase.COLLISION_2.equals(key) || CollisionsTestCase.COLLISION_3.equals(key)));
        assertEquals(this.newWithKeysValues(100, "100"), map3);

        MutableMapIterable<Integer, String> map4 = this.newWithKeysValues(CollisionsTestCase.COLLISION_1, "0", CollisionsTestCase.COLLISION_2, "17", CollisionsTestCase.COLLISION_3, "34", 100, "100");
        assertTrue(map4.removeIf(Predicates2.alwaysTrue()));
        assertEquals(this.newWithKeysValues(), map4);

        MutableMapIterable<Integer, String> map5 = this.newWithKeysValues(CollisionsTestCase.COLLISION_1, "0", CollisionsTestCase.COLLISION_2, "17", CollisionsTestCase.COLLISION_3, "34", 100, "100");
        assertTrue(map5.removeIf((key, value) -> CollisionsTestCase.COLLISION_1.equals(key) || CollisionsTestCase.COLLISION_3.equals(key)));
        assertEquals(this.newWithKeysValues(CollisionsTestCase.COLLISION_2, "17", 100, "100"), map5);
    }

    @Test
    default void MutableMapIterable_getIfAbsentPut()
    {
        MutableMapIterable<String, Integer> map = this.newWithKeysValues("3", 3, "2", 2, "1", 1);
        assertEquals(3, map.getIfAbsentPut("3", () -> {
            throw new AssertionError();
        }));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map);

        assertEquals(4, map.getIfAbsentPut("4", () -> 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1, "4", 4), map);

        MutableMapIterable<String, Integer> map2 = this.newWithKeysValues("3", 3, "2", 2, "1", 1);
        assertEquals(3, map2.getIfAbsentPut("3", 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map2);

        assertEquals(4, map2.getIfAbsentPut("4", 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1, "4", 4), map2);

        MutableMapIterable<String, Integer> map3 = this.newWithKeysValues("3", 3, "2", 2, "1", 1);
        assertEquals(3, map3.getIfAbsentPutWithKey("3", key -> {
            throw new AssertionError();
        }));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map3);

        assertEquals(14, map3.getIfAbsentPutWithKey("4", key -> Integer.parseInt(key) + 10));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1, "4", 14), map3);

        MutableMapIterable<String, Integer> map4 = this.newWithKeysValues("3", 3, "2", 2, "1", 1);
        assertEquals(3, map4.getIfAbsentPutWith("3", x -> x + 10, 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1), map4);

        assertEquals(14, map4.getIfAbsentPutWith("4", x -> x + 10, 4));
        assertEquals(this.newWithKeysValues("3", 3, "2", 2, "1", 1, "4", 14), map4);
    }

    @Test
    default void MutableMapIterable_updateValue()
    {
        MutableMapIterable<Integer, Integer> map = this.newWithKeysValues();
        Interval.oneTo(1000).each(each -> map.updateValue(each % 10, () -> 0, integer -> integer + 1));
        assertEquals(Interval.zeroTo(9).toSet(), map.keySet());
        assertIterablesEqual(Collections.nCopies(10, 100), map.values());

        MutableMapIterable<Integer, Integer> map2 = this.newWithKeysValues();
        MutableList<Integer> list = Interval.oneTo(2000).toList().shuffleThis();
        list.each(each -> map2.updateValue(each % 1000, () -> 0, integer -> integer + 1));
        assertEquals(Interval.zeroTo(999).toSet(), map2.keySet());
        assertIterablesEqual(
                Bags.mutable.withAll(map2.values()).toStringOfItemToCount(),
                Collections.nCopies(1000, 2),
                map2.values());

        MutableMapIterable<Integer, Integer> map3 = this.newWithKeysValues();
        Function2<Integer, String, Integer> increment = (integer, parameter) -> {
            assertEquals("test", parameter);
            return integer + 1;
        };

        Interval.oneTo(1000).each(each -> map3.updateValueWith(each % 10, () -> 0, increment, "test"));
        assertEquals(Interval.zeroTo(9).toSet(), map3.keySet());
        assertIterablesEqual(Collections.nCopies(10, 100), map3.values());

        MutableMapIterable<Integer, Integer> map4 = this.newWithKeysValues();
        MutableList<Integer> list2 = Interval.oneTo(2000).toList().shuffleThis();
        list2.each(each -> map4.updateValueWith(each % 1000, () -> 0, increment, "test"));
        assertEquals(Interval.zeroTo(999).toSet(), map4.keySet());
        assertIterablesEqual(
                Bags.mutable.withAll(map4.values()).toStringOfItemToCount(),
                Collections.nCopies(1000, 2),
                map4.values());
    }
}
