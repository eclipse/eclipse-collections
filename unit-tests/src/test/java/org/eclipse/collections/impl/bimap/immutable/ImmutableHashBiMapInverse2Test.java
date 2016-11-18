/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.immutable;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.impl.IntegerWithCast;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.map.MapIterableTestCase;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableHashBiMapInverse2Test extends MapIterableTestCase
{
    @Override
    protected <K, V> ImmutableBiMap<K, V> newMap()
    {
        return BiMaps.immutable.<V, K>empty().inverse();
    }

    @Override
    protected <K, V> MapIterable<K, V> newMapWithKeyValue(K key1, V value1)
    {
        return BiMaps.immutable.withAll(Maps.immutable.with(value1, key1)).inverse();
    }

    @Override
    protected <K, V> ImmutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return BiMaps.immutable.withAll(Maps.immutable.with(value1, key1, value2, key2)).inverse();
    }

    @Override
    protected <K, V> ImmutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return BiMaps.immutable.withAll(Maps.immutable.with(value1, key1, value2, key2, value3, key3)).inverse();
    }

    @Override
    protected <K, V> ImmutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return BiMaps.immutable.withAll(Maps.immutable.with(value1, key1, value2, key2, value3, key3, value4, key4)).inverse();
    }

    @Override
    @Test
    public void flipUniqueValues()
    {
        ImmutableBiMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        ImmutableBiMap<String, Integer> result = map.flipUniqueValues();
        ImmutableBiMap<String, Integer> expectedMap = this.newMapWithKeysValues("1", 1, "2", 2, "3", 3);
        Assert.assertEquals(expectedMap, result);
    }

    @Override
    @Test
    public void flip()
    {
        ImmutableBiMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        ImmutableSetMultimap<String, Integer> result = map.flip();
        UnifiedSetMultimap<String, Integer> expected = UnifiedSetMultimap.newMultimap(Tuples.pair("1", 1), Tuples.pair("2", 2), Tuples.pair("3", 3));
        Assert.assertEquals(expected, result);
    }

    @Override
    @Test
    public void nullCollisionWithCastInEquals()
    {
        ImmutableBiMap<IntegerWithCast, String> map = this.newMapWithKeysValues(
                new IntegerWithCast(0), "Test 2",
                null, "Test 1");
        Assert.assertEquals(
                this.newMapWithKeysValues(
                        new IntegerWithCast(0), "Test 2",
                        null, "Test 1"),
                map);
        Assert.assertEquals("Test 2", map.get(new IntegerWithCast(0)));
        Assert.assertEquals("Test 1", map.get(null));
    }
}
