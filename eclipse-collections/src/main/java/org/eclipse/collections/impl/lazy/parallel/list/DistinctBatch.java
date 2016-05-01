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
import org.eclipse.collections.impl.lazy.parallel.AbstractBatch;
import org.eclipse.collections.impl.lazy.parallel.Batch;
import org.eclipse.collections.impl.lazy.parallel.bag.CollectUnsortedBagBatch;
import org.eclipse.collections.impl.lazy.parallel.bag.FlatCollectUnsortedBagBatch;
import org.eclipse.collections.impl.lazy.parallel.bag.UnsortedBagBatch;
import org.eclipse.collections.impl.lazy.parallel.set.SelectUnsortedSetBatch;
import org.eclipse.collections.impl.lazy.parallel.set.UnsortedSetBatch;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

@Beta
public class DistinctBatch<T> extends AbstractBatch<T> implements UnsortedSetBatch<T>
{
    private final Batch<T> batch;
    private final ConcurrentHashMap<T, Boolean> distinct;

    public DistinctBatch(Batch<T> batch, ConcurrentHashMap<T, Boolean> distinct)
    {
        this.batch = batch;
        this.distinct = distinct;
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.batch.forEach(each -> {
            if (this.distinct.put(each, true) == null)
            {
                procedure.value(each);
            }
        });
    }

    @Override
    public UnsortedSetBatch<T> select(Predicate<? super T> predicate)
    {
        return new SelectUnsortedSetBatch<>(this, predicate);
    }

    @Override
    public <V> UnsortedBagBatch<V> collect(Function<? super T, ? extends V> function)
    {
        return new CollectUnsortedBagBatch<>(this, function);
    }

    @Override
    public <V> UnsortedBagBatch<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        return new FlatCollectUnsortedBagBatch<>(this, function);
    }
}
