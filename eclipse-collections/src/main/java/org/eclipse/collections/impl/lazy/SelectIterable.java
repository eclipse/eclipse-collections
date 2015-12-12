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
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.IfObjectIntProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedure;
import org.eclipse.collections.impl.block.procedure.IfProcedureWith;
import org.eclipse.collections.impl.lazy.iterator.SelectIterator;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * A SelectIterable is an iterable that filters a source iterable on a condition as it iterates.
 */
@Immutable
public class SelectIterable<T>
        extends AbstractLazyIterable<T>
{
    private final Iterable<T> adapted;
    private final Predicate<? super T> predicate;

    public SelectIterable(Iterable<T> newAdapted, Predicate<? super T> newPredicate)
    {
        this.adapted = newAdapted;
        this.predicate = newPredicate;
    }

    public void each(Procedure<? super T> procedure)
    {
        Iterate.forEach(this.adapted, new IfProcedure<T>(this.predicate, procedure));
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        Iterate.forEach(this.adapted, new IfObjectIntProcedure<T>(this.predicate, objectIntProcedure));
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        Iterate.forEachWith(this.adapted, new IfProcedureWith<T, P>(this.predicate, procedure), parameter);
    }

    public Iterator<T> iterator()
    {
        return new SelectIterator<T>(this.adapted.iterator(), this.predicate);
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return Iterate.anySatisfy(this.adapted, Predicates.and(this.predicate, predicate));
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.anySatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return Iterate.allSatisfy(this.adapted, new AllSatisfyPredicate<T>(this.predicate, predicate));
    }

    @Override
    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.allSatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return Iterate.noneSatisfy(this.adapted, new AllSatisfyPredicate<T>(this.predicate, predicate));
    }

    @Override
    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.noneSatisfy(Predicates.bind(predicate, parameter));
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return Iterate.detect(this.adapted, Predicates.and(this.predicate, predicate));
    }

    @Override
    public T getFirst()
    {
        return Iterate.detect(this.adapted, this.predicate);
    }
}
