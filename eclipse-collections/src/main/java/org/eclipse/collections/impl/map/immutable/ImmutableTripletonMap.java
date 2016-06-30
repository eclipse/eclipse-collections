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
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.tuple.Tuples;

final class ImmutableTripletonMap<K, V>
        extends AbstractImmutableMap<K, V>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final K key1;
    private final V value1;
    private final K key2;
    private final V value2;
    private final K key3;
    private final V value3;

    ImmutableTripletonMap(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        this.key1 = key1;
        this.value1 = value1;
        this.key2 = key2;
        this.value2 = value2;
        this.key3 = key3;
        this.value3 = value3;
    }

    @Override
    public RichIterable<K> keysView()
    {
        return Lists.immutable.with(this.key1, this.key2, this.key3).asLazy();
    }

    @Override
    public RichIterable<V> valuesView()
    {
        return Lists.immutable.with(this.value1, this.value2, this.value3).asLazy();
    }

    @Override
    public RichIterable<Pair<K, V>> keyValuesView()
    {
        return Lists.immutable.with(
                Tuples.pair(this.key1, this.value1),
                Tuples.pair(this.key2, this.value2),
                Tuples.pair(this.key3, this.value3)).asLazy();
    }

    @Override
    public int size()
    {
        return 3;
    }

    @Override
    public boolean containsKey(Object key)
    {
        return Comparators.nullSafeEquals(this.key3, key)
                || Comparators.nullSafeEquals(this.key2, key)
                || Comparators.nullSafeEquals(this.key1, key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return Comparators.nullSafeEquals(this.value3, value)
                || Comparators.nullSafeEquals(this.value2, value)
                || Comparators.nullSafeEquals(this.value1, value);
    }

    @Override
    public V get(Object key)
    {
        if (Comparators.nullSafeEquals(this.key3, key))
        {
            return this.value3;
        }
        if (Comparators.nullSafeEquals(this.key2, key))
        {
            return this.value2;
        }
        if (Comparators.nullSafeEquals(this.key1, key))
        {
            return this.value1;
        }
        return null;
    }

    @Override
    public Set<K> keySet()
    {
        return Sets.immutable.with(this.key1, this.key2, this.key3).castToSet();
    }

    @Override
    public Collection<V> values()
    {
        return Lists.immutable.with(this.value1, this.value2, this.value3).castToList();
    }

    @Override
    public int hashCode()
    {
        return this.keyAndValueHashCode(this.key1, this.value1)
                + this.keyAndValueHashCode(this.key2, this.value2)
                + this.keyAndValueHashCode(this.key3, this.value3);
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Map))
        {
            return false;
        }
        Map<K, V> that = (Map<K, V>) other;
        return that.size() == this.size()
                && this.keyAndValueEquals(this.key1, this.value1, that)
                && this.keyAndValueEquals(this.key2, this.value2, that)
                && this.keyAndValueEquals(this.key3, this.value3, that);
    }

    @Override
    public String toString()
    {
        return "{"
                + this.key1 + '=' + this.value1 + ", "
                + this.key2 + '=' + this.value2 + ", "
                + this.key3 + '=' + this.value3 + '}';
    }

    @Override
    public ImmutableMap<V, K> flipUniqueValues()
    {
        return Maps.immutable.with(this.value1, this.key1, this.value2, this.key2, this.value3, this.key3);
    }

    @Override
    public void forEachKeyValue(Procedure2<? super K, ? super V> procedure)
    {
        procedure.value(this.key1, this.value1);
        procedure.value(this.key2, this.value2);
        procedure.value(this.key3, this.value3);
    }

    @Override
    public void forEachKey(Procedure<? super K> procedure)
    {
        procedure.value(this.key1);
        procedure.value(this.key2);
        procedure.value(this.key3);
    }

    @Override
    public void forEachValue(Procedure<? super V> procedure)
    {
        procedure.value(this.value1);
        procedure.value(this.value2);
        procedure.value(this.value3);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super V> objectIntProcedure)
    {
        objectIntProcedure.value(this.value1, 0);
        objectIntProcedure.value(this.value2, 1);
        objectIntProcedure.value(this.value3, 2);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super V, ? super P> procedure, P parameter)
    {
        procedure.value(this.value1, parameter);
        procedure.value(this.value2, parameter);
        procedure.value(this.value3, parameter);
    }

    @Override
    public <K2, V2> ImmutableMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function)
    {
        Pair<K2, V2> pair1 = function.value(this.key1, this.value1);
        Pair<K2, V2> pair2 = function.value(this.key2, this.value2);
        Pair<K2, V2> pair3 = function.value(this.key3, this.value3);

        return Maps.immutable.with(pair1.getOne(), pair1.getTwo(), pair2.getOne(), pair2.getTwo(), pair3.getOne(), pair3.getTwo());
    }

    @Override
    public <R> ImmutableMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function)
    {
        return Maps.immutable.with(this.key1, function.value(this.key1, this.value1), this.key2, function.value(this.key2, this.value2), this.key3, function.value(this.key3, this.value3));
    }

    @Override
    public Pair<K, V> detect(Predicate2<? super K, ? super V> predicate)
    {
        if (predicate.accept(this.key1, this.value1))
        {
            return Tuples.pair(this.key1, this.value1);
        }
        if (predicate.accept(this.key2, this.value2))
        {
            return Tuples.pair(this.key2, this.value2);
        }
        if (predicate.accept(this.key3, this.value3))
        {
            return Tuples.pair(this.key3, this.value3);
        }
        return null;
    }

    @Override
    public ImmutableMap<K, V> select(Predicate2<? super K, ? super V> predicate)
    {
        return this.filter(predicate);
    }

    @Override
    public ImmutableMap<K, V> reject(Predicate2<? super K, ? super V> predicate)
    {
        return this.filter(Predicates2.not(predicate));
    }

    private ImmutableMap<K, V> filter(Predicate2<? super K, ? super V> predicate)
    {
        int result = 0;

        if (predicate.accept(this.key1, this.value1))
        {
            result |= 1;
        }
        if (predicate.accept(this.key2, this.value2))
        {
            result |= 2;
        }
        if (predicate.accept(this.key3, this.value3))
        {
            result |= 4;
        }

        switch (result)
        {
            case 1:
                return Maps.immutable.with(this.key1, this.value1);
            case 2:
                return Maps.immutable.with(this.key2, this.value2);
            case 3:
                return Maps.immutable.with(this.key1, this.value1, this.key2, this.value2);
            case 4:
                return Maps.immutable.with(this.key3, this.value3);
            case 5:
                return Maps.immutable.with(this.key1, this.value1, this.key3, this.value3);
            case 6:
                return Maps.immutable.with(this.key2, this.value2, this.key3, this.value3);
            case 7:
                return Maps.immutable.with(this.key1, this.value1, this.key2, this.value2, this.key3, this.value3);
            default:
                return Maps.immutable.empty();
        }
    }

    @Override
    public V getOnly()
    {
        throw new IllegalStateException("Size must be 1 but was " + this.size());
    }

    private Object writeReplace()
    {
        return new ImmutableMapSerializationProxy<>(this);
    }
}
