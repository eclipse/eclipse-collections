/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.mutable;

import org.eclipse.collections.api.bimap.MutableBiMap;

public class HashBiMapInverseValuesTest extends AbstractMutableBiMapValuesTestCase
{
    @Override
    public MutableBiMap<Float, String> newMapWithKeysValues(float key1, String value1, float key2, String value2)
    {
        return HashBiMap.newWithKeysValues(value1, key1, value2, key2).inverse();
    }

    @Override
    public MutableBiMap<Float, Integer> newMapWithKeysValues(float key1, Integer value1, float key2, Integer value2, float key3, Integer value3)
    {
        return HashBiMap.newWithKeysValues(value1, key1, value2, key2, value3, key3).inverse();
    }

    @Override
    public MutableBiMap<Float, Integer> newMapWithKeysValues(float key1, Integer value1, float key2, Integer value2, float key3, Integer value3, float key4, Integer value4)
    {
        return HashBiMap.newWithKeysValues(value1, key1, value2, key2, value3, key3, value4, key4).inverse();
    }
}
