/*
 * Copyright (c) 2021 Two Sigma and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable.ordered;

import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.map.MutableOrderedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.test.MutableOrderedIterableTestCase;
import org.eclipse.collections.test.map.OrderedMapIterableTestCase;
import org.eclipse.collections.test.map.mutable.MutableMapIterableTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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

        assertThrows(NullPointerException.class, () -> map.removeAllKeys(null));
        assertFalse(map.removeAllKeys(Sets.mutable.empty()));
        assertFalse(map.removeAllKeys(Sets.mutable.with(4)));
        assertFalse(map.removeAllKeys(Sets.mutable.with(4, 5, 6)));
        assertFalse(map.removeAllKeys(Sets.mutable.with(4, 5, 6, 7, 8, 9)));

        assertTrue(map.removeAllKeys(Sets.mutable.with(1)));
        Verify.denyContainsKey(1, map);
        assertTrue(map.removeAllKeys(Sets.mutable.with(3, 4, 5, 6, 7)));
        Verify.denyContainsKey(3, map);

        map.putAll(Maps.mutable.with(4, "Four", 5, "Five", 6, "Six", 7, "Seven"));
        assertTrue(map.removeAllKeys(Sets.mutable.with(2, 3, 9, 10)));
        Verify.denyContainsKey(2, map);
        assertTrue(map.removeAllKeys(Sets.mutable.with(5, 3, 7, 8, 9)));
        assertEquals(this.newWithKeysValues(4, "Four", 6, "Six"), map);
    }
}
