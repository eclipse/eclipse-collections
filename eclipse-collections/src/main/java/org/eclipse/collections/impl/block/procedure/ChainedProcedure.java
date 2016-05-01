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

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.list.mutable.FastList;

/**
 * ChainedProcedure allows a developer to chain together procedure to be executed in sequence.
 */
public final class ChainedProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final List<Procedure<? super T>> procedures = FastList.newList(3);

    public static <E> ChainedProcedure<E> with(Procedure<? super E> procedure1, Procedure<? super E> procedure2)
    {
        ChainedProcedure<E> chainedProcedure = new ChainedProcedure<>();
        chainedProcedure.addProcedure(procedure1);
        chainedProcedure.addProcedure(procedure2);
        return chainedProcedure;
    }

    public void addProcedure(Procedure<? super T> procedure)
    {
        this.procedures.add(procedure);
    }

    @Override
    public void value(T object)
    {
        int size = this.procedures.size();
        for (int i = 0; i < size; i++)
        {
            this.procedures.get(i).value(object);
        }
    }

    @Override
    public String toString()
    {
        return "ChainedProcedure.with(" + this.procedures + ')';
    }
}
