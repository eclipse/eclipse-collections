/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.immutable;

import java.util.Comparator;

import org.eclipse.collections.api.bag.sorted.ImmutableSortedBag;
import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.factory.bag.sorted.ImmutableSortedBagFactory;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.utility.Iterate;

public enum ImmutableSortedBagFactoryImpl implements ImmutableSortedBagFactory
{
    INSTANCE;

    @Override
    public <T> ImmutableSortedBag<T> empty()
    {
        return (ImmutableSortedBag<T>) ImmutableEmptySortedBag.INSTANCE;
    }

    @Override
    public <T> ImmutableSortedBag<T> empty(Comparator<? super T> comparator)
    {
        return new ImmutableEmptySortedBag<>(comparator);
    }

    @Override
    public <T> ImmutableSortedBag<T> of()
    {
        return this.with();
    }

    @Override
    public <T> ImmutableSortedBag<T> with()
    {
        return this.empty();
    }

    @Override
    public <T> ImmutableSortedBag<T> of(T... items)
    {
        return this.with(items);
    }

    @Override
    public <T> ImmutableSortedBag<T> with(T... items)
    {
        if (items == null || items.length == 0)
        {
            return this.of();
        }
        return new ImmutableSortedBagImpl<>(TreeBag.newBagWith(items));
    }

    @Override
    public <T> ImmutableSortedBag<T> ofAll(Iterable<? extends T> items)
    {
        return this.withAll(items);
    }

    @Override
    public <T> ImmutableSortedBag<T> withAll(Iterable<? extends T> items)
    {
        if (items instanceof ImmutableSortedBag<?>)
        {
            return (ImmutableSortedBag<T>) items;
        }

        return this.of((T[]) Iterate.toArray(items));
    }

    @Override
    public <T> ImmutableSortedBag<T> of(Comparator<? super T> comparator)
    {
        return this.with(comparator);
    }

    @Override
    public <T> ImmutableSortedBag<T> with(Comparator<? super T> comparator)
    {
        if (comparator == null)
        {
            return this.of();
        }
        return new ImmutableEmptySortedBag<>(comparator);
    }

    @Override
    public <T> ImmutableSortedBag<T> of(Comparator<? super T> comparator, T... items)
    {
        return this.with(comparator, items);
    }

    @Override
    public <T> ImmutableSortedBag<T> with(Comparator<? super T> comparator, T... items)
    {
        if (items == null || items.length == 0)
        {
            return this.of(comparator);
        }
        return new ImmutableSortedBagImpl<>(TreeBag.newBagWith(comparator, items));
    }

    @Override
    public <T> ImmutableSortedBag<T> ofAll(Comparator<? super T> comparator, Iterable<? extends T> items)
    {
        return this.withAll(comparator, items);
    }

    @Override
    public <T> ImmutableSortedBag<T> withAll(Comparator<? super T> comparator, Iterable<? extends T> items)
    {
        return this.of(comparator, (T[]) Iterate.toArray(items));
    }

    @Override
    public <T> ImmutableSortedBag<T> ofSortedBag(SortedBag<T> bag)
    {
        return this.withSortedBag(bag);
    }

    @Override
    public <T> ImmutableSortedBag<T> withSortedBag(SortedBag<T> bag)
    {
        if (bag instanceof ImmutableSortedBag)
        {
            return (ImmutableSortedBag<T>) bag;
        }
        if (bag.isEmpty())
        {
            return this.of(bag.comparator());
        }
        return new ImmutableSortedBagImpl<>(bag);
    }
}
