/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.tuple;

import java.util.Objects;

import org.eclipse.collections.api.tuple.Triple;

class TripleImpl<T1, T2, T3> implements Triple<T1, T2, T3>
{
    private static final long serialVersionUID = 1L;

    private final T1 one;
    private final T2 two;
    private final T3 three;

    TripleImpl(T1 one, T2 two, T3 three)
    {
        this.one = one;
        this.two = two;
        this.three = three;
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
    public T3 getThree()
    {
        return this.three;
    }

    @Override
    public TripleImpl<T3, T2, T1> reverse()
    {
        return new TripleImpl<>(this.three, this.two, this.one);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Triple))
        {
            return false;
        }

        Triple<?, ?, ?> that = (Triple<?, ?, ?>) o;

        return Objects.equals(this.one, that.getOne())
                && Objects.equals(this.two, that.getTwo())
                && Objects.equals(this.three, that.getThree());
    }

    @Override
    public int hashCode()
    {
        int result = this.one == null ? 0 : this.one.hashCode();
        result = 29 * result + (this.two == null ? 0 : this.two.hashCode());
        result = 43 * result + (this.three == null ? 0 : this.three.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        return this.one + ":" + this.two + ":" + this.three;
    }

    @Override
    public int compareTo(Triple<T1, T2, T3> other)
    {
        int i = ((Comparable<T1>) this.one).compareTo(other.getOne());
        if (i != 0)
        {
            return i;
        }
        int j = ((Comparable<T2>) this.two).compareTo(other.getTwo());
        if (j != 0)
        {
            return j;
        }
        return ((Comparable<T3>) this.three).compareTo(other.getThree());
    }
}
