/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.immutable;

import java.util.Comparator;
import java.util.SortedMap;

import org.eclipse.collections.api.factory.map.sorted.ImmutableSortedMapFactory;
import org.eclipse.collections.api.map.sorted.ImmutableSortedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;

public enum ImmutableSortedMapFactoryImpl implements ImmutableSortedMapFactory
{
    INSTANCE;

    @Override
    public <K, V> ImmutableSortedMap<K, V> empty()
    {
        return (ImmutableSortedMap<K, V>) ImmutableEmptySortedMap.INSTANCE;
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of()
    {
        return this.empty();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with()
    {
        return this.empty();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(K key, V value)
    {
        return this.with(key, value);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(K key, V value)
    {
        return TreeSortedMap.newMapWith(key, value).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(K key1, V value1, K key2, V value2)
    {
        return this.with(key1, value1, key2, value2);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(K key1, V value1, K key2, V value2)
    {
        return TreeSortedMap.newMapWith(key1, value1, key2, value2).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return this.with(key1, value1, key2, value2, key3, value3);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return TreeSortedMap.newMapWith(key1, value1, key2, value2, key3, value3).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return this.with(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return TreeSortedMap.newMapWith(key1, value1, key2, value2, key3, value3, key4, value4).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(Comparator<? super K> comparator)
    {
        return this.with(comparator);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(Comparator<? super K> comparator)
    {
        if (comparator == null)
        {
            return this.of();
        }
        return new ImmutableEmptySortedMap<>(comparator);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(Comparator<? super K> comparator, K key, V value)
    {
        return this.with(comparator, key, value);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(Comparator<? super K> comparator, K key, V value)
    {
        return TreeSortedMap.<K, V>newMap(comparator).with(key, value).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(Comparator<? super K> comparator, K key1, V value1, K key2, V value2)
    {
        return this.with(comparator, key1, value1, key2, value2);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(Comparator<? super K> comparator, K key1, V value1, K key2, V value2)
    {
        return TreeSortedMap.<K, V>newMap(comparator).with(key1, value1, key2, value2).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return this.with(comparator, key1, value1, key2, value2, key3, value3);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return TreeSortedMap.<K, V>newMap(comparator).with(key1, value1, key2, value2, key3, value3).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> of(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return this.with(comparator, key1, value1, key2, value2, key3, value3, key4, value4);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> with(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        return TreeSortedMap.<K, V>newMap(comparator).with(key1, value1, key2, value2, key3, value3, key4, value4).toImmutable();
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> ofSortedMap(SortedMap<K, V> map)
    {
        return this.withSortedMap(map);
    }

    @Override
    public <K, V> ImmutableSortedMap<K, V> withSortedMap(SortedMap<K, V> map)
    {
        if (map instanceof ImmutableSortedMap)
        {
            return (ImmutableSortedMap<K, V>) map;
        }
        if (map.isEmpty())
        {
            return this.of(map.comparator());
        }
        return ImmutableTreeMap.newMap(map);
    }
}
