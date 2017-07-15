/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.factory.Sets;

final class ImmutableSingletonSet<T>
        extends AbstractImmutableSet<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final T element1;

    ImmutableSingletonSet(T obj1)
    {
        this.element1 = obj1;
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if (!(other instanceof Set))
        {
            return false;
        }
        Set<?> set = (Set<?>) other;
        return set.size() == this.size() && set.contains(this.element1);
    }

    @Override
    public int hashCode()
    {
        return this.nullSafeHashCode(this.element1);
    }

    @Override
    public ImmutableSet<T> newWith(T element)
    {
        if (!this.contains(element))
        {
            return Sets.immutable.with(this.element1, element);
        }
        return this;
    }

    @Override
    public ImmutableSet<T> newWithout(T element)
    {
        if (this.contains(element))
        {
            return Sets.immutable.empty();
        }
        return this;
    }

    @Override
    public boolean contains(Object obj)
    {
        return Comparators.nullSafeEquals(obj, this.element1);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new SingletonSetIterator();
    }

    private class SingletonSetIterator
            extends ImmutableSetIterator
    {
        @Override
        protected T getElement(int i)
        {
            if (i == 0)
            {
                return ImmutableSingletonSet.this.element1;
            }
            throw new NoSuchElementException("i=" + i);
        }
    }

    @Override
    public T getFirst()
    {
        return this.element1;
    }

    @Override
    public T getLast()
    {
        return this.element1;
    }

    @Override
    public T getOnly()
    {
        return this.element1;
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        procedure.value(this.element1);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        objectIntProcedure.value(this.element1, 0);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        procedure.value(this.element1, parameter);
    }

    private Object writeReplace()
    {
        return new ImmutableSetSerializationProxy<>(this);
    }
}
