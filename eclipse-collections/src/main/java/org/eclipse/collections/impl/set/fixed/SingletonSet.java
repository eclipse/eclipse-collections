/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.fixed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.Comparators;

class SingletonSet<T>
        extends AbstractMemoryEfficientMutableSet<T>
        implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private T element1;

    SingletonSet(T obj1)
    {
        this.element1 = obj1;
    }

    @SuppressWarnings("UnusedDeclaration")
    public SingletonSet()
    {
        // For Externalizable use only
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }

        if (!(o instanceof Set<?>))
        {
            return false;
        }
        Collection<?> collection = (Collection<?>) o;
        return collection.size() == this.size() && collection.contains(this.element1);
    }

    @Override
    public int hashCode()
    {
        return this.nullSafeHashCode(this.element1);
    }

    // Weird implementation of clone() is ok on final classes
    @Override
    public SingletonSet<T> clone()
    {
        return new SingletonSet<>(this.element1);
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
            extends MemoryEfficientSetIterator
    {
        @Override
        protected T getElement(int i)
        {
            if (i == 0)
            {
                return SingletonSet.this.element1;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.element1);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.element1 = (T) in.readObject();
    }

    @Override
    public MutableSet<T> with(T element)
    {
        return this.contains(element) ? this : new DoubletonSet<>(this.element1, element);
    }

    @Override
    public MutableSet<T> without(T element)
    {
        if (Comparators.nullSafeEquals(element, this.element1))
        {
            return new EmptySet<>();
        }
        return this;
    }
}
