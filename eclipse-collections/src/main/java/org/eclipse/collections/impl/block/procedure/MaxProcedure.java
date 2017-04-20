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

import java.util.NoSuchElementException;
import java.util.Optional;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * Implementation of {@link Procedure} that holds on to the minimum element seen so far,
 * determined by the {@link Function}.
 */
public class MaxProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    protected boolean visitedAtLeastOnce;
    protected T result;

    public T getResult()
    {
        if (!this.visitedAtLeastOnce)
        {
            throw new NoSuchElementException();
        }
        return this.result;
    }

    public Optional<T> getResultOptional()
    {
        if (!this.visitedAtLeastOnce)
        {
            return Optional.empty();
        }
        return Optional.of(this.result);
    }

    @Override
    public void value(T each)
    {
        if (this.visitedAtLeastOnce)
        {
            if (((Comparable<T>) each).compareTo(this.result) > 0)
            {
                this.result = each;
            }
        }
        else
        {
            this.visitedAtLeastOnce = true;
            this.result = each;
        }
    }
}
