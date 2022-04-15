/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;

public abstract class AbstractSynchronizedPutMultimap<K, V, C extends MutableCollection<V>> extends AbstractMutableMultimap<K, V, C>
{
    private final AtomicInteger atomicTotalSize = new AtomicInteger(0);

    protected AbstractSynchronizedPutMultimap()
    {
    }

    protected AbstractSynchronizedPutMultimap(MutableMap<K, C> newMap)
    {
        super(newMap);
    }

    @Override
    protected MutableMap<K, C> createMap()
    {
        return ConcurrentHashMap.newMap();
    }

    @Override
    protected MutableMap<K, C> createMapWithKeyCount(int keyCount)
    {
        return ConcurrentHashMap.newMap(keyCount);
    }

    @Override
    public int size()
    {
        return this.atomicTotalSize.get();
    }

    @Override
    protected void incrementTotalSize()
    {
        this.atomicTotalSize.incrementAndGet();
    }

    @Override
    protected void decrementTotalSize()
    {
        this.atomicTotalSize.decrementAndGet();
    }

    @Override
    protected void addToTotalSize(int value)
    {
        this.atomicTotalSize.addAndGet(value);
    }

    @Override
    protected void subtractFromTotalSize(int value)
    {
        this.atomicTotalSize.addAndGet(-value);
    }

    @Override
    protected void clearTotalSize()
    {
        this.atomicTotalSize.set(0);
    }

    @Override
    public boolean put(K key, V value)
    {
        MutableCollection<V> collection = this.getIfAbsentPutCollection(key);
        synchronized (collection)
        {
            if (collection.add(value))
            {
                this.incrementTotalSize();
                return true;
            }
            return false;
        }
    }

    @Override
    public MutableMultimap<K, V> withKeyMultiValues(K key, V... values)
    {
        Objects.requireNonNull(values);
        if (values.length > 0)
        {
            C existingValues = this.getIfAbsentPutCollection(key);
            synchronized (existingValues)
            {
                this.addAll(existingValues, values);
                return this;
            }
        }
        return this;
    }

    private void addAll(C existingValues, V[] values)
    {
        int currentSize = existingValues.size();
        int newSize = ArrayIterate.addAllTo(values, existingValues).size();
        this.addToTotalSize(newSize - currentSize);
    }

    private C getIfAbsentPutCollection(K key)
    {
        return this.map.getIfAbsentPutWith(key, this.createCollectionBlock(), this);
    }

    @Override
    public C getIfAbsentPutAll(K key, Iterable<? extends V> values)
    {
        if (Iterate.isEmpty(values))
        {
            return this.get(key);
        }

        C existingValues = this.getIfAbsentPutCollection(key);
        synchronized (existingValues)
        {
            if (existingValues.isEmpty())
            {
                int newSize = Iterate.addAllTo(values, existingValues).size();
                this.addToTotalSize(newSize);
            }

            return (C) existingValues.asUnmodifiable();
        }
    }
}
