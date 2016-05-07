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

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.block.factory.Predicates;

/**
 * Applies a predicate to an object and increments a count if it returns true.
 */
public class CountProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Predicate<? super T> predicate;
    private int count;

    public CountProcedure(Predicate<? super T> newPredicate)
    {
        this.predicate = newPredicate;
    }

    public CountProcedure()
    {
        this(Predicates.alwaysTrue());
    }

    @Override
    public void value(T object)
    {
        if (this.predicate.accept(object))
        {
            this.count++;
        }
    }

    public int getCount()
    {
        return this.count;
    }
}
