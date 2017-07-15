/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bimap.immutable;

import java.io.Serializable;

import org.eclipse.collections.api.map.ImmutableMap;

final class ImmutableHashBiMap<K, V> extends AbstractImmutableBiMap<K, V> implements Serializable
{
    private static final long serialVersionUID = 1L;

    ImmutableHashBiMap(ImmutableMap<K, V> map, ImmutableMap<V, K> inverse)
    {
        super(map, inverse);
    }

    private Object writeReplace()
    {
        return new ImmutableBiMapSerializationProxy<>(this);
    }
}
