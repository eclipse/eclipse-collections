/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.map.fixed;

import org.eclipse.collections.api.factory.map.FixedSizeMapFactory;
import org.eclipse.collections.api.map.FixedSizeMap;
import org.eclipse.collections.impl.block.factory.Comparators;
import net.jcip.annotations.Immutable;

@Immutable
public class FixedSizeMapFactoryImpl implements FixedSizeMapFactory
{
    private static final FixedSizeMap<?, ?> EMPTY_MAP = new EmptyMap<Object, Object>();

    public <K, V> FixedSizeMap<K, V> empty()
    {
        return (FixedSizeMap<K, V>) EMPTY_MAP;
    }

    public <K, V> FixedSizeMap<K, V> of()
    {
        return this.empty();
    }

    public <K, V> FixedSizeMap<K, V> with()
    {
        return this.empty();
    }

    public <K, V> FixedSizeMap<K, V> of(K key, V value)
    {
        return this.with(key, value);
    }

    public <K, V> FixedSizeMap<K, V> with(K key, V value)
    {
        return new SingletonMap<K, V>(key, value);
    }

    public <K, V> FixedSizeMap<K, V> of(K key1, V value1, K key2, V value2)
    {
        return this.with(key1, value1, key2, value2);
    }

    public <K, V> FixedSizeMap<K, V> with(K key1, V value1, K key2, V value2)
    {
        if (Comparators.nullSafeEquals(key1, key2))
        {
            return this.of(key1, value2);
        }
        return new DoubletonMap<K, V>(key1, value1, key2, value2);
    }

    public <K, V> FixedSizeMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return this.with(key1, value1, key2, value2, key3, value3);
    }

    public <K, V> FixedSizeMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        if (Comparators.nullSafeEquals(key1, key2) && Comparators.nullSafeEquals(key2, key3))
        {
            return this.of(key1, value3);
        }
        if (Comparators.nullSafeEquals(key1, key2))
        {
            return this.of(key1, value2, key3, value3);
        }
        if (Comparators.nullSafeEquals(key1, key3))
        {
            return this.of(key2, value2, key1, value3);
        }
        if (Comparators.nullSafeEquals(key2, key3))
        {
            return this.of(key1, value1, key2, value3);
        }
        return new TripletonMap<K, V>(key1, value1, key2, value2, key3, value3);
    }
}
