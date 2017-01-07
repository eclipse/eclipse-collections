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

import java.util.List;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.tuple.Tuples;

/**
 * CaseProcedure allows developers to create an object form of a case statement, which instead of being based on
 * a single switch value is based on a list of predicate / procedure combinations.  For the first predicate
 * that returns true for a given value in the case statement, the corresponding procedure will be executed.
 */
public final class CaseProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final List<Pair<Predicate<? super T>, Procedure<? super T>>> predicateProcedures = Lists.mutable.empty();
    private Procedure<? super T> defaultProcedure;

    public CaseProcedure(Procedure<? super T> defaultProcedure)
    {
        this.defaultProcedure = defaultProcedure;
    }

    public CaseProcedure()
    {
    }

    public CaseProcedure<T> addCase(Predicate<? super T> predicate, Procedure<? super T> procedure)
    {
        this.predicateProcedures.add(Tuples.pair(predicate, procedure));
        return this;
    }

    public CaseProcedure<T> setDefault(Procedure<? super T> procedure)
    {
        this.defaultProcedure = procedure;
        return this;
    }

    @Override
    public void value(T argument)
    {
        int localSize = this.predicateProcedures.size();
        for (int i = 0; i < localSize; i++)
        {
            Pair<Predicate<? super T>, Procedure<? super T>> pair = this.predicateProcedures.get(i);
            if (pair.getOne().accept(argument))
            {
                pair.getTwo().value(argument);
                return;
            }
        }
        if (this.defaultProcedure != null)
        {
            this.defaultProcedure.value(argument);
        }
    }

    @Override
    public String toString()
    {
        return "new CaseProcedure(" + this.predicateProcedures + ')';
    }
}
