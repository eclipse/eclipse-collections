/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.tuple.Tuples;

final class ImmutableSingletonMap<K, V>
        extends AbstractImmutableMap<K, V>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final K key1;
    private final V value1;

    ImmutableSingletonMap(K key1, V value1)
    {
        this.key1 = key1;
        this.value1 = value1;
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public RichIterable<K> keysView()
    {
        return Lists.immutable.with(this.key1).asLazy();
    }

    @Override
    public RichIterable<V> valuesView()
    {
        return Lists.immutable.with(this.value1).asLazy();
    }

    @Override
    public RichIterable<Pair<K, V>> keyValuesView()
    {
        return Lists.immutable.with(Tuples.pair(this.key1, this.value1)).asLazy();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return Comparators.nullSafeEquals(this.key1, key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return Comparators.nullSafeEquals(this.value1, value);
    }

    @Override
    public V get(Object key)
    {
        if (Comparators.nullSafeEquals(this.key1, key))
        {
            return this.value1;
        }

        return null;
    }

    @Override
    public Set<K> keySet()
    {
        return Sets.immutable.with(this.key1).castToSet();
    }

    @Override
    public Collection<V> values()
    {
        return Lists.immutable.with(this.value1).castToList();
    }

    @Override
    public String toString()
    {
        return "{" + this.key1 + '=' + this.value1 + '}';
    }

    @Override
    public int hashCode()
    {
        return this.keyAndValueHashCode(this.key1, this.value1);
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Map))
        {
            return false;
        }
        Map<K, V> that = (Map<K, V>) other;
        return that.size() == this.size() && this.keyAndValueEquals(this.key1, this.value1, that);
    }

    @Override
    public void forEachKeyValue(Procedure2<? super K, ? super V> procedure)
    {
        procedure.value(this.key1, this.value1);
    }

    @Override
    public ImmutableMap<V, K> flipUniqueValues()
    {
        return Maps.immutable.with(this.value1, this.key1);
    }

    @Override
    public void forEachKey(Procedure<? super K> procedure)
    {
        procedure.value(this.key1);
    }

    @Override
    public void forEachValue(Procedure<? super V> procedure)
    {
        procedure.value(this.value1);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        objectIntProcedure.value(this.value1, 0);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super V, ? super P> procedure, P parameter)
    {
        procedure.value(this.value1, parameter);
    }

    @Override
    public ImmutableMap<K, V> select(Predicate2<? super K, ? super V> predicate)
    {
        if (predicate.accept(this.key1, this.value1))
        {
            return this;
        }
        return Maps.immutable.empty();
    }

    @Override
    public ImmutableMap<K, V> reject(Predicate2<? super K, ? super V> predicate)
    {
        if (predicate.accept(this.key1, this.value1))
        {
            return Maps.immutable.empty();
        }
        return this;
    }

    @Override
    public <K2, V2> ImmutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        Pair<K2, V2> pair = function.value(this.key1, this.value1);
        return Maps.immutable.with(pair.getOne(), pair.getTwo());
    }

    @Override
    public <R> ImmutableMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function)
    {
        return Maps.immutable.with(this.key1, function.value(this.key1, this.value1));
    }

    @Override
    public Pair<K, V> detect(Predicate2<? super K, ? super V> predicate)
    {
        if (predicate.accept(this.key1, this.value1))
        {
            return Tuples.pair(this.key1, this.value1);
        }
        return null;
    }

    @Override
    public V getOnly()
    {
        return this.value1;
    }

    private Object writeReplace()
    {
        return new ImmutableMapSerializationProxy<>(this);
    }
}
