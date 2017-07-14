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

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.EmptyIterator;
import org.eclipse.collections.impl.factory.Lists;

/**
 * This class is a memory efficient list with no elements.  It is created by calling Lists.fixedSize.of() which
 * actually returns a singleton instance.
 */
final class EmptyList<T>
        extends AbstractMemoryEfficientMutableList<T>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Object readResolve()
    {
        return Lists.fixedSize.empty();
    }

    @Override
    public SingletonList<T> with(T value)
    {
        return new SingletonList<>(value);
    }

    // Weird implementation of clone() is ok on final classes

    @Override
    public EmptyList<T> clone()
    {
        return this;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean contains(Object obj)
    {
        return false;
    }

    @Override
    public T get(int index)
    {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
    }

    @Override
    public T set(int index, T element)
    {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
    }

    @Override
    public EmptyList<T> sortThis(Comparator<? super T> comparator)
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
    public ListIterator<T> listIterator()
    {
        return EmptyIterator.getInstance();
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        if (index != 0)
        {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
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
    public <S> MutableList<Pair<T, S>> zip(Iterable<S> that)
    {
        return Lists.fixedSize.empty();
    }

    @Override
    public MutableList<Pair<T, Integer>> zipWithIndex()
    {
        return Lists.fixedSize.empty();
    }

    @Override
    public ImmutableList<T> toImmutable()
    {
        return Lists.immutable.empty();
    }
}
