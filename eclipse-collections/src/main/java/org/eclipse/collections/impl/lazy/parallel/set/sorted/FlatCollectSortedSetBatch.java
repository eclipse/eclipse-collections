/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set.sorted;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.lazy.parallel.AbstractBatch;
import org.eclipse.collections.impl.lazy.parallel.list.CollectListBatch;
import org.eclipse.collections.impl.lazy.parallel.list.DistinctBatch;
import org.eclipse.collections.impl.lazy.parallel.list.FlatCollectListBatch;
import org.eclipse.collections.impl.lazy.parallel.list.ListBatch;
import org.eclipse.collections.impl.lazy.parallel.list.SelectListBatch;
import org.eclipse.collections.impl.lazy.parallel.set.UnsortedSetBatch;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.utility.Iterate;

@Beta
public class FlatCollectSortedSetBatch<T, V> extends AbstractBatch<V> implements ListBatch<V>
{
    private final SortedSetBatch<T> sortedSetBatch;
    private final Function<? super T, ? extends Iterable<V>> function;

    public FlatCollectSortedSetBatch(SortedSetBatch<T> sortedSetBatch, Function<? super T, ? extends Iterable<V>> function)
    {
        this.sortedSetBatch = sortedSetBatch;
        this.function = function;
    }

    @Override
    public void forEach(Procedure<? super V> procedure)
    {
        this.sortedSetBatch.forEach(each -> Iterate.forEach(this.function.valueOf(each), procedure));
    }

    @Override
    public ListBatch<V> select(Predicate<? super V> predicate)
    {
        return new SelectListBatch<>(this, predicate);
    }

    @Override
    public <VV> ListBatch<VV> collect(Function<? super V, ? extends VV> function)
    {
        return new CollectListBatch<>(this, function);
    }

    @Override
    public <VV> ListBatch<VV> flatCollect(Function<? super V, ? extends Iterable<VV>> function)
    {
        return new FlatCollectListBatch<>(this, function);
    }

    @Override
    public UnsortedSetBatch<V> distinct(ConcurrentHashMap<V, Boolean> distinct)
    {
        return new DistinctBatch<>(this, distinct);
    }
}
