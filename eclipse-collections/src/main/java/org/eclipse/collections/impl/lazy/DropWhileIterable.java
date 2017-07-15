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
import java.util.Optional;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.predicate.DropWhileIterablePredicate;
import org.eclipse.collections.impl.block.procedure.IfObjectIntProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedureWith;
import org.eclipse.collections.impl.lazy.iterator.SelectIterator;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * Iterates over the elements of the adapted Iterable skipping the first elements until the predicate returns false.
 *
 * @since 8.0
 */
public class DropWhileIterable<T> extends AbstractLazyIterable<T>
{
    private final Iterable<T> adapted;
    private final Predicate<? super T> predicate;

    public DropWhileIterable(Iterable<T> newAdapted, Predicate<? super T> predicate)
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
        Iterate.forEach(this.adapted, new IfProcedure<>(new DropWhileIterablePredicate<>(this.predicate), procedure));
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        Iterate.forEach(this.adapted, new IfObjectIntProcedure<>(new DropWhileIterablePredicate<>(this.predicate), objectIntProcedure));
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        Iterate.forEachWith(this.adapted, new IfProcedureWith<>(new DropWhileIterablePredicate<>(this.predicate), procedure), parameter);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return Iterate.anySatisfy(this.adapted, Predicates.and(new DropWhileIterablePredicate<>(this.predicate), predicate));
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return Iterate.allSatisfy(this.adapted, Predicates.or(Predicates.not(new DropWhileIterablePredicate<>(this.predicate)), predicate));
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return Iterate.noneSatisfy(this.adapted, Predicates.and(new DropWhileIterablePredicate<>(this.predicate), predicate));
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return Iterate.detect(this.adapted, Predicates.and(new DropWhileIterablePredicate<>(this.predicate), predicate));
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return Iterate.detectOptional(this.adapted, Predicates.and(new DropWhileIterablePredicate<>(this.predicate), predicate));
    }

    @Override
    public Iterator<T> iterator()
    {
        return new SelectIterator<>(this.adapted.iterator(), new DropWhileIterablePredicate<>(this.predicate));
    }
}
