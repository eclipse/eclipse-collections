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
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.impl.lazy.parallel.AbstractBatch;
import org.eclipse.collections.impl.lazy.parallel.set.UnsortedSetBatch;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

@Beta
public class ListIterableBatch<T> extends AbstractBatch<T> implements RootListBatch<T>
{
    private final ListIterable<T> list;
    private final int chunkStartIndex;
    private final int chunkEndIndex;

    public ListIterableBatch(ListIterable<T> list, int chunkStartIndex, int chunkEndIndex)
    {
        this.list = list;
        this.chunkStartIndex = chunkStartIndex;
        this.chunkEndIndex = chunkEndIndex;
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        for (int i = this.chunkStartIndex; i < this.chunkEndIndex; i++)
        {
            procedure.value(this.list.get(i));
        }
    }

    @Override
    public int count(Predicate<? super T> predicate)
    {
        int count = 0;
        for (int i = this.chunkStartIndex; i < this.chunkEndIndex; i++)
        {
            if (predicate.accept(this.list.get(i)))
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        for (int i = this.chunkStartIndex; i < this.chunkEndIndex; i++)
        {
            if (predicate.accept(this.list.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        for (int i = this.chunkStartIndex; i < this.chunkEndIndex; i++)
        {
            if (!predicate.accept(this.list.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        for (int i = this.chunkStartIndex; i < this.chunkEndIndex; i++)
        {
            if (predicate.accept(this.list.get(i)))
            {
                return this.list.get(i);
            }
        }
        return null;
    }

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
