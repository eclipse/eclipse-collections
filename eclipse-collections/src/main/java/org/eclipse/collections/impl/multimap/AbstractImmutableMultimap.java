/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.multimap;

import java.io.InvalidClassException;
import java.io.ObjectStreamException;
import java.util.Collection;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.collection.ImmutableCollection;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.ImmutableMultimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.utility.Iterate;

public abstract class AbstractImmutableMultimap<K, V, C extends ImmutableCollection<V>>
        extends AbstractMultimap<K, V, C>
        implements ImmutableMultimap<K, V>
{
    protected final ImmutableMap<K, C> map;

    /**
     * Creates a new multimap that clones the provided map into an ImmutableMap.
     *
     * @param map place to store the mapping from each key to its corresponding values
     */
    protected AbstractImmutableMultimap(MutableMap<K, C> map)
    {
        this(map.toImmutable());
    }

    /**
     * Creates a new multimap that uses the provided immutableMap.
     *
     * @param immutableMap place to store the mapping from each key to its corresponding values
     */
    protected AbstractImmutableMultimap(ImmutableMap<K, C> immutableMap)
    {
        this.map = immutableMap;
    }

    @Override
    protected ImmutableMap<K, C> getMap()
    {
        return this.map;
    }

// Query Operations

    public int size()
    {
        class CountProcedure implements Procedure<C>
        {
            private static final long serialVersionUID = 1L;

            private int totalSize;

            public void value(C collection)
            {
                this.totalSize += collection.size();
            }

            public int getTotalSize()
            {
                return this.totalSize;
            }
        }

        CountProcedure procedure = new CountProcedure();
        this.map.forEachValue(procedure);
        return procedure.getTotalSize();
    }

    public int sizeDistinct()
    {
        return this.map.size();
    }

    public boolean isEmpty()
    {
        return this.map.isEmpty();
    }

    // Views

    public C get(K key)
    {
        return this.map.getIfAbsentWith(key, this.createCollectionBlock(), this);
    }

    public MutableMap<K, RichIterable<V>> toMap()
    {
        return (MutableMap<K, RichIterable<V>>) (MutableMap<?, ?>) this.map.toMap();
    }

    public <R extends Collection<V>> MutableMap<K, R> toMap(final Function0<R> collectionFactory)
    {
        final MutableMap<K, R> result = UnifiedMap.newMap();
        this.map.forEachKeyValue(new Procedure2<K, C>()
        {
            public void value(K key, C iterable)
            {
                R newCollection = collectionFactory.value();
                Iterate.addAllTo(iterable, newCollection);
                result.put(key, newCollection);
            }
        });

        return result;
    }

    public ImmutableMultimap<K, V> newWith(K key, V value)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.put(key, value);
        return mutableMultimap.toImmutable();
    }

    public ImmutableMultimap<K, V> newWithout(Object key, Object value)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.remove(key, value);
        return mutableMultimap.toImmutable();
    }

    public ImmutableMultimap<K, V> newWithAll(K key, Iterable<? extends V> values)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.putAll(key, values);
        return mutableMultimap.toImmutable();
    }

    public ImmutableMultimap<K, V> newWithoutAll(Object key)
    {
        MutableMultimap<K, V> mutableMultimap = this.toMutable();
        mutableMultimap.removeAll(key);
        return mutableMultimap.toImmutable();
    }

    public ImmutableMultimap<K, V> toImmutable()
    {
        return this;
    }

    protected Object readResolve() throws ObjectStreamException
    {
        throw new InvalidClassException("You should be using the proxy for serialization of ImmutableMultimaps");
    }
}
