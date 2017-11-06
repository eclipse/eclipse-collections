/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

public class UnmodifiableBiMapTest extends AbstractMutableBiMapTestCase
{
    @Override
    public MutableBiMap<Integer, Character> classUnderTest()
    {
        HashBiMap<Integer, Character> map = HashBiMap.newMap();
        map.put(1, null);
        map.put(null, 'b');
        map.put(3, 'c');
        return map.asUnmodifiable();
    }

    @Override
    public MutableBiMap<Integer, Character> getEmptyMap()
    {
        return HashBiMap.<Integer, Character>newMap().asUnmodifiable();
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMap()
    {
        return HashBiMap.<K, V>newMap().asUnmodifiable();
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeyValue(K key, V value)
    {
        return UnmodifiableBiMap.of(Maps.mutable.of(key, value));
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return HashBiMap.newWithKeysValues(key1, value1, key2, value2).asUnmodifiable();
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return HashBiMap.newWithKeysValues(key1, value1, key2, value2, key3, value3).asUnmodifiable();
    }

    @Override
    protected <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return HashBiMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4).asUnmodifiable();
    }

    @Test
    public void newMap_throws()
    {
        Verify.assertThrows(IllegalArgumentException.class, () -> new UnmodifiableBiMap<>(null));
        MutableBiMap<String, String> biMap = null;
        Verify.assertThrows(IllegalArgumentException.class, () -> UnmodifiableBiMap.of(biMap));
        Map<String, String> map = null;
        Verify.assertThrows(IllegalArgumentException.class, () -> UnmodifiableBiMap.of(map));
    }

    @Override
    @Test
    public void containsKey()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();

        Assert.assertTrue(biMap.containsKey(1));
        Assert.assertTrue(biMap.containsKey(null));
        Assert.assertTrue(biMap.containsKey(3));
        Assert.assertFalse(biMap.containsKey(4));
    }

    @Override
    @Test
    public void containsValue()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();

        Assert.assertTrue(biMap.containsValue(null));
        Assert.assertTrue(biMap.containsValue('b'));
        Assert.assertTrue(biMap.containsValue('c'));
        Assert.assertFalse(biMap.containsValue('d'));
    }

    @Override
    @Test
    public void get()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Assert.assertNull(biMap.get(1));
        Assert.assertEquals(Character.valueOf('b'), biMap.get(null));
        Assert.assertEquals(Character.valueOf('c'), biMap.get(3));
        Assert.assertNull(biMap.get(4));
    }

    @Override
    @Test
    public void iterator()
    {
        MutableSet<Character> expected = UnifiedSet.newSetWith(null, 'b', 'c');
        MutableSet<Character> actual = UnifiedSet.newSet();
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Iterator<Character> iterator = biMap.iterator();
        Assert.assertTrue(iterator.hasNext());
        Verify.assertThrows(UnsupportedOperationException.class, iterator::remove);
        Verify.assertSize(3, biMap);
        Verify.assertSize(3, biMap.inverse());
        for (int i = 0; i < 3; i++)
        {
            Assert.assertTrue(iterator.hasNext());
            actual.add(iterator.next());
        }
        Assert.assertEquals(expected, actual);
        Assert.assertFalse(iterator.hasNext());
        Verify.assertThrows(NoSuchElementException.class, (Runnable) iterator::next);
    }

    @Override
    @Test
    public void testClone()
    {
        MutableBiMap<Object, Object> biMap = this.newMap();
        MutableBiMap<Object, Object> clone = biMap.clone();
        Assert.assertSame(biMap, clone);
    }

    @Override
    @Test
    public void withKeyValue()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.getEmptyMap().withKeyValue(1, 'a'));
    }

    @Override
    @Test
    public void withAllKeyValueArguments()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("A", 1, "B", 2).withAllKeyValueArguments(Tuples.pair("B", 22), Tuples.pair("C", 3)));
    }

    @Override
    @Test
    public void add()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeyValue("A", 1).add(Tuples.pair("A", 3)));
    }

    @Override
    @Test
    public void put()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().put(4, 'd'));
    }

    @Override
    @Test
    public void putPair()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().putPair(Tuples.pair(4, 'd')));
    }

    @Override
    @Test
    public void putAll()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("key1", "value1", "key2", "value2").putAll(UnifiedMap.newMap()));
    }

    @Override
    @Test
    public void forcePut()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("key1", "value1", "key2", "value2").forcePut("value2", "key1"));
    }

    @Override
    @Test
    public void updateValue()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("key1", "value1", "key2", "value2").updateValue("key1", () -> "value3", String::toUpperCase));
    }

    @Override
    @Test
    public void updateValueWith()
    {
        MutableBiMap<Integer, Character> biMap = this.classUnderTest();
        Function2<Character, Boolean, Character> toUpperOrLowerCase = (character, parameter) -> parameter ? Character.toUpperCase(character) : Character.toLowerCase(character);
        Verify.assertThrows(UnsupportedOperationException.class, () -> biMap.updateValueWith(4, () -> 'd', toUpperOrLowerCase, true));
    }

    @Override
    @Test
    public void getIfAbsentPut()
    {
        MutableMapIterable<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut(4, () -> "4"));
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut(4, "4"));
    }

    @Override
    @Test
    public void getIfAbsentPutWith()
    {
        MutableMapIterable<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPutWith(4, object -> "4", null));
    }

    @Override
    @Test
    public void getIfAbsentPutWithKey()
    {
        MutableMapIterable<Integer, Integer> map = this.newMapWithKeysValues(1, 1, 2, 2, 3, 3);
        Assert.assertNull(map.get(4));
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPutWithKey(4, Functions.getIntegerPassThru()));
    }

    @Override
    @Test
    public void getIfAbsentPutValue()
    {
        MutableMapIterable<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        Assert.assertNull(map.get(4));
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.getIfAbsentPut(4, "4"));
    }

    @Override
    @Test
    public void withoutKey()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("A", 1, "B", 2).withoutKey("B"));
    }

    @Override
    @Test
    public void withoutAllKeys()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("A", 1, "B", 2, "C", 3).withoutAllKeys(FastList.newListWith("A", "C")));
    }

    @Override
    @Test
    public void withAllKeyValues()
    {
        Verify.assertThrows(UnsupportedOperationException.class,
                () -> this.newMapWithKeysValues("A", 1, "B", 2).withAllKeyValues(
                        FastList.newListWith(Tuples.pair("B", 22), Tuples.pair("C", 3))));
    }

    @Override
    @Test
    public void clear()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().clear());
    }

    @Override
    @Test
    public void clearEntrySet()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).entrySet().clear());
    }

    @Override
    @Test
    public void clearKeySet()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).keySet().clear());
    }

    @Override
    @Test
    public void remove()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("key1", "value1", "key2", "value2").remove("key1"));
    }

    @Override
    @Test
    public void removeObject()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).remove("Two"));
    }

    @Override
    @Test
    public void removeFromEntrySet()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).entrySet().remove(ImmutableEntry.of("Two", 2)));
    }

    @Override
    @Test
    public void removeFromKeySet()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).keySet().remove("Four"));
    }

    @Override
    @Test
    public void removeNullFromKeySet()
    {
        MutableBiMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.values().remove(null));
    }

    @Override
    @Test
    public void removeKey()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues(1, "1", 2, "Two").removeKey(1));
    }

    @Override
    @Test
    public void removeNullFromValues()
    {
        MutableBiMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.values().remove(null));
    }

    @Override
    @Test
    public void removeFromValues()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).values().remove(4));
    }

    @Override
    @Test
    public void retainAllFromEntrySet()
    {
        MutableMapIterable<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.entrySet().retainAll(FastList.newListWith(
                ImmutableEntry.of("One", 1),
                ImmutableEntry.of("Two", 2),
                ImmutableEntry.of("Three", 3),
                ImmutableEntry.of("Four", 4))));
    }

    @Override
    @Test
    public void retainAllFromKeySet()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).keySet().retainAll(FastList.newListWith("One", "Two", "Three", "Four")));
    }

    @Override
    @Test
    public void retainAllFromValues()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).values().retainAll(FastList.newListWith(1, 2, 3, 4)));
    }

    @Override
    @Test
    public void removeAllFromEntrySet()
    {
        MutableMapIterable<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.entrySet().removeAll(FastList.newListWith(
                ImmutableEntry.of("One", 1),
                ImmutableEntry.of("Three", 3))));
    }

    @Override
    @Test
    public void removeAllFromKeySet()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).keySet().removeAll(FastList.newListWith("Four")));
    }

    @Override
    @Test
    public void removeAllFromValues()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).values().removeAll(FastList.newListWith(4)));
    }

    @Override
    @Test
    public void getIfAbsentPut_block_throws()
    {
        // Not applicable for unmodifiable maps
    }

    @Override
    @Test
    public void getIfAbsentPutWith_block_throws()
    {
        // Not applicable for unmodifiable maps
    }

    @Override
    public void retainAllFromKeySet_null_collision()
    {
        // Not applicable for unmodifiable maps
    }

    @Override
    public void rehash_null_collision()
    {
        // Not applicable for unmodifiable maps
    }

    @Override
    public void nullCollisionWithCastInEquals()
    {
        // Not applicable for unmodifiable maps
    }
}
