/*
 * Copyright (c) 2019 Two Sigma and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable.ordered;

import org.eclipse.collections.api.map.MutableOrderedMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.test.MutableOrderedIterableTestCase;
import org.eclipse.collections.test.map.OrderedMapIterableTestCase;
import org.eclipse.collections.test.map.mutable.MutableMapIterableTestCase;
import org.junit.Assert;
import org.junit.Test;

public interface MutableOrderedMapTestCase extends OrderedMapIterableTestCase, MutableMapIterableTestCase, MutableOrderedIterableTestCase
{
    @Override
    <T> MutableOrderedMap<Object, T> newWith(T... elements);

    @Override
    <K, V> MutableOrderedMap<K, V> newWithKeysValues(Object... elements);

    @Test
    default void MutableOrderedMap_removeAllKeys()
    {
        MutableOrderedMap<Integer, String> map = this.newWithKeysValues(1, "1", 2, "Two", 3, "Three");

        Assert.assertThrows(NullPointerException.class, () -> map.removeAllKeys(null));
        Assert.assertFalse(map.removeAllKeys(Sets.mutable.empty()));
        Assert.assertFalse(map.removeAllKeys(Sets.mutable.with(4)));
        Assert.assertFalse(map.removeAllKeys(Sets.mutable.with(4, 5, 6)));
        Assert.assertFalse(map.removeAllKeys(Sets.mutable.with(4, 5, 6, 7, 8, 9)));

        Assert.assertTrue(map.removeAllKeys(Sets.mutable.with(1)));
        Verify.denyContainsKey(1, map);
        Assert.assertTrue(map.removeAllKeys(Sets.mutable.with(3, 4, 5, 6, 7)));
        Verify.denyContainsKey(3, map);

        map.putAll(Maps.mutable.with(4, "Four", 5, "Five", 6, "Six", 7, "Seven"));
        Assert.assertTrue(map.removeAllKeys(Sets.mutable.with(2, 3, 9, 10)));
        Verify.denyContainsKey(2, map);
        Assert.assertTrue(map.removeAllKeys(Sets.mutable.with(5, 3, 7, 8, 9)));
        Assert.assertEquals(this.newWithKeysValues(4, "Four", 6, "Six"), map);
    }
}
