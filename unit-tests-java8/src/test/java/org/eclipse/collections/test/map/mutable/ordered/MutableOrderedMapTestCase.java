/*
 * Copyright (c) 2018 Two Sigma.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.map.mutable.ordered;

import org.eclipse.collections.api.map.MutableOrderedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.test.MutableOrderedIterableTestCase;
import org.eclipse.collections.test.map.OrderedMapIterableTestCase;
import org.eclipse.collections.test.map.mutable.MutableMapIterableTestCase;
import org.junit.Test;

public interface MutableOrderedMapTestCase extends OrderedMapIterableTestCase, MutableMapIterableTestCase, MutableOrderedIterableTestCase
{
    @Override
    <T> MutableOrderedMap<Object, T> newWith(T... elements);

    @Override
    <K, V> MutableOrderedMap<K, V> newWithKeysValues(Object... elements);

    @Test
    default void newEmptyWithInitialCapacity()
    {
        MutableOrderedMap<Object, Integer> map = this.newWith(1, 1);
        MutableOrderedMap<Integer, Integer> newMap1 = map.newEmpty(5);
        Verify.assertEmpty(newMap1);
        MutableOrderedMap<String, String> newMap2 = newMap1.newEmpty(6);
        Verify.assertEmpty(newMap2);

        newMap1.put(1, 1);
        Verify.assertSize(1, newMap1);
        MutableOrderedMap<Double, Double> newMap3 = newMap1.newEmpty(7);
        Verify.assertEmpty(newMap3);

        newMap2.put("1", "1");
        Verify.assertSize(1, newMap2);

        newMap3.put(1.0, 1.0);
        newMap3.put(2.0, 2.0);
        newMap3.put(3.0, 3.0);
        Verify.assertSize(3, newMap3);
    }
}
