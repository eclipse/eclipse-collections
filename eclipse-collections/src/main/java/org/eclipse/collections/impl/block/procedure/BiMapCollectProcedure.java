/*
 * Copyright (c) 2019 Xebialabs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.bimap.MutableBiMap;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * BiMapCollectProcedure uses an Function to calculate the key for an object and puts the object with the key
 * into the specified BiMap.
 */
public final class BiMapCollectProcedure<T, K, V> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final MutableBiMap<K, V> biMap;
    private final Function<? super T, ? extends K> keyFunction;
    private final Function<? super T, ? extends V> valueFunction;

    public BiMapCollectProcedure(MutableBiMap<K, V> newMap, Function<? super T, ? extends K> newKeyFunction, Function<? super T, ? extends V> newValueFunction)
    {
        this.biMap = newMap;
        this.keyFunction = newKeyFunction;
        this.valueFunction = newValueFunction;
    }

    @Override
    public void value(T object)
    {
        K key = this.keyFunction.valueOf(object);
        V value = this.valueFunction.valueOf(object);

        if (this.biMap.containsKey(key))
        {
            throw new IllegalArgumentException("Key " + key + " already exists in map!");
        }

        if (this.biMap.inverse().containsKey(value))
        {
            throw new IllegalArgumentException("Value " + value + " already exists in map!");
        }

        this.biMap.put(key, value);
    }
}
