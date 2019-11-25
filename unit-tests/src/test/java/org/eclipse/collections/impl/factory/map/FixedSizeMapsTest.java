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

import org.eclipse.collections.api.map.FixedSizeMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Key;
import org.junit.Assert;
import org.junit.Test;

public class FixedSizeMapsTest
{
    @Test
    public void of()
    {
        Assert.assertEquals(UnifiedMap.newMap(), FixedSizeMap.of());
        Verify.assertInstanceOf(FixedSizeMap.class, FixedSizeMap.of());
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), FixedSizeMap.of(1, 2));
        Verify.assertInstanceOf(FixedSizeMap.class, FixedSizeMap.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4), FixedSizeMap.of(1, 2, 3, 4));
        Verify.assertInstanceOf(FixedSizeMap.class, FixedSizeMap.of(1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 5, 6), FixedSizeMap.of(1, 2, 3, 4, 5, 6));
        Verify.assertInstanceOf(FixedSizeMap.class, FixedSizeMap.of(1, 2, 3, 4, 5, 6));

        FixedSizeMap<String, String> map1 = FixedSizeMap.of("key1", "value1");
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue("key1", "value1", map1);

        FixedSizeMap<String, String> map2 = FixedSizeMap.of("key1", "value1", "key2", "value2");
        Verify.assertSize(2, map2);
        Verify.assertContainsAllKeyValues(map2, "key1", "value1", "key2", "value2");

        FixedSizeMap<String, String> map3 = FixedSizeMap.of("key1", "value1", "key2", "value2", "key3", "value3");
        Verify.assertSize(3, map3);
        Verify.assertContainsAllKeyValues(map3, "key1", "value1", "key2", "value2", "key3", "value3");
    }

    @Test
    public void ofDuplicateKeys()
    {
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2), FixedSizeMap.of(1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2), FixedSizeMap.of(1, 2, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 1, 2), FixedSizeMap.of(1, 2, 3, 4, 1, 2));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 1, 2, 3, 4), FixedSizeMap.of(1, 2, 1, 2, 3, 4));
        Assert.assertEquals(UnifiedMap.newWithKeysValues(1, 2, 3, 4, 3, 4), FixedSizeMap.of(1, 2, 3, 4, 3, 4));
    }

    @Test
    public void ofDuplicates()
    {
        Assert.assertEquals(FixedSizeMap.of(0, 0), FixedSizeMap.of(0, 0, 0, 0));
        Assert.assertEquals(FixedSizeMap.of(0, 0), FixedSizeMap.of(0, 0, 0, 0, 0, 0));

        Assert.assertEquals(FixedSizeMap.of(0, 0, 1, 1), FixedSizeMap.of(1, 1, 0, 0, 0, 0));
        Assert.assertEquals(FixedSizeMap.of(0, 0, 2, 2), FixedSizeMap.of(0, 0, 2, 2, 0, 0));
        Assert.assertEquals(FixedSizeMap.of(0, 0, 3, 3), FixedSizeMap.of(0, 0, 0, 0, 3, 3));
    }

    @Test
    public void mapKeyPreservation()
    {
        Key key = new Key("key");

        Key duplicateKey1 = new Key("key");
        FixedSizeMap<Key, Integer> map1 = FixedSizeMap.of(key, 1, duplicateKey1, 2);
        Verify.assertSize(1, map1);
        Verify.assertContainsKeyValue(key, 2, map1);
        Assert.assertSame(key, map1.keysView().getFirst());

        Key duplicateKey2 = new Key("key");
        FixedSizeMap<Key, Integer> map2 = FixedSizeMap.of(key, 1, duplicateKey1, 2, duplicateKey2, 3);
        Verify.assertSize(1, map2);
        Verify.assertContainsKeyValue(key, 3, map2);
        Assert.assertSame(key, map2.keysView().getFirst());

        Key duplicateKey3 = new Key("key");
        FixedSizeMap<Key, Integer> map3 = FixedSizeMap.of(key, 1, new Key("not a dupe"), 2, duplicateKey3, 3);
        Verify.assertSize(2, map3);
        Verify.assertContainsAllKeyValues(map3, key, 3, new Key("not a dupe"), 2);
        Assert.assertSame(key, map3.keysView().detect(key::equals));
    }

    @Test
    public void emptyMap()
    {
        Assert.assertTrue(FixedSizeMap.of().isEmpty());
        Assert.assertSame(FixedSizeMap.of(), FixedSizeMap.of());
        Verify.assertPostSerializedIdentity(FixedSizeMap.of());
    }
}
