/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractMutableBiMapKeySetTestCase
{
    protected abstract MutableBiMap<String, Integer> newMapWithKeysValues(String key1, int value1, String key2, int value2, String key3, int value3);

    protected abstract MutableBiMap<String, Integer> newMapWithKeysValues(String key1, int value1, String key2, int value2, String key3, int value3, String key4, int value4);

    @Test
    public void add()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).keySet().add("Four"));
    }

    @Test
    public void addAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3).keySet().addAll(FastList.newListWith("Four")));
    }

    @Test
    public void contains()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3, null, 4);
        Set<String> keySet = map.keySet();
        assertTrue(keySet.contains("One"));
        assertTrue(keySet.contains("Two"));
        assertTrue(keySet.contains("Three"));
        assertFalse(keySet.contains("Four"));
        assertTrue(keySet.contains(null));
        keySet.remove(null);
        assertFalse(keySet.contains(null));
        map.remove("One");
        assertFalse(keySet.contains("One"));
    }

    @Test
    public void containsAll()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3, null, 4);
        Set<String> keySet = map.keySet();
        assertTrue(keySet.containsAll(FastList.newListWith("One", "Two")));
        assertTrue(keySet.containsAll(FastList.newListWith("One", "Two", "Three", null)));
        assertTrue(keySet.containsAll(FastList.newListWith(null, null)));
        assertFalse(keySet.containsAll(FastList.newListWith("One", "Four")));
        assertFalse(keySet.containsAll(FastList.newListWith("Five", "Four")));
        keySet.remove(null);
        assertFalse(keySet.containsAll(FastList.newListWith("One", "Two", "Three", null)));
        assertTrue(keySet.containsAll(FastList.newListWith("One", "Two", "Three")));
        map.remove("One");
        assertFalse(keySet.containsAll(FastList.newListWith("One", "Two")));
        assertTrue(keySet.containsAll(FastList.newListWith("Three", "Two")));
    }

    @Test
    public void isEmpty()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3, null, 4);
        Set<String> keySet = map.keySet();
        assertFalse(keySet.isEmpty());
        HashBiMap<String, Integer> map1 = HashBiMap.newMap();
        Set<String> keySet1 = map1.keySet();
        assertTrue(keySet1.isEmpty());
        map1.put("One", 1);
        assertFalse(keySet1.isEmpty());
    }

    @Test
    public void size()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3, null, 4);
        Set<String> keySet = map.keySet();
        Verify.assertSize(4, keySet);
        map.remove("One");
        Verify.assertSize(3, keySet);
        map.put("Five", 5);
        Verify.assertSize(4, keySet);

        HashBiMap<String, Integer> map1 = HashBiMap.newMap();
        Set<String> keySet1 = map1.keySet();
        Verify.assertSize(0, keySet1);
        map1.put(null, 1);
        Verify.assertSize(1, keySet1);
    }

    @Test
    public void iterator()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3, null, 4);
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();

        MutableBag<String> expected = HashBag.newBagWith("One", "Two", "Three", null);
        MutableBag<String> actual = HashBag.newBag();
        assertThrows(IllegalStateException.class, iterator::remove);
        for (int i = 0; i < 4; i++)
        {
            assertTrue(iterator.hasNext());
            actual.add(iterator.next());
        }
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals(expected, actual);

        Iterator<String> iterator1 = keySet.iterator();
        for (int i = 4; i > 0; i--)
        {
            assertTrue(iterator1.hasNext());
            iterator1.next();
            iterator1.remove();
            assertThrows(IllegalStateException.class, iterator1::remove);
            Verify.assertSize(i - 1, keySet);
            Verify.assertSize(i - 1, map);
            Verify.assertSize(i - 1, map.inverse());
        }

        assertFalse(iterator1.hasNext());
        Verify.assertEmpty(map);
        Verify.assertEmpty(map.inverse());
        Verify.assertEmpty(keySet);
    }

    @Test
    public void removeFromKeySet()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        assertFalse(map.keySet().remove("Four"));

        assertTrue(map.keySet().remove("Two"));
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Three", 3), map);
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Three", 3).inverse(), map.inverse());
        assertEquals(UnifiedSet.newSetWith("One", "Three"), map.keySet());
    }

    @Test
    public void removeNullFromKeySet()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        assertFalse(map.keySet().remove(null));
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Two", 2, "Three", 3), map);
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Two", 2, "Three", 3).inverse(), map.inverse());
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());

        map.put(null, 4);
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three", null), map.keySet());
        assertTrue(map.keySet().remove(null));
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Two", 2, "Three", 3), map);
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Two", 2, "Three", 3).inverse(), map.inverse());
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());
    }

    @Test
    public void removeAllFromKeySet()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        assertFalse(map.keySet().removeAll(FastList.newListWith("Four")));
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());

        assertTrue(map.keySet().removeAll(FastList.newListWith("Two", "Four")));
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Three", 3), map);
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Three", 3).inverse(), map.inverse());
        assertEquals(UnifiedSet.newSetWith("One", "Three"), map.keySet());
    }

    @Test
    public void retainAllFromKeySet()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        assertFalse(map.keySet().retainAll(FastList.newListWith("One", "Two", "Three", "Four")));
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());

        assertTrue(map.keySet().retainAll(FastList.newListWith("One", "Three")));
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Three", 3), map);
        assertEquals(HashBiMap.newWithKeysValues("One", 1, "Three", 3).inverse(), map.inverse());
        assertEquals(UnifiedSet.newSetWith("One", "Three"), map.keySet());
    }

    @Test
    public void clearKeySet()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        map.keySet().clear();
        Verify.assertEmpty(map);
        Verify.assertEmpty(map.inverse());
        Verify.assertEmpty(map.keySet());
    }

    @Test
    public void keySetEqualsAndHashCode()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3, null, 0);
        Verify.assertEqualsAndHashCode(UnifiedSet.newSetWith("One", "Two", "Three", null), map.keySet());
        assertNotEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());
        assertNotEquals(FastList.newListWith("One", "Two", "Three", null), map.keySet());
    }

    @Test
    public void keySetToArray()
    {
        MutableBiMap<String, Integer> map = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        MutableBag<String> expected = HashBag.newBagWith("One", "Two", "Three");
        Set<String> keySet = map.keySet();
        assertEquals(expected, HashBag.newBagWith(keySet.toArray()));
        assertEquals(expected, HashBag.newBagWith(keySet.toArray(new String[keySet.size()])));
        assertEquals(expected, HashBag.newBagWith(keySet.toArray(new String[0])));
        expected.add(null);
        assertEquals(expected, HashBag.newBagWith(keySet.toArray(new String[keySet.size() + 1])));
    }

    @Test
    public void serialization()
    {
        MutableBiMap<String, Integer> biMap = this.newMapWithKeysValues("One", 1, "Two", 2, "Three", 3);
        Verify.assertPostSerializedEqualsAndHashCode(biMap);
    }
}
