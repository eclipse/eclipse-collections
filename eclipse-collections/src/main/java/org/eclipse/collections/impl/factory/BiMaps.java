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

import org.eclipse.collections.api.factory.bimap.ImmutableBiMapFactory;
import org.eclipse.collections.api.factory.bimap.MutableBiMapFactory;
import org.eclipse.collections.impl.bimap.immutable.ImmutableBiMapFactoryImpl;
import org.eclipse.collections.impl.bimap.mutable.MutableBiMapFactoryImpl;

/**
 * @since 6.0
 */
@SuppressWarnings("ConstantNamingConvention")
public final class BiMaps
{
    public static final ImmutableBiMapFactory immutable = ImmutableBiMapFactoryImpl.INSTANCE;
    public static final MutableBiMapFactory mutable = MutableBiMapFactoryImpl.INSTANCE;

    private BiMaps()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
