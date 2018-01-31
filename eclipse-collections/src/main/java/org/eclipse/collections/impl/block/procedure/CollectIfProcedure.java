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

import java.util.Collection;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.list.mutable.FastList;

public final class CollectIfProcedure<T, V>
        implements Procedure<T>
{
    private static final long serialVersionUID = 1L;
    private final Function<? super T, ? extends V> function;
    private final Predicate<? super T> predicate;
    private final Collection<V> collection;

    public CollectIfProcedure(
            int taskSize,
            Function<? super T, ? extends V> function,
            Predicate<? super T> predicate)
    {
        this(FastList.newList(taskSize), function, predicate);
    }

    public CollectIfProcedure(
            Collection<V> targetCollection,
            Function<? super T, ? extends V> function,
            Predicate<? super T> predicate)
    {
        this.function = function;
        this.predicate = predicate;
        this.collection = targetCollection;
    }

    @Override
    public void value(T object)
    {
        if (this.predicate.accept(object))
        {
            this.collection.add(this.function.valueOf(object));
        }
    }

    public Collection<V> getCollection()
    {
        return this.collection;
    }
}
