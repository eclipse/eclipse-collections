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

import java.util.Comparator;
import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.IfProcedure;

@Beta
class ParallelSelectSortedSetIterable<T> extends AbstractParallelSortedSetIterable<T, SortedSetBatch<T>>
{
    private final AbstractParallelSortedSetIterable<T, ? extends SortedSetBatch<T>> parallelIterable;
    private final Predicate<? super T> predicate;

    ParallelSelectSortedSetIterable(AbstractParallelSortedSetIterable<T, ? extends SortedSetBatch<T>> parallelIterable, Predicate<? super T> predicate)
    {
        this.parallelIterable = parallelIterable;
        this.predicate = predicate;
    }

    @Override
    public Comparator<? super T> comparator()
    {
        return this.parallelIterable.comparator();
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
    public LazyIterable<SortedSetBatch<T>> split()
    {
        return this.parallelIterable.split().collect(eachBatch -> eachBatch.select(this.predicate));
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.parallelIterable.forEach(new IfProcedure<>(this.predicate, procedure));
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.parallelIterable.anySatisfy(Predicates.and(this.predicate, predicate));
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.parallelIterable.allSatisfy(new SelectAllSatisfyPredicate<>(this.predicate, predicate));
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return this.parallelIterable.detect(Predicates.and(this.predicate, predicate));
    }

    private static final class SelectAllSatisfyPredicate<T> implements Predicate<T>
    {
        private final Predicate<? super T> left;
        private final Predicate<? super T> right;

        private SelectAllSatisfyPredicate(Predicate<? super T> left, Predicate<? super T> right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean accept(T each)
        {
            boolean leftResult = this.left.accept(each);
            return !leftResult || this.right.accept(each);
        }
    }
}
