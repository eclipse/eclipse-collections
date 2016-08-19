/*
 * Copyright (c) 2016 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.list;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.AbstractMutableMultimap;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;

public abstract class AbstractMutableListMultimap<K, V> extends AbstractMutableMultimap<K, V, MutableList<V>> implements MutableListMultimap<K, V>
{
    protected AbstractMutableListMultimap()
    {
    }

    protected AbstractMutableListMultimap(Pair<K, V>... pairs)
    {
        super(pairs);
    }

    protected AbstractMutableListMultimap(Iterable<Pair<K, V>> inputIterable)
    {
        super(inputIterable);
    }

    protected AbstractMutableListMultimap(int size)
    {
        super(size);
    }

    @Override
    public MutableListMultimap<K, V> toMutable()
    {
        return new FastListMultimap<>(this);
    }

    @Override
    public ImmutableListMultimap<K, V> toImmutable()
    {
        MutableMap<K, ImmutableList<V>> map = UnifiedMap.newMap();

        this.map.forEachKeyValue((key, list) -> map.put(key, list.toImmutable()));

        return new ImmutableListMultimapImpl<>(map);
    }

    @Override
    public <K2, V2> HashBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return this.collectKeysValues(function, HashBagMultimap.newMultimap());
    }

    @Override
    public <V2> FastListMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function)
    {
        return this.collectValues(function, FastListMultimap.newMultimap());
    }

    @Override
    public MutableListMultimap<K, V> asSynchronized()
    {
        return SynchronizedListMultimap.of(this);
    }
}
