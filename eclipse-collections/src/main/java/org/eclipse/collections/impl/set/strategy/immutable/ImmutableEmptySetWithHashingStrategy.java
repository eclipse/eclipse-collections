/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.strategy.immutable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.EmptyIterator;
import org.eclipse.collections.impl.factory.HashingStrategySets;
import org.eclipse.collections.impl.set.immutable.AbstractImmutableSet;

/**
 * This is a zero element {@link ImmutableUnifiedSetWithHashingStrategy} which is created by calling the HashingStrategySets.immutable.empty() method.
 */
final class ImmutableEmptySetWithHashingStrategy<T>
        extends AbstractImmutableSet<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final HashingStrategy<? super T> hashingStrategy;

    ImmutableEmptySetWithHashingStrategy(HashingStrategy<? super T> hashingStrategy)
    {
        this.hashingStrategy = hashingStrategy;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        return other instanceof Set && ((Collection<?>) other).isEmpty();
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public ImmutableSet<T> newWith(T element)
    {
        return HashingStrategySets.immutable.with(this.hashingStrategy, element);
    }

    @Override
    public ImmutableSet<T> newWithAll(Iterable<? extends T> elements)
    {
        return HashingStrategySets.immutable.withAll(this.hashingStrategy, elements);
    }

    @Override
    public ImmutableSet<T> newWithout(T element)
    {
        return this;
    }

    @Override
    public ImmutableSet<T> newWithoutAll(Iterable<? extends T> elements)
    {
        return this;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean contains(Object object)
    {
        return false;
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
    }

    @Override
    public Iterator<T> iterator()
    {
        return EmptyIterator.getInstance();
    }

    @Override
    public T min(Comparator<? super T> comparator)
    {
        throw new NoSuchElementException();
    }

    @Override
    public T max(Comparator<? super T> comparator)
    {
        throw new NoSuchElementException();
    }

    @Override
    public T min()
    {
        throw new NoSuchElementException();
    }

    @Override
    public T max()
    {
        throw new NoSuchElementException();
    }

    @Override
    public <V extends Comparable<? super V>> T minBy(Function<? super T, ? extends V> function)
    {
        throw new NoSuchElementException();
    }

    @Override
    public <V extends Comparable<? super V>> T maxBy(Function<? super T, ? extends V> function)
    {
        throw new NoSuchElementException();
    }

    @Override
    public <S, R extends Collection<Pair<T, S>>> R zip(Iterable<S> that, R target)
    {
        return target;
    }

    @Override
    public <R extends Collection<Pair<T, Integer>>> R zipWithIndex(R target)
    {
        return target;
    }

    @Override
    public T getFirst()
    {
        return null;
    }

    @Override
    public T getLast()
    {
        return null;
    }

    @Override
    public T getOnly()
    {
        throw new IllegalStateException("Size must be 1 but was " + this.size());
    }

    private Object writeReplace()
    {
        return new ImmutableSetWithHashingStrategySerializationProxy<>(this, this.hashingStrategy);
    }
}
