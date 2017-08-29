/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import java.util.SortedMap;

import org.eclipse.collections.api.factory.map.sorted.ImmutableSortedMapFactory;
import org.eclipse.collections.api.factory.map.sorted.MutableSortedMapFactory;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.impl.map.sorted.immutable.ImmutableSortedMapFactoryImpl;
import org.eclipse.collections.impl.map.sorted.mutable.MutableSortedMapFactoryImpl;
import org.eclipse.collections.impl.map.sorted.mutable.SortedMapAdapter;

@SuppressWarnings("ConstantNamingConvention")
public final class SortedMaps
{
    public static final ImmutableSortedMapFactory immutable = ImmutableSortedMapFactoryImpl.INSTANCE;
    public static final MutableSortedMapFactory mutable = MutableSortedMapFactoryImpl.INSTANCE;

    private SortedMaps()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * @since 9.0.
     */
    public static <K, V> MutableSortedMap<K, V> adapt(SortedMap<K, V> list)
    {
        return SortedMapAdapter.adapt(list);
    }
}
