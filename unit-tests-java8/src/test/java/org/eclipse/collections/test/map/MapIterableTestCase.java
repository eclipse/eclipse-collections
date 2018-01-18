/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map;

import java.util.Optional;

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.SerializeTestHelper;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.test.RichIterableWithDuplicatesTestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.eclipse.collections.impl.test.Verify.assertThrows;
import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public interface MapIterableTestCase extends RichIterableWithDuplicatesTestCase
{
    @Override
    <T> MapIterable<Object, T> newWith(T... elements);

    <K, V> MapIterable<K, V> newWithKeysValues(Object... elements);

    default boolean supportsNullKeys()
    {
        return true;
    }

    default boolean supportsNullValues()
    {
        return true;
    }

    @Test
    default void serialization()
    {
        MapIterable<Object, String> original = this.newWith("Three", "Two", "One");
        MapIterable<Object, String> copy = SerializeTestHelper.serializeDeserialize(original);
        assertEquals(copy, original);
    }

    @Test
    default void MapIterable_ifPresentApply()
    {
        MapIterable<String, Integer> map = this.newWithKeysValues("Three", 3, "Two", 2, "One", 1);
        assertEquals(13, map.ifPresentApply("Three", x -> x + 10));
        assertEquals(12, map.ifPresentApply("Two", x -> x + 10));
        assertEquals(11, map.ifPresentApply("One", x -> x + 10));
        assertNull(map.ifPresentApply("Zero", x -> x + 10));
        assertEquals(map, this.newWithKeysValues("Three", 3, "Two", 2, "One", 1));
    }

    @Test
    default void MapIterable_getIfAbsent()
    {
        MapIterable<String, Integer> map = this.newWithKeysValues("Three", 3, "Two", 2, "One", 1);
        assertEquals(3, map.getIfAbsentValue("Three", 0));
        assertEquals(2, map.getIfAbsentValue("Two", 0));
        assertEquals(1, map.getIfAbsentValue("One", 0));
        assertEquals(0, map.getIfAbsentValue("Zero", 0));
        assertEquals(map, this.newWithKeysValues("Three", 3, "Two", 2, "One", 1));

        assertEquals(3, map.getIfAbsent("Three", () -> 0));
        assertEquals(2, map.getIfAbsent("Two", () -> 0));
        assertEquals(1, map.getIfAbsent("One", () -> 0));
        assertEquals(0, map.getIfAbsent("Zero", () -> 0));
        assertEquals(map, this.newWithKeysValues("Three", 3, "Two", 2, "One", 1));

        assertEquals(3, map.getIfAbsentWith("Three", x -> x + 1, 13));
        assertEquals(2, map.getIfAbsentWith("Two", x -> x + 1, 12));
        assertEquals(1, map.getIfAbsentWith("One", x -> x + 1, 11));
        assertEquals(11, map.getIfAbsentWith("Zero", x -> x + 1, 10));
        assertEquals(map, this.newWithKeysValues("Three", 3, "Two", 2, "One", 1));
    }

    @Test
    default void MapIterable_forEachKey()
    {
        UnifiedSet<Integer> result = UnifiedSet.newSet();
        MapIterable<Integer, String> map = this.newWithKeysValues(3, "3", 2, "2", 1, "1");
        map.forEachKey(CollectionAddProcedure.on(result));
        Verify.assertSetsEqual(UnifiedSet.newSetWith(1, 2, 3), result);
    }

    @Test
    default void MapIterable_forEachValue()
    {
        UnifiedSet<String> result = UnifiedSet.newSet();
        MapIterable<Integer, String> map = this.newWithKeysValues(3, "3", 2, "2", 1, "1");
        map.forEachValue(CollectionAddProcedure.on(result));
        Verify.assertSetsEqual(UnifiedSet.newSetWith("1", "2", "3"), result);
    }

    @Test
    default void MapIterable_forEachKeyValue()
    {
        MapIterable<Object, Integer> map = this.newWith(3, 3, 3, 2, 2, 1);
        MutableCollection<Integer> forEachKeyValue = this.newMutableForFilter();
        map.forEachKeyValue((key, value) -> forEachKeyValue.add(value + 10));
        assertEquals(this.newMutableForFilter(13, 13, 13, 12, 12, 11), forEachKeyValue);

        MapIterable<Integer, String> map2 = this.newWithKeysValues(3, "Three", 2, "Two", 1, "Three");
        MutableCollection<String> forEachKeyValue2 = this.newMutableForFilter();
        map2.forEachKeyValue((key, value) -> forEachKeyValue2.add(key + value));
        assertEquals(this.newMutableForFilter("3Three", "2Two", "1Three"), forEachKeyValue2);

        MutableCollection<Integer> forEachValue = this.newMutableForFilter();
        map.forEachValue(value -> forEachValue.add(value + 10));
        assertEquals(this.newMutableForFilter(13, 13, 13, 12, 12, 11), forEachValue);

        MutableCollection<Object> forEachKey = this.newMutableForFilter();
        map2.forEachKey(key -> forEachKey.add(key + 1));
        assertEquals(this.newMutableForFilter(4, 3, 2), forEachKey);
    }

    @Test
    default void MapIterable_flipUniqueValues()
    {
        MapIterable<String, Integer> map = this.newWithKeysValues("Three", 3, "Two", 2, "One", 1);
        MapIterable<Integer, String> result = map.flipUniqueValues();

        // TODO: Use IterableTestCase.assertEquals instead, after setting up methods like getExpectedTransformed, but for maps.
        Assert.assertEquals(
                UnifiedMap.newWithKeysValues(3, "Three", 2, "Two", 1, "One"),
                result);

        assertThrows(
                IllegalStateException.class,
                () -> this.newWithKeysValues(1, "2", 2, "2").flipUniqueValues());
    }

    @Test
    default void MapIterable_collect_Function2()
    {
        this.getExpectedTransformed();
        MapIterable<Integer, String> map = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        MapIterable<Integer, String> actual = map.collect((key, value) -> Tuples.pair(key + 1, key + value));
        // TODO: Use IterableTestCase.assertEquals instead, after setting up methods like getExpectedTransformed, but for maps.
        Assert.assertEquals(Maps.mutable.with(4, "3Three", 3, "2Two", 2, "1One"), actual);
    }

    @Test
    default void MapIterable_collectValues()
    {
        MapIterable<Integer, String> map = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        MapIterable<Integer, String> actual = map.collectValues((argument1, argument2) -> new StringBuilder(argument2).reverse().toString());
        assertEquals(
                this.newWithKeysValues(3, "eerhT", 2, "owT", 1, "enO"),
                actual);
    }

    @Test
    default void MapIterable_select_reject()
    {
        MapIterable<Integer, String> map = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        MapIterable<Object, Object> expected = this.newWithKeysValues(2, "Two", 1, "One");

        assertEquals(
                expected,
                map.select((key1, value1) -> key1.equals(1) || value1.equals("Two")));

        assertEquals(
                expected,
                map.reject((key, value) -> !key.equals(1) && !value.equals("Two")));
    }

    @Test
    default void MapIterable_detect()
    {
        MapIterable<String, String> map = this.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        assertEquals(Tuples.pair("1", "One"), map.detect((key1, value1) -> "1".equals(key1)));
        assertEquals(Tuples.pair("2", "Two"), map.detect((key1, value1) -> "Two".equals(value1)));
        assertNull(map.detect((ignored1, ignored2) -> false));

        MutableCollection<Integer> expectedIterationOrder = this.expectedIterationOrder();
        MutableCollection<Integer> detectIterationOrder = this.newMutableForFilter();
        MapIterable<Object, Integer> instanceUnderTest = (MapIterable<Object, Integer>) this.getInstanceUnderTest();
        instanceUnderTest.detect((key, value) -> {
            detectIterationOrder.add(value);
            return false;
        });
        assertEquals(expectedIterationOrder, detectIterationOrder);
    }

    @Test
    default void MapIterable_detectOptional()
    {
        MapIterable<String, String> map = this.newWithKeysValues("1", "One", "2", "Two", "3", "Three");
        assertEquals(Optional.of(Tuples.pair("1", "One")), map.detectOptional((key1, value1) -> "1".equals(key1)));
        assertEquals(Optional.of(Tuples.pair("2", "Two")), map.detectOptional((key1, value1) -> "Two".equals(value1)));
        assertSame(Optional.empty(), map.detectOptional((ignored1, ignored2) -> false));

        MutableCollection<Integer> expectedIterationOrder = this.expectedIterationOrder();
        MutableCollection<Integer> detectOptionalIterationOrder = this.newMutableForFilter();
        MapIterable<Object, Integer> instanceUnderTest = (MapIterable<Object, Integer>) this.getInstanceUnderTest();
        instanceUnderTest.detectOptional((key, value) -> {
            detectOptionalIterationOrder.add(value);
            return false;
        });
        assertEquals(expectedIterationOrder, detectOptionalIterationOrder);
    }

    @Test
    default void MapIterable_containsKey()
    {
        MapIterable<String, Integer> map = this.newWithKeysValues("Three", 3, "Two", 2, "One", 1);
        assertTrue(map.containsKey("Three"));
        assertFalse(map.containsKey("Four"));

        if (this.supportsNullKeys())
        {
            assertFalse(map.containsKey(null));

            MapIterable<String, Integer> map2 = this.newWithKeysValues("Three", 3, "Two", 2, "One", 1, null, null);
            assertTrue(map2.containsKey(null));
        }
    }

    @Test
    default void MapIterable_containsValue()
    {
        MapIterable<Integer, String> map = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One");
        assertTrue(map.containsValue("Three"));
        assertFalse(map.containsValue("Four"));

        if (this.supportsNullValues())
        {
            assertFalse(map.containsValue(null));
            MapIterable<String, Integer> map2 = this.newWithKeysValues(3, "Three", 2, "Two", 1, null);
            assertTrue(map2.containsValue(null));
        }

        if (this.supportsNullKeys() && this.supportsNullValues())
        {
            MapIterable<String, Integer> map2 = this.newWithKeysValues(3, "Three", 2, "Two", 1, "One", null, null);
            assertTrue(map2.containsValue(null));
        }
    }
}
