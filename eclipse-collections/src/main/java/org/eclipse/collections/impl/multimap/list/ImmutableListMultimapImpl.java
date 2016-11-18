/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.list;

import java.io.Externalizable;
import java.io.Serializable;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.multimap.AbstractImmutableMultimap;
import org.eclipse.collections.impl.multimap.AbstractMutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationProxy;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * The default ImmutableListMultimap implementation.
 *
 * @since 1.0
 */
public final class ImmutableListMultimapImpl<K, V>
        extends AbstractImmutableMultimap<K, V, ImmutableList<V>>
        implements ImmutableListMultimap<K, V>, Serializable
{
    private static final long serialVersionUID = 1L;

    public ImmutableListMultimapImpl(MutableMap<K, ImmutableList<V>> map)
    {
        super(map);
    }

    public ImmutableListMultimapImpl(ImmutableMap<K, ImmutableList<V>> map)
    {
        super(map);
    }

    @Override
    protected ImmutableList<V> createCollection()
    {
        return Lists.immutable.empty();
    }

    @Override
    public ImmutableListMultimap<K, V> newEmpty()
    {
        return new ImmutableListMultimapImpl<>(Maps.immutable.of());
    }

    @Override
    public MutableListMultimap<K, V> toMutable()
    {
        return new FastListMultimap<>(this);
    }

    @Override
    public ImmutableListMultimap<K, V> toImmutable()
    {
        return this;
    }

    private Object writeReplace()
    {
        return new ImmutableListMultimapSerializationProxy<>(this.map);
    }

    public static class ImmutableListMultimapSerializationProxy<K, V>
            extends ImmutableMultimapSerializationProxy<K, V, ImmutableList<V>> implements Externalizable
    {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("UnusedDeclaration")
        public ImmutableListMultimapSerializationProxy()
        {
            // For Externalizable use only
        }

        public ImmutableListMultimapSerializationProxy(ImmutableMap<K, ImmutableList<V>> map)
        {
            super(map);
        }

        @Override
        protected AbstractMutableMultimap<K, V, MutableList<V>> createEmptyMutableMultimap()
        {
            return new FastListMultimap<>();
        }
    }

    @Override
    public ImmutableListMultimap<K, V> newWith(K key, V value)
    {
        return (ImmutableListMultimap<K, V>) super.newWith(key, value);
    }

    @Override
    public ImmutableListMultimap<K, V> newWithout(Object key, Object value)
    {
        return (ImmutableListMultimap<K, V>) super.newWithout(key, value);
    }

    @Override
    public ImmutableListMultimap<K, V> newWithAll(K key, Iterable<? extends V> values)
    {
        return (ImmutableListMultimap<K, V>) super.newWithAll(key, values);
    }

    @Override
    public ImmutableListMultimap<K, V> newWithoutAll(Object key)
    {
        return (ImmutableListMultimap<K, V>) super.newWithoutAll(key);
    }

    @Override
    public ImmutableBagMultimap<V, K> flip()
    {
        return Iterate.flip(this).toImmutable();
    }

    @Override
    public ImmutableListMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.selectKeysValues(predicate, FastListMultimap.newMultimap()).toImmutable();
    }

    @Override
    public ImmutableListMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.rejectKeysValues(predicate, FastListMultimap.newMultimap()).toImmutable();
    }

    @Override
    public ImmutableListMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.selectKeysMultiValues(predicate, FastListMultimap.newMultimap()).toImmutable();
    }

    @Override
    public ImmutableListMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.rejectKeysMultiValues(predicate, FastListMultimap.newMultimap()).toImmutable();
    }

    @Override
    public <K2, V2> ImmutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return this.collectKeysValues(function, HashBagMultimap.newMultimap()).toImmutable();
    }

    @Override
    public <V2> ImmutableListMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function)
    {
        return this.collectValues(function, FastListMultimap.<K, V2>newMultimap()).toImmutable();
    }
}
