/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.factory.map.sorted.ImmutableSortedMapFactory;
import org.eclipse.collections.api.factory.map.sorted.MutableSortedMapFactory;
import org.eclipse.collections.impl.map.sorted.immutable.ImmutableSortedMapFactoryImpl;
import org.eclipse.collections.impl.map.sorted.mutable.MutableSortedMapFactoryImpl;

@SuppressWarnings("ConstantNamingConvention")
public final class SortedMaps
{
    public static final ImmutableSortedMapFactory immutable = new ImmutableSortedMapFactoryImpl();
    public static final MutableSortedMapFactory mutable = new MutableSortedMapFactoryImpl();

    private SortedMaps()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
