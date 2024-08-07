/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import java.util.Collections;
import java.util.Map;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.factory.bimap.ImmutableBiMapFactory;
import org.eclipse.collections.api.factory.bimap.MutableBiMapFactory;
import org.eclipse.collections.impl.bimap.mutable.HashBiMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BiMapsTest
{
    @Test
    public void immutable()
    {
        ImmutableBiMapFactory factory = BiMaps.immutable;
        assertEquals(HashBiMap.newMap(), factory.of());
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.of());
        assertEquals(HashBiMap.newWithKeysValues(1, "2"), factory.of(1, "2"));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.of(1, "2"));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4"), factory.of(1, "2", 3, "4"));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.of(1, "2", 3, "4"));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6"), factory.of(1, "2", 3, "4", 5, "6"));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.of(1, "2", 3, "4", 5, "6"));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8"), factory.of(1, "2", 3, "4", 5, "6", 7, "8"));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.of(1, "2", 3, "4", 5, "6", 7, "8"));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8"), factory.ofAll(UnifiedMap.newMapWith(Tuples.pair(1, "2"), Tuples.pair(3, "4"), Tuples.pair(5, "6"), Tuples.pair(7, "8"))));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.ofAll(UnifiedMap.newMapWith(Tuples.pair(1, "2"), Tuples.pair(3, "4"), Tuples.pair(5, "6"), Tuples.pair(7, 8))));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8"), factory.ofAll(Maps.immutable.ofAll(UnifiedMap.newMapWith(Tuples.pair(1, "2"), Tuples.pair(3, "4"), Tuples.pair(5, "6"), Tuples.pair(7, "8")))));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.ofAll(Maps.immutable.ofAll(UnifiedMap.newMapWith(Tuples.pair(1, "2"), Tuples.pair(3, "4"), Tuples.pair(5, "6"), Tuples.pair(7, "8")))));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8"), factory.ofAll(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8")));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.ofAll(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8")));
        Map<Integer, String> map1 = HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8");
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8"), factory.ofAll(map1));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.ofAll(map1));
        ImmutableBiMap<Integer, String> map2 = BiMaps.immutable.with(1, "2", 3, "4", 5, "6", 7, "8");
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8"), factory.ofAll(map2.castToMap()));
        Verify.assertInstanceOf(ImmutableBiMap.class, factory.ofAll(map2.castToMap()));
    }

    @Test
    public void mutable()
    {
        MutableBiMapFactory factory = BiMaps.mutable;
        assertEquals(HashBiMap.newMap(), factory.of());
        Verify.assertInstanceOf(MutableBiMap.class, factory.of());
        assertEquals(HashBiMap.newWithKeysValues(1, "2"), factory.of(1, "2"));
        Verify.assertInstanceOf(MutableBiMap.class, factory.of(1, "2"));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4"), factory.of(1, "2", 3, "4"));
        Verify.assertInstanceOf(MutableBiMap.class, factory.of(1, "2", 3, "4"));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6"), factory.of(1, "2", 3, "4", 5, "6"));
        Verify.assertInstanceOf(MutableBiMap.class, factory.of(1, "2", 3, "4", 5, "6"));
        assertEquals(HashBiMap.newWithKeysValues(1, "2", 3, "4", 5, "6", 7, "8"), factory.of(1, "2", 3, "4", 5, "6", 7, "8"));
        Verify.assertInstanceOf(MutableBiMap.class, factory.of(1, "2", 3, "4", 5, "6", 7, "8"));
    }

    @Test
    public void withAllEmptyImmutableSame()
    {
        ImmutableBiMap<Integer, Integer> empty = BiMaps.immutable.withAll(Collections.emptyMap());
        Map<Integer, Integer> integers =
                BiMaps.immutable.<Integer, Integer>empty().newWithMap(Maps.mutable.empty()).castToMap();
        ImmutableBiMap<Integer, Integer> empty2 = BiMaps.immutable.withAll(integers);
        assertSame(BiMaps.immutable.empty(), empty);
        assertSame(BiMaps.immutable.empty(), empty2);
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(BiMaps.class);
    }
}
