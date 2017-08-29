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

import org.eclipse.collections.api.factory.map.strategy.ImmutableHashingStrategyMapFactory;
import org.eclipse.collections.api.factory.map.strategy.MutableHashingStrategyMapFactory;
import org.eclipse.collections.impl.map.strategy.immutable.ImmutableHashingStrategyMapFactoryImpl;
import org.eclipse.collections.impl.map.strategy.mutable.MutableHashingStrategyMapFactoryImpl;

@SuppressWarnings("ConstantNamingConvention")
public final class HashingStrategyMaps
{
    public static final ImmutableHashingStrategyMapFactory immutable = ImmutableHashingStrategyMapFactoryImpl.INSTANCE;
    public static final MutableHashingStrategyMapFactory mutable = MutableHashingStrategyMapFactoryImpl.INSTANCE;

    private HashingStrategyMaps()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
