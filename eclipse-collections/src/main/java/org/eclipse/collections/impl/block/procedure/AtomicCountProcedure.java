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

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * Applies a predicate to an object and increments a count if it returns true.
 */
public class AtomicCountProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Predicate<? super T> predicate;
    private final AtomicInteger count = new AtomicInteger(0);

    public AtomicCountProcedure(Predicate<? super T> predicate)
    {
        this.predicate = predicate;
    }

    @Override
    public void value(T object)
    {
        if (this.predicate.accept(object))
        {
            this.count.incrementAndGet();
        }
    }

    public int getCount()
    {
        return this.count.get();
    }
}
