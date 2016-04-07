/*
 * Copyright (c) 2016 Goldman Sachs.
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
import org.eclipse.collections.impl.test.junit.Java8Runner;
import org.junit.runner.RunWith;

@RunWith(Java8Runner.class)
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
            if (result.put(each, random.nextDouble()) != null)
            {
                throw new IllegalStateException();
            }
        }
        return result.toImmutable().inverse();
    }
}
