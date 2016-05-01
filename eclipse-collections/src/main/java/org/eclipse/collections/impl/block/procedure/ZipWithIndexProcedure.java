/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.util.Collection;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

/**
 * Creates a PairImpl of objects and their indexes and adds the result to a target collection.
 */
public final class ZipWithIndexProcedure<T, R extends Collection<Pair<T, Integer>>>
        implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private int index = 0;
    private final R target;

    public ZipWithIndexProcedure(R target)
    {
        this.target = target;
    }

    public static <TT, RR extends Collection<Pair<TT, Integer>>> ZipWithIndexProcedure<TT, RR> create(RR target)
    {
        return new ZipWithIndexProcedure<>(target);
    }

    @Override
    public void value(T each)
    {
        this.target.add(Tuples.pair(each, Integer.valueOf(this.index)));
        this.index += 1;
    }
}
