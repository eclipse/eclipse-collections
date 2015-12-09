/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.lazy.parallel.list;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.lazy.parallel.AbstractBatch;
import org.eclipse.collections.impl.lazy.parallel.Batch;
import org.eclipse.collections.impl.lazy.parallel.set.UnsortedSetBatch;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.utility.Iterate;

@Beta
public class FlatCollectListBatch<T, V> extends AbstractBatch<V> implements ListBatch<V>
{
    private final Batch<T> batch;
    private final Function<? super T, ? extends Iterable<V>> function;

    public FlatCollectListBatch(Batch<T> batch, Function<? super T, ? extends Iterable<V>> function)
    {
        this.batch = batch;
        this.function = function;
    }

    public void forEach(final Procedure<? super V> procedure)
    {
        this.batch.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                Iterate.forEach(FlatCollectListBatch.this.function.valueOf(each), procedure);
            }
        });
    }

    public ListBatch<V> select(Predicate<? super V> predicate)
    {
        return new SelectListBatch<V>(this, predicate);
    }

    public <VV> ListBatch<VV> collect(Function<? super V, ? extends VV> function)
    {
        return new CollectListBatch<V, VV>(this, function);
    }

    public <VV> ListBatch<VV> flatCollect(Function<? super V, ? extends Iterable<VV>> function)
    {
        return new FlatCollectListBatch<V, VV>(this, function);
    }

    public UnsortedSetBatch<V> distinct(ConcurrentHashMap<V, Boolean> distinct)
    {
        return new DistinctBatch<V>(this, distinct);
    }
}
