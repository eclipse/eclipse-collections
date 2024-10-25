/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.fixed;

import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.collections.api.factory.set.FixedSizeSetFactory;
import org.eclipse.collections.api.set.FixedSizeSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

@aQute.bnd.annotation.spi.ServiceProvider(FixedSizeSetFactory.class)
public class FixedSizeSetFactoryImpl implements FixedSizeSetFactory
{
    public static final FixedSizeSetFactory INSTANCE = new FixedSizeSetFactoryImpl();

    private static final FixedSizeSet<?> EMPTY_SET = new EmptySet<>();

    @Override
    public <T> FixedSizeSet<T> empty()
    {
        return (FixedSizeSet<T>) FixedSizeSetFactoryImpl.EMPTY_SET;
    }

    @Override
    public <T> FixedSizeSet<T> of()
    {
        return this.empty();
    }

    @Override
    public <T> FixedSizeSet<T> with()
    {
        return this.empty();
    }

    @Override
    public <T> FixedSizeSet<T> of(T one)
    {
        return this.with(one);
    }

    @Override
    public <T> FixedSizeSet<T> with(T one)
    {
        return new SingletonSet<>(one);
    }

    @Override
    public <T> FixedSizeSet<T> of(T one, T two)
    {
        return this.with(one, two);
    }

    @Override
    public <T> FixedSizeSet<T> with(T one, T two)
    {
        if (Objects.equals(one, two))
        {
            return this.of(one);
        }
        return new DoubletonSet<>(one, two);
    }

    @Override
    public <T> FixedSizeSet<T> of(T one, T two, T three)
    {
        return this.with(one, two, three);
    }

    @Override
    public <T> FixedSizeSet<T> with(T one, T two, T three)
    {
        if (Objects.equals(one, two))
        {
            return this.of(one, three);
        }
        if (Objects.equals(one, three))
        {
            return this.of(one, two);
        }
        if (Objects.equals(two, three))
        {
            return this.of(one, two);
        }
        return new TripletonSet<>(one, two, three);
    }

    @Override
    public <T> FixedSizeSet<T> of(T one, T two, T three, T four)
    {
        return this.with(one, two, three, four);
    }

    @Override
    public <T> FixedSizeSet<T> with(T one, T two, T three, T four)
    {
        if (Objects.equals(one, two))
        {
            return this.of(one, three, four);
        }
        if (Objects.equals(one, three))
        {
            return this.of(one, two, four);
        }
        if (Objects.equals(one, four))
        {
            return this.of(one, two, three);
        }
        if (Objects.equals(two, three))
        {
            return this.of(one, two, four);
        }
        if (Objects.equals(two, four))
        {
            return this.of(one, two, three);
        }
        if (Objects.equals(three, four))
        {
            return this.of(one, two, three);
        }
        return new QuadrupletonSet<>(one, two, three, four);
    }

    @Override
    public <T> MutableSet<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    @Override
    public <T> MutableSet<T> withAll(Iterable<? extends T> items)
    {
        MutableSet<T> set = UnifiedSet.newSet(items);
        T[] itemArray;
        switch (set.size())
        {
            case 0:
                return new EmptySet<>();
            case 1:
                itemArray = (T[]) set.toArray();
                return new SingletonSet<>(itemArray[0]);
            case 2:
                itemArray = (T[]) set.toArray();
                return new DoubletonSet<>(itemArray[0], itemArray[1]);
            case 3:
                itemArray = (T[]) set.toArray();
                return new TripletonSet<>(itemArray[0], itemArray[1], itemArray[2]);
            case 4:
                itemArray = (T[]) set.toArray();
                return new QuadrupletonSet<>(itemArray[0], itemArray[1], itemArray[2], itemArray[3]);
            default:
                return set;
        }
    }

    @Override
    public <T> MutableSet<T> fromStream(Stream<? extends T> stream)
    {
        MutableSet<T> set = stream.collect(Collectors2.toSet());
        T[] itemArray;
        switch (set.size())
        {
            case 0:
                return new EmptySet<>();
            case 1:
                itemArray = (T[]) set.toArray();
                return new SingletonSet<>(itemArray[0]);
            case 2:
                itemArray = (T[]) set.toArray();
                return new DoubletonSet<>(itemArray[0], itemArray[1]);
            case 3:
                itemArray = (T[]) set.toArray();
                return new TripletonSet<>(itemArray[0], itemArray[1], itemArray[2]);
            case 4:
                itemArray = (T[]) set.toArray();
                return new QuadrupletonSet<>(itemArray[0], itemArray[1], itemArray[2], itemArray[3]);
            default:
                return set;
        }
    }
}
