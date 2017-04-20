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
 * Implementation of {@link Procedure} that holds on to the maximum element seen so far,
 * determined by the {@link Function}.
 */
public class MaxByProcedure<T, V extends Comparable<? super V>> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    protected final Function<? super T, ? extends V> function;
    protected boolean visitedAtLeastOnce;
    protected T result;
    protected V cachedResultValue;

    public MaxByProcedure(Function<? super T, ? extends V> function)
    {
        this.function = function;
    }

    public boolean isVisitedAtLeastOnce()
    {
        return this.visitedAtLeastOnce;
    }

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
            V eachValue = this.function.valueOf(each);
            if (eachValue.compareTo(this.cachedResultValue) > 0)
            {
                this.result = each;
                this.cachedResultValue = eachValue;
            }
        }
        else
        {
            this.visitedAtLeastOnce = true;
            this.result = each;
            this.cachedResultValue = this.function.valueOf(each);
        }
    }
}
