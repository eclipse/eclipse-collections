/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import java.util.Collection;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.mutable.CompositeFastList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.utility.internal.DefaultSpeciesNewStrategy;

public abstract class AbstractTransformerBasedCombiner<V, T, BT extends Procedure<T>>
        extends AbstractProcedureCombiner<BT>
{
    private static final long serialVersionUID = 1L;

    protected final Collection<V> result;

    protected AbstractTransformerBasedCombiner(boolean useCombineOne, Collection<V> targetCollection, Iterable<T> iterable, int initialCapacity)
    {
        super(useCombineOne);
        this.result = this.initializeResult(iterable, targetCollection, initialCapacity);
    }

    protected Collection<V> initializeResult(Iterable<T> sourceIterable, Collection<V> targetCollection, int initialCapacity)
    {
        if (targetCollection != null)
        {
            return targetCollection;
        }
        if (sourceIterable instanceof ListIterable)
        {
            return new CompositeFastList<>();
        }
        if (sourceIterable instanceof SortedSetIterable)
        {
            return FastList.newList();
        }
        if (sourceIterable instanceof SetIterable)
        {
            this.setCombineOne(true);
            return UnifiedSet.newSet(initialCapacity);
        }
        if (sourceIterable instanceof Bag || sourceIterable instanceof MapIterable)
        {
            return HashBag.newBag(initialCapacity);
        }
        return this.createResultForCollection(sourceIterable, initialCapacity);
    }

    private Collection<V> createResultForCollection(Iterable<T> sourceCollection, int initialCapacity)
    {
        if (sourceCollection instanceof Collection)
        {
            return DefaultSpeciesNewStrategy.INSTANCE.speciesNew((Collection<?>) sourceCollection, initialCapacity);
        }
        return FastList.newList(initialCapacity);
    }

    public Collection<V> getResult()
    {
        return this.result;
    }
}
