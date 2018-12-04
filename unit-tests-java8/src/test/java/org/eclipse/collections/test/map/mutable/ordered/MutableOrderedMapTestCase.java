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

import org.eclipse.collections.api.map.MutableMapIterable;
import org.eclipse.collections.api.map.MutableOrderedMap;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Maps;
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
    default void MutableOrderedMap_removeIf()
    {
        MutableOrderedMap<Integer, String> map = this.newWithKeysValues(1, "1", 2, "Two", 3, "Three");

        Assert.assertFalse(map.removeIf(Predicates2.alwaysFalse()));
        Assert.assertEquals(this.newWithKeysValues(1, "1", 2, "Two", 3, "Three"), map);
        Assert.assertTrue(map.removeIf(Predicates2.alwaysTrue()));
        Verify.assertEmpty(map);

        map.putAll(Maps.mutable.with(1, "One", 2, "TWO", 3, "THREE", 4, "four"));
        map.putAll(Maps.mutable.with(5, "Five", 6, "Six", 7, "Seven", 8, "Eight"));
        Assert.assertTrue(map.removeIf((each, value) -> each % 2 == 0 && value.length() < 4));
        Verify.denyContainsKey(2, map);
        Verify.denyContainsKey(6, map);
        MutableMapIterable<Integer, String> expected = this.newWithKeysValues(1, "One", 3, "THREE", 4, "four", 5, "Five");
        expected.put(7, "Seven");
        expected.put(8, "Eight");
        Assert.assertEquals(expected, map);

        Assert.assertTrue(map.removeIf((each, value) -> each % 2 != 0 && value.equals("THREE")));
        Verify.denyContainsKey(3, map);
        Verify.assertSize(5, map);

        Assert.assertTrue(map.removeIf((each, value) -> each % 2 != 0));
        Assert.assertFalse(map.removeIf((each, value) -> each % 2 != 0));
        Assert.assertEquals(this.newWithKeysValues(4, "four", 8, "Eight"), map);
    }
}
