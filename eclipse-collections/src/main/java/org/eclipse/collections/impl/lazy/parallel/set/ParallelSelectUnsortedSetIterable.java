/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.set;

import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.IfProcedure;

@Beta
class ParallelSelectUnsortedSetIterable<T> extends AbstractParallelUnsortedSetIterable<T, UnsortedSetBatch<T>>
{
    private final AbstractParallelUnsortedSetIterable<T, ? extends UnsortedSetBatch<T>> delegate;
    private final Predicate<? super T> predicate;

    ParallelSelectUnsortedSetIterable(AbstractParallelUnsortedSetIterable<T, ? extends UnsortedSetBatch<T>> delegate, Predicate<? super T> predicate)
    {
        this.delegate = delegate;
        this.predicate = predicate;
    }

    @Override
    public ExecutorService getExecutorService()
    {
        return this.delegate.getExecutorService();
    }

    @Override
    public int getBatchSize()
    {
        return this.delegate.getBatchSize();
    }

    @Override
    public LazyIterable<UnsortedSetBatch<T>> split()
    {
        return this.delegate.split().collect(eachBatch -> eachBatch.select(this.predicate));
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.delegate.forEach(new IfProcedure<>(this.predicate, procedure));
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.delegate.anySatisfy(Predicates.and(this.predicate, predicate));
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.delegate.allSatisfy(new SelectAllSatisfyPredicate<>(this.predicate, predicate));
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return this.delegate.detect(Predicates.and(this.predicate, predicate));
    }

    @Override
    public Object[] toArray()
    {
        // TODO: Implement in parallel
        return this.delegate.toList().select(this.predicate).toArray();
    }

    @Override
    public <E> E[] toArray(E[] array)
    {
        return this.delegate.toList().select(this.predicate).toArray(array);
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
