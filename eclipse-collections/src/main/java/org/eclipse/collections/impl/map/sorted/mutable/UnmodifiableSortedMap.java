/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.sorted.mutable;

import java.util.Comparator;
import java.util.SortedMap;

import org.eclipse.collections.impl.UnmodifiableMap;

/**
 * An unmodifiable view of a SortedMap.
 */
public class UnmodifiableSortedMap<K, V> extends UnmodifiableMap<K, V> implements SortedMap<K, V>
{
    private static final long serialVersionUID = 1L;

    public UnmodifiableSortedMap(SortedMap<K, V> delegate)
    {
        super(delegate);
    }

    protected SortedMap<K, V> getSortedMap()
    {
        return (SortedMap<K, V>) this.delegate;
    }

    @Override
    public Comparator<? super K> comparator()
    {
        return this.getSortedMap().comparator();
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey)
    {
        return UnmodifiableTreeMap.of(this.getSortedMap().subMap(fromKey, toKey));
    }

    @Override
    public SortedMap<K, V> headMap(K toKey)
    {
        return UnmodifiableTreeMap.of(this.getSortedMap().headMap(toKey));
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey)
    {
        return UnmodifiableTreeMap.of(this.getSortedMap().tailMap(fromKey));
    }

    @Override
    public K firstKey()
    {
        return this.getSortedMap().firstKey();
    }

    @Override
    public K lastKey()
    {
        return this.getSortedMap().lastKey();
    }
}
