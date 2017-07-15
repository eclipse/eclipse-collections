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

final class ImmutableDoubletonSet<T>
        extends AbstractImmutableSet<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final T element1;
    private final T element2;

    ImmutableDoubletonSet(T obj1, T obj2)
    {
        this.element1 = obj1;
        this.element2 = obj2;
    }

    @Override
    public int size()
    {
        return 2;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }

        if (!(o instanceof Set))
        {
            return false;
        }
        Set<?> set = (Set<?>) o;
        return set.size() == this.size()
                && set.contains(this.element1)
                && set.contains(this.element2);
    }

    @Override
    public int hashCode()
    {
        return this.nullSafeHashCode(this.element1) + this.nullSafeHashCode(this.element2);
    }

    @Override
    public ImmutableSet<T> newWith(T element)
    {
        if (!this.contains(element))
        {
            return Sets.immutable.with(this.element1, this.element2, element);
        }
        return this;
    }

    @Override
    public ImmutableSet<T> newWithout(T element)
    {
        if (this.contains(element))
        {
            return Comparators.nullSafeEquals(element, this.element1)
                    ? Sets.immutable.with(this.element2)
                    : Sets.immutable.with(this.element1);
        }
        return this;
    }

    @Override
    public boolean contains(Object obj)
    {
        return Comparators.nullSafeEquals(obj, this.element1) || Comparators.nullSafeEquals(obj, this.element2);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new DoubletonSetIterator();
    }

    @Override
    public T getFirst()
    {
        return this.element1;
    }

    @Override
    public T getLast()
    {
        return this.element2;
    }

    @Override
    public T getOnly()
    {
        throw new IllegalStateException("Size must be 1 but was " + this.size());
    }

    @Override
    public void each(Procedure<? super T> procedure)
    {
        procedure.value(this.element1);
        procedure.value(this.element2);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        objectIntProcedure.value(this.element1, 0);
        objectIntProcedure.value(this.element2, 1);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        procedure.value(this.element1, parameter);
        procedure.value(this.element2, parameter);
    }

    private class DoubletonSetIterator
            extends ImmutableSetIterator
    {
        @Override
        protected T getElement(int i)
        {
            if (i == 0)
            {
                return ImmutableDoubletonSet.this.element1;
            }
            if (i == 1)
            {
                return ImmutableDoubletonSet.this.element2;
            }
            throw new NoSuchElementException("i=" + i);
        }
    }

    private Object writeReplace()
    {
        return new ImmutableSetSerializationProxy<>(this);
    }
}
