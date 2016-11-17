/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.immutable;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.impl.bimap.mutable.HashBiMap;
import org.eclipse.collections.impl.factory.BiMaps;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

public class ImmutableHashBiMapInverseTest extends AbstractImmutableBiMapTestCase
{
    @Override
    protected ImmutableBiMap<Integer, String> classUnderTest()
    {
        return BiMaps.immutable.with("1", 1, "2", 2, "3", 3, "4", 4).inverse();
    }

    @Override
    protected ImmutableBiMap<Integer, String> newEmpty()
    {
        return BiMaps.immutable.<String, Integer>empty().inverse();
    }

    @Override
    protected ImmutableBiMap<Integer, String> newWithMap()
    {
        return BiMaps.immutable.withAll(UnifiedMap.newWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4)).inverse();
    }

    @Override
    protected ImmutableBiMap<Integer, String> newWithHashBiMap()
    {
        return BiMaps.immutable.withAll(HashBiMap.newWithKeysValues("1", 1, "2", 2, "3", 3, "4", 4)).inverse();
    }

    @Override
    protected ImmutableBiMap<Integer, String> newWithImmutableMap()
    {
        return BiMaps.immutable.withAll(Maps.immutable.of("1", 1, "2", 2, "3", 3, "4", 4)).inverse();
    }
}
