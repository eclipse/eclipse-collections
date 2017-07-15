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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

final class QuadrupletonSet<T>
        extends AbstractMemoryEfficientMutableSet<T>
        implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private T element1;
    private T element2;
    private T element3;
    private T element4;

    @SuppressWarnings("UnusedDeclaration")
    public QuadrupletonSet()
    {
        // For Externalizable use only
    }

    QuadrupletonSet(T obj1, T obj2, T obj3, T obj4)
    {
        this.element1 = obj1;
        this.element2 = obj2;
        this.element3 = obj3;
        this.element4 = obj4;
    }

    @Override
    public int size()
    {
        return 4;
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
        Set<?> collection = (Set<?>) o;
        return collection.size() == this.size()
                && collection.contains(this.element1)
                && collection.contains(this.element2)
                && collection.contains(this.element3)
                && collection.contains(this.element4);
    }

    @Override
    public int hashCode()
    {
        return this.nullSafeHashCode(this.element1)
                + this.nullSafeHashCode(this.element2)
                + this.nullSafeHashCode(this.element3)
                + this.nullSafeHashCode(this.element4);
    }

    // Weird implementation of clone() is ok on final classes
    @Override
    public QuadrupletonSet<T> clone()
    {
        return new QuadrupletonSet<>(this.element1, this.element2, this.element3, this.element4);
    }

    @Override
    public boolean contains(Object obj)
    {
        return Comparators.nullSafeEquals(obj, this.element1)
                || Comparators.nullSafeEquals(obj, this.element2)
                || Comparators.nullSafeEquals(obj, this.element3)
                || Comparators.nullSafeEquals(obj, this.element4);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new QuadrupletonSetIterator();
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

    T getThird()
    {
        return this.element3;
    }

    @Override
    public T getLast()
    {
        return this.element4;
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
        procedure.value(this.element4);
    }

    @Override
    public void forEachWithIndex(ObjectIntProcedure<? super T> objectIntProcedure)
    {
        objectIntProcedure.value(this.element1, 0);
        objectIntProcedure.value(this.element2, 1);
        objectIntProcedure.value(this.element3, 2);
        objectIntProcedure.value(this.element4, 3);
    }

    @Override
    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        procedure.value(this.element1, parameter);
        procedure.value(this.element2, parameter);
        procedure.value(this.element3, parameter);
        procedure.value(this.element4, parameter);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.element1);
        out.writeObject(this.element2);
        out.writeObject(this.element3);
        out.writeObject(this.element4);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.element1 = (T) in.readObject();
        this.element2 = (T) in.readObject();
        this.element3 = (T) in.readObject();
        this.element4 = (T) in.readObject();
    }

    protected class QuadrupletonSetIterator
            extends MemoryEfficientSetIterator
    {
        @Override
        protected T getElement(int i)
        {
            if (i == 0)
            {
                return QuadrupletonSet.this.element1;
            }
            if (i == 1)
            {
                return QuadrupletonSet.this.element2;
            }
            if (i == 2)
            {
                return QuadrupletonSet.this.element3;
            }
            if (i == 3)
            {
                return QuadrupletonSet.this.element4;
            }
            throw new NoSuchElementException("i=" + i);
        }
    }

    @Override
    public MutableSet<T> with(T element)
    {
        return this.contains(element) ? this : UnifiedSet.newSet(this).with(element);
    }

    @Override
    public MutableSet<T> without(T element)
    {
        if (Comparators.nullSafeEquals(element, this.element1))
        {
            return new TripletonSet<>(this.element2, this.element3, this.element4);
        }
        if (Comparators.nullSafeEquals(element, this.element2))
        {
            return new TripletonSet<>(this.element1, this.element3, this.element4);
        }
        if (Comparators.nullSafeEquals(element, this.element3))
        {
            return new TripletonSet<>(this.element1, this.element2, this.element4);
        }
        if (Comparators.nullSafeEquals(element, this.element4))
        {
            return new TripletonSet<>(this.element1, this.element2, this.element3);
        }
        return this;
    }
}
