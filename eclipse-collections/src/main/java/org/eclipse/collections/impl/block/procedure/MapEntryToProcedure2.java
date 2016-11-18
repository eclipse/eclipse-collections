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

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;

/**
 * MapEntryToProcedure2 translates the result of calling entrySet() on a Map, which results in a collection
 * of Map.Entry objects into corresponding Procedure2s.  This removes the need to deal with Map.Entry when
 * iterating over an entrySet.
 */
public final class MapEntryToProcedure2<K, V> implements Procedure<Map.Entry<K, V>>
{
    private static final long serialVersionUID = 1L;

    private final Procedure2<? super K, ? super V> procedure;

    public MapEntryToProcedure2(Procedure2<? super K, ? super V> procedure)
    {
        this.procedure = procedure;
    }

    @Override
    public void value(Map.Entry<K, V> entry)
    {
        this.procedure.value(entry.getKey(), entry.getValue());
    }
}
