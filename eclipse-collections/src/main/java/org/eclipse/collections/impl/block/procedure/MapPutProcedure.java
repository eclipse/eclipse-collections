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

import org.eclipse.collections.api.block.procedure.Procedure2;

/**
 * Transfers keys and values from one map to another
 */
public class MapPutProcedure<K, V> implements Procedure2<K, V>
{
    private static final long serialVersionUID = 1L;
    private final Map<K, V> newMap;

    public MapPutProcedure(Map<K, V> newMap)
    {
        this.newMap = newMap;
    }

    @Override
    public void value(K key, V value)
    {
        this.newMap.put(key, value);
    }
}
