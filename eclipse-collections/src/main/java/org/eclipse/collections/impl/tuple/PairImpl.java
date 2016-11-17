/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import java.util.Map;

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.block.factory.Comparators;

/**
 * A PairImpl is a container that holds two related objects.  It is the equivalent of an Association in Smalltalk, or an
 * implementation of Map.Entry in the JDK.
 */
class PairImpl<T1, T2>
        implements Pair<T1, T2>
{
    private static final long serialVersionUID = 1L;

    private final T1 one;
    private final T2 two;

    PairImpl(T1 newOne, T2 newTwo)
    {
        this.one = newOne;
        this.two = newTwo;
    }

    @Override
    public T1 getOne()
    {
        return this.one;
    }

    @Override
    public T2 getTwo()
    {
        return this.two;
    }

    @Override
    public void put(Map<T1, T2> map)
    {
        map.put(this.one, this.two);
    }

    @Override
    public PairImpl<T2, T1> swap()
    {
        return new PairImpl<>(this.two, this.one);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Pair))
        {
            return false;
        }

        Pair<?, ?> that = (Pair<?, ?>) o;

        return Comparators.nullSafeEquals(this.one, that.getOne())
                && Comparators.nullSafeEquals(this.two, that.getTwo());
    }

    @Override
    public int hashCode()
    {
        int result = this.one == null ? 0 : this.one.hashCode();
        result = 29 * result + (this.two == null ? 0 : this.two.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return this.one + ":" + this.two;
    }

    @Override
    public Map.Entry<T1, T2> toEntry()
    {
        return ImmutableEntry.of(this.one, this.two);
    }

    @Override
    public int compareTo(Pair<T1, T2> other)
    {
        int i = ((Comparable<T1>) this.one).compareTo(other.getOne());
        if (i != 0)
        {
            return i;
        }
        return ((Comparable<T2>) this.two).compareTo(other.getTwo());
    }
}
