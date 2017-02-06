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

import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.lazy.parallel.AbstractParallelIterable;
import org.eclipse.collections.impl.lazy.parallel.OrderedBatch;

@Beta
public class ParallelCollectListIterable<T, V> extends AbstractParallelListIterable<V, ListBatch<V>>
{
    private final AbstractParallelIterable<T, ? extends OrderedBatch<T>> parallelIterable;
    private final Function<? super T, ? extends V> function;

    public ParallelCollectListIterable(AbstractParallelIterable<T, ? extends OrderedBatch<T>> parallelIterable, Function<? super T, ? extends V> function)
    {
        this.parallelIterable = parallelIterable;
        this.function = function;
    }

    @Override
    public ExecutorService getExecutorService()
    {
        return this.parallelIterable.getExecutorService();
    }

    @Override
    public int getBatchSize()
    {
        return this.parallelIterable.getBatchSize();
    }

    @Override
    public LazyIterable<ListBatch<V>> split()
    {
        return this.parallelIterable.split().collect(eachBatch -> eachBatch.collect(this.function));
    }

    @Override
    public void forEach(Procedure<? super V> procedure)
    {
        this.parallelIterable.forEach(Functions.bind(procedure, this.function));
    }

    @Override
    public boolean anySatisfy(Predicate<? super V> predicate)
    {
        return this.parallelIterable.anySatisfy(Predicates.attributePredicate(this.function, predicate));
    }

    @Override
    public boolean allSatisfy(Predicate<? super V> predicate)
    {
        return this.parallelIterable.allSatisfy(Predicates.attributePredicate(this.function, predicate));
    }

    @Override
    public V detect(Predicate<? super V> predicate)
    {
        T resultItem = this.parallelIterable.detect(Predicates.attributePredicate(this.function, predicate));
        return resultItem == null ? null : this.function.valueOf(resultItem);
    }
}
