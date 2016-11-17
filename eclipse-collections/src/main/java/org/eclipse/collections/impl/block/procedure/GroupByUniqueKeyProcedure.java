/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.util.Map;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;

public class GroupByUniqueKeyProcedure<T, K> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Map<K, T> map;
    private final Function<? super T, ? extends K> keyFunction;

    public GroupByUniqueKeyProcedure(Map<K, T> newMap, Function<? super T, ? extends K> newKeyFunction)
    {
        this.map = newMap;
        this.keyFunction = newKeyFunction;
    }

    @Override
    public void value(T object)
    {
        K key = this.keyFunction.valueOf(object);
        if (this.map.put(key, object) != null)
        {
            throw new IllegalStateException("Key " + key + " already exists in map!");
        }
    }
}
