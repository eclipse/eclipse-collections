/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.mutable;

import java.util.Comparator;

import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link UnmodifiableTreeMap}.
 */
public class UnmodifiableTreeMapTest extends MutableSortedMapTestCase
{
    @Override
    public <K, V> MutableSortedMap<K, V> newMap()
    {
        return new UnmodifiableTreeMap<>(new TreeSortedMap<>());
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeyValue(K key, V value)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(key, value));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(key1, value1, key2, value2));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(
                key1, value1, key2, value2, key3, value3));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(
            K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(
                key1, value1, key2, value2, key3, value3, key4, value4));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMap(Comparator<? super K> comparator)
    {
        return new UnmodifiableTreeMap<>(new TreeSortedMap<>(comparator));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeyValue(Comparator<? super K> comparator, K key, V value)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(key, value));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(comparator, key1, value1, key2, value2));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(
                comparator,
                key1, value1,
                key2, value2,
                key3, value3));
    }

    @Override
    public <K, V> MutableSortedMap<K, V> newMapWithKeysValues(
            Comparator<? super K> comparator,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        return new UnmodifiableTreeMap<>(TreeSortedMap.newMapWith(
                comparator,
                key1, value1,
                key2, value2,
                key3, value3,
                key4, value4));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeObject()
    {
        MutableSortedMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        map.remove("One");
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeKey()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.removeKey(1);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeFromEntrySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.entrySet().remove(ImmutableEntry.of(2, "Two"));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAllFromEntrySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.entrySet().removeAll(FastList.newListWith(ImmutableEntry.of(2, "Two")));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void retainAllFromEntrySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.entrySet().retainAll(FastList.newListWith(ImmutableEntry.of(2, "Two")));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void clearEntrySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.entrySet().clear();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeFromKeySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.keySet().remove(2);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAllFromKeySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.keySet().removeAll(FastList.newListWith(1, 2));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void retainAllFromKeySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.keySet().retainAll(Lists.mutable.of());
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void clearKeySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.keySet().clear();
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeFromValues()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.values().remove("Two");
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeAllFromValues()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.values().removeAll(FastList.newListWith("One", "Two"));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void removeNullFromValues()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.values().remove(null);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void retainAllFromValues()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "Two");
        map.values().retainAll(Lists.mutable.of());
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void getIfAbsentPut()
    {
        this.newMapWithKeysValues(1, "1", 2, "2", 3, "3").getIfAbsentPut(4, new PassThruFunction0<>("4"));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void getIfAbsentPutValue()
    {
        this.newMapWithKeysValues(1, "1", 2, "2", 3, "3").getIfAbsentPut(4, "4");
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void getIfAbsentPutWithKey()
    {
        this.newMapWithKeysValues(1, 1, 2, 2, 3, 3).getIfAbsentPutWithKey(4, Functions.getIntegerPassThru());
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void getIfAbsentPutWith()
    {
        this.newMapWithKeysValues(1, "1", 2, "2", 3, "3").getIfAbsentPutWith(4, String::valueOf, 4);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void putAll()
    {
        this.newMapWithKeysValues(1, "One", 2, "2").putAll(null);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void putAllFromCollection()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "2");
        map.collectKeysAndValues(null, null, null);
    }

    @Override
    @Test
    public void clear()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "2");
        Verify.assertThrows(UnsupportedOperationException.class, map::clear);
    }

    @Override
    @Test
    public void asUnmodifiable()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "2");
        Assert.assertSame(map, map.asUnmodifiable());
    }

    @Test
    public void entrySet()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "One", 2, "2").asUnmodifiable();

        Verify.assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(null));

        Verify.assertThrows(UnsupportedOperationException.class, () -> Iterate.getFirst(map.entrySet()).setValue("Three"));

        Assert.assertEquals(this.newMapWithKeysValues(1, "One", 2, "2"), map);
    }

    @Test
    public void entrySetToArray()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeyValue(1, "One").asUnmodifiable();
        Object[] entries = map.entrySet().toArray();
        Assert.assertEquals(ImmutableEntry.of(1, "One"), entries[0]);
    }

    @Test
    public void entrySetToArrayWithTarget()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeyValue(1, "One").asUnmodifiable();
        Object[] entries = map.entrySet().toArray(new Object[]{});
        Assert.assertEquals(ImmutableEntry.of(1, "One"), entries[0]);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void put()
    {
        this.newMapWithKeysValues(1, "One", 2, "Two").put(3, "Three");
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void add()
    {
        this.newMapWithKeyValue("A", 1).add(Tuples.pair("A", 3));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void putPair()
    {
        this.newMapWithKeyValue("A", 1).putPair(Tuples.pair("A", 3));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void withKeyValue()
    {
        this.newMapWithKeysValues(1, "One", 2, "2").withKeyValue(null, null);
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void withAllKeyValues()
    {
        this.newMapWithKeysValues("A", 1, "B", 2).withAllKeyValues(
                FastList.newListWith(Tuples.pair("B", 22), Tuples.pair("C", 3)));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void withAllKeyValueArguments()
    {
        this.newMapWithKeysValues("A", 1, "B", 2).withAllKeyValueArguments(Tuples.pair("B", 22), Tuples.pair("C", 3));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void withoutKey()
    {
        this.newMapWithKeysValues("A", 1, "B", 2).withoutKey("B");
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void withoutAllKeys()
    {
        this.newMapWithKeysValues("A", 1, "B", 2, "C", 3).withoutAllKeys(FastList.newListWith("A", "C"));
    }

    @Override
    @Test(expected = UnsupportedOperationException.class)
    public void with()
    {
        this.newMapWithKeysValues(1, "1", 2, "2").with(Tuples.pair(3, "3"));
    }

    @Override
    @Test
    public void headMap()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");

        Verify.assertInstanceOf(UnmodifiableTreeMap.class, map.headMap(3));
        this.checkMutability(map.headMap(3));
    }

    @Override
    @Test
    public void tailMap()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");

        Verify.assertInstanceOf(UnmodifiableTreeMap.class, map.tailMap(2));
        this.checkMutability(map.tailMap(2));
    }

    @Override
    @Test
    public void subMap()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");

        Verify.assertInstanceOf(UnmodifiableTreeMap.class, map.subMap(1, 3));
        this.checkMutability(map.subMap(1, 3));
    }

    @Override
    @Test
    public void testClone()
    {
        MutableSortedMap<Integer, String> map = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3", 4, "4");
        Assert.assertSame(map, map.clone());
        Verify.assertInstanceOf(UnmodifiableTreeMap.class, map.clone());
    }

    private void checkMutability(MutableSortedMap<Integer, String> map)
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> map.put(3, "3"));

        Verify.assertThrows(UnsupportedOperationException.class, () -> map.putAll(TreeSortedMap.newMapWith(1, "1", 2, "2")));

        Verify.assertThrows(UnsupportedOperationException.class, () -> map.remove(2));

        Verify.assertThrows(UnsupportedOperationException.class, map::clear);

        Verify.assertThrows(UnsupportedOperationException.class, () -> map.with(Tuples.pair(1, "1")));
    }

    @Override
    @Test
    public void getIfAbsentPut_block_throws()
    {
        // Not applicable for unmodifiable adapter
    }

    @Override
    @Test
    public void getIfAbsentPutWith_block_throws()
    {
        // Not applicable for unmodifiable adapter
    }

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void updateValue()
    {
        this.<Integer, Integer>newMap().updateValue(0, () -> 0, Functions.identity());
    }

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void updateValue_collisions()
    {
        this.<Integer, Integer>newMap().updateValue(0, () -> 0, Functions.identity());
    }

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void updateValueWith()
    {
        this.<Integer, Integer>newMap().updateValueWith(0, () -> 0, (integer, parameter) -> 0, "test");
    }

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void updateValueWith_collisions()
    {
        this.<Integer, Integer>newMap().updateValueWith(0, () -> 0, (integer, parameter) -> 0, "test");
    }
}
