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

import org.eclipse.collections.api.factory.set.strategy.ImmutableHashingStrategySetFactory;
import org.eclipse.collections.api.factory.set.strategy.MutableHashingStrategySetFactory;
import org.eclipse.collections.impl.set.strategy.immutable.ImmutableHashingStrategySetFactoryImpl;
import org.eclipse.collections.impl.set.strategy.mutable.MutableHashingStrategySetFactoryImpl;

@SuppressWarnings("ConstantNamingConvention")
public final class HashingStrategySets
{
    public static final ImmutableHashingStrategySetFactory immutable = ImmutableHashingStrategySetFactoryImpl.INSTANCE;
    public static final MutableHashingStrategySetFactory mutable = MutableHashingStrategySetFactoryImpl.INSTANCE;

    private HashingStrategySets()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
