/*
 * Copyright (c) 2016 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set;

import java.io.Externalizable;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.AbstractSynchronizedPutMultimap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * A Multimap that is optimized for parallel writes, but is not protected for concurrent reads.
 */
public final class SynchronizedPutUnifiedSetMultimap<K, V>
        extends AbstractSynchronizedPutMultimap<K, V, MutableSet<V>> implements MutableSetMultimap<K, V>, Externalizable
{
    private static final long serialVersionUID = 42L;

    public SynchronizedPutUnifiedSetMultimap()
    {
    }

    public SynchronizedPutUnifiedSetMultimap(int initialCapacity)
    {
        super(ConcurrentHashMap.newMap(initialCapacity));
    }

    public SynchronizedPutUnifiedSetMultimap(Multimap<? extends K, ? extends V> multimap)
    {
        this.putAll(multimap);
    }

    public SynchronizedPutUnifiedSetMultimap(Pair<K, V>... pairs)
    {
        this();
        ArrayIterate.forEach(pairs, pair -> this.put(pair.getOne(), pair.getTwo()));
    }

    public SynchronizedPutUnifiedSetMultimap(Iterable<Pair<K, V>> inputIterable)
    {
        this();
        Iterate.forEach(inputIterable, this::add);
    }

    public static <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimap()
    {
        return new SynchronizedPutUnifiedSetMultimap<>();
    }

    public static <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimap(int initialCapacity, float loadFactor, int concurrencyLevel)
    {
        return new SynchronizedPutUnifiedSetMultimap<>(initialCapacity);
    }

    public static <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimap(Multimap<? extends K, ? extends V> multimap)
    {
        return new SynchronizedPutUnifiedSetMultimap<>(multimap);
    }

    public static <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimap(Pair<K, V>... pairs)
    {
        return new SynchronizedPutUnifiedSetMultimap<>(pairs);
    }

    public static <K, V> SynchronizedPutUnifiedSetMultimap<K, V> newMultimap(Iterable<Pair<K, V>> inputIterable)
    {
        return new SynchronizedPutUnifiedSetMultimap<>(inputIterable);
    }

    @Override
    protected MutableSet<V> createCollection()
    {
        return UnifiedSet.newSet(1);
    }

    @Override
    public SynchronizedPutUnifiedSetMultimap<K, V> newEmpty()
    {
        return new SynchronizedPutUnifiedSetMultimap<>();
    }

    @Override
    public MutableSetMultimap<K, V> toMutable()
    {
        return new SynchronizedPutUnifiedSetMultimap<>(this);
    }

    @Override
    public ImmutableSetMultimap<K, V> toImmutable()
    {
        MutableMap<K, ImmutableSet<V>> map = UnifiedMap.newMap();

        this.map.forEachKeyValue((key, set) -> map.put(key, set.toImmutable()));

        return new ImmutableSetMultimapImpl<>(map);
    }

    @Override
    public UnifiedSetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.selectKeysValues(predicate, UnifiedSetMultimap.newMultimap());
    }

    @Override
    public UnifiedSetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.rejectKeysValues(predicate, UnifiedSetMultimap.newMultimap());
    }

    @Override
    public UnifiedSetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.selectKeysMultiValues(predicate, UnifiedSetMultimap.newMultimap());
    }

    @Override
    public UnifiedSetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.rejectKeysMultiValues(predicate, UnifiedSetMultimap.newMultimap());
    }

    @Override
    public <K2, V2> MutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return this.collectKeysValues(function, HashBagMultimap.newMultimap());
    }

    @Override
    public <V2> MutableBagMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function)
    {
        return this.collectValues(function, HashBagMultimap.newMultimap());
    }

    @Override
    public MutableSetMultimap<V, K> flip()
    {
        return Iterate.flip(this);
    }

    @Override
    public MutableSetMultimap<K, V> asSynchronized()
    {
        throw new UnsupportedOperationException("Cannot call asSynchronized() on " + this.getClass().getSimpleName());
    }
}
