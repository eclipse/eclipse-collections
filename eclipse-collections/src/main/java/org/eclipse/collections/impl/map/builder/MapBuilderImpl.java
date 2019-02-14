/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.builder;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.builder.MapBuilder;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

public class MapBuilderImpl<K, V> implements MapBuilder<K, V>
{
    private final MutableMap<K, V> map;

    /**
     * Constructs a new MapBuilder simultaneously configuring the Key and Value types for use.
     */
    public MapBuilderImpl(MutableMap<K, V> initialMap)
    {
        this.map = initialMap;
    }

    @Override
    public MapBuilder<K, V> and(K key, V value)
    {
        this.map.put(key, value);
        return this;
    }

    @Override
    public ImmutableMap<K, V> build()
    {
        return this.map.toImmutable();
    }

    @Override
    public MutableMap<K, V> buildMutable()
    {
        return UnifiedMap.newMap(this.map);
    }
}
