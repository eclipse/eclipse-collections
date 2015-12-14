/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * An unmodifiable view of a Map.
 */
public class UnmodifiableMap<K, V> implements Map<K, V>, Serializable
{
    private static final long serialVersionUID = 1L;

    protected final Map<K, V> delegate;

    public UnmodifiableMap(Map<K, V> delegate)
    {
        if (delegate == null)
        {
            throw new NullPointerException();
        }
        this.delegate = delegate;
    }

    public int size()
    {
        return this.delegate.size();
    }

    public boolean isEmpty()
    {
        return this.delegate.isEmpty();
    }

    public boolean containsKey(Object key)
    {
        return this.delegate.containsKey(key);
    }

    public boolean containsValue(Object value)
    {
        return this.delegate.containsValue(value);
    }

    public V get(Object key)
    {
        return this.delegate.get(key);
    }

    public V put(K key, V value)
    {
        throw new UnsupportedOperationException("Cannot call put() on " + this.getClass().getSimpleName());
    }

    public V remove(Object key)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    public void putAll(Map<? extends K, ? extends V> t)
    {
        throw new UnsupportedOperationException("Cannot call putAll() on " + this.getClass().getSimpleName());
    }

    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    public Set<K> keySet()
    {
        return Collections.unmodifiableSet(this.delegate.keySet());
    }

    public Set<Map.Entry<K, V>> entrySet()
    {
        return Collections.unmodifiableMap(this.delegate).entrySet();
    }

    public Collection<V> values()
    {
        return Collections.unmodifiableCollection(this.delegate.values());
    }

    @Override
    public boolean equals(Object o)
    {
        return o == this || this.delegate.equals(o);
    }

    @Override
    public int hashCode()
    {
        return this.delegate.hashCode();
    }

    @Override
    public String toString()
    {
        return this.delegate.toString();
    }
}
