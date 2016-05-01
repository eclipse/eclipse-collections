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

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.list.mutable.FastList;

/**
 * Applies a function to an object and adds the result to a target fastList.
 */
public final class FastListCollectProcedure<T, V> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Function<? super T, ? extends V> function;
    private final FastList<V> fastList;

    public FastListCollectProcedure(Function<? super T, ? extends V> function, FastList<V> targetCollection)
    {
        this.function = function;
        this.fastList = targetCollection;
    }

    @Override
    public void value(T object)
    {
        V value = this.function.valueOf(object);
        this.fastList.add(value);
    }

    public FastList<V> getFastList()
    {
        return this.fastList;
    }
}
