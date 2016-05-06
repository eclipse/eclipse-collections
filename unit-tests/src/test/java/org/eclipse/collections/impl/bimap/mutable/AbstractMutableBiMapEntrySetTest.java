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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.ImmutableEntry;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractMutableBiMapEntrySetTest
{
    protected abstract <K, V> MutableBiMap<K, V> newMap();

    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2);

    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3);

    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeysValues(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4);

    protected abstract <K, V> MutableBiMap<K, V> newMapWithKeyValue(K key, V value);

    @Test
    public void entry_clear()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        Set<Map.Entry<Integer, String>> entries = biMap.entrySet();
        entries.clear();
        Verify.assertEmpty(entries);
        Verify.assertEmpty(biMap);
        Verify.assertEmpty(biMap.inverse());
    }

    @Test
    public void entry_isEmpty()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        Set<Map.Entry<Integer, String>> entries = biMap.entrySet();
        Verify.assertNotEmpty(entries);
        biMap.clear();
        Verify.assertEmpty(entries);
    }

    @Test
    public void entry_toArray()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeysValues(1, "1", 2, "2", 3, "3");
        Map.Entry<Integer, String>[] objects = biMap.entrySet().toArray(new Map.Entry[3]);

        Comparator<Map.Entry<Integer, String>> comparator = (o1, o2) -> o1.getKey().compareTo(o2.getKey());
        Arrays.sort(objects, comparator);

        for (int i = 0; i < objects.length; i++)
        {
            Map.Entry<Integer, String> object = objects[i];
            Assert.assertEquals(Integer.valueOf(i + 1), object.getKey());
            Assert.assertEquals(String.valueOf(i + 1), object.getValue());
        }

        Map.Entry<Integer, String>[] smallArray = biMap.entrySet().toArray(new Map.Entry[2]);

        Arrays.sort(objects, comparator);

        for (int i = 0; i < objects.length; i++)
        {
            Map.Entry<Integer, String> object = smallArray[i];
            Assert.assertEquals(Integer.valueOf(i + 1), object.getKey());
            Assert.assertEquals(String.valueOf(i + 1), object.getValue());
        }

        for (int i = 0; i < objects.length; i++)
        {
            Map.Entry<Integer, String> object = objects[i];
            object.setValue(String.valueOf(i + 4));
        }
        Assert.assertTrue(Arrays.equals(new Map.Entry[]{ImmutableEntry.of(1, "4"), ImmutableEntry.of(2, "5"), ImmutableEntry.of(3, "6")}, biMap.entrySet().toArray()));
        Assert.assertEquals(HashBiMap.newWithKeysValues(1, "4", 2, "5", 3, "6"), biMap);
    }

    @Test
    public void entry_setValue()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(1, "One");
        Map.Entry<Integer, String> entry = Iterate.getFirst(biMap.entrySet());
        String value = "Ninety-Nine";
        Assert.assertEquals("One", entry.setValue(value));
        Assert.assertEquals(value, entry.getValue());
        Verify.assertContainsKeyValue(1, value, biMap);

        biMap.remove(1);
        Verify.assertEmpty(biMap);
        Assert.assertNull(entry.setValue("Ignored"));
    }

    @Test
    public void entry_setValue_throws()
    {
        MutableBiMap<Integer, Character> biMap = this.newMapWithKeysValues(1, 'a', 2, 'b', 3, 'c');
        Map.Entry<Integer, Character> entry = Iterate.getFirst(biMap.entrySet());

        Verify.assertThrows(IllegalArgumentException.class, () -> entry.setValue('b'));
        Verify.assertContainsKeyValue(2, 'b', biMap);
    }

    @Test
    public void entrySet_remove()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeysValues(1, "One", 3, "Three", 4, "Four");
        Set<Map.Entry<Integer, String>> entries = biMap.entrySet();
        entries.remove(ImmutableEntry.of(5, "Five"));
        Verify.assertSize(3, biMap);
        Verify.assertSize(3, biMap.inverse());
        Verify.assertSize(3, biMap.entrySet());

        entries.remove(ImmutableEntry.of(1, "Two"));
        Verify.assertSize(3, biMap);
        Verify.assertSize(3, biMap.inverse());

        entries.remove(ImmutableEntry.of(1, "One"));
        Verify.assertSize(2, biMap);
        Verify.assertSize(2, biMap.inverse());
        Verify.assertSize(2, biMap.entrySet());

        Assert.assertEquals(HashBiMap.newWithKeysValues(3, "Three", 4, "Four"), biMap);
        Assert.assertEquals(HashBiMap.newWithKeysValues(3, "Three", 4, "Four").inverse(), biMap.inverse());

        MutableBiMap<Integer, String> map1 = this.newMapWithKeysValues(1, null, 3, "Three", 4, "Four");
        Set<Map.Entry<Integer, String>> entries1 = map1.entrySet();
        entries1.remove(ImmutableEntry.of(1, null));
        Verify.assertSize(2, biMap);
        Verify.assertSize(2, biMap.inverse());
        Verify.assertSize(2, biMap.entrySet());
        Assert.assertEquals(HashBiMap.newWithKeysValues(3, "Three", 4, "Four"), biMap);
    }

    @Test
    public void entrySet_contains()
    {
        // simple biMap, test for null key
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(1, "One");
        Set<Map.Entry<Integer, String>> entries = biMap.entrySet();
        Verify.assertNotContains(ImmutableEntry.of(null, "Null"), entries);
        Verify.assertNotContains(new Object(), entries);
    }

    @Test
    public void entrySet_containsAll()
    {
        // simple biMap, test for non-existent entries
        MutableBiMap<Integer, String> biMap = this.newMapWithKeysValues(1, "One", 3, "Three");
        Set<Map.Entry<Integer, String>> entries = biMap.entrySet();
        Assert.assertFalse(entries.containsAll(FastList.newListWith(ImmutableEntry.of(2, "Two"))));

        Assert.assertTrue(entries.containsAll(FastList.newListWith(ImmutableEntry.of(1, "One"), ImmutableEntry.of(3, "Three"))));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void entrySet_add()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(1, "One");
        Set<Map.Entry<Integer, String>> entries = biMap.entrySet();
        entries.add(ImmutableEntry.of(2, "Two"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void entrySet_addAll()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(1, "One");
        Set<Map.Entry<Integer, String>> entries = biMap.entrySet();
        entries.addAll(FastList.newListWith(ImmutableEntry.of(2, "Two")));
    }

    @Test
    public void entrySet_equals()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeysValues(1, "One", 2, "Two", 3, "Three", null, null);
        Assert.assertNotEquals(UnifiedSet.newSetWith(ImmutableEntry.of(5, "Five")), biMap.entrySet());

        UnifiedSet<ImmutableEntry<Integer, String>> expected = UnifiedSet.newSetWith(
                ImmutableEntry.of(1, "One"),
                ImmutableEntry.of(2, "Two"),
                ImmutableEntry.of(3, "Three"),
                ImmutableEntry.of(null, null));
        Assert.assertEquals(expected, biMap.entrySet());
    }

    @Test(expected = NoSuchElementException.class)
    public void entrySet_Iterator_incrementPastEnd()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(1, "One");
        Iterator<Map.Entry<Integer, String>> iterator = biMap.entrySet().iterator();
        iterator.next();
        iterator.next();
    }

    @Test
    public void entry_hashCodeForNullKeyAndValue()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(null, null);
        Map.Entry<Integer, String> entry = Iterate.getFirst(biMap.entrySet());

        Assert.assertEquals(0, entry.hashCode());
    }

    @Test
    public void entry_equals()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(1, "a");
        Map.Entry<Integer, String> entry = Iterate.getFirst(biMap.entrySet());

        Assert.assertEquals(entry, ImmutableEntry.of(1, "a"));
    }

    @Test
    public void entry_equalsWithNonEntry()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(null, null);
        Map.Entry<Integer, String> entry = Iterate.getFirst(biMap.entrySet());

        Assert.assertNotEquals(entry, new Object());
    }

    @Test
    public void entry_toString()
    {
        MutableBiMap<Integer, String> biMap = this.newMapWithKeyValue(1, "a");
        Map.Entry<Integer, String> entry = Iterate.getFirst(biMap.entrySet());

        Assert.assertEquals("1=a", entry.toString());
    }
}
