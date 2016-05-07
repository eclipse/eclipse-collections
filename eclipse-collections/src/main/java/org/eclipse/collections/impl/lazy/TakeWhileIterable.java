/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.Iterator;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.lazy.iterator.TakeWhileIterator;

/**
 * Iterates over the elements of the adapted Iterable until the predicate returns false.
 *
 * @since 8.0
 */
public class TakeWhileIterable<T> extends AbstractLazyIterable<T>
{
    private final Iterable<T> adapted;
    private final Predicate<? super T> predicate;

    public TakeWhileIterable(Iterable<T> newAdapted, Predicate<? super T> predicate)
    {
        if (predicate == null)
        {
            throw new IllegalStateException("Predicate cannot be null");
        }
        this.adapted = newAdapted;
        this.predicate = predicate;
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        for (T each : this.adapted)
        {
            if (!this.predicate.accept(each))
            {
                return;
            }
            procedure.value(each);
        }
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> procedure)
    {
        int i = 0;
        for (T each : this.adapted)
        {
            if (!this.predicate.accept(each))
            {
                return;
            }
            procedure.value(each, i);
            i++;
        }
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        for (T each : this.adapted)
        {
            if (!this.predicate.accept(each))
            {
                return;
            }
            procedure.value(each, parameter);
        }
    }

    @Override
    public Iterator<T> iterator()
    {
        return new TakeWhileIterator<>(this.adapted, this.predicate);
    }
}
