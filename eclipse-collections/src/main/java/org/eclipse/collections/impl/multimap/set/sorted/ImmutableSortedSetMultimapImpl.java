/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set.sorted;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Comparator;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.SortedSets;
import org.eclipse.collections.impl.multimap.AbstractImmutableMultimap;
import org.eclipse.collections.impl.multimap.AbstractMutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationProxy;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * The default ImmutableSortedSetMultimap implementation.
 *
 * @since 1.0
 */
public final class ImmutableSortedSetMultimapImpl<K, V>
        extends AbstractImmutableMultimap<K, V, ImmutableSortedSet<V>>
        implements ImmutableSortedSetMultimap<K, V>, Serializable
{
    private static final long serialVersionUID = 1L;
    private final Comparator<? super V> comparator;

    ImmutableSortedSetMultimapImpl(MutableMap<K, ImmutableSortedSet<V>> map)
    {
        super(map);
        this.comparator = null;
    }

    public ImmutableSortedSetMultimapImpl(MutableMap<K, ImmutableSortedSet<V>> map, Comparator<? super V> comparator)
    {
        super(map);
        this.comparator = comparator;
    }

    ImmutableSortedSetMultimapImpl(ImmutableMap<K, ImmutableSortedSet<V>> map)
    {
        super(map);
        this.comparator = null;
    }

    public ImmutableSortedSetMultimapImpl(ImmutableMap<K, ImmutableSortedSet<V>> map, Comparator<? super V> comparator)
    {
        super(map);
        this.comparator = comparator;
    }

    @Override
    protected ImmutableSortedSet<V> createCollection()
    {
        return SortedSets.immutable.with(this.comparator());
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> newEmpty()
    {
        return new ImmutableSortedSetMultimapImpl<>(Maps.immutable.with(), this.comparator());
    }

    @Override
    public Comparator<? super V> comparator()
    {
        return this.comparator;
    }

    @Override
    public MutableSortedSetMultimap<K, V> toMutable()
    {
        return new TreeSortedSetMultimap<>(this);
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> toImmutable()
    {
        return this;
    }

    private Object writeReplace()
    {
        return new ImmutableSortedSetMultimapSerializationProxy<>(this.map, this.comparator());
    }

    private static final class ImmutableSortedSetMultimapSerializationProxy<K, V>
            extends ImmutableMultimapSerializationProxy<K, V, ImmutableSortedSet<V>> implements Externalizable
    {
        private static final long serialVersionUID = 1L;
        private Comparator<? super V> comparator;

        @SuppressWarnings("UnusedDeclaration")
        public ImmutableSortedSetMultimapSerializationProxy()
        {
            // For Externalizable use only
        }

        private ImmutableSortedSetMultimapSerializationProxy(ImmutableMap<K, ImmutableSortedSet<V>> map, Comparator<? super V> comparator)
        {
            super(map);
            this.comparator = comparator;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException
        {
            out.writeObject(this.comparator);
            super.writeExternal(out);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        {
            this.comparator = (Comparator<? super V>) in.readObject();
            super.readExternal(in);
        }

        @Override
        protected AbstractMutableMultimap<K, V, MutableSortedSet<V>> createEmptyMutableMultimap()
        {
            return new TreeSortedSetMultimap<>(this.comparator);
        }
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> newWith(K key, V value)
    {
        return (ImmutableSortedSetMultimap<K, V>) super.newWith(key, value);
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> newWithout(Object key, Object value)
    {
        return (ImmutableSortedSetMultimap<K, V>) super.newWithout(key, value);
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> newWithAll(K key, Iterable<? extends V> values)
    {
        return (ImmutableSortedSetMultimap<K, V>) super.newWithAll(key, values);
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> newWithoutAll(Object key)
    {
        return (ImmutableSortedSetMultimap<K, V>) super.newWithoutAll(key);
    }

    @Override
    public ImmutableSetMultimap<V, K> flip()
    {
        return Iterate.flip(this).toImmutable();
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> selectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.selectKeysValues(predicate, TreeSortedSetMultimap.newMultimap(this.comparator())).toImmutable();
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> rejectKeysValues(Predicate2<? super K, ? super V> predicate)
    {
        return this.rejectKeysValues(predicate, TreeSortedSetMultimap.newMultimap(this.comparator())).toImmutable();
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> selectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.selectKeysMultiValues(predicate, TreeSortedSetMultimap.newMultimap(this.comparator())).toImmutable();
    }

    @Override
    public ImmutableSortedSetMultimap<K, V> rejectKeysMultiValues(Predicate2<? super K, ? super Iterable<V>> predicate)
    {
        return this.rejectKeysMultiValues(predicate, TreeSortedSetMultimap.newMultimap(this.comparator())).toImmutable();
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
