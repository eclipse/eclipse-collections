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
import org.eclipse.collections.impl.lazy.iterator.TapIterator;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * A TapIterable is an iterable that executes a procedure for each element before each iteration.
 */
public class TapIterable<T>
        extends AbstractLazyIterable<T>
{
    private final Iterable<T> adapted;
    private final Procedure<? super T> procedure;

    public TapIterable(Iterable<T> newAdapted, Procedure<? super T> procedure)
    {
        this.adapted = newAdapted;
        this.procedure = procedure;
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        Iterate.forEach(this.adapted, each ->
        {
            this.procedure.value(each);
            procedure.value(each);
        });
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        Iterate.forEachWithIndex(this.adapted, (each, index) ->
        {
            this.procedure.value(each);
            objectIntProcedure.value(each, index);
        });
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        Iterate.forEachWith(this.adapted, (each, aParameter) ->
        {
            this.procedure.value(each);
            procedure.value(each, aParameter);
        }, parameter);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return Iterate.anySatisfy(this.adapted, each ->
        {
            this.procedure.value(each);
            return predicate.accept(each);
        });
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return Iterate.allSatisfy(this.adapted, each ->
        {
            this.procedure.value(each);
            return predicate.accept(each);
        });
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return Iterate.noneSatisfy(this.adapted, each ->
        {
            this.procedure.value(each);
            return predicate.accept(each);
        });
    }

    @Override
    public T getFirst()
    {
        return Iterate.detect(this.adapted, each ->
        {
            this.procedure.value(each);
            return true;
        });
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return Iterate.detect(this.adapted, each ->
        {
            this.procedure.value(each);
            return predicate.accept(each);
        });
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return Iterate.detectOptional(this.adapted, each ->
        {
            this.procedure.value(each);
            return predicate.accept(each);
        });
    }

    @Override
    public Iterator<T> iterator()
    {
        return new TapIterator<>(this.adapted, this.procedure);
    }
}
