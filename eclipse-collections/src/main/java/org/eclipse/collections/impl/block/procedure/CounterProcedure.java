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

import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * CounterProcedure wraps a specified procedure and keeps track of the number of times it is executed.
 */
public final class CounterProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private int count = 0;
    private final Procedure<T> procedure;

    public CounterProcedure(Procedure<T> procedure)
    {
        this.procedure = procedure;
    }

    @Override
    public void value(T object)
    {
        this.incrementCounter();
        this.procedure.value(object);
    }

    private void incrementCounter()
    {
        this.count++;
    }

    public int getCount()
    {
        return this.count;
    }

    @Override
    public String toString()
    {
        return "counter: " + this.count + " procedure: " + this.procedure;
    }
}
