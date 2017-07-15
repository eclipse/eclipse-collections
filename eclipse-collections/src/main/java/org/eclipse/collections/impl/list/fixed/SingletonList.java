/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Comparator;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Comparators;

/**
 * This class is a memory efficient list with one element.  Unlike Collections.singletonList(), it can be sorted.  It is
 * normally created by calling Lists.fixedSize.of(one).
 */
final class SingletonList<T>
        extends AbstractMemoryEfficientMutableList<T>
        implements Externalizable
{
    private static final long serialVersionUID = 1L;

    private T element1;

    @SuppressWarnings("UnusedDeclaration")
    public SingletonList()
    {
        // For Externalizable use only
    }

    SingletonList(T obj1)
    {
        this.element1 = obj1;
    }

    @Override
    public DoubletonList<T> with(T value)
    {
        return new DoubletonList<>(this.element1, value);
    }

    // Weird implementation of clone() is ok on final classes

    @Override
    public SingletonList<T> clone()
    {
        return new SingletonList<>(this.element1);
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public boolean contains(Object obj)
    {
        return Comparators.nullSafeEquals(obj, this.element1);
    }

    @Override
    public T get(int index)
    {
        if (index == 0)
        {
            return this.element1;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
    }

    /**
     * set is implemented purely to allow the List to be sorted, not because this List should be considered mutable.
     */
    @Override
    public T set(int index, T element)
    {
        if (index == 0)
        {
            T previousElement = this.element1;
            this.element1 = element;
            return previousElement;
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
    }

    @Override
    public SingletonList<T> sortThis(Comparator<? super T> comparator)
    {
        return this;
    }

    @Override
    public <V extends Comparable<? super V>> MutableList<T> sortThisBy(Function<? super T, ? extends V> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByInt(IntFunction<? super T> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByBoolean(BooleanFunction<? super T> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByChar(CharFunction<? super T> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByByte(ByteFunction<? super T> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByShort(ShortFunction<? super T> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByFloat(FloatFunction<? super T> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByLong(LongFunction<? super T> function)
    {
        return this;
    }

    @Override
    public MutableList<T> sortThisByDouble(DoubleFunction<? super T> function)
    {
        return this;
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
}
