/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.fixed;

import java.util.Map;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.map.FixedSizeMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.eclipse.collections.impl.map.mutable.AbstractMutableMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

abstract class AbstractMemoryEfficientMutableMap<K, V>
        extends AbstractMutableMap<K, V>
        implements FixedSizeMap<K, V>
{
    @Override
    public V put(K key, V value)
    {
        throw new UnsupportedOperationException("Cannot call put() on " + this.getClass().getSimpleName());
    }

    @Override
    public V remove(Object key)
    {
        throw new UnsupportedOperationException("Cannot call remove() on " + this.getClass().getSimpleName());
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map)
    {
        throw new UnsupportedOperationException("Cannot call putAll() on " + this.getClass().getSimpleName());
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("Cannot call clear() on " + this.getClass().getSimpleName());
    }

    @Override
    public V removeKey(K key)
    {
        throw new UnsupportedOperationException("Cannot call removeKey() on " + this.getClass().getSimpleName());
    }

    @Override
    public <E> MutableMap<K, V> collectKeysAndValues(
            Iterable<E> iterable,
            Function<? super E, ? extends K> keyFunction,
            Function<? super E, ? extends V> valueFunction)
    {
        throw new UnsupportedOperationException("Cannot call collectKeysAndValues() on " + this.getClass().getSimpleName());
    }

    @Override
    public V updateValue(K key, Function0<? extends V> factory, Function<? super V, ? extends V> function)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".updateValue() not implemented yet");
    }

    @Override
    public <P> V updateValueWith(K key, Function0<? extends V> factory, Function2<? super V, ? super P, ? extends V> function, P parameter)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".updateValueWith() not implemented yet");
    }

    @Override
    public MutableMap<K, V> newEmpty()
    {
        return UnifiedMap.newMap();
    }

    @Override
    public MutableMap<K, V> newEmpty(int capacity)
    {
        return UnifiedMap.newMap(capacity);
    }

    @Override
    public MutableMap<K, V> withAllKeyValues(Iterable<? extends Pair<? extends K, ? extends V>> keyValues)
    {
        MutableMap<K, V> map = this;
        for (Pair<? extends K, ? extends V> keyVal : keyValues)
        {
            map = map.withKeyValue(keyVal.getOne(), keyVal.getTwo());
        }
        return map;
    }

    @Override
    public MutableMap<K, V> withAllKeyValueArguments(Pair<? extends K, ? extends V>... keyValues)
    {
        return this.withAllKeyValues(ArrayAdapter.adapt(keyValues));
    }

    @Override
    public MutableMap<K, V> withoutAllKeys(Iterable<? extends K> keys)
    {
        MutableMap<K, V> map = this;
        for (K key : keys)
        {
            map = map.withoutKey(key);
        }
        return map;
    }

    @Override
    public FixedSizeMap<K, V> tap(Procedure<? super V> procedure)
    {
        this.forEach(procedure);
        return this;
    }

    @Override
    public abstract FixedSizeMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    @Override
    public abstract <R> FixedSizeMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    @Override
    public abstract <K2, V2> FixedSizeMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    @Override
    public abstract FixedSizeMap<K, V> reject(Predicate2<? super K, ? super V> predicate);
}
