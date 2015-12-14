/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.list;

import java.io.Externalizable;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.AbstractSynchronizedPutMultimap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * A Multimap that is optimized for parallel writes, but is not protected for concurrent reads.
 */
public final class SynchronizedPutFastListMultimap<K, V>
        extends AbstractSynchronizedPutMultimap<K, V, MutableList<V>>
        implements MutableListMultimap<K, V>, Externalizable
{
    private static final long serialVersionUID = 42L;

    public SynchronizedPutFastListMultimap()
    {
    }

    public SynchronizedPutFastListMultimap(int initialCapacity)
    {
        super(ConcurrentHashMap.<K, MutableList<V>>newMap(initialCapacity));
    }

    public SynchronizedPutFastListMultimap(Multimap<? extends K, ? extends V> multimap)
    {
        this.putAll(multimap);
    }

    public SynchronizedPutFastListMultimap(Pair<K, V>... pairs)
    {
        this();
        ArrayIterate.forEach(pairs, new Procedure<Pair<K, V>>()
        {
            public void value(Pair<K, V> pair)
            {
                SynchronizedPutFastListMultimap.this.put(pair.getOne(), pair.getTwo());
            }
        });
    }

    public SynchronizedPutFastListMultimap(Iterable<Pair<K, V>> inputIterable)
    {
        this();
        Iterate.forEach(inputIterable, new Procedure<Pair<K, V>>()
        {
            public void value(Pair<K, V> pair)
            {
                SynchronizedPutFastListMultimap.this.add(pair);
            }
        });
    }

    public static <K, V> SynchronizedPutFastListMultimap<K, V> newMultimap()
    {
        return new SynchronizedPutFastListMultimap<K, V>();
    }

    public static <K, V> SynchronizedPutFastListMultimap<K, V> newMultimap(int initialCapacity, float loadFactor, int concurrencyLevel)
    {
        return new SynchronizedPutFastListMultimap<K, V>(initialCapacity);
    }

    public static <K, V> SynchronizedPutFastListMultimap<K, V> newMultimap(Multimap<? extends K, ? extends V> multimap)
    {
        return new SynchronizedPutFastListMultimap<K, V>(multimap);
    }

    public static <K, V> SynchronizedPutFastListMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return new SynchronizedPutFastListMultimap<K, V>(pairs);
    }

    public static <K, V> SynchronizedPutFastListMultimap<K, V> newMultimap(Iterable<Pair<K, V>> inputIterable)
    {
        return new SynchronizedPutFastListMultimap<K, V>(inputIterable);
    }

    @Override
    protected MutableList<V> createCollection()
    {
        return FastList.newList(1);
    }

    public SynchronizedPutFastListMultimap<K, V> newEmpty()
    {
        return new SynchronizedPutFastListMultimap<K, V>();
    }

    public MutableListMultimap<K, V> toMutable()
    {
        return new SynchronizedPutFastListMultimap<K, V>(this);
    }

    public ImmutableListMultimap<K, V> toImmutable()
    {
        final MutableMap<K, ImmutableList<V>> map = UnifiedMap.newMap();

        this.map.forEachKeyValue(new Procedure2<K, MutableList<V>>()
        {
            public void value(K key, MutableList<V> list)
            {
                map.put(key, list.toImmutable());
            }
        });

        return new ImmutableListMultimapImpl<K, V>(map);
    }

    public MutableBagMultimap<V, K> flip()
    {
        return Iterate.flip(this);
    }

    public FastListMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.selectKeysValues(predicate, FastListMultimap.<K, V>newMultimap());
    }

    public FastListMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.rejectKeysValues(predicate, FastListMultimap.<K, V>newMultimap());
    }

    public FastListMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.selectKeysMultiValues(predicate, FastListMultimap.<K, V>newMultimap());
    }

    public FastListMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.rejectKeysMultiValues(predicate, FastListMultimap.<K, V>newMultimap());
    }

    public <K2, V2> HashBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return this.collectKeysValues(function, HashBagMultimap.<K2, V2>newMultimap());
    }

    public <V2> FastListMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function)
    {
        return this.collectValues(function, FastListMultimap.<K, V2>newMultimap());
    }
}
