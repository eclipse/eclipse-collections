/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set;

import java.io.Externalizable;
import java.io.Serializable;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.multimap.AbstractImmutableMultimap;
import org.eclipse.collections.impl.multimap.AbstractMutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationProxy;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * The default ImmutableBagMultimap implementation.
 *
 * @since 1.0
 */
public final class ImmutableSetMultimapImpl<K, V>
        extends AbstractImmutableMultimap<K, V, ImmutableSet<V>>
        implements ImmutableSetMultimap<K, V>, Serializable
{
    private static final long serialVersionUID = 1L;

    public ImmutableSetMultimapImpl(MutableMap<K, ImmutableSet<V>> map)
    {
        super(map);
    }

    public ImmutableSetMultimapImpl(ImmutableMap<K, ImmutableSet<V>> map)
    {
        super(map);
    }

    @Override
    protected ImmutableSet<V> createCollection()
    {
        return Sets.immutable.empty();
    }

    @Override
    public ImmutableSetMultimap<K, V> newEmpty()
    {
        return new ImmutableSetMultimapImpl<>(Maps.immutable.with());
    }

    @Override
    public MutableSetMultimap<K, V> toMutable()
    {
        return new UnifiedSetMultimap<>(this);
    }

    @Override
    public ImmutableSetMultimap<K, V> toImmutable()
    {
        return this;
    }

    private Object writeReplace()
    {
        return new ImmutableSetMultimapSerializationProxy<>(this.map);
    }

    private static final class ImmutableSetMultimapSerializationProxy<K, V>
            extends ImmutableMultimapSerializationProxy<K, V, ImmutableSet<V>> implements Externalizable
    {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("UnusedDeclaration")
        public ImmutableSetMultimapSerializationProxy()
        {
            // For Externalizable use only
        }

        private ImmutableSetMultimapSerializationProxy(ImmutableMap<K, ImmutableSet<V>> map)
        {
            super(map);
        }

        @Override
        protected AbstractMutableMultimap<K, V, MutableSet<V>> createEmptyMutableMultimap()
        {
            return new UnifiedSetMultimap<>();
        }
    }

    @Override
    public ImmutableSetMultimap<K, V> newWith(K key, V value)
    {
        return (ImmutableSetMultimap<K, V>) super.newWith(key, value);
    }

    @Override
    public ImmutableSetMultimap<K, V> newWithout(Object key, Object value)
    {
        return (ImmutableSetMultimap<K, V>) super.newWithout(key, value);
    }

    @Override
    public ImmutableSetMultimap<K, V> newWithAll(K key, Iterable<? extends V> values)
    {
        return (ImmutableSetMultimap<K, V>) super.newWithAll(key, values);
    }

    @Override
    public ImmutableSetMultimap<K, V> newWithoutAll(Object key)
    {
        return (ImmutableSetMultimap<K, V>) super.newWithoutAll(key);
    }

    @Override
    public ImmutableSetMultimap<V, K> flip()
    {
        return Iterate.flip(this).toImmutable();
    }

    @Override
    public ImmutableSetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.selectKeysValues(predicate, UnifiedSetMultimap.newMultimap()).toImmutable();
    }

    @Override
    public ImmutableSetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.rejectKeysValues(predicate, UnifiedSetMultimap.newMultimap()).toImmutable();
    }

    @Override
    public ImmutableSetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.selectKeysMultiValues(predicate, UnifiedSetMultimap.newMultimap()).toImmutable();
    }

    @Override
    public ImmutableSetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.rejectKeysMultiValues(predicate, UnifiedSetMultimap.newMultimap()).toImmutable();
    }

    @Override
    public <K2, V2> ImmutableBagMultimap<K2, V2> collectKeysValues(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        return this.collectKeysValues(function, HashBagMultimap.newMultimap()).toImmutable();
    }

    @Override
    public <V2> ImmutableBagMultimap<K, V2> collectValues(Function<? super V, ? extends V2> function)
    {
        return this.collectValues(function, HashBagMultimap.<K, V2>newMultimap()).toImmutable();
    }
}
