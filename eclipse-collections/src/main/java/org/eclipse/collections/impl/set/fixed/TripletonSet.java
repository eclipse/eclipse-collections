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

final class TripletonSet<T>
        extends AbstractMemoryEfficientMutableSet<T>
        implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private T element1;
    private T element2;
    private T element3;

    @SuppressWarnings("UnusedDeclaration")
    public TripletonSet()
    {
        // For Externalizable use only
    }

    TripletonSet(T obj1, T obj2, T obj3)
    {
        this.element1 = obj1;
        this.element2 = obj2;
        this.element3 = obj3;
    }

    @Override
    public int size()
    {
        return 3;
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
        Collection<?> collection = (Collection<?>) o;
        return collection.size() == this.size()
                && collection.contains(this.element1)
                && collection.contains(this.element2)
                && collection.contains(this.element3);
    }

    @Override
    public int hashCode()
    {
        return this.nullSafeHashCode(this.element1)
                + this.nullSafeHashCode(this.element2)
                + this.nullSafeHashCode(this.element3);
    }

    // Weird implementation of clone() is ok on final classes
    @Override
    public TripletonSet<T> clone()
    {
        return new TripletonSet<>(this.element1, this.element2, this.element3);
    }

    @Override
    public boolean contains(Object obj)
    {
        return Comparators.nullSafeEquals(obj, this.element1)
                || Comparators.nullSafeEquals(obj, this.element2)
                || Comparators.nullSafeEquals(obj, this.element3);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new TripletonSetIterator();
    }

    @Override
    public T getFirst()
    {
        return this.element1;
    }

    T getSecond()
    {
        return this.element2;
    }

    @Override
    public T getLast()
    {
        return this.element3;
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
        procedure.value(this.element3);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        objectIntProcedure.value(this.element1, 0);
        objectIntProcedure.value(this.element2, 1);
        objectIntProcedure.value(this.element3, 2);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        procedure.value(this.element1, parameter);
        procedure.value(this.element2, parameter);
        procedure.value(this.element3, parameter);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.element1);
        out.writeObject(this.element2);
        out.writeObject(this.element3);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.element1 = (T) in.readObject();
        this.element2 = (T) in.readObject();
        this.element3 = (T) in.readObject();
    }

    private class TripletonSetIterator
            extends MemoryEfficientSetIterator
    {
        @Override
        protected T getElement(int i)
        {
            if (i == 0)
            {
                return TripletonSet.this.element1;
            }
            if (i == 1)
            {
                return TripletonSet.this.element2;
            }
            if (i == 2)
            {
                return TripletonSet.this.element3;
            }
            throw new NoSuchElementException("i=" + i);
        }
    }

    @Override
    public MutableSet<T> with(T element)
    {
        return this.contains(element) ? this : new QuadrupletonSet<>(this.element1, this.element2, this.element3, element);
    }

    @Override
    public MutableSet<T> without(T element)
    {
        if (Comparators.nullSafeEquals(element, this.element1))
        {
            return new DoubletonSet<>(this.element2, this.element3);
        }
        if (Comparators.nullSafeEquals(element, this.element2))
        {
            return new DoubletonSet<>(this.element1, this.element3);
        }
        if (Comparators.nullSafeEquals(element, this.element3))
        {
            return new DoubletonSet<>(this.element1, this.element2);
        }
        return this;
    }
}
