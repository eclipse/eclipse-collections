/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.predicate;

import java.util.Map;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;

public abstract class MapEntryPredicate<T1, T2>
        implements Predicate<Map.Entry<T1, T2>>, Predicate2<T1, T2>
{
    private static final long serialVersionUID = 1L;

    @Override
    public boolean accept(Map.Entry<T1, T2> entry)
    {
        return this.accept(entry.getKey(), entry.getValue());
    }

    @Override
    public MapEntryPredicate<T1, T2> negate()
    {
        return new MapEntryPredicate<T1, T2>()
        {
            @Override
            public boolean accept(Map.Entry<T1, T2> entry)
            {
                return !MapEntryPredicate.this.accept(entry);
            }

            @Override
            public boolean accept(T1 argument1, T2 argument2)
            {
                return !MapEntryPredicate.this.accept(argument1, argument2);
            }
        };
    }
}
