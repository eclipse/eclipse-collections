/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
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

public abstract class ObjectBooleanHashMapKeySetTestCase
{
    public abstract MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1);

    public abstract MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1, String key2, boolean value2);

    public abstract MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1, String key2, boolean value2, String key3, boolean value3);

    public abstract MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1, String key2, boolean value2, String key3, boolean value3, String key4, boolean value4);

    public abstract MutableObjectBooleanMap<String> newEmptyMap();

    @Test
    public void add()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", true, "Two", false, "Three", true).keySet().add("Four"));
    }

    @Test
    public void addAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.newMapWithKeysValues("One", true, "Two", false, "Three", true).keySet().addAll(FastList.newListWith("Four")));
    }

    @Test
    public void contains()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true, null, false);
        Set<String> keySet = map.keySet();
        assertTrue(keySet.contains("One"));
        assertTrue(keySet.contains("Two"));
        assertTrue(keySet.contains("Three"));
        assertFalse(keySet.contains("Four"));
        assertTrue(keySet.contains(null));
        keySet.remove(null);
        assertFalse(keySet.contains(null));
        map.removeKey("One");
        assertFalse(keySet.contains("One"));
    }

    @Test
    public void containsAll()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true, null, false);
        Set<String> keySet = map.keySet();
        assertTrue(keySet.containsAll(FastList.newListWith("One", "Two")));
        assertTrue(keySet.containsAll(FastList.newListWith("One", "Two", "Three", null)));
        assertTrue(keySet.containsAll(FastList.newListWith(null, null)));
        assertFalse(keySet.containsAll(FastList.newListWith("One", "Four")));
        assertFalse(keySet.containsAll(FastList.newListWith("Five", "Four")));
        keySet.remove(null);
        assertFalse(keySet.containsAll(FastList.newListWith("One", "Two", "Three", null)));
        assertTrue(keySet.containsAll(FastList.newListWith("One", "Two", "Three")));
        map.removeKey("One");
        assertFalse(keySet.containsAll(FastList.newListWith("One", "Two")));
        assertTrue(keySet.containsAll(FastList.newListWith("Three", "Two")));
    }

    @Test
    public void isEmpty()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true, null, false);
        Set<String> keySet = map.keySet();
        assertFalse(keySet.isEmpty());
        MutableObjectBooleanMap<String> map1 = this.newEmptyMap();
        Set<String> keySet1 = map1.keySet();
        assertTrue(keySet1.isEmpty());
        map1.put("One", true);
        assertFalse(keySet1.isEmpty());
    }

    @Test
    public void size()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true, null, false);
        Set<String> keySet = map.keySet();
        Verify.assertSize(4, keySet);
        map.remove("One");
        Verify.assertSize(3, keySet);
        map.put("Five", true);
        Verify.assertSize(4, keySet);

        MutableObjectBooleanMap<String> map1 = this.newEmptyMap();
        Set<String> keySet1 = map1.keySet();
        Verify.assertSize(0, keySet1);
        map1.put(null, true);
        Verify.assertSize(1, keySet1);
    }

    @Test
    public void iterator()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true, null, false);
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();

        HashBag<String> expected = HashBag.newBagWith("One", "Two", "Three", null);
        HashBag<String> actual = HashBag.newBag();
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
        }

        assertFalse(iterator1.hasNext());
        Verify.assertEmpty(map);
        Verify.assertEmpty(keySet);
    }

    @Test
    public void removeFromKeySet()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true);
        assertFalse(map.keySet().remove("Four"));

        assertTrue(map.keySet().remove("Two"));
        assertEquals(this.newMapWithKeysValues("One", true, "Three", true), map);
        assertEquals(UnifiedSet.newSetWith("One", "Three"), map.keySet());
    }

    @Test
    public void removeNullFromKeySet()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true);
        assertFalse(map.keySet().remove(null));
        assertEquals(this.newMapWithKeysValues("One", true, "Two", false, "Three", true), map);
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());

        map.put(null, true);
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three", null), map.keySet());
        assertTrue(map.keySet().remove(null));
        assertEquals(this.newMapWithKeysValues("One", true, "Two", false, "Three", true), map);
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());
    }

    @Test
    public void removeAllFromKeySet()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true);
        assertFalse(map.keySet().removeAll(FastList.newListWith("Four")));
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());

        assertTrue(map.keySet().removeAll(FastList.newListWith("Two", "Four")));
        assertEquals(this.newMapWithKeysValues("One", true, "Three", true), map);
        assertEquals(UnifiedSet.newSetWith("One", "Three"), map.keySet());
    }

    @Test
    public void retainAllFromKeySet()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true);
        assertFalse(map.keySet().retainAll(FastList.newListWith("One", "Two", "Three", "Four")));
        assertEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());

        assertTrue(map.keySet().retainAll(FastList.newListWith("One", "Three")));
        assertEquals(this.newMapWithKeysValues("One", true, "Three", true), map);
        assertEquals(UnifiedSet.newSetWith("One", "Three"), map.keySet());
    }

    @Test
    public void clearKeySet()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true);
        map.keySet().clear();
        Verify.assertEmpty(map);
        Verify.assertEmpty(map.keySet());
    }

    @Test
    public void keySetEqualsAndHashCode()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true, null, false);
        Verify.assertEqualsAndHashCode(UnifiedSet.newSetWith("One", "Two", "Three", null), map.keySet());
        assertNotEquals(UnifiedSet.newSetWith("One", "Two", "Three"), map.keySet());
        assertNotEquals(FastList.newListWith("One", "Two", "Three", null), map.keySet());
    }

    @Test
    public void keySetToArray()
    {
        MutableObjectBooleanMap<String> map = this.newMapWithKeysValues("One", true, "Two", false, "Three", true);
        HashBag<String> expected = HashBag.newBagWith("One", "Two", "Three");
        Set<String> keySet = map.keySet();
        assertEquals(expected, HashBag.newBagWith(keySet.toArray()));
        assertEquals(expected, HashBag.newBagWith(keySet.toArray(new String[keySet.size()])));
        assertEquals(expected, HashBag.newBagWith(keySet.toArray(new String[0])));
        expected.add(null);
        assertEquals(expected, HashBag.newBagWith(keySet.toArray(new String[keySet.size() + 1])));
    }
}
