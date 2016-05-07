/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.bag;

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedure;
import org.eclipse.collections.impl.lazy.parallel.AbstractBatch;

@Beta
public class SelectUnsortedBagBatch<T> extends AbstractBatch<T> implements UnsortedBagBatch<T>
{
    private final UnsortedBagBatch<T> unsortedBagBatch;
    private final Predicate<? super T> predicate;

    public SelectUnsortedBagBatch(UnsortedBagBatch<T> unsortedBagBatch, Predicate<? super T> predicate)
    {
        this.unsortedBagBatch = unsortedBagBatch;
        this.predicate = predicate;
    }

    @Override
    public void forEach(Procedure<? super T> procedure)
    {
        this.unsortedBagBatch.forEach(new IfProcedure<>(this.predicate, procedure));
    }

    @Override
    public void forEachWithOccurrences(ObjectIntProcedure<? super T> procedure)
    {
        this.unsortedBagBatch.forEachWithOccurrences(new IfProcedureWithOccurrences<>(this.predicate, procedure));
    }

    @Override
    public UnsortedBagBatch<T> select(Predicate<? super T> predicate)
    {
        return new SelectUnsortedBagBatch<>(this, predicate);
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

    /*
    public BagBatch<T> select(Predicate<? super T> predicate)
    {
        return new SelectBagBatch<T>(this.bagBatch, Predicates.and(this.predicate, predicate));
    }
    */

    private static final class IfProcedureWithOccurrences<T> implements ObjectIntProcedure<T>
    {
        private final Predicate<? super T> predicate;
        private final ObjectIntProcedure<? super T> procedure;

        private IfProcedureWithOccurrences(Predicate<? super T> predicate, ObjectIntProcedure<? super T> procedure)
        {
            this.predicate = predicate;
            this.procedure = procedure;
        }

        @Override
        public void value(T each, int parameter)
        {
            if (this.predicate.accept(each))
            {
                this.procedure.value(each, parameter);
            }
        }
    }
}
