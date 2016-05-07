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

import org.eclipse.collections.impl.block.procedure.CollectProcedure;

/**
 * Combines the results of a Collection of CollectBlocks which each hold onto a transformed (collect)
 * collection of results.
 */
public final class CollectProcedureCombiner<T, V>
        extends AbstractTransformerBasedCombiner<V, T, CollectProcedure<T, V>>
{
    private static final long serialVersionUID = 1L;

    public CollectProcedureCombiner(
            Iterable<T> sourceIterable,
            Collection<V> targetCollection,
            int initialCapacity,
            boolean combineOne)
    {
        super(combineOne, targetCollection, sourceIterable, initialCapacity);
    }

    @Override
    public void combineOne(CollectProcedure<T, V> procedure)
    {
        this.result.addAll(procedure.getCollection());
    }
}
