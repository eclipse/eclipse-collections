/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.Iterator;

import net.jcip.annotations.Immutable;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.predicate.DropIterablePredicate;
import org.eclipse.collections.impl.block.procedure.IfObjectIntProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedureWith;
import org.eclipse.collections.impl.lazy.iterator.DropIterator;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * Iterates over the elements of the adapted Iterable skipping the first count elements or the full adapted Iterable if
 * the count is non-positive.
 */
@Immutable
public class DropIterable<T> extends AbstractLazyIterable<T>
{
    private final Iterable<T> adapted;
    private final int count;

    public DropIterable(Iterable<T> newAdapted, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        this.adapted = newAdapted;
        this.count = count;
    }

    public void each(Procedure<? super T> procedure)
    {
        Iterate.forEach(this.adapted, new IfProcedure<T>(new DropIterablePredicate<T>(this.count), procedure));
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        Iterate.forEach(this.adapted, new IfObjectIntProcedure<T>(new DropIterablePredicate<T>(this.count), objectIntProcedure));
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        Iterate.forEachWith(this.adapted, new IfProcedureWith<T, P>(new DropIterablePredicate<T>(this.count), procedure), parameter);
    }

    public Iterator<T> iterator()
    {
        return new DropIterator<T>(this.adapted, this.count);
    }
}
