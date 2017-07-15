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

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.procedure.AdaptObjectIntProcedureToProcedure;
import org.eclipse.collections.impl.lazy.iterator.DistinctIterator;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * A DistinctIterable is an iterable that eliminates duplicates from a source iterable as it iterates.
 *
 * @since 5.0
 */
public class DistinctIterable<T>
        extends AbstractLazyIterable<T>
{
    private final Iterable<T> adapted;

    public DistinctIterable(Iterable<T> newAdapted)
    {
        this.adapted = newAdapted;
    }

    @Override
    public LazyIterable<T> distinct()
    {
        return this;
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        MutableSet<T> seenSoFar = UnifiedSet.newSet();

        Iterate.forEach(this.adapted, each ->
        {
            if (seenSoFar.add(each))
            {
                procedure.value(each);
            }
        });
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        this.each(new AdaptObjectIntProcedureToProcedure<>(objectIntProcedure));
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        MutableSet<T> seenSoFar = UnifiedSet.newSet();

        return Iterate.anySatisfy(this.adapted, each -> seenSoFar.add(each) && predicate.accept(each));
    }

    @Override
    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        MutableSet<T> seenSoFar = UnifiedSet.newSet();

        return Iterate.allSatisfy(this.adapted, each -> !seenSoFar.add(each) || predicate.accept(each));
    }

    @Override
    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        MutableSet<T> seenSoFar = UnifiedSet.newSet();

        return Iterate.allSatisfy(this.adapted, each -> !seenSoFar.add(each) || !predicate.accept(each));
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        MutableSet<T> seenSoFar = UnifiedSet.newSet();

        return Iterate.detect(this.adapted, each -> seenSoFar.add(each) && predicate.accept(each));
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        MutableSet<T> seenSoFar = UnifiedSet.newSet();

        return Iterate.detectOptional(this.adapted, each -> seenSoFar.add(each) && predicate.accept(each));
    }

    @Override
    public Iterator<T> iterator()
    {
        return new DistinctIterator<>(this.adapted);
    }
}
