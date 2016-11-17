/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import java.lang.reflect.Field;

import org.eclipse.collections.api.factory.map.FixedSizeMapFactory;
import org.eclipse.collections.api.factory.map.ImmutableMapFactory;
import org.eclipse.collections.api.factory.map.sorted.MutableSortedMapFactory;
import org.eclipse.collections.api.map.FixedSizeMap;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Key;
import org.junit.Assert;
import org.junit.Test;

public class MapsTest
{
    @Test
    public void immutable()
    {
        ImmutableMapFactory factory = Maps.immutable;
        Assert.assertEquals(UnifiedMap.newMap(), factory.of());
        Verify.assertInstanceOf(ImmutableMap.class, factory.of());
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), factory.of(1, 2));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), factory.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), factory.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6, 7, 8), factory.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableMap.class, factory.of(1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void immutableWithDuplicateKeys()
    {
        ImmutableMapFactory factory = Maps.immutable;
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), factory.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2), factory.of(1, 2, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 1, 2), factory.of(1, 2, 3, 4, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2, 3, 4), factory.of(1, 2, 1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 3, 4), factory.of(1, 2, 3, 4, 3, 4));
    }

    @Test
    public void fixedSize()
    {
        FixedSizeMapFactory undertest = Maps.fixedSize;
        Assert.assertEquals(UnifiedMap.newMap(), undertest.of());
        Verify.assertInstanceOf(FixedSizeMap.class, undertest.of());
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), undertest.of(1, 2));
        Verify.assertInstanceOf(FixedSizeMap.class, undertest.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), undertest.of(1, 2, 3, 4));
        Verify.assertInstanceOf(FixedSizeMap.class, undertest.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), undertest.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(FixedSizeMap.class, undertest.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void fixedSizeWithDuplicateKeys()
    {
        FixedSizeMapFactory undertest = Maps.fixedSize;
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), undertest.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2), undertest.of(1, 2, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 1, 2), undertest.of(1, 2, 3, 4, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2, 3, 4), undertest.of(1, 2, 1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 3, 4), undertest.of(1, 2, 3, 4, 3, 4));
    }

    @Test
    public void copyMap()
    {
        Assert.assertEquals(Maps.fixedSize.of(1, "One"), Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One")));
        Verify.assertInstanceOf(ImmutableMap.class, Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos"), Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));
        Verify.assertInstanceOf(ImmutableMap.class, Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos", 3, "Drei"), Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));
        Verify.assertInstanceOf(ImmutableMap.class, Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));

        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro"), Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
        Verify.assertInstanceOf(ImmutableMap.class, Maps.immutable.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
    }

    @Test
    public void newMapWith()
    {
        ImmutableMap<String, String> map1 = Maps.immutable.of("key1", "value1");
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue("key1", "value1", map1);

        ImmutableMap<String, String> map2 = Maps.immutable.of("key1", "value1", "key2", "value2");
        Verify.assertSize(2, map2);
        Verify.assertContainsAllKeyValues(map2, "key1", "value1", "key2", "value2");

        ImmutableMap<String, String> map3 = Maps.immutable.of("key1", "value1", "key2", "value2", "key3", "value3");
        Verify.assertSize(3, map3);
        Verify.assertContainsAllKeyValues(map3, "key1", "value1", "key2", "value2", "key3", "value3");

        ImmutableMap<String, String> map4 = Maps.immutable.of("key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");
        Verify.assertSize(4, map4);
        Verify.assertContainsAllKeyValues(map4, "key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");
    }

    @Test
    public void duplicates()
    {
        Assert.assertEquals(Maps.immutable.of(0, 0), Maps.immutable.of(0, 0, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0), Maps.immutable.of(0, 0, 0, 0, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0), Maps.immutable.of(0, 0, 0, 0, 0, 0, 0, 0));

        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1), Maps.immutable.of(1, 1, 0, 0, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0, 2, 2), Maps.immutable.of(0, 0, 2, 2, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0, 3, 3), Maps.immutable.of(0, 0, 0, 0, 3, 3));

        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1), Maps.immutable.of(1, 1, 0, 0, 0, 0, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0, 2, 2), Maps.immutable.of(0, 0, 2, 2, 0, 0, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0, 3, 3), Maps.immutable.of(0, 0, 0, 0, 3, 3, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0, 4, 4), Maps.immutable.of(0, 0, 0, 0, 0, 0, 4, 4));

        Assert.assertEquals(Maps.immutable.of(0, 0, 2, 2, 3, 3, 4, 4), Maps.immutable.of(0, 0, 2, 2, 3, 3, 4, 4));
        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1, 3, 3, 4, 4), Maps.immutable.of(1, 1, 0, 0, 3, 3, 4, 4));
        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1, 2, 2, 4, 4), Maps.immutable.of(1, 1, 2, 2, 0, 0, 4, 4));
        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1, 2, 2, 3, 3), Maps.immutable.of(1, 1, 2, 2, 3, 3, 0, 0));

        Assert.assertEquals(Maps.immutable.of(0, 0, 3, 3, 4, 4), Maps.immutable.of(0, 0, 0, 0, 3, 3, 4, 4));
        Assert.assertEquals(Maps.immutable.of(0, 0, 2, 2, 4, 4), Maps.immutable.of(0, 0, 2, 2, 0, 0, 4, 4));
        Assert.assertEquals(Maps.immutable.of(0, 0, 2, 2, 3, 3), Maps.immutable.of(0, 0, 2, 2, 3, 3, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1, 4, 4), Maps.immutable.of(1, 1, 0, 0, 0, 0, 4, 4));
        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1, 3, 3), Maps.immutable.of(1, 1, 0, 0, 3, 3, 0, 0));
        Assert.assertEquals(Maps.immutable.of(0, 0, 1, 1, 2, 2), Maps.immutable.of(1, 1, 2, 2, 0, 0, 0, 0));
    }

    @Test
    public void mapKeyPreservation()
    {
        Key key = new Key("key");

        Key duplicateKey1 = new Key("key");
        ImmutableMap<Key, Integer> map1 = Maps.immutable.of(key, 1, duplicateKey1, 2);
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue(key, 2, map1);
        Assert.assertSame(key, map1.keysView().getFirst());

        Key duplicateKey2 = new Key("key");
        ImmutableMap<Key, Integer> map2 = Maps.immutable.of(key, 1, duplicateKey1, 2, duplicateKey2, 3);
        Verify.assertSize(1, map2);
        Verify.assertContainsKeyValue(key, 3, map2);
        Assert.assertSame(key, map2.keysView().getFirst());

        Key duplicateKey3 = new Key("key");
        ImmutableMap<Key, Integer> map3 = Maps.immutable.of(key, 1, new Key("not a dupe"), 2, duplicateKey3, 3);
        Verify.assertSize(2, map3);
        Verify.assertContainsAllKeyValues(map3, key, 3, new Key("not a dupe"), 2);
        Assert.assertSame(key, map3.keysView().detect(key::equals));
    }

    @Test
    public void sortedMaps()
    {
        MutableSortedMapFactory factory = SortedMaps.mutable;
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), factory.ofSortedMap(UnifiedMap.newWithKeysValues(1, 2, 3, 4)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(Maps.class);
    }

    @Test
    public void ofInitialCapacity()
    {
        MutableMap<String, String> map1 = Maps.mutable.ofInitialCapacity(0);
        this.assertPresizedMapSizeEquals(0, (UnifiedMap<String, String>) map1);

        MutableMap<String, String> map2 = Maps.mutable.ofInitialCapacity(5);
        this.assertPresizedMapSizeEquals(5, (UnifiedMap<String, String>) map2);

        MutableMap<String, String> map3 = Maps.mutable.ofInitialCapacity(20);
        this.assertPresizedMapSizeEquals(20, (UnifiedMap<String, String>) map3);

        Verify.assertThrows(IllegalArgumentException.class, () -> Maps.mutable.ofInitialCapacity(-12));
    }

    @Test
    public void withInitialCapacity()
    {
        MutableMap<String, String> map1 = Maps.mutable.withInitialCapacity(0);
        this.assertPresizedMapSizeEquals(0, (UnifiedMap<String, String>) map1);

        MutableMap<String, String> map2 = Maps.mutable.withInitialCapacity(2);
        this.assertPresizedMapSizeEquals(2, (UnifiedMap<String, String>) map2);

        MutableMap<String, String> map3 = Maps.mutable.withInitialCapacity(3);
        this.assertPresizedMapSizeEquals(3, (UnifiedMap<String, String>) map3);

        MutableMap<String, String> map4 = Maps.mutable.withInitialCapacity(14);
        this.assertPresizedMapSizeEquals(14, (UnifiedMap<String, String>) map4);

        MutableMap<String, String> map5 = Maps.mutable.withInitialCapacity(17);
        this.assertPresizedMapSizeEquals(17, (UnifiedMap<String, String>) map5);

        Verify.assertThrows(IllegalArgumentException.class, () -> Maps.mutable.ofInitialCapacity(-6));
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
}
