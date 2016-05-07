/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.list;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.procedure.IfProcedure;
import org.eclipse.collections.impl.lazy.parallel.AbstractBatch;
import org.eclipse.collections.impl.lazy.parallel.set.UnsortedSetBatch;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

@Beta
public class SelectListBatch<T> extends AbstractBatch<T> implements ListBatch<T>
{
    private final ListBatch<T> listBatch;
    private final Predicate<? super T> predicate;

    public SelectListBatch(ListBatch<T> listBatch, Predicate<? super T> predicate)
    {
        this.listBatch = listBatch;
        this.predicate = predicate;
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.listBatch.forEach(new IfProcedure<>(this.predicate, procedure));
    }

    /*
    public ListBatch<T> select(Predicate<? super T> predicate)
    {
        return new SelectListBatch<T>(this.listBatch, Predicates.and(this.predicate, predicate));
    }
    */

    @Override
    public ListBatch<T> select(Predicate<? super T> predicate)
    {
        return new SelectListBatch<>(this, predicate);
    }

    @Override
    public <V> ListBatch<V> collect(Function<? super T, ? extends V> function)
    {
        return new CollectListBatch<>(this, function);
    }

    @Override
    public <V> ListBatch<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new FlatCollectListBatch<>(this, function);
    }

    @Override
    public UnsortedSetBatch<T> distinct(ConcurrentHashMap<T, Boolean> distinct)
    {
        return new DistinctBatch<>(this, distinct);
    }
}
