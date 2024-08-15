/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bimap.immutable;

import java.util.Random;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.impl.bimap.mutable.HashBiMap;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ImmutableHashBiMapInverseTest implements ImmutableBiMapTestCase
{
    private static final long CURRENT_TIME_MILLIS = System.currentTimeMillis();

    @Override
    public <T> ImmutableBiMap<Object, T> newWith(T... elements)
    {
        Random random = new Random(CURRENT_TIME_MILLIS);
        MutableBiMap<T, Object> result = new HashBiMap<>();
        for (T each : elements)
        {
            assertNull(result.put(each, random.nextDouble()));
        }
        return result.toImmutable().inverse();
    }

    @Override
    public <K, V> ImmutableBiMap<K, V> newWithKeysValues(Object... elements)
    {
        if (elements.length % 2 != 0)
        {
            fail(String.valueOf(elements.length));
        }

        MutableBiMap<V, K> result = new HashBiMap<>();
        for (int i = 0; i < elements.length; i += 2)
        {
            assertNull(result.put((V) elements[i + 1], (K) elements[i]));
        }
        return result.toImmutable().inverse();
    }
}
