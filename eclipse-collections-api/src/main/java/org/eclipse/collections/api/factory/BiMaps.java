/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory;

import org.eclipse.collections.api.factory.bimap.ImmutableBiMapFactory;
import org.eclipse.collections.api.factory.bimap.MutableBiMapFactory;

@SuppressWarnings("ConstantNamingConvention")
public final class BiMaps
{
    public static final ImmutableBiMapFactory immutable = ServiceLoaderUtils.loadServiceClass(ImmutableBiMapFactory.class);
    public static final MutableBiMapFactory mutable = ServiceLoaderUtils.loadServiceClass(MutableBiMapFactory.class);

    private BiMaps()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
