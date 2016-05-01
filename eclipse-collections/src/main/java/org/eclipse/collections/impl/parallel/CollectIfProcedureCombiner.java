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

import org.eclipse.collections.impl.block.procedure.CollectIfProcedure;

/**
 * Combines the results of a Collection of CollectIfBlocks which each hold onto a transformed and filtered (collect, if)
 * collection of results.
 */
public final class CollectIfProcedureCombiner<T, V>
        extends AbstractTransformerBasedCombiner<V, T, CollectIfProcedure<T, V>>
{
    private static final long serialVersionUID = 1L;

    public CollectIfProcedureCombiner(
            Iterable<T> iterable,
            Collection<V> targetCollection,
            int initialCapacity,
            boolean combineOne)
    {
        super(combineOne, targetCollection, iterable, initialCapacity);
    }

    @Override
    public void combineOne(CollectIfProcedure<T, V> procedureCombine)
    {
        this.result.addAll(procedureCombine.getCollection());
    }
}
