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

import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.lazy.parallel.AbstractBatch;
import org.eclipse.collections.impl.lazy.parallel.bag.CollectUnsortedBagBatch;
import org.eclipse.collections.impl.lazy.parallel.bag.FlatCollectUnsortedBagBatch;
import org.eclipse.collections.impl.lazy.parallel.bag.UnsortedBagBatch;

@Beta
public class CollectUnsortedSetBatch<T, V> extends AbstractBatch<V> implements UnsortedSetBatch<V>
{
    private final UnsortedSetBatch<T> unsortedSetBatch;
    private final Function<? super T, ? extends V> function;

    public CollectUnsortedSetBatch(UnsortedSetBatch<T> unsortedSetBatch, Function<? super T, ? extends V> function)
    {
        this.unsortedSetBatch = unsortedSetBatch;
        this.function = function;
    }

    @Override
    public void forEach(Procedure<? super V> procedure)
    {
        this.unsortedSetBatch.forEach(Functions.bind(procedure, this.function));
    }

    /*
    public <VV> SetBatch<VV> collect(Function<? super V, ? extends VV> function)
    {
        return new CollectSetBatch<T, VV>(this.setBatch, Functions.chain(this.function, function));
    }
    */

    @Override
    public UnsortedSetBatch<V> select(Predicate<? super V> predicate)
    {
        return new SelectUnsortedSetBatch<>(this, predicate);
    }

    @Override
    public <VV> UnsortedBagBatch<VV> collect(Function<? super V, ? extends VV> function)
    {
        return new CollectUnsortedBagBatch<>(this, function);
    }

    @Override
    public <V1> UnsortedBagBatch<V1> flatCollect(Function<? super V, ? extends Iterable<V1>> function)
    {
        return new FlatCollectUnsortedBagBatch<>(this, function);
    }
}
