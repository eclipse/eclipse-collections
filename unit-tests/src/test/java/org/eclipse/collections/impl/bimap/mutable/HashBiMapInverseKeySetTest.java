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

public class HashBiMapInverseKeySetTest extends AbstractMutableBiMapKeySetTestCase
{
    @Override
    protected MutableBiMap<String, Integer> newMapWithKeysValues(String key1, int value1, String key2, int value2, String key3, int value3)
    {
        return HashBiMap.newWithKeysValues(value1, key1, value2, key2, value3, key3).inverse();
    }

    @Override
    protected MutableBiMap<String, Integer> newMapWithKeysValues(String key1, int value1, String key2, int value2, String key3, int value3, String key4, int value4)
    {
        return HashBiMap.newWithKeysValues(value1, key1, value2, key2, value3, key3, value4, key4).inverse();
    }
}
