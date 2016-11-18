/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.util.Map;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Functions;

/**
 * MapCollectProcedure uses an Function to calculate the key for an object and puts the object with the key
 * into the specified Map.
 */
public final class MapCollectProcedure<T, K, V> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Map<K, V> map;
    private final Function<? super T, ? extends K> keyFunction;
    private final Function<? super T, ? extends V> valueFunction;

    public MapCollectProcedure(Map<K, V> newMap, Function<? super T, ? extends K> newKeyFunction)
    {
        this(newMap, newKeyFunction, (Function<T, V>) Functions.identity());
    }

    public MapCollectProcedure(Map<K, V> newMap, Function<? super T, ? extends K> newKeyFunction, Function<? super T, ? extends V> newValueFunction)
    {
        this.map = newMap;
        this.keyFunction = newKeyFunction;
        this.valueFunction = newValueFunction;
    }

    @Override
    public void value(T object)
    {
        this.map.put(this.keyFunction.valueOf(object), this.valueFunction.valueOf(object));
    }
}
