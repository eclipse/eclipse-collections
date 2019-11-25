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

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Key;
import org.junit.Assert;
import org.junit.Test;

public class ImmutableMapsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(UnifiedMap.newMap(), ImmutableMap.of());
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.of());
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), ImmutableMap.of(1, 2));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), ImmutableMap.of(1, 2, 3, 4));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), ImmutableMap.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.of(1, 2, 3, 4, 5, 6));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6, 7, 8), ImmutableMap.of(1, 2, 3, 4, 5, 6, 7, 8));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.of(1, 2, 3, 4, 5, 6, 7, 8));

        ImmutableMap<String, String> map1 = ImmutableMap.of("key1", "value1");
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue("key1", "value1", map1);

        ImmutableMap<String, String> map2 = ImmutableMap.of("key1", "value1", "key2", "value2");
        Verify.assertSize(2, map2);
        Verify.assertContainsAllKeyValues(map2, "key1", "value1", "key2", "value2");

        ImmutableMap<String, String> map3 = ImmutableMap.of("key1", "value1", "key2", "value2", "key3", "value3");
        Verify.assertSize(3, map3);
        Verify.assertContainsAllKeyValues(map3, "key1", "value1", "key2", "value2", "key3", "value3");

        ImmutableMap<String, String> map4 = ImmutableMap.of("key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");
        Verify.assertSize(4, map4);
        Verify.assertContainsAllKeyValues(map4, "key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");
    }

    @Test
    public void ofDuplicateKeys()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), ImmutableMap.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2), ImmutableMap.of(1, 2, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 1, 2), ImmutableMap.of(1, 2, 3, 4, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2, 3, 4), ImmutableMap.of(1, 2, 1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 3, 4), ImmutableMap.of(1, 2, 3, 4, 3, 4));
    }

    @Test
    public void ofAllMap()
    {
        Assert.assertEquals(Maps.fixedSize.of(1, "One"), ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One")));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos"), ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos")));

        Assert.assertEquals(Maps.fixedSize.of(1, "One", 2, "Dos", 3, "Drei"), ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei")));

        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro"), ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
        Verify.assertInstanceOf(ImmutableMap.class, ImmutableMap.ofAll(UnifiedMap.newWithKeysValues(1, "One", 2, "Dos", 3, "Drei", 4, "Quatro")));
    }

    @Test
    public void ofDuplicates()
    {
        Assert.assertEquals(ImmutableMap.of(0, 0), ImmutableMap.of(0, 0, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0), ImmutableMap.of(0, 0, 0, 0, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0), ImmutableMap.of(0, 0, 0, 0, 0, 0, 0, 0));

        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1), ImmutableMap.of(1, 1, 0, 0, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0, 2, 2), ImmutableMap.of(0, 0, 2, 2, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0, 3, 3), ImmutableMap.of(0, 0, 0, 0, 3, 3));

        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1), ImmutableMap.of(1, 1, 0, 0, 0, 0, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0, 2, 2), ImmutableMap.of(0, 0, 2, 2, 0, 0, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0, 3, 3), ImmutableMap.of(0, 0, 0, 0, 3, 3, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0, 4, 4), ImmutableMap.of(0, 0, 0, 0, 0, 0, 4, 4));

        Assert.assertEquals(ImmutableMap.of(0, 0, 2, 2, 3, 3, 4, 4), ImmutableMap.of(0, 0, 2, 2, 3, 3, 4, 4));
        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1, 3, 3, 4, 4), ImmutableMap.of(1, 1, 0, 0, 3, 3, 4, 4));
        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1, 2, 2, 4, 4), ImmutableMap.of(1, 1, 2, 2, 0, 0, 4, 4));
        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1, 2, 2, 3, 3), ImmutableMap.of(1, 1, 2, 2, 3, 3, 0, 0));

        Assert.assertEquals(ImmutableMap.of(0, 0, 3, 3, 4, 4), ImmutableMap.of(0, 0, 0, 0, 3, 3, 4, 4));
        Assert.assertEquals(ImmutableMap.of(0, 0, 2, 2, 4, 4), ImmutableMap.of(0, 0, 2, 2, 0, 0, 4, 4));
        Assert.assertEquals(ImmutableMap.of(0, 0, 2, 2, 3, 3), ImmutableMap.of(0, 0, 2, 2, 3, 3, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1, 4, 4), ImmutableMap.of(1, 1, 0, 0, 0, 0, 4, 4));
        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1, 3, 3), ImmutableMap.of(1, 1, 0, 0, 3, 3, 0, 0));
        Assert.assertEquals(ImmutableMap.of(0, 0, 1, 1, 2, 2), ImmutableMap.of(1, 1, 2, 2, 0, 0, 0, 0));
    }

    @Test
    public void mapKeyPreservation()
    {
        Key key = new Key("key");

        Key duplicateKey1 = new Key("key");
        ImmutableMap<Key, Integer> map1 = ImmutableMap.of(key, 1, duplicateKey1, 2);
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue(key, 2, map1);
        Assert.assertSame(key, map1.keysView().getFirst());

        Key duplicateKey2 = new Key("key");
        ImmutableMap<Key, Integer> map2 = ImmutableMap.of(key, 1, duplicateKey1, 2, duplicateKey2, 3);
        Verify.assertSize(1, map2);
        Verify.assertContainsKeyValue(key, 3, map2);
        Assert.assertSame(key, map2.keysView().getFirst());

        Key duplicateKey3 = new Key("key");
        ImmutableMap<Key, Integer> map3 = ImmutableMap.of(key, 1, new Key("not a dupe"), 2, duplicateKey3, 3);
        Verify.assertSize(2, map3);
        Verify.assertContainsAllKeyValues(map3, key, 3, new Key("not a dupe"), 2);
        Assert.assertSame(key, map3.keysView().detect(key::equals));
    }

    @Test
    public void emptyMap()
    {
        Assert.assertTrue(ImmutableMap.of().isEmpty());
        Assert.assertSame(ImmutableMap.of(), ImmutableMap.of());
        Verify.assertPostSerializedIdentity(ImmutableMap.of());
    }
}
