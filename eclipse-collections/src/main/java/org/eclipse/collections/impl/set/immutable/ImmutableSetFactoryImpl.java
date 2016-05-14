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

import org.eclipse.collections.api.factory.set.ImmutableSetFactory;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.utility.Iterate;

public enum ImmutableSetFactoryImpl implements ImmutableSetFactory
{
    INSTANCE;

    @Override
    public <T> ImmutableSet<T> empty()
    {
        return (ImmutableSet<T>) ImmutableEmptySet.INSTANCE;
    }

    @Override
    public <T> ImmutableSet<T> of()
    {
        return this.empty();
    }

    @Override
    public <T> ImmutableSet<T> with()
    {
        return this.empty();
    }

    @Override
    public <T> ImmutableSet<T> of(T one)
    {
        return this.with(one);
    }

    @Override
    public <T> ImmutableSet<T> with(T one)
    {
        return new ImmutableSingletonSet<>(one);
    }

    @Override
    public <T> ImmutableSet<T> of(T one, T two)
    {
        return this.with(one, two);
    }

    @Override
    public <T> ImmutableSet<T> with(T one, T two)
    {
        if (Comparators.nullSafeEquals(one, two))
        {
            return this.of(one);
        }
        return new ImmutableDoubletonSet<>(one, two);
    }

    @Override
    public <T> ImmutableSet<T> of(T one, T two, T three)
    {
        return this.with(one, two, three);
    }

    @Override
    public <T> ImmutableSet<T> with(T one, T two, T three)
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
        return new ImmutableTripletonSet<>(one, two, three);
    }

    @Override
    public <T> ImmutableSet<T> of(T one, T two, T three, T four)
    {
        return this.with(one, two, three, four);
    }

    @Override
    public <T> ImmutableSet<T> with(T one, T two, T three, T four)
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
        return new ImmutableQuadrupletonSet<>(one, two, three, four);
    }

    @Override
    public <T> ImmutableSet<T> of(T... items)
    {
        return this.with(items);
    }

    @Override
    public <T> ImmutableSet<T> with(T... items)
    {
        if (items == null || items.length == 0)
        {
            return this.of();
        }

        switch (items.length)
        {
            case 1:
                return this.of(items[0]);
            case 2:
                return this.of(items[0], items[1]);
            case 3:
                return this.of(items[0], items[1], items[2]);
            case 4:
                return this.of(items[0], items[1], items[2], items[3]);
            default:
                return ImmutableUnifiedSet.newSetWith(items);
        }
    }

    @Override
    public <T> ImmutableSet<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    @Override
    public <T> ImmutableSet<T> withAll(Iterable<? extends T> items)
    {
        if (items instanceof ImmutableSet<?>)
        {
            return (ImmutableSet<T>) items;
        }

        if (Iterate.isEmpty(items))
        {
            return this.with();
        }
        return this.with((T[]) Iterate.toArray(items));
    }
}
