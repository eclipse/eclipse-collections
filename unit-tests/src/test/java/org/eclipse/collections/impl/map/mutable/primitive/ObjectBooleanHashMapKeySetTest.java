/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.map.mutable.primitive;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for {@link ObjectBooleanHashMap#keySet()}.
 */
public class ObjectBooleanHashMapKeySetTest extends ObjectBooleanHashMapKeySetTestCase
{
    @Override
    public MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1);
    }

    @Override
    public MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1, String key2, boolean value2)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2);
    }

    @Override
    public MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1, String key2, boolean value2, String key3, boolean value3)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2, key3, value3);
    }

    @Override
    public MutableObjectBooleanMap<String> newMapWithKeysValues(String key1, boolean value1, String key2, boolean value2, String key3, boolean value3, String key4, boolean value4)
    {
        return ObjectBooleanHashMap.newWithKeysValues(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    @Override
    public MutableObjectBooleanMap<String> newEmptyMap()
    {
        return ObjectBooleanHashMap.newMap();
    }
}
