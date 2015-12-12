/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.fixed;

import net.jcip.annotations.Immutable;
import org.eclipse.collections.api.factory.set.FixedSizeSetFactory;
import org.eclipse.collections.api.set.FixedSizeSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

@Immutable
public class FixedSizeSetFactoryImpl implements FixedSizeSetFactory
{
    private static final FixedSizeSet<?> EMPTY_SET = new EmptySet<Object>();

    public <T> FixedSizeSet<T> empty()
    {
        return (FixedSizeSet<T>) FixedSizeSetFactoryImpl.EMPTY_SET;
    }

    public <T> FixedSizeSet<T> of()
    {
        return this.empty();
    }

    public <T> FixedSizeSet<T> with()
    {
        return this.empty();
    }

    public <T> FixedSizeSet<T> of(T one)
    {
        return this.with(one);
    }

    public <T> FixedSizeSet<T> with(T one)
    {
        return new SingletonSet<T>(one);
    }

    public <T> FixedSizeSet<T> of(T one, T two)
    {
        return this.with(one, two);
    }

    public <T> FixedSizeSet<T> with(T one, T two)
    {
        if (Comparators.nullSafeEquals(one, two))
        {
            return this.of(one);
        }
        return new DoubletonSet<T>(one, two);
    }

    public <T> FixedSizeSet<T> of(T one, T two, T three)
    {
        return this.with(one, two, three);
    }

    public <T> FixedSizeSet<T> with(T one, T two, T three)
    {
        if (Comparators.nullSafeEquals(one, two))
        {
            return this.of(one, three);
        }
        if (Comparators.nullSafeEquals(one, three))
        {
            return this.of(one, two);
        }
        if (Comparators.nullSafeEquals(two, three))
        {
            return this.of(one, two);
        }
        return new TripletonSet<T>(one, two, three);
    }

    public <T> FixedSizeSet<T> of(T one, T two, T three, T four)
    {
        return this.with(one, two, three, four);
    }

    public <T> FixedSizeSet<T> with(T one, T two, T three, T four)
    {
        if (Comparators.nullSafeEquals(one, two))
        {
            return this.of(one, three, four);
        }
        if (Comparators.nullSafeEquals(one, three))
        {
            return this.of(one, two, four);
        }
        if (Comparators.nullSafeEquals(one, four))
        {
            return this.of(one, two, three);
        }
        if (Comparators.nullSafeEquals(two, three))
        {
            return this.of(one, two, four);
        }
        if (Comparators.nullSafeEquals(two, four))
        {
            return this.of(one, two, three);
        }
        if (Comparators.nullSafeEquals(three, four))
        {
            return this.of(one, two, three);
        }
        return new QuadrupletonSet<T>(one, two, three, four);
    }

    public <T> MutableSet<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    public <T> MutableSet<T> withAll(Iterable<? extends T> items)
    {
        UnifiedSet<T> set = UnifiedSet.newSet(items);
        T[] itemArray;
        switch (set.size())
        {
            case 0:
                return new EmptySet<T>();
            case 1:
                itemArray = (T[]) set.toArray();
                return new SingletonSet<T>(itemArray[0]);
            case 2:
                itemArray = (T[]) set.toArray();
                return new DoubletonSet<T>(itemArray[0], itemArray[1]);
            case 3:
                itemArray = (T[]) set.toArray();
                return new TripletonSet<T>(itemArray[0], itemArray[1], itemArray[2]);
            case 4:
                itemArray = (T[]) set.toArray();
                return new QuadrupletonSet<T>(itemArray[0], itemArray[1], itemArray[2], itemArray[3]);
            default:
                return set;
        }
    }
}
