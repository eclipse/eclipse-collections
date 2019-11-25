/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory.map;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Key;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.Assert;
import org.junit.Test;

public class MutableMapsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(UnifiedMap.newMap(), MutableMap.of());
        Verify.assertInstanceOf(MutableMap.class, MutableMap.of());
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), MutableMap.of(1, 2));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), MutableMap.of(1, 2, 3, 4));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), MutableMap.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6, 7, 8), MutableMap.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.of(1, 2, 3, 4, 5, 6, 7, 8));

        MutableMap<String, String> map1 = MutableMap.of("key1", "value1");
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue("key1", "value1", map1);

        MutableMap<String, String> map2 = MutableMap.of("key1", "value1", "key2", "value2");
        Verify.assertSize(2, map2);
        Verify.assertContainsAllKeyValues(map2, "key1", "value1", "key2", "value2");

        MutableMap<String, String> map3 = MutableMap.of("key1", "value1", "key2", "value2", "key3", "value3");
        Verify.assertSize(3, map3);
        Verify.assertContainsAllKeyValues(map3, "key1", "value1", "key2", "value2", "key3", "value3");

        MutableMap<String, String> map4 = MutableMap.of("key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");
        Verify.assertSize(4, map4);
        Verify.assertContainsAllKeyValues(map4, "key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");
    }

    @Test
    public void ofDuplicateKeys()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), MutableMap.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2), MutableMap.of(1, 2, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 1, 2), MutableMap.of(1, 2, 3, 4, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2, 3, 4), MutableMap.of(1, 2, 1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 3, 4), MutableMap.of(1, 2, 3, 4, 3, 4));
    }

    @Test
    public void ofMap()
    {
        Assert.assertEquals(Maps.fixedSize.of(1, "One"), MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos"), MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos", 3, "Drei"), MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));

        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro"), MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMap(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
    }

    @Test
    public void ofMapIterable()
    {
        Assert.assertEquals(Maps.fixedSize.of(1, "One"), MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos"), MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos", 3, "Drei"), MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));

        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro"), MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
        Verify.assertInstanceOf(MutableMap.class, MutableMap.ofMapIterable(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
    }

    @Test
    public void ofDuplicates()
    {
        Assert.assertEquals(MutableMap.of(0, 0), MutableMap.of(0, 0, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0), MutableMap.of(0, 0, 0, 0, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0), MutableMap.of(0, 0, 0, 0, 0, 0, 0, 0));

        Assert.assertEquals(MutableMap.of(0, 0, 1, 1), MutableMap.of(1, 1, 0, 0, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0, 2, 2), MutableMap.of(0, 0, 2, 2, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0, 3, 3), MutableMap.of(0, 0, 0, 0, 3, 3));

        Assert.assertEquals(MutableMap.of(0, 0, 1, 1), MutableMap.of(1, 1, 0, 0, 0, 0, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0, 2, 2), MutableMap.of(0, 0, 2, 2, 0, 0, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0, 3, 3), MutableMap.of(0, 0, 0, 0, 3, 3, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0, 4, 4), MutableMap.of(0, 0, 0, 0, 0, 0, 4, 4));

        Assert.assertEquals(MutableMap.of(0, 0, 2, 2, 3, 3, 4, 4), MutableMap.of(0, 0, 2, 2, 3, 3, 4, 4));
        Assert.assertEquals(MutableMap.of(0, 0, 1, 1, 3, 3, 4, 4), MutableMap.of(1, 1, 0, 0, 3, 3, 4, 4));
        Assert.assertEquals(MutableMap.of(0, 0, 1, 1, 2, 2, 4, 4), MutableMap.of(1, 1, 2, 2, 0, 0, 4, 4));
        Assert.assertEquals(MutableMap.of(0, 0, 1, 1, 2, 2, 3, 3), MutableMap.of(1, 1, 2, 2, 3, 3, 0, 0));

        Assert.assertEquals(MutableMap.of(0, 0, 3, 3, 4, 4), MutableMap.of(0, 0, 0, 0, 3, 3, 4, 4));
        Assert.assertEquals(MutableMap.of(0, 0, 2, 2, 4, 4), MutableMap.of(0, 0, 2, 2, 0, 0, 4, 4));
        Assert.assertEquals(MutableMap.of(0, 0, 2, 2, 3, 3), MutableMap.of(0, 0, 2, 2, 3, 3, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0, 1, 1, 4, 4), MutableMap.of(1, 1, 0, 0, 0, 0, 4, 4));
        Assert.assertEquals(MutableMap.of(0, 0, 1, 1, 3, 3), MutableMap.of(1, 1, 0, 0, 3, 3, 0, 0));
        Assert.assertEquals(MutableMap.of(0, 0, 1, 1, 2, 2), MutableMap.of(1, 1, 2, 2, 0, 0, 0, 0));
    }

    @Test
    public void mapKeyPreservation()
    {
        Key key = new Key("key");

        Key duplicateKey1 = new Key("key");
        MutableMap<Key, Integer> map1 = MutableMap.of(key, 1, duplicateKey1, 2);
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue(key, 2, map1);
        Assert.assertSame(key, map1.keysView().getFirst());

        Key duplicateKey2 = new Key("key");
        MutableMap<Key, Integer> map2 = MutableMap.of(key, 1, duplicateKey1, 2, duplicateKey2, 3);
        Verify.assertSize(1, map2);
        Verify.assertContainsKeyValue(key, 3, map2);
        Assert.assertSame(key, map2.keysView().getFirst());

        Key duplicateKey3 = new Key("key");
        MutableMap<Key, Integer> map3 = MutableMap.of(key, 1, new Key("not a dupe"), 2, duplicateKey3, 3);
        Verify.assertSize(2, map3);
        Verify.assertContainsAllKeyValues(map3, key, 3, new Key("not a dupe"), 2);
        Assert.assertSame(key, map3.keysView().detect(key::equals));
    }

    @Test
    public void ofInitialCapacity()
    {
        MutableMap<String, String> map1 = MutableMap.ofInitialCapacity(0);
        this.assertPresizedMapSizeEquals(0, (UnifiedMap<String, String>) map1);

        MutableMap<String, String> map2 = MutableMap.ofInitialCapacity(5);
        this.assertPresizedMapSizeEquals(5, (UnifiedMap<String, String>) map2);

        MutableMap<String, String> map3 = MutableMap.ofInitialCapacity(20);
        this.assertPresizedMapSizeEquals(20, (UnifiedMap<String, String>) map3);

        Verify.assertThrows(IllegalArgumentException.class, () -> MutableMap.ofInitialCapacity(-12));
    }

    private void assertPresizedMapSizeEquals(int initialCapacity, UnifiedMap<String, String> map)
    {
        try
        {
            Field tableField = UnifiedMap.class.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] table = (Object[]) tableField.get(map);

            int size = (int) Math.ceil(initialCapacity / 0.75);
            int capacity = 1;
            while (capacity < size)
            {
                capacity <<= 1;
            }
            capacity <<= 1;

            Assert.assertEquals(capacity, table.length);
        }
        catch (SecurityException ignored)
        {
            Assert.fail("Unable to modify the visibility of the table on UnifiedMap");
        }
        catch (NoSuchFieldException ignored)
        {
            Assert.fail("No field named table UnifiedMap");
        }
        catch (IllegalAccessException ignored)
        {
            Assert.fail("No access the field table in UnifiedMap");
        }
    }

    @Test
    public void ofAll()
    {
        Assert.assertEquals(UnifiedMap.newMap(), MutableMap.ofMap(new HashMap<>()));
        Verify.assertInstanceOf(UnifiedMap.class, MutableMap.ofMap(new HashMap<>()));

        Assert.assertEquals(UnifiedMap.newMap(), MutableMap.ofMapIterable(Maps.immutable.with()));
        Verify.assertInstanceOf(UnifiedMap.class, MutableMap.ofMapIterable(Maps.immutable.with()));

        Assert.assertEquals(UnifiedMap.newMapWith(Tuples.pair(1, 1)), MutableMap.ofMap(MutableMap.of(1, 1)));
        Verify.assertInstanceOf(UnifiedMap.class, MutableMap.ofMap(MutableMap.of(1, 1)));

        Assert.assertEquals(UnifiedMap.newMapWith(Tuples.pair(1, 1)), MutableMap.ofMapIterable(MutableMap.of(1, 1)));
        Verify.assertInstanceOf(UnifiedMap.class, MutableMap.ofMapIterable(MutableMap.of(1, 1)));
    }
}
