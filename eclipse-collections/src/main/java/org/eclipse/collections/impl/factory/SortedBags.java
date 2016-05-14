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

import org.eclipse.collections.api.factory.bag.sorted.ImmutableSortedBagFactory;
import org.eclipse.collections.api.factory.bag.sorted.MutableSortedBagFactory;
import org.eclipse.collections.impl.bag.sorted.immutable.ImmutableSortedBagFactoryImpl;
import org.eclipse.collections.impl.bag.sorted.mutable.MutableSortedBagFactoryImpl;

@SuppressWarnings("ConstantNamingConvention")
public final class SortedBags
{
    public static final MutableSortedBagFactory mutable = MutableSortedBagFactoryImpl.INSTANCE;
    public static final ImmutableSortedBagFactory immutable = ImmutableSortedBagFactoryImpl.INSTANCE;

    private SortedBags()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
