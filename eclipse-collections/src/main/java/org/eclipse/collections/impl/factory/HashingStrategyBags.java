/*
 * Copyright (c) 2016 Bhavana Hindupur.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.factory;

import org.eclipse.collections.api.factory.bag.strategy.MutableHashingStrategyBagFactory;
import org.eclipse.collections.impl.bag.strategy.mutable.MutableHashingStrategyBagFactoryImpl;

@SuppressWarnings("ConstantNamingConvention")
public final class HashingStrategyBags
{
    public static final MutableHashingStrategyBagFactory mutable = MutableHashingStrategyBagFactoryImpl.INSTANCE;

    private HashingStrategyBags()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
